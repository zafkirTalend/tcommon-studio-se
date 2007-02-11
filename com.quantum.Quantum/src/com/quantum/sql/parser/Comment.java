package com.quantum.sql.parser;

import java.util.Iterator;
import java.util.List;


/**
 * @author BC Holmes
 */
public class Comment implements SQL {

	private final String sql;

	/**
	 * @param token
	 */
	public Comment(Token token) {
		this.sql = token.getValue();
	}

	/**
	 * @param token
	 */
	Comment(List tokens) {
		StringBuffer buffer = new StringBuffer();
		for (Iterator i = tokens.iterator(); i.hasNext();) {
			Token token = (Token) i.next();
			buffer.append(token.getValue());
		}
		this.sql = buffer.toString();
	}

	public String getCommand() {
		return "";
	}

	public boolean isComment() {
		return true;
	}

	public String toString() {
		return this.sql;
	}
}
