// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.swt.dialogs.ErrorDialogWidthDetailArea;
import org.talend.commons.utils.VersionUtils;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.ITDQRepositoryService;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.model.metadata.IMetadataConnection;
import org.talend.core.model.metadata.builder.ConvertionHelper;
import org.talend.core.model.metadata.builder.connection.ConnectionFactory;
import org.talend.core.model.metadata.builder.connection.MDMConnection;
import org.talend.core.model.metadata.builder.util.MetadataConnectionUtils;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.MDMConnectionItem;
import org.talend.core.model.properties.PropertiesFactory;
import org.talend.core.model.properties.Property;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.core.model.update.RepositoryUpdateManager;
import org.talend.core.repository.model.ProxyRepositoryFactory;
import org.talend.core.runtime.CoreRuntimePlugin;
import org.talend.repository.mdm.i18n.Messages;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.model.RepositoryNodeUtilities;
import org.talend.repository.ui.utils.ConnectionContextHelper;
import org.talend.repository.ui.wizards.PropertiesWizardPage;
import org.talend.repository.ui.wizards.RepositoryWizard;

/**
 * DOC hwang class global comment. Detailled comment
 */
public class MDMWizard extends RepositoryWizard implements INewWizard {

    private PropertiesWizardPage propertiesWizardPage;

    private MDMWizardPage mdmWizardPage;

    private UniversePage universePage;

    private MDMConnection connection;

    private Property connectionProperty;

    private ConnectionItem connectionItem;

    private boolean isToolBar;

    private RepositoryNode node;

    private WizardPage currentPage;
    
    private String originaleObjectLabel;

    private String originalVersion;

    private String originalPurpose;

    private String originalDescription;

    private String originalStatus;

    private IMetadataConnection iMetadataConn;

