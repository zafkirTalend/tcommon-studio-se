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
public class CreateLdifFile extends TalendSwtBotForTos {

    private static SWTBotTree tree;

    private static SWTBotView view;

    private static String FILENAME = "test_ldif";

    private static String FILEPATH = "E:/testdata/sample.ldif";

    @Test
    public void createLdifFile() {
        view = gefBot.viewByTitle("Repository");
        view.setFocus();

        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        tree.setFocus();

        tree.expandNode("Metadata").getNode("File ldif").contextMenu("Create file ldif").click();
        gefBot.waitUntil(Conditions.shellIsActive("New Ldif File"));
        gefBot.shell("New Ldif File").activate();

        gefBot.textWithLabel("Name").setText(FILENAME);
        gefBot.button("Next >").click();
        gefBot.textWithLabel("File").setText(FILEPATH);
        gefBot.button("Next >").click();
        for (int i = 0; i < 5; i++) {
            gefBot.tableInGroup("List Attributes of Ldif file").getTableItem(i).check();
        }
        gefBot.button("Next >").click();
        gefBot.button("Finish").click();
    }
}
