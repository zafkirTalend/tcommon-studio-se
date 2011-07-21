// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.commons.utils.io;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.talend.commons.utils.io.FilesUtils;

/**
 * DOC xqliu class global comment. Detailled comment
 */
public class FilesUtilsTest {

    /**
     * Test method for
     * {@link org.talend.commons.utils.io.FilesUtils#migrateFolder(java.io.File, java.lang.String[], java.util.Map, org.apache.log4j.Logger)}
     * .
     */
    @Test
    public void testMigrateFolder() {
        File fileMdmConnection = new File(
                "/Talend/Talend_All_trunk/runtime/TOP_runtime/TOP_DEFAULT_PRJ/TDQ_Metadata/MDM Connections");

        String[] mdmFileExtentionNames = { ".prv" };

        Map<String, String> replaceStringMap = new HashMap<String, String>();
        replaceStringMap.put("TdXMLDocument", "TdXmlSchema");
        replaceStringMap.put("TdXMLElement", "TdXmlElementType");

        Logger log = Logger.getLogger(FilesUtils.class);

        FilesUtils.migrateFolder(fileMdmConnection, mdmFileExtentionNames, replaceStringMap, log);
    }

    /**
     * Test method for
     * {@link org.talend.commons.utils.io.FilesUtils#migrateFolder(java.io.File, java.io.File, boolean, java.io.FileFilter,java.io.FileFilter, boolean, boolean, org.eclipse.core.runtime.IProgressMonitor)}
     * .
     * 
     * @throws IOException
     */
    @Test
    public void testCopyFolder() throws IOException {
        File source = new File("");
        File target = new File("");
        FilesUtils.copyFolder(source, target, false, null, null, true, null);
        assertTrue(!target.exists());
    }

}
