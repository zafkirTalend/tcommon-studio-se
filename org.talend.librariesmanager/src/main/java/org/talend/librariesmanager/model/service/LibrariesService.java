// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
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
import org.talend.core.model.process.Element;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.Problem;
import org.talend.designer.codegen.perlmodule.ModuleNeeded.ELibraryInstallStatus;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class LibrariesService implements ILibrariesService {

    private static ILibrariesService singleton = getLibrariesService();

    public static ILibrariesService getLibrariesService() {
        switch (((RepositoryContext) CorePlugin.getContext().getProperty(Context.REPOSITORY_CONTEXT_KEY)).getProject()
                .getLanguage()) {
        case JAVA:
            return new JavaLibrariesService();
        case PERL:
            return new PerlLibrariesService();
        default:
            throw new UnsupportedOperationException("Unknow language");
        }
    }

    public void deployLibrary(URL source) throws IOException {
        this.singleton.deployLibrary(source);
    }

    public String getLibrariesPath() {
        return this.singleton.getLibrariesPath();
    }

    public ELibraryInstallStatus getLibraryStatus(String libName) throws BusinessException {
        return this.singleton.getLibraryStatus(libName);
    }

    public List<Problem> getProblems(INode node, Element element) {
        return this.singleton.getProblems(node, element);
    }

    public URL getRoutineTemplate() {
        return this.singleton.getRoutineTemplate();
    }

    public List<URL> getSystemRoutines() {
        return this.singleton.getSystemRoutines();
    }

    public URL getTalendRoutinesFolder() throws IOException {
        return this.singleton.getTalendRoutinesFolder();
    }

    public void syncLibraries() {
        this.singleton.syncLibraries();
    }

    public void checkLibraries() {
        this.singleton.checkLibraries();
    }

}
