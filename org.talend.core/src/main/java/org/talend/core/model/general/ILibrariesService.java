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

    public static final String SOURCE_SQLPATTERN_FOLDER = "sqlpatterns";

    public static final String SQLPATTERN_FILE_SUFFIX = ".sqlpattern";

    public static final String TEMPLATE_SUFFIX = ".template";

    public List<URL> getSystemRoutines();

    public List<URL> getSystemSQLPatterns();

    public URL getTalendRoutinesFolder() throws IOException;

    public List<URL> getTalendRoutines();

    public String getLibrariesPath();

    public String getJavaLibrariesPath();

    public String getPerlLibrariesPath();

    public void deployLibrary(URL source) throws IOException;

    public void undeployLibrary(String path) throws IOException;

    public URL getRoutineTemplate();

    public URL getSqlPatternTemplate();

    public ELibraryInstallStatus getLibraryStatus(String libName) throws BusinessException;

    public List<Problem> getProblems(INode node, Element element);

    public void syncLibraries();

    public void checkLibraries();

    public void addChangeLibrariesListener(IChangedLibrariesListener listener);

    public void resetModulesNeeded();

    public boolean isLibSynchronized();

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
