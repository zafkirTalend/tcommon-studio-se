// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
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
package org.talend.core.model.metadata;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.talend.core.CorePlugin;
import org.talend.core.model.metadata.builder.connection.ConnectionFactory;
import org.talend.core.model.metadata.builder.connection.SchemaTarget;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

/**
 * Metadata Schema.
 * 
 * $Id$
 * 
 */
public final class MetadataSchema {

    /**
     * 
     */
    private static final String SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

    private static final String SCHEMA_XSD = "talend_metadata_columns_schema.xsd";

    private static final String TARGETSCHEMA_XSD = "talend_targetschema_columns_schema.xsd";

    private static final String SCHEMA_VALIDATOR = "http://java.sun.com/xml/jaxp/properties/schemaSource";

    /**
     * default constructor. Must not be used
     */
    private MetadataSchema() {

    }

    /**
     * Load Metadatas form a file.
     * 
     * @param file to read datas
     * @param oldTable precedent table, will be cloned
     * @return IMetadataTable setted with datas from file
     * @throws IOException if file cannot be read
     * @throws SAXException if sax exception occured
     * @throws ParserConfigurationException if dom exception occured
     */
    @Deprecated
    public static IMetadataTable loadMetadaFromFile(final File file, IMetadataTable oldTable)
            throws ParserConfigurationException, SAXException, IOException {
        final IMetadataTable table = new MetadataTable();
        table.setDescription(oldTable.getDescription());
        table.setId(oldTable.getId());
        table.setLabel(oldTable.getLabel());
        table.setParent(oldTable.getParent());
        table.setTableName(oldTable.getTableName());
        final List<IMetadataColumn> listColumns = initializeColumns(file);
        table.setListColumns(listColumns);
        return table;
    }

    /**
     * Load MetadataColumn from a file.
     * 
     * @param file file to load
     * @return List MetadataColumn to set
     * @throws ParserConfigurationException if dom exception occured
     * @throws SAXException if sax exception occured
     * @throws IOException if file cannot be read
     */
    public static List<org.talend.core.model.metadata.builder.connection.MetadataColumn> loadMetadataColumnFromFile(
            final File file) throws ParserConfigurationException, SAXException, IOException {
        final List<org.talend.core.model.metadata.builder.connection.MetadataColumn> listColumns = initializeColumns2(file);
        return listColumns;
    }

    /**
     * Load SchemaTarget from a file.
     * 
     * @param file file to load
     * @return List SchemaTarget to set
     * @throws ParserConfigurationException if dom exception occured
     * @throws SAXException if sax exception occured
     * @throws IOException if file cannot be read
     */
    public static List<org.talend.core.model.metadata.builder.connection.SchemaTarget> loadTargetSchemaColumnFromFile(
            final File file) throws ParserConfigurationException, SAXException, IOException {
        final List<org.talend.core.model.metadata.builder.connection.SchemaTarget> listSchemaTargets = initializeSchemaTarget2(file);
        return listSchemaTargets;
    }

