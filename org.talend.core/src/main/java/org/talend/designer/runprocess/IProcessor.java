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
package org.talend.designer.runprocess;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.talend.core.model.process.IContext;

/**
 * DOC qian class global comment. Detailled comment <br/>
 * 
 * $Id: talend-code-templates.xml 1 2006-09-29 17:06:40 +0000 (星期五, 29 九月 2006) nrousseau $
 * 
 */
public interface IProcessor {

    public void initPaths(IContext context) throws ProcessorException;

    public void generateCode(IContext context, boolean statistics, boolean trace, boolean perlProperties)
            throws ProcessorException;

    /**
     * getter the code context.
     * 
     * @return
     */
    public String getCodeContext();

    /**
     * Getter for codePath.
     * 
     * @return the codePath
     */
    public IPath getCodePath();

    /**
     * Getter for contextPath.
     * 
     * @return the contextPath
     */
    public IPath getContextPath();

    /**
     * getter the code project.
     * 
     * @return
     */
    public IProject getCodeProject();

    /**
     * Return line number where stands specific node in code generated.
     * 
     * @param nodeName
     */
    public int getLineNumber(String nodeName);

    /**
     * Get the interpreter for each kinds of language.
     * 
     * DOC yzhang Comment method "getInterpreter".
     * 
     * @return
     * @throws ProcessorException
     */
    public String getInterpreter() throws ProcessorException;

}
