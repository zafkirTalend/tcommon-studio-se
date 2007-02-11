package com.quantum.sql.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class SimpleSQL implements SQLStatement {
	
	private final List tokens = Collections.synchronizedList(new ArrayList());

	SimpleSQL(List tokens) {
		this.tokens.addAll(tokens);
	}

	public String getCommand() {
		return null;
	}

	public boolean isComment() {
		return false;
	}

	public String getExecutableForm() {
		StringBuffer buffer = new StringBuffer();
		for (Iterator i = this.tokens.iterator(); i.hasNext();) {
			Token token = (Token) i.next();
			if (token.getType() == Token.WHITESPACE) {
				buffer.append(" ");
			} else {
				buffer.append(token.getValue());
			}
		}
		String command = buffer.toString().trim();
		return command.endsWith(";") ? command.substring(0, command.length()-1) : command;
	}
}