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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
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
import org.eclipse.swt.graphics.Font;
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
import org.talend.core.CorePlugin;
import org.talend.core.download.DownloadHelper;
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

    private Font font = new Font(null, "Arial", 9, SWT.NORMAL); //$NON-NLS-1$

    private Font fontMac = new Font(null, "Arial", 12, SWT.NORMAL); //$NON-NLS-1$

    private final String osName = System.getProperties().getProperty("os.name"); //$NON-NLS-1$

    private Color color = new Color(null, 255, 255, 255);

    private TableViewerCreator tableViewerCreator;

    protected String text;

    protected String title;

    private Button installAllBtn;

    protected List<String> jarsInstalledSuccuss = new ArrayList<String>();

    private List<Button> installButtons = new ArrayList<Button>();

    protected List<ModuleToInstall> inputList = new ArrayList<ModuleToInstall>();

    private boolean showMessage = true;

    public ExternalModulesInstallDialog(Shell shell, String text, String title) {
        super(shell);
        setShellStyle(SWT.CLOSE | SWT.MIN | SWT.MAX | SWT.TITLE | SWT.BORDER | SWT.RESIZE | getDefaultOrientation());
        if (osName.contains("Mac")) { //$NON-NLS-1$
            font = fontMac;
        }

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

        TableViewerCreatorColumn urlcolumn = new TableViewerCreatorColumn(tableViewerCreator);
        urlcolumn.setTitle(Messages.getString("ExternalModulesInstallDialog_ColumnUrl")); //$NON-NLS-1$
        urlcolumn.setToolTipHeader(Messages.getString("ExternalModulesInstallDialog_ColumnUrl")); //$NON-NLS-1$
        urlcolumn.setModifiable(false);
        urlcolumn.setSortable(true);
        urlcolumn.setWeight(7);

        TableViewerCreatorColumn installcolumn = new TableViewerCreatorColumn(tableViewerCreator);
        installcolumn.setTitle(Messages.getString("ExternalModulesInstallDialog_AvailableOnTalendForge")); //$NON-NLS-1$
        installcolumn.setToolTipHeader(Messages.getString("ExternalModulesInstallDialog_AvailableOnTalendForge")); //$NON-NLS-1$
        installcolumn.setModifiable(false);
        installcolumn.setSortable(true);
        installcolumn.setWeight(5);
        tableViewerCreator.init(inputList);
        addInstallButtons(installcolumn, urlcolumn);
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

        installAllBtn = new Button(footComposite, SWT.NONE);
        installAllBtn.setText(Messages.getString("ExternalModulesInstallDialog_InstallAll")); //$NON-NLS-1$

        createFooter(composite);
        setTitle(title);
        addListeners();
        return composite;
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

    private void addListeners() {

        installAllBtn.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                // installAllBtn.setEnabled(false);
                // for (Button button : installButtons) {
                // button.setEnabled(false);
                // }

                List<ModuleToInstall> inputList = tableViewerCreator.getInputList();

                List<ModuleToInstall> toInstall = new ArrayList<ModuleToInstall>();
                if (!jarsInstalledSuccuss.isEmpty()) {
                    for (ModuleToInstall module : inputList) {
                        if (!jarsInstalledSuccuss.contains(module.getName())) {
                            toInstall.add(module);
                        }
                    }
                } else {
                    toInstall.addAll(inputList);
                }

                final DownloadModuleJob job = new DownloadModuleJob(toInstall);

                job.addJobChangeListener(new JobChangeAdapter() {

                    @Override
                    public void done(final IJobChangeEvent event) {

                        Display.getDefault().asyncExec(new Runnable() {

                            @Override
                            public void run() {
                                if (event.getJob() instanceof DownloadModuleJob) {
                                    DownloadModuleJob job = (DownloadModuleJob) event.getJob();
                                    Set<String> downloadFialed = job.downloadFialed;
                                    jarsInstalledSuccuss.addAll(job.installedModules);
                                    int installedModules = job.installedModules.size();
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

    private void addInstallButtons(TableViewerCreatorColumn installColumn, TableViewerCreatorColumn urlColumn) {
        installButtons.clear();
        tableViewerCreator.getTableViewer().getControl().setRedraw(false);
        final Table table = tableViewerCreator.getTable();
        for (final TableItem item : table.getItems()) {
            TableEditor editor = new TableEditor(table);
            Control control = null;
            Object obj = item.getData();
            if (obj instanceof ModuleToInstall) {
                final ModuleToInstall data = (ModuleToInstall) obj;
                if (data.getUrl_download() != null) {
                    final Button button = new Button(table, SWT.FLAT);
                    control = button;
                    installButtons.add(button);
                    button.setText(Messages.getString("ExternalModulesInstallDialog_Download")); //$NON-NLS-1$
                    button.setData(item);
                    button.addSelectionListener(new SelectionAdapter() {

                        @Override
                        public void widgetSelected(SelectionEvent e) {
                            button.setEnabled(false);
                            table.select(table.indexOf(item));
                            List<ModuleToInstall> datalist = new ArrayList<ModuleToInstall>();
                            datalist.add(data);
                            final DownloadModuleJob job = new DownloadModuleJob(datalist);
                            job.addJobChangeListener(new JobChangeAdapter() {

                                @Override
                                public void done(IJobChangeEvent event) {
                                    Display.getDefault().asyncExec(new Runnable() {

                                        @Override
                                        public void run() {
                                            String message = ""; //$NON-NLS-1$
                                            if (!job.installedModules.isEmpty()) {
                                                message = Messages.getString(
                                                        "ExternalModulesInstallDialog_Download_Ok", data.getName()); //$NON-NLS-1$
                                                emptyLibs();
                                            } else {
                                                message = Messages.getString(
                                                        "ExternalModulesInstallDialog_Download_Fialed", data.getName());; //$NON-NLS-1$
                                            }
                                            MessageDialog.openInformation(
                                                    getShell(),
                                                    Messages.getString("ExternalModulesInstallDialog.MessageDialog.Infor"), message); //$NON-NLS-1$
                                        }
                                    });
                                }
                            });
                            job.setUser(true);
                            job.setPriority(Job.INTERACTIVE);
                            job.schedule();
                            int n = 0;
                            for (Button button : installButtons) {
                                if (!button.isEnabled()) {
                                    n++;
                                }
                            }
                            if (n == installButtons.size()) {
                                close();
                            }
                        }

                    });
                } else {
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
                editor.setEditor(control, item, tableViewerCreator.getColumns().indexOf(installColumn));
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
                editor.setEditor(composite, item, tableViewerCreator.getColumns().indexOf(urlColumn));
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
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
    }

    @Override
    protected void okPressed() {
        super.okPressed();
        font.dispose();
        color.dispose();
        fontMac.dispose();
    }

    class DownloadModuleJob extends Job {

        List<ModuleToInstall> toDownload;

        private Set<String> downloadFialed;

        private Set<String> installedModules;

        private boolean accepted = false;

        public DownloadModuleJob(List<ModuleToInstall> toDownload) {
            super(Messages.getString("ExternalModulesInstallDialog.downloading1")); //$NON-NLS-1$
            this.toDownload = toDownload;
            downloadFialed = new HashSet<String>();
            installedModules = new HashSet<String>();
        }

        @Override
        protected IStatus run(final IProgressMonitor monitor) {
            monitor.beginTask(Messages.getString("ExternalModulesInstallDialog.downloading2"), toDownload.size() * 10 + 5); //$NON-NLS-1$
            downLoad(monitor);
            monitor.done();
            return Status.OK_STATUS;
        }

        private void downLoad(final IProgressMonitor monitor) {
            final List<URL> downloadOk = new ArrayList<URL>();
            for (final ModuleToInstall module : toDownload) {
                if (!monitor.isCanceled()) {
                    monitor.subTask(module.getName());
                    monitor.worked(5);
                    DownloadHelper downloader = new DownloadHelper();
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
                                            downloadFialed.add(module.getName());
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

                            File destination = new File(target.toString() + File.separator + module.getName());
                            downloader.download(new URL(module.getUrl_download()), destination);
                            downloadOk.add(destination.toURL());
                            installedModules.add(module.getName());
                            monitor.worked(2);
                        } catch (Exception e) {
                            downloadFialed.add(module.getName());
                            ExceptionHandler.process(e);
                            continue;
                        }
                    }
                    accepted = false;
                } else {
                    downloadFialed.add(module.getName());
                }
            }
            if (!downloadOk.isEmpty()) {
                Display.getDefault().syncExec(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            CorePlugin.getDefault().getLibrariesService()
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

}
