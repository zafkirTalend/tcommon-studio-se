package com.quantum.editors;

import com.quantum.model.DatabaseObject;
import com.quantum.sql.grammar.IQToken;

public class QToken implements IQToken {
	
	int index = 0;
	int type = 0;
	String text = null;
	String state = null;
	int offset = 0;
	
	public QToken() {
		super();
	}

	public boolean isUndefined() {
		return false;
	}

	public boolean isWhitespace() {
		return false;
	}

	public boolean isEOF() {
		return false;
	}

	public boolean isOther() {
		return false;
	}

	public Object getData() {
		return null;
	}

	public int getIndex()
	{
		return index;
	}
	
	public int getType()
	{
		return type;
	}
	
	public void setType(int type)
	{
		this.type = type;
	}

	public void setOffset(int offset)
	{
		this.offset = offset;
	}
	
	public void setState(String state)
	{
		this.state = state;
	}
	public String getState()
	{
		return state;
	}
	
	public int getOffset()
	{
		return offset;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	public String getText()
	{
		return text;
	}

	public DatabaseObject getDatabaseObject() {
		return null;
	}

	public void setDatabaseObject(DatabaseObject o) {
	}
	
	
}
