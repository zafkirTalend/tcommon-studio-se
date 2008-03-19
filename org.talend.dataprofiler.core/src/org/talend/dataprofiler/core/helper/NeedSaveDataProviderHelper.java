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
import java.util.Iterator;
import java.util.Map;

import org.talend.cwm.management.api.DqRepositoryViewService;
import org.talend.cwm.softwaredeployment.TdDataProvider;

/**
 * This class help store the needed save TdDataProvider object.
 * 
 */
public final class NeedSaveDataProviderHelper {

    private NeedSaveDataProviderHelper() {
    }

    private static Map<String, TdDataProvider> needSaveProviderMap = new HashMap<String, TdDataProvider>();

    public static void register(String providerName, TdDataProvider dataProvider) {
        // MODSCA 2008-03-19 removed ".prv" extension since it is not used in get() method
        needSaveProviderMap.put(providerName, dataProvider);
    }

    public static TdDataProvider get(String providerName) {
        return needSaveProviderMap.get(providerName);
    }

    // public static String[] getAllDataProviderNames() {
    // return needSaveProviderMap.keySet().toArray(new String[getDataProviderSize()]);
    // }
    //    
    // public static TdDataProvider[] getAllDataProvider() {
    // return needSaveProviderMap.values().toArray(new TdDataProvider[getDataProviderSize()]);
    // }
    //    
    // public static int getDataProviderSize() {
    // return needSaveProviderMap.keySet().size();
    // }

    /**
     * Save all the data provider.
     */
    public static void saveAllDataProvider() {
        Iterator<String> it = needSaveProviderMap.keySet().iterator();
        while (it.hasNext()) {
            TdDataProvider provider = needSaveProviderMap.get(it.next());
            DqRepositoryViewService.saveOpenDataProvider(provider);
        }
    }
}
