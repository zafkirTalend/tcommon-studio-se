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
package org.talend.amc.model;

import java.util.Date;

/**
 * Store informations (status, dates, duration) for a job execution.
 */
public class JobExecution {

    private String pid;

    private String name;

    private Date begin;

    private Date end;

    private String message;

    private Long duration;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.pid == null) ? 0 : this.pid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JobExecution other = (JobExecution) obj;
        if (this.pid == null) {
            if (other.pid != null)
                return false;
        } else if (!this.pid.equals(other.pid))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return name + " [" + pid + "]:Status=" + getStatus() + ", Begin=" + DateFormat.format(begin) + ", End="
                + DateFormat.format(end) + ", Duration=" + duration;
    }

    public String getPid() {
        return this.pid;
    }

    public void setPid(String id) {
        this.pid = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        if (begin != null && end == null)
            return "Running";
        return message;
    }

    public Date getBegin() {
        return this.begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return this.end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Long getDuration() {
        return this.duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
