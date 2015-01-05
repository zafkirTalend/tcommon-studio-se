// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.ui.dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.talend.commons.ui.runtime.image.EImage;
import org.talend.commons.ui.runtime.image.ImageProvider;
import org.talend.commons.ui.swt.advanced.dataeditor.HadoopPropertiesTableView;
import org.talend.commons.ui.swt.extended.table.HadoopPropertiesFieldModel;
import org.talend.metadata.managment.ui.i18n.Messages;

/**
 * created by ycbai on 2014年12月23日 Detailled comment
 *
 */
public class HadoopPropertiesDialog extends TitleAreaDialog {

    private List<Map<String, Object>> initPropertiesOfParent;

    private List<Map<String, Object>> initProperties;

    private List<Map<String, Object>> properties;

    private HadoopPropertiesTableView propertiesTableView;

    private ExpandBar propertiesBar;

    private Label statusLabel;

    private Font italicFont;

    public HadoopPropertiesDialog(Shell parentShell, List<Map<String, Object>> initProperties) {
        this(parentShell, null, initProperties);
    }

    public HadoopPropertiesDialog(Shell parentShell, List<Map<String, Object>> initPropertiesOfParent,
            List<Map<String, Object>> initProperties) {
        super(parentShell);
        setShellStyle(getShellStyle() | SWT.RESIZE | SWT.MAX);
        this.initPropertiesOfParent = initPropertiesOfParent;
        this.initProperties = initProperties;
        properties = cloneProperties(initProperties);
    }

