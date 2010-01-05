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
package org.talend.repository.mdm.action;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.talend.commons.ui.image.ImageProvider;
import org.talend.core.model.metadata.builder.connection.ConnectionFactory;
import org.talend.core.model.metadata.builder.connection.MDMConnection;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.MDMConnectionItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.RepositoryManager;
import org.talend.core.ui.images.ECoreImage;
import org.talend.core.ui.images.OverlayImageProvider;
import org.talend.repository.ProjectManager;
import org.talend.repository.mdm.ui.wizard.MDMWizard;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.ProxyRepositoryFactory;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.model.RepositoryNodeUtilities;
import org.talend.repository.model.RepositoryNode.EProperties;
import org.talend.repository.ui.actions.metadata.AbstractCreateAction;

/**
 * DOC hwang class global comment. Detailled comment
 */
public class CreateMDMConnectionAction extends AbstractCreateAction {

    // protected static Logger log = Logger.getLogger(CreateMDMConnectionAction.class);

    private static final String EDIT_LABEL = "Edit MDM";

    private static final String OPEN_LABEL = "Open MDM";

    private static final String CREATE_LABEL = "Create MDM";

    ImageDescriptor defaultImage = ImageProvider.getImageDesc(ECoreImage.METADATA_MDM_CONNECTION_ICON);

    ImageDescriptor createImage = OverlayImageProvider.getImageWithNew(ImageProvider
            .getImage(ECoreImage.METADATA_MDM_CONNECTION_ICON));

    public CreateMDMConnectionAction() {
        super();

        this.setText(CREATE_LABEL);
        this.setToolTipText(CREATE_LABEL);
        this.setImageDescriptor(defaultImage);
    }

    public CreateMDMConnectionAction(boolean isToolbar) {
        super();
        setToolbar(isToolbar);
        this.setText(CREATE_LABEL);
        this.setToolTipText(CREATE_LABEL);
        this.setImageDescriptor(defaultImage);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.repository.ui.actions.metadata.AbstractCreateAction#init(org.talend.repository.model.RepositoryNode)
     */
    @Override
    protected void init(RepositoryNode node) {
        ERepositoryObjectType nodeType = (ERepositoryObjectType) node.getProperties(EProperties.CONTENT_TYPE);
        if (!ERepositoryObjectType.METADATA_MDMCONNECTION.equals(nodeType)) {
            setEnabled(false);
            return;
        }
        IProxyRepositoryFactory factory = ProxyRepositoryFactory.getInstance();
        switch (node.getType()) {
        case SIMPLE_FOLDER:
        case SYSTEM_FOLDER:
            if (factory.isUserReadOnlyOnCurrentProject() || !ProjectManager.getInstance().isInCurrentMainProject(node)) {
                setEnabled(false);
                return;
            }
            this.setText(CREATE_LABEL);
            this.setImageDescriptor(createImage);
            collectChildNames(node);
            break;
        case REPOSITORY_ELEMENT:
            if (factory.isPotentiallyEditable(node.getObject())) {
                this.setText(EDIT_LABEL);
                this.setImageDescriptor(defaultImage);
                collectSiblingNames(node);
            } else {
                this.setText(OPEN_LABEL);
                this.setImageDescriptor(defaultImage);
            }
            break;
        default:
            return;
        }
        setEnabled(true);

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.actions.AContextualAction#doRun()
     */
    @Override
    protected void doRun() {
        RepositoryNode dbConnectionNode = getCurrentRepositoryNode();

        if (isToolbar()) {
            if (dbConnectionNode != null && dbConnectionNode.getContentType() != ERepositoryObjectType.METADATA_MDMCONNECTION) {
                dbConnectionNode = null;
            }
            if (dbConnectionNode == null) {
                dbConnectionNode = getRepositoryNodeForDefault(ERepositoryObjectType.METADATA_MDMCONNECTION);
            }
        }
        RepositoryNode metadataNode = dbConnectionNode.getParent();
        if (metadataNode != null) {
            // Force focus to the repositoryView and open Metadata and DbConnection nodes
            getViewPart().setFocus();
            getViewPart().expand(metadataNode, true);
            getViewPart().expand(dbConnectionNode, true);
        }

        MDMConnection connection = null;
        IPath pathToSave = null;

        // Define the RepositoryNode, by default Metadata/DbConnection
        RepositoryNode node = dbConnectionNode;
        ISelection selection = null;
        // When the userSelection is an element of metadataNode, use it !
        if (!isToolbar()) {
            Object userSelection = ((IStructuredSelection) getSelection()).getFirstElement();
            if (userSelection instanceof RepositoryNode) {
                switch (((RepositoryNode) userSelection).getType()) {
                case REPOSITORY_ELEMENT:
                case SIMPLE_FOLDER:
                case SYSTEM_FOLDER:
                    node = (RepositoryNode) userSelection;
                    break;
                }
            }
            selection = getSelection();
        }
        boolean creation = false;
        // Define the repositoryObject DatabaseConnection and his pathToSave
        switch (node.getType()) {
        case REPOSITORY_ELEMENT:
            // pathToSave = null;
            connection = (MDMConnection) ((ConnectionItem) node.getObject().getProperty().getItem()).getConnection();
            creation = false;
            break;
        case SIMPLE_FOLDER:
            pathToSave = RepositoryNodeUtilities.getPath(node);
            connection = ConnectionFactory.eINSTANCE.createMDMConnection();
            creation = true;
            break;
        case SYSTEM_FOLDER:
            pathToSave = new Path(""); //$NON-NLS-1$
            connection = ConnectionFactory.eINSTANCE.createMDMConnection();
            creation = true;
            break;
        }

        // Init the content of the Wizard
        init(node);
        MDMWizard mdmWizard;
        if (isToolbar()) {
            mdmWizard = new MDMWizard(PlatformUI.getWorkbench(), creation, node, getExistingNames());
            mdmWizard.setToolBar(true);
        } else {
            mdmWizard = new MDMWizard(PlatformUI.getWorkbench(), creation, selection, getExistingNames());
        }

        // Open the Wizard
        WizardDialog wizardDialog = new WizardDialog(Display.getCurrent().getActiveShell(), mdmWizard);
        wizardDialog.setPageSize(600, 480);
        wizardDialog.create();
        wizardDialog.open();

        RepositoryManager.getRepositoryView().expand(dbConnectionNode, true);

        RepositoryManager.refreshCreatedNode(ERepositoryObjectType.METADATA_MDMCONNECTION);

    }

    @Override
    public Class getClassForDoubleClick() {
        return MDMConnectionItem.class;
    }

}
