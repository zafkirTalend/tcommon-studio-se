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
package org.talend.core.model.metadata;

import org.talend.core.i18n.Messages;

/**
 * Enum for available Code Languages in the application.
 * 
 * $Id$
 * 
 */
public enum EMetadataEncoding {

    UTF8("UTF-8", "Unicode 2.0 UTF-8"), //$NON-NLS-1$ //$NON-NLS-2$
    UTF16("UTF-16", "Unicode 2.0 (16 bit)"), //$NON-NLS-1$ //$NON-NLS-2$
    UTF16LE("UTF-16LE", "Unicode (16 bit)"), //$NON-NLS-1$ //$NON-NLS-2$
    UTF16BE("UTF-16BE", "Unicode BigEndian (16 bit)"), //$NON-NLS-1$ //$NON-NLS-2$
    UTF7("UTF-7", "Unicode 2.0 UTF-7"), //$NON-NLS-1$ //$NON-NLS-2$
    ISO88591("ISO-8859-1", "(Latin-1) Europe occidentale, Am�rique latine"), //$NON-NLS-1$ //$NON-NLS-2$
    ISO88592("ISO-8859-2", "(Latin-2) Europe centrale et orientale"), //$NON-NLS-1$ //$NON-NLS-2$
    ISO88593("ISO-8859-3", "(Latin-3) Europe du sud-est"), //$NON-NLS-1$ //$NON-NLS-2$
    ISO88594("ISO-8859-4", "(Latin-4) Scandinavie, pays Baltes"), //$NON-NLS-1$ //$NON-NLS-2$
    ISO88595("ISO-8859-5", "Cyrillique"), //$NON-NLS-1$
    ISO88596("ISO-8859-6", "Arabe"), //$NON-NLS-1$
    ISO88597("ISO-8859-7", "Grec"), //$NON-NLS-1$
    ISO88598("ISO-8859-8", "H�breu"), //$NON-NLS-1$
    ISO88599("ISO-8859-9", "Turc"), //$NON-NLS-1$
    ISO885910("ISO-8859-10", "Langues lapone, nordique, esquimaude"), //$NON-NLS-1$
    windows1252("windows-1252", "Microsoft (Latin-1)"), //$NON-NLS-1$
    BIG5("BIG5", "Big Five (Chinois traditionnel)"), //$NON-NLS-1$
    GB18030("GB18030", "Standard national Chinois"), //$NON-NLS-1$
    GB2312("GB2312", "Chinois"), //$NON-NLS-1$
    EUC_CN("EUC_CN", "Chinois simplifi� (Code-CN Unix �tendu)"); //$NON-NLS-1$

    /*
     * EUC_JP Code-JP Unix �tendu Japonais EUC_KR Code-KR Unix �tendu Cor�en EUC_TW Code-TW Unix �tendu Chinois
     * traditionnel, ta�wanais GBK Standard national �tendu Chinois simplifi� ISO 2022-CN ("GB") GB 2312-80
     */

    private EMetadataEncoding(String name, String label) {
        this.name = name;
        this.label = label;
    }

    public static EMetadataEncoding getMetadataEncoding(String name) {
        for (EMetadataEncoding metadataEncoding : EMetadataEncoding.values()) {
            if (metadataEncoding.getName().equals(name)) {
                return metadataEncoding;
            }
        }
        return UTF8;
    }

    private String name;

    private String label;

    /**
     * Getter for Label.
     * 
     * @return the Label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the Label.
     * 
     * @param extension the extension to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Getter for name.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     * 
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
