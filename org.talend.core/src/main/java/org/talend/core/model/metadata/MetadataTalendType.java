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
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.talend.commons.exception.SystemException;
import org.talend.core.CorePlugin;
import org.talend.core.i18n.Messages;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Load Metadata Talend Type from mappingMetadataTypes.xml. Talend Types available in the application
 * 
 * 
 * $Id$
 * 
 */
public final class MetadataTalendType {

    private static final Set<String> PRIMITIVE_TYPES = new HashSet<String>(6);

    public static final String DEFAULT_CHAR = "' '"; //$NON-NLS-1$

    public static final String DEFAULT_NUMBER = "0"; //$NON-NLS-1$

    public static final String NULL = "null"; //$NON-NLS-1$

    public static final String CHAR = "char"; //$NON-NLS-1$

    static {
        PRIMITIVE_TYPES.add("short"); //$NON-NLS-1$
        PRIMITIVE_TYPES.add("int"); //$NON-NLS-1$
        PRIMITIVE_TYPES.add("long"); //$NON-NLS-1$
        PRIMITIVE_TYPES.add("float"); //$NON-NLS-1$
        PRIMITIVE_TYPES.add("double"); //$NON-NLS-1$
        PRIMITIVE_TYPES.add(CHAR);
    }

    /**
     * Default Constructor. Must not be used.
     */
    private MetadataTalendType() {
    }

    private static final String MAPPING_METADATA_TYPES_XML = "mappingMetadataTypes.xml"; //$NON-NLS-1$

    private static Map<String, Map<String, String>> talendTypes = null;

    private static Map<String, Map<String, String>> defaultvalue = null;

    public static final String NULLABLE = "nullable"; //$NON-NLS-1$

    public static final String LANGUAGE_JAVA = "JAVA"; //$NON-NLS-1$

    public static final String TALENDDEFAULT = "TALENDDEFAULT"; //$NON-NLS-1$

    private static Comparator<String> comparatorIgnoreCase = new Comparator<String>() {

        public int compare(String o1, String o2) {
            if (o1 == null) {
                return 1;
            }
            if (o2 == null) {
                return -1;
            }

            int compareIgnoreCase = o1.compareToIgnoreCase(o2);
            int compare = o1.compareTo(o2);
            if (compareIgnoreCase == 0 && compare != 0) {
                return -1 * compare;
            }

            return compareIgnoreCase;
        }

    };

    /**
     * Get the Talend Type for a particular type of a particular database.
     * 
     * @param type to map
     * @param dbms
     * @param reload, true if it's necessary to reload mapping from our repository
     * @return
     */
    public static String loadTalendType(String type, String dbms, boolean reload) {
        dbms = dbms.toUpperCase();
        checkTypesAreInitialized(dbms, reload);
        if (talendTypes.get(dbms) != null) {
            return talendTypes.get(dbms).get(type.toUpperCase());
        }
        return null;
    }

    /**
     * Load dbms default type for a talend Type and a dbms.
     * 
     * @param talendType
     * @param dbms
     * @param reload
     * @return
     */
    public static String loadDBMSType(String talendType, String dbms, boolean reload) {
        dbms = dbms.toUpperCase();
        checkTypesAreInitialized(dbms, reload);
        if (defaultvalue.get(dbms) != null) {
            return defaultvalue.get(dbms).get(talendType);
        }
        return null;
    }

    /**
     * Load Talend Types available for a particular Database.
     * 
     * @param dbms
     * @param reload
     * @return
     */
    public static String[] loadTalendTypes(String dbms, boolean reload) {

        dbms = dbms.toUpperCase();
        Collection<String> types = new TreeSet<String>();
        checkTypesAreInitialized(dbms, reload);
        if (talendTypes.get(dbms) != null) {
            types = talendTypes.get(dbms).values();
        }

        return createStringArray(types);
    }

    /**
     * Load Database Types available.
     * 
     * @param dbms
     * @param reload
     * @return
     */
    public static String[] loadDatabaseTypes(String dbms, boolean reload) {
        dbms = dbms.toUpperCase();
        Collection<String> types = new TreeSet<String>();
        checkTypesAreInitialized(dbms, reload);
        if (talendTypes.get(dbms) != null) {
            types = talendTypes.get(dbms).keySet();
        }
        return createStringArray(types);
    }

