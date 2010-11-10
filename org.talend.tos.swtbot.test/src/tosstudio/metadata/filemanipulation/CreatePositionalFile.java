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
public class CreatePositionalFile extends TalendSwtBotForTos {

    private static SWTBotTree tree;

    private static SWTBotView view;

    private static String FILENAME = "test_positional";

    private static String FILEPATH = "E:/testdata/test1.txt";

    @Test
    public void createPositionalFile() {
        view = gefBot.viewByTitle("Repository");
        view.setFocus();

        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        tree.setFocus();

        tree.expandNode("Metadata").getNode("File positional").contextMenu("Create file positional").click();
        gefBot.waitUntil(Conditions.shellIsActive("New Positional File"));
        gefBot.shell("New Positional File").activate();

        gefBot.textWithLabel("Name").setText(FILENAME);
        gefBot.button("Next >").click();
        gefBot.textWithLabel("File").setText(FILEPATH);
        gefBot.comboBoxWithLabel("Format").setSelection("WINDOWS");
        gefBot.textWithLabel("Field Separator").setText("5,7,7,*");
        gefBot.textWithLabel("Marker position").setText("5,12,19");
        gefBot.button("Next >").click();
        while (!"Refresh Preview".equals(gefBot.button(0).getText())) {
            // wait for previewing
        }
        gefBot.button("Next >").click();
        gefBot.button("Finish").click();
    }
}
