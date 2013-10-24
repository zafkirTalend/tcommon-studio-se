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
package org.talend.commons.ui.utils;

import org.eclipse.core.runtime.Path;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class PathUtils {

    public static String getPortablePath(String path) {
        return Path.fromOSString(path).toPortableString();
    }

    public static String getOSPath(String path) {
        return Path.fromPortableString(path).toOSString();
    }

    /*
     * author by hfchang
     * 
     * make a string line wrap autom
     */
    public static String cutStr(String str, int len) {
        String s = "";
        if (str.length() < len) {
            s += str;
        }
        for (int i = 0; i < str.length(); i++) {
            int r = i % len;
            if (i != 0 && i <= len) {
                if (r == 0) {
                    s += str.substring(i - len, len) + "\n";
                }
            } else if (i > len) {
                String laststr = str.substring(i - 1);
                if (laststr.length() > len) {
                    s += cutStr(laststr, len);
                } else {
                    s += laststr;
                }
                break;
            }
        }
        return s;
    }
}
