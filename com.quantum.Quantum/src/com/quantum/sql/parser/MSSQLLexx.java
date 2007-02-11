package com.quantum.sql.parser;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import com.quantum.sql.parser.SQLLexx;
/**
 * <p>An SQL Lexer.  From 
 * <a href="http://www.dictionary.com/">dictionary.com</a>:
 * 
 * <blockquote>
 * <p><b>lexer</b>
 *
 * <p>/lek'sr/ n. Common hacker shorthand for 'lexical
 * analyzer', the input-tokenizing stage in the parser for a language
 * (the part that breaks it into word-like pieces).
 * </blockquote>
 * 
 * <p>Note that this class has nothing to do with the Sci-fi channel's
 * <a href="http://www.scifi.com/lexx/">Lexx</a> TV series.
 */
public class MSSQLLexx extends SQLLexx{
	private final static char CHAR_EOL = '\n';
	private final static char CHAR_DASH = '-';
	private final static char CHAR_ESCAPE = '\\';
	private final static char CHAR_SEPARATOR = ';';
	private final static char CHAR_VARIABLE = '@'; // this should be database specific, if at all...
	
	private final static int CONDITION_WHITESPACE = 1;
	private final static int CONDITION_IDENTIFIER = 2;
	private final static int CONDITION_IDENTIFIER_INITIAL = 3;
	private final static int CONDITION_LITERAL_SIMPLE_QUOTE = 4;
	private final static int CONDITION_LITERAL_DOUBLE_QUOTE = 5;
	private final static int CONDITION_NUMERIC = 6;
	private final static int CONDITION_EOL = 7;
	
	public MSSQLLexx()
	{
		MSSQLLexx.keywords = Collections.synchronizedSet(new HashSet());
		MSSQLLexx.keywords.addAll(Arrays.asList(statementStarters));
	}
	private static Set keywords = null;

	public static boolean isStatementStarter(String word){
    	return keywords.contains(word.toUpperCase());
    }
    

    private static String[] statementStarters = {
    	"SELECT", "UPDATE", "DELETE", "CREATE",
    	"SET", "IF", "DECLARE", "OPEN", "FETCH",
    	"CLOSE", "DEALLOCATE", "RETURN", "EXEC",
    	"WHILE", "INSERT", "ELSE",
    	"BEGIN", "END", "GOTO"
    };
    
