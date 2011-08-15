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

    protected String itemName;

    protected TalendItemType itemType;

    protected SWTBotTreeItem parentNode;

    public SWTBotTreeItem getItem() {
        return this.item;
    }

    public void setItem(SWTBotTreeItem item) {
        this.item = item;
        if (item.getText().indexOf(" ") != -1)
            this.itemName = item.getText().substring(0, item.getText().indexOf(" "));
        else
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

    protected void initialise(TalendItemType itemType) {
        setItemType(itemType);
        setParentNode(Utilities.getTalendItemNode(itemType));
    }

    public void create() {
    }
}
