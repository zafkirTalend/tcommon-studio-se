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
package org.talend.commons.ui.swt.colorstyledtext.jedit;

import java.util.HashMap;
import java.util.Map;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class KeywordMap {

    protected Map keywords;

    protected boolean ignoreCase;

    public KeywordMap(boolean ignoreCase) {
        super();
        keywords = new HashMap();
        this.ignoreCase = ignoreCase;
    }

    @SuppressWarnings("unchecked")
    public void put(Object key, Object value) {
        keywords.put(key, value);
    }

    public boolean ignoreCase() {
        return ignoreCase;
    }

    public String[] get(Object key) {
        return (String[]) keywords.get(key);
    }

}
