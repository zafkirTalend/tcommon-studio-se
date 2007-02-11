/*
 * Created on 11/08/2003
 *
 */
package com.quantum.actions;

import java.sql.SQLException;
import java.util.Iterator;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.QuantumPlugin;
import com.quantum.model.Column;
import com.quantum.model.EntityHolder;
import com.quantum.util.NthLevelConverter;
import com.quantum.util.QuantumUtil;
import com.quantum.util.StringUtil;
import com.quantum.util.connection.NotConnectedException;
import com.quantum.view.bookmark.ColumnNode;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.actions.SelectionListenerAction;


public class CustomCopyAction extends SelectionListenerAction {
	// The number of actions defined.
	public static final int NUMBER_OF_ACTIONS = 3;
	// The prefix to get the name of the custom copy names defined.
	// Basically, the users will store their custom copy definitions as preferences.
	// These preferences will be called like this prefix plus a number, from 1 to the
	// number indicated in the previous constant NUMBER_OF_ACTIONS, inclusive.
	// So to know if the first custom copy is defined (has a name) we can ask for
	// QuantumPlugin.getDefault().getPreferenceStore().getString("customCopyName1")
	public static final String CUSTOM_COPY_NAME_PREFIX = "customCopyName";
	public static final String CUSTOM_COPY_TEMPLATE_PREFIX = "customCopyTemplate";
	public static final String CUSTOM_COPY_TABLE_ITEM_PREFIX = "customCopyTableItem";
	public static final String CUSTOM_COPY_TABLE_SEPARATOR_PREFIX = "customCopyTableSeparator";
	public static final String CUSTOM_COPY_COLUMN_ITEM_PREFIX = "customCopyColumnItem";
	public static final String CUSTOM_COPY_COLUMN_SEPARATOR_PREFIX = "customCopyColumnSeparator";
	
	private final int ind;
	public CustomCopyAction( int ind ) {
        super(Messages.getString(CustomCopyAction.class.getName() + ".text"));
		this.ind = ind;
		IPreferenceStore store = QuantumPlugin.getDefault().getPreferenceStore();
		String name = store.getString(CustomCopyAction.CUSTOM_COPY_NAME_PREFIX + Integer.toString(ind));
		setText(name);
		setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.COPY));

	}
	public void run() {
		// Basically the idea is to copy first a list of the entities (tables/views) selected to
		// a converter. The converter has two levels, one for the entities and one for the columns.
		// The converter allows recursive substitution of the strings. It's a mess to understand,
		// only try it if really needed.
		String copyText = "";
		Iterator iter =  getSelectedNonResources().iterator();
		NthLevelConverter converter = new NthLevelConverter();
		// Fill up the converter. We iterate the selection items
		while (iter.hasNext()) {
			Object current = iter.next();
			if (current instanceof ColumnNode) {
				ColumnNode column = (ColumnNode) current;
				if (column != null) {
					converter.add(column.getColumn().getName(), 
                        column.getColumn().getParentEntity().getQualifiedName(), 0);
				}
			} else if (current instanceof EntityHolder) {
                EntityHolder currentSelec = (EntityHolder) current;
				converter.add(currentSelec.getEntity().getQualifiedName(), null, 1);
				try {
					Column[] columns = currentSelec.getEntity().getColumns();
					for (int i = 0, length = (columns == null) ? 0 : columns.length;
	                    i < length;
	                    i++) {
						converter.add(columns[i].getName(), 
	                        currentSelec.getEntity().getQualifiedName(), 0);
					}					
				} catch (NotConnectedException e) {
				} catch (SQLException e) {
				}
			}
		}
		IPreferenceStore store = QuantumPlugin.getDefault().getPreferenceStore();
	
		
		converter.convert(Integer.toString(ind));
		
		String template = QuantumUtil.trasposeEscape(store.getString(CUSTOM_COPY_TEMPLATE_PREFIX + Integer.toString(ind)));
		copyText = StringUtil.substituteString(template, "${table_list}", converter.getTableList());
		
		
		QuantumPlugin.getDefault().getSysClip().setContents(
			new Object[] { copyText },
			new Transfer[] { TextTransfer.getInstance()});
	}

}
