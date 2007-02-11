package com.quantum.view.bookmark;

import com.quantum.ImageStore;
import com.quantum.Messages;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.AbstractTreeViewer;


/**
 * @author BC Holmes
 */
class CollapseAllAction extends Action {

	private final AbstractTreeViewer viewer;

	/**
	 * @param viewer
	 */
	public CollapseAllAction(AbstractTreeViewer viewer) {
        super(Messages.getString(CollapseAllAction.class.getName() + ".text"));
        setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.COLLAPSE_ALL));
        setToolTipText(Messages.getString(CollapseAllAction.class.getName() + ".text"));
		this.viewer = viewer;
	}
	
	public void run() {
		if (this.viewer != null) {
			this.viewer.collapseAll();
		}
	}
}
