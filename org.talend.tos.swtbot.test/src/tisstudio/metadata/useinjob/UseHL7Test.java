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
package tisstudio.metadata.useinjob;

import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
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
import org.talend.swtbot.helpers.MetadataHelper;
import org.talend.swtbot.items.TalendHL7Item;

/**
 * DOC fzhong class global comment. Detailled comment
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class UseHL7Test extends TalendSwtBotForTos {

    private SWTBotView view;

    private SWTBotTree tree;

    private SWTBotTreeItem jobNode;

    private SWTBotTreeItem jobItem;

    private SWTBotTreeItem metadataNode;

    private SWTBotTreeItem metadataItem;

    private SWTBotGefEditor jobEditor;

    private static final String JOBNAME = "jobTest"; // $NON-NLS-1$

    private static final String METADATA_NAME = "hl7Test"; // $NON-NLS-1$

    @Before
    public void createJobAndMetadata() throws IOException, URISyntaxException {
        view = Utilities.getRepositoryView();
        view.setFocus();
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        jobNode = Utilities.getTalendItemNode(Utilities.TalendItemType.JOB_DESIGNS);
        jobItem = Utilities.createJob(JOBNAME, jobNode);
        jobEditor = gefBot.gefEditor("Job " + jobItem.getText());
        metadataNode = Utilities.getTalendItemNode(Utilities.TalendItemType.HL7);
        metadataItem = Utilities.createHL7("input", gefBot, metadataNode, METADATA_NAME);
    }

    @Test
    public void useMetadataInJob() throws IOException, URISyntaxException {
        TalendHL7Item hl7Item = new TalendHL7Item();
        hl7Item.setItem(metadataItem);
        MetadataHelper.output2Console(jobEditor, hl7Item, "row_MSH_1");

        String result = gefBot.styledText().getText();
        MetadataHelper.assertResult(result, hl7Item);
    }

    @After
    public void removePreviousCreateItems() {
        jobEditor.saveAndClose();
        Utilities.cleanUpRepository(jobNode);
        Utilities.cleanUpRepository(metadataNode);
        Utilities.emptyRecycleBin();
    }
}
