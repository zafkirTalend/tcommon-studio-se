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
package org.talend.migrationtool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.MessageBoxExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.model.general.Project;
import org.talend.core.model.migration.IMigrationToolService;
import org.talend.core.model.migration.IProjectMigrationTask;
import org.talend.core.model.migration.IWorkspaceMigrationTask;
import org.talend.core.prefs.PreferenceManipulator;
import org.talend.migrationtool.i18n.Messages;
import org.talend.migrationtool.model.GetTasksHelper;
import org.talend.migrationtool.model.summary.AlertUserOnLogin;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.IRepositoryService;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class MigrationToolService implements IMigrationToolService {

    private static Logger log = Logger.getLogger(MigrationToolService.class);

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
    public void executeProjectTasks(Project project, boolean beforeLogon, IProgressMonitor monitorWrap) {

        String taskDesc = "Migration tool: project [" + project.getLabel() + "] tasks"; //$NON-NLS-1$ //$NON-NLS-2$
        log.trace(taskDesc); //$NON-NLS-1$ //$NON-NLS-2$ 

        List<IProjectMigrationTask> toExecute = GetTasksHelper.getProjectTasks(beforeLogon);
        List<String> done = new ArrayList<String>(project.getEmfProject().getMigrationTasks());

        boolean needSave = false;

        Collections.sort(toExecute, new Comparator<IProjectMigrationTask>() {

            public int compare(IProjectMigrationTask o1, IProjectMigrationTask o2) {
                return o1.getOrder().compareTo(o2.getOrder());
            }
        });

        SubProgressMonitor subProgressMonitor = new SubProgressMonitor(monitorWrap, toExecute.size());

        for (IProjectMigrationTask task : toExecute) {
            if (monitorWrap.isCanceled()) {
                throw new OperationCanceledException(Messages.getString("MigrationToolService.migrationCancel", task.getName())); //$NON-NLS-1$
            }
            if (!done.contains(task.getId())) {
                monitorWrap.setTaskName(Messages.getString("MigrationToolService.taskInProgress", task.getName())); //$NON-NLS-1$
                subProgressMonitor.worked(1);
                try {
                    switch (task.execute(project)) {
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
            }
        }

        if (needSave) {
            saveProjectMigrationTasksDone(project, done);
        }

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

        PreferenceManipulator prefManipulator = new PreferenceManipulator(CorePlugin.getDefault().getPreferenceStore());
        List<IWorkspaceMigrationTask> toExecute = GetTasksHelper.getWorkspaceTasks();
        List<String> done = prefManipulator.readWorkspaceTasksDone();

        // --------------------------------------------------------------------------------------------------
        // This code part aim is to know if we have a new workspace or one from an old Talend version:
        // --------------------------------------------------------------------------------------------------
        String lastUser = prefManipulator.getLastUser();
        if (lastUser == null || lastUser.length() == 0) {
            if (done.isEmpty()) {
                // We are sure on a initialized or new workspace:
                initNewWorkspaceTasks();
                done = prefManipulator.readWorkspaceTasksDone();
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
                    prefManipulator.addWorkspaceTaskDone(task.getId());
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
        PreferenceManipulator prefManipulator = new PreferenceManipulator(CorePlugin.getDefault().getPreferenceStore());
        for (IWorkspaceMigrationTask task : toExecute) {
            prefManipulator.addWorkspaceTaskDone(task.getId());
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
