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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.runtime.services.IMavenUIService;
import org.talend.designer.maven.DesignerMavenPlugin;
import org.talend.designer.maven.setting.project.IProjectSettingManagerProvider;

/**
 * created by ggu on 5 Feb 2015 Detailled comment
 *
 */
public class MavenTemplateManager {

    public static Map<String, AbstractMavenTemplateManager> getTemplateManagerMap() {
        return MavenTemplateManagerRegistry.getInstance().getTemplateManagerMap();
    }

    public static Map<String, IProjectSettingManagerProvider> getProjectSettingManagerMap() {
        return MavenTemplateManagerRegistry.getInstance().getProjectSettingManagerMap();
    }

    /**
     * read the template from bundle resource.
     * 
     * @deprecated use AbstractMavenTemplateManager instead
     */
    public static InputStream getBundleTemplateStream(String templateName) throws IOException {
        if (templateName != null) {
            URL templateUrl = DesignerMavenPlugin.getPlugin().getContext().getBundle()
                    .getEntry(MavenTemplateConstants.PATH_RESOURCES_TEMPLATES + '/' + templateName);
            if (templateUrl != null) {
                InputStream inputStream = templateUrl.openStream();
                return inputStream;
            }
        }
        return null;
    }

    /**
     * @deprecated use AbstractMavenTemplateManager instead
     */
    public static String getBundleTemplateContent(String templateName) throws IOException {
        return getContentFromInputStream(getBundleTemplateStream(templateName));
    }

    public static String getContentFromInputStream(InputStream is) throws IOException {
        if (is != null) {
            try {
                StringWriter sw = new StringWriter(1000);
                int c = 0;
                while ((c = is.read()) != -1) {
                    sw.write(c);
                }
                return sw.toString();
            } finally {
                is.close();
            }
        }
        return null;
    }

    /**
     * read the template from project setting preference.
     * 
     * @deprecated use AbstractMavenTemplateManager instead
     */
    public static InputStream getProjectSettingTemplateStream(String templateKey) throws IOException {
        if (templateKey != null && GlobalServiceRegister.getDefault().isServiceRegistered(IMavenUIService.class)) {
            IMavenUIService mavenUiService = (IMavenUIService) GlobalServiceRegister.getDefault().getService(
                    IMavenUIService.class);
            return mavenUiService.getProjectSettingStream(templateKey);
        }
        return null;
    }

    /**
     * 
     * @deprecated use AbstractMavenTemplateManager instead
     */
    public static String getProjectSettingTemplateContent(String templateKey) throws IOException {
        return getContentFromInputStream(getProjectSettingTemplateStream(templateKey));
    }

    /**
     * 1. get the template from the file template under the folder first.
     * 
     * 2. if file template is not existed, try to get th template from project setting.
     * 
     * 3. if the project setting is not set still, try to get the template from the bundle template.
     * 
     * @deprecated use AbstractMavenTemplateManager instead
     */
    @SuppressWarnings("resource")
    public static InputStream getTemplateStream(File templateFile, String projectSettingKey, String templateName)
            throws IOException {
        InputStream stream = null;
        // 1. from file template dirctly.
        if (templateFile != null && templateFile.exists()) {
            // will close it later.
            stream = new BufferedInputStream(new FileInputStream(templateFile));
        }

        // 2. from project setting
        if (stream == null && projectSettingKey != null) {
            stream = getProjectSettingTemplateStream(projectSettingKey);
        }
        // 3. from bundle template.
        if (stream == null && templateName != null) {
            stream = getBundleTemplateStream(templateName);
        }
        return stream;
    }

    /**
     * @deprecated use AbstractMavenTemplateManager instead
     */
    public static String getTemplateContent(File templateFile, String projectSettingKey, String templateName) throws IOException {
        return getContentFromInputStream(getTemplateStream(templateFile, projectSettingKey, templateName));
    }

    /**
     * @deprecated use AbstractMavenTemplateManager instead
     */
    public static void copyTemplate(String templateName, IFile targetFile, boolean overwrite) throws IOException {
        copyTemplate(templateName, targetFile.getLocation().toFile(), overwrite);
    }

    /**
     * @deprecated use AbstractMavenTemplateManager instead
     */
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
                .getEntry(MavenTemplateConstants.PATH_RESOURCES_TEMPLATES + '/' + templateName);
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
