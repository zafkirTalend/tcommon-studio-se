package com.quantum.wizards;

import com.quantum.Messages;
import com.quantum.model.BookmarkCollection;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


/**
 * @author BC
 */
public class BookmarkNameWizardPage extends PropertyChangeWizardPage {
	
	private String name;
	private final String originalName;

	public BookmarkNameWizardPage(String pageName) {
		this(pageName, null);
	}

	/**
	 * @param pageName
	 */
	public BookmarkNameWizardPage(String pageName, String originalName) {
		super(pageName);
		this.originalName = originalName;
		setTitle(Messages.getString(getClass(), "title"));
		setDescription(Messages.getString(getClass(), "description"));
	}

	public void createControl(Composite parent) {
		setPageComplete(false);
		
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		
		Label label = new Label(composite, SWT.NONE);
		label.setText(Messages.getString(getClass(), "name"));
		
		Text name = new Text(composite, SWT.BORDER | SWT.SINGLE);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		name.setLayoutData(data);
		if (this.originalName != null) {
			name.setText(this.originalName);
		}
		name.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent event) {
				String name = ((Text) event.getSource()).getText();
				setName(name);
			}
		});
		setControl(composite);
	}

	/**
	 * 
	 */
	private void setName(String bookmarkName) {
		String errorMessage = null;
		boolean done = true;
		if (bookmarkName == null || bookmarkName.trim().length() == 0) {
			errorMessage = Messages.getString(getClass(), "mustProvide");
			done = false;
		} else if (this.originalName != null && this.originalName.equals(bookmarkName)) {
			done = false;
		} else if (BookmarkCollection.getInstance().find(bookmarkName) != null) {
			errorMessage = Messages.getString(getClass(), "alreadyExists");
			done = false;
		}
		
		setErrorMessage(errorMessage);
		setPageComplete(done);

		if (errorMessage == null && !bookmarkName.equals(this.name)) {
			String original = this.name;
			this.name = bookmarkName;
			firePropertyChange("name", original, bookmarkName);
		}
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return this.name;
	}
}
