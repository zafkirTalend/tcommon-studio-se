// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.core.ui.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.SelectionManager;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.talend.designer.core.ui.editor.connections.IConnLabelEditPart;
import org.talend.designer.core.ui.editor.connections.IConnectionPart;
import org.talend.designer.core.ui.editor.nodecontainer.INodeContainerPart;
import org.talend.designer.core.ui.editor.nodes.INodeLabelEditPart;
import org.talend.designer.core.ui.editor.process.IProcessPart;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 */
public class TalendSelectionManager extends SelectionManager {

    private ETalendSelectionType selectionType;

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.SelectionManager#appendSelection(org.eclipse.gef.EditPart)
     */
    @Override
    public void appendSelection(EditPart arg0) {
        if (getSelection() instanceof StructuredSelection) {
            StructuredSelection selection = (StructuredSelection) getSelection();
            Object selected = null;
            for (Object element : selection.toArray()) {
                selected = element;
            }
            if (getSelection().isEmpty() || (selected instanceof IProcessPart)) {
                this.selectionType = ETalendSelectionType.SINGLE;
                super.appendSelection(arg0);
            }

            if (!(arg0 instanceof INodeLabelEditPart) && !(arg0 instanceof IConnLabelEditPart)
                    && !(arg0 instanceof IConnectionPart)) {
                // removes old selections of labels by calling setSelection
                for (Object element : selection.toArray()) {
                    if (element instanceof INodeLabelEditPart) {
                        this.deselect(((AbstractGraphicalEditPart) element));
                    } else if (element instanceof IConnLabelEditPart) {
                        this.deselect(((AbstractGraphicalEditPart) element));
                    } else if (element instanceof IConnectionPart) {
                        this.deselect(((AbstractConnectionEditPart) element));
                    }
                }

                super.appendSelection(arg0);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.SelectionManager#setSelection(org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setSelection(ISelection arg0) {
        if (arg0 instanceof StructuredSelection) {
            StructuredSelection selection = (StructuredSelection) arg0;
            if (selection.size() != 1) {
                // if there is more than one element, remove all the selections of labels
                if (selection.size() > 1) {
                    this.selectionType = ETalendSelectionType.MULTIPLE;
                } else {
                    this.selectionType = ETalendSelectionType.NONE;
                }
                super.setSelection(filterSelection(selection));
            }
        } else {
            this.selectionType = ETalendSelectionType.SINGLE;
            super.setSelection(arg0);
        }
    }

    private StructuredSelection filterSelection(StructuredSelection selection) {
        List newSelection = new ArrayList(selection.toList());
        for (Object element : selection.toArray()) {
            if (element instanceof INodeLabelEditPart) {
                newSelection.remove(element);
            } else if (element instanceof INodeContainerPart) {
                newSelection.remove(element);
            }
        }
        StructuredSelection newList = new StructuredSelection(newSelection);
        return newList;
    }

    /**
     * yzhang Comment method "getSelectionType".
     * 
     * @return
     */
    public ETalendSelectionType getSelectionType() {
        return this.selectionType;
    }

}
