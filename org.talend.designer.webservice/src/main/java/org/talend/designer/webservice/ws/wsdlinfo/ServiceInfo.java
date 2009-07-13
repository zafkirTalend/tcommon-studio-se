package org.talend.designer.webservice.ws.wsdlinfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.exolab.castor.xml.schema.Schema;

/**
 * 
 * @author gcui
 */
public class ServiceInfo {

    /** server name */
    private String name;

    /** WSDL URI */
    private String wsdluri;//

    private String endpoint;

    private String targetnamespace;

    private Schema wsdlType;

    /** The list of operations that this service defines. */
    List operations = new ArrayList();

    public Schema getWsdlType() {
        return wsdlType;
    }

    public void setWsdlTypes(Schema wsdlType) {
        this.wsdlType = wsdlType;
    }

    public List getOperation() {
        return operations;
    }

    public Iterator getOperations() {
        return operations.iterator();
    }

    public void addOperation(OperationInfo operation) {
        operations.add(operation);
    }

    public String toString() {
        return getName();
    }

    public String getTargetnamespace() {
        return targetnamespace;
    }

    public void setTargetnamespace(String targetnamespace) {
        this.targetnamespace = targetnamespace;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getWsdlUri() {
        return wsdluri;
    }

    public void setWsdlUri(String wsdluri) {
        this.wsdluri = wsdluri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
