// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.utils.security;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

/**
 * created by zwli on Feb 27, 2013 Detailed comment
 */
public class AES {

    private static Logger log = Logger.getLogger(AES.class);

    private static final String OS_NAME = "SunOS";

    private static final String RANDOM_SHA1PRNG = "SHA1PRNG";

    private static final String ENCRYPTION_ALGORITHM = "AES";

    private static final String EMPTY_STRING = "";

    private static final String UTF8 = "UTF8";

    // 8-byte
    private static final byte[] KeyValues = { (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32, (byte) 0x56, (byte) 0x35,
            (byte) 0xE3, (byte) 0x03 };

    private static AES instance;

    private Cipher ecipher;

    private Cipher dcipher;

    public static synchronized AES getInstance() {
        if (null == instance) {
            instance = new AES();
        }
        return instance;
    }

    private AES() {
        try {
            String osName = System.getProperty("os.name");
            boolean isSunOS = false;
            if (null != osName) {
                isSunOS = osName.contains(OS_NAME);
            }

            Provider providerSunJCE = null;
            // TDI-28380: Database password in tac db configuration page becomes empty once restart tomcat on Solaris.
            // To solve this problem, there are two ways:
            // Security.removeProvider("SunPKCS11-Solaris");
            // or: providerSunJCE = Security.getProvider("SunJCE");
            if (isSunOS) {
                providerSunJCE = Security.getProvider("SunJCE");
            }

            KeyGenerator keyGen = null;
            SecureRandom random = null;

            if (isSunOS) {
                keyGen = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM, providerSunJCE);
            } else {
                keyGen = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM);
            }

            random = SecureRandom.getInstance(RANDOM_SHA1PRNG);
            random.setSeed(KeyValues);
            keyGen.init(128, random);

            Key key = keyGen.generateKey();

            if (isSunOS) {
                ecipher = Cipher.getInstance(ENCRYPTION_ALGORITHM, providerSunJCE);
                dcipher = Cipher.getInstance(ENCRYPTION_ALGORITHM, providerSunJCE);
            } else {
                ecipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
                dcipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
            }

            ecipher.init(Cipher.ENCRYPT_MODE, key);
            dcipher.init(Cipher.DECRYPT_MODE, key);
        } catch (Exception e) {
            // log the error to avoid that break GWT service
            log.error(e.getMessage(), e);
        }
    }

    public String encrypt(String data) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        if (EMPTY_STRING.equals(data)) {
            return EMPTY_STRING;
        }
        byte[] enc = ecipher.doFinal(data.getBytes(UTF8));
        String encryptedData = new String(Base64.encodeBase64(enc), UTF8);
        return encryptedData;
    }

    public String decrypt(String encryptedData) throws UnsupportedEncodingException, IllegalBlockSizeException,
            BadPaddingException {
        if (EMPTY_STRING.equals(encryptedData)) {
            return EMPTY_STRING;
        }
        byte[] dec = Base64.decodeBase64(encryptedData.getBytes(UTF8));
        dec = dcipher.doFinal(dec);
        String decryptedData = new String(dec, UTF8);
        return decryptedData;
    }

}
