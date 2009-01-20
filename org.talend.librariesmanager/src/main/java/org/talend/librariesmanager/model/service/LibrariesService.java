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
package org.talend.librariesmanager.model.service;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.talend.commons.exception.BusinessException;
import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.model.general.ILibrariesService;
import org.talend.core.model.general.ModuleNeeded.ELibraryInstallStatus;
import org.talend.core.model.process.Element;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess2;
import org.talend.core.model.process.Problem;
import org.talend.librariesmanager.i18n.Messages;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class LibrariesService implements ILibrariesService {

    private static ILibrariesService javaService = new JavaLibrariesService();

    private static ILibrariesService perlService = new PerlLibrariesService();

    private ILibrariesService getLibrariesService() {
        switch (((RepositoryContext) CorePlugin.getContext().getProperty(Context.REPOSITORY_CONTEXT_KEY)).getProject()
                .getLanguage()) {
        case JAVA:
            return javaService;
        case PERL:
            return perlService;
        default:
            throw new UnsupportedOperationException(Messages.getString("LibrariesService.unknowLanguage")); //$NON-NLS-1$
        }
    }

    private ILibrariesService getJavaLibrariesService() {
        return javaService;
    }

    private ILibrariesService getPerlLibrariesService() {
        return perlService;
    }

    public void deployLibrary(URL source) throws IOException {
        this.getLibrariesService().deployLibrary(source);
    }

    public String getLibrariesPath() {
        return this.getLibrariesService().getLibrariesPath();
    }

    public String getJavaLibrariesPath() {
        return this.getJavaLibrariesService().getLibrariesPath();
    }

    public String getPerlLibrariesPath() {
        return this.getPerlLibrariesService().getLibrariesPath();
    }

    public ELibraryInstallStatus getLibraryStatus(String libName) throws BusinessException {
        return this.getLibrariesService().getLibraryStatus(libName);
    }

    public List<Problem> getProblems(INode node, Element element) {
        return this.getLibrariesService().getProblems(node, element);
    }

    public URL getRoutineTemplate() {
        return this.getLibrariesService().getRoutineTemplate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.general.ILibrariesService#getSqlPatternTemplate()
     */
    public URL getSqlPatternTemplate() {
        return this.getLibrariesService().getSqlPatternTemplate();
    }

    public List<URL> getSystemRoutines() {
        return this.getLibrariesService().getSystemRoutines();
    }

    public List<URL> getTalendRoutinesFolder() throws IOException {
        return this.getLibrariesService().getTalendRoutinesFolder();
    }

    public List<URL> getTalendRoutines() {
        return this.getLibrariesService().getTalendRoutines();
    }

    public void syncLibraries(IProgressMonitor... monitorWrap) {
        this.getLibrariesService().syncLibraries(monitorWrap);
    }

    public void checkLibraries() {
        this.getLibrariesService().checkLibraries();
    }

    public void addChangeLibrariesListener(IChangedLibrariesListener listener) {
        this.getLibrariesService().addChangeLibrariesListener(listener);
    }

    public void removeChangeLibrariesListener(IChangedLibrariesListener listener) {
        this.getLibrariesService().removeChangeLibrariesListener(listener);
    }

    public void resetModulesNeeded() {
        this.getLibrariesService().resetModulesNeeded();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.general.ILibrariesService#undeployLibrary(java.net.URL)
     */
    public void undeployLibrary(String path) throws IOException {
        this.getLibrariesService().undeployLibrary(path);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.general.ILibrariesService#isLibSynchronized()
     */
    public boolean isLibSynchronized() {
        return this.getLibrariesService().isLibSynchronized();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.general.ILibrariesService#getSystemSQLPatterns()
     */
    public List<URL> getSystemSQLPatterns() {
        return this.getLibrariesService().getSystemSQLPatterns();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.core.model.general.ILibrariesService#resetModulesNeededForCurrentJob(org.talend.core.model.properties
     * .Item)
     */
    public void updateModulesNeededForCurrentJob(IProcess2 process) {
        this.getLibrariesService().updateModulesNeededForCurrentJob(process);

    }
}
