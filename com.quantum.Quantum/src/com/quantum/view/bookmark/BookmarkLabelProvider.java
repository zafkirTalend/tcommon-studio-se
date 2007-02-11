package com.quantum.view.bookmark;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

/**
 * @author root
 *
 * Implements the ILabelProvider for the Bookmarks.
 */
public class BookmarkLabelProvider implements ILabelProvider {
    
    private LabelDecorationInstructions labelDecorationInstructions = new LabelDecorationInstructions();
    
	/**
	 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(Object)
	 */
	public Image getImage(Object element) {
        if (element != null && element instanceof TreeNode) {
            return ((TreeNode) element).getImage();
        } else {
            return null;
        }
	}

	/**
	 * @see org.eclipse.jface.viewers.ILabelProvider#getText(Object)
	 */
	public String getText(Object element) {
		if (element != null && element instanceof TreeNode) {
            String labelName = ((TreeNode) element).getLabelName();
            String decorations = ((TreeNode) element).getLabelDecorations(
                this.labelDecorationInstructions);
            if (decorations != null && decorations.trim().length() > 0) {
                labelName += " " + decorations;
            }
			return labelName;
		} else {
            return element == null ? "<<empty>>" : element.toString();
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
    /**
     * @return
     */
    public LabelDecorationInstructions getLabelDecorationInstructions() {
        return labelDecorationInstructions;
    }
}
