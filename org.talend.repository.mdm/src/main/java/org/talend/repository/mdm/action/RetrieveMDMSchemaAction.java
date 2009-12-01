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

import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;
import org.talend.commons.ui.image.ImageProvider;
import org.talend.core.model.metadata.builder.connection.MDMConnection;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.metadata.builder.connection.TableHelper;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.MDMConnectionItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.core.ui.images.ECoreImage;
import org.talend.repository.ProjectManager;
import org.talend.repository.mdm.ui.wizard.CreateConceptWizard;
import org.talend.repository.model.ERepositoryStatus;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.ProxyRepositoryFactory;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.model.RepositoryNode.ENodeType;
import org.talend.repository.model.RepositoryNode.EProperties;
import org.talend.repository.ui.actions.metadata.AbstractCreateAction;
import org.talend.repository.ui.utils.ConnectionContextHelper;

/**
 * DOC hwang class global comment. Detailled comment
 */
public class RetrieveMDMSchemaAction extends AbstractCreateAction {

    protected static final int WIZARD_WIDTH = 800;

    protected static final int WIZARD_HEIGHT = 495;

    protected static final String CREATE_LABEL = "Retrieve Concept";

    protected static final String EDIT_LABEL = "Edit Concept";

    private RepositoryNode node;

    protected boolean readOnly = false;

    public RetrieveMDMSchemaAction() {
        super();

        this.setText(CREATE_LABEL);
        this.setToolTipText(CREATE_LABEL);
        this.setImageDescriptor(ImageProvider.getImageDesc(ECoreImage.METADATA_TABLE_ICON));
    }

    public RetrieveMDMSchemaAction(RepositoryNode node) {
        this();
        this.node = node;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.repository.ui.actions.metadata.AbstractCreateAction#init(org.talend.repository.model.RepositoryNode)
     */
    @Override
    protected void init(RepositoryNode node) {
        IProxyRepositoryFactory factory = ProxyRepositoryFactory.getInstance();
        if (factory.isUserReadOnlyOnCurrentProject() || !ProjectManager.getInstance().isInCurrentMainProject(node)) {
            setEnabled(false);
        } else {
            if (ENodeType.REPOSITORY_ELEMENT.equals(node.getType())) {
                ERepositoryStatus status = factory.getStatus(node.getObject());
                if (status == ERepositoryStatus.DELETED || status == ERepositoryStatus.LOCK_BY_OTHER) {
                    setEnabled(false);
                    return;
                }

                ERepositoryObjectType nodeType = (ERepositoryObjectType) node.getProperties(EProperties.CONTENT_TYPE);
                if (ERepositoryObjectType.METADATA_CON_TABLE.equals(nodeType)) {
                    setText(EDIT_LABEL);
                    if (node.getObject() != null) {
                        Item item = node.getObject().getProperty().getItem();
                        if (item instanceof MDMConnectionItem) {
                            collectSiblingNames(node);
                            setEnabled(true);
                            return;
                        }
                    }
                }

                if (ERepositoryObjectType.METADATA_MDMCONNECTION.equals(nodeType)) {
                    setText(CREATE_LABEL);
                    collectChildNames(node);
                    setEnabled(true);
                    return;
                }
            }
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.actions.AContextualAction#doRun()
     */
    @Override
    protected void doRun() {
        node = getCurrentRepositoryNode();
        // Force focus to the repositoryView and open Metadata and DbConnection nodes
        getViewPart().setFocus();
        getViewPart().expand(node.getParent(), true);

        // Init the content of the Wizard
        init(node);

        ERepositoryObjectType nodeType = (ERepositoryObjectType) node.getProperties(EProperties.CONTENT_TYPE);

        if (ERepositoryObjectType.METADATA_CON_TABLE.equals(nodeType)) {
            final IRepositoryObject object = node.getObject();

            ConnectionItem connectionItem = (ConnectionItem) object.getProperty().getItem();
            nodeType = ERepositoryObjectType.getItemType(connectionItem);

        }
        createMDMTableWizard(node, readOnly);

    }

    protected void createMDMTableWizard(final RepositoryNode node, final boolean forceReadOnly) {

        // Define the repositoryObject
        MDMConnection connection = null;
        MetadataTable metadataTable = null;

        boolean creation = false;
        if (node.getType() == ENodeType.REPOSITORY_ELEMENT) {
            ERepositoryObjectType nodeType = (ERepositoryObjectType) node.getProperties(EProperties.CONTENT_TYPE);
            String metadataTableLabel = (String) node.getProperties(EProperties.LABEL);

            MDMConnectionItem item = null;
            switch (nodeType) {
            case METADATA_CON_TABLE:
                item = (MDMConnectionItem) node.getObject().getProperty().getItem();
                connection = (MDMConnection) item.getConnection();
                metadataTable = TableHelper.findByLabel(connection, metadataTableLabel);
                creation = false;
                break;
            case METADATA_MDMCONNECTION:
                item = (MDMConnectionItem) node.getObject().getProperty().getItem();
                connection = (MDMConnection) item.getConnection();

                creation = true;
                break;
            default:
                break;
            }
            ConnectionContextHelper.checkContextMode(item);

            openMDMTableWizard(item, metadataTable, forceReadOnly, node, creation);
        }
    }

    private void openMDMTableWizard(final MDMConnectionItem item, final MetadataTable metadataTable, final boolean forceReadOnly,
            final RepositoryNode node, final boolean creation) {
        boolean skipStep = !creation;
        CreateConceptWizard conceptWizard = new CreateConceptWizard(PlatformUI.getWorkbench(), creation, getExistingNames(),
                node, item, metadataTable, readOnly);
        WizardDialog wizardDialog = new WizardDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), conceptWizard);
        wizardDialog.setBlockOnOpen(true);
        wizardDialog.setPageSize(WIZARD_WIDTH, WIZARD_HEIGHT);
        wizardDialog.create();
        if (wizardDialog.open() == wizardDialog.OK) {
            getViewPart().expand(node, true);
            refresh(node);
        }
        // node = null;
    }

    @Override
    public Class getClassForDoubleClick() {
        return MDMConnection.class;
    }

}
