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
package org.talend.commons.ui.swt.colorstyledtext.rules;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.text.rules.IWordDetector;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class ColoringWordDetector implements IWordDetector {

    protected Set<Integer> charMap = new HashSet<Integer>();

    public boolean isWordStart(char c) {
        return Character.isLetter(c) || '_' == c || isTextSequencePart(c);
    }

    private boolean isTextSequencePart(char c) {
        return charMap.contains(new Integer(c));
    }

    public void addWord(String word) {
        charMap.add(new Integer(word.charAt(0)));
    }

    public boolean isWordPart(char c) {
        /*
         * added the dot so properties file would mark_following correctly when they path contained dots. For example
         * a.b.c=124
         */
        return isWordStart(c) || Character.isDigit(c) || '.' == c;
    }
}
