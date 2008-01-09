// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.language;

import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class LanguageManager {

    private static ECodeLanguage currentLanguage;

    public static void reset() {
        currentLanguage = null;
    }

    public static ECodeLanguage getCurrentLanguage() {
        if (CorePlugin.getContext().getProperty(Context.REPOSITORY_CONTEXT_KEY) == null) {
            CorePlugin.getDefault().getRepositoryService().initializeForTalendStartupJob();

            String lanType = CorePlugin.getDefault().getPluginPreferences().getString(CorePlugin.PROJECT_LANGUAGE_TYPE);

            for (ECodeLanguage language : ECodeLanguage.values()) {
                if (language.getName().equals(lanType)) {
                    return language;
                }
            }

            // the first time run talend in eclipse
            // TODO
            return ECodeLanguage.JAVA;
        }

        try {

            if (((RepositoryContext) CorePlugin.getContext().getProperty(Context.REPOSITORY_CONTEXT_KEY)).getProject() != null) {
                currentLanguage = ((RepositoryContext) CorePlugin.getContext().getProperty(Context.REPOSITORY_CONTEXT_KEY))
                        .getProject().getLanguage();
            } else {
                currentLanguage = ECodeLanguage.PERL;
            }
        } catch (RuntimeException e) {
            // should be run only when testing
            e.printStackTrace();
            currentLanguage = ECodeLanguage.PERL;
        }
        return currentLanguage;
    }
}
