/*******************************************************************************
 * Copyright (c) 2005 RadRails.org and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.radrails.org/legal/cpl-v10.html
 *******************************************************************************/

package com.quantum.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;

import com.quantum.Messages;
import com.quantum.editors.IndentCodeFormatter;
import com.quantum.editors.SQLEditor;

/**
 * Action for formatting the editor.
 * 
 * @author	mbaumbach/jhvdv
 *
 * @version	0.5.3
 */
public class SQLFormatAction extends Action {
    
    private final SQLEditor editor;

	public SQLFormatAction(SQLEditor editor) {
        this.editor = editor;
        setText(Messages.getString(SQLFormatAction.class, "text"));
	}

	public void run() {
		IDocument doc = editor.getDocumentProvider().getDocument(editor.getEditorInput());

		try {
			ISelection selection = editor.getSelectionProvider().getSelection();
			if (selection instanceof TextSelection) {
				TextSelection textSelection = (TextSelection) selection;
				String text = textSelection.getText();
				if (text == null || text.length() == 0) {
					String allFormatted = IndentCodeFormatter.getInstance().format(doc.get(), 4, false);
					doc.set(allFormatted);
				} else {
					// format always complete lines, otherwise the indentation
					// of the first line is lost
					int startPos = doc.getLineOffset(textSelection.getStartLine());
					int endLine = textSelection.getEndLine();
					int endPos = doc.getLineOffset(endLine) + doc.getLineLength(endLine);

					String formatted = IndentCodeFormatter.getInstance().format(doc.get(startPos, endPos - startPos), 4, false);
					doc.replace(startPos, endPos - startPos, formatted);
				}
			}

		} catch (BadLocationException e) {
		}

		super.run();
	}
}