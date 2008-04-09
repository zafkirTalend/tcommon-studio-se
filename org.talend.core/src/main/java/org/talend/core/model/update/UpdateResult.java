// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.update;

/**
 * ggu class global comment. Detailled comment
 */
public abstract class UpdateResult {

    private boolean checked = true;

    private Object item = null;

    private EUpdateResult resultType = null;

    private EUpdateItemType updateType = null;

    private Object parameter = null;

    private String remark = null;

    /**
     * ggu UpdateCheckResult constructor comment.
     * 
     * @param item
     */
    public UpdateResult(Object item) {
        super();
        this.item = item;
    }

    /**
     * 
     * ggu Comment method "setResult".
     * 
     * @param updateType
     * @param resultType
     * @param parameter
     * @param remark
     */
    public void setResult(EUpdateItemType updateType, EUpdateResult resultType, Object parameter, String remark) {
        this.updateType = updateType;
        this.resultType = resultType;
        this.parameter = parameter;
        this.remark = remark;
        // must initialize the checked==true, so it will show in the dialog
        switch (resultType) {
        case BUIL_IN:
        case RELOAD:
        case JOBLET_UPDATE:
            setChecked(true);
            break;
        default:
        }
    }

    public void setResult(EUpdateItemType updateType, EUpdateResult resultType, Object parameter) {
        setResult(updateType, resultType, parameter, null);
    }

    public void setResult(EUpdateItemType updateType, EUpdateResult resultType) {
        setResult(updateType, resultType, null);
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Object getUpdateObject() {
        return this.item;
    }

    public EUpdateResult getResultType() {
        return this.resultType;
    }

    public EUpdateItemType getUpdateType() {
        return this.updateType;
    }

    public Object getParameter() {
        return this.parameter;
    }

    public String getRemark() {
        return this.remark;
    }

    public String toString() {
        // the sequence is very important, it will sort with it
        StringBuffer sb = new StringBuffer();
        sb.append(getCategory());
        sb.append(UpdatesConstants.SPACE);
        sb.append(getName());
        sb.append(UpdatesConstants.SPACE);
        sb.append(getUpdateType());
        sb.append(UpdatesConstants.SPACE);
        sb.append(getResultType());
        sb.append(UpdatesConstants.SPACE);
        sb.append(getRemark());
        return sb.toString();
    }

    public abstract String getName();

    public abstract String getCategory();
}
