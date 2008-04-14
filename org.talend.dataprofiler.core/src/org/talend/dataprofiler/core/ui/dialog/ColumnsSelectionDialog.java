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
package org.talend.dataprofiler.core.ui.dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.talend.cwm.helper.ColumnHelper;
import org.talend.cwm.helper.ColumnSetHelper;
import org.talend.cwm.helper.DataProviderHelper;
import org.talend.cwm.helper.SwitchHelpers;
import org.talend.cwm.management.api.DqRepositoryViewService;
import org.talend.cwm.relational.TdColumn;
import org.talend.cwm.softwaredeployment.TdDataProvider;
import org.talend.dataprofiler.core.ImageLib;
import org.talend.dataprofiler.core.PluginConstant;
import org.talend.dataprofiler.core.helper.EObjectHelper;
import org.talend.dataprofiler.core.helper.NeedSaveDataProviderHelper;
import org.talend.dataprofiler.core.model.nodes.foldernode.IFolderNode;
import org.talend.dataprofiler.core.ui.dialog.filter.TypedViewerFilter;
import org.talend.dataprofiler.core.ui.dialog.provider.DBTablesViewContentProvider;
import org.talend.dataprofiler.core.ui.dialog.provider.DBTablesViewLabelProvider;
import org.talend.dataprofiler.core.ui.views.filters.EMFObjFilter;

import orgomg.cwm.objectmodel.core.Package;
import orgomg.cwm.resource.relational.ColumnSet;

/**
 * @author Select the special columns from this dialog.
 * 
 */
public class ColumnsSelectionDialog extends TwoPartCheckSelectionDialog {

    private Map<ColumnSet, ColumnCheckedMap> columnSetCheckedMap;

    public ColumnsSelectionDialog(Shell parent, String message) {
        super(parent, message);
        addFirstPartFilters();
        this.setInput(ResourcesPlugin.getWorkspace().getRoot());
        columnSetCheckedMap = new HashMap<ColumnSet, ColumnCheckedMap>();
    }

    protected void initProvider() {
        fLabelProvider = new DBTablesViewLabelProvider();
        fContentProvider = new DBTablesViewContentProvider();
        sLabelProvider = new ColumnLabelProvider();
        sContentProvider = new ColumnContentProvider();
    }

