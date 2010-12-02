// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.metadata;

import org.talend.designer.core.model.utils.emf.talendfile.MetadataType;
import org.talend.designer.core.model.utils.emf.talendfile.NodeType;
import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;

/**
 * DOC nrousseau class global comment. Detailled comment
 */
public class ProcessUtil {

    public static IMetadataTable getOutputMetadata(ProcessType processType) {
        for (Object nodeObject : processType.getNode()) {
            NodeType nodeType = (NodeType) nodeObject;
            if (nodeType.getComponentName().equals("tBufferOutput")) { //$NON-NLS-1$
                MetadataType metadataType = (MetadataType) nodeType.getMetadata().get(0);
                MetadataEmfFactory emfFact = new MetadataEmfFactory();
                emfFact.setMetadataType(metadataType);
                return emfFact.getMetadataTable();
            }
        }
        return null;
    }
}
