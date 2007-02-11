/*
 * Created on Jul 13, 2004
 */
package com.quantum.editors.graphical.model;

import java.sql.SQLException;

import com.quantum.actions.BookmarkSelectionUtil;
import com.quantum.model.Bookmark;
import com.quantum.model.DataType;
import com.quantum.util.connection.NotConnectedException;
import com.quantum.util.sql.TypesHelper;

/**
 * Column entry in model Table
 * @author Phil Zoio/JHvdV
 */
public class Column extends PropertyAwareObject
{

	/**
     * 
     */
    private static final long serialVersionUID = 2L;

	private String name;
    private String originalName; // for renaming
    private long originalSize;
    private int originalPrecision;
    private String originalType;
    
	private String type;
    private String javaType;
    private String aliasName;
    
    private boolean primaryKey = false;
    private boolean foreignKey = false;
    private boolean numeric = false;
    private boolean real = false;
    private boolean fixed = false;

    private long size;
    private int precision;
    private boolean selected = false;
    private boolean nullable = false;
    ;
	public boolean isNumeric() {
        return numeric;
    }

    public void setNumeric(boolean numeric) {
        this.numeric = numeric;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        int oldPrecision = getPrecision();
        this.precision = precision;
        firePropertyChange(SIZE_TYPE_PRECISION, new Integer(oldPrecision), new Integer(precision));
    }

    public void setOriginalPrecision(int precision) {
        this.originalPrecision = precision;
    }

    public boolean isReal() {
        return real;
    }

    public void setReal(boolean real) {
        this.real = real;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        long oldSize = getSize();
        this.size = size;
        firePropertyChange(SIZE_TYPE_PRECISION, new Long(oldSize), new Long(size));
    }

    public void setOriginalSize(long size) {
        this.originalSize = size;
    }

    public Column()
	{
		super();
	}	
	
	public Column(String name, ColumnType type)
	{
		super();
		this.name = name;
        this.originalName = name;
		this.type = type.getType();
	}

    public Column(String name, String type)
    {
        super();
        this.name = name;
        this.originalName = name;
        this.originalType = type;
        this.type = type;
    }
	
    public Column(String name, String type, boolean isPrimary, boolean isForeign, long size, int precision, boolean numeric, boolean real)
    {
        super();
        this.name = name;
        this.type = type;
        this.foreignKey = isForeign;
        this.primaryKey = isPrimary;
        this.nullable = !this.primaryKey;
        this.size = size;
        this.precision = precision;
        this.numeric = numeric;
        this.real = real;
        this.originalName = name;
        this.originalPrecision = precision;
        this.originalSize = size;
        this.originalType = type;
    }

    public Column(String name, String type, boolean isPrimary, boolean isForeign, long size, int precision, boolean numeric, boolean real, boolean selected)
    {
        super();
        this.name = name;
        this.type = type;
        this.foreignKey = isForeign;
        this.primaryKey = isPrimary;
        this.nullable = !this.primaryKey;
        this.size = size;
        this.precision = precision;
        this.numeric = numeric;
        this.real = real;
        this.originalName = name;
        this.originalPrecision = precision;
        this.originalSize = size;
        this.originalType = type;
        this.selected = selected;
    }

	/**
	 * @return Returns the name.
	 */
	public String getName()
	{
		return name;
	}

    public String getOriginalName()
    {
        return (originalName == null)?"":originalName;
    }
    public String getOriginalType()
    {
        return (originalType == null)?"":originalType;
    }
    public long getOriginalSize()
    {
        return originalSize;
    }
    public int getOriginalPrecision()
    {
        return originalPrecision;
    }
	/**
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name)
	{
        String oldName = getName();
        if(oldName == null){
            this.originalName = name;
        }
		this.name = name;
        firePropertyChange(NAME, oldName, name);
	}

    public void setAliasName(String alias)
    {
        String oldAlias = this.aliasName;
        this.aliasName = alias;
        firePropertyChange(ALIAS, oldAlias, alias);
    }
    
    public String getAliasName()
    {
        if(this.aliasName==null){
            return "";
        }
        return this.aliasName;
    }
    
    public void setPrimaryKey(boolean isPrimary)
    {
        this.primaryKey = isPrimary;
        this.nullable = !this.primaryKey;
        firePropertyChange(COLUMNIMAGE, null, new Boolean(isPrimary));
    }
    
    public boolean isPrimaryKey()
    {
        return this.primaryKey;
    }

    public void setForeignKey(boolean isForeign)
    {
        this.foreignKey = isForeign;
        firePropertyChange(COLUMNIMAGE, null, new Boolean(isForeign));
    }
    
    public boolean isNullable()
    {
        return this.nullable;
    }
    
    public boolean isForeignKey()
    {
        return this.foreignKey;
    }
    
	/**
	 * @return Returns the type.
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @param type
	 *            The type to set.
	 */
	public void setType(String type)
	{
		String oldType = this.type;
		this.type = type;
        // we need to pass the java type to the TypesHelper...
        // TODO: Create a map and store at editor level... This assumes the same database with the diagram forever...
        Bookmark bookmark = BookmarkSelectionUtil.getInstance().getLastUsedBookmark();
        DataType[] types = null;
        int javaType = 0;
        int idx = 0;
        try {
            types = bookmark.getDataTypes();
            for (idx = 0; idx < types.length; idx++) {
                if(types[idx].getDatabaseTypeName().equalsIgnoreCase(type)){
                    javaType = types[idx].getJavaType();
                    break;
                }
            }
            setJavaType(types[idx].getJavaNameType());
            setNumeric(TypesHelper.isNumeric(javaType));
            setReal(TypesHelper.isReal(javaType));
            fixed = TypesHelper.isFixedSize(javaType);
            firePropertyChange(SIZE_TYPE_PRECISION, oldType, type);
        } catch (NotConnectedException e) {
        } catch (SQLException e) {
        }
	}

    public void setOriginalType(String type)
    {
        this.originalType = type;
    }
	
	/**
	 * @param column
	 * @return
	 */
	public String getLabelText()
	{
        if(isNumeric())
        {
            if(isReal())
            {
                return getType() + "(" + getSize() + ", " + getPrecision() + ")";
            }else{
                return getType(); // no size for fixed types
            }
        }else{
            if(fixed)
            {
                return getType();
            }else{
                return getType() + "(" + getSize() + ")";
            }
        }
	}

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        firePropertyChange(MARKEDFOROUTPUT, null, new Boolean(selected));
    }
	
    public void toggleSelected()
    {
        setSelected(!isSelected());
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }
}