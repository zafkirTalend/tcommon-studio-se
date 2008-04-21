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
import org.talend.commons.emf.ResourceHelper;
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
import org.talend.dataquality.reports.TdReport;

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
 * IS_LAST: a boolean that tells that the couple (report, analysis) is the last instance or not. When set to true
 * (SqlConstants.YES), the END_DATE should be the future date.<br>
 * VERSION: an integer that is incremented each time a new couple (report, analysis) is created.<br>
 * 
 * Each row of the table TDQ_ANALYSIS is an instance of the couple (TdReport, Analysis). <br>
 * Analyzed elements and analysis have universal identifiers (UUIDs). These UUIDs can be retrieved with
 * ResourceHelper.getId().<br>
 * 
 * Simple indicators such as count indicators
 * 
 */
public class DatabasePersistence {

    private Session session;

    public boolean persist(TdReport report) {
        boolean ok = true;
        // create a session transaction
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

        // TODO rli get the last instance of TdqAnalysis from DB
        // get row from DB with report's UUID, analysis' UUID and IS_LAST=SqlConstants.YES
        String reportUuid = ResourceHelper.getUUID(report);
        String analysisUuid = ResourceHelper.getUUID(analysis);

        // TODO rli create a current TdqAnalysis from analysis and report
        // TODO rli check whether a new TdqAnalysis must be created
        // (compare values of TdqAnalysis with current TdqAnalysis

        // TODO rli if the new instance TdqAnalysis has changes, then
        // update the last instance (end date = now, is_last = SqlCosntants.NO)
        // and persist the new instance of TdqAnalysis with a new version number

        // if no changes exist, continue
        // TODO save indicators
        AnalysisResult results = analysis.getResults();
        EList<Indicator> indicators = results.getIndicators();
        for (Indicator indicator : indicators) {
            persist(indicator);
        }

    }

    /**
     * DOC scorreia Comment method "persist".
     * 
     * @param indicator
     */
    private void persist(Indicator indicator) {
        indicator.getAnalyzedElement();
        // TODO persist analyzed element (try to get it first before creating it with its UUID)
        // update row only when something has changed (then update END_DATE, VERSION, and IS_LAST columns)

        // TODO do the same for TdqIndicatorDefinition (get the definition with its LABEL which identifies a row)
        // update only if something has changed (then update END_DATE, VERSION, and IS_LAST columns)

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

        // TODO complete code here
        return dbAnalysis;
    }

    private TdqAnalyzedElement createTdqAnalyzedElement(TdColumn column) {
        TdqAnalyzedElement dbAnalyzedElement = new TdqAnalyzedElement();
        // TODO implement me
        return dbAnalyzedElement;
    }

    private TdqIndicatorDefinition createTdqIndicatorDefinition(Indicator indicator) {
        TdqIndicatorDefinition dbIndicatorDefinition = new TdqIndicatorDefinition();
        dbIndicatorDefinition.setIndBeginDate(new Date(System.currentTimeMillis()));
        dbIndicatorDefinition.setIndIsLast(SqlConstants.YES);

        // TODO implement me
        return dbIndicatorDefinition;
    }

}
