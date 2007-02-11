package com.quantum.actions;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.SelectionListenerAction;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.adapters.DatabaseAdapter;
import com.quantum.model.Bookmark;
import com.quantum.model.Schema;
import com.quantum.model.SequenceMetadata;
import com.quantum.model.TableMetadata;
import com.quantum.model.xml.XMLMetadataIterator;
import com.quantum.model.xml.XMLToModelConverter;
import com.quantum.ui.dialog.ConnectionUtil;
import com.quantum.util.connection.NotConnectedException;
import com.quantum.util.xml.XMLHelper;
import com.quantum.view.SQLQueryView;
import com.quantum.view.bookmark.BookmarkNode;
import com.quantum.view.bookmark.SchemaNode;

public class StructurePasteAction extends SelectionListenerAction {
	private final IViewPart view;
	
	public StructurePasteAction(IViewPart view) {
		super(Messages.getString(StructurePasteAction.class, "text"));
		this.view = view;
		setImageDescriptor(
				ImageStore.getImageDescriptor(ImageStore.PASTE));
	}
	
	public void run() {
		List list = getSelectedNonResources();
		if (list.size() != 1) {
			MessageDialog.openInformation(
				      view.getSite().getShell(),"Error","Please select just one schema or bookmark");
			return;
		}
		Object selection = list.get(0);
		if ( selection instanceof BookmarkNode || 
				selection instanceof SchemaNode ) {
			
			// Take the text from the Clipboard and make it into an XML Element
			Document doc;
			try {
				doc = XMLHelper.getDocumentFromClipboard();
			} catch (Exception e) {
				MessageDialog.openInformation(
						view.getSite().getShell(),"Error","Clipboard contents doesn't seem to be XML. Try Structure->Copy on a table");
				return;
			}
			// Check to see if it looks like Quantum metadata
			Element root = (doc == null) ? null : doc.getDocumentElement();
			if ( root == null || ! root.getNodeName().equals(Messages.getString("ExportXMLAction.Metadata"))) {
				MessageDialog.openInformation(
						view.getSite().getShell(),"Error","Clipboard contents doesn't seem to be Quantum metadata. Try Structure->Copy on a table");
				return;
			}
			SchemaNode schemaNode = (SchemaNode) selection;
			String createEntities = "";
			Iterator iter = new XMLMetadataIterator(doc, 1);		
			// Then we generate the DDL statements
			Bookmark bookmark = schemaNode.getBookmark();
			Shell shell = view.getSite().getShell();
			String eol = System.getProperty("line.separator");
			if (!bookmark.isConnected()) {
				new ConnectionUtil().connect(bookmark, shell);
			}
			//	Iterate through the entities and generate its DDL
			while (iter.hasNext()) {
				Element element = (Element) iter.next();
				String database = element.getAttribute("database");
				try {
					// If the metadata comes from a different database, we'll use the java types
					boolean useJavaTypes = !(database.equals(bookmark.getAdapter().getDisplayName()));
					createEntities += buidCreateStatement(bookmark, element, schemaNode.getSchema(), useJavaTypes);
					createEntities += eol;
				} catch (NotConnectedException e2) {
					e2.printStackTrace();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			
			SQLQueryView queryView = SQLQueryView.getInstance();
			BookmarkSelectionUtil.getInstance().setLastUsedBookmark(bookmark);
			
			if (queryView != null && createEntities != null) {
				queryView.setQuery(createEntities);
			}
			
		} else {
			MessageDialog.openInformation(
					view.getSite().getShell(),"Error",
			"Your selection cannot be pasted. Try selecting a schema or bookmark");
			return;
		}
		
	}
	/**
	 * @param bookmark
	 * @param node
	 * @param schema
	 * @param b
	 * @return
	 * @throws SQLException 
	 * @throws NotConnectedException 
	 */
	private String buidCreateStatement(Bookmark bookmark, Node node, Schema schema, boolean useJavaTypes) 
	throws NotConnectedException, SQLException {
		DatabaseAdapter adapter = bookmark.getAdapter();
		if (node.getNodeName() == "table") {
			TableMetadata tableMetadata = new XMLToModelConverter((Element)node);
			return adapter.buildCreateTable(bookmark, tableMetadata, schema, useJavaTypes);
		} else if (node.getNodeName() == "view") {
			TableMetadata tableMetadata = new XMLToModelConverter((Element)node);
			return adapter.buildCreateView(bookmark, tableMetadata, schema );
		} else if (node.getNodeName() == "sequence") {
			SequenceMetadata sequenceMetadata = new XMLToModelConverter((Element)node);
			return adapter.buildCreateSequence(bookmark, sequenceMetadata, schema );
		} 
		return "";
	}
	
	public boolean updateSelection(IStructuredSelection selection) {
		// I like it better to have the selection always active, and explain in a
		// message box why it cannot be executed if that's the case.
		return true;
	}


}
