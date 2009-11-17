// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.mdm.ui.wizard;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.datatools.enablement.oda.xml.util.ui.ATreeNode;
import org.eclipse.datatools.enablement.oda.xml.util.ui.XPathPopulationUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.ui.command.CommandStackForComposite;
import org.talend.commons.ui.swt.dialogs.ErrorDialogWidthDetailArea;
import org.talend.commons.ui.swt.formtools.Form;
import org.talend.commons.ui.swt.formtools.LabelledCheckboxCombo;
import org.talend.commons.ui.swt.formtools.UtilsButton;
import org.talend.commons.ui.swt.tableviewer.IModifiedBeanListener;
import org.talend.commons.ui.swt.tableviewer.ModifiedBeanEvent;
import org.talend.commons.ui.ws.WindowSystem;
import org.talend.commons.utils.data.list.IListenableListListener;
import org.talend.commons.utils.data.list.ListenableListEvent;
import org.talend.commons.utils.encoding.CharsetToolkit;
import org.talend.core.model.metadata.MappingTypeRetriever;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.builder.connection.Concept;
import org.talend.core.model.metadata.builder.connection.ConceptTarget;
import org.talend.core.model.metadata.builder.connection.ConnectionFactory;
import org.talend.core.model.metadata.builder.connection.MDMConnection;
import org.talend.core.model.metadata.builder.connection.MetadataColumn;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.utils.TalendTextUtils;
import org.talend.core.utils.CsvArray;
import org.talend.core.utils.XmlArray;
import org.talend.designer.core.model.utils.emf.talendfile.ContextType;
import org.talend.mdm.webservice.WSDataModel;
import org.talend.mdm.webservice.WSDataModelPK;
import org.talend.mdm.webservice.WSGetDataModel;
import org.talend.mdm.webservice.WSGetUniversePKs;
import org.talend.mdm.webservice.WSPing;
import org.talend.mdm.webservice.WSRegexDataModelPKs;
import org.talend.mdm.webservice.WSUniversePK;
import org.talend.mdm.webservice.XtentisBindingStub;
import org.talend.mdm.webservice.XtentisPort;
import org.talend.mdm.webservice.XtentisServiceLocator;
import org.talend.repository.i18n.Messages;
import org.talend.repository.mdm.model.MDMXSDExtractorFieldModel;
import org.talend.repository.mdm.model.MDMXSDExtractorLoopModel;
import org.talend.repository.mdm.ui.wizard.dnd.MDMLinker;
import org.talend.repository.mdm.ui.wizard.table.ExtractionFieldsWithMDMEditorView;
import org.talend.repository.mdm.ui.wizard.table.ExtractionLoopWithMDMEditorView;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.ProxyRepositoryFactory;
import org.talend.repository.preview.AsynchronousPreviewHandler;
import org.talend.repository.preview.IPreviewHandlerListener;
import org.talend.repository.preview.StoppablePreviewLoader;
import org.talend.repository.ui.swt.preview.ShadowProcessPreview;
import org.talend.repository.ui.swt.utils.IRefreshable;
import org.talend.repository.ui.utils.ConnectionContextHelper;
import org.talend.repository.ui.utils.ShadowProcessHelper;
import org.talend.repository.ui.wizards.metadata.connection.files.xml.TreePopulator;

/**
 * DOC hwang class global comment. Detailled comment
 */
public class MDMXSDFileForm extends AbstractMDMFileStepForm implements IRefreshable {

    private static Logger log = Logger.getLogger(MDMXSDFileForm.class);

    /**
     * Main Fields.
     */

    private transient Tree availableXmlTree;

    private ATreeNode treeNode;

    private MDMXSDExtractorFieldModel fieldsModel;

    private ExtractionLoopWithMDMEditorView loopTableEditorView;

    private ExtractionFieldsWithMDMEditorView fieldsTableEditorView;

    private Button previewButton;

    private Label previewInformationLabel;

    private ShadowProcessPreview xmlFilePreview;

    private Text fileXmlText;

    protected boolean filePathIsDone;

    private UtilsButton cancelButton;

    private boolean readOnly;

    private SashForm xmlToSchemaSash;

    private MDMLinker linker;

    private TreePopulator treePopulator;

    private MDMXSDExtractorLoopModel loopModel;

    private Concept concept;

    private IPreviewHandlerListener previewHandlerListener;

    private static Boolean firstTimeWizardOpened = null;

    private String xsdFilePath;

    private String conceptName;

    private Group schemaTargetGroup;

    private boolean isTemplateExist = false;

    /**
     * Output tab.
     */
    private CTabFolder tabFolder;

    private CTabItem previewTabItem;

    private CTabItem outputTabItem;

    private Composite outputComposite;

    /**
     * Constructor to use by RCP Wizard.
     * 
     * @param Composite
     * @param Wizard
     * @param Style
     */
    public MDMXSDFileForm(Composite parent, ConnectionItem connectionItem) {
        super(parent, connectionItem);
        setupForm(true);

    }

