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
package org.talend.core.model.properties;



/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 */
public class CronTalendTrigger extends TalendTrigger {

    private String daysOfWeek;
    private String daysOfMonth;
    private String months;
    private String years;
    private String seconds;
    private String minutes;
    private String hours;
    
    /**
     * Getter for daysOfWeek.
     * @return the daysOfWeek
     */
    public String getDaysOfWeek() {
        return this.daysOfWeek;
    }
    
    /**
     * Sets the daysOfWeek.
     * @param daysOfWeek the daysOfWeek to set
     */
    public void setDaysOfWeek(String daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }
    
    /**
     * Getter for daysOfMonth.
     * @return the daysOfMonth
     */
    public String getDaysOfMonth() {
        return this.daysOfMonth;
    }
    
    /**
     * Sets the daysOfMonth.
     * @param daysOfMonth the daysOfMonth to set
     */
    public void setDaysOfMonth(String daysOfMonth) {
        this.daysOfMonth = daysOfMonth;
    }
    
    /**
     * Getter for months.
     * @return the months
     */
    public String getMonths() {
        return this.months;
    }
    
    /**
     * Sets the months.
     * @param months the months to set
     */
    public void setMonths(String months) {
        this.months = months;
    }
    
    /**
     * Getter for years.
     * @return the years
     */
    public String getYears() {
        return this.years;
    }
    
    /**
     * Sets the years.
     * @param years the years to set
     */
    public void setYears(String years) {
        this.years = years;
    }
    
    /**
     * Getter for seconds.
     * @return the seconds
     */
    public String getSeconds() {
        return this.seconds;
    }
    
    /**
     * Sets the seconds.
     * @param seconds the seconds to set
     */
    public void setSeconds(String seconds) {
        this.seconds = seconds;
    }
    
    /**
     * Getter for minutes.
     * @return the minutes
     */
    public String getMinutes() {
        return this.minutes;
    }
    
    /**
     * Sets the minutes.
     * @param minutes the minutes to set
     */
    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }
    
    /**
     * Getter for hours.
     * @return the hours
     */
    public String getHours() {
        return this.hours;
    }
    
    /**
     * Sets the hours.
     * @param hours the hours to set
     */
    public void setHours(String hours) {
        this.hours = hours;
    }

    
    
    
    
}


