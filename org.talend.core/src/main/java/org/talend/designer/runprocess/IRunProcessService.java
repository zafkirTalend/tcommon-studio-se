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
package org.talend.designer.runprocess;

import org.apache.log4j.Level;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.talend.core.IService;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.ICodeProblemsChecker;
import org.talend.core.model.process.IProcess;

/**
 * DOC qian class global comment. Detailled comment <br/>
 * 
 * $Id: talend-code-templates.xml 1 2006-09-29 17:06:40 +0000 (星期五, 29 九月 2006) nrousseau $
 * 
 */
public interface IRunProcessService extends IService {

    public ICodeProblemsChecker getSyntaxChecker(ECodeLanguage codeLanguage);

    /**
     * Sets the activeProcess.
     * 
     * @param activeContext the activeContext to set
     */
    public void setActiveProcess(IProcess activeProcess);

    public void removeProcess(IProcess activeProcess);

    /**
     * Code Execution, used, when you know where the code stands.
     * 
     * @param Perl Absolute Code Path
     * @param Context Name
     * @param Port Statistics
     * @param Port Trace
     * @return Command Process Launched
     * @throws ProcessorException
     */
    public int perlExec(StringBuffer out, StringBuffer err, IPath absCodePath, String contextName, Level level,
            String perlInterpreterLibOption, String perlModuleDirectoryOption,
            int statOption, int traceOption, String... codeOptions) throws ProcessorException;

    /**
     * DOC xue Comment method "createCodeProcessor".
     * 
     * @param process
     * @param language
     * @param filenameFromLabel
     * @return
     */
    public IProcessor createCodeProcessor(IProcess process, ECodeLanguage language, boolean filenameFromLabel);

    /**
     * DOC qian Comment method "createPerformanceData".
     * 
     * @param data IPerformanceData
     * @return
     */
    public IPerformanceData createPerformanceData(String data);

    /**
     * DOC qian Gets routine filename extension.
     * 
     * @return
     */
    public String getRoutineFilenameExt();

    public IProject getProject(ECodeLanguage language) throws CoreException;
    
    /**
     * Setter for a custom delegate service.
     * This method should'nt be called from a delegate class, only in the RunProcessService class.
     * @param delegateService
     */
    public void setDelegateService(IRunProcessService delegateService);
    
}
