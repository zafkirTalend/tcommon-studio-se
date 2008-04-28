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
package org.talend.dataprofiler.core.ui.editor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.talend.cwm.helper.SwitchHelpers;
import org.talend.cwm.relational.TdColumn;
import org.talend.cwm.softwaredeployment.TdDataProvider;
import org.talend.dataprofiler.core.ImageLib;
import org.talend.dataprofiler.core.PluginConstant;
import org.talend.dataprofiler.core.exception.DataprofilerCoreException;
import org.talend.dataprofiler.core.exception.ExceptionHandler;
import org.talend.dataprofiler.core.helper.AnaResourceFileHelper;
import org.talend.dataprofiler.core.helper.EObjectHelper;
import org.talend.dataprofiler.core.helper.NeedSaveDataProviderHelper;
import org.talend.dataprofiler.core.model.ColumnIndicator;
import org.talend.dataprofiler.core.ui.dialog.ColumnsSelectionDialog;
import org.talend.dataprofiler.core.ui.editor.composite.AnasisColumnTreeViewer;
import org.talend.dataprofiler.core.ui.editor.composite.DataFilterComp;
import org.talend.dataquality.indicators.DataminingType;
import org.talend.dataquality.indicators.Indicator;
import org.talend.dq.analysis.ColumnAnalysisHandler;
import org.talend.utils.sugars.ReturnCode;
import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * @author rli
 * 
 */
public class ColumnMasterDetailsPage extends FormPage implements PropertyChangeListener {

    private static Logger log = Logger.getLogger(ColumnMasterDetailsPage.class);

    private Text nameText;

    private Text purposeText;

    private Text descriptionText;

    private AnasisColumnTreeViewer treeViewer;

    private DataFilterComp dataFilterComp;

    private ColumnAnalysisHandler analysisHandler;

    private ColumnIndicator[] currentColumnIndicators;

    private boolean isDirty = false;

    private String stringDataFilter;

    public ColumnMasterDetailsPage(FormEditor editor, String id, String title) {
        super(editor, id, title);
        this.initAnalysis(editor);
    }

    private void initAnalysis(FormEditor editor) {
        IEditorInput input = editor.getEditorInput();
        analysisHandler = new ColumnAnalysisHandler();
        analysisHandler.setAnalysis(((AnalysisEditorInuput) input).getAnalysis());
        stringDataFilter = analysisHandler.getStringDataFilter();
        EList<ModelElement> analyzedColumns = analysisHandler.getAnalyzedColumns();
        List<ColumnIndicator> columnIndicatorList = new ArrayList<ColumnIndicator>();
        ColumnIndicator currentColumnIndicator;
        for (ModelElement element : analyzedColumns) {
            TdColumn tdColumn = SwitchHelpers.COLUMN_SWITCH.doSwitch(element);
            if (tdColumn == null) {
                continue;
            }
            currentColumnIndicator = new ColumnIndicator(tdColumn);
            DataminingType dataminingType = DataminingType.get(analysisHandler.getDatamingType(tdColumn));
            currentColumnIndicator.setDataminingType(dataminingType == null ? DataminingType.NOMINAL : dataminingType);
            Collection<Indicator> indicatorList = analysisHandler.getIndicators(tdColumn);
            currentColumnIndicator.setIndicators(indicatorList.toArray(new Indicator[indicatorList.size()]));
            columnIndicatorList.add(currentColumnIndicator);
        }
        currentColumnIndicators = columnIndicatorList.toArray(new ColumnIndicator[columnIndicatorList.size()]);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.forms.editor.FormPage#createFormContent(org.eclipse.ui.forms.IManagedForm)
     */
    @Override
    protected void createFormContent(IManagedForm managedForm) {
        final ScrolledForm form = managedForm.getForm();
        FormToolkit toolkit = this.getEditor().getToolkit();
        Composite body = form.getBody();
        form.setText("Analysis Settings");

        // TableWrapLayout layout = new TableWrapLayout();
        body.setLayout(new GridLayout(2, false));

        Composite anasisDataComp = toolkit.createComposite(body);
        GridData anasisData = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);

        anasisDataComp.setLayoutData(anasisData);
        anasisDataComp.setLayout(new GridLayout(1, false));

        createAnalysisMetadataSection(form, toolkit, anasisDataComp);
        createAnalysisColumnsSection(form, toolkit, anasisDataComp);
        createDataFilterSection(form, toolkit, anasisDataComp);

        Composite previewComp = toolkit.createComposite(body);
        GridData previewData = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);

