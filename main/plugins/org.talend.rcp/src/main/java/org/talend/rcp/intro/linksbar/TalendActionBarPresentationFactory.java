// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
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

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.internal.provisional.action.ICoolBarManager2;
import org.eclipse.jface.internal.provisional.action.IToolBarContributionItem;
import org.eclipse.jface.internal.provisional.action.IToolBarManager2;
import org.eclipse.jface.internal.provisional.action.ToolBarContributionItem2;
import org.eclipse.jface.internal.provisional.action.ToolBarManager2;
import org.eclipse.swt.SWT;
import org.eclipse.ui.internal.provisional.presentations.IActionBarPresentationFactory;
import org.eclipse.ui.presentations.WorkbenchPresentationFactory;
import org.talend.rcp.Activator;

/**
 * created by nrousseau on May 15, 2013 Detailled comment
 * 
 */
public class TalendActionBarPresentationFactory extends WorkbenchPresentationFactory implements IActionBarPresentationFactory {

    public static final String COOLITEM_LINKS_ID = Activator.PLUGIN_ID + ".CoolItemLinks"; //$NON-NLS-1$

    @Override
    public ICoolBarManager2 createCoolBarManager() {

        return new TalendCoolBarManager(SWT.FLAT);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.internal.presentations.IActionBarPresentationFactory#createToolBarManager()
     */
    @Override
    public IToolBarManager2 createToolBarManager() {
        return new ToolBarManager2(SWT.FLAT | SWT.RIGHT);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.internal.presentations.IActionBarPresentationFactory#createViewToolBarManager()
     */
    @Override
    public IToolBarManager2 createViewToolBarManager() {
        return new ToolBarManager2(SWT.FLAT | SWT.RIGHT | SWT.WRAP);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.internal.presentations.IActionBarPresentationFactory#createToolBarContributionItem(org.eclipse
     * .jface.action.IToolBarManager, java.lang.String)
     */
    @Override
    public IToolBarContributionItem createToolBarContributionItem(IToolBarManager toolBarManager, String id) {
        return new ToolBarContributionItem2(toolBarManager, id);
    }
}
