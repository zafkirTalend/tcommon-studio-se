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
package org.talend.core;

import org.talend.designer.codegen.ICodeGeneratorService;
import org.talend.designer.runprocess.IRunProcessService;
import org.talend.repository.model.IRepositoryService;
/**
 * DOC qian class global comment. Contains vary factories. <br/>
 * 
 * $Id: talend-code-templates.xml 1 2006-09-29 17:06:40 +0000 (星期五, 29 九月 2006) nrousseau $
 * 
 */
public class GlobalServiceRegister {

    private static ICodeGeneratorService codeGeneratorService;

    private static IRunProcessService runProcessService;
    
    private static IRepositoryService repositoryService;

    /**
     * Getter for codeGeneratorFactory.
     * 
     * @return the codeGeneratorFactory
     */
    public static ICodeGeneratorService getCodeGeneratorService() {
        return codeGeneratorService;
    }

    /**
     * Sets the codeGeneratorFactory.
     * 
     * @param codeGeneratorFactory the codeGeneratorFactory to set
     */
    public static void registerCodeGeneratorService(ICodeGeneratorService service) {
        GlobalServiceRegister.codeGeneratorService = service;
    }

    /**
     * Getter for runProcessFactory.
     * 
     * @return the runProcessFactory
     */
    public static IRunProcessService getRunProcessService() {
        return runProcessService;
    }

    /**
     * Sets the runProcessFactory.
     * 
     * @param runProcessFactory the runProcessFactory to set
     */
    public static void registerRunProcessService(IRunProcessService service) {
        GlobalServiceRegister.runProcessService = service;
    }

    
    /**
     * Getter for repositoryService.
     * @return the repositoryService
     */
    public static IRepositoryService getRepositoryService() {
        return repositoryService;
    }

    
    /**
     * Sets the repositoryService.
     * @param repositoryService the repositoryService to set
     */
    public static void registerRepositoryService(IRepositoryService repositoryService) {
        GlobalServiceRegister.repositoryService = repositoryService;
    }

    
    
}
