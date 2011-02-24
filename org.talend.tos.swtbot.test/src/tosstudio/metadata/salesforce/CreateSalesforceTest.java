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
package tosstudio.metadata.salesforce;

import junit.framework.Assert;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.WidgetOfType;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.talend.swtbot.TalendSwtBotForTos;
import org.talend.swtbot.Utilities;

/**
 * DOC Administrator class global comment. Detailled comment
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class CreateSalesforceTest extends TalendSwtBotForTos {

    private SWTBotTree tree;

    private SWTBotView view;

    private static final String SALESFORCENAME = "saleforce1"; //$NON-NLS-1$

    private static final String USERNAME = "cantoine@talend.com"; //$NON-NLS-1$

    private static final String PASSWORD = "talendehmrEvHz2xZ8f2KlmTCymS0XU"; //$NON-NLS-1$

    @Before
    public void initialisePrivateFields() {
        view = gefBot.viewByTitle("Repository");
        view.setFocus();
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
    }

    @Test
    public void createSalesforce() {
        tree.setFocus();

        tree.expandNode("Metadata").getNode("Salesforce").contextMenu("Create Salesforce schema").click();
        gefBot.shell("New Salesforce ").activate();
        gefBot.waitUntil(Conditions.shellIsActive("New Salesforce "));

        /* step 1 of 4 */
        gefBot.textWithLabel("Name").setText(SALESFORCENAME);
        gefBot.button("Next >").click();

        /* step 2 of 4 */
        gefBot.textWithLabel("User name").setText(USERNAME);
        gefBot.textWithLabel("Password ").setText(PASSWORD);
        gefBot.button("Check login").click();

        gefBot.waitUntil(new DefaultCondition() {

            public boolean test() throws Exception {
                return gefBot.shell("Check Connection ").isActive();
            }

            public String getFailureMessage() {
                gefBot.shell("New Salesforce ").close();
                return "connection failure";
            }
        }, 30000);
        gefBot.button("OK").click();
        gefBot.button("Next >").click();

        /* step 3 of 4 */
        gefBot.waitUntil(new DefaultCondition() {

            public boolean test() throws Exception {
                return gefBot.button("Next >").isEnabled();
            }

            public String getFailureMessage() {
                gefBot.shell("New Salesforce ").close();
                return "next button was never enabled";
            }
        }, 60000);
        gefBot.button("Next >").click();

        /* step 4 of 4 */
        gefBot.waitUntil(new DefaultCondition() {

            public boolean test() throws Exception {
                return gefBot.button("Finish").isEnabled();
            }

            public String getFailureMessage() {
                gefBot.shell("New Salesforce ").close();
                return "finish button was never enabled";
            }
        });
        gefBot.button("Finish").click();

        SWTBotTreeItem newSalesforceItem = null;
        try {
            newSalesforceItem = tree.expandNode("Metadata").expandNode("Salesforce").getNode(SALESFORCENAME + " 0.1");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Assert.assertNotNull("salesforce item is not created", newSalesforceItem);
        }
    }

    @After
    public void removePreviouslyCreateItems() {
        tree.expandNode("Metadata").expandNode("Salesforce").getNode(SALESFORCENAME + " 0.1").contextMenu("Delete").click();
        Utilities.emptyRecycleBin(gefBot, tree);
    }
}
