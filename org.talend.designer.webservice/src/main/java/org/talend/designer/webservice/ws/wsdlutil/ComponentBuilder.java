package org.talend.designer.webservice.ws.wsdlutil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.wsdl.Binding;
import javax.wsdl.BindingInput;
import javax.wsdl.BindingOperation;
import javax.wsdl.BindingOutput;
import javax.wsdl.Definition;
import javax.wsdl.Input;
import javax.wsdl.Message;
import javax.wsdl.Operation;
import javax.wsdl.Output;
import javax.wsdl.Part;
import javax.wsdl.Port;
import javax.wsdl.Service;
import javax.wsdl.extensions.ExtensibilityElement;
import javax.wsdl.extensions.UnknownExtensibilityElement;
import javax.wsdl.extensions.soap.SOAPAddress;
import javax.wsdl.extensions.soap.SOAPBinding;
import javax.wsdl.extensions.soap.SOAPBody;
import javax.wsdl.extensions.soap.SOAPOperation;
import javax.wsdl.factory.WSDLFactory;
import javax.xml.namespace.QName;

import org.apache.ws.commons.schema.XmlSchemaCollection;
import org.exolab.castor.xml.schema.ComplexType;
import org.exolab.castor.xml.schema.ElementDecl;
import org.exolab.castor.xml.schema.Group;
import org.exolab.castor.xml.schema.Particle;
import org.exolab.castor.xml.schema.Schema;
import org.exolab.castor.xml.schema.Structure;
import org.exolab.castor.xml.schema.XMLType;
import org.jdom.input.DOMBuilder;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.designer.webservice.ws.wsdlinfo.OperationInfo;
import org.talend.designer.webservice.ws.wsdlinfo.ParameterInfo;
import org.talend.designer.webservice.ws.wsdlinfo.PortNames;
import org.talend.designer.webservice.ws.wsdlinfo.ServiceInfo;

/**
 * DOC gcui class global comment. Detailled comment
 */
public class ComponentBuilder {

    WSDLFactory wsdlFactory = null;

    private Vector wsdlTypes = new Vector();

    private Vector testWsdlTypes = new Vector();

    private int inOrOut;

    private List parametersName = new ArrayList();

    private String documentBaseURI = null;

    private XmlSchemaCollection schemaCollection;

    public String exceptionMessage = "";

    public final static String DEFAULT_SOAP_ENCODING_STYLE = "http://schemas.xmlsoap.org/soap/encoding/";

    // SimpleTypesFactory simpleTypesFactory = null;

    public ComponentBuilder() {
        try {
            wsdlFactory = WSDLFactory.newInstance();
            // simpleTypesFactory = new SimpleTypesFactory();
        } catch (Exception e) {
            exceptionMessage = exceptionMessage + e.getMessage();
            ExceptionHandler.process(e);
        }
    }

    public ServiceInfo buildserviceinformation(ServiceInfo serviceinfo) throws Exception {
        // WSDLReader reader = wsdlFactory.newWSDLReader();
        // Definition def = reader.readWSDL(null, serviceinfo.getWsdlUri());
        ServiceDiscoveryHelper sdh;
        if (serviceinfo.getAuthConfig() != null && serviceinfo.getWsdlUri().indexOf("http") == 0) {
            sdh = new ServiceDiscoveryHelper(serviceinfo.getWsdlUri(), serviceinfo.getAuthConfig());

        } else {
            sdh = new ServiceDiscoveryHelper(serviceinfo.getWsdlUri());
        }
        Definition def = sdh.getDefinition();

        wsdlTypes = createSchemaFromTypes(def);

        Map services = def.getServices();
        if (services != null) {
            Iterator svcIter = services.values().iterator();
            populateComponent(serviceinfo, (Service) svcIter.next());
        }
        return serviceinfo;
    }

