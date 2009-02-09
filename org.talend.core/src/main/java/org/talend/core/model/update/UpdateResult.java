// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
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

import org.talend.core.model.process.IProcess2;
import org.talend.core.model.properties.ConnectionItem;

/**
 * ggu class global comment. Detailled comment
 */
public abstract class UpdateResult {

    private boolean checked = true;

    private Object object = null;

    private EUpdateResult resultType = null;

    private EUpdateItemType updateType = null;

    private Object parameter = null;

    private String remark = null;

    private Object job = null;

    private boolean readOnlyProcess = false;

    private ConnectionItem connItem;

    /**
     * Getter for readOnlyProcess.
     * 
     * @return the readOnlyProcess
     */
    public boolean isReadOnlyProcess() {
        return this.readOnlyProcess;
    }

    /**
     * Sets the readOnlyProcess.
     * 
     * @param readOnlyProcess the readOnlyProcess to set
     */
    public void setReadOnlyProcess(boolean readOnlyProcess) {
        this.readOnlyProcess = readOnlyProcess;
    }

    private boolean readOnly = false;

    /**
     * only for repository item update.
     */
    private IProcess2 process2 = null;

    /**
     * ggu UpdateCheckResult constructor comment.
     * 
     * @param item
     */
    public UpdateResult(Object object) {
        super();
        this.object = object;
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
        case UPDATE:
            break;
        case ADD:
        case DELETE:
            setChecked(true);
            break;
        default:
            setChecked(true);
            setReadOnly(true);
        }
    }

    public void setResult(EUpdateItemType updateType, EUpdateResult resultType, Object parameter) {
        setResult(updateType, resultType, parameter, null);
    }

    public void setResult(EUpdateItemType updateType, EUpdateResult resultType) {
        setResult(updateType, resultType, null);
    }

    public void setJob(Object job) {
        this.job = job;
    }

    public Object getJob() {
        return this.job;
    }

    public IProcess2 getItemProcess() {
        return this.process2;
    }

    public void setItemProcess(IProcess2 process2) {
        this.process2 = process2;
    }

    public boolean isReadOnly() {
        return this.readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Object getUpdateObject() {
        return this.object;
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

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public abstract String getJobInfor();

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(checkValues(getJobInfor()));
        sb.append(UpdatesConstants.SPACE);
        sb.append(checkValues(getCategory()));
        sb.append(UpdatesConstants.SPACE);
        sb.append(checkValues(getName()));
        sb.append(UpdatesConstants.SPACE);
        sb.append(checkValues(getResultType()));
        sb.append(UpdatesConstants.SPACE);
        sb.append(checkValues(getRemark()));
        sb.append(UpdatesConstants.SPACE);

        return sb.toString();
    }

    private Object checkValues(Object obj) {
        return obj == null ? UpdatesConstants.EMPTY : obj;

    }

    public ConnectionItem getContextModeConnectionItem() {
        return this.connItem;
    }

    public void setContextModeConnectionItem(ConnectionItem connItem) {
        this.connItem = connItem;
    }

    public abstract String getName();

    public abstract String getCategory();
}
