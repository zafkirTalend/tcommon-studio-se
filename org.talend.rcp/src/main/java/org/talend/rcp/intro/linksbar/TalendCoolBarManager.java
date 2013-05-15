// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.rcp.intro.linksbar;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.internal.provisional.action.CoolBarManager2;
import org.eclipse.swt.SWT;

/**
 * created by nrousseau on May 15, 2013 Detailled comment
 * 
 */
public class TalendCoolBarManager extends CoolBarManager2 {

    public TalendCoolBarManager(int style) {
        super(style);

        IToolBarManager toolBarManager = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
        toolBarManager.add(new LinksToolbarItem());
        add(new ToolBarContributionItem(toolBarManager, TalendActionBarPresentationFactory.COOLITEM_LINKS_ID));
    }

    @Override
    public void refresh() {
        IContributionItem linksCoolItem = find(TalendActionBarPresentationFactory.COOLITEM_LINKS_ID);
        // means: adjust the order, make sure the Links of Studio coolItem always at the last postion.
        // (deal with the case: the Extention point add new coolitem dynamically. )
        if (linksCoolItem != null) {
            remove(linksCoolItem);
            add(linksCoolItem);
        }

        super.refresh();
    }
}
