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

package org.talend.core.model.metadata;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.SystemException;
import org.talend.core.CorePlugin;
import org.talend.core.i18n.Messages;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
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

    /**
     * 
     */
    public static final String INTERNAL_MAPPINGS_FOLDER = "mappings";

    private static Logger log = Logger.getLogger(MetadataTalendType.class);

    private static ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();

    private static final String[] PERL_TYPES = new String[] {
            "boolean", "date", "datetime", "integer", "float", "time", "string" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$

    private static Set<Dbms> dbmsSet = new HashSet<Dbms>();

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

    private static List<File> metadataMappingFiles = null;
    static {
        try {
            MetadataTalendType.loadCommonMappings();
        } catch (SystemException e) {
            ExceptionHandler.process(e);
        }
    }

    /**
     * Get the Talend Type for a particular type of a particular database.
     * 
     * @param type to map
     * @param dbms
     * @param reload, true if it's necessary to reload mapping from our repository
     * @return
     * @deprecated Use {@link MetadataTalendType#getTalendTypesLabels()} method instead.
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
     * @deprecated
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
     * @deprecated Use {@link MetadataTalendType#getTalendTypesLabels()} method instead.
     */
    public static String[] loadTalendTypes(String dbms, boolean reload) {

        if (codeLanguage == ECodeLanguage.JAVA) {
            return JavaTypesManager.getJavaTypesLabels();
        } else {
            dbms = dbms.toUpperCase();
            Collection<String> types = new TreeSet<String>();
            checkTypesAreInitialized(dbms, reload);
            if (talendTypes.get(dbms) != null) {
                types = talendTypes.get(dbms).values();
            }

            return createStringArray(types);
        }
    }

    /**
     * Load Database Types available.
     * 
     * @param dbms
     * @param reload
     * @return
     * @deprecated
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
     * @deprecated.
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
                        value.put(keyForValues.toUpperCase(), typeNodeAttributes.getNamedItem("talend").getNodeValue()); //$NON-NLS-1$

                        Node defaultNamedItem = typeNodeAttributes.getNamedItem("default"); //$NON-NLS-1$
                        if (defaultNamedItem != null && "true".equals(defaultNamedItem.getNodeValue())) { //$NON-NLS-1$
                            dv
                                    .put(
                                            typeNodeAttributes.getNamedItem("talend").getNodeValue(), typeNodeAttributes.getNamedItem("dbms") //$NON-NLS-1$ //$NON-NLS-2$
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
     * @deprecated
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
     * @deprecated
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

    /**
     * 
     * Return the talend types function of the current language.
     * 
     * @return
     */
    public static String[] getTalendTypesLabels() {
        if (codeLanguage == ECodeLanguage.JAVA) {
            return JavaTypesManager.getJavaTypesLabels();
        } else if (codeLanguage == ECodeLanguage.PERL) {
            // return (String[]) ArrayUtils.clone(PERL_TYPES);
            return loadTalendTypes("TALENDDEFAULT", false); //$NON-NLS-1$
        }
        throw new IllegalStateException("Case not found."); //$NON-NLS-1$
    }

    public static Dbms[] getAllDbmsArray() {
        return dbmsSet.toArray(new Dbms[0]);
    }

    /**
     * 
     * Return array of Dbms which have the given product value.
     * 
     * @param product
     * @return array of Dbms which have the given product value
     */
    public static Dbms[] getDbmsArrayFromProduct(String product) {
        if (product == null) {
            throw new IllegalArgumentException();
        }
        Dbms[] allDbmsArray = getAllDbmsArray();
        ArrayList<Dbms> list = new ArrayList<Dbms>();
        for (int i = 0; i < allDbmsArray.length; i++) {
            Dbms dbms = allDbmsArray[i];
            if (product.equals(dbms.getProduct())) {
                list.add(dbms);
            }
        }
        return list.toArray(new Dbms[0]);
    }

    public static Dbms getDefaultDbmsFromProduct(String product) {
        if (product == null) {
            throw new IllegalArgumentException();
        }
        Dbms[] allDbmsArray = getAllDbmsArray();
        Dbms defaultDbms = null;
        for (int i = 0; i < allDbmsArray.length; i++) {
            Dbms dbms = allDbmsArray[i];
            if (product.equals(dbms.getProduct())) {
                if (dbms.isDefaultDbms()) {
                    return dbms;
                }
                defaultDbms = dbms; // set this value, so even if no dbms is set by default there will still be a dbms
                // used.
            }
        }
        return defaultDbms;
    }

    /**
     * 
     * Retrieve and return the dbms from the given id.
     * 
     * @param dbmsId
     * @return the dbms from the given id
     */
    public static Dbms getDbms(String dbmsId) {
        if (dbmsId == null) {
            throw new IllegalArgumentException();
        }
        Dbms[] allDbmsArray = getAllDbmsArray();
        for (int i = 0; i < allDbmsArray.length; i++) {
            Dbms dbms = allDbmsArray[i];
            if (dbmsId.equals(dbms.getId())) {
                return dbms;
            }
        }
        return null;
    }

    /**
     * Retrievd the dbms from the given dbmsId and return db types from it.
     * 
     * @param dbmsId
     * @return return db types from the dbms
     */
    public static String[] getDbTypes(String dbmsId) {
        if (dbmsId == null) {
            throw new IllegalArgumentException();
        }
        Dbms dbms = getDbms(dbmsId);
        if (dbms == null) {
            throw new IllegalArgumentException("Unknown dbmsId: '" + dbmsId + "'"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        String [] list = dbms.getDbTypes().toArray(new String[0]);
        Arrays.sort(list);
        return list;
    }

    /**
     * 
     * Load db types and mapping with the current activated language (Java, Perl, ...).
     * 
     * @throws SystemException
     */
    public static void loadCommonMappings() throws SystemException {

        String dirName = INTERNAL_MAPPINGS_FOLDER; //$NON-NLS-1$

        String dirPath = "/" + dirName; //$NON-NLS-1$

        Path filePath = new Path(dirPath);

        Bundle b = Platform.getBundle(CorePlugin.PLUGIN_ID);
        URL url;
        try {
            if (b != null) {
                url = FileLocator.toFileURL(FileLocator.find(b, filePath, null));
            } else {
                // for testing only, see org.talend.core\src\test\java\mappings for test files
                url = MetadataTalendType.class.getResource(dirPath);
            }
        } catch (IOException e) {
            throw new SystemException(e);
        }
        File dir = new File(url.getPath());
        metadataMappingFiles = new ArrayList<File>();
        dbmsSet.clear();
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.getName().matches("^mapping_.*\\.xml$")) { //$NON-NLS-1$
                    loadMapping(file);
                    metadataMappingFiles.add(file);
                }
            }
        }
    }

    public static List<File> getMetadataMappingFiles() {
        return metadataMappingFiles;
    }

    private static void loadMapping(File file) throws SystemException {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder analyser = documentBuilderFactory.newDocumentBuilder();
            Document document = analyser.parse(file);

            NodeList dbmsNodes = document.getElementsByTagName("dbms"); //$NON-NLS-1$

            for (int iDbms = 0; iDbms < dbmsNodes.getLength(); iDbms++) {
                Node dbmsNode = dbmsNodes.item(iDbms);

                NamedNodeMap dbmsAttributes = dbmsNode.getAttributes();
                String dbmsProductValue = dbmsAttributes.getNamedItem("product").getNodeValue(); //$NON-NLS-1$
                String dbmsIdValue = dbmsAttributes.getNamedItem("id").getNodeValue(); //$NON-NLS-1$
                String dbmsLabelValue = dbmsAttributes.getNamedItem("label").getNodeValue(); //$NON-NLS-1$
                Node defaultDbmsItem = dbmsAttributes.getNamedItem("default");
                boolean defaultDbms = false;
                if (defaultDbmsItem != null && "true".equals(defaultDbmsItem.getNodeValue())) {
                    defaultDbms = true;
                }

                Dbms dbms = new Dbms(dbmsIdValue);
                dbms.setLabel(dbmsLabelValue);
                dbms.setProduct(dbmsProductValue);
                dbms.setDefaultDbms(defaultDbms);
                boolean dbmsOverwriteExisting = !dbmsSet.add(dbms);
                if (dbmsOverwriteExisting) {
                    log.warn("Dbms with id '" + dbmsIdValue + "' already exists !");
                }

                // list all dbms children nodes
                List<Node> childrenOfDbmsNode = getChildElementNodes(dbmsNode);

                // TODO create a dtd

                // search "db_types" node
                Node dbTypesNode = childrenOfDbmsNode.get(0);

                // search and load "db_types/type" nodes
                ArrayList<String> dbTypes = new ArrayList<String>();
                dbms.setDbmsTypes(dbTypes);
                List<Node> typeNodes = getChildElementNodes(dbTypesNode);
                for (Node typeNode : typeNodes) {
                    NamedNodeMap typeNodeAtttributes = typeNode.getAttributes();
                    String typeValue = typeNodeAtttributes.getNamedItem("dbType").getNodeValue(); //$NON-NLS-1$
                    Node defaultTypeItem = typeNodeAtttributes.getNamedItem("default"); //$NON-NLS-1$
                    dbTypes.add(typeValue);
                    if (defaultTypeItem != null && "true".equals(defaultTypeItem.getNodeValue())) { //$NON-NLS-1$
                        dbms.setDefaultDbType(typeValue);
                    }
                }

                // search and load "language/type" nodes
                List<Node> languageNodes = childrenOfDbmsNode.subList(1, childrenOfDbmsNode.size());
                ArrayList<MappingType> mappingTypes = new ArrayList<MappingType>();
                dbms.setMappingTypes(mappingTypes);
                for (int i = 0; i < languageNodes.size(); i++) {
                    Node languageNode = languageNodes.get(i);

                    // System.out.println();

                    String languageValue = languageNode.getAttributes().getNamedItem("name").getNodeValue(); //$NON-NLS-1$

                    if (codeLanguage.getName().equalsIgnoreCase(languageValue)) {

                        List<Node> languageTypesNodes = getChildElementNodes(languageNode);

                        for (int j = 0; j < languageTypesNodes.size(); j++) {

                            Node languageTypeNode = languageTypesNodes.get(j);

                            NamedNodeMap dbTypeAttributes = languageTypeNode.getAttributes();

                            Node talendTypeItem = dbTypeAttributes.getNamedItem("talendType"); //$NON-NLS-1$
                            if (talendTypeItem == null) {
                                continue;
                            }

                            Node dbTypeItem = dbTypeAttributes.getNamedItem("dbType"); //$NON-NLS-1$
                            if (dbTypeItem == null) {
                                continue;
                            }

                            Node defaultSelectedItem = dbTypeAttributes.getNamedItem("default"); //$NON-NLS-1$

                            if (LanguageManager.getCurrentLanguage() == ECodeLanguage.JAVA) {

                                String talendType = talendTypeItem.getNodeValue();
                                JavaType javaType = JavaTypesManager.getJavaTypeFromId(talendType);

                                boolean defaultSelected = defaultSelectedItem != null
                                        && defaultSelectedItem.getNodeValue().equalsIgnoreCase("true") ? Boolean.TRUE : Boolean.FALSE; //$NON-NLS-1$

                                if (javaType != null) { // test if the type exists

                                    MappingType objectMappingType = new MappingType();
                                    objectMappingType.setTalendType(talendType);
                                    objectMappingType.setDbType(dbTypeItem.getNodeValue());
                                    objectMappingType.setDefaultSelected(defaultSelected);
                                    mappingTypes.add(objectMappingType);

                                } else {
                                    log.warn("'" + talendType + "' is not a valid Java Talend type.");
                                }

                            } else if (LanguageManager.getCurrentLanguage() == ECodeLanguage.PERL) {

                                MappingType mappingType = new MappingType();
                                mappingType.setTalendType(talendTypeItem.getNodeValue());
                                mappingType.setDbType(dbTypeItem.getNodeValue());
                                mappingType
                                        .setDefaultSelected(defaultSelectedItem != null
                                                && defaultSelectedItem.getNodeValue().equalsIgnoreCase("true") ? Boolean.TRUE : Boolean.FALSE); //$NON-NLS-1$
                                mappingTypes.add(mappingType);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new SystemException(e);
        } catch (ParserConfigurationException e) {
            throw new SystemException(e);
        } catch (SAXException e) {
            throw new SystemException(e);
        }
    }

    /**
     * Get children of type ELEMENT_NODE from parent <code>parentNode</code>.
     * 
     * @param parentNode
     * @return
     */
    private static List<Node> getChildElementNodes(Node parentNode) {
        Node childNode = parentNode.getFirstChild();
        ArrayList<Node> list = new ArrayList<Node>();
        while (childNode != null) {
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                list.add(childNode);
            }
            childNode = childNode.getNextSibling();
        }
        return list;
    }

    /**
     * Create and return a MappingTypeRetriever which helps to retrieve dbType from a talendType or the contrary.
     * 
     * @param dbmsId
     * @return new MappingTypeRetriever loaded with Dbms found with given dbmsId
     */
    public static MappingTypeRetriever getMappingTypeRetriever(String dbmsId) {
        if (dbmsId == null) {
            throw new IllegalArgumentException();
        }
        Dbms dbms = getDbms(dbmsId);
        if (dbms == null) {
            return null;
        }
        return new MappingTypeRetriever(dbms);
    }

    /**
     * 
     * Return the default Talend type function of the current language.
     * 
     * @return the default Talend type function of the current language
     */
    public static String getDefaultTalendType() {
        if (codeLanguage == ECodeLanguage.JAVA) {
            return JavaTypesManager.getDefaultJavaType().getId();
        } else if (codeLanguage == ECodeLanguage.PERL) {
            return ""; //$NON-NLS-1$
        } else {
            throw new IllegalStateException("Case not found"); //$NON-NLS-1$
        }
    }

    public static void main(String[] args) {
        codeLanguage = ECodeLanguage.JAVA;
        try {
            MetadataTalendType.loadCommonMappings();
        } catch (SystemException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String dbmsId = "oracle_id"; //$NON-NLS-1$
        Dbms dbms = getDbms(dbmsId);
        System.out.println("Oracle10g dbms:" + dbms);
        System.out.println("Oracle10g types:" + Arrays.asList(getDbTypes(dbmsId)));

        MappingTypeRetriever mappingTypeRetriever = getMappingTypeRetriever(dbmsId);
        System.out.println("java int(id_Integer) => " + mappingTypeRetriever.getDefaultSelectedDbType("id_Integer"));

        System.out.println("Db INT => " + mappingTypeRetriever.getDefaultSelectedTalendType("INT"));

        System.out.println("java UNKNOWN TYPE => " + mappingTypeRetriever.getDefaultSelectedDbType("UNKNOWN"));

        System.out.println("Db UNKNOWN TYPE => " + mappingTypeRetriever.getDefaultSelectedTalendType("UNKNOWN"));

        System.out.println();
    }

}
