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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.talend.dataprofiler.core.ui.utils.AbstractIndicatorForm;
import org.talend.dq.analysis.parameters.IParameterConstant;


/**
 * DOC zqin  class global comment. Detailled comment
 */
public class TextParametersForm extends AbstractIndicatorForm {

    private final String formName = "Text Parameter";
    
    private Button caseBtn;
    
//    private String[] args = new String[] {"Exact Match", "Metaphone", "Double Metaphone", "Levenshtein", "Soundex", "Refined Soundex"};
    /**
     * DOC zqin TextParametersForm constructor comment.
     * @param parent
     * @param style
     */
    public TextParametersForm(Composite parent, int style) {
        super(parent, style);
        
        setupForm();
    }

    /* (non-Javadoc)
     * @see org.talend.dataprofiler.core.ui.utils.AbstractIndicatorForm#getFormName()
     */
    @Override
    public String getFormName() {
        return this.formName;
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
        this.setLayout(new GridLayout());
        
        Group group = new Group(this, SWT.NONE);
        group.setLayout(new GridLayout());
        group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        group.setText("Options");
        
        caseBtn = new Button(group, SWT.CHECK);
        caseBtn.setText("ignore case");
        
//        Label match = new Label(this, SWT.NONE);
//        match.setText("Matching Algorithm");
//        
//        Button btn = null;
//        
//        for (String str : args) {
//            btn = new Button(this, SWT.RADIO);
//            btn.setText(str);
//        }
    }

    /* (non-Javadoc)
     * @see org.talend.dataprofiler.core.ui.utils.AbstractForm#addFieldsListeners()
     */
    @Override
    protected void addFieldsListeners() {
        
        caseBtn.addSelectionListener(new SelectionAdapter() {

            /* (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                
                parameters.put(IParameterConstant.INDICATOR_IGNORE_CASE, String.valueOf(caseBtn.getSelection()));
            }
            
        });
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

        parameters.put(IParameterConstant.INDICATOR_IGNORE_CASE, "false");
    }

}
