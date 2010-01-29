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
package org.talend.core.model.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.model.general.Project;
import org.talend.core.model.properties.ProjectReference;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.repository.ProjectManager;
import org.talend.repository.model.ERepositoryStatus;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * cli class global comment. Detailled comment
 */
public final class RetrieveItemsUtil {

    private RetrieveItemsUtil() {
    }

    /**
     * 
     * cli Comment method "retrieveItems".
     * 
     */
    public static List<IRepositoryObject> retrieveItems(final ERepositoryObjectType[] types, final boolean withLastVersion,
            final boolean withDeleted, final boolean withLocked, final boolean withRefProject) throws PersistenceException {
        final Project curProject = ProjectManager.getInstance().getCurrentProject();
        final IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();

        List<IRepositoryObject> results = new ArrayList<IRepositoryObject>();
        if (types != null) {
            for (ERepositoryObjectType type : types) {
                retrieveItems(factory, curProject, type, withLastVersion, withDeleted, withLocked, withRefProject, results);
            }

        }
        return results;
    }

    /**
     * 
     * cli Comment method "retrieveItems".
     * 
     * arguments set by preference
     */
    public static List<IRepositoryObject> retrieveItems(ERepositoryObjectType[] types) throws PersistenceException {
        final boolean withLastVersion = checkLastVersion();
        final boolean withDeleted = checkDeleted();
        final boolean withLocked = checkLocked();
        final boolean withRefProject = checkRefProject();

        return retrieveItems(types, withLastVersion, withDeleted, withLocked, withRefProject);

    }

    private static boolean checkLastVersion() {
        return Boolean.parseBoolean(CorePlugin.getDefault().getDesignerCoreService().getPreferenceStore("checkOnlyLastVersion")); //$NON-NLS-1$
    }

    private static boolean checkDeleted() {
        return true; // maybe, need set in preference.
    }

    private static boolean checkLocked() {
        return true; // maybe, need set in preference.
    }

    private static boolean checkRefProject() {
        return true; // maybe, need set in preference.
    }

    @SuppressWarnings("unchecked")
    private static void retrieveItems(final IProxyRepositoryFactory factory, final Project project,
            final ERepositoryObjectType type, final boolean withLastVersion, final boolean withDeleted, final boolean withLocked,
            final boolean withRefProject, final List<IRepositoryObject> results) throws PersistenceException {
        // List<IRepositoryObject> tmpList = new ArrayList<IRepositoryObject>();

        List<IRepositoryObject> list = factory.getAll(project, type, withDeleted, !withLastVersion);
        if (list == null) {
            list = new ArrayList<IRepositoryObject>();
        }
        // Don't need this codes, because the argument "allVersions" in "factory.getAll".

        // if (onlyLastVersion) {
        // Set<String> processedIds = new HashSet<String>();
        // for (IRepositoryObject object : list) {
        // Property property = object.getProperty();
        // Item item = property.getItem();
        //
        // if (!processedIds.contains(property.getId())) { // avoid the duplicate last version
        // IRepositoryObject lastVersion = factory.getLastVersion(project, property.getId(), item.getState().getPath(),
        // type);
        // if (lastVersion != null) {
        // tmpList.add(lastVersion);
        // }
        // processedIds.add(property.getId());
        // }
        // }
        // } else {
        // tmpList.addAll(list);
        // }

        // locked
        if (withLocked) {
            results.addAll(list);
        } else {
            for (IRepositoryObject object : list) {
                if (factory.getStatus(object) != ERepositoryStatus.LOCK_BY_OTHER
                        && factory.getStatus(object) != ERepositoryStatus.LOCK_BY_USER) {
                    results.add(object);
                }
            }
        }
        if (withRefProject) {
            EList refProjects = project.getEmfProject().getReferencedProjects();
            for (ProjectReference pRef : (List<ProjectReference>) refProjects) {
                Project refP = new Project(pRef.getProject());
                retrieveItems(factory, refP, type, withLastVersion, withDeleted, withLocked, withRefProject, results);
            }
        }

    }

}
