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
package org.talend.dq.indicators.definitions;

import java.io.File;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.talend.commons.emf.EMFUtil;
import org.talend.dataquality.indicators.AverageLengthIndicator;
import org.talend.dataquality.indicators.BigDecimalIndicator;
import org.talend.dataquality.indicators.BigDecimalMeanIndicator;
import org.talend.dataquality.indicators.BlankCountIndicator;
import org.talend.dataquality.indicators.BoxIndicator;
import org.talend.dataquality.indicators.DistinctCountIndicator;
import org.talend.dataquality.indicators.DoubleMeanIndicator;
import org.talend.dataquality.indicators.DoubleSumIndicator;
import org.talend.dataquality.indicators.DuplicateCountIndicator;
import org.talend.dataquality.indicators.FrequencyIndicator;
import org.talend.dataquality.indicators.IQRIndicator;
import org.talend.dataquality.indicators.Indicator;
import org.talend.dataquality.indicators.IntegerMeanIndicator;
import org.talend.dataquality.indicators.LengthIndicator;
import org.talend.dataquality.indicators.MaxLengthIndicator;
import org.talend.dataquality.indicators.MaxValueIndicator;
import org.talend.dataquality.indicators.MeanIndicator;
import org.talend.dataquality.indicators.MedianIndicator;
import org.talend.dataquality.indicators.MinLengthIndicator;
import org.talend.dataquality.indicators.MinValueIndicator;
import org.talend.dataquality.indicators.ModeIndicator;
import org.talend.dataquality.indicators.NullCountIndicator;
import org.talend.dataquality.indicators.RangeIndicator;
import org.talend.dataquality.indicators.RowCountIndicator;
import org.talend.dataquality.indicators.SumIndicator;
import org.talend.dataquality.indicators.TextIndicator;
import org.talend.dataquality.indicators.UniqueCountIndicator;
import org.talend.dataquality.indicators.ValueIndicator;
import org.talend.dataquality.indicators.definition.IndicatorDefinition;
import org.talend.dataquality.indicators.definition.IndicatorsDefinitions;
import org.talend.dataquality.indicators.definition.util.DefinitionSwitch;
import org.talend.dataquality.indicators.util.IndicatorsSwitch;
import orgomg.cwm.objectmodel.core.Element;

/**
 * @author scorreia
 * 
 * This class contains the singleton instance for the default indicator' definitions.
 */
public final class DefinitionHandler {

    private static Logger log = Logger.getLogger(DefinitionHandler.class);

    private static DefinitionHandler instance;

    private IndicatorsDefinitions indicatorDefinitions;

    /**
     * plugin relative path to the default file.
     */
    private static final String FILENAME = "Talend.definition";

    private static final String PATH_NAME = "/org.talend.dataquality/" + FILENAME;

    private DefinitionHandler() {
        this.indicatorDefinitions = loadFromFile();
    }

    private IndicatorsDefinitions loadFromFile() {
        EMFUtil util = new EMFUtil();
        Resource definitionsFile = null;
        URI uri = URI.createPlatformPluginURI(PATH_NAME, false);
        try { // load from plugin path
            definitionsFile = util.getResourceSet().getResource(uri, true);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }

        if (definitionsFile == null) {
            // try to load from a local file
            definitionsFile = util.getResourceSet().getResource(URI.createFileURI(".." + File.separator + PATH_NAME), true);
        }
        if (definitionsFile == null) {
            log.error("No resource found at " + PATH_NAME + " URI= " + uri);
            return null;
        }
        EList<EObject> contents = definitionsFile.getContents();
        if (contents == null) {
            log.error("No content found in given resource: " + uri);
            return null;
        }
        DefinitionSwitch<IndicatorsDefinitions> catSwitch = new DefinitionSwitch<IndicatorsDefinitions>() {

            @Override
            public IndicatorsDefinitions caseIndicatorsDefinitions(IndicatorsDefinitions object) {
                return object;
            }

        };

        return catSwitch.doSwitch(contents.get(0));
    }

    /**
     * Method "getIndicatorsDefinitions".
     * 
     * @return the singleton analysis categories (or throws an exception if a problem occured)
     */
    public IndicatorsDefinitions getIndicatorsDefinitions() {
        if (indicatorDefinitions == null) {
            indicatorDefinitions = loadFromFile();
        }
        if (indicatorDefinitions == null) {
            throw new RuntimeException("Indicators' definition not loaded!");
        }
        return indicatorDefinitions;
    }

    /**
     * Method "copyDefinitionsIntoFolder".
     * 
     * @param folder the folder where to copy the default resource.
     * @return the copied resource.
     */
    public Resource copyDefinitionsIntoFolder(File folder) {
        URI destinationUri = URI.createFileURI(folder.getAbsolutePath());
        Resource resource = getIndicatorsDefinitions().eResource();
        EMFUtil.changeUri(resource, destinationUri);
        if (EMFUtil.saveResource(resource)) {
            if (log.isInfoEnabled()) {
                log.info("Indicator default definitions correctly saved in " + resource.getURI());
            } else {
                log.warn("Failed to save default indicator definitions in " + resource.getURI());
            }
        }
        return resource;
    }

    public static DefinitionHandler getInstance() {
        if (instance == null) {
            instance = new DefinitionHandler();
        }
        return instance;
    }

    /**
     * Method "setDefaultIndicatorDefinition" sets the indicator's default definition.
     * 
     * @param indicator the indicator
     * @return true when set, false when not set.
     */
    public boolean setDefaultIndicatorDefinition(Indicator indicator) {
        return mySwitch.doSwitch(indicator);
    }

