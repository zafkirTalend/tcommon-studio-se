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
package org.talend.utils.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * DOC stephane class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40Z nrousseau $
 * 
 */
public final class FileUtils {

    private FileUtils() {
    }
    
    public static synchronized void replaceInFile(String path, String oldString, String newString) throws IOException,
            URISyntaxException {
        File file = new File(path);
        File tmpFile = new File(path + ".tmp");

        BufferedReader in = new BufferedReader(new FileReader(file));

        FileWriter out = new FileWriter(tmpFile);

        String line;
        String newLine;
        while ((line = in.readLine()) != null) {
            newLine = line.replace(oldString, newString);
            out.write(newLine + "\n");
        }

        out.close();
        in.close();

        file.delete();
        tmpFile.renameTo(file);
    }

}
