// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.metadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by rdubois on 30 juil. 2015 Detailled comment
 *
 */
public class SparkMetadataTalendTypeFilter extends MetadataTalendTypeFilter {

    private final static List<String> UNSUPPORTED_TYPES = Arrays.asList(new String[] { "Document", "Dynamic" }); //$NON-NLS-1$ //$NON-NLS-2$

    private final static String ROWGENERATOR_COMPONENT_NAME = "tRowGenerator"; //$NON-NLS-1$

    private final static Map<String, List<String>> COMPONENT_UNSUPPORTED_TYPES = new HashMap<>();

    private final String mComponentName;

    static {
        COMPONENT_UNSUPPORTED_TYPES.put(ROWGENERATOR_COMPONENT_NAME, Arrays.asList(new String[] { "Object" })); //$NON-NLS-1$
    }

    /**
     * DOC rdubois SparkMetadataTalendTypeFilter constructor comment.
     * 
     * @param componentName, the current component name the filter applies on.
     */
    public SparkMetadataTalendTypeFilter(String componentName) {
        this.mComponentName = componentName;
    }

    @Override
    protected List<String> getUnsupportedTypes() {
        List<String> currentComponentUnsupportedType = COMPONENT_UNSUPPORTED_TYPES.get(this.mComponentName);
        if (currentComponentUnsupportedType != null) {
            List<String> unionList = new ArrayList<>();
            unionList.addAll(currentComponentUnsupportedType);
            unionList.addAll(UNSUPPORTED_TYPES);
            return unionList;
        } else {
            return UNSUPPORTED_TYPES;
        }
    }

}