    /**
     * Initalize MetadataColumns available in a file.
     * 
     * @param file where MeatadataColumns data are available
     * @return IMetadataTable setted with datas from file
     * @throws ParserConfigurationException if dom exception occured
     * @throws SAXException if sax exception occured
     * @throws IOException if file cannot be read
     */
    @Deprecated
    private static List<IMetadataColumn> initializeColumns(final File file) throws ParserConfigurationException, SAXException,
            IOException {
        final List<IMetadataColumn> listColumns = new ArrayList<IMetadataColumn>();
        if (file != null) {
            final DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();

            final Bundle b = Platform.getBundle(CorePlugin.PLUGIN_ID);
            final URL url = FileLocator.toFileURL(FileLocator.find(b, new Path(SCHEMA_XSD), null));
            final File schema = new File(url.getPath());

            fabrique.setAttribute(SCHEMA_LANGUAGE, "http://www.w3.org/2001/XMLSchema");
            fabrique.setAttribute(SCHEMA_VALIDATOR, schema);
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

            final Document document = analyseur.parse(file);
            final NodeList nodes = document.getElementsByTagName("column");
            for (int i = 0; i < nodes.getLength(); i++) {
                final IMetadataColumn metadataColumn = new MetadataColumn();
                final Node nodetoParse = nodes.item(i);
                final NamedNodeMap nodeMap = nodetoParse.getAttributes();
                final Node label = nodeMap.getNamedItem("label");
                final Node key = nodeMap.getNamedItem("key");
                final Node type = nodeMap.getNamedItem("talendType");
                final Node length = nodeMap.getNamedItem("length");
                final Node nullable = nodeMap.getNamedItem("nullable");
                final Node defaultValue = nodeMap.getNamedItem("default");
                final Node comment = nodeMap.getNamedItem("comment");

                metadataColumn.setLabel(label.getNodeValue());
                metadataColumn.setKey(Boolean.parseBoolean(key.getNodeValue()));
                metadataColumn.setTalendType(type.getNodeValue());
                if (length.getNodeValue() != null) {
                    try {
                        metadataColumn.setLength(Integer.parseInt(length.getNodeValue()));
                    } catch (final NumberFormatException e) {
                        metadataColumn.setLength(null);
                    }
                } else {
                    metadataColumn.setLength(null);
                }
                metadataColumn.setNullable(Boolean.parseBoolean(nullable.getNodeValue()));
                metadataColumn.setDefault(defaultValue.getNodeValue());
                metadataColumn.setComment(comment.getNodeValue());
                listColumns.add(metadataColumn);
            }
        }
        return listColumns;
    }

    /**
     * Initalize MetadataColumns available in a file.
     * 
     * @param file where MeatadataColumns data are available
     * @return IMetadataTable setted with datas from file
     * @throws ParserConfigurationException if dom exception occured
     * @throws SAXException if sax exception occured
     * @throws IOException if file cannot be read
     */
    private static List<org.talend.core.model.metadata.builder.connection.MetadataColumn> initializeColumns2(final File file)
            throws ParserConfigurationException, SAXException, IOException {
        final List<org.talend.core.model.metadata.builder.connection.MetadataColumn> listColumns = new ArrayList<org.talend.core.model.metadata.builder.connection.MetadataColumn>();
        if (file != null) {
            final DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();

            final Bundle b = Platform.getBundle(CorePlugin.PLUGIN_ID);
            final URL url = FileLocator.toFileURL(FileLocator.find(b, new Path(SCHEMA_XSD), null));
            final File schema = new File(url.getPath());

            fabrique.setAttribute(SCHEMA_LANGUAGE, "http://www.w3.org/2001/XMLSchema");
            fabrique.setAttribute(SCHEMA_VALIDATOR, schema);
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

            final Document document = analyseur.parse(file);
            final NodeList nodes = document.getElementsByTagName("column");
            for (int i = 0; i < nodes.getLength(); i++) {
                final org.talend.core.model.metadata.builder.connection.MetadataColumn metadataColumn = ConnectionFactory.eINSTANCE
                        .createMetadataColumn();
                final Node nodetoParse = nodes.item(i);
                final NamedNodeMap nodeMap = nodetoParse.getAttributes();
                final Node label = nodeMap.getNamedItem("label");
                final Node key = nodeMap.getNamedItem("key");
                final Node type = nodeMap.getNamedItem("talendType");
                final Node length = nodeMap.getNamedItem("length");
                final Node precision = nodeMap.getNamedItem("precision");
                final Node nullable = nodeMap.getNamedItem("nullable");
                final Node defaultValue = nodeMap.getNamedItem("default");
                final Node comment = nodeMap.getNamedItem("comment");

                metadataColumn.setLabel(label.getNodeValue());
                metadataColumn.setKey(Boolean.parseBoolean(key.getNodeValue()));
                metadataColumn.setTalendType(type.getNodeValue());
                if (length.getNodeValue() != null) {
                    try {
                        metadataColumn.setLength(Integer.parseInt(length.getNodeValue()));
                    } catch (final NumberFormatException e) {
                        metadataColumn.setLength(0);
                    }
                } else {
                    metadataColumn.setLength(0);
                }
                if (precision.getNodeValue() != null) {
                    try {
                        metadataColumn.setPrecision(Integer.parseInt(precision.getNodeValue()));
                    } catch (final NumberFormatException e) {
                        metadataColumn.setPrecision(0);
                    }
                } else {
                    metadataColumn.setPrecision(0);
                }
                metadataColumn.setNullable(Boolean.parseBoolean(nullable.getNodeValue()));
                metadataColumn.setDefaultValue(defaultValue.getNodeValue());
                metadataColumn.setComment(comment.getNodeValue());
                listColumns.add(metadataColumn);
            }
        }
        return listColumns;
    }

