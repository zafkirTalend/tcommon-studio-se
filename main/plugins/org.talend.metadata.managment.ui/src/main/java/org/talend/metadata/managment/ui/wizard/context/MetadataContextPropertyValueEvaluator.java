// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.metadata.managment.ui.wizard.context;

import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.utils.ContextParameterUtils;
import org.talend.daikon.properties.Property;
import org.talend.daikon.properties.PropertyValueEvaluator;
import org.talend.designer.core.model.utils.emf.talendfile.ContextType;
import org.talend.metadata.managment.ui.utils.ConnectionContextHelper;

/**
 * created by ycbai on 2016年2月6日 Detailled comment
 *
 */
public class MetadataContextPropertyValueEvaluator implements PropertyValueEvaluator {

    private Connection connection;

    public MetadataContextPropertyValueEvaluator(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Object evaluate(Property property, Object storedValue) {
        if (connection != null && connection.isContextMode() && storedValue != null) {
            ContextType contextType = ConnectionContextHelper.getContextTypeForContextMode(null, connection, true);
            if (contextType != null) {
                return ContextParameterUtils.getOriginalValue(contextType, String.valueOf(storedValue));
            }
        }
        return storedValue;
    }

}
