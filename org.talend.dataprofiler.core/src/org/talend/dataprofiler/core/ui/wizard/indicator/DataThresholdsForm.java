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
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.talend.dataprofiler.core.ui.utils.AbstractIndicatorForm;
import org.talend.dq.analysis.parameters.IParameterConstant;


/**
 * DOC zqin  class global comment. Detailled comment
 */
public class DataThresholdsForm extends AbstractIndicatorForm {

    private final String formName = "Data Thresholds";
    
    Text lowerText, higherText;
    /**
     * DOC zqin DataThresholdsForm constructor comment.
     * @param parent
     * @param style
     */
    public DataThresholdsForm(Composite parent, int style) {
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
        
        Group group = new Group(this, SWT.NONE);
        group.setLayout(new GridLayout(2, false));
        group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        group.setText("Set here the thresholds expected on the data");
        
        GridData gdText = new GridData(GridData.FILL_HORIZONTAL);
        
        Label lowerLabel = new Label(group, SWT.NONE);
        lowerLabel.setText("Lower threshold");
        lowerText = new Text(group, SWT.BORDER);
        lowerText.setLayoutData(gdText);
        
        Label higherLabel = new Label(group, SWT.NONE);
        higherLabel.setText("Higher threshold");
        higherText = new Text(group, SWT.BORDER);
        higherText.setLayoutData(gdText);
    }

    /* (non-Javadoc)
     * @see org.talend.dataprofiler.core.ui.utils.AbstractForm#addFieldsListeners()
     */
    @Override
    protected void addFieldsListeners() {
        
        lowerText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
               
                parameters.put(IParameterConstant.INDICATOR_LOWER_THRESHOLD, lowerText.getText());
            }
            
        });
        
        higherText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
               
                parameters.put(IParameterConstant.INDICATOR_HIGHER_THRESHOLD, higherText.getText());
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
        // TODO Auto-generated method stub

    }

}
