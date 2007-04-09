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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.talend.core.model.process.IContext;
import org.talend.designer.core.ISyntaxCheckableEditor;

/**
 * DOC qian class global comment. Detailled comment <br/>
 * 
 * $Id: talend-code-templates.xml 1 2006-09-29 17:06:40 +0000 (星期五, 29 九月 2006) nrousseau $
 * 
 */
public interface IProcessor {

    public static final int NO_STATISTICS = -1;

    public static final int NO_TRACES = -1;

    public static final int WATCH_LIMITED = -1;

    public static final int WATCH_ALLOWED = 1;

    public static final int STATES_EDIT = 0;

    public static final int STATES_RUNTIME = 1;

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
     * yzhang Comment method "getInterpreter".
     * 
     * @return
     * @throws ProcessorException
     */
    public String getInterpreter() throws ProcessorException;

    /**
     * Used to set a specific interpreter.
     * 
     * @return
     */
    public void setInterpreter(String interpreter);

    /**
     * Used to get the routine path.
     * 
     * @return
     */
    public String getLibraryPath() throws ProcessorException;

    /**
     * Used to set a specific routine path.
     * 
     * @return
     */
    public void setLibraryPath(String libraryPath);

    /**
     * Used to get the routine path.
     * 
     * @return
     */
    public String getCodeLocation() throws ProcessorException;

    /**
     * Used to set a specific routine path.
     * 
     * @return
     */
    public void setCodeLocation(String codeLocation);

    /**
     * Get the processor type, e.g. java processor, perl processor.
     * 
     * yzhang Comment method "getProcessorType".
     * 
     * @return
     */
    public String getProcessorType();

    /**
     * Set the processor's current states.
     * 
     * yzhang Comment method "getProcessorStates".
     * 
     * @return
     */
    public void setProcessorStates(int states);

    /**
     * Add the Syntax Checkable Editor for refresh format of the code wihtin the editor, and also for error check.
     * 
     * yzhang Comment method "addSyntaxCheckableEditor".
     * 
     * @param editor
     */
    public void setSyntaxCheckableEditor(ISyntaxCheckableEditor editor);

    /**
     * Get Current type name for launching.
     * 
     * yzhang Comment method "getTypeName".
     */
    public String getTypeName();

    /**
     * Save lauch configuration.
     * 
     * @return
     * @throws CoreException
     */
    public Object saveLaunchConfiguration() throws CoreException;

    public String[] getCommandLine(boolean externalUse, int statOption, int traceOption, String... codeOptions);

    public void setContext(IContext context);

    public Process run(int statisticsPort, int tracePort, String watchParam) throws ProcessorException;

    public ILaunchConfiguration debug() throws ProcessorException;
}