    /**
     * initialisation of datas.
     * 
     * @return
     * @throws SystemException
     */
    private static void init() throws SystemException {
        talendTypes = new HashMap<String, Map<String, String>>();
        defaultvalue = new HashMap<String, Map<String, String>>();

        try {
            DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
            DocumentBuilder analyseur = fabrique.newDocumentBuilder();

            Bundle b = Platform.getBundle(CorePlugin.PLUGIN_ID);
            URL url = FileLocator.toFileURL(FileLocator.find(b, new Path(MAPPING_METADATA_TYPES_XML), null));
            File dir = new File(url.getPath());
            Document document = analyseur.parse(dir);

            NodeList nodes = document.getElementsByTagName("version"); //$NON-NLS-1$
            for (int i = 0; i < nodes.getLength(); i++) {
                Node nodetoParse = nodes.item(i);
                NamedNodeMap nodeMap = nodetoParse.getAttributes();
                Node version = nodeMap.getNamedItem("name"); //$NON-NLS-1$
                Node brotherNode = nodetoParse.getParentNode().getParentNode().getFirstChild();
                Map<String, String> value = new HashMap<String, String>();
                Map<String, String> dv = new HashMap<String, String>();
                while (brotherNode != null) {
                    if (brotherNode.getNodeName().compareTo("type") == 0) { //$NON-NLS-1$
                        NamedNodeMap typeNodeAttributes = brotherNode.getAttributes();
                        String dbmsNodeValue = typeNodeAttributes.getNamedItem("dbms").getNodeValue(); //$NON-NLS-1$
                        Node nullableNamedItem = typeNodeAttributes.getNamedItem(NULLABLE);
                        String keyForValues = dbmsNodeValue;
                        if (nullableNamedItem != null && "true".equals(nullableNamedItem.getNodeValue())) { //$NON-NLS-1$
                            keyForValues = dbmsNodeValue + NULLABLE;
                        }
                        value.put(keyForValues, typeNodeAttributes.getNamedItem("talend").getNodeValue()); //$NON-NLS-1$

                        Node defaultNamedItem = typeNodeAttributes.getNamedItem("default"); //$NON-NLS-1$
                        if (defaultNamedItem != null && "true".equals(defaultNamedItem.getNodeValue())) { //$NON-NLS-1$
                            dv.put(typeNodeAttributes.getNamedItem("talend").getNodeValue(), typeNodeAttributes.getNamedItem("dbms") //$NON-NLS-1$ //$NON-NLS-2$
                                    .getNodeValue());
                        }
                    }
                    brotherNode = brotherNode.getNextSibling();
                }
                talendTypes.put(version.getNodeValue().toUpperCase(), value);
                defaultvalue.put(version.getNodeValue().toUpperCase(), dv);
            }
        } catch (IOException e) {
            throw new SystemException(e.getCause());
        } catch (ParserConfigurationException e) {
            throw new SystemException(e.getCause());
        } catch (SAXException e) {
            throw new SystemException(e.getCause());
        }
    }

    /**
     * return an Array of String for a given Collection TO DO Move in utils.
     * 
     * @param types
     * @return
     */
    private static String[] createStringArray(Collection<String> types) {
        SortedSet<String> sortedTypes = new TreeSet<String>(comparatorIgnoreCase);
        sortedTypes.addAll(types);
        return sortedTypes.toArray(new String[] {});
    }

    /**
     * initialize if necessary or asked.
     * 
     * @param dbms
     * @param reload
     * @throws SystemException
     */
    private static void checkTypesAreInitialized(String dbms, boolean reload) {
        if (dbms == null) {
            throw new NullPointerException(Messages.getString("MetadataTalendType.DBMSNotSetted")); //$NON-NLS-1$
        }
        if ((talendTypes == null) || (reload)) {
            try {
                init();
            } catch (SystemException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isJavaPrimitiveType(String type) {
        return PRIMITIVE_TYPES.contains(type);
    }

    public static String getDefaultValueFromJavaType(String type) {
        if (type == null) {
            throw new IllegalArgumentException();
        }
        if (isJavaPrimitiveType(type)) {
            if (type.equals(CHAR)) {
                return DEFAULT_CHAR;
            } else {
                return DEFAULT_NUMBER;
            }
        } else {
            return NULL;
        }
    }

}
