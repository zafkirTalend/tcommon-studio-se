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
package org.talend.dq.persistence;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.talend.cwm.helper.ColumnHelper;
import org.talend.cwm.helper.ColumnSetHelper;
import org.talend.cwm.helper.DataProviderHelper;
import org.talend.cwm.helper.ResourceHelper;
import org.talend.cwm.helper.TaggedValueHelper;
import org.talend.cwm.relational.TdColumn;
import org.talend.dataprofiler.persistence.TdqAnalysis;
import org.talend.dataprofiler.persistence.TdqAnalyzedElement;
import org.talend.dataprofiler.persistence.TdqCalendar;
import org.talend.dataprofiler.persistence.TdqDayTime;
import org.talend.dataprofiler.persistence.TdqIndicatorDefinition;
import org.talend.dataprofiler.persistence.TdqIndicatorOptions;
import org.talend.dataprofiler.persistence.TdqIndicatorValue;
import org.talend.dataprofiler.persistence.TdqValues;
import org.talend.dataprofiler.persistence.business.SqlConstants;
import org.talend.dataprofiler.persistence.utils.HibernateUtil;
import org.talend.dataquality.analysis.Analysis;
import org.talend.dataquality.analysis.AnalysisResult;
import org.talend.dataquality.helpers.AnalysisHelper;
import org.talend.dataquality.helpers.ReportHelper;
import org.talend.dataquality.indicators.DataminingType;
import org.talend.dataquality.indicators.FrequencyIndicator;
import org.talend.dataquality.indicators.Indicator;
import org.talend.dataquality.indicators.ModeIndicator;
import org.talend.dataquality.indicators.definition.IndicatorCategory;
import org.talend.dataquality.indicators.util.IndicatorsSwitch;
import org.talend.dataquality.reports.TdReport;
import org.talend.utils.sugars.ReturnCode;
import orgomg.cwm.objectmodel.core.Package;
import orgomg.cwm.resource.relational.ColumnSet;

/**
 * @author scorreia
 * 
 * This class helps for storing analysis in database in a dimensional model. The dimensions are TDQ_ANALYSIS,
 * TDQ_ANALYZED_ELEMENT, TDQ_INDICATOR_DEFINITION and the date represented by 2 tables: TDQ_CALENDAR, TDQ_DAY_TIME. The
 * fact table is currently TDQ_INDICATOR_VALUE in which all indicators containing an integer value are stored.
 * 
 * For each dimension (except the date), a slowly changing dimension of type 2 (see
 * {@link http://en.wikipedia.org/wiki/Slowly_changing_dimension#Type_2)} implementation is used. For this
 * implementation, there are in our case four columns: BEGIN_DATE, END_DATE, IS_LAST, VERSION.<br>
 * The meaning of these columns is the following (for the table TDQ_ANALYSIS, but the meaning is the same for other
 * dimensions):<br>
 * BEGIN_DATE: the date of creation of the row in the table (i.e. the first time we create the couple report and
 * analysis).<br>
 * END_DATE: the end of the validity of the row (A new row for this couple must have been created). When a row is
 * created, this date is set to the future date defined in SqlConstants.END_DATE. <br>
 * VERSION: an integer that is incremented each time a new couple (report, analysis) is created.<br>
 * 
 * Each row of the table TDQ_ANALYSIS is an instance of the couple (TdReport, Analysis). <br>
 * Analyzed elements and analysis have universal identifiers (UUIDs). These UUIDs can be retrieved with
 * ResourceHelper.getId().<br>
 * 
 * Simple indicators such as count indicators belong to "Simple Statistics" category, "Count" subcategory. <br>
 * Text indicators belong to "Text Statistics" category.<br>
 * Mean, Median, Lower quartile, Upper quartile, min value, max value belong to "Summary Statistics" category. <br>
 * Mode belong to "Advanced Statistics" category and "Mode" subcategory. <br>
 * Frequency table belong to "Advanced Statistics" category and "Frequencies" subcategory.<br>
 * 
 * The column "IND_TYPE" is the datamining type of the indicator.<br>
 * 
 * The column INDV_REAL_VALUE_INDICATOR of table TDQ_INDICATOR_VALUE is set to 'Y' when the indicator are the Mean,
 * Median, Lower quartile, Upper quartile, Min and Max indicators. For the other indicators, it is set to 'N'.
 */
