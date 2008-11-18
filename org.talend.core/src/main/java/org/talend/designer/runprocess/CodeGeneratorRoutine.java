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
package org.talend.designer.runprocess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * Actually used in ProcessorUtilities.generateCode.
     * 
     * @param processItem
     */
    public static void initializeRoutinesName(Item processItem) {
        ProjectManager pManager = ProjectManager.getInstance();

        org.talend.core.model.properties.Project emfProject = pManager.getProject(processItem);

        Project currentProject = new Project(emfProject);

        ECodeLanguage currentLanguage = currentProject.getLanguage();
        List<String> toReturn = new ArrayList<String>();

        IProxyRepositoryFactory repositoryFactory = CorePlugin.getDefault().getRepositoryService().getProxyRepositoryFactory();

        String builtInPath = ILibrariesService.SOURCE_PERL_ROUTINES_FOLDER + "::" + "system" + "::";
        String userPath = ILibrariesService.SOURCE_PERL_ROUTINES_FOLDER + "::" + currentProject.getTechnicalLabel() + "::";
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
        routinesName.put(emfProject, toReturn);
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
        List<String> routines = routinesName.get(emfProject);

        if (routines == null) {
            initializeRoutinesName(processItem);
            routines = routinesName.get(emfProject);
        }
        return routines;
    }

}
