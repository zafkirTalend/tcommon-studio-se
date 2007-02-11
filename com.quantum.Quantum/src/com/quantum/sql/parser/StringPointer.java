package com.quantum.sql.parser;

public class StringPointer {
	char[] value;
	int offset = 0;
	int mark = 0;
	public StringPointer(String s) {
		value = s.toCharArray();
	}
	/**
	 * Returns the next character. Will return 0 if at end of file, but that's not
	 * checkeable because it can be a valid character. You should check with isDone();
	 * @return
	 */
	public char getNext() {
		char retVal = (offset < value.length) ? value[offset] : 0;
		offset++;
		return retVal;
	}
	/**
	 * Returns the next character, without advancing the pointer.
	 *  Will return 0 if at end of file, but that's not
	 * checkeable because it can be a valid character. You should check with isDone();
	 * @return
	 */
	public char peek() {
		char retVal = (offset < value.length) ? value[offset] : 0;
		return retVal;
	}
	/**
	 * Marks a poing of the stream to come back later (using reset());
	 */
	public void mark() {
		mark = offset;
	}
	/**
	 * Returns to a previously marked (with mark()) place.
	 */
	public void reset() {
		offset = mark;
	}
	/**
	 * Sets the pointer back a character, in fact 'pop'ing it back to the stream;
	 */
	public void back() {
		if (offset > 0) offset--;
	}
	
	public int getOffset() {
		return offset;
	}
	/**
	 * @return true if the stream is at an end
	 */
	public boolean isDone() {
		return offset >= value.length;
	}
	
	public int getLength() {
		return value.length;
	}
}
