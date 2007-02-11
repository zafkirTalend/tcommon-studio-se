package com.quantum.actions;

import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.SelectionListenerAction;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.QuantumPlugin;
import com.quantum.model.Bookmark;
import com.quantum.model.Entity;
import com.quantum.view.bookmark.EntityNode;
import com.quantum.view.bookmark.GroupNode;
import com.quantum.view.bookmark.PackageNode;
import com.quantum.view.bookmark.ProcedureNode;
import com.quantum.view.bookmark.QueryListNode;
import com.quantum.view.bookmark.QuickListNode;
import com.quantum.view.bookmark.SchemaNode;
import com.quantum.view.bookmark.TreeNode;

public class SearchEntityAction extends SelectionListenerAction{
	private IViewPart view;
    
	public SearchEntityAction(IViewPart view) {
        super(Messages.getString(SearchEntityAction.class, "text"));
        setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.SEARCH));
		this.view = view;
	}

	public void run() {
		final List list = getSelectedNonResources();
		Entity entity = null;
		if (list.size() < 1)
		{
			MessageDialog.openInformation(
				      view.getSite().getShell(),"Error","You have selected nothing");
			return;
		}
		Object selection = list.get(0);
		Bookmark bookmark = null;
		if (selection instanceof TreeNode)
			bookmark = ((TreeNode)selection).getBookmark();
		if (bookmark == null) {
			MessageDialog.openInformation(view.getSite().getShell(),"Unsupported action","Search is unavailable for this selection");
			return;
		}

		boolean canSearch = false;
		if(selection instanceof QuickListNode){
			canSearch = true;
		}else if(selection instanceof GroupNode){
            canSearch = true;
		}else if(selection instanceof SchemaNode){
            canSearch = true;
		}else if(selection instanceof EntityNode){
			entity = ((EntityNode) list.get(0)).getEntity();
			if(Entity.VIEW_TYPE.equals(entity.getType()))
			{
                canSearch = true;
			}else if(Entity.TABLE_TYPE.equals(entity.getType()))
			{
                canSearch = true;
			}else if(entity.isSynonym()){
                canSearch = true;
			}
		}else if(selection instanceof QueryListNode){
            canSearch = true;
        }else if(selection instanceof PackageNode){
            canSearch = true;
        }else if(selection instanceof ProcedureNode){
            canSearch = true;
		}else{
			MessageDialog.openInformation(view.getSite().getShell(),"Unsupported action","Search is unavailable for this selection");
			return;
		}
        NewSearchUI.openSearchDialog(QuantumPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow(), "com.quantum.search.QuantumSearchPage");
    }
}
