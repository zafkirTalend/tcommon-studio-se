package com.quantum.model.xml;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import com.quantum.Messages;
import com.quantum.model.Bookmark;
import com.quantum.model.Check;
import com.quantum.model.Column;
import com.quantum.model.DatabaseObject;
import com.quantum.model.Entity;
import com.quantum.model.ForeignKey;
import com.quantum.model.JDBCDriver;
import com.quantum.model.Privilege;
import com.quantum.model.Schema;
import com.quantum.model.Sequence;
import com.quantum.model.Table;
import com.quantum.model.Trigger;
import com.quantum.model.View;
import com.quantum.util.connection.NotConnectedException;
import com.quantum.util.xml.XMLHelper;
import com.quantum.util.xml.XMLUtil;
import com.quantum.view.bookmark.DbObjectNode;
import com.quantum.view.bookmark.GroupNode;
import com.quantum.view.bookmark.QuickListNode;
import com.quantum.view.bookmark.SchemaNode;
import com.quantum.view.bookmark.TreeNode;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * @author BC
 */
public class ModelToXMLConverter {
    
    private static final ModelToXMLConverter instance = new ModelToXMLConverter();
    
    private ModelToXMLConverter() {
    }
    
    public static ModelToXMLConverter getInstance() {
        return ModelToXMLConverter.instance;
    }

    public void createRoot(Document document) {
        document.appendChild(document.createElement("SAVED_DATA"));
    }
    public void convert(Element bookmarkRoot, JDBCDriver jdbcDriver) {
        Document document = bookmarkRoot.getOwnerDocument();
        Element driverNode = document.createElement("jdbcDriver");
        driverNode.setAttribute("name", jdbcDriver.getName());
        driverNode.setAttribute("version", jdbcDriver.getVersion());
        driverNode.setAttribute("type", jdbcDriver.getType());
        driverNode.setAttribute("className", jdbcDriver.getClassName());
        
        String[] fileNames = jdbcDriver.getJarFileNames();
        for (int i = 0, length = fileNames == null ? 0 : fileNames.length; i < length; i++) {
        	if (fileNames[i] != null) {
				Element fileName = document.createElement("jar");
				fileName.setAttribute("fileName", fileNames[i]);
				driverNode.appendChild(fileName);
        	}
		}
        
        bookmarkRoot.appendChild(driverNode);
    }

