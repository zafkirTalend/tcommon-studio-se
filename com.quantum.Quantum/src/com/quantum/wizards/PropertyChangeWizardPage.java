package com.quantum.wizards;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.jface.wizard.WizardPage;


/**
 * @author BC
 */
public abstract class PropertyChangeWizardPage extends WizardPage {

	protected PropertyChangeWizardPage(String pageName) {
		super(pageName);
	}

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	/**
	 * @param listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.propertyChangeSupport.addPropertyChangeListener(listener);
	}

	/**
	 * @param listener
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		this.propertyChangeSupport.removePropertyChangeListener(listener);
	}

	/**
	 * @param propertyName
	 * @param oldValue
	 * @param newValue
	 */
	protected void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {
		this.propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}
	/**
	 * @param propertyName
	 * @param oldValue
	 * @param newValue
	 */
	protected void firePropertyChange(String propertyName, int oldValue, int newValue) {
		this.propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}
	/**
	 * @param propertyName
	 * @param oldValue
	 * @param newValue
	 */
	protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
		this.propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}
}
