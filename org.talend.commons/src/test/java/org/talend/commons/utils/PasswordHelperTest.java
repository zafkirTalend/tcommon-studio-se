package org.talend.commons.utils;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;


public class PasswordHelperTest {

    @Test
    public void testVerifyPassword() {
        String passwd = "good passwd";
        try {
            String  encryptPasswd = PasswordHelper.encryptPasswd(passwd);
            assertTrue(PasswordHelper.verifyPassword(passwd, encryptPasswd));
            assertFalse(PasswordHelper.verifyPassword("false passwd", encryptPasswd));
        } catch (NoSuchAlgorithmException e) {
            fail();
        }
    }
}
