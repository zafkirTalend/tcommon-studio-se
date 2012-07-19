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
package org.talend.rcp.intro.contentProvider;

import java.io.File;
import java.io.PrintWriter;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.intro.config.IIntroContentProviderSite;
import org.talend.rcp.i18n.Messages;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * DOC hcyi class global comment. Detailled comment
 */
public class WelcomePageDynamicContentProvider extends IntroProvider {

    private static final String BROWSER_URL = "http://org.eclipse.ui.intro/runAction?pluginId=org.talend.rcp&class=org.talend.rcp.intro.OpenWebBrowserAction&type="; //$NON-NLS-1$

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.intro.config.IIntroXHTMLContentProvider#createContent(java.lang.String, org.w3c.dom.Element)
     */
    public void createContent(String id, Element parent) {
        Document dom = parent.getOwnerDocument();
        String content = dom.getBaseURI();
        // baseUri like :file:///D:/Talend_trunk_gtk/org.talend.rcp.branding.generic/content/root.xhtml
        if (content != null && !"".equals(content)) {
            File file = new File(content);
            int index = content.indexOf(file.getName());
            content = content.substring(0, index);
        }
        if ("CREATE_NEW_ITEM".equals(id)) {
            createTitlePart(dom, parent, Messages.getString("WelcomePageDynamicContentProvider.CreateNewTitle"));
            // create job
            String text = Messages.getString("WelcomePageDynamicContentProvider.LatestItemsJob.Title");
            String url = "http://org.eclipse.ui.intro/runAction?pluginId=org.talend.designer.core&class=org.talend.designer.core.ui.action.CreateProcess&id=org.talend.designer.core.actions.createprocess&type=PROCESS";
            String title = "Create a data integration process";
            createANewItem(dom, parent, content + "imgs/img_process.jpg", text, title, url);

            // create business
            text = Messages.getString("WelcomePageDynamicContentProvider.CreateNewBusinessModelTitle");
            url = "http://org.eclipse.ui.intro/runAction?pluginId=org.talend.designer.business.diagram&class=org.talend.designer.business.diagram.custom.actions.CreateDiagramAction&id=org.talend.designer.business.diagram.custom.actions.CreateDiagramAction&type=BUSINESS_PROCESS";
            title = "Create a business model";
            createANewItem(dom, parent, content + "imgs/img_businessProcess.jpg", text, title, url);

            // create analysis
            if (isItemShow("ANALYSIS")) {
                text = Messages.getString("WelcomePageDynamicContentProvider.CreateNewAnalysisTitle");
                url = "http://org.eclipse.ui.intro/runAction?pluginId=org.talend.dataprofiler.core&class=org.talend.dataprofiler.core.ui.action.actions.CreateNewAnalysisAction&id=org.talend.dataprofiler.core.ui.action.actions.CreateNewAnalysisAction&type=ANALYSIS";
                title = "Create an analysis";
                createANewItem(dom, parent, content + "imgs/chart_bar.png", text, title, url);
            }

            // create service
            if (isItemShow("SERVICES")) {
                text = Messages.getString("WelcomePageDynamicContentProvider.CreateNewServiceTitle");
                url = "http://org.eclipse.ui.intro/runAction?pluginId=org.talend.repository.services&class=org.talend.repository.services.action.CreateESBAction&id=org.talend.repository.services.action.CreateESBAction&type=SERVICES";
                title = "Create a service";
                createANewItem(dom, parent, content + "imgs/img_service.png", text, title, url);
            }

            // create route
            if (isItemShow("ROUTE")) {
                text = Messages.getString("WelcomePageDynamicContentProvider.CreateNewRouteTitle");
                url = "http://org.eclipse.ui.intro/runAction?pluginId=org.talend.camel.designer&class=org.talend.camel.designer.ui.CreateCamelProcess&id=org.talend.camel.designer.ui.CreateCamelProcess&type=ROUTES";
                title = "Create a route";
                createANewItem(dom, parent, content + "imgs/img_route.png", text, title, url);
            }

        } else if ("DOCUMENTATIONTITLE".equals(id)) {
            Element pDoc = dom.createElement("p");
            pDoc.setAttribute("class", "style_1 style_2 style_3");
            pDoc.setAttribute("style", "padding-top:10px;");
            pDoc.appendChild(dom.createTextNode(Messages.getString("WelcomePageDynamicContentProvider.DocumentationTitle")));
            parent.appendChild(pDoc);
            Element blockquoteDoc = dom.createElement("blockquote");
            Element blockquotePDoc = dom.createElement("p");
            Element blockquoteA1Doc = dom.createElement("a");
            blockquoteA1Doc.setAttribute("href", BROWSER_URL + "showUserGuide");
            blockquoteA1Doc.appendChild(dom.createTextNode(Messages
                    .getString("WelcomePageDynamicContentProvider.DocumentationUserGuideTitle")));

            Element blockquoteA2Doc = dom.createElement("a");
            blockquoteA2Doc.setAttribute("href", BROWSER_URL + "showReferenceGuide");
            blockquoteA2Doc.appendChild(dom.createTextNode(Messages
                    .getString("WelcomePageDynamicContentProvider.DocumentationReferenceGuideTitle")));
            blockquotePDoc.appendChild(blockquoteA1Doc);
            blockquotePDoc.appendChild(dom.createElement("br"));
            blockquotePDoc.appendChild(blockquoteA2Doc);
            blockquoteDoc.appendChild(blockquotePDoc);
            parent.appendChild(blockquoteDoc);
        } else if ("GETTINGSTARTEDTITLE".equals(id)) {
            Element pGS = dom.createElement("p");
            pGS.setAttribute("class", "style_1 style_2 style_3");
            pGS.setAttribute("style", "padding-top:10px;");
            pGS.appendChild(dom.createTextNode(Messages.getString("WelcomePageDynamicContentProvider.GettingStartedTitle")));
            parent.appendChild(pGS);
            Element blockquoteGS = dom.createElement("blockquote");
            Element blockquotePGS = dom.createElement("p");
            Element blockquoteA1GS = dom.createElement("a");
            blockquoteA1GS.setAttribute("href", BROWSER_URL + "showTutorials");
            blockquoteA1GS.appendChild(dom.createTextNode(Messages
                    .getString("WelcomePageDynamicContentProvider.GettingStartedTutorialsTitle")));

            Element blockquoteA2GS = dom.createElement("a");
            blockquoteA2GS.setAttribute("href", BROWSER_URL + "showForums");
            blockquoteA2GS.appendChild(dom.createTextNode(Messages
                    .getString("WelcomePageDynamicContentProvider.GettingStartedForumsTitle")));

            Element blockquoteA3GS = dom.createElement("a");
            blockquoteA3GS.setAttribute("href", BROWSER_URL + "showExchange");
            blockquoteA3GS.appendChild(dom.createTextNode(Messages
                    .getString("WelcomePageDynamicContentProvider.GettingStartedExchangeTitle")));

            blockquotePGS.appendChild(blockquoteA1GS);
            blockquotePGS.appendChild(dom.createTextNode(": "
                    + Messages.getString("WelcomePageDynamicContentProvider.GettingStartedTutorialsBrief")));
            blockquotePGS.appendChild(dom.createElement("br"));
            blockquotePGS.appendChild(blockquoteA2GS);
            blockquotePGS.appendChild(dom.createTextNode(": "
                    + Messages.getString("WelcomePageDynamicContentProvider.GettingStartedForumsBrief")));
            blockquotePGS.appendChild(dom.createElement("br"));
            blockquotePGS.appendChild(blockquoteA3GS);
            blockquotePGS.appendChild(dom.createTextNode(": "
                    + Messages.getString("WelcomePageDynamicContentProvider.GettingStartedExchangeBrief")));
            blockquoteGS.appendChild(blockquotePGS);
            parent.appendChild(blockquoteGS);
        }
    }