public class DatabasePersistence {

    private Session session;

    private static SimpleDateFormat simpleDateFormat;

    private Map<String, TdqAnalysis> tdqAnalysisCache = new HashMap<String, TdqAnalysis>();

    private Map<String, TdqAnalyzedElement> tdqAnaEleCache = new HashMap<String, TdqAnalyzedElement>();

    private Map<String, TdqIndicatorDefinition> tdqIndDefinitionCache = new HashMap<String, TdqIndicatorDefinition>();

    private List<TdqIndicatorOptions> optionsList = new ArrayList<TdqIndicatorOptions>();

    private List<Object> needSaveObjects = new ArrayList<Object>();

    public ReturnCode persist(TdReport report) {
        ReturnCode rc = new ReturnCode();
        // // create a session transaction
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // persist in database
        List<Analysis> analyses = ReportHelper.getAnalyses(report);
        for (Analysis analysis : analyses) {
            if (analysis.getResults().getResultMetadata().getExecutionDate() == null) {
                rc.setReturnCode("Warning: The current analysis " + analysis.getName() + " haven't executed.", false);
                return rc;
            }
            persist(report, analysis);
        }
        for (Object object : needSaveObjects) {
            session.save(object);
        }

        // commit and close session
        session.getTransaction().commit();
        clearCache();
        return rc;
    }

    private void clearCache() {
        tdqAnalysisCache.clear();
        tdqAnaEleCache.clear();
        tdqIndDefinitionCache.clear();
        needSaveObjects.clear();
        optionsList.clear();

    }

    /**
     * DOC scorreia Comment method "persist".
     * 
     * @param report
     * 
     * @param analysis
     * @param dbReport
     */
    @SuppressWarnings("unchecked")
    private void persist(TdReport report, Analysis analysis) {
        // Get the last instance of TdqAnalysis from DB
        // get row from DB with report's UUID, analysis' UUID and the max(beginDate)
        TdqAnalysis useTdqAnalysis = getCacheTdqAnalysis(report, analysis);

        // if no changes exist, continue
        // save indicators
        AnalysisResult results = analysis.getResults();
        // Date executionDate = results.getResultMetadata().getExecutionDate();
        EList<Indicator> indicators = results.getIndicators();
        for (Indicator indicator : indicators) {
            persist(indicator, useTdqAnalysis, analysis);
        }

    }

    /**
     * DOC rli Comment method "getCurrentTdqAnalysis".
     * 
     * @param report
     * @param analysis
     * @return
     */
    private TdqAnalysis getCacheTdqAnalysis(TdReport report, Analysis analysis) {
        String reportUuid = ResourceHelper.getUUID(report);
        String analysisUuid = ResourceHelper.getUUID(analysis);
        String uniKey = reportUuid + analysisUuid;
        TdqAnalysis cacheTdqAnalysis = tdqAnalysisCache.get(uniKey);
        if (cacheTdqAnalysis != null) {
            return cacheTdqAnalysis;
        }
        List maxVersionList = session.createQuery(
                "select MAX(anVersion) from TdqAnalysis where anUuid='" + analysisUuid + "' and repUuid='" + reportUuid + "'")
                .list();
        Integer maxversion = (Integer) maxVersionList.get(0);

        // Create a current TdqAnalysis from analysis and report
        TdqAnalysis createTdqAnalysis = createTdqAnalysis(report, analysis);

        // check whether a new TdqAnalysis must be created
        // (compare values of TdqAnalysis with current TdqAnalysis
        TdqAnalysis dbTdqAnalysis = null;
        boolean isNeedSave = false;
        if (maxversion != null) {
            List list = session.createCriteria(TdqAnalysis.class).add(Expression.eq("anUuid", analysisUuid)).add(
                    Expression.eq("repUuid", reportUuid)).add(Expression.eq("anVersion", maxversion)).list();
            dbTdqAnalysis = (TdqAnalysis) list.get(0);

            // If the new instance TdqAnalysis has changes, then
            // update the last instance (end date = now)
            // and persist the new instance of TdqAnalysis with a new version number
            if (!dbTdqAnalysis.valueEqual(createTdqAnalysis)) {
                dbTdqAnalysis.setAnEndDate(new Date(System.currentTimeMillis()));
                session.update(dbTdqAnalysis);
                createTdqAnalysis.setAnVersion(dbTdqAnalysis.getAnVersion() + 1);
                session.save(createTdqAnalysis);
                isNeedSave = true;
            }
        } else {
            session.save(createTdqAnalysis);
            isNeedSave = true;
        }
        cacheTdqAnalysis = isNeedSave ? createTdqAnalysis : dbTdqAnalysis;
        tdqAnalysisCache.put(uniKey, cacheTdqAnalysis);
        return cacheTdqAnalysis;
    }

