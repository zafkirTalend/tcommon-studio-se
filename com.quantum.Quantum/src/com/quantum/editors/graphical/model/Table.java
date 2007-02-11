/*
 * Created on Jul 13, 2004
 */
package com.quantum.editors.graphical.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.quantum.actions.BookmarkSelectionUtil;
import com.quantum.log.QuantumLog;
import com.quantum.model.Bookmark;
import com.quantum.model.Check;
import com.quantum.model.ForeignKey;
import com.quantum.model.ForeignKeyImpl;
import com.quantum.model.Index;
import com.quantum.model.Privilege;
import com.quantum.model.TableMetadata;
import com.quantum.model.Trigger;
import com.quantum.util.connection.NotConnectedException;
import com.quantum.util.sql.TypesHelper;

/**
 * Model object representing a relational database Table
 * adapted from Phil Zoio
 * Implements TableMetaData, so it can create CREATE TABLE
 * statements specific to the Bookmark's database.
 */
public class Table extends PropertyAwareObject implements TableMetadata, IPropertySource
{

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    private EntityRelationDiagram diagram;
	private String name;
    private String alias;
	private ArrayList columns = new ArrayList();
	private Rectangle bounds;

	private ArrayList primaryKeyRelationships = new ArrayList();
	private ArrayList foreignKeyRelationships = new ArrayList();

    private String createStatement;
    private String whereClause;

	public Table()
	{
		super();
	}

	/**
	 * @param name
	 */
	public Table(String name, EntityRelationDiagram diagram)
	{
		super();
		if (name == null)
			throw new NullPointerException("Name cannot be null");
		if (diagram == null)
			throw new NullPointerException("Diagram cannot be null");
		this.name = name;
		this.diagram = diagram;
	}

    public Table(String name, String alias, EntityRelationDiagram diagram)
    {
        super();
        if (name == null)
            throw new NullPointerException("Name cannot be null");
        if (diagram == null)
            throw new NullPointerException("Diagram cannot be null");
        this.name = name;
        this.alias = alias;
        this.diagram = diagram;
    }

    public String createCreateStatement()
    {
        Bookmark bm = BookmarkSelectionUtil.getInstance().getLastUsedBookmark();
        try {
            createStatement = bm.getAdapter().buildCreateTable(bm, this, null, false);
            QuantumLog.getInstance().info(createStatement);
            return createStatement;
        } catch (NotConnectedException e) {
        } catch (SQLException e) {
        }
        return "";
    }
	public void addColumn(Column column)
	{
		if (columns.contains(column))
		{
			throw new IllegalArgumentException("Column already present");
		}
        if(column.getName()==null)
        {
            // this one must be dropped/clicked from the palette.
            // this means it may have type, but the fields that define the type are not yet filled.
            if(column.getType()!=null)
            {
                // we have to convert from database type to java type
                int type = TypesHelper.getType(column.getJavaType());
                column.setNumeric(TypesHelper.isNumeric(type));
                column.setReal(TypesHelper.isReal(type));
            }
        }
		columns.add(column);
		firePropertyChange(CHILD, null, column);
	}

	public void addColumn(Column column, int index)
	{
		if (columns.contains(column))
		{
			throw new IllegalArgumentException("Column already present");
		}
		columns.add(index, column);
		firePropertyChange(CHILD, null, column);
	}

	public void removeColumn(Column column)
	{
		columns.remove(column);
		firePropertyChange(CHILD, column, null);
	}

	public void switchColumn(Column column, int index)
	{
		columns.remove(column);
		columns.add(index, column);
		firePropertyChange(REORDER, this, column);
	}

	/**
	 * Sets name without firing off any event notifications
	 * 
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @param schema
	 *            The schema to set.
	 */
	public void setDiagram(EntityRelationDiagram diagram)
	{
		this.diagram = diagram;
	}

	/**
	 * Sets bounds without firing off any event notifications
	 * 
	 * @param bounds
	 *            The bounds to set.
	 */
	public void setBounds(Rectangle bounds)
	{
		this.bounds = bounds;
	}

	/**
	 * Adds relationship where the current object is the foreign key table in a relationship
	 * 
	 * @param table
	 *            the primary key relationship
	 */
	public void addForeignKeyRelationship(Relationship table)
	{
		foreignKeyRelationships.add(table);
        for(int i=0; i < table.getForeignKeyTable().getModelColumns().size(); i++)
        {
            Column c = (Column) table.getForeignKeyTable().getModelColumns().get(i);
            if(c.getName().equals(table.getForeignKeyName()))
            {
                c.setForeignKey(true);
                break;
            }
        }
		firePropertyChange(OUTPUT, null, table);
	}

