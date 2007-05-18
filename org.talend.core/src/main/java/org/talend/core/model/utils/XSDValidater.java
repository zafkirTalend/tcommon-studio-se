// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.core.model.utils;

/**
 * DOC acer class global comment. Detailled comment <br/>
 * 
 */
import java.io.File;
import java.io.FileReader;
import java.io.Reader;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

import com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl;

/**
 * bqian Use xml xsd file to validate the xml file. <br/>
 * 
 */
public class XSDValidater {

    /**
     * Validate xml with xsd by dom.
     * 
     * @param xsd the reader of xsd file.
     * @param xml the reader of xml file.
     * @throws Exception
     */
    public void validateWithDom(Reader xsd, Reader xml) throws Exception {
        // Load up the document
        DocumentBuilderFactory factory = new DocumentBuilderFactoryImpl();
        factory.setNamespaceAware(true);
        // Set up an XML Schema validator, using the supplied schema
        Source schemaSource = new StreamSource(xsd);
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(schemaSource);

        // Instead of explicitly validating, assign the Schema to the factory
        factory.setSchema(schema);

        // Parsers from this factory will automatically validate against the
        // associated schema
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(xml));
        schema.newValidator().validate(new DOMSource(doc));
    }
    /**
     * Validate xml with xsd by sax.
     * 
     * @param xsd the reader of xsd file.
     * @param xml the reader of xml file.
     * @throws Exception
     */
    public void validateWithSax(Reader xsd, Reader xml) throws Exception {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser parser = null;
        spf.setNamespaceAware(true);
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        spf.setSchema(sf.newSchema(new SAXSource(new InputSource(xsd))));
        parser = spf.newSAXParser();
        DefaultHandler handler = new DefaultHandler();
        parser.parse(new InputSource(xml), handler);
    }

    public void validateWithDom(File xsd, File xml) throws Exception {
        this.validateWithDom(new FileReader(xsd), new FileReader(xml));
    }

}
