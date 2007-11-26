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
package org.talend.core.properties.tab;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.tabbed.ITabItem;
import org.talend.core.model.process.EComponentCategory;
import org.talend.core.model.process.Element;

/**
 * DOC yzhang class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40Z nrousseau $
 * 
 */
public class TalendPropertyTabDescriptor implements ITabItem {

    private Element element;

    private EComponentCategory category;

    /**
     * yzhang TalendPropertyTabDescriptor constructor comment.
     */
    public TalendPropertyTabDescriptor(EComponentCategory category) {
        this.category = category;
    }

    /**
     * Getter for category.
     * 
     * @return the category
     */
    public EComponentCategory getCategory() {
        return this.category;
    }

    /**
     * Sets the element.
     * 
     * @param element the element to set
     */
    public void setElement(Element element) {
        this.element = element;
    }

    /**
     * Getter for element.
     * 
     * @return the element
     */
    public Element getElement() {
        return this.element;
    }

    public String getLabel() {
        return category.getTitle();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.views.properties.tabbed.ITabItem#getImage()
     */
    public Image getImage() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.views.properties.tabbed.ITabItem#getText()
     */
    public String getText() {
        return category.getTitle();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.views.properties.tabbed.ITabItem#isIndented()
     */
    public boolean isIndented() {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.views.properties.tabbed.ITabItem#isSelected()
     */
    public boolean isSelected() {
        return false;
    }

}
