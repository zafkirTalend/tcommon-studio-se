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
package org.talend.repository.mdm.ui.wizard;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.talend.core.model.metadata.builder.connection.MDMConnection;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.repository.ui.swt.utils.AbstractForm;
import org.talend.repository.ui.utils.OtherConnectionContextUtils.EParamName;

/**
 * DOC hwang class global comment. Detailled comment
 */
public abstract class AbstractMDMFileStepForm extends AbstractForm {

    protected MDMConnection connection;

    /**
     * DOC Administrator AbstractMDMFileStepForm constructor comment.
     * 
     * @param parent
     * @param style
     */
    protected AbstractMDMFileStepForm(Composite parent, ConnectionItem connectionItem, String[] existingNames) {
        super(parent, SWT.NONE, existingNames);
        setConnectionItem(connectionItem);
    }

    public AbstractMDMFileStepForm(Composite parent, ConnectionItem connectionItem) {
        this(parent, connectionItem, null);
    }

    public AbstractMDMFileStepForm(Composite parent, ConnectionItem connectionItem, MetadataTable metadataTable,
            String[] existingNames) {
        super(parent, SWT.NONE, existingNames);
        setConnectionItem(connectionItem);
    }

    protected MDMConnection getConnection() {
        return (MDMConnection) connectionItem.getConnection();
    }

    @Override
    protected void exportAsContext() {
        collectConnParams();
        super.exportAsContext();
    }

    protected void collectConnParams() {
        addContextParams(EParamName.UNIVERSE, true);
        addContextParams(EParamName.DATACLUSTER, true);
        addContextParams(EParamName.DATAMODEL, true);
        addContextParams(EParamName.MDMURL, true);
    }

}
