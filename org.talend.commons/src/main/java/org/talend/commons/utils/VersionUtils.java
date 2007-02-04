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
package org.talend.commons.utils;

import java.util.StringTokenizer;

import org.talend.commons.i18n.internal.Messages;

/**
 * Represents a version. Contents a major and a minor version.<br/>
 * 
 * $Id: Version.java 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class VersionUtils {

    public static final String DEFAULT_VERSION = "0.1";

    /**
     * 
     * @author smallet
     * 
     */
    private static class Version implements Comparable<Version> {

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
            this();
            this.major = major;
            this.minor = minor;
        }

        public Version(String version) {
            StringTokenizer stringTokenizer = new StringTokenizer(version, LEVEL_SEPARATOR);
            try {
                this.major = Integer.parseInt(stringTokenizer.nextToken());
                this.minor = Integer.parseInt(stringTokenizer.nextToken());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(Messages.getString("VersionUtils.Version.Error2", version), e); //$NON-NLS-1$
            }
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

        @Override
        public String toString() {
            return major + LEVEL_SEPARATOR + minor;
        }

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

    }

    public static int compareTo(String arg0, String arg1) {
        return new Version(arg0).compareTo(new Version(arg1));
    }

    public static String upMinor(String version) {
        Version toReturn = new Version(version);
        toReturn.upMinor();
        return toReturn.toString();
    }

    public static String upMajor(String version) {
        Version toReturn = new Version(version);
        toReturn.upMajor();
        return toReturn.toString();
    }
}
