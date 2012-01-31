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
package org.talend.migrationtool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.commons.ui.runtime.exception.MessageBoxExceptionHandler;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.ICoreService;
import org.talend.core.model.general.Project;
import org.talend.core.model.migration.IMigrationToolService;
import org.talend.core.model.properties.Item;
import org.talend.core.model.relationship.RelationshipItemBuilder;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.core.model.repository.RepositoryObject;
import org.talend.migration.IProjectMigrationTask;
import org.talend.migration.IProjectMigrationTask.ExecutionResult;
import org.talend.migration.IWorkspaceMigrationTask;
import org.talend.migrationtool.i18n.Messages;
import org.talend.migrationtool.model.GetTasksHelper;
import org.talend.migrationtool.model.summary.AlertUserOnLogin;
import org.talend.repository.RepositoryWorkUnit;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.IRepositoryService;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class MigrationToolService implements IMigrationToolService {

    private static ICoreService coreService = (ICoreService) GlobalServiceRegister.getDefault().getService(ICoreService.class);

    private static Logger log = Logger.getLogger(MigrationToolService.class);

    private static final String RELATION_TASK = "org.talend.repository.model.migration.AutoUpdateRelationsMigrationTask"; //$NON-NLS-1$ 

    // FIXME SML Change that
    public List<IProjectMigrationTask> doneThisSession;

    public MigrationToolService() {
        doneThisSession = new ArrayList<IProjectMigrationTask>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.core.model.migration.IMigrationToolService#executeProjectTasks(org.talend.core.model.general.Project,
     * boolean, org.eclipse.core.runtime.IProgressMonitor)
     */
    public void executeProjectTasks(final Project project, final boolean beforeLogon, final IProgressMonitor monitorWrap) {

        String taskDesc = "Migration tool: project [" + project.getLabel() + "] tasks"; //$NON-NLS-1$ //$NON-NLS-2$
        log.trace(taskDesc); //$NON-NLS-1$ //$NON-NLS-2$ 

        final List<IProjectMigrationTask> toExecute = GetTasksHelper.getProjectTasks(beforeLogon);
        final List<String> done = new ArrayList<String>(project.getEmfProject().getMigrationTasks());

        // force execute migration in case user copy-past items with diffrent path on the file system and refresh
        // the studio,it may cause bug TDI-19229
        done.remove("org.talend.repository.model.migration.FixProjectResourceLink");

        Collections.sort(toExecute, new Comparator<IProjectMigrationTask>() {

            public int compare(IProjectMigrationTask o1, IProjectMigrationTask o2) {
                return o1.getOrder().compareTo(o2.getOrder());
            }
        });

        int nbMigrationsToDo = 0;
        for (IProjectMigrationTask task : toExecute) {
            if (!done.contains(task.getId()) && !task.isDeprecated()) {
                nbMigrationsToDo++;
            }
        }

        // force to redo the migration task for the relations only if user ask for "clean" or if relations is empty
        // or if there is at least another migration to do.
        if (!beforeLogon
                && (!RelationshipItemBuilder.INDEX_VERSION.equals(project.getEmfProject().getItemsRelationVersion())
                        || project.getEmfProject().getItemsRelations() == null
                        || project.getEmfProject().getItemsRelations().isEmpty() || nbMigrationsToDo > 0)) {
            // force to redo this migration task, to make sure the relationship is done correctly
            done.remove(RELATION_TASK);
        }

        boolean haveAnyBinFolder = false; // to avoid some problems of migration, sometimes
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IProject fsProject = workspace.getRoot().getProject(project.getTechnicalLabel());
        for (ERepositoryObjectType type : (ERepositoryObjectType[]) ERepositoryObjectType.values()) {
            if (!type.hasFolder()) {
                continue;
            }
            String folderName = ERepositoryObjectType.getFolderName(type);
            if (folderName == null || "".equals(folderName)) {
                continue;
            }
            IFolder folder = fsProject.getFolder(folderName);
            if (folder.exists() && folder.getFolder("bin").exists()) { //$NON-NLS-1$
                haveAnyBinFolder = true;
                break;
            }
        }
        if (haveAnyBinFolder) {
            done.remove("org.talend.repository.model.migration.RemoveBinFolderMigrationTask"); //$NON-NLS-1$
        }

        final SubProgressMonitor subProgressMonitor = new SubProgressMonitor(monitorWrap, toExecute.size());
        IRepositoryService service = (IRepositoryService) GlobalServiceRegister.getDefault().getService(IRepositoryService.class);
        final IProxyRepositoryFactory repFactory = service.getProxyRepositoryFactory();

        RepositoryWorkUnit repositoryWorkUnit = new RepositoryWorkUnit(project, taskDesc) {

            public void run() throws PersistenceException {
                boolean needSave = false;
                if (!beforeLogon) {
                    ERepositoryObjectType[] types = (ERepositoryObjectType[]) ERepositoryObjectType.values();
                    Arrays.sort(types, new Comparator<ERepositoryObjectType>() {

                        public int compare(ERepositoryObjectType arg0, ERepositoryObjectType arg1) {
                            if (arg0 == ERepositoryObjectType.PROCESS) {
                                return 1;
                            }
                            if (arg0 == ERepositoryObjectType.JOBLET) {
                                return 1;
                            }
                            return 0;
                        }
                    });

                    for (ERepositoryObjectType type : types) {
                        if (!type.isResourceItem()) {
                            continue;
                        }
                        List<IRepositoryViewObject> objects = repFactory.getAll(project, type, true, true);

                        for (IRepositoryViewObject object : objects) {
                            Item item = object.getProperty().getItem();
                            monitorWrap.subTask("Migrate... " + item.getProperty().getLabel());

                            subProgressMonitor.worked(1);
                            for (IProjectMigrationTask task : toExecute) {
                                if (monitorWrap.isCanceled()) {
                                    throw new OperationCanceledException(Messages.getString(
                                            "MigrationToolService.migrationCancel", task.getName())); //$NON-NLS-1$
                                }
                                if (!done.contains(task.getId()) && !task.isDeprecated()) {
                                    ExecutionResult status = task.execute(project, item);
                                    switch (status) {
                                    case SUCCESS_WITH_ALERT:
                                        if (task.getStatus() != ExecutionResult.FAILURE) {
                                            task.setStatus(status);
                                        }
                                    case SUCCESS_NO_ALERT:
                                        if (task.getStatus() != ExecutionResult.FAILURE) {
                                            task.setStatus(status);
                                        }
                                    case NOTHING_TO_DO:
                                        if (task.getStatus() != ExecutionResult.SUCCESS_WITH_ALERT
                                                && task.getStatus() != ExecutionResult.SUCCESS_NO_ALERT
                                                && task.getStatus() != ExecutionResult.FAILURE) {
                                            task.setStatus(status);
                                        }
                                        break;
                                    case SKIPPED:
                                        if (task.getStatus() != ExecutionResult.SUCCESS_WITH_ALERT
                                                && task.getStatus() != ExecutionResult.SUCCESS_NO_ALERT
                                                && task.getStatus() != ExecutionResult.FAILURE) {
                                            task.setStatus(status);
                                        }
                                        break;
                                    case FAILURE:
                                        task.setStatus(status);
                                    default:
                                        task.setStatus(status);
                                        break;
                                    }
                                    // monitorWrap.setTaskName("");
                                }
                            }

                            if (object instanceof RepositoryObject) {
                                ((RepositoryObject) object).unload();
                            }
                        }
                        monitorWrap.subTask(""); //$NON-NLS-1$
                    }
                }
                for (IProjectMigrationTask task : toExecute) {
                    if (!done.contains(task.getId()) && !task.isDeprecated()) {
                        try {
                            ExecutionResult status;
                            if (beforeLogon) {
                                status = task.execute(project);
                            } else {
                                status = task.getStatus();
                            }
                            switch (status) {
                            case SUCCESS_WITH_ALERT:
                                doneThisSession.add(task);
                            case SUCCESS_NO_ALERT:
                                log.debug("Task \"" + task.getName() + "\" done"); //$NON-NLS-1$ //$NON-NLS-2$
                            case NOTHING_TO_DO:
                                done.add(task.getId());
                                needSave = true;
                                break;
                            case SKIPPED:
                                log.debug("Task \"" + task.getName() + "\" skipped"); //$NON-NLS-1$ //$NON-NLS-2$
                                break;
                            case FAILURE:
                            default:
                                log.debug("Task \"" + task.getName() + "\" failed"); //$NON-NLS-1$ //$NON-NLS-2$
                                break;
                            }
                        } catch (Exception e) {
                            ExceptionHandler.process(e);
                            log.debug("Task \"" + task.getName() + "\" failed"); //$NON-NLS-1$ //$NON-NLS-2$
                        }
                    } else if (task.isDeprecated() && !done.contains(task.getId())) {
                        done.add(task.getId());
                        needSave = true;
                    }
                }
                if (needSave) {
                    saveProjectMigrationTasksDone(project, done);
                }
                RelationshipItemBuilder.getInstance().saveRelations();
                if (!RelationshipItemBuilder.INDEX_VERSION.equals(project.getEmfProject().getItemsRelationVersion())) {
                    project.getEmfProject().setItemsRelationVersion(RelationshipItemBuilder.INDEX_VERSION);
                }
            }
        };
        repositoryWorkUnit.setAvoidUnloadResources(true);
        repFactory.executeRepositoryWorkUnit(repositoryWorkUnit);
        // repositoryWorkUnit.throwPersistenceExceptionIfAny();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.migration.IMigrationToolService#initNewProjectTasks()
     */
    public void initNewProjectTasks(Project project) {
        List<IProjectMigrationTask> toExecute = GetTasksHelper.getProjectTasks(true);
        toExecute.addAll(GetTasksHelper.getProjectTasks(false));
        List<String> done = new ArrayList<String>();

        for (IProjectMigrationTask task : toExecute) {
            done.add(task.getId());
        }

        saveProjectMigrationTasksDone(project, done);
    }

    /**
     * DOC smallet Comment method "saveProjectMigrationTasksDone".
     * 
     * @param project
     * @param done
     */
    private void saveProjectMigrationTasksDone(Project project, List<String> done) {
        IRepositoryService service = (IRepositoryService) GlobalServiceRegister.getDefault().getService(IRepositoryService.class);
        IProxyRepositoryFactory repFactory = service.getProxyRepositoryFactory();
        try {
            repFactory.setMigrationTasksDone(project, done);
        } catch (PersistenceException e) {
            MessageBoxExceptionHandler.process(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.migration.IMigrationToolService#executeWorspaceTasks()
     */
    public void executeWorspaceTasks() {
        log.trace("Migration tool: workspace tasks"); //$NON-NLS-1$

        // PreferenceManipulator prefManipulator = new
        // PreferenceManipulator(CorePlugin.getDefault().getPreferenceStore());
        List<IWorkspaceMigrationTask> toExecute = GetTasksHelper.getWorkspaceTasks();

        // List<String> done = prefManipulator.readWorkspaceTasksDone();
        List<String> done = coreService.readWorkspaceTasksDone();

        // --------------------------------------------------------------------------------------------------
        // This code part aim is to know if we have a new workspace or one from an old Talend version:
        // --------------------------------------------------------------------------------------------------
        // String lastUser = prefManipulator.getLastUser();
        String lastUser = coreService.getLastUser();
        if (lastUser == null || lastUser.length() == 0) {
            if (done.isEmpty()) {
                // We are sure on a initialized or new workspace:
                initNewWorkspaceTasks();
                // done = prefManipulator.readWorkspaceTasksDone();
                done = coreService.readWorkspaceTasksDone();
            }
        }
        // --------------------------------------------------------------------------------------------------

        Collections.sort(toExecute, new Comparator<IWorkspaceMigrationTask>() {

            public int compare(IWorkspaceMigrationTask o1, IWorkspaceMigrationTask o2) {
                return o1.getOrder().compareTo(o2.getOrder());
            }
        });

        for (IWorkspaceMigrationTask task : toExecute) {
            if (!done.contains(task.getId())) {
                if (task.execute()) {
                    // prefManipulator.addWorkspaceTaskDone(task.getId());
                    coreService.addWorkspaceTaskDone(task.getId());
                    log.debug("Task \"" + task.getName() + "\" done"); //$NON-NLS-1$ //$NON-NLS-2$
                } else {
                    log.debug("Task \"" + task.getName() + "\" failed"); //$NON-NLS-1$ //$NON-NLS-2$
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.migration.IMigrationToolService#initNewWorkspaceTasks()
     */
    public void initNewWorkspaceTasks() {
        List<IWorkspaceMigrationTask> toExecute = GetTasksHelper.getWorkspaceTasks();
        // PreferenceManipulator prefManipulator = new
        // PreferenceManipulator(CorePlugin.getDefault().getPreferenceStore());
        for (IWorkspaceMigrationTask task : toExecute) {
            // prefManipulator.addWorkspaceTaskDone(task.getId());
            coreService.addWorkspaceTaskDone(task.getId());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.migration.IMigrationToolService#executeMigration()
     */
    public void executeMigration(boolean underPluginModel) {
        new AlertUserOnLogin().startup(underPluginModel);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.migration.IMigrationToolService#needExecutemigration()
     */
    public boolean needExecutemigration() {
        return !AlertUserOnLogin.executed;
    }

}
