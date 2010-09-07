// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.registeruser;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.update.core.SiteManager;
import org.talend.commons.exception.BusinessException;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.model.general.ConnectionBean;
import org.talend.core.prefs.PreferenceManipulator;
import org.talend.core.ui.branding.IBrandingService;
import org.talend.repository.RegistrationPlugin;
import org.talend.repository.registeruser.proxy.RegisterUserPortTypeProxy;

/**
 * DOC mhirt class global comment. Detailled comment <br/>
 * 
 * $Id: RegisterManagement.java 38235 2010-03-10 03:32:12Z nrousseau $
 * 
 */
public class RegisterManagement {

    private static final int REGISTRATION_MAX_TRIES = 6;

    // REGISTRATION_DONE = 1 : registration OK
    private static final int REGISTRATION_DONE = 2;

    private static Long registNumber = null;

    @Deprecated
    public static boolean register(String email, String country, boolean isProxyEnabled, String proxyHost, String proxyPort,
            String designerVersion, String projectLanguage, String osName, String osVersion, String javaVersion,
            long totalMemory, Long memRAM, int nbProc) throws BusinessException {
        registNumber = null;
        BigInteger result = BigInteger.valueOf(-1);

        // if proxy is enabled
        if (isProxyEnabled) {
            // get parameter and put them in System.properties.
            System.setProperty("http.proxyHost", proxyHost); //$NON-NLS-1$
            System.setProperty("http.proxyPort", proxyPort); //$NON-NLS-1$

            // override automatic update parameters
            if (proxyPort != null && proxyPort.trim().equals("")) { //$NON-NLS-1$
                proxyPort = null;
            }
            SiteManager.setHttpProxyInfo(true, proxyHost, proxyPort);
        }

        RegisterUserPortTypeProxy proxy = new RegisterUserPortTypeProxy();
        proxy.setEndpoint("http://www.talend.com/TalendRegisterWS/registerws.php"); //$NON-NLS-1$
        try {
            IBrandingService brandingService = (IBrandingService) GlobalServiceRegister.getDefault().getService(
                    IBrandingService.class);
            result = proxy.registerUserWithAllUserInformationsAndReturnId(email, country, designerVersion, brandingService
                    .getShortProductName(), projectLanguage, osName, osVersion, javaVersion, totalMemory + "", memRAM //$NON-NLS-1$
                    + "", nbProc + ""); //$NON-NLS-1$ //$NON-NLS-2$
            if (result.signum() > 0) {
                PlatformUI.getPreferenceStore().setValue("REGISTRATION_DONE", 1); //$NON-NLS-1$
                registNumber = result.longValue();
                // validateRegistration(brandingService.getAcronym(), result.longValue());
                PreferenceManipulator prefManipulator = new PreferenceManipulator();
                // prefManipulator.addUser(email);
                // prefManipulator.setLastUser(email);

                // Create a default connection:
                if (prefManipulator.readConnections().isEmpty()) {
                    ConnectionBean recup = ConnectionBean.getDefaultConnectionBean();
                    recup.setUser(email);
                    recup.setComplete(true);
                    prefManipulator.addConnection(recup);
                }

            }
        } catch (RemoteException e) {
            decrementTry();
            throw new BusinessException(e);
        }
        return result.signum() > 0;
    }

