package com.quantum;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.quantum.util.versioning.VersioningHelper;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;



/**
 * @author BC
 */
public class ImageStore {
	
	private static final Map imageDescriptorMap = Collections.synchronizedMap(new HashMap());
	
	private static final String VERSION_2 = "_2x";
	private static final String GIF_FILE_EXTENSION = ".gif";
	private static final String JPG_FILE_EXTENSION = ".jpg";
	
	public static final String ADD = "add";
	public static final String ADD_SCHEMA = "add_schema";
	public static final String APPEND = "append";
	public static final String AUTOCOMMIT = "autocommit";
	public static final String BEANSHELL = "beanshell";
	public static final String BOOKMARK = "bookmarks";
	public static final String BOOKMARKFILE = "bookmarkfile";
	public static final String CLASS = "class";
	public static final String CLEAR = "clear";
	public static final String CLOSE = "close";
	public static final String CLOSE_DISABLED = "close_disabled";
	public static final String CLOSE_ALL = "close_all";
	public static final String CLOSE_ALL_DISABLED = "close_all_disabled";
	public static final String COLLAPSE_ALL = "collapse_all";
    public static final String COLUMN = "column";
    public static final String COLUMNSELECTED = "columnselected";
	public static final String CONNECT = "connect";
	public static final String CONNECTED = "connected";
	public static final String COPY = "copy";
	public static final String CUT = "cut";
	public static final String DELETE = "delete";
	public static final String DISCONNECT = "disconnect";
	public static final String DRIVER = "driver";
	public static final String EDIT = "edit";
	public static final String ENTITYGROUP = "entitygroup";
	public static final String EXPORT = "export";
	public static final String EXTERNAL_JAR = "external_jar";
	public static final String FILTER = "filter";
    public static final String FOREIGNKEY = "foreignkey";
    public static final String FOREIGNKEYSELECTED = "foreignkeyselected";
	public static final String FULLDATA = "fulldata";
	public static final String GRID = "grid";
	public static final String GROUP = "group";
	public static final String IMPORT = "import";
	public static final String JAVA_CUP = "javacup";
	public static final String KEY = "key";
    public static final String KEYCOLUMN = "keycolumn";
    public static final String KEYCOLUMNSELECTED = "keycolumnselected";
	public static final String LOG = "log";
	public static final String MISSINGSCHEMA = "missingschema";
	public static final String MISSINGTABLE = "table";
	public static final String NEXT = "next";
	public static final String NEW_BOOKMARK = "new_bookmark";
	public static final String NEW_BOOKMARK_WIZARD = "new_bookmark_wizard";
	public static final String OPEN_TABLE = "table";
	public static final String PASTE = "paste";
	public static final String PLAY = "play";
	public static final String PREVIOUS = "previous";
	public static final String QMODEL = "qmodel";
	public static final String QUANTUM = "quantum";
    public static final String REFRESH = "refresh";
    public static final String RELATIONSHIPSON = "relationshipson";
    public static final String RELATIONSHIPSOFF = "relationshipsoff";
	public static final String ROLLBACK = "rollback";
	public static final String SAMPLE = "sample";
	public static final String SCHEMA = "schema";
	public static final String SCRIPT = "script";
	public static final String SEQUENCE = "sequence";
	public static final String STATEMENT = "statement";
	public static final String STOP = "stop";
	public static final String SUBSET = "subset";
	public static final String SUCCESS = "success";
	public static final String TABLE = "bigtable";
	public static final String SYNONYM_TABLE = "big_syn_table";
	public static final String TABLE_DETAILS = "table_details";
    public static final String TARGET_COLUMN= "targetColumn";
	public static final String TEMPLATE = "template";
	public static final String TRIGGER = "trigger";
	public static final String USER = "user";
	public static final String VIEW = "view";
	public static final String WARNING = "warning";
	public static final String XML = "xml";
	public static final String CHECKED = "checked";
	public static final String UNCHECKED = "unchecked";
	public static final String SEARCH = "search_results_view";
	public static final String VALUE = "value";
	public static final String PROCEDURE = "procedure";
	public static final String ALL_PROCEDURES = "all_procedures";
	
	
	
	public static ImageDescriptor getImageDescriptor(String imageName) {
		return getImageDescriptor(imageName, QuantumPlugin.getDefault());
	}

	public static Image getImage(String imageName) {
		return getImage(imageName, QuantumPlugin.getDefault());
	}

	/**
	 * @param imageName
	 * @param default1
	 * @return
	 */
	public static Image getImage(String imageName, AbstractUIPlugin plugin) {
		ImageRegistry registry = plugin.getImageRegistry();
		String key = stripSuffix(imageName);
		Image image = registry.get(getVersionAppropriateImage(key, plugin));
		return image;
	}

	public static ImageDescriptor getImageDescriptor(String imageName, AbstractUIPlugin plugin) {
		ImageRegistry registry = plugin.getImageRegistry();
		String key = stripSuffix(imageName);
		key = getVersionAppropriateImage(key, plugin);

		if (VersioningHelper.isEclipse21OrHigher()) {
			return VersioningHelper.getDescriptor(registry, key);
		} else {
			return (ImageDescriptor) getImageDescriptorMap(plugin).get(key);
		}
	}
	
	private static Map getImageDescriptorMap(AbstractUIPlugin plugin) {
		String key = plugin.getClass().getName();
		if (!imageDescriptorMap.containsKey(key)) {
			imageDescriptorMap.put(key, new HashMap());
		}
		return (Map) imageDescriptorMap.get(key);
	}

	/**
	 * @param imageName
	 * @return
	 */
	private static String stripSuffix(String imageName) {
		if (imageName.endsWith(GIF_FILE_EXTENSION)) {
			return imageName.substring(0, imageName.length() - 4);
		}else if (imageName.endsWith(JPG_FILE_EXTENSION)) {
			return imageName.substring(0, imageName.length() - 4);
		} else {
			return imageName;
		}
	}

	/**
	 * @param imageName
	 * @return
	 */
	private static String getVersionAppropriateImage(String imageName, AbstractUIPlugin plugin) {
		String version2Key = imageName + VERSION_2;
		return !VersioningHelper.isEclipse30() && getImageDescriptorMap(plugin).containsKey(version2Key) 
			? version2Key : imageName;
	}

	/**
	 * @param registry
	 * @param url
	 */
	static void initialize(AbstractUIPlugin plugin, ImageRegistry registry, URL url) {
		
		addImage(plugin, registry, url, ADD);
		addImage(plugin, registry, url, BOOKMARK);
		addImage(plugin, registry, url, DISCONNECT);
		addImage(plugin, registry, url, ADD);
		addImage(plugin, registry, url, ADD_SCHEMA);
		addImage(plugin, registry, url, APPEND);
		addImage(plugin, registry, url, AUTOCOMMIT);
		addImage(plugin, registry, url, BOOKMARK);
		addImage(plugin, registry, url, BOOKMARKFILE);
		addImage(plugin, registry, url, CLASS);
		addImage(plugin, registry, url, CLEAR);
		addImage(plugin, registry, url, CLOSE);
		addImage(plugin, registry, url, CLOSE_DISABLED);
		addImage(plugin, registry, url, CLOSE_ALL);
		addImage(plugin, registry, url, CLOSE_ALL_DISABLED);
		addImage(plugin, registry, url, COLLAPSE_ALL);
        addImage(plugin, registry, url, COLUMN);
        addImage(plugin, registry, url, COLUMNSELECTED);
		addImage(plugin, registry, url, CONNECT);
		addImage(plugin, registry, url, CONNECTED);
		addImage(plugin, registry, url, COPY);
		addImage(plugin, registry, url, CUT);
		addImage(plugin, registry, url, DELETE);
		addImage(plugin, registry, url, DISCONNECT);
		addImage(plugin, registry, url, DRIVER);
		addImage(plugin, registry, url, EDIT);
		addImage(plugin, registry, url, ENTITYGROUP);
		addImage(plugin, registry, url, EXPORT);
		addImage(plugin, registry, url, EXTERNAL_JAR);
		addImage(plugin, registry, url, FILTER);
        addImage(plugin, registry, url, FOREIGNKEY);
        addImage(plugin, registry, url, FOREIGNKEYSELECTED);
		addImage(plugin, registry, url, FULLDATA);
		addImage(plugin, registry, url, GRID);
		addImage(plugin, registry, url, GROUP);
		addImage(plugin, registry, url, IMPORT);
		addImage(plugin, registry, url, JAVA_CUP);
		addImage(plugin, registry, url, KEY);
        addImage(plugin, registry, url, KEYCOLUMN);
        addImage(plugin, registry, url, KEYCOLUMNSELECTED);
		addImage(plugin, registry, url, LOG);
		addImage(plugin, registry, url, MISSINGSCHEMA);
		addImage(plugin, registry, url, MISSINGTABLE);
		addImage(plugin, registry, url, NEXT);
		addImage(plugin, registry, url, NEW_BOOKMARK);
		addImage(plugin, registry, url, NEW_BOOKMARK_WIZARD, JPG_FILE_EXTENSION);
		addImage(plugin, registry, url, PASTE);
		addImage(plugin, registry, url, PLAY);
		addImage(plugin, registry, url, PREVIOUS);
		addImage(plugin, registry, url, QMODEL);
		addImage(plugin, registry, url, QUANTUM);
        addImage(plugin, registry, url, REFRESH);
        addImage(plugin, registry, url, RELATIONSHIPSOFF);
        addImage(plugin, registry, url, RELATIONSHIPSON);
		addImage(plugin, registry, url, ROLLBACK);
		addImage(plugin, registry, url, SAMPLE);
		addImage(plugin, registry, url, SCHEMA);
		addImage(plugin, registry, url, SCRIPT);
		addImage(plugin, registry, url, SEQUENCE);
		addImage(plugin, registry, url, STATEMENT);
		addImage(plugin, registry, url, STOP);
		addImage(plugin, registry, url, SUBSET);
		addImage(plugin, registry, url, SUCCESS);
		addImage(plugin, registry, url, TABLE);
        addImage(plugin, registry, url, TARGET_COLUMN);
		addImage(plugin, registry, url, SYNONYM_TABLE);
		addImage(plugin, registry, url, TABLE_DETAILS);
		addImage(plugin, registry, url, TEMPLATE);
		addImage(plugin, registry, url, TRIGGER);
		addImage(plugin, registry, url, USER);
		addImage(plugin, registry, url, VIEW);
		addImage(plugin, registry, url, WARNING);
		addImage(plugin, registry, url, XML);
		addImage(plugin, registry, url, CHECKED);
		addImage(plugin, registry, url, UNCHECKED);
		addImage(plugin, registry, url, SEARCH);
		addImage(plugin, registry, url, VALUE);
		addImage(plugin, registry, url, PROCEDURE);
		addImage(plugin, registry, url, ALL_PROCEDURES);
		
		
		if (!VersioningHelper.isEclipse30()) {
			addImage(plugin, registry, url, CLOSE + VERSION_2);
			addImage(plugin, registry, url, CLOSE_ALL + VERSION_2);
			addImage(plugin, registry, url, COLLAPSE_ALL + VERSION_2);
			addImage(plugin, registry, url, COPY + VERSION_2);
			addImage(plugin, registry, url, DELETE + VERSION_2);
			addImage(plugin, registry, url, EXPORT + VERSION_2);
			addImage(plugin, registry, url, EXTERNAL_JAR + VERSION_2);
			addImage(plugin, registry, url, IMPORT + VERSION_2);
			addImage(plugin, registry, url, PASTE + VERSION_2);
			addImage(plugin, registry, url, REFRESH + VERSION_2);
		}
	}
	public static void addImage(AbstractUIPlugin plugin, ImageRegistry registry, 
			URL url, String key) {
		addImage(plugin, registry, url, key, GIF_FILE_EXTENSION);
	}

	public static void addImage(AbstractUIPlugin plugin, ImageRegistry registry, 
			URL url, String key, String extension) {
		try {
			ImageDescriptor descriptor = ImageDescriptor.createFromURL(
					new URL(url, key + extension));
			getImageDescriptorMap(plugin).put(key, descriptor);
			registry.put(key, descriptor);
		} catch (MalformedURLException e) {
			// skip, but try to go on to the next one...
		}
	}
}
