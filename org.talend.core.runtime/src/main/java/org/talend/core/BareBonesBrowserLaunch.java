// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import org.talend.commons.exception.ExceptionHandler;

/**
 * DOC Administrator class global comment. Detailled comment
 */
public class BareBonesBrowserLaunch {

    static final String[] browsers = { "google-chrome", "firefox", "opera", "epiphany", "konqueror", "conkeror", "midori",
            "kazehakase", "mozilla" };

    static final String errMsg = "Error attempting to launch web browser";

    public static void openURL(String url) {
        Class<?> name;
        try {
            name = Class.forName("java.awt.Desktop");
            name.getDeclaredMethod("browse", new Class[] { java.net.URI.class }).invoke(
                    name.getDeclaredMethod("getDesktop").invoke(null), new Object[] { java.net.URI.create(url) });
        } catch (Exception ignore) {

            String osName = System.getProperty("os.name");
            if (osName.startsWith("Mac OS")) {
                try {
                    Class.forName("com.apple.eio.FileManager").getDeclaredMethod("openURL", new Class[] { String.class }).invoke(
                            null, new Object[] { url });
                } catch (IllegalArgumentException e1) {
                    ExceptionHandler.process(e1);
                } catch (SecurityException e2) {
                    ExceptionHandler.process(e2);
                } catch (IllegalAccessException e3) {
                    ExceptionHandler.process(e3);
                } catch (InvocationTargetException e4) {
                    ExceptionHandler.process(e4);
                } catch (NoSuchMethodException e5) {
                    ExceptionHandler.process(e5);
                } catch (ClassNotFoundException e6) {
                    ExceptionHandler.process(e6);
                }
            } else if (osName.startsWith("Windows")) {
                try {
                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
                } catch (IOException e) {
                    ExceptionHandler.process(e);
                }
            } else {
                String browser = null;
                for (String b : browsers) {
                    try {
                        if (browser == null
                                && Runtime.getRuntime().exec(new String[] { "which", b }).getInputStream().read() != -1) {
                            Runtime.getRuntime().exec(new String[] { browser = b, url });
                        }
                    } catch (IOException e) {
                        ExceptionHandler.process(e);
                    }
                    if (browser == null) {
                        try {
                            throw new Exception(Arrays.toString(browsers));
                        } catch (Exception e) {
                            ExceptionHandler.process(e);
                        }
                    }
                }
            }
        }
    }

}
