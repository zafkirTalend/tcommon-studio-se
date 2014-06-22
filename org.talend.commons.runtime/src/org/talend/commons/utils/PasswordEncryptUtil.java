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
package org.talend.commons.utils;

import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * DOC chuang class global comment. Detailled comment
 */
public class PasswordEncryptUtil {

    private static String rawKey = "Talend-Key";

    private static SecretKey key = null;

    private static SecureRandom secureRandom = new SecureRandom();

    private static SecretKey getSecretKey() throws Exception {
        if (key == null) {

            byte rawKeyData[] = rawKey.getBytes();
            DESKeySpec dks = new DESKeySpec(rawKeyData);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            key = keyFactory.generateSecret(dks);
        }
        return key;
    }

    public static String encryptPassword(String input) throws Exception {
        if (input == null || input.length() == 0) {
            return input;
        }

        SecretKey key = getSecretKey();
        Cipher c = Cipher.getInstance("DES");
        c.init(Cipher.ENCRYPT_MODE, key, secureRandom);
        byte[] cipherByte = c.doFinal(input.getBytes());
        String dec = new String(Base64.encodeBase64(cipherByte));
        return dec;
    }

    public static String decryptPassword(String input) throws Exception, BadPaddingException {
        if (input == null || input.length() == 0) {
            return input;
        }
        byte[] dec = Base64.decodeBase64(input.getBytes());
        SecretKey key = getSecretKey();
        Cipher c = Cipher.getInstance("DES");
        c.init(Cipher.DECRYPT_MODE, key, secureRandom);
        byte[] clearByte = c.doFinal(dec);
        return new String(clearByte);
    }

    public static boolean isPasswordType(String type) {
        // should match with JavaTypesManager.PASSWORD.getLabel() and
        // JavaTypesManager.PASSWORD.getId()
        if (type == null) {
            return false;
        }
        return type.equals("Password") || type.equals("id_Password");
    }

    // public static void main(String[] args) {
    //
    // try {
    // String input = PasswordEncryptUtil.encryptPassword("test");
    // System.out.println(input);
    // System.out.println(PasswordEncryptUtil.decryptPassword(input));
    // } catch (BadPaddingException e) {
    // e.printStackTrace();
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

}
