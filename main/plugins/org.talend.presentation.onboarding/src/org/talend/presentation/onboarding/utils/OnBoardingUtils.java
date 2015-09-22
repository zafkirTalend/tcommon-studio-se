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
package org.talend.presentation.onboarding.utils;

import java.net.URL;

import org.apache.log4j.Priority;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;
import org.talend.presentation.onboarding.exceptions.OnBoardingExceptionHandler;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * created by cmeng on Sep 15, 2015 Detailled comment
 *
 */
public class OnBoardingUtils {

    private static Bundle bundle = null;

    public static Bundle getBundle() {
        if (bundle == null) {
            bundle = Platform.getBundle(OnBoardingConstants.PLUGIN_ID);
        }
        return bundle;
    }

    public static URL getResourceLocalURL(Bundle iBoundle, String path) {
        try {
            URL resourceURL = iBoundle.getEntry(path);
            return FileLocator.toFileURL(resourceURL);
        } catch (Throwable e) {
            OnBoardingExceptionHandler.process(e);
            return null;
        }
    }

    public static URL getResourceLocalURL(String path) {
        return getResourceLocalURL(getBundle(), path);
    }

    public static void setVisible(boolean visible, final Shell shell, final int totalAnimateTime, final int sleepTime,
            final int initialAlpha, final int finalAlphaValue) {
        final int executeTimes = totalAnimateTime / sleepTime;
        // seems have bugs maybe related to multithread, need to check it in some day, currently always set it enable
        // after finished the execution
        // final boolean originalEnable = shell.getEnabled();
        shell.setEnabled(false);
        if (visible) {
            final int alpha = initialAlpha;
            shell.setAlpha(alpha);
            final int increment = (finalAlphaValue - alpha) / executeTimes + 1;
            shell.setVisible(visible);
            new Thread(new Runnable() {

                @Override
                public void run() {
                    final ObjectBox<Integer> alphaBox = new ObjectBox<Integer>();
                    final ObjectBox<Boolean> isContinue = new ObjectBox<Boolean>();
                    alphaBox.value = alpha;
                    isContinue.value = true;
                    for (int i = 1; i < executeTimes - 1; i++) {
                        alphaBox.value += increment;
                        Display.getDefault().syncExec(new Runnable() {

                            @Override
                            public void run() {
                                if (!isContinueExecute(shell)) {
                                    isContinue.value = false;
                                    return;
                                }
                                shell.setAlpha(alphaBox.value);
                            }
                        });
                        if (!isContinue.value) {
                            break;
                        }
                        try {
                            Thread.sleep(sleepTime);
                        } catch (InterruptedException e) {
                            OnBoardingExceptionHandler.process(e, Priority.WARN);
                            break;
                        }
                    }
                    Display.getDefault().syncExec(new Runnable() {

                        @Override
                        public void run() {
                            if (!shell.isDisposed()) {
                                shell.setAlpha(finalAlphaValue);
                                // shell.setEnabled(originalEnable);
                                shell.setEnabled(true);
                            }
                        }
                    });
                }
            }).start();
        } else {
            int alpha = shell.getAlpha();
            int increment = (alpha - initialAlpha) / executeTimes + 1;
            for (int i = 1; i < executeTimes - 1; i++) {
                alpha -= increment;
                shell.setAlpha(alpha);
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    OnBoardingExceptionHandler.process(e, Priority.WARN);
                    break;
                }
            }
            shell.setAlpha(initialAlpha);

            // shell.setEnabled(originalEnable);
            shell.setEnabled(true);
            shell.setVisible(visible);
        }
    }

    public static boolean isContinueExecute(Shell shell) {
        if (shell.isDisposed() || !shell.isVisible()) {
            return false;
        } else {
            return true;
        }
    }

    public static Color getColor(RGB rgb) {
        ColorRegistry colorRegistry = JFaceResources.getColorRegistry();
        String symbol = rgb.toString();
        Color color = colorRegistry.get(symbol);
        if (color == null) {
            colorRegistry.put(symbol, rgb);
        }
        return colorRegistry.get(symbol);
    }

    /**
     * Returns an array version of the passed NodeList. Used to work around DOM design issues.
     */
    public static Node[] getArray(NodeList nodeList) {
        Node[] nodes = new Node[nodeList.getLength()];
        for (int i = 0; i < nodeList.getLength(); i++) {
            nodes[i] = nodeList.item(i);
        }
        return nodes;
    }

    public static boolean isSupportBrowser() {
        final ObjectBox<Boolean> isSupportBrowser = new ObjectBox<Boolean>();
        isSupportBrowser.value = true;
        Display.getDefault().syncExec(new Runnable() {

            @Override
            public void run() {
                Shell shell = new Shell();
                Browser browser = null;
                try {
                    shell.setVisible(false);
                    browser = new Browser(shell, SWT.NONE);
                } catch (Throwable e) {
                    isSupportBrowser.value = false;
                } finally {
                    try {
                        if (browser != null) {
                            browser.dispose();
                        }
                        if (shell != null) {
                            shell.dispose();
                        }
                    } catch (Throwable e) {
                        // nothing need to do
                    }
                }
            }
        });
        return isSupportBrowser.value;
    }

    public static String getCurrentSelectedPerspectiveId(IWorkbenchWindow wbWindow) {
        String perspId = null;
        if (wbWindow == null || !PlatformUI.isWorkbenchRunning()) {
            return perspId;
        }
        IWorkbenchPage workbenchPage = wbWindow.getActivePage();
        if (workbenchPage == null) {
            return perspId;
        }
        IPerspectiveDescriptor perspDesc = workbenchPage.getPerspective();
        if (perspDesc != null) {
            perspId = perspDesc.getId();
        }
        return perspId;
    }
}
