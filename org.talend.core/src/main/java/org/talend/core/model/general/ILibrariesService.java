// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
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
package org.talend.core.model.general;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.talend.commons.exception.BusinessException;
import org.talend.core.IService;
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
public interface ILibrariesService extends IService {

    public static final String SOURCE_PERL_ROUTINES_FOLDER = "routines";

    public List<URL> getSystemRoutines();

    public URL getTalendRoutinesFolder() throws IOException;
    
    public List<URL> getTalendRoutines();

    public String getLibrariesPath();

    public void deployLibrary(URL source) throws IOException;

    public URL getRoutineTemplate();

    public ELibraryInstallStatus getLibraryStatus(String libName) throws BusinessException;

    public List<Problem> getProblems(INode node, Element element);

    public void syncLibraries();

    public void checkLibraries();

    public void addChangeLibrariesListener(IChangedLibrariesListener listener);

    public void resetModulesNeeded();

    /**
     * Listener used to fire that libraries status has been changed (new lib or new check install).
     * 
     * $Id$
     * 
     */
    public interface IChangedLibrariesListener {

        public void afterChangingLibraries();
    }
}
