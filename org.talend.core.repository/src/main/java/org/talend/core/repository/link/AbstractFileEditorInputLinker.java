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
package org.talend.core.repository.link;

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.talend.repository.model.RepositoryNode;

/**
 * DOC ggu class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 55206 2011-02-15 17:32:14Z mhirt $
 * 
 */
public abstract class AbstractFileEditorInputLinker implements IRepoViewLinker {

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.repository.link.IRepoViewLinker#getRelationNode(org.eclipse.ui.IEditorInput)
     */
    @Override
    public RepositoryNode getRelationNode(IEditorInput editorInput) {
        if (editorInput != null && editorInput.getClass().equals(FileEditorInput.class)) {
            FileEditorInput fileEditorInput = (FileEditorInput) editorInput;
            final IFile file = fileEditorInput.getFile();
            if (checkFileExtension(file)) {
                IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                if (activeWorkbenchWindow != null) {
                    IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
                    if (activePage != null) {
                        return getRepoNodeFromEditor(getEditor(activePage, fileEditorInput));
                    }
                }
            }
        }
        return null;
    }

    protected IEditorPart getEditor(IWorkbenchPage activePage, IEditorInput editorInput) {
        if (activePage != null && editorInput != null) {
            return activePage.findEditor(editorInput);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.repository.link.IRepoViewLinker#isRelation(org.eclipse.ui.IEditorInput, java.lang.String)
     */
    @Override
    public boolean isRelation(IEditorInput editorInput, String repoNodeId) {
        RepositoryNode relationNode = getRelationNode(editorInput);
        if (relationNode != null && repoNodeId != null && repoNodeId.equals(relationNode.getId())) {
            return true;
        }
        return false;
    }

    protected boolean checkFileExtension(IFile file) {
        return true; // ignore by default
    }

    protected abstract RepositoryNode getRepoNodeFromEditor(IEditorPart editorPart);

}
