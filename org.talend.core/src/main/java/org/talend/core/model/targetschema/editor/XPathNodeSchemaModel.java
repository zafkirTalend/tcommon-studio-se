// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
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
import org.talend.core.model.metadata.builder.connection.MetadataSchema;
import org.talend.core.model.metadata.builder.connection.SchemaTarget;

/**
 * DOC cantoine class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class XPathNodeSchemaModel extends ExtendedTableModel<SchemaTarget> {

    private MetadataSchema metadataSchema;

    public XPathNodeSchemaModel(String name) {
        super(name);
    }

    public XPathNodeSchemaModel(MetadataSchema metadataSchema, String name) {
        super(name);
        setMetadataSchema(metadataSchema);
    }


    public MetadataSchema getMetadataSchema() {
        return this.metadataSchema;
    }

    /**
     * set MetadataSchema.
     * 
     * @param metadataSchema
     */
    public void setMetadataSchema(MetadataSchema metadataSchema) {
        this.metadataSchema = metadataSchema;
        registerDataList((List<SchemaTarget>) metadataSchema.getSchemaTargets());
    }

    /**
     * DOC amaumont Comment method "createSchemaTarget".
     * @return
     */
    public SchemaTarget createNewSchemaTarget() {
        return ConnectionFactory.eINSTANCE.createSchemaTarget();
    }

}
