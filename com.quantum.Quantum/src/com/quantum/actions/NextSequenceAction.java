package com.quantum.actions;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.adapters.DatabaseAdapter;
import com.quantum.view.bookmark.EntityNode;

import org.eclipse.ui.IViewPart;

public class NextSequenceAction extends BaseSequenceAction {
    
    /**
     * @param text
     * @param view
     */
    public NextSequenceAction(IViewPart view) {
        super(Messages.getString(NextSequenceAction.class, "text"), view);
        setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.APPEND));    
    }
    
	/**
	 * @param sequence
	 * @param name
	 * @param adapter
	 * @return
	 */
	protected String getQuery(EntityNode sequence, String name, DatabaseAdapter adapter) {
		return adapter.getNextValue(name, sequence.getEntity().getSchema());
	}
}
