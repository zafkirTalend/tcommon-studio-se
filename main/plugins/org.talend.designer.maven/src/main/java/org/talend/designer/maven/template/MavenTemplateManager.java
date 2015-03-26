// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.maven.template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.core.resources.IFile;
import org.talend.designer.maven.DesignerMavenPlugin;

/**
 * created by ggu on 5 Feb 2015 Detailled comment
 *
 */
public class MavenTemplateManager {

    public static InputStream getTemplate(String templateName) throws IOException {
        URL routinesTemplateUrl = DesignerMavenPlugin.getPlugin().getContext().getBundle()
                .getEntry(MavenTemplateConstants.RESOURCES_TEMPLATE_PATH + '/' + templateName);
        if (routinesTemplateUrl != null) {
            InputStream inputStream = routinesTemplateUrl.openStream();
            return inputStream;
        }
        return null;
    }

    public static void copyTemplate(String templateName, IFile targetFile, boolean overwrite) throws IOException {
        copyTemplate(templateName, targetFile.getLocation().toFile(), overwrite);
    }

    public static void copyTemplate(String templateName, File targetFile, boolean overwrite) throws IOException {
        if (targetFile.exists()) {
            if (!overwrite) {
                // throw new IOException("The file have existed, must delete or with overwrite option.");
                return; // nothing to do, keep it.
            }
            if (!targetFile.isFile()) {
                throw new IOException("Can't write the template to directory, must be file.");
            }
        }

        targetFile.getParentFile().mkdirs();

        URL routinesTemplateUrl = DesignerMavenPlugin.getPlugin().getContext().getBundle()
                .getEntry(MavenTemplateConstants.RESOURCES_TEMPLATE_PATH + '/' + templateName);
        if (routinesTemplateUrl != null) {
            InputStream inputStream = routinesTemplateUrl.openStream();
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(targetFile);
                byte[] buffer = new byte[1024];
                int byteread = 0;
                while ((byteread = inputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, byteread);
                }
                out.flush();
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        }
    }
}
