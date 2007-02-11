package com.quantum.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

import com.quantum.IQuantumConstants;
import com.quantum.Messages;
import com.quantum.adapters.AdapterFactory;
import com.quantum.model.xml.ModelToXMLConverter;
import com.quantum.util.JarUtil;
import com.quantum.util.xml.XMLUtil;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The collection of database bookmarks that the Quantum plug-in knows about.
 * This collection is loaded by the QuantumPlugin class before any Quantum plugin 
 * extension is invoked.
 * 
 * @author BC
 */
public class BookmarkCollection {
    
    
private static final String SUN_JDBC_ODBC_DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
	private static BookmarkCollection instance = new BookmarkCollection();
    private List bookmarks = new Vector();
    private boolean changed = false;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
	private Set drivers = Collections.synchronizedSet(new HashSet());
    
    private BookmarkCollection() {
    }

    /**
     * Singleton accessor
     */
    public static BookmarkCollection getInstance() {
        return BookmarkCollection.instance;
    }
    
    /**
     * Imports the bookmars from a properties file.  This load method is 
     * provided for backwards compatability only; we no longer persist 
     * bookmarks as properties files.
     * @param file
     */
    public void load(File file) throws IOException {
        Properties props = new Properties();
        FileInputStream in = new FileInputStream(file);
        props.load(in);
        in.close();
        fromProperties(true, props);
    }
     
    private void fromProperties(boolean overwrite, Properties props) {
        List newBookmarks = new Vector();
        int i = 0;
        while (true) {
            Bookmark bookmark = new Bookmark();
            String name = props.getProperty(i + ".name"); //$NON-NLS-1$
            if (name == null) {
                break;
            }
            bookmark.setName(name);
            bookmark.setUsername(props.getProperty(i + ".username")); //$NON-NLS-1$
            bookmark.setPassword(props.getProperty(i + ".password")); //$NON-NLS-1$
            bookmark.setConnect(props.getProperty(i + ".connect")); //$NON-NLS-1$
            String schema = props.getProperty(i + ".schema"); //$NON-NLS-1$
            if (schema != null) {
                bookmark.addSchema(schema);
            }
            if (!bookmark.isEmpty()) {
                newBookmarks.add(bookmark);
            }
            i++;
            String driver = props.getProperty(i + ".driver"); //$NON-NLS-1$
            String driverFile = props.getProperty(i + ".driverLocation"); //$NON-NLS-1$
            String type = props.getProperty(i + ".type"); //$NON-NLS-1$
            this.drivers.add(new JDBCDriver(driver, new String[] { driverFile }, type));
        }
        if (overwrite) {
            this.bookmarks = newBookmarks;
        } else {
            this.bookmarks.addAll(newBookmarks);
        }
    }
    /**
     * Finds a Bookmark with the specified name. Is case-sensitive.
     * 
     * @param name
     * @return the bookmark with the specified name, or null if no bookmark can be found
     */
    public Bookmark find(String name){
        Bookmark result = null;
        if (name != null) {
            for (Iterator i = this.bookmarks.iterator(); result == null && i.hasNext(); ) {
                Bookmark temp = (Bookmark) i.next();
                if (name.equals(temp.getName())) {
                    result = temp;
                }
            }
        }
        return result;
    }

    /**
     * Exports a Bookmark data to an XMLDocument Element
     * The complementary function is importXML()
     * @param root  The Element to fill up with the bookmark info
     */
    public void exportXML(Element root) {
        System.out.println("Bookmarks: Saving to Element"); //$NON-NLS-1$
        Element bookmarkRoot = XMLUtil.createElementText(root,"bookmarks", ""); //$NON-NLS-1$ //$NON-NLS-2$
        for (Iterator i = this.drivers.iterator(); i.hasNext(); ) {
            JDBCDriver driver = (JDBCDriver) i.next();
            ModelToXMLConverter.getInstance().convert(bookmarkRoot, driver);
        }
        for (int i = 0; i < bookmarks.size(); i++) {
            Bookmark b = (Bookmark) bookmarks.get(i);
            ModelToXMLConverter.getInstance().convert(bookmarkRoot, b);
        }
    }

    /**
     * Imports a Bookmark data from an XMLDocument Element
     * The complementary function is exportXML()
     * @param root  The Element from which to load
     */
    public void importXML(Element root) {
        this.changed = true;
        System.out.println("Bookmarks: Loading from Element"); //$NON-NLS-1$
        importDrivers(root);
        Vector newBookmarks = importBookmarks(root);
        for (Iterator iter = newBookmarks.iterator(); iter.hasNext();) {
			Bookmark bookmark = (Bookmark) iter.next();
			if (!this.bookmarks.contains(bookmark))
				this.bookmarks.add(bookmark);
		}
        this.support.firePropertyChange("bookmarks", null, null);
    }

