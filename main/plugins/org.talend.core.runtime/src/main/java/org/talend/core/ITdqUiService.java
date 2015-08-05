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
package org.talend.core;

/**
 * created by xqliu on Sep 5, 2013 Detailled comment
 */
public interface ITdqUiService extends IService {

    public void updateContextView(boolean isBuildIn);

    public void updateContextView(boolean isBuildIn, boolean isDisposeAll);

    public void updateContextView(boolean isBuildIn, boolean isDisposeAll, boolean refreshView);
}
