// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
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
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.internal.intro.impl.model.util.ModelUtil;
import org.eclipse.ui.intro.config.IIntroContentProviderSite;
import org.eclipse.ui.intro.config.IIntroXHTMLContentProvider;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.model.general.Project;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.core.prefs.ITalendCorePrefConstants;
import org.talend.repository.ProjectManager;
import org.talend.repository.model.ProxyRepositoryFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * wchen class global comment. Detailled comment
 */
public class DynamicContentProvider implements IIntroXHTMLContentProvider {

    private static boolean isInternetChecked;

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.intro.config.IIntroXHTMLContentProvider#createContent(java.lang.String, org.w3c.dom.Element)
     */
    public void createContent(String id, Element parent) {
        // content for latest modified jobs and business models
        List<IRepositoryObject> latestItems = new ArrayList<IRepositoryObject>();
        Document dom = parent.getOwnerDocument();
        String url = "";
        if (ERepositoryObjectType.PROCESS.name().equals(id)) {
            latestItems = getLatestModifiedItems(ERepositoryObjectType.PROCESS, 8);
            url = "http://org.eclipse.ui.intro/runAction?pluginId=org.talend.designer.core&"
                    + "class=org.talend.designer.core.ui.action.EditProcess&"
                    + "id=org.talend.designer.core.actions.editprocess&nodeId=";
            if (latestItems.size() == 0) {

                parent.appendChild(dom.createElement("br"));
            }
        } else if (ERepositoryObjectType.BUSINESS_PROCESS.name().equals(id)) {
            latestItems = getLatestModifiedItems(ERepositoryObjectType.BUSINESS_PROCESS, 8);
            url = "http://org.eclipse.ui.intro/runAction?pluginId=org.talend.designer.business.diagram&"
                    + "class=org.talend.designer.business.diagram.custom.actions.OpenDiagramAction&"
                    + "id=org.talend.designer.business.diagram.Action2&nodeId=";
        } else if ("CUSTOMER_PAGE".equals(id)) {
            if (!isInternetChecked) {
                createOnlinePage(dom, parent);
            }
            isInternetChecked = !isInternetChecked;
        }

        for (IRepositoryObject object : latestItems) {
            Element hyperlink = dom.createElement("a");
            hyperlink.setAttribute("href", url + object.getId());
            hyperlink.setAttribute("title", "Modified at " + object.getModificationDate() + " by " + object.getAuthor() + "\n"
                    + "Created at " + object.getCreationDate() + " by" + object.getAuthor());
            hyperlink.appendChild(dom.createTextNode(object.getLabel() + " " + object.getVersion()));
            parent.appendChild(hyperlink);
            parent.appendChild(dom.createElement("br"));
        }
        // content for always welcome check box
        if ("ALWAYS_WELCOME".equals(id)) {
            IPreferenceStore store = CorePlugin.getDefault().getPreferenceStore();
            boolean showIntroConfig = store.getBoolean(ITalendCorePrefConstants.ALWAYS_WELCOME);
            url = "location.href='http://org.eclipse.ui.intro/runAction?pluginId=org.talend.rcp&"
                    + "class=org.talend.rcp.intro.AlwaysWelcomeAction'";
            Element input = dom.createElement("input");
            input.setAttribute("type", "checkbox");
            if (!showIntroConfig) {
                input.setAttribute("checked", "checked");
            }
            input.setAttribute("onclick", url);

            input.appendChild(dom.createTextNode("Do not display again\u00a0"));
            parent.appendChild(input);
        }

    }

    private void createOnlinePage(Document dom, Element parent) {

        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL("http://www.talend.com");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setReadTimeout(1000);
            urlConnection.getInputStream();
            setDIVStyle(dom, true);
        } catch (Exception e) {
            setDIVStyle(dom, false);
            return;
        } finally {
            urlConnection.disconnect();
        }
        // separator
        Element separatorTD = dom.createElement("td");
        separatorTD.setAttribute("bgcolor", "#676767");
        separatorTD.appendChild(dom.createElement("div"));
        parent.appendChild(separatorTD);