    /**
     * Initalize SchemaTargets available in a file.
     * 
     * @param file where SchemaTargets data are available
     * @return MetadataSchema setted with datas from file
     * @throws ParserConfigurationException if dom exception occured
     * @throws SAXException if sax exception occured
     * @throws IOException if file cannot be read
     */
    private static List<org.talend.core.model.metadata.builder.connection.SchemaTarget> initializeSchemaTarget2(final File file)
            throws ParserConfigurationException, SAXException, IOException {
        final List<org.talend.core.model.metadata.builder.connection.SchemaTarget> listSchemaTargets = new ArrayList<org.talend.core.model.metadata.builder.connection.SchemaTarget>();
        if (file != null) {
            final DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();

            final Bundle b = Platform.getBundle(CorePlugin.PLUGIN_ID);
            final URL url = FileLocator.toFileURL(FileLocator.find(b, new Path(TARGETSCHEMA_XSD), null));
            final File schema = new File(url.getPath());

            fabrique.setAttribute(SCHEMA_LANGUAGE, "http://www.w3.org/2001/XMLSchema");
            fabrique.setAttribute(SCHEMA_VALIDATOR, schema);
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

            final Document document = analyseur.parse(file);
            final NodeList nodes = document.getElementsByTagName("schemaTargets");
            for (int i = 0; i < nodes.getLength(); i++) {
                final org.talend.core.model.metadata.builder.connection.SchemaTarget schemaTarget = ConnectionFactory.eINSTANCE
                        .createSchemaTarget();
                final Node nodetoParse = nodes.item(i);
                final NamedNodeMap nodeMap = nodetoParse.getAttributes();
                final Node XPathQuery = nodeMap.getNamedItem("XPathQuery");
                final Node TagName = nodeMap.getNamedItem("TagName");
                final Node LimitBoucle = nodeMap.getNamedItem("LimitBoucle");
                final Node IsBoucle = nodeMap.getNamedItem("IsBoucle");

                schemaTarget.setXPathQuery(XPathQuery.getNodeValue());
                schemaTarget.setTagName(TagName.getNodeValue());
                if (LimitBoucle.getNodeValue() != null) {
                    try {
                        schemaTarget.setLimitBoucle(Integer.parseInt(LimitBoucle.getNodeValue()));
                    } catch (final NumberFormatException e) {
                        schemaTarget.setLimitBoucle(0);
                    }
                } else {
                    schemaTarget.setLimitBoucle(0);
                }
                schemaTarget.setBoucle(Boolean.parseBoolean(IsBoucle.getNodeValue()));

                listSchemaTargets.add(schemaTarget);
            }
        }
        return listSchemaTargets;
    }

