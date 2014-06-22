// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.items.importexport.handlers.imports;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.talend.core.model.repository.ERepositoryObjectType;

/**
 * DOC ggu class global comment. Detailled comment
 * 
 * 
 * most of importers should extends this class, just recommend.
 */
public class ImportRepTypeHandler extends ImportBasicHandler {

    protected static final String PARAM_TYPE = "type"; //$NON-NLS-1$

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.repository.items.importexport.handlers.imports.AbstractImportExecutableHandler#setInitializationData
     * (org.eclipse.core.runtime.IConfigurationElement, java.lang.String, java.lang.Object)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
        super.setInitializationData(config, propertyName, data);
        if (data != null && data instanceof Map) {
            Map parametersMap = (Map) data;
            Object object = parametersMap.get(PARAM_TYPE);
            if (object != null) {
                String[] types = object.toString().split(IImportConstants.SEP_COMMA);
                for (String type : types) {
                    if (StringUtils.isNotEmpty(type)) {
                        type = type.trim();
                        if (StringUtils.isNotEmpty(type)) {
                            ERepositoryObjectType type2 = ERepositoryObjectType.getType(type);
                            if (type2 != null && StringUtils.isNotEmpty(type2.getFolder())) {
                                checkedBasePathes.add(type2.getFolder());
                            }
                        }
                    }
                }
            }
        }
    }

}
