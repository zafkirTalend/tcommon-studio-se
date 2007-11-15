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
package org.talend.commons.ui.swt.advanced.dataeditor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.talend.commons.ui.swt.advanced.dataeditor.button.AddPushButton;
import org.talend.commons.ui.swt.advanced.dataeditor.button.CopyPushButton;
import org.talend.commons.ui.swt.advanced.dataeditor.button.CopyPushButtonForExtendedTable;
import org.talend.commons.ui.swt.advanced.dataeditor.button.ExportPushButton;
import org.talend.commons.ui.swt.advanced.dataeditor.button.ImportPushButton;
import org.talend.commons.ui.swt.advanced.dataeditor.button.MoveDownPushButton;
import org.talend.commons.ui.swt.advanced.dataeditor.button.MoveDownPushButtonForExtendedTable;
import org.talend.commons.ui.swt.advanced.dataeditor.button.MoveUpPushButton;
import org.talend.commons.ui.swt.advanced.dataeditor.button.MoveUpPushButtonForExtendedTable;
import org.talend.commons.ui.swt.advanced.dataeditor.button.PastePushButton;
import org.talend.commons.ui.swt.advanced.dataeditor.button.RemovePushButton;
import org.talend.commons.ui.swt.advanced.dataeditor.button.RemovePushButtonForExtendedTable;
import org.talend.commons.ui.swt.advanced.dataeditor.button.ResetDBTypesPushButton;
import org.talend.commons.ui.swt.advanced.dataeditor.button.SaveAsGenericSchemaPushButton;
import org.talend.commons.ui.swt.advanced.dataeditor.button.SaveAsGenericSchemaPushButtonForExtendedTable;
import org.talend.commons.ui.swt.advanced.dataeditor.control.ExtendedPushButton;
import org.talend.commons.ui.swt.extended.table.AbstractExtendedTableViewer;

/**
 * DOC cantoine class global comment. Detailled comment <br/>
 * 
 * TGU same purpose as TargetSchemaToolbarEditorView2 but uses EMF model directly $Id:
 * TargetSchemaToolbarEditorView2.java,v 1.1 2006/08/02 19:43:45 cantoine Exp $
 * 
 */
public class ExtendedToolbarView extends AbstractExtendedToolbar {

    protected AddPushButton addButton;

    protected RemovePushButton removeButton;

    protected MoveUpPushButton moveUpButton;

    protected MoveDownPushButton moveDownButton;

    protected CopyPushButton copyButton;

    protected PastePushButton pasteButton;

    private ExportPushButton exportButton;

    private ImportPushButton importButton;

    protected SaveAsGenericSchemaPushButton saveAsGenericSchemaButton;

    protected ResetDBTypesPushButton resetDBTypesButton;

    /**
     * DOC amaumont MatadataToolbarEditor constructor comment.
     * 
     * @param parent
     * @param style
     * @param targetSchemaEditorView
     */
    public ExtendedToolbarView(Composite parent, int style, AbstractExtendedTableViewer extendedTableViewer) {
        super(parent, style, extendedTableViewer);
        // // Force the height of the toolbars
        // GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        // gridData.minimumHeight = 40;
        // gridData.heightHint = 40;
        // toolbar.setLayoutData(gridData);
    }

    /**
     * Override this method if needed to instanciate only the buttons of your choice.
     * 
     */
    protected void createComponents(Composite parent) {
        addButton = createAddPushButton();

        removeButton = createRemovePushButton();

        moveUpButton = createMoveUpPushButton();

        moveDownButton = createMoveDownPushButton();

        copyButton = createCopyPushButton();

        pasteButton = createPastePushButton();

        exportButton = createExportPushButton();

        importButton = createImportPushButton();

        saveAsGenericSchemaButton = createSaveAsGenericSchemaButton();

        // loadButton = new Button(toolbar, SWT.PUSH);
        // loadButton.setToolTipText("Import");
        // loadButton.setImage(ImageProvider.getImage(EImage.IMPORT_ICON));
        //
        // exportButton = new Button(toolbar, SWT.PUSH);
        // exportButton.setToolTipText("Export");
        // exportButton.setImage(ImageProvider.getImage(EImage.EXPORT_ICON));
    }


