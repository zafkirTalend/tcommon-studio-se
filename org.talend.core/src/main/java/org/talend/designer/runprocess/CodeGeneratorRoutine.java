// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.runprocess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.CorePlugin;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.general.ILibrariesService;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.properties.RoutineItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.repository.ProjectManager;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * Build RoutineName for PerlHeader.
 * 
 * $Id: CodeGeneratorRoutine.java 14854 2008-06-06 16:05:36Z mhelleboid $
 * 
 */
public final class CodeGeneratorRoutine {

    /**
     * Default Constructor. Must not be used.
     */
    private CodeGeneratorRoutine() {
    }

    @SuppressWarnings("unchecked")
    public static List<String> getRequiredRoutineName(IProcess process) {
        Set<String> neededRoutines = process.getNeededRoutines();
        ECodeLanguage currentLanguage = LanguageManager.getCurrentLanguage();
        String perlConn = "::"; //$NON-NLS-1$
        String builtInPath = ILibrariesService.SOURCE_PERL_ROUTINES_FOLDER + perlConn + "system" + perlConn; //$NON-NLS-1$ 
        String userPath = ILibrariesService.SOURCE_PERL_ROUTINES_FOLDER + perlConn
                + ProjectManager.getInstance().getCurrentProject().getTechnicalLabel();
        if (neededRoutines == null || neededRoutines.isEmpty()) {
            try {
                List<IRepositoryViewObject> objects = CorePlugin.getDefault().getProxyRepositoryFactory()
                        .getAll(ERepositoryObjectType.ROUTINES);
                neededRoutines = new HashSet<String>();
                for (IRepositoryViewObject object : objects) {
                    neededRoutines.add(object.getLabel());
                }
            } catch (PersistenceException e) {
                ExceptionHandler.process(e);
            }
        }
        if (currentLanguage == ECodeLanguage.PERL) {
            List<IRepositoryViewObject> availableRoutines;
            try {
                IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
                availableRoutines = factory.getAll(ProjectManager.getInstance().getCurrentProject(),
                        ERepositoryObjectType.ROUTINES);
                Set<String> newNeededRoutines = new HashSet<String>();
                for (IRepositoryViewObject object : availableRoutines) {
                    if (neededRoutines.contains(object.getLabel())) {
                        neededRoutines.remove(object.getLabel());
                        if (((RoutineItem) object.getProperty().getItem()).isBuiltIn()) {
                            newNeededRoutines.add(builtInPath + object.getLabel());
                        } else {
                            newNeededRoutines.add(userPath + object.getLabel());
                        }
                    }
                }
                if (newNeededRoutines.isEmpty()) {
                    return new ArrayList<String>(newNeededRoutines);
                }
                for (org.talend.core.model.general.Project project : ProjectManager.getInstance().getAllReferencedProjects()) {
                    for (IRepositoryViewObject object : factory.getAll(project, ERepositoryObjectType.ROUTINES)) {
                        if (neededRoutines.contains(object.getLabel())) {
                            neededRoutines.remove(object.getLabel());
                            if (((RoutineItem) object.getProperty().getItem()).isBuiltIn()) {
                                newNeededRoutines.add(builtInPath + object.getLabel());
                            } else {
                                newNeededRoutines.add(userPath + object.getLabel());
                            }
                        }
                    }
                    if (newNeededRoutines.isEmpty()) {
                        return new ArrayList<String>(newNeededRoutines);
                    }
                }
                neededRoutines = newNeededRoutines;
            } catch (PersistenceException e) {
                ExceptionHandler.process(e);
            }
        }
        return new ArrayList<String>(neededRoutines);
    }
}
