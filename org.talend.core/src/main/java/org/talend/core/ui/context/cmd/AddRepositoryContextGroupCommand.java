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
package org.talend.core.ui.context.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gef.commands.Command;
import org.talend.core.model.context.JobContext;
import org.talend.core.model.context.JobContextParameter;
import org.talend.core.model.process.IContext;
import org.talend.core.model.process.IContextManager;
import org.talend.core.model.process.IContextParameter;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class AddRepositoryContextGroupCommand extends Command {

    private IProgressMonitor monitor;

    private IContextManager manager;

    private final Set<String> nameSet;

    public AddRepositoryContextGroupCommand(IProgressMonitor monitor, IContextManager manager, final Set<String> nameSet) {
        super();
        this.monitor = monitor;
        this.manager = manager;
        this.nameSet = nameSet;
    }

    @Override
    public void execute() {
        if (manager == null || nameSet == null || nameSet.isEmpty()) {
            return;
        }
        if (monitor == null) {
            monitor = new NullProgressMonitor();
        }
        IContext context = manager.getDefaultContext();
        for (String name : nameSet) {
            JobContext newContext = new JobContext(name);

            List<IContextParameter> newParamList = new ArrayList<IContextParameter>();
            newContext.setContextParameterList(newParamList);
            JobContextParameter param;
            for (IContextParameter parameter : context.getContextParameterList()) {
                param = (JobContextParameter) parameter.clone();
                param.setContext(newContext);
                newParamList.add(param);
            }
            manager.getListContext().add(newContext);
            monitor.worked(1);
        }
    }

}
