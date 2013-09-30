// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.librariesmanager.ui;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.talend.core.ILibraryManagerUIService;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.general.LibraryInfo;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.core.model.routines.IRoutinesProvider;
import org.talend.core.model.routines.RoutineLibraryMananger;
import org.talend.core.model.routines.RoutinesUtil;
import org.talend.librariesmanager.prefs.LibrariesManagerUtils;
import org.talend.librariesmanager.ui.service.RoutineProviderManager;
import org.talend.librariesmanager.utils.ModulesInstaller;

/**
 * created by wchen on 2013-1-24 Detailled comment
 * 
 */
public class LibraryManagerUIService implements ILibraryManagerUIService {

    @Override
    public void installModules(final String[] jarNames) {
        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {

            @Override
            public void run() {
                Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
                ModulesInstaller.installModules(new Shell(shell), jarNames);
            }
        });

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ILibraryManagerUIService#getRoutinesProviders()
     */
    @Override
    public Collection<IRoutinesProvider> getRoutinesProviders(ECodeLanguage language) {
        return RoutineProviderManager.getInstance().getProviders(language);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ILibraryManagerUIService#initializeSystemLibs()
     */
    @Override
    public void initializeSystemLibs() {
        RoutineLibraryMananger.getInstance().initializeSystemLibs();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ILibraryManagerUIService#getRoutineAndJars()
     */
    @Override
    public Map<String, List<LibraryInfo>> getRoutineAndJars() {
        return RoutineLibraryMananger.getInstance().getRoutineAndJars();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ILibraryManagerUIService#collectRelatedRoutines()
     */
    @Override
    public List<IRepositoryViewObject> collectRelatedRoutines(Set<String> includeRoutineIdOrNames, boolean system,
            ERepositoryObjectType type) {
        return RoutinesUtil.collectRelatedRoutines(includeRoutineIdOrNames, system, type);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ILibraryManagerUIService#getLibrariesPath(org.talend.core.language.ECodeLanguage)
     */
    @Override
    public String getLibrariesPath(ECodeLanguage language) {
        return LibrariesManagerUtils.getLibrariesPath(language);
    }

}
