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
package org.talend.designer.components.mdmprovider;

import java.io.File;
import java.net.URL;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.talend.core.model.components.AbstractComponentsProvider;

/**
 * DOC wliu class global comment. Detailled comment
 */
public class MdmComponentsProvider extends AbstractComponentsProvider {

    private static Logger logger = Logger.getLogger(MdmComponentsProvider.class);

    /**
     * DOC wliu MdmComponentsProvider constructor comment.
     */
    public MdmComponentsProvider() {
    }

    protected File getExternalComponentsLocation() {
        URL url = FileLocator.find(MdmComponentProviderActivator.getDefault().getBundle(), new Path("components"), null); //$NON-NLS-1$
        URL fileUrl;
        try {
            fileUrl = FileLocator.toFileURL(url);
            return new File(fileUrl.getPath());
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.AbstractComponentsProvider#getFamilyTranslation(java.lang.String)
     */
    @Override
    public String getFamilyTranslation(String text) {
        // TODO to implement when TDQ provider got translations
        return null;
    }
}
