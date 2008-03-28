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
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.talend.dataprofiler.core.ui.dialog.ColumnsSelectionDialog;
import org.talend.dataprofiler.core.ui.editor.composite.AnasisColumnTreeViewer;

/**
 * @author rli
 * 
 */
public class MasterDetailsPage extends FormPage {

    private Text nameText;

    private Text purposeText;

    private Text descriptionText;

    private AnasisColumnTreeViewer treeViewer;

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
        Section section = toolkit.createSection(anasisDataComp, Section.DESCRIPTION

        | Section.TWISTIE | Section.TITLE_BAR);

        section.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING));

        section.addExpansionListener(new ExpansionAdapter() {

            public void expansionStateChanged(ExpansionEvent e) {
                form.reflow(true);
            }

        });

        section.setText("Analysis metadata");
        section.setDescription("Set the properties of analysis.");
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
        Section section = toolkit.createSection(anasisDataComp, Section.DESCRIPTION

        | Section.TWISTIE | Section.TITLE_BAR);

        section.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING));

        section.addExpansionListener(new ExpansionAdapter() {

            public void expansionStateChanged(ExpansionEvent e) {
                form.reflow(true);
            }

        });
        section.setText("Analysis metadata");
        section.setDescription("Edit the columns anasis on the following section.");
        section.setExpanded(true);
        Composite topComp = toolkit.createComposite(section);
        topComp.setLayout(new GridLayout(3, true));

        Tree tree = toolkit.createTree(topComp, SWT.BORDER);
//        GridData gd = new GridData();
        // gd.
        GridDataFactory.fillDefaults().span(2, 1).align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(tree);
        ((GridData) tree.getLayoutData()).heightHint = 300;
        ((GridData) tree.getLayoutData()).widthHint = 500;

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
        Section section = toolkit.createSection(anasisDataComp, Section.DESCRIPTION

        | Section.TWISTIE | Section.TITLE_BAR);

        section.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING));

        section.addExpansionListener(new ExpansionAdapter() {

            public void expansionStateChanged(ExpansionEvent e) {
                form.reflow(true);
            }

        });

        section.setText("Anasis Columns");
        section.setExpanded(false);

        // toolkit.createCompositeSeparator(section);

        section

        .setDescription("Edit the data filter on the following section.");

        Composite sectionClient = toolkit.createComposite(section);

        sectionClient.setLayout(new GridLayout(3, true));

        Table table = toolkit.createTable(sectionClient, SWT.BORDER);
        GridDataFactory.fillDefaults().span(2, 3).align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(table);

        Composite buttonsComp = toolkit.createComposite(sectionClient, SWT.None);
        GridDataFactory.fillDefaults().span(1, 1).applyTo(buttonsComp);
        buttonsComp.setLayout(new GridLayout(1, true));

        Button button = toolkit.createButton(buttonsComp, "Add..", SWT.None);
        GridDataFactory.fillDefaults().span(1, 1).align(SWT.FILL, SWT.TOP).applyTo(button);
        button = toolkit.createButton(buttonsComp, "Edit..", SWT.None);
        GridDataFactory.fillDefaults().span(1, 1).align(SWT.FILL, SWT.TOP).applyTo(button);
        button = toolkit.createButton(buttonsComp, "Remove", SWT.None);
        GridDataFactory.fillDefaults().span(1, 1).align(SWT.FILL, SWT.TOP).applyTo(button);

        section.setClient(sectionClient);
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
