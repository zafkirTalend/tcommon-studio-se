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
package org.talend.utils.properties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * DOC stephane class global comment. Detailled comment
 */
public class PropertiesReloader {

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

        System.out.println("Delete ok=" + file.delete());
        System.out.println("Rename ok=" + tmpFile.renameTo(file));
    }

    public static synchronized void changeProperties(String fileName, String key, String oldValue, String newValue)
            throws IOException, URISyntaxException {
        replaceInFile(fileName, key + "=" + oldValue, key + "=" + newValue);
    }

    public static synchronized void setProperties(Class<?> clazz, String propertiesFilename, String key, String oldValue,
            String newValue) throws IOException, URISyntaxException {
        URL resource = clazz.getClassLoader().getResource(propertiesFilename);
        changeProperties(resource.toURI().getPath(), key, oldValue, newValue);
    }

    public static void main(String[] args) {
        String key = "database.driver";
        String newValue = "tagada";
        String oldValue = "org.gjt.mm.mysql.Driver";

        try {
            new PropertiesReloader()
                    .changeProperties(
                            "C:/Program Files/tomcat/apache-tomcat-5.5.26/apache-tomcat-5.5.26/webapps/org.talend.administrator/WEB-INF/classes/database.properties",
                            key, oldValue, newValue);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
