// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.rcp.intro.contentProvider;

import java.io.PrintWriter;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.intro.config.IIntroContentProviderSite;
import org.eclipse.ui.intro.config.IIntroXHTMLContentProvider;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.ui.branding.IBrandingService;
import org.talend.rcp.intro.starting.StartingConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 
 * DOC wchen class global comment. Detailled comment
 */
public class StartingContentProvider implements IIntroXHTMLContentProvider {

    public void init(IIntroContentProviderSite site) {
        // TODO Auto-generated method stub

    }

    public void createContent(String id, PrintWriter out) {
        // TODO Auto-generated method stub

    }

    public void createContent(String id, Composite parent, FormToolkit toolkit) {
        // TODO Auto-generated method stub

    }

    public void dispose() {
        // TODO Auto-generated method stub

    }

    public void createContent(String id, Element parent) {
        IBrandingService service = (IBrandingService) GlobalServiceRegister.getDefault().getService(IBrandingService.class);
        if (service != null) {
            Document dom = parent.getOwnerDocument();
            if (StartingConstants.PRODUCT_NAME.equals(id)) {
                parent.appendChild(dom.createTextNode(service.getProductName()));
            } else if (StartingConstants.OPTION_NAME.equals(id)) {
                parent.appendChild(dom.createTextNode(service.getOptionName()));
            }
        }
    }

}
