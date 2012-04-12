// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.rcp.intro;

import java.util.Properties;

import org.eclipse.swt.program.Program;
import org.eclipse.ui.intro.IIntroSite;
import org.eclipse.ui.intro.config.IIntroAction;

/**
 * DOC hcyi class global comment. Detailled comment
 */
public class OpenWebBrowserAction implements IIntroAction {

    private static final String USERGUIDE_URL = "http://www.talend.com/resources/documentation.php#TOS_UG"; //$NON-NLS-1$

    private static final String REFERENCEGUIDE_URL = "http://www.talend.com/resources/documentation.php#TOS_RG"; //$NON-NLS-1$

    private static final String TUTORIALS_URL = "http://www.talendforge.org/tutorials"; //$NON-NLS-1$

    private static final String FORUMS_URL = "http://www.talendforge.org/forum"; //$NON-NLS-1$

    private static final String EXCHANGE_URL = "http://www.talendforge.org/exchange"; //$NON-NLS-1$

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.intro.config.IIntroAction#run(org.eclipse.ui.intro.IIntroSite, java.util.Properties)
     */
    public void run(IIntroSite site, Properties params) {
        if (params == null) {
            return;
        }
        Object type = params.get("type");
        if (type != null) {
            if ("showUserGuide".equals(type.toString())) {
                Program.launch(USERGUIDE_URL);
            } else if ("showReferenceGuide".equals(type.toString())) {
                Program.launch(REFERENCEGUIDE_URL);
            } else if ("showTutorials".equals(type.toString())) {
                Program.launch(TUTORIALS_URL);
            } else if ("showForums".equals(type.toString())) {
                Program.launch(FORUMS_URL);
            } else if ("showExchange".equals(type.toString())) {
                Program.launch(EXCHANGE_URL);
            }
        }
    }
}
