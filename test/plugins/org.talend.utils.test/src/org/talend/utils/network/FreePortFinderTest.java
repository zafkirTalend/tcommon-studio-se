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
package org.talend.utils.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import junit.framework.TestCase;

/**
 * DOC amaumont class global comment. Detailled comment
 */
public class FreePortFinderTest extends TestCase {

    public FreePortFinderTest(String name) {
        super(name);
    }

    public void testIsFreePort() {

        FreePortFinder freePortFinder = new FreePortFinder();

        int[] portsToOpen = new int[] { 10000, 10002, 10004 };
        List<ServerSocket> servers = openPorts(portsToOpen);

        int[] portsWouldBeFree = new int[] { 10001, 10003, 10005 };
        for (int i = 0; i < portsWouldBeFree.length; i++) {
            boolean portFree = isPortFree(portsWouldBeFree[i]);
            if (!portFree) {
                portsWouldBeFree[i] = -1;
            }
        }

        for (int i = 0; i < portsWouldBeFree.length; i++) {
            int port = portsWouldBeFree[i];
            if (port != -1) {
                boolean isFree = freePortFinder.isPortFree(port);
                if (!isFree) {
                    fail("Failed, port " + port + " should be free, but it is detected as not free");
                }
            }
        }

        closeServerSockets(servers);

    }

    public void testSearchFreePort() {

        FreePortFinder freePortFinder = new FreePortFinder();

        int[] portsToOpen = new int[] { 10000, 10001, 10002, 10003, 10004 };
        List<ServerSocket> servers = openPorts(portsToOpen);

        int freePort = -1;

        for (int i = 0; i < 1000; i++) {
            if (isPortFree(10005 + i)) {
                freePort = 10005 + i;
                break;
            }
        }

        if (freePort == -1) {
            fail("Can't find a free port");
        }

        int freePortFound = freePortFinder.searchFreePort(10000, freePort, false);
        if (freePortFound != -1) {
            fail("Failed, found free port is " + freePortFound + ", it should be " + freePort);
        }

        freePortFound = freePortFinder.searchFreePort(10000, freePort, true);
        if (freePortFound != -1) {
            fail("Failed, found free port is " + freePortFound + ", it should be " + freePort);
        }

        freePortFound = freePortFinder.searchFreePort(10000, 10000, false);
        if (freePortFound != -1) {
            fail("Failed, found free port is " + freePortFound + ", it should be -1 (means not found free port)");
        }

        freePortFound = freePortFinder.searchFreePort(10000, 10000, true);
        if (freePortFound != -1) {
            fail("Failed, found free port is " + freePortFound + ", it should be -1 (means not found free port)");
        }

        freePortFound = freePortFinder.searchFreePort(freePort, freePort, false);
        if (freePortFound != -1) {
            fail("Failed, found free port is " + freePortFound + ", it should be " + freePort);
        }

        freePortFinder.removePort(freePort);
        freePortFound = freePortFinder.searchFreePort(freePort, freePort, true);
        if (freePortFound != freePort) {
            fail("Failed, found free port is " + freePortFound + ", it should be " + freePort);
        }

        closeServerSockets(servers);

    }

    Set<String> freePorts = new HashSet<String>();

    Set<ServerSocket> serverSockets = new HashSet<ServerSocket>();

    public void testConcurrency() {

        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(50);

        int concurrencyThreadNum = 500;

        /**
         * 
         * DOC amaumont FreePortFinderTest class global comment. Detailled comment
         */
        class Command implements Runnable {

            int port;

            /*
             * (non-Javadoc)
             * 
             * @see java.lang.Runnable#run()
             */
            public void run() {
                port = new FreePortFinder().searchFreePort(10000, 20000);
                try {
                    // Assume that all the ports found are used...
                    serverSockets.add(new ServerSocket(port));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                freePorts.add(String.valueOf(port));
            }

            /**
             * Getter for port.
             * 
             * @return the port
             */
            public int getPort() {
                return port;
            }

        }
        ;

        final Command[] commands = new Command[concurrencyThreadNum];

        for (int i = 0; i < concurrencyThreadNum; i++) {
            Command command = new Command();
            commands[i] = command;
        }

        // To execute all commands to get the free ports concurrently, every command is regard as a client.
        for (int i = 0; i < concurrencyThreadNum; i++) {
            threadPool.execute(commands[i]);
        }

        threadPool.shutdown();
        try {
            // In order to execute all the commands, if the value of concurrencyThreadNum is changed, the sleep time
            // also needs to change manually.
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean flag = freePorts.size() == concurrencyThreadNum;
        System.out.println("The number of threads and free ports should be equal. The concurrent thread number is: "
                + concurrencyThreadNum + ", and the free ports number is: " + freePorts.size());
        assertTrue(flag);

        List<ServerSocket> list = new ArrayList<ServerSocket>();
        Iterator<ServerSocket> it = serverSockets.iterator();
        if (it != null) {
            while (it.hasNext()) {
                list.add(it.next());
            }
        }

        closeServerSockets(list);
        // try {
        // threadPool.awaitTermination(1, TimeUnit.MINUTES);
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // fail(e.getMessage());
        // }

        // for (int i = 1; i < testsCount; i++) {
        // if (commands[i].getPort() == commands[i - 1].getPort()) {
        // fail("Identical port found " + commands[i].getPort());
        // }
        // }

    }

    /**
     * 
     * Return true if the specified port is free.
     * 
     * @param port
     * @return Return true if the specified port is free
     */
    public boolean isPortFree(int port) {

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
        } catch (Throwable e) {
            return false;
        } finally {
            try {
                serverSocket.close();
            } catch (Throwable e) {
                // nothing
            }
        }
        return true;
    }

    private List<ServerSocket> openPorts(int[] ports) {
        List<ServerSocket> serverSockets = new ArrayList<ServerSocket>();

        FreePortFinder freePortFinder = new FreePortFinder();
        for (int i = 0; i < ports.length; i++) {
            int port = ports[i];
            ServerSocket openedServerSocket = freePortFinder.openServerSocket(port);
            if (openedServerSocket != null) {
                serverSockets.add(openedServerSocket);
            } else {
                System.out.println("Warning: Port " + port + " already used");
            }
        }
        return serverSockets;
    }

    private void closeServerSockets(List<ServerSocket> servers) {
        for (ServerSocket serverSocket : servers) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