    protected Vector createSchemaFromTypes(Definition wsdlDefinition) {
        Vector schemas = new Vector();
        org.w3c.dom.Element schemaElementt = null;
        Map importElement = null;
        List includeElement = null;
        if (wsdlDefinition.getTypes() != null) {
            Vector schemaExtElem = findExtensibilityElement(wsdlDefinition.getTypes().getExtensibilityElements(), "schema");
            for (int i = 0; i < schemaExtElem.size(); i++) {
                ExtensibilityElement schemaElement = (ExtensibilityElement) schemaExtElem.elementAt(i);
                if (schemaElement != null && schemaElement instanceof UnknownExtensibilityElement) {
                    schemaElementt = ((UnknownExtensibilityElement) schemaElement).getElement();

                    String documentBase = ((javax.wsdl.extensions.schema.Schema) schemaElement).getDocumentBaseURI();
                    Schema schema = createschemafromtype(schemaElementt, wsdlDefinition, documentBase);
                    if (schema != null) {
                        schemas.add(schema);
                    }
                    importElement = ((javax.wsdl.extensions.schema.Schema) schemaElement).getImports();
                    if (importElement != null && importElement.size() > 0) {
                        findImportSchema(wsdlDefinition, schemas, importElement);
                    }
                }

                if (schemaElement != null && schemaElement instanceof javax.wsdl.extensions.schema.Schema) {
                    schemaElementt = ((javax.wsdl.extensions.schema.Schema) schemaElement).getElement();
                    String documentBase = ((javax.wsdl.extensions.schema.Schema) schemaElement).getDocumentBaseURI();
                    Boolean isHaveImport = false;
                    importElement = ((javax.wsdl.extensions.schema.Schema) schemaElement).getImports();
                    if (importElement != null && importElement.size() > 0) {

                        isHaveImport = true;
                        // validateImportUrlPath(importElement);
                    }

                    Schema schema = createschemafromtype(schemaElementt, wsdlDefinition, documentBase);
                    if (schema != null) {
                        schemas.add(schema);
                    }

                    if (isHaveImport) {
                        findImportSchema(wsdlDefinition, schemas, importElement);
                    }
                }
            }

        }
        return schemas;
    }