    protected AddPushButton createAddPushButton() {
        return null;
    }

    protected RemovePushButton createRemovePushButton() {
        return new RemovePushButtonForExtendedTable(toolbar, extendedTableViewer);
    }

    protected MoveUpPushButton createMoveUpPushButton() {
        return new MoveUpPushButtonForExtendedTable(toolbar, extendedTableViewer);
    }

    protected MoveDownPushButton createMoveDownPushButton() {
        return new MoveDownPushButtonForExtendedTable(toolbar, extendedTableViewer);
    }

    protected CopyPushButton createCopyPushButton() {
        return new CopyPushButtonForExtendedTable(toolbar, extendedTableViewer);
    }

    protected PastePushButton createPastePushButton() {
        return null;
    }

    protected ExportPushButton createExportPushButton() {
        return null;
    }

    protected ImportPushButton createImportPushButton() {
        return null;
    }
    
    protected SaveAsGenericSchemaPushButton createSaveAsGenericSchemaButton() {
        return null;
    }

    /**
     * Getter for addButton.
     * 
     * @return the addButton
     */
    public AddPushButton getAddButton() {
        return this.addButton;
    }

    /**
     * Getter for copyButton.
     * 
     * @return the copyButton
     */
    public CopyPushButton getCopyButton() {
        return this.copyButton;
    }

    /**
     * Getter for moveDownButton.
     * 
     * @return the moveDownButton
     */
    public MoveDownPushButton getMoveDownButton() {
        return this.moveDownButton;
    }

    /**
     * Getter for moveUpButton.
     * 
     * @return the moveUpButton
     */
    public MoveUpPushButton getMoveUpButton() {
        return this.moveUpButton;
    }

    /**
     * Getter for pastButton.
     * 
     * @return the pastButton
     */
    public PastePushButton getPasteButton() {
        return this.pasteButton;
    }

    /**
     * Getter for removeButton.
     * 
     * @return the removeButton
     */
    public RemovePushButton getRemoveButton() {
        return this.removeButton;
    }

    /**
     * Getter for exportButton.
     * 
     * @return the exportButton
     */
    public ExportPushButton getExportButton() {
        return this.exportButton;
    }

    /**
     * Getter for importButton.
     * 
     * @return the importButton
     */
    public ImportPushButton getImportButton() {
        return this.importButton;
    }

    /**
     * Getter for resetDBTypesButton.
     * 
     * @return the resetDBTypesButton
     */
    public ResetDBTypesPushButton getResetDBTypesButton() {
        return resetDBTypesButton;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.advanced.dataeditor.AbstractExtendedToolbar#updateEnabledStateOfButtons()
     */
    @Override
    public void updateEnabledStateOfButtons() {
        List<ExtendedPushButton> buttons = getButtons();
        for (ExtendedPushButton button : buttons) {
            button.getButton().setEnabled(button.getEnabledState());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.advanced.dataeditor.AbstractExtendedToolbar#getButtons()
     */
    @Override
    public List<ExtendedPushButton> getButtons() {

        ArrayList<ExtendedPushButton> list = new ArrayList<ExtendedPushButton>();

        if (getAddButton() != null) {
            list.add(getAddButton());
        }

        if (getRemoveButton() != null) {
            list.add(getRemoveButton());
        }

        if (getMoveDownButton() != null) {
            list.add(getMoveDownButton());
        }

        if (getMoveUpButton() != null) {
            list.add(getMoveUpButton());
        }

        if (getCopyButton() != null) {
            list.add(getCopyButton());
        }

        if (getPasteButton() != null) {
            list.add(getPasteButton());
        }

        if (getExportButton() != null) {
            list.add(getExportButton());
        }

        if (getImportButton() != null) {
            list.add(getImportButton());
        }

        if (getResetDBTypesButton() != null) {
            list.add(getResetDBTypesButton());
        }

        return list;
    }

    /**
     * Getter for saveAsGenericSchemaButton.
     * 
     * @return the saveAsGenericSchemaButton
     */
    public SaveAsGenericSchemaPushButton getSaveAsGenericSchemaButton() {
        return saveAsGenericSchemaButton;
    }
}

// 