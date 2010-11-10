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
package tisstudio.metadata.sap;

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
public class CreateSapConnection extends TalendSwtBotForTos {

    private static SWTBotTree tree;

    private static SWTBotShell shell;

    private static SWTBotView view;

    private static String SAPNAME = "sap1";

    private static String CLIENT = "000";

    private static String HOST = "192.168.0.185";

    private static String USER = "TALEND";

    private static String PASSWORD = "FRANCE";

    private static String SYSTEM_NUMBER = "00";

    private static String LANGUAGE = "en";

    @Test
    public void createSapConnection() {
        view = gefBot.viewByTitle("Repository");
        view.setFocus();

        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        tree.setFocus();

        tree.expandNode("Metadata").getNode("SAP Connections").contextMenu("Create SAP connection").click();
        gefBot.waitUntil(Conditions.shellIsActive("SAP Connection"));
        gefBot.shell("SAP Connection").activate();

        /* step 1 of 2 */
        gefBot.textWithLabel("Name").setText(SAPNAME);
        gefBot.button("Next >").click();

        /* step 2 of 2 */
        gefBot.textWithLabel("Client").setText(CLIENT);
        gefBot.textWithLabel("Host").setText(HOST);
        gefBot.textWithLabel("User").setText(USER);
        gefBot.textWithLabel("Password").setText(PASSWORD);
        gefBot.textWithLabel("System Number").setText(SYSTEM_NUMBER);
        gefBot.textWithLabel("Language").setText(LANGUAGE);
        gefBot.button("Check").click();
        gefBot.waitUntil(Conditions.shellIsActive("Check SAP Connection"));
        shell = gefBot.shell("Check SAP Connection");
        gefBot.button("OK").click();
        gefBot.waitUntil(Conditions.shellCloses(shell));

        gefBot.button("Finish").click();
    }
}
