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
package org.talend.librariesmanager.ui.dialogs;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.commons.ui.runtime.image.EImage;
import org.talend.commons.ui.runtime.image.ImageProvider;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorNotModifiable.LAYOUT_MODE;
import org.talend.commons.ui.swt.tableviewer.behavior.IColumnImageProvider;
import org.talend.commons.utils.data.bean.IBeanPropertyAccessors;
import org.talend.core.CorePlugin;
import org.talend.core.download.DownloadHelper;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.general.ModuleNeeded;
import org.talend.core.model.general.ModuleNeeded.ELibraryInstallStatus;
import org.talend.core.model.general.ModuleToInstall;
import org.talend.librariesmanager.Activator;
import org.talend.librariesmanager.model.ModulesNeededProvider;
import org.talend.librariesmanager.prefs.PreferencesUtilities;
import org.talend.librariesmanager.utils.RemoteModulesHelper;

/**
 * DOC wchen class global comment. Detailled comment
 */
public class ExternalModulesInstallDialog extends TitleAreaDialog {

    public static final String DO_NOT_SHOW_EXTERNALMODULESINSTALLDIALOG = "do_not_show_ExternalModulesInstallDialog";

    private Font font = new Font(null, "Arial", 9, SWT.NORMAL);

    private Font fontMac = new Font(null, "Arial", 12, SWT.NORMAL);

    private final String osName = System.getProperties().getProperty("os.name");

    private Color color = new Color(null, 255, 255, 255);

    private TableViewerCreator tableViewerCreator;

    protected String text;

    protected String title;

    private Button installAllBtn;

    protected List<String> installedJars = new ArrayList<String>();

    private List<Button> installButtons = new ArrayList<Button>();

    private List<ModuleToInstall> inputList;

    /**
     * DOC wchen TalendForgeDialog constructor comment.
     * 
     * @param shell
     */
    public ExternalModulesInstallDialog(Shell shell) {
        super(shell);
        setShellStyle(SWT.CLOSE | SWT.MAX | SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL | SWT.RESIZE | getDefaultOrientation());
        if (osName.contains("Mac")) {
            font = fontMac;
        }
        this.title = "The following modules are not yet installed. Please download and install all required modules.";
        this.text = "List of modules not installed in the product";
    }

    @Override
    protected void initializeBounds() {
        super.initializeBounds();
        getShell().setSize(800, 400);
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
        column.setTitle("Module");
        column.setToolTipHeader("Module");
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
        column.setTitle("Required by component");
        column.setToolTipHeader("Required by component");
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
        column.setWeight(6);

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("Required");
        column.setToolTipHeader("Required");
        column.setDisplayedValue("");
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

        column.setModifiable(false);
        column.setWeight(2);

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("License");
        column.setToolTipHeader("License");
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
        urlcolumn.setTitle("More information");
        urlcolumn.setToolTipHeader("More information");
        urlcolumn.setModifiable(false);
        urlcolumn.setWeight(8);

        TableViewerCreatorColumn installcolumn = new TableViewerCreatorColumn(tableViewerCreator);
        installcolumn.setTitle("Available on TalendForge");
        installcolumn.setToolTipHeader("Available on TalendForge");
        installcolumn.setModifiable(false);
        installcolumn.setWeight(5);
        if (inputList == null) {
            inputList = getUpdatedModulesToInstall();
        }
        tableViewerCreator.init(inputList);
        addInstallButtons(installcolumn, urlcolumn);
        layoutData = new GridData(GridData.FILL_BOTH);
        tableViewerCreator.getTable().setLayoutData(layoutData);

        Composite footComposite = new Composite(composite, SWT.NONE);
        layoutData = new GridData(GridData.FILL_HORIZONTAL);
        footComposite.setLayoutData(layoutData);
        layout = new GridLayout();
        layout.numColumns = 2;
        footComposite.setLayout(layout);

        Label label = new Label(footComposite, SWT.WRAP);
        layoutData = new GridData(GridData.FILL_HORIZONTAL);
        layoutData.widthHint = 200;
        label.setText("Click here to obtain more informations about external modules");
        label.setLayoutData(layoutData);

        installAllBtn = new Button(footComposite, SWT.NONE);
        installAllBtn.setText("Download and install all modules available");

        createFooter(composite);
        setTitle(title);
        addListeners();
        return composite;
    }