    /**
     * 
     * Initialize value, forceFocus first field.
     */
    @Override
    protected void initialize() {
        initConcepts();
        this.treePopulator = new TreePopulator(availableXmlTree);

        checkFieldsValue();

        if (concept == null) {
            if (getConnection().getSchemas() != null && !getConnection().getSchemas().isEmpty()) {
                concept = (Concept) getConnection().getSchemas().get(0);
                // xmlFilePath = getConnection().getXmlFilePath();
                if (isContextMode()) {
                    ContextType contextType = ConnectionContextHelper.getContextTypeForContextMode(
                            connectionItem.getConnection(), true);
                    xsdFilePath = TalendTextUtils
                            .removeQuotes(ConnectionContextHelper.getOriginalValue(contextType, xsdFilePath));
                }
            } else {
                IProxyRepositoryFactory factory = ProxyRepositoryFactory.getInstance();
                concept = ConnectionFactory.eINSTANCE.createConcept();
                // concept.setLabel(conceptName);
                // concept.setId(factory.getNextId());
                getConnection().getSchemas().add(concept);
            }
        }

        loopModel.setConcept(concept);
        if (concept.getLoopLimit() == null) {
            concept.setLoopLimit(-1);
            XmlArray.setLimitToDefault();
            concept.setLoopLimit(XmlArray.getRowLimit());

        }
        fieldsModel.setConcept(concept.getConceptTargets());
        fieldsTableEditorView.getTableViewerCreator().layout();

        if (isContextMode()) {
            adaptFormToEditable();
        }

    }

    /**
     * DOC ocarbone Comment method "adaptFormToReadOnly".
     */
    @Override
    protected void adaptFormToReadOnly() {
        // readOnly = isReadOnly();
    }

    @Override
    protected void adaptFormToEditable() {
        super.adaptFormToEditable();
        loopTableEditorView.setReadOnly(isContextMode());
        this.fieldsTableEditorView.setReadOnly(isContextMode());
    }

    @Override
    protected void addFields() {

        // compositeFile Main Fields
        // Composite mainComposite = Form.startNewGridLayout(this, 1);
        SashForm mainComposite = new SashForm(this, SWT.VERTICAL | SWT.SMOOTH);
        mainComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

        if (firstTimeWizardOpened == null) {
            firstTimeWizardOpened = Boolean.TRUE;
        } else if (firstTimeWizardOpened.equals(Boolean.TRUE)) {
            firstTimeWizardOpened = Boolean.FALSE;
        }

        // Splitter
        this.xmlToSchemaSash = new SashForm(mainComposite, SWT.HORIZONTAL | SWT.SMOOTH);
        xmlToSchemaSash.setLayoutData(new GridData(GridData.FILL_BOTH));
        xmlToSchemaSash.setBackgroundMode(SWT.INHERIT_FORCE);

        addGroupXmlFileSettings(xmlToSchemaSash, 400, 110);
        addGroupSchemaTarget(xmlToSchemaSash, 300, 110);
        xmlToSchemaSash.setWeights(new int[] { 40, 60 });

        SashForm sash2 = new SashForm(mainComposite, SWT.HORIZONTAL | SWT.SMOOTH);
        sash2.setLayoutData(new GridData(GridData.FILL_BOTH));

        addGroupFileViewer(sash2, 400, 210);
        addGroupXmlViewer(sash2, 300, 110);

        if (!isInWizard()) {
            // Bottom Button
            Composite compositeBottomButton = Form.startNewGridLayout(this, 2, false, SWT.CENTER, SWT.CENTER);
            // Button Cancel
            cancelButton = new UtilsButton(compositeBottomButton, Messages.getString("CommonWizard.cancel"), WIDTH_BUTTON_PIXEL, //$NON-NLS-1$
                    HEIGHT_BUTTON_PIXEL);
        }
        addUtilsButtonListeners();
    }

    /**
     * add Field to Group Xml File Settings.
     * 
     * @param mainComposite
     * @param form
     * @param width
     * @param height
     */
    private void addGroupXmlFileSettings(final Composite mainComposite, final int width, final int height) {

        // Group Schema Viewer
        Group group = Form.createGroup(mainComposite, 1, Messages.getString("XmlFileStep1.sourceSchema"), height); //$NON-NLS-1$
        group.setBackground(null);

        availableXmlTree = new Tree(group, SWT.MULTI | SWT.BORDER);

        // availableXmlTree.setVisible(false);
        GridData gridData2 = new GridData(GridData.FILL_BOTH);
        availableXmlTree.setLayoutData(gridData2);
    }

