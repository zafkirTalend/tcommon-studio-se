/*
 * Created on Jul 13, 2004
 */
package com.quantum.editors.graphical.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents our allowable data types - a small subset of those available in a real database!
 * @author Phil Zoio
 * adapted for use with Quantum
 */
public class ColumnType
{

	private static List types = new ArrayList();

	private String type;

	public ColumnType(String type)
	{
		this.type = type;
		String typeToAdd = this.getType();
		types.add(typeToAdd);
	}

	/**
	 * @return Returns the type.
	 */
	public String getType()
	{
		return type;
	}

	public static boolean hasType(String type)
	{
		return types.contains(type);
	}

	public static String getTypes()
	{
		StringBuffer typeBuffer = new StringBuffer();
		for (Iterator iter = types.iterator(); iter.hasNext();)
		{
			String element = (String) iter.next();
			typeBuffer.append(element).append(", ");
		}
		if (types.size() >= 1)
		{
			typeBuffer.delete(typeBuffer.length() - 2, typeBuffer.length());
		}
		return typeBuffer.toString();
	}
    
    public static String[] getTypeNames()
    {
        List list = new ArrayList(types.size());
        list.addAll(types);
        return ((String[])list.toArray(new String[types.size()]));
    }
    
    public static void clear()
    {
        types.clear();
    }

}