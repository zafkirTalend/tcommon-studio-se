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

import java.io.File;

import org.apache.log4j.Logger;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.talend.cwm.management.api.DqRepositoryViewService;
import org.talend.cwm.management.api.FolderProvider;
import org.talend.dataprofiler.core.exception.DataprofilerCoreException;
import org.talend.dataprofiler.core.model.ColumnIndicator;
import org.talend.dataprofiler.core.ui.dialog.ColumnsSelectionDialog;
import org.talend.dataprofiler.core.ui.editor.composite.AnasisColumnTreeViewer;
import org.talend.dataprofiler.core.ui.editor.composite.DataFilterComp;
import org.talend.dataquality.analysis.Analysis;
import org.talend.dataquality.analysis.AnalysisType;
import org.talend.dataquality.domain.Domain;
import org.talend.dataquality.indicators.Indicator;
import org.talend.dq.analysis.AnalysisBuilder;
import org.talend.dq.analysis.AnalysisWriter;
import org.talend.dq.analysis.ColumnAnalysisExecutor;
import org.talend.dq.analysis.IAnalysisExecutor;
import org.talend.utils.sugars.ReturnCode;
import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.resource.relational.Column;

/**
 * @author rli
 * 
 */
public class MasterDetailsPage extends FormPage {

    private static Logger log = Logger.getLogger(MasterDetailsPage.class);

    private Text nameText;

    private Text purposeText;

    private Text descriptionText;

    private AnasisColumnTreeViewer treeViewer;

    private DataFilterComp dataFilterComp;

    public MasterDetailsPage(FormEditor editor, String id, String title) {
        super(editor, id, title);
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
        form.setText("Anasis Settings");

        // TableWrapLayout layout = new TableWrapLayout();
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        body.setLayout(layout);

        Composite anasisDataComp = toolkit.createComposite(body);
        GridData anasisData = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
        anasisData.horizontalSpan = 1;
        anasisDataComp.setLayoutData(anasisData);
        anasisDataComp.setLayout(new GridLayout(1, true));

        createAnalysisMetadataSection(form, toolkit, anasisDataComp);
        createAnalysisColumnsSection(form, toolkit, anasisDataComp);
        createDataFilterSection(form, toolkit, anasisDataComp);
    }

    private void createAnalysisMetadataSection(final ScrolledForm form, FormToolkit toolkit, Composite anasisDataComp) {
        Section section = createSection(form, toolkit, anasisDataComp, "Analysis metadata", false,
                "Set the properties of analysis.");
        Composite labelButtonClient = toolkit.createComposite(section);

        labelButtonClient.setLayout(new GridLayout(2, false));

        Label label = toolkit.createLabel(labelButtonClient, "Anasis Name:");
        label.setLayoutData(new GridData());
        nameText = toolkit.createText(labelButtonClient, null, SWT.BORDER);
        nameText.setLayoutData(new GridData());
        label = toolkit.createLabel(labelButtonClient, "Anasis Purpose:");
        label.setLayoutData(new GridData());
        purposeText = toolkit.createText(labelButtonClient, null, SWT.BORDER);
        purposeText.setLayoutData(new GridData());
        label = toolkit.createLabel(labelButtonClient, "Anasis Description:");
        label.setLayoutData(new GridData());
        descriptionText = toolkit.createText(labelButtonClient, null, SWT.BORDER);
        descriptionText.setLayoutData(new GridData());
        section.setClient(labelButtonClient);
    }

