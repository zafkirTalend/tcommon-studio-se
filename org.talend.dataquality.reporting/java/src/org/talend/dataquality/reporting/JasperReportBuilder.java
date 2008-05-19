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
package org.talend.dataquality.reporting;

import java.sql.Connection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 * @author scorreia
 * 
 * Creates reports from given jrxml source file.
 */
public class JasperReportBuilder {

    private static Logger log = Logger.getLogger(JasperReportBuilder.class);

    /**
     * The file output format.
     */
    public static enum OUTPUT_FORMAT {
        pdf,
        xml,
        html
    };

    private static final OUTPUT_FORMAT DEFAULT_FORMAT = OUTPUT_FORMAT.pdf;

    private static final String DEFAULT_FILENAME = "TalendDataprofilerReport";

    private Connection connection;

    private String jrxmlSource;

    private String errorMessage = null;

    private String outpufFilename;

    private Set<OUTPUT_FORMAT> formats = new HashSet<OUTPUT_FORMAT>();

    /**
     * Method "addFormat". This methods is used to choose the output formats. It can be called several times in order to
     * output the report in several file formats.
     * 
     * @param format the output format to add.
     * @return true when the format is added for the first time.
     */
    public boolean addFormat(OUTPUT_FORMAT format) {
        return this.formats.add(format);
    }

    /**
     * Method "removeFormat".
     * 
     * @param format the format to remove from the output formats
     * @return true if the format was set and has been removed.
     */
    public boolean removeFormat(OUTPUT_FORMAT format) {
        return this.formats.remove(format);
    }

    /**
     * DOC scorreia Comment method "run".
     */
    public boolean run() {
        boolean ok = true;
        JasperReport jasperReport;

        JasperPrint jasperPrint;

        try {
            if (jrxmlSource == null) {
                this.errorMessage = "Error: Jasper source file is null!";
                return false;
            }

            if (formats.isEmpty()) {
                if (log.isInfoEnabled()) {
                    log.info("No output format set for jasper report " + jrxmlSource + ". Using default format: "
                            + DEFAULT_FORMAT);
                }
                this.formats.add(DEFAULT_FORMAT);
            }

            if (this.outpufFilename == null) {
                if (log.isInfoEnabled()) {
                    log.info("No output filename given for jasper report " + jrxmlSource + ". Using default filename "
                            + DEFAULT_FILENAME);
                }
                this.outpufFilename = DEFAULT_FILENAME;
            }
            jasperReport = JasperCompileManager.compileReport(jrxmlSource);

            jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<Object, Object>(), connection);

            // generates PDF
            if (formats.contains(OUTPUT_FORMAT.pdf)) {
                JasperExportManager.exportReportToPdfFile(jasperPrint, this.outpufFilename + ".pdf");
            }

            // generates HTML
            if (formats.contains(OUTPUT_FORMAT.html)) {
                JasperExportManager.exportReportToHtmlFile(jasperPrint, this.outpufFilename + ".html");
            }

            // generates XML
            if (formats.contains(OUTPUT_FORMAT.xml)) {
                JasperExportManager.exportReportToXmlFile(jasperPrint, this.outpufFilename + ".xml", true);
            }

        } catch (JRException jrException) {
            this.errorMessage = jrException.getMessage();
            log.error(jrException, jrException);
            ok = false;
        }
        return ok;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getJrxmlSource() {
        return this.jrxmlSource;
    }

    public void setJrxmlSource(String jrxmlSource) {
        this.jrxmlSource = jrxmlSource;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public String getOutpufFilename() {
        return this.outpufFilename;
    }

    /**
     * Method "setOutpufFilename".
     * 
     * @param outpufFilename the output file name without the extension.
     */
    public void setOutpufFilename(String outpufFilename) {
        this.outpufFilename = outpufFilename;
    }
}
