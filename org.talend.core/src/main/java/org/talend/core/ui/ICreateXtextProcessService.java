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

/**
 * DOC nma class global comment. Detailled comment
 */
public interface ICreateXtextProcessService extends IService {

    public void createProcessItem(String jobName, String path);

    // create job's property and give parameters default values except label.
    public void addProperty(String jobName);

    // create the processType with a .job file created by metalanguage.
    public void addProcess(String path);

}
