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
package tosstudio.metadata.genericschema;

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
public class CreateGenericSchema extends TalendSwtBotForTos {

    private static SWTBotTree tree;

    private static SWTBotView view;

    private static String SCHEMANAME = "schema1";

    private static String[] COLUMN = { "A", "B", "C" };

    private static String[] TYPE = { "int | Integer", "String", "float | Float" };

    @Test
    public void createGenericSchema() {
        view = gefBot.viewByTitle("Repository");
        view.setFocus();

        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        tree.setFocus();

        tree.expandNode("Metadata").getNode("Generic schemas").contextMenu("Create generic schema").click();
        gefBot.waitUntil(Conditions.shellIsActive("Create new generic schema"));
        gefBot.shell("Create new generic schema").activate();

        /* step 1 of 2 */
        gefBot.textWithLabel("Name").setText(SCHEMANAME);
        gefBot.button("Next >").click();

        /* step 2 of 2 */
        for (int i = 0; i < 3; i++) {
            gefBot.buttonWithTooltip("Add").click();
            gefBot.table().click(i, 2);
            gefBot.text().setText(COLUMN[i]);
            gefBot.table().click(i, 4);
            gefBot.ccomboBox().setSelection(TYPE[i]);
        }

        gefBot.button("Finish").click();
    }
}
