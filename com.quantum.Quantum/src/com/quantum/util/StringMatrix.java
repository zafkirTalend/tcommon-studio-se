/*
 * Created on 8/04/2003
 *
 */
package com.quantum.util;

import java.util.Vector;

/**
 * @author jparrai
 * Generic class to hold a Matrix of Strings, that is a Vector of Vectors of Strings.
 * The first StringMatrix "line" is supposed to have headers to the values of the rest.
 * Those headers will always be case insensitive
 * Rows start at 0
 */
public class StringMatrix {
	private final int DEFAULT_COLUMNS = 10;
	private final int DEFAULT_ROWS = 10;
	private final int DEFAULT_INCREMENT = 10;
	
	private Vector header = new Vector(DEFAULT_COLUMNS,DEFAULT_INCREMENT);
	private Vector matrix = new Vector(DEFAULT_ROWS,DEFAULT_INCREMENT);
	
	/**
	 * Adds a String to the end of the header keys
	 * @param header : The string to be added
	 */
	public void addHeader(String header) {
			this.header.add(header);
	}
	/**
	 * Adds a whole vector to the header
	 * @param header
	 */
	private void addVectorHeader(Vector header){
			this.header.addAll(header);
			for (int i = 0; i < this.header.size(); i++) {
				String element = (String) this.header.get(i);
				this.header.setElementAt(element, i);			
			}
	}
	/**
	 * Adds a whole matrix to the header
	 * @param header
	 */
	public void addMatrixHeader(String header[]){
			for (int i = 0; i < header.length; i++) {	
				String element = header[i];
				this.header.add(element);			
			}
	}
	/**
	 * Adds a String to the end of the row indicated
	 * @param value : The string to be added
	 * @param row : The row to 
	 */
	public void add(String value, int row) {
		grow(row);
		Vector rowVector = (Vector) matrix.get(row);
		rowVector.add(value);
	}
	/**
	 * Adds a StringMatrix to the end of the row indicated
	 * @param value : The string to be added
	 * @param row : The row to 
	 */
	public void add(StringMatrix value) {
		int row = matrix.size();
		for (int i = 0; i < value.size(); i++){
			grow(row);
			for (int j = 0; j < value.getNumColumns(); j++){
				String header = value.getHeaderColumn(j); 
				addAt(header, value.get(header,i), row);
			}
			row++;
		}
		Vector rowVector = (Vector) matrix.get(row);
		rowVector.add(value);
	}
	
	/**
	 * Converts the StringMatrix into a copy of the given
	 * @param origin - The StringMatrix to be copied
	 */
	public void copy(StringMatrix origin) {
		clear();
		add(origin);
	}
	
	/**
	 * Clears (emtpies) the StringMatrix
	 */
	public void clear(){
		header.clear();
		matrix.clear();
	}
	/**
	 * Clears (emtpies) the values in the StringMatrix, leaving the headers
	*/
	public void clearValues(){
		matrix.clear();
	}
	
	/**
	* Adds a String to the row indicated, to the column that matches the key
	* The matrix will grow to acomodate the indexes, so be careful 
	* @param value : The string to be added
	* @param row : The row to update 
	*/
	public void addAt(String key, String value, int row) {
		grow(row);
		Vector rowVector = (Vector) matrix.get(row);
		int ind = header.indexOf(key);
		if (ind < 0) return;
		if (rowVector.size() <= ind) rowVector.setSize(ind);
		rowVector.add(ind, value);
	}
	/**
	 * Adds a String in the location specified.
	 * The matrix will grow to acomodate the indexes, so be careful 
	 * @param column	Column selected
	 * @param row		row selected
	 * @param value		value to add
	 */
	public void addAt(int column, int row, String value) {
		grow(row);
		Vector rowVector = (Vector) matrix.get(row);
		if (column >= rowVector.size()) rowVector.setSize(column);
		rowVector.add(column, value);
	}
	/**
	 * Adds a whole vector to the end of the row indicated
	 * @param value : The vector to be added
	 * @param row : The row to 
	 */
	private void addVector(Vector value, int row){
		grow(row);
		Vector rowVector = (Vector) matrix.get(row);
		rowVector.addAll(value);
	}
	