        // online content
        Element tdElem = dom.createElement("td");
        parent.appendChild(tdElem);
        Element div = dom.createElement("div");
        div.setAttribute("style", "overflow:auto;height:400px;width:220px;padding-left:20px;");
        tdElem.appendChild(div);
        Element p = dom.createElement("p");
        div.appendChild(p);
        Element strong = dom.createElement("strong");
        p.appendChild(strong);
        strong.appendChild(dom.createTextNode("Talend Receives Intelligent Enterprise Editors' Choice Award - February 2010"));
        p.appendChild(dom.createElement("br"));
        Element img = dom.createElement("img");
        img.setAttribute("src", "http://www.talend.com/img/home/2010_IE_edchoice_ctw.jpg");
        img.setAttribute("alt", "Talend Receives Intelligent Enterprise Editors' Choice Award");
        img.setAttribute("vspace", "1");
        img.setAttribute("align", "left");
        p.appendChild(img);
        p.appendChild(dom.createTextNode("With \"its sights set on MDM\" "
                + "Talend received the Intelligent Enterprise Editors' " + "Choice Award for \"democratizing data integration\" "
                + "and \"making data integration and data quality management more affordable.\""));

        p = dom.createElement("p");
        div.appendChild(p);
        Element a = dom.createElement("a");
        a.setAttribute("href", "http://intelligent-enterprise.informationweek.com/"
                + "showArticle.jhtml;jsessionid=OJ1CNZZDCSTHNQE1GHPSKHWATMY32JVN?articleID=222880034&amp;pgno=5");
        a.setAttribute("target", "_blank");
        a.setAttribute("title", "Talend Receives Intelligent Enterprise Editors' Choice Award");
        a.appendChild(dom.createTextNode("Read more"));
        p.appendChild(a);

        p = dom.createElement("p");
        div.appendChild(p);

        p = dom.createElement("p");
        div.appendChild(p);
        strong = dom.createElement("strong");
        strong.appendChild(dom.createTextNode("Talend named Visionary in Gartner Magic Quadrant - December 2009"));
        p.appendChild(strong);
        img = dom.createElement("img");
        img.setAttribute("src", "http://www.talend.com/img/home/logo_gartner.jpg");
        img.setAttribute("alt", "Talend UK Partner Summit 2010");
        img.setAttribute("vspace", "1");
        img.setAttribute("align", "left");
        p.appendChild(img);

        p = dom.createElement("p");
        div.appendChild(p);
        p.appendChild(dom.createTextNode("Talend is positioned as Visionary in the Gartner "
                + "Magic Quadrant for Data Integration Tools."
                + " Talend is happy to provide you complimentary access to the full report."));

        p = dom.createElement("p");
        div.appendChild(p);
        a = dom.createElement("a");
        a.setAttribute("href",
                "http://www.talend.com/press/Talend-Positioned-as-a-Visionary-in-Magic-Quadrant-for-Data-Integration-Tools.php");
        a.setAttribute("title", "Talend named Visionary in Gartner Magic Quadrant");
        a.appendChild(dom.createTextNode("Read more"));
        p.appendChild(a);
        p.appendChild(dom.createTextNode(" | "));
        a = dom.createElement("a");
        a.setAttribute("href", "http://www.talend.com/campaign/campaign.php?id=137&amp;src=HomepageSpecial");
        a.setAttribute("title", "Request Access to Gartner's Magic Quadrant for Data Integration Tools");
        a.appendChild(dom.createTextNode("Access the report now!"));
        p.appendChild(a);

        p = dom.createElement("p");
        div.appendChild(p);

        p = dom.createElement("p");
        div.appendChild(p);
        strong = dom.createElement("strong");
        strong.appendChild(dom.createTextNode("Talend Named Open Source Company to Watch by Network World - November 2009"));
        p.appendChild(strong);

        img = dom.createElement("img");
        img.setAttribute("src", "http://www.talend.com/img/home/logo_networkworld.jpg");
        img.setAttribute("alt", "Talend Named Open Source Company to Watch by Network World");
        img.setAttribute("vspace", "1");
        img.setAttribute("align", "left");
        p.appendChild(img);

        p = dom.createElement("p");
        div.appendChild(p);
        p.appendChild(dom.createTextNode("Network World named Talend an Open Source "
                + "Company to Watch. According to the publication, "
                + "Talend is worth watching because it started with data integration"
                + " then added data quality and Master Data Management (MDM), which is the first open source MDM."));

        p = dom.createElement("p");
        div.appendChild(p);
        a = dom.createElement("a");
        a.setAttribute("href", "http://www.networkworld.com/slideshows/2009/110909-open-source-companies-list.html#slide11");
        a.setAttribute("target", "_blank");
        a.setAttribute("title", "Talend Named Open Source Company to Watch by Network World");
        a.appendChild(dom.createTextNode("Read more"));
        p.appendChild(a);

