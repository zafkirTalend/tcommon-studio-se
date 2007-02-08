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
package org.talend.core.i18n;

import java.util.Locale;

import org.talend.core.CorePlugin;
import org.talend.core.prefs.ITalendCorePrefConstants;

/**
 * This class is used for getting local language. <br/>
 * 
 * $Id: LocalLanguageFactory.java 2007-2-8,上午10:55:05 ftang $
 * 
 */
public class LocalLanguageFactory {

    /**
     * Gets the default language of product.
     * 
     * @return an instance of <code>Locale</code>
     */
    public static Locale getLocalLanguage() {
        String language = CorePlugin.getDefault().getPreferenceStore().getString(
                ITalendCorePrefConstants.LANGUAGE_SELECTOR);
        if (language == null || language.length() == 0) {
            return Locale.ENGLISH;
        }

        if (language.equals(Locale.CHINESE.getLanguage())) {
            return Locale.CHINESE;
        } else if (language.equals(Locale.ENGLISH.getLanguage())) {
            return Locale.ENGLISH;
        } else if (language.equals(Locale.FRENCH.getLanguage())) {
            return Locale.FRENCH;
        }
        return Locale.ENGLISH;
    }
}
