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

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.editor.MetadataTableEditor;
import org.talend.core.ui.metadata.editor.MetadataTableEditorView;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class MetadataDialog extends Dialog {

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

    private String text = "";

    private String titleOutput = "";

    private String titleInput = "";

    private boolean inputReadOnly = false;

    private boolean outputReadOnly = false;

    public MetadataDialog(Shell parent, IMetadataTable inputMetaTable, String titleInput,
            IMetadataTable outputMetaTable, String titleOutput) {
        super(parent);
        this.inputMetaTable = inputMetaTable;
        this.titleInput = titleInput;
        this.outputMetaTable = outputMetaTable;
        this.titleOutput = titleOutput;
        if (inputMetaTable == null) {
            size = new Point(550, 400);
        } else {
            size = new Point(900, 400);
        }
    }

    public MetadataDialog(Shell parent, IMetadataTable outputMetaTable, String titleOutput) {
        super(parent);
        this.inputMetaTable = null;
        this.titleInput = null;
        this.outputMetaTable = outputMetaTable;
        this.titleOutput = titleOutput;
        if (inputMetaTable == null) {
            size = new Point(550, 400);
        } else {
            size = new Point(900, 400);
        }
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    protected void configureShell(final Shell newShell) {
        super.configureShell(newShell);
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
        if (inputMetaTable == null) {
            composite.setLayout(new FillLayout());
            outputMetaView = new MetadataTableEditorView(composite, SWT.NONE, new MetadataTableEditor(outputMetaTable,
                    titleOutput));
            // outputMetaView.getTableViewerCreator().layout();
        } else {
            GridLayout gridLayout = new GridLayout(3, false);
            composite.setLayout(gridLayout);
            inputMetaView = new MetadataTableEditorView(composite, SWT.NONE, new MetadataTableEditor(inputMetaTable,
                    titleInput + " (Input)"));

            // inputMetaView.getTableViewerCreator().setVerticalScroll(true);
            inputMetaView.setGridDataSize(size.x / 2 - 50, size.y - 150);
            if (inputReadOnly) {
                inputMetaView.getTableViewerCreator().getTable().setEnabled(false);
                Button curButton = inputMetaView.getMetadataToolbarEditorView().getAddButton();
                if (curButton != null) {
                    curButton.setEnabled(false);
                }
                curButton = inputMetaView.getMetadataToolbarEditorView().getCopyButton();
                if (curButton != null) {
                    curButton.setEnabled(false);
                }
                curButton = inputMetaView.getMetadataToolbarEditorView().getCutButton();
                if (curButton != null) {
                    curButton.setEnabled(false);
                }
                curButton = inputMetaView.getMetadataToolbarEditorView().getLoadButton();
                if (curButton != null) {
                    curButton.setEnabled(false);
                }
                curButton = inputMetaView.getMetadataToolbarEditorView().getMoveDownButton();
                if (curButton != null) {
                    curButton.setEnabled(false);
                }
                curButton = inputMetaView.getMetadataToolbarEditorView().getMoveUpButton();
                if (curButton != null) {
                    curButton.setEnabled(false);
                }
                curButton = inputMetaView.getMetadataToolbarEditorView().getPasteButton();
                if (curButton != null) {
                    curButton.setEnabled(false);
                }
                curButton = inputMetaView.getMetadataToolbarEditorView().getRemoveButton();
                if (curButton != null) {
                    curButton.setEnabled(false);
                }
            }
            // inputMetaView.getTableViewerCreator().layout();

            Composite buttonComposite = new Composite(composite, SWT.NONE);
            buttonComposite.setLayout(new GridLayout(1, false));
            Button copyToOutput = new Button(buttonComposite, SWT.NONE);
            copyToOutput.setText("=>");
            copyToOutput.setToolTipText("Copy all columns from input schema to output schema");
            copyToOutput.addListener(SWT.Selection, new Listener() {

                public void handleEvent(Event event) {
                    MessageBox messageBox = new MessageBox(parent.getShell(), SWT.APPLICATION_MODAL | SWT.OK
                            | SWT.CANCEL);
                    messageBox.setText("Schema modification");
                    messageBox.setMessage("All columns from the input schema will be transfered to the output schema");
                    if (messageBox.open() == SWT.OK) {
                        outputMetaView.getMetadataTableEditor().clear();
                        outputMetaView.getMetadataTableEditor().addAll(
                                inputMetaView.getMetadataTableEditor().getMetadataColumnList(), 0);
                        outputMetaView.getTableViewerCreator().getTableViewer().refresh();
                    }
                }
            });
            Button copyToInput = new Button(buttonComposite, SWT.NONE);
            copyToInput.setText("<=");
            copyToInput.setToolTipText("Copy all columns from output schema to input schema");
            copyToInput.addListener(SWT.Selection, new Listener() {

                public void handleEvent(Event event) {
                    MessageBox messageBox = new MessageBox(parent.getShell(), SWT.APPLICATION_MODAL | SWT.OK
                            | SWT.CANCEL);
                    messageBox.setText("Schema modification");
                    messageBox.setMessage("All columns from the output schema will be transfered to the input schema");
                    if (messageBox.open() == SWT.OK) {
                        inputMetaView.getMetadataTableEditor().clear();
                        inputMetaView.getMetadataTableEditor().addAll(
                                outputMetaView.getMetadataTableEditor().getMetadataColumnList(), 0);
                        inputMetaView.getTableViewerCreator().getTableViewer().refresh();
                    }
                }
            });

            if (inputReadOnly) {
                copyToInput.setEnabled(false);
            }

            outputMetaView = new MetadataTableEditorView(composite, SWT.NONE, new MetadataTableEditor(outputMetaTable,
                    titleOutput + " (Output)"));
            outputMetaView.setGridDataSize(size.x / 2 - 50, size.y - 150);
            if (outputReadOnly) {
                copyToOutput.setEnabled(false);
                outputMetaView.getTableViewerCreator().getTable().setEnabled(false);
                Button curButton = outputMetaView.getMetadataToolbarEditorView().getAddButton();
                if (curButton != null) {
                    curButton.setEnabled(false);
                }
                curButton = outputMetaView.getMetadataToolbarEditorView().getCopyButton();
                if (curButton != null) {
                    curButton.setEnabled(false);
                }
                curButton = outputMetaView.getMetadataToolbarEditorView().getCutButton();
                if (curButton != null) {
                    curButton.setEnabled(false);
                }
                curButton = outputMetaView.getMetadataToolbarEditorView().getLoadButton();
                if (curButton != null) {
                    curButton.setEnabled(false);
                }
                curButton = outputMetaView.getMetadataToolbarEditorView().getMoveDownButton();
                if (curButton != null) {
                    curButton.setEnabled(false);
                }
                curButton = outputMetaView.getMetadataToolbarEditorView().getMoveUpButton();
                if (curButton != null) {
                    curButton.setEnabled(false);
                }
                curButton = outputMetaView.getMetadataToolbarEditorView().getPasteButton();
                if (curButton != null) {
                    curButton.setEnabled(false);
                }
                curButton = outputMetaView.getMetadataToolbarEditorView().getRemoveButton();
                if (curButton != null) {
                    curButton.setEnabled(false);
                }
            }
            // outputMetaView.getTableViewerCreator().layout();
        }
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
}
