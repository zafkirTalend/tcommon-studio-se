// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.core.ui;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.SubContributionItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;

/**
 * Use to disabled some actions following the project language.<br/>
 * 
 */
public class DisableLanguageActions implements IStartup {

    public void earlyStartup() {
        List<String> ids = null;

        switch (((RepositoryContext) CorePlugin.getContext().getProperty(Context.REPOSITORY_CONTEXT_KEY)).getProject()
                .getLanguage()) {
        case JAVA:
            ids = Arrays.asList(new String[] { "org.talend.help.perl.OpenPerlHelpAction" });
            break;
        case PERL:
            ids = null;
            break;
        }

        if (ids != null && !ids.isEmpty()) {
            final String[] toolsID = ids.toArray(new String[] {});
            final IWorkbench workbench = PlatformUI.getWorkbench();
            workbench.getDisplay().asyncExec(new Runnable() {

                public void run() {
                    IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
                    if (window != null) {
                        Menu menubar = window.getShell().getMenuBar();
                        MenuItem[] mItems = menubar.getItems();

                        for (MenuItem item : mItems) {
                            SubContributionItem cItem = null;
                            Object menuItemData = item.getData();
                            if (menuItemData == null) {
                                continue;
                            }

                            MenuManager menumanager = (MenuManager) menuItemData;
                            IContributionItem[] items2 = menumanager.getItems();
                            for (IContributionItem contributionItem : items2) {
                                if (contributionItem instanceof SubContributionItem) {
                                    cItem = (SubContributionItem) contributionItem;
                                    IContributionItem conItems = cItem.getInnerItem();
                                    if (conItems instanceof ActionContributionItem) {
                                        ActionContributionItem items = (ActionContributionItem) conItems;
                                        String id = cItem.getId();
                                        if ((id != null) && (Arrays.binarySearch(toolsID, id) >= 0)) {
                                            items.getAction().setEnabled(false);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    }
}
