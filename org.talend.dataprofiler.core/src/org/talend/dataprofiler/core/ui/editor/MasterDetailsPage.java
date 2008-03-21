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
import org.eclipse.swt.SWT;
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

/**
 * @author rli
 * 
 */
public class MasterDetailsPage extends FormPage {

    private Text nameText;

    private Text purposeText;

    private Text descriptionText;

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

        section.setText("Data Filter");
        section.setExpanded(true);

        // toolkit.createCompositeSeparator(section);

        section

        .setDescription("This is restriction list to filter the data");

        Composite sectionClient = toolkit.createComposite(section);

        sectionClient.setLayout(new GridLayout(5, true));

        Table table = toolkit.createTable(sectionClient, SWT.BORDER);
        GridDataFactory.fillDefaults().span(2, 3).align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(table);

        Button button = toolkit.createButton(sectionClient, "Edit", SWT.None);
        GridDataFactory.fillDefaults().span(1, 1).align(SWT.FILL, SWT.CENTER).applyTo(button);

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
