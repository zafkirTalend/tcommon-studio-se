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

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Random;

import org.apache.log4j.Logger;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 */
public class FreePortFinder {

    private static Logger log = Logger.getLogger(FreePortFinder.class);

    private ServerSocket serverSocket;

    private KnownBusyPorts knownBusyPorts;

    private boolean forceCheck;

    /**
     * DOC amaumont FreePortFinder constructor comment.
     * 
     * @throws IOException
     */
    public FreePortFinder() {
        super();
    }

    /**
     * DOC amaumont FreePortFinder constructor comment.
     * 
     * @throws IOException
     */
    public FreePortFinder(KnownBusyPorts knownBusyPorts) {
        super();
        this.knownBusyPorts = knownBusyPorts;
    }

    /**
     * 
     * Return true if the specified port is free.
     * 
     * @param port
     * @return Return true if the specified port is free
     */
    public boolean isPortFree(int port) {

        if (!forceCheck && knownBusyPorts != null && knownBusyPorts.isBusy(port)) {
            return false;
        }

        try {
            serverSocket = new ServerSocket(port);
        } catch (Throwable e) {
            log.debug(e.getMessage());
            return false;
        } finally {
            try {
                serverSocket.close();
            } catch (Throwable e) {
                // TODO Auto-generated catch block
                log.debug(e.getMessage());
            }
        }
        return true;
    }

    /**
     * 
     * Return true if the specified port is free.
     * 
     * @param port
     * @return Return true if the specified port is free
     */
    public ServerSocket openServerSocket(int port) {

        if (!forceCheck && knownBusyPorts != null && knownBusyPorts.isBusy(port)) {
            return null;
        }

        try {
            serverSocket = new ServerSocket(port);
        } catch (Throwable e) {
            log.debug(e.getMessage());
        }
        return serverSocket;
    }

    /**
     * 
     * Search the next free port from <code>portRangeBound1</code> to max port <code>portRangeBound2</code> with
     * randomize start searching.
     * 
     * @param portRangeBound1
     * @param portRangeBound2
     * @return
     */
    public int searchFreePort(int portRangeBound1, int portRangeBound2) {
        return searchFreePort(portRangeBound1, portRangeBound2, true);
    }

    /**
     * 
     * Search the next free port from <code>portRangeBound1</code> to max port <code>portRangeBound2</code>..
     * 
     * @param portRangeBound1
     * @param portRangeBound2
     * @param randomize if true, start with a randomized port number between <code>portRangeBound1</code> and
     * <code>portRangeBound2</code>
     * @return
     */
    public int searchFreePort(int portRangeBound1, int portRangeBound2, boolean randomize) {

        int portBoundMin = portRangeBound1 < portRangeBound2 ? portRangeBound1 : portRangeBound2;
        int portBoundMax = portRangeBound1 < portRangeBound2 ? portRangeBound2 : portRangeBound1;
        int increment = 0;
        if (randomize) {
            Random random = new Random();
            int maxRandomBound = (int) ((double) portBoundMax - (double) portBoundMin) * 3 / 4;
            increment = random.nextInt(maxRandomBound);
        }

        int portStart = portBoundMin + increment;

        boolean isFirstLoop = true;
        boolean isFirstPass = true;
        for (int port = portStart; true; port++) {
            if (port > portBoundMax) {
                port = portBoundMin;
            }
            if (!isFirstLoop && port == portStart) {
                if (isFirstPass) {
                    isFirstPass = false;
                } else if (!forceCheck) {
                    forceCheck = true;
                } else {
                    break;
                }
            }
            if (isPortFree(port)) {
                return port;
            } else {
                knownBusyPorts.addBusyPort(port);
            }
            // System.out.println(port);
            isFirstLoop = false;

        }
        return -1;

    }

    public static void main(String[] args) {
        FreePortFinder freePortFinder = new FreePortFinder();
        System.out.println(freePortFinder.searchFreePort(10, 20));
    }

}
