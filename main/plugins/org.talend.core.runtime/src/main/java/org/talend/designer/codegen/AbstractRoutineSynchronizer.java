// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.codegen;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.talend.commons.exception.SystemException;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.model.general.Project;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.PigudfItem;
import org.talend.core.model.properties.ProjectReference;
import org.talend.core.model.properties.RoutineItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.designer.core.ICamelDesignerCoreService;
import org.talend.repository.ProjectManager;
import org.talend.repository.model.IRepositoryService;

/***/
public abstract class AbstractRoutineSynchronizer implements ITalendSynchronizer {

    private static Map<String, Date> id2date = new HashMap<String, Date>();

    protected Collection<RoutineItem> getRoutines() throws SystemException {
        return getAll(ERepositoryObjectType.ROUTINES);
    }

    protected Collection<RoutineItem> getAllPigudf() throws SystemException {
        return getAll(ERepositoryObjectType.PIG_UDF);
    }

    protected Collection<RoutineItem> getBeans() throws SystemException {
        if (GlobalServiceRegister.getDefault().isServiceRegistered(ICamelDesignerCoreService.class)) {
            ICamelDesignerCoreService service = (ICamelDesignerCoreService) GlobalServiceRegister.getDefault().getService(
                    ICamelDesignerCoreService.class);
                return getAll(service.getBeansType());
        }
        return Collections.emptyList();
    }

    private static Collection<RoutineItem> getAll(ERepositoryObjectType type) throws SystemException {
        // remove routine with same name in reference project
        final Map<String, RoutineItem> beansList = new HashMap<String, RoutineItem>();
        for (IRepositoryViewObject obj : getRepositoryService().getProxyRepositoryFactory().getAll(type)) {
            beansList.put(obj.getProperty().getLabel(), (RoutineItem) obj.getProperty().getItem());
        }

        for (Project project : ProjectManager.getInstance().getReferencedProjects()) {
            getReferencedProjectRoutine(beansList, project, type);
        }
        return beansList.values();
    }

    private static IRepositoryService getRepositoryService() {
        return (IRepositoryService) GlobalServiceRegister.getDefault().getService(IRepositoryService.class);
    }

    private static void getReferencedProjectRoutine(final Map<String, RoutineItem> beansList, final Project project,
            ERepositoryObjectType routineType) throws SystemException {
        for (IRepositoryViewObject obj : getRepositoryService().getProxyRepositoryFactory().getAll(project, routineType)) {
            final String key = obj.getProperty().getLabel();
            // it does not have a routine with same name
            if (!beansList.containsKey(key)) {
                beansList.put(key, (RoutineItem) obj.getProperty().getItem());
            }
        }
        for (ProjectReference projectReference : (Collection<ProjectReference>) project.getEmfProject().getReferencedProjects()) {
            getReferencedProjectRoutine(beansList, new Project(projectReference.getReferencedProject()), routineType);
        }
    }

    @Override
    public void syncRoutine(RoutineItem routineItem, boolean copyToTemp) throws SystemException {
        syncRoutine(routineItem, copyToTemp, false);
    }

    public void syncRoutine(RoutineItem routineItem, boolean copyToTemp, boolean forceUpdate) throws SystemException {
        boolean needSync = false;
        if (routineItem != null) {
            if (forceUpdate || !isRoutineUptodate(routineItem)) {
                needSync = true;
            } else {
                IFile file = getFile(routineItem);
                if (file != null && !file.exists()) {
                    needSync = true;
                }
            }
        }
        if (needSync) {
            doSyncRoutine(routineItem, copyToTemp);
            setRoutineAsUptodate(routineItem);
        }
    }

    public void syncRoutine(RoutineItem routineItem) throws SystemException {
        if (routineItem != null) {
            doSyncRoutine(routineItem, true);
            setRoutineAsUptodate(routineItem);
        }
    }

    protected abstract void doSyncRoutine(RoutineItem routineItem, boolean copyToTemp) throws SystemException;

    @Override
    public abstract void deleteRoutinefile(IRepositoryViewObject objToDelete);

    protected boolean isRoutineUptodate(RoutineItem routineItem) {
        Date refDate = getRefDate(routineItem);
        if (refDate == null) {
            return false;
        }
        Date date = id2date.get(routineItem.getProperty().getId());
        return refDate.equals(date);
    }

    protected void setRoutineAsUptodate(RoutineItem routineItem) {
        Date refDate = getRefDate(routineItem);
        if (refDate == null) {
            return;
        }
        id2date.put(routineItem.getProperty().getId(), refDate);
    }

    private Date getRefDate(RoutineItem routineItem) {
        if (routineItem.isBuiltIn()) {
            // FIXME mhelleboid for now, routines are deleted and recreated on
            // project logon
            // change this code, if one day routines are updated
            return routineItem.getProperty().getCreationDate();
        } else {
            return routineItem.getProperty().getModificationDate();
        }

    }

    @Override
    public void forceSyncRoutine(RoutineItem routineItem) {
        id2date.remove(routineItem.getProperty().getId());
        try {
            IFile file = getFile(routineItem);
            if (file == null) {
                return;
            }
            file.delete(true, new NullProgressMonitor());
        } catch (Exception e) {
            // ignore me
        }
    }

    // qli modified to fix the bug 5400 and 6185.
    @Override
    public abstract void renameRoutineClass(RoutineItem routineItem);

    @Override
    public void renamePigudfClass(PigudfItem routineItem, String oldLabel) {

    }

    /**
     * DOC Administrator Comment method "renameBeanClass".
     * 
     * @param beanItem
     */
    @Override
    public abstract void renameBeanClass(Item beanItem);

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.codegen.ITalendSynchronizer#syncAllPigudf()
     */
    @Override
    public void syncAllPigudf() throws SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    public void syncAllRoutinesForLogOn() throws SystemException {
    }

    @Override
    public void syncAllBeansForLogOn() throws SystemException {
        for (RoutineItem beanItem : getBeans()) {
            syncRoutine(beanItem, true, true);
        }
    }

    @Override
    public void syncAllPigudfForLogOn() throws SystemException {
    }

    @Override
    public void syncAllBeans() throws SystemException {
        for (RoutineItem beanItem : getBeans()) {
            syncRoutine(beanItem, true);
        }
    }


}
