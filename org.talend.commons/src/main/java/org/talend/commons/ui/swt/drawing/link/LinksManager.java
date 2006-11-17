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
 * @param <A1> the attached object of the extremety 1
 * @param <A2> the attached object of the extremety 2
 */
public class LinksManager<A1, A2> {

    // private static final Comparator<Link<TIP1, TIP2>> COMPARATOR = new Comparator<Link<TIP1, TIP2>>() {
    //
    // public int compare(Link<TIP1, TIP2> link1, Link<TIP1, TIP2> link2) {
    // if (link1.getState() == link2.getState()) {
    // return 0;
    // }
    // if (link1.getState() == LinkState.SELECTED) {
    // return 1;
    // }
    // return -1;
    // }
    //
    // };

    private List<LinkDescriptor<A1, A2>> links = new ArrayList<LinkDescriptor<A1, A2>>();

    private int currentNumberLinks = 0;

    private Map<IExtremityLink<A1>, Set<LinkDescriptor<A1, A2>>> extremity1ToLinks = new HashMap<IExtremityLink<A1>, Set<LinkDescriptor<A1, A2>>>();

    private Map<IExtremityLink<A2>, Set<LinkDescriptor<A1, A2>>> extremity2ToLinks = new HashMap<IExtremityLink<A2>, Set<LinkDescriptor<A1, A2>>>();

    private Map<A2, Set<IExtremityLink<A2>>> associatedObjectToExtremities = new HashMap<A2, Set<IExtremityLink<A2>>>();

    public LinksManager() {
        super();
        currentNumberLinks = 0;
    }

    /**
     * DOC amaumont Comment method "addLink".
     * 
     * @param link
     */
    public void addLink(LinkDescriptor<A1, A2> link) {
        currentNumberLinks++;
        // System.out.println(currentNumberLinks + " links");

        links.add(link);
        IExtremityLink<A1> extremity1 = link.getExtremity1();
        IExtremityLink<A2> extremity2 = link.getExtremity2();
        // TIP1 sourceITableEntry = link.getPointLinkDescriptor1().getTableEntry();
        // TIP2 targetITableEntry = link.getPointLinkDescriptor2().getTableEntry();
        Set<IExtremityLink<A1>> sourceDataMapTableEntries = getSourcesCollection(extremity2);
        sourceDataMapTableEntries.add(extremity1);
        Set<LinkDescriptor<A1, A2>> targetGraphicalLinks = getLinksFromExtremity2Internal(extremity2);
        targetGraphicalLinks.add(link);
        Set<LinkDescriptor<A1, A2>> sourceGraphicalLinks = getLinksFromExtremity1Internal(extremity1);
        sourceGraphicalLinks.add(link);
    }

    /**
     * DOC amaumont Comment method "getGraphicalLinks".
     * 
     * @param targetTableEntry
     * @return
     */
    private Set<LinkDescriptor<A1, A2>> getLinksFromExtremity2Internal(IExtremityLink<A2> extremity2) {
        Set<LinkDescriptor<A1, A2>> links = extremity2ToLinks.get(extremity2);
        if (links == null) {
            links = new HashSet<LinkDescriptor<A1, A2>>();
            extremity2ToLinks.put(extremity2, links);
        }
        return links;
    }

    /**
     * DOC amaumont Comment method "getGraphicalLinks".
     * 
     * @param targetTableEntry
     * @return
     */
    public Set<LinkDescriptor<A1, A2>> getLinksFromExtremity2(IExtremityLink<A2> extremity2) {
        return new HashSet<LinkDescriptor<A1, A2>>(getLinksFromExtremity2Internal(extremity2));
    }

    /**
     * DOC amaumont Comment method "getGraphicalLinks".
     * 
     * @param targetTableEntry
     * @return
     */
    private Set<LinkDescriptor<A1, A2>> getLinksFromExtremity1Internal(IExtremityLink<A1> extremity1) {
        Set<LinkDescriptor<A1, A2>> links = extremity1ToLinks.get(extremity1);
        if (links == null) {
            links = new HashSet<LinkDescriptor<A1, A2>>();
            extremity1ToLinks.put(extremity1, links);
        }
        return links;
    }

    public Set<LinkDescriptor<A1, A2>> getLinksFromExtremity1(IExtremityLink<A1> extremity1) {
        return new HashSet<LinkDescriptor<A1, A2>>(getLinksFromExtremity1Internal(extremity1));
    }