    /**
     * DOC hwang MDMWizard constructor comment.
     * 
     * @param workbench
     * @param creation
     */
    public MDMWizard(IWorkbench workbench, boolean creation, RepositoryNode node, String[] existingNames) {
        super(workbench, creation);
        this.existingNames = existingNames;
        this.node = node;
        setNeedsProgressMonitor(true);
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
            connectionProperty.setAuthor(((RepositoryContext) CoreRuntimePlugin.getInstance().getContext()
                    .getProperty(Context.REPOSITORY_CONTEXT_KEY)).getUser());
            connectionProperty.setVersion(VersionUtils.DEFAULT_VERSION);
            connectionProperty.setStatusCode(""); //$NON-NLS-1$

            connectionItem = PropertiesFactory.eINSTANCE.createMDMConnectionItem();
            connectionItem.setProperty(connectionProperty);
            connectionItem.setConnection(connection);
            break;

        case REPOSITORY_ELEMENT:
            connection = (MDMConnection) ((ConnectionItem) node.getObject().getProperty().getItem()).getConnection();
            connectionProperty = node.getObject().getProperty();
            connectionItem = (ConnectionItem) node.getObject().getProperty().getItem();
            // set the repositoryObject, lock and set isRepositoryObjectEditable
            setRepositoryObject(node.getObject());
            isRepositoryObjectEditable();
            initLockStrategy();
            break;
        }
        if (!creation) {
            this.originaleObjectLabel = this.connectionItem.getProperty().getLabel();
            this.originalVersion = this.connectionItem.getProperty().getVersion();
            this.originalDescription = this.connectionItem.getProperty().getDescription();
            this.originalPurpose = this.connectionItem.getProperty().getPurpose();
            this.originalStatus = this.connectionItem.getProperty().getStatusCode();
            this.iMetadataConn = ConvertionHelper.convert(((MDMConnectionItem) this.connectionItem).getConnection());
        }
        // initialize the context mode
        ConnectionContextHelper.checkContextMode(connectionItem);
    }

    public MDMWizard(IWorkbench workbench, boolean creation, ISelection selection, String[] existingNames) {
        super(workbench, creation);
        this.selection = selection;
        this.existingNames = existingNames;
        setNeedsProgressMonitor(true);

        Object obj = ((IStructuredSelection) selection).getFirstElement();
        node = (RepositoryNode) obj;
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
            connectionProperty.setAuthor(((RepositoryContext) CoreRuntimePlugin.getInstance().getContext()
                    .getProperty(Context.REPOSITORY_CONTEXT_KEY)).getUser());
            connectionProperty.setVersion(VersionUtils.DEFAULT_VERSION);
            connectionProperty.setStatusCode(""); //$NON-NLS-1$

            connectionItem = PropertiesFactory.eINSTANCE.createMDMConnectionItem();
            connectionItem.setProperty(connectionProperty);
            connectionItem.setConnection(connection);
            break;

        case REPOSITORY_ELEMENT:
            connection = (MDMConnection) ((ConnectionItem) node.getObject().getProperty().getItem()).getConnection();
            connectionProperty = node.getObject().getProperty();
            connectionItem = (ConnectionItem) node.getObject().getProperty().getItem();
            // set the repositoryObject, lock and set isRepositoryObjectEditable
            setRepositoryObject(node.getObject());
            isRepositoryObjectEditable();
            initLockStrategy();
            break;
        }
        if (!creation) {
            this.originaleObjectLabel = this.connectionItem.getProperty().getLabel();
            this.originalVersion = this.connectionItem.getProperty().getVersion();
            this.originalDescription = this.connectionItem.getProperty().getDescription();
            this.originalPurpose = this.connectionItem.getProperty().getPurpose();
            this.originalStatus = this.connectionItem.getProperty().getStatusCode();
            this.iMetadataConn = ConvertionHelper.convert(((MDMConnectionItem) this.connectionItem).getConnection());
        }
        // initialize the context mode
        ConnectionContextHelper.checkContextMode(connectionItem);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        // if (mdmWizardPage.isPageComplete()) {
        IProxyRepositoryFactory factory = ProxyRepositoryFactory.getInstance();

        ITDQRepositoryService tdqRepService = null;

        if (GlobalServiceRegister.getDefault().isServiceRegistered(ITDQRepositoryService.class)) {
            tdqRepService = (ITDQRepositoryService) GlobalServiceRegister.getDefault().getService(ITDQRepositoryService.class);
        }

        try {
            if (creation) {
                String nextId = factory.getNextId();
                connectionProperty.setId(nextId);
                factory.create(connectionItem, propertiesWizardPage.getDestinationPath());

                // feature 17159
                if (tdqRepService != null) {
                    tdqRepService.fillMetadata(connectionItem);
                }
            } else {
                RepositoryUpdateManager.updateFileConnection(connectionItem);
                factory.save(connectionItem);
                closeLockStrategy();
            }
        } catch (PersistenceException e) {
            String detailError = e.toString();
            new ErrorDialogWidthDetailArea(getShell(), PID, "", //$NON-NLS-1$
                    detailError);
            //                log.error(Messages.getString("CommonWizard.persistenceException") + "\n" + detailError); //$NON-NLS-1$ //$NON-NLS-2$
            return false;
        }
        List<IRepositoryViewObject> list = new ArrayList<IRepositoryViewObject>();
        list.add(repositoryObject);

        if (tdqRepService != null) {
            if (CoreRuntimePlugin.getInstance().isDataProfilePerspectiveSelected()) {
                tdqRepService.openEditor(connectionItem);
                tdqRepService.refresh();
            }
        }

        return true;
    }

    @Override
    public void createPageControls(Composite pageContainer) {
        // do nothing to create pages lazily
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
     * org.eclipse.jface.viewers.IStructuredSelection)
     */
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addPages() {
        setWindowTitle("");//$NON-NLS-1$
        // setDefaultPageImageDescriptor(ImageProvider.getImageDesc(ECoreImage.METADATA_MDM_CONNECTION_WIZ));
        if (isToolBar) {
            pathToSave = null;
        }
        propertiesWizardPage = new MdmStep0WizardPage(connectionProperty, pathToSave,
                ERepositoryObjectType.METADATA_MDMCONNECTION, !isRepositoryObjectEditable(), creation);
        propertiesWizardPage.setTitle("Talend MDM"); //$NON-NLS-1$
        propertiesWizardPage.setDescription(Messages.getString("MDMWizard_create_mdm_conn")); //$NON-NLS-1$
        mdmWizardPage = new MDMWizardPage(connectionItem, isRepositoryObjectEditable(), existingNames);
        universePage = new UniversePage(connectionItem, "Talend MDM"); //$NON-NLS-1$
        addPage(propertiesWizardPage);
        addPage(mdmWizardPage);
        addPage(universePage);
    }

    public void setToolBar(boolean isToolbar) {
        this.isToolBar = isToolbar;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.wizards.RepositoryWizard#getConnectionItem()
     */
    @Override
    public ConnectionItem getConnectionItem() {
        return this.connectionItem;
    }
    @Override
    public boolean performCancel() {
        if (!creation) {
            connectionItem.getProperty().setVersion(this.originalVersion);
            connectionItem.getProperty().setLabel(this.originaleObjectLabel);
            connectionItem.getProperty().setDescription(this.originalDescription);
            connectionItem.getProperty().setPurpose(this.originalPurpose);
            connectionItem.getProperty().setStatusCode(this.originalStatus);
            MetadataConnectionUtils.setMDMConnectionParameter(connection, iMetadataConn);
        }
        return super.performCancel();
    }

    @Override
    public boolean canFinish() {
        if (currentPage instanceof UniversePage && currentPage.isPageComplete()) {
            return true;
        }
        return false;
    }

    public void setCurrentPage(WizardPage currentPage) {
        this.currentPage = currentPage;
    }
}