    @SuppressWarnings("unchecked")
    private void addFirstPartFilters() {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        final Class[] acceptedClasses = new Class[] { IResource.class, IFolderNode.class, EObject.class };
        IProject[] allProjects = root.getProjects();
        ArrayList rejectedElements = new ArrayList(allProjects.length);
        for (int i = 0; i < allProjects.length; i++) {
            if (!allProjects[i].equals(ResourcesPlugin.getWorkspace().getRoot().getProject(PluginConstant.METADATA_PROJECTNAME))) {
                rejectedElements.add(allProjects[i]);
            }
        }
        rejectedElements.add(ResourcesPlugin.getWorkspace().getRoot().getProject(PluginConstant.METADATA_PROJECTNAME).getFile(
                ".project"));
        ViewerFilter filter = new TypedViewerFilter(acceptedClasses, rejectedElements.toArray());
        this.addFilter(filter);
        this.addFilter(new EMFObjFilter());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.dataprofiler.core.ui.dialog.TwoPartCheckSelectionDialog#addCheckedListener()
     */
    protected void addCheckedListener() {

        // When user checks a checkbox in the tree, check all its children
        getTreeViewer().addCheckStateListener(new ICheckStateListener() {

            public void checkStateChanged(CheckStateChangedEvent event) {
                // If the item is checked . . .
                if (event.getChecked()) {
                    // . . . check all its children
                    getTreeViewer().setSubtreeChecked(event.getElement(), true);
                    if (event.getElement() instanceof ColumnSet) {
                        handleColumnsChecked((ColumnSet) event.getElement(), true);
                    }
                } else {
                    getTreeViewer().setSubtreeChecked(event.getElement(), false);
                    if (event.getElement() instanceof ColumnSet) {
                        handleColumnsChecked((ColumnSet) event.getElement(), false);
                    }
                }
            }
        });

        getTableViewer().addCheckStateListener(new ICheckStateListener() {

            public void checkStateChanged(CheckStateChangedEvent event) {
                if (event.getElement() instanceof TdColumn) {
                    handleColumnChecked((TdColumn) event.getElement(), event.getChecked());
                }
            }
        });
    }

    private TdColumn[] getCheckedColumns(ColumnSet columnSet) {
        ColumnCheckedMap columnCheckMap = columnSetCheckedMap.get(columnSet);
        if (columnCheckMap == null) {
            Boolean allCheckFlag = this.getTreeViewer().getChecked(columnSet);
            this.getTableViewer().setAllChecked(allCheckFlag);
            columnCheckMap = new ColumnCheckedMap();
            TdColumn[] columns = EObjectHelper.getColumns(columnSet);
            columnCheckMap.putAllChecked(columns, allCheckFlag);
            columnSetCheckedMap.put(columnSet, columnCheckMap);
            return allCheckFlag ? columns : null;
        } else {
            return columnCheckMap.getCheckedColumns();
        }

    }

    private void handleColumnChecked(TdColumn column, Boolean checkedFlag) {
        ColumnSet columnSetOwner = ColumnHelper.getColumnSetOwner(column);
        if (checkedFlag) {
            getTreeViewer().setChecked(columnSetOwner, true);
        }
        ColumnCheckedMap columnCheckMap = columnSetCheckedMap.get(columnSetOwner);
        if (columnCheckMap != null) {
            columnCheckMap.putColumnChecked(column, checkedFlag);
        }

    }

    private void handleColumnsChecked(ColumnSet columnSet, Boolean checkedFlag) {
        ColumnCheckedMap columnCheckMap = columnSetCheckedMap.get(columnSet);
        if (columnCheckMap != null) {
            columnCheckMap.clear();
            columnCheckMap.putAllChecked(EObjectHelper.getColumns(columnSet), checkedFlag);
        } else {
            columnCheckMap = new ColumnCheckedMap();
            columnCheckMap.putAllChecked(EObjectHelper.getColumns(columnSet), checkedFlag);
            columnSetCheckedMap.put(columnSet, columnCheckMap);
        }

        getTableViewer().setAllChecked(checkedFlag);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.dataprofiler.core.ui.dialog.TwoPartCheckSelectionDialog#addSelectionButtonListener(org.eclipse.swt.widgets.Button,
     * org.eclipse.swt.widgets.Button)
     */
    protected void addSelectionButtonListener(Button selectButton, Button deselectButton) {
        SelectionListener listener = new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                Object[] viewerElements = fContentProvider.getElements(getTreeViewer().getInput());
                if (fContainerMode) {
                    getTreeViewer().setCheckedElements(viewerElements);
                } else {
                    for (int i = 0; i < viewerElements.length; i++) {
                        getTreeViewer().setSubtreeChecked(viewerElements[i], true);
                    }
                }
                columnSetCheckedMap.clear();
                if (getTableViewer().getInput() != null) {
                    handleColumnsChecked((ColumnSet) getTableViewer().getInput(), true);
                }
                updateOKStatus();
            }
        };
        selectButton.addSelectionListener(listener);

