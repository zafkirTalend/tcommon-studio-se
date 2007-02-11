package com.quantum.sql.parser;

public class Token {
	public static final char SEPARATOR = 'S';
	public static final char SYMBOL = 'Y';
	public static final char LITERAL = 'L';
	public static final char IDENTIFIER = 'I';
	public static final char COMMENT = 'C';
	public static final char WHITESPACE = 'W';
	public static final char NUMERIC = 'N';
	public static final char END_OF_LINE = 'E';
	public static final char VARIABLE = '@';
	
	private char type;
	private int start;
	private int end;
	private String value;

	public Token(char type, char value, int start) {
		this.type = type;
		this.value = new Character(value).toString();
		this.start = start;
		this.end = start + 1;		
	}
	public Token(char type, String value, int start, int end) {
		this.type = type;
		this.value = value;
		this.start = start;
		this.end = end;
	}
	public int getEnd() {
		return end;
	}

	public int getStart() {
		return start;
	}

	public int getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setType(char type) {
		this.type = type;
	}

	public void setValue(String value) {
		this.value = value;
	}
	public String toString() {
		return type + " ->" + value + "<- [" + start + ", " + end + "]";  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}
}
