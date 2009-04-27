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
package org.talend.core.model.components.conversions;

import org.talend.core.model.components.ComponentUtilities;
import org.talend.designer.core.model.utils.emf.talendfile.ElementParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.NodeType;

/**
 * DOC s class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2009-4-27 17:06:40 +0000 rshi $
 */
public class UpdateExtendInsertDefaultValue implements IComponentConversion {

    public void transform(NodeType node) {
        // TODO Auto-generated method stub
        ElementParameterType property = ComponentUtilities.getNodeProperty(node, "EXTENDINSERT"); //$NON-NLS-1$
        property.setValue("true");
    }

}
