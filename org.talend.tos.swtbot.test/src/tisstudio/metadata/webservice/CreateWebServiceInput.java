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
package tisstudio.metadata.webservice;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.WidgetOfType;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.talend.swtbot.TalendSwtBotForTos;

/**
 * DOC Administrator class global comment. Detailled comment
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class CreateWebServiceInput extends TalendSwtBotForTos {

    private static SWTBotView view;

    private static SWTBotTree tree;

    private static String WEBSERVICENAME = "webService1";

    private static String URL = "http://www.deeptraining.com/webservices/weather.asmx?WSDL";

    private static String METHOD = "GetWeather";

    @Test
    public void createWebServiceInput() {
        view = gefBot.viewByTitle("Repository");
        view.setFocus();

        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        tree.setFocus();

        tree.expandNode("Metadata").getNode("Web Service").contextMenu("Create Web Service schema").click();
        gefBot.waitUntil(Conditions.shellIsActive("Create new Web Service schema"));
        gefBot.shell("Create new Web Service schema").activate();

        /* step 1 of 4 */
        gefBot.textWithLabel("Name").setText(WEBSERVICENAME);
        gefBot.button("Next >").click();

        /* step 2 of 4 */
        gefBot.button("Next >").click();

        /* step 3 of 4 */
        gefBot.textWithLabel("Web Service").setText(URL);
        gefBot.textWithLabel("Method").setText(METHOD);
        gefBot.button("Add").click();
        gefBot.button("Refresh Preview").click();
        while (!"Refresh Preview".equals(gefBot.button(2).getText())) {
            // wait for previewing
        }
        gefBot.button("Next >").click();

        /* step 4 of 4 */
        gefBot.button("Finish").click();

    }
}
