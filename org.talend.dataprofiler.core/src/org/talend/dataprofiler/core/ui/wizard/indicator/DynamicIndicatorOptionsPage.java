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

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.TableItem;
import org.talend.dataprofiler.core.model.nodes.indicator.tpye.IndicatorEnum;
import org.talend.dataprofiler.core.ui.utils.AbstractIndicatorForm;


/**
 * DOC zqin  class global comment. Detailled comment
 */
public class DynamicIndicatorOptionsPage extends WizardPage {

    private IndicatorEnum indicatorEnum;
    
    private TabFolder tabFolder;
    /**
     * DOC zqin DynamicIndicatorOptionsPage constructor comment.
     * @param pageName
     */
    public DynamicIndicatorOptionsPage(IndicatorEnum indicatorEnum) {
        super("Indicator settings");
        
        this.indicatorEnum = indicatorEnum;
        setTitle("Indicator settings");
        setMessage("In this wizard, parameter for the given indicator can be set");
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new FillLayout());
        
        tabFolder = new TabFolder(container, SWT.FLAT);
        tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));
       
        BinsDesignerForm binsForm = new BinsDesignerForm(tabFolder, SWT.NONE);
        TimeSlicesForm timeForm = new TimeSlicesForm(tabFolder, SWT.NONE);
        TextParametersForm textForm = new TextParametersForm(tabFolder, SWT.NONE);
        TextLengthForm textLengthForm = new TextLengthForm(tabFolder, SWT.NONE);
        DataThresholdsForm dataForm = new DataThresholdsForm(tabFolder, SWT.NONE);
        
        if (indicatorEnum != null) {       
            switch (indicatorEnum) {

            case DistinctCountIndicatorEnum:
            case UniqueIndicatorEnum:
            case DuplicateCountIndicatorEnum:
                
                break;

            case MedianIndicatorEnum:
            case MinValueIndicatorEnum:
            case MaxValueIndicatorEnum:
                
                break;
            case FrequencyIndicatorEnum:      
            case ModeIndicatorEnum:
                try {
                    setControl(createView(binsForm, timeForm, textForm, textLengthForm, dataForm));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            default:
                setControl(createView(binsForm, timeForm, textForm, textLengthForm, dataForm));
            }
        }

    }

    private Composite createView(AbstractIndicatorForm... forms) {
        try {
            for (AbstractIndicatorForm form : forms) {
                TabItem item = new TabItem(tabFolder, SWT.NONE);
                item.setText(form.getFormName());
                
                item.setControl(form);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tabFolder;
    }
}
