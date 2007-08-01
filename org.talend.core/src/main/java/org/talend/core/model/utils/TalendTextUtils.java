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

import java.util.StringTokenizer;

import org.eclipse.swt.graphics.RGB;
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

    public static final String SQL_BUILDER_TITLE_COMP_PREFIX = "SQL Builder [Component Mode] - Job:";

    public static final String SQL_BUILDER_TITLE_COMP_NAME = " - Component:";

    public static final String SQL_BUILDER_TITLE_REP = "SQL Builder [Repository Mode]";

    public static final String SQL_BUILDER_TITLE_COMP_MODPREFIX = "SQL Builder - Component Mode - Job:";

    public static final String SINGLE_QUOTE = "'"; //$NON-NLS-1$

    public static final String ANTI_QUOTE = "`";

    public static final String QUOTATION_MARK = "\""; //$NON-NLS-1$

    public static final String LBRACKET = "[";

    public static final String RBRACKET = "]";

    private static final int LINE_MAX_NUM = 100;

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

    public static String addSQLQuotes(String text) {
        ECodeLanguage language = LanguageManager.getCurrentLanguage();

        switch (language) {
        case JAVA:
            return addSQLQuotes(text, QUOTATION_MARK);
        default: // PERL
            return addSQLQuotes(text, SINGLE_QUOTE);
        }
    }

    private static String addSQLQuotes(String text, String quoteStyle) {
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
        return widenSQLRestrict(newString, quoteStyle);
    }

    /**
     * DOC qiang.zhang Comment method "widenRestrict".
     * 
     * @param newString
     * @param quoteStyle
     * @return
     */
    private static String widenSQLRestrict(String newString, String quoteStyle) {
        String after = "";
        final String[] split = newString.split("\n");
        for (int i = 0; i < split.length; i++) {
            String string = split[i];
            if (i == 0) {
                after += getAfterString(quoteStyle, string);
            } else {
                after += getAfterString(quoteStyle, "\n" + string);
            }
        }
        return after;
    }

    /**
     * DOC qiang.zhang Comment method "getAfterString".
     * 
     * @param quoteStyle
     * @param string
     * @return
     */
    private static String getAfterString(String quoteStyle, String string) {
        String after = "";
        if (string.length() > LINE_MAX_NUM) {
            String substring = string.substring(0, LINE_MAX_NUM);
            substring = substring.substring(0, getLastWord(substring, quoteStyle));
            after += substring + "\n";
            after += getAfterString(quoteStyle, string.substring(substring.length()));
        } else {
            after += string;
        }
        return after;
    }

    /**
     * DOC qiang.zhang Comment method "getLastWord".
     * 
     * @param substring
     * @param quoteStyle
     * @return
     */
    private static int getLastWord(String substring, String quoteStyle) {
        int lastIndexOf = substring.lastIndexOf(" ");
        int lastIndexOf2 = substring.lastIndexOf(quoteStyle);
        if (lastIndexOf2 > 1) {
            String s = substring.substring(lastIndexOf2 - 1, lastIndexOf2);
            if (s.equals("\\")) {
                lastIndexOf2 = lastIndexOf2 - 1;
            }
        }
        final int lastIndexOf3 = substring.lastIndexOf(",");
        lastIndexOf = lastIndexOf < lastIndexOf2 ? lastIndexOf2 : lastIndexOf;
        lastIndexOf = lastIndexOf < lastIndexOf3 ? lastIndexOf3 : lastIndexOf;
        return lastIndexOf;
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
        case INTERBASE:
            return QUOTATION_MARK;
        case SQLITE:
            return QUOTATION_MARK;
        case FIREBIRD:
            return QUOTATION_MARK;
        case INFORMIX:
            return QUOTATION_MARK;
        case ACCESS:
            return QUOTATION_MARK;
        case TERADATA:
            return QUOTATION_MARK;
            // case JAVADB_DERBYCLIENT:
            // return QUOTATION_MARK;
            // case JAVADB_JCCJDBC:
            // return QUOTATION_MARK;
            // case JAVADB_EMBEDED:
            // return QUOTATION_MARK;
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

    public static RGB stringToRGB(String string) {
        RGB rgb;
        int r = 0;
        int g = 0;
        int b = 0;
        StringTokenizer token = new StringTokenizer(string, ";");
        if (token.hasMoreTokens()) {
            r = new Integer(token.nextToken());
        }
        if (token.hasMoreTokens()) {
            g = new Integer(token.nextToken());
        }
        if (token.hasMoreTokens()) {
            b = new Integer(token.nextToken());
        }
        rgb = new RGB(r, g, b);
        return rgb;
    }

    public static String removeQuotes(String text) {
        ECodeLanguage language = LanguageManager.getCurrentLanguage();

        switch (language) {
        case JAVA:
            return removeQuotes(text, QUOTATION_MARK);
        default: // PERL
            return removeQuotes(text, SINGLE_QUOTE);
        }
    }

    /**
     * qzhang Comment method "removeQuotes".
     * 
     * @param text
     * @param quotation_mark2
     * @return
     */
    private static String removeQuotes(String text, String quotation) {
        if (text.length() > 1) {
            String substring = text.substring(0, 1);
            if (quotation.equals(substring)) {
                text = text.substring(1, text.length());
            }
            substring = text.substring(text.length() - 1, text.length());
            if (quotation.equals(substring)) {
                text = text.substring(0, text.length() - 1);
            }
        }
        return text;
    }
}
