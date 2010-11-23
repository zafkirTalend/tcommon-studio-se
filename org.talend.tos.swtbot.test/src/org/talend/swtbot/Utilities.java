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

package org.talend.swtbot;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.URIUtil;

/**
 * DOC sgandon class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40Z nrousseau $
 * 
 */
public class Utilities {

    private static final String SAMPLE_FOLDER_PATH = "/sample/"; //$NON-NLS-1$

    /**
     * return a File instance pointing to the file or folder located below this plugin sample folder
     * 
     * @param subSamplePath, path located below /sample/, so subpath should never start with a /, example : test.xsl
     * @return the File instance pointing to the obsolute path of the subSamplePath
     * @throws IOException
     * @throws URISyntaxException
     */
    public static File getFileFromCurrentPluginSampleFolder(String subSamplePath) throws IOException, URISyntaxException {
        URL url = Utilities.class.getResource(SAMPLE_FOLDER_PATH + subSamplePath);
        URI escapedURI = getFileUrl(url);
        return URIUtil.toFile(escapedURI);
    }

    /**
     * Convert any URL to a file URL
     * 
     * @param url, may be a eclipse URL
     * @return a file:/ url
     * @throws IOException
     * @throws URISyntaxException
     */
    public static URI getFileUrl(URL url) throws IOException, URISyntaxException {
        // bundleresource://875.fwk22949069:4/top400/
        URL unescapedURL = FileLocator.toFileURL(url);// bug 145096
        // file:/E:sv n/org.talend.model.migration.test/samples/top400/
        URI escapedURI = new URI(unescapedURL.getProtocol(), unescapedURL.getPath(), unescapedURL.getQuery());
        // file:/E:sv%20n/org.talend.model.migration.test/samples/top400/
        return escapedURI;
    }
}