    private void addListeners() {

        installAllBtn.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {

                for (Button button : installButtons) {
                    button.setEnabled(false);
                }

                List<ModuleToInstall> inputList = tableViewerCreator.getInputList();
                final DownloadModuleJob job = new DownloadModuleJob(inputList);

                job.addJobChangeListener(new JobChangeAdapter() {

                    @Override
                    public void done(final IJobChangeEvent event) {

                        Display.getDefault().asyncExec(new Runnable() {

                            @Override
                            public void run() {
                                if (event.getJob() instanceof DownloadModuleJob) {
                                    DownloadModuleJob job = (DownloadModuleJob) event.getJob();
                                    Set<String> downloadFialed = job.getDownloadFialed();
                                    installedJars.addAll(job.getInstalledModules());
                                    int installedModules = job.getInstalledModules().size();
                                    String success = installedModules + " Modules installed successfully ! \n";
                                    String message = success;
                                    if (!downloadFialed.isEmpty()) {
                                        String fail = "Some modules installed failed :";
                                        String names = "";
                                        for (String name : downloadFialed) {
                                            if (names.length() > 0) {
                                                names = " | " + name;
                                            } else {
                                                names = name;
                                            }
                                        }
                                        message = message + fail + names;
                                    }
                                    MessageDialog.openInformation(getShell(), "Information", message);
                                    refreshUI();
                                }
                            }
                        });
                    }
                });
                job.setUser(true);
                job.setPriority(Job.INTERACTIVE);
                job.schedule();
                job.wakeUp();
            }

        });

    }

    /**
     * 
     * ONLY call this function if need to update the module list
     */
    protected List<ModuleToInstall> getUpdatedModulesToInstall() {
        List<ModuleNeeded> updatedModules = new ArrayList<ModuleNeeded>();
        for (ModuleNeeded neededModule : ModulesNeededProvider.getModulesNeeded()) {
            if (neededModule.getStatus() != ELibraryInstallStatus.NOT_INSTALLED) {
                continue;
            }
            updatedModules.add(neededModule);
        }

        return RemoteModulesHelper.getInstance().getNotInstalledModules(updatedModules);
    }

    private void refreshUI() {
        inputList = getUpdatedModulesToInstall();
        if (!inputList.isEmpty()) {
            if (!tableViewerCreator.getTable().isDisposed()) {
                tableViewerCreator.init(inputList);
                tableViewerCreator.refresh();
            }
        } else {
            okPressed();
        }
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
                    button.setText("Download and Install");
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
                                public void done(final IJobChangeEvent event) {

                                    Display.getDefault().asyncExec(new Runnable() {

                                        @Override
                                        public void run() {
                                            // button.setEnabled(true);
                                        }
                                    });
                                }
                            });
                            job.setUser(true);
                            job.setPriority(Job.INTERACTIVE);
                            job.schedule();
                            job.wakeUp();
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
                    openLink.setText("<a href=\"\">" + "Open in browser" + "</a>"); //$NON-NLS-1$ //$NON-NLS-2$
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

        public DownloadModuleJob(List<ModuleToInstall> toDownload) {
            super("Download extended modules");
            this.toDownload = toDownload;
            downloadFialed = new HashSet<String>();
            installedModules = new HashSet<String>();
        }

        @Override
        protected IStatus run(final IProgressMonitor monitor) {
            monitor.beginTask("Download modules", toDownload.size() * 2);
            Display.getDefault().syncExec(new Runnable() {

                @Override
                public void run() {
                    downLoad(monitor);
                }
            });
            monitor.done();
            return Status.OK_STATUS;
        }

        private void downLoad(IProgressMonitor monitor) {
            IRunnableWithProgress iRunnableWithProgress = new IRunnableWithProgress() {

                @Override
                public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    Display.getDefault().syncExec(new Runnable() {

                        @Override
                        public void run() {
                            monitor.beginTask("Download modules", toDownload.size());
                            List<URL> downloadOk = new ArrayList<URL>();
                            for (final ModuleToInstall module : toDownload) {
                                monitor.subTask("Download " + module.getName() + module.getUrl_description());
                                DownloadHelper downloader = new DownloadHelper();
                                // downloader.addDownloadListener(this);
                                String librariesPath = PreferencesUtilities.getLibrariesPath(ECodeLanguage.JAVA);
                                File target = new File(librariesPath);
                                if (module.getUrl_download() != null && !"".equals(module.getUrl_download())) {
                                    try {
                                        // check license
                                        boolean isLicenseAccepted = Activator.getDefault().getPreferenceStore()
                                                .getBoolean(module.getLicenseType());
                                        if (!isLicenseAccepted) {
                                            String license = RemoteModulesHelper.getInstance().getLicenseUrl(
                                                    module.getLicenseType());
                                            ModuleLicenseDialog licenseDialog = new ModuleLicenseDialog(getShell(), module
                                                    .getLicenseType(), license, module.getDescription());
                                            if (licenseDialog.open() != Window.OK) {
                                                downloadFialed.add(module.getName());
                                                monitor.worked(1);
                                                continue;
                                            }
                                        }

                                        File destination = new File(target.toString() + File.separator + module.getName());
                                        downloader.download(new URL(module.getUrl_download()), destination);
                                        downloadOk.add(destination.toURL());
                                        installedModules.add(module.getName());
                                        monitor.worked(1);
                                    } catch (Exception e) {
                                        downloadFialed.add(module.getName());
                                        continue;
                                    }
                                }

                            }
                            try {
                                if (!downloadOk.isEmpty()) {
                                    CorePlugin.getDefault().getLibrariesService()
                                            .deployLibrarys(downloadOk.toArray(new URL[downloadOk.size()]));
                                }
                            } catch (IOException e) {
                                ExceptionHandler.process(e);
                            }
                            monitor.done();
                        }
                    });
                }
            };

            try {
                new ProgressMonitorDialog(getShell()).run(true, true, iRunnableWithProgress);
            } catch (InvocationTargetException e1) {
            } catch (InterruptedException e1) {
            }

        }

        public Set<String> getInstalledModules() {
            return this.installedModules;
        }

        public Set<String> getDownloadFialed() {
            return this.downloadFialed;
        }

    }

    public List<String> getInstalledJars() {
        return this.installedJars;
    }

    public boolean needOpen() {
        inputList = getUpdatedModulesToInstall();
        if (!inputList.isEmpty()) {
            return true;
        }
        return false;
    }

}