        p = dom.createElement("p");
        div.appendChild(p);

        p = dom.createElement("p");
        div.appendChild(p);
        strong = dom.createElement("strong");
        strong.appendChild(dom.createTextNode("Talend MDM is Available - Download it Now!"));
        p.appendChild(strong);
        p.appendChild(dom.createElement("br"));
        img = dom.createElement("img");
        img.setAttribute("src", "http://www.talend.com/img/home/talendMDM.jpg");
        img.setAttribute("alt", "Talend MDM is Available - Download it Now!");
        img.setAttribute("vspace", "2");
        img.setAttribute("align", "left");
        p.appendChild(img);
        p.appendChild(dom.createTextNode("The most complete open source master "
                + "data management solution, Talend MDM provides Enterprise-grade"
                + " features to synchronize, quality-assure and provide a single version "
                + "of master data throughout and across the organization's systems."));

        p = dom.createElement("p");
        div.appendChild(p);
        a = dom.createElement("a");
        a.setAttribute("href", "http://www.talend.com/master-data-management/talend-mdm.php");
        a.setAttribute("title", "Discover its features and benefits");
        a.appendChild(dom.createTextNode("Discover its features and benefits"));
        p.appendChild(a);
        p.appendChild(dom.createTextNode(", "));
        a = dom.createElement("a");
        a.setAttribute("href", "http://www.talend.com/download_form.php?cont=mdm&amp;src=HomepageSpecial");
        a.setAttribute("title", "download Talend MDM Community Edition");
        a.appendChild(dom.createTextNode("download Talend MDM Community Edition"));
        p.appendChild(a);
        p.appendChild(dom.createTextNode(" or "));
        a = dom.createElement("a");
        a.setAttribute("href", "http://www.talend.com/campaign/campaign.php?id=135&amp;src=HomepageSpecial");
        a.setAttribute("title", "download Talend MDM Community Edition");
        a.appendChild(dom.createTextNode("request more information"));
        p.appendChild(a);

        p = dom.createElement("p");
        div.appendChild(p);
    }

    private List<IRepositoryObject> getLatestModifiedItems(ERepositoryObjectType type, int count) {
        List<IRepositoryObject> latestItems = new ArrayList<IRepositoryObject>();
        try {
            Project currentProject = ProjectManager.getInstance().getCurrentProject();
            if (currentProject != null) {
                List<IRepositoryObject> all = ProxyRepositoryFactory.getInstance().getAll(currentProject, type);
                IRepositoryObject[] data = new IRepositoryObject[all.size()];
                all.toArray(data);
                for (int i = 0; i < data.length && i < count; i++) {
                    for (int j = data.length - 1; j > i; j--) {
                        Date modificationDate = data[j].getModificationDate();
                        Date modificationDate2 = data[j - 1].getModificationDate();
                        if (modificationDate == null) {
                            modificationDate = data[j].getCreationDate();
                        }
                        if (modificationDate2 == null) {
                            modificationDate2 = data[j - 1].getCreationDate();
                        }
                        if (modificationDate.after(modificationDate2)) {
                            IRepositoryObject temp = data[j - 1];
                            data[j - 1] = data[j];
                            data[j] = temp;
                        }
                    }
                    latestItems.add(data[i]);
                }

            }
            return latestItems;
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }
        return latestItems;
    }

    private void setDIVStyle(Document dom, boolean online) {
        NodeList elementsByTagName = dom.getElementsByTagName("div");
        Node[] array = ModelUtil.getArray(elementsByTagName);
        Element leftDiv = null;
        Element rightDiv = null;
        for (int i = 0; i < array.length; i++) {
            Element node = (Element) array[i];
            if ("div_left_part".equals(node.getAttribute("id"))) {
                leftDiv = node;
            } else if ("div_right_part".equals(node.getAttribute("id"))) {
                rightDiv = node;
            }

        }

        if (leftDiv != null) {
            if (online) {
                leftDiv.setAttribute("class", "left_1");
            } else {
                leftDiv.setAttribute("class", "left");
            }
        }

        if (rightDiv != null) {
            if (online) {
                rightDiv.setAttribute("class", "right_1");
            } else {
                rightDiv.setAttribute("class", "right");
            }
        }
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.intro.config.IIntroContentProvider#init(org.eclipse.ui.intro.config.IIntroContentProviderSite)
     */
    public void init(IIntroContentProviderSite site) {
    }

}