    /**
     * DOC gcui Comment method "validateImportUrlPath".
     * 
     * @param importElement
     * @throws URISyntaxException
     */
    private void validateImportUrlPath(Map importElement) {
        org.w3c.dom.Element schemaElementt;
        Iterator keyIterator = importElement.keySet().iterator();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next().toString();
            Vector importEle = (Vector) importElement.get(key);

            for (int i = 0; i < importEle.size(); i++) {
                com.ibm.wsdl.extensions.schema.SchemaImportImpl importImpl = (com.ibm.wsdl.extensions.schema.SchemaImportImpl) importEle
                        .elementAt(i);
                if (importImpl.getReferencedSchema() != null && importImpl.getSchemaLocationURI() != null) {
                    URI schemaLocationURI = null;
                    try {
                        schemaLocationURI = new URI(importImpl.getSchemaLocationURI());
                    } catch (URISyntaxException e) {
                        exceptionMessage = exceptionMessage + e.getMessage();
                        e.printStackTrace();
                    }
                    if (schemaLocationURI != null && !schemaLocationURI.isAbsolute()) {
                        importImpl.setSchemaLocationURI(importImpl.getReferencedSchema().getDocumentBaseURI());
                    }
                }
            }
        }
    }

    /**
     * DOC gcui Comment method "findIncludesSchema".
     * 
     * @param wsdlDefinition
     * @param schemas
     * @param includeElement
     */
    private void findIncludesSchema(Definition wsdlDefinition, Vector schemas, List includeElement) {
        org.w3c.dom.Element schemaElementt;
        for (int i = 0; i < includeElement.size(); i++) {

            schemaElementt = ((com.ibm.wsdl.extensions.schema.SchemaReferenceImpl) includeElement.get(i)).getReferencedSchema()
                    .getElement();
            String documentBase = ((com.ibm.wsdl.extensions.schema.SchemaReferenceImpl) includeElement.get(i))
                    .getReferencedSchema().getDocumentBaseURI();
            Schema schemaInclude = createschemafromtype(schemaElementt, wsdlDefinition, documentBase);
            if (schemaInclude != null) {
                schemas.add(schemaInclude);
            }
        }
    }

    private void findImportSchema(Definition wsdlDefinition, Vector schemas, Map importElement) {
        org.w3c.dom.Element schemaElementt;
        List includeElement = null;
        Iterator keyIterator = importElement.keySet().iterator();
        Boolean isHaveImport = false;
        while (keyIterator.hasNext()) {
            String key = keyIterator.next().toString();
            Vector importEle = (Vector) importElement.get(key);

            for (int i = 0; i < importEle.size(); i++) {
                com.ibm.wsdl.extensions.schema.SchemaImportImpl importImpl = (com.ibm.wsdl.extensions.schema.SchemaImportImpl) importEle
                        .elementAt(i);
                if (importImpl.getReferencedSchema() != null) {

                    schemaElementt = importImpl.getReferencedSchema().getElement();
                    String documentBase = importImpl.getReferencedSchema().getDocumentBaseURI();

                    if ((com.ibm.wsdl.extensions.schema.SchemaImportImpl) importEle.elementAt(i) != null) {
                        if (((com.ibm.wsdl.extensions.schema.SchemaImportImpl) importEle.elementAt(i)).getReferencedSchema() != null) {
                            importElement = ((com.ibm.wsdl.extensions.schema.SchemaImportImpl) importEle.elementAt(i))
                                    .getReferencedSchema().getImports();
                            if (importElement != null && importElement.size() > 0) {
                                isHaveImport = true;
                                // validateImportUrlPath(importElement);
                            }
                        }
                    }

                    Schema schemaImport = createschemafromtype(schemaElementt, wsdlDefinition, documentBase);
                    if (schemaImport != null) {
                        schemas.add(schemaImport);
                    }
                }

                if (isHaveImport) {
                    findImportSchema(wsdlDefinition, schemas, importElement);
                }

                if ((com.ibm.wsdl.extensions.schema.SchemaImportImpl) importEle.elementAt(i) != null) {
                    if (((com.ibm.wsdl.extensions.schema.SchemaImportImpl) importEle.elementAt(i)).getReferencedSchema() != null) {
                        includeElement = ((com.ibm.wsdl.extensions.schema.SchemaImportImpl) importEle.elementAt(i))
                                .getReferencedSchema().getIncludes();
                        if (includeElement != null && includeElement.size() > 0) {

                            findIncludesSchema(wsdlDefinition, schemas, includeElement);
                        }
                    }
                }

            }
        }
    }

    private Vector findExtensibilityElement(List extensibilityElements, String elementType) {

        Vector elements = new Vector();
        if (extensibilityElements != null) {
            Iterator iter = extensibilityElements.iterator();
            while (iter.hasNext()) {
                ExtensibilityElement elment = (ExtensibilityElement) iter.next();
                if (elment.getElementType().getLocalPart().equalsIgnoreCase(elementType)) {
                    elements.add(elment);
                }
            }
        }
        return elements;
    }

    private Schema createschemafromtype(org.w3c.dom.Element schemaElement, Definition wsdlDefinition, String documentBase) {
        if (schemaElement == null) {
            exceptionMessage = exceptionMessage + "Unable to find schema extensibility element in WSDL";
            return null;
        }
        DOMBuilder domBuilder = new DOMBuilder();
        String namespaceURI = schemaElement.getNamespaceURI();
        org.jdom.Element jdomSchemaElement = domBuilder.build(schemaElement);
        if (jdomSchemaElement == null) {
            exceptionMessage = exceptionMessage + "Unable to read schema defined in WSDL";
            return null;
        }
        Map namespaces = wsdlDefinition.getNamespaces();
        if (namespaces != null && !namespaces.isEmpty()) {
            Iterator nsIter = namespaces.keySet().iterator();
            Boolean isContainNS = false;
            while (nsIter.hasNext()) {
                String nsPrefix = (String) nsIter.next();
                String nsURI = (String) namespaces.get(nsPrefix);
                if (nsURI.equals(namespaceURI)) {
                    isContainNS = true;
                }
                if (nsPrefix != null && nsPrefix.length() > 0) {
                    org.jdom.Namespace nsDecl = org.jdom.Namespace.getNamespace(nsPrefix, nsURI);
                    jdomSchemaElement.addNamespaceDeclaration(nsDecl);
                }
            }
            // if (!isContainNS) {
            // org.jdom.Namespace nsDecl = org.jdom.Namespace.getNamespace("talend", namespaceURI);
            // jdomSchemaElement.addNamespaceDeclaration(nsDecl);
            // }
        }
        jdomSchemaElement.detach();
        Schema schema = null;
        try {
            schema = XMLSupport.convertElementToSchema(jdomSchemaElement, documentBase);
        } catch (Exception e) {
            exceptionMessage = exceptionMessage + e.getMessage();
            ExceptionHandler.process(e);
        }
        return schema;
    }

    private ServiceInfo populateComponent(ServiceInfo component, Service service) {
        QName qName = service.getQName();
        String namespace = qName.getNamespaceURI();
        String name = qName.getLocalPart();
        component.setServerName(name);
        component.setServerNameSpace(namespace);
        Map ports = service.getPorts();
        Iterator portIter = ports.values().iterator();
        while (portIter.hasNext()) {
            Port port = (Port) portIter.next();
            Binding binding = port.getBinding();
            if (port.getName() != null && component.getPortNames() == null) {
                List<PortNames> portNames = new ArrayList();
                PortNames portName = new PortNames();
                portName.setPortName(port.getName());
                portNames.add(portName);
                component.setPortNames(portNames);
            } else if (port.getName() != null && component.getPortNames() != null) {
                PortNames portName = new PortNames();
                portName.setPortName(port.getName());
                component.getPortNames().add(portName);
            }
            List operations = buildOperations(binding);
            Iterator operIter = operations.iterator();
            while (operIter.hasNext()) {
                OperationInfo operation = (OperationInfo) operIter.next();
                Vector addrElems = findExtensibilityElement(port.getExtensibilityElements(), "address");
                ExtensibilityElement element = (ExtensibilityElement) addrElems.elementAt(0);
                if (element != null && element instanceof SOAPAddress) {
                    SOAPAddress soapAddr = (SOAPAddress) element;
                    operation.setTargetURL(soapAddr.getLocationURI());
                }
                component.addOperation(operation);
            }
        }
        return component;
    }

    private List buildOperations(Binding binding) {
        List operationInfos = new ArrayList();

        List operations = binding.getBindingOperations();

        if (operations != null && !operations.isEmpty()) {

            Vector soapBindingElems = findExtensibilityElement(binding.getExtensibilityElements(), "binding");
            String style = "document"; // default

            ExtensibilityElement soapBindingElem = (ExtensibilityElement) soapBindingElems.elementAt(0);
            if (soapBindingElem != null && soapBindingElem instanceof SOAPBinding) {
                SOAPBinding soapBinding = (SOAPBinding) soapBindingElem;
                style = soapBinding.getStyle();
            }

            Iterator opIter = operations.iterator();

            while (opIter.hasNext()) {
                BindingOperation oper = (BindingOperation) opIter.next();
                Vector operElems = findExtensibilityElement(oper.getExtensibilityElements(), "operation");
                ExtensibilityElement operElem = (ExtensibilityElement) operElems.elementAt(0);
                if (operElem != null && operElem instanceof SOAPOperation) {

                    OperationInfo operationInfo = new OperationInfo(style);
                    buildOperation(operationInfo, oper);
                    operationInfos.add(operationInfo);
                }
            }
        }

        return operationInfos;
    }

    private OperationInfo buildOperation(OperationInfo operationInfo, BindingOperation bindingOper) {
        Operation oper = bindingOper.getOperation();
        operationInfo.setTargetMethodName(oper.getName());
        Vector operElems = findExtensibilityElement(bindingOper.getExtensibilityElements(), "operation");
        ExtensibilityElement operElem = (ExtensibilityElement) operElems.elementAt(0);
        if (operElem != null && operElem instanceof SOAPOperation) {
            SOAPOperation soapOperation = (SOAPOperation) operElem;
            operationInfo.setSoapActionURI(soapOperation.getSoapActionURI());
        }
        BindingInput bindingInput = bindingOper.getBindingInput();
        BindingOutput bindingOutput = bindingOper.getBindingOutput();
        Vector bodyElems = findExtensibilityElement(bindingInput.getExtensibilityElements(), "body");
        ExtensibilityElement bodyElem = (ExtensibilityElement) bodyElems.elementAt(0);

        if (bodyElem != null && bodyElem instanceof SOAPBody) {
            SOAPBody soapBody = (SOAPBody) bodyElem;
            List styles = soapBody.getEncodingStyles();
            String encodingStyle = null;
            if (styles != null) {
                encodingStyle = styles.get(0).toString();
            }
            if (encodingStyle == null) {
                encodingStyle = DEFAULT_SOAP_ENCODING_STYLE;
            }
            operationInfo.setEncodingStyle(encodingStyle.toString());
            operationInfo.setTargetObjectURI(soapBody.getNamespaceURI());
        }

        Input inDef = oper.getInput();
        if (inDef != null) {
            Message inMsg = inDef.getMessage();
            if (inMsg != null) {
                operationInfo.setInputMessageName(inMsg.getQName().getLocalPart());
                getParameterFromMessage(operationInfo, inMsg, 1);
                operationInfo.setInmessage(inMsg);
            }
        }

        Output outDef = oper.getOutput();
        if (outDef != null) {
            Message outMsg = outDef.getMessage();
            if (outMsg != null) {
                operationInfo.setOutputMessageName(outMsg.getQName().getLocalPart());
                getParameterFromMessage(operationInfo, outMsg, 2);
                operationInfo.setOutmessage(outMsg);
            }
        }

        return operationInfo;
    }

    private void getParameterFromMessage(OperationInfo operationInfo, Message msg, int manner) {
        String tip = "";
        inOrOut = manner;
        if (manner == 1) {
            tip = "input";
        } else {
            tip = "output";
        }

        List msgParts = msg.getOrderedParts(null);
        msg.getQName();
        Schema wsdlType = null;
        Iterator iter = msgParts.iterator();
        while (iter.hasNext()) {
            Part part = (Part) iter.next();
            String targetnamespace = "";
            ComplexType complexType = null;
            XMLType xmlType = getXMLType(part, wsdlType, operationInfo);

            if (xmlType == null) {
                for (int i = 0; i < wsdlTypes.size(); i++) {
                    wsdlType = (Schema) (wsdlTypes.elementAt(i));
                    if (wsdlType == null) {
                        continue;
                    }
                    if (wsdlType != null && wsdlType.getTargetNamespace() != null) {
                        targetnamespace = wsdlType.getTargetNamespace();
                    }
                    operationInfo.setNamespaceURI(targetnamespace);
                    if (part.getTypeName() != null) {
                        if (part.getTypeName().getLocalPart() != null) {
                            complexType = wsdlType.getComplexType(part.getTypeName().getLocalPart());
                        }
                    }
                }
            }

            if (xmlType != null && xmlType.isComplexType()) {
                if (part.getName() != null) {
                    ParameterInfo parameter = new ParameterInfo();
                    String partName = part.getName();
                    parameter.setName(partName);
                    if (xmlType.getName() != null) {
                        // if (part.getTypeName().getLocalPart() != null) {
                        parameter.setKind(xmlType.getName());
                        // }
                    }
                    if (manner == 1) {
                        operationInfo.addInparameter(parameter);
                    } else {
                        operationInfo.addOutparameter(parameter);
                    }
                    buildComplexParameter((ComplexType) xmlType, parameter, operationInfo, 3);
                } else {
                    buildComplexParameter((ComplexType) xmlType, null, operationInfo, manner);
                }
            } else if (xmlType == null && part.getTypeName() != null && part.getTypeName().getLocalPart() != null
                    && complexType != null) {
                ParameterInfo parameter = new ParameterInfo();
                if (part.getName() != null) {
                    String partName = part.getName();
                    parameter.setName(partName);
                    if (part.getTypeName() != null) {
                        if (part.getTypeName().getLocalPart() != null) {
                            parameter.setKind(part.getTypeName().getLocalPart());
                        }
                    }
                    if (manner == 1) {
                        operationInfo.addInparameter(parameter);
                    } else {
                        operationInfo.addOutparameter(parameter);
                    }
                    buildComplexParameter(complexType, parameter, operationInfo, 3);
                } else {
                    buildComplexParameter(complexType, null, operationInfo, manner);
                }
            } else {
                String partName = part.getName();
                ParameterInfo parameter = new ParameterInfo();
                parameter.setName(partName);
                if (part.getTypeName() != null) {
                    if (part.getTypeName().getLocalPart() != null) {
                        parameter.setKind(part.getTypeName().getLocalPart());
                    }
                } else if (xmlType != null) {
                    if (xmlType.getName() != null) {
                        parameter.setKind(xmlType.getName());
                    }
                }

                // if ((xmlType.getMaxOccurs()) - (elementDecl.getMinOccurs()) > 1 && parentParameterInfo != null) {
                // parameter1.setArraySize((elementDecl.getMaxOccurs()) - (elementDecl.getMinOccurs()));
                // } else if (elementDecl.getMaxOccurs() == -1 && parentParameterInfo != null) {
                // parameter1.setArraySize(-1);
                // }
                if (manner == 1) {
                    operationInfo.addInparameter(parameter);
                } else {
                    operationInfo.addOutparameter(parameter);
                }
            }
            operationInfo.setWsdltype(wsdlTypes);
        }
    }

    private void buildComplexParameter(ComplexType type, ParameterInfo parentParameterInfo, OperationInfo containOperationInfo,
            int manner) {
        // manner==1 is input,manner==2 is output,manner==4 is recursion.
        if (manner < 4) {
            parametersName.clear();
            parametersName.add(parentParameterInfo.getName());
        } else if (manner == 4) {
            parametersName.add(parentParameterInfo.getName());
        }
        Enumeration particleEnum = type.enumerate();
        Group group = null;
        if (!particleEnum.hasMoreElements()) {
        }
        while (particleEnum.hasMoreElements()) {
            Particle particle = (Particle) particleEnum.nextElement();
            if (particle instanceof Group) {
                group = (Group) particle;
                break;
            }
        }
        if (group != null) {

            Enumeration groupEnum = group.enumerate();
            while (groupEnum.hasMoreElements()) {
                Structure item = (Structure) groupEnum.nextElement();
                if (item.getStructureType() == Structure.ELEMENT) {
                    ElementDecl elementDecl = (ElementDecl) item;
                    XMLType xmlType = elementDecl.getType();

                    if (xmlType != null && xmlType.isComplexType()) {

                        ParameterInfo parameter1 = createSimpleParamInfor(containOperationInfo, elementDecl, manner);

                        if ((elementDecl.getMaxOccurs()) - (elementDecl.getMinOccurs()) > 1) {
                            int arraySize = (elementDecl.getMaxOccurs()) - (elementDecl.getMinOccurs());
                            Integer arraySizeString = new Integer(arraySize);
                            parameter1.setArraySize(arraySize);
                            parameter1.setIndex(arraySizeString.toString());
                        } else if (elementDecl.getMaxOccurs() == -1) {
                            parameter1.setArraySize(-1);
                            parameter1.setIndex("*");
                        }

                        if (parentParameterInfo != null) {
                            parameter1.setParent(parentParameterInfo);
                            parentParameterInfo.getParameterInfos().add(parameter1);
                        }
                        if (!parametersName.isEmpty() && parameter1.getName() != null) {
                            Boolean isHave = false;
                            for (int p = 0; p < parametersName.size(); p++) {
                                if (parameter1.getName().equals(parametersName.get(p))) {
                                    isHave = true;
                                }
                            }
                            if (!isHave) {
                                buildComplexParameter((ComplexType) xmlType, parameter1, containOperationInfo, 4);
                            }
                        }

                    } else {
                        ParameterInfo param = createSimpleParamInfor(containOperationInfo, elementDecl, manner);
                        if (parentParameterInfo != null) {
                            param.setParent(parentParameterInfo);
                            parentParameterInfo.getParameterInfos().add(param);
                        }
                    }
                }
            }
        }
    }

    private ParameterInfo createSimpleParamInfor(OperationInfo containOperationInfo, ElementDecl elementDecl, int input) {
        ParameterInfo parameter1 = new ParameterInfo();
        parameter1.setName(elementDecl.getName());
        if (elementDecl.getType() != null) {
            if (elementDecl.getType().getName() != null) {
                parameter1.setKind(elementDecl.getType().getName());
            }
        }
        if (elementDecl.getSchema() != null) {
            if (elementDecl.getSchema().getTargetNamespace() != null) {
                parameter1.setNameSpace(elementDecl.getSchema().getTargetNamespace());
            }
        }

        if ((elementDecl.getMaxOccurs()) - (elementDecl.getMinOccurs()) > 1) {
            int arraySize = (elementDecl.getMaxOccurs()) - (elementDecl.getMinOccurs());
            Integer arraySizeString = new Integer(arraySize);
            parameter1.setArraySize(arraySize);
            parameter1.setIndex(arraySizeString.toString());
        } else if (elementDecl.getMaxOccurs() == -1) {
            parameter1.setArraySize(-1);
            parameter1.setIndex("*");
        }
        if (input == 1) {
            containOperationInfo.addInparameter(parameter1);
        } else if (input == 2) {
            containOperationInfo.addOutparameter(parameter1);
        }
        return parameter1;
    }

    protected XMLType getXMLType(Part part, Schema wsdlType, OperationInfo operationInfo) {
        if (wsdlTypes == null) {
            exceptionMessage = exceptionMessage + "null is here in the input";
            return null;
        }

        XMLType xmlType = null;

        if (part.getElementName() != null) {
            String elemName = part.getElementName().getLocalPart();
            ElementDecl elemDecl = null;
            for (int i = 0; i < wsdlTypes.size(); i++) {
                wsdlType = (Schema) (wsdlTypes.elementAt(i));
                if (wsdlType == null) {
                    continue;
                }
                if (wsdlType != null && wsdlType.getTargetNamespace() != null) {
                    String targetnamespace = wsdlType.getTargetNamespace();
                    operationInfo.setNamespaceURI(targetnamespace);
                }
                elemDecl = wsdlType.getElementDecl(elemName);
                if (elemDecl != null) {
                    break;
                }

            }
            if (elemDecl != null) {
                // elemDecl.getType();

                xmlType = elemDecl.getType();
                if (xmlType == null) {
                    elemDecl.getReference();
                }
                // System.out.println(xmlType);
            }

        }
        // else if (part.getTypeName() != null) {
        // String elemName = part.getTypeName().getLocalPart();
        // ElementDecl elemDecl = null;
        // for (int i = 0; i < wsdlTypes.size(); i++) {
        // wsdlType = (Schema) (wsdlTypes.elementAt(i));
        // String targetnamespace = wsdlType.getTargetNamespace();
        // operationInfo.setNamespaceURI(targetnamespace);
        // ComplexType complexType = wsdlType.getComplexType(part.getTypeName().getLocalPart());
        // if (complexType != null) {
        // buildComplexParameter(complexType, null, operationInfo, inOrOut);
        // }
        // }
        //
        // }

        return xmlType;
    }

    public String getExceptionMessage() {
        return this.exceptionMessage;
    }
}
