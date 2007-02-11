package com.quantum.actions;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.wizards.BookmarkWizard;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IViewPart;

/**
 * @author root
 *
 */
public class NewBookmarkAction extends Action {
    private IViewPart view;

    public NewBookmarkAction(IViewPart view) {
        this.view = view;
        setText(Messages.getString(getClass(), "text")); //$NON-NLS-1$
        setToolTipText(Messages.getString(getClass(), "text")); //$NON-NLS-1$
        setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.NEW_BOOKMARK));
    }

    public void run() {
        BookmarkWizard wizard = new BookmarkWizard();
        wizard.init();
        WizardDialog dialog =
            new WizardDialog(view.getSite().getShell(), wizard);
        dialog.open();
    }
}
