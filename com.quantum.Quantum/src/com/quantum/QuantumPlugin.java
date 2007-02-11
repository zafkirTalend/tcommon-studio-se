package com.quantum;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.PropertyResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.quantum.model.BookmarkCollection;
import com.quantum.util.versioning.VersioningHelper;
import com.quantum.util.xml.XMLHelper;
import com.quantum.view.subset.SubsetContentProvider;

import org.eclipse.core.resources.ISavedState;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * Main class of the quantum plugin, sets defaults, saves and recovers state.
 * @author root
 */
public class QuantumPlugin extends AbstractUIPlugin {
     
	private static QuantumPlugin plugin;
	private Clipboard sysClip;

	/**
	 * 
	 * TODO: BCH - this constructor has changed in Eclipse 3.0.  This
	 * old version of the constructor is still necessary for running under
	 * Eclipse 2.x.
	 * 
	 * @param descriptor
	 */
	public QuantumPlugin(IPluginDescriptor descriptor) {
		this();
	}
	/**
	 * This version is recommended for eclipse3.0 and above
	 */
	public QuantumPlugin(){ 
		super();
		plugin = this;
	}

	public static QuantumPlugin getDefault() {
		return plugin;
	}
	/**
	 * Reads the Quantum Plugin state from a file. The file has been created with writeImportantState
	 * @param target
	 */
	protected void readStateFrom(File target) {
		String fileName = target.getName();
		if (!fileName.endsWith(Messages.getString("QuantumPlugin.saveFileExtension"))){ //$NON-NLS-1$
            try {
    			// It's the 2.0 format for preferences
    			BookmarkCollection.getInstance().load(target);
            } catch (IOException e) {
                e.printStackTrace();
            }
		} else {
			//It's the 2.1 format for preferences and subsets
			FileInputStream source = null;
			try {
				source = new FileInputStream(target);  
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				return;
			}
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder parser;
			try {
				parser = factory.newDocumentBuilder();
				
                Document doc = parser.parse(source);
                
                Element root = doc.getDocumentElement();
                BookmarkCollection.getInstance().importXML(root);
                BookmarkCollection.getInstance().setChanged(false);
                SubsetContentProvider.getInstance().importXML(root);

			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#startup()
	 * This method is deprecated in Eclipse3.0 we must use start(BundleContext context): 
	 * Migration completed.
	 */
	public void startup() throws CoreException {
		super.startup();
		// We must avoid calling two times to startupMigrationTempMethod because
		// now we have a default value.
		if (!VersioningHelper.isEclipse30()) startupMigrationTempMethod(); // To be removed later
 	}
	
	/**
	 * Used during startup Eclipse3.0 compatible
	 */
	public void start(BundleContext bundleContext) throws Exception {
		super.start(bundleContext); 
		startupMigrationTempMethod(); // To be removed later
	}
	private void startupMigrationTempMethod() throws CoreException {
		// the contents of this.startup() is moved to here to avoid code duplication - see this.start(BundleContext) 
		ISavedState lastState =
			ResourcesPlugin.getWorkspace().addSaveParticipant(
				this,
				new QuantumSaveParticipant());
		File file = null;
		if (lastState != null) {
			IPath location = lastState.lookup(new Path(Messages.getString("QuantumPlugin.saveDir"))); //$NON-NLS-1$
	    	if (location != null) {
	        	file = getStateLocation().append(location).toFile();
	        }
		} else {
			// If we haven't previously saved the state, it's a new installation. 
			// We check if there is a default bookmark file.
			// operatingSystem ==> maxosx, linux, solaris, win32,... 
			String operatingSystem = Platform.getOS();
			// The file path is a preference of Quantum 
			IPreferenceStore store = getPreferenceStore(); 
			IPath path = new Path(store.getString("com.quantum.plugin.defaultBookmarksFile"));
			path = path.append("/def_bookmarks_"+ operatingSystem + ".xml");
			file = path.toFile();
			if (!file.exists()) file = null;
		}
			
    	// the plugin instance should read any important state from the file. 
    	if (file != null) readStateFrom(file);
        sysClip = new Clipboard(null);
	}

	/**
	 * Write the bookmarks and subsets to a file, saving them for next use of the quantum plugin
	 * @param target
	 */
	protected void writeImportantState(File target) {
        try {
            Document document = XMLHelper.createEmptyDocument();
            
    		Element root = (Element) document.appendChild(
                document.createElement(Messages.getString("ExportXMLAction.SavedData"))); //$NON-NLS-1$
    		
    		BookmarkCollection.getInstance().exportXML(root);
    		SubsetContentProvider.getInstance().exportXML(root);

            //FileWriter writer =  new FileWriter(target);
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(target), "UTF-8");
		    try {
                XMLHelper.write(writer, document);
    		} finally {
    			writer.close();
    		}
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	protected void initializeDefaultPluginPreferences() {
		IPreferenceStore store = getPreferenceStore(); 
		// operatingSystem ==> maxosx, linux, solaris, win32,... 
		String operatingSystem = Platform.getOS(); 
		// Apply the defaults to the preferences 
		PluginPreferences.initialize(store);
		// If we have defined a "prefs" directory with a different defaults file for our operating system, we apply them
		try { 
			InputStream is = getDefault().openStream(new Path("prefs/default_" + operatingSystem + ".properties")); 
			PropertyResourceBundle resourceBundle = new PropertyResourceBundle(is); 
			Enumeration keysEnum = resourceBundle.getKeys(); 
			String key; 
			while (keysEnum.hasMoreElements()) { 
				key = (String)keysEnum.nextElement(); 
				store.setDefault(key, resourceBundle.getString( key )); 
			} 
		} catch (Exception e) { 
			// Left intentionally blank. If the stream does not exist, it will simply go on.
		}
		
	}
	
	// Returns the active page
	public IWorkbenchPage getActivePage()
	{
		IWorkbench workbench = getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		if (window == null)	return null;
		IWorkbenchPage page = window.getActivePage();
		return page;
	}
	/**
	 * returns a view in the active page, creating it if needed
	 * @param view, the name of the view (e.g com.quantum.view.tableview)
	 * @return true if successful, false if not
	 */
	public IViewPart getView(String view)
	{
		IViewPart tableView = null;
		try {
			IWorkbenchPage page = QuantumPlugin.getDefault().getActivePage();
			tableView =  page.findView(view);
			if (tableView == null){
				// showView will give focus to the created view, we don't want that
				// so we save the active part
				IWorkbenchPart part = page.getActivePart();
				tableView = page.showView(view);
				// and return the focus to it
				page.activate(part);
			}
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		return tableView;
	}



	/**
	 * @return
	 */
	public Clipboard getSysClip() {
		return sysClip;
	}
	protected void initializeImageRegistry(ImageRegistry registry) {
		super.initializeImageRegistry(registry);
		try {
			ImageStore.initialize(this, registry, getIconLocation());
		} catch (MalformedURLException e) {
			// this should never happen, but if it does, we don't get images.
		}
	}

	/**
	 * @return
	 * @throws MalformedURLException
	 */
	private URL getIconLocation() throws MalformedURLException {
		URL installURL = getBundle().getEntry("/");
		return new URL(installURL, "icons/");
	}
}