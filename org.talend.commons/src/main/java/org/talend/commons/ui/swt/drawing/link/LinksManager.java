// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
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
package org.talend.commons.ui.swt.drawing.link;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id: LinkManager.java 318 2006-11-03 09:44:45 +0000 (ven., 03 nov. 2006) amaumont $
 * 
 * @param <G1> the graphical item of extremety 1
 * @param <D1> the data item of extremety 1
 * @param <G2> the graphical item of extremety 2
 * @param <D2> the data item of extremety 2
 */
public class LinksManager<G1, D1, G2, D2> {

    private List<LinkDescriptor<G1, D1, G2, D2>> links = new ArrayList<LinkDescriptor<G1, D1, G2, D2>>();

    private int currentNumberLinks = 0;

    private Map<G2, Set<LinkDescriptor<G1, D1, G2, D2>>> g2ToLinks = new HashMap<G2, Set<LinkDescriptor<G1, D1, G2, D2>>>();
    
    private Map<D2, Set<LinkDescriptor<G1, D1, G2, D2>>> d2ToLinks = new HashMap<D2, Set<LinkDescriptor<G1, D1, G2, D2>>>();

    public LinksManager() {
        super();
        currentNumberLinks = 0;
    }

    /**
     * DOC amaumont Comment method "addLink".
     * 
     * @param link
     */
    public void addLink(LinkDescriptor<G1, D1, G2, D2> link) {
        currentNumberLinks++;

        links.add(link);
//        IExtremityLink<G1, D1> extremitsy1 = link.getExtremity1();
        IExtremityLink<G2, D2> extremity2 = link.getExtremity2();
        
        Set<LinkDescriptor<G1, D1, G2, D2>> linksFromG2 = g2ToLinks.get(extremity2.getGraphicalItem());
        if (linksFromG2 == null) {
            linksFromG2 = new HashSet<LinkDescriptor<G1, D1, G2, D2>>();
            g2ToLinks.put(extremity2.getGraphicalItem(), linksFromG2);
        }
        linksFromG2.add(link);
        
        Set<LinkDescriptor<G1, D1, G2, D2>> linksFromD2 = d2ToLinks.get(extremity2.getDataItem());
        if (linksFromD2 == null) {
            linksFromD2 = new HashSet<LinkDescriptor<G1, D1, G2, D2>>();
            d2ToLinks.put(extremity2.getDataItem(), linksFromD2);
        }
        linksFromD2.add(link);
        
    }


    /**
     * DOC amaumont Comment method "addLink".
     * 
     * @param link
     */
    public void removeLink(LinkDescriptor<G1, D1, G2, D2> link) {
        currentNumberLinks--;

        links.remove(link);
        
        g2ToLinks.remove(link.getExtremity2().getGraphicalItem());
        d2ToLinks.remove(link.getExtremity2().getDataItem());
        
        
        
    }

    /**
     * DOC amaumont Comment method "clearLinks".
     */
    public void clearLinks() {
        links.clear();
        d2ToLinks.clear();
        g2ToLinks.clear();
        currentNumberLinks = 0;
    }

    /**
     * DOC amaumont Comment method "getLinks".
     * 
     * @return
     */
    public List<LinkDescriptor<G1, D1, G2, D2>> getLinks() {
        return this.links;
    }

    /**
     * 
     * DOC amaumont Comment method "changeTargetGraphicalItemFromTargetDataItem".
     * @param dataItemOfExtremity links are searched from this object
     * @param newGraphicalTarget new graphical target to set
     * @return true if change applied, else false if dataTarget doesn't have links
     */
    public boolean setNewGraphicalItemToExtremity2(D2 dataItemOfExtremity, G2 newGraphicalTarget) {
        Set<LinkDescriptor<G1, D1, G2, D2>> linksFromD2 = d2ToLinks.get(dataItemOfExtremity);
        if(linksFromD2 != null) {
            Set<LinkDescriptor<G1, D1, G2, D2>> linksForG2 = g2ToLinks.get(newGraphicalTarget);
            if(linksForG2 != null) {
                linksForG2.clear();
            } else {
                linksForG2 = new HashSet<LinkDescriptor<G1, D1, G2, D2>>();
            }
            g2ToLinks.put(newGraphicalTarget, linksForG2);
            for (LinkDescriptor<G1, D1, G2, D2> linkFromD2 : linksFromD2) {
                linksForG2.add(linkFromD2);
                linkFromD2.getExtremity2().setGraphicalItem(newGraphicalTarget);
            }
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Getter for currentNumberLinks.
     * 
     * @return the currentNumberLinks
     */
    public int getCurrentNumberLinks() {
        return this.currentNumberLinks;
    }
    
    /**
     * DOC amaumont Comment method "removeLink".
     * @param tableItem
     */
    public void removeLink(G2 graphicalItem) {
        Set<LinkDescriptor<G1, D1, G2, D2>> linksFromG2 = g2ToLinks.get(graphicalItem);
        if(linksFromG2 != null) {
            removeLinks(linksFromG2);
        }
    }

    /**
     * DOC amaumont Comment method "removeLinks".
     * @param linksFromG2
     */
    private void removeLinks(Set<LinkDescriptor<G1, D1, G2, D2>> linksFromG2) {
        for (LinkDescriptor<G1, D1, G2, D2> linkDescriptor : linksFromG2) {
            removeLink(linkDescriptor);
        }
    }
    
    
}