    /**
	 * @param root
	 * @return
	 */
	private void importDrivers(Element root) {
        NodeList nodes = root.getElementsByTagName("jdbcDriver"); //$NON-NLS-1$
        for (int i = 0; i < nodes.getLength(); i++) {
        	Element driver = (Element) nodes.item(i);

        	List jarFileNames = new ArrayList();
        	String jarFileName = driver.getAttribute("jarFileName");
        	if (jarFileName != null && jarFileName.trim().length() > 0) {
        		jarFileNames.add(jarFileName);
        	}
        	NodeList jars = driver.getElementsByTagName("jar");
        	for (int j = 0; j < jars.getLength(); j++) {
        		String fileName = ((Element) jars.item(j)).getAttribute("fileName");
        		if (fileName != null && fileName.trim().length() > 0) {
        			jarFileNames.add(fileName);
        		}
        	}
        	
        	if (!isQuantum232Notation(driver)) {
	        	addDriver(new JDBCDriver(
		        	driver.getAttribute("className"),
		        	(String[]) jarFileNames.toArray(new String[jarFileNames.size()]),
		        	driver.getAttribute("type"),
		        	driver.getAttribute("name"),
		        	driver.getAttribute("version")));
        	}
        }
        addStandardDrivers();
	}

	/**
	 * An earlier version of the Quantum XML format omitted the
	 * type.
	 * 
	 * @param driver
	 * @return
	 */
	private boolean isQuantum232Notation(Element driver) {
		return "".equals(driver.getAttribute("type"));
	}

	/**
	 * 
	 */
	private void addStandardDrivers() {
		if (JarUtil.loadDriver(null, SUN_JDBC_ODBC_DRIVER) != null) {
	        addDriver(new JDBCDriver(SUN_JDBC_ODBC_DRIVER, new String[0], 
	        		AdapterFactory.JDBC_ODBC_BRIDGE));
        }
	}

	/**
	 * @param driver
	 */
	public void addDriver(JDBCDriver driver) {
		if (!this.drivers.contains(driver)) {
			this.drivers.add(driver);
			this.support.firePropertyChange("drivers", null, driver);
		}
	}

	/**
	 * @param root
	 * @return
	 */
	private Vector importBookmarks(Element root) {
		Vector newBookmarks = new Vector();
        NodeList nodes = root.getElementsByTagName("bookmark"); //$NON-NLS-1$
        for (int i = 0; i < nodes.getLength(); i++) {
            Bookmark bookmark = new Bookmark();
            Element column = (Element) nodes.item(i);
            
            String name = XMLUtil.getElementText(column,"name"); //$NON-NLS-1$
            if (name == null) break;
            bookmark.setName(name);
            
            XMLUtil.getElementText(column,"name"); //$NON-NLS-1$
            bookmark.setUsername(XMLUtil.getElementText(column,"username")); //$NON-NLS-1$
            bookmark.setPassword(XMLUtil.getElementText(column,"password")); //$NON-NLS-1$
            bookmark.setPromptForPassword(Boolean.TRUE.toString().equalsIgnoreCase(
                XMLUtil.getElementText(column,"prompt"))); //$NON-NLS-1$
            bookmark.setConnect(XMLUtil.getElementText(column,"connect")); //$NON-NLS-1$
			bookmark.setAutoCommit(Boolean.TRUE.toString().equalsIgnoreCase(
				XMLUtil.getElementText(column,"autoCommit", "True"))); //$NON-NLS-1$
			bookmark.setQuoteAll(Boolean.TRUE.toString().equalsIgnoreCase(
					XMLUtil.getElementText(column,"quoteAll", "False"))); //$NON-NLS-1$
			bookmark.setSendQueryAsIs(Boolean.TRUE.toString().equalsIgnoreCase(
					XMLUtil.getElementText(column,"sendQueryAsIs", "False"))); //$NON-NLS-1$
			bookmark.setStripNewline(Boolean.TRUE.toString().equalsIgnoreCase(
					XMLUtil.getElementText(column,"stripNewline", "False"))); //$NON-NLS-1$
			bookmark.setDefaultEncoding(XMLUtil.getElementText(column,"defaultEncoding", "")); //$NON-NLS-1$
			bookmark.setAutoCommitPreference(XMLUtil.getElementText(column,"autoCommitPreference", IQuantumConstants.autoCommitTrue)); //$NON-NLS-1$
			bookmark.setIgnoreQueryViewSetting(Boolean.TRUE.toString().equalsIgnoreCase(
					XMLUtil.getElementText(column,"autoCommitIgnoreView", "False"))); //$NON-NLS-1$
			
			backwardCompatibility(bookmark, column);

            String driverClassName = XMLUtil.getElementText(column,"driver"); //$NON-NLS-1$
            
            List jarFiles = new ArrayList();
            NodeList driverLocations = column.getElementsByTagName("driverLocation");
            for (int j = 0, length = driverLocations == null 
            		? 0 : driverLocations.getLength(); j < length; j++) {
            	String jarFileName = XMLUtil.extractText((Element) driverLocations.item(j), "");
            	if (jarFileName != null && jarFileName.trim().length() > 0) {
            		jarFiles.add(jarFileName);
            	}
			}
            String type = XMLUtil.getElementText(column,"type"); //$NON-NLS-1$
            
            bookmark.setJDBCDriver(new JDBCDriver(driverClassName, 
            		(String[]) jarFiles.toArray(new String[jarFiles.size()]), 
					type));
            
            NodeList children = column.getElementsByTagName("Other_Schemas");
            if (children.getLength() > 0) {
                importSchemas((Element) children.item(0), bookmark);
            }
            
            
            System.out.println(bookmark.toString());
            if (!bookmark.isEmpty()) {
                newBookmarks.addElement(bookmark);
            }
            importQuickList(bookmark, column);
            importQueryList(bookmark, column);
        }
		return newBookmarks;
	}

