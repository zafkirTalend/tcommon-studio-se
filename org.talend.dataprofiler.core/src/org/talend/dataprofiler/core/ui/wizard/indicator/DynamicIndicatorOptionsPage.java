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
import org.talend.dataprofiler.core.model.ColumnIndicator;
import org.talend.dataprofiler.core.model.nodes.indicator.tpye.IndicatorEnum;
import org.talend.dataprofiler.core.ui.utils.AbstractIndicatorForm;
import org.talend.dataprofiler.help.HelpPlugin;
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
        setMessage("In this wizard, parameters for the given indicator can be set");
        
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new FillLayout());
        
        tabFolder = new TabFolder(container, SWT.FLAT);
        tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));
        
        if (indicatorEnum != null) {
            
            int sqlType = columnIndicator.getTdColumn().getJavaType();
            //System.out.println(sqlType);
            
            switch (indicatorEnum) {

            case DistinctCountIndicatorEnum:
            case UniqueIndicatorEnum:
            case DuplicateCountIndicatorEnum:

                if (isTextInSQL(sqlType)) {
                    
                    setControl(createView(new TextParametersForm(tabFolder, SWT.NONE)));
                }
                break;
            case MinLengthIndicatorEnum:
            case MaxLengthIndicatorEnum:
            case AverageLengthIndicatorEnum:

                setControl(createView(new TextLengthForm(tabFolder, SWT.NONE)));
                
                break;
            case FrequencyIndicatorEnum:
                if (columnIndicator.getDataminingType() == DataminingType.INTERVAL) {
                    if (isNumbericInSQL(sqlType)) {

                        setControl(createView(new BinsDesignerForm(tabFolder, SWT.NONE)));
                    }
                    
                    if (isDateInSQL(sqlType)) {

                        setControl(createView(new TimeSlicesForm(tabFolder, SWT.NONE)));
                    }
                } else if (isTextInSQL(sqlType)) {

                    setControl(createView(new TextParametersForm(tabFolder, SWT.NONE)));
                }
                
                break;
            case ModeIndicatorEnum:
                if (columnIndicator.getDataminingType() == DataminingType.INTERVAL) {
                    if (isNumbericInSQL(sqlType)) {

                        setControl(createView(new BinsDesignerForm(tabFolder, SWT.NONE)));
                    }
                } else if (isTextInSQL(sqlType)) {
 
                    setControl(createView(new TextParametersForm(tabFolder, SWT.NONE)));
                }

                break;
            case BoxIIndicatorEnum:

                setControl(createView(new DataThresholdsForm(tabFolder, SWT.NONE)));
                break;
            default:
                
            }
        }

        if (getControl() != null) {
            try {
                PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), HelpPlugin.PLUGIN_ID + ".mycontexthelpid");
            } catch (Exception e) {
                e.printStackTrace();
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
    
    public boolean isTextInSQL(int type) {
        
        switch (type) {
        case Types.CHAR:
        case Types.VARCHAR:
        case Types.LONGVARCHAR:
        case Types.CLOB:
        case Types.NCHAR:
        case Types.LONGNVARCHAR:
            
            return true;
        default:
            return false;
        }
    }
    
    public boolean isNumbericInSQL(int type) {
        switch (columnIndicator.getTdColumn().getJavaType()) {
        case Types.DOUBLE:
        case Types.REAL:
        case Types.FLOAT:
        case Types.INTEGER:
        case Types.TINYINT:
        case Types.SMALLINT:
        case Types.BIGINT:
        case Types.DECIMAL:
        case Types.NUMERIC:
            
            return true;
        default:
            return false;
        }
    }
    
    public boolean isDateInSQL(int type) {
        switch (columnIndicator.getTdColumn().getJavaType()) {
        case Types.DATE:
        case Types.TIME:
        case Types.TIMESTAMP:
            
            return true;
        default:
            return false;
        }
    }
}
