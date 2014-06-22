// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
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
import org.talend.core.model.repository.IRepositoryEditorInput;
import org.talend.core.runtime.CoreRuntimePlugin;
import org.talend.repository.model.IRepositoryNode;
import org.talend.repository.model.RepositoryNode;

/**
 * DOC mhelleboid class global comment. Detailled comment <br/>
 * 
 * $Id: RepositoryEditorInput.java 1 2006-09-29 17:06:40 +0000 (星期五, 29 九月 2006) nrousseau $
 * 
 */
public class RepositoryEditorInput extends FileEditorInput implements IRepositoryEditorInput {

    private Item item;

    private boolean readOnly;

    private String id, version;

    private String name;

    public RepositoryEditorInput(IFile file, Item item) {
        super(file);
        this.item = item;
        this.id = item.getProperty().getId();
        this.version = item.getProperty().getVersion();
        this.name = getItem().getProperty().getLabel();
    }

    @Override
    public String getName() {
        return name;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
        if (item != null) {
            this.id = item.getProperty().getId();
            this.version = item.getProperty().getVersion();
            this.name = getItem().getProperty().getLabel();
        }
    }

    public boolean isReadOnly() {
        return this.readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RepositoryEditorInput other = (RepositoryEditorInput) obj;
        if (!this.getId().equals(other.getId())) {
            return false;
        } else if (!this.getVersion().equals(other.getVersion())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        if (getItem() != null) {
            return super.hashCode() & getItem().hashCode();
        }
        return super.hashCode();
    }

    protected RepositoryNode repositoryNode;

    public RepositoryNode getRepositoryNode() {
        return this.repositoryNode;
    }

    /**
     * DOC smallet Comment method "setNode".
     * 
     * @param node
     */
    public void setRepositoryNode(IRepositoryNode repositoryNode) {
        if (repositoryNode != null) {
            this.repositoryNode = (RepositoryNode) repositoryNode;
        } else {
            // see bug 0005256: All folders got expanded after job creation in a folder
            this.repositoryNode = (RepositoryNode) CoreRuntimePlugin.getInstance().getRepositoryService()
                    .getRepositoryNode(getItem().getProperty().getId(), false);
        }
    }

    @Override
    public Object getAdapter(Class adapter) {
        if (adapter.equals(RepositoryEditorInput.class)) {
            return this;
        }

        return super.getAdapter(adapter);
    }

    /**
     * Clear items from memory, make sure we don't keep anything in memory. Might be removed in the future if no problem
     * with memory management.
     */
    public void dispose() {
        this.repositoryNode = null;
        this.item = null;
    }

    public String getId() {
        return id;
    }

    public String getVersion() {
        return version;
    }

}
