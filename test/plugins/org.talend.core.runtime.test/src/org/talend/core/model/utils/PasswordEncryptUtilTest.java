// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.utils;

import junit.framework.Assert;

import org.junit.Test;
import org.talend.commons.utils.PasswordEncryptUtil;
import org.talend.cwm.helper.ConnectionHelper;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class PasswordEncryptUtilTest {

    @Test
    public void testEncryptPassword() throws Exception {
        String rawStr = "Talend123";
        String encryptPassword = PasswordEncryptUtil.encryptPassword(rawStr);
        Assert.assertEquals("ABBKp4a4zypsW08UouALBw==", encryptPassword);

        String decryptPassword = PasswordEncryptUtil.decryptPassword(encryptPassword);
        Assert.assertEquals(rawStr, decryptPassword);
    }

    // @Test
    public void testTemp() throws Exception {
        //
        System.out.println("--------------Ref-------------");
        process("mysql_0.1.item", "18EtsbtPYT8L2YrW7TIiEPS1zh8bi+vb9PeroXRnhOo=", true);
        process("ora_0.1.item", "o5x4H8fO+ytK9p711oe8Ww==", true);

        System.out.println("--------------Main-------------");
        process("condb2_0.1.item", "H78DEODtsZpZy/zAAA4SvQ==", false);
        process("conmysql_0.1.item", "A6+FeQ5U2Pw=", false);
        process("conpostgres_0.1.item", "xA8b4Nmhxhc=", false);

    }

    private void process(String itemName, String encrypted, boolean more) {
        String decryptPassword = null;
        System.out.println(itemName);
        try {
            decryptPassword = PasswordEncryptUtil.decryptPassword(encrypted);
            System.out.print("  ");
        } catch (Exception e) {
            System.out.print("~*");
            decryptPassword = ConnectionHelper.getDecryptPassword(encrypted);
        }
        System.out.println(">>: " + decryptPassword);

        if (more) {
            try {
                decryptPassword = PasswordEncryptUtil.decryptPassword(decryptPassword);
                System.out.print("  ");
            } catch (Exception e) {
                System.out.print("~*");
                decryptPassword = ConnectionHelper.getDecryptPassword(decryptPassword);
            }
            System.out.println(">>: " + decryptPassword);
        }
    }
}
