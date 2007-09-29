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
package org.talend.commons.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

/**
 * class global comment. Detailled comment. <br/>
 * 
 */
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
