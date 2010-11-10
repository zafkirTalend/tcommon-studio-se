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
package tosstudio.metadata.ldap;

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
public class CreateLdap extends TalendSwtBotForTos {

    private static SWTBotTree tree;

    private static SWTBotShell shell;

    private static SWTBotView view;

    private static String LDAPNAME = "ldap1";

    private static String HOSTNAME = "192.168.0.244";

    private static String PORT = "389";

    private static String DN_OR_USER = "cn=Manager,dc=example,dc=com";

    private static String PASSWORD = "secret";

    @Test
    public void createLdap() {
        view = gefBot.viewByTitle("Repository");
        view.setFocus();

        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        tree.setFocus();

        tree.expandNode("Metadata").getNode("LDAP").contextMenu("Create LDAP schema").click();
        gefBot.waitUntil(Conditions.shellIsActive("Create new LDAP schema"));
        gefBot.shell("Create new LDAP schema").activate();

        /* step 1 of 5 */
        gefBot.textWithLabel("Name").setText(LDAPNAME);
        gefBot.button("Next >").click();

        /* step 2 of 5 */
        gefBot.comboBoxWithLabel("Hostname:").setText(HOSTNAME);
        gefBot.comboBoxWithLabel("Port:").setText(PORT);
        gefBot.button("Check Network Parameter").click();

        gefBot.waitUntil(Conditions.shellIsActive("Check Network Parameter"), 20000);
        shell = gefBot.shell("Check Network Parameter");
        gefBot.button("OK").click();
        gefBot.waitUntil(Conditions.shellCloses(shell));
        gefBot.button("Next >").click();

        /* step 3 of 5 */
        gefBot.comboBoxWithLabel("Bind DN or user:").setText(DN_OR_USER);
        gefBot.textWithLabel("Bind password:").setText(PASSWORD);
        gefBot.button("Check Authentication").click();

        gefBot.waitUntil(Conditions.shellIsActive("Check Authentication Parameter"), 20000);
        shell = gefBot.shell("Check Authentication Parameter");
        gefBot.button("OK").click();
        gefBot.waitUntil(Conditions.shellCloses(shell));
        gefBot.button("Fetch Base DNs").click();

        gefBot.waitUntil(Conditions.shellIsActive("Fetch base DNs"));
        shell = gefBot.shell("Fetch base DNs");
        gefBot.button("OK").click();
        gefBot.waitUntil(Conditions.shellCloses(shell));
        gefBot.button("Next >").click();

        /* step 4 of 5 */
        for (int i = 0; i < 5; i++) {
            gefBot.tableInGroup("List attributes of LDAP Schema").getTableItem(i).check();
        }
        gefBot.button("Refresh Preview").click();
        while (!"Refresh Preview".equals(gefBot.button(2).getText())) {
            // wait for previewing
        }
        gefBot.button("Next >").click();

        /* step 5 of 5 */
        gefBot.button("Finish").click();
    }
}
