// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.presentation.onboarding.ui.html;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.eclipse.ui.internal.intro.impl.html.IIntroHTMLConstants;
import org.eclipse.ui.internal.intro.impl.model.IntroContentProvider;
import org.eclipse.ui.internal.intro.impl.model.loader.IntroContentParser;
import org.eclipse.ui.intro.config.IIntroContentProviderSite;
import org.talend.presentation.onboarding.i18n.Messages;
import org.talend.presentation.onboarding.ui.managers.OnBoardingManager;
import org.talend.presentation.onboarding.ui.runtimedata.OnBoardingDocBean;
import org.talend.presentation.onboarding.ui.runtimedata.OnBoardingPresentationData;
import org.talend.presentation.onboarding.utils.OnBoardingConstants;
import org.talend.presentation.onboarding.utils.OnBoardingUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * DOC talend class global comment. Detailled comment
 */
@SuppressWarnings("restriction")
public class HtmlContentHelper {

    private OnBoardingManager onBoardingManager;

    public HtmlContentHelper(OnBoardingManager obManager) {
        onBoardingManager = obManager;
    }

    public String getHtmlContent(URL htmlUrl) throws IOException {
        String content = ""; //$NON-NLS-1$
        if (htmlUrl != null) {
            String result = htmlUrl.toExternalForm();
            if (result.startsWith("file:/")) { //$NON-NLS-1$
                if (result.startsWith("file:///") == false) { //$NON-NLS-1$
                    result = "file:///" + result.substring(6); //$NON-NLS-1$
                }
            }

            IntroContentParser parser = new IntroContentParser(result);
            Document dom = parser.getDocument();
            if (dom != null) {
                resolveInternationalization(dom);
                resolveDynamicContent(dom, null);
                content = IntroContentParser.convertToString(dom);
            }
        }

        return content;
    }

    private void resolveInternationalization(Document dom) {
        NodeList internationals = dom.getElementsByTagNameNS("*", //$NON-NLS-1$
                OnBoardingConstants.KEY_INTERNATIONAL);

        Node[] nodes = OnBoardingUtils.getArray(internationals);
        for (Node node : nodes) {
            Element internationalElement = (Element) node;
            internationalElement.getParentNode().replaceChild(
                    dom.createTextNode(Messages.getString(internationalElement.getAttribute(IIntroHTMLConstants.ATTRIBUTE_ID))),
                    internationalElement);
        }
    }

    private Document resolveDynamicContent(Document dom, IIntroContentProviderSite site) {

        // get all content provider elements in DOM.
        NodeList contentProviders = dom.getElementsByTagNameNS("*", //$NON-NLS-1$
                IntroContentProvider.TAG_CONTENT_PROVIDER);

        List<OnBoardingPresentationData> presDatas = onBoardingManager.getPresentationDatas();
        if (presDatas == null) {
            return dom;
        }
        int index = onBoardingManager.getCurrentSelectedPresentationDataIndex();
        if (index < 0) {
            return dom;
        }
        OnBoardingPresentationData presData = onBoardingManager.getCurrentSelectedPresentationData();
        if (presData == null) {
            index = presDatas.size() - 1;
            onBoardingManager.setCurrentSelectedPresentationDataIndex(index);
            presData = onBoardingManager.getCurrentSelectedPresentationData();
        }
        OnBoardingDocBean docBean = presData.getDocBean();

        // get the array version of the nodelist to work around DOM api design.
        // Node[] nodes = ModelUtil.getArray(contentProviders);
        Node[] nodes = OnBoardingUtils.getArray(contentProviders);
        for (Node node : nodes) {
            Element contentProviderElement = (Element) node;
            String id = contentProviderElement.getAttribute(IIntroHTMLConstants.ATTRIBUTE_ID);
            // if (OnBoardingConstants.HTML_CSS_PATH.equals(id)) {
            // Element link = dom.createElement(IIntroHTMLConstants.ELEMENT_LINK);
            //
            // Attr rel = dom.createAttribute(IIntroHTMLConstants.ATTRIBUTE_RELATIONSHIP);
            // rel.setValue(IIntroHTMLConstants.LINK_REL);
            // Attr href = dom.createAttribute(IIntroHTMLConstants.ATTRIBUTE_HREF);
            // href.setValue(OnBoardingUtils.getResourceLocalURL(OnBoardingConstants.ON_BOARDING_VIEW_CSS_PATH).toString());
            //
            // link.setAttributeNode(rel);
            // link.setAttributeNode(href);
            //
            // node.getParentNode().replaceChild(link, node);
            // } else
            if (OnBoardingConstants.HTML_STEP.equals(id)) {
                node.getParentNode().replaceChild(dom.createTextNode("" + (index + 1)), node); //$NON-NLS-1$
            } else if (OnBoardingConstants.HTML_TITLE.equals(id)) {
                node.getParentNode().replaceChild(dom.createTextNode(docBean.getTitle()), node);
            } else if (OnBoardingConstants.HTML_CONTENT.equals(id)) {
                node.getParentNode().replaceChild(dom.createTextNode(docBean.getContent()), node);
            } else if (OnBoardingConstants.HTML_BULLETS.equals(id)) {
                Node parent = node.getParentNode();
                parent.removeChild(node);
                int totalSize = presDatas.size();
                for (int i = 0; i < totalSize; i++) {
                    Element li = dom.createElement(OnBoardingConstants.HTML_TAG_LI);
                    Element a = dom.createElement(IIntroHTMLConstants.ELEMENT_ANCHOR);
                    Attr href = dom.createAttribute(IIntroHTMLConstants.ATTRIBUTE_HREF);
                    href.setValue("javascript:void(0);"); //$NON-NLS-1$
                    Attr clazz = dom.createAttribute(IIntroHTMLConstants.ATTRIBUTE_CLASS);
                    if (index == i) {
                        clazz.setValue("active"); //$NON-NLS-1$
                    } else {
                        clazz.setValue(""); //$NON-NLS-1$
                    }
                    Attr onClick = dom.createAttribute("onClick"); //$NON-NLS-1$
                    onClick.setValue("showPage(" + i + ");"); //$NON-NLS-1$//$NON-NLS-2$

                    a.setAttributeNode(href);
                    a.setAttributeNode(clazz);
                    a.setAttributeNode(onClick);
                    a.setTextContent(" "); //$NON-NLS-1$

                    li.appendChild(a);
                    parent.appendChild(li);
                }
            } else if (OnBoardingConstants.HTML_MANAGER_ID.equals(id)) {
                node.getParentNode().replaceChild(dom.createTextNode(onBoardingManager.getManagerId()), node);
            }

        }
        return dom;
    }

}
