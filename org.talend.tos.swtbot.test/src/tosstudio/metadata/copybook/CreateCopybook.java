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
package tosstudio.metadata.copybook;

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
public class CreateCopybook extends TalendSwtBotForTos {

    private static SWTBotTree tree;

    private static SWTBotView view;

    private static String COPYBOOKNAME = "copybook1";

    private static String FILEPATH = "E:/testdata/FDSUJ-formated.copybook";

    private static String DATAFILE = "E:/testdata/file1.txt";

    @Test
    public void createCopybook() {
        view = gefBot.viewByTitle("Repository");
        view.setFocus();

        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        tree.setFocus();

        tree.expandNode("Metadata").getNode("Copybook").contextMenu("Create EBCDIC").click();
        gefBot.waitUntil(Conditions.shellIsActive("EBCDIC Connection"));
        gefBot.shell("EBCDIC Connection").activate();

        /* step 1 of 3 */
        gefBot.textWithLabel("Name").setText(COPYBOOKNAME);
        gefBot.button("Next >").click();

        /* step 2 of 3 */
        gefBot.textWithLabel("File").setText(FILEPATH);
        gefBot.textWithLabel("Data file").setText(DATAFILE);
        gefBot.button("Generate").click();
        gefBot.button("Next >").click();

        /* step 3 of 3 */
        gefBot.button("Finish").click();
    }
}
