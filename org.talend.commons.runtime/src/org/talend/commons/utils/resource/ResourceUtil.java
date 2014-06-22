// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.commons.utils.resource;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.URIUtil;

/**
 * class ResourceUtil. Helper to handle resource URL from java or Eclipse.
 */
public class ResourceUtil {

    private static final String BUNDLERESOURCE = "bundleresource";

    public static File convertResourceToFile(URL resource) throws IOException, URISyntaxException {
        File fileDir = null;
        if (BUNDLERESOURCE.equals(resource.getProtocol())) {
            URL unescapedURL = FileLocator.toFileURL(resource);
            URI escapedURI = new URI(unescapedURL.getProtocol(), unescapedURL.getPath(), unescapedURL.getQuery());
            fileDir = URIUtil.toFile(escapedURI);
        } else {
            fileDir = new File(resource.getFile());
        }
        return fileDir;
    }

}
