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

import org.eclipse.jface.wizard.Wizard;
import org.talend.dataprofiler.core.model.ColumnIndicator;
import org.talend.dataprofiler.core.model.nodes.indicator.tpye.IndicatorEnum;


/**
 * DOC zqin  class global comment. Detailled comment
 */
public class IndicatorOptionsWizard extends Wizard {

    private IndicatorEnum indicatorEnum;
    
    private ColumnIndicator columnIndicator;
    /**
     * DOC zqin IndicatorOptionsWizard constructor comment.
     */
    public IndicatorOptionsWizard(ColumnIndicator columnIndicator, IndicatorEnum indicatorEnum) {
        setWindowTitle("Indicator");
        
        this.columnIndicator = columnIndicator;
        this.indicatorEnum = indicatorEnum;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {

        return true;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {
        
        DynamicIndicatorOptionsPage indicatorPage = new DynamicIndicatorOptionsPage(columnIndicator, indicatorEnum);
        
        addPage(indicatorPage);
    }

}
