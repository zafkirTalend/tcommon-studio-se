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

import org.hibernate.Session;
import org.hibernate.Transaction;
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
import org.talend.dataquality.helpers.ReportHelper;
import org.talend.dataquality.indicators.Indicator;
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
    private void persist(TdReport report, Analysis analysis) {
        // Get the last instance of TdqAnalysis from DB
        // get row from DB with report's UUID, analysis' UUID and IS_LAST=SqlConstants.YES
        String reportUuid = ResourceHelper.getUUID(report);
        String analysisUuid = ResourceHelper.getUUID(analysis);
        List list = session.createCriteria(TdqAnalysis.class).add(Expression.eq("anUuid", analysisUuid)).add(
                Expression.eq("repUuid", reportUuid)).add(Expression.eq("isLast", SqlConstants.YES)).list();

        //Create a current TdqAnalysis from analysis and report
        TdqAnalysis createTdqAnalysis = createTdqAnalysis(report, analysis);

        //check whether a new TdqAnalysis must be created
        // (compare values of TdqAnalysis with current TdqAnalysis
        TdqAnalysis dbTdqAnalysis = null;
        if (list.size() > 0) {
            dbTdqAnalysis = (TdqAnalysis) list.get(0);
            
            // If the new instance TdqAnalysis has changes, then
            // update the last instance (end date = now, is_last = SqlCosntants.NO)
            // and persist the new instance of TdqAnalysis with a new version number
            if (!dbTdqAnalysis.valueEqual(createTdqAnalysis)) {
                dbTdqAnalysis.setAnEndDate(new Date(System.currentTimeMillis()));
                dbTdqAnalysis.setIsLast(SqlConstants.NO);
                session.update(dbTdqAnalysis);
                session.save(createTdqAnalysis);
            }
        } else {
            session.save(createTdqAnalysis);
        }

        // if no changes exist, continue
        // TODO save indicators
        // AnalysisResult results = analysis.getResults();
        // EList<Indicator> indicators = results.getIndicators();
        // for (Indicator indicator : indicators) {
        // persist(indicator);
        // }

    }

    /**
     * DOC scorreia Comment method "persist".
     * 
     * @param indicator
     */
    private void persist(Indicator indicator) {
        // TODO persist analyzed element (try to get it first before creating it with its UUID)
        // update row only when something has changed (then update END_DATE, VERSION, and IS_LAST columns)
        createTdqAnalyzedElement((TdColumn) indicator.getAnalyzedElement());
        List list = session.createQuery("from TDQ_ANALYZED_ELEMENT as a where a.eltUuid:uuid").setString("uuid",
                ResourceHelper.getUUID(indicator.getAnalyzedElement())).list();

        org.hibernate.classic.Session session2 = HibernateUtil.getSessionFactory().openSession();
        Transaction tx2 = session2.beginTransaction();

        // TODO do the same for TdqIndicatorDefinition (get the definition with its LABEL which identifies a row)
        // update only if something has changed (then update END_DATE, VERSION, and IS_LAST columns)
        createTdqIndicatorDefinition(indicator);

        tx2.commit();

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
        dbAnalysis.setAnAuthor(TaggedValueHelper.getAuthor(analysis));
        dbAnalysis.setAnCreationDate(analysis.getCreationDate());
        dbAnalysis.setAnBeginDate(new Date(System.currentTimeMillis()));
        dbAnalysis.setAnEndDate(SqlConstants.END_DATE);
        dbAnalysis.setAnDataFilter("filter");
        dbAnalysis.setAnStatus(TaggedValueHelper.getDevStatus(analysis).getLiteral());
        // dbAnalysis.setAnVersion(0);
        dbAnalysis.setAnUuid(ResourceHelper.getUUID(analysis));

        dbAnalysis.setRepLabel(report.getName());
        dbAnalysis.setRepAuthor(TaggedValueHelper.getAuthor(report));
        dbAnalysis.setRepCreationDate(report.getCreationDate());
        dbAnalysis.setRepStatus(TaggedValueHelper.getDevStatus(report).getLiteral());
        dbAnalysis.setRepUuid(ResourceHelper.getUUID(report));
        dbAnalysis.setIsLast(SqlConstants.YES);
        return dbAnalysis;
    }

    private TdqAnalyzedElement createTdqAnalyzedElement(TdColumn column) {
        TdqAnalyzedElement dbAnalyzedElement = new TdqAnalyzedElement();
        // dbAnalyzedElement.setEltBeginDate(eltBeginDate);
        dbAnalyzedElement.setEltCatalogName(column.getName());
        ColumnSet columnSetOwner = ColumnHelper.getColumnSetOwner(column);
        Package parentCatalogOrSchema = ColumnSetHelper.getParentCatalogOrSchema(columnSetOwner);
        ;
        dbAnalyzedElement.setEltConnectionName(DataProviderHelper.getTdDataProvider(parentCatalogOrSchema).getName());
        dbAnalyzedElement.setEltConnectionUuid(ResourceHelper.getUUID(column));
        // dbAnalyzedElement.setEltEndDate(eltEndDate);
        dbAnalyzedElement.setEltSchemaName(parentCatalogOrSchema.getName());
        dbAnalyzedElement.setEltCatalogName(parentCatalogOrSchema.getName());
        dbAnalyzedElement.setEltTableName(columnSetOwner.getName());
        // dbAnalyzedElement.setEltVersion(eltVersion)
        return dbAnalyzedElement;
    }

    private TdqIndicatorDefinition createTdqIndicatorDefinition(Indicator indicator) {
        TdqIndicatorDefinition dbIndicatorDefinition = new TdqIndicatorDefinition();
        dbIndicatorDefinition.setIndBeginDate(new Date(System.currentTimeMillis()));
        dbIndicatorDefinition.setIndCategory(indicator.getIndicatorDefinition().getCategories().get(0).getLabel());
        // dbIndicatorDefinition.setIndEndDate(indicator.)
        dbIndicatorDefinition.setIndLabel(indicator.getName());
        dbIndicatorDefinition.setIndType(indicator.getIndicatorType().getName());
        dbIndicatorDefinition.setIndSubcategory(indicator.getIndicatorDefinition().getSubCategories().get(0).getLabel());
        // dbIndicatorDefinition.setTdqIndicatorValues(tdqIndicatorValues)

        return dbIndicatorDefinition;
    }

}
