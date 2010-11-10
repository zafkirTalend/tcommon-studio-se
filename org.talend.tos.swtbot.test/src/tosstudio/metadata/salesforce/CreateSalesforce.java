// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
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

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.WidgetOfType;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.talend.swtbot.TalendSwtBotForTos;

/**
 * DOC Administrator class global comment. Detailled comment
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class CreateSalesforce extends TalendSwtBotForTos {

    private static SWTBotTree tree;

    private static SWTBotShell shell;

    private static SWTBotView view;

    private static String SALESFORCENAME = "saleforce1";

    private static String USERNAME = "cantoine@talend.com";

    private static String PASSWORD = "talendehmrEvHz2xZ8f2KlmTCymS0XU";

    @Test
    public void createSalesforce() {
        view = gefBot.viewByTitle("Repository");
        view.setFocus();

        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        tree.setFocus();

        tree.expandNode("Metadata").getNode("Salesforce").contextMenu("Create Salesforce schema").click();
        gefBot.waitUntil(Conditions.shellIsActive("New Salesforce"));
        gefBot.shell("New Salesforce").activate();

        /* step 1 of 4 */
        gefBot.textWithLabel("Name").setText(SALESFORCENAME);
        gefBot.button("Next >").click();

        /* step 2 of 4 */
        gefBot.textWithLabel("User name").setText(USERNAME);
        gefBot.textWithLabel("Password").setText(PASSWORD);
        gefBot.button("Check login").click();

        gefBot.waitUntil(Conditions.shellIsActive("Check Connection"), 20000);
        shell = gefBot.shell("Check Connection");
        gefBot.button("OK").click();
        gefBot.waitUntil(Conditions.shellCloses(shell));
        gefBot.button("Next >").click();

        /* step 3 of 4 */
        while (!"Refresh Preview".equals(gefBot.button(0).getText())) {
            // wait for previewing
        }
        gefBot.button("Next >").click();

        /* step 4 of 4 */
        gefBot.button("Finish").click();
    }
}
