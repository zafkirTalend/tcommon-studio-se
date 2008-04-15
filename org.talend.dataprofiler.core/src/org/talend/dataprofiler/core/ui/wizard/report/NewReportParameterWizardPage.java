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
package org.talend.dataprofiler.core.ui.wizard.report;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.talend.dataprofiler.core.ui.wizard.analysis.AbstractAnalysisWizardPage;


/**
 * DOC zqin  class global comment. Detailled comment
 */
public class NewReportParameterWizardPage extends AbstractAnalysisWizardPage {

    private final int textHight = 50;
    
    private final String pageTitle = "New Report Step 2/2";
    
    private final String pageMessage = "Editor header and footer.";
    
    private Text headerText, footerText;
    
    private Button checkRefresh;
    
    private CCombo formatSelection;
    
    private TableViewer analysisTable;
    
    private Button addAnalysis, delAnalysis, moveToUp, moveToDown;
    /**
     * DOC zqin NewReportParameterWizardPage constructor comment.
     */
    public NewReportParameterWizardPage() {
        setTitle(pageTitle);
        setMessage(pageMessage);
        
    }

    /* (non-Javadoc)
     * @see org.talend.dataprofiler.core.ui.wizard.analysis.AbstractAnalysisWizardPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {

        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new GridLayout());
        
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.heightHint = 50;
        
        //header
        Label headerLabel = new Label(container, SWT.NONE);
        headerLabel.setText("Header");
        headerText = new Text(container, SWT.BORDER | SWT.MULTI);
        headerText.setLayoutData(gd);
        
        //table
        Label analysisLabel = new Label(container, SWT.NONE);
        analysisLabel.setText("Add one or serval analysis to this report");
        analysisTable = new TableViewer(container, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
        Table table = analysisTable.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        table.setLayoutData(gd);
        
        TableColumn analysisName = new TableColumn(table, SWT.NONE);
        analysisName.setText("Analysis Name");
        analysisName.setWidth(470);
        
        //button of table
        Composite comp = new Composite(container, SWT.NONE);
        comp.setLayout(new GridLayout(4, false));
        addAnalysis = new Button(comp, SWT.PUSH);
        addAnalysis.setText("add");
        delAnalysis = new Button(comp, SWT.PUSH);
        delAnalysis.setText("del");
        moveToUp = new Button(comp, SWT.PUSH);
        moveToUp.setText("up");
        moveToDown = new Button(comp, SWT.PUSH);
        moveToDown.setText("down");
        
        checkRefresh = new Button(container, SWT.CHECK);
        checkRefresh.setText("Refresh all analysis");
        //footer
        Label footerLabel = new Label(container, SWT.NONE);
        footerLabel.setText("Footer");
        footerText = new Text(container, SWT.BORDER | SWT.MULTI);
        footerText.setLayoutData(gd);
        
        comp = new Composite(container, SWT.NONE);
        comp.setLayout(new GridLayout(2, false));
        Label formatLabel = new Label(comp, SWT.NONE);
        formatLabel.setText("Format");
        formatSelection = new CCombo(comp, SWT.BORDER);
        formatSelection.setText("pdf");
        GridData gdd = new GridData();
        gdd.widthHint = 150;
        formatSelection.setLayoutData(gdd);
        
        String[] formatOptions = new String[] {"pdf", "xml", "html"};
        
        for (String str : formatOptions) {
            formatSelection.add(str);
        }
       
        setControl(container);
    }

}
