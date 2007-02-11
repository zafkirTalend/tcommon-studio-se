package com.quantum.wizards;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.quantum.Messages;
import com.quantum.model.Bookmark;
import com.quantum.view.SchemaSelectionControl;

import org.eclipse.swt.widgets.Composite;


/**
 * @author BC
 */
public class SchemaSelectionWizardPage extends PropertyChangeWizardPage {

	private final Bookmark bookmark;
	private SchemaSelectionControl control;
	private PropertyChangeListener listener;

	/**
	 * @param pageName
	 */
	protected SchemaSelectionWizardPage(String pageName, Bookmark bookmark) {
		super(pageName);
		this.bookmark = bookmark;
		setTitle(Messages.getString(getClass(), "title"));
		setDescription(Messages.getString(getClass(), "description"));
	}

	public void createControl(Composite parent) {
		control = new SchemaSelectionControl(parent, this.bookmark);
		control.addPropertyChangeListener(this.listener = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				firePropertyChange(event.getPropertyName(), event.getOldValue(), event.getNewValue());
			}
		});
		setControl(control);
	}
	public void dispose() {
		if (this.listener != null) {
			this.control.removePropertyChangeListener(this.listener);
		}
		super.dispose();
	}
}
