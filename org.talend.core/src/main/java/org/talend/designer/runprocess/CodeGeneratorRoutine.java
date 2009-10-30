// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.general.ILibrariesService;
import org.talend.core.model.general.Project;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.properties.InformationLevel;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.RoutineItem;
import org.talend.core.model.properties.impl.ProjectReferenceImpl;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.repository.ProjectManager;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * Build RoutineName for PerlHeader.
 * 
 * $Id: CodeGeneratorRoutine.java 14854 2008-06-06 16:05:36Z mhelleboid $
 * 
 */
public final class CodeGeneratorRoutine {

    private static Map<org.talend.core.model.properties.Project, List<String>> routinesName = new HashMap<org.talend.core.model.properties.Project, List<String>>();

    /**
     * Default Constructor. Must not be used.
     */
    private CodeGeneratorRoutine() {
    }

    public static void reset() {
        routinesName.clear();
    }

    /**
     * Actually used in ProcessorUtilities.generateCode.
     * 
     * @param processItem
     */
    public static void initializeRoutinesName(Item processItem) {
        ProjectManager pManager = ProjectManager.getInstance();

        org.talend.core.model.properties.Project emfProject = pManager.getProject(processItem);
        IProxyRepositoryFactory repositoryFactory = CorePlugin.getDefault().getRepositoryService().getProxyRepositoryFactory();
        List<String> toReturn = initRoutineNamesFromProject(emfProject, repositoryFactory);
        routinesName.put(emfProject, toReturn);

        EList referencedProjects = emfProject.getReferencedProjects();
        if (referencedProjects != null) {
            for (Object object : referencedProjects) {
                ProjectReferenceImpl projectRef = (ProjectReferenceImpl) object;
                org.talend.core.model.properties.Project refEmfProject = projectRef.getReferencedProject();
                toReturn = initRoutineNamesFromProject(refEmfProject, repositoryFactory);
                routinesName.put(refEmfProject, toReturn);
            }
        }

    }

    private static List<String> initRoutineNamesFromProject(org.talend.core.model.properties.Project emfProject,
            IProxyRepositoryFactory repositoryFactory) {
        List<String> toReturn = new ArrayList<String>();

        if (emfProject == null || repositoryFactory == null) {
            return toReturn;
        }
        Project currentProject = new Project(emfProject);

        ECodeLanguage currentLanguage = currentProject.getLanguage();

        String builtInPath = ILibrariesService.SOURCE_PERL_ROUTINES_FOLDER + "::" + "system" + "::"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        String userPath = ILibrariesService.SOURCE_PERL_ROUTINES_FOLDER + "::" + currentProject.getTechnicalLabel() + "::"; //$NON-NLS-1$ //$NON-NLS-2$
        try {
            List<IRepositoryObject> routines = repositoryFactory.getAll(currentProject, ERepositoryObjectType.ROUTINES);
            for (IRepositoryObject routine : routines) {
                RoutineItem item = (RoutineItem) routine.getProperty().getItem();
                if (currentLanguage.equals(ECodeLanguage.JAVA)) {
                    InformationLevel level = routine.getProperty().getMaxInformationLevel();
                    if (level.getValue() == InformationLevel.ERROR) {
                        continue;
                    }
                    if (item.isBuiltIn()) {
                        toReturn.add(routine.getLabel());
                    } else {
                        toReturn.add(routine.getLabel());
                        // toReturn.add(emfProject.getTechnicalLabel().toLowerCase() + "." + routine.getLabel());
                    }
                } else {
                    if (item.isBuiltIn()) {
                        toReturn.add(builtInPath + routine.getLabel());
                    } else {
                        toReturn.add(userPath + routine.getLabel());
                    }
                }
            }
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }

        return toReturn;
    }

    /**
     * initializeRoutinesName must be called first or it might return wrong list name or null.
     */
    public static List<String> getRoutineName() {
        // return routinesName;
        return new ArrayList<String>();
    }

    /**
     * DOC bqian Comment method "getRoutineName".
     * 
     * @param process
     * @return
     */
    public static List<String> getRoutineName(IProcess process) {
        Item processItem = process.getProperty().getItem();
        ProjectManager pManager = ProjectManager.getInstance();

        org.talend.core.model.properties.Project emfProject = pManager.getProject(processItem);
        List<String> routines = getRoutineName(emfProject);
        if (routines == null) {
            initializeRoutinesName(processItem);
            routines = getRoutineName(emfProject);
        }
        return routines;
    }

    private static List<String> getRoutineName(org.talend.core.model.properties.Project emfProject) {
        List<String> routines = routinesName.get(emfProject);

        EList refProjects = emfProject.getReferencedProjects();
        if (refProjects != null) {
            for (Object object : refProjects) {
                ProjectReferenceImpl projectRef = (ProjectReferenceImpl) object;
                List<String> refRoutines = routinesName.get(projectRef.getReferencedProject());
                for (String routineName : refRoutines) {
                    if (!routines.contains(routineName)) {
                        routines.add(routineName);
                    }
                }
            }
        }

        return routines;
    }
}
