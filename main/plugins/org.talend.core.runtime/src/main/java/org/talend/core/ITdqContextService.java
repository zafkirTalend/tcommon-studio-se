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
package org.talend.core;

import org.eclipse.ui.IWorkbenchPart;

/**
 * created by xqliu on Jul 29, 2013 Detailled comment
 * 
 */
public interface ITdqContextService extends IService {

    public void updateContextView(IWorkbenchPart part);

    public void hideContextView(IWorkbenchPart part);

    public void resetContextView();

    /**
     * set the report editor dirty according to the TdqContextViewComposite.
     * 
     * @param manager the TdqContextViewComposite
     */
    public void setReportEditorDirty(Object manager);
}
