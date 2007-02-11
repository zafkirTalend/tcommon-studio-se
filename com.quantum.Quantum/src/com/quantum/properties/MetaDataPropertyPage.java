package com.quantum.properties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.dialogs.PropertyPage;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.model.Bookmark;
import com.quantum.model.BookmarkHolder;

/**
 * @author <a href="http://www.intelliware.ca/">Intelliware Development</a>
 * @author BC Holmes
 */
public abstract class MetaDataPropertyPage extends PropertyPage {
    protected Control createContents(Composite parent) {

        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout());
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		createHeader(composite);
        
        createInformationArea(composite);
        
        return composite;
    }	
	/**
     * @param composite
     */
    protected abstract void createHeader(Composite composite);
	protected abstract void createInformationArea(Composite composite);
    /**
	 * @param composite
	 */
	protected void createErrorMessage(Composite composite, Exception e) {
		Label icon = new Label(composite, SWT.NONE);
		icon.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING));
		icon.setImage(ImageStore.getImage(ImageStore.WARNING));
		
		Label error = new Label(composite, SWT.NONE);
		error.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING));
		error.setText(Messages.getString(MetaDataPropertyPage.class, "error") 
				+ (e.getMessage() == null ? "" : "\n" + e.getMessage()));
	}
    /**
     * @return
     */
    protected Bookmark getBookmark() {
        return ((BookmarkHolder) getElement()).getBookmark();
    }
}
