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
package org.talend.core.ui.metadata.dialog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.talend.commons.ui.command.CommandStackForComposite;
import org.talend.commons.ui.image.EImage;
import org.talend.commons.ui.image.ImageProvider;
import org.talend.commons.ui.swt.advanced.composite.ThreeCompositesSashForm;
import org.talend.commons.ui.swt.tableviewer.IModifiedBeanListener;
import org.talend.commons.ui.swt.tableviewer.ModifiedBeanEvent;
import org.talend.core.i18n.Messages;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.editor.MetadataTableEditor;
import org.talend.core.ui.metadata.editor.AbstractMetadataTableEditorView;
import org.talend.core.ui.metadata.editor.MetadataTableEditorView;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class MetadataDialog extends Dialog {

    private static final Object DATABASE_LABEL = Messages.getString("MetadataDialog.DatabaseLabel"); //$NON-NLS-1$

    @Override
    protected void setShellStyle(int newShellStyle) {
        newShellStyle = newShellStyle | SWT.RESIZE;
        super.setShellStyle(newShellStyle);
    }

    private MetadataTableEditorView outputMetaView;

    private MetadataTableEditorView inputMetaView;

    private Point size;

    private IMetadataTable outputMetaTable;

    private IMetadataTable inputMetaTable;

    private String text = ""; //$NON-NLS-1$

    private String titleOutput = ""; //$NON-NLS-1$

    private String titleInput = ""; //$NON-NLS-1$

    private boolean inputReadOnly = false;

    private boolean outputReadOnly = false;

    private Map<IMetadataColumn, String> changedNameColumns = new HashMap<IMetadataColumn, String>();

    private CommandStack commandStack;

    private String outputFamily;

    private String inputFamily;

    private ThreeCompositesSashForm compositesSachForm;

    public MetadataDialog(Shell parent, IMetadataTable inputMetaTable, String titleInput, String inputFamily,
            IMetadataTable outputMetaTable, String titleOutput, String outputFamily, CommandStack commandStack) {
        super(parent);
        this.inputMetaTable = inputMetaTable;
        this.titleInput = titleInput;
        this.outputMetaTable = outputMetaTable;
        this.titleOutput = titleOutput;
        this.commandStack = commandStack;
        this.inputFamily = inputFamily;
        this.outputFamily = outputFamily;

        if (inputMetaTable == null) {
            size = new Point(550, 400);
        } else {
            size = new Point(1000, 400);
        }
    }

    public MetadataDialog(Shell parent, IMetadataTable outputMetaTable, String titleOutput, String outputFamily,
            CommandStack commandStack) {
        this(parent, null, null, null, outputMetaTable, titleOutput, outputFamily, commandStack);
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    protected void configureShell(final Shell newShell) {
        super.configureShell(newShell);
        this.commandStack = new CommandStackForComposite(newShell);
        newShell.setSize(size);
        newShell.setText(text);
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
    }

    @Override
    protected Control createDialogArea(final Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);

        MetadataTableEditor metadataTableEditor;

        if (inputMetaTable == null) {
            composite.setLayout(new FillLayout());
            metadataTableEditor = new MetadataTableEditor(outputMetaTable, titleOutput);
            outputMetaView = new MetadataTableEditorView(composite, SWT.NONE, metadataTableEditor, outputReadOnly, true, true,
                    false);
            outputMetaView.setDbTypeColumnsState(DATABASE_LABEL.equals(outputFamily), true, false);
            outputMetaView.initGraphicComponents();
            outputMetaView.getExtendedTableViewer().setCommandStack(commandStack);
        } else {

            compositesSachForm = new ThreeCompositesSashForm(composite, SWT.NONE);

            GridLayout gridLayout = new GridLayout(1, false);
            gridLayout.marginBottom = 0;
            gridLayout.marginHeight = 0;
            gridLayout.marginLeft = 0;
            gridLayout.marginRight = 0;
            gridLayout.marginTop = 0;
            gridLayout.marginWidth = 0;
            gridLayout.horizontalSpacing = 0;
            composite.setLayout(gridLayout);
            GridData gridData = new GridData(GridData.FILL_BOTH);
            composite.setLayoutData(gridData);

            metadataTableEditor = new MetadataTableEditor(inputMetaTable, titleInput + " (Input)"); //$NON-NLS-1$
            inputMetaView = new MetadataTableEditorView(compositesSachForm.getLeftComposite(), SWT.NONE, metadataTableEditor,
                    inputReadOnly, true, true, false);
            inputMetaView.setDbTypeColumnsState(DATABASE_LABEL.equals(inputFamily), true, false);
            inputMetaView.initGraphicComponents();
            inputMetaView.getExtendedTableViewer().setCommandStack(commandStack);

            inputMetaView.setGridDataSize(size.x / 2 - 50, size.y - 150);

            Label label1 = new Label(compositesSachForm.getMidComposite(), SWT.NONE);
            GridDataFactory.swtDefaults().hint(42, 18).applyTo(label1);
            Composite buttonComposite = new Composite(compositesSachForm.getMidComposite(), SWT.BORDER);
            Label label2 = new Label(compositesSachForm.getMidComposite(), SWT.NONE);
            GridDataFactory.swtDefaults().hint(42, 36).applyTo(label2);

            gridLayout = new GridLayout(1, true);
            // gridLayout.marginBottom = 0;
            // gridLayout.marginHeight = 0;
            // gridLayout.marginLeft = 0;
            // gridLayout.marginRight = 0;
            // gridLayout.marginTop = 0;
            // gridLayout.marginWidth = 0;
            buttonComposite.setLayout(gridLayout);
            gridData = new GridData(GridData.FILL_BOTH);
            // gridData.verticalAlignment = GridData.CENTER;
            buttonComposite.setLayoutData(gridData);

            Composite buttonComposite2 = new Composite(buttonComposite, SWT.NONE);

            gridLayout = new GridLayout(1, true);
            gridLayout.marginBottom = 0;
            gridLayout.marginHeight = 0;
            gridLayout.marginLeft = 0;
            gridLayout.marginRight = 0;
            gridLayout.marginTop = 0;
            gridLayout.marginWidth = 0;
            buttonComposite2.setLayout(gridLayout);
            gridData = new GridData(GridData.FILL_BOTH);
            gridData.verticalAlignment = GridData.CENTER;
            buttonComposite2.setLayoutData(gridData);

            // Input => Output
            Button copyToOutput = new Button(buttonComposite2, SWT.NONE);
            copyToOutput.setImage(ImageProvider.getImage(EImage.RIGHT_ICON));
            copyToOutput.setToolTipText(Messages.getString("MetadataDialog.CopyToOutput.ToolTopText")); //$NON-NLS-1$
            GridDataFactory.swtDefaults().align(SWT.CENTER, SWT.CENTER).applyTo(copyToOutput);
            copyToOutput.addListener(SWT.Selection, new Listener() {

                public void handleEvent(Event event) {
                    MessageBox messageBox = new MessageBox(parent.getShell(), SWT.APPLICATION_MODAL | SWT.OK | SWT.CANCEL);
                    messageBox.setText(Messages.getString("MetadataDialog.SchemaModification")); //$NON-NLS-1$
                    messageBox.setMessage(Messages.getString("MetadataDialog.Message")); //$NON-NLS-1$
                    if (messageBox.open() == SWT.OK) {
                        outputMetaView.getMetadataTableEditor().removeAll();
                        IMetadataTable metadataTable = inputMetaView.getMetadataTableEditor().getMetadataTable().clone();
                        List<IMetadataColumn> listColumns = metadataTable.getListColumns();
                        for (IMetadataColumn column : listColumns) {
                            column.setPattern(null);
                        }
                        outputMetaView.getMetadataTableEditor().setMetadataTable(metadataTable);
                    }
                }
            });

            // Output => Input
            Button copyToInput = new Button(buttonComposite2, SWT.NONE);
            copyToInput.setImage(ImageProvider.getImage(EImage.LEFT_ICON));
            copyToInput.setToolTipText(Messages.getString("MetadataDialog.CopyToInput")); //$NON-NLS-1$
            gridData = new GridData();
            gridData.verticalAlignment = GridData.CENTER;
            copyToInput.setLayoutData(gridData);
            copyToInput.addListener(SWT.Selection, new Listener() {

                public void handleEvent(Event event) {
                    MessageBox messageBox = new MessageBox(parent.getShell(), SWT.APPLICATION_MODAL | SWT.OK | SWT.CANCEL);
                    messageBox.setText(Messages.getString("MetadataDialog.SchemaModification")); //$NON-NLS-1$
                    messageBox.setMessage(Messages.getString("MetadataDialog.TransferMessage")); //$NON-NLS-1$
                    if (messageBox.open() == SWT.OK) {
                        inputMetaView.getMetadataTableEditor().removeAll();
                        IMetadataTable metadataTable = outputMetaView.getMetadataTableEditor().getMetadataTable().clone();
                        List<IMetadataColumn> listColumns = metadataTable.getListColumns();
                        for (IMetadataColumn column : listColumns) {
                            column.setPattern(null);
                        }
                        inputMetaView.getMetadataTableEditor().setMetadataTable(metadataTable);
                    }
                }
            });

            if (inputReadOnly) {
                copyToInput.setEnabled(false);
            }

            outputMetaView = new MetadataTableEditorView(compositesSachForm.getRightComposite(), SWT.NONE,
                    new MetadataTableEditor(outputMetaTable, titleOutput + " (Output)"), outputReadOnly, true, true, //$NON-NLS-1$
                    false);
            outputMetaView.setDbTypeColumnsState(DATABASE_LABEL.equals(outputFamily), false, true);
            outputMetaView.initGraphicComponents();
            outputMetaView.getExtendedTableViewer().setCommandStack(commandStack);
            outputMetaView.setGridDataSize(size.x / 2 - 50, size.y - 150);
            if (outputReadOnly) {
                copyToOutput.setEnabled(false);
            }
            compositesSachForm.setGridDatas();
        }
        metadataTableEditor.addModifiedBeanListener(new IModifiedBeanListener<IMetadataColumn>() {

            public void handleEvent(ModifiedBeanEvent<IMetadataColumn> event) {
                if (AbstractMetadataTableEditorView.ID_COLUMN_NAME.equals(event.column.getId())) {
                    IMetadataColumn modifiedObject = (IMetadataColumn) event.bean;
                    if (modifiedObject != null) {
                        String originalLabel = changedNameColumns.get(modifiedObject);
                        if (originalLabel == null) {
                            changedNameColumns.put(modifiedObject, (String) event.previousValue);
                        }
                    }
                }

            }

        });
        return composite;
    }

    /**
     * Returns input metadata.
     * 
     * @return
     */
    public IMetadataTable getInputMetaData() {
        if (inputMetaView == null) {
            return null;
        }
        return inputMetaView.getMetadataTableEditor().getMetadataTable();
    }

    /**
     * Returns output metadata.
     * 
     * @return
     */
    public IMetadataTable getOutputMetaData() {
        return outputMetaView.getMetadataTableEditor().getMetadataTable();
    }

    public void setInputReadOnly(boolean inputReadOnly) {
        this.inputReadOnly = inputReadOnly;
    }

    public void setOutputReadOnly(boolean outputReadOnly) {
        this.outputReadOnly = outputReadOnly;
    }

    /**
     * Getter for inputReadOnly.
     * 
     * @return the inputReadOnly
     */
    public boolean isInputReadOnly() {
        return this.inputReadOnly;
    }

    /**
     * Getter for outputReadOnly.
     * 
     * @return the outputReadOnly
     */
    public boolean isOutputReadOnly() {
        return this.outputReadOnly;
    }

    public ThreeCompositesSashForm getCompositesSachForm() {
        return this.compositesSachForm;
    }

    public MetadataTableEditorView getInputMetaView() {
        return this.inputMetaView;
    }

}
