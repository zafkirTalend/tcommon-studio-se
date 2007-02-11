package com.quantum.properties;

import com.quantum.model.Bookmark;
import com.quantum.model.BookmarkHolder;
import com.quantum.view.SchemaSelectionControl;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.dialogs.PropertyPage;

public class SchemaPropertyPage extends PropertyPage {
	
	private SchemaSelectionControl control;
    
    protected Control createContents(Composite parent) {
    	return this.control = new SchemaSelectionControl(parent, getBookmark());
    }

    private Bookmark getBookmark() {
        Bookmark bookmark =
            ((BookmarkHolder) getElement()).getBookmark();
        return bookmark;
    }

    /**
     * @see org.eclipse.jface.preference.PreferencePage#performApply()
     */
    public boolean performOk() {
        Bookmark bookmark = getBookmark();
    	bookmark.setSchemaSelections(this.control.getSchemas());
    	bookmark.setSchemaRule(this.control.getSchemaRule());
        return true;
    }
    
    protected void performDefaults() {
        super.performDefaults();
        Bookmark bookmark = getBookmark();
        this.control.setSchemaRule(bookmark.getSchemaRule());
        this.control.setSchemas(bookmark.getSchemaSelections());
    }
}