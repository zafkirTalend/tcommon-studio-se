/*
 * Created on 11/08/2003
 *
 */
package com.quantum.util;

import java.util.Iterator;
import java.util.Vector;

import com.quantum.QuantumPlugin;
import com.quantum.actions.CustomCopyAction;

import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Class to clasify two levels of strings. It's called Nth Level but in fact it has only two levels :o) 
 * @author Julen
 *
 */
public class NthLevelConverter {
	private final int DEFAULT_COLUMNS = 10;
	private final int DEFAULT_INCREMENT = 10;
	
	private Vector upper = new Vector(DEFAULT_COLUMNS,DEFAULT_INCREMENT);

	public void add(String id, String parentId, int level) {
		OneLevelConverter upperLine = new OneLevelConverter(id);
		if (level == 1) { // If it's an upper leaf
			if (!upper.contains(upperLine))
				this.upper.add(upperLine);
		}
		else if (level == 0) { // It's a lower leaf
			if  (parentId == null) return; // No parent, what shall we do.
			OneLevelConverter upperFinder = new OneLevelConverter(parentId);
			// Search if its parent is already present in the upper level
			int ind = upper.indexOf(upperFinder);
			// If not, addIt and find its index
			if (ind < 0) {
				this.upper.add(upperFinder);
				ind = upper.indexOf(upperFinder);
			}
			OneLevelConverter upperItem = (OneLevelConverter) upper.get(ind);
			// If it has no children, we create a new Vector for the children
			if (upperItem.getLower() == null)
				upperItem.setLower(new Vector(DEFAULT_COLUMNS,DEFAULT_INCREMENT));
			Vector children = upperItem.getLower();
			// Is the item already present in the children of its parent?
			int indChildren = children.indexOf(id);
			// If it's not, we add it
			if (indChildren < 0) {
				OneLevelConverter leaf = new OneLevelConverter(id);
				children.add(leaf);
			} 		
		}
		
	}
	
	
	/**
	 * @return
	 */
	public Vector getUpper() {
		return upper;
	}
	
	public Vector getLevel(String id, int level){
		if (level == 1)
			return upper;
		else if (level == 0){
			if (id == null) return null;
			OneLevelConverter upperFinder = new OneLevelConverter(id);
			int ind = upper.indexOf(upperFinder);
			if (ind < 0) 
				return null;
			else {
				OneLevelConverter upperItem = (OneLevelConverter) upper.get(ind);
				return upperItem.getLower();
			}				
		}
		return null;
	}
	
	public void convert(String ind){
		IPreferenceStore store = QuantumPlugin.getDefault().getPreferenceStore();

		String tableItem = QuantumUtil.trasposeEscape(store.getString(CustomCopyAction.CUSTOM_COPY_TABLE_ITEM_PREFIX + ind));
		String tableSeparator = QuantumUtil.trasposeEscape(store.getString(CustomCopyAction.CUSTOM_COPY_TABLE_SEPARATOR_PREFIX+ ind));
		String columnItem = QuantumUtil.trasposeEscape(store.getString(CustomCopyAction.CUSTOM_COPY_COLUMN_ITEM_PREFIX + ind));
		String columnSeparator = QuantumUtil.trasposeEscape(store.getString(CustomCopyAction.CUSTOM_COPY_COLUMN_SEPARATOR_PREFIX + ind));

		for (Iterator iter = upper.iterator(); iter.hasNext();) {
			OneLevelConverter oneLevel = (OneLevelConverter) iter.next();
			String upperId = oneLevel.getId();
			Vector lower = oneLevel.getLower();
			if (lower != null) {
				for (Iterator iterator = lower.iterator(); iterator.hasNext();) {
					OneLevelConverter element = (OneLevelConverter) iterator.next();
					String convLeaf = 	columnItem + (iterator.hasNext() ? columnSeparator : "");
					 
					convLeaf = StringUtil.substituteString(convLeaf, "${schema}", QuantumUtil.getSchemaName(upperId));
					convLeaf = StringUtil.substituteString(convLeaf, "${qualified}", upperId);
					convLeaf = StringUtil.substituteString(convLeaf, "${table}", QuantumUtil.getTableName(upperId));
					convLeaf = StringUtil.substituteString(convLeaf, "${column}", element.getId());
					
					element.setResult(convLeaf);
				}
			}
			// We have all the leafs converted, we calculate the parent conversion
			String convUpper = tableItem + (iter.hasNext() ? tableSeparator : "");
			convUpper = StringUtil.substituteString(convUpper, "${schema}", QuantumUtil.getSchemaName(upperId));
			convUpper = StringUtil.substituteString(convUpper, "${qualified}", upperId);
			convUpper = StringUtil.substituteString(convUpper, "${table}", QuantumUtil.getTableName(upperId));
			convUpper = StringUtil.substituteString(convUpper, "${column_list}", getColumnList(oneLevel));
			convUpper = StringUtil.substituteString(convUpper, "${create_sql}", getColumnList(oneLevel));
			
			oneLevel.setResult(convUpper);
		}
	}


	/**
	 * Calculates the list of columns for an upper level converter. Has to be already converted.
	 * @param oneLevel
	 * @return
	 */
	private String getColumnList(OneLevelConverter oneLevel) {

		String result = "";
		Vector lower = oneLevel.getLower();
		if (lower != null) {
			for (Iterator iterator = lower.iterator(); iterator.hasNext();) {
				OneLevelConverter element = (OneLevelConverter) iterator.next();
				result += element.getResult();
			}
		}
		return result;
	}

	public String getTableList() {
		String result = "";
		for (Iterator iter = upper.iterator(); iter.hasNext();) {
			OneLevelConverter oneLevel = (OneLevelConverter) iter.next();
			result += oneLevel.getResult();
		}
		return result;
	}
}
