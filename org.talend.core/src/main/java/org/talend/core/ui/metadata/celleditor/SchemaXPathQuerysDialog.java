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
package org.talend.core.ui.metadata.celleditor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.talend.core.model.metadata.IMetadataColumn;

/**
 * DOC informix class global comment. Detailled comment
 */
public class SchemaXPathQuerysDialog extends Dialog {

    private TableViewer tableViewer;

    private static final String[] COLUMN_NAME = { "Schema", "Schema XPathQuerys" };

    private List<IMetadataColumn> listColumns;

    private Table table;

    private Map<String, String> mapping;

    private String values;

    public SchemaXPathQuerysDialog(Shell parentShell) {
        super(parentShell);
    }

    public SchemaXPathQuerysDialog(Shell parentShell, List<IMetadataColumn> listColumns, Map mapping) {
        super(parentShell);
        this.listColumns = listColumns;
        this.mapping = mapping;
        this.setShellStyle(this.getShellStyle() | SWT.RESIZE);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Mappings");

    }

    public Control createDialogArea(Composite parent) {

        Composite createDialogArea = (Composite) super.createDialogArea(parent);
        GridLayout gLayout = new GridLayout();

        createDialogArea.setLayout(gLayout);
        createDialogArea.setLayoutData(new GridData(GridData.CENTER));

        tableViewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.CENTER);
        table = tableViewer.getTable();

        CellEditor[] editors = new CellEditor[2];
        editors[0] = null;
        editors[1] = new TextCellEditor(table);

        tableViewer.setCellEditors(editors);
        tableViewer.setCellModifier(new ICellModifier() {

            public boolean canModify(Object element, String property) {
                return true;
            }

            public Object getValue(Object element, String property) {
                if (element instanceof XPathQueryBean) {
                    if (property.equals(COLUMN_NAME[1])) {
                        return ((XPathQueryBean) element).getValue();
                    }
                }
                return null;
            }

            public void modify(Object element, String property, Object value) {
                if (element instanceof TableItem) {
                    element = ((TableItem) element).getData();
                }
                if (element instanceof XPathQueryBean) {
                    if (property.equals(COLUMN_NAME[1])) {
                        ((XPathQueryBean) element).setValue((String) value);
                    }
                }
                tableViewer.refresh();
            }
        });

        TableColumn tableColumn1 = new TableColumn(table, SWT.NONE);
        tableColumn1.setText(COLUMN_NAME[0]); //$NON-NLS-1$
        tableColumn1.setWidth(150);

        TableColumn tableColumn2 = new TableColumn(table, SWT.NONE);
        tableColumn2.setText(COLUMN_NAME[1]); //$NON-NLS-1$
        tableColumn2.setWidth(180);

        GridData gridData = new GridData(GridData.FILL_BOTH);

        gridData.heightHint = 400;
        gridData.minimumHeight = 150;
        gridData.widthHint = 350;
        gridData.minimumWidth = 100;
        table.setLayoutData(gridData);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        tableViewer.setColumnProperties(COLUMN_NAME);

        tableViewer.setContentProvider(new ArrayContentProvider());
        tableViewer.setLabelProvider(new TableLabelProvider());

        String mappings = mapping.get("MAPPING");
        String[] beanValues = mappings.split(",");
        List<XPathQueryBean> beans = new ArrayList<XPathQueryBean>();
        for (int i = 0; i < listColumns.size(); i++) {
            IMetadataColumn column = listColumns.get(i);
            XPathQueryBean bean = new XPathQueryBean(column);

            if (beanValues.length > i) {
                bean.setValue(beanValues[i]);
            }
            beans.add(bean);
            beans.get(i);
        }
        tableViewer.setInput(beans);

        return createDialogArea;
    }

    /**
     * 
     * zli SchemaXPathQuerysDialog class global comment. Detailled comment
     */
    class XPathQueryBean {

        private IMetadataColumn column;

        private String value;

        public XPathQueryBean(IMetadataColumn column) {
            super();
            this.column = column;
            this.value = "";
        }

        public String getColumnName() {
            return this.column.getLabel();
        }

        public String getValue() {
            return this.value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    /**
     * 
     * zli SchemaXPathQuerysDialog class global comment. Detailled comment
     */
    private class TableLabelProvider implements ITableLabelProvider {

        public Image getColumnImage(Object element, int columnIndex) {
            return null;
        }

        public String getColumnText(Object element, int columnIndex) {
            if (element instanceof XPathQueryBean) {
                XPathQueryBean bean = (XPathQueryBean) element;
                switch (columnIndex) {
                case 0:
                    return bean.getColumnName();
                case 1:
                    return bean.getValue();
                default:
                }
            }
            return null;
        }

        public void addListener(ILabelProviderListener listener) {
        }

        public void dispose() {
        }

        public boolean isLabelProperty(Object element, String property) {
            return false;
        }

        public void removeListener(ILabelProviderListener listener) {
        }
    }

    protected void okPressed() {
        values = "";
        for (int i = 0; i < table.getItemCount(); i++) {
            TableItem item = table.getItem(i);
            String value = ((XPathQueryBean) item.getData()).getValue();
            if (i < table.getItemCount() - 1) {
                values += value + ",";
            } else {
                values += value;
            }
        }

        super.okPressed();

    }

    public String getValues() {
        return this.values;
    }

}
