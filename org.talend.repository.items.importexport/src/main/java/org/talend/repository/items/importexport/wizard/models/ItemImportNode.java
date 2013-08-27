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
package org.talend.repository.items.importexport.wizard.models;

import org.apache.commons.lang.StringUtils;
import org.talend.core.model.properties.Project;
import org.talend.repository.items.importexport.handlers.model.ItemRecord;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class ItemImportNode extends ImportNode {

    private final ItemRecord itemRecord;

    public ItemImportNode(ItemRecord itemRecord) {
        super();
        this.itemRecord = itemRecord;
        this.setVisible(itemRecord.isVisible() && itemRecord.getErrors().isEmpty());
    }

    @Override
    public String getName() {
        StringBuffer sb = new StringBuffer();

        final ProjectImportNode projectNode = this.getProjectNode();
        if (projectNode != null) {
            Project project = projectNode.getProject();
            sb.append(project.getTechnicalLabel());
            sb.append('/');
        }
        String importPath = getItemRecord().getImportPath();
        if (StringUtils.isNotEmpty(importPath)) {
            sb.append(importPath);
            sb.append('/');
        }
        sb.append(getItemRecord().getLabel());
        return sb.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.items.importexport.ui.wizard.imports.models.ImportNode#getDisplayLabel()
     */
    @Override
    public String getDisplayLabel() {
        return getItemRecord().getLabel();
    }

    public ItemRecord getItemRecord() {
        return this.itemRecord;
    }

}
