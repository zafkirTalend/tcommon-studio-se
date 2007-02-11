/*
 * Created on Oct 19, 2004
 */
package com.quantum.model;

/**
 * @author lleavitt
 */
public class ProcedureArgumentImpl implements ProcedureArgument 
{
    String name;
    String typename;
    int    index;
    
    public ProcedureArgumentImpl( String name, int index, String type )
    {
        this.name = name;
        this.index = index;
        this.typename = type;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public String getTypeName() {
        return typename;
    }
}
