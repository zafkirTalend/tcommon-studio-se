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

import org.talend.swtbot.Utilities;

/**
 * DOC fzhong class global comment. Detailled comment
 */
public class TalendXmlFileItem extends TalendFileItem {

    public TalendXmlFileItem() {
        super(Utilities.TalendItemType.FILE_XML, System.getProperty("fileXml.filepath"));
    }

    public TalendXmlFileItem(String itemName) {
        super(itemName, Utilities.TalendItemType.FILE_XML, System.getProperty("fileXml.filepath"));
    }
}
