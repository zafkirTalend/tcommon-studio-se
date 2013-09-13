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
package org.talend.osgi.hook;

import java.io.IOException;
import java.net.URLConnection;
import java.util.Properties;

import org.eclipse.osgi.baseadaptor.BaseAdaptor;
import org.eclipse.osgi.baseadaptor.hooks.AdaptorHook;
import org.eclipse.osgi.framework.log.FrameworkLog;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.talend.osgi.hook.notification.JarMissingObservable;

/**
 * This adaptor hook register the JarMissingObservable as an OSGI service. created by sgandon on 12 sept. 2013
 * 
 */
public class TalendHookAdaptor implements AdaptorHook {

    private final JarMissingObservable jarMissingObservable;

    public TalendHookAdaptor(JarMissingObservable jarMissingObservable) {
        this.jarMissingObservable = jarMissingObservable;

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.osgi.baseadaptor.hooks.AdaptorHook#initialize(org.eclipse.osgi.baseadaptor.BaseAdaptor)
     */
    @Override
    public void initialize(BaseAdaptor adaptor) {
        // nothing done here
    }

    /*
     * register the jarMissingObservable as an OSGI service
     * 
     * @see org.eclipse.osgi.baseadaptor.hooks.AdaptorHook#frameworkStart(org.osgi.framework.BundleContext)
     */
    @Override
    public void frameworkStart(BundleContext context) throws BundleException {
        context.registerService(JarMissingObservable.class.getCanonicalName(), jarMissingObservable, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.osgi.baseadaptor.hooks.AdaptorHook#frameworkStop(org.osgi.framework.BundleContext)
     */
    @Override
    public void frameworkStop(BundleContext context) throws BundleException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.osgi.baseadaptor.hooks.AdaptorHook#frameworkStopping(org.osgi.framework.BundleContext)
     */
    @Override
    public void frameworkStopping(BundleContext context) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.osgi.baseadaptor.hooks.AdaptorHook#addProperties(java.util.Properties)
     */
    @Override
    public void addProperties(Properties properties) {

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.osgi.baseadaptor.hooks.AdaptorHook#mapLocationToURLConnection(java.lang.String)
     */
    @Override
    public URLConnection mapLocationToURLConnection(String location) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.osgi.baseadaptor.hooks.AdaptorHook#handleRuntimeError(java.lang.Throwable)
     */
    @Override
    public void handleRuntimeError(Throwable error) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.osgi.baseadaptor.hooks.AdaptorHook#createFrameworkLog()
     */
    @Override
    public FrameworkLog createFrameworkLog() {
        // TODO Auto-generated method stub
        return null;
    }

}
