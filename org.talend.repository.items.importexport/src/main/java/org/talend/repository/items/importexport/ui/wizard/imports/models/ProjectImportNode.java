// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.items.importexport.ui.wizard.imports.models;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.talend.commons.ui.runtime.image.ECoreImage;
import org.talend.commons.ui.runtime.image.ImageProvider;
import org.talend.core.model.properties.Project;
import org.talend.core.model.repository.ERepositoryObjectType;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class ProjectImportNode extends ImportNode {

    private Map<ERepositoryObjectType, TypeImportNode> typeNodesChildrenMap = new HashMap<ERepositoryObjectType, TypeImportNode>();

    private Project project;

    public ProjectImportNode(Project project) {
        super();
        this.project = project;
        this.setProjectNode(this);
    }

    @Override
    public String getName() {
        return project.getTechnicalLabel();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.items.importexport.ui.wizard.imports.models.ImportNode#getDisplayLabel()
     */
    @Override
    public String getDisplayLabel() {
        return project.getLabel();
    }

    @Override
    public Image getImage() {
        return ImageProvider.getImage(ECoreImage.PROJECT_ICON);
    }

    public Project getProject() {
        return this.project;
    }

    public Map<ERepositoryObjectType, TypeImportNode> getTypeNodesChildrenMap() {
        return this.typeNodesChildrenMap;
    }
}