	/**
	 * Earlier versions of the xml file expected one schema element under the 
	 * bookmark element.  This method sees if it exists.
	 * 
	 * @param bookmark
	 * @param element
	 */
	private void backwardCompatibility(Bookmark bookmark, Element element) {
		NodeList children = element.getChildNodes();
		for (int i = 0, length = children.getLength(); i < length; i++) {
			Node node = children.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE && 
					"schema".equals(((Element) node).getTagName())) {
				String schema = XMLUtil.extractText(element,"");
				if (schema != null && schema.trim().length() > 0) {
					bookmark.addSchema(schema);
				}
			}
		}
	}

	private void importSchemas(Element otherSchemas, Bookmark bookmark) {
        List list = new ArrayList();
        NodeList children = otherSchemas.getChildNodes();
        for (int i = 0, length = children.getLength(); i < length; i++) {
        	Node node = children.item(i);
        	if (node.getNodeType() == Node.ELEMENT_NODE
        			&& "schema".equalsIgnoreCase(((Element) node).getTagName())) {
	            list.add(new Schema(
	            		XMLUtil.extractText((Element) node, "")));
        	}
        }
        
        String schemaRule = otherSchemas.getAttribute("schemaRule");
        if ("useAll".equals(schemaRule)) {
        	bookmark.setSchemaRule(Bookmark.SCHEMA_RULE_USE_ALL);
        } else if ("useDefault".equals(schemaRule)) {
        	bookmark.setSchemaRule(Bookmark.SCHEMA_RULE_USE_DEFAULT);
        } else {
        	bookmark.setSchemaRule(Bookmark.SCHEMA_RULE_USE_SELECTED);
        }
        bookmark.setSchemaSelections((Schema[]) list.toArray(new Schema[list.size()]));
    }

	private void importQuickList(Bookmark bookmark, Element bookmarkElement) {
        NodeList quickList = bookmarkElement.getElementsByTagName("quickList");
        for (int j = 0,
            length = (quickList == null) ? 0 : quickList.getLength();
            j < length;
            j++) {
            
            Element element = (Element) quickList.item(j);
            NodeList childNodes = element.getChildNodes();
                
            for (int k = 0,
                length2 = (childNodes == null) ? 0 : childNodes.getLength();
                k < length2;
                k++) {
                if (Node.ELEMENT_NODE == childNodes.item(k).getNodeType()) {
                    Element entity = (Element) childNodes.item(k);
                    bookmark.addQuickListEntry(entity.getTagName(), 
                        entity.getAttribute("schema"), entity.getAttribute("name"), entity.getAttribute("isSynonym").equals("true") );
                }
            }
        }
    }
    
    private void importQueryList(Bookmark bookmark, Element bookmarkElement) {
        NodeList queryList = bookmarkElement.getElementsByTagName("queryList");
        for (int i = 0,
            length = (queryList == null) ? 0 : queryList.getLength();
            i < length;
            i++) {
            
            Element element = (Element) queryList.item(i);
            NodeList childNodes = element.getElementsByTagName("query");
                
            for (int k = 0,
                length2 = (childNodes == null) ? 0 : childNodes.getLength();
                k < length2;
                k++) {

                Element query = (Element) childNodes.item(k);
                bookmark.addQuery(XMLUtil.getElementText(query,"queryString"));
        
            }
        }
    }
    
    public void addBookmark(Bookmark b) {
        this.changed = true;
        if (!bookmarks.contains(b)) {
            Bookmark[] original = getBookmarks();
            bookmarks.add(b);
            this.support.firePropertyChange("bookmarks", original, getBookmarks());
        }
    }
    public void removeBookmark(Bookmark b) {
        this.changed = true;
        if (bookmarks.contains(b)) {
            Bookmark[] original = getBookmarks();
            bookmarks.remove(b);
            this.support.firePropertyChange("bookmarks", original, getBookmarks());
        }
    }
    
    /**
     * @return An array of Bookmark objects with all the bookmarks defined in Quantum
     */
    public Bookmark[] getBookmarks() {
        return (Bookmark[]) this.bookmarks.toArray(new Bookmark[this.bookmarks.size()]);
    }

    /**
	 * @return An array of strings with a list of the names of the bookmarks defined
	 */
	public String[] getBookmarkNames() {
		String[] bookmarkNames = new String[this.bookmarks.size()];
		for (int i = 0; i < bookmarkNames.length; i++) {
			bookmarkNames[i] = ((Bookmark)this.bookmarks.get(i)).getName();
		}
		return bookmarkNames;
	}
    
    public JDBCDriver[] getJDBCDrivers() {
    	if (this.drivers.isEmpty()) {
            addStandardDrivers();
    	}
    	
    	List list = new ArrayList(this.drivers);
    	Collections.sort(list);
    	return (JDBCDriver[]) list.toArray(new JDBCDriver[list.size()]);
    }
    /**
     * @return
     */
    public boolean isAnythingChanged() {
        boolean anythingChanged = this.changed;
        for (Iterator i = this.bookmarks.iterator(); !anythingChanged && i.hasNext();) {
            Bookmark bookmark = (Bookmark) i.next();
            anythingChanged |= bookmark.isChanged();
        }
        return anythingChanged;
    }
    
    public boolean isChanged() {
        return this.changed;
    }

    /**
     * @param b
     */
    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    /**
     * @param listener
     */
    public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }

    /**
     * @param listener
     */
    public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
        this.support.removePropertyChangeListener(listener);
    }

	/**
	 * @param string
	 * @return
	 */
	public String getCopyName(String name) {
		
		String copyName = Messages.getString("BookmarkView.CopyOf") + name;
		int i = 1;
		while (find(copyName) != null)
		{
			copyName = Messages.getString("BookmarkView.CopyOf") + name + "(" + String.valueOf(i) + ")";
			i++;
		}
		
		return copyName;
	}

	/**
	 * @param driver
	 * @param driverFile
	 */
	public JDBCDriver findDriver(String driverClassName, String[] driverFiles, String type) {
		JDBCDriver temp = new JDBCDriver(driverClassName, driverFiles, type);
		return findDriver(temp);
	}

	/**
	 * @param temp A JDBCDriver to seek
	 * @return The stored JDBCDriver if found, null if not
	 */
	public JDBCDriver findDriver(JDBCDriver temp) {
		JDBCDriver result = null;
		for (Iterator i = this.drivers.iterator(); result == null && i.hasNext();) {
			JDBCDriver driver = (JDBCDriver) i.next();
			if (temp.equals(driver)) {
				result = driver;
			}
		}
		if (result == null) {
			addDriver(temp);
			result = temp;
		}
		return result;
	}

	/**
	 * @param temp A JDBCDriver to seek
	 * @return An array of Bookmark objects that use that JDBCDriver, empty array if none.
	 */
	public Bookmark[] getBookmarksUsingDriver(JDBCDriver driver) {
		Vector bookmarkList = new Vector();
		for (Iterator i = this.bookmarks.iterator(); 
				driver != null && i.hasNext();) {
			Bookmark bookmark = (Bookmark) i.next();
			if (driver.equals(bookmark.getJDBCDriver()))
				bookmarkList.add(bookmark);
		}
		return (Bookmark[]) bookmarkList.toArray(new Bookmark[bookmarkList.size()]);
	}
	
	
	/**
	 * @param driver
	 */
	public boolean removeDriver(JDBCDriver driver) {
		boolean found = (getBookmarksUsingDriver(driver).length > 0 );
		
		if (!found && driver != null && !SUN_JDBC_ODBC_DRIVER.equals(driver.getClassName())) {
			boolean deleted = this.drivers.remove(driver);
			if (deleted) {
				this.support.firePropertyChange("drivers", null, null);
			}
			return deleted;
		} else {
			return false;
		}
	}

}
