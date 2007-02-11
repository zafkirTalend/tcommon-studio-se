/*
 * Created on Oct 19, 2004
 */
package com.quantum.model;


/**
 * @author lleavitt
 */
public interface Procedure extends DatabaseObject {

    public Package getPackage();

    public int                 getOverload();
    public ProcedureArgument   getReturnType();
    public ProcedureArgument[] getArguments();
    public ProcedureArgument   getArgument( String name );
    public String			   getBody();
}
