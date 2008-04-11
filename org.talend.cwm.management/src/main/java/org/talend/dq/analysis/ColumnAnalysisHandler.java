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
package org.talend.dq.analysis;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.talend.cwm.helper.DescriptionHelper;
import org.talend.cwm.relational.TdColumn;
import org.talend.dataquality.analysis.Analysis;
import org.talend.dataquality.analysis.AnalysisParameters;
import org.talend.dataquality.domain.Domain;
import org.talend.dataquality.domain.RangeRestriction;
import org.talend.dataquality.expressions.BooleanExpressionNode;
import org.talend.dataquality.helpers.BooleanExpressionHelper;
import org.talend.dataquality.helpers.DomainHelper;
import org.talend.dataquality.helpers.MetadataHelper;
import org.talend.dataquality.indicators.DataminingType;
import org.talend.dataquality.indicators.Indicator;
import orgomg.cwm.objectmodel.core.Expression;
import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * @author scorreia
 * 
 * This class helps to handle a Column analysis.
 */
public class ColumnAnalysisHandler {

    private Analysis analysis;

    /**
     * Method "setAnalysis".
     * 
     * @param columnAnalysis the analysis to set
     */
    public void setAnalysis(Analysis columnAnalysis) {
        this.analysis = columnAnalysis;
    }

    /**
     * Method "getAnalysis".
     * 
     * @return the analysis
     */
    public Analysis getAnalysis() {
        return this.analysis;
    }

    public String getName() {
        assert analysis != null;
        return this.analysis.getName();
    }

    public void setName(String name) {
        assert analysis != null;
        this.analysis.setName(name);
    }

    public String getPurpose() {
        assert analysis != null;
        return DescriptionHelper.getPurpose(analysis);
    }

    public void setPurpose(String purpose) {
        assert analysis != null;
        DescriptionHelper.setPurpose(purpose, analysis);
    }

    public String getDescription() {
        assert analysis != null;
        return DescriptionHelper.getDescription(analysis);
    }

    public void setDescription(String description) {
        assert analysis != null;
        DescriptionHelper.setDescription(description, analysis);
    }

    /**
     * Method "addColumnToAnalyze".
     * 
     * @param column
     * @return
     */
    public boolean addColumnToAnalyze(TdColumn column) {
        assert analysis != null;
        assert analysis.getContext() != null;
        return analysis.getContext().getAnalysedElements().add(column);
    }

    public boolean addColumnsToAnalyze(Collection<TdColumn> column) {
        assert analysis != null;
        assert analysis.getContext() != null;
        return analysis.getContext().getAnalysedElements().addAll(column);
    }

    public EList<ModelElement> getAnalyzedColumns() {
        return analysis.getContext().getAnalysedElements();
    }

    public boolean addIndicator(TdColumn column, Indicator... indicators) {
        if (!analysis.getContext().getAnalysedElements().contains(column)) {
            analysis.getContext().getAnalysedElements().add(column);
        }
        for (Indicator indicator : indicators) {
            indicator.setAnalyzedElement(column);
            analysis.getResults().getIndicators().add(indicator);
        }
        return true;
    }

    public void clearAnalysis() {
        assert analysis != null;
        assert analysis.getContext() != null;
        analysis.getContext().getAnalysedElements().clear();
        analysis.getResults().getIndicators().clear();
    }

    /**
     * Method "setDatamingType".
     * 
     * @param dataminingTypeLiteral the literal expression of the datamining type used for the analysis
     * @param column a column
     */
    public void setDatamingType(String dataminingTypeLiteral, TdColumn column) {
        DataminingType type = DataminingType.get(dataminingTypeLiteral);
        MetadataHelper.setDataminingType(type, column);
    }

    /**
     * Method "getDatamingType".
     * 
     * @param column
     * @return the datamining type literal if any or empty string
     */
    public String getDatamingType(TdColumn column) {
        DataminingType dmType = MetadataHelper.getDataminingType(column);
        if (dmType == null) {
            return "";
        }
        // else
        return dmType.getLiteral();
    }

    /**
     * Method "getIndicators".
     * 
     * @param column
     * @return the indicators attached to this column
     */
    public Collection<Indicator> getIndicators(TdColumn column) {
        Collection<Indicator> indics = new ArrayList<Indicator>();
        EList<Indicator> allIndics = analysis.getResults().getIndicators();
        for (Indicator indicator : allIndics) {
            if (indicator.getAnalyzedElement() != null && indicator.getAnalyzedElement().equals(column)) {
                indics.add(indicator);
            }
        }
        return indics;
    }

    public boolean setStringDataFilter(String datafilterString) {
        EList<Domain> dataFilters = analysis.getParameters().getDataFilter();
        // remove existing filters
        if (!dataFilters.isEmpty()) {
            dataFilters.clear();
        }
        return dataFilters.add(createDomain(datafilterString));
    }

    public String getStringDataFilter() {
        AnalysisParameters parameters = analysis.getParameters();
        if (parameters == null) {
            return null;
        }
        EList<Domain> dataFilters = parameters.getDataFilter();
        // remove existing filters
        if (dataFilters.isEmpty()) {
            return null;
        }

        for (Domain domain : dataFilters) {
            if (domain == null) {
                continue;
            }
            EList<RangeRestriction> ranges = domain.getRanges();
            for (RangeRestriction rangeRestriction : ranges) {
                BooleanExpressionNode expressions = rangeRestriction.getExpressions();
                if (expressions == null) {
                    continue;
                }
                Expression expression = expressions.getExpression();
                if (expression == null) {
                    continue;
                }
                return expression.getBody();
            }
        }
        return null;
    }

    private Domain createDomain(String datafilterString) {
        // by default use same name as the analysis. This is ok as long as there is only one datafilter.
        Domain domain = DomainHelper.createDomain(getName());
        RangeRestriction rangeRestriction = DomainHelper.addRangeRestriction(domain);
        BooleanExpressionNode expressionNode = BooleanExpressionHelper.createBooleanExpressionNode(datafilterString);
        rangeRestriction.setExpressions(expressionNode);
        return domain;
    }
}
