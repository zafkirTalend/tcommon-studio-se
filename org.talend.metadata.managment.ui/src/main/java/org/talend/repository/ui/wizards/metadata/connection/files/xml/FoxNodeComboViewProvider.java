package org.talend.repository.ui.wizards.metadata.connection.files.xml;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.talend.repository.ui.wizards.metadata.connection.files.xml.treeNode.FOXTreeNode;

/**
 * DOC ycbai class global comment. Detailled comment
 */
public class FoxNodeComboViewProvider extends ArrayContentProvider implements ILabelProvider {

    public void addListener(ILabelProviderListener listener) {
    }

    public boolean isLabelProperty(Object element, String property) {
        return false;
    }

    public void removeListener(ILabelProviderListener listener) {
    }

    public Image getImage(Object element) {
        return null;
    }

    public String getText(Object element) {
        String text = "";
        if (element instanceof FOXTreeNode) {
            text = ((FOXTreeNode) element).getLabel();
        }

        return text;
    }

}