    private void createAnalysisColumnsSection(final ScrolledForm form, FormToolkit toolkit, Composite anasisDataComp) {
        Section section = createSection(form, toolkit, anasisDataComp, "Analysis Column Selection", true,
                "Edit the columns anasis on the following section.");

        Composite topComp = toolkit.createComposite(section);
        topComp.setLayout(new GridLayout(3, true));

        Composite tree = toolkit.createComposite(topComp, SWT.BORDER);
        GridDataFactory.fillDefaults().span(2, 1).align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(tree);
        tree.setLayout(new GridLayout());

        treeViewer = new AnasisColumnTreeViewer(tree);
        Composite buttonsComp = toolkit.createComposite(topComp, SWT.None);
        GridDataFactory.fillDefaults().span(1, 1).applyTo(buttonsComp);
        buttonsComp.setLayout(new GridLayout(1, true));
        Button button = toolkit.createButton(buttonsComp, "Add..", SWT.None);
        GridDataFactory.fillDefaults().span(1, 1).align(SWT.FILL, SWT.TOP).applyTo(button);
        button.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                openColumnsSelectionDialog();
            }
        });

        section.setClient(topComp);

    }

    /**
     * 
     */
    private void openColumnsSelectionDialog() {
        ColumnsSelectionDialog dialog = new ColumnsSelectionDialog(getSite().getShell(), "Column Selection");
        if (dialog.open() == Window.OK) {
            Object[] columns = dialog.getResult();
            treeViewer.setInput(columns);
            return;
        }
    }

    /**
     * @param form
     * @param toolkit
     * @param anasisDataComp
     */
    private void createDataFilterSection(final ScrolledForm form, FormToolkit toolkit, Composite anasisDataComp) {
        Section section = createSection(form, toolkit, anasisDataComp, "Anasis Columns", false,
                "Edit the data filter on the following section.");

        Composite sectionClient = toolkit.createComposite(section);
        dataFilterComp = new DataFilterComp(sectionClient);
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
        Section section = toolkit.createSection(parent, Section.DESCRIPTION

        | Section.TWISTIE | Section.TITLE_BAR);

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

    private void createAnalysisBuilder() throws DataprofilerCoreException {
        String outputFolder = "ANA";
        // initialized analysis
        AnalysisBuilder analysisBuilder = new AnalysisBuilder();
        String analysisName = "My test analysis";

        boolean analysisInitialized = analysisBuilder.initializeAnalysis(analysisName, AnalysisType.COLUMN);
        if (analysisInitialized) {
            throw new DataprofilerCoreException(analysisName + " failed to initialize!");
        }

        // get a column to analyze
        ColumnIndicator[] columnIndicators = treeViewer.getColumnIndicator();
        for (ColumnIndicator columnIndicator : columnIndicators) {
            analysisBuilder.addElementToAnalyze(columnIndicator.getTdColumn(), columnIndicator.getIndicators());
        }

        //TODO get the domain constraint, we will see later.
        // Domain dataFilter = getDataFilter(dataManager, (Column) column); // CAST here for test
        // analysisBuilder.addFilterOnData(dataFilter);
        //
        // FolderProvider folderProvider = new FolderProvider();
        // folderProvider.setFolder(new File(outputFolder));
        // DqRepositoryViewService.saveDomain(dataFilter, folderProvider);

        // run analysis
        Analysis analysis = analysisBuilder.getAnalysis();
        IAnalysisExecutor exec = new ColumnAnalysisExecutor();
        ReturnCode executed = exec.execute(analysis);
        if (executed.isOk()) {
            throw new DataprofilerCoreException("Problem executing analysis: " + analysisName + ": " + executed.getMessage());
        }

        // save data provider
        // DqRepositoryViewService.saveDataProviderAndStructure(dataManager, folderProvider);

        // save analysis
        AnalysisWriter writer = new AnalysisWriter();
        File file = new File(outputFolder + File.separator + "analysis.ana");
        ReturnCode saved = writer.save(analysis, file);
        if (saved.isOk()) {
            log.info("Saved in  " + file.getAbsolutePath());
        } else {
            throw new DataprofilerCoreException("Problem saving file: " + file.getAbsolutePath() + ": " + executed.getMessage());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.forms.editor.FormPage#dispose()
     */
    @Override
    public void dispose() {
        super.dispose();
    }

}
