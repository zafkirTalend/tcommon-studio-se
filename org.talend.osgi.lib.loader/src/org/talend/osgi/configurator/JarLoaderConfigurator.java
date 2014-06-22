// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.osgi.configurator;

import org.eclipse.osgi.baseadaptor.HookConfigurator;
import org.eclipse.osgi.baseadaptor.HookRegistry;
import org.talend.osgi.hook.JarLoaderClassLoadingHook;
import org.talend.osgi.hook.TalendHookAdaptor;
import org.talend.osgi.hook.notification.JarMissingObservable;

/**
 * configure the equinox hook that Talend uses to derive the jar missing in the bundle to load them in another folder.
 * created by sgandon on 9 sept. 2013 Detailled comment
 * 
 */
public class JarLoaderConfigurator implements HookConfigurator {

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.osgi.baseadaptor.HookConfigurator#addHooks(org.eclipse.osgi.baseadaptor.HookRegistry)
     */
    @Override
    public void addHooks(HookRegistry hookRegistry) {
        JarMissingObservable jarMissingObservable = new JarMissingObservable();
        hookRegistry.addAdaptorHook(new TalendHookAdaptor(jarMissingObservable));
        hookRegistry.addClassLoadingHook(new JarLoaderClassLoadingHook(jarMissingObservable));

    }

}