        previewComp.setLayoutData(previewData);
        previewComp.setLayout(new GridLayout());
        createPreviewSection(form, toolkit, previewComp);
    }

    private void createAnalysisMetadataSection(final ScrolledForm form, FormToolkit toolkit, Composite anasisDataComp) {
        Section section = createSection(form, toolkit, anasisDataComp, "Analysis metadata", false,
                "Set the properties of analysis.");
        Composite labelButtonClient = toolkit.createComposite(section);

        labelButtonClient.setLayout(new GridLayout(2, false));

        Label label = toolkit.createLabel(labelButtonClient, "Analysis Name:");
        label.setLayoutData(new GridData());
        nameText = toolkit.createText(labelButtonClient, null, SWT.BORDER);
        GridDataFactory.fillDefaults().grab(true, true).applyTo(nameText);
        nameText.setText(analysisHandler.getName() == null ? PluginConstant.EMPTY_STRING : analysisHandler.getName());
        label = toolkit.createLabel(labelButtonClient, "Analysis Purpose:");
        label.setLayoutData(new GridData());
        purposeText = toolkit.createText(labelButtonClient, null, SWT.BORDER);
        // purposeText.setLayoutData(new GridData());
        GridDataFactory.fillDefaults().grab(true, true).applyTo(purposeText);
        purposeText.setText(analysisHandler.getPurpose() == null ? PluginConstant.EMPTY_STRING : analysisHandler.getPurpose());
        label = toolkit.createLabel(labelButtonClient, "Analysis Description:");
        label.setLayoutData(new GridData());
        descriptionText = toolkit.createText(labelButtonClient, null, SWT.BORDER);
        // descriptionText.setLayoutData(new GridData());
        GridDataFactory.fillDefaults().grab(true, true).applyTo(descriptionText);
        descriptionText.setText(analysisHandler.getDescription() == null ? PluginConstant.EMPTY_STRING : analysisHandler
                .getDescription());
        nameText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                setDirty(true);
                analysisHandler.setName(nameText.getText());
            }

        });
        purposeText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                setDirty(true);
                analysisHandler.setPurpose(purposeText.getText());
            }

        });
        descriptionText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                setDirty(true);
                analysisHandler.setDescription(descriptionText.getText());
            }

        });
        section.setClient(labelButtonClient);
    }

    private void createAnalysisColumnsSection(final ScrolledForm form, FormToolkit toolkit, Composite anasisDataComp) {
        Section section = createSection(form, toolkit, anasisDataComp, "Analyzed Columns", true, null);

        Composite topComp = toolkit.createComposite(section);
        topComp.setLayout(new GridLayout());

        Hyperlink clmnBtn = toolkit.createHyperlink(topComp, "Select columns to analyze", SWT.NONE);
        GridDataFactory.fillDefaults().align(SWT.FILL, SWT.TOP).applyTo(clmnBtn);
        clmnBtn.addHyperlinkListener(new HyperlinkAdapter() {

            public void linkActivated(HyperlinkEvent e) {
                openColumnsSelectionDialog();
            }

        });

        Hyperlink indcBtn = toolkit.createHyperlink(topComp, "Select indicators for each column", SWT.NONE);
        GridDataFactory.fillDefaults().align(SWT.FILL, SWT.TOP).applyTo(indcBtn);
        indcBtn.addHyperlinkListener(new HyperlinkAdapter() {

            public void linkActivated(HyperlinkEvent e) {
                // TODO Auto-generated method stub
                treeViewer.openIndicatorSelectDialog();
            }

        });

        Composite tree = toolkit.createComposite(topComp, SWT.BORDER);
        GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(tree);
        tree.setLayout(new GridLayout());
        ((GridData) tree.getLayoutData()).heightHint = 400;

        treeViewer = new AnasisColumnTreeViewer(tree, currentColumnIndicators, analysisHandler.getAnalysis());
        treeViewer.setDirty(false);
        treeViewer.addPropertyChangeListener(this);

        section.setClient(topComp);

    }

    /**
     * 
     */
    private void openColumnsSelectionDialog() {
        ColumnIndicator[] columnIndicator = treeViewer.getColumnIndicator();
        ColumnsSelectionDialog dialog = new ColumnsSelectionDialog(getSite().getShell(), columnIndicator, "Column Selection");
        if (dialog.open() == Window.OK) {
            Object[] columns = dialog.getResult();
            treeViewer.setInput(columns);
            return;
        }
    }

    private void createPreviewSection(final ScrolledForm form, FormToolkit toolkit, Composite parent) {

        Section section = createSection(form, toolkit, parent, "Preview", false, "");

        Composite sectionClient = toolkit.createComposite(section);
        sectionClient.setLayout(new GridLayout());
        sectionClient.setLayoutData(new GridData(GridData.FILL_BOTH));

        ColumnIndicator[] columnIndicator = treeViewer.getColumnIndicator();

        for (ColumnIndicator column : columnIndicator) {

            ExpandableComposite oneComp = toolkit.createExpandableComposite(sectionClient, ExpandableComposite.TREE_NODE
                    | ExpandableComposite.CLIENT_INDENT);

            oneComp.setText("Column: " + column.getTdColumn().getName());
            oneComp.setLayoutData(new GridData(GridData.FILL_BOTH));
            oneComp.setLayout(new GridLayout());
            final ImageHyperlink image = toolkit.createImageHyperlink(oneComp, SWT.WRAP);
            image.setImage(ImageLib.getImage(ImageLib.REFRESH_IMAGE));
            oneComp.setClient(image);

            oneComp.addExpansionListener(new ExpansionAdapter() {

                /*
                 * (non-Javadoc)
                 * 
                 * @see org.eclipse.ui.forms.events.ExpansionAdapter#expansionStateChanged(org.eclipse.ui.forms.events.ExpansionEvent)
                 */
                @Override
                public void expansionStateChanged(ExpansionEvent e) {

                    form.reflow(true);
                }

            });
        }

        section.setClient(sectionClient);
    }

    /**
     * @param form
     * @param toolkit
     * @param anasisDataComp
     */
    private void createDataFilterSection(final ScrolledForm form, FormToolkit toolkit, Composite anasisDataComp) {
        Section section = createSection(form, toolkit, anasisDataComp, "Data Filter", false, "Edit the data filter:");

        Composite sectionClient = toolkit.createComposite(section);
        dataFilterComp = new DataFilterComp(sectionClient, stringDataFilter);
        dataFilterComp.addPropertyChangeListener(this);
        section.setClient(sectionClient);
    }

    /**
     * @param form
     * @param toolkit
     * @param anasisDataComp
     * @param title
     * @param expanded
     * @param discription
     * @return
     */
    private Section createSection(final ScrolledForm form, FormToolkit toolkit, Composite parent, String title, boolean expanded,
            String discription) {
        final int style = (discription == null) ? Section.TWISTIE | Section.TITLE_BAR : Section.DESCRIPTION | Section.TWISTIE
                | Section.TITLE_BAR;
        Section section = toolkit.createSection(parent, style);

        section.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING));

        section.addExpansionListener(new ExpansionAdapter() {

            public void expansionStateChanged(ExpansionEvent e) {
                form.reflow(true);
            }

        });

        section.setText(title);
        section.setExpanded(expanded);

        // toolkit.createCompositeSeparator(section);

        section.setDescription(discription);
        return section;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.forms.editor.FormPage#doSave(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void doSave(IProgressMonitor monitor) {
        super.doSave(monitor);
        try {
            saveAnalysis();
            this.isDirty = false;
            treeViewer.setDirty(false);
            dataFilterComp.setDirty(false);
        } catch (DataprofilerCoreException e) {
            ExceptionHandler.process(e, Level.ERROR);
            e.printStackTrace();
        }
    }

    /**
     * @param outputFolder
     * @throws DataprofilerCoreException
     */
    private void saveAnalysis() throws DataprofilerCoreException {
        AnalysisEditorInuput editorInput = (AnalysisEditorInuput) this.getEditorInput();
        analysisHandler.clearAnalysis();
        AnaResourceFileHelper.getInstance().clear();
        ColumnIndicator[] columnIndicators = treeViewer.getColumnIndicator();
        List<TdDataProvider> providerList = new ArrayList<TdDataProvider>();
        if (columnIndicators != null) {
            TdDataProvider tdProvider = null;
            for (ColumnIndicator columnIndicator : columnIndicators) {
                analysisHandler.addIndicator(columnIndicator.getTdColumn(), columnIndicator.getIndicators());
                analysisHandler.setDatamingType(columnIndicator.getDataminingType().getLiteral(), columnIndicator.getTdColumn());
                tdProvider = EObjectHelper.getTdDataProvider(columnIndicator.getTdColumn());
                if (!providerList.contains(tdProvider)) {
                    providerList.add(tdProvider);
                }
            }
        }
        if (providerList.size() != 0) {
            analysisHandler.getAnalysis().getContext().setConnection(providerList.get(0));
        }
        analysisHandler.setStringDataFilter(dataFilterComp.getDataFilterString());
        boolean modifiedResourcesSaved = analysisHandler.saveModifiedResources();
        if (!modifiedResourcesSaved) {
            log.error("Problem when saving modified resource.");
        }
        // AnalysisWriter writer = new AnalysisWriter();

        String urlString = PluginConstant.EMPTY_STRING;
        try {
            urlString = editorInput.getFile().toURL().getFile();
            analysisHandler.getAnalysis().setUrl(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        // File file = new File(editorInput.getFile().getParent() + File.separator + fileName);
        // ReturnCode saved = writer.save(analysisHandler.getAnalysis(), file);
        ReturnCode saved = AnaResourceFileHelper.getInstance().save(analysisHandler.getAnalysis());
        if (saved.isOk()) {
            for (TdDataProvider provider : providerList) {
                NeedSaveDataProviderHelper.register(provider.getName(), provider);
            }
            NeedSaveDataProviderHelper.saveAllDataProvider();
            AnaResourceFileHelper.getInstance().setResourceChanged(true);
            log.info("Saved in  " + urlString + " successful");
        } else {
            throw new DataprofilerCoreException("Problem saving file: " + urlString + ": " + saved.getMessage());
        }

        // TODO get the domain constraint, we will see later.
        // Domain dataFilter = getDataFilter(dataManager, (Column) column); // CAST here for test
        // analysisBuilder.addFilterOnData(dataFilter);
        //
        // FolderProvider folderProvider = new FolderProvider();
        // folderProvider.setFolder(new File(outputFolder));
        // DqRepositoryViewService.saveDomain(dataFilter, folderProvider);
    }

    public void setDirty(boolean isDirty) {
        if (this.isDirty != isDirty) {
            this.isDirty = isDirty;
            ((AnalysisEditor) this.getEditor()).firePropertyChange(IEditorPart.PROP_DIRTY);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.forms.editor.FormPage#isDirty()
     */
    @Override
    public boolean isDirty() {
        return super.isDirty() || isDirty || treeViewer.isDirty() || dataFilterComp.isDirty();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.forms.editor.FormPage#dispose()
     */
    @Override
    public void dispose() {
        super.dispose();
        if (this.treeViewer != null) {
            this.treeViewer.removePropertyChangeListener(this);
        }
        if (dataFilterComp != null) {
            this.dataFilterComp.removePropertyChangeListener(this);
        }
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (PluginConstant.ISDIRTY_PROPERTY.equals(evt.getPropertyName())) {
            ((AnalysisEditor) this.getEditor()).firePropertyChange(IEditorPart.PROP_DIRTY);
        } else if (PluginConstant.DATAFILTER_PROPERTY.equals(evt.getPropertyName())) {
            this.analysisHandler.setStringDataFilter((String) evt.getNewValue());
        }

    }

}
