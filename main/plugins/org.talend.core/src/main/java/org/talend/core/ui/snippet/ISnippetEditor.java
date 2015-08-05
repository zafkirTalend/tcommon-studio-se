// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================

package org.talend.core.ui.snippet;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.talend.core.model.properties.SnippetItem;

/**
 * A snippet editor is responsible for creating the interface from which a user modifies a snippet item or category.
 * 
 * This interface is not meant to be implemented by clients.
 */
public interface ISnippetEditor {

    /**
     * Adds a modify listener to this editor. Typically the UI surrounding this editor will listen for modifications.
     * 
     * @param listener the to be added
     */
    // void addModifyListener(ModifyListener listener);
    /**
     * Fill-in the contents of an editing Dialog.
     * 
     * @param parent the parent composite for the editor's control
     * @return the main control provided by the editor
     */
    Control createContents(Composite parent);

    /**
     * Get the ISnippetItem being edited.
     * 
     * @return the item being edited
     */
    SnippetItem getItem();

    /**
     * Remove a modify listener from this editor. Typically the UI surrounding this editor will listen for
     * modifications.
     * 
     * @param listener the to be added
     */
    // void removeModifyListener(ModifyListener listener);
    /**
     * Set the ISnippetItem to edit.
     * 
     * @param item the item to edit
     */
    void setItem(SnippetItem item);

    /**
     * Update the ISnippetItem being edited, usually because the values of the dialog's controls have been changed or it
     * is being closed.
     */
    void updateItem();
}
