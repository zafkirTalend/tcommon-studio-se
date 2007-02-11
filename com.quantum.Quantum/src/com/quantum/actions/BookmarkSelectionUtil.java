package com.quantum.actions;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.quantum.QuantumPlugin;
import com.quantum.model.Bookmark;
import com.quantum.model.BookmarkCollection;


/**
 * @author BC Holmes
 */
public class BookmarkSelectionUtil {
	
	private static BookmarkSelectionUtil instance = new BookmarkSelectionUtil();
	
	private static final String OLD_LAST_USED_BOOKMARK_PREFERENCE = 
			ExecuteAction.class.getName() + ".bookmark";
	private static final String LAST_USED_BOOKMARK_PREFERENCE = 
			BookmarkSelectionUtil.class.getName() + ".bookmark";
	
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	
	public static BookmarkSelectionUtil getInstance() {
		return BookmarkSelectionUtil.instance;
	}
	
	public void setLastUsedBookmark(Bookmark bookmark) {
	    String lastUsedBookmarkName = getLastUsedBookmarkName();
	    
		QuantumPlugin.getDefault().getPreferenceStore().setValue(
				LAST_USED_BOOKMARK_PREFERENCE, bookmark.getName());
	    if ((lastUsedBookmarkName == null && bookmark != null)
	            || (lastUsedBookmarkName != null && bookmark != null 
	                    && !lastUsedBookmarkName.equals(bookmark.getName()))) {
	        this.propertyChangeSupport.firePropertyChange(
	                "lastUsedBookmark", null, bookmark);
	    }
	}

	/**
	 * @return
	 */
	public Bookmark getLastUsedBookmark() {
		String lastUsedName = getLastUsedBookmarkName();
		return lastUsedName == null || lastUsedName.trim().length() == 0
				? null 
				: BookmarkCollection.getInstance().find(lastUsedName);
	}

	/**
     * @return
     */
    private String getLastUsedBookmarkName() {
        String lastUsedName1 = QuantumPlugin.getDefault().getPreferenceStore().getString(
		        LAST_USED_BOOKMARK_PREFERENCE);
        String lastUsedName = lastUsedName1;
		
		if (lastUsedName == null || lastUsedName.trim().length() == 0) {
			lastUsedName = getLastUsedBookmarkFromOlderVersionOfQuantum();
		}
        return lastUsedName;
    }

    /**
	 * @param lastUsedName
	 * @return
	 */
	private String getLastUsedBookmarkFromOlderVersionOfQuantum() {
		String lastUsedName = QuantumPlugin.getDefault().getPreferenceStore().getString(
				OLD_LAST_USED_BOOKMARK_PREFERENCE);
		if (lastUsedName != null && lastUsedName.trim().length() > 0) {
			QuantumPlugin.getDefault().getPreferenceStore().setValue(
					LAST_USED_BOOKMARK_PREFERENCE, lastUsedName);
			QuantumPlugin.getDefault().getPreferenceStore().setToDefault(
					OLD_LAST_USED_BOOKMARK_PREFERENCE);
		}
		return lastUsedName;
	}
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
