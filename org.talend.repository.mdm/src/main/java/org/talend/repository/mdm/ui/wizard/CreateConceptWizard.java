// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.mdm.ui.wizard;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.swt.dialogs.ErrorDialogWidthDetailArea;
import org.talend.commons.utils.VersionUtils;
import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.model.metadata.builder.connection.ConnectionFactory;
import org.talend.core.model.metadata.builder.connection.MDMConnection;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.MDMConnectionItem;
import org.talend.core.model.properties.PropertiesFactory;
import org.talend.core.model.properties.Property;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.designer.core.DesignerPlugin;
import org.talend.repository.RepositoryPlugin;
import org.talend.repository.i18n.Messages;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.IRepositoryService;
import org.talend.repository.model.MetadataTableRepositoryObject;
import org.talend.repository.model.ProxyRepositoryFactory;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.model.RepositoryNodeUtilities;
import org.talend.repository.ui.utils.ConnectionContextHelper;
import org.talend.repository.ui.wizards.RepositoryWizard;

/**
 * DOC hwang class global comment. Detailled comment
 */
public class CreateConceptWizard extends RepositoryWizard implements INewWizard {

    private MDMSchemaWizardPage schemaPage;

    private SetConceptNamePage step0Page;

    private MDMTablePage tablePage;

    private ConnectionItem connectionItem;

    private MDMConnection connection;

    private Property connectionProperty;

    protected IRepositoryObject repositoryObject = null;

    private Property property;

    private RepositoryNode node;

    private IPath path;

    private String[] existNames;

    private boolean readonly;

    /**
     * DOC Administrator CreateConceptWizard constructor comment.
     * 
     * @param workbench
     * @param creation
     */
    public CreateConceptWizard(IWorkbench workbench, boolean creation, String[] existNames, RepositoryNode node,
            ConnectionItem connectionItem, boolean readonly) {
        super(workbench, creation, readonly);
        this.connectionItem = connectionItem;
        this.node = node;
        this.readonly = readonly;
        this.repositoryObject = node.getObject();
        this.existNames = existNames;
        switch (node.getType()) {
        case SIMPLE_FOLDER:
        case REPOSITORY_ELEMENT:
            pathToSave = RepositoryNodeUtilities.getPath(node);
            break;
        case SYSTEM_FOLDER:
            pathToSave = new Path(""); //$NON-NLS-1$
            break;
        }

        switch (node.getType()) {
        case SIMPLE_FOLDER:
        case SYSTEM_FOLDER:
            connection = ConnectionFactory.eINSTANCE.createMDMConnection();
            connectionProperty = PropertiesFactory.eINSTANCE.createProperty();
            connectionProperty
                    .setAuthor(((RepositoryContext) CorePlugin.getContext().getProperty(Context.REPOSITORY_CONTEXT_KEY))
                            .getUser());
            connectionProperty.setVersion(VersionUtils.DEFAULT_VERSION);
            connectionProperty.setStatusCode(""); //$NON-NLS-1$

            connectionItem = PropertiesFactory.eINSTANCE.createMDMConnectionItem();
            connectionItem.setProperty(connectionProperty);
            connectionItem.setConnection(connection);
            break;

        case REPOSITORY_ELEMENT:
            connection = (MDMConnection) ((ConnectionItem) node.getObject().getProperty().getItem()).getConnection();
            connectionProperty = node.getObject().getProperty();
            initTalendProperty();
            connectionItem = (ConnectionItem) node.getObject().getProperty().getItem();
            // set the repositoryObject, lock and set isRepositoryObjectEditable
            setRepositoryObject(node.getObject());
            isRepositoryObjectEditable();
            initLockStrategy();
            break;
        }
        // initialize the context mode
        ConnectionContextHelper.checkContextMode(connectionItem);
    }

    private void initTalendProperty() {
        this.property = PropertiesFactory.eINSTANCE.createProperty();
        this.property.setAuthor(((RepositoryContext) CorePlugin.getContext().getProperty(Context.REPOSITORY_CONTEXT_KEY))
                .getUser());
        this.property.setVersion(VersionUtils.DEFAULT_VERSION);
        this.property.setStatusCode(""); //$NON-NLS-1$
    }

    @Override
    public void addPages() {
        if (creation) {
            step0Page = new SetConceptNamePage(node, connectionItem, isRepositoryObjectEditable(), existNames);
            schemaPage = new MDMSchemaWizardPage(connectionItem, "");
            addPage(step0Page);
            addPage(schemaPage);
        } else {
            if (node.getObject() instanceof MetadataTableRepositoryObject) {
                MetadataTable table = (MetadataTable) ((MetadataTableRepositoryObject) node.getObject()).getTable();
                tablePage = new MDMTablePage(connectionItem, table, isRepositoryObjectEditable());
                addPage(tablePage);
            }

        }

    }

    @Override
    public IWizardPage getNextPage(IWizardPage page) {
        if (page instanceof SetConceptNamePage) {
            String contentName = page.getName();
            if (schemaPage != null) {
                schemaPage.setConceptName(contentName);
            }
        }
        return super.getNextPage(page);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        IPath sasPath = new Path(System.getProperty("user.dir")).append("temp");//$NON-NLS-1$ //$NON-NLS-2$
        File sasDir = sasPath.toFile();
        if (sasDir.exists()) {
            delete(sasDir);
        }
        if (creation && schemaPage.isPageComplete()) {
            // RepositoryUpdateManager.updateMultiSchema(connectionItem, oldMetadataTable, oldTableMap);
            schemaPage.createMetadataTable();
            saveMetaData();
            closeLockStrategy();

            List<IRepositoryObject> list = new ArrayList<IRepositoryObject>();
            list.add(repositoryObject);
            RepositoryPlugin.getDefault().getRepositoryService().notifySQLBuilder(list);
            return true;
        } else if (!creation && tablePage.isPageComplete()) {
            saveMetaData();
            closeLockStrategy();

            List<IRepositoryObject> list = new ArrayList<IRepositoryObject>();
            list.add(repositoryObject);
            RepositoryPlugin.getDefault().getRepositoryService().notifySQLBuilder(list);
            return true;
        }
        return false;
    }

    private void saveMetaData() {
        IProxyRepositoryFactory factory = ProxyRepositoryFactory.getInstance();
        try {
            MDMConnectionItem item = (MDMConnectionItem) repositoryObject.getProperty().getItem();
            factory.save(item);
        } catch (PersistenceException e) {
            String detailError = e.toString();
            new ErrorDialogWidthDetailArea(getShell(), PID, Messages.getString("CommonWizard.persistenceException"), detailError); //$NON-NLS-1$
            log.error(Messages.getString("CommonWizard.persistenceException") + "\n" + detailError); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    private void delete(File pathFile) {
        if (!pathFile.isDirectory()) {
            return;
        }
        File[] subFile = pathFile.listFiles();
        if (subFile != null && subFile.length > 0) {
            for (File file : subFile) {
                if (file.isDirectory()) {
                    delete(file);
                } else {
                    file.delete();
                }
            }
        }
        pathFile.delete();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
     * org.eclipse.jface.viewers.IStructuredSelection)
     */
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        IRepositoryService service = DesignerPlugin.getDefault().getRepositoryService();
        path = service.getRepositoryPath(node);
    }

}
