package org.talend.designer.webservice.ws;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    public static synchronized List<Function> getFunctionsAvailable(String wsdlURI) {
        functionsAvailable = new ArrayList();
        try {
            ComponentBuilder builder = new ComponentBuilder();

            ServiceInfo serviceInfo = new ServiceInfo();
            serviceInfo.setWsdlUri(wsdlURI);
            serviceInfo = builder.buildserviceinformation(serviceInfo);

            Iterator iter = serviceInfo.getOperations();
            while (iter.hasNext()) {

                OperationInfo oper = (OperationInfo) iter.next();
                // operationName
                Function f = new Function(oper.getTargetMethodName());
                String operationName = oper.getTargetMethodName() + "(";

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
                        // ParameterInfo p = new ParameterInfo();
                        // p.setType(element.getKind());
                        inputParameters.add(element);
                        System.out.println("INPUT------" + element.getName() + "----" + element.getKind());
                        if (element.getKind() != null) {
                            operationName = operationName + element.getKind() + ",";
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
                        // ParameterInfo p = new ParameterInfo();
                        // p.setType(element.getKind());
                        outputParameters.add(element);
                        System.out.println("OUTPUT---" + element.getName() + "----" + element.getKind());
                        operationName = operationName + element.getKind() + ",";
                    }
                    int operationNamelen = operationName.length();
                    operationName = operationName.substring(0, operationNamelen - 1);
                    f.setOutputParameters(outputParameters);
                }
                System.out.println(operationName);
                f.setName(operationName);
                functionsAvailable.add(f);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return functionsAvailable;
    }

    public static void main(String[] args) {
        List<Function> test = new ArrayList<Function>();
        test = getFunctionsAvailable("C:/Documents and Settings/Administrator/桌面/wsdl/Person.wsdl");
        // test =
        //getFunctionsAvailable("F:/strivecui_work_log/week_21(0622-0626)/TestAxis/src/org/soyatec/wsdl/testExample.wsdl"
        // );
        // test = getFunctionsAvailable("C:/Documents and Settings/Administrator/桌面/wsdl/mathservice.asmx.wsdl");
        // test = getFunctionsAvailable("http://www.deeptraining.com/webservices/mathservice.asmx?WSDL");
    }
}
