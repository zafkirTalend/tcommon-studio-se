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
package org.talend.core.model.action;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.talend.commons.ui.runtime.image.ImageProvider;
import org.talend.core.i18n.Messages;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class LogImageCacheAction extends Action {

    private static Logger log = Logger.getLogger(LogImageCacheAction.class);

    public LogImageCacheAction() {
        super();
        this.setActionDefinitionId("logImageCache"); //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        log.info(Messages.getString("LogImageCacheAction.CacheImage") + ImageProvider.getImageCache()); //$NON-NLS-1$
        log.info(Messages.getString("LogImageCacheAction.CacheImageDesc") + ImageProvider.getImageDescCache()); //$NON-NLS-1$
    }

}
