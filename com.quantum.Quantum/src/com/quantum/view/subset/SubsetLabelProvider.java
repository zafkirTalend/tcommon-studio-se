package com.quantum.view.subset;

import com.quantum.view.bookmark.TreeNode;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

/**
 * @author root
 *
 * Implements the ILabelProvider for the Bookmarks.
 */
public class SubsetLabelProvider implements ILabelProvider {
	/**
	 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(Object)
	 */
	public Image getImage(Object element) {
	    if (element instanceof TreeNode) {
            return ((TreeNode) element).getImage();
		} else {
    	   	return null;
        }
	}

	/**
	 * @see org.eclipse.jface.viewers.ILabelProvider#getText(Object)
	 */
	public String getText(Object element) {
		if (element instanceof TreeNode) {
			return ((TreeNode) element).getLabelName();
		} else {
            return element.toString();
        }
	}

	/**
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(ILabelProviderListener)
	 */
	public void addListener(ILabelProviderListener listener) {
	}

	/**
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
	 */
	public void dispose() {
	}

	/**
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(Object, String)
	 */
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	/**
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(ILabelProviderListener)
	 */
	public void removeListener(ILabelProviderListener listener) {
     }
}