	/**
	 * Tells if you have that particular key in your StringMatrix
	 * @param key
	 * @return 
	 */
	public boolean contains(String key){
		return (findKey(key) >= 0);
	}
	/**
	 * Gets a String value from the row indicated, from the column that matches the key
	 * @param key
	 * @param row
	 * @return
	 */
	public String get(String key, int row){
		if (matrix.size() <= row) return null;
		int col = findKey(key);
		if (col < 0) return null; 
		Vector rowVector = (Vector) matrix.get(row);
		if (rowVector ==  null) return null;
		return  (col < rowVector.size()) ? (String) rowVector.get(col) : null;
		
	}
	/**
	 * Finds the key in the header
	 * @param key
	 * @return
	 */
	private int findKey(String key) {
		for (int i = 0; i < header.size(); i++) {
			String element = (String) header.get(i);
			if (element.equalsIgnoreCase(key)) return i;
		}
		return -1;
	}
	/**
	 * @param key: selects the column
	 * @return a Vector with all the values in the selected column; null if empty
	 */
	public Vector getColumn(String key){
		if (size() < 1 ) return null;
		Vector result = new Vector(size(),1);
		for (int i = 0; i < size(); i++){
			result.add(get(key, i));
		}
		return result;
	}
	/**
	 * @param key: selects the column
	 * @return a Vector with all the values in the selected column, dropping duplicates; null if empty
	 */
	public Vector getUniqueColumn(String key){
		if (size() < 1 ) return null;
		Vector result = new Vector(size(),1);
		for (int i = 0; i < size(); i++){
			if (!result.contains(get(key, i))) result.add(get(key, i));
		}
		return result;
	}
	/**
	 * @param key: selects the column
	 * @return a Vector of Integers with all the indexes of the rows
	 * matching the selected column, dropping duplicates; null if empty
	 */
	public Vector getIndexes(String key, String value){
		Vector result = new Vector();
		for (int i = 0; i < size(); i++){
			if (get(key, i).equals(value))
				result.add(new Integer(i));
		}
		return result;
	}
	/**
	 * Deletes all the rows that matches the value for the key
	 * @param key: selects the column
	 */
	public void dropMatching(String key, String value){
		for (int i = 0; i < size(); i++){
			if (get(key, i).equals(value)) deleteRow(i);
		}
	}
	/**
	 * Returns a StringMatrix with all the complete rows that match the key - value pair
	 * @param key The column key
	 * @param value The value to match
	 * @return a StringMatrix with only the rows where the key equals the value
	 */
	public StringMatrix select(String key, String value){
		StringMatrix result = new StringMatrix();
		result.addVectorHeader(header);
		int j = 0;
		for (int i = 0; i < size(); i++){
			if (get(key, i).equals(value)) {
				result.addVector((Vector)matrix.get(i), j);
				j++;
			} 
		}
		return result;
	}

	// Private functions
	/**
	 * Grows the StringMatrix till it's able to hold "row" rows.
	 * @param row : The number of rows that must hold
	 */
	private void grow(int row) {
		if (matrix.size() <= row)
			for (int i = matrix.size(); i <= row; i++) {
				matrix.add(new Vector(header.size(), 1));
			}
	}
	/**
	 * Gets a String value from the row indicated , from the column number
	 * @param col : 0-index column 
	 * @param row : 0-index column
	 * @return
	 */
	public String get(int col, int row){
		if (col < 0 || row < 0) return null; 
		Vector rowVector = (Vector) matrix.get(row);
		if (rowVector ==  null) return null;
		if (col < rowVector.size())
			return (String) rowVector.get(col);
		else return null;
	}
	
	// Generic interfaces
	/**
	 * @param i
	 * @return : a StringMatrix with only one row, the selected by i
	 */
	public StringMatrix rowMatrix(int i){
		StringMatrix result = new StringMatrix();
		result.addVectorHeader(header);
		result.addVector((Vector)matrix.get(i),0);
		return result;
	}
	
	/**
	 * Resturns a String[] with the selected row
	 * @param i The index of the row
	 * @return
	 */
	public String[] getRow(int i) {
		if (i < matrix.size()) {
			String[] result = new String[header.size()];
			Vector resVector = (Vector)matrix.get(i);
			resVector.toArray(result); 
			return result;
		}
		return null;
		
	}


	/**
	 * @return the number of rows
	 */
	public int size() {
		return matrix.size();
	}
	/**
	 * @return The number of columns of the StringMatrix
	 */
	public int getNumColumns() {
		return header.size();
	}
	/**
	 * @param i
	 * @return The name of the header column in the "i" position. Null if no such position.
	 */
	public String getHeaderColumn(int i){
		if (i < header.size())
			return (String) header.get(i);
		return null;
	}
	/**
	 * Deletes the row number i
	 * @param i
	 */
	public void deleteRow(int i){
		if (i < matrix.size())
			matrix.remove(i);
	}


	// Common Object interface
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Object clone() {
		// Deep clone
		StringMatrix matrix = new StringMatrix();
		matrix.copy(this);
		return matrix;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String result = "";
		for (int j = 0; j < header.size(); j++){
			result += (String) header.get(j);
			result += "\t";
		}
		result += "\n";
		// Write the lines
		for (int i = 0; i < matrix.size(); i++){
			for (int j = 0; j < header.size(); j++){
				result += get(j,i);
				result += "\t";
			}
			result += "\n";
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof StringMatrix)) return false;
		StringMatrix sm = (StringMatrix) obj;
		if (!header.equals(sm.header)) return false;
		if (matrix.size() != sm.matrix.size()) return false;
		for (int i = 0; i < matrix.size(); i++){
			Vector row = (Vector) matrix.get(i);
			Vector smRow = (Vector) sm.matrix.get(i);
			if (!(row.equals(smRow))) return false;
		}
		return true;
	}

	/**
	 * @return
	 */
	public Vector getMatrix() {
		return matrix;
	}
	/**
	 * @return
	 */
	public String[] getHeader() {
		String[] result = new String[header.size()];
		header.toArray(result);
		return result;
	}

}
