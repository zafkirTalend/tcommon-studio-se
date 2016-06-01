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

import org.eclipse.core.runtime.jobs.ISchedulingRule;

/**
 * created by cmeng on May 31, 2016
 * Detailled comment
 *
 */
public class DefaultTalendSchedulingRule extends AbsTalendSchedulingRule {

    @Override
    public boolean contains(ISchedulingRule rule) {
        if (rule == null) {
            return false;
        }
        if (this == rule) {
            return true;
        }
        if (rule instanceof ITalendSchedulingRule) {
            return true;
        }
        return rule.contains(this);
    }

    @Override
    public boolean isConflicting(ISchedulingRule rule) {
        if (rule == null) {
            return false;
        }
        if (this == rule) {
            return true;
        }
        if (rule instanceof ITalendSchedulingRule) {
            if (((ITalendSchedulingRule) rule).getRuleThread() == getRuleThread()) {
                return true;
            } else {
                return false;
            }
        }
        return rule.isConflicting(this);
    }

}
