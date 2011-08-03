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

/**
 * DOC fzhong class global comment. Detailled comment
 */
public class TalendItem {

    protected String itemName;

    protected SWTBotTreeItem item;

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public SWTBotTreeItem getItem() {
        return this.item;
    }

    public void setItem(SWTBotTreeItem item) {
        this.item = item;
        this.itemName = item.getText().substring(0, item.getText().indexOf(" "));
    }

}
