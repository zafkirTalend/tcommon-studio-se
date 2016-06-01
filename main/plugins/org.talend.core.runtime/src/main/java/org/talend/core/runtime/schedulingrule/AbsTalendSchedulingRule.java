// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.runtime.schedulingrule;

/**
 * created by cmeng on May 31, 2016
 * Detailled comment
 *
 */
public abstract class AbsTalendSchedulingRule implements ITalendSchedulingRule {

    private Thread roleThread;

    public AbsTalendSchedulingRule() {
        roleThread = Thread.currentThread();
    }

    @Override
    public Thread getRuleThread() {
        return roleThread;
    }
}
