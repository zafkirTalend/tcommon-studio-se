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
package org.talend.core.ui.metadata.editor;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.talend.core.model.action.IAction;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.MetadataColumn;
import org.talend.core.model.metadata.MetadataSchema;
import org.talend.core.model.metadata.editor.MetadataEditor2;
import org.talend.core.model.metadata.editor.MetadataEditorActionFactory;
import org.talend.core.model.metadata.editor.MetadataEditorEvent;
import org.talend.core.model.metadata.editor.MetadataTableEditor;
import org.talend.core.ui.ImageProvider;
import org.talend.core.ui.ImageProvider.EImage;
import org.xml.sax.SAXException;

/**
 * MetadataToolbarEditorView2 must be used.
 * 
 * $Id$
 * 
 */
@Deprecated
public class MetadataToolbarEditorView {

    private Composite toolbar;

    private Button addButton;

    private Button removeButton;

    private Button copyButton;

    private Button cutButton;

    private Button pasteButton;

    private Button moveUpButton;

    private Button moveDownButton;

    private Button loadButton;

    private Button exportButton;

    private MetadataTableEditorView metadataEditorView;

    /**
     * DOC amaumont MatadataToolbarEditor constructor comment.
     * 
     * @param parent
     * @param style
     * @param metadataEditorView
     */
    public MetadataToolbarEditorView(Composite parent, int style, MetadataTableEditorView metadatEditorView) {
        toolbar = new Composite(parent, style);
        toolbar.setLayout(new RowLayout(SWT.HORIZONTAL));
        this.metadataEditorView = metadatEditorView;
        createComponents();
        addListeners();
    }

    /**
     * DOC amaumont Comment method "addListeners".
     */
    private void addListeners() {
        addButton.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                if (metadataEditorView.getMetadataTableEditor() != null) {
                    metadataEditorView.getTableViewerCreator().getTable().setFocus();
                    MetadataEditorEvent metadataEditorEvent = new MetadataEditorEvent(MetadataEditorEvent.TYPE.ADD);
                    MetadataColumn metadataColumn = new MetadataColumn();
                    MetadataTableEditor metadataTableEditor = metadataEditorView.getMetadataTableEditor();
                    metadataColumn.setLabel(metadataTableEditor.getNextGeneratedColumnName());
                    metadataEditorEvent.entries.add(metadataColumn);
                    Table metadataEditorTable = metadataEditorView.getTableViewerCreator().getTable();
                    metadataEditorEvent.entriesIndices = metadataEditorTable.getSelectionIndices();
                    IAction action = MetadataEditorActionFactory.getInstance().getAction(metadataEditorView,
                            metadataEditorEvent);
                    action.run(metadataEditorEvent);
                    metadataEditorView.getTableViewerCreator().getTableViewer().refresh();
                }
            }
        });

        removeButton.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                if (metadataEditorView.getMetadataTableEditor() != null) {
                    int index = metadataEditorView.getTableViewerCreator().getTable().getSelectionIndex();
                    metadataEditorView.getTableViewerCreator().getTable().setFocus();
                    MetadataEditorEvent metadataEditorEvent = new MetadataEditorEvent(MetadataEditorEvent.TYPE.REMOVE);
                    metadataEditorEvent.entriesIndices = metadataEditorView.getTableViewerCreator().getTable()
                            .getSelectionIndices();
                    IAction action = MetadataEditorActionFactory.getInstance().getAction(metadataEditorView,
                            metadataEditorEvent);
                    action.run(metadataEditorEvent);
                    if ((index) < metadataEditorView.getTableViewerCreator().getTable().getItemCount()) {
                        metadataEditorView.getTableViewerCreator().getTable().setSelection(index);
                    } else if (metadataEditorView.getTableViewerCreator().getTable().getItemCount() != 0) {
                        metadataEditorView.getTableViewerCreator().getTable().setSelection(
                                metadataEditorView.getTableViewerCreator().getTable().getItemCount() - 1);
                    }
                    metadataEditorView.getTableViewerCreator().getTableViewer().refresh();
                }
            }
        });

        moveUpButton.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                if (metadataEditorView.getMetadataTableEditor() != null) {
                    metadataEditorView.getTableViewerCreator().getTable().setFocus();
                    MetadataEditorEvent metadataEditorEvent = new MetadataEditorEvent(MetadataEditorEvent.TYPE.MOVE_UP);
                    metadataEditorEvent.entriesIndices = metadataEditorView.getTableViewerCreator().getTable()
                            .getSelectionIndices();
                    IAction action = MetadataEditorActionFactory.getInstance().getAction(metadataEditorView,
                            metadataEditorEvent);
                    action.run(metadataEditorEvent);
                    metadataEditorView.getTableViewerCreator().getTableViewer().refresh();
                }
            }
        });

        moveDownButton.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                if (metadataEditorView.getMetadataTableEditor() != null) {
                    metadataEditorView.getTableViewerCreator().getTable().setFocus();
                    MetadataEditorEvent metadataEditorEvent = new MetadataEditorEvent(
                            MetadataEditorEvent.TYPE.MOVE_DOWN);
                    metadataEditorEvent.entriesIndices = metadataEditorView.getTableViewerCreator().getTable()
                            .getSelectionIndices();
                    IAction action = MetadataEditorActionFactory.getInstance().getAction(metadataEditorView,
                            metadataEditorEvent);
                    action.run(metadataEditorEvent);
                    metadataEditorView.getTableViewerCreator().getTableViewer().refresh();
                }
            }
        });

        loadButton.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                FileDialog dial = new FileDialog(toolbar.getShell(), SWT.NONE);
                dial.setFilterExtensions(new String[] { "*.xml" });
                String fileName = dial.open();
                if ((fileName != null) && (!fileName.equals(""))) {
                    File file = new File(fileName);
                    if (file != null) {
                        MetadataTableEditor newEditor = metadataEditorView.getMetadataTableEditor();
                        if (newEditor != null) {
                            try {
                                metadataEditorView.getTableViewerCreator().getTable().setFocus();
                                IMetadataTable oldTable = newEditor.getMetadataTable();

                                IMetadataTable metadataEditorTable = MetadataSchema.loadMetadaFromFile(file, oldTable);
                                newEditor.setMetadataTable(metadataEditorTable);
                                metadataEditorView.setMetadataTableEditor(newEditor);
                                metadataEditorView.getTableViewerCreator().getTableViewer().refresh();

                            } catch (ParserConfigurationException e) {
                                openMessageError("Parsing XML Error : " + e.getMessage());
                            } catch (SAXException e) {
                                openMessageError("Parsing XML Error : " + e.getMessage());
                            } catch (IOException e) {
                                openMessageError("File Error : " + e.getMessage());
                            }
                        }
                    }
                }
            }

            private void openMessageError(String errorText) {
                MessageBox msgBox = new MessageBox(toolbar.getShell());
                msgBox.setText("Error Occurred");
                msgBox.setMessage(errorText);
                msgBox.open();
            }
        });

        exportButton.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                FileDialog dial = new FileDialog(toolbar.getShell(), SWT.SAVE);
                dial.setFilterExtensions(new String[] { "*.xml" });
                String fileName = dial.open();
                if ((fileName != null) && (!fileName.equals(""))) {
                    File file = new File(fileName);
                    try {
                        file.createNewFile();
                        if (file != null) {
                            MetadataTableEditor newEditor = metadataEditorView.getMetadataTableEditor();
                            if (newEditor != null) {
                                // get all the columns from the table
                                IMetadataTable oldTable = newEditor.getMetadataTable();

                                MetadataSchema.saveMetadataColumnToFile(file, oldTable);
                            }
                        }
                    } catch (IOException e) {
                        openMessageError("File Error : " + e.getMessage());
                    } catch (ParserConfigurationException e) {
                        openMessageError("Parsing XML Error : " + e.getMessage());
                    }
                }
            }

            private void openMessageError(String errorText) {
                MessageBox msgBox = new MessageBox(toolbar.getShell());
                msgBox.setText("Error Occurred");
                msgBox.setMessage(errorText);
                msgBox.open();
            }
        });
    }

    /**
     * DOC amaumont Comment method "createComponents".
     */
    private void createComponents() {

        addButton = new Button(toolbar, SWT.PUSH);
        addButton.setToolTipText("Add");
        addButton.setImage(ImageProvider.getImage(EImage.ADD_ICON));

        removeButton = new Button(toolbar, SWT.PUSH);
        removeButton.setToolTipText("Remove");
        removeButton.setImage(ImageProvider.getImage(EImage.MINUS_ICON));

        // copyButton = new Button(toolbar, SWT.PUSH);
        // copyButton.setText("Copy");
        //
        // cutButton = new Button(toolbar, SWT.PUSH);
        // cutButton.setText("Cut");
        //
        // pasteButton = new Button(toolbar, SWT.PUSH);
        // pasteButton.setText("Paste");
        //
        moveUpButton = new Button(toolbar, SWT.PUSH);
        moveUpButton.setToolTipText("Move up");
        moveUpButton.setImage(ImageProvider.getImage(EImage.UP_ICON));

        moveDownButton = new Button(toolbar, SWT.PUSH);
        moveDownButton.setToolTipText("Move down");
        moveDownButton.setImage(ImageProvider.getImage(EImage.DOWN_ICON));

        loadButton = new Button(toolbar, SWT.PUSH);
        loadButton.setText("Load");
        loadButton.setImage(ImageProvider.getImage(EImage.LOAD_ICON));

        exportButton = new Button(toolbar, SWT.PUSH);
        exportButton.setText("Export");
        exportButton.setImage(ImageProvider.getImage(EImage.EXPORT_ICON));
    }

    public Button getAddButton() {
        return this.addButton;
    }

    public Button getCopyButton() {
        return this.copyButton;
    }

    public Button getCutButton() {
        return this.cutButton;
    }

    public Button getLoadButton() {
        return this.loadButton;
    }

    public Button getMoveDownButton() {
        return this.moveDownButton;
    }

    public Button getMoveUpButton() {
        return this.moveUpButton;
    }

    public Button getPasteButton() {
        return this.pasteButton;
    }

    public Button getRemoveButton() {
        return this.removeButton;
    }

}