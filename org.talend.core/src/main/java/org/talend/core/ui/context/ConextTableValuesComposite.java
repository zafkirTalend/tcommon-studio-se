// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.core.ui.context;

import java.util.Collections;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.talend.core.model.process.IContext;
import org.talend.core.model.process.IContextManager;
import org.talend.core.model.process.IContextParameter;

/**
 * DOC bqian class global comment. Detailled comment <br/>
 * 
 */
public class ConextTableValuesComposite extends Composite {

    private static final String COLUMN_NAME_PROPERTY = "Name";

    public static final int CONTEXT_COLUMN_WIDTH = 200;

    private TableViewer viewer;

    private IContextModelManager modelManager = null;

    /**
     * Constructor.
     * 
     * @param parent
     * @param style
     */
    public ConextTableValuesComposite(Composite parent, IContextModelManager manager) {
        super(parent, SWT.NONE);
        modelManager = manager;
        this.setBackground(parent.getBackground());
        this.setLayout(GridLayoutFactory.swtDefaults().spacing(0, 0).create());
        initializeUI();
    }

    /**
     * bqian Comment method "initializeUI".
     * 
     * @param viewer
     */
    private void initializeUI() {
        final ToolBar toolBar = new ToolBar(this, SWT.FLAT | SWT.NO_BACKGROUND);
        GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.TOP).applyTo(toolBar);

        ToolItem pullDownButton = new ToolItem(toolBar, SWT.PUSH);
        Image hoverImage = WorkbenchImages.getImage(IWorkbenchGraphicConstants.IMG_LCL_RENDERED_VIEW_MENU);
        pullDownButton.setDisabledImage(hoverImage);
        pullDownButton.setImage(hoverImage);
        pullDownButton.setHotImage(hoverImage);
        pullDownButton.setToolTipText(WorkbenchMessages.Menu);

        final MenuManager menuManager = new MenuManager("Context Configuration");

        ConfigureContextAction configContext = new ConfigureContextAction(modelManager, this.getShell());
        menuManager.add(configContext);

        final Menu aMenu = menuManager.createContextMenu(toolBar.getParent());

        pullDownButton.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                Point toolbarSize = toolBar.getSize();
                toolbarSize = toolBar.toDisplay(0, toolbarSize.y);
                aMenu.setLocation(toolbarSize);
                aMenu.setVisible(true);
            }
        });

        viewer = new TableViewer(this, SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        ViewerProvider provider = new ViewerProvider();
        viewer.setLabelProvider(provider);
        viewer.setContentProvider(provider);
        Table table = viewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(table);
        viewer.setCellModifier(new CellModifier());
    }

    /**
     * bqian Comment method "getContexts".
     * 
     * @return
     */
    public List<IContext> getContexts() {
        IContextManager cm = modelManager.getContextManager();
        List<IContext> contexts = cm.getListContext();
        return contexts;
    }

    public void refresh() {
        List<IContext> contexts = getContexts();
        Table table = viewer.getTable();
        TableColumn[] columns = table.getColumns();
        for (TableColumn tableColumn : columns) {
            tableColumn.dispose();
        }

        TableColumn column = new TableColumn(table, SWT.NONE);
        column.setText("Name");
        column.setWidth(CONTEXT_COLUMN_WIDTH);

        CellEditor[] cellEditors = new CellEditor[contexts.size() + 1];
        String[] properties = new String[contexts.size() + 1];
        properties[0] = COLUMN_NAME_PROPERTY;
        int index = 1;
        for (IContext context : contexts) {
            column = new TableColumn(table, SWT.NONE);
            column.setText(context.getName());
            column.setWidth(CONTEXT_COLUMN_WIDTH);
            properties[index] = context.getName();
            cellEditors[index] = new TextCellEditor(table);
            index++;
        }

        viewer.setColumnProperties(properties);
        viewer.setCellEditors(cellEditors);
        table.layout();

        List<IContextParameter> contextTemplate = ConextTemplateComposite.computeContextTemplate(contexts);
        viewer.setInput(contextTemplate);
    }

    /**
     * Clear the data in this viewer.
     * 
     * @param jobContextManager2
     */
    public void clear() {
        Table table = viewer.getTable();
        TableColumn[] columns = table.getColumns();
        for (TableColumn tableColumn : columns) {
            tableColumn.dispose();
        }
        this.layout();
        viewer.setInput(Collections.EMPTY_LIST);
    }

    /**
     * Label and content provider for table viewer. <br/>
     * 
     */
    class ViewerProvider extends LabelProvider implements ITableLabelProvider, IStructuredContentProvider {

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
         */
        public Image getColumnImage(Object element, int columnIndex) {
            return null;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
         */
        public String getColumnText(Object element, int columnIndex) {
            IContextParameter para = (IContextParameter) element;
            if (columnIndex == 0) {
                return para.getName();
            } else {
                return getContexts().get(columnIndex - 1).getContextParameter(para.getName()).getValue();
            }
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
         */
        public Object[] getElements(Object inputElement) {
            return ((List) inputElement).toArray();
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
         * java.lang.Object, java.lang.Object)
         */
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        }
    }

    /**
     * bqian ConextTableValuesComposite class global comment. Detailled comment <br/>
     * 
     */
    class CellModifier implements ICellModifier {

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
         */
        public boolean canModify(Object element, String property) {
            if (property.equals(COLUMN_NAME_PROPERTY)) {
                return false;
            }
            return true;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object, java.lang.String)
         */
        public Object getValue(Object element, String property) {
            IContextParameter templatePara = (IContextParameter) element;
            return getRealParameter(property, templatePara).getValue();
        }

        /**
         * bqian Comment method "getRealParameter".
         * 
         * @param property
         * @param templatePara
         * @return
         */
        private IContextParameter getRealParameter(String property, IContextParameter templatePara) {
            IContext context = modelManager.getContextManager().getContext(property);
            IContextParameter para = context.getContextParameter(templatePara.getName());
            return para;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object, java.lang.String, java.lang.Object)
         */
        public void modify(Object element, String property, Object value) {
            TableItem item = (TableItem) element;
            final IContextParameter templatePara = (IContextParameter) item.getData();
            final IContextParameter parameterToSet = getRealParameter(property, templatePara);
            final String value2Set = (String) value;
            parameterToSet.setValue(value2Set);
            Command command = new Command() {

                public void execute() {
                    modelManager.refresh();
                }
            };
            if (modelManager.getCommandStack() == null) {
                command.execute();
            } else {
                modelManager.getCommandStack().execute(command);
            }
        }
    }
}
