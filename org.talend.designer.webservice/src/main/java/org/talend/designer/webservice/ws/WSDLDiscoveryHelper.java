package org.talend.designer.webservice.ws;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.talend.core.model.utils.TalendTextUtils;
import org.talend.designer.webservice.ws.helper.conf.ServiceHelperConfiguration;
import org.talend.designer.webservice.ws.wsdlinfo.Function;
import org.talend.designer.webservice.ws.wsdlinfo.OperationInfo;
import org.talend.designer.webservice.ws.wsdlinfo.ParameterInfo;
import org.talend.designer.webservice.ws.wsdlinfo.ServiceInfo;
import org.talend.designer.webservice.ws.wsdlutil.ComponentBuilder;

/**
 * 
 * @author gcui
 */
public class WSDLDiscoveryHelper {

    public static List<Function> functionsAvailable;

    public static List<ParameterInfo> inputParameters;

    public static List<ParameterInfo> outputParameters;

    /**
     * DOC gcui Comment method "getFunctionsAvailable".
     * 
     * @param wsdlURI
     * @return
     */
    public static List<Function> getFunctionsAvailable(String wsdlURI, ServiceHelperConfiguration config) {
        functionsAvailable = new ArrayList();
        wsdlURI = TalendTextUtils.removeQuotes(wsdlURI);
        try {
            ComponentBuilder builder = new ComponentBuilder();

            ServiceInfo serviceInfo = new ServiceInfo();
            serviceInfo.setWsdlUri(wsdlURI);
            serviceInfo.setAuthConfig(config);
            serviceInfo = builder.buildserviceinformation(serviceInfo);

            Iterator iter = serviceInfo.getOperations();
            while (iter.hasNext()) {

                OperationInfo oper = (OperationInfo) iter.next();
                // operationName
                Function f = new Function(oper.getTargetMethodName());
                String operationName = oper.getTargetMethodName() + "(";

                if (serviceInfo.getServerName() != null) {
                    f.setServerName(serviceInfo.getServerName());
                }
                if (serviceInfo.getServerNameSpace() != null) {
                    f.setServerNameSpace(serviceInfo.getServerNameSpace());
                }
                if (serviceInfo.getPortNames() != null) {
                    f.setPortNames(serviceInfo.getPortNames());
                }
                if (oper.getSoapActionURI() != null) {
                    f.setSoapAction(oper.getSoapActionURI());
                }
                if (oper.getNamespaceURI() != null) {
                    f.setNameSpaceURI(oper.getNamespaceURI());
                }
                if (oper.getEncodingStyle() != null) {
                    f.setEncodingStyle(oper.getEncodingStyle());
                }

                if (oper.getTargetURL() != null) {
                    f.setAddressLocation(oper.getTargetURL());
                }

                List inps = oper.getInparameters();
                List outps = oper.getOutparameters();
                // input parameters
                inputParameters = new ArrayList();
                if (inps.size() == 0) {
                    inputParameters.add(new ParameterInfo());
                    operationName = operationName + "):";
                } else if (inps.size() > 0 && inps != null) {
                    for (Iterator iterator1 = inps.iterator(); iterator1.hasNext();) {
                        ParameterInfo element = (ParameterInfo) iterator1.next();
                        getParaFullName(element);
                        // ParameterInfo p = new ParameterInfo();
                        // p.setType(element.getKind());
                        inputParameters.add(element);
                        if (element.getKind() != null) {
                            operationName = operationName + element.getKind() + ",";
                        } else if (element.getKind() == null && element.getName() != null) {
                            operationName = operationName + element.getName() + ",";
                        } else if (element.getKind() == null) {
                            operationName = operationName + "noType" + ",";
                        }
                    }
                    int operationNamelen = operationName.length();
                    operationName = operationName.substring(0, operationNamelen - 1) + "):";
                    f.setInputParameters(inputParameters);
                }
                // output parameters
                outputParameters = new ArrayList();
                if (outps.size() == 0) {
                    outputParameters.add(new ParameterInfo());
                } else {
                    for (Iterator iterator2 = outps.iterator(); iterator2.hasNext();) {
                        ParameterInfo element = (ParameterInfo) iterator2.next();
                        getParaFullName(element);
                        // ParameterInfo p = new ParameterInfo();
                        // p.setType(element.getKind());
                        outputParameters.add(element);
                        if (element.getKind() != null) {
                            operationName = operationName + element.getKind() + ",";
                        } else if (element.getParameterInfos() != null && !element.getParameterInfos().isEmpty()) {
                            for (Iterator iterator3 = element.getParameterInfos().iterator(); iterator3.hasNext();) {
                                ParameterInfo elementBranch = (ParameterInfo) iterator3.next();
                                if (elementBranch.getKind() != null) {
                                    operationName = operationName + elementBranch.getKind() + ",";
                                } else {
                                    operationName = operationName + "noType" + ",";
                                }
                            }
                        }
                    }
                    int operationNamelen = operationName.length();
                    operationName = operationName.substring(0, operationNamelen - 1);
                    f.setOutputParameters(outputParameters);
                }
                f.setName(operationName);
                functionsAvailable.add(f);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return functionsAvailable;
    }

    /**
     * DOC gcui Comment method "getParaFullName".
     * 
     * @param element
     */
    private static void getParaFullName(ParameterInfo paraElement) {
        paraElement.setParaFullName(paraElement.getName());
        List<ParameterInfo> allChildList = getAllChildren(paraElement);

    }

    public static List<ParameterInfo> getAllChildren(ParameterInfo para) {
        List<ParameterInfo> list = new ArrayList<ParameterInfo>();
        List<ParameterInfo> childList = para.getParameterInfos();
        for (ParameterInfo paraC : childList) {
            if (paraC.getParent().getParaFullName() != null) {
                paraC.setParaFullName(paraC.getParent().getParaFullName() + "." + paraC.getName());
            }
        }
        list.addAll(childList);
        for (ParameterInfo paraC : childList) {
            if (paraC.getParameterInfos().size() > 0) {
                list.addAll(getAllChildren(paraC));
            }
        }
        return list;
    }

    public static List<Function> getFunctionsAvailable(String wsdlURI) {
        ServiceHelperConfiguration config = null;
        return getFunctionsAvailable(wsdlURI, config);

    }

    public static void main(String[] args) {
        List<Function> test = new ArrayList<Function>();
        test = getFunctionsAvailable("\"C:/Documents and Settings/Administrator/桌面/wsdl/Person.wsdl\"");
        // test =
        //getFunctionsAvailable("F:/strivecui_work_log/week_21(0622-0626)/TestAxis/src/org/soyatec/wsdl/testExample.wsdl"
        // );
        // test = getFunctionsAvailable("C:/Documents and Settings/Administrator/桌面/wsdl/mathservice.asmx.wsdl");
        // test = getFunctionsAvailable("http://www.deeptraining.com/webservices/mathservice.asmx?WSDL");
    }
}