	/**
	 * Adds relationship where the current object is the primary key table in a relationship
	 * 
	 * @param table
	 *            the foreign key relationship
	 */
	public void addPrimaryKeyRelationship(Relationship table)
	{
		primaryKeyRelationships.add(table);
        for(int i=0; i < table.getPrimaryKeyTable().getModelColumns().size(); i++)
        {
            Column c = (Column) table.getPrimaryKeyTable().getModelColumns().get(i);
            if(c.getName().equals(table.getPrimaryKeyName()))
            {
                c.setPrimaryKey(true);
                break;
            }
        }
		firePropertyChange(INPUT, null, table);
	}

	/**
	 * Removes relationship where the current object is the foreign key table in a relationship
	 * 
	 * @param table
	 *            the primary key relationship
	 */
	public void removeForeignKeyRelationship(Relationship table)
	{
		foreignKeyRelationships.remove(table);
		firePropertyChange(OUTPUT, table, null);
	}

	/**
	 * Removes relationship where the current object is the primary key table in a relationship
	 * 
	 * @param table
	 *            the foreign key relationship
	 */
	public void removePrimaryKeyRelationship(Relationship table)
	{
		primaryKeyRelationships.remove(table);
		firePropertyChange(INPUT, table, null);
	}

	/**
	 * If modified, sets name and fires off event notification
	 * 
	 * @param name
	 *            The name to set.
	 */
	public void modifyName(String name)
	{
		String oldName = this.name;
		if (!name.equals(oldName))
		{
			this.name = name;
			firePropertyChange(NAME, null, name);
		}
	}

    /**
     * If modified, sets name and fires off event notification
     * 
     * @param name
     *            The name to set.
     */
    public void modifyAlias(String name)
    {
        String oldName = this.alias;
        if (!name.equals(oldName))
        {
            this.alias= name;
            firePropertyChange(ALIAS, oldName, name);
        }
    }

	/**
	 * If modified, sets bounds and fires off event notification
	 * 
	 * @param bounds
	 *            The bounds to set.
	 */
	public void modifyBounds(Rectangle bounds)
	{
		Rectangle oldBounds = this.bounds;
		if (!bounds.equals(oldBounds))
		{
			this.bounds = bounds;
			firePropertyChange(BOUNDS, null, bounds);
		}
	}
 
    public String getTableName()
    {
        return name;
    }
    
	public String getName()
	{
        if(alias != null)
        {
            if(!alias.equals("")){
                return alias;
            }
        }
		return name;
	}

	public List getModelColumns() // renamed because of the Metadata name clash
	{
		return columns;
	}

	/**
	 * @return Returns the foreignKeyRelationships.
	 */
	public List getForeignKeyRelationships()
	{
		return foreignKeyRelationships;
	}

	/**
	 * @return Returns the primaryKeyRelationships.
	 */
	public List getPrimaryKeyRelationships()
	{
		return primaryKeyRelationships;
	}

	/**
	 * @return Returns the schema.
	 */
	public EntityRelationDiagram getDiagram()
	{
		return diagram;
	}

	/**
	 * @return Returns the bounds.
	 */
	public Rectangle getBounds()
	{
		return bounds;
	}

	public String toString()
	{
		return name;
	}

	/**
	 * hashcode implementation for use as key in Map
	 */
	public int hashCode()
	{
		//just an extra line so that this does not fall over when the tool is used incorrectly
		if (diagram == null || name == null)
			return super.hashCode();
		String schemaName = diagram.getName();
		return schemaName.hashCode() + name.hashCode();
	}

	/**
	 * equals implementation for use as key in Map
	 */
	public boolean equals(Object o)
	{
		if (o == null || !(o instanceof Table))
			return false;
		Table t = (Table) o;

		if (diagram.getName().equals(t.getDiagram().getName()) && name.equals(t.getTableName()) && ((alias == null)?true:alias.equals(t.getAlias())))
		{
			return true;
		}
		else
			return false;
	}

    public com.quantum.model.Column[] getPrimaryKeyColumns() throws NotConnectedException, SQLException {
        List list = new ArrayList();
        for(int i=0; i<columns.size(); i++)
        {
            Column c = (Column) columns.get(i);
            if(c.isPrimaryKey())
            {
                list.add(new com.quantum.model.ColumnImpl(
                        null, c.getName(), c.getType(), 
                        com.quantum.util.sql.TypesHelper.getType(c.getType()), 
                        c.getSize(), c.getPrecision(), c.isNullable(), i, null, null));
            }
        }
        return (com.quantum.model.Column[])list.toArray(new com.quantum.model.Column[list.size()]);
    }

    public Index[] getIndexes() throws NotConnectedException, SQLException {
        return null;
    }

    public com.quantum.model.Column getColumn(String columnName) throws NotConnectedException, SQLException {
        return null;
    }