    public void convert(Element bookmarkRoot, Bookmark b) {
        Document document = bookmarkRoot.getOwnerDocument();
        Element bookmark = XMLUtil.createElementText(bookmarkRoot,"bookmark", ""); //$NON-NLS-1$ //$NON-NLS-2$
        XMLUtil.createElementText(bookmark,"name", b.getName()); //$NON-NLS-1$
        XMLUtil.createElementText(bookmark,"username", b.getUsername()); //$NON-NLS-1$
        XMLUtil.createElementText(bookmark,"password", b.getPassword()); //$NON-NLS-1$
        XMLUtil.createElementText(bookmark,"prompt", b.getPromptForPassword() ? "true" : "false"); //$NON-NLS-1$
        XMLUtil.createElementText(bookmark,"connect", b.getConnect()); //$NON-NLS-1$
		XMLUtil.createElementText(bookmark,"autoCommit", b.isAutoCommit() ? "true" : "false"); //$NON-NLS-1$
		XMLUtil.createElementText(bookmark,"autoCommitPreference", b.getAutoCommitPreference()); //$NON-NLS-1$
		XMLUtil.createElementText(bookmark,"autoCommitIgnoreView", b.isIgnoreQueryViewSetting() ? "true" : "false"); //$NON-NLS-1$
		XMLUtil.createElementText(bookmark,"quoteAll", b.isQuoteAll() ? "true" : "false"); //$NON-NLS-1$
		XMLUtil.createElementText(bookmark,"sendQueryAsIs", b.isSendQueryAsIs() ? "true" : "false"); //$NON-NLS-1$
		XMLUtil.createElementText(bookmark,"stripNewline", b.isStripNewline() ? "true" : "false"); //$NON-NLS-1$
		XMLUtil.createElementText(bookmark,"defaultEncoding", b.getDefaultEncoding()); //$NON-NLS-1$
		XMLUtil.createElementText(bookmark,"driver", b.getJDBCDriver().getClassName()); //$NON-NLS-1$
        XMLUtil.createElementText(bookmark,"type", b.getJDBCDriver().getType()); //$NON-NLS-1$
        String[] fileNames = b.getJDBCDriver().getJarFileNames();
        for (int i = 0, length = fileNames == null ? 0 : fileNames.length; i < length; i++) {
			XMLUtil.createElementText(bookmark,"driverLocation", fileNames[i]);
		}
        Element otherSchemas = (Element) bookmark.appendChild(document.createElement("Other_Schemas")); //$NON-NLS-1$
        otherSchemas.setAttribute("schemaRule", 
        		b.useAllSchemas() 
					? "useAll" 
					: b.useUsernameAsSchema() ? "useDefault" : "useSelected");
        
        Schema[] schemas = b.getSchemaSelections();
        for (int i = 0, length = (schemas == null) ? 0 : schemas.length; i < length; i++) {
            XMLUtil.createElementText(
                otherSchemas,"schema", schemas[i].getName()); //$NON-NLS-1$
        }
        
        DatabaseObject[] quickList = b.getQuickListEntries();
        Element quickListEntity = document.createElement("quickList");
        for (int j = 0, length = (quickList == null) ? 0 : quickList.length;
            j < length;
            j++) {
            ModelToXMLConverter.getInstance().convert(quickListEntity, quickList[j], false, false);
        }
        bookmark.appendChild(quickListEntity);
        convertQueryList(b, bookmark);
    }

    
    public void convert(Element parent, DatabaseObject object) {
        convert(parent, object, true, false);
    }
	public void convert(Element parent, DatabaseObject object, boolean isFull) {
	        convert(parent, object, true, isFull);
	}
	/**
     * @param parent	The parent element where to append the XML generated
     * @param object	The database object with the metadata to convert to XML
     * @param recurse	If the database object must be recursed. 
     * 					If recurse is false, then only the topmost level of the object is converted (that means no columns, usually)
     * @param full		If recurse and full are true, all the metadata will be converted, including foreing keys, triggers, etc.
     */
    public void convert(Element parent, DatabaseObject object, boolean recurse, boolean full) {
        Document document = parent.getOwnerDocument();
        Element element = document.createElement(getEntityDOMNodeName(object));
        element.setAttribute("name", object.getName());
        if (object.getSchema() != null) {
            element.setAttribute("schema", object.getSchema());
        }
        element.setAttribute("isSynonym", object.isSynonym() ? "true" : "false");
        element.setAttribute("database", object.getBookmark().getAdapter().getDisplayName());
        element.setAttribute("bookmark", object.getBookmark().getName());
        if (recurse) {
        	if ( object instanceof Sequence ) {
        		Sequence sequence = (Sequence) object;
        		try {
					element.setAttribute("minValue", sequence.getMinValue());
					element.setAttribute("maxValue", sequence.getMaxValue());
					element.setAttribute("initialValue", sequence.getInitialValue());
					element.setAttribute("incrementBy", sequence.getIncrementBy());
					element.setAttribute("cycled", sequence.isCycled() ? "true" : "false");
					element.setAttribute("ordered", sequence.isOrdered() ? "true" : "false");
				} catch (NotConnectedException e) {
				} catch (SQLException e) {
				}
        	} else if ( object instanceof Table ) {
        		try {
        			Table table = (Table) object;
					convert(element, table.getColumns());
					if (full) {
						convert(element, table.getExportedKeys(), true);
						convert(element, table.getImportedKeys(), false);
					}
        		} catch (NotConnectedException e) {
        		} catch (SQLException e) {
        		}
        	} else if ( object instanceof View ) {
        		try {
        			View view = (View) object;
        			convert(element, view.getColumns());
        			if (view.getCreateStatement().length() > 0)
        				XMLUtil.createCDATA(element, "createStatement", view.getCreateStatement());
        		} catch (NotConnectedException e) {
        		} catch (SQLException e) {
        		}
        	}
        	// Get the triggers and checks and privileges associated and export them
        	if (recurse && full && object instanceof Entity) {
        		convertTriggers(element, (Entity) object);
        		convertChecks(element, (Entity) object);
				convertPrivileges(element, (Entity) object);
        	}

        }
       	parent.appendChild(element);
}

 
	private void convertPrivileges(Element parent, Entity entity) {
		Privilege[] privileges = null;
		try {
			privileges = entity.getPrivileges();
		} catch (NotConnectedException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (privileges != null) {
	        Document document = parent.getOwnerDocument();
	        for (int i = 0; i < privileges.length; i++) {
	        	Element element = document.createElement("privilege");
		        Privilege privilege = privileges[i];
				element.setAttribute("grantor", privilege.getGrantor());
				element.setAttribute("grantee", privilege.getGrantee());
				element.setAttribute("access", privilege.getAccess());
				element.setAttribute("isGrantable", privilege.isGrantable() ? "true" : "false");
				
				parent.appendChild(element);
			}		
		}
	}

	private void convert(Element parent, ForeignKey[] exportedKeys, boolean exported) {
		Document document = parent.getOwnerDocument();
        for (int i = 0; i < exportedKeys.length; i++) {
			Element element = document.createElement("foreignKey");
			ForeignKey key = exportedKeys[i];
			element.setAttribute("type", exported ? "exported" : "imported");
			element.setAttribute("name", key.getName());
			element.setAttribute("localSchema", key.getLocalEntitySchema());
			element.setAttribute("localTable", key.getLocalEntityName());
			element.setAttribute("foreignSchema", key.getForeignEntitySchema());
			element.setAttribute("foreignTable", key.getForeignEntityName());
			element.setAttribute("onUpdate", foreignKeyRuleToText(key.getUpdateRule()));
			element.setAttribute("onDelete", foreignKeyRuleToText(key.getDeleteRule()));
			for (int j = 0; j < key.getNumberOfColumns(); j++) {
				Element columnElement = document.createElement("joinedColumn");
				columnElement.setAttribute("position",String.valueOf(j+1));
				columnElement.setAttribute("local",key.getLocalColumnName(j));
				columnElement.setAttribute("foreign",key.getForeignColumnName(j));
				element.appendChild(columnElement);
			}
			parent.appendChild(element);
		}
	}

	private static String foreignKeyRuleToText(int rule) {
		if (rule == DatabaseMetaData.importedKeyCascade ) {
			return "cascade";
		} else if (rule == DatabaseMetaData.importedKeyNoAction) {
			return "noAction";
		} else if (rule == DatabaseMetaData.importedKeyRestrict) {
			return "restrict";
		} else if (rule == DatabaseMetaData.importedKeySetDefault) {
			return "setDefault";
		} else if (rule == DatabaseMetaData.importedKeySetNull) {
			return "setNull";
		} else {
			return "";
		}
	}

	
	/**
	 * @param element
	 * @param object
	 */
	private void convertTriggers(Element parent, Entity entity) {
		Trigger[] triggers = null;
		try {
			triggers = entity.getTriggers();
		} catch (NotConnectedException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (triggers != null) {
	        Document document = parent.getOwnerDocument();
	        for (int i = 0; i < triggers.length; i++) {
	        	Element element = document.createElement("trigger");
		        Trigger trigger = triggers[i];
				element.setAttribute("name", trigger.getName());
				element.setAttribute("moment", trigger.getMoment());
				element.setAttribute("forEach", trigger.getForEach());
				element.setAttribute("event", trigger.getEvent());
				element.setAttribute("columnName", trigger.getColumnName());
				element.setAttribute("language", trigger.getLanguage());
				element.setAttribute("referencing", trigger.getReferencing());
				if (trigger.getWhenClause() != null) {
					XMLUtil.createCDATA(element, "whenClause", trigger.getWhenClause());
				}
				element.setAttribute("status", trigger.getStatus());
				element.setAttribute("actionType", trigger.getActionType());
				XMLUtil.createCDATA(element, "body", trigger.getBody());
				
				parent.appendChild(element);
			}
			
		}
		
	}
    /**
	 * @param element
	 * @param object
	 */
	private void convertChecks(Element parent, Entity entity) {
		Check[] checks = null;
		try {
			checks = entity.getChecks();
		} catch (NotConnectedException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (checks != null) {
	        Document document = parent.getOwnerDocument();
	        for (int i = 0; i < checks.length; i++) {
	        	Element element = document.createElement("checkConstraint");
		        Check check = checks[i];
				element.setAttribute("name", check.getName());
				XMLUtil.createCDATA(element, "body", check.getBody());
				
				parent.appendChild(element);
			}
			
		}
		
	}

	public void convert(Element element, Column[] columns) {
        for (int i = 0, length = (columns == null) ? 0 : columns.length;
            i < length;
            i++) {
            convert(element, columns[i]);
        }
    }

    public void convert(Element parent, Column column) {
        Document document = parent.getOwnerDocument();
        Element element = document.createElement("column");
        element.setAttribute("name", column.getName());
        element.setAttribute("type", column.getTypeName());
        element.setAttribute("java.sql.Types", String.valueOf(column.getType()));
        element.setAttribute("primaryKey", column.isPrimaryKey() ? "true" : "false");
        element.setAttribute("keyOrder", String.valueOf(column.getPrimaryKeyOrder()));
        element.setAttribute("nullable", column.isNullable() ? "true" : "false");
        element.setAttribute("position", String.valueOf(column.getPosition()));
        if (column.isNumeric()) {
            if (column.getSize() > 0) {
                element.setAttribute("size", String.valueOf(column.getSize()));
            }
            if (column.getNumberOfFractionalDigits() > 0) {
                element.setAttribute("numberOfFractionalDigits", String.valueOf(column.getNumberOfFractionalDigits()));
            }
        } else {
            if (column.getSize() > 0) {
                element.setAttribute("size", String.valueOf(column.getSize()));
            }
        }
        // A database can make differences between a default value of "null" and an empty string
        if (column.getDefaultValue() != null)
        	XMLUtil.createElementText(element, "defaultValue", column.getDefaultValue());
		if (column.getRemarks().length() > 0)
			XMLUtil.createElementText(element, "remarks", column.getRemarks());

		
        parent.appendChild(element);
    }


    public String getEntityDOMNodeName(DatabaseObject object) {
        if (DatabaseObject.TABLE_TYPE.equals(object.getType())) {
            return "table";
        } else if (DatabaseObject.VIEW_TYPE.equals(object.getType())) {
            return "view";
        } else if (DatabaseObject.SEQUENCE_TYPE.equals(object.getType())) {
            return "sequence";
        } else if (DatabaseObject.PACKAGE_TYPE.equals(object.getType())){
            return "package";
        } else {
            return "unknown";
        }
    }

    public void convertQueryList(
        Bookmark bookmark,
        Element parent) {
            
        Document document = parent.getOwnerDocument();
        String[] queryList = bookmark.getQueries();
        Element queryListEntity = document.createElement("queryList");
        for (int j = 0, length = (queryList == null) ? 0 : queryList.length;
            j < length;
            j++) {
            Element query = document.createElement("query");
            
            // will have more possibilities later
            Element queryString = document.createElement("queryString");
            Text queryText = document.createTextNode(queryList[j]);
            queryString.appendChild(queryText);
            query.appendChild(queryString);
            queryListEntity.appendChild(query);
        }
        parent.appendChild(queryListEntity);
    }

    /**
     * @param list List of TreeNodes (usually selected nodes in the bookmark view) to be converted to metadata
     * @param doc	XML document where the output is to be stored. If null, a new one will be created.
     * @param full	If the structure is to be comverted in full, including foreign keys, etc.
     * @return	An XML Document with the metadata of the ObjectDatabase nodes and the GroupNodes recursed 
     * @throws SQLException 
     * @throws NotConnectedException 
     */
    public Document convertList(List list, Document doc, boolean full) throws NotConnectedException, SQLException {
    	// If not provided with a document, it's a new document, so create and fill headers
    	if (doc == null) {
    		try {
    			doc = XMLHelper.createEmptyDocument();
    		} catch (ParserConfigurationException e) {
    			e.printStackTrace();
    			return null;
    		}
    		Element newRoot = (Element) doc.appendChild(doc.createElement(Messages.getString("ExportXMLAction.Metadata"))); //$NON-NLS-1$
    		XMLUtil.createElementText(newRoot, Messages.getString("ExportXMLAction.Author"), //$NON-NLS-1$
    				Messages.getString("ExportXMLAction.Quantum")); //$NON-NLS-1$
    		XMLUtil.createElementText(newRoot, Messages.getString("ExportXMLAction.Version"), //$NON-NLS-1$
    				Messages.getString("ExportXMLAction.XMLVersionNumber")); //$NON-NLS-1$
    	}
    	Element root = doc.getDocumentElement();
		// Now we have a document, either provided or created. Iterate the given list
    	Iterator iter = list.iterator();
    	while (iter.hasNext()) {
    		Object object = iter.next();
			// If it's a DbObject, we can directly extract the metadata
    		if ( object instanceof DbObjectNode ) {
    			ModelToXMLConverter.getInstance().convert(root, ((DbObjectNode) object).getDatabaseObject(), full );
			// if it's a group of nodes, iterate the group of nodes
    		} else if ( object instanceof GroupNode || object instanceof SchemaNode || object instanceof QuickListNode )
    		{
    			TreeNode groupNode = (TreeNode) object;
    			Object[] children = groupNode.getChildren();
    			for (int i = 0; i < children.length; i++) {
					if ( children[i] instanceof DbObjectNode ) {
		    			ModelToXMLConverter.getInstance().convert(root, ((DbObjectNode) children[i]).getDatabaseObject() , full);
					} else if ( children[i] instanceof GroupNode) {
		    			// If it's another group, recurse on it
						convertList( Arrays.asList(new GroupNode[] {(GroupNode)children[i]}), doc, full);
					}
    			}
    		}
    	}
    	return doc;
    }
}
