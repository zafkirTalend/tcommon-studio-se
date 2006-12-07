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
import java.util.Collections;
import java.util.Comparator;
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
 * @param <D1> the data item of extremety 1
 * @param <D2> the data item of extremety 2
 */
public class LinksManager<D1, D2> {

    private List<LinkDescriptor<D1, D2>> links = new ArrayList<LinkDescriptor<D1, D2>>();

    private int currentNumberLinks = 0;

    private Map<D1, Set<LinkDescriptor<D1, D2>>> d1ToLinks = new HashMap<D1, Set<LinkDescriptor<D1, D2>>>();

    private Map<D2, Set<LinkDescriptor<D1, D2>>> d2ToLinks = new HashMap<D2, Set<LinkDescriptor<D1, D2>>>();

    public LinksManager() {
        super();
        currentNumberLinks = 0;
    }

    /**
     * DOC amaumont Comment method "addLink".
     * 
     * @param link
     */
    public void addLink(LinkDescriptor<D1, D2> link) {
        currentNumberLinks++;

        links.add(link);

        IExtremityLink<D1> extremity1 = link.getExtremity1();
        IExtremityLink<D2> extremity2 = link.getExtremity2();

        registerItem(link, d1ToLinks, extremity1.getDataItem());
        registerItem(link, d2ToLinks, extremity2.getDataItem());

    }

    /**
     * DOC amaumont Comment method "registerItem".
     * 
     * @param link
     * @param extremity
     */
    @SuppressWarnings("unchecked")
    private void registerItem(LinkDescriptor<D1, D2> link, Map gToLinks, Object item) {
        Set<LinkDescriptor<D1, D2>> linksFromItem = (Set<LinkDescriptor<D1, D2>>) gToLinks.get(item);
        if (linksFromItem == null) {
            linksFromItem = new HashSet<LinkDescriptor<D1, D2>>();
            gToLinks.put(item, linksFromItem);
        }
        linksFromItem.add(link);
    }

    /**
     * DOC amaumont Comment method "addLink".
     * 
     * @param link
     */
    public boolean removeLink(LinkDescriptor<D1, D2> link) {
        currentNumberLinks--;

        boolean removed = links.remove(link);

        d1ToLinks.remove(link.getExtremity1().getDataItem());
        d2ToLinks.remove(link.getExtremity2().getDataItem());

        return removed;
    }

    /**
     * DOC amaumont Comment method "clearLinks".
     */
    public void clearLinks() {
        links.clear();
        d1ToLinks.clear();
        d2ToLinks.clear();
        currentNumberLinks = 0;
    }

    /**
     * DOC amaumont Comment method "getLinks".
     * 
     * @return
     */
    public List<LinkDescriptor<D1, D2>> getLinks() {
        return this.links;
    }

//    /**
//     * 
//     * DOC amaumont Comment method "changeTargetGraphicalItemFromTargetDataItem".
//     * 
//     * @param dataItemOfExtremity links are searched from this object
//     * @param newGraphicalTarget new graphical target to set
//     * @return true if change applied, else false if dataTarget doesn't have links
//     */
//    public boolean setNewGraphicalItemToExtremity2(D2 dataItemOfExtremity, G2 newGraphicalTarget) {
//        Set<LinkDescriptor<D1, D2>> linksFromD2 = d2ToLinks.get(dataItemOfExtremity);
//        if (linksFromD2 != null) {
//            Set<LinkDescriptor<D1, D2>> linksForG2 = g2ToLinks.get(newGraphicalTarget);
//            if (linksForG2 != null) {
//                linksForG2.clear();
//            } else {
//                linksForG2 = new HashSet<LinkDescriptor<D1, D2>>();
//            }
//            g2ToLinks.put(newGraphicalTarget, linksForG2);
//            for (LinkDescriptor<D1, D2> linkFromD2 : linksFromD2) {
//                linksForG2.add(linkFromD2);
//                linkFromD2.getExtremity2().setGraphicalItem(newGraphicalTarget);
//            }
//            return true;
//        } else {
//            return false;
//        }
//    }

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
     * 
     * @param tableItem
     */
    public boolean removeLinksFromDataItem1(D1 dataItem1) {
        Set<LinkDescriptor<D1, D2>> linksFromD1 = d1ToLinks.get(dataItem1);
        if (linksFromD1 != null) {
            return removeLinks(linksFromD1);
        }
        return false;
    }

    /**
     * DOC amaumont Comment method "removeLink".
     * 
     * @param tableItem
     */
    public boolean removeLinksFromDataItem2(D2 dataItem2) {
        Set<LinkDescriptor<D1, D2>> linksFromD2 = d2ToLinks.get(dataItem2);
        if (linksFromD2 != null) {
            return removeLinks(linksFromD2);
        }
        return false;
    }

    /**
     * DOC amaumont Comment method "removeLinks".
     * 
     * @param linksFromG2
     */
    private boolean removeLinks(Set<LinkDescriptor<D1, D2>> linksFromG2) {
        boolean removed = false;
        for (LinkDescriptor<D1, D2> linkDescriptor : linksFromG2) {
            if (removeLink(linkDescriptor)) {
                removed = true;
            }
        }
        return removed;
    }

    /**
     * DOC amaumont Comment method "getLinksFromExtremity1".
     * 
     * @param treeItem
     */
    public Set<LinkDescriptor<D1, D2>> getLinksFromDataExtremity1(D1 dataItem) {
        return getLinks(d1ToLinks, dataItem);
    }

    /**
     * DOC amaumont Comment method "getLinksFromExtremity1".
     * 
     * @param treeItem
     */
    public Set<LinkDescriptor<D1, D2>> getLinksFromDataExtremity2(D2 dataItem) {
        return getLinks(d2ToLinks, dataItem);
    }

    /**
     * DOC amaumont Comment method "getLinks".
     * 
     * @param toLinks
     * @param dataItem
     * @return
     */
    @SuppressWarnings("unchecked")
    private Set<LinkDescriptor<D1, D2>> getLinks(Map objectToLinks, Object object) {
        Set<LinkDescriptor<D1, D2>> linksFound = (Set<LinkDescriptor<D1, D2>>) objectToLinks.get(object);
        if (linksFound == null) {
            return new HashSet<LinkDescriptor<D1, D2>>(0);
        }
        return new HashSet<LinkDescriptor<D1, D2>>(linksFound);
    }

    public void sortLinks(Comparator<LinkDescriptor<D1, D2>> comparator) {
        Collections.sort(links, comparator);
    }

}