    /**
     * Export MetadataColumn to the specified file.
     * 
     * @param file to save
     * @param table to export
     * @return boolean result
     * @throws IOException if file cannot be saved
     * @throws ParserConfigurationException if dom is not fully respected
     */
    public static boolean saveMetadataColumnToFile(File file,
            org.talend.core.model.metadata.builder.connection.MetadataTable table) throws IOException,
            ParserConfigurationException {

        if (file != null) {
            final DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();

            final Bundle b = Platform.getBundle(CorePlugin.PLUGIN_ID);
            final URL url = FileLocator.toFileURL(FileLocator.find(b, new Path(SCHEMA_XSD), null));
            final File schema = new File(url.getPath());

            fabrique.setAttribute(SCHEMA_LANGUAGE, "http://www.w3.org/2001/XMLSchema");
            fabrique.setAttribute(SCHEMA_VALIDATOR, schema);
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

            Document document = analyseur.newDocument();
            Element racine = document.createElement("schema");
            document.appendChild(racine);

            for (Object list : table.getColumns()) {
                org.talend.core.model.metadata.builder.connection.MetadataColumn metadataColumn = (org.talend.core.model.metadata.builder.connection.MetadataColumn) list;
                Element column = document.createElement("column");
                racine.appendChild(column);

                Attr label = document.createAttribute("label");
                label.setNodeValue(metadataColumn.getLabel());
                column.setAttributeNode(label);

                Attr key = document.createAttribute("key");
                key.setNodeValue(String.valueOf(metadataColumn.isKey()));
                column.setAttributeNode(key);

                Attr talendType = document.createAttribute("talendType");
                talendType.setNodeValue(metadataColumn.getTalendType());
                column.setAttributeNode(talendType);

                Attr length = document.createAttribute("length");
                length.setNodeValue(String.valueOf(metadataColumn.getLength()));
                column.setAttributeNode(length);

                Attr precision = document.createAttribute("precision");
                precision.setNodeValue(String.valueOf(metadataColumn.getPrecision()));
                column.setAttributeNode(precision);

                Attr nullable = document.createAttribute("nullable");
                nullable.setNodeValue(String.valueOf(metadataColumn.isNullable()));
                column.setAttributeNode(nullable);

                Attr defaultValue = document.createAttribute("default");
                defaultValue.setNodeValue(metadataColumn.getDefaultValue());
                column.setAttributeNode(defaultValue);

                Attr comment = document.createAttribute("comment");
                comment.setNodeValue(metadataColumn.getComment());
                column.setAttributeNode(comment);
            }

            // use specific Xerces class to write DOM-data to a file:
            XMLSerializer serializer = new XMLSerializer();
            serializer.setOutputCharStream(new java.io.FileWriter(file));
            serializer.serialize(document);
            return true;
        }
        return false;
    }

    /**
     * Export SchemaTarget to the specified file.
     * 
     * @param file to save
     * @param table to export
     * @return boolean result
     * @throws IOException if file cannot be saved
     * @throws ParserConfigurationException if dom is not fully respected
     */
    public static boolean saveSchemaTargetToFile(File file, org.talend.core.model.metadata.builder.connection.MetadataSchema table)
            throws IOException, ParserConfigurationException {

        if (file != null) {
            final DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();

            final Bundle b = Platform.getBundle(CorePlugin.PLUGIN_ID);
            final URL url = FileLocator.toFileURL(FileLocator.find(b, new Path(TARGETSCHEMA_XSD), null));
            final File schema = new File(url.getPath());

            fabrique.setAttribute(SCHEMA_LANGUAGE, "http://www.w3.org/2001/XMLSchema");
            fabrique.setAttribute(SCHEMA_VALIDATOR, schema);
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

            Document document = analyseur.newDocument();
            Element racine = document.createElement("schema");
            document.appendChild(racine);

            for (Object list : table.getSchemaTargets()) {
                SchemaTarget schemaTarget = (SchemaTarget) list;
                Element column = document.createElement("schemaTargets");
                racine.appendChild(column);

                Attr xPathQuery = document.createAttribute("XPathQuery");
                xPathQuery.setNodeValue(schemaTarget.getXPathQuery());
                column.setAttributeNode(xPathQuery);

                Attr tagName = document.createAttribute("TagName");
                tagName.setNodeValue(String.valueOf(schemaTarget.getTagName()));
                column.setAttributeNode(tagName);

                Attr isBoucle = document.createAttribute("IsBoucle");
                isBoucle.setNodeValue(String.valueOf(schemaTarget.isBoucle()));
                column.setAttributeNode(isBoucle);

                Attr limitBoucle = document.createAttribute("LimitBoucle");
                limitBoucle.setNodeValue(String.valueOf(schemaTarget.getLimitBoucle()));
                column.setAttributeNode(limitBoucle);
            }

            // use specific Xerces class to write DOM-data to a file:
            XMLSerializer serializer = new XMLSerializer();
            serializer.setOutputCharStream(new java.io.FileWriter(file));
            serializer.serialize(document);
            return true;
        }
        return false;
    }

