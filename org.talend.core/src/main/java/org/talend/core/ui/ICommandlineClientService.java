// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.ui;

import org.talend.core.IService;
import org.talend.core.model.process.ITargetExecutionConfig;
import org.talend.designer.runprocess.IProcessor;
import org.talend.designer.runprocess.ProcessorException;

/**
 * ggu class global comment. Detailled comment
 */
public interface ICommandlineClientService extends IService {

    public Process deployAndRunByCommandline(ITargetExecutionConfig config, IProcessor processor, int statisticsPort,
            int tracePort, String watchParam) throws ProcessorException;
}
