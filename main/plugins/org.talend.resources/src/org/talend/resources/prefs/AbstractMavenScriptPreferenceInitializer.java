package org.talend.resources.prefs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.services.resource.IExportScriptResourcesService;

public abstract class AbstractMavenScriptPreferenceInitializer extends AbstractPreferenceInitializer {

    protected static final String EMPTY_STR = ""; //$NON-NLS-1$

    protected Map<String, String> prefSettingsMap = new LinkedHashMap<String, String>();

    @Override
    public void initializeDefaultPreferences() {
        initSettings();

        final IPreferenceStore preferenceStore = getPreferenceStore();
        for (String key : prefSettingsMap.keySet()) {
            String pomPath = prefSettingsMap.get(key);
            if (pomPath != null) {
                preferenceStore.setDefault(key, getMavenScriptTemplate(pomPath));
            }
        }

    }

    protected abstract void initSettings();

    protected abstract IPreferenceStore getPreferenceStore();

    protected abstract IExportScriptResourcesService getExportScriptService();

    protected String getMavenScriptTemplate(String pomPath) {
        IExportScriptResourcesService resourcesService = getExportScriptService();
        if (resourcesService == null) {
            return EMPTY_STR;
        }

        File templateScriptFile = new File(resourcesService.getMavenScriptFilePath(pomPath));
        if (!templateScriptFile.exists()) {
            return EMPTY_STR;
        }

        return getScriptXmlString(templateScriptFile);
    }

    protected String getScriptXmlString(File templateScriptFile) {
        if (templateScriptFile != null && templateScriptFile.exists()) {
            try {
                return new Scanner(templateScriptFile).useDelimiter("\\A").next(); //$NON-NLS-1$
            } catch (FileNotFoundException e) {
                ExceptionHandler.process(e);
            }
        }
        return EMPTY_STR;
    }
}