    /**
     * Method "getIndicatorDefinition".
     * 
     * @param label the label of the definition to get
     * @return the definition with the given label
     */
    private IndicatorDefinition getIndicatorDefinition(String label) {
        EList<IndicatorDefinition> definitions = this.indicatorDefinitions.getIndicatorDefinitions();
        for (IndicatorDefinition indicatorDefinition : definitions) {
            if (indicatorDefinition != null && indicatorDefinition.getLabel() != null
                    && indicatorDefinition.getLabel().compareTo(label) == 0) {
                return indicatorDefinition;
            }
        }
        return null;
    }

    private final IndicatorsSwitch<Boolean> mySwitch = new IndicatorsSwitch<Boolean>() {

        // TODO rli complete all definitions
        @Override
        public Boolean defaultCase(EObject object) {
            return false;
        }

        @Override
        public Boolean caseValueIndicator(ValueIndicator object) {
            // TODO Auto-generated method stub
            return super.caseValueIndicator(object);
        }

        @Override
        public Boolean caseUniqueCountIndicator(UniqueCountIndicator object) {
            return setIndicatorDefinition(object, "Unique Count");
        }

        @Override
        public Boolean caseTextIndicator(TextIndicator object) {
            return super.caseTextIndicator(object);
        }

        @Override
        public Boolean caseSumIndicator(SumIndicator object) {
            return setIndicatorDefinition(object, "Sum");
        }

        @Override
        public Boolean caseRowCountIndicator(RowCountIndicator object) {
            return setIndicatorDefinition(object, "Row Count");
        }

        @Override
        public Boolean caseRangeIndicator(RangeIndicator object) {
            return setIndicatorDefinition(object, "Range");
        }

        @Override
        public Boolean caseNullCountIndicator(NullCountIndicator object) {
            return setIndicatorDefinition(object, "Null Count");
        }

        @Override
        public Boolean caseModeIndicator(ModeIndicator object) {
            return setIndicatorDefinition(object, "Mode");
        }

        @Override
        public Boolean caseMinValueIndicator(MinValueIndicator object) {
            return setIndicatorDefinition(object, "Minimum");
        }

        @Override
        public Boolean caseMinLengthIndicator(MinLengthIndicator object) {
            return setIndicatorDefinition(object, "Minimal Length");
        }

        @Override
        public Boolean caseMedianIndicator(MedianIndicator object) {
            return setIndicatorDefinition(object, "Median");
        }

        @Override
        public Boolean caseMeanIndicator(MeanIndicator object) {
            return setIndicatorDefinition(object, "Mean");
        }

        @Override
        public Boolean caseMaxValueIndicator(MaxValueIndicator object) {
            return setIndicatorDefinition(object, "Maximum");
        }

        @Override
        public Boolean caseMaxLengthIndicator(MaxLengthIndicator object) {
            return setIndicatorDefinition(object, "Maximal Length");
        }

        @Override
        public Boolean caseLengthIndicator(LengthIndicator object) {
            return setIndicatorDefinition(object, "Length");
        }

        @Override
        public Boolean caseIQRIndicator(IQRIndicator object) {
            return setIndicatorDefinition(object, "IQR");
        }

        @Override
        public Boolean caseIntegerMeanIndicator(IntegerMeanIndicator object) {
            // TODO Auto-generated method stub
            return super.caseIntegerMeanIndicator(object);
        }

        @Override
        public Boolean caseFrequencyIndicator(FrequencyIndicator object) {
            // TODO Auto-generated method stub
            return super.caseFrequencyIndicator(object);
        }

        @Override
        public Boolean caseElement(Element object) {
            // TODO Auto-generated method stub
            return super.caseElement(object);
        }

        @Override
        public Boolean caseDuplicateCountIndicator(DuplicateCountIndicator object) {
            return setIndicatorDefinition(object, "Duplicate Count");
        }

        @Override
        public Boolean caseDoubleSumIndicator(DoubleSumIndicator object) {
            // TODO Auto-generated method stub
            return super.caseDoubleSumIndicator(object);
        }

        @Override
        public Boolean caseDoubleMeanIndicator(DoubleMeanIndicator object) {
            // TODO Auto-generated method stub
            return super.caseDoubleMeanIndicator(object);
        }

        @Override
        public Boolean caseDistinctCountIndicator(DistinctCountIndicator object) {
            return setIndicatorDefinition(object, "Distinct Count");
        }

        @Override
        public Boolean caseBoxIndicator(BoxIndicator object) {
            // TODO Auto-generated method stub
            return super.caseBoxIndicator(object);
        }

        @Override
        public Boolean caseBlankCountIndicator(BlankCountIndicator object) {
            return setIndicatorDefinition(object, "Blank Count");
        }

        @Override
        public Boolean caseBigDecimalMeanIndicator(BigDecimalMeanIndicator object) {
            // TODO Auto-generated method stub
            return super.caseBigDecimalMeanIndicator(object);
        }

        @Override
        public Boolean caseBigDecimalIndicator(BigDecimalIndicator object) {
            // TODO Auto-generated method stub
            return super.caseBigDecimalIndicator(object);
        }

        @Override
        public Boolean caseAverageLengthIndicator(AverageLengthIndicator object) {
            // TODO Auto-generated method stub
            return super.caseAverageLengthIndicator(object);
        }

        private Boolean setIndicatorDefinition(Indicator indicator, String definitionLabel) {
            // get the definition
            IndicatorDefinition indicatorDefinition = DefinitionHandler.this.getIndicatorDefinition(definitionLabel);
            if (indicatorDefinition == null) {
                return false;
            }
            // else
            indicator.setIndicatorDefinition(indicatorDefinition);
            return true;
        }

    };
}
