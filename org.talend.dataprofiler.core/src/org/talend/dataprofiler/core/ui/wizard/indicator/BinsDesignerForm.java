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
package org.talend.dataprofiler.core.ui.wizard.indicator;

import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.talend.dataprofiler.core.ui.utils.AbstractIndicatorForm;


/**
 * DOC zqin  class global comment. Detailled comment
 */
public class BinsDesignerForm extends AbstractIndicatorForm {

    private final String formName = "Bins Designer";
    
    private Text numberOfBins;
    
    private Button checkEquidistant;
    
    private TableViewer tableOfBins;
    
    private Button addSlice, deleteSlice;
    /**
     * DOC zqin BinsDesignerForm constructor comment.
     * @param parent
     * @param style
     */
    public BinsDesignerForm(Composite parent, int style) {
        super(parent, style);
        setupForm();
    }

    /* (non-Javadoc)
     * @see org.talend.dataprofiler.core.ui.utils.AbstractForm#adaptFormToReadOnly()
     */
    @Override
    protected void adaptFormToReadOnly() {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.talend.dataprofiler.core.ui.utils.AbstractForm#addFields()
     */
    @Override
    protected void addFields() {        
        this.setLayout(new GridLayout(2, false));

        Label labelOfNbumber = new Label(this, SWT.NONE);
        labelOfNbumber.setText("Set number of slice");
        numberOfBins = new Text(this, SWT.BORDER);
        numberOfBins.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        Label labelOfEquidistant = new Label(this, SWT.NONE);
        labelOfEquidistant.setText("Equidistant    slices");
        checkEquidistant = new Button(this, SWT.CHECK);

        tableOfBins = new TableViewer(this, SWT.BORDER | SWT.V_SCROLL | SWT.FULL_SELECTION);
        Table table = tableOfBins.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        
        GridData gdOfTable = new GridData(GridData.FILL_HORIZONTAL);
        gdOfTable.horizontalSpan = 2;
        table.setLayoutData(gdOfTable);

        GridData gdOfContainer = new GridData(GridData.FILL_HORIZONTAL);
        gdOfContainer.horizontalSpan = 2;
        Composite bottomContiner = new Composite(this, SWT.NONE);
        bottomContiner.setLayout(new RowLayout());
        bottomContiner.setLayoutData(gdOfContainer);
        addSlice = new Button(bottomContiner, SWT.BORDER);
        addSlice.setText("add slice");
        deleteSlice = new Button(bottomContiner, SWT.BORDER);
        deleteSlice.setText("delete slice");
        
        createTableHeader(table);
        
    }
    
    private void createTableHeader(Table table) {
        TableLayout tLayout = new TableLayout();
        table.setLayout(tLayout);
        tLayout.addColumnData(new ColumnWeightData(33));
        tLayout.addColumnData(new ColumnWeightData(33));
        tLayout.addColumnData(new ColumnWeightData(33));
        
        TableColumn column1 = new TableColumn(table, SWT.CENTER);
        column1.setText("Low");
        TableColumn column2 = new TableColumn(table, SWT.CENTER);
        column2.setText("Data");
        TableColumn column3 = new TableColumn(table, SWT.CENTER);
        column3.setText("High");
    }

    /* (non-Javadoc)
     * @see org.talend.dataprofiler.core.ui.utils.AbstractForm#addFieldsListeners()
     */
    @Override
    protected void addFieldsListeners() {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.talend.dataprofiler.core.ui.utils.AbstractForm#addUtilsButtonListeners()
     */
    @Override
    protected void addUtilsButtonListeners() {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.talend.dataprofiler.core.ui.utils.AbstractForm#checkFieldsValue()
     */
    @Override
    protected boolean checkFieldsValue() {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see org.talend.dataprofiler.core.ui.utils.AbstractForm#initialize()
     */
    @Override
    protected void initialize() {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.talend.dataprofiler.core.ui.utils.AbstractIndicatorForm#getFormName()
     */
    @Override
    public String getFormName() {
        
        return this.formName;
    }

}