    /**
     * persist the indicator object.
     * 
     * @param indicator
     */
    @SuppressWarnings("unchecked")
    private void persist(Indicator indicator, TdqAnalysis tdqAnalysis, Analysis analysis) {
        // Persist analyzed element (try to get it first before creating it with its UUID)
        // update row only when something has changed (then update END_DATE, VERSION)
        TdqAnalyzedElement persistAnalyzedElement = getCacheTdqAnalyzedElement(indicator);

        // Do the same for TdqIndicatorDefinition (get the definition with its LABEL which identifies a row)
        // update only if something has changed (then update END_DATE, VERSION)
        TdqIndicatorDefinition persistIndicatorDefinition = getCacheTdqIndicatorDef(indicator);

        // get the tdqCalendar object and the TdqDayTime from the database given the date
        Date executionDate = analysis.getResults().getResultMetadata().getExecutionDate();
        if (executionDate != null) {
            TdqIndicatorValue[] createTdqindicatorValues = createTdqindicatorValues(indicator, tdqAnalysis,
                    persistAnalyzedElement, persistIndicatorDefinition, analysis);
            List list = session.createCriteria(TdqCalendar.class).add(Expression.eq("calDate", executionDate)).list();
            TdqCalendar dbCalendar = (TdqCalendar) list.get(0);
            list = session.createCriteria(TdqDayTime.class).add(Expression.eq("timeLabel", getSimpleDateString(executionDate)))
                    .list();
            TdqDayTime dbDayTime = (TdqDayTime) list.get(0);
            for (TdqIndicatorValue tdqIndicatorValue : createTdqindicatorValues) {
                tdqIndicatorValue.setTdqCalendar(dbCalendar);
                tdqIndicatorValue.setTdqDayTime(dbDayTime);
                needSaveObjects.add(tdqIndicatorValue);
            }
        }
    }

