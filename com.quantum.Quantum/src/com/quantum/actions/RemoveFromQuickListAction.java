package com.quantum.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.quantum.Messages;
import com.quantum.view.bookmark.EntityNode;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.SelectionListenerAction;

/**
 * <p>This action takes an EntityNode and adds a copy of it to the quicklist
 * of a bookmark.</p>
 * 
 * @author bcholmes
 */
public class RemoveFromQuickListAction extends SelectionListenerAction  {
    private List entities = Collections.synchronizedList(new ArrayList());

    public RemoveFromQuickListAction(IViewPart view) {
        super(Messages.getString(RemoveFromQuickListAction.class.getName() + ".text"));
    }

    public void run() {
        for (Iterator i = this.entities.iterator(); i.hasNext(); ) {
            EntityNode entityNode = (EntityNode) i.next();
            entityNode.getBookmark().removeQuickListEntry(entityNode.getEntity());
        }
    }

    /**
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(IAction, ISelection)
     */
    public boolean updateSelection(IStructuredSelection selection) {
        boolean enabled = super.updateSelection(selection);
        this.entities.clear();
        for (Iterator i = selection.iterator(); enabled && i.hasNext(); ) {
            Object object = i.next();
            if (object != null && object instanceof EntityNode) {
                EntityNode node = (EntityNode) object;
                enabled &= node.getBookmark().isInQuickList(node.getEntity());
                this.entities.add(node);
            } else {
                enabled = false;
            }
        }
        
        return enabled;
    }
}
