// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.general.ILibrariesService;
import org.talend.core.model.general.Project;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.process.IProcess2;
import org.talend.core.model.properties.InformationLevel;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.ProjectReference;
import org.talend.core.model.properties.RoutineItem;
import org.talend.core.model.properties.impl.ProjectReferenceImpl;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.designer.core.model.utils.emf.talendfile.ItemInforType;
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
        retrieveRoutineNamesFromProject(emfProject, true);

        Context ctx = CorePlugin.getContext();
        if (ctx != null) {
            RepositoryContext repositoryContext = (RepositoryContext) ctx.getProperty(Context.REPOSITORY_CONTEXT_KEY);
            String parentBranch = repositoryContext.getFields().get(
                    IProxyRepositoryFactory.BRANCH_SELECTION + "_" + emfProject.getTechnicalLabel());

            EList referencedProjects = emfProject.getReferencedProjects();
            if (referencedProjects != null) {
                for (Object object : referencedProjects) {
                    ProjectReferenceImpl projectRef = (ProjectReferenceImpl) object;
                    if (projectRef.getBranch() == null || parentBranch.equals(projectRef.getBranch())) {
                        org.talend.core.model.properties.Project refEmfProject = projectRef.getReferencedProject();
                        retrieveRoutineNamesFromProject(refEmfProject, true);
                    }
                }
            }
        }
    }

    private static void retrieveRoutineNamesFromProject(org.talend.core.model.properties.Project emfProject, boolean force) {
        List<String> routines = routinesName.get(emfProject);
        if (routines == null || force) {
            IProxyRepositoryFactory repositoryFactory = CorePlugin.getDefault().getRepositoryService()
                    .getProxyRepositoryFactory();
            List<String> toReturn = initRoutineNamesFromProject(emfProject, repositoryFactory);
            if (toReturn == null) {
                toReturn = Collections.emptyList();
            }
            routinesName.put(emfProject, toReturn);
        }
    }

    private static List<String> initRoutineNamesFromProject(org.talend.core.model.properties.Project emfProject,
            IProxyRepositoryFactory repositoryFactory) {
        List<String> toReturn = new ArrayList<String>();

        if (emfProject == null || repositoryFactory == null) {
            return toReturn;
        }
        Project currentProject = new Project(emfProject);

        try {
            List<IRepositoryViewObject> routines = repositoryFactory.getAll(currentProject, ERepositoryObjectType.ROUTINES);
            for (IRepositoryViewObject routine : routines) {
                RoutineItem item = (RoutineItem) routine.getProperty().getItem();
                String routineStr = getRoutineStr(currentProject, item);
                if (routineStr != null) {
                    toReturn.add(routineStr);
                }
            }
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }

        return toReturn;
    }

    private static String getRoutineStr(Project currentProject, RoutineItem item) {
        if (currentProject.getLanguage().equals(ECodeLanguage.JAVA)) {
            InformationLevel level = item.getProperty().getMaxInformationLevel();
            if (level.getValue() == InformationLevel.ERROR) {
                return null;
            }
        }
        return getRoutineStr(currentProject, item.getProperty().getLabel(), item.isBuiltIn());
    }

    private static String getRoutineStr(Project currentProject, String itemLabel, boolean system) {
        if (currentProject.getLanguage().equals(ECodeLanguage.JAVA)) {
            if (system) {
                return itemLabel;
            } else {
                return itemLabel;
                // toReturn.add(emfProject.getTechnicalLabel().toLowerCase() + "." + routine.getLabel());
            }
        } else {
            String perlConn = "::"; //$NON-NLS-1$
            if (system) {
                String builtInPath = ILibrariesService.SOURCE_PERL_ROUTINES_FOLDER + perlConn + "system" + perlConn; //$NON-NLS-1$ 
                return builtInPath + itemLabel;
            } else {
                String userPath = ILibrariesService.SOURCE_PERL_ROUTINES_FOLDER + perlConn + currentProject.getTechnicalLabel()
                        + perlConn;
                return userPath + itemLabel;
            }
        }
    }

    /**
     * initializeRoutinesName must be called first or it might return wrong list name or null.
     */
    public static List<String> getRoutineName() {
        // return routinesName;
        return new ArrayList<String>();
    }

    @SuppressWarnings("unchecked")
    public static List<String> getRequiredRoutineName(IProcess process) {
        List<String> routines = new ArrayList<String>();
        Project currentProject = ProjectManager.getInstance().getCurrentProject();
        IProxyRepositoryFactory proxyRepositoryFactory = CorePlugin.getDefault().getRepositoryService()
                .getProxyRepositoryFactory();
        try {
            if (process instanceof IProcess2) { // for process
                Item processItem = process.getProperty().getItem();
                if (processItem instanceof ProcessItem) {
                    EList routinesDependencies = ((ProcessItem) processItem).getProcess().getRoutinesDependencies();
                    for (ItemInforType infor : (List<ItemInforType>) routinesDependencies) {
                        if (infor.isSystem()) {
                            routines.add(getRoutineStr(currentProject, infor.getIdOrName(), true));
                        } else {
                            String id = infor.getIdOrName();
                            IRepositoryViewObject lastVersion = proxyRepositoryFactory.getLastVersion(id);
                            if (lastVersion != null) {
                                Item item = lastVersion.getProperty().getItem();
                                if (item instanceof RoutineItem) {
                                    String routineStr = getRoutineStr(currentProject, (RoutineItem) item);
                                    if (routineStr != null) {
                                        routines.add(routineStr);
                                    }
                                }
                            }
                        }
                    }
                }
            } else { // for virtual process
                return getRoutineName(process); // same as before, add all
            }
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }
        return routines;
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
        if (routines == null) {
            retrieveRoutineNamesFromProject(emfProject, false);
            return getRoutineName(emfProject);
        }
        Context ctx = CorePlugin.getContext();
        if (ctx != null) {
            RepositoryContext repositoryContext = (RepositoryContext) ctx.getProperty(Context.REPOSITORY_CONTEXT_KEY);
            String parentBranch = repositoryContext.getFields().get(
                    IProxyRepositoryFactory.BRANCH_SELECTION + "_" + emfProject.getTechnicalLabel());
            List refProjects = emfProject.getReferencedProjects();
            getRoutineNameFromRef(routines, refProjects);
        }
        return routines;
    }

    private static void getRoutineNameFromRef(List<String> routines, List projects) {
        if (projects != null) {
            for (Object obj : projects) {
                if (obj instanceof ProjectReference) {
                    ProjectReference projectRef = (ProjectReference) obj;
                    org.talend.core.model.properties.Project referencedProject = projectRef.getReferencedProject();
                    List<String> refRoutines = routinesName.get(referencedProject);
                    if (refRoutines == null) {
                        retrieveRoutineNamesFromProject(referencedProject, false);
                        // retrieve again
                        refRoutines = routinesName.get(referencedProject);
                    }
                    if (refRoutines != null && routines != null) {
                        for (String routineName : refRoutines) {
                            if (!routines.contains(routineName)) {
                                routines.add(routineName);
                            }
                        }
                    }

                    getRoutineNameFromRef(routines, referencedProject.getReferencedProjects());
                }
            }
        }
    }
}
