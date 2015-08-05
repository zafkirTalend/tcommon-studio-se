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
package org.talend.commons.ui.html;

import org.eclipse.ui.internal.intro.impl.model.url.IntroURLParser;
import org.eclipse.ui.internal.intro.impl.model.util.BundleUtil;
import org.osgi.framework.Bundle;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * DOC talend class global comment. Detailled comment
 */
public class TalendHtmlModelUtil {

    private static String TAG_BODY = "body"; //$NON-NLS-1$

    private static String TAG_HEAD = "head"; //$NON-NLS-1$

    private static String TAG_BASE = "base"; //$NON-NLS-1$

    public static String TAG_DIV = "div"; //$NON-NLS-1$

    public static String TAG_HEAD_LINK = "link"; //$NON-NLS-1$

    private static String TAG_PARAM = "param"; //$NON-NLS-1$

    private static String ATT_SRC = "src"; //$NON-NLS-1$

    private static String ATT_HREF = "href"; //$NON-NLS-1$

    private static String ATT_CITE = "cite"; //$NON-NLS-1$

    private static String ATT_LONGDESC = "longdesc"; //$NON-NLS-1$

    private static String ATT_DATA = "data"; //$NON-NLS-1$

    private static String ATT_CODEBASE = "codebase"; //$NON-NLS-1$

    private static String ATT_VALUE = "value"; //$NON-NLS-1$

    private static String ATT_VALUE_TYPE = "valuetype"; //$NON-NLS-1$

    private static String ATT_REL = "rel"; //$NON-NLS-1$

    private static String ATT_TYPE = "type"; //$NON-NLS-1$

    public static void updateResourceAttributes(Element element, String base, Bundle bundle) {
        // doUpdateResourceAttributes(element, base, bundle);
        NodeList children = element.getElementsByTagName("*"); //$NON-NLS-1$
        for (int i = 0; i < children.getLength(); i++) {
            Element child = (Element) children.item(i);
            doUpdateResourceAttributes(child, base, bundle);
        }
    }

    private static void doUpdateResourceAttributes(Element element, String base, Bundle bundle) {
        qualifyAttribute(element, ATT_SRC, base, bundle);
        qualifyAttribute(element, ATT_HREF, base, bundle);
        qualifyAttribute(element, ATT_CITE, base, bundle);
        qualifyAttribute(element, ATT_LONGDESC, base, bundle);
        qualifyAttribute(element, ATT_CODEBASE, base, bundle);
        qualifyAttribute(element, ATT_DATA, base, bundle);
        qualifyValueAttribute(element, base, bundle);
    }

    private static void qualifyAttribute(Element element, String attributeName, String base, Bundle bundle) {
        if (element.hasAttribute(attributeName)) {
            String attributeValue = element.getAttribute(attributeName);
            if (new IntroURLParser(attributeValue).hasProtocol())
                return;

            // resolve the resource against the nl mechanism.
            String attributePath = BundleUtil.getResolvedResourceLocation(base, attributeValue, bundle);
            element.setAttribute(attributeName, attributePath);
        }
    }

    private static void qualifyValueAttribute(Element element, String base, Bundle bundle) {
        if (element.hasAttribute(ATT_VALUE) && element.hasAttribute(ATT_VALUE_TYPE)
                && element.getAttribute(ATT_VALUE_TYPE).equals("ref") //$NON-NLS-1$
                && element.getLocalName().equals(TAG_PARAM)) {
            String value = element.getAttribute(ATT_VALUE);
            if (new IntroURLParser(value).hasProtocol())
                return;
            // resolve the resource against the nl mechanism.
            String attributePath = BundleUtil.getResolvedResourceLocation(base, value, bundle);
            element.setAttribute(ATT_VALUE, attributePath);
        }
    }

}
