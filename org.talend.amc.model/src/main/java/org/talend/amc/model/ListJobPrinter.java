// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.amc.model;

import java.util.Collection;

/**
 * DOC stephane class global comment. Detailled comment
 */
public class ListJobPrinter {

    public static void print(Collection<JobExecution> list) {
        for (JobExecution job : list)
            System.out.println(job);
    }
}
