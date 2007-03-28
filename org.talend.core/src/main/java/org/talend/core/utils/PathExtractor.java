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
package org.talend.core.utils;

import org.eclipse.core.runtime.Path;
import org.talend.commons.utils.StringUtils;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;


/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 * $Id$
 *
 */
public class PathExtractor {

    /**
     * DOC amaumont Comment method "extractPath".
     * @param text
     * @return 
     */
    public static String extractPath(String filePathExpression) {

        ECodeLanguage currentLanguage = LanguageManager.getCurrentLanguage();
        String extractedFilePath;
        if (currentLanguage == ECodeLanguage.PERL) {
            String extractedFilePathWithQuote = StringUtils.extractFirstDelimitedString(filePathExpression, "'"); //$NON-NLS-1$
            String extractedFilePathWithDoubleQuote = StringUtils.extractFirstDelimitedString(filePathExpression, "\""); //$NON-NLS-1$
            if (extractedFilePathWithDoubleQuote.length() > extractedFilePathWithQuote.length()) {
                extractedFilePath = extractedFilePathWithDoubleQuote;
            } else {
                extractedFilePath = extractedFilePathWithQuote;
            }
        } else if (currentLanguage == ECodeLanguage.JAVA) {
            extractedFilePath = StringUtils.extractFirstDelimitedString(filePathExpression, "\""); //$NON-NLS-1$
        } else {
            throw new IllegalStateException("Case not found"); //$NON-NLS-1$
        }

        return extractedFilePath;
    }

}
