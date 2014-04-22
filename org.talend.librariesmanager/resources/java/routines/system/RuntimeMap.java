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
package routines.system;

import java.util.HashMap;

/**
 * created by hwang on 2014-4-15 Detailled comment
 * 
 */
public class RuntimeMap {

    public static java.util.Map<String, Object> globalMap = null;

    private static RuntimeMap runtimeMap;

    public static synchronized RuntimeMap getInstance() {
        if (runtimeMap == null) {
            runtimeMap = new RuntimeMap();
        }
        return runtimeMap;
    }

    public static synchronized java.util.Map<String, Object> getRuntimeMap() {
        if (globalMap == null) {
            globalMap = new HashMap<String, Object>();
        }
        return globalMap;
    }

}
