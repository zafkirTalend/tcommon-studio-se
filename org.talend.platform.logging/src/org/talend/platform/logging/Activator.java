package org.talend.platform.logging;

import java.util.Dictionary;

import org.apache.log4j.Logger;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.talend.utils.format.PresentableBox;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

    private static Logger log = Logger.getLogger(Activator.class);

    // The plug-in ID
    public static final String PLUGIN_ID = "org.talend.platform.logging"; //$NON-NLS-1$

    // The shared instance
    private static Activator plugin;

    /**
     * The constructor
     */
    public Activator() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    @SuppressWarnings("unchecked")
    public void start(BundleContext context) throws Exception {
        super.start(context);
        if (log.isInfoEnabled()) {
            Object version = null;
            Bundle b = this.getBundle();
            if (b != null) {
                Dictionary headers = b.getHeaders(null);
                if (headers != null) {
                    version = headers.get(Constants.BUNDLE_VERSION);
                }
            }
            String mess = "Starting Talend's platform log system."; //$NON-NLS-1$
            if (version != null) {
                mess += ("VERSION= " + version); //$NON-NLS-1$
            }
            PresentableBox box = new PresentableBox("TALEND", mess, 0); //$NON-NLS-1$
            log.info(box.getFullBox());
        }
        plugin = this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance
     * 
     * @return the shared instance
     */
    public static Activator getDefault() {
        return plugin;
    }

}
