package com.quantum.sql.grammar;

import org.eclipse.jface.text.rules.IToken;

import com.quantum.model.DatabaseObject;

public interface IQToken extends IToken{

	public int getIndex();
	
	public int getType();
	
	public void setType(int type);
	
	public int getOffset();
	
	public DatabaseObject getDatabaseObject();

	public void setDatabaseObject(DatabaseObject o);
	
	// do we need stuff for the keys and columns?
	
}
