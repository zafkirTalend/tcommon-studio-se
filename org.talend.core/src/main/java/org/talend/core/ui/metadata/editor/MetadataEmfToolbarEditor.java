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
package org.talend.core.ui.metadata.editor;

import java.io.File;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.widgets.Composite;
import org.talend.commons.ui.swt.advanced.dataeditor.ExtendedToolbarView;
import org.talend.commons.ui.swt.advanced.dataeditor.button.AddPushButton;
import org.talend.commons.ui.swt.advanced.dataeditor.button.AddPushButtonForExtendedTable;
import org.talend.commons.ui.swt.advanced.dataeditor.button.ExportPushButton;
import org.talend.commons.ui.swt.advanced.dataeditor.button.ExportPushButtonForExtendedTable;
import org.talend.commons.ui.swt.advanced.dataeditor.button.ImportPushButton;
import org.talend.commons.ui.swt.advanced.dataeditor.button.ImportPushButtonForExtendedTable;
import org.talend.commons.ui.swt.advanced.dataeditor.button.PastePushButton;
import org.talend.commons.ui.swt.advanced.dataeditor.button.PastePushButtonForExtendedTable;
import org.talend.commons.ui.swt.advanced.dataeditor.button.ResetDBTypesPushButton;
import org.talend.commons.ui.swt.advanced.dataeditor.button.ResetDBTypesPushButtonForExtendedTable;
import org.talend.commons.ui.swt.extended.table.AbstractExtendedTableViewer;
import org.talend.commons.ui.swt.extended.table.ExtendedTableModel;
import org.talend.core.model.metadata.builder.connection.MetadataColumn;
import org.talend.core.model.metadata.editor.MetadataEmfTableEditor;
import org.talend.core.ui.extended.command.MetadataEmfExportXmlCommand;
import org.talend.core.ui.extended.command.MetadataEmfImportXmlCommand;
import org.talend.core.ui.extended.command.MetadataEmfPasteCommand;
import org.talend.core.ui.metadata.dialog.ExtendedTableResetDBTypesCommand;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * TGU same purpose as MetadataToolbarEditorView but uses EMF model directly $Id: MetadataToolbarEditorView2.java,v 1.1
 * 2006/08/02 19:43:45 tguiu Exp $
 * 
 */
public class MetadataEmfToolbarEditor extends ExtendedToolbarView {

    private String dbmsId;

    /**
     * DOC amaumont MatadataToolbarEditor constructor comment.
     * 
     * @param parent
     * @param style
     * @param metadataEditorView
     */
    public MetadataEmfToolbarEditor(Composite parent, int style,
            AbstractExtendedTableViewer<MetadataColumn> extendedTableViewer) {
        super(parent, style, extendedTableViewer);
    }

    public MetadataEmfToolbarEditor(Composite parent, int style,
            AbstractExtendedTableViewer<MetadataColumn> extendedTableViewer, String dbmsId) {
        this(parent, style, extendedTableViewer);
        this.dbmsId = dbmsId;
        if (dbmsId != null) {
            resetDBTypesButton = createResetDBTypesPushButton(dbmsId);
            updateEnabledStateOfButtons();
        }
    }

    protected ResetDBTypesPushButton createResetDBTypesPushButton(final String dbmsId) {
        return new ResetDBTypesPushButtonForExtendedTable(toolbar, extendedTableViewer, dbmsId) {

            @Override
            protected Command getCommandToExecute(ExtendedTableModel extendedTableModel) {
                return new ExtendedTableResetDBTypesCommand(extendedTableModel, dbmsId, extendedTableViewer);
            }

        };
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.extended.ExtendedToolbarView#createAddPushButton()
     */
    @Override
    protected AddPushButton createAddPushButton() {
        return new AddPushButtonForExtendedTable(this.toolbar, getExtendedTableViewer()) {

            @Override
            protected Object getObjectToAdd() {
                MetadataEmfTableEditor tableEditorModel = (MetadataEmfTableEditor) getExtendedTableViewer()
                        .getExtendedControlModel();
                return tableEditorModel.createNewMetadataColumn(dbmsId);
            }

        };
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.extended.ExtendedToolbarView#createPastButton()
     */
    @Override
    public PastePushButton createPastePushButton() {
        return new PastePushButtonForExtendedTable(toolbar, extendedTableViewer) {

            @Override
            protected Command getCommandToExecute(ExtendedTableModel extendedTableModel, Integer indexWhereInsert) {
                return new MetadataEmfPasteCommand(extendedTableModel, indexWhereInsert);
            }

        };
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.extended.ExtendedToolbarView#createExportPushButton()
     */
    @Override
    protected ExportPushButton createExportPushButton() {
        return new ExportPushButtonForExtendedTable(toolbar, extendedTableViewer) {

            @Override
            protected Command getCommandToExecute(ExtendedTableModel extendedTableModel, File file) {
                return new MetadataEmfExportXmlCommand((MetadataEmfTableEditor) extendedTableModel, file, dbmsId);
            }

        };
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.extended.ExtendedToolbarView#createPastButton()
     */
    @Override
    public ImportPushButton createImportPushButton() {
        return new ImportPushButtonForExtendedTable(toolbar, extendedTableViewer) {

            @Override
            protected Command getCommandToExecute(ExtendedTableModel extendedTableModel, File file) {
                return new MetadataEmfImportXmlCommand(extendedTableModel, file);
            }

        };
    }

}
