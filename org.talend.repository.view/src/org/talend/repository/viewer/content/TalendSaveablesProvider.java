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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.Saveable;
import org.eclipse.ui.navigator.SaveablesProvider;
import org.talend.core.model.process.IProcess2;
import org.talend.designer.core.IMultiPageTalendEditor;
import org.talend.repository.model.IRepositoryNode;

/**
 * Created by Marvin Wang on Apr. 20, 2012.
 */
public class TalendSaveablesProvider extends SaveablesProvider implements IAdaptable {

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.navigator.SaveablesProvider#getSaveables()
     */
    @Override
    public Saveable[] getSaveables() {
        Saveable[] saveables = new Saveable[0];
        if (PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null) {
            IEditorPart currEditorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
            if (currEditorPart == null)
                saveables = new Saveable[0];
            else {
                if (currEditorPart.isDirty()) {
                    saveables = new Saveable[1];
                    saveables[0] = new TalendSaveablePart(currEditorPart);
                }
            }
        }
        return saveables;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.navigator.SaveablesProvider#getElements(org.eclipse.ui.Saveable)
     */
    @Override
    public Object[] getElements(Saveable saveable) {
        if (saveable instanceof IMultiPageTalendEditor) {
            IMultiPageTalendEditor multiPageTE = (IMultiPageTalendEditor) saveable;
            IProcess2 process = multiPageTE.getProcess();
            IRepositoryNode repNode = process.getRepositoryNode();
            return new Object[] { repNode };
        }
        return new Object[0];
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.navigator.SaveablesProvider#getSaveable(java.lang.Object)
     */
    @Override
    public Saveable getSaveable(Object element) {
        Saveable saveable = null;
        if (PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null) {
            IEditorPart currEditorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
            if (currEditorPart != null && currEditorPart.isDirty())
                saveable = new TalendSaveablePart(currEditorPart);
        }

        return saveable;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     */
    @Override
    public Object getAdapter(Class adapter) {
        return null;
    }

}