    /**
     * DOC rli Comment method "getCacheTdqIndicatorDef".
     * 
     * @param indicator
     * @return
     */
    private TdqIndicatorDefinition getCacheTdqIndicatorDef(Indicator indicator) {
        String indLabel = indicator.getName();
        TdqIndicatorDefinition cacheIndicatorDefinition = tdqIndDefinitionCache.get(indLabel);
        if (cacheIndicatorDefinition != null) {
            return cacheIndicatorDefinition;
        }
        List indDefinitionVersionList = session.createQuery(
                "select MAX(indVersion) from TdqIndicatorDefinition where indLabel='" + indLabel + "'").list();
        Integer indDefinitionVersion = (Integer) indDefinitionVersionList.get(0);
        TdqIndicatorDefinition createTdqIndicatorDefinition = createTdqIndicatorDefinition(indicator);
        boolean isIndicatorSaved = false;
        TdqIndicatorDefinition dbIndDefinition = null;
        if (indDefinitionVersion != null) {
            List list = session.createCriteria(TdqIndicatorDefinition.class).add(Expression.eq("indLabel", indLabel)).add(
                    Expression.eq("indVersion", indDefinitionVersion)).list();
            dbIndDefinition = (TdqIndicatorDefinition) list.get(0);
            if (!dbIndDefinition.valueEqual(createTdqIndicatorDefinition)) {
                dbIndDefinition.setIndEndDate(new Date(System.currentTimeMillis()));
                session.update(dbIndDefinition);
                createTdqIndicatorDefinition.setIndVersion(dbIndDefinition.getIndVersion() + 1);
                // session.save(createTdqIndicatorDefinition);
                needSaveObjects.add(createTdqIndicatorDefinition);
                isIndicatorSaved = true;
            }
        } else {
            isIndicatorSaved = true;
        }
        cacheIndicatorDefinition = isIndicatorSaved ? createTdqIndicatorDefinition : dbIndDefinition;
        tdqIndDefinitionCache.put(indLabel, cacheIndicatorDefinition);
        return cacheIndicatorDefinition;
    }

    /**
     * DOC rli Comment method "getCurrentTdqAnalyzedElement".
     * 
     * @param indicator
     * @return
     */
    private TdqAnalyzedElement getCacheTdqAnalyzedElement(Indicator indicator) {
        String uuid = ResourceHelper.getUUID(indicator.getAnalyzedElement());
        TdqAnalyzedElement cacheAnalyzedElement = this.tdqAnaEleCache.get(uuid);
        if (cacheAnalyzedElement != null) {
            return cacheAnalyzedElement;
        }
        List analyzedEleVersionList = session.createQuery(
                "select MAX(eltVersion) from TdqAnalyzedElement where eltUuid='" + uuid + "'").list();
        Integer analyzedEleVersion = (Integer) analyzedEleVersionList.get(0);

        TdqAnalyzedElement createTdqAnalyzedElement = createTdqAnalyzedElement((TdColumn) indicator.getAnalyzedElement());
        boolean isAnalyzedEleSaved = false;
        TdqAnalyzedElement dbAnalyzedElement = null;
        if (analyzedEleVersion != null) {
            List list = session.createCriteria(TdqAnalyzedElement.class).add(Expression.eq("eltUuid", uuid)).add(
                    Expression.eq("eltVersion", analyzedEleVersion)).list();
            dbAnalyzedElement = (TdqAnalyzedElement) list.get(0);
            if (!dbAnalyzedElement.valueEqual(createTdqAnalyzedElement)) {
                dbAnalyzedElement.setEltEndDate(new Date(System.currentTimeMillis()));
                session.update(dbAnalyzedElement);
                createTdqAnalyzedElement.setEltVersion(dbAnalyzedElement.getEltVersion() + 1);
                session.save(createTdqAnalyzedElement);
                isAnalyzedEleSaved = true;
            }
        } else {
            session.save(createTdqAnalyzedElement);
            isAnalyzedEleSaved = true;
        }
        cacheAnalyzedElement = isAnalyzedEleSaved ? createTdqAnalyzedElement : dbAnalyzedElement;
        tdqAnaEleCache.put(uuid, cacheAnalyzedElement);
        return cacheAnalyzedElement;
    }

