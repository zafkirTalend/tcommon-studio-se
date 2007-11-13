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
package org.talend.core.ui.metadata.dialog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
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
import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.i18n.Messages;
import org.talend.core.model.metadata.Dbms;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.MetadataTool;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.metadata.builder.connection.GenericSchemaConnection;
import org.talend.core.model.metadata.editor.MetadataTableEditor;
import org.talend.core.model.process.EParameterFieldType;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.INodeConnector;
import org.talend.core.ui.metadata.editor.AbstractMetadataTableEditorView;
import org.talend.core.ui.metadata.editor.MetadataTableEditorView;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class MetadataDialog extends Dialog {

    private static final String DATABASE_LABEL = "Database"; //$NON-NLS-1$

    private static final String ELT_LABEL = "ELT"; //$NON-NLS-1$

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

    private INode inputNode;

    private INode outputNode;

    private ThreeCompositesSashForm compositesSachForm;

    public MetadataDialog(Shell parent, IMetadataTable inputMetaTable, INode inputNode, IMetadataTable outputMetaTable,
            INode outputNode, CommandStack commandStack) {
        super(parent);
        this.inputMetaTable = inputMetaTable;
        this.inputNode = inputNode;
        if (inputNode != null) {
            this.titleInput = inputMetaTable.getTableName() + " (Input)";
            INodeConnector connector = inputNode.getConnectorFromName(inputMetaTable.getAttachedConnector());
            if (!connector.isBuiltIn()) {
                this.titleInput = inputNode.getLabel() + " (Input - " + connector.getLinkName() + ")";
            }
        }
        this.outputNode = outputNode;
        if (outputNode != null) {
            this.titleOutput = outputNode.getLabel();
        }
        this.outputMetaTable = outputMetaTable;
        this.commandStack = commandStack;

        if (inputMetaTable == null) {
            size = new Point(550, 400);
        } else {
            size = new Point(1000, 400);
        }
    }

    public MetadataDialog(Shell parent, IMetadataTable outputMetaTable, INode outputNode, CommandStack commandStack) {
        this(parent, null, null, outputMetaTable, outputNode, commandStack);
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
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, false);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
    }

    public static void initializeMetadataTableView(MetadataTableEditorView metaView, INode node, IMetadataTable metadataTable) {
        boolean dbComponent = false;
        boolean hasMappingType = false;
        boolean eltComponent = node.getComponent().getFamily().startsWith(ELT_LABEL);

        boolean hasRepositoryDbSchema = false;
        if (node.getComponent().getFamily().startsWith(DATABASE_LABEL) || eltComponent) {
            dbComponent = true;
            for (IElementParameter currentParam : node.getElementParameters()) {
                if (currentParam.getField().equals(EParameterFieldType.MAPPING_TYPE)) {
                    metaView.setCurrentDbms((String) currentParam.getValue());
                    hasMappingType = true;
                }
            }

            IElementParameter schemaParam = node.getElementParameter("SCHEMA_TYPE");
            if (!hasMappingType && schemaParam != null) { // if there is no
                // mapping type,
                // then check if a
                // db
                // repository schema is used
                String schemaType = (String) schemaParam.getValue();
                if (schemaType.equals("REPOSITORY")) {
                    // repository mode
                    String metaRepositoryName = (String) node.getElementParameter("REPOSITORY_SCHEMA_TYPE").getValue();
                    Connection connection = MetadataTool.getConnectionFromRepository(metaRepositoryName);

                    boolean isDatabaseConnection = connection instanceof DatabaseConnection;
                    boolean isGenericSchemaConnection = connection instanceof GenericSchemaConnection;
                    if (isDatabaseConnection || isGenericSchemaConnection) {
                        hasRepositoryDbSchema = true;

                        for (IMetadataColumn column : metadataTable.getListColumns()) {
                            if ((column.getType() == "") || (column.getType() == null)) {
                                hasRepositoryDbSchema = false;
                            }
                        }
                        String componentDbType = "";
                        for (IElementParameter param : (List<IElementParameter>) node.getElementParameters()) {
                            if (param.getRepositoryValue() != null) {
                                if (param.getRepositoryValue().equals("TYPE")) {
                                    componentDbType = (String) param.getValue();
                                }
                            }
                        }

                        // if we don't support yet the db type for the mapping
                        // type, then don't display.
                        if (!EDatabaseTypeName.supportDbType(componentDbType)) {
                            hasRepositoryDbSchema = false;
                        }
                        String componentProduct = EDatabaseTypeName.getTypeFromDbType(componentDbType).getProduct();
                        String connectionProduct = null;
                        if (isDatabaseConnection) {
                            connectionProduct = ((DatabaseConnection) connection).getProductId();
                            if (!componentProduct.equals(connectionProduct)) {
                                hasRepositoryDbSchema = false;
                                // the component don't support this product so don't
                                // display.
                            } else {
                                metaView.setCurrentDbms(((DatabaseConnection) connection).getDbmsId());
                            }
                        }

                        else if (isGenericSchemaConnection) {
                            String mappingTypeId = ((GenericSchemaConnection) connection).getMappingTypeId();

                            if (mappingTypeId != null) {
                                connectionProduct = mappingTypeId;
                                metaView.setCurrentDbms(connectionProduct);
                            }
                        }
                    }
                }
            }
        }
        metaView.setShowDbTypeColumn(hasMappingType || eltComponent, false, hasMappingType
                || (dbComponent && !hasRepositoryDbSchema));
        metaView.setShowDbColumnName(dbComponent && (!eltComponent), hasMappingType || (dbComponent && !hasRepositoryDbSchema));

        // hide the talend type for ELT components
        metaView.setShowTalendTypeColumn(!eltComponent);
        // hide the pattern for ELT components
        metaView.setShowPatternColumn(!eltComponent);
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

            initializeMetadataTableView(outputMetaView, outputNode, outputMetaTable);
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

            metadataTableEditor = new MetadataTableEditor(inputMetaTable, titleInput); //$NON-NLS-1$
            inputMetaView = new MetadataTableEditorView(compositesSachForm.getLeftComposite(), SWT.NONE, metadataTableEditor,
                    inputReadOnly, true, true, false);
            initializeMetadataTableView(inputMetaView, inputNode, inputMetaTable);
            inputMetaView.initGraphicComponents();
            inputMetaView.getExtendedTableViewer().setCommandStack(commandStack);

            inputMetaView.setGridDataSize(size.x / 2 - 50, size.y - 150);

            Label label1 = new Label(compositesSachForm.getMidComposite(), SWT.NONE);
            GridDataFactory.swtDefaults().hint(42, 18).applyTo(label1);
            Composite buttonComposite = new Composite(compositesSachForm.getMidComposite(), SWT.BORDER);
            Label label2 = new Label(compositesSachForm.getMidComposite(), SWT.NONE);
            GridDataFactory.swtDefaults().hint(42, 36).applyTo(label2);

            gridLayout = new GridLayout(1, true);
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
                        MetadataTool.copyTable(getInputMetaData(), getOutputMetaData());
                        outputMetaView.getTableViewerCreator().getTableViewer().refresh();
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
                        MetadataTool.copyTable(getOutputMetaData(), getInputMetaData());
                        inputMetaView.getTableViewerCreator().getTableViewer().refresh();
                    }
                }
            });

            if (inputReadOnly || inputMetaTable.isReadOnly()) {
                copyToInput.setEnabled(false);
            }

            outputMetaView = new MetadataTableEditorView(compositesSachForm.getRightComposite(), SWT.NONE,
                    new MetadataTableEditor(outputMetaTable, titleOutput + " (Output)"), outputReadOnly, true, true, //$NON-NLS-1$
                    false);
            initializeMetadataTableView(outputMetaView, outputNode, outputMetaTable);
            outputMetaView.initGraphicComponents();
            outputMetaView.getExtendedTableViewer().setCommandStack(commandStack);
            outputMetaView.setGridDataSize(size.x / 2 - 50, size.y - 150);

            if (outputReadOnly || outputMetaTable.isReadOnly()) {
                copyToOutput.setEnabled(false);
            }
            compositesSachForm.setGridDatas();
            CustomTableManager.addCustomManagementToTable(inputMetaView, inputReadOnly);
            CustomTableManager.addCustomManagementToToolBar(inputMetaView, inputMetaTable, inputReadOnly, outputMetaView,
                    outputMetaTable, outputNode.getComponent().isSchemaAutoPropagated());
        }
        CustomTableManager.addCustomManagementToTable(outputMetaView, outputReadOnly);
        CustomTableManager.addCustomManagementToToolBar(outputMetaView, outputMetaTable, outputReadOnly, inputMetaView,
                inputMetaTable, false);
        metadataTableEditor.addModifiedBeanListener(new IModifiedBeanListener<IMetadataColumn>() {

            public void handleEvent(ModifiedBeanEvent<IMetadataColumn> event) {
                if ((inputMetaTable != null) && outputMetaTable.isReadOnly()
                        && outputNode.getComponent().isSchemaAutoPropagated()) {
                    MetadataTool.copyTable(inputMetaTable, outputMetaTable);
                    outputMetaView.getTableViewerCreator().getTableViewer().refresh();
                }
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

    /**
     * DOC Administrator Comment method "getMappingTypeLabelById".
     * 
     * @param mappingTypeId
     * @return
     */
    private static String getMappingTypeLabelById(String mappingTypeId) {
        Dbms[] dbmsArray = MetadataTalendType.getAllDbmsArray();

        for (int i = 0; i < dbmsArray.length; i++) {
            String indexId = dbmsArray[i].getId();
            if (mappingTypeId.equals(indexId)) {
                return dbmsArray[i].getLabel();
            }
        }
        return null;
    }

}
