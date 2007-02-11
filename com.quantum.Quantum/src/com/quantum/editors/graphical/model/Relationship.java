/*
 * Created on Jul 13, 2004
 */
package com.quantum.editors.graphical.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;


/**
 * Relates one table to another
 * 
 * @author Phil Zoio
 */
public class Relationship extends PropertyAwareObject implements IPropertySource
{
    public static final Integer SOLID_CONNECTION = new Integer(Graphics.LINE_SOLID);
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Table primaryKeyTable;
	private Table foreignKeyTable;
    private String foreignKeyName;
    private String primaryKeyName;
    private EntityRelationDiagram diagram;
    
    private String name;
    
	/**
	 * @param foreignTable
	 * @param primaryKeyTable
	 * @param foreignKeyColumn
	 */
	public Relationship(Table foreignTable, Table primaryTable)
	{
		super();
		this.primaryKeyTable = primaryTable;
		this.foreignKeyTable = foreignTable;
		this.primaryKeyTable.addPrimaryKeyRelationship(this);
		this.foreignKeyTable.addForeignKeyRelationship(this);
	}

    public Relationship(String name, Table foreignTable, String foreignKeyName, Table primaryTable, String primaryKeyName)
    {
        super();
        this.name = name;
        this.primaryKeyTable = primaryTable;
        this.foreignKeyTable = foreignTable;
        this.primaryKeyName = primaryKeyName;
        this.foreignKeyName = foreignKeyName;
        this.primaryKeyTable.addPrimaryKeyRelationship(this);
        this.foreignKeyTable.addForeignKeyRelationship(this);
    }

    /**
     * @return Returns the primaryKeyColumn.
     */
    public String getPrimaryKeyName()
    {
        return primaryKeyName;
    }
    /**
     * @return Returns the foreignKeyColumn.
     */
    public String getForeignKeyName()
    {
        return foreignKeyName;
    }

	/**
	 * @return Returns the foreignKeyTable.
	 */
	public Table getForeignKeyTable()
	{
		return foreignKeyTable;
	}

	/**
	 * @return Returns the primaryKeyTable.
	 */
	public Table getPrimaryKeyTable()
	{
		return primaryKeyTable;
	}

	/**
	 * @param sourceForeignKey the primary key table you are connecting to
	 */
	public void setPrimaryKeyTable(Table targetPrimaryKey)
	{
		this.primaryKeyTable = targetPrimaryKey;
	}	
	
	/**
	 * @param sourceForeignKey the foreign key table you are connecting from
	 */
	public void setForeignKeyTable(Table sourceForeignKey)
	{
		this.foreignKeyTable = sourceForeignKey;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public EntityRelationDiagram getDiagram()
    {
        if(diagram == null)
        {
            diagram = foreignKeyTable.getDiagram();
        }
        if(diagram == null)
        {
            diagram = primaryKeyTable.getDiagram();
        }
        return diagram; 
    }
    
    private List bendpoints = new ArrayList();
 
    public void addBendpoint(int index, Point point) {
        bendpoints.add(index, point);
        firePropertyChange(P_BEND_POINT, null, null);
    }
    
    public List getBendpoints() {
        return bendpoints;
    }
    
    public void removeBendpoint(int index) {
        bendpoints.remove(index);
        firePropertyChange(P_BEND_POINT, null, null);
    }
    
    public void replaceBendpoint(int index, Point point) {
        bendpoints.set(index, point);
        firePropertyChange(P_BEND_POINT, null, null);
    }

    private static final String NAME_PROP = "Table.Name";

    public Object getEditableValue() {
        return this;
    }

    public IPropertyDescriptor[] getPropertyDescriptors() {
        List list = new ArrayList();

        
        TextPropertyDescriptor tpd = new TextPropertyDescriptor(NAME_PROP,"Name");
        list.add(tpd);
     
        return (IPropertyDescriptor[]) list.toArray(new IPropertyDescriptor[list.size()]);
    }

    public Object getPropertyValue(Object id) {
        return getName();
    }

    public boolean isPropertySet(Object id) {
        return false;
    }

    public void resetPropertyValue(Object id) {
    }

    public void setPropertyValue(Object id, Object value) {
        setName((String)value);
    }
}