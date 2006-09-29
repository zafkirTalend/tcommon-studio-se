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
package org.talend.core.model.general;

import java.util.StringTokenizer;

import org.apache.commons.collections.keyvalue.MultiKey;

/**
 * Represents a version. Contents a major and a minor version.<br/>
 * 
 * $Id$
 * 
 */
public class Version implements Comparable<Version> {

    private static final String LEVEL_SEPARATOR = ".";

    private int major = 0;

    private int minor = 1;

    /**
     * Empty constructor.
     */
    public Version() {
    }

    /**
     * Version initialized constructor.
     * 
     * @param major
     * @param minor
     */
    public Version(int major, int minor) {
        super();
        this.major = major;
        this.minor = minor;
    }

    public Version(String version) {
        StringTokenizer stringTokenizer = new StringTokenizer(version, LEVEL_SEPARATOR);
        try {
            this.major = Integer.parseInt(stringTokenizer.nextToken());
            this.minor = Integer.parseInt(stringTokenizer.nextToken());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Malformed string " + version + " (attending M.m)", e);
        }
    }

    /**
     * Returns the version like <i>major.minor</i>.
     * 
     * @return the string represents this version
     */
    public String toString() {
        return major + LEVEL_SEPARATOR + minor;
    }

    public void upMajor() {
        this.major++;
        this.minor = 0;
    }

    public void upMinor() {
        this.minor++;
    }

    /**
     * Getter for major.
     * 
     * @return the major
     */
    public int getMajor() {
        return this.major;
    }

    /**
     * Getter for minor.
     * 
     * @return the minor
     */
    public int getMinor() {
        return this.minor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Version arg0) {
        if (this.equals(arg0)) {
            return 0;
        }

        if (this.getMajor() == arg0.getMajor()) {
            Integer m1 = this.getMinor();
            Integer m2 = arg0.getMinor();
            return m1.compareTo(m2);
        } else {
            Integer m1 = this.getMajor();
            Integer m2 = arg0.getMajor();
            return m1.compareTo(m2);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object arg0) {
        if (arg0 == null) {
            return this == null;
        }
        if (!(arg0 instanceof Version)) {
            return false;
        }
        Version toCompare = (Version) arg0;
        return toCompare.getMajor() == this.getMajor() && toCompare.getMinor() == this.getMinor();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return major * 1000 + minor;
    }

    public static void main(String[] args) {
        Version v1 = new Version(1, 3);
        Version v2 = new Version("1.3");
        System.out.println(v1.equals(v2));

        MultiKey k1 = new MultiKey(new Integer(8), v1);
        MultiKey k2 = new MultiKey(new Integer(8), v2);
        System.out.println(k1.equals(k2));
        System.out.println(k1.hashCode() + "-" + k2.hashCode());
    }
}
