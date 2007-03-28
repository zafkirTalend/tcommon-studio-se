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
package org.talend.commons.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class XSDValidator {

    private static final String SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

    private static final String SCHEMA_VALIDATOR = "http://java.sun.com/xml/jaxp/properties/schemaSource";

    public static Document checkXSD(File fileToCheck, File fileXSD) throws IOException, ParserConfigurationException,
            SAXException {
        final DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();

        fabrique.setAttribute(SCHEMA_LANGUAGE, "http://www.w3.org/2001/XMLSchema");
        fabrique.setAttribute(SCHEMA_VALIDATOR, fileXSD);
        fabrique.setValidating(true);

        final DocumentBuilder analyseur = fabrique.newDocumentBuilder();
        analyseur.setErrorHandler(new ErrorHandler() {

            public void error(final SAXParseException exception) throws SAXException {
                throw exception;
            }

            public void fatalError(final SAXParseException exception) throws SAXException {
                throw exception;
            }

            public void warning(final SAXParseException exception) throws SAXException {
                throw exception;
            }

        });

        return analyseur.parse(fileToCheck);
    }
}
