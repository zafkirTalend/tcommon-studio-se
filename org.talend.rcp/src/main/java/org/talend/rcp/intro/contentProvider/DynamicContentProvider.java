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
package org.talend.rcp.intro.contentProvider;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
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

/**
 * wchen class global comment. Detailled comment
 */
public class DynamicContentProvider implements IIntroXHTMLContentProvider {

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
            latestItems = getLatestModifiedItems(ERepositoryObjectType.PROCESS, 5);
            url = "http://org.eclipse.ui.intro/runAction?pluginId=org.talend.designer.core&"
                    + "class=org.talend.designer.core.ui.action.EditProcess&"
                    + "id=org.talend.designer.core.actions.editprocess&nodeId=";
            if (latestItems.size() == 0) {

                parent.appendChild(dom.createElement("br"));
            }
        } else if (ERepositoryObjectType.BUSINESS_PROCESS.name().equals(id)) {
            latestItems = getLatestModifiedItems(ERepositoryObjectType.BUSINESS_PROCESS, 5);
            url = "http://org.eclipse.ui.intro/runAction?pluginId=org.talend.designer.business.diagram&"
                    + "class=org.talend.designer.business.diagram.custom.actions.OpenDiagramAction&"
                    + "id=org.talend.designer.business.diagram.Action2&nodeId=";
        }
        for (IRepositoryObject object : latestItems) {
            Element hyperlink = dom.createElement("a");
            hyperlink.setAttribute("href", url + object.getId());
            hyperlink.setAttribute("title", "Modified at " + object.getModificationDate() + " by " + object.getAuthor() + "\n"
                    + "Created at " + object.getCreationDate() + " by" + object.getAuthor());
            hyperlink.appendChild(dom.createTextNode(object.getLabel()));
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
            input.appendChild(dom.createTextNode("Do not display again"));
            parent.appendChild(input);
        }

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
                        if (data[j].getModificationDate().after(data[j - 1].getModificationDate())) {
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
        // TODO Auto-generated method stub

    }

}
