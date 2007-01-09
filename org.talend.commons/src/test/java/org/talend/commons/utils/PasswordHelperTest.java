package org.talend.commons.utils;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;


public class PasswordHelperTest {

    @Test
    public void testVerifyPassword() {
        String passwd = "good passwd";
        String passwd2 = "false passwd";
        try {
            byte[] bs1 = PasswordHelper.encryptPasswd(passwd);
            byte[] bs2 = PasswordHelper.encryptPasswd(passwd2);
            assertTrue(PasswordHelper.verifyPassword(bs1, bs1));
            assertFalse(PasswordHelper.verifyPassword(bs1, bs2));
        } catch (NoSuchAlgorithmException e) {
            fail();
        }
    }
}