    private void addGroupSchemaTarget(final Composite mainComposite, final int width, final int height) {
        // Group Schema Viewer
        schemaTargetGroup = Form.createGroup(mainComposite, 1, Messages.getString("XmlFileStep1.groupSchemaTarget"), height); //$NON-NLS-1$

        // ///////////////////////////////////////////
        // to correct graphic bug under Linux-GTK when the wizard is opened the first time
        if (WindowSystem.isGTK() && firstTimeWizardOpened.equals(Boolean.TRUE)) {
            schemaTargetGroup.addListener(SWT.Paint, new Listener() {

                public void handleEvent(Event event) {
                    Point offsetPoint = event.display.map(linker.getBgDrawableComposite(), schemaTargetGroup, new Point(0, 0));
                    linker.setOffset(offsetPoint);
                    linker.drawBackground(event.gc);
                }

            });
        }
        // ///////////////////////////////////////////

        schemaTargetGroup.setBackgroundMode(SWT.INHERIT_FORCE);

        CommandStackForComposite commandStack = new CommandStackForComposite(schemaTargetGroup);

        loopModel = new MDMXSDExtractorLoopModel("Xpath loop expression"); //$NON-NLS-1$

        loopTableEditorView = new ExtractionLoopWithMDMEditorView(loopModel, schemaTargetGroup);
        loopTableEditorView.getExtendedTableViewer().setCommandStack(commandStack);
        GridData data2 = new GridData(GridData.FILL_HORIZONTAL);
        data2.heightHint = 90;

        final Composite loopTableEditorComposite = loopTableEditorView.getMainComposite();
        loopTableEditorComposite.setLayoutData(data2);
        loopTableEditorComposite.setBackground(null);
        // ///////////////////////////////////////////
        // to correct graphic bug under Linux-GTK when the wizard is opened the first time
        if (WindowSystem.isGTK() && firstTimeWizardOpened.equals(Boolean.TRUE)) {
            loopTableEditorComposite.addListener(SWT.Paint, new Listener() {

                public void handleEvent(Event event) {
                    Point offsetPoint = event.display.map(linker.getBgDrawableComposite(), loopTableEditorComposite, new Point(0,
                            0));
                    linker.setOffset(offsetPoint);
                    linker.drawBackground(event.gc);
                }

            });
        }
        // ///////////////////////////////////////////

        // Messages.getString("FileStep3.metadataDescription")
        fieldsModel = new MDMXSDExtractorFieldModel("Fields to extract"); //$NON-NLS-1$
        fieldsTableEditorView = new ExtractionFieldsWithMDMEditorView(fieldsModel, schemaTargetGroup);
        fieldsTableEditorView.getExtendedTableViewer().setCommandStack(commandStack);
        final Composite fieldTableEditorComposite = fieldsTableEditorView.getMainComposite();
        fieldTableEditorComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
        fieldTableEditorComposite.setBackground(null);
        // ///////////////////////////////////////////
        // to correct graphic bug under Linux-GTK when the wizard is opened the first time
        if (WindowSystem.isGTK() && firstTimeWizardOpened.equals(Boolean.TRUE)) {
            fieldTableEditorComposite.addListener(SWT.Paint, new Listener() {

                public void handleEvent(Event event) {
                    Point offsetPoint = event.display.map(linker.getBgDrawableComposite(), fieldTableEditorComposite, new Point(
                            0, 0));
                    linker.setOffset(offsetPoint);
                    linker.drawBackground(event.gc);
                }

            });
        }
        // ///////////////////////////////////////////

    }

    /**
     * add Field to Group File Viewer.
     * 
     * @param parent
     * @param form
     * @param width
     * @param height
     */
    private void addGroupFileViewer(final Composite parent, final int width, int height) {
        // composite Xml File Preview
        // Group previewGroup = Form.createGroup(parent, 1, Messages.getString("FileStep2.groupPreview"), height);
        // //$NON-NLS-1$
        // Composite compositeXmlFilePreviewButton = Form.startNewDimensionnedGridLayout(previewGroup, 4, width,
        // HEIGHT_BUTTON_PIXEL);
        // height = height - HEIGHT_BUTTON_PIXEL - 15;

        tabFolder = new CTabFolder(parent, SWT.BORDER);
        tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

        previewTabItem = new CTabItem(tabFolder, SWT.BORDER);
        previewTabItem.setText("Preview"); //$NON-NLS-1$
        outputTabItem = new CTabItem(tabFolder, SWT.BORDER);
        outputTabItem.setText("Output"); //$NON-NLS-1$

        Composite previewComposite = Form.startNewGridLayout(tabFolder, 1);
        outputComposite = Form.startNewGridLayout(tabFolder, 1);

        // previewGroup.setLayout(new GridLayout());

        Composite preivewButtonPart = new Composite(previewComposite, SWT.NONE);
        preivewButtonPart.setLayout(new GridLayout(3, false));
        preivewButtonPart.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        // Preview Button
        previewButton = new Button(preivewButtonPart, SWT.NONE);
        previewButton.setText(Messages.getString("FileStep2.refreshPreview")); //$NON-NLS-1$
        previewButton.setSize(WIDTH_BUTTON_PIXEL, HEIGHT_BUTTON_PIXEL);

        XmlArray.setLimitToDefault();
        previewInformationLabel = new Label(previewComposite, SWT.NONE);
        previewInformationLabel.setForeground(getDisplay().getSystemColor(SWT.COLOR_BLUE));

        // Xml File Preview
        xmlFilePreview = new ShadowProcessPreview(previewComposite, null, width, height - 10);
        xmlFilePreview.newTablePreview();

        previewTabItem.setControl(previewComposite);
        outputTabItem.setControl(outputComposite);
        tabFolder.setSelection(previewTabItem);
        tabFolder.pack();
    }

