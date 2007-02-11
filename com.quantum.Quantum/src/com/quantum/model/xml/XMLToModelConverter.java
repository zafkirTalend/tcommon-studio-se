/*
 * Created on 01.02.2005
 *
 * This class allows you to query an XML document and extract metadata information
 */
package com.quantum.model.xml;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import com.quantum.model.Check;
import com.quantum.model.CheckImpl;
import com.quantum.model.Column;
import com.quantum.model.ColumnImpl;
import com.quantum.model.ForeignKey;
import com.quantum.model.ForeignKeyImpl;
import com.quantum.model.Index;
import com.quantum.model.Privilege;
import com.quantum.model.PrivilegeImpl;
import com.quantum.model.SequenceMetadata;
import com.quantum.model.TableMetadata;
import com.quantum.model.Trigger;
import com.quantum.model.TriggerImpl;
import com.quantum.util.ModelUtil;
import com.quantum.util.connection.NotConnectedException;
import com.quantum.util.xml.XMLUtil;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * @author Julen
 *
 */
//TODO: Julen - I don't particularly like this structure, could be refactored into
//				something more flexible
public class XMLToModelConverter implements TableMetadata, SequenceMetadata {
	Element root;
	String type;
	
	public XMLToModelConverter(Element node) {
		this.root = node;
		this.type = root.getNodeName();
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.DatabaseObjectMetadata#getName()
	 */
	public String getName() {
		return XMLUtil.getElementAtribute(root,type,"name");
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.DatabaseObjectMetadata#getSchema()
	 */
	public String getSchema() {
		return XMLUtil.getElementAtribute(root,type,"schema");
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.DatabaseObjectMetadata#getQualifiedName()
	 */
	public String getQualifiedName() {
		String schema = getSchema();
		String name = getName();
        return (schema == null || schema.length() == 0) ?
            name : schema + "." + name;
    }

	/* (non-Javadoc)
	 * @see com.quantum.model.DatabaseObjectMetadata#isSynonym()
	 */
	public boolean isSynonym() {
		String isSynonym = XMLUtil.getElementAtribute(root,type,"isSynonym");
		return (isSynonym.equals("true"));
	}

	/**
	 * @return An array of Column objects, with the columns of the first table
	 * 			in the XML Element, the array will be empty if none found
	 */
	public Column[] getColumns() {
		NodeList columnNodes = root.getElementsByTagName("column");
		Column[] columns = new Column[columnNodes.getLength()];
		for (int i = 0; i < columnNodes.getLength(); i++) {
			Element columnElement = (Element) columnNodes.item(i);
			columns[i] = convertColumn(columnElement);
		}
		return columns;
	}

	/**
	 * @param columnElement An XML Element with the column data
	 * @return	a Column object with the Element data
	 */
	private Column convertColumn(Element columnElement) {
		int javaType;
		// First, some attributes could be missing, and that's a no-go
		try {
			javaType = Integer.parseInt(columnElement.getAttribute("java.sql.Types"));
		} catch (NumberFormatException e) {
			// If the javaType is not present, there is a problem
			return null;
		}
		ColumnImpl newColumn = new ColumnImpl(
	            null, 
	            columnElement.getAttribute("name"),
	            columnElement.getAttribute("type"),
				javaType,
				columnElement.getAttribute("size").equals("") ? 0 : Long.parseLong(columnElement.getAttribute("size")),
				columnElement.getAttribute("numberOfFractionalDigits").equals("") ? 0 : Integer.parseInt(columnElement.getAttribute("numberOfFractionalDigits")),
	            "TRUE".equalsIgnoreCase(columnElement.getAttribute("nullable")),
	            // position should always be present, so it's better to give an error (NumberFormatException will be thrown if empty string) 
				Integer.parseInt(columnElement.getAttribute("position")),
				// we want to difference between a column with no default value, and one with a default value of ""
	            !columnElement.hasAttribute("defaultValue") ? null : columnElement.getAttribute("defaultValue"),
				XMLUtil.getElementText(columnElement,"remarks","")
	            );
		String keyOrder = columnElement.getAttribute("keyOrder");
		newColumn.setPrimaryKeyOrder(keyOrder == "" ? 0 : Integer.decode(keyOrder).intValue());
		return newColumn;
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.TableMetadata#getIndexes()
	 */
	public Index[] getIndexes() throws NotConnectedException, SQLException {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.TableMetadata#getColumn(java.lang.String)
	 */
	public Column getColumn(String columnName) throws NotConnectedException, SQLException {
		Column columns[] = getColumns();
		Column column = null;
		
		for (int i = 0, length = (columns == null) ? 0 : columns.length;
		column == null && i < length;
		i++) {
			if (columnName != null && columnName.equals(columns[i].getName())) {
				column = columns[i];
			}
		}
		return column;
	}


	private ForeignKey convertForeignKey(Element keyElement) {
		ForeignKeyImpl newForeignKey = new ForeignKeyImpl();
		newForeignKey.setName(keyElement.getAttribute("name"));
		newForeignKey.setForeignEntitySchema(keyElement.getAttribute("foreignSchema"));
		newForeignKey.setForeignEntityName(keyElement.getAttribute("foreignTable"));
		newForeignKey.setLocalEntitySchema(keyElement.getAttribute("localSchema"));
		newForeignKey.setLocalEntityName(keyElement.getAttribute("localTable"));
		newForeignKey.setUpdateRule(foreignKeyRuleToInt(keyElement.getAttribute("onUpdate")));
		newForeignKey.setDeleteRule(foreignKeyRuleToInt(keyElement.getAttribute("onDelete")));
		NodeList columnNodes = root.getElementsByTagName("joinedColumn");
		for (int i = 0; i < columnNodes.getLength(); i++) {
			Element element = (Element) columnNodes.item(i);
			newForeignKey.addColumns(element.getAttribute("local"), element.getAttribute("foreign"));
		}
		
		return newForeignKey;
	}

	private static int foreignKeyRuleToInt(String rule) {
		if (rule == "cascade") {
			return DatabaseMetaData.importedKeyCascade;
		} else if (rule == "noAction") {
			return DatabaseMetaData.importedKeyNoAction;
		} else if (rule == "restrict") {
			return DatabaseMetaData.importedKeyRestrict;
		} else if (rule == "setDefault") {
			return DatabaseMetaData.importedKeySetDefault;
		} else {
			return DatabaseMetaData.importedKeySetNull;
		} 
	}
	
	/* (non-Javadoc)
	 * @see com.quantum.model.TableMetadata#getImportedKeys()
	 */
	public ForeignKey[] getImportedKeys() throws NotConnectedException, SQLException {
		return getForeignKeys(true);
	}
	/* (non-Javadoc)
	 * @see com.quantum.model.TableMetadata#getExportedKeys()
	 */
	public ForeignKey[] getExportedKeys() throws NotConnectedException, SQLException {
		return getForeignKeys(false);
	}

	private ForeignKey[] getForeignKeys(boolean imported) {
		NodeList foreignKeyNodes = root.getElementsByTagName("foreignKey");
		NodeList exportedKeyNodes = XMLUtil.selectNodesOnAttributeValue(foreignKeyNodes, "type", imported ? "imported" : "exported");
		ForeignKey[] key = new ForeignKey[exportedKeyNodes.getLength()];
		for (int i = 0; i < exportedKeyNodes.getLength(); i++) {
			Element keyElement = (Element) exportedKeyNodes.item(i);
			key[i] = convertForeignKey(keyElement);
		}
		return key;

	}

	/* (non-Javadoc)
	 * @see com.quantum.model.TableMetadata#getReferences()
	 */
	public ForeignKey[] getReferences() throws NotConnectedException, SQLException {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.TableMetadata#getCreateStatement()
	 */
	public String getCreateStatement() throws NotConnectedException, SQLException {
		return XMLUtil.getElementText(root,"createStatement");
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.SequenceMetadata#getMinValue()
	 */
	public String getMinValue() {
		return XMLUtil.getElementAtribute(root,type,"minValue");
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.SequenceMetadata#getMaxValue()
	 */
	public String getMaxValue() {
		return XMLUtil.getElementAtribute(root,type,"maxValue");
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.SequenceMetadata#getInitialValue()
	 */
	public String getInitialValue() {
		return XMLUtil.getElementAtribute(root,type,"initialValue");
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.SequenceMetadata#getIncrementBy()
	 */
	public String getIncrementBy() {
		return XMLUtil.getElementAtribute(root,type,"incrementBy");
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.SequenceMetadata#isCycled()
	 */
	public boolean isCycled() {
		return XMLUtil.getElementAtribute(root,type,"isCycled").equals("true");
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.SequenceMetadata#isOrdered()
	 */
	public boolean isOrdered() {
		return XMLUtil.getElementAtribute(root,type,"isOrdered").equals("true");
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.TableMetadata#getPrimaryKeyColumns()
	 */
	public Column[] getPrimaryKeyColumns() 
	throws NotConnectedException, SQLException {
		return ModelUtil.extractPrimaryKeyColumns(getColumns());
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.TableMetadata#getTriggers()
	 */
	public Trigger[] getTriggers() throws NotConnectedException, SQLException {
		NodeList triggerNodes = root.getElementsByTagName("trigger");
		Trigger[] triggers = new Trigger[triggerNodes.getLength()];
		for (int i = 0; i < triggerNodes.getLength(); i++) {
			Element triggerElement = (Element) triggerNodes.item(i);
			triggers[i] = converTrigger(triggerElement);
		}
		return triggers;
	}

	/**
	 * @param triggerElement
	 * @return
	 */
	private Trigger converTrigger(Element triggerElement) {
		TriggerImpl newTrigger = new TriggerImpl(
	            triggerElement.getAttribute("name"),
	            triggerElement.getAttribute("moment"),
	            triggerElement.getAttribute("forEach"),
	            triggerElement.getAttribute("event"),
	            triggerElement.getAttribute("columnName"),
	            triggerElement.getAttribute("language"),
	            triggerElement.getAttribute("referencing"),
				XMLUtil.getElementText(triggerElement,"whenClause",""),
				triggerElement.getAttribute("status"),
				triggerElement.getAttribute("actionType"),
				XMLUtil.getElementText(triggerElement,"body","")
	            );
		return newTrigger;
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.TableMetadata#getChecks()
	 */
	public Check[] getChecks() throws NotConnectedException, SQLException {
		NodeList checkNodes = root.getElementsByTagName("checkConstraint");
		Check[] checks = new Check[checkNodes.getLength()];
		for (int i = 0; i < checkNodes.getLength(); i++) {
			Element checkElement = (Element) checkNodes.item(i);
			checks[i] = new CheckImpl (
						checkElement.getAttribute("name"),
						XMLUtil.getElementText(checkElement,"body","")
            			);
		}
		return checks;
	}

	public Privilege[] getPrivileges() throws NotConnectedException, SQLException {
		NodeList privilegeNodes = root.getElementsByTagName("privilege");
		Privilege[] privileges = new Privilege[privilegeNodes.getLength()];
		for (int i = 0; i < privilegeNodes.getLength(); i++) {
			Element privilegeElement = (Element) privilegeNodes.item(i);
			privileges[i] = new PrivilegeImpl (
						privilegeElement.getAttribute("grantor"),
						privilegeElement.getAttribute("grantee"),
						privilegeElement.getAttribute("access"),
						privilegeElement.getAttribute("isGrantable").equals("true") ? true : false
            			);
		}
		return privileges;
	}
}
