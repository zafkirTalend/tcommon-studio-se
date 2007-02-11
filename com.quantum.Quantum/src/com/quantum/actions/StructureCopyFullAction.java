package com.quantum.actions;

import java.sql.SQLException;
import java.util.List;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.QuantumPlugin;
import com.quantum.model.xml.ModelToXMLConverter;
import com.quantum.ui.dialog.ExceptionDisplayDialog;
import com.quantum.ui.dialog.SQLExceptionDialog;
import com.quantum.util.connection.NotConnectedException;
import com.quantum.util.xml.XMLRenderer;
import com.quantum.view.bookmark.DbObjectNode;
import com.quantum.view.bookmark.GroupNode;
import com.quantum.view.bookmark.QuickListNode;
import com.quantum.view.bookmark.SchemaNode;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.SelectionListenerAction;
import org.w3c.dom.Document;

public class StructureCopyFullAction extends SelectionListenerAction {
	private final IViewPart view;
	
	public StructureCopyFullAction(IViewPart view) {
		super(Messages.getString(StructureCopyFullAction.class, "text"));
		this.view = view;
		setImageDescriptor(
				ImageStore.getImageDescriptor(ImageStore.COPY));
	}
	
	public void run() {
		List list = getSelectedNonResources();
		if (list.size() < 1) {
			MessageDialog.openInformation(
				      view.getSite().getShell(),"Error","You have selected nothing");
			return;
		}
		Object selection = list.get(0);
        if ( selection instanceof DbObjectNode 
					|| selection instanceof GroupNode
					|| selection instanceof SchemaNode 
					|| selection instanceof QuickListNode ) {
			
			Document doc;
			try {
				doc = ModelToXMLConverter.getInstance().convertList(list, null, true);
			} catch (NotConnectedException e) {
	            ExceptionDisplayDialog.openError(view.getSite().getShell(), null, null, e);
	            e.printStackTrace();
				return;
			} catch (SQLException e) {
				SQLExceptionDialog.openException(view.getSite().getShell(), null, e);
				e.printStackTrace();
				return;
			}
			String copyText = XMLRenderer.render(doc);
			if (copyText != null) {
				QuantumPlugin.getDefault().getSysClip().setContents(
						new Object[] { copyText },
						new Transfer[] { TextTransfer.getInstance()});
			}
        } else {
			MessageDialog.openInformation(
				      view.getSite().getShell(),"Error",
				      "Your selection cannot be copied. Try selecting a table, view, schema, quick list...");
			return;
        }
		
	}

	public boolean updateSelection(IStructuredSelection selection) {
		// I like it better to have the selection always active, and explain in a
		// message box why it cannot be executed if that's the case.
		return true;
	}

}
