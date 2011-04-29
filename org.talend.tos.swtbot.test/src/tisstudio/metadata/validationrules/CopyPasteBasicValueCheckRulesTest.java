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
package tisstudio.metadata.validationrules;

import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.WidgetOfType;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.talend.swtbot.TalendSwtBotForTos;
import org.talend.swtbot.Utilities;

/**
 * DOC fzhong class global comment. Detailled comment
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class CopyPasteBasicValueCheckRulesTest extends TalendSwtBotForTos {

    private SWTBotView view;

    private SWTBotTree tree;

    private SWTBotTreeItem treeNode;

    private SWTBotTreeItem metadataNode;

    private static final String VALIDATION_RULES_NAME = "rulesTest";

    private static final String METADATA_NAME = "metadata";

    private static final String RULE_TYPE = "Basic Value Check";

    @Before
    public void createBasicValueCheckRules() throws IOException, URISyntaxException {
        view = Utilities.getRepositoryView(gefBot);
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        treeNode = Utilities.getTalendItemNode(tree, Utilities.TalendItemType.VALIDATION_RULES);
        metadataNode = Utilities.getTalendItemNode(tree, Utilities.TalendItemType.FILE_DELIMITED);
        Utilities.createFileDelimited(METADATA_NAME, metadataNode, gefBot);
        Utilities.createValidationRules(RULE_TYPE, Utilities.TalendItemType.FILE_DELIMITED, METADATA_NAME, VALIDATION_RULES_NAME,
                gefBot, treeNode);
    }

    @Test
    public void copyPasteBasicValueCheckRules() {
        Utilities.copyAndPaste(treeNode, VALIDATION_RULES_NAME, "0.1");
    }

    @After
    public void removePreviouslyCreateItems() {
        Utilities.cleanUpRepository(treeNode);
        Utilities.cleanUpRepository(metadataNode);
        Utilities.emptyRecycleBin(gefBot, tree);
    }
}
