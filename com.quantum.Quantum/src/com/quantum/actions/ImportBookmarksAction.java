package com.quantum.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import com.quantum.Messages;
import com.quantum.QuantumPlugin;
import com.quantum.model.BookmarkCollection;
import com.quantum.util.xml.XMLHelper;
import com.quantum.view.bookmark.BookmarkView;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * @author root
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ImportBookmarksAction implements IViewActionDelegate {
	BookmarkView view;
	FileDialog dialog;
	/**
	 * @see org.eclipse.ui.IViewActionDelegate#init(IViewPart)
	 */
	public void init(IViewPart view) {
		this.view = (BookmarkView) view;
		dialog = new FileDialog(view.getSite().getShell(), SWT.OPEN);
		dialog.setFilterExtensions(new String[]{"*.xml","*.*"}); //$NON-NLS-1$ //$NON-NLS-2$
		dialog.setFilterNames(new String[]{Messages.getString("filedialog.exportxml.filter"),Messages.getString("filedialog.xmlFiles")}); //$NON-NLS-1$ //$NON-NLS-2$
		
	}

	/**
	 * @see org.eclipse.ui.IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		dialog.setFilterPath(QuantumPlugin.getDefault().getPreferenceStore().getString("quantum.dialogs.importbookmark.path"));
		String filename = dialog.open();
		if (filename != null) {
			QuantumPlugin.getDefault().getPreferenceStore().setValue("quantum.dialogs.importbookmark.path", filename);
			File importFile = new File(filename);
			
			FileInputStream source = null;
			try {
				source = new FileInputStream(importFile);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				return;
			}
			try {
				Document doc = XMLHelper.createFromInputStream(source);
    			Element root = doc.getDocumentElement();
    			BookmarkCollection.getInstance().importXML(root);
    		
    			view.refresh();		
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

		}
	}

	/**
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

}
