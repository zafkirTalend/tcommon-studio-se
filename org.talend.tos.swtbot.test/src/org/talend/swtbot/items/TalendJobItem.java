package org.talend.swtbot.items;

import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.talend.swtbot.Utilities;

public class TalendJobItem extends TalendItem {

    public TalendJobItem() {
        super(Utilities.TalendItemType.JOB_DESIGNS);
    }

    public TalendJobItem(String itemName) {
        super(itemName, Utilities.TalendItemType.JOB_DESIGNS);
    }

    public SWTBotGefEditor getJobEditor() {
        return new SWTGefBot().gefEditor("Job " + item.getText());
    }

    @Override
    public void create() {
        SWTBotTreeItem item = Utilities.createJob(itemName, getParentNode());
        setItem(item);
    }
}
