/*
 * Created on 22-jul-2003
 *
 */
package com.quantum.actions;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.adapters.DatabaseAdapter;
import com.quantum.view.bookmark.EntityNode;

import org.eclipse.ui.IViewPart;

public class PrevSequenceAction extends BaseSequenceAction {
	/**
     * @param text
     * @param view
     */
    public PrevSequenceAction(IViewPart view) {
        super(Messages.getString(PrevSequenceAction.class.getName() + ".text"), view);
        setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.GRID));
    }

	/**
	 * @param sequence
	 * @param name
	 * @param adapter
	 * @return
	 */
	protected String getQuery(EntityNode sequence, String name, DatabaseAdapter adapter) {
		return adapter.getPrevValue(name, sequence.getEntity().getSchema());
	}
}