    /**
     * Get the Hour:Minute display string.
     */
    public static String getSimpleDateString(Date date) {
        GregorianCalendar paremCal = new GregorianCalendar();
        paremCal.setTime(date);
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat("KK:mm");
        }
        return simpleDateFormat.format(date);

    }

    private TdqIndicatorValue[] createTdqindicatorValues(Indicator indicator, TdqAnalysis tdqAnalysis,
            TdqAnalyzedElement tdqAnalyzedElement, TdqIndicatorDefinition tdqIndicatorDefinition, Analysis analysis) {
        TdqIndicatorValue[] indicatorValues;

        // for each value in FrequencyIndicator.getDistinctValues()
        // for ModeIndicator modeIndicator.getMode()
        IndicatorsSwitch<FrequencyIndicator> frequencySwitch = new IndicatorsSwitch<FrequencyIndicator>() {

            public FrequencyIndicator caseFrequencyIndicator(FrequencyIndicator object) {
                return object;
            }
        };
        FrequencyIndicator frequencyIndicator = frequencySwitch.doSwitch(indicator);
        if (frequencyIndicator != null) {
            indicatorValues = new TdqIndicatorValue[frequencyIndicator.getDistinctValues().size()];
            Iterator<Object> iterator = frequencyIndicator.getDistinctValues().iterator();
            int i = 0;
            while (iterator.hasNext()) {
                Object next = iterator.next();
                Long count = frequencyIndicator.getCount(next);
                List list = session.createCriteria(TdqValues.class).add(Expression.eq("valString", next)).list();
                TdqValues currentTdqValues = null;
                if (list.size() > 0) {
                    TdqValues dbTdqValues = (TdqValues) list.get(0);
                    currentTdqValues = dbTdqValues;
                } else {
                    TdqValues createTdqValues = new TdqValues();
                    createTdqValues.setValString((String) next);
                    currentTdqValues = createTdqValues;
                }

                // create the new indicator value here
                indicatorValues[i] = createPartTdqIndicatorValue(indicator, tdqAnalysis, tdqAnalyzedElement,
                        tdqIndicatorDefinition, analysis);
                indicatorValues[i].setTdqValues(currentTdqValues);
                indicatorValues[i].setIndvIntValue(count.intValue());
                i++;
            }
        } else {
            TdqIndicatorValue createPartTdqIndicatorValue = createPartTdqIndicatorValue(indicator, tdqAnalysis,
                    tdqAnalyzedElement, tdqIndicatorDefinition, analysis);
            createPartTdqIndicatorValue.setIndvIntValue(indicator.getIntegerValue() == null ? SqlConstants.DEFAULT_INT
                    : indicator.getIntegerValue().intValue());
            indicatorValues = new TdqIndicatorValue[] { createPartTdqIndicatorValue };

            // case ModeIndicator, still need to save TdqValues.
            IndicatorsSwitch<ModeIndicator> modeSwitch = new IndicatorsSwitch<ModeIndicator>() {

                public ModeIndicator caseModeIndicator(ModeIndicator object) {
                    return object;
                }
            };
            ModeIndicator modeIndicator = modeSwitch.doSwitch(indicator);
            if (modeIndicator == null) {
                return indicatorValues;
            }
            List list = session.createCriteria(TdqValues.class).add(Expression.eq("valString", modeIndicator.getMode())).list();
            TdqValues currentTdqValues = null;
            if (list.size() > 0) {
                TdqValues dbTdqValues = (TdqValues) list.get(0);
                currentTdqValues = dbTdqValues;
            } else {
                TdqValues createTdqValues = new TdqValues();
                createTdqValues.setValString((String) modeIndicator.getMode());
                currentTdqValues = createTdqValues;
            }
            createPartTdqIndicatorValue.setTdqValues(currentTdqValues);

        }
        return indicatorValues;

    }

    /**
     * DOC rli Comment method "createPartTdqIndicatorValue".
     * 
     * @param indicator
     * @param tdqAnalysis
     * @param tdqAnalyzedElement
     * @param tdqIndicatorDefinition
     * @param analysis
     * @return
     */
    private TdqIndicatorValue createPartTdqIndicatorValue(Indicator indicator, TdqAnalysis tdqAnalysis,
            TdqAnalyzedElement tdqAnalyzedElement, TdqIndicatorDefinition tdqIndicatorDefinition, Analysis analysis) {
        TdqIndicatorValue indicatorValue = new TdqIndicatorValue();
        indicatorValue.setTdqAnalysis(tdqAnalysis);
        indicatorValue.setTdqAnalyzedElement(tdqAnalyzedElement);
        indicatorValue.setTdqIndicatorDefinition(tdqIndicatorDefinition);
        indicatorValue.setAnDuration(analysis.getResults().getResultMetadata().getExecutionDuration());

        // lower upper quartiles:
        indicatorValue.setIndvRealValue(indicator.getRealValue() == null ? SqlConstants.DEFAULT_REAL : indicator.getRealValue()
                .doubleValue());

        // for all indicators Indicator.getCount()
        indicatorValue.setIndvRowCount(indicator.getCount() == null ? SqlConstants.DEFAULT_INT : indicator.getCount().intValue());

        // Indicator.getValueType().getLiteral()
        indicatorValue.setIndvValueTypeIndicator(indicator.getValueType().getLiteral().charAt(0));

        // set options
        TdqIndicatorOptions createOptions = new TdqIndicatorOptions();
        char isCaseSensitive = SqlConstants.NO;
        char isCountNulls = SqlConstants.NO;
        char isCountBlanks = SqlConstants.NO;
        String regexp = SqlConstants.NULL_VALUE;
        if (indicator.getParameters() != null && indicator.getParameters().getTextParameter() != null) {
            isCaseSensitive = indicator.getParameters().getTextParameter().isIgnoreCase() ? SqlConstants.YES : SqlConstants.NO;
            isCountNulls = indicator.getParameters().getTextParameter().isUseNulls() ? SqlConstants.YES : SqlConstants.NO;
            isCountBlanks = indicator.getParameters().getTextParameter().isUseBlank() ? SqlConstants.YES : SqlConstants.NO;

        } else {
            isCaseSensitive = SqlConstants.UNDEFINED;
            isCountNulls = SqlConstants.UNDEFINED;
            isCountBlanks = SqlConstants.UNDEFINED;
        }

        createOptions.setInoCaseSensitive(isCaseSensitive);
        createOptions.setInoCountNulls(isCountNulls);
        createOptions.setInoCountBlanks(isCountBlanks);
        createOptions.setInoRegexp(regexp); // TODO scorreia try to get the regexp string.

        // insert a new row only if the full row does not exists yet
        List list = session.createCriteria(TdqIndicatorOptions.class).add(Expression.eq("inoCaseSensitive", isCaseSensitive))
                .add(Expression.eq("inoRegexp", regexp)).add(Expression.eq("inoCountNulls", isCountNulls)).add(
                        Expression.eq("inoCountBlanks", isCountBlanks)).list();
        TdqIndicatorOptions currentTdqOptions = null;
        if (list.size() > 0) {
            TdqIndicatorOptions dbTdqOptions = (TdqIndicatorOptions) list.get(0);
            currentTdqOptions = dbTdqOptions;
        } else {
            if (optionsList.indexOf(createOptions) != -1) {
                currentTdqOptions = optionsList.get(optionsList.indexOf(createOptions));
            } else {
                currentTdqOptions = createOptions;
                optionsList.add(currentTdqOptions);
            }
        }
        indicatorValue.setTdqIndicatorOptions(currentTdqOptions);

        // TODO scorreia getbins()
        // EList<RangeRestriction> ranges = indicator.getParameters().getBins().getRanges();
        // for (RangeRestriction rangeRestriction : ranges) {
        // String minValue = DomainHelper.getMinValue(rangeRestriction);
        // String maxValue = DomainHelper.getMaxValue(rangeRestriction);
        // }
        //        
        // indicatorValue.setTdqInterval(tdqInterval);
        return indicatorValue;
    }

    /**
     * Method "createTdqAnalysis".
     * 
     * @param report
     * @param analysis
     * @return the analysis ready for persistence
     */
    private TdqAnalysis createTdqAnalysis(TdReport report, Analysis analysis) {
        TdqAnalysis dbAnalysis = new TdqAnalysis();
        dbAnalysis.setAnLabel(analysis.getName());
        String anaAuthor = TaggedValueHelper.getAuthor(analysis);
        dbAnalysis.setAnAuthor(anaAuthor == null ? SqlConstants.NULL_VALUE : anaAuthor);
        dbAnalysis.setAnCreationDate(analysis.getCreationDate());
        dbAnalysis.setAnBeginDate(new Date(System.currentTimeMillis()));
        dbAnalysis.setAnEndDate(SqlConstants.END_DATE);

        String dataFilter = AnalysisHelper.getStringDataFilter(analysis);
        dbAnalysis.setAnDataFilter(dataFilter == null ? SqlConstants.NULL_VALUE : dataFilter);
        String anaStatus = TaggedValueHelper.getDevStatus(analysis).getLiteral();
        dbAnalysis.setAnStatus(anaStatus == null ? SqlConstants.NULL_VALUE : anaStatus);
        dbAnalysis.setAnUuid(ResourceHelper.getUUID(analysis));

        dbAnalysis.setRepLabel(report.getName() == null ? SqlConstants.NULL_VALUE : report.getName());
        String repAuthor = TaggedValueHelper.getAuthor(report);
        dbAnalysis.setRepAuthor(repAuthor == null ? SqlConstants.NULL_VALUE : repAuthor);
        dbAnalysis.setRepCreationDate(report.getCreationDate());
        String repStatus = TaggedValueHelper.getDevStatus(report).getLiteral();
        dbAnalysis.setRepStatus(repStatus == null ? SqlConstants.NULL_VALUE : repStatus);
        dbAnalysis.setRepUuid(ResourceHelper.getUUID(report));
        return dbAnalysis;
    }

    private TdqAnalyzedElement createTdqAnalyzedElement(TdColumn column) {
        TdqAnalyzedElement dbAnalyzedElement = new TdqAnalyzedElement();
        dbAnalyzedElement.setEltUuid(ResourceHelper.getUUID(column));
        dbAnalyzedElement.setEltColumnName(column.getName());
        dbAnalyzedElement.setEltBeginDate(new Date(System.currentTimeMillis()));
        ColumnSet columnSetOwner = ColumnHelper.getColumnSetOwner(column);
        Package parentCatalogOrSchema = ColumnSetHelper.getParentCatalogOrSchema(columnSetOwner);
        ;
        dbAnalyzedElement.setEltConnectionName(DataProviderHelper.getTdDataProvider(parentCatalogOrSchema).getName());
        dbAnalyzedElement.setEltConnectionUuid(ResourceHelper.getUUID(column));
        dbAnalyzedElement.setEltEndDate(SqlConstants.END_DATE);
        dbAnalyzedElement.setEltSchemaName(parentCatalogOrSchema.getName());
        dbAnalyzedElement.setEltCatalogName(parentCatalogOrSchema.getName());
        dbAnalyzedElement.setEltTableName(columnSetOwner.getName());
        return dbAnalyzedElement;
    }

    private TdqIndicatorDefinition createTdqIndicatorDefinition(Indicator indicator) {
        TdqIndicatorDefinition dbIndicatorDefinition = new TdqIndicatorDefinition();
        dbIndicatorDefinition.setIndBeginDate(new Date(System.currentTimeMillis()));
        dbIndicatorDefinition.setIndCategory(indicator.getIndicatorDefinition().getCategories().get(0).getLabel());
        dbIndicatorDefinition.setIndEndDate(SqlConstants.END_DATE);
        dbIndicatorDefinition.setIndLabel(indicator.getName());
        DataminingType dataminingType = indicator.getDataminingType();
        dbIndicatorDefinition.setIndType(dataminingType == null ? SqlConstants.NULL_VALUE : dataminingType.getLiteral());
        EList<IndicatorCategory> subCategories = indicator.getIndicatorDefinition().getSubCategories();
        IndicatorCategory subCategory = subCategories.size() > 0 ? subCategories.get(0) : null;
        dbIndicatorDefinition.setIndSubcategory(subCategory == null ? SqlConstants.NULL_VALUE : subCategory.getLabel());

        return dbIndicatorDefinition;
    }

}
