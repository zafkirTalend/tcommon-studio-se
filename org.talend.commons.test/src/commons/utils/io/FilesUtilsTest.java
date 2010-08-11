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
package commons.utils.io;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.talend.commons.utils.io.FilesUtils;
import org.talend.resource.ResourceManager;


/**
 * DOC xqliu  class global comment. Detailled comment
 */
public class FilesUtilsTest {

    /**
     * Test method for {@link org.talend.commons.utils.io.FilesUtils#migrateFolder(java.io.File, java.lang.String[], java.util.Map, org.apache.log4j.Logger)}.
     */
    @Test
    public void testMigrateFolder() {
        File fileMdmConnection = new File(ResourceManager.getMDMConnectionFolder().getRawLocationURI());

        String[] mdmFileExtentionNames = { ".prv" };

        Map<String, String> replaceStringMap = new HashMap<String, String>();
        replaceStringMap.put("TdXMLDocument", "TdXmlSchema");
        replaceStringMap.put("TdXMLElement", "TdXmlElementType");

        Logger log = Logger.getLogger(FilesUtils.class);

        FilesUtils.migrateFolder(fileMdmConnection, mdmFileExtentionNames, replaceStringMap, log);
    }

}
