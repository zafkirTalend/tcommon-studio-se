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

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.Security;
import java.util.Hashtable;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

import org.apache.log4j.Logger;

import com.sun.net.ssl.KeyManagerFactory;
import com.sun.net.ssl.SSLContext;
import com.sun.net.ssl.TrustManager;
import com.sun.net.ssl.internal.ssl.Provider;

/**
 * This class is used for LDAP. <br/>
 * 
 * @author ftang, 19/09/2007
 * 
 */
public class AdvancedSocketFactory extends SSLSocketFactory {

    private SSLSocketFactory factory;

    private static TrustManager trustManagers[] = null;

    private static AdvancedSocketFactory defaultFactory = null;

    private static Hashtable factories = null;

    private static String certStorePath = null;

    private static Logger log = Logger.getLogger(AdvancedSocketFactory.class);

    /**
     * AdvancedSocketFactory constructor comment.
     */
    protected AdvancedSocketFactory() {
        factory = null;
        init(null, null);
    }

    /**
     * AdvancedSocketFactory constructor comment.
     * 
     * @param in
     * @param keyStore
     * @param password
     * @throws Exception
     */
    protected AdvancedSocketFactory(InputStream in, String keyStore, String password) throws Exception {
        factory = null;
        KeyStore ks = null;
        if (keyStore.endsWith(".p12"))
            ks = KeyStore.getInstance("PKCS12");
        else
            ks = KeyStore.getInstance("JKS");
        char pwd[] = password.toCharArray();
        ks.load(in, pwd);
        init(ks, pwd);
    }

    /**
     * AdvancedSocketFactory constructor comment.
     * 
     * @param keyStore
     * @param passphrase
     */
    protected AdvancedSocketFactory(String keyStore, String passphrase) {
        factory = null;
        init(null, null);
    }

    /**
     * Comment method "closeStream".
     * 
     * @param in
     */
    private static void closeStream(InputStream in) {
        if (in == null)
            return;
        try {
            in.close();
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.net.SocketFactory#createSocket(java.lang.String, int)
     */
    public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
        return factory.createSocket(host, port);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.net.SocketFactory#createSocket(java.lang.String, int, java.net.InetAddress, int)
     */
    public Socket createSocket(String host, int port, InetAddress clientHost, int clientPort) throws IOException,
            UnknownHostException {
        return factory.createSocket(host, port, clientHost, clientPort);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.net.SocketFactory#createSocket(java.net.InetAddress, int)
     */
    public Socket createSocket(InetAddress host, int port) throws IOException, UnknownHostException {
        return factory.createSocket(host, port);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.net.SocketFactory#createSocket(java.net.InetAddress, int, java.net.InetAddress, int)
     */
    public Socket createSocket(InetAddress host, int port, InetAddress clientHost, int clientPort) throws IOException,
            UnknownHostException {
        return factory.createSocket(host, port, clientHost, clientPort);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.net.ssl.SSLSocketFactory#createSocket(java.net.Socket, java.lang.String, int, boolean)
     */
    public Socket createSocket(Socket socket, String host, int port, boolean autoclose) throws IOException,
            UnknownHostException {
        return factory.createSocket(socket, host, port, autoclose);
    }

    /**
     * Comment method "getDefault".
     * 
     * @return
     */
    public static synchronized SocketFactory getDefault() {
        return getDefaultFactory();
    }

    /**
     * Comment method "setCertStorePath".
     * 
     * @param path
     */
    public static void setCertStorePath(String path) {
        AdvancedSocketFactory.certStorePath = path;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.net.ssl.SSLSocketFactory#getDefaultCipherSuites()
     */
    public String[] getDefaultCipherSuites() {
        return factory.getDefaultCipherSuites();
    }

    /**
     * Comment method "getDefaultFactory".
     * 
     * @return
     */
    private static SocketFactory getDefaultFactory() {
        if (defaultFactory == null)
            defaultFactory = new AdvancedSocketFactory();
        return defaultFactory;
    }

    /**
     * Comment method "getDefaultTrustManager".
     * 
     * @return
     */
    private TrustManager[] getDefaultTrustManager() {
        if (trustManagers == null)
            trustManagers = (new LDAPCATruster[] { new LDAPCATruster(AdvancedSocketFactory.certStorePath) });
        return trustManagers;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.net.ssl.SSLSocketFactory#getSupportedCipherSuites()
     */
    public String[] getSupportedCipherSuites() {
        return factory.getSupportedCipherSuites();
    }

    /**
     * Comment method "init".
     * 
     * @param ks
     * @param password
     */
    private void init(KeyStore ks, char password[]) {
        SSLContext ctx = null;
        com.sun.net.ssl.KeyManager keyManagers[] = null;
        TrustManager trustManagers[] = null;
        try {
            if (ks != null) {
                KeyManagerFactory kmf = null;
                kmf = KeyManagerFactory.getInstance("SunX509");
                kmf.init(ks, password);
                keyManagers = kmf.getKeyManagers();
            }
            ctx = SSLContext.getInstance("TLS");
            trustManagers = getDefaultTrustManager();
            ctx.init(keyManagers, trustManagers, null);
            factory = ctx.getSocketFactory();
        } catch (Exception e) {
            log.error("Error: failed to initialize : " + e.getMessage());
        }
    }

    static {
        Security.addProvider(new Provider());
    }
}
