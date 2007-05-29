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
package org.talend.core.model.utils;

import org.talend.core.context.RepositoryContext;
import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id: talend-code-templates.xml 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class TalendTextUtils {

    public static final String SINGLE_QUOTE = "'"; //$NON-NLS-1$

    public static final String ANTI_QUOTE = "`";

    public static final String QUOTATION_MARK = "\""; //$NON-NLS-1$

    public static final String LBRACKET = "[";

    public static final String RBRACKET = "]";

    public static String addQuotes(String text) {
        ECodeLanguage language = LanguageManager.getCurrentLanguage();

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
        } else if (quoteStyle.equals(ANTI_QUOTE)) {
            newString = ANTI_QUOTE + checkStringQuotationMarks(text) + ANTI_QUOTE;
        } else if (quoteStyle.equals(LBRACKET) || quoteStyle.equals(RBRACKET)) {
            newString = LBRACKET + checkStringQuotationMarks(text) + RBRACKET;
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

    public static String addQuotesWithSpaceField(String fieldName, String dbType) {
        if (!fieldName.contains(" ")) {
            return fieldName;
        }
        String newFieldName = fieldName;
        newFieldName = addQuotes(newFieldName, getQuoteByDBType(dbType));
        return newFieldName;
    }

    private static boolean isLeft = true;

    public static String getQuoteByDBType(String dbType) {
        EDatabaseTypeName name = EDatabaseTypeName.IBMDB2;
        for (EDatabaseTypeName typename : EDatabaseTypeName.values()) {
            if (typename.getDisplayName().equals(dbType)) {
                name = typename;
                break;
            }
        }
        switch (name) {
        case GODBC:
            return getBracket();
        case IBMDB2:
            return QUOTATION_MARK;
        case INGRES:
            return QUOTATION_MARK;
        case MSODBC:
            return QUOTATION_MARK;
        case MSSQL:
            return QUOTATION_MARK;
        case MYSQL:
            return ANTI_QUOTE;
        case ORACLEFORSID:
            return QUOTATION_MARK;
        case ORACLESN:
            return QUOTATION_MARK;
        case PSQL:
            return QUOTATION_MARK;
        case SYBASEASE:
            return QUOTATION_MARK;
        case SYBASEIQ:
            return QUOTATION_MARK;
        default:
            return QUOTATION_MARK;
        }
    }

    /**
     * qzhang Comment method "getBracket".
     * 
     * @return
     */
    private static String getBracket() {
        if (isLeft) {
            isLeft = false;
            return LBRACKET;
        } else {
            isLeft = true;
            return RBRACKET;
        }
    }

    public static String removeQuotesForField(String text, String dbType) {
        String newText;
        isLeft = true;
        final String quoteByDBType = getQuoteByDBType(dbType);
        if (quoteByDBType.equals(LBRACKET)) {
            if (text.length() > 2) {
                newText = text.substring(1, text.length() - 1);
                if (text.contains(RBRACKET)) {
                    newText = newText.replaceAll(RBRACKET, "");
                } else {
                    newText = text;
                }
            } else {
                newText = text;
            }

        } else {
            newText = text.replaceAll(quoteByDBType, "");
        }
        return newText;
    }

    /**
     * qzhang Comment method "getQuoteByDBType".
     * 
     * @param dbType
     * @param b
     * @return
     */
    public static String getQuoteByDBType(String dbType, boolean b) {
        isLeft = b;
        return getQuoteByDBType(dbType);
    }
}
