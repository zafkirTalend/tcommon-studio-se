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

import java.sql.Types;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.PlatformUI;
import org.talend.dataprofiler.core.CorePlugin;
import org.talend.dataprofiler.core.model.ColumnIndicator;
import org.talend.dataprofiler.core.model.nodes.indicator.tpye.IndicatorEnum;
import org.talend.dataprofiler.core.ui.utils.AbstractIndicatorForm;
import org.talend.dataquality.indicators.DataminingType;


/**
 * DOC zqin  class global comment. Detailled comment
 */
public class DynamicIndicatorOptionsPage extends WizardPage {

    private ColumnIndicator columnIndicator;
    
    private IndicatorEnum indicatorEnum;
    
    private TabFolder tabFolder;
    /**
     * DOC zqin DynamicIndicatorOptionsPage constructor comment.
     * @param pageName
     */
    public DynamicIndicatorOptionsPage(ColumnIndicator columnIndicator, IndicatorEnum indicatorEnum) {
        super("Indicator settings");
        
        this.columnIndicator = columnIndicator;
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
                if (columnIndicator.getTdColumn().getJavaType() == Types.VARCHAR) {
                    setControl(createView(textForm));
                }

                break;

            case MinLengthIndicatorEnum:
            case MaxLengthIndicatorEnum:
            case AverageLengthIndicatorEnum:
                setControl(createView(textLengthForm));
                break;
            case FrequencyIndicatorEnum:
                if (columnIndicator.getDataminingType() == DataminingType.INTERVAL 
                        && columnIndicator.getTdColumn().getJavaType() == Types.NUMERIC) {
                    setControl(createView(binsForm));
                } else if (columnIndicator.getDataminingType() == DataminingType.INTERVAL 
                        && columnIndicator.getTdColumn().getJavaType() == Types.DATE) {
                    setControl(createView(timeForm));
                } else if (columnIndicator.getTdColumn().getJavaType() == Types.VARCHAR) {
                    setControl(createView(textForm));
                }

                break;
            case ModeIndicatorEnum:
                if (columnIndicator.getDataminingType() == DataminingType.INTERVAL 
                        && columnIndicator.getTdColumn().getJavaType() == Types.NUMERIC) {
                    setControl(createView(binsForm));
                } else if (columnIndicator.getTdColumn().getJavaType() == Types.VARCHAR) {
                    setControl(createView(textForm));
                }

                break;
            default:
                setControl(createView(binsForm, timeForm, textForm, textLengthForm, dataForm));
            }
        }
        
        setControl(createView(binsForm, timeForm, textForm, textLengthForm, dataForm));
        
        if (getControl() != null) {
            PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), CorePlugin.PLUGIN_ID + ".mycontexthelpid");
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
