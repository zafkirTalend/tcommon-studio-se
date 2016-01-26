// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.codegen;

import org.eclipse.core.runtime.jobs.Job;
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
     * 
     * @param monitorWrap
     * 
     */
    public Job initializeTemplates();

    /**
     * DOC gke Comment method "refreshTemplates".
     * 
     * @return
     */
    public Job refreshTemplates();

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

    public boolean isInitializingJet();
}
