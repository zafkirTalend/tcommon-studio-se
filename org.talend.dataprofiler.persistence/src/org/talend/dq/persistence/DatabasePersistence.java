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

import java.util.Date;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.talend.commons.emf.ResourceHelper;
import org.talend.cwm.helper.ColumnHelper;
import org.talend.cwm.helper.ColumnSetHelper;
import org.talend.cwm.helper.DataProviderHelper;
import org.talend.cwm.helper.TaggedValueHelper;
import org.talend.cwm.relational.TdColumn;
import org.talend.dataprofiler.persistence.TdqAnalysis;
import org.talend.dataprofiler.persistence.TdqAnalyzedElement;
import org.talend.dataprofiler.persistence.TdqIndicatorDefinition;
import org.talend.dataprofiler.persistence.business.SqlConstants;
import org.talend.dataprofiler.persistence.utils.HibernateUtil;
import org.talend.dataquality.analysis.Analysis;
import org.talend.dataquality.analysis.AnalysisResult;
import org.talend.dataquality.helpers.ReportHelper;
import org.talend.dataquality.indicators.Indicator;
import org.talend.dataquality.indicators.definition.IndicatorCategory;
import org.talend.dataquality.reports.TdReport;
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

    public boolean persist(TdReport report) {
        boolean ok = true;
        // // create a session transaction
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // persist in database
        List<Analysis> analyses = ReportHelper.getAnalyses(report);
        for (Analysis analysis : analyses) {
            persist(report, analysis);
        }

        // commit and close session
        session.getTransaction().commit();
        return ok;
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
        String reportUuid = ResourceHelper.getUUID(report);
        String analysisUuid = ResourceHelper.getUUID(analysis);
        List maxVersionList = session.createQuery(
                "select MAX(anVersion) from TdqAnalysis where anUuid='" + analysisUuid + "' and repUuid='" + reportUuid + "'")
                .list();
        Integer maxversion = (Integer) maxVersionList.get(0);

        // Create a current TdqAnalysis from analysis and report
        TdqAnalysis createTdqAnalysis = createTdqAnalysis(report, analysis);

        // check whether a new TdqAnalysis must be created
        // (compare values of TdqAnalysis with current TdqAnalysis
        TdqAnalysis dbTdqAnalysis = null;
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
            }
        } else {
            session.save(createTdqAnalysis);
        }

        // if no changes exist, continue
        // save indicators
        AnalysisResult results = analysis.getResults();
        EList<Indicator> indicators = results.getIndicators();
        for (Indicator indicator : indicators) {
            persist(indicator);
        }

    }

    /**
     * persist the indicator object.
     * 
     * @param indicator
     */
    @SuppressWarnings("unchecked")
    private void persist(Indicator indicator) {
        // Persist analyzed element (try to get it first before creating it with its UUID)
        // update row only when something has changed (then update END_DATE, VERSION)
        String uuid = ResourceHelper.getUUID(indicator.getAnalyzedElement());
        List analyzedEleVersionList = session.createQuery(
                "select MAX(eltVersion) from TdqAnalyzedElement where eltUuid='" + uuid + "'").list();
        Integer analyzedEleVersion = (Integer) analyzedEleVersionList.get(0);

        TdqAnalyzedElement createTdqAnalyzedElement = createTdqAnalyzedElement((TdColumn) indicator.getAnalyzedElement());
        if (analyzedEleVersion != null) {
            List list = session.createCriteria(TdqAnalyzedElement.class).add(Expression.eq("eltUuid", uuid)).add(
                    Expression.eq("eltVersion", analyzedEleVersion)).list();
            TdqAnalyzedElement dbAnalyzedElement = (TdqAnalyzedElement) list.get(0);
            if (!dbAnalyzedElement.valueEqual(createTdqAnalyzedElement)) {
                dbAnalyzedElement.setEltEndDate(new Date(System.currentTimeMillis()));
                session.update(dbAnalyzedElement);
                createTdqAnalyzedElement.setEltVersion(dbAnalyzedElement.getEltVersion() + 1);
                session.save(createTdqAnalyzedElement);
            }
        } else {
            session.save(createTdqAnalyzedElement);
        }

        // Do the same for TdqIndicatorDefinition (get the definition with its LABEL which identifies a row)
        // update only if something has changed (then update END_DATE, VERSION)
        String indLabel = indicator.getName();

        List indDefinitionVersionList = session.createQuery(
                "select MAX(indVersion) from TdqIndicatorDefinition where indLabel='" + indLabel + "'").list();
        Integer indDefinitionVersion = (Integer) indDefinitionVersionList.get(0);
        TdqIndicatorDefinition createTdqIndicatorDefinition = createTdqIndicatorDefinition(indicator);
        if (indDefinitionVersion != null) {
            List list = session.createCriteria(TdqIndicatorDefinition.class).add(Expression.eq("indLabel", indLabel)).add(
                    Expression.eq("indVersion", indDefinitionVersion)).list();
            TdqIndicatorDefinition dbIndDefinition = (TdqIndicatorDefinition) list.get(0);
            if (!dbIndDefinition.valueEqual(createTdqIndicatorDefinition)) {
                dbIndDefinition.setIndEndDate(new Date(System.currentTimeMillis()));
                session.update(dbIndDefinition);
                createTdqIndicatorDefinition.setIndVersion(dbIndDefinition.getIndVersion() + 1);
                session.save(createTdqIndicatorDefinition);
            }
        } else {
            session.save(createTdqIndicatorDefinition);
        }

        // TODO get the tdqCalendar object and the TdqDayTime from the database given the
        // date analysis.getResults().getResultMetadata().getExecutionDate()
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
        dbAnalysis.setAnDataFilter("filter");
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
        dbIndicatorDefinition.setIndType(indicator.getIndicatorType() == null ? SqlConstants.NULL_VALUE : indicator
                .getIndicatorType().getName());
        EList<IndicatorCategory> subCategories = indicator.getIndicatorDefinition().getSubCategories();
        IndicatorCategory subCategory = subCategories.size() > 0 ? subCategories.get(0) : null;
        dbIndicatorDefinition.setIndSubcategory(subCategory == null ? SqlConstants.NULL_VALUE : subCategory.getLabel());

        return dbIndicatorDefinition;
    }

}
