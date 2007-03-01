// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.core.prefs;

import java.io.File;
import java.util.Locale;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.adaptor.EclipseStarter;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.osgi.framework.internal.core.FrameworkProperties;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.eclipse.update.core.SiteManager;
import org.eclipse.update.internal.scheduler.SchedulerStartup;
import org.talend.commons.ui.swt.colorstyledtext.ColorManager;
import org.talend.core.CorePlugin;

/**
 * Intializer of core preferences. <br/>
 * 
 * $Id$
 * 
 */
public class CorePreferenceInitializer extends AbstractPreferenceInitializer {

    /**
     * 
     */
    private static final String PERL_EMBEDDED_INTERPRETER_DIRECTORY = "perl/bin/perl.exe"; //$NON-NLS-1$

    private static final String JAVA_LINUX_INTERPRETER_PATH = "/java"; // NON-NLS-1$ //$NON-NLS-1$

    private static final String JAVA_WIN32_INTERPRETER = "\\java.exe"; // NON-NLS-1$ //$NON-NLS-1$

    private static final String PERL_LINUX_INTERPRETER_PATH = "/usr/bin/perl"; // NON-NLS-1$ //$NON-NLS-1$

    private static final String PERL_WIN32_INTERPRETER_PATH = "C:\\Perl\\bin\\perl.exe"; // NON-NLS-1$ //$NON-NLS-1$

    /**
     * Construct a new CorePreferenceInitializer.
     */
    public CorePreferenceInitializer() {
        super();
    }

    /**
     * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
     */
    @Override
    public void initializeDefaultPreferences() {

        IEclipsePreferences node = new DefaultScope().getNode(CorePlugin.getDefault().getBundle().getSymbolicName());

        // Building temporary files directory path
        IPath tempPath = new Path(System.getProperty("user.dir")).append("temp"); // NON-NLS-1$// NON-NLS-2$
        // //$NON-NLS-1$ //$NON-NLS-2$
        File tempFile = tempPath.toFile();
        if (!tempFile.exists()) {
            tempFile.mkdirs();
        }
        node.put(ITalendCorePrefConstants.FILE_PATH_TEMP, tempPath.toOSString());

        // TODO CCA : Change this default value
        String os = Platform.getOS();
        String javaPath = System.getProperty("sun.boot.library.path"); // NON-NLS-1$ //$NON-NLS-1$
        if (os.equals(Platform.OS_WIN32)) {
            node.put(ITalendCorePrefConstants.PERL_INTERPRETER, Platform.getInstallLocation().getURL().getFile().substring(1)
                    + PERL_EMBEDDED_INTERPRETER_DIRECTORY);
            node.put(ITalendCorePrefConstants.PERL_SECONDARY_INTERPRETER, PERL_LINUX_INTERPRETER_PATH);
            node.put(ITalendCorePrefConstants.JAVA_INTERPRETER, javaPath + JAVA_WIN32_INTERPRETER);
        } else if (os.equals(Platform.OS_LINUX)) {
            node.put(ITalendCorePrefConstants.PERL_INTERPRETER, PERL_LINUX_INTERPRETER_PATH);
            node.put(ITalendCorePrefConstants.PERL_SECONDARY_INTERPRETER, PERL_WIN32_INTERPRETER_PATH);
            node.put(ITalendCorePrefConstants.JAVA_INTERPRETER, javaPath + JAVA_LINUX_INTERPRETER_PATH);

        }

        // Sets default language
        node.put(ITalendCorePrefConstants.LANGUAGE_SELECTOR, Locale.getDefault().getLanguage());

        node.put(ITalendCorePrefConstants.PREVIEW_LIMIT, "50"); //$NON-NLS-1$

        initializeUpdatePreference();

        // Initialize editors properties : line number shown
        final String perlEditorBundleName = "org.epic.perleditor"; // NON-NLS-1$ //$NON-NLS-1$
        final String editorsBundleName = "org.eclipse.ui.editors"; // NON-NLS-1$ //$NON-NLS-1$
        // AbstractDecoratedTextEditorPreferenceConstants.EDITOR_LINE_NUMBER_RULER = "lineNumberRuler"
        final String editorLineNumberRuler = "lineNumberRuler"; //$NON-NLS-1$
        IPreferenceStore store = new ScopedPreferenceStore(new InstanceScope(), perlEditorBundleName);
        store.setValue(editorLineNumberRuler, true);
        store = new ScopedPreferenceStore(new InstanceScope(), editorsBundleName);
        store.setValue(editorLineNumberRuler, true);

        // default colors for the ColorStyledText.
        ColorManager.initDefaultColors(CorePlugin.getDefault().getPreferenceStore());

        CorePlugin.getDefault().getPreferenceStore().setValue(ITalendCorePrefConstants.LANGUAGE_SELECTOR,
                System.getProperty(EclipseStarter.PROP_NL));
    }

    public void initializeUpdatePreference() {
        IEclipsePreferences nodeScheduler = new DefaultScope().getNode("org.eclipse.update.scheduler"); // NON-NLS-1$
        // //$NON-NLS-1$
        nodeScheduler.putBoolean(SchedulerStartup.P_ENABLED, true);
        nodeScheduler.put(SchedulerStartup.P_SCHEDULE, SchedulerStartup.VALUE_ON_STARTUP);
        nodeScheduler.putBoolean(SchedulerStartup.P_DOWNLOAD, true);
    }

    public static void setProxy(String proxyHost, String proxyPort) {
        SiteManager.setHttpProxyInfo(true, proxyHost, proxyPort);
    }

}
