/*
 * Created on 17/02/2004
 *
 */
package com.quantum.extensions;

import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;

import com.quantum.Messages;
import com.quantum.QuantumPlugin;
import com.quantum.model.xml.ModelToXMLConverter;
import com.quantum.sql.TableRow;
import com.quantum.ui.dialog.ExceptionDisplayDialog;
import com.quantum.ui.dialog.SQLExceptionDialog;
import com.quantum.util.connection.NotConnectedException;
import com.quantum.util.xml.XMLHelper;
import com.quantum.util.xml.XMLRenderer;
import com.quantum.util.xml.XMLUtil;
import com.quantum.view.bookmark.BookmarkView;
import com.quantum.view.tableview.TableView;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.SelectionListenerAction;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author panic
 *
 * Defines an Action Class to implement extension Actions. 
 * An external plug-in that wants to extend quantum will 
 * generate one ExtensionAction object for each action it
 * wants to perform.
 */
public class ExtensionAction extends SelectionListenerAction {
	private IViewPart view;
	private IQuantumExtension extension;
	private TableRow tableRow;
	
	public ExtensionAction(IViewPart view, String label, String toolTip, IQuantumExtension extension) {
		super(label);
		this.view = view;
		this.extension = extension;

		setText(label);
		setToolTipText(toolTip); //$NON-NLS-1$
		tableRow = null;
	}
	
	public void run() {
		if (extension instanceof IMetadataExtension &&	view instanceof BookmarkView)
			runMetadataExtension();
		else if (extension instanceof IDataExtension &&	view instanceof TableView)
			runDataExtension();
	}

	/**
	 * 
	 */
	private void runDataExtension() {
		IDataExtension dataExtension = (IDataExtension) extension;
		if (tableRow == null) return;
		Document doc;
		try {
			doc = XMLHelper.createEmptyDocument();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return;
		}
		Element root = (Element) doc.appendChild(doc.createElement(Messages.getString("ExportXMLAction.Metadata"))); //$NON-NLS-1$
		XMLUtil.createElementText(root, Messages.getString("ExportXMLAction.Author"), //$NON-NLS-1$
									Messages.getString("ExportXMLAction.Quantum")); //$NON-NLS-1$
		XMLUtil.createElementText(root, Messages.getString("ExportXMLAction.Version"), //$NON-NLS-1$
									Messages.getString("ExportXMLAction.XMLVersionNumber")); //$NON-NLS-1$
		ModelToXMLConverter.getInstance().convert(root, tableRow.getEntity() );                  
		XMLUtil.stringMatrixToXML(tableRow.getRowTableData(), doc, root, "DataRow"); //$NON-NLS-1$

		dataExtension.run(doc);
	}

	private void runMetadataExtension() {
		IMetadataExtension metadataExtension = (IMetadataExtension) extension;
		BookmarkView bookmarkView = (BookmarkView) view;
		StructuredSelection selection = bookmarkView.getSelection();
		if (selection == null) return;
		Document doc = null;
		try {
			doc = ModelToXMLConverter.getInstance().convertList(selection.toList(), null, false);
		} catch (NotConnectedException e) {
            ExceptionDisplayDialog.openError(view.getSite().getShell(), null, null, e);
			e.printStackTrace();
		} catch (SQLException e) {
			SQLExceptionDialog.openException(view.getSite().getShell(), null, e);
			e.printStackTrace();
		}
		if (doc == null) 
			return;
		
		QuantumPlugin.getDefault().getSysClip().setContents(
			new Object[] { XMLRenderer.render(doc) },
			new Transfer[] { TextTransfer.getInstance()});
		
		metadataExtension.run(doc);
	}

	/**
	 * @param row
	 */
	public void addRowData(TableRow row) {
		tableRow = row;
	}

}
