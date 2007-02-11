/*******************************************************************************
 * Copyright (c) 2005 RadRails.org and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.radrails.org/legal/cpl-v10.html
 *******************************************************************************/

package com.quantum.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;

import com.quantum.Messages;
import com.quantum.QuantumPlugin;
import com.quantum.editors.SQLEditor;
import com.quantum.model.Bookmark;
import com.quantum.ui.dialog.ExceptionDisplayDialog;

/**
 * Action for creating a diagram from the SQL in the editor.
 * 
 * @author	jhvdv
 *
 * 
 **/
 
public class CreateDiagramAction extends Action {
    
    private final SQLEditor editor;

	public CreateDiagramAction(SQLEditor editor) {
        this.editor = editor;
        setText(Messages.getString(CreateDiagramAction.class, "text"));
	}

	public void run() {
        // is there a query?
        String query = editor.getQuery();
        if(query == null)return;
        if(query.equals(""))return;
        try
        {
            // first check the syntax
            editor.checkSyntaxAction.run();
            if(editor.getConfig().getModel().getNumberOfErrors()!=0){
                ExceptionDisplayDialog.openError(editor.getSite().getShell(), "Syntax incorrect", "Quantum can only create Entity-Relation diagrams \r\nfrom syntactically sound SQL statements.", null);
                return;
            }
            // if we are here we have a model
            // We must first create the resource...
            IFile file = ((IFileEditorInput) editor.getEditorInput()).getFile();
            Bookmark bookmark = BookmarkSelectionUtil.getInstance().getLastUsedBookmark();
            IWorkbenchPage page = QuantumPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
            IWorkspace workspace = ResourcesPlugin.getWorkspace();
            IWorkspaceRoot root = workspace.getRoot();
            IProject project = root.getProject(bookmark.getName());
            IFolder folder = project.getFolder("Diagrams");
            IFile diagramFile = folder.getFile(file.getName() + ".erd");
            if(query != "")
            {
                try {
                    if (!project.exists())project.create(null);
                    if (!project.isOpen())project.open(null);
                    if (!folder.exists())folder.create(IResource.NONE, false, null);
                    if(diagramFile.exists())diagramFile.delete(true, null);
                    if (!diagramFile.exists()) {
                        byte[] bytes = "".getBytes();
                        InputStream source = new ByteArrayInputStream(bytes);
                        diagramFile.create(source, IResource.NONE, null);
                    }
                } catch (CoreException e) {
                    ExceptionDisplayDialog.openError(editor.getSite().getShell(), "Error", e.getMessage(), e);
                } catch (Exception exx){
                    ExceptionDisplayDialog.openError(editor.getSite().getShell(), "Error", exx.getMessage(), exx);
                }
                try {
                    IDE.openEditor(page, diagramFile, true);
                    editor.getConfig().getModel().createERDiagram(diagramFile, editor.getQuery());
                } catch (PartInitException e) {
                    ExceptionDisplayDialog.openError(editor.getSite().getShell(), "Error", e.getMessage(), e);
                }
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }

		super.run();
	}
}