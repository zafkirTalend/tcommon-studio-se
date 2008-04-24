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
package org.talend.dataquality.helpers;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.talend.cwm.helper.ModelElementHelper;
import org.talend.dataquality.analysis.Analysis;
import org.talend.dataquality.analysis.AnalysisFactory;
import org.talend.dataquality.analysis.AnalysisParameters;
import org.talend.dataquality.analysis.AnalysisType;
import org.talend.dataquality.domain.Domain;
import org.talend.utils.sugars.TypedReturnCode;
import orgomg.cwm.foundation.softwaredeployment.DataManager;
import orgomg.cwm.objectmodel.core.Dependency;

/**
 * @author scorreia
 * 
 * Helper class.
 */
public class AnalysisHelper {

    private AnalysisHelper() {
    }

    /**
     * Method "createAnalysis".
     * 
     * @param name the name of the analysis
     * @return the new analysis with the given name
     */
    public static Analysis createAnalysis(String name) {
        Analysis analysis = AnalysisFactory.eINSTANCE.createAnalysis();
        analysis.setName(name);
        return analysis;
    }

    /**
     * Method "getAnalysisType".
     * 
     * @param analysis
     * @return the analysis type or null if not set
     */
    public static AnalysisType getAnalysisType(Analysis analysis) {
        AnalysisParameters parameters = analysis.getParameters();
        if (parameters == null) {
            return null;
        }
        // else
        return parameters.getAnalysisType();
    }

    /**
     * Method "setAnalysisType".
     * 
     * @param analysis an analysis
     * @param analysisType the type to set to the analysis
     */
    public static void setAnalysisType(Analysis analysis, AnalysisType analysisType) {
        AnalysisParameters parameters = analysis.getParameters();
        if (parameters == null) {
            parameters = AnalysisFactory.eINSTANCE.createAnalysisParameters();
            analysis.setParameters(parameters);
        }
        parameters.setAnalysisType(analysisType);
    }

    /**
     * Method "getDataFilter".
     * 
     * @param analysis
     * @return the list of domains or null
     */
    public static EList<Domain> getDataFilter(Analysis analysis) {
        AnalysisParameters parameters = analysis.getParameters();
        if (parameters == null) {
            return null;
        }
        return parameters.getDataFilter();
    }

    /**
     * Method "createUsageDependencyOn".
     * 
     * @param clientElement the analysis that depends on the data provider.
     * @param dataManager the data provider
     * @return a true return code if the dependency has been correctly added to the resource of the supplier element.
     * Return false otherwise. In any case, the dependency is created and the getObject() method returns it.
     */
    public static TypedReturnCode<Dependency> createUsageDependencyOn(Analysis clientElement, DataManager dataManager) {
        assert dataManager != null;
        Dependency dependency = ModelElementHelper.createDependencyOn(ModelElementHelper.ANALYSIS_DATAPROVIDER,
                clientElement, dataManager);
        TypedReturnCode<Dependency> rc = new TypedReturnCode<Dependency>();
        rc.setObject(dependency);
        // store dependency in supplier's resource
        Resource resource = dataManager.eResource();
        if (resource == null) {
            rc.setReturnCode("No resource found in given " + dataManager.getName(), false);
            return rc;
        }
        // else
        resource.getContents().add(dependency);
        return rc;
    }
}