    public ForeignKey[] getExportedKeys() throws NotConnectedException, SQLException {
        ArrayList list = new ArrayList();
        for(int i = 0; i < getPrimaryKeyRelationships().size(); i++)
        {
            Relationship r = (Relationship) getPrimaryKeyRelationships().get(i);
            ForeignKeyImpl fk = new ForeignKeyImpl();
            fk.setName(r.getName());
            fk.setForeignEntityName(r.getForeignKeyTable().getName());
            fk.setLocalEntityName(r.getPrimaryKeyTable().getName());
            list.add(fk);
        }
        return (ForeignKey[]) list.toArray(new ForeignKey[list.size()]);
    }

    public ForeignKey[] getImportedKeys() throws NotConnectedException, SQLException {
        // this would be our foreign keys
        ArrayList list = new ArrayList();
        for(int i = 0; i < getForeignKeyRelationships().size(); i++)
        {
            Relationship r = (Relationship) getForeignKeyRelationships().get(i);
            ForeignKeyImpl fk = new ForeignKeyImpl();
            fk.setName(r.getName());
            fk.setForeignEntityName(r.getForeignKeyTable().getName());
            fk.setLocalEntityName(r.getPrimaryKeyTable().getName());
            fk.addColumns(r.getPrimaryKeyName(), r.getForeignKeyName());
            list.add(fk);
        }
        return (ForeignKey[]) list.toArray(new ForeignKey[list.size()]);
    }

    public ForeignKey[] getReferences() throws NotConnectedException, SQLException {
        return null;
    }

    public String getCreateStatement() throws NotConnectedException, SQLException {
        return null;
    }

    public Trigger[] getTriggers() throws NotConnectedException, SQLException {
        return null;
    }

    public Check[] getChecks() throws NotConnectedException, SQLException {
        return null;
    }

    public Privilege[] getPrivileges() throws NotConnectedException, SQLException {
        return null;
    }

    public String getSchema() {
        return null;
    }

    public String getQualifiedName() {
        return null;
    }

    public boolean isSynonym() {
        return false;
    }

    public com.quantum.model.Column[] getColumns() throws NotConnectedException, SQLException {
        List list = new ArrayList();
        for(int i=0; i<columns.size(); i++)
        {
            Column c = (Column) columns.get(i);
            list.add(new com.quantum.model.ColumnImpl(
                    null, c.getName(), c.getType(), 
                    com.quantum.util.sql.TypesHelper.getType(c.getType()), 
                    c.getSize(), c.getPrecision(), c.isNullable(), i, null, null));
        }
        return (com.quantum.model.Column[])list.toArray(new com.quantum.model.Column[list.size()]);
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        if(alias.equals("")){
            this.alias = null;
        }else{
            this.alias = alias;
        }
    }

    // ---------------------------------------------------
    // IPropertySource
    
    private static final String XPOS_PROP = "Table.xPos";
    private static final String YPOS_PROP = "Table.yPos";
    private static final String NAME_PROP = "Table.Name";
    private static final String ALIAS_PROP = "Table.Alias";

    public Object getEditableValue() {
        return this;
    }

    public IPropertyDescriptor[] getPropertyDescriptors() {
        List list = new ArrayList();

        PropertyDescriptor pd;
        
        TextPropertyDescriptor tpd = new TextPropertyDescriptor(XPOS_PROP, "X");
        tpd.setCategory("Location");
        list.add(tpd);
        
        tpd = new TextPropertyDescriptor(YPOS_PROP, "Y");
        tpd.setCategory("Location");
        list.add(tpd);
        
        tpd = new TextPropertyDescriptor(NAME_PROP,"Name");
        tpd.setCategory("Name");
        list.add(tpd);
        
        tpd = new TextPropertyDescriptor(ALIAS_PROP,"Alias");
        tpd.setCategory("Name");
        list.add(tpd);
        
        for (int i = 0; i < getModelColumns().size(); i++) {
            Column c = (Column) getModelColumns().get(i);
            pd = new PropertyDescriptor(c.getName(), c.getName());
            pd.setLabelProvider(new NullLabelProvider());
            pd.setCategory("Columns");
            list.add(pd);
        }
        
        return (IPropertyDescriptor[]) list.toArray(new IPropertyDescriptor[list.size()]);
    }

    public Object getPropertyValue(Object id) {
        if (XPOS_PROP.equals(id)) {
            return Integer.toString(getBounds().x);
        }
        if (YPOS_PROP.equals(id)) {
            return Integer.toString(getBounds().y);
        }
        if (NAME_PROP.equals(id)){
            return getTableName();
        }
        if (ALIAS_PROP.equals(id)){
            String alias = getAlias();
            return (alias==null)?"":alias;
        }
        for (int i = 0; i < getModelColumns().size(); i++) {
            Column c = (Column) getModelColumns().get(i);
            if(c.getName().equals(id)){
                // this is our column
                return new ColumnPropertySource(c);
            }
        }
        return null;//super.getPropertyValue(id);
    }

