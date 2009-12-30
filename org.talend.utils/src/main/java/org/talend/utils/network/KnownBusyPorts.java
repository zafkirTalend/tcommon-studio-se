// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.utils.network;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * DOC amaumont class global comment. Detailled comment
 */
public class KnownBusyPorts {

    /**
     * Integer is port Long is time is milliseconds get at insertion
     */
    private Map<Integer, Long> busyPorts = new HashMap<Integer, Long>();

    public synchronized void addBusyPort(int busyPort) {
        busyPorts.put(busyPort, System.currentTimeMillis());
    }

    public synchronized void removeBusyPort(int busyPort) {
        busyPorts.remove(busyPort);
    }

    public boolean isBusy(int port) {
        return busyPorts.get(port) != null;
    }

}
