// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.viewer.action;

import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.internal.navigator.TextActionHandler;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.talend.repository.ui.actions.CopyAction;
import org.talend.repository.ui.actions.DeleteAction;
import org.talend.repository.ui.actions.PasteAction;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class RepoGlobalActionProvider extends CommonActionProvider {

    public RepoGlobalActionProvider() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.actions.ActionGroup#fillActionBars(org.eclipse.ui.IActionBars)
     */
    @Override
    public void fillActionBars(IActionBars actionBars) {
        super.fillActionBars(actionBars);
        // actionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, doubleClickAction);
        actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(), CopyAction.getInstance());
        actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(), PasteAction.getInstance());
        actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), DeleteAction.getInstance());

        // TODO TextActionHandler is an internal class and should not be used.
        TextActionHandler textActionHandler = new TextActionHandler(actionBars);
        textActionHandler.setCopyAction(CopyAction.getInstance());
        textActionHandler.setPasteAction(PasteAction.getInstance());
        textActionHandler.setDeleteAction(DeleteAction.getInstance());

    }
}