    public boolean isPropertySet(Object id) {
        return false;
    }

    public void resetPropertyValue(Object id) {
    }

    public void setPropertyValue(Object id, Object value) {
        if (XPOS_PROP.equals(id)) {
            Rectangle r = getBounds().getCopy();
            int x = Integer.parseInt((String) value);
            r.x = x;
            modifyBounds(r);
        } else if (YPOS_PROP.equals(id)) {
            Rectangle r = getBounds().getCopy();
            int y = Integer.parseInt((String) value);
            r.y = y;
            modifyBounds(r);
        } else if (NAME_PROP.equals(id)){
            String newName = (String) value;
            modifyName(newName);
        } else if (ALIAS_PROP.equals(id)){
            modifyAlias((String)value);
        }
    }
    
    private class NullLabelProvider implements ILabelProvider {

        public Image getImage(Object element) {
            return null;
        }

        public String getText(Object element) {
            return null;
        }

        public void addListener(ILabelProviderListener listener) {
        }

        public void dispose() {
        }

        public boolean isLabelProperty(Object element, String property) {
            return false;
        }

        public void removeListener(ILabelProviderListener listener) {
        }
    }
    
    private class ColumnPropertySource implements IPropertySource {
        private final String COL_TYPE_PROP = "Column.Type";

        private final String COL_SIZE_PROP = "Column.Size";

        private final String COL_PRECISION_PROP = "Column.Precision";
        
        private final String COL_NAME_PROP = "Column.Name";
        
//        private final String COL_FILTER_PROP = "Column.Filter";

        private String columnName;
        
        private Column column;
        
        ColumnPropertySource(Column c)
        {
            this.columnName= c.getName();
            this.column = c;
        }
        
        public Object getEditableValue() {
            return this;
        }

        public IPropertyDescriptor[] getPropertyDescriptors() {
            List list = new ArrayList();
            
            TextPropertyDescriptor tpd = new TextPropertyDescriptor(COL_NAME_PROP, "Name");
            tpd.setCategory(columnName);
            list.add(tpd);
            
            ComboBoxPropertyDescriptor cpd = new ComboBoxPropertyDescriptor(COL_TYPE_PROP, "Type", ColumnType.getTypeNames());
            cpd.setCategory(columnName);
            list.add(cpd);
            
            tpd = new TextPropertyDescriptor(COL_SIZE_PROP, "Size");
            tpd.setCategory(columnName);
            list.add(tpd);

            tpd = new TextPropertyDescriptor(COL_PRECISION_PROP, "Precision");
            tpd.setCategory(columnName);
            list.add(tpd);

//            tpd = new TextPropertyDescriptor(COL_FILTER_PROP, "Filter");
//            tpd.setCategory(columnName);
//            tpd.setLabelProvider(new NullLabelProvider());
//            list.add(tpd);
            
            return (IPropertyDescriptor[]) list
                    .toArray(new IPropertyDescriptor[list.size()]);
        }

        public Object getPropertyValue(Object id) {
            if(COL_NAME_PROP.equals(id)){
                return columnName;
            }
            if(COL_TYPE_PROP.equals(id)){
                String type = column.getType();
                for(int i=0; i < ColumnType.getTypeNames().length; i++){
                    if(ColumnType.getTypeNames()[i].equals(type)){
                        return new Integer(i);
                    }
                }
            }
            if(COL_SIZE_PROP.equals(id)){
                return new Long(column.getSize());
            }
            if(COL_PRECISION_PROP.equals(id)){
                return new Integer(column.getPrecision());
            }
//            if(COL_FILTER_PROP.equals(id)){
//                return new ColumnFilterPropertySource(column);
//            }
            return "";
        }

        public boolean isPropertySet(Object id) {
            return false;
        }

        public void resetPropertyValue(Object id) {
        }

        public void setPropertyValue(Object id, Object value) {
            if(COL_NAME_PROP.equals(id)){
                column.setName((String) value);
                return;
            }
            if(COL_TYPE_PROP.equals(id)){
                column.setType(ColumnType.getTypeNames()[((Integer)value).intValue()]);
                return;
            }
        }
    }

    public void setWhereClause(String clause) {
        this.whereClause = clause;
    }

    public String getWhereClause(){
        if(whereClause != null)
        {
            String clause = whereClause;
            if(clause.startsWith(" WHERE ")){
                clause = clause.substring(7);
            }else if(clause.startsWith(" ORDER BY ")){
                return ""; // TODO: Extract ORDER BY columns...
            }
            return clause;
        }else{
            return "";
        }
    }
}