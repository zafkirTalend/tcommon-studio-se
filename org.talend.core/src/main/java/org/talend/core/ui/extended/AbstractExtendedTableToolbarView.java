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
package org.talend.core.ui.extended;

import org.eclipse.swt.widgets.Composite;
import org.talend.commons.ui.swt.extended.macrotable.AbstractExtendedTableViewer;
import org.talend.core.ui.extended.button.AddPushButton;
import org.talend.core.ui.extended.button.CopyPushButton;
import org.talend.core.ui.extended.button.MoveDownPushButton;
import org.talend.core.ui.extended.button.MoveDownPushButtonForExtendedTable;
import org.talend.core.ui.extended.button.MoveUpPushButton;
import org.talend.core.ui.extended.button.MoveUpPushButtonForExtendedTable;
import org.talend.core.ui.extended.button.PastPushButton;
import org.talend.core.ui.extended.button.RemovePushButton;
import org.talend.core.ui.extended.button.RemovePushButtonForExtendedTable;

/**
 * DOC cantoine class global comment. Detailled comment <br/>
 * 
 * TGU same purpose as TargetSchemaToolbarEditorView2 but uses EMF model directly $Id:
 * TargetSchemaToolbarEditorView2.java,v 1.1 2006/08/02 19:43:45 cantoine Exp $
 * 
 */
public abstract class AbstractExtendedTableToolbarView extends AbstractExtendedToolbar {

    private AddPushButton addButton;

    private RemovePushButton removeButton;

//    private Button copyButton;
//
//    // private Button cutButton;
//
//    private Button pasteButton;

    private MoveUpPushButton moveUpButton;

    private MoveDownPushButton moveDownButton;

    private CopyPushButton copyButton;

    private PastPushButton pastButton;

//    private Button loadButton;
//
//    private Button exportButton;

    
    
    /**
     * DOC amaumont MatadataToolbarEditor constructor comment.
     * 
     * @param parent
     * @param style
     * @param targetSchemaEditorView
     */
    public AbstractExtendedTableToolbarView(Composite parent, int style, AbstractExtendedTableViewer extendedTableViewer) {
        super(parent, style, extendedTableViewer);
//        // Force the height of the toolbars
//        GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
//        gridData.minimumHeight = 40;
//        gridData.heightHint = 40;
//        toolbar.setLayoutData(gridData);
    }

//    /**
//     * DOC amaumont Comment method "addListeners".
//     */
//    private void addListeners() {
//        addButton.addListener(SWT.Selection, new Listener() {
//
//            public void handleEvent(Event event) {
//                TableViewerCreator tableViewerCreator = targetSchemaEditorView.getTableViewerCreator();
//                targetSchemaEditorView.createEntry(tableViewerCreator.getTable().getSelectionIndices());
//                tableViewerCreator.getTableViewer().refresh();
//            }
//
//        });
//
//        removeButton.addListener(SWT.Selection, new Listener() {
//
//            public void handleEvent(Event event) {
//                if (targetSchemaEditorView.getTargetSchemaEditor() != null) {
//                    int index = targetSchemaEditorView.getTableViewerCreator().getTable().getSelectionIndex();
//                    targetSchemaEditorView.getTableViewerCreator().getTable().setFocus();
//                    TargetSchemaEditorEvent targetSchemaEditorEvent = new TargetSchemaEditorEvent();
//                    targetSchemaEditorEvent.type = TargetSchemaEditorEvent.TYPE.REMOVE;
//                    targetSchemaEditorEvent.entriesIndices = targetSchemaEditorView.getTableViewerCreator().getTable()
//                            .getSelectionIndices();
//                    IAction action = TargetSchemaEditorActionFactory2.getInstance().getAction(targetSchemaEditorView,
//                            targetSchemaEditorEvent);
//                    action.run(targetSchemaEditorEvent);
//                    if ((index) < targetSchemaEditorView.getTableViewerCreator().getTable().getItemCount()) {
//                        targetSchemaEditorView.getTableViewerCreator().getTable().setSelection(index);
//                    } else if (targetSchemaEditorView.getTableViewerCreator().getTable().getItemCount() != 0) {
//                        targetSchemaEditorView.getTableViewerCreator().getTable().setSelection(
//                                targetSchemaEditorView.getTableViewerCreator().getTable().getItemCount() - 1);
//                    }
//                    targetSchemaEditorView.getTableViewerCreator().getTableViewer().refresh();
//                }
//            }
//        });
//
//        copyButton.addListener(SWT.Selection, new Listener() {
//
//            public void handleEvent(Event event) {
//                if (targetSchemaEditorView.getTargetSchemaEditor() != null) {
//                    targetSchemaEditorView.getTableViewerCreator().getTable().setFocus();
//                    TargetSchemaEditorEvent targetSchemaEditorEvent = new TargetSchemaEditorEvent();
//                    targetSchemaEditorEvent.type = TargetSchemaEditorEvent.TYPE.COPY;
//                    targetSchemaEditorEvent.entriesIndices = targetSchemaEditorView.getTableViewerCreator().getTable()
//                            .getSelectionIndices();
//                    IAction action = TargetSchemaEditorActionFactory2.getInstance().getAction(targetSchemaEditorView,
//                            targetSchemaEditorEvent);
//                    action.run(targetSchemaEditorEvent);
//                    targetSchemaEditorView.getTableViewerCreator().getTableViewer().refresh();
//                }
//            }
//        });
//
//        pasteButton.addListener(SWT.Selection, new Listener() {
//
//            public void handleEvent(Event event) {
//                if (targetSchemaEditorView.getTargetSchemaEditor() != null) {
//                    targetSchemaEditorView.getTableViewerCreator().getTable().setFocus();
//                    TargetSchemaEditorEvent targetSchemaEditorEvent = new TargetSchemaEditorEvent();
//                    targetSchemaEditorEvent.type = TargetSchemaEditorEvent.TYPE.PASTE;
//                    targetSchemaEditorEvent.entriesIndices = targetSchemaEditorView.getTableViewerCreator().getTable()
//                            .getSelectionIndices();
//                    IAction action = TargetSchemaEditorActionFactory2.getInstance().getAction(targetSchemaEditorView,
//                            targetSchemaEditorEvent);
//                    action.run(targetSchemaEditorEvent);
//                    targetSchemaEditorView.getTableViewerCreator().getTableViewer().refresh();
//                }
//            }
//        });
//
//        moveUpButton.addListener(SWT.Selection, new Listener() {
//
//            public void handleEvent(Event event) {
//                if (targetSchemaEditorView.getTargetSchemaEditor() != null) {
//                    targetSchemaEditorView.getTableViewerCreator().getTable().setFocus();
//                    TargetSchemaEditorEvent targetSchemaEditorEvent = new TargetSchemaEditorEvent();
//                    targetSchemaEditorEvent.type = TargetSchemaEditorEvent.TYPE.MOVE_UP;
//                    targetSchemaEditorEvent.entriesIndices = targetSchemaEditorView.getTableViewerCreator().getTable()
//                            .getSelectionIndices();
//                    IAction action = TargetSchemaEditorActionFactory2.getInstance().getAction(targetSchemaEditorView,
//                            targetSchemaEditorEvent);
//                    action.run(targetSchemaEditorEvent);
//                    targetSchemaEditorView.getTableViewerCreator().getTableViewer().refresh();
//                }
//            }
//        });
//
//        moveDownButton.addListener(SWT.Selection, new Listener() {
//
//            public void handleEvent(Event event) {
//                if (targetSchemaEditorView.getTargetSchemaEditor() != null) {
//                    targetSchemaEditorView.getTableViewerCreator().getTable().setFocus();
//                    TargetSchemaEditorEvent targetSchemaEditorEvent = new TargetSchemaEditorEvent();
//                    targetSchemaEditorEvent.type = TargetSchemaEditorEvent.TYPE.MOVE_DOWN;
//                    targetSchemaEditorEvent.entriesIndices = targetSchemaEditorView.getTableViewerCreator().getTable()
//                            .getSelectionIndices();
//                    IAction action = TargetSchemaEditorActionFactory2.getInstance().getAction(targetSchemaEditorView,
//                            targetSchemaEditorEvent);
//                    action.run(targetSchemaEditorEvent);
//                    targetSchemaEditorView.getTableViewerCreator().getTableViewer().refresh();
//                }
//            }
//        });
//
//        loadButton.addListener(SWT.Selection, new Listener() {
//
//            public void handleEvent(Event event) {
//                FileDialog dial = new FileDialog(toolbar.getShell(), SWT.OPEN);
//                dial.setFilterExtensions(new String[] { "*.xml" });
//                String fileName = dial.open();
//                if ((fileName != null) && (!fileName.equals(""))) {
//                    File file = new File(fileName);
//                    if (file != null) {
//                        XPathNodeSchemaModel newEditor = targetSchemaEditorView.getTargetSchemaEditor();
//                        if (newEditor != null) {
//                            try {
//                                // remove all the columns from the table
//                                TargetSchemaEditorEvent targetSchemaEditorEvent = new TargetSchemaEditorEvent();
//                                targetSchemaEditorEvent.type = TargetSchemaEditorEvent.TYPE.REMOVE;
//                                int[] index = new int[targetSchemaEditorView.getTableViewerCreator().getTable().getItemCount()];
//                                for (int i = 0; i < targetSchemaEditorView.getTableViewerCreator().getTable().getItemCount(); i++) {
//                                    index[i] = i;
//                                }
//                                targetSchemaEditorEvent.entriesIndices = index;
//                                IAction action = TargetSchemaEditorActionFactory2.getInstance().getAction(targetSchemaEditorView,
//                                        targetSchemaEditorEvent);
//                                action.run(targetSchemaEditorEvent);
//
//                                // load the schema
//                                // PTODO CAN : XMLFILE ATTENTION AVEC SCHEMATARGET
//                                List<SchemaTarget> loadSchemaTarget = MetadataSchema.loadTargetSchemaColumnFromFile(file);
//
//                                // Add Columnsof Schema to the table
//                                if (!loadSchemaTarget.isEmpty()) {
//                                    targetSchemaEditorEvent.type = TargetSchemaEditorEvent.TYPE.ADD;
//                                    List<SchemaTarget> newList = new ArrayList<SchemaTarget>();
//                                    for (int i = 0; i < loadSchemaTarget.size(); i++) {
//                                        // check the unicity of label
//                                        newList.add(loadSchemaTarget.get(i));
//                                        targetSchemaEditorEvent.entries.add(loadSchemaTarget.get(i));
//                                    }
//                                    Table targetSchemaEditorTable = targetSchemaEditorView.getTableViewerCreator().getTable();
//                                    targetSchemaEditorEvent.entriesIndices = targetSchemaEditorTable.getSelectionIndices();
//                                    action = TargetSchemaEditorActionFactory2.getInstance().getAction(targetSchemaEditorView,
//                                            targetSchemaEditorEvent);
//                                    action.run(targetSchemaEditorEvent);
//                                }
//
//                                // Refresh the table
//                                targetSchemaEditorView.getTableViewerCreator().getTable().setFocus();
//                                targetSchemaEditorView.getTableViewerCreator().getTable().deselectAll();
//                                targetSchemaEditorView.getTableViewerCreator().getTableViewer().refresh();
//                                targetSchemaEditorView.getTableViewerCreator().layout();
//
//                            } catch (ParserConfigurationException e) {
//                                openMessageError("Parsing XML Error : " + e.getMessage());
//                            } catch (SAXException e) {
//                                openMessageError("Parsing XML Error : " + e.getMessage());
//                            } catch (IOException e) {
//                                openMessageError("File Error : " + e.getMessage());
//                            }
//                        }
//                    }
//                }
//            }
//
//            private void openMessageError(String errorText) {
//                MessageBox msgBox = new MessageBox(toolbar.getShell());
//                msgBox.setText("Error Occurred");
//                msgBox.setMessage(errorText);
//                msgBox.open();
//            }
//        });
//
//        exportButton.addListener(SWT.Selection, new Listener() {
//
//            public void handleEvent(Event event) {
//                FileDialog dial = new FileDialog(toolbar.getShell(), SWT.SAVE);
//                dial.setFilterExtensions(new String[] { "*.xml" });
//                String fileName = dial.open();
//                if ((fileName != null) && (!fileName.equals(""))) {
//                    File file = new File(fileName);
//                    try {
//                        file.createNewFile();
//                        if (file != null) {
//                            XPathNodeSchemaModel newEditor = targetSchemaEditorView.getTargetSchemaEditor();
//                            if (newEditor != null) {
//                                // get all the columns from the table
//                                // PTODO CAN : XMLFILE
//                                org.talend.core.model.metadata.builder.connection.MetadataSchema oldTable = newEditor
//                                        .getMetadataSchema();
//                                MetadataSchema.saveSchemaTargetToFile(file, oldTable);
//                            }
//                        }
//                    } catch (IOException e) {
//                        openMessageError("File Error : " + e.getMessage());
//                    } catch (ParserConfigurationException e) {
//                        openMessageError("Parsing XML Error : " + e.getMessage());
//                    }
//                }
//            }
//
//            private void openMessageError(String errorText) {
//                MessageBox msgBox = new MessageBox(toolbar.getShell());
//                msgBox.setText("Error Occurred");
//                msgBox.setMessage(errorText);
//                msgBox.open();
//            }
//        });
//    }

    protected void createComponents(Composite parent) {
        addButton = createAddPushButton();
        
        removeButton = createRemovePushButton();

        moveUpButton = createMoveUpPushButton();
        
        moveDownButton = createMoveDownPushButton();
        
        copyButton = createCopyPushButton();
        
        pastButton = createPastPushButton();

//        loadButton = new Button(toolbar, SWT.PUSH);
//        loadButton.setToolTipText("Import");
//        loadButton.setImage(ImageProvider.getImage(EImage.IMPORT_ICON));
//
//        exportButton = new Button(toolbar, SWT.PUSH);
//        exportButton.setToolTipText("Export");
//        exportButton.setImage(ImageProvider.getImage(EImage.EXPORT_ICON));
    }
    
    protected abstract AddPushButton createAddPushButton();
    
    
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
//        return new CopyPushButtonForExtendedTable(toolbar, extendedTableViewer);
        return null;
    }
    
    protected PastPushButton createPastPushButton() {
//        return new PastPushButtonForExtendedTable(toolbar, extendedTableViewer);
        return null;
    }
    

    /**
     * DOC ocarbone Comment method "setReadOnly". setReadOnly(boolean) is equivalent to setEnabled(boolean) all the
     * buttons.
     * 
     * @param b
     */
    public void setReadOnly(boolean b) {
//        addButton.setEnabled(!b);
//        removeButton.setEnabled(!b);
//        copyButton.setEnabled(!b);
//        pasteButton.setEnabled(!b);
//        moveUpButton.setEnabled(!b);
//        moveDownButton.setEnabled(!b);
//        loadButton.setEnabled(!b);
//        exportButton.setEnabled(!b);
    }

    
    /**
     * Getter for addButton.
     * @return the addButton
     */
    public AddPushButton getAddButton() {
        return this.addButton;
    }

    
    /**
     * Getter for copyButton.
     * @return the copyButton
     */
    public CopyPushButton getCopyButton() {
        return this.copyButton;
    }

    
    /**
     * Getter for moveDownButton.
     * @return the moveDownButton
     */
    public MoveDownPushButton getMoveDownButton() {
        return this.moveDownButton;
    }

    
    /**
     * Getter for moveUpButton.
     * @return the moveUpButton
     */
    public MoveUpPushButton getMoveUpButton() {
        return this.moveUpButton;
    }

    
    /**
     * Getter for pastButton.
     * @return the pastButton
     */
    public PastPushButton getPastButton() {
        return this.pastButton;
    }

    
    /**
     * Getter for removeButton.
     * @return the removeButton
     */
    public RemovePushButton getRemoveButton() {
        return this.removeButton;
    }
    
    
    
}
