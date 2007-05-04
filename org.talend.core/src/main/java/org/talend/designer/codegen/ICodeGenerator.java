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

import org.talend.commons.exception.SystemException;
import org.talend.core.i18n.Messages;
import org.talend.core.model.process.IContext;
import org.talend.core.model.process.INode;
import org.talend.core.model.temp.ECodePart;

/**
 * CodeGenerator Interface
 * 
 * $Id: CodeGenerator.java 636 2006-11-21 03:34:52 +0000 (星期二, 21 十一月 2006) ftang $
 * 
 */
public interface ICodeGenerator {

    /**
     * Generate the code for the process given to the constructor.
     * 
     * @return
     * @throws CodeGeneratorException
     */
    @SuppressWarnings("unchecked") //$NON-NLS-1$
    public String generateProcessCode() throws SystemException;

    /**
     * Parse Process, and generate Code for Context Variables.
     */
    public String generateContextCode(IContext designerContext) throws SystemException;

    /**
     * Generate Part Code for a given Component.
     * 
     * @param config
     * @param node
     * @return
     * @throws JETException
     * @throws CoreException
     */
    public String generateComponentCode(INode node, ECodePart part) throws SystemException;
    
    /**
     * Generate Part Code for a given node, with optional nodeConfigurer.
     * 
     * @param node
     * @param nodeConfigurer allows to configure thr process node, can be null
     * @return generated code
     */
    public String generateComponentCodeWithRows(String nodeName, IAloneProcessNodeConfigurer nodeConfigurer);

}