    /**
     * add Field to Group File Viewer.
     * 
     * @param parent
     * @param form
     * @param width
     * @param height
     */
    private void addGroupXmlViewer(final Composite parent, final int width, int height) {
        // Group File Viewer
        Group group = Form.createGroup(parent, 1, Messages.getString("FileStep1.groupFileViewer"), height); //$NON-NLS-1$
        Composite compositeFileViewer = Form.startNewDimensionnedGridLayout(group, 1, width, HEIGHT_BUTTON_PIXEL);

        fileXmlText = new Text(compositeFileViewer, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
        GridData gridData = new GridData(GridData.FILL_BOTH);
        gridData.minimumWidth = width;
        gridData.minimumHeight = HEIGHT_BUTTON_PIXEL;
        fileXmlText.setLayoutData(gridData);
        fileXmlText
                .setToolTipText(Messages.getString("FileStep1.fileViewerTip1") + " " + TreePopulator.getMaximumRowsToPreview() + " " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        + Messages.getString("FileStep1.fileViewerTip2")); //$NON-NLS-1$
        fileXmlText.setEditable(false);
        fileXmlText.setText(Messages.getString("FileStep1.fileViewerAlert")); //$NON-NLS-1$
    }

    /**
     * create ProcessDescription and set it.
     * 
     * WARNING ::field FieldSeparator, RowSeparator, EscapeChar and TextEnclosure are surround by double quote.
     * 
     * @param getConnection()
     * 
     * @return processDescription
     */
    // private ProcessDescription getProcessDescription(boolean defaultContext) {
    // XmlFileConnection connection2 = OtherConnectionContextUtils.getOriginalValueConnection(getConnection(),
    // this.connectionItem, isContextMode(), defaultContext);
    // ProcessDescription processDescription = ShadowProcessHelper.getProcessDescription(connection2);
    // return processDescription;
    // }
    /**
     * clear the table preview.
     */
    void clearPreview() {
        xmlFilePreview.clearTablePreview();
    }

    /**
     * refreshPreview use ShadowProcess to refresh the preview.
     */
    void refreshPreview() {
        clearPreview();

        // if no file, the process don't be executed
        if (xsdFilePath == null || xsdFilePath.equals("")) { //$NON-NLS-1$
            previewInformationLabel.setText("   " + Messages.getString("FileStep2.filePathIncomplete")); //$NON-NLS-1$ //$NON-NLS-2$
            return;
        }

        // if incomplete settings, , the process don't be executed
        if (!checkFieldsValue()) {
            previewInformationLabel.setText("   " + Messages.getString("FileStep2.settingsIncomplete")); //$NON-NLS-1$ //$NON-NLS-2$
            return;
        }

        // set row limit
        if (concept.getLoopLimit() > 0 && concept.getLoopLimit() < XmlArray.getRowLimit()) {
            XmlArray.setRowLimit(concept.getLoopLimit());
        }

        previewInformationLabel.setText("   " + Messages.getString("FileStep2.previewProgress")); //$NON-NLS-1$ //$NON-NLS-2$

        AsynchronousPreviewHandler<CsvArray> previewHandler = null;
        try {
            previewHandler = ShadowProcessHelper.createPreviewHandler();
        } catch (CoreException e) {
            previewInError(e);
            return;
        }

        StoppablePreviewLoader previewLoader = new StoppablePreviewLoader<CsvArray>(previewHandler, previewInformationLabel) {

            /*
             * (non-Javadoc)
             * 
             * @see
             * org.talend.repository.ui.wizards.metadata.connection.files.xml.StoppablePreviewLoader#previewEnded(java
             * .lang.Object)
             */
            @Override
            protected void previewEnded(CsvArray result) {
                xmlFilePreview.refreshTablePreview(result, false, ((Concept) getConnection().getSchemas().get(0))
                        .getConceptTargets());
            }

            @Override
            public void previewInError(CoreException e) {
                MDMXSDFileForm.this.previewInError(e);
            }

        };

        // previewLoader.load(getProcessDescription(false));

    }

    /**
     * DOC amaumont Comment method "previewInFileError".
     * 
     * @param e
     */
    protected void previewInError(CoreException e) {

        String errorMessage = null;
        if (e != null) {
            errorMessage = e.getMessage();
        }

        previewInformationLabel.setText("   " + Messages.getString("FileStep2.previewFailure")); //$NON-NLS-1$ //$NON-NLS-2$
        new ErrorDialogWidthDetailArea(previewInformationLabel.getShell(), PID, Messages.getString("FileStep2.previewFailure"), //$NON-NLS-1$
                errorMessage);
        log.error(Messages.getString("FileStep2.previewFailure") + " " + errorMessage); //$NON-NLS-1$ //$NON-NLS-2$

    }

    /**
     * Main Fields addControls.
     */
    @Override
    protected void addFieldsListeners() {
        // add listener to tableMetadata (listen the event of the toolbars)
        fieldsTableEditorView.getExtendedTableModel().addAfterOperationListListener(new IListenableListListener() {

            public void handleEvent(ListenableListEvent event) {
                checkFieldsValue();
            }
        });

        fieldsTableEditorView.getExtendedTableModel().addModifiedBeanListener(new IModifiedBeanListener<ConceptTarget>() {

            public void handleEvent(ModifiedBeanEvent<ConceptTarget> event) {

                updateStatus(IStatus.OK, null);
                String msg = fieldsTableEditorView.checkColumnNames();
                if (!StringUtils.isEmpty(msg)) {
                    updateStatus(IStatus.ERROR, msg);
                }
            }
        });
    }

    /**
     * get the standby XPath expression.
     * 
     * @return
     */
    protected List getSelectedXPath(TreeItem selected) {
        // TreeItem selected = this.selectedItem;
        String rootPath = ""; //$NON-NLS-1$
        if (selected.getData() instanceof ATreeNode) {
            ATreeNode node = (ATreeNode) selected.getData();
            rootPath = "/" + selected.getText(); //$NON-NLS-1$
        }

        while (selected.getParentItem() != null) {
            selected = selected.getParentItem();
            if (selected.getData() instanceof ATreeNode) {
                ATreeNode node = (ATreeNode) selected.getData();
                if (node.getType() == ATreeNode.ELEMENT_TYPE) {
                    rootPath = "/" + selected.getText() + rootPath; //$NON-NLS-1$
                }
            }
        }
        return XPathPopulationUtil.populateRootPath(rootPath);

    }

    /**
     * Ensures that fields are set. Update checkEnable / use to checkConnection().
     * 
     * @return
     */
    @Override
    protected boolean checkFieldsValue() {
        previewInformationLabel.setText("   " + Messages.getString("FileStep2.settingsIncomplete")); //$NON-NLS-1$ //$NON-NLS-2$
        updateStatus(IStatus.OK, null);
        previewButton.setEnabled(false);

        String msg = fieldsTableEditorView.checkColumnNames();
        if (!StringUtils.isEmpty(msg)) {
            updateStatus(IStatus.ERROR, msg);
            return false;
        }

        // Labelled Checkbox Combo (Row to Skip and Limit)
        ArrayList<LabelledCheckboxCombo> labelledCheckboxCombo2Control = new ArrayList<LabelledCheckboxCombo>();

        Iterator<LabelledCheckboxCombo> iCheckboxCombo;
        LabelledCheckboxCombo labelledCheckboxCombo;

        for (iCheckboxCombo = labelledCheckboxCombo2Control.iterator(); iCheckboxCombo.hasNext();) {
            labelledCheckboxCombo = iCheckboxCombo.next();
            // if the checkbox is checked, check Numeric value
            if (labelledCheckboxCombo.getCheckbox().getSelection()) {
                if (labelledCheckboxCombo.getText() == "") { //$NON-NLS-1$
                    updateStatus(IStatus.ERROR, labelledCheckboxCombo.getLabelText()
                            + Messages.getString("FileStep2.mustBePrecised")); //$NON-NLS-1$
                    return false;
                }
            }
        }

        previewInformationLabel.setText(""); //$NON-NLS-1$

        // String pathStr = getConnection().getXmlFilePath();
        if (isContextMode()) {
            ContextType contextType = ConnectionContextHelper.getContextTypeForContextMode(connectionItem.getConnection(), true);
            // pathStr = TalendTextUtils.removeQuotes(ConnectionContextHelper.getOriginalValue(contextType, pathStr));
        }
        if (xsdFilePath != null && xsdFilePath.toLowerCase().endsWith(".xsd")) { //$NON-NLS-1$

            previewButton.setEnabled(false);
        } else {
            previewButton.setEnabled(true);
        }
        updateStatus(IStatus.OK, null);
        return true;
    }

    /**
     * addButtonControls.
     * 
     * @param cancelButton
     */
    @Override
    protected void addUtilsButtonListeners() {

        // Event PreviewButton
        previewButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                if (!previewButton.getText().equals(Messages.getString("FileStep2.wait"))) { //$NON-NLS-1$
                    previewButton.setText(Messages.getString("FileStep2.wait")); //$NON-NLS-1$
                    if (// getConnection().getXmlFilePath() != null
                    //&& !getConnection().getXmlFilePath().equals("") //$NON-NLS-1$
                    getConnection().getSchemas() != null
                            && !getConnection().getSchemas().isEmpty()
                            && ((Concept) getConnection().getSchemas().get(0)).getLoopExpression() != null
                            && !("").equals(((Concept) getConnection().getSchemas().get(0)).getLoopExpression()) //$NON-NLS-1$
                            && ((Concept) getConnection().getSchemas().get(0)).getConceptTargets() != null
                            && !((Concept) getConnection().getSchemas().get(0)).getConceptTargets().isEmpty()) {
                        refreshPreview();
                    } else {
                        previewButton.setText(Messages.getString("FileStep2.refreshPreview")); //$NON-NLS-1$
                        if (!previewButton.getEnabled()) {
                            // new ErrorDialogWidthDetailArea(getShell(), PID, Messages.getString("FileStep2.noresult"),
                            // Messages //$NON-NLS-1$
                            // .getString("FileStep2.noresultDetailMessage")); //$NON-NLS-1$

                            Display.getDefault().asyncExec(new Runnable() {

                                public void run() {
                                    handleErrorOutput(outputComposite, tabFolder, outputTabItem);

                                }
                            });
                            log.error(Messages.getString("FileStep2.noresult")); //$NON-NLS-1$
                            previewButton.setEnabled(true);
                        } else {
                            previewButton.setEnabled(false);
                        }
                    }
                } else {
                    previewButton.setText(Messages.getString("FileStep2.refreshPreview")); //$NON-NLS-1$
                }
            }
        });

        if (cancelButton != null) {
            // Event CancelButton
            cancelButton.addSelectionListener(new SelectionAdapter() {

                @Override
                public void widgetSelected(final SelectionEvent e) {
                    getShell().close();
                }
            });
        }
    }

    /**
     * ftang Comment method "handleErrorOutput".
     */
    private void handleErrorOutput(Composite outputComposite, CTabFolder tabFolder, CTabItem outputTabItem) {
        Font font = new Font(Display.getDefault(), "courier", 8, SWT.NONE); //$NON-NLS-1$

        StyledText text = new StyledText(outputComposite, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.READ_ONLY);
        GridData gridData = new GridData(GridData.FILL_BOTH);
        text.setLayoutData(gridData);
        outputComposite.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));

        String errorInfo = Messages.getString("FileStep2.noresult") + "\n"; //$NON-NLS-1$ //$NON-NLS-2$
        errorInfo = errorInfo + Messages.getString("FileStep2.noresultDetailMessage") + "\n"; //$NON-NLS-1$ //$NON-NLS-2$

        text.setText(errorInfo);
        text.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_RED));
        text.setFont(font);

        tabFolder.setSelection(outputTabItem);
    }

    /**
     * checkFileFieldsValue active fileViewer if file exist.
     * 
     * @throws IOException
     */
    private void checkFilePathAndManageIt() {
        updateStatus(IStatus.OK, null);
        filePathIsDone = false;
        //        if (getConnection().getXmlFilePath() == "") { //$NON-NLS-1$
        // fileXmlText
        //                    .setText(Messages.getString("FileStep1.fileViewerTip1") + " " + TreePopulator.getMaximumRowsToPreview() + " " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        //                            + Messages.getString("FileStep1.fileViewerTip2")); //$NON-NLS-1$
        // } else {
        fileXmlText.setText(Messages.getString("FileStep1.fileViewerProgress")); //$NON-NLS-1$

        StringBuilder previewRows = new StringBuilder();
        BufferedReader in = null;

        // String pathStr = "";

        try {
            // pathStr = getConnection().getXmlFilePath();
            if (isContextMode()) {
                ContextType contextType = ConnectionContextHelper.getContextTypeForContextMode(connectionItem.getConnection(),
                        true);
                // pathStr = TalendTextUtils.removeQuotes(ConnectionContextHelper.getOriginalValue(contextType,
                // pathStr));
            }

            File file = new File(xsdFilePath);
            Charset guessedCharset = CharsetToolkit.guessEncoding(file, 4096);

            String str;
            in = new BufferedReader(new InputStreamReader(new FileInputStream(xsdFilePath), guessedCharset.displayName()));

            while ((str = in.readLine()) != null) {
                previewRows.append(str + "\n"); //$NON-NLS-1$
            }

            // show lines
            fileXmlText.setText(new String(previewRows));
            filePathIsDone = true;

        } catch (Exception e) {
            String msgError = Messages.getString("FileStep1.filepath") + " \"" + fileXmlText.getText().replace("\\\\", "\\") + "\"\n"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 

            //$NON-NLS-4$ //$NON-NLS-5$
            if (e instanceof FileNotFoundException) {
                msgError = msgError + Messages.getString("FileStep1.fileNotFoundException"); //$NON-NLS-1$
            } else if (e instanceof EOFException) {
                msgError = msgError + Messages.getString("FileStep1.eofException"); //$NON-NLS-1$
            } else if (e instanceof IOException) {
                msgError = msgError + Messages.getString("FileStep1.fileLocked"); //$NON-NLS-1$
            } else {
                msgError = msgError + Messages.getString("FileStep1.noExist"); //$NON-NLS-1$
            }
            fileXmlText.setText(msgError);
            if (!isReadOnly()) {
                updateStatus(IStatus.ERROR, msgError);
            }
            log.error(msgError + " " + e.getMessage()); //$NON-NLS-1$
        } finally {
            String msgError = Messages.getString("FileStep1.filepath") + " \"" + fileXmlText.getText().replace("\\\\", "\\") + "\"\n"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 

            //$NON-NLS-4$ //$NON-NLS-5$
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                msgError = msgError + Messages.getString("FileStep1.fileLocked"); //$NON-NLS-1$
            }
        }
        checkFieldsValue();
        // }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.widgets.Control#setVisible(boolean)
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {// super.isVisible()) {
            if (this.linker != null) {
                this.linker.removeAllLinks();
            }

            // String pathStr = getConnection().getXmlFilePath();
            if (isContextMode()) {
                ContextType contextType = ConnectionContextHelper.getContextTypeForContextMode(connectionItem.getConnection(),
                        true);
                // pathStr = TalendTextUtils.removeQuotes(ConnectionContextHelper.getOriginalValue(contextType,
                // pathStr));
            }
            this.treePopulator.populateTree(xsdFilePath, treeNode);

            ScrollBar verticalBar = availableXmlTree.getVerticalBar();
            if (verticalBar != null) {
                verticalBar.setSelection(0);
            }
            // fix bug: when the xml file is changed, the linker doesn't work.
            resetStatusIfNecessary();

            if (this.linker == null) {
                this.linker = new MDMLinker(this.xmlToSchemaSash, isTemplateExist);
                this.linker.init(availableXmlTree, loopTableEditorView, fieldsTableEditorView, treePopulator);
                loopTableEditorView.setLinker(this.linker);
                fieldsTableEditorView.setLinker(this.linker);
            } else {
                this.linker.init(treePopulator);
                this.linker.createLinks();
            }
            checkFilePathAndManageIt();

            if (xsdFilePath != null && xsdFilePath.endsWith(".xsd")) { //$NON-NLS-1$
                previewButton.setEnabled(false);
                previewButton.setText(Messages.getString("XmlFileStep2Form.previewNotAvailable")); //$NON-NLS-1$
                previewButton.computeSize(SWT.DEFAULT, SWT.DEFAULT);
                previewButton.getParent().layout();
            }
            if (isContextMode()) {
                adaptFormToEditable();
            }

        }
    }

    private void resetStatusIfNecessary() {
        // String curXmlPath = getConnection().getXmlFilePath();
        String oraginalPath = "";
        if (xsdFilePath != null) {
            // change xml file
            if (isContextMode()) {
                ContextType contextType = ConnectionContextHelper.getContextTypeForContextMode(connectionItem.getConnection(),
                        true);
                // oraginalPath = TalendTextUtils.removeQuotes(ConnectionContextHelper.getOriginalValue(contextType,
                // xsdFilePath));
            }
            // if (!xsdFilePath.equals(xsdFilePath) && !xsdFilePath.equals(oraginalPath)) {
            // clear command stack
            CommandStackForComposite commandStack = new CommandStackForComposite(schemaTargetGroup);
            loopTableEditorView.getExtendedTableViewer().setCommandStack(commandStack);
            fieldsTableEditorView.getExtendedTableViewer().setCommandStack(commandStack);

            getConnection().getSchemas().remove(concept);

            // IProxyRepositoryFactory factory = ProxyRepositoryFactory.getInstance();
            concept = ConnectionFactory.eINSTANCE.createConcept();
            // if (conceptName != null) {
            // concept.setLabel(conceptName);
            // concept.setId(factory.getNextId());
            // }
            getConnection().getSchemas().add(concept);

            loopModel.setConcept(concept);
            XmlArray.setLimitToDefault();
            concept.setLoopLimit(XmlArray.getRowLimit());

            fieldsModel.setConcept(concept.getConceptTargets());
            fieldsTableEditorView.getTableViewerCreator().layout();

            // reset linker
            if (linker != null) {
                linker.init(treePopulator);
            }
            xmlFilePreview.removePreviewContent();
            // }
        }
        // if (isContextMode()) {
        // xmlFilePath = oraginalPath;
        // } else {
        // xmlFilePath = curXmlPath;
        // }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.swt.utils.IRefreshable#refresh()
     */
    public void refresh() {
        refreshPreview();
    }

    protected void createTable() {
        List<MetadataColumn> metadataColumns = new ArrayList<MetadataColumn>();
        MetadataTable metadataTable = ConnectionFactory.eINSTANCE.createMetadataTable();
        IProxyRepositoryFactory factory = ProxyRepositoryFactory.getInstance();
        metadataTable.setLabel(conceptName);
        metadataTable.setId(factory.getNextId());

        MappingTypeRetriever retriever = MetadataTalendType.getMappingTypeRetriever("xsd_id"); //$NON-NLS-1$
        List<ConceptTarget> targetList = concept.getConceptTargets();

        for (ConceptTarget target : targetList) {
            String relativeXpath = target.getRelativeLoopExpression();
            String fullPath = target.getSchema().getLoopExpression();
            if (fullPath.contains("/") && metadataTable.getSourceName() == null) { //$NON-NLS-1$
                String source = fullPath.split("/")[1]; //$NON-NLS-1$
                metadataTable.setSourceName(source);
            }

            if (isContextMode()) {
                ContextType contextType = ConnectionContextHelper.getContextTypeForContextMode(connectionItem.getConnection(),
                        true);
                fullPath = TalendTextUtils.removeQuotes(ConnectionContextHelper.getOriginalValue(contextType, fullPath));
            }
            // adapt relative path
            String[] relatedSplitedPaths = relativeXpath.split("\\.\\./"); //$NON-NLS-1$
            if (relatedSplitedPaths.length > 1) {
                int pathsToRemove = relatedSplitedPaths.length - 1;
                String[] fullPathSplited = fullPath.split("/"); //$NON-NLS-1$
                fullPath = ""; //$NON-NLS-1$
                for (int i = 1; i < (fullPathSplited.length - pathsToRemove); i++) {
                    fullPath += "/" + fullPathSplited[i]; //$NON-NLS-1$
                }
                fullPath += "/" + relatedSplitedPaths[pathsToRemove]; //$NON-NLS-1$
            } else {
                fullPath += "/" + relativeXpath; //$NON-NLS-1$
            }
            TreeItem treeItem = treePopulator.getTreeItem(fullPath);
            if (treeItem != null) {
                ATreeNode curNode = (ATreeNode) treeItem.getData();
                MetadataColumn metadataColumn = ConnectionFactory.eINSTANCE.createMetadataColumn();
                metadataColumn.setLabel(target.getTargetName());
                // metadataColumn.setTalendType(target.getTargetName());

                if (curNode == null || retriever == null) {
                    metadataColumn.setTalendType(MetadataTalendType.getDefaultTalendType());
                } else {

                    metadataColumn.setTalendType(retriever.getDefaultSelectedTalendType("xs:" + curNode.getOriginalDataType())); //$NON-NLS-1$
                }
                if (!metadataTable.getColumns().contains(metadataColumn)) {
                    metadataTable.getColumns().add(metadataColumn);
                }
                metadataColumns.add(metadataColumn);
            }
        }
        if (!getConnection().getTables().contains(metadataTable)) {
            getConnection().getTables().add(metadataTable);
        }
    }

    // private void createColumns() {
    //
    // }

    private void initConcepts() {
        // IPath temp = new Path(System.getProperty("user.dir")).append("temp");
        // xsdFilePath = temp.toOSString() + "\\template.xsd";
        MDMConnection mdmConn = (MDMConnection) connectionItem.getConnection();
        XtentisBindingStub stub = null;
        String userName = mdmConn.getUsername();
        String password = mdmConn.getPassword();
        String server = mdmConn.getServer();
        String port = mdmConn.getPort();
        String universe = mdmConn.getUniverse();
        String datamodel = mdmConn.getDatamodel();
        WSUniversePK[] universes = null;
        WSUniversePK universePK = null;
        WSDataModelPK modelPK = null;
        XtentisServiceLocator xtentisService = new XtentisServiceLocator();
        xtentisService.setXtentisPortEndpointAddress("http://" + server + ":" + port + "/talend/TalendPort"); //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-1$
        try {
            XtentisPort xtentisWS = xtentisService.getXtentisPort();
            stub = (XtentisBindingStub) xtentisWS;
            stub.setUsername(userName);
            stub.setPassword(password);
            stub.ping(new WSPing());
            universes = stub.getUniversePKs(new WSGetUniversePKs("")); //$NON-NLS-1$
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }
        if (universes == null) {
            return;
        }
        for (int i = 0; i < universes.length; i++) {
            if (universes[i].getPk().equals(universe)) {
                universePK = universes[i];
                break;
            }
        }
        if (universePK != null && universe != null && !"".equals(universe)) { //$NON-NLS-1$
            stub.setUsername(universe + "/" + userName); //$NON-NLS-1$
            stub.setPassword(password);
        } else {
            stub.setUsername(userName);
            stub.setPassword(password);
        }
        try {
            WSDataModelPK[] models = stub.getDataModelPKs(new WSRegexDataModelPKs(""));//$NON-NLS-1$
            if (models == null) {
                return;
            }
            for (int i = 0; i < models.length; i++) {
                if (models[i].getPk().equals(datamodel)) {
                    modelPK = models[i];
                    break;
                }
            }
            if (modelPK == null) {
                return;
            }
            IPath tempPath = new Path(System.getProperty("user.dir")).append("temp"); //$NON-NLS-1$ //$NON-NLS-1$
            File tempFile = tempPath.toFile();
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            xsdFilePath = tempPath.toOSString();
            WSDataModel model = stub.getDataModel(new WSGetDataModel(modelPK));
            if (model == null) {
                return;
            }
            writeInFile(model.getXsdSchema(), xsdFilePath);
            // List<String> list = MDMUtil.getConcepts(MDMUtil.getXSDSchema(model.getXsdSchema()));
            // concepts.addAll(list);
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }
        // return concepts;
    }

    private void writeInFile(String schema, String path) {
        File file = new File(path + "\\template.xsd"); //$NON-NLS-1$
        xsdFilePath = file.getAbsolutePath();
        StringReader reader = new StringReader(schema);

        try {
            FileWriter writer = new FileWriter(file);
            char[] c = new char[1024];
            int l = 0;
            while ((l = reader.read(c)) != -1) {
                writer.write(c, 0, l);
            }
            writer.close();
            reader.close();
            isTemplateExist = file.exists();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setConceptName(String name) {
        this.conceptName = name;
    }

}
