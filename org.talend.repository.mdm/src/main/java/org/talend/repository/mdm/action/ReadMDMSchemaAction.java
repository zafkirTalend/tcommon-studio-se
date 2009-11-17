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

import org.talend.commons.ui.image.EImage;
import org.talend.commons.ui.image.ImageProvider;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.MDMConnectionItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.repository.model.ERepositoryStatus;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.ProxyRepositoryFactory;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.model.RepositoryNode.ENodeType;
import org.talend.repository.model.RepositoryNode.EProperties;

/**
 * DOC hwang class global comment. Detailled comment
 */
public class ReadMDMSchemaAction extends RetrieveMDMSchemaAction {

    protected static final String LABEL = "Read concept";

    public ReadMDMSchemaAction() {
        super();
        this.setText(LABEL);
        this.setToolTipText(LABEL);
        this.setImageDescriptor(ImageProvider.getImageDesc(EImage.READ_ICON));
    }

    protected void init(RepositoryNode node) {
        setEnabled(false);
        IProxyRepositoryFactory factory = ProxyRepositoryFactory.getInstance();
        if (ENodeType.REPOSITORY_ELEMENT.equals(node.getType())) {
            if (factory.getStatus(node.getObject()) == ERepositoryStatus.DELETED) {
                return;
            }
            ERepositoryObjectType nodeType = (ERepositoryObjectType) node.getProperties(EProperties.CONTENT_TYPE);
            if (ERepositoryObjectType.METADATA_CON_TABLE.equals(nodeType)) {
                IRepositoryObject repositoryObject = node.getObject();
                if (repositoryObject != null) {
                    Item item2 = repositoryObject.getProperty().getItem();
                    if (item2 instanceof MDMConnectionItem) {
                        setEnabled(true);
                        return;
                    }
                }
                setEnabled(false);
                return;
            }

        }
    }

    @Override
    protected void doRun() {
        this.readOnly = true;
        super.doRun();
    }
}
