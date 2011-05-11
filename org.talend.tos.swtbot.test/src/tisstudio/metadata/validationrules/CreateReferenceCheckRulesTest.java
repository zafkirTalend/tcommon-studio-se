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
public class CreateReferenceCheckRulesTest extends TalendSwtBotForTos {

    private SWTBotView view;

    private SWTBotTree tree;

    private SWTBotTreeItem treeNode;

    private SWTBotTreeItem metadataNode;

    private static final String VALIDATION_RULES_NAME = "rulesTest";

    private static final String DB_NAME = "testDB";

    private static final String RULE_TYPE = "Reference Check";

    @Before
    public void initialisePrivateFields() throws IOException, URISyntaxException {
        view = Utilities.getRepositoryView(gefBot);
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        treeNode = Utilities.getTalendItemNode(tree, Utilities.TalendItemType.VALIDATION_RULES);
        metadataNode = Utilities.getTalendItemNode(tree, Utilities.TalendItemType.DB_CONNECTIONS);
        Utilities.createDbConnection(gefBot, metadataNode, Utilities.DbConnectionType.MYSQL, DB_NAME);
        String sql = "create table test(id int, name varchar(12));\n" + "create table reference(id int, name varchar(12));";
        Utilities.executeSQL(gefBot, metadataNode.getNode(DB_NAME + " 0.1"), sql);
        Utilities.retrieveDbSchema(gefBot, metadataNode, DB_NAME, "test", "reference");
    }

    @Test
    public void createReferenceCheckRules() {
        Utilities.createValidationRules(RULE_TYPE, Utilities.TalendItemType.DB_CONNECTIONS, DB_NAME, VALIDATION_RULES_NAME,
                gefBot, treeNode);
    }

    @After
    public void removePreviouslyCreateItems() {
        String sql = "drop table test;\n" + "drop table reference;";
        Utilities.executeSQL(gefBot, metadataNode.getNode(DB_NAME + " 0.1"), sql);
        Utilities.cleanUpRepository(treeNode);
        Utilities.cleanUpRepository(metadataNode);
        Utilities.emptyRecycleBin(gefBot, tree);
    }
}