    public static boolean updateUser(String email, String pseudo, String password, String firstname, String lastname,
            String country, boolean isProxyEnabled, String proxyHost, String proxyPort) throws BusinessException {
        BigInteger result = BigInteger.valueOf(-1);
        registNumber = null;
        // if proxy is enabled
        if (isProxyEnabled) {
            // get parameter and put them in System.properties.
            System.setProperty("http.proxyHost", proxyHost); //$NON-NLS-1$
            System.setProperty("http.proxyPort", proxyPort); //$NON-NLS-1$

            // override automatic update parameters
            if (proxyPort != null && proxyPort.trim().equals("")) { //$NON-NLS-1$
                proxyPort = null;
            }
            SiteManager.setHttpProxyInfo(true, proxyHost, proxyPort);
        }

        // OS
        String osName = System.getProperty("os.name"); //$NON-NLS-1$
        String osVersion = System.getProperty("os.version"); //$NON-NLS-1$

        // Java version
        String javaVersion = System.getProperty("java.version"); //$NON-NLS-1$

        // Java Memory
        long totalMemory = Runtime.getRuntime().totalMemory();

        // RAM
        com.sun.management.OperatingSystemMXBean composantSystem = (com.sun.management.OperatingSystemMXBean) ManagementFactory
                .getOperatingSystemMXBean();
        Long memRAM = new Long(composantSystem.getTotalPhysicalMemorySize() / 1024);

        // CPU
        int nbProc = Runtime.getRuntime().availableProcessors();

        // VERSION

        String version = RegistrationPlugin.getDefault().getBundle().getHeaders()
                .get(org.osgi.framework.Constants.BUNDLE_VERSION).toString();

        RegisterUserPortTypeProxy proxy = new RegisterUserPortTypeProxy();
        proxy.setEndpoint("http://www.talend.com/TalendRegisterWS/registerws.php"); //$NON-NLS-1$
        try {
            IBrandingService brandingService = (IBrandingService) GlobalServiceRegister.getDefault().getService(
                    IBrandingService.class);
            result = proxy.updateUser(email, pseudo, password, firstname, lastname, country, version, brandingService
                    .getShortProductName(), osName, osVersion, javaVersion, totalMemory + "", memRAM //$NON-NLS-1$
                    + "", nbProc + ""); //$NON-NLS-1$ //$NON-NLS-2$
            if (result != null && result.signum() > 0) {
                PlatformUI.getPreferenceStore().setValue("REGISTRATION_DONE", 1); //$NON-NLS-1$
                registNumber = result.longValue();
                // validateRegistration(brandingService.getAcronym(), result.longValue());
                PreferenceManipulator prefManipulator = new PreferenceManipulator();
                // prefManipulator.addUser(email);
                // prefManipulator.setLastUser(email);

                // Create a default connection:
                if (prefManipulator.readConnections().isEmpty()) {
                    ConnectionBean recup = ConnectionBean.getDefaultConnectionBean();
                    recup.setUser(email);
                    recup.setComplete(true);
                    prefManipulator.addConnection(recup);
                }
            }
        } catch (RemoteException e) {
            decrementTry();
            throw new BusinessException(e);
        }
        if (result != null) {
            return result.signum() > 0;
        } else {
            return false;
        }
    }

    public static boolean createUser(String email, String pseudo, String password, String firstname, String lastname,
            String country, boolean isProxyEnabled, String proxyHost, String proxyPort) throws BusinessException {
        BigInteger result = BigInteger.valueOf(-1);
        registNumber = null;
        // if proxy is enabled
        if (isProxyEnabled) {
            // get parameter and put them in System.properties.
            System.setProperty("http.proxyHost", proxyHost); //$NON-NLS-1$
            System.setProperty("http.proxyPort", proxyPort); //$NON-NLS-1$

            // override automatic update parameters
            if (proxyPort != null && proxyPort.trim().equals("")) { //$NON-NLS-1$
                proxyPort = null;
            }
            SiteManager.setHttpProxyInfo(true, proxyHost, proxyPort);
        }

        // OS
        String osName = System.getProperty("os.name"); //$NON-NLS-1$
        String osVersion = System.getProperty("os.version"); //$NON-NLS-1$

        // Java version
        String javaVersion = System.getProperty("java.version"); //$NON-NLS-1$

        // Java Memory
        long totalMemory = Runtime.getRuntime().totalMemory();

        // RAM
        com.sun.management.OperatingSystemMXBean composantSystem = (com.sun.management.OperatingSystemMXBean) ManagementFactory
                .getOperatingSystemMXBean();
        Long memRAM = new Long(composantSystem.getTotalPhysicalMemorySize() / 1024);

        // CPU
        int nbProc = Runtime.getRuntime().availableProcessors();

        // VERSION

        String version = RegistrationPlugin.getDefault().getBundle().getHeaders()
                .get(org.osgi.framework.Constants.BUNDLE_VERSION).toString();

        RegisterUserPortTypeProxy proxy = new RegisterUserPortTypeProxy();
        proxy.setEndpoint("http://www.talend.com/TalendRegisterWS/registerws.php"); //$NON-NLS-1$
        try {
            IBrandingService brandingService = (IBrandingService) GlobalServiceRegister.getDefault().getService(
                    IBrandingService.class);
            result = proxy.createUser(email, pseudo, password, firstname, lastname, country, version, brandingService
                    .getShortProductName(), osName, osVersion, javaVersion, totalMemory + "", memRAM //$NON-NLS-1$
                    + "", nbProc + ""); //$NON-NLS-1$ //$NON-NLS-2$
            if (result.signum() > 0) {
                PlatformUI.getPreferenceStore().setValue("REGISTRATION_DONE", 1); //$NON-NLS-1$
                registNumber = result.longValue();
                // validateRegistration(brandingService.getAcronym(), result.longValue());
                PreferenceManipulator prefManipulator = new PreferenceManipulator();
                // prefManipulator.addUser(email);
                // prefManipulator.setLastUser(email);

                // Create a default connection:
                if (prefManipulator.readConnections().isEmpty()) {
                    ConnectionBean recup = ConnectionBean.getDefaultConnectionBean();
                    recup.setUser(email);
                    recup.setComplete(true);
                    prefManipulator.addConnection(recup);
                }
            }
        } catch (RemoteException e) {
            decrementTry();
            throw new BusinessException(e);
        }
        return result.signum() > 0;
    }

