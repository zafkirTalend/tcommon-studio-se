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
package org.talend.designer.codegen;

import org.eclipse.core.runtime.IProgressMonitor;
import org.talend.core.IService;
import org.talend.core.model.process.IProcess;

/**
 * DOC bqian class global comment. Interface for CodeGeneratorService. <br/>
 * 
 * $Id: talend-code-templates.xml 1 2006-09-29 17:06:40 +0000 (星期五, 29 九月 2006) nrousseau $
 * 
 */
public interface ICodeGeneratorService extends IService {
    
    /**
     * DOC mhirt Comment method "initializeCodeGenerator".
     * @param monitorWrap 
     * 
     */
    public void initializeTemplates();
    
    /**
     * DOC qian Comment method "createCodeGenerator".
     * 
     * @return ICodeGenerator
     */
    public ICodeGenerator createCodeGenerator();

    /**
     * DOC qian Comment method "createCodeGenerator".
     * 
     * @param process IProcess
     * @param statistics boolean
     * @param trace boolean
     * @param options String...
     * @return
     */
    public ICodeGenerator createCodeGenerator(IProcess process, boolean statistics, boolean trace, String... options);

    /**
     * Create the routine synchronizer for Perl project.
     * 
     *  yzhang Comment method "creatPerlRoutineSynchronizer".
     * @return
     */
    public IRoutineSynchronizer createPerlRoutineSynchronizer();

    /**
     * Create the routine synchronizer for Java project.
     * 
     *  yzhang Comment method "creatJavaRoutineSynchronizer".
     * @return
     */
    public IRoutineSynchronizer createJavaRoutineSynchronizer();
}
