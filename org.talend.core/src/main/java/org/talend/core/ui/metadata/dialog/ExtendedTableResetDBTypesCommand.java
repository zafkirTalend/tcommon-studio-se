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
package org.talend.core.ui.metadata.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.talend.commons.ui.swt.advanced.dataeditor.commands.IExtendedTableCommand;
import org.talend.commons.ui.swt.extended.table.AbstractExtendedTableViewer;
import org.talend.commons.ui.swt.extended.table.ExtendedTableModel;
import org.talend.core.i18n.Messages;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.types.TypesManager;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 */
public class ExtendedTableResetDBTypesCommand extends Command implements IExtendedTableCommand {

    public static final String LABEL = Messages.getString("ExtendedTableResetDBTypesCommand.ResetDBTypes.Label"); //$NON-NLS-1$

    private ExtendedTableModel extendedTable;

    private List<String> oldDbTypes;

    private String dbmsId;
    
    private AbstractExtendedTableViewer extendedTableViewer;

    public ExtendedTableResetDBTypesCommand(ExtendedTableModel extendedTable, String dbmsId, AbstractExtendedTableViewer extendedTableViewer) {
        super(LABEL);
        this.extendedTable = extendedTable;
        this.dbmsId = dbmsId;
        this.extendedTableViewer = extendedTableViewer;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.advanced.dataeditor.commands.AbstractExtendedTableResetDBTypesCommand#execute()
     */
    @Override
    public void execute() {
        // JavaType javaType = JavaTypesManager.getJavaTypeFromId(oldTalendType);
        // String typeName = JavaTypesManager.getShortNameFromJavaType(javaType);

        oldDbTypes = new ArrayList<String>();

        List beansList = extendedTable.getBeansList();
        for (Object object : beansList) {
            if (object instanceof IMetadataColumn) {
                oldDbTypes.add(((IMetadataColumn) object).getType());
            }
        }
        

        for (int i = 0; i < beansList.size(); i++) {
            if (beansList.get(i) instanceof IMetadataColumn) {
                IMetadataColumn column = (IMetadataColumn) beansList.get(i);
                column.setType(TypesManager.getDBTypeFromTalendType(dbmsId, column.getTalendType(), column.isNullable()));
//                JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
//                String typeName = JavaTypesManager.getShortNameFromJavaType(javaType);
//                column.setType(MetadataTalendType.getMappingTypeRetriever(dbmsId).getDefaultSelectedDbType(typeName,
//                        column.isNullable()));
            }
        }
        extendedTableViewer.getTableViewerCreator().getTableViewer().refresh();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.advanced.dataeditor.commands.AbstractExtendedTableResetDBTypesCommand#undo()
     */
    @Override
    public synchronized void undo() {
        List beansList = extendedTable.getBeansList();

        for (int i = 0; i < beansList.size(); i++) {
            if (beansList.get(i) instanceof IMetadataColumn) {
                IMetadataColumn column = (IMetadataColumn) beansList.get(i);
                column.setType(oldDbTypes.get(i));
            }
        }
        extendedTableViewer.getTableViewerCreator().getTableViewer().refresh();
    }
}
