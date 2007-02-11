package com.quantum.view.bookmark;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.quantum.IQuantumConstants;
import com.quantum.Messages;
import com.quantum.model.Bookmark;
import com.quantum.wizards.BookmarkNameWizardPage;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.SelectionListenerAction;

/**
 * @author BC
 */
public class RenameAction extends SelectionListenerAction {
	
	public class RenameWizard extends Wizard implements PropertyChangeListener {
		
		private BookmarkNameWizardPage page;
		private String name;
		
		public RenameWizard(String name) {
			this.name = name;
		}
		
		public void addPages() {
			super.addPages();
			this.page = new BookmarkNameWizardPage("pageName", this.name);
			this.page.addPropertyChangeListener(this);
			addPage(this.page);
		}
		
		public void dispose() {
			this.page.removePropertyChangeListener(this);
			super.dispose();
		}
		public boolean performFinish() {
			return true;
		}

		public void propertyChange(PropertyChangeEvent event) {
			if (IQuantumConstants.NAME_PROPERTY.equals(event.getPropertyName())) {
				setName((String) event.getNewValue());
			}
		}
		
		public String getName() {
			return this.name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
	

    private IViewPart viewPart;

    /**
     * @param text
     */
    protected RenameAction(IViewPart viewPart) {
        super(Messages.getString(RenameAction.class.getName() + ".text"));
        this.viewPart = viewPart;
    }

    /**
     * @see org.eclipse.ui.actions.SelectionListenerAction#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
     */
    protected boolean updateSelection(IStructuredSelection selection) {
        boolean enabled = super.updateSelection(selection);
        return enabled & selection.size() == 1 & 
            selection.getFirstElement() instanceof BookmarkNode;
    }
    
    private Bookmark getBookmark() {
        return ((BookmarkNode) getSelectedNonResources().get(0)).getBookmark();
    }

    /**
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
    	RenameWizard wizard = new RenameWizard(getBookmark().getName());
        WizardDialog dialog = new WizardDialog(this.viewPart.getSite().getShell(), wizard);
        int result = dialog.open();
        if (result == WizardDialog.OK) {
            getBookmark().setName(wizard.getName());
        }
    }
}
