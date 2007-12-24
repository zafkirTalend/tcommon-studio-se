// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 */
public class PasswordHelper {

    private static final String ALGORITHM = "MD5";

    public static synchronized boolean verifyPassword(byte[] encryptedPassword, byte[] encryptedPassword2) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
        return messageDigest.isEqual(encryptedPassword, encryptedPassword2);
    }

    public static synchronized byte[] encryptPasswd(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
        return messageDigest.digest(password.getBytes());
    }
}
