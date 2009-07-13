package org.talend.designer.webservice.ws;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.wsdl.Definition;
import javax.wsdl.WSDLException;

import org.apache.ws.commons.schema.XmlSchema;
import org.apache.ws.commons.schema.XmlSchemaCollection;
import org.talend.designer.webservice.ws.helper.ServiceDiscoveryHelper;
import org.talend.designer.webservice.ws.helper.ServiceInvokerHelper;

/**
 * DOC gcui class global comment. Detailled comment
 */
public class WSDLDiscoveryHelper2 extends ServiceInvokerHelper {

    public static void main(String[] args) {
        ServiceDiscoveryHelper sdh;
        try {
            sdh = new ServiceDiscoveryHelper("C:/Documents and Settings/Administrator/桌面/wsdl/testExample.wsdl");
            Definition def = sdh.getDefinition();

            Map m1 = def.getBindings();
            Set<javax.xml.namespace.QName> h1 = m1.keySet();
            for (javax.xml.namespace.QName s : h1) {
                System.out.println(m1.get(s));
            }
            System.out.println("#################################################################");
            Map m2 = def.getServices();
            Set<javax.xml.namespace.QName> h2 = m2.keySet();
            for (javax.xml.namespace.QName s : h2) {
                System.out.println(m2.get(s));
            }
            System.out.println("#################################################################");
            Map m3 = def.getPortTypes();
            Set<javax.xml.namespace.QName> h3 = m3.keySet();
            for (javax.xml.namespace.QName s : h3) {
                System.out.println(m3.get(s));
            }

            XmlSchemaCollection xsc = sdh.getSchema();
            XmlSchema[] xs = xsc.getXmlSchemas();
            // for (String s : xs[0].getAttributes().getNames()) {
            // s
            // }
        } catch (WSDLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
