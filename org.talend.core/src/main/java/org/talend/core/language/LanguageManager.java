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
            CorePlugin.getDefault().getRepositoryServie().initializeForTalendStartupJob();

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
            currentLanguage = ((RepositoryContext) CorePlugin.getContext().getProperty(Context.REPOSITORY_CONTEXT_KEY))
                    .getProject().getLanguage();
        } catch (RuntimeException e) {
            // should be run only when testing
            e.printStackTrace();
            currentLanguage = ECodeLanguage.PERL;
        }
        return currentLanguage;
    }
}
