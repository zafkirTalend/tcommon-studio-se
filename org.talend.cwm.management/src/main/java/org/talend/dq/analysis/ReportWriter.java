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

import java.io.File;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.talend.commons.emf.EMFUtil;
import org.talend.commons.emf.FactoriesUtil;
import org.talend.cwm.dependencies.DependenciesHandler;
import org.talend.dataquality.analysis.Analysis;
import org.talend.dataquality.helpers.ReportHelper;
import org.talend.dataquality.reports.TdReport;
import org.talend.utils.sugars.ReturnCode;
import org.talend.utils.sugars.TypedReturnCode;
import orgomg.cwm.objectmodel.core.Dependency;

/**
 * @author scorreia
 * 
 * This class saves the analysis.
 */
public class ReportWriter {

    public static final String VALID_EXTENSION = FactoriesUtil.REP;

    /**
     * Method "save".
     * 
     * @param report the analysis to save
     * @param file the file in which the analysis will be save
     * @return whether everything is ok
     */
    public ReturnCode save(TdReport report, File file) {
        assert file != null : "Cannot save report: No file name given.";
        assert report != null : "No report to save (null)";

        ReturnCode rc = new ReturnCode();
        if (!checkFileExtension(file)) {
            rc.setReturnCode("Bad file extension for " + file.getAbsolutePath() + ". Should be " + VALID_EXTENSION, false);
            return rc;
        }
        EMFUtil util = new EMFUtil();
        // Resource resource = util.getResourceSet().createResource(URI.createFileURI(file.getAbsolutePath()));
        // resource.getContents().addAll(report.getResults().getIndicators());

        boolean added = util.addPoolToResourceSet(file, report);

        if (!added) {
            rc.setReturnCode("Report  " + report.getName() + " won't be saved. " + util.getLastErrorMessage(), added);
            return rc;
        }

        // --- store descriptions (description and purpose) in the same resource
        EList<EObject> resourceContents = report.eResource().getContents();
        resourceContents.addAll(report.getDescription());
        
        for (Analysis ana : ReportHelper.getAnalyses(report)) {
            TypedReturnCode<Dependency> dependencyReturn = DependenciesHandler.getInstance().setDependencyOn(report, ana);
            if (dependencyReturn.isOk()) {
                util.getResourceSet().getResources().add(DependenciesHandler.getInstance().getDependencyResource());
                util.getResourceSet().getResources().add(ana.eResource());
            }
        }
        boolean saved = util.save();
        if (!saved) {
            rc.setReturnCode("Problem while saving report " + report.getName() + ". " + util.getLastErrorMessage(), saved);
        }
        return rc;
    }

    private boolean checkFileExtension(File file) {
        return file.getAbsolutePath().endsWith(VALID_EXTENSION);
    }
}
