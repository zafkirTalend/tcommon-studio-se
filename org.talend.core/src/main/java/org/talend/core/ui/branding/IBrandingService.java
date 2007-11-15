// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.ui.branding;

import java.io.IOException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.talend.core.IService;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 */
public interface IBrandingService extends IService {

    public String getFullProductName();

    public String getShortProductName();

    public String getCorporationName();

    public ImageDescriptor getLoginVImage();

    public ImageDescriptor getLoginHImage();

    public URL getLicenseFile() throws IOException;
}
