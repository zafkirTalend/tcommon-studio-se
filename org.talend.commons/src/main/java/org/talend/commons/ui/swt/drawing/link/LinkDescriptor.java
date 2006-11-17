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



/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 * $Id$
 * 
 * @param <A1> the attached object of the extremety 1
 * @param <A2> the attached object of the extremety 2
 */
public class LinkDescriptor<A1, A2> {

    private IExtremityLink<A1> extremity1;
    
    private IExtremityLink<A2> extremity2;

    private IDrawableLink drawableLink;
    
    /**
     * DOC amaumont Link constructor comment.
     * @param extremity1
     * @param extremity2
     */
    public LinkDescriptor(IExtremityLink<A1> extremity1, IExtremityLink<A2> extremity2) {
        super();
        this.extremity1 = extremity1;
        this.extremity2 = extremity2;
    }

    
    /**
     * Getter for tip1.
     * @return the tip1
     */
    public IExtremityLink<A1> getExtremity1() {
        return this.extremity1;
    }

    
    /**
     * Sets the tip1.
     * @param tip1 the tip1 to set
     */
    public void setExtremity1(IExtremityLink<A1> tip1) {
        this.extremity1 = tip1;
    }

    
    /**
     * Getter for tip2.
     * @return the tip2
     */
    public IExtremityLink<A2> getExtremity2() {
        return this.extremity2;
    }

    
    /**
     * Sets the tip2.
     * @param tip2 the tip2 to set
     */
    public void setExtremity2(IExtremityLink<A2> tip2) {
        this.extremity2 = tip2;
    }


    
    /**
     * Getter for drawableLink.
     * @return the drawableLink
     */
    public IDrawableLink getDrawableLink() {
        return this.drawableLink;
    }


    
    /**
     * Sets the drawableLink.
     * @param drawableLink the drawableLink to set
     */
    public void setDrawableLink(IDrawableLink drawableLink) {
        this.drawableLink = drawableLink;
    }
    
    

    
    
    
}
