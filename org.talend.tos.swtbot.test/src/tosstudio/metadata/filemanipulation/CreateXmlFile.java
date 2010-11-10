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
package tosstudio.metadata.filemanipulation;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.WidgetOfType;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.talend.swtbot.TalendSwtBotForTos;

/**
 * DOC Administrator class global comment. Detailled comment
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class CreateXmlFile extends TalendSwtBotForTos {

    private static SWTBotTree tree;

    private static SWTBotView view;

    private static String FILENAME = "test_xml";

    private static String FILEPATH = "E:/testdata/log.xml";

    private static String LOOP = "/schema/column";

    private static String[] SCHEMA = { "comment", "default", "key" };

    @Test
    public void createXmlFile() {
        view = gefBot.viewByTitle("Repository");
        view.setFocus();

        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        tree.setFocus();

        tree.expandNode("Metadata").getNode("File XML").contextMenu("Create file XML").click();
        gefBot.waitUntil(Conditions.shellIsActive("New Xml File"));
        gefBot.shell("New Xml File").activate();

        gefBot.textWithLabel("Name").setText(FILENAME);
        gefBot.button("Next >").click();
        gefBot.button("Next >").click();
        gefBot.textWithLabel("XML").setText(FILEPATH);
        gefBot.button("Next >").click();
        gefBot.tableInGroup("Target Schema", 0).click(0, 1);
        gefBot.text().setText(LOOP);
        for (int i = 0; i < 3; i++) {
            gefBot.buttonWithTooltip("Add").click();
            gefBot.tableInGroup("Target Schema", 1).click(0, 1);
            gefBot.text().setText("@" + SCHEMA[i]);
            gefBot.tableInGroup("Target Schema", 1).click(0, 2);
            gefBot.text().setText(SCHEMA[i]);
        }
        gefBot.button("Next >").click();
        gefBot.button("Finish").click();
    }
}