    public void create(Composite parent) {
        Composite hpComposite = new Composite(parent, SWT.NONE);
        GridLayout hpCompLayout = new GridLayout(3, false);
        hpCompLayout.marginWidth = getMarginWidth();
        hpCompLayout.marginHeight = getMarginHeight();
        hpComposite.setLayout(hpCompLayout);
        hpComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        Label hpLabel = new Label(hpComposite, SWT.NONE);
        hpLabel.setText(Messages.getString("HadoopPropertiesDialog.title")); //$NON-NLS-1$
        Button openHpButton = new Button(hpComposite, SWT.NONE);
        final HadoopPropertiesDialog propertiesDialog = this;
        openHpButton.setImage(ImageProvider.getImage(EImage.THREE_DOTS_ICON));
        openHpButton.setLayoutData(new GridData(30, 25));
        openHpButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                if (propertiesDialog.open() == IDialogConstants.OK_ID) {
                    List<Map<String, Object>> hadoopProperties = propertiesDialog.getHadoopProperties();
                    updateProperties(properties, hadoopProperties);
                    updateHadoopProperties(properties);
                    updateStatusLabel(properties);
                }
            }
        });
        statusLabel = new Label(hpComposite, SWT.NONE);
        statusLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        updateStatusLabel(initProperties);
    }

    protected int getMarginWidth() {
        return 5;
    }

    protected int getMarginHeight() {
        return 5;
    }

    private List<Map<String, Object>> cloneProperties(List<Map<String, Object>> originalProperties) {
        List<Map<String, Object>> newProperties = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> map : originalProperties) {
            newProperties.add(new HashMap<String, Object>(map));
        }
        return newProperties;
    }

    private void updateProperties(List<Map<String, Object>> originalProperties, List<Map<String, Object>> referProperties) {
        originalProperties.clear();
        for (Map<String, Object> map : referProperties) {
            originalProperties.add(new HashMap<String, Object>(map));
        }
    }

    private void updateStatusLabel(List<Map<String, Object>> hadoopProperties) {
        if (hadoopProperties == null || hadoopProperties.size() == 0) {
            statusLabel.setText(Messages.getString("HadoopPropertiesDialog.statusLabel.empty")); //$NON-NLS-1$
        } else {
            StringBuffer propsBuffer = new StringBuffer();
            propsBuffer.append("("); //$NON-NLS-1$
            for (Map<String, Object> propMap : hadoopProperties) {
                String key = String.valueOf(propMap.get("PROPERTY")); //$NON-NLS-1$
                String value = String.valueOf(propMap.get("VALUE")); //$NON-NLS-1$
                String propStr = key + "=" + value + ";"; //$NON-NLS-1$ //$NON-NLS-2$
                propsBuffer.append(propStr);
            }
            if (propsBuffer.length() > 0) {
                propsBuffer.deleteCharAt(propsBuffer.length() - 1);
            }
            if (propsBuffer.length() > 50) {
                propsBuffer = new StringBuffer(propsBuffer.subSequence(0, 50));
                propsBuffer.append("..."); //$NON-NLS-1$
            }
            propsBuffer.append(")"); //$NON-NLS-1$
            statusLabel.setText(propsBuffer.toString());
        }
        statusLabel.setFont(getItalicFont());
    }

    private Font getItalicFont() {
        if (italicFont == null) {
            Display display = PlatformUI.getWorkbench().getDisplay();
            FontData fontData = display.getSystemFont().getFontData()[0];
            italicFont = new Font(display, new FontData(fontData.getName(), fontData.getHeight(), SWT.ITALIC));
        }

        return italicFont;
    }

    protected void updateHadoopProperties(List<Map<String, Object>> hadoopProperties) {
        // Do nothing by default.
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(Messages.getString("HadoopPropertiesDialog.title")); //$NON-NLS-1$
        setHelpAvailable(false);
    }

    @Override
    public void create() {
        super.create();
        setTitle(Messages.getString("HadoopPropertiesDialog.title")); //$NON-NLS-1$
        setMessage(Messages.getString("HadoopPropertiesDialog.desc")); //$NON-NLS-1$
        setTitleImage(ImageProvider.getImage(EImage.PROPERTIES_WIZ));
    }

    @Override
    protected void initializeBounds() {
        super.initializeBounds();

        Point size = getShell().getSize();
        Point location = getInitialLocation(size);
        getShell().setBounds(getConstrainedShellBounds(new Rectangle(location.x, location.y, size.x, size.y)));
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        Composite comp = new Composite(composite, SWT.NONE);
        comp.setLayoutData(new GridData(GridData.FILL_BOTH));
        GridLayout layout = new GridLayout();
        layout.marginHeight = 10;
        layout.marginWidth = 10;
        comp.setLayout(layout);

        propertiesBar = new ExpandBar(comp, SWT.V_SCROLL);
        propertiesBar.setSpacing(10);
        propertiesBar.setBackground(propertiesBar.getParent().getBackground());
        propertiesBar.setLayoutData(new GridData(GridData.FILL_BOTH));
        HadoopPropertiesTableView parentPropertiesTable = null;
        if (initPropertiesOfParent != null) {
            parentPropertiesTable = createPropertiesTable(propertiesBar, 0,
                    Messages.getString("HadoopPropertiesDialog.parentProperties.title"), initPropertiesOfParent, true, false); //$NON-NLS-1$
        }
        int tableIndex = parentPropertiesTable == null ? 0 : 1;
        propertiesTableView = createPropertiesTable(propertiesBar, tableIndex,
                Messages.getString("HadoopPropertiesDialog.title"), initProperties, false, true); //$NON-NLS-1$
        updateExpandItems();

        return parent;
    }

    @Override
    protected void cancelPressed() {
        updateProperties(initProperties, properties);
        super.cancelPressed();
    }

    private HadoopPropertiesTableView createPropertiesTable(ExpandBar bar, int index, String title,
            List<Map<String, Object>> hadoopProperties, boolean readonly, boolean expanded) {
        Composite compositeTable = new Composite(bar, SWT.NONE);
        GridLayout compositeTableLayout = new GridLayout(1, false);
        compositeTableLayout.marginWidth = 0;
        compositeTableLayout.marginHeight = 0;
        compositeTable.setLayout(compositeTableLayout);
        compositeTable.setLayoutData(new GridData(GridData.FILL_BOTH));

        HadoopPropertiesFieldModel model = new HadoopPropertiesFieldModel(hadoopProperties, ""); //$NON-NLS-1$
        HadoopPropertiesTableView propertiesTable = new HadoopPropertiesTableView(compositeTable, model);
        propertiesTable.setReadOnly(readonly);
        Composite fieldTableEditorComposite = propertiesTable.getMainComposite();
        fieldTableEditorComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
        propertiesTable.getTable().addControlListener(new ControlAdapter() {

            @Override
            public void controlResized(ControlEvent e) {
                updateExpandItems();
            }
        });
        ExpandItem item = new ExpandItem(bar, SWT.NONE, index);
        item.setText(title);
        item.setHeight(compositeTable.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        item.setControl(compositeTable);
        item.setExpanded(expanded);

        return propertiesTable;
    }

    private void updateExpandItems() {
        ExpandItem[] items = propertiesBar.getItems();
        for (ExpandItem expandItem : items) {
            expandItem.setHeight(expandItem.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        }
    }

    public List<Map<String, Object>> getHadoopProperties() {
        return propertiesTableView.getExtendedTableModel().getBeansList();
    }

    public void setInitPropertiesOfParent(List<Map<String, Object>> initPropertiesOfParent) {
        this.initPropertiesOfParent = initPropertiesOfParent;
    }

    public void setInitProperties(List<Map<String, Object>> initProperties) {
        this.initProperties = initProperties;
    }

}