    private void createTitlePart(Document dom, Element parent, String title) {
        Element span = dom.createElement("span");
        span.setAttribute("class", "style_1 style_2 style_3");
        span.appendChild(dom.createTextNode(title));
        parent.appendChild(span);
    }

    private void createANewItem(Document dom, Element parent, String imgPath, String text, String title, String url) {
        parent.appendChild(dom.createElement("br"));
        Element imgJob = dom.createElement("img");
        imgJob.setAttribute("style", "padding-top:10px;margin-left:15px;");
        imgJob.setAttribute("src", imgPath);
        parent.appendChild(imgJob);
        Element hyperlinkJob = dom.createElement("a");
        hyperlinkJob.setAttribute("class", "xh");
        hyperlinkJob.setAttribute("title", title);
        hyperlinkJob.setAttribute("href", url);
        Element span2 = dom.createElement("span");
        span2.setAttribute("class", "style_3");
        span2.appendChild(dom.createTextNode(text));
        hyperlinkJob.appendChild(span2);
        parent.appendChild(hyperlinkJob);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.intro.config.IIntroContentProvider#init(org.eclipse.ui.intro.config.IIntroContentProviderSite)
     */
    public void init(IIntroContentProviderSite site) {
        // TODO Auto-generated method stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.intro.config.IIntroContentProvider#createContent(java.lang.String, java.io.PrintWriter)
     */
    public void createContent(String id, PrintWriter out) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.intro.config.IIntroContentProvider#createContent(java.lang.String,
     * org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit)
     */
    public void createContent(String id, Composite parent, FormToolkit toolkit) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.intro.config.IIntroContentProvider#dispose()
     */
    public void dispose() {
        // TODO Auto-generated method stub

    }
}
