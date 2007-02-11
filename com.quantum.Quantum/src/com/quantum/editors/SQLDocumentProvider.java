package com.quantum.editors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.editors.text.FileDocumentProvider;

public class SQLDocumentProvider extends FileDocumentProvider {
	
	public SQLDocumentProvider() {
		super();
	}

	protected IDocument createDocument(Object element) throws CoreException {
		IDocument document = super.createDocument(element);
		if (document != null) {
            // if the document contains tabs, turn them into spaces...
            String allFormatted = IndentCodeFormatter.getInstance().format(document.get(), 4, false);
            document.set(allFormatted);
		}
		return document;
	}
}