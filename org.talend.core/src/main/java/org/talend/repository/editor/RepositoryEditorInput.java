// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.part.FileEditorInput;
import org.talend.core.model.properties.Item;

/**
 * DOC mhelleboid class global comment. Detailled comment <br/>
 * 
 * $Id: RepositoryEditorInput.java 1 2006-09-29 17:06:40 +0000 (星期五, 29 九月 2006) nrousseau $
 * 
 */
public class RepositoryEditorInput extends FileEditorInput {

    private Item item;

    private boolean readOnly;

    public RepositoryEditorInput(IFile file, Item item) {
        super(file);
        this.item = item;
    }

    public String getName() {
        // PTODO mhelleboid use RepositoryLabelProvider when ready
        return "Model " + item.getProperty().getLabel(); //$NON-NLS-1$
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public boolean isReadOnly() {
        return this.readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RepositoryEditorInput other = (RepositoryEditorInput) obj;
        if (this.item == null) {
            if (other.item != null) {
                return false;
            }
        } else if (!this.item.getProperty().getId().equals(other.item.getProperty().getId())) {
            return false;
        } else if (!this.item.getProperty().getVersion().equals(other.item.getProperty().getVersion())) {
            return false;
        }
        return true;
    }
}
