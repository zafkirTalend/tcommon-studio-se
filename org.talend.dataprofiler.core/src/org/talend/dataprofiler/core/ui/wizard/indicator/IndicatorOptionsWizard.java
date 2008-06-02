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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.wizard.Wizard;
import org.talend.dataprofiler.core.ui.editor.preview.IndicatorUnit;
import org.talend.dataprofiler.core.ui.utils.AbstractIndicatorForm;
import org.talend.dataprofiler.core.ui.wizard.indicator.parameter.AbstractIndicatorParameter;
import org.talend.dataprofiler.core.ui.wizard.indicator.parameter.BinsDesignerParameter;
import org.talend.dataprofiler.core.ui.wizard.indicator.parameter.DataThresholdsParameter;
import org.talend.dataprofiler.core.ui.wizard.indicator.parameter.TextLengthParameter;
import org.talend.dataprofiler.core.ui.wizard.indicator.parameter.TextParameter;
import org.talend.dataprofiler.core.ui.wizard.indicator.parameter.TimeSlicesParameter;
import org.talend.dataquality.analysis.Analysis;
import org.talend.dataquality.domain.Domain;
import org.talend.dataquality.helpers.DomainHelper;
import org.talend.dataquality.helpers.IndicatorHelper;
import org.talend.dataquality.indicators.DateGrain;
import org.talend.dataquality.indicators.DateParameters;
import org.talend.dataquality.indicators.Indicator;
import org.talend.dataquality.indicators.IndicatorParameters;
import org.talend.dataquality.indicators.IndicatorsFactory;
import org.talend.dataquality.indicators.TextParameters;

/**
 * DOC zqin class global comment. Detailled comment
 */
public class IndicatorOptionsWizard extends Wizard {

    private Analysis analysis;

    private IndicatorUnit indicatorUnit;

    private Indicator indicator;

    private Map<String, AbstractIndicatorParameter> paramMap;

    /**
     * DOC zqin IndicatorOptionsWizard constructor comment.
     */
    public IndicatorOptionsWizard(IndicatorUnit indicatorUnit, Analysis analysis) {
        setWindowTitle("Indicator");

        this.indicatorUnit = indicatorUnit;
        this.analysis = analysis;
        this.indicator = indicatorUnit.getIndicator();

        initWizard();
    }

    private void initWizard() {

        if (!AbstractIndicatorForm.getTheParameter().isEmpty()) {
            AbstractIndicatorForm.emptyParameterList();
        }

        IndicatorParameters indicatorParam = indicatorUnit.getIndicator().getParameters();
        if (indicatorParam != null) {

            paramMap = new HashMap<String, AbstractIndicatorParameter>();

            TextParameters textParameters = indicatorParam.getTextParameter();

            if (textParameters != null) {

                TextParameter textParam = new TextParameter();
                textParam.setIngoreCase(textParameters.isIgnoreCase());

                TextLengthParameter textLengthParam = new TextLengthParameter();
                textLengthParam.setUseBlank(textParameters.isUseBlank());
                textLengthParam.setUseNull(textParameters.isUseNulls());

                paramMap.put(AbstractIndicatorForm.TEXT_PARAMETERS_FORM, textParam);
                paramMap.put(AbstractIndicatorForm.TEXT_LENGTH_FORM, textLengthParam);
            }

            if (IndicatorHelper.getDataThreshold(indicator) != null) {

                DataThresholdsParameter dataParam = new DataThresholdsParameter();
                dataParam.setMinThreshold(IndicatorHelper.getDataThreshold(indicator)[0]);
                dataParam.setMaxThreshold(IndicatorHelper.getDataThreshold(indicator)[1]);

                paramMap.put(AbstractIndicatorForm.DATA_THRESHOLDS_FORM, dataParam);
            }

            if (indicatorParam.getBins() != null) {

                BinsDesignerParameter binsParam = new BinsDesignerParameter();
                binsParam.setMaxValue(DomainHelper.getMaxBinValue(indicatorParam.getBins()));
                binsParam.setMinValue(DomainHelper.getMinBinValue(indicatorParam.getBins()));
                binsParam.setNumOfBins(DomainHelper.getNumberOfBins(indicatorParam.getBins()));

                paramMap.put(AbstractIndicatorForm.BINS_DESIGNER_FORM, binsParam);
            }
            if (indicatorParam.getDateParameters() != null) {
                TimeSlicesParameter timeParam = new TimeSlicesParameter();
                timeParam.setDataUnit(indicatorParam.getDateParameters().getDateAggregationType().getLiteral());

                paramMap.put(AbstractIndicatorForm.TIME_SLICES_FROM, timeParam);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {

        try {

            IndicatorParameters paramters = indicatorUnit.getIndicator().getParameters();

            if (paramters == null) {
                paramters = IndicatorsFactory.eINSTANCE.createIndicatorParameters();
                indicatorUnit.getIndicator().setParameters(paramters);
            }

            TextParameters textParameters = paramters.getTextParameter();

            if (textParameters == null) {
                textParameters = IndicatorsFactory.eINSTANCE.createTextParameters();
                paramters.setTextParameter(textParameters);
            }

            for (AbstractIndicatorParameter parameter : AbstractIndicatorForm.getTheParameter()) {

                if (parameter instanceof BinsDesignerParameter) {

                    BinsDesignerParameter tempParam = (BinsDesignerParameter) parameter;
                    int numOfBin = tempParam.getNumOfBins();
                    double min = tempParam.getMinValue();
                    double max = tempParam.getMaxValue();
                    Domain domain = DomainHelper.createContiguousClosedBinsIntoDomain("test", numOfBin, min, max);
                    // MOD scorreia 2008-05-27 adding to EMF resource not needed because the relation is now a
                    // containment relation.
                    // Resource resource = analysis.eResource();
                    // resource.getContents().add(domain);

                    paramters.setBins(domain);
                }

                if (parameter instanceof TextParameter) {

                    TextParameter tempParam = (TextParameter) parameter;
                    textParameters.setIgnoreCase(tempParam.isIngoreCase());
                }

                if (parameter instanceof TextLengthParameter) {

                    TextLengthParameter tempParam = (TextLengthParameter) parameter;
                    textParameters.setUseBlank(tempParam.isUseBlank());
                    textParameters.setUseNulls(tempParam.isUseNull());
                }

                if (parameter instanceof DataThresholdsParameter) {

                    DataThresholdsParameter tempParam = (DataThresholdsParameter) parameter;
                    IndicatorHelper.setDataThreshold(indicatorUnit.getIndicator(), tempParam.getMinThreshold(), tempParam
                            .getMaxThreshold());
                }

                if (parameter instanceof TimeSlicesParameter) {
                    DateParameters dateParameters = paramters.getDateParameters();
                    if (dateParameters == null) {
                        dateParameters = IndicatorsFactory.eINSTANCE.createDateParameters();
                        paramters.setDateParameters(dateParameters);
                    }
                    TimeSlicesParameter tempParam = (TimeSlicesParameter) parameter;
                    dateParameters.setDateAggregationType(DateGrain.get(tempParam.getDataUnit()));
                }
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {

        DynamicIndicatorOptionsPage indicatorPage = new DynamicIndicatorOptionsPage(indicatorUnit, paramMap);

        addPage(indicatorPage);
    }

}
