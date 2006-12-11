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

import java.util.ArrayList;
import java.util.List;

import org.talend.commons.ui.swt.extended.table.ExtendedTableModel;
import org.talend.core.model.metadata.builder.connection.ConnectionFactory;
import org.talend.core.model.metadata.builder.connection.XmlXPathLoopDescriptor;

/**
 * DOC cantoine class global comment. Detailled comment <br/>
 * 
 * $Id: XmlExtractorSchemaModel.java 949 2006-12-11 15:03:40Z cantoine $
 * 
 */
public class XmlExtractorLoopModel extends ExtendedTableModel<XmlXPathLoopDescriptor> {

    private XmlXPathLoopDescriptor xmlXPathLoopDescriptor;

    public XmlExtractorLoopModel(String name) {
        super(name);
    }

    public XmlExtractorLoopModel(XmlXPathLoopDescriptor xmlXPathLoopDescriptor, String name) {
        super(name);
        setXmlXPathLoopDescriptor(xmlXPathLoopDescriptor);
    }

    public XmlXPathLoopDescriptor getXmlXPathLoopDescriptor() {
        return this.xmlXPathLoopDescriptor;
    }

    /**
     * set XmlXPathLoopDescriptor.
     * 
     * @param xmlXPathLoopDescriptor
     */
    public void setXmlXPathLoopDescriptor(XmlXPathLoopDescriptor xmlXPathLoopDescriptor) {
        if (xmlXPathLoopDescriptor != null) {
            this.xmlXPathLoopDescriptor = xmlXPathLoopDescriptor;
            registerDataList((List<XmlXPathLoopDescriptor>) xmlXPathLoopDescriptor.getSchemaTargets());
        } else {
            List<XmlXPathLoopDescriptor> list = new ArrayList<XmlXPathLoopDescriptor>();
            list.add(createXmlXPathLoopDescriptor());
            registerDataList(list);
        }
    }

    /**
     * DOC amaumont Comment method "createSchemaTarget".
     * 
     * @return
     */
    public XmlXPathLoopDescriptor createXmlXPathLoopDescriptor() {
        return ConnectionFactory.eINSTANCE.createXmlXPathLoopDescriptor();
    }

}
