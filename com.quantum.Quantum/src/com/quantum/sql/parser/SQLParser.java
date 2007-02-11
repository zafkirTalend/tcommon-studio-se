package com.quantum.sql.parser;

import java.util.Vector;

import com.quantum.actions.BookmarkSelectionUtil;
import com.quantum.model.Bookmark;


public class SQLParser {

	/**
	 * Parses a query string into executable units.
	 * @param query
	 * @return a Vector of Strings, with the individual executable units.
	 */
	public static Vector parse(String query) {
		Vector commands = new Vector();
		try {
			SQLLexx lexer = BookmarkSelectionUtil.getInstance().getLastUsedBookmark().getAdapter().getLexer();
			Vector tokens = lexer.parse(query);
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < tokens.size(); i++) {
				Token t = (Token) tokens.elementAt(i);
				if (t.getType() == Token.COMMENT) {
					// ignore comments
				} else if (t.getType() == Token.SEPARATOR) {
					String newCommand = buffer.toString().trim();
					if (!newCommand.equals("")) { //$NON-NLS-1$
						commands.addElement(newCommand);
					}
					buffer = new StringBuffer();
				} else {	
					// We append the tokens to the buffer until it's a separator
					buffer.append(t.getValue());
				}
			}
			String newCommand = buffer.toString().trim();
			if (!newCommand.equals("")) { //$NON-NLS-1$
				commands.addElement(newCommand);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return commands;
	}

//	private Bookmark getLastUsedBookmark() {
//		return BookmarkSelectionUtil.getInstance().getLastUsedBookmark();
//	}

}