package routines.system;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MetadataTalendType {
	
	private static String MAPPING_FOLDER="xmlMappings";
	private static Map<String, Map<String,String>> DB_TO_TALEND_TYPES = Collections.synchronizedMap(new HashMap<String,Map<String,String>>());
	
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
    
	private static void load(File file) throws Exception{
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder analyser;
		try {
			analyser = documentBuilderFactory.newDocumentBuilder();
		
			Document document = analyser.parse(file);

	        NodeList dbmsNodes = document.getElementsByTagName("dbms"); //$NON-NLS-1$
            for (int iDbms = 0; iDbms < dbmsNodes.getLength(); iDbms++) {
                Node dbmsNode = dbmsNodes.item(iDbms);
                NamedNodeMap dbmsAttributes = dbmsNode.getAttributes();

                String dbmsIdValue = dbmsAttributes.getNamedItem("id").getNodeValue(); //$NON-NLS-1$
                
                if(DB_TO_TALEND_TYPES.containsKey(dbmsIdValue)){// the corresponding mapping file is already loaded.
                	break;
                }
                Map<String , String> map = Collections.synchronizedMap(new HashMap<String,String>());// use to store--DBType:TalendType
                DB_TO_TALEND_TYPES.put(dbmsIdValue, map);// store
                
                List<Node> childrens=getChildElementNodes(dbmsNode);
                childrens=childrens.subList(1, childrens.size());// get all the language nodes
                // find the java language node
                for(int i=0;i<childrens.size();i++){
                	if(!("java").equals(childrens.get(i).getAttributes().getNamedItem("name").getNodeValue())){
                		continue;
                	}
                	
                	Node javaNode=childrens.get(i);
                	List<Node> mappingDirections = getChildElementNodes(javaNode);
                	Node dbToTalendTypes = mappingDirections.get(1);// the element:dbToTalendTypes
                	List<Node> dbTypeSourcesList = getChildElementNodes(dbToTalendTypes);
                	
                    int dbTypeSourcesListListSize = dbTypeSourcesList.size();
                    for (int iDbTypeSource = 0; iDbTypeSource < dbTypeSourcesListListSize; iDbTypeSource++) {
                        Node dbTypeSource = dbTypeSourcesList.get(iDbTypeSource);

                        NamedNodeMap dbTypeAttributes = dbTypeSource.getAttributes();
                        Node dbTypeItem = dbTypeAttributes.getNamedItem("type"); //$NON-NLS-1$
                        if (dbTypeItem == null) {
                            continue;
                        }
                        String dbType = dbTypeItem.getNodeValue();
                        
                        List<Node> languageTypesNodes = getChildElementNodes(dbTypeSource);
                        
                        boolean defaultSelected = false;

                        for (int j = 0; j < languageTypesNodes.size(); j++) {

                            Node languageTypeNode = languageTypesNodes.get(j);

                            NamedNodeMap talendTypeAttributes = languageTypeNode.getAttributes();

                            Node talendTypeItem = talendTypeAttributes.getNamedItem("type"); //$NON-NLS-1$
                            if (talendTypeItem == null) {
                                continue;
                            }
                            String talendType = talendTypeItem.getNodeValue();

                            Node defaultSelectedItem = talendTypeAttributes.getNamedItem("default"); //$NON-NLS-1$

                            defaultSelected = defaultSelectedItem != null
                                    && defaultSelectedItem.getNodeValue().equalsIgnoreCase("true") ? true : false; //$NON-NLS-1$
                            if(defaultSelected==true){// store the default talendType for the DB type.
                            	map.put(dbType, talendType);
                            	break;
                            }
                        }
                        // no default value, then use the first one as the talend type
                        if(!defaultSelected){
                        	if(languageTypesNodes.size()>0){
                        		Node talendTypeItem=languageTypesNodes.get(0).getAttributes().getNamedItem("type");
                        		if(talendTypeItem!=null){
                        			map.put(dbType, talendTypeItem.getNodeValue());
                        		}
                        	}else{// there is no talend type
                        		map.put(dbType, "id_String");
                        	}
                        }
                    }
                	                	
                }
                
            }
	        
		} catch (ParserConfigurationException e) {
			throw new Exception(e); 
		} catch (SAXException e) {
			throw new Exception(e); 
		} catch (IOException e) {
			throw new Exception(e); 
		}


	}
	
	public static String getDefaultSelectedTalendType(String dbmsId, String dbmsType, int length, int precison) {
		if(dbmsId==null || "".equals(dbmsId) || dbmsType==null || "".equals(dbmsType)){
			return "id_String";
		}
		
		if(DB_TO_TALEND_TYPES.get(dbmsId)==null){

			URL url=MetadataTalendType.class.getResource("/"+MAPPING_FOLDER);
			File dir = new File(url.getPath());
			
			if(dir.isDirectory()){
				String dbms= dbmsId.substring(0, dbmsId.indexOf("_"));
				File[] listFiles = dir.listFiles();
				for(File file: listFiles){
					
					if (file.getName().toLowerCase().matches("^mapping_"+dbms+"\\.xml$")) { //$NON-NLS-1$

						try{
							load(file);
							break;
						}catch(Exception e){
							System.out.println(e.getMessage());
							return null;
						}
						
					}
				}
			}
			
		}
		
		if(DB_TO_TALEND_TYPES.get(dbmsId)!=null){
			return DB_TO_TALEND_TYPES.get(dbmsId).get(dbmsType);
		}
		
		return "id_String";
	}
	
}