    /**
     * DOC amaumont Comment method "getSourcesCollection".
     * 
     * @param extremity2
     * @return
     */
    private Set<IExtremityLink<A1>> getSourcesCollection(IExtremityLink<A2> extremity2) {
        Set<LinkDescriptor<A1, A2>> associatedLinks = getLinksFromExtremity2Internal(extremity2);
        int lstSize = associatedLinks.size();
        Set<IExtremityLink<A1>> extremities1 = new HashSet<IExtremityLink<A1>>(lstSize);
        for (LinkDescriptor<A1, A2> link : associatedLinks) {
            extremities1.add(link.getExtremity1());
        }
        return extremities1;
    }

    /**
     * DOC amaumont Comment method "getSourcesCollection".
     * 
     * @param extremity2
     * @return
     */
    private Set<IExtremityLink<A2>> getTargetsCollection(IExtremityLink<A1> extremity1) {
        Set<LinkDescriptor<A1, A2>> associatedLinks = getLinksFromExtremity1Internal(extremity1);
        int lstSize = associatedLinks.size();
        Set<IExtremityLink<A2>> extremities1 = new HashSet<IExtremityLink<A2>>(lstSize);
        for (LinkDescriptor<A1, A2> link : associatedLinks) {
            extremities1.add(link.getExtremity2());
        }
        return extremities1;
    }

    /**
     * DOC amaumont Comment method "addLink".
     * 
     * @param link
     */
    public void removeLink(LinkDescriptor<A1, A2> link) {
        currentNumberLinks--;

        links.remove(link);
        IExtremityLink<A1> extremity1 = link.getExtremity1();
        getLinksFromExtremity1Internal(extremity1).remove(link);
        IExtremityLink<A2> extremity2 = link.getExtremity2();
        getLinksFromExtremity2Internal(extremity2).remove(link);
    }

    /**
     * DOC amaumont Comment method "clearLinks".
     */
    public void clearLinks() {
        links.clear();
        extremity1ToLinks.clear();
        extremity2ToLinks.clear();
        currentNumberLinks = 0;
    }

    /**
     * DOC amaumont Comment method "getLinks".
     * 
     * @return
     */
    public List<LinkDescriptor<A1, A2>> getLinks() {
        return this.links;
    }

    /**
     * DOC amaumont Comment method "getSourcesForTarget".
     * 
     * @param extremity2
     */
    public Set<IExtremityLink<A1>> getSourcesForTarget(IExtremityLink<A2> extremity2) {
        return Collections.unmodifiableSet(getSourcesCollection(extremity2));

    }

    // /**
    // * DOC amaumont Comment method "orderLinks".
    // */
    // public void orderLinks() {
    // Collections.sort(links, COMPARATOR);
    // }
    //
    /**
     * Getter for currentNumberLinks.
     * 
     * @return the currentNumberLinks
     */
    public int getCurrentNumberLinks() {
        return this.currentNumberLinks;
    }
    
    public void swapExtremitiesForRelativeLinks(IExtremityLink<A2> previousExtremity, IExtremityLink<A2> newExtremity) {
        Set<IExtremityLink<A2>> extremities2 = associatedObjectToExtremities.remove(previousExtremity.getAssociatedItem());
        associatedObjectToExtremities.put(newExtremity.getAssociatedItem(), extremities2);
        
        Set<LinkDescriptor<A1, A2>> linksFromPrevious = getLinksFromExtremity2(previousExtremity);
        Set<LinkDescriptor<A1, A2>> linksFromNew = getLinksFromExtremity2(newExtremity);
        Set<LinkDescriptor<A1, A2>> linksFromPreviousExtremity = extremity2ToLinks.get(previousExtremity);
        Set<LinkDescriptor<A1, A2>> linksFromNewExtremity = extremity2ToLinks.get(newExtremity);
        
        extremity2ToLinks.put(previousExtremity, linksFromNewExtremity);
        extremity2ToLinks.put(newExtremity, linksFromPreviousExtremity);
        
        for (LinkDescriptor<A1, A2> linkDescriptorPrevious : linksFromPrevious) {
            linkDescriptorPrevious.setExtremity2(newExtremity);
        }
        for (LinkDescriptor<A1, A2> linkDescriptorNew : linksFromNew) {
            linkDescriptorNew.setExtremity2(previousExtremity);
        }

    }
    
}
