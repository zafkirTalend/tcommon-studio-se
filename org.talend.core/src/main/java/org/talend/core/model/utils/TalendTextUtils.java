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
package org.talend.core.model.utils;

import org.talend.core.context.RepositoryContext;
import org.talend.core.i18n.Messages;
import org.talend.core.language.ECodeLanguage;


/**
 * DOC nrousseau  class global comment. Detailled comment
 * <br/>
 *
 * $Id: talend-code-templates.xml 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 *
 */
public class TalendTextUtils {
    public static final String SINGLE_QUOTE = "'"; //$NON-NLS-1$
    
    public static final String QUOTATION_MARK = "\""; //$NON-NLS-1$
    
    public static String addQuotes(String text) {
        ECodeLanguage language = ((RepositoryContext) org.talend.core.CorePlugin.getContext().getProperty(
                org.talend.core.context.Context.REPOSITORY_CONTEXT_KEY)).getProject().getLanguage();
        
        switch (language) {
        case JAVA:
            return addQuotes(text, QUOTATION_MARK);
        default: // PERL
            return addQuotes(text, SINGLE_QUOTE);
        }
    }
    
    public static String addQuotes(String text, String quoteStyle) {
        String newString;
        
        if (quoteStyle.equals(SINGLE_QUOTE)) {
            newString = SINGLE_QUOTE + checkStringQuotes(text) + SINGLE_QUOTE;
        } else {
            newString = QUOTATION_MARK + checkStringQuotationMarks(text) + QUOTATION_MARK;
        }
        return newString;
    }
    
    private static String checkStringQuotes(String str) {
        if (str == null) {
            return ""; //$NON-NLS-1$
        }
        return str.replace("'", "\\'"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    private static String checkStringQuotationMarks(String str) {
        if (str == null) {
            return ""; //$NON-NLS-1$
        }
        return str.replace("\"", "\\\""); //$NON-NLS-1$ //$NON-NLS-2$
    }
}
