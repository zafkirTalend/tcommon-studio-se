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
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.WidgetOfType;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * DOC Administrator class global comment. Detailled comment
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class CopyPasteRegexFile {

    private static SWTGefBot gefBot = new SWTGefBot();

    private static SWTBotTree tree;

    private static SWTBotView view;

    private static String FILENAME = "test_regex";

    private static String DEFAULT_VERSION = "0.1";

    @Test
    public void copyRegexFile() {
        view = gefBot.viewByTitle("Repository");
        view.setFocus();

        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        tree.setFocus();

        tree.expandNode("Metadata", "File regex").getNode(FILENAME + " " + DEFAULT_VERSION).contextMenu("Copy").click();
    }

    @Test
    public void pasteRegexFile() {
        tree.select("Metadata", "File regex").contextMenu("Paste").click();
    }
}
