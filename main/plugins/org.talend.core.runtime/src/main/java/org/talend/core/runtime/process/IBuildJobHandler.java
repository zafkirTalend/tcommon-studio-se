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
package org.talend.core.runtime.process;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * created by ycbai on 2015年5月13日 Detailled comment
 *
 */
public interface IBuildJobHandler {

    public void generateJobFiles(IProgressMonitor monitor) throws Exception;

    /**
     * Some code generators use the BuildJobHandler to execute job in the studio. This method is used to correctly
     * generate the stats code if stats are enabled within the studio.
     * 
     * @param monitor
     * @param stats
     * @param trace
     * @throws Exception
     */
    public void generateJobFiles(IProgressMonitor monitor, boolean stats, boolean trace) throws Exception;

    public void generateTestReports(IProgressMonitor monitor) throws Exception;

    public void generateItemFiles(boolean withDependencies, IProgressMonitor monitor) throws Exception;

    public void build(IProgressMonitor monitor) throws Exception;

    public IFile getJobTargetFile();

}
