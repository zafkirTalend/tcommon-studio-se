package com.quantum.wizards;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.quantum.IQuantumConstants;
import com.quantum.Messages;
import com.quantum.adapters.AdapterFactory;
import com.quantum.adapters.DatabaseAdapter;
import com.quantum.model.JDBCDriver;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;


/**
 * @author BC
 */
public abstract class URLSetupControl extends Composite {
	
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	private String urlPattern;
	private Map properties = Collections.synchronizedMap(new HashMap());
	private final JDBCDriver driver;
	private String connectionURL;

	/**
	 * @param parent
	 * @param style
	 */
	public URLSetupControl(Composite parent, JDBCDriver driver) {
		super(parent, SWT.NONE);
		this.driver = driver;
		GridLayout layout = new GridLayout();
		setLayout(layout);
		this.urlPattern = AdapterFactory.getInstance().getURLPattern(driver.getClassName());
		
		setPropertyDefaults();
		setConnectionURL(URLBuilder.createURL(this.urlPattern, getProperties()));
	}

	/**
	 * 
	 */
	protected void createPart() {
		Group group = new Group(this, SWT.SHADOW_ETCHED_IN);
		group.setText(Messages.getString(URLSetupControl.class, "text"));
		group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING));
		
		createPart(group);
	}

	/**
	 * 
	 */
	private void setPropertyDefaults() {
		DatabaseAdapter adapter = 
			AdapterFactory.getInstance().getAdapter(getDriver().getType());
		Map properties = (adapter == null) ? new HashMap() : adapter.getDefaultConnectionParameters();
		for (Iterator i = properties.keySet().iterator(); i.hasNext();) {
			String key = (String) i.next();
			putProperty(key, (String) properties.get(key));
			
		}
	}

	protected abstract void createPart(Composite parent);
	
	private Map getProperties() {
		return this.properties;
	}

	protected String getProperty(String name) {
		String value = (String) this.properties.get(name);
		return value == null ? "" : value;
	}

	protected void putProperty(String name, String value) {
		this.properties.put(name, value);
		setConnectionURL(URLBuilder.createURL(this.urlPattern, getProperties()));
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.propertyChangeSupport.addPropertyChangeListener(listener);
	}
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		this.propertyChangeSupport.removePropertyChangeListener(listener);
	}
	protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
		this.propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}
	
	protected JDBCDriver getDriver() {
		return this.driver;
	}
	public String getConnectionURL() {
		return this.connectionURL == null ? "" : this.connectionURL;
	}
	public void setConnectionURL(String connectionURL) {
		if (connectionURL != null && !connectionURL.equals(this.connectionURL)) {
			String original = this.connectionURL;
			this.connectionURL = connectionURL;
			firePropertyChange(IQuantumConstants.CONNECTION_URL_PROPERTY, original, connectionURL);
		}
	}
}
