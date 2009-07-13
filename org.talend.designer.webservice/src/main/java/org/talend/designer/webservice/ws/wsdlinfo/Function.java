package org.talend.designer.webservice.ws.wsdlinfo;

import java.util.List;

/**
 * 
 * @author gcui
 */
public class Function {

    private List<ParameterInfo> inputParameters;

    private List<ParameterInfo> outputParameters;

    private String name;

    private String soapAction;

    private String nameSpaceURI;

    public Function(String name) {
        super();
        this.name = name;
    }

    public List<ParameterInfo> getInputParameters() {
        return inputParameters;
    }

    public void setInputParameters(List<ParameterInfo> inputParameters) {
        this.inputParameters = inputParameters;
    }

    public List<ParameterInfo> getOutputParameters() {
        return outputParameters;
    }

    public void setOutputParameters(List<ParameterInfo> outputParameters) {
        this.outputParameters = outputParameters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoapAction() {
        return soapAction;
    }

    public void setSoapAction(String soapAction) {
        this.soapAction = soapAction;
    }

    public String getNameSpaceURI() {
        return nameSpaceURI;
    }

    public void setNameSpaceURI(String nameSpaceURI) {
        this.nameSpaceURI = nameSpaceURI;
    }

}