	/**
	 * Parses a SQL text into tokens. 
	 * @param text
	 * @return a vector of Token objects.
	 */
	public static Vector parse(String text) {
		Vector tokens = new Vector();
		StringPointer p = new StringPointer(text);
		int lastOffset = 0;
		int offset = 0;
		try {
			while (!p.isDone()) {
				offset = p.getOffset();
				char c = p.getNext();
				// Adds END_OF_LINE token
				if (c == CHAR_EOL) {
					tokens.addElement(new Token(Token.END_OF_LINE, CHAR_EOL, offset));	
				}
				else if (c == CHAR_VARIABLE)
				{
//					char d = p.getNext();
//					if (d == CHAR_VARIABLE){
//						// MS SQL System function...
//					}
					// this is the start of a variable (as used in MS SQLServer
					StringBuffer value = AddTokenWhile(p, c, CONDITION_IDENTIFIER);
					tokens.addElement(new Token(Token.VARIABLE, value.toString(), offset, offset + value.length()));
					lastOffset = offset + value.length();
				}
				// Adds WHITESPACE token;
				else if (CheckCondition( c, CONDITION_WHITESPACE)) 
				{
					StringBuffer value = AddTokenWhile(p, c, CONDITION_WHITESPACE);
					tokens.addElement(new Token(Token.WHITESPACE, value.toString(), offset, offset + value.length()));
				// Adds IDENTIFIER token (can be reserved SQL word or not);
				} else if (CheckCondition( c , CONDITION_IDENTIFIER_INITIAL)) 
				{
					StringBuffer value = AddTokenWhile(p, c, CONDITION_IDENTIFIER);
					// if this identifier (can) start(s) a new command, we need to insert a zero width separator first.
					if(isStatementStarter(value.toString()))
					{
						// we want to insert it after the statement, so we must backtrack to the first non WS
						// and non comment token for the end of the statement. If it is the beginning we use offset.
						// Problem is that we do not whether the statement starts or ends. Adding them both will do
						// the trick. If the user selects/double clicks the comment section, it will get all irrelevant
						// whitespace too.
						tokens.addElement(new Token(Token.SEPARATOR, "", lastOffset, lastOffset));
						tokens.addElement(new Token(Token.SEPARATOR, "", offset, offset));
						// what is not good yet is the SELECT ... WHERE pietje = (SELECT ...)
						// and also INSERT ... SELECT ...
						// this will probably require ANTLR to work for MS SQL...
						// let's see what the () count will do... Nothing INSERT () SELECT braces == 0
						// Update {table} set is not working
						// insert into .. exec ... is not working
					}
					tokens.addElement(new Token(Token.IDENTIFIER, value.toString(), offset, offset + value.length()));
					lastOffset = offset + value.length();
				// Adds LITERAL token;
				} else if (CheckCondition(c, CONDITION_LITERAL_SIMPLE_QUOTE)) {
					StringBuffer value = AddTokenUntil(p, c, CONDITION_LITERAL_SIMPLE_QUOTE);
					tokens.addElement(new Token(Token.LITERAL, value.toString(), offset, offset + value.length()));
					lastOffset = offset + value.length();
				// Adds LITERAL token;
				} else if (CheckCondition(c, CONDITION_LITERAL_DOUBLE_QUOTE)) {
					StringBuffer value = AddTokenUntil(p, c, CONDITION_LITERAL_DOUBLE_QUOTE);
					tokens.addElement(new Token(Token.LITERAL, value.toString(), offset, offset + value.length()));
					lastOffset = offset + value.length();
				// Adds NUMERIC token;
				} else if (Character.isDigit(c)) {
					StringBuffer value = AddTokenWhile(p, c, CONDITION_NUMERIC);
					tokens.addElement(new Token(Token.NUMERIC, value.toString(), offset, offset + value.length()));
					lastOffset = offset + value.length();
				// Adds COMMENT token if two dashes (or SYMBOL (dash) if only one dash);
				} else if (c == CHAR_DASH) {
					if (p.isDone()) {
						tokens.addElement(new Token(Token.SYMBOL, new Character(CHAR_DASH).toString(), offset, offset + 1));
					} else {
						char next = p.peek();
						if (next == CHAR_DASH) {
							StringBuffer value = AddTokenUntil(p, CHAR_DASH, CONDITION_EOL);
							tokens.addElement(new Token(Token.COMMENT, value.toString(), offset, offset + value.length()));
						} else {
							tokens.addElement(new Token(Token.SYMBOL, new Character(CHAR_DASH).toString(), offset, offset + 1));
						}
					}	
                 //	Determine if the ';' is escaped or not
				} else if (c == CHAR_ESCAPE) {
					if (p.peek() == CHAR_SEPARATOR) {
						p.getNext();	// We advance the pointer so the separator is not marked again
						// We DON´T SAVE the scape character in the tokens. 
						// For correct sintax highlighting we set the offset to +2
						// This is so far the only case when a character is eliminated and not saved to the tokens.
						// That means it won´t be sent to the database when executed.
						// This is to allow definitions of procedures with ';' as an end-of-sentence, 
						//  not as an execution symbol for SQL.
						tokens.addElement(new Token(Token.SYMBOL, new Character(CHAR_SEPARATOR).toString() , offset, offset + 2));
						lastOffset = offset + 2;
					}	else {
						tokens.addElement(new Token(Token.SYMBOL, new Character(CHAR_ESCAPE).toString() , offset, offset + 1));
						lastOffset = offset + 1;
					}
				// Adds SEPARATOR token (;),  considers the rest of the line as COMMENT token;
				} else if (c == CHAR_SEPARATOR) {
					tokens.addElement(new Token(Token.SEPARATOR, new Character(CHAR_SEPARATOR).toString(), offset, offset + 1));
					// The rest of the line will be a comment
					// BCH: huh?
					if (!p.isDone()) {
						StringBuffer value = AddTokenUntil(p, "", CONDITION_EOL);
						// 	We add to the offset so as to skip the initial ';'
						offset++;
						if (value.toString().trim().length() == 0) {
							tokens.addElement(new Token(
									Token.WHITESPACE, value.toString(), 
									offset, offset + value.length()));
						} else {
							tokens.addElement(new Token(Token.COMMENT, value.toString(), offset, offset + value.length()));
						}
					}
				// Adds COMMENT token, for several lines;
				} else if (c == '/') {
					// If we have '/*', it's a comment till '*/' found or eof
					if (p.peek() == '*') {
						tokens.addElement(tokenizeComment(p, offset));
					} else {
						tokens.addElement(new Token(Token.SYMBOL, new String(new char[] {c}) , offset, offset + 1));
					}
				// Adds SYMBOL token;
				} else {
					tokens.addElement(new Token(Token.SYMBOL, new String(new char[] {c}), offset, offset + 1));
					lastOffset = offset + 1;
				}
			}
			// let's add a separator add the end as well
			tokens.addElement(new Token(Token.SEPARATOR, "", lastOffset, lastOffset));
			tokens.addElement(new Token(Token.SEPARATOR, "", offset, offset));
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
//		System.out.println("-------------------");
//		for (int i = 0; i < tokens.size(); i++) {
//			System.out.println((Token) tokens.elementAt(i));
//		}
		return tokens;
	}
	/**
	 * Searchs for a token end, UNTIL the condition is true, or a newline, or the end of the StringPointer
	 * The end character is also addedd to the StringBuffer
	 * @param p
	 * @param s A string with the first character from the token, already extracted from the StringPointer
	 * @param condition
	 * @return a StringBuffer with the complete token
	 */
	private static StringBuffer AddTokenUntil(StringPointer p, String s, int condition) {
		StringBuffer value = new StringBuffer(s);
		if (p.isDone()) return value;
		for(;;) {
			char c = p.getNext();
			if (c != CHAR_EOL) value.append(c);
			if (CheckCondition (c, condition) || c == CHAR_EOL || p.isDone()) {
				break; 
			} 
		}	
		return value;
	}
	private static StringBuffer AddTokenUntil(StringPointer p, char c, int condition) {
		return AddTokenUntil(p, new Character(c).toString(), condition);
	}
	/**
	 * Searchs for a token end, WHILE the condition is true, or the end or the StringPointer.
	 * @param p		The StringPointer where the original stream is
	 * @param s		A string with the first character from the token, already extracted from the StringPointer
	 * @param condition	The condition to end the token
	 * @return a StringBuffer with the complete token
	 */
	private static StringBuffer AddTokenWhile(StringPointer p, String s, int condition) {
		StringBuffer value = new StringBuffer(s);
		if (p.isDone()) return value;
		for(;;) {
			char c = p.getNext();
			if (CheckCondition (c, condition)) {
				value.append(c);
				if (p.isDone()) break; 
			} 
			else
			{
				p.back();
				break;
			}
		}	
		return value;
	}
	private static StringBuffer AddTokenWhile(StringPointer p, char c, int condition) {
		return AddTokenWhile(p, new Character(c).toString(), condition);
	}
	/**
	 * Returns true if the character meets the condition, and false if not. 
	 * New conditions should be defined in this function
	 * @param c	The character to check the condition
	 * @param condition The condition to check
	 * @return
	 */
	private static boolean CheckCondition(char c, int condition) {
		switch (condition) {
		case CONDITION_WHITESPACE:
			return Character.isWhitespace(c);
		case CONDITION_IDENTIFIER_INITIAL:
			return (Character.isLetter(c) || c == '$' || c == '#'); 
		case CONDITION_IDENTIFIER:
			return (Character.isLetter(c) || Character.isDigit(c) || c == '_' || c == '$' || c == '#'); 
		case CONDITION_LITERAL_SIMPLE_QUOTE:
			return (c == '\''); 
		case CONDITION_LITERAL_DOUBLE_QUOTE:
			return (c == '\"');
		case CONDITION_NUMERIC:
			return (Character.isDigit(c) || c == '.'); 
		case CONDITION_EOL:
			return (c == CHAR_EOL); 
		default:
			break;
		}
		return false;
	}
	/**
	 * @param tokens
	 * @param p
	 * @param offset
	 */
	private static Token tokenizeComment(StringPointer p, int offset) {
		char c;
		StringBuffer value = new StringBuffer();
		c = p.getNext();
		value.append('/');
		while (!( c == '*' && p.peek() == '/' ) && !p.isDone()) {
			value.append(c);
			c = p.getNext();
		}
		if (!p.isDone()){
			value.append(c);
			c = p.getNext();
			value.append(c);	
		}
		return new Token(Token.COMMENT, value.toString(), offset, offset + value.length());
	}
}
