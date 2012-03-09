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
package org.talend.core.repository;

import org.talend.core.model.components.IComponent;
import org.talend.core.model.metadata.builder.connection.XmlFileConnection;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.XmlFileConnectionItem;
import org.talend.core.model.properties.impl.XmlFileConnectionItemImpl;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.repository.model.RepositoryNode;

/**
 * ggu class global comment. Detailled comment
 */
public class DefaultRepositoryComponentDndFilter implements IRepositoryComponentDndFilter {

    protected static final String MAP = "MAP";//$NON-NLS-1$

    public DefaultRepositoryComponentDndFilter() {
        super();
    }

    public String getRepositoryType(Item item, ERepositoryObjectType type) {
        RepositoryComponentSetting setting = RepositoryComponentManager.getSetting(item, type);
        if (setting != null) {
            return setting.getRepositoryType();
        }
        return null;
    }

    public boolean except(Item item, ERepositoryObjectType type, RepositoryNode seletetedNode, IComponent component,
            String repositoryType) {
        return false; // default, no except components to valid
    }

    public boolean valid(Item item, ERepositoryObjectType type, RepositoryNode seletetedNode, IComponent component,
            String repositoryType) {
        String productNameWanted = repositoryType;
        if (component == null || repositoryType == null) {
            return false;
        }
        // bug TDI-19945,when drop tFileOutputXML that yourself created,filter tFileInputXML,tExtractXMLField component
        if (("tExtractXMLField").equals(component.getName()) || ("tFileInputXML").equals(component.getName())) {
            if (item instanceof XmlFileConnectionItem) {
                XmlFileConnection connection = (XmlFileConnection) ((XmlFileConnectionItemImpl) item).getConnection();
                if (!connection.isInputModel()) {
                    productNameWanted = "XMLOUTPUT"; //$NON-NLS-1$
                }
            }
        }
        String componentProductname = component.getRepositoryType();

        if (componentProductname != null && productNameWanted.endsWith(componentProductname)
                && validSub(item, type, seletetedNode, component, repositoryType)) {
            return true;
        }
        return false;
    }

    protected boolean validSub(Item item, ERepositoryObjectType type, RepositoryNode seletetedNode, IComponent component,
            String repositoryType) {
        boolean tableWithMap = true;
        if (type == ERepositoryObjectType.METADATA_CON_TABLE) {
            if (component.getName().toUpperCase().endsWith(MAP)) {
                tableWithMap = false;
            }
        }
        return tableWithMap;
    }

}
