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
package org.talend.commons.utils.data.text.rules;

import org.eclipse.jface.text.rules.IToken;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public interface IStringTokenScanner {

    /**
     * Configures the scanner by providing access to the document range that should be scanned.
     * 
     * @param text to scan
     * @param offset the offset of the document range to scan
     * @param length the length of the document range to scan
     */
    void setRange(String text, int offset, int length);

    /**
     * Returns the next token in the document.
     * 
     * @return the next token in the document
     */
    IToken nextToken();

    /**
     * Returns the offset of the last token read by this scanner.
     * 
     * @return the offset of the last token read by this scanner
     */
    int getTokenOffset();

    /**
     * Returns the length of the last token read by this scanner.
     * 
     * @return the length of the last token read by this scanner
     */
    int getTokenLength();
}