    /**
     * Export MetadataColumn to the specified file.
     * 
     * @param file to save
     * @param table to export
     * @return boolean result
     * @throws IOException if file cannot be saved
     * @throws ParserConfigurationException if dom is not fully respected
     */
    @Deprecated
    public static boolean saveMetadataColumnToFile(File file, IMetadataTable table) throws IOException,
            ParserConfigurationException {

        if (file != null) {
            final DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();

            final Bundle b = Platform.getBundle(CorePlugin.PLUGIN_ID);
            final URL url = FileLocator.toFileURL(FileLocator.find(b, new Path(SCHEMA_XSD), null));
            final File schema = new File(url.getPath());

            fabrique.setAttribute(SCHEMA_LANGUAGE, "http://www.w3.org/2001/XMLSchema");
            fabrique.setAttribute(SCHEMA_VALIDATOR, schema);
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

            Document document = analyseur.newDocument();
            Element racine = document.createElement("schema");
            document.appendChild(racine);

            for (IMetadataColumn metadataColumn : table.getListColumns()) {
                Element column = document.createElement("column");
                racine.appendChild(column);

                Attr label = document.createAttribute("label");
                label.setNodeValue(metadataColumn.getLabel());
                column.setAttributeNode(label);

                Attr key = document.createAttribute("key");
                key.setNodeValue(String.valueOf(metadataColumn.isKey()));
                column.setAttributeNode(key);

                Attr talendType = document.createAttribute("talendType");
                talendType.setNodeValue(metadataColumn.getTalendType());
                column.setAttributeNode(talendType);

                Attr length = document.createAttribute("length");
                if (metadataColumn.getLength() == null) {
                    length.setNodeValue("-1");
                } else {
                    length.setNodeValue(String.valueOf(metadataColumn.getLength()));
                }
                column.setAttributeNode(length);

                Attr precision = document.createAttribute("precision");
                if (metadataColumn.getPrecision() == null) {
                    precision.setNodeValue("-1");
                } else {
                    precision.setNodeValue(String.valueOf(metadataColumn.getPrecision()));
                }

                column.setAttributeNode(precision);

                Attr nullable = document.createAttribute("nullable");
                nullable.setNodeValue(String.valueOf(metadataColumn.isNullable()));
                column.setAttributeNode(nullable);

                Attr defaultValue = document.createAttribute("default");
                defaultValue.setNodeValue(metadataColumn.getDefault());
                column.setAttributeNode(defaultValue);

                Attr comment = document.createAttribute("comment");
                comment.setNodeValue(metadataColumn.getComment());
                column.setAttributeNode(comment);
            }

            // use specific Xerces class to write DOM-data to a file:
            XMLSerializer serializer = new XMLSerializer();
            serializer.setOutputCharStream(new java.io.FileWriter(file));
            serializer.serialize(document);
            return true;
        }
        return false;
    }
}
