// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
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
package org.talend.core.ldap;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import com.sun.net.ssl.TrustManagerFactory;
import com.sun.net.ssl.X509TrustManager;

/**
 *  Administrator  class global comment. Detailled comment
 * <br/>
 *
 */
public class Truster implements X509TrustManager {

    private String certStore;

    private char certStorePwd[];

    private X509TrustManager trustManager;

    private boolean isSaveCA = true;

    private KeyStore ks;

    public Truster(String certStorePath) {
        certStore = null;
        certStorePwd = null;
        trustManager = null;
        ks = null;
        if (certStorePath == null) {
            isSaveCA = false;
            certStore = "talendcecerts";
        } else {
            certStore = certStorePath;
        }
        certStorePwd = "changeit".toCharArray();
        init();
    }

    private boolean deleteCert(String id) {
        try {
            ks.deleteEntry(id);
        } catch (KeyStoreException ex) {
            return false;
        }
        return true;
    }

    public X509Certificate[] getAcceptedIssuers() {
        if (trustManager == null)
            return null;
        else
            return trustManager.getAcceptedIssuers();
    }

    private X509Certificate getCACert(X509Certificate chain[]) {
        X509Certificate ca = chain[chain.length - 1];
        if (ca.getSubjectDN().equals(ca.getIssuerDN()))
            return ca;
        else
            return null;
    }

    private void init() {
        try {
            if (certStore.endsWith(".p12"))
                ks = KeyStore.getInstance("PKCS12");
            else
                ks = KeyStore.getInstance("JKS");
        } catch (KeyStoreException e) {
            System.err.println("ASF Truster: Failed to create cert store : " + e.getMessage());
            return;
        }
        InputStream in = null;
        if (certStore.indexOf("://") == -1)
            try {
                in = new FileInputStream(certStore);
            } catch (FileNotFoundException ex) {
            }
        else
            try {
                URL url = new URL(certStore);
                URLConnection con = url.openConnection();
                in = con.getInputStream();
            } catch (MalformedURLException e) {
                System.err.println("ASF Truster: The location of the cert store file is invalid: " + e.getMessage());
            } catch (IOException ex) {
            }
        try {
            ks.load(in, certStorePwd);
        } catch (Exception e) {
            System.err.println("ASF Truster: Failed to load the cert store : " + e.getMessage());
            return;
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (Exception ex) {
                }
        }
        try {
            trustManager = initTrustManager(ks);
        } catch (Exception e) {
            System.err.println("ASF Truster: Failed to create initial trust manager : " + e.getMessage());
            return;
        }
    }

    private X509TrustManager initTrustManager(KeyStore ks) throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory trustManagerFactory = null;
        trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
        trustManagerFactory.init(ks);
        com.sun.net.ssl.TrustManager trusts[] = trustManagerFactory.getTrustManagers();
        return (X509TrustManager) trusts[0];
    }

    private boolean isAccepted(X509Certificate caCert) {
        X509Certificate certs[] = getAcceptedIssuers();
        if (certs == null)
            return false;
        for (int i = 0; i < certs.length; i++)
            if (caCert.equals(certs[i]))
                return true;

        return false;
    }

    public boolean isClientTrusted(X509Certificate chain[]) {
        if (trustManager == null)
            return false;
        else
            return trustManager.isClientTrusted(chain);
    }

    public boolean isServerTrusted(X509Certificate chain[]) {
        if (trustManager != null) {
            boolean rs = trustManager.isServerTrusted(chain);
            if (rs)
                return rs;
        }
        X509Certificate ca = getCACert(chain);
        if (ca != null) {
            if (isAccepted(ca)) {
                System.err.println("SSL Error:Server certificate chain verification failed.");
                return false;
            }
            String id = String.valueOf(System.currentTimeMillis());
            X509TrustManager tmpTrustManager = null;
            try {
                ks.setCertificateEntry(id, ca);
                tmpTrustManager = initTrustManager(ks);
            } catch (Exception e) {
                System.err.println("ASF Truster: Failed to create tmp trust store : " + e.getMessage());
                return false;
            }
            if (tmpTrustManager.isServerTrusted(chain)) {
                if (this.isSaveCA) {
                    saveStore();
                    trustManager = tmpTrustManager;
                }
                return true;
            } else {
                System.err.println("SSL Error:Server certificate chain verification failed and \\nthe CA is missing.");
                return false;
            }
        } else {
            System.err
                    .println("SSL Error:CA certificate is not in the server certificate chain.\nPlease use the keytool command to import the server certificate.");
            return false;
        }
    }

    private boolean saveStore() {
        OutputStream out = null;
        try {
            try {
                if (certStore.indexOf("://") == -1) {
                    out = new FileOutputStream(certStore);
                } else {
                    URL url = new URL(certStore);
                    URLConnection con = url.openConnection();
                    con.setDoOutput(true);
                    out = con.getOutputStream();
                }
                ks.store(out, certStorePwd);
                return true;
            } catch (Exception e) {
                System.err.println("ASF Truster: Failed to save trust store : " + e.getMessage());
            }
            return false;
        } finally {
            if (out != null)
                try {
                    out.close();
                } catch (Exception ex) {
                }
        }
    }
}
