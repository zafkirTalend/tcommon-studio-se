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
package org.talend.core.model.metadata;

/**
 * DOC qwei class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ææäº, 29 ä¹æ 2006) nrousseau $
 * 
 */
public class TalendTypePrecisionLength {

    private String talendtype;

    private int lengthMax;

    private int lengthMin;

    private int precMax;

    private int precMin;

    public TalendTypePrecisionLength(String talendtype, int lengthMax, int lengthMin, int precMax, int precMin) {

        this.talendtype = talendtype;
        this.lengthMax = lengthMax;
        this.lengthMin = lengthMin;
        this.precMax = precMax;
        this.precMin = precMin;

    }

    /**
     * Getter for talendtype.
     * 
     * @return the talendtype
     */
    public String getTalendtype() {
        return this.talendtype;
    }

    /**
     * Sets the talendtype.
     * 
     * @param talendtype the talendtype to set
     */
    public void setTalendtype(String talendtype) {
        this.talendtype = talendtype;
    }

    /**
     * Getter for lengthMax.
     * 
     * @return the lengthMax
     */
    public int getLengthMax() {
        return this.lengthMax;
    }

    /**
     * Sets the lengthMax.
     * 
     * @param lengthMax the lengthMax to set
     */
    public void setLengthMax(int lengthMax) {
        this.lengthMax = lengthMax;
    }

    /**
     * Getter for lengthMin.
     * 
     * @return the lengthMin
     */
    public int getLengthMin() {
        return this.lengthMin;
    }

    /**
     * Sets the lengthMin.
     * 
     * @param lengthMin the lengthMin to set
     */
    public void setLengthMin(int lengthMin) {
        this.lengthMin = lengthMin;
    }

    /**
     * Getter for precMax.
     * 
     * @return the precMax
     */
    public int getPrecMax() {
        return this.precMax;
    }

    /**
     * Sets the precMax.
     * 
     * @param precMax the precMax to set
     */
    public void setPrecMax(int precMax) {
        this.precMax = precMax;
    }

    /**
     * Getter for precMin.
     * 
     * @return the precMin
     */
    public int getPrecMin() {
        return this.precMin;
    }

    /**
     * Sets the precMin.
     * 
     * @param precMin the precMin to set
     */
    public void setPrecMin(int precMin) {
        this.precMin = precMin;
    }

}
