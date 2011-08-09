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
package org.talend.swtbot.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.junit.Assert;
import org.talend.swtbot.Utilities;
import org.talend.swtbot.items.TalendFileItem;
import org.talend.swtbot.items.TalendMetadataItem;

/**
 * DOC fzhong class global comment. Detailled comment
 */
public class MetadataHelper implements Helper {

    /**
     * DOC fzhong Comment method "output2Console". Use component "tLogRow" to output metadata which has default row name
     * "Main" in context menu to console.
     * 
     * @param jobEditor
     * @param item Metadata item
     */
    public static void output2Console(SWTBotGefEditor jobEditor, TalendMetadataItem item) {
        output2Console(jobEditor, item, "Main");
    }

    /**
     * DOC fzhong Comment method "output2Console". Use component "tLogRow" to output metadata to console.
     * 
     * @param jobEditor
     * @param item Metadata item
     * @param rowName The name of row in the context menu of component. "Main" as default.
     */
    public static void output2Console(SWTBotGefEditor jobEditor, TalendMetadataItem item, String rowName) {
        Utilities.dndMetadataOntoJob(GEFBOT, jobEditor, item.getItem(), item.getComponentType(), new Point(100, 100));
        Utilities.dndPaletteToolOntoJob(GEFBOT, jobEditor, "tLogRow", new Point(300, 100));

        SWTBotGefEditPart metadata = UTIL.getTalendComponentPart(jobEditor, item.getItemName());
        Assert.assertNotNull("can not get component '" + item.getComponentType() + "'", metadata);
        jobEditor.select(metadata).setFocus();
        jobEditor.clickContextMenu("Row").clickContextMenu(rowName);
        SWTBotGefEditPart tlogRow = UTIL.getTalendComponentPart(jobEditor, "tLogRow_1");
        Assert.assertNotNull("can not get component 'tLogRow'", tlogRow);
        jobEditor.click(tlogRow);
        jobEditor.save();

        JobHelper.runJob(jobEditor);
    }

    /**
     * DOC fzhong Comment method "assertResult". Assert the result of running.
     * 
     * @param result Result in console after running
     * @param item Metadata item
     * @throws IOException
     * @throws URISyntaxException
     */
    public static void assertResult(String result, TalendMetadataItem item) throws IOException, URISyntaxException {
        StringBuffer source = new StringBuffer();
        if (item instanceof TalendFileItem) {
            File sourcefile = ((TalendFileItem) item).getSourceFileAsResult();
            BufferedReader reader = new BufferedReader(new FileReader(sourcefile));
            String tempStr = null;
            while ((tempStr = reader.readLine()) != null)
                source.append(tempStr + "\n");
            if (result.indexOf(source.toString()) == -1)
                Assert.fail("the results are not expected");
        } else {
            String realResult = JobHelper.filterStatistics(result);
            if (!item.getRightResult().contains(realResult))
                Assert.fail("the results are not expected - " + realResult);
        }
    }

}
