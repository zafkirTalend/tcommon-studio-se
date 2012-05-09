package org.talend.rcp;

import org.eclipse.ui.internal.splash.EclipseSplashHandler;

/**
 * this class is justmade so that we can get the handler instance (no other way to get the instance). this instance is
 * used by the RCP WorkbenchAdvisor.preStartup to execute so login task and report some progress on the splash screen.
 * */
public class TalendSplashHandler extends EclipseSplashHandler {

    public static TalendSplashHandler INSTANCE;

    /**
     * DOC sgandon TalendSplashHandler constructor comment.
     */
    public TalendSplashHandler() {
        if (INSTANCE == null) {
            INSTANCE = this;
        }
    }
}
