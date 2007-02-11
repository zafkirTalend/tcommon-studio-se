package com.quantum.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.quantum.IQuantumConstants;
import com.quantum.util.Displayable;
import com.quantum.util.DisplayableComparator;
import com.quantum.util.JarUtil;
import com.quantum.util.StringArrayComparator;


/**
 * @author BC
 */
public class JDBCDriver implements Comparable, Displayable {
	private String name;
	private String version;
	private String className;
	private List jarFileNames = Collections.synchronizedList(new ArrayList());
	private String type;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	/**
	 * @param className
	 * @param jarFileName
	 */
	public JDBCDriver(String className, String[] jarFileNames, String type) {
		this(className, jarFileNames, type, null, null);
	}
	/**
	 * @param className
	 * @param jarFileName
	 * @param name
	 * @param version
	 */
	public JDBCDriver(String className, String[] jarFileNames, String type, String name, String version) {
		this.name = name;
		this.version = version;
		this.type = type;
		this.className = className;
		this.jarFileNames.addAll(Arrays.asList(jarFileNames));
	}
	/**
	 * @return Returns the className.
	 */
	public String getClassName() {
		return this.className;
	}
	/**
	 * @param className The className to set.
	 */
	public void setClassName(String className) {
		if (className != null && !className.equals(this.className)) {
			String original = this.className;
			this.className = className;
			this.propertyChangeSupport.firePropertyChange("className", original, className);
		}
	}
	/**
	 * @return Returns the jarFileName.
	 */
	public String[] getJarFileNames() {
		return (String[]) this.jarFileNames.toArray(new String[this.jarFileNames.size()]);
	}
	/**
	 * @param jarFileName The jarFileName to set.
	 */
	public void setJarFileNames(String[] jarFileNames) {
		StringArrayComparator comparator = new StringArrayComparator();
		if (comparator.compare(
				(String[]) this.jarFileNames.toArray(new String[this.jarFileNames.size()]),
				jarFileNames) != 0) {
			String[] original = getJarFileNames();
			this.jarFileNames.clear();
			this.jarFileNames.addAll(Arrays.asList(jarFileNames));
			this.propertyChangeSupport.firePropertyChange("jarFileName", original, jarFileNames);
		}
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return this.name == null || this.name.trim().length() == 0 ? getClassName() : this.name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		if (name != null && !name.equals(this.name)) {
			String original = this.name;
			this.name = name;
			this.propertyChangeSupport.firePropertyChange(IQuantumConstants.NAME_PROPERTY, original, name);
		}
	}
	/**
	 * @return Returns the version.
	 */
	public String getVersion() {
		return this.version;
	}
	/**
	 * @param version The version to set.
	 */
	public void setVersion(String version) {
		if (version != null && !version.equals(this.version)) {
			String original = this.version;
			this.version = version;
			this.propertyChangeSupport.firePropertyChange("version", original, version);
		}
	}
	
	public boolean equals(Object object) {
		if (super.equals(object)) {
			return true;
		} else if (object == null) {
			return false;
		} else if (getClass() != object.getClass()) {
			return false;
		} else {
			return equals((JDBCDriver) object);
		}
	}
	/**
	 * @param that
	 * @return
	 */
	private boolean equals(JDBCDriver that) {
		if (this.className == null && that.className != null) {
			return false;
		} else if (this.className != null && !this.className.equals(that.className)) {
			return false;
		} else if ((new StringArrayComparator()).compare(
				this.getJarFileNames(), that.getJarFileNames()) != 0) {
			return false;
		} else if (this.type == null && that.type != null) {
			return false;
		} else if (this.type != null && !this.type.equals(that.type)) {
			return false;
		} else {
			return true;
		}
	}
	public int hashCode() {
		int hashCode = 31;
		if (this.className != null) {
			hashCode ^= this.className.hashCode();
		}
		for (Iterator i = this.jarFileNames.iterator(); i.hasNext();) {
			Object jarFile = i.next();
			if (jarFile != null) {
				hashCode ^= jarFile.hashCode();
			}
		}
		if (this.type != null) {
			hashCode ^= this.type.hashCode();
		}
		return hashCode;
	}
	
	public Driver getDriver() {
		return JarUtil.loadDriver(getJarFileNames(), getClassName());
	}
	/**
	 * @param listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.propertyChangeSupport.addPropertyChangeListener(listener);
	}
	/**
	 * @param propertyName
	 * @param listener
	 */
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		this.propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}
	/**
	 * @param arg0
	 */
	public void removePropertyChangeListener(PropertyChangeListener arg0) {
		this.propertyChangeSupport.removePropertyChangeListener(arg0);
	}
	/**
	 * @param arg0
	 * @param arg1
	 */
	public void removePropertyChangeListener(String arg0, PropertyChangeListener arg1) {
		this.propertyChangeSupport.removePropertyChangeListener(arg0, arg1);
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return this.type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		if (type != null && !type.equals(this.type)) {
			String original = this.type;
			this.type = type;
			this.propertyChangeSupport.firePropertyChange("type", original, type);
		}
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object object) {
		return (new DisplayableComparator()).compare(this, object);
	}
	/* (non-Javadoc)
	 * @see com.quantum.model.Displayable#getDisplayName()
	 */
	public String getDisplayName() {
		return getName();
	}
	
	public String getJarFilePath() {
		StringBuffer buffer = new StringBuffer();
		for (Iterator i = this.jarFileNames.iterator(); i.hasNext();) {
			String element = (String) i.next();
			buffer.append(element);
			if (i.hasNext()) {
				buffer.append(File.pathSeparator);
			}
		}
		return buffer.toString();
	}
}
