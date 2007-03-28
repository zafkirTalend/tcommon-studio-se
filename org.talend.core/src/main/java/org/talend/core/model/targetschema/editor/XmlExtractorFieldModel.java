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
package org.talend.core.model.targetschema.editor;

import java.util.List;

import org.talend.commons.ui.swt.extended.table.ExtendedTableModel;
import org.talend.core.model.metadata.builder.connection.ConnectionFactory;
import org.talend.core.model.metadata.builder.connection.SchemaTarget;

/**
 * DOC cantoine class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class XmlExtractorFieldModel extends ExtendedTableModel<SchemaTarget> {

    public XmlExtractorFieldModel(String name) {
        super(name);
    }

    public XmlExtractorFieldModel(List schemaTargetList, String name) {
        super(name);
        setXmlXPathLoopDescriptor(schemaTargetList);
    }

    /**
     * set XmlXPathLoopDescriptor.
     * 
     * @param schemaTargetList
     */
    public void setXmlXPathLoopDescriptor(List schemaTargetList) {
        registerDataList((List<SchemaTarget>) schemaTargetList);
    }

    /**
     * DOC amaumont Comment method "createSchemaTarget".
     * 
     * @return
     */
    public SchemaTarget createNewSchemaTarget() {
        return ConnectionFactory.eINSTANCE.createSchemaTarget();
    }

}
