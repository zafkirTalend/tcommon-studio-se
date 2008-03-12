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
package org.talend.dataprofiler.core.helper;

import java.util.HashMap;
import java.util.Map;

import org.talend.cwm.softwaredeployment.TdDataProvider;


/**
 * @author rli
 *
 */
public final class TDDataProviderStoreHelper {
    private static Map<String, TdDataProvider> tdDataProviderMap = new HashMap<String, TdDataProvider>();

    public static void put(String providerName, TdDataProvider dataProvider) {
        tdDataProviderMap.put(providerName + ".prv", dataProvider);
    }

    public static TdDataProvider get(String providerName) {
        return tdDataProviderMap.get(providerName);
    }
}
