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
package org.talend.dataprofiler.core.helper;

import java.io.File;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.talend.dataquality.analysis.Analysis;
import org.talend.dataquality.analysis.util.AnalysisSwitch;

/**
 * DOC rli class global comment. Detailled comment
 */
public final class AnaResourceFileHelper extends ResourceFileMap {

    private static Logger log = Logger.getLogger(AnaResourceFileHelper.class);

    private static AnaResourceFileHelper instance;

    private AnaResourceFileHelper() {
        super();
    }

    public static AnaResourceFileHelper getInstance() {
        if (instance == null) {
            instance = new AnaResourceFileHelper();
        }
        return instance;
    }

    public Analysis getAnalysis(IFile file) {
        Resource fileResource = getFileResource(file);
        Analysis analysis = retireAnalysis(fileResource);
        return analysis;
    }
    public Analysis getAnalysis(File file) {
        Resource fileResource = getFileResource(file);
        Analysis analysis = retireAnalysis(fileResource);
        return analysis;
    }

    /**
     * DOC rli Comment method "retireAnalysis".
     * @param fileResource
     * @return
     */
    private Analysis retireAnalysis(Resource fileResource) {
        EList<EObject> contents = fileResource.getContents();
        if (contents.isEmpty()) {
            log.error("No content in " + fileResource);
        }
        log.info("Nb elements in contents " + contents.size());
        AnalysisSwitch<Analysis> mySwitch = new AnalysisSwitch<Analysis>() {

            public Analysis caseAnalysis(Analysis object) {
                return object;
            }
        };
        Analysis analysis = null;
        if (contents != null) {
            analysis = mySwitch.doSwitch(contents.get(0));
        }
        return analysis;
    }
}
