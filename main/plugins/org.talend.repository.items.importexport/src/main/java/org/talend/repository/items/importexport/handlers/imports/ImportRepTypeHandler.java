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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.talend.commons.CommonsPlugin;
import org.talend.core.model.repository.DynaEnum;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.runtime.CoreRuntimePlugin;
import org.talend.core.utils.ProductUtils;

/**
 * DOC ggu class global comment. Detailled comment
 * 
 * 
 * most of importers should extends this class, just recommend.
 */
@SuppressWarnings("rawtypes")
public class ImportRepTypeHandler extends ImportBasicHandler {

    /**
     * key is perspectiveId, value is type of path.
     */
    protected static Map<String, List<IPath>> product4PathMap = new HashMap<String, List<IPath>>();

    protected static final String PARAM_TYPE = "type"; //$NON-NLS-1$
    static {
        // temp work for TUP-1878, if want to enable to import all item in one dialog, should remove this.
        for (ERepositoryObjectType type : DynaEnum.values(ERepositoryObjectType.class)) {
            if (!type.isResouce()) {
                continue;
            }
            String[] products = type.getProducts();
            if (products != null) {
                for (String p : products) {
                    String perspectiveId = ProductUtils.getPerspectiveId(p);
                    List<IPath> list = product4PathMap.get(perspectiveId);
                    if (list == null) {
                        list = new ArrayList<IPath>();
                        product4PathMap.put(perspectiveId, list);
                    }
                    String folder = type.getFolder();
                    if (StringUtils.isNotEmpty(folder)) {
                        list.add(new Path(folder));
                    }
                }
            }
        }
        //

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.repository.items.importexport.handlers.imports.AbstractImportExecutableHandler#setInitializationData
     * (org.eclipse.core.runtime.IConfigurationElement, java.lang.String, java.lang.Object)
     */
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

    /**
     * temp resolve for TUP-1878.
     */
    @Override
    protected boolean validRelativePath(IPath relativePath) {
        boolean valid = super.validRelativePath(relativePath);
        if (valid && !CommonsPlugin.isHeadless() && isEnableProductChecking()) {
            String currentPerspectiveId = CoreRuntimePlugin.getInstance().getActivedPerspectiveId();
            if (StringUtils.isNotEmpty(currentPerspectiveId)) {
                valid = false; // reset to check
                List<IPath> pathes = product4PathMap.get(currentPerspectiveId);
                if (pathes != null) {
                    for (IPath typePath : pathes) {
                        if (typePath.isPrefixOf(relativePath)) {
                            valid = true; // the path is under current product.
                            break;
                        }
                    }
                }
            }
        }
        return valid;
    }

}
