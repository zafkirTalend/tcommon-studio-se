// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.librariesmanager.ui.dialogs;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.PlatformUI;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.commons.ui.runtime.image.EImage;
import org.talend.commons.ui.runtime.image.ImageProvider;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorNotModifiable.LAYOUT_MODE;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorNotModifiable.SORT;
import org.talend.commons.ui.swt.tableviewer.behavior.IColumnImageProvider;
import org.talend.commons.utils.data.bean.IBeanPropertyAccessors;
import org.talend.commons.utils.generation.JavaUtils;
import org.talend.commons.utils.io.FilesUtils;
import org.talend.core.download.DownloadHelperWithProgress;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.general.ModuleNeeded;
import org.talend.core.model.general.ModuleNeeded.ELibraryInstallStatus;
import org.talend.core.model.general.ModuleToInstall;
import org.talend.librariesmanager.model.ModulesNeededProvider;
import org.talend.librariesmanager.prefs.LibrariesManagerUtils;
import org.talend.librariesmanager.ui.LibManagerUiPlugin;
import org.talend.librariesmanager.ui.i18n.Messages;
import org.talend.librariesmanager.utils.RemoteModulesHelper;

/**
 * DOC wchen class global comment. Detailled comment
 */
public class ExternalModulesInstallDialog extends TitleAreaDialog implements IModulesListener {

    public static final String DO_NOT_SHOW_EXTERNALMODULESINSTALLDIALOG = "do_not_show_ExternalModulesInstallDialog"; //$NON-NLS-1$

    private static final String HELP_CONTENT = "http://talendforge.org/wiki/doku.php?id=doc:installation_guide&s[]=jar#install_jar_dependencies"; //$NON-NLS-1$

    private Color color = new Color(null, 255, 255, 255);

    protected TableViewerCreator tableViewerCreator;

    protected String text;

    protected String title;

    protected Button installAllBtn;

    protected List<String> jarsInstalledSuccuss = new ArrayList<String>();

    protected List<ModuleToInstall> inputList = new ArrayList<ModuleToInstall>();

    private boolean showMessage = true;

    protected TableViewerCreatorColumn urlcolumn;

    protected TableViewerCreatorColumn installcolumn;

    public ExternalModulesInstallDialog(Shell shell, String text, String title) {
        super(shell);
        setShellStyle(SWT.DIALOG_TRIM | SWT.MIN | SWT.MAX | SWT.RESIZE | getDefaultOrientation());
        this.text = text;
        this.title = title;
    }

    @Override
    protected void initializeBounds() {
        super.initializeBounds();
        getShell().setSize(1050, 400);
        Point location = getInitialLocation(getShell().getSize());
        getShell().setLocation(location.x, location.y);
    }

