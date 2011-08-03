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

/**
 * DOC fzhong class global comment. Detailled comment
 */
public class TalendEdiItem extends TalendItem {

    private String standard;

    private String release;

    private String[] schemas;

    public TalendEdiItem(String itemName, String standard, String release, String[] schema) {
        super();
        this.itemName = itemName;
        this.standard = standard;
        this.release = release;
        this.schemas = schema;
    }

    public String getStandard() {
        return this.standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getRelease() {
        return this.release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String[] getSchema() {
        return this.schemas;
    }

    public void setSchema(String[] schema) {
        this.schemas = schema;
    }

}
