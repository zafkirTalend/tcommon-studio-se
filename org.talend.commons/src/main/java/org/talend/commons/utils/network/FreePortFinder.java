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
package org.talend.commons.utils.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.Random;

import org.talend.commons.exception.ExceptionHandler;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 */
public class FreePortFinder {

    private ServerSocket serverSocket;

    /**
     * DOC amaumont FreePortFinder constructor comment.
     * 
     * @throws IOException
     */
    public FreePortFinder() {
        super();
    }

    /**
     * 
     * Return true if the specified port is free.
     * 
     * @param port
     * @return Return true if the specified port is free
     */
    public boolean isPortFree(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 
     * Search the next free port from <code>portRangeBound1</code> to max port <code>portRangeBound2</code> with randomize start searching.
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
     * @param randomize if true, start with a randomized port number between <code>portRangeBound1</code> and <code>portRangeBound2</code>
     * @return
     */
    public int searchFreePort(int portRangeBound1, int portRangeBound2, boolean randomize) {

        int portBoundMin = portRangeBound1 < portRangeBound2 ? portRangeBound1 : portRangeBound2;
        int portBoundMax = portRangeBound1 < portRangeBound2 ? portRangeBound2 : portRangeBound1;

        Random random = new Random();
        int portStart = portBoundMin + random.nextInt(portBoundMax - portBoundMin);

        boolean isFirstLoop = true;
        for (int port = portStart; true; port++) {
            if (port > portBoundMax) {
                port = portBoundMin;
            }
            if (!isFirstLoop && port == portStart) {
                break;
            }
            if (isPortFree(port)) {
                return port;
            }
//            System.out.println(port);
            isFirstLoop = false;

        }
        return -1;

    }

    public static void main(String[] args) {
        FreePortFinder freePortFinder = new FreePortFinder();
        System.out.println("Free port : " + freePortFinder.searchFreePort(10, 20));;
    }
    
}