    @Override
    protected void configureShell(final Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(text);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        GridData layoutData = new GridData(GridData.FILL_BOTH);
        Composite composite = new Composite(parent, SWT.BORDER);
        GridLayout layout = new GridLayout();
        composite.setLayout(layout);
        composite.setLayoutData(layoutData);
        tableViewerCreator = new TableViewerCreator(composite);
        tableViewerCreator.setCheckboxInFirstColumn(false);
        tableViewerCreator.setColumnsResizableByDefault(true);
        tableViewerCreator.setLinesVisible(true);
        tableViewerCreator.setLayoutMode(LAYOUT_MODE.CONTINUOUS);

        tableViewerCreator.createTable();

        createJarNameColumn();
        createModuleNameColumn();
        createContextColumn();
        createRequiredColumn();
        createLicenseColumn();

        urlcolumn = createMoreInformationColumn();

        installcolumn = createActionColumn();

        tableViewerCreator.init(inputList);
        addInstallButtons();
        layoutData = new GridData(GridData.FILL_BOTH);
        tableViewerCreator.getTable().setLayoutData(layoutData);
        tableViewerCreator.getTable().pack();

        Composite footComposite = new Composite(composite, SWT.NONE);
        layoutData = new GridData(GridData.FILL_HORIZONTAL);
        footComposite.setLayoutData(layoutData);
        layout = new GridLayout();
        layout.numColumns = 2;
        footComposite.setLayout(layout);

        final Link moreInfor = new Link(footComposite, SWT.NONE);
        layoutData = new GridData(GridData.FILL_HORIZONTAL);
        layoutData.widthHint = 200;
        moreInfor.setText(Messages.getString("ExternalModulesInstallDialog_MoreInfor")); //$NON-NLS-1$
        moreInfor.setLayoutData(layoutData);
        moreInfor.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                Program.launch(HELP_CONTENT);
            }
        });

        createFooter(composite);
        setTitle(title);
        return composite;
    }

    /**
     * DOC sgandon Comment method "updateInstallModulesButtonState".
     */
    protected void updateInstallModulesButtonState() {
        List<ModuleToInstall> inputList = tableViewerCreator.getInputList();
        boolean isEnable = false;
        if (!inputList.isEmpty()) {
            for (ModuleToInstall module : inputList) {
                if (module.getUrl_download() != null) {
                    isEnable = true;
                    break;
                }
            }
        }
        installAllBtn.setEnabled(isEnable);
    }

    /**
     * DOC sgandon Comment method "createJarNameColumn".
     */
    public void createJarNameColumn() {
        TableViewerCreatorColumn column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle(Messages.getString("ExternalModulesInstallDialog_ColumnJarName")); //$NON-NLS-1$
        column.setToolTipHeader(Messages.getString("ExternalModulesInstallDialog_ColumnJarName")); //$NON-NLS-1$
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<ModuleToInstall, String>() {

            @Override
            public String get(ModuleToInstall bean) {
                return bean.getName();
            }

            @Override
            public void set(ModuleToInstall bean, String value) {
            }
        });
        column.setSortable(true);
        tableViewerCreator.setDefaultSort(column, SORT.ASC);
        column.setWeight(5);
        column.setModifiable(false);
    }

    /**
     * DOC sgandon Comment method "createModuleNameColumn".
     */
    public void createModuleNameColumn() {
        TableViewerCreatorColumn column;
        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle(Messages.getString("ExternalModulesInstallDialog_ColumnModuleName")); //$NON-NLS-1$
        column.setToolTipHeader(Messages.getString("ExternalModulesInstallDialog_ColumnModuleName")); //$NON-NLS-1$
        column.setSortable(true);
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<ModuleToInstall, String>() {

            @Override
            public String get(ModuleToInstall bean) {
                return bean.getDescription();
            }

            @Override
            public void set(ModuleToInstall bean, String value) {
            }
        });
        column.setWeight(4);
        column.setModifiable(false);
    }

    /**
     * DOC sgandon Comment method "createContextColumn".
     */
    public void createContextColumn() {
        TableViewerCreatorColumn column;
        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setSortable(true);
        column.setTitle(Messages.getString("ExternalModulesInstallDialog_ColumnRequiredBy")); //$NON-NLS-1$
        column.setToolTipHeader(Messages.getString("ExternalModulesInstallDialog_ColumnRequiredBy")); //$NON-NLS-1$
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<ModuleToInstall, String>() {

            @Override
            public String get(ModuleToInstall bean) {
                return bean.getContext();
            }

            @Override
            public void set(ModuleToInstall bean, String value) {
            }
        });

        column.setModifiable(false);
        column.setWeight(5);
    }

    /**
     * DOC sgandon Comment method "createREquiredColumn".
     */
    public void createRequiredColumn() {
        TableViewerCreatorColumn column;
        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle(Messages.getString("ExternalModulesInstallDialog_ColumnRequired")); //$NON-NLS-1$
        column.setToolTipHeader(Messages.getString("ExternalModulesInstallDialog_ColumnRequired")); //$NON-NLS-1$
        column.setDisplayedValue(""); //$NON-NLS-1$
        column.setSortable(true);
        column.setImageProvider(new IColumnImageProvider<ModuleToInstall>() {

            @Override
            public Image getImage(ModuleToInstall bean) {
                if (bean.isRequired()) {
                    return ImageProvider.getImage(EImage.CHECKED_ICON);
                } else {
                    return ImageProvider.getImage(EImage.UNCHECKED_ICON);
                }
            }
        });
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<ModuleToInstall, Boolean>() {

            @Override
            public Boolean get(ModuleToInstall bean) {
                return bean.isRequired();
            }

            @Override
            public void set(ModuleToInstall bean, Boolean value) {
            }
        });

        column.setModifiable(false);
        column.setWeight(2);
    }

    /**
     * DOC sgandon Comment method "createLicenseColumn".
     */
    public void createLicenseColumn() {
        TableViewerCreatorColumn column;
        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle(Messages.getString("ExternalModulesInstallDialog_ColumnLicense")); //$NON-NLS-1$
        column.setToolTipHeader(Messages.getString("ExternalModulesInstallDialog_ColumnLicense")); //$NON-NLS-1$
        column.setSortable(true);
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<ModuleToInstall, String>() {

            @Override
            public String get(ModuleToInstall bean) {
                return bean.getLicenseType();
            }

            @Override
            public void set(ModuleToInstall bean, String value) {
            }
        });

        column.setModifiable(false);
        column.setWeight(3);
    }

    /**
     * DOC sgandon Comment method "createMoreInformationColumn".
     * 
     * @return
     */
    public TableViewerCreatorColumn createMoreInformationColumn() {
        TableViewerCreatorColumn urlcolumn = new TableViewerCreatorColumn(tableViewerCreator);
        urlcolumn.setTitle(Messages.getString("ExternalModulesInstallDialog_ColumnUrl")); //$NON-NLS-1$
        urlcolumn.setToolTipHeader(Messages.getString("ExternalModulesInstallDialog_ColumnUrl")); //$NON-NLS-1$
        urlcolumn.setModifiable(false);
        urlcolumn.setSortable(true);
        urlcolumn.setWeight(7);
        return urlcolumn;
    }

    /**
     * DOC sgandon Comment method "createActionColumn".
     * 
     * @return
     */
    public TableViewerCreatorColumn createActionColumn() {
        TableViewerCreatorColumn installcolumn = new TableViewerCreatorColumn(tableViewerCreator);
        installcolumn.setTitle(Messages.getString("ExternalModulesInstallDialog_AvailableOnTalendForge")); //$NON-NLS-1$
        installcolumn.setToolTipHeader(Messages.getString("ExternalModulesInstallDialog_AvailableOnTalendForge")); //$NON-NLS-1$
        installcolumn.setModifiable(false);
        installcolumn.setSortable(true);
        installcolumn.setWeight(5);
        return installcolumn;
    }

    @Override
    protected Control createHelpControl(Composite parent) {
        Image helpImage = JFaceResources.getImage(DLG_IMG_HELP);
        if (helpImage != null) {
            return createHelpImageButton(parent, helpImage);
        }
        return createHelpLink(parent);
    }

    private Link createHelpLink(Composite parent) {
        Link link = new Link(parent, SWT.WRAP | SWT.NO_FOCUS);
        ((GridLayout) parent.getLayout()).numColumns++;
        link.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
        link.setText("<a>" + IDialogConstants.HELP_LABEL + "</a>"); //$NON-NLS-1$ //$NON-NLS-2$
        link.setToolTipText(IDialogConstants.HELP_LABEL);
        link.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                helpPressed();
            }
        });
        return link;
    }

    private void helpPressed() {
        Program.launch(HELP_CONTENT);
    }

    private ToolBar createHelpImageButton(Composite parent, Image image) {
        ToolBar toolBar = new ToolBar(parent, SWT.FLAT | SWT.NO_FOCUS);
        ((GridLayout) parent.getLayout()).numColumns++;
        toolBar.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
        final Cursor cursor = new Cursor(parent.getDisplay(), SWT.CURSOR_HAND);
        toolBar.setCursor(cursor);
        toolBar.addDisposeListener(new DisposeListener() {

            @Override
            public void widgetDisposed(DisposeEvent e) {
                cursor.dispose();
            }
        });
        ToolItem item = new ToolItem(toolBar, SWT.NONE);
        item.setImage(image);
        item.setToolTipText(JFaceResources.getString("helpToolTip")); //$NON-NLS-1$
        item.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                helpPressed();
            }
        });
        return toolBar;
    }

    private void emptyLibs() {
        File libsDir = org.eclipse.core.runtime.Platform.getLocation().append(JavaUtils.JAVA_PROJECT_NAME).append(File.separator)
                .append(JavaUtils.JAVA_LIB_DIRECTORY).toFile();
        if (libsDir.exists() && libsDir.isDirectory()) {
            FilesUtils.emptyFolder(libsDir);
        }
    }

    protected void addListeners() {

        installAllBtn.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                List<ModuleToInstall> toInstall = getModulesToBeInstalled();

                final DownloadModuleJob job = new DownloadModuleJob(toInstall);

                job.addJobChangeListener(new JobChangeAdapter() {

                    @Override
                    public void done(final IJobChangeEvent event) {

                        Display.getDefault().asyncExec(new Runnable() {

                            @Override
                            public void run() {
                                if (event.getJob() instanceof DownloadModuleJob) {
                                    DownloadModuleJob job = (DownloadModuleJob) event.getJob();
                                    Set<String> downloadFialed = job.getDownloadFailed();
                                    Set<String> installedModule = job.getInstalledModule();
                                    jarsInstalledSuccuss.addAll(installedModule);
                                    int installedModules = installedModule.size();
                                    String success = installedModules
                                            + Messages.getString("ExternalModulesInstallDialog_DownloadSuccess"); //$NON-NLS-1$
                                    String message = success;
                                    if (!downloadFialed.isEmpty()) {
                                        String fail = Messages.getString("ExternalModulesInstallDialog_DownloadFailed"); //$NON-NLS-1$
                                        String names = ""; //$NON-NLS-1$
                                        for (String name : downloadFialed) {
                                            if (names.length() > 0) {
                                                names += " , " + name; //$NON-NLS-1$
                                            } else {
                                                names += name;
                                            }
                                        }
                                        message = message + fail + names;
                                    }
                                    MessageDialog.openInformation(getShell(),
                                            Messages.getString("ExternalModulesInstallDialog.MessageDialog.Infor"), message); //$NON-NLS-1$
                                    // refreshUI();
                                    if (installedModules > 0) {
                                        emptyLibs();
                                    }
                                }
                            }
                        });
                    }
                });
                job.setUser(true);
                job.setPriority(Job.INTERACTIVE);
                job.schedule();

                close();
            }

        });

    }

    public void openDialog() {
        List<ModuleNeeded> updatedModules = new ArrayList<ModuleNeeded>();
        for (ModuleNeeded neededModule : ModulesNeededProvider.getModulesNeeded()) {
            if (neededModule.getStatus() != ELibraryInstallStatus.NOT_INSTALLED) {
                continue;
            }
            updatedModules.add(neededModule);
        }
        inputList.clear();
        RemoteModulesHelper.getInstance().getNotInstalledModules(updatedModules, inputList, this, true);
    }

    private void refreshUI() {
        showMessage = false;
        openDialog();
    }

    protected void createFooter(Composite parent) {
    }

    // TODO the implementation of this method is horrible and creating too many widgets
    // table/column renderer should be used instead
    protected void addInstallButtons() {
        final AtomicInteger enabledButtonCount = new AtomicInteger(0);
        tableViewerCreator.getTableViewer().getControl().setRedraw(false);
        final Table table = tableViewerCreator.getTable();
        for (final TableItem item : table.getItems()) {
            TableEditor editor = new TableEditor(table);
            Control control = null;
            Object obj = item.getData();
            if (obj instanceof ModuleToInstall) {
                final ModuleToInstall data = (ModuleToInstall) obj;
                if (data.getUrl_download() != null) {// add the button to download
                    final Button button = new Button(table, SWT.FLAT);
                    control = button;
                    enabledButtonCount.incrementAndGet();
                    button.setText(Messages.getString("ExternalModulesInstallDialog_Download")); //$NON-NLS-1$
                    button.setData(item);
                    button.addSelectionListener(new SelectionAdapter() {

                        @Override
                        public void widgetSelected(SelectionEvent e) {
                            table.select(table.indexOf(item));
                            launchIndividualDownload(enabledButtonCount, data, button);
                        }

                    });
                } else {// add the link for manual download
                    Composite composite = new Composite(table, SWT.NONE);
                    composite.setBackground(color);
                    control = composite;
                    GridLayout layout = new GridLayout();
                    layout.marginHeight = 0;
                    layout.verticalSpacing = 1;
                    composite.setLayout(layout);
                    GridData gData = new GridData(GridData.FILL_HORIZONTAL);
                    gData.horizontalAlignment = SWT.CENTER;
                    gData.verticalAlignment = SWT.CENTER;
                    final Link openLink = new Link(composite, SWT.NONE);
                    openLink.setBackground(color);
                    openLink.setLayoutData(gData);
                    openLink.setText("<a href=\"\">" + Messages.getString("ExternalModulesInstallDialog.openInBrowser") + "</a>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                    openLink.addSelectionListener(new SelectionAdapter() {

                        @Override
                        public void widgetSelected(final SelectionEvent e) {
                            Program.launch(data.getUrl_description());
                        }
                    });
                }
                editor.grabHorizontal = true;
                editor.minimumHeight = 20;
                editor.setEditor(control, item, tableViewerCreator.getColumns().indexOf(installcolumn));
                editor.layout();

                // url
                editor = new TableEditor(table);
                Composite composite = new Composite(table, SWT.NONE);
                composite.setBackground(color);
                GridLayout layout = new GridLayout();
                layout.marginHeight = 0;
                layout.marginRight = 0;
                layout.verticalSpacing = 1;
                composite.setLayout(layout);
                GridData gData = new GridData(GridData.FILL_HORIZONTAL);
                // gData.verticalAlignment = SWT.CENTER;
                final Link openLink = new Link(composite, SWT.NONE);
                openLink.setLayoutData(gData);
                openLink.setBackground(color);
                openLink.setText("<a href=\"\">" + data.getUrl_description() + "</a>"); //$NON-NLS-1$ //$NON-NLS-2$
                openLink.addSelectionListener(new SelectionAdapter() {

                    @Override
                    public void widgetSelected(final SelectionEvent e) {
                        Program.launch(data.getUrl_description());
                    }
                });
                editor.grabHorizontal = true;
                editor.minimumHeight = 20;
                editor.setEditor(composite, item, tableViewerCreator.getColumns().indexOf(urlcolumn));
                editor.layout();
            }
        }
        tableViewerCreator.getTableViewer().getTable().layout();
        tableViewerCreator.getTableViewer().refresh(true);
        tableViewerCreator.getTableViewer().getControl().setRedraw(true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.librariesmanager.ui.dialogs.ExternalModulesInstallDialog#createButtonsForButtonBar(org.eclipse.swt
     * .widgets.Composite)
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        installAllBtn = createInstallButton(parent);
        updateInstallModulesButtonState();
        addListeners();// bad name but I accanont change it since it may have been overriden.

    }

    /**
     * Creates the Install button for this wizard dialog. Creates a standard (<code>SWT.PUSH</code>) button and
     * registers for its selection events. Note that the number of columns in the button bar composite is incremented.
     * 
     * @param parent the parent button bar
     * @return the new Install button
     */
    private Button createInstallButton(Composite parent) {
        // increment the number of columns in the button bar
        ((GridLayout) parent.getLayout()).numColumns++;
        Button button = new Button(parent, SWT.PUSH);
        button.setText(Messages.getString("ExternalModulesInstallDialog_InstallAll") + "  "); //$NON-NLS-1$ //$NON-NLS-2$//a space is added cause the last letter is missing on my MAC
        setButtonLayoutData(button);
        button.setFont(parent.getFont());

        return button;
    }

    @Override
    protected void okPressed() {
        super.okPressed();
        color.dispose();
    }

    /**
     * I (SG) do not want to refadctor every thing so I delagate to a IRunnableWithProgress
     **/
    class DownloadModuleJob extends Job {

        private final List<ModuleToInstall> toDownload;

        private DownloadModuleRunnable downloadModuleRunnable;

        public DownloadModuleJob(List<ModuleToInstall> toDownload) {
            super(Messages.getString("ExternalModulesInstallDialog.downloading1")); //$NON-NLS-1$
            this.toDownload = toDownload;
            downloadModuleRunnable = new DownloadModuleRunnable(toDownload);
        }

        @Override
        protected IStatus run(final IProgressMonitor monitor) {
            downloadModuleRunnable.run(monitor);
            return Status.OK_STATUS;
        }

        Set<String> getInstalledModule() {
            return downloadModuleRunnable.installedModules;
        }

        Set<String> getDownloadFailed() {
            return downloadModuleRunnable.downloadFailed;
        }

    }

    class DownloadModuleRunnable implements IRunnableWithProgress {

        List<ModuleToInstall> toDownload;

        private Set<String> downloadFailed;

        private Set<String> installedModules;

        private boolean accepted = false;

        public DownloadModuleRunnable(List<ModuleToInstall> toDownload) {
            //super(Messages.getString("ExternalModulesInstallDialog.downloading1")); //$NON-NLS-1$
            this.toDownload = toDownload;
            downloadFailed = new HashSet<String>();
            installedModules = new HashSet<String>();
        }

        @Override
        public void run(final IProgressMonitor monitor) {
            SubMonitor subMonitor = SubMonitor.convert(monitor,
                    Messages.getString("ExternalModulesInstallDialog.downloading2"), toDownload.size() * 10 + 5); //$NON-NLS-1$
            downLoad(subMonitor);
            if (monitor != null) {
                monitor.done();
            }
        }

        private void downLoad(final SubMonitor monitor) {
            final List<URL> downloadOk = new ArrayList<URL>();
            for (final ModuleToInstall module : toDownload) {
                if (!monitor.isCanceled()) {
                    monitor.subTask(module.getName());
                    monitor.worked(5);

                    String librariesPath = LibrariesManagerUtils.getLibrariesPath(ECodeLanguage.JAVA);
                    File target = new File(librariesPath);
                    if (module.getUrl_download() != null && !"".equals(module.getUrl_download())) { //$NON-NLS-1$
                        try {
                            // check license
                            boolean isLicenseAccepted = LibManagerUiPlugin.getDefault().getPreferenceStore()
                                    .getBoolean(module.getLicenseType());
                            if (!isLicenseAccepted) {
                                Display.getDefault().syncExec(new Runnable() {

                                    @Override
                                    public void run() {
                                        String license = RemoteModulesHelper.getInstance().getLicenseUrl(module.getLicenseType());
                                        ModuleLicenseDialog licenseDialog = new ModuleLicenseDialog(getShell(), module
                                                .getLicenseType(), license, module.getName());
                                        if (licenseDialog.open() != Window.OK) {
                                            downloadFailed.add(module.getName());
                                            accepted = false;
                                        } else {
                                            accepted = true;
                                        }
                                    }
                                });

                            } else {
                                accepted = true;
                            }
                            if (!accepted) {
                                monitor.worked(5);
                                continue;
                            }
                            if (monitor.isCanceled()) {
                                return;
                            }
                            File destination = new File(target.toString() + File.separator + module.getName());
                            DownloadHelperWithProgress downloader = new DownloadHelperWithProgress();
                            downloader.download(new URL(module.getUrl_download()), destination, monitor.newChild(4));
                            downloadOk.add(destination.toURL());
                            installedModules.add(module.getName());
                            monitor.worked(1);
                        } catch (Exception e) {
                            downloadFailed.add(module.getName());
                            ExceptionHandler.process(e);
                            continue;
                        }
                    }
                    accepted = false;
                } else {
                    downloadFailed.add(module.getName());
                }
            }
            if (!downloadOk.isEmpty()) {
                Display.getDefault().syncExec(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            LibManagerUiPlugin.getDefault().getLibrariesService()
                                    .deployLibrarys(downloadOk.toArray(new URL[downloadOk.size()]));
                        } catch (IOException e) {
                            ExceptionHandler.process(e);
                        }
                        monitor.worked(5);
                    }
                });
            }

        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.librariesmanager.ui.dialogs.IJobDownListener#downloadProgress()
     */
    @Override
    public void listModulesDone() {
        Display.getDefault().asyncExec(new Runnable() {

            @Override
            public void run() {
                if (inputList.size() > 0) {
                    open();
                } else if (showMessage) {
                    MessageDialog.openInformation(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                            Messages.getString("ExternalModulesInstallDialog.MessageDialog.Infor"), //$NON-NLS-1$
                            Messages.getString("ExternalModulesInstallDialog_NoExternalModules")); //$NON-NLS-1$
                }
            }
        });

    }

    /**
     * DOC sgandon Comment method "getModulesToBeInstalled".
     * 
     * @return
     */
    protected List<ModuleToInstall> getModulesToBeInstalled() {
        List<ModuleToInstall> theInputList = tableViewerCreator.getInputList();

        List<ModuleToInstall> toInstall = new ArrayList<ModuleToInstall>();
        if (!jarsInstalledSuccuss.isEmpty()) {
            for (ModuleToInstall module : theInputList) {
                if (!jarsInstalledSuccuss.contains(module.getName())) {
                    toInstall.add(module);
                }
            }
        } else {
            toInstall.addAll(theInputList);
        }
        return toInstall;
    }

    /**
     * called when the user clicked on a indivual download button.
     * 
     * @param button, to make it disabled or enabled
     * 
     * @param enabledButtonCount, if 0 means that the last button has been clicked. This needs to maintained when
     * enabeling or disabeling the button
     * @param data, the data to install
     */
    protected void launchIndividualDownload(final AtomicInteger enabledButtonCount, final ModuleToInstall data, Button button) {
        button.setEnabled(false);
        enabledButtonCount.decrementAndGet();
        final DownloadModuleJob job = new DownloadModuleJob(Collections.singletonList(data));
        job.addJobChangeListener(new JobChangeAdapter() {

            @Override
            public void done(IJobChangeEvent event) {
                Display.getDefault().asyncExec(new Runnable() {

                    @Override
                    public void run() {
                        String message = ""; //$NON-NLS-1$
                        if (!job.getInstalledModule().isEmpty()) {
                            message = Messages.getString("ExternalModulesInstallDialog_Download_Ok", data.getName()); //$NON-NLS-1$
                            emptyLibs();
                        } else {
                            message = Messages.getString("ExternalModulesInstallDialog_Download_Fialed", data.getName());; //$NON-NLS-1$
                        }
                        MessageDialog.openInformation(getShell(),
                                Messages.getString("ExternalModulesInstallDialog.MessageDialog.Infor"), message); //$NON-NLS-1$
                    }
                });
            }
        });
        job.setUser(true);
        job.setPriority(Job.INTERACTIVE);
        job.schedule();
        if (enabledButtonCount.get() == 0) {
            close();
        }
    }

}
