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

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.part.FileEditorInput;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.MessageBoxExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.utils.data.container.Content;
import org.talend.commons.utils.data.container.ContentList;
import org.talend.commons.utils.data.container.RootContainer;
import org.talend.core.CorePlugin;
import org.talend.core.model.process.IProcess2;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.JobletProcessItem;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;
import org.talend.designer.joblet.model.JobletProcess;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.IRepositoryService;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.model.RepositoryNode.ENodeType;
import org.talend.repository.model.RepositoryNode.EProperties;
import org.talend.repository.ui.views.IRepositoryView;

/**
 * DOC mhelleboid class global comment. Detailled comment <br/>
 * 
 * $Id: RepositoryEditorInput.java 1 2006-09-29 17:06:40 +0000 (星期五, 29 九月 2006) nrousseau $
 * 
 */
public class RepositoryEditorInput extends FileEditorInput {

    private Item item;

    private boolean readOnly;

    protected IProcess2 loadedProcess;

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

    // see bug 4088. Don't override equals() to make the JavaEditor context actions not open a new JavaEditor
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return true;
        }
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

    /**
     * DOC qzhang Comment method "getLoadedProcess".
     * 
     * @return
     */
    public IProcess2 getLoadedProcess() {
        return loadedProcess;
    }

    protected Item getProcessItem() {
        return getItem();
    }

    protected void loadProcess() throws PersistenceException {
        loadedProcess.loadXmlFile();
        loadedProcess.checkLoadNodes();
    }

    public boolean saveProcess(IProgressMonitor monitor, IPath path) {
        // PTODO SML Removed null test on monitor after assure that in can't be
        try {
            if (monitor != null) {
                monitor.beginTask("save process", 100);
            }
            ProcessType processType = loadedProcess.saveXmlFile();
            if (monitor != null) {
                monitor.worked(40);
            }

            // getFile().refreshLocal(IResource.DEPTH_ONE, monitor);

            // loadedProcess.setXmlStream(getFile().getContents());

            IRepositoryService service = CorePlugin.getDefault().getRepositoryService();
            IProxyRepositoryFactory factory = service.getProxyRepositoryFactory();

            if (path != null) {
                // factory.createProcess(project, loadedProcess, path);
            } else {
                if (getProcessItem() instanceof JobletProcessItem) {
                    ((JobletProcessItem) getProcessItem()).setJobletProcess((JobletProcess) processType);
                } else {
                    ((ProcessItem) getProcessItem()).setProcess(processType);
                }
                factory.save(getProcessItem());
                if (monitor != null) {
                    monitor.worked(50);
                }
            }

            refresh();
            if (monitor != null) {
                monitor.worked(10);
            }
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            if (monitor != null) {
                monitor.setCanceled(true);
            }
            return false;
        } catch (PersistenceException e) {
            MessageBoxExceptionHandler.process(e);
            if (monitor != null) {
                monitor.setCanceled(true);
            }
            return false;
        } catch (Exception e) {
            ExceptionHandler.process(e);
            if (monitor != null) {
                monitor.setCanceled(true);
            }
            return false;
        } finally {
            if (monitor != null) {
                monitor.done();
            }
        }
    }

    private IRepositoryView view;

    /**
     * DOC smallet Comment method "refresh".
     */
    private void refresh() {
        if (view != null && repositoryNode != null) {
            view.refresh(repositoryNode);
        }
    }

    protected RepositoryNode repositoryNode;

    public void setView(IRepositoryView viewPart) {
        this.view = viewPart;
    }

    public RepositoryNode getRepositoryNode() {
        return this.repositoryNode;
    }

    /**
     * DOC mhelleboid Comment method "checkReadOnly".
     * 
     * @param processItem
     * @throws PersistenceException
     */
    protected boolean checkReadOnly() throws PersistenceException {
        return loadedProcess.checkReadOnly();
    }

    public boolean setForceReadOnly(boolean readonly) {
        if (readonly) {
            loadedProcess.setReadOnly(readonly);
            return true;
        } else {
            try {
                return checkReadOnly();
            } catch (PersistenceException e) {
                ExceptionHandler.process(e);
                return false;
            }
        }
    }

    /**
     * DOC smallet Comment method "setNode".
     * 
     * @param node
     */
    public void setRepositoryNode(RepositoryNode repositoryNode) {
        if (repositoryNode != null) {
            this.repositoryNode = repositoryNode;
        } else {
            IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
            IRepositoryObject repositoryObject = null;
            RepositoryNode parentNode = null;
            try {
                RootContainer<String, IRepositoryObject> processContainer = factory.getProcess();
                ContentList<String, IRepositoryObject> processAbsoluteMembers = processContainer.getAbsoluteMembers();

                for (Content<String, IRepositoryObject> object : processAbsoluteMembers.values()) {
                    IRepositoryObject process = (IRepositoryObject) object.getContent();
                    if (process.getLabel().equals(this.getProcessItem().getProperty().getLabel())) {
                        repositoryObject = process;
                    }
                }
            } catch (PersistenceException e) {
                e.printStackTrace();
            }
            ERepositoryObjectType itemType = ERepositoryObjectType.getItemType(this.getProcessItem());
            if (repositoryObject != null) {
                this.repositoryNode = new RepositoryNode(repositoryObject, parentNode, ENodeType.REPOSITORY_ELEMENT);
                this.repositoryNode.setProperties(EProperties.CONTENT_TYPE, itemType);
            }

        }
    }

    /**
     * Sets the loadedProcess.
     * 
     * @param loadedProcess the loadedProcess to set
     */
    public void setLoadedProcess(IProcess2 loadedProcess) {
        this.loadedProcess = loadedProcess;
    }

}
