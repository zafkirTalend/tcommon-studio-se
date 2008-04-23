package org.talend.dataprofiler.persistence.tests;

import java.util.Date;

import org.hibernate.Session;
import org.talend.dataprofiler.persistence.TdqAnalysis;
import org.talend.dataprofiler.persistence.utils.HibernateUtil;

/**
 * @author scorreia
 * 
 * my first test.
 */
public class MyTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        // try {
        // new InitialContext().bind("SessionFactory", HibernateUtil.getSessionFactory());
        // } catch (NamingException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

        session.beginTransaction();

        TdqAnalysis analysis = new TdqAnalysis(); // 
        analysis.setAnBeginDate(new Date(System.currentTimeMillis()));
        analysis.setAnLabel("analyse");
        analysis.setAnCreationDate(new Date(System.currentTimeMillis()));
        analysis.setAnAuthor("me");
        analysis.setAnDataFilter("");
        analysis.setAnEndDate(new Date(System.currentTimeMillis()));
        analysis.setAnVersion(2);
        analysis.setAnUuid("XMI id of analysis");
        analysis.setAnStatus("PROD");
        analysis.setRepAuthor("me again");
        analysis.setRepCreationDate(new Date(System.currentTimeMillis()));
        analysis.setRepUuid("Report's UUID");
        analysis.setRepLabel("Report 1");
        analysis.setRepStatus("PROD");

        session.save(analysis);

        System.out.println("Report Id=" + analysis.getAnPk());
        session.getTransaction().commit();

    }
}
