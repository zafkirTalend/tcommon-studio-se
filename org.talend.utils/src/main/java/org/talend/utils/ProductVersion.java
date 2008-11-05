// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***/
public class ProductVersion implements Comparable<ProductVersion> {

    private static Pattern pattern = Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+).*");

    private int major;

    private int minor;

    private int micro;

    public ProductVersion(int major, int micro, int minor) {
        super();
        this.major = major;
        this.micro = micro;
        this.minor = minor;
    }

    public static ProductVersion fromString(String version) {
        Matcher matcher = pattern.matcher(version);
        if (matcher.matches()) {
            int major = Integer.parseInt(matcher.group(1));
            int minor = Integer.parseInt(matcher.group(2));
            int micro = Integer.parseInt(matcher.group(3));
            return new ProductVersion(major, minor, micro);
        }
        return null;
    }

    public int compareTo(ProductVersion other) {
        int diff = major - other.major;
        if (diff != 0) {
            return diff;
        }
        diff = minor - other.minor;
        if (diff != 0) {
            return diff;
        }
        diff = micro - other.micro;
        if (diff != 0) {
            return diff;
        }
        return 0;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + major;
        result = prime * result + micro;
        result = prime * result + minor;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ProductVersion other = (ProductVersion) obj;
        if (major != other.major) {
            return false;
        }
        if (micro != other.micro) {
            return false;
        }
        if (minor != other.minor) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(major);
        stringBuilder.append(".");
        stringBuilder.append(minor);
        stringBuilder.append(".");
        stringBuilder.append(micro);
        return stringBuilder.toString();
    }
}
