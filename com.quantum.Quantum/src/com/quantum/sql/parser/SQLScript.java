package com.quantum.sql.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.quantum.actions.BookmarkSelectionUtil;


/**
 * @author BC Holmes
 */
public class SQLScript {
	
	private final SQL[] statements;

	SQLScript(SQL[] statements) {
		this.statements = statements;
	}

	public SQL[] getSQLStatements() {
		return this.statements;
	}
		
	public static SQLScript parse(String text) {
		List statements = new ArrayList();
		SQLLexx lexer = BookmarkSelectionUtil.getInstance().getLastUsedBookmark().getAdapter().getLexer();
		List tokens = lexer.parse(text);
		int start = -1;
		
		for (int i = 0, length = tokens == null ? 0 : tokens.size(); i < length; i++) {
			Token token = (Token) tokens.get(i);
			if (token.getType() == Token.COMMENT && start < 0) {
				statements.add(new Comment(token));
			} else if (token.getType() == Token.COMMENT && isWhitespace(tokens, start, i)) {
				statements.add(new Comment(tokens.subList(start, i)));
				start = -1;
			} else if (token.getType() == Token.SEPARATOR) {
				List subList = tokens.subList(start, i);
				statements.add(new SimpleSQL(subList));
				start = -1;
			} else if (start < 0) {
				start = i;
			}
		}
		
		return new SQLScript((SQL[]) statements.toArray(new SQL[statements.size()]));
	}

	/**
	 * @param tokens
	 * @param start
	 * @param i
	 * @return
	 */
	private static boolean isWhitespace(List tokens, int start, int end) {
		boolean whitespace = true;
		List list = tokens.subList(start, end);
		for (Iterator i = list.iterator(); whitespace && i.hasNext();) {
			Token token = (Token) i.next();
			whitespace &= (token.getType() == Token.WHITESPACE);
		}
		return whitespace;
	}
}
