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

import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Assert;
import org.talend.swtbot.Utilities;

/**
 * DOC fzhong class global comment. Detailled comment
 */
public class TalendWebServiceItem extends TalendMetadataItem {

    private String type;

    private static final String SIMPLE = "Simple WSDL";

    private static final String ADVANCED = "Advanced WebService";

    public TalendWebServiceItem() {
        super(Utilities.TalendItemType.WEB_SERVICE);
    }

    public TalendWebServiceItem(String itemName) {
        super(itemName, Utilities.TalendItemType.WEB_SERVICE);
    }

    public void setTypeAsSimple() {
        type = SIMPLE;
    }

    public void setTypeAsAdvanced() {
        type = ADVANCED;
    }

    @Override
    public void create() {
        final SWTGefBot gefBot = new SWTGefBot();
        getParentNode().contextMenu("Create WSDL schema").click();
        gefBot.waitUntil(Conditions.shellIsActive("Create new WSDL schema"));
        final SWTBotShell shell = gefBot.shell("Create new WSDL schema").activate();
        try {
            /* step 1 of 4 */
            gefBot.textWithLabel("Name").setText(itemName);
            boolean nextButtonIsEnabled = gefBot.button("Next >").isEnabled();
            if (nextButtonIsEnabled) {
                gefBot.button("Next >").click();
            } else {
                shell.close();
                Assert.assertTrue("next button is not enabled, maybe the item name is exist,", nextButtonIsEnabled);
            }

            if (SIMPLE.equals(type)) {
                /* step 2 of 4 */
                gefBot.button("Next >").click();

                /* step 3 of 4 */
                gefBot.textWithLabel("WSDL").setText(System.getProperty("webService.url"));
                gefBot.textWithLabel("Method").setText(System.getProperty("webService.method"));
                gefBot.button("Add ").click();
                gefBot.button("Refresh Preview").click();
                gefBot.waitUntil(new DefaultCondition() {

                    public boolean test() throws Exception {

                        return gefBot.button("Next >").isEnabled();
                    }

                    public String getFailureMessage() {
                        shell.close();
                        return "next button was never enabled";
                    }
                }, 60000);
                gefBot.button("Next >").click();
            } else if (ADVANCED.equals(type)) {
                /* step 2 of 4 */
                gefBot.radio("Advanced WebService").click();
                gefBot.button("Next >").click();

                /* step 3 of 4 */
                gefBot.button(1).click();// click refresh button
                gefBot.waitUntil(Conditions.shellCloses(gefBot.shell("Progress Information")), 30000);
                gefBot.tableWithLabel("Operation:").click(0, 0);

                // set input mapping
                // left schema
                gefBot.cTabItem(" Input mapping ").activate();
                gefBot.button("Schema Management").click();
                gefBot.shell("Schema").activate();
                gefBot.buttonWithTooltip("Add").click();
                gefBot.button("OK").click();
                // right schema
                gefBot.table(1).click(0, 1);
                gefBot.buttonWithTooltip("Add list element").click();
                gefBot.shell("ParameterTree").activate();
                gefBot.tree().select("City");
                gefBot.button("OK").click();

                gefBot.table(1).click(1, 0);
                gefBot.text().setText("input.newColumn");

                // set output mapping
                // left schema
                gefBot.cTabItem(" Output mapping ").activate();
                gefBot.table(0).click(0, 0);
                gefBot.buttonWithTooltip("Add List element").click();
                gefBot.shell("ParameterTree").activate();
                gefBot.tree().select("GetWeatherResult");
                gefBot.button("OK").click();
                // right schema
                gefBot.button("...").click();
                gefBot.buttonWithTooltip("Add").click();
                gefBot.button("OK").click();

                gefBot.table(1).click(0, 0);
                gefBot.text().setText("parameters.GetWeatherResult");

                gefBot.button("Next >").click();
            }

            /* step 4 of 4 */
            gefBot.waitUntil(new DefaultCondition() {

                public boolean test() throws Exception {

                    return gefBot.button("Finish").isEnabled();
                }

                public String getFailureMessage() {
                    shell.close();
                    return "finish button was never enabled";
                }
            });
            gefBot.button("Finish").click();
        } catch (Exception e) {
            shell.close();
            Assert.fail(e.getCause().getMessage());
        }

        SWTBotTreeItem newWebServiceItem = null;
        try {
            newWebServiceItem = getParentNode().expand().select(itemName + " 0.1");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Assert.assertNotNull("web service item is not created", newWebServiceItem);
        }

        setItem(getParentNode().getNode(itemName + " 0.1"));
    }
}
