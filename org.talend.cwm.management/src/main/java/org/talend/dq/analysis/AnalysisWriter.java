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

import org.talend.commons.emf.EMFUtil;
import org.talend.commons.emf.FactoriesUtil;
import org.talend.dataquality.analysis.Analysis;
import org.talend.utils.sugars.ReturnCode;

/**
 * @author scorreia
 * 
 * This class saves the analysis.
 */
public class AnalysisWriter {

    public static final String VALID_EXTENSION = FactoriesUtil.ANA;

    /**
     * Method "save".
     * 
     * @param analysis the analysis to save
     * @param file the file in which the analysis will be save
     * @return whether everything is ok
     */
    public ReturnCode save(Analysis analysis, File file) {
        assert file != null : "Cannot save analysis: No file name given.";
        assert analysis != null : "No analysis to save (null)";

        ReturnCode rc = new ReturnCode();
        if (!checkFileExtension(file)) {
            rc.setReturnCode("Bad file extension for " + file.getAbsolutePath() + ". Should be " + VALID_EXTENSION,
                    false);
            return rc;
        }
        EMFUtil util = new EMFUtil();
        // Resource resource = util.getResourceSet().createResource(URI.createFileURI(file.getAbsolutePath()));
        // resource.getContents().addAll(analysis.getResults().getIndicators());

        boolean added = util.addPoolToResourceSet(file, analysis);

        if (!added) {
            rc.setReturnCode("Analysis won't be saved. " + util.getLastErrorMessage(), added);
            return rc;
        }
        boolean saved = util.save();
        if (!saved) {
            rc.setReturnCode("Problem while saving analysis " + analysis.getName() + ". " + util.getLastErrorMessage(),
                    saved);
        }
        return rc;
    }

    private boolean checkFileExtension(File file) {
        return file.getAbsolutePath().endsWith(VALID_EXTENSION);
    }
}
