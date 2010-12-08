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

import junit.framework.Assert;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.WidgetOfType;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.talend.swtbot.TalendSwtBotForTos;

/**
 * DOC Administrator class global comment. Detailled comment
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class CreateWebServiceInputTest extends TalendSwtBotForTos {

    private SWTBotView view;

    private SWTBotTree tree;

    private static String WEBSERVICENAME = "webService1"; //$NON-NLS-1$

    private static String URL = "http://www.deeptraining.com/webservices/weather.asmx?WSDL"; //$NON-NLS-1$

    private static String METHOD = "GetWeather"; //$NON-NLS-1$

    @Before
    public void InitialisePrivateFields() {
        view = gefBot.viewByTitle("Repository");
        view.setFocus();
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
    }

    @Test
    public void createWebServiceInput() {
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
        gefBot.waitUntil(new DefaultCondition() {

            public boolean test() throws Exception {

                return gefBot.button("Next >").isEnabled();
            }

            public String getFailureMessage() {
                gefBot.shell("Create new Web Service schema").close();
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
                gefBot.shell("Create new Web Service schema").close();
                return "finish button was never enabled";
            }
        });
        gefBot.button("Finish").click();

        SWTBotTreeItem newWebServiceItem = tree.expandNode("Metadata", "Web Service").select(WEBSERVICENAME + " 0.1");
        Assert.assertNotNull(newWebServiceItem);
    }

    @After
    public void removePreviouslyCreateItems() {
        tree.expandNode("Metadata", "Web Service").getNode(WEBSERVICENAME + " 0.1").contextMenu("Delete").click();

        tree.select("Recycle bin").contextMenu("Empty recycle bin").click();
        gefBot.waitUntil(Conditions.shellIsActive("Empty recycle bin"));
        gefBot.button("Yes").click();
    }
}
