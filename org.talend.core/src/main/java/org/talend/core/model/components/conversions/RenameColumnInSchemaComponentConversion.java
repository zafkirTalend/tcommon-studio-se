// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.core.model.components.conversions;

import java.util.List;

import org.talend.designer.core.model.utils.emf.talendfile.ColumnType;
import org.talend.designer.core.model.utils.emf.talendfile.MetadataType;
import org.talend.designer.core.model.utils.emf.talendfile.NodeType;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class RenameColumnInSchemaComponentConversion implements IComponentConversion {

    private String oldColumnName;
    private String newColumnName;

    public RenameColumnInSchemaComponentConversion(String oldColumnName, String newColumnName) {
        super();
        this.oldColumnName = oldColumnName;
        this.newColumnName = newColumnName;
    }

    public void transform(NodeType node) {
        List<MetadataType> metadataTypeList = node.getMetadata();
        for (MetadataType metadataType : metadataTypeList) {
            for (ColumnType col: (List<ColumnType>) metadataType.getColumn()) {
                if (col.getName().equals(oldColumnName)) {
                    col.setName(newColumnName);
                }
            }
        }
        //
    }
}
