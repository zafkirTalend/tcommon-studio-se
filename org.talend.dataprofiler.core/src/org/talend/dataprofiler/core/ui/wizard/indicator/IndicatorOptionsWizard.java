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

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.PlatformUI;
import org.talend.dataprofiler.core.CorePlugin;
import org.talend.dataprofiler.core.model.ColumnIndicator;
import org.talend.dataprofiler.core.model.nodes.indicator.tpye.IndicatorEnum;
import org.talend.dataprofiler.core.ui.editor.AnalysisEditorInuput;
import org.talend.dataprofiler.core.ui.utils.AbstractIndicatorForm;
import org.talend.dataquality.analysis.Analysis;
import org.talend.dataquality.domain.Domain;
import org.talend.dataquality.helpers.DomainHelper;
import org.talend.dataquality.helpers.IndicatorHelper;
import org.talend.dataquality.indicators.DateGrain;
import org.talend.dataquality.indicators.Indicator;
import org.talend.dataquality.indicators.IndicatorParameters;
import org.talend.dataquality.indicators.IndicatorsFactory;
import org.talend.dataquality.indicators.TextParameters;
import org.talend.dq.analysis.parameters.IParameterConstant;


/**
 * DOC zqin  class global comment. Detailled comment
 */
public class IndicatorOptionsWizard extends Wizard {

    private IndicatorEnum indicatorEnum;
    
    private ColumnIndicator columnIndicator;
    
    private Indicator indicator;
    
    private Analysis analysis;
    /**
     * DOC zqin IndicatorOptionsWizard constructor comment.
     */
    public IndicatorOptionsWizard(ColumnIndicator columnIndicator, Indicator indicator, IndicatorEnum indicatorEnum, Analysis analysis) {
        setWindowTitle("Indicator");
        
        AbstractIndicatorForm.parameters.clear();
        this.columnIndicator = columnIndicator;
        this.indicatorEnum = indicatorEnum;
        this.indicator = indicator;
        this.analysis = analysis;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        
        try {
            if (!AbstractIndicatorForm.parameters.isEmpty()) {
                
                String minStr = AbstractIndicatorForm.parameters.get(IParameterConstant.INDICATOR_MIN_VALUE);
                String maxStr = AbstractIndicatorForm.parameters.get(IParameterConstant.INDICATOR_MAX_VALUE);
                String numOfBinStr = AbstractIndicatorForm.parameters.get(IParameterConstant.INDICATOR_NUM_OF_BIN);
                String ignoreCaseStr = AbstractIndicatorForm.parameters.get(IParameterConstant.INDICATOR_IGNORE_CASE);
                String useBlankStr = AbstractIndicatorForm.parameters.get(IParameterConstant.INDICATOR_COUNT_BLANKS);
                String useNullStr = AbstractIndicatorForm.parameters.get(IParameterConstant.INDICATOR_COUNT_NULLS);
                String dateStr = AbstractIndicatorForm.parameters.get(IParameterConstant.INDICATOR_TIME_SLICES);
                String dataThresholdMin = AbstractIndicatorForm.parameters.get(IParameterConstant.INDICATOR_LOWER_THRESHOLD);
                String dataThresholdMax = AbstractIndicatorForm.parameters.get(IParameterConstant.INDICATOR_HIGHER_THRESHOLD);
                
                IndicatorParameters paramters = indicator.getParameters();
                
                if (paramters == null) {
                    paramters = IndicatorsFactory.eINSTANCE.createIndicatorParameters();
                    indicator.setParameters(paramters);
                }
                
                TextParameters textParameters = paramters.getTextParameter();
                
                if (textParameters == null) {
                    textParameters = IndicatorsFactory.eINSTANCE.createTextParameters();
                    paramters.setTextParameter(textParameters);
                }
                
                if (minStr != null && maxStr != null && numOfBinStr != null) {
                    Double min = Double.valueOf(minStr);
                    Double max = Double.valueOf(maxStr);
                    int numOfBin = Integer.parseInt(numOfBinStr);
                    Domain domain = DomainHelper.createContiguousClosedBinsIntoDomain("test", numOfBin, min, max);
                    paramters.setBins(domain);
                    
                    //save the domain
                    Resource resource = analysis.eResource();
                    resource.getContents().add(domain);
                    return true;
                } else if (dateStr != null) {
                    paramters.setDateAggregationType(DateGrain.get(dateStr));
                    return true;
                } else if (ignoreCaseStr != null) {
                    boolean isIgnoreCase = Boolean.valueOf(ignoreCaseStr);
                    textParameters.setIgnoreCase(isIgnoreCase);
                    return true;
                } else if (useBlankStr != null && useNullStr != null) {
                    boolean isUseBlank = Boolean.valueOf(useBlankStr);
                    boolean isUseNull = Boolean.valueOf(useNullStr);
                    textParameters.setUseBlank(isUseBlank);
                    textParameters.setUseNulls(isUseNull);
                    return true;
                } else if (dataThresholdMin != null && dataThresholdMax != null) {
                    IndicatorHelper.setDataThreshold(indicator, dataThresholdMin, dataThresholdMax);
                    return true;
                } else {
                    return false;
                }
            }
        } catch (NumberFormatException e) {
            
            MessageDialogWithToggle.openError(null, "indicator setting", e.getMessage() + ", it's invalid");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
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
