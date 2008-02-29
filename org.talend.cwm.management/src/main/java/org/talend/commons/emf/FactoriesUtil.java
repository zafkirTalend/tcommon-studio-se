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
package org.talend.commons.emf;

import java.util.ArrayList;
import java.util.List;

import org.talend.cwm.relational.RelationalPackage;
import org.talend.cwm.softwaredeployment.SoftwaredeploymentPackage;
import org.talend.dataquality.domain.impl.DomainFactoryImpl;
import org.talend.dataquality.domain.pattern.impl.PatternFactoryImpl;
import org.talend.dataquality.indicators.IndicatorsPackage;
import org.talend.dataquality.indicators.impl.IndicatorsFactoryImpl;
import orgomg.cwm.foundation.typemapping.TypemappingPackage;
import orgomg.cwm.objectmodel.core.CorePackage;

/**
 * @author scorreia
 * 
 * This class is a utility for CWM and Talend extension Factories initialization.
 */
public class FactoriesUtil {

    /**
     * Method "initializeAllFactories" calls static method init() for each of the factories in this project.
     */
    public static void initializeAllFactories() {
        // --- talend extension packages
        org.talend.cwm.softwaredeployment.impl.SoftwaredeploymentFactoryImpl.init();
        org.talend.cwm.relational.impl.RelationalFactoryImpl.init();

        // --- talend DQ factories
        DomainFactoryImpl.init();
        IndicatorsFactoryImpl.init();
        PatternFactoryImpl.init();
        org.talend.dataquality.expressions.impl.ExpressionsFactoryImpl.init();

        // CWM generated packages
        // TODO scorreia add other factories
        orgomg.cwm.foundation.softwaredeployment.impl.SoftwaredeploymentFactoryImpl.init();
        orgomg.cwm.resource.relational.impl.RelationalFactoryImpl.init();

        orgomg.cwmmip.impl.CwmmipFactoryImpl.init();
        orgomg.mof.model.impl.ModelFactoryImpl.init();
        orgomg.cwm.foundation.datatypes.impl.DatatypesFactoryImpl.init();
        orgomg.cwm.objectmodel.core.impl.CoreFactoryImpl.init();
        orgomg.cwm.objectmodel.relationships.impl.RelationshipsFactoryImpl.init();
        orgomg.cwm.foundation.typemapping.impl.TypemappingFactoryImpl.init();
    }

    /**
     * Method "getExtensions".
     * 
     * @return the list of file extensions
     */
    public static List<String> getExtensions() {
        List<String> extensions = new ArrayList<String>();
        // --- Talend extension packages
        extensions.add(SoftwaredeploymentPackage.eNAME);
        extensions.add(RelationalPackage.eNAME);

        // --- Talend DQ extension packages
        extensions.add(IndicatorsPackage.eNAME);

        // --- CWM generated packages
        extensions.add(CorePackage.eNAME);
        extensions.add(TypemappingPackage.eNAME);
        // TODO scorreia add other file extensions
        return extensions;
    }
}
