// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.viewer.content;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.Saveable;
import org.talend.designer.core.IMultiPageTalendEditor;

/**
 * DOC Marvin class global comment. Detailled comment
 */
public class TalendSaveablePart extends Saveable {

    private IWorkbenchPart workbenchPart;

    public TalendSaveablePart(IWorkbenchPart workbenchPart) {
        this.workbenchPart = workbenchPart;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.Saveable#getName()
     */
    @Override
    public String getName() {
        return workbenchPart.getTitle();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.Saveable#getToolTipText()
     */
    @Override
    public String getToolTipText() {
        return workbenchPart.getTitleToolTip();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.Saveable#getImageDescriptor()
     */
    @Override
    public ImageDescriptor getImageDescriptor() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.Saveable#doSave(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void doSave(IProgressMonitor monitor) throws CoreException {
        if (workbenchPart instanceof ISaveablePart) {
            ISaveablePart saveable = (ISaveablePart) workbenchPart;
            saveable.doSave(monitor);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.Saveable#isDirty()
     */
    @Override
    public boolean isDirty() {
        boolean dirty = false;
        if (workbenchPart instanceof IMultiPageTalendEditor) {
            dirty = ((IMultiPageTalendEditor) workbenchPart).isDirty();
        }
        return dirty;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.Saveable#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object) {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.Saveable#hashCode()
     */
    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return 0;
    }

    public IWorkbenchPart getWorkbenchPart() {
        return this.workbenchPart;
    }

    public void setWorkbenchPart(IWorkbenchPart workbenchPart) {
        this.workbenchPart = workbenchPart;
    }

}
