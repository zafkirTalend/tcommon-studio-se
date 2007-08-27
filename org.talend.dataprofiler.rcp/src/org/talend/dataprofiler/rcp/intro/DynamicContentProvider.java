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
package org.talend.dataprofiler.rcp.intro;

import java.io.PrintWriter;
import java.util.Date;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.intro.config.IIntroContentProviderSite;
import org.eclipse.ui.intro.config.IIntroXHTMLContentProvider;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


//

/**
 * DOC rli class global comment. Detailled comment <br/>
 * 
 */
public class DynamicContentProvider implements IIntroXHTMLContentProvider {

    public void init(IIntroContentProviderSite site) {
    }

    public void createContent(String id, PrintWriter out) {
    }

    public void createContent(String id, Composite parent, FormToolkit toolkit) {
    }

    private String getCurrentTimeString() {
        StringBuffer content = new StringBuffer("Dynamic content from Intro ContentProvider: ");
        content.append("Current time is: ");
        content.append(new Date(System.currentTimeMillis()));
        return content.toString();
    }

    public void createContent(String id, Element parent) {
        Document dom = parent.getOwnerDocument();
        Element para = dom.createElement("p");
        para.setAttribute("id", "someDynamicContentId");
        para.appendChild(dom.createTextNode(getCurrentTimeString()));
        parent.appendChild(para);

    }

    public void dispose() {

    }

}
