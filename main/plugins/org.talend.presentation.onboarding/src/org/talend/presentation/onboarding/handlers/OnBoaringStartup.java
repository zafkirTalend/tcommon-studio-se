package org.talend.presentation.onboarding.handlers;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.PlatformUI;
import org.talend.presentation.onboarding.ui.managers.OnBoardingManager;
import org.talend.presentation.onboarding.ui.managers.OnBoardingResourceManager;
import org.talend.presentation.onboarding.utils.OnBoardingConstants;
import org.talend.presentation.onboarding.utils.OnBoardingUtils;

public class OnBoaringStartup implements IStartup {

    @Override
    public void earlyStartup() {
        showOnboardingPresentationIfNeeded();
    }

    private void showOnboardingPresentationIfNeeded() {
        if (!OnBoardingUtils.isSupportBrowser()) {
            return;
        }
        boolean notShowOnBoarding = PlatformUI.getPreferenceStore().getBoolean(
                OnBoardingConstants.PREFERENCE_NOT_SHOW_ONBOARDING_AT_STARTUP);
        if (!notShowOnBoarding) {
            if (OnBoardingResourceManager.getDefaultResourceManager() != null) {
                Display.getDefault().asyncExec(new Runnable() {

                    @Override
                    public void run() {
                        OnBoardingManager obManager = OnBoardingManager.getDefaultOnBoardingManager();
                        if (obManager != null) {
                            obManager.onBoarding(0);
                        }
                    }
                });
            }
            PlatformUI.getPreferenceStore().setValue(OnBoardingConstants.PREFERENCE_NOT_SHOW_ONBOARDING_AT_STARTUP, true);
        }
    }
}
