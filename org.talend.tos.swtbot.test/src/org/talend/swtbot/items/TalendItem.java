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

import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.talend.swtbot.Utilities;
import org.talend.swtbot.Utilities.TalendItemType;

/**
 * DOC fzhong class global comment. Detailled comment
 */
public class TalendItem {

    protected SWTBotTreeItem item;

    protected String itemFullName;

    protected String itemName;

    protected String itemVersion;

    protected String folderPath;

    protected TalendItemType itemType;

    protected SWTBotTreeItem parentNode;

    public TalendItem() {
    }

    public TalendItem(TalendItemType itemType) {
        initialise(itemType);
    }

    public TalendItem(String itemName, TalendItemType itemType) {
        this.itemName = itemName;
        initialise(itemType);
    }

    public SWTBotTreeItem getItem() {
        return this.item;
    }

    public void setItem(SWTBotTreeItem item) {
        this.item = item;
        this.itemFullName = item.getText();
        if (item.getText().indexOf(" ") != -1) {
            String[] temp = itemFullName.split(" ");
            this.itemName = temp[0];
            this.itemVersion = temp[1];
        } else
            this.itemName = item.getText();
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public TalendItemType getItemType() {
        return this.itemType;
    }

    protected void setItemType(TalendItemType itemType) {
        this.itemType = itemType;
    }

    public SWTBotTreeItem getParentNode() {
        return parentNode;
    }

    protected void setParentNode(SWTBotTreeItem parentNode) {
        this.parentNode = parentNode;
    }

    public String getItemFullName() {
        return itemFullName;
    }

    protected void setItemFullName(String itemFullName) {
        this.itemFullName = itemFullName;
    }

    public String getItemVersion() {
        return itemVersion;
    }

    public void setItemVersion(String itemVersion) {
        this.itemVersion = itemVersion;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    protected void initialise(TalendItemType itemType) {
        setItemType(itemType);
        setParentNode(Utilities.getTalendItemNode(itemType));
    }

    public void create() {
    }

    public void copyAndPaste() {
        Utilities.copyAndPaste(parentNode, itemName, itemVersion);
    }

    public void delete() {
        Utilities.delete(parentNode, itemName, itemVersion, folderPath);
    }

    public void duplicate(String newItemName) {
        Utilities.duplicate(parentNode, itemName, itemVersion, newItemName);
    }
}