        listener = new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                getTreeViewer().setCheckedElements(new Object[0]);
                columnSetCheckedMap.clear();
                if (getTableViewer().getInput() != null) {
                    handleColumnsChecked((ColumnSet) getTableViewer().getInput(), false);
                }
                updateOKStatus();
            }
        };
        deselectButton.addSelectionListener(listener);
    }

    public void selectionChanged(SelectionChangedEvent event) {
        Object selectedObj = ((IStructuredSelection) event.getSelection()).getFirstElement();
        if (selectedObj instanceof ColumnSet) {
            this.setOutput(selectedObj);
            TdColumn[] columns = getCheckedColumns((ColumnSet) selectedObj);
            if (columns != null) {
                this.getTableViewer().setCheckedElements(columns);
            }
        }

    }

    /**
     * @author rli
     * 
     */
    class ColumnCheckedMap {

        Map<TdColumn, Boolean> columnMap = new HashMap<TdColumn, Boolean>();

        public void putColumnChecked(TdColumn column, Boolean isChecked) {
            columnMap.put(column, isChecked);
        }

        public Boolean getColumnChecked(TdColumn key) {
            return columnMap.get(key);
        }

        public void putAllChecked(TdColumn[] columns, Boolean isChecked) {
            for (int i = 0; i < columns.length; i++) {
                columnMap.put(columns[i], isChecked);
            }
        }

        public TdColumn[] getCheckedColumns() {
            List<TdColumn> checkedColumns = new ArrayList<TdColumn>();
            Iterator<TdColumn> it = columnMap.keySet().iterator();
            while (it.hasNext()) {
                TdColumn column = it.next();
                if (columnMap.get(column)) {
                    checkedColumns.add(column);
                }
            }
            return checkedColumns.toArray(new TdColumn[checkedColumns.size()]);
        }

        public List<TdColumn> getCheckedColumnList() {
            List<TdColumn> checkedColumns = new ArrayList<TdColumn>();
            Iterator<TdColumn> it = columnMap.keySet().iterator();
            while (it.hasNext()) {
                TdColumn column = it.next();
                if (columnMap.get(column)) {
                    checkedColumns.add(column);
                }
            }
            return checkedColumns;
        }

        public void clear() {
            columnMap.clear();
        }

    }

    protected void computeResult() {
        setResult(getAllCheckedColumns());
    }

    private List<TdColumn> getAllCheckedColumns() {
        Object[] checkedNodes = this.getTreeViewer().getCheckedElements();
        List<TdColumn> columnList = new ArrayList<TdColumn>();
        for (int i = 0; i < checkedNodes.length; i++) {
            if (!(checkedNodes[i] instanceof ColumnSet)) {
                continue;
            }
            if (columnSetCheckedMap.containsKey(checkedNodes[i])) {
                ColumnCheckedMap columnMap = columnSetCheckedMap.get(checkedNodes[i]);
                columnList.addAll(columnMap.getCheckedColumnList());
            } else {
                columnList.addAll(ColumnSetHelper.getColumns((ColumnSet) checkedNodes[i]));
            }
        }
        return columnList;
    }

    protected void okPressed() {
        super.okPressed();
        NeedSaveDataProviderHelper.saveAllDataProvider();
    }

    /**
     * @author rli
     * 
     */
    class ColumnLabelProvider extends LabelProvider {

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
         */
        public Image getImage(Object element) {
            return ImageLib.getImage(ImageLib.REFRESH_IMAGE);
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
         */
        public String getText(Object element) {
            return ((TdColumn) element).getName();
        }

    }

    /**
     * @author rli
     * 
     */
    class ColumnContentProvider implements IStructuredContentProvider {

        public Object[] getElements(Object inputElement) {
            if (inputElement instanceof ColumnSet) {
                EObject eObj = (EObject) inputElement;
                ColumnSet columnSet = SwitchHelpers.COLUMN_SET_SWITCH.doSwitch(eObj);
                if (columnSet != null) {
                    TdColumn[] columns = EObjectHelper.getColumns(columnSet);
                    if (columns.length <= 0) {
                        Package parentCatalogOrSchema = ColumnSetHelper.getParentCatalogOrSchema(columnSet);
                        if (parentCatalogOrSchema == null) {
                            return null;
                        }
                        TdDataProvider provider = DataProviderHelper.getTdDataProvider(parentCatalogOrSchema);
                        if (provider == null) {
                            return null;
                        }
                        List<TdColumn> columnList = DqRepositoryViewService.getColumns(provider, columnSet, null, true);
                        columns = columnList.toArray(new TdColumn[columnList.size()]);
                        // store tables in catalog
                        ColumnSetHelper.addColumns(columnSet, columnList);
                        NeedSaveDataProviderHelper.register(DqRepositoryViewService.createTechnicalName(provider.getName()),
                                provider);
                    }
                    return columns;
                }
            }
            return null;
        }

        public void dispose() {
        }

        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

        }

    }

}
