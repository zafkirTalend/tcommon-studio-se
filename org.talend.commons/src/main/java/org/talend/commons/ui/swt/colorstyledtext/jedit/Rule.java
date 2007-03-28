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
package org.talend.commons.ui.swt.colorstyledtext.jedit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.talend.commons.ui.swt.colorstyledtext.scanner.ColoringScanner;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class Rule {

    protected String name;

    protected Map types;

    protected KeywordMap keywords;

    protected List orderdList;

    protected Mode mode;

    protected char escape;

    protected boolean highlightDigits = false;

    protected boolean ignoreCase = true;

    protected String digitRE;

    protected String defaultTokenType = ColoringScanner.NULL;

    public static final String DEFAULT_NAME = "MAIN";

    private static final KeywordMap EMPTY_MAP = new KeywordMap(false);

    public static Rule newRule(Mode mode, String name, boolean highlightDigits, boolean ignoreCase, String digitRE, char escape,
            String defaultTokenType) {

        Rule newRule = new Rule(name);
        newRule.mode = mode;
        newRule.highlightDigits = highlightDigits;
        newRule.ignoreCase = ignoreCase;
        newRule.digitRE = digitRE;
        newRule.escape = escape;
        newRule.defaultTokenType = defaultTokenType;
        return newRule;
    }

    public Rule(String name) {
        super();
        types = new HashMap();
        orderdList = new ArrayList();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @SuppressWarnings("unchecked")
    public void add(Type type) {
        orderdList.add(type);
        List all = (List) types.get(type.getColor());
        if (all == null) {
            all = new ArrayList();
            types.put(type.getColor(), all);
        }
        all.add(type);
    }

    public List get(String type) {
        List all = (List) types.get(type);
        if (all == null) {
            return Collections.EMPTY_LIST;
        }
        return all;
    }

    public String toString() {
        return "Rule [" + name + "]";
    }

    public KeywordMap getKeywords() {
        if (keywords == null) {
            return EMPTY_MAP;
        }
        return keywords;
    }

    public List getTypes() {
        return orderdList;
    }

    public void setKeywords(KeywordMap keywordMap) {
        this.keywords = keywordMap;
    }

    public String getDefaultTokenType() {
        return defaultTokenType;
    }

    public char getEscape() {
        return escape;
    }

    public boolean getIgnoreCase() {
        return ignoreCase;
    }

    public boolean getHighlightDigits() {
        return highlightDigits;
    }

    public Mode getMode() {
        return mode;
    }

}