    public static String checkUser(String email, boolean isProxyEnabled, String proxyHost, String proxyPort)
            throws BusinessException {

        // if proxy is enabled
        if (isProxyEnabled) {
            // get parameter and put them in System.properties.
            System.setProperty("http.proxyHost", proxyHost); //$NON-NLS-1$
            System.setProperty("http.proxyPort", proxyPort); //$NON-NLS-1$

            // override automatic update parameters
            if (proxyPort != null && proxyPort.trim().equals("")) { //$NON-NLS-1$
                proxyPort = null;
            }
            SiteManager.setHttpProxyInfo(true, proxyHost, proxyPort);
        }

        RegisterUserPortTypeProxy proxy = new RegisterUserPortTypeProxy();
        proxy.setEndpoint("http://www.talend.com/TalendRegisterWS/registerws.php"); //$NON-NLS-1$
        try {
            return proxy.checkUser(email);
        } catch (RemoteException e) {
            throw new BusinessException(e);
        }
    }

    public static void validateRegistration() {
        if (registNumber == null) {
            // PTODO
            // if did not register this time.
            return;
        }
        URL registURL = null;
        try {
            IBrandingService brandingService = (IBrandingService) GlobalServiceRegister.getDefault().getService(
                    IBrandingService.class);
            registURL = new URL("http://www.talend.com/designer_post_reg.php?prd=" + brandingService.getAcronym() + "&cid="
                    + registNumber);
            PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser().openURL(registURL);
        } catch (PartInitException e) {
            // if no default browser (like on linux), try to open directly with firefox.
            try {
                Runtime.getRuntime().exec("firefox " + registURL.toString());
            } catch (IOException e2) {
                if (PlatformUI.getWorkbench().getBrowserSupport().isInternalWebBrowserAvailable()) {
                    IWebBrowser browser;
                    try {
                        browser = PlatformUI.getWorkbench().getBrowserSupport().createBrowser("registrationId");
                        browser.openURL(registURL);
                    } catch (PartInitException e1) {
                        ExceptionHandler.process(e);
                    }
                } else {
                    ExceptionHandler.process(e);
                }
            }
        } catch (MalformedURLException e) {
            ExceptionHandler.process(e);
        }
    }

    /**
     * DOC mhirt Comment method "isProductRegistered".
     * 
     * @return
     */
    public static boolean isProductRegistered() {
        initPreferenceStore();
        IPreferenceStore prefStore = PlatformUI.getPreferenceStore();
        if ((prefStore.getInt("REGISTRATION_TRIES") > 1) && ((prefStore.getInt("REGISTRATION_DONE") != 1))) { //$NON-NLS-1$ //$NON-NLS-2$
            return false;
        }
        return true;
    }

    /**
     * DOC mhirt Comment method "init".
     * 
     * @return
     */
    private static void initPreferenceStore() {
        IPreferenceStore prefStore = PlatformUI.getPreferenceStore();
        if (prefStore.getDefaultInt("REGISTRATION_TRIES") == 0) { //$NON-NLS-1$
            prefStore.setDefault("REGISTRATION_TRIES", REGISTRATION_MAX_TRIES); //$NON-NLS-1$
        }
        if (prefStore.getDefaultInt("REGISTRATION_DONE") == 0) { //$NON-NLS-1$
            prefStore.setDefault("REGISTRATION_DONE", REGISTRATION_DONE); //$NON-NLS-1$
        }
    }

    /**
     * DOC mhirt Comment method "incrementTryNumber".
     */
    public static void decrementTry() {
        IPreferenceStore prefStore = PlatformUI.getPreferenceStore();
        prefStore.setValue("REGISTRATION_TRIES", prefStore.getInt("REGISTRATION_TRIES") - 1); //$NON-NLS-1$ //$NON-NLS-2$
    }

    // public static void main(String[] args) {
    // try {
    // boolean result = RegisterManagement.register("a@a.fr", "fr", "Beta2");
    // System.out.println(result);
    // } catch (BusinessException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // }
}
