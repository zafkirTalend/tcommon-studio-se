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
package org.talend.rcp.intro.linksbar;

import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.PluginChecker;
import org.talend.core.model.general.IExchangeService;
import org.talend.rcp.Activator;
import org.talend.rcp.i18n.Messages;

/**
 * DOC xtan class global comment. Detailled comment <br/>
 * 
 */
public class LinksToolbarItem extends ContributionItem {

    private ToolItem toolitem;

    private static final String LEARN_URL = "<a href=\"http://www.talendforge.org/tutorials/menu.php\">Learn</a>"; //$NON-NLS-1$

    private static final String ASK_URL = "<a href=\"http://www.talendforge.org/forum/\">Ask</a>"; //$NON-NLS-1$

    // private static final String SHARE_URL = "<a href=\"http://www.talendforge.org/exchange/\">Share</a>"; //$NON-NLS-1$

    private static final String UPGRADE_URL = "<a href=\"http://www.talend.com/whyupgrade.php\">Upgrade!</a>"; //$NON-NLS-1$

    private static final String EXCHANGE_URL = "<a href=\"http://www.talendforge.org/exchange/index.php\">Exchange</a>"; //$NON-NLS-1$

    private static ImageRegistry registry = new ImageRegistry();

    @Override
    public void fill(ToolBar parent, int index) {
        // parent.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));

        toolitem = new ToolItem(parent, SWT.SEPARATOR, index);
        Control control = createControl(parent);
        toolitem.setControl(control);
        toolitem.setWidth(control.computeSize(SWT.DEFAULT, SWT.DEFAULT).x);
    }

    protected Control createControl(Composite parent) {

        final Composite composite = new Composite(parent, SWT.NONE);

        GridLayout layout = new GridLayout(!PluginChecker.isTIS() ? 8 : 6, false);
        layout.marginHeight = 0;
        composite.setLayout(layout);

        // 1.learn
        Label learnLabel = new Label(composite, SWT.NONE);

        if (registry.get("demo") == null) {
            registry.put("demo", Activator.getImageDescriptor("icons/demo.png").createImage());
        }

        learnLabel.setImage(registry.get("demo")); //$NON-NLS-1$

        Link learn = new Link(composite, SWT.NONE);
        learn.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
        learn.setText(LEARN_URL);
        learn.setToolTipText(Messages.getString("LinksToolbarItem_0"));

        learn.addListener(SWT.Selection, new Listener() {

            public void handleEvent(Event event) {
                openBrower(event.text);
            }
        });

        // 2.ask
        Label askLabel = new Label(composite, SWT.NONE);

        if (registry.get("protocol") == null) {
            registry.put("protocol", Activator.getImageDescriptor("icons/irc_protocol.png").createImage());
        }
        askLabel.setImage(registry.get("protocol")); //$NON-NLS-1$

        Link ask = new Link(composite, SWT.NONE);
        ask.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
        ask.setText(ASK_URL);
        ask.setToolTipText(Messages.getString("LinksToolbarItem_7"));

        ask.addListener(SWT.Selection, new Listener() {

            public void handleEvent(Event event) {
                openBrower(event.text);
            }
        });

        if (!PluginChecker.isTIS()) {
            // 3.upgrade
            Label upgradeLabel = new Label(composite, SWT.NONE);
            if (registry.get("wizard") == null) {
                registry.put("wizard", Activator.getImageDescriptor("icons/wizard.png").createImage());
            }
            upgradeLabel.setImage(registry.get("wizard")); //$NON-NLS-1$
            Link upgrade = new Link(composite, SWT.NONE);
            upgrade.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
            upgrade.setText(UPGRADE_URL);
            upgrade.setToolTipText(Messages.getString("LinksToolbarItem_11"));

            upgrade.addListener(SWT.Selection, new Listener() {

                public void handleEvent(Event event) {
                    openBrower(event.text);
                }
            });
        }

        // 4. Link to Talend Exchange
        Label exchangeLabel = new Label(composite, SWT.NONE);

        if (registry.get("exchange") == null) {
            registry.put("exchange", Activator.getImageDescriptor("icons/exchange_view.gif").createImage());
        }
        exchangeLabel.setImage(registry.get("exchange")); //$NON-NLS-1$

        Link exchange = new Link(composite, SWT.NONE);
        exchange.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
        exchange.setText(EXCHANGE_URL);
        exchange.setToolTipText(Messages.getString("LinksToolbarItem_exchange"));

        exchange.addListener(SWT.Selection, new Listener() {

            public void handleEvent(Event event) {
                if (PluginChecker.isExchangeSystemLoaded()) {
                    IExchangeService service = (IExchangeService) GlobalServiceRegister.getDefault().getService(
                            IExchangeService.class);
                    service.openExchangeEditor();
                }
            }
        });

        return composite;
    }

    private void openBrower(String url) {
        Program.launch(url);
    }

}
