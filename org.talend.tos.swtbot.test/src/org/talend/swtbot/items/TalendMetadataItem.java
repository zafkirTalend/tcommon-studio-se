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
package org.talend.swtbot.items;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.talend.swtbot.Utilities;
import org.talend.swtbot.Utilities.TalendItemType;

/**
 * DOC fzhong class global comment. Detailled comment
 */
public class TalendMetadataItem extends TalendItem {

    protected String expectResult;

    protected String componentType;

    public TalendMetadataItem() {
    }

    public TalendMetadataItem(TalendItemType itemType) {
        super(itemType);
    }

    public TalendMetadataItem(String itemName, TalendItemType itemType) {
        super(itemName, itemType);
    }

    public String getComponentType() {
        return this.componentType;
    }

    /**
     * DOC fzhong Comment method "setComponentType". The componentType should to be set if it will pop up a shell
     * "Components" that ask you to choose when drag&drop the metadata to a job. Otherwise, don't need to set it.
     * 
     * @param componentType The label of component you would choose
     */
    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public String getExpectResult() {
        return this.expectResult;
    }

    public void setExpectResult(String expectResult) {
        this.expectResult = expectResult;
    }

    public void setExpectResultFromFile(String fileName) {
        try {
            File resultFile = Utilities.getFileFromCurrentPluginSampleFolder(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(resultFile));
            String tempStr = null;
            StringBuffer rightResult = new StringBuffer();
            while ((tempStr = reader.readLine()) != null)
                rightResult.append(tempStr + "\n");
            this.expectResult = rightResult.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
