package com.quantum.wizards;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.quantum.IQuantumConstants;
import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.model.Bookmark;
import com.quantum.model.BookmarkCollection;
import com.quantum.model.JDBCDriver;
import com.quantum.model.Schema;

import org.eclipse.jface.wizard.Wizard;

public class BookmarkWizard extends Wizard implements PropertyChangeListener {
	private JDBCDriverSelectionWizardPage page1;
	private BookmarkConnectionWizardPage page2;
	private BookmarkNameWizardPage page3;
	private SchemaSelectionWizardPage page4;

	private Bookmark bookmark;
	
	public void init() {
		setWindowTitle(Messages.getString("BookmarkWizard.NewBookmark")); //$NON-NLS-1$
		setDefaultPageImageDescriptor(ImageStore.getImageDescriptor(ImageStore.NEW_BOOKMARK_WIZARD));
		this.bookmark = new Bookmark();
	}
	public boolean performFinish() {
		BookmarkCollection.getInstance().addBookmark(this.bookmark);
		
		return true;
	}
	public void addPages() {
		this.page1 = new JDBCDriverSelectionWizardPage("page1"); //$NON-NLS-1$
		this.page1.addPropertyChangeListener(this);
		addPage(this.page1);
		this.page2 = new BookmarkConnectionWizardPage("page2"); //$NON-NLS-1$
		this.page2.addPropertyChangeListener(this);
		addPage(this.page2);
		this.page3 = new BookmarkNameWizardPage("page3"); //$NON-NLS-1$
		this.page3.addPropertyChangeListener(this);
		addPage(this.page3);
		this.page4 = new SchemaSelectionWizardPage("page4", this.bookmark); //$NON-NLS-1$
		this.page4.addPropertyChangeListener(this);
		addPage(this.page4);
	}
	public void propertyChange(PropertyChangeEvent event) {
		
		if (IQuantumConstants.DRIVER_PROPERTY.equals(event.getPropertyName())) {
			JDBCDriver driver = (JDBCDriver) event.getNewValue();
			this.bookmark.setJDBCDriver(driver);
			this.page2.setDriver(driver);
		} else if (IQuantumConstants.NAME_PROPERTY.equals(event.getPropertyName())) {
			this.bookmark.setName((String) event.getNewValue());
		} else if (IQuantumConstants.USERID_PROPERTY.equals(event.getPropertyName())) {
			this.bookmark.setUsername((String) event.getNewValue());
		} else if (IQuantumConstants.PASSWORD_PROPERTY.equals(event.getPropertyName())) {
			this.bookmark.setPassword((String) event.getNewValue());
		} else if (IQuantumConstants.CONNECTION_URL_PROPERTY.equals(event.getPropertyName())) {
			this.bookmark.setConnect((String) event.getNewValue());
		} else if (IQuantumConstants.PROMPT_PROPERTY.equals(event.getPropertyName())) {
			this.bookmark.setPromptForPassword(Boolean.TRUE.equals(event.getNewValue()));
		} else if (IQuantumConstants.SCHEMA_RULE_PROPERTY.equals(event.getPropertyName())) {
			this.bookmark.setSchemaRule(((Integer) event.getNewValue()).intValue());
		} else if (IQuantumConstants.SCHEMAS_PROPERTY.equals(event.getPropertyName())) {
			this.bookmark.setSchemaSelections((Schema[]) event.getNewValue());
		}
		
	}
	public void dispose() {
		this.page1.removePropertyChangeListener(this);
		this.page2.removePropertyChangeListener(this);
		this.page3.removePropertyChangeListener(this);
		this.page4.removePropertyChangeListener(this);
		super.dispose();
	}
}

