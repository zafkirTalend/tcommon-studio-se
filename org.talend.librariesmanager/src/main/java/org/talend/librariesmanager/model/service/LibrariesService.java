// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the  agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//   
// ============================================================================
package org.talend.librariesmanager.model.service;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.talend.commons.exception.BusinessException;
import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.model.general.ILibrariesService;
import org.talend.core.model.general.ModuleNeeded.ELibraryInstallStatus;
import org.talend.core.model.process.Element;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.Problem;

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
            throw new UnsupportedOperationException("Unknow language");
        }
    }

    public void deployLibrary(URL source) throws IOException {
        this.getLibrariesService().deployLibrary(source);
    }

    public String getLibrariesPath() {
        return this.getLibrariesService().getLibrariesPath();
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

    public List<URL> getSystemRoutines() {
        return this.getLibrariesService().getSystemRoutines();
    }

    public URL getTalendRoutinesFolder() throws IOException {
        return this.getLibrariesService().getTalendRoutinesFolder();
    }

    public List<URL> getTalendRoutines() {
        return this.getLibrariesService().getTalendRoutines();
    }

    public void syncLibraries() {
        this.getLibrariesService().syncLibraries();
    }

    public void checkLibraries() {
        this.getLibrariesService().checkLibraries();
    }

    public void addChangeLibrariesListener(IChangedLibrariesListener listener) {
        this.getLibrariesService().addChangeLibrariesListener(listener);
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

}
