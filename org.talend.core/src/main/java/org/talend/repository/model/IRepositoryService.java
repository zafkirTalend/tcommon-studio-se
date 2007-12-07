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
package org.talend.repository.model;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.talend.core.IService;
import org.talend.core.model.components.IComponentsFactory;
import org.talend.core.model.properties.Property;
import org.talend.core.model.properties.User;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.repository.IRepositoryChangedListener;
import org.talend.repository.RepositoryElementDelta;

/**
 * DOC qian class global comment. Interface for RepositoryService. <br/>
 * 
 * $Id: talend-code-templates.xml 1 2006-09-29 17:06:40 +0000 (星期五, 29 九月 2006) nrousseau $
 * 
 */
public interface IRepositoryService extends IService {

    public IComponentsFactory getComponentsFactory();

    public IPath getPathFileName(String folderName, String fileName);

    public IProxyRepositoryFactory getProxyRepositoryFactory();

    
    public IPath getRepositoryPath(RepositoryNode node);

    public void registerRepositoryChangedListener(IRepositoryChangedListener listener);

    public void removeRepositoryChangedListener(IRepositoryChangedListener listener);

    public void repositoryChanged(RepositoryElementDelta event);

    public void notifySQLBuilder(List<IRepositoryObject> list);

    public String validateColumnName(String columnName, int index);

    /**
     * qzhang Comment method "registerRepositoryChangedListenerAsFirst".
     * 
     * @param view
     */
    public void registerRepositoryChangedListenerAsFirst(IRepositoryChangedListener listener);

    public WizardDialog getGenericSchemaWizardDialog(Shell shell, IWorkbench workbench, boolean creation, ISelection selection,
            String[] existingNames, boolean isSinglePageOnly);

    public Property getPropertyFromWizardDialog();

    public IPath getPathForSaveAsGenericSchema();

    // for integration with eclipse
    public void openLoginDialog();

    /**
     * initialize before running of job that extends the "IStartup" extension.
     */
    public void initializeForTalendStartupJob();

    public void initializeTalend();

    public boolean isRCPMode();

    public void setRCPMode();

    public void setPartListener(boolean isReuqired);

    public boolean needSetPartListener();
}
