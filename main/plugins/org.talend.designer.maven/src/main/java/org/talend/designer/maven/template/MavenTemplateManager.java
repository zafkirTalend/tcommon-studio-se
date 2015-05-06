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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;

import org.eclipse.core.resources.IFile;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.runtime.services.IDesignerMavenUIService;
import org.talend.designer.maven.DesignerMavenPlugin;

/**
 * created by ggu on 5 Feb 2015 Detailled comment
 *
 */
public class MavenTemplateManager {

    /**
     * read the template from bundle resource.
     */
    public static InputStream getTemplate(String templateName) throws IOException {
        URL templateUrl = DesignerMavenPlugin.getPlugin().getContext().getBundle()
                .getEntry(MavenTemplateConstants.RESOURCES_TEMPLATE_PATH + '/' + templateName);
        if (templateUrl != null) {
            InputStream inputStream = templateUrl.openStream();
            return inputStream;
        }
        return null;
    }

    public static String getTemplateContent(String templateName) throws IOException {
        return getContentFromInputStream(getTemplate(templateName));
    }

    private static String getContentFromInputStream(InputStream is) throws IOException {
        if (is != null) {
            StringWriter sw = new StringWriter(1000);
            int c = 0;
            while ((c = is.read()) != -1) {
                sw.write(c);
            }
            return sw.toString();
        }
        return null;
    }

    /**
     * read the template from project setting preference.
     */
    public static InputStream getProjectTemplate(String templateKey) throws IOException {
        if (GlobalServiceRegister.getDefault().isServiceRegistered(IDesignerMavenUIService.class)) {
            IDesignerMavenUIService mavenUiService = (IDesignerMavenUIService) GlobalServiceRegister.getDefault().getService(
                    IDesignerMavenUIService.class);
            try {
                String jobTemplateFromProjectSettingContents = mavenUiService.getProjectSettingPreferenceValue(templateKey);
                if (jobTemplateFromProjectSettingContents != null && jobTemplateFromProjectSettingContents.length() > 0) {
                    InputStream is = new ByteArrayInputStream(jobTemplateFromProjectSettingContents.getBytes("UTF-8"));
                    return is;
                }
            } catch (IOException e) {
                ExceptionHandler.process(e);
            }
        }
        return null;
    }

    public static String getProjectTemplateContent(String templateKey) throws IOException {
        return getContentFromInputStream(getProjectTemplate(templateKey));
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
