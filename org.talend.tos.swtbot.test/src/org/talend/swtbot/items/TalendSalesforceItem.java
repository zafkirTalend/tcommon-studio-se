// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.swtbot.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Assert;
import org.talend.swtbot.Utilities;

/**
 * DOC fzhong class global comment. Detailled comment
 */
public class TalendSalesforceItem extends TalendMetadataItem {

    public TalendSalesforceItem() {
        super(Utilities.TalendItemType.SALESFORCE);
    }

    public TalendSalesforceItem(String itemName) {
        super(itemName, Utilities.TalendItemType.SALESFORCE);
    }

    public Map<String, SWTBotTreeItem> retrieveModules(String... moduleName) {
        getItem().contextMenu("Retrieve Salesforce Modules").click();
        final SWTGefBot gefBot = new SWTGefBot();
        gefBot.waitUntil(Conditions.shellIsActive("Schema"), 10000);
        SWTBotShell tempShell = gefBot.shell("Schema");
        Map<String, SWTBotTreeItem> moduleItems = new HashMap<String, SWTBotTreeItem>();

        try {
            List<String> modules = new ArrayList<String>(Arrays.asList(moduleName));
            for (String module : modules) {
                gefBot.table(0).getTableItem(module).check();
            }
            gefBot.waitUntil(Conditions.shellCloses(gefBot.shell("Progress Information")));
            gefBot.button("Finish").click();

            for (String module : modules) {
                moduleItems.put(module, getItem().getNode(module).select());
            }
        } catch (WidgetNotFoundException wnfe) {
            tempShell.close();
            Assert.fail(wnfe.getCause().getMessage());
        } catch (Exception e) {
            tempShell.close();
            Assert.fail(e.getMessage());
        }
        return moduleItems;
    }
}
