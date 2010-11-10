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
package tisstudio.metadata.hl7;

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
public class CreateHL7Input extends TalendSwtBotForTos {

    private static SWTBotView view;

    private static SWTBotTree tree;

    private static String HL7NAME = "hl7_1";

    private static String FILEPATH = "E:/testdata/HL7.txt";

    private static String[] COLUMN_MSH = { "MSH-1(1)-1-1[ST]", "MSH-2(1)-1-1[ST]", "MSH-3(1)-1-1[IS]" };

    private static String[] COLUMN_EVN = { "EVN-1(1)-1-1[ID]", "EVN-2(1)-1-1[TSComponentOne]" };

    @Test
    public void createHL7Input() {
        view = gefBot.viewByTitle("Repository");
        view.setFocus();

        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        tree.setFocus();

        tree.expandNode("Metadata").getNode("HL7").contextMenu("Create HL7").click();
        gefBot.waitUntil(Conditions.shellIsActive("New HL7 File"));
        gefBot.shell("New HL7 File").activate();

        /* step 1 of 5 */
        gefBot.textWithLabel("Name").setText(HL7NAME);
        gefBot.button("Next >").click();

        /* step 2 of 5 */
        gefBot.button("Next >").click();

        /* step 3 of 5 */
        gefBot.textWithLabel("File path:").setText(FILEPATH);
        gefBot.button("Next >").click();

        /* step 4 of 5 */
        for (int i = 0; i < 3; i++) {
            gefBot.buttonWithTooltip("Add").click();
            gefBot.tableInGroup("Schema View").click(i, 3);
            gefBot.text().setText(COLUMN_MSH[i]);
        }
        gefBot.comboBoxWithLabel("Segment (As Schema)").setSelection("EVN");
        for (int j = 0; j < 2; j++) {
            gefBot.buttonWithTooltip("Add").click();
            gefBot.tableInGroup("Schema View").click(j, 3);
            gefBot.text().setText(COLUMN_EVN[j]);
        }
        gefBot.button("Next >").click();

        /* step 5 of 5 */
        gefBot.button("Finish").click();
    }
}
