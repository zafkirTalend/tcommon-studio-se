// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.talend.commons.ui.swt.colorstyledtext.jedit.KeywordMap;
import org.talend.commons.ui.swt.colorstyledtext.jedit.Mode;
import org.talend.commons.ui.swt.colorstyledtext.jedit.Modes;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;

/**
 * DOC hcw class global comment. Detailled comment
 */
public class KeywordsValidator {

    private static Map<ECodeLanguage, Set<String>> keywords = new HashMap<ECodeLanguage, Set<String>>();

    public static boolean isKeyword(String word) {
        return isKeyword(LanguageManager.getCurrentLanguage(), word);
    }

    public static boolean isKeyword(ECodeLanguage lang, String word) {
        Set<String> words = getKeywords(lang);
        return words.contains(word);
    }

    public static Set<String> getKeywords(ECodeLanguage lang) {
        Set<String> words = keywords.get(lang);
        if (words == null) {
            words = new HashSet<String>();

            Mode mode = Modes.getMode(lang.getName() + ".xml"); //$NON-NLS-1$
            KeywordMap keywordMap = mode.getDefaultRuleSet().getKeywords();
            words.addAll(Arrays.asList(keywordMap.get("KEYWORD1"))); //$NON-NLS-1$
            words.addAll(Arrays.asList(keywordMap.get("KEYWORD2"))); //$NON-NLS-1$
            words.addAll(Arrays.asList(keywordMap.get("KEYWORD3"))); //$NON-NLS-1$
            words.addAll(Arrays.asList(keywordMap.get("LITERAL2"))); //$NON-NLS-1$
            words.addAll(Arrays.asList(keywordMap.get("INVALID"))); //$NON-NLS-1$         
            keywords.put(lang, words);
        }
        return words;
    }

    public static void main(String[] args) {
        System.out.println(isKeyword(ECodeLanguage.JAVA, "class")); //$NON-NLS-1$
    }
}
