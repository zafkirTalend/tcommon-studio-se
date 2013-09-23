// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.commons.ui.swt.extended.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.talend.commons.ui.swt.extended.table.ExtendedTableModel;

public class HadoopPropertiesFieldModel extends ExtendedTableModel<HashMap<String, Object>> {

    public HadoopPropertiesFieldModel(String name) {
        super(name);
        setProperties(new ArrayList<HashMap<String, Object>>());
    }

    public HadoopPropertiesFieldModel(List<HashMap<String, Object>> conditionTypeList, String name) {
        super(name);
        setProperties(conditionTypeList);
    }

    public void setProperties(List<HashMap<String, Object>> properties) {
        registerDataList((List<HashMap<String, Object>>) properties);
    }

    public HashMap<String, Object> createHadoopPropertiesType() {
        return new HashMap<String, Object>();
    }
}
