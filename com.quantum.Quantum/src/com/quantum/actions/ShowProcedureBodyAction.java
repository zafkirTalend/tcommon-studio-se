package com.quantum.actions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.SelectionListenerAction;
import org.eclipse.ui.ide.IDE;

import com.quantum.Messages;
import com.quantum.QuantumPlugin;
import com.quantum.log.QuantumLog;
import com.quantum.model.Bookmark;
import com.quantum.view.bookmark.ProcedureNode;

/**
 * @author BC
 */
public class ShowProcedureBodyAction extends SelectionListenerAction {

    /**
     * @param text
     */
    public ShowProcedureBodyAction(IViewPart viewPart) {
        super(Messages.getString(ShowProcedureBodyAction.class.getName() + ".text"));
    }

    protected boolean updateSelection(IStructuredSelection selection) {
        boolean enabled = super.updateSelection(selection);
        enabled &= (selection.size() == 1 &&
            selection.getFirstElement() instanceof ProcedureNode);
        return enabled;
    }
    
    public void run() {
        ProcedureNode procNode = getProcedureNode();
        if (procNode == null) return;
        Bookmark bookmark = procNode.getBookmark();
        BookmarkSelectionUtil.getInstance().setLastUsedBookmark(bookmark);
        IWorkbenchPage page = QuantumPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IWorkspaceRoot root = workspace.getRoot();
        IProject project = root.getProject(bookmark.getName());
        IFolder folder = project.getFolder("P");
        IFile file = folder.getFile(procNode.getName() + ".sql");
        try {
            if (!project.exists())project.create(null);
            if (!project.isOpen())project.open(null);
            if (!folder.exists())folder.create(IResource.NONE, false, null);
            if(file.exists())file.delete(true, null);
            if (!file.exists()) {
                String s = procNode.getProcedure().getBody() + "\r\n";
                FileOutputStream source=null;
                try {
                    source = new FileOutputStream(file.getName());
                } catch (FileNotFoundException e) {
                }
                try {
                    source.write(s.getBytes());
                    source.flush();
                    source.close();
                } catch (IOException e) {
                }
                FileInputStream dest = null;
                try {
                    dest = new FileInputStream(file.getName());
                } catch (FileNotFoundException e) {
                }
                file.create(dest, IResource.NONE, null);
            }
        } catch (CoreException e) {
			QuantumLog.getInstance().error(
					"Error creating file: " + e.getMessage(), e); //$NON-NLS-1$ //$NON-NLS-2$
        }
        try {
            IDE.openEditor(page, file, true);
        } catch (PartInitException e) {
			QuantumLog.getInstance().error(
					"Error opening editor: " + e.getMessage(), e); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }
    
    private ProcedureNode getProcedureNode() {
        if (isEnabled()) {
            return (ProcedureNode) getSelectedNonResources().get(0);
        } else {
            return null;
        }
    }
    
}
