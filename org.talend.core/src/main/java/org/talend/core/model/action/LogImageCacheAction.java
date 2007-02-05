// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.core.model.action;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.talend.commons.ui.image.ImageProvider;
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
