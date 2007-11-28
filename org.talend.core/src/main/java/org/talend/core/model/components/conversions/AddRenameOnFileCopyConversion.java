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
package org.talend.core.model.components.conversions;

import org.talend.core.model.components.ComponentUtilities;
import org.talend.designer.core.model.utils.emf.talendfile.ElementParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.NodeType;

/**
 * DOC Administrator class global comment. Detailled comment
 */
public class AddRenameOnFileCopyConversion implements IComponentConversion {

    private String field = "CHECK";

    private String name = "RENAME";

    /**
     * DOC Administrator AddRenameOnFileCopyConversion constructor comment.
     */
    public AddRenameOnFileCopyConversion() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.conversions.IComponentConversion#transform(org.talend.designer.core.model.utils.emf.talendfile.NodeType)
     */
    public void transform(NodeType node) {
        ElementParameterType nodeProperty = ComponentUtilities.getNodeProperty(node, "DESTINATION_RENAME");
        String destinationFileName = "";
        if (nodeProperty != null) {
            destinationFileName = nodeProperty.getValue();
        }
        // the old tFileCopy, if the property "DESTINATION_RENAME" is "", it means "don't rename"
        if (ComponentUtilities.getNodeProperty(node, name) == null && !destinationFileName.trim().equals("\"\"")) {

            ComponentUtilities.addNodeProperty(node, name, field);
            ComponentUtilities.setNodeValue(node, name, "true");
        }
    }
}
