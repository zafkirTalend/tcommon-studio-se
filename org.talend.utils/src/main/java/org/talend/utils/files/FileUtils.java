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
import java.util.ArrayList;
import java.util.List;

import org.talend.utils.string.StringUtilities;
import org.talend.utils.sugars.ReturnCode;

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

    /**
     * Method "checkBracketsInFile" checks whether the parentheses are well balanced on each line of the given file.
     * 
     * @param path the path of the file to check
     * @return true when all lines contain well balanced parentheses.
     * @throws IOException
     * @throws URISyntaxException
     */
    public static synchronized List<ReturnCode> checkBracketsInFile(String path) throws IOException,
            URISyntaxException {
        List<ReturnCode> returncodes = new ArrayList<ReturnCode>();
        File file = new File(path);
        BufferedReader in = new BufferedReader(new FileReader(file));

        String line;
        int lineNb = 0;

        while ((line = in.readLine()) != null) {
            ReturnCode checkBlocks = StringUtilities.checkBalancedParenthesis(line, '(', ')');
            lineNb++;
            if (!checkBlocks.isOk()) {
                String errorMsg = "Line " + lineNb + ": " + checkBlocks.getMessage();
                returncodes.add(new ReturnCode(errorMsg, false));
            }
        }

        in.close();
        return returncodes;
    }

}
