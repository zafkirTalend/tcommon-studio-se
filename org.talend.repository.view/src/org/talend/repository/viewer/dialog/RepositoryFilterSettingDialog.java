// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.viewer.dialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.navigator.filters.ContentExtensionsTab;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.INavigatorContentDescriptor;
import org.eclipse.ui.navigator.INavigatorContentService;
import org.talend.core.model.general.Project;
import org.talend.core.model.properties.PropertiesPackage;
import org.talend.core.model.properties.Status;
import org.talend.core.model.properties.User;
import org.talend.core.model.repository.IRepositoryPrefConstants;
import org.talend.core.model.repository.RepositoryManager;
import org.talend.repository.ProjectManager;
import org.talend.repository.RepositoryViewPlugin;
import org.talend.repository.model.RepositoryConstants;
import org.talend.repository.viewer.filter.RepositoryNodeFilterHelper;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class RepositoryFilterSettingDialog extends Dialog {

    private static final int WIDTH = 600;

    private static final int HEIGHT = 500;

    public static final String SEPARATOR = ":";

    private final ICommonActionExtensionSite actionSite;

    private final boolean activedFilter;

    private RepositoryContentExtensionsTab contentExtensionsTab;

    private Table statusTable;

    private Table userTable;

    private Text userFilterPattern;

    private Label patternInfo;

    private Button enableUserPatternBtn;

    private Button allUsersBtn;

    private Set<String> uncheckedStatus = new HashSet<String>();

    private Set<String> uncheckedUser = new HashSet<String>();

    public RepositoryFilterSettingDialog(final ICommonActionExtensionSite actionSite, final boolean activedFilter) {
        super(actionSite.getViewSite().getShell());
        setShellStyle(getShellStyle() | SWT.MAX | SWT.MIN | SWT.RESIZE | SWT.APPLICATION_MODAL);
        this.actionSite = actionSite;
        this.activedFilter = activedFilter;
    }

    protected boolean isActivedFilter() {
        return activedFilter;
    }

    protected INavigatorContentService getNavigatorContentService() {
        return this.actionSite.getContentService();
    }

    protected CommonViewer getCommonViewer() {
        return (CommonViewer) this.actionSite.getStructuredViewer();
    }

    protected CommonNavigator getCommonNavigator() {
        return getCommonViewer().getCommonNavigator();
    }

    protected IPreferenceStore getPreferenceStore() {
        return RepositoryViewPlugin.getDefault().getPreferenceStore();
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("Repository Filter Setting");
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        SashForm sash = new SashForm(parent, SWT.HORIZONTAL | SWT.SMOOTH);
        sash.setLayoutData(new GridData(GridData.FILL_BOTH));
        GridLayout layout = new GridLayout();
        layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
        layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
        layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
        layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
        // layout.numColumns = 2;
        sash.setLayout(layout);
        GridData layoutData = new GridData(GridData.FILL_BOTH);
        layoutData.widthHint = WIDTH;
        layoutData.heightHint = HEIGHT;
        sash.setLayoutData(layoutData);
        applyDialogFont(sash);

        Composite leftPart = new Composite(sash, SWT.NONE);
        leftPart.setLayout(new GridLayout());
        leftPart.setLayoutData(new GridData(GridData.FILL_BOTH));
        applyDialogFont(leftPart);
        createLeftContent(leftPart);

        Composite rightPart = new Composite(sash, SWT.NONE);
        rightPart.setLayout(new GridLayout());
        rightPart.setLayoutData(new GridData(GridData.FILL_BOTH));
        applyDialogFont(rightPart);
        createRightContent(rightPart);
        addListeners();
        sash.setWeights(new int[] { 1, 1 });
        return sash;
    }

    private void createLeftContent(Composite parent) {

        contentExtensionsTab = new RepositoryContentExtensionsTab(parent, getNavigatorContentService());
        applyDialogFont(contentExtensionsTab);
        contentExtensionsTab.setLayoutData(new GridData(GridData.FILL_BOTH));

    }

    private void createRightContent(Composite parent) {
        Project currentProject = ProjectManager.getInstance().getCurrentProject();
        org.talend.core.model.properties.Project emfProject = currentProject.getEmfProject();
        Resource resource = emfProject.eResource();
        Collection<User> users = new ArrayList<User>();
        if (resource != null) {
            users = EcoreUtil.getObjectsByType(resource.getContents(), PropertiesPackage.eINSTANCE.getUser());
        }
        // if resource is null, add current user
        if (users.size() == 0) {
            users.add(emfProject.getAuthor());
        }
        // filter by Name
        Composite userPatternComp = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        userPatternComp.setLayout(layout);
        enableUserPatternBtn = new Button(userPatternComp, SWT.CHECK);
        enableUserPatternBtn.setText("Filter By Name : ");
        boolean enabled = getPreferenceStore().getBoolean(IRepositoryPrefConstants.TAG_USER_DEFINED_PATTERNS_ENABLED);
        enableUserPatternBtn.setSelection(enabled);

        String patterns = getPreferenceStore().getString(IRepositoryPrefConstants.FILTER_BY_NAME);
        userFilterPattern = new Text(parent, SWT.BORDER);
        userFilterPattern.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        userFilterPattern.setEnabled(enabled);
        if (patterns != null) {
            userFilterPattern.setText(patterns);
        }

        patternInfo = new Label(parent, SWT.NONE);
        patternInfo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        patternInfo.setText("The patterns are separated by comma, where\n* = any string, ? = any character, ,, = ,");
        patternInfo.setEnabled(enabled);

        // filter by user
        Composite userTop = new Composite(parent, SWT.NONE);
        userTop.setLayout(new GridLayout(2, false));
        userTop.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        Label filterByUser = new Label(userTop, SWT.NONE);
        filterByUser.setText("Filter By User :");

        allUsersBtn = new Button(userTop, SWT.CHECK | SWT.LEFT);
        allUsersBtn.setText("All Users");
        boolean userTableEnable = getPreferenceStore().getBoolean(IRepositoryPrefConstants.USER_FILTER_TABLE_ENABLED);
        allUsersBtn.setSelection(!userTableEnable);

        userTable = new Table(parent, SWT.BORDER | SWT.CHECK);
        userTable.setLayoutData(new GridData(GridData.FILL_BOTH));
        userTable.setHeaderVisible(true);
        userTable.setLinesVisible(true);
        userTable.setEnabled(!allUsersBtn.getSelection());

        TableColumn login = new TableColumn(userTable, SWT.NONE);
        login.setWidth(100);
        login.setText("Login");

        TableColumn firstName = new TableColumn(userTable, SWT.NONE);
        firstName.setWidth(100);
        firstName.setText("FirstName");

        TableColumn lastName = new TableColumn(userTable, SWT.NONE);
        lastName.setWidth(100);
        lastName.setText("LastName");

        String[] filtersByPreferenceKey = RepositoryViewPlugin.getDefault().getPreferenceValues(
                IRepositoryPrefConstants.FILTER_BY_USER);
        if (filtersByPreferenceKey != null && filtersByPreferenceKey.length > 0) {
            uncheckedUser.addAll(Arrays.asList(filtersByPreferenceKey));
        }
        for (User user : users) {
            TableItem item = new TableItem(userTable, SWT.NONE);
            item.setText(0, user.getLogin());
            item.setText(1, user.getFirstName() == null ? "" : user.getFirstName());
            item.setText(2, user.getLastName() == null ? "" : user.getLastName());
            if (!uncheckedUser.contains(user.getLogin())) {
                item.setChecked(true);
            }
        }

        // filter by status
        Label filterByStatus = new Label(parent, SWT.NONE);
        filterByStatus.setText("Filter By Status :");
        EList technicalStatus = emfProject.getTechnicalStatus();
        statusTable = new Table(parent, SWT.BORDER | SWT.CHECK);
        statusTable.setLayoutData(new GridData(GridData.FILL_BOTH));
        statusTable.setHeaderVisible(true);
        statusTable.setLinesVisible(true);

        TableColumn code = new TableColumn(statusTable, SWT.NONE);
        code.setWidth(100);
        code.setText("Code");

        TableColumn lable = new TableColumn(statusTable, SWT.NONE);
        lable.setWidth(100);
        lable.setText("Label");
        String[] filters = RepositoryViewPlugin.getDefault().getPreferenceValues(IRepositoryPrefConstants.FILTER_BY_STATUS);
        if (filters != null && filters.length > 0) {
            uncheckedStatus.addAll(Arrays.asList(filters));
        }
        for (Object o : technicalStatus) {
            Status status = (Status) o;
            TableItem item = new TableItem(statusTable, SWT.NONE);
            item.setText(0, status.getCode());
            item.setText(1, status.getLabel());
            if (!uncheckedStatus.contains(status.getCode())) {
                item.setChecked(true);
            }

        }
        TableItem item = new TableItem(statusTable, SWT.NONE);
        item.setText(1, "not set status");
        item.setData(RepositoryConstants.NOT_SET_STATUS, RepositoryConstants.NOT_SET_STATUS);
        if (!uncheckedStatus.contains(RepositoryConstants.NOT_SET_STATUS)) {
            item.setChecked(true);
        }
    }

    private void addListeners() {
        // treeViewer.addCheckStateListener(new ICheckStateListener() {
        //
        // public void checkStateChanged(CheckStateChangedEvent event) {
        // Set<String> all = new HashSet<String>();
        // TreeItem lastClickedItem = treeViewer.getLastClickedItem();
        // if (lastClickedItem != null) {
        // updateChildrenItems(lastClickedItem, all);
        // if (event.getChecked()) {
        // updateParentItems(lastClickedItem, all, true);
        // uncheckedNode.removeAll(all);
        // } else {
        // updateParentItems(lastClickedItem, all, false);
        // uncheckedNode.addAll(all);
        // }
        // }
        // }
        // });
        statusTable.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                if (e.detail == SWT.CHECK) {
                    TableItem item = (TableItem) e.item;
                    String text = item.getText(0);
                    if (text == null || "".equals(text)) {
                        Object data = item.getData(RepositoryConstants.NOT_SET_STATUS);
                        if (data != null && RepositoryConstants.NOT_SET_STATUS.equals(data)) {
                            text = data.toString();
                        }
                    }
                    if (!item.getChecked()) {
                        uncheckedStatus.add(text);
                    } else {
                        uncheckedStatus.remove(text);
                    }
                }
            }

        });

        userTable.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                if (e.detail == SWT.CHECK) {
                    TableItem item = (TableItem) e.item;
                    if (!item.getChecked()) {
                        uncheckedUser.add(item.getText(0));
                    } else {
                        uncheckedUser.remove(item.getText(0));
                    }
                }
            }
        });
        enableUserPatternBtn.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {

                boolean selection = enableUserPatternBtn.getSelection();
                patternInfo.setEnabled(selection);
                userFilterPattern.setEnabled(selection);
                if (selection) {
                    userFilterPattern.setFocus();
                }
            }

        });

        allUsersBtn.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                userTable.setEnabled(!allUsersBtn.getSelection());
                if (allUsersBtn.getSelection()) {
                    uncheckedUser.clear();
                }
            }

        });
    }

    private List<String> getFilteredContents() {
        List<String> filteredContents = new ArrayList<String>();
        TableItem[] tableItems = contentExtensionsTab.getTable().getItems();
        INavigatorContentDescriptor descriptor;
        for (int i = 0; i < tableItems.length; i++) {
            descriptor = (INavigatorContentDescriptor) tableItems[i].getData();

            if (!tableItems[i].getChecked()) {
                filteredContents.add(descriptor.getId());
            }
        }
        return filteredContents;
    }

    @Override
    protected void okPressed() {
        IPreferenceStore preferenceStore = getPreferenceStore();
        final List<String> filteredContents = getFilteredContents();
        String relust = convertToString(filteredContents, RepositoryManager.ITEM_SEPARATOR);
        preferenceStore.setValue(IRepositoryPrefConstants.FILTER_BY_NODE,
                relust.length() > 2 ? relust.substring(0, relust.length() - 2) : relust);

        String status = convertToString(uncheckedStatus, RepositoryManager.ITEM_SEPARATOR);
        preferenceStore.setValue(IRepositoryPrefConstants.FILTER_BY_STATUS,
                status.length() > 2 ? status.substring(0, status.length() - 2) : status);

        String users = convertToString(uncheckedUser, RepositoryManager.ITEM_SEPARATOR);
        preferenceStore.setValue(IRepositoryPrefConstants.FILTER_BY_USER,
                users.length() > 2 ? users.substring(0, users.length() - 2) : users);

        boolean canUserFilterEnable = this.userFilterPattern.getText() != null && !"".equals(this.userFilterPattern.getText());
        preferenceStore.setValue(IRepositoryPrefConstants.FILTER_BY_NAME, this.userFilterPattern.getText());
        preferenceStore.setValue(IRepositoryPrefConstants.TAG_USER_DEFINED_PATTERNS_ENABLED,
                this.enableUserPatternBtn.getSelection() && canUserFilterEnable);

        preferenceStore.setValue(IRepositoryPrefConstants.USER_FILTER_TABLE_ENABLED, !allUsersBtn.getSelection());
        super.okPressed();

        RepositoryNodeFilterHelper.filter(actionSite, isActivedFilter(), false);

    }

    private String convertToString(Collection<String> connection, String separator) {
        StringBuffer buffer = new StringBuffer();
        for (String item : connection) {
            buffer.append(item);
            buffer.append(separator);
        }
        return buffer.toString();
    }

    /**
     * 
     * DOC ggu RepositoryContentExtensionsTab class global comment. Detailled comment
     */
    @SuppressWarnings("restriction")
    class RepositoryContentExtensionsTab extends ContentExtensionsTab {

        public RepositoryContentExtensionsTab(Composite parent, INavigatorContentService aContentService) {
            super(parent, aContentService);
            updateCheckedState();
        }

        protected void updateCheckedState() {
            String[] filteredContents = RepositoryViewPlugin.getDefault().getPreferenceValues(
                    IRepositoryPrefConstants.FILTER_BY_NODE);
            List<String> list = new ArrayList<String>();
            if (filteredContents != null) {
                list = Arrays.asList(filteredContents);
            }
            INavigatorContentDescriptor[] visibleExtensions = getContentService().getVisibleExtensions();
            for (int i = 0; i < visibleExtensions.length; i++) {
                if (list.contains(visibleExtensions[i].getId())) { // filtered
                    getTableViewer().setChecked(visibleExtensions[i], false);
                } else {
                    getTableViewer().setChecked(visibleExtensions[i], true);
                }
            }
        }

        @Override
        public Table getTable() {
            return super.getTable();
        }

    }

}
