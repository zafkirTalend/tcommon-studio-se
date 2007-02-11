/*
 * Created on 24/02/2005
 * Metadata of a sequence
 */
package com.quantum.model;

import java.sql.SQLException;

import com.quantum.util.connection.NotConnectedException;

/**
 * @author Julen
 *
 */
public interface SequenceMetadata extends DatabaseObjectMetadata {
	/**
	 * @return	The absolute minimum value of the sequence
	 * 			Usually will be an integer number, but stored as String as it can be big.
	 * 			Care is to be taken when converting to numbers
	 */
	public String getMinValue() throws NotConnectedException, SQLException ;
	/**
	 * @return	The absolute maximum value of the sequence
	 * 			Usually will be an integer number, but stored as String as it can be big.
	 * 			Care is to be taken when converting to numbers
	 */
	public String getMaxValue() throws NotConnectedException, SQLException ;
	/**
	 * @return	The initial value from which to start to count
	 * 			Usually will be an integer number, but stored as String as it can be big.
	 * 			Care is to be taken when converting to numbers
	 */
	public String getInitialValue() throws NotConnectedException, SQLException ;;
	/**
	 * @return	The increment to apply to the sequence
	 * 			Usually will be an integer number, but stored as String as it can be big.
	 * 			Care is to be taken when converting to numbers
	 */
	public String getIncrementBy() throws NotConnectedException, SQLException ;;
	/**
	 * @return	True if the sequence cycles trhough its values, false if not
	 */
	public boolean isCycled() throws NotConnectedException, SQLException ;;
	/**
	 * @return	True if the sequence guarantees ordering in the returned values, false if not
	 */
	public boolean isOrdered() throws NotConnectedException, SQLException ;;
	
}
