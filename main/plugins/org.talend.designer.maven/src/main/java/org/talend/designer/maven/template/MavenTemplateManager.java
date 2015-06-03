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
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Map;

import org.eclipse.core.resources.IFile;
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
     * 1. get the template from the file template under the folder first.
     * 
     * 2. if file template is not existed, try to get th template from project setting.
     * 
     * 3. if the project setting is not set still, try to get the template from the bundle template.
     * 
     */
    @SuppressWarnings("resource")
    public static InputStream getTemplateStream(File templateFile, String projectSettingKey, String bundleName,
            String bundleTemplatePath) throws Exception {
        InputStream stream = null;
        // 1. from file template dirctly.
        if (templateFile != null && templateFile.exists()) {
            // will close it later.
            stream = new BufferedInputStream(new FileInputStream(templateFile));
        }

        // 2. from project setting
        if (stream == null && projectSettingKey != null) {
            stream = getProjectSettingStream(projectSettingKey);
        }
        // 3. from bundle template.
        if (stream == null && bundleName != null && bundleTemplatePath != null) {
            stream = getBundleTemplateStream(bundleName, bundleTemplatePath);
        }
        return stream;
    }

    public static String getTemplateContent(File templateFile, String projectSettingKey, String bundleName,
            String bundleTemplatePath) throws Exception {
        return getContentFromInputStream(getTemplateStream(templateFile, projectSettingKey, bundleName, bundleTemplatePath));
    }

    /**
     * 
     * get the template file stream from bundle.
     */
    public static InputStream getBundleTemplateStream(String bundleName, String bundleTemplatePath) throws Exception {
        if (bundleName == null || bundleTemplatePath == null) {
            return null;
        }
        Map<String, AbstractMavenTemplateManager> templateManagerMap = MavenTemplateManager.getTemplateManagerMap();
        AbstractMavenTemplateManager templateManager = templateManagerMap.get(bundleName);
        if (templateManager != null) {
            return templateManager.readBundleStream(bundleTemplatePath);
        }
        return null;
    }

    public static String getBundleTemplateContent(String bundleName, String templatePath) throws Exception {
        return getContentFromInputStream(getBundleTemplateStream(bundleName, templatePath));
    }

    /**
     * try to find the template setting in project setting one by one.
     */
    public static String getProjectSettingValue(String key) {
        Map<String, AbstractMavenTemplateManager> templateManagerMap = MavenTemplateManager.getTemplateManagerMap();

        for (String bundleName : templateManagerMap.keySet()) {
            AbstractMavenTemplateManager templateManager = templateManagerMap.get(bundleName);
            try {
                InputStream steam = templateManager.readProjectSettingStream(key);
                if (steam != null) {
                    String content = MavenTemplateManager.getContentFromInputStream(steam);
                    if (content != null) {
                        return content;
                    }
                }
            } catch (Exception e) {
                // try to find another one
            }
        }
        return null;
    }

    /**
     * try to find the template setting in project setting one by one.
     */
    public static InputStream getProjectSettingStream(String key) {
        Map<String, AbstractMavenTemplateManager> templateManagerMap = MavenTemplateManager.getTemplateManagerMap();

        for (String bundleName : templateManagerMap.keySet()) {
            AbstractMavenTemplateManager templateManager = templateManagerMap.get(bundleName);
            try {
                InputStream steam = templateManager.readProjectSettingStream(key);
                if (steam != null) {
                    return steam;
                }
            } catch (Exception e) {
                // try to find another one
            }
        }
        return null;
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

    public static void saveContent(IFile targetFile, String content, boolean overwrite) throws IOException {
        saveContent(targetFile.getLocation().toFile(), content, overwrite);
    }

    public static void saveContent(File targetFile, String content, boolean overwrite) throws IOException {
        if (targetFile.exists()) {
            if (!overwrite) {
                // throw new IOException("The file have existed, must delete or with overwrite option.");
                return; // nothing to do, keep it.
            }
            if (!targetFile.isFile()) {
                throw new IOException("Can't write the template to directory, must be file.");
            }
        }

        if (content != null) {
            FileWriter writer = null;
            try {
                writer = new FileWriter(targetFile);
                writer.write(content);
                writer.flush();
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
        }
    }

}
