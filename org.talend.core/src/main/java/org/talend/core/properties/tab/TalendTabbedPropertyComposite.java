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

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyTitle;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * yzhang class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40Z nrousseau $
 * 
 */
public class TalendTabbedPropertyComposite extends Composite {

    private TabbedPropertySheetWidgetFactory factory;

    private Composite mainComposite;

    private Composite leftComposite;

    private ScrolledComposite scrolledComposite;

    private Composite tabComposite;

    private TabbedPropertyTitle title;

    private TalendTabbedPropertyList listComposite;

    private boolean displayTitle;

    /**
     * Constructor for a TabbedPropertyComposite
     * 
     * @param parent the parent widget.
     * @param factory the widget factory.
     * @param displayTitle if <code>true</code>, then the title bar will be displayed.
     */
    public TalendTabbedPropertyComposite(Composite parent, TabbedPropertySheetWidgetFactory factory, boolean displayTitle) {
        super(parent, SWT.NO_FOCUS);
        this.factory = factory;
        this.displayTitle = displayTitle;

        createMainComposite();
    }

    /**
     * Create the main composite.
     */
    protected void createMainComposite() {
        mainComposite = factory.createComposite(this, SWT.NO_FOCUS);
        mainComposite.setLayout(new FormLayout());
        FormData formData = new FormData();
        formData.left = new FormAttachment(0, 0);
        formData.right = new FormAttachment(100, 0);
        formData.top = new FormAttachment(0, 0);
        formData.bottom = new FormAttachment(100, 0);
        mainComposite.setLayoutData(formData);

        createMainContents();
    }

    /**
     * Create the contents in the main composite.
     */
    protected void createMainContents() {
        if (displayTitle) {
            title = new TabbedPropertyTitle(mainComposite, factory);

            FormData data = new FormData();
            data.left = new FormAttachment(0, 0);
            data.right = new FormAttachment(100, 0);
            data.top = new FormAttachment(0, 0);
            title.setLayoutData(data);
        }

        leftComposite = factory.createComposite(mainComposite, SWT.NO_FOCUS);
        leftComposite.setLayout(new FormLayout());

        scrolledComposite = factory.createScrolledComposite(mainComposite, SWT.H_SCROLL | SWT.V_SCROLL | SWT.NO_FOCUS);
        scrolledComposite.setLayout(new FormLayout());

        FormData formData = new FormData();
        formData.left = new FormAttachment(leftComposite, 0);
        formData.right = new FormAttachment(100, 0);
        if (displayTitle) {
            formData.top = new FormAttachment(title, 0);
        } else {
            formData.top = new FormAttachment(0, 0);
        }
        formData.bottom = new FormAttachment(100, 0);
        scrolledComposite.setLayoutData(formData);

        formData = new FormData();
        formData.left = new FormAttachment(0, 0);
        formData.right = new FormAttachment(scrolledComposite, 0);
        if (displayTitle) {
            formData.top = new FormAttachment(title, 0);
        } else {
            formData.top = new FormAttachment(0, 0);
        }
        formData.bottom = new FormAttachment(100, 0);
        leftComposite.setLayoutData(formData);

        tabComposite = factory.createComposite(scrolledComposite, SWT.NO_FOCUS);
        tabComposite.setLayout(new FormLayout());
        tabComposite.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));

        scrolledComposite.setContent(tabComposite);
        scrolledComposite.setAlwaysShowScrollBars(false);
        scrolledComposite.setExpandVertical(true);
        scrolledComposite.setExpandHorizontal(true);

        listComposite = new TalendTabbedPropertyList(leftComposite, factory);
        formData = new FormData();
        formData.left = new FormAttachment(0, 0);
        formData.right = new FormAttachment(100, 0);
        formData.top = new FormAttachment(0, 0);
        formData.bottom = new FormAttachment(100, 0);
        listComposite.setLayoutData(formData);

        FormData data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(0, 0);
        data.bottom = new FormAttachment(100, 0);
        tabComposite.setLayoutData(data);
    }

    /**
     * Get the tabbed property list, which is the list of tabs on the left hand side of this composite.
     * 
     * @return the tabbed property list.
     */
    public TalendTabbedPropertyList getList() {
        return listComposite;
    }

    /**
     * Get the tabbed property title bar.
     * 
     * @return the tabbed property title bar or <code>null</code> if not used.
     */
    public TabbedPropertyTitle getTitle() {
        return title;
    }

    /**
     * Get the tab composite where sections display their property contents.
     * 
     * @return the tab composite.
     */
    public Composite getTabComposite() {
        return tabComposite;
    }

    /**
     * Get the scrolled composite which surrounds the title bar and tab composite.
     * 
     * @return the scrolled composite.
     */
    public ScrolledComposite getScrolledComposite() {
        return scrolledComposite;
    }

    /**
     * Get the widget factory.
     * 
     * @return the widget factory.
     */
    protected TabbedPropertySheetWidgetFactory getFactory() {
        return factory;
    }

    /**
     * @see org.eclipse.swt.widgets.Widget#dispose()
     */
    public void dispose() {
        listComposite.dispose();
        if (displayTitle) {
            title.dispose();
        }
        super.dispose();
    }
}