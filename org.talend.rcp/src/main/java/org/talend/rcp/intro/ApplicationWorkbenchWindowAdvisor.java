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
package org.talend.rcp.intro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.commands.ActionHandler;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PerspectiveAdapter;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.internal.WorkbenchPage;
import org.eclipse.ui.internal.ide.EditorAreaDropAdapter;
import org.eclipse.ui.internal.ide.IDEInternalPreferences;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.commons.utils.workbench.extensions.ExtensionImplementationProvider;
import org.talend.commons.utils.workbench.extensions.ExtensionPointLimiterImpl;
import org.talend.commons.utils.workbench.extensions.IExtensionPointLimiter;
import org.talend.core.CorePlugin;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.PluginChecker;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.model.general.Project;
import org.talend.core.prefs.ITalendCorePrefConstants;
import org.talend.core.prefs.PreferenceManipulator;
import org.talend.core.ui.ISQLBuilderService;
import org.talend.core.ui.branding.IBrandingConfiguration;
import org.talend.core.ui.branding.IBrandingService;
import org.talend.designer.business.diagram.custom.IDiagramModelService;
import org.talend.rcp.Activator;
import org.talend.rcp.i18n.Messages;
import org.talend.rcp.util.ApplicationDeletionUtil;
import org.talend.repository.ui.views.IRepositoryView;

/**
 * DOC ccarbone class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
@SuppressWarnings("restriction")
public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    private List<IAction> actions = new ArrayList<IAction>();

    ApplicationActionBarAdvisor adviser = null;

    private static String componentViewId = "org.talend.designer.core.ui.views.properties.ComponentSettingsView";

    private static String gefViewId = "org.eclipse.gef.ui.palette_view";

    private static String repositoryViewId = "org.talend.repository.views.repository";

    private static String processViewId = "org.talend.designer.runprocess.ui.views.processview";

    private Composite foreGroundComposite;

    private Composite parentComposite;

    private Composite backGroundComposite;

    private boolean isTos = false;

    private StackLayout stackLayout = new StackLayout();

    public static final IExtensionPointLimiter GLOBAL_ACTIONS = new ExtensionPointLimiterImpl("org.talend.core.global_actions", //$NON-NLS-1$
            "GlobalAction"); //$NON-NLS-1$

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    @Override
    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return adviser = new ApplicationActionBarAdvisor(configurer);
    }

    @Override
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setInitialSize(new Point(1000, 750));
        configurer.setShowCoolBar(true);
        configurer.setShowStatusLine(true);
        configurer.setShowProgressIndicator(true);
        configurer.setShowPerspectiveBar(true);
        configurer.configureEditorAreaDropListener(new EditorAreaDropAdapter(configurer.getWindow()));
        RepositoryContext repositoryContext = (RepositoryContext) CorePlugin.getContext().getProperty(
                Context.REPOSITORY_CONTEXT_KEY);
        Project project = repositoryContext.getProject();

        Object buildId = Activator.getDefault().getBundle().getHeaders().get(org.osgi.framework.Constants.BUNDLE_VERSION);

        String appName = configurer.getTitle(); // BrandingService.getInstance().getFullProductName();
        PreferenceManipulator prefManipulator = new PreferenceManipulator(CorePlugin.getDefault().getPreferenceStore());
        configurer
                .setTitle(appName
                        + " (" + buildId + ") | " + repositoryContext.getUser() + " | " + project.getLabel() + " (" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
                        + Messages.getString("ApplicationWorkbenchWindowAdvisor.repositoryConnection") + ": " + prefManipulator.getLastConnection() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
        IBrandingService service = (IBrandingService) GlobalServiceRegister.getDefault().getService(IBrandingService.class);
        IBrandingConfiguration brandingConfiguration = service.getBrandingConfiguration();
        ActionBarBuildHelper helper = (ActionBarBuildHelper) brandingConfiguration.getHelper();
        if (helper == null) {
            helper = new ActionBarBuildHelper();
            brandingConfiguration.setHelper(helper);
        }
        helper.preWindowOpen(configurer);
        // generate welcome header image.
        // if (brandingConfiguration instanceof DefaultBrandingConfiguration) {
        // ((DefaultBrandingConfiguration) brandingConfiguration).generateWelcomeHeaderImage();
        // }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.application.WorkbenchWindowAdvisor#postWindowOpen()
     */
    @Override
    public void postWindowOpen() {
        // intro

        IPreferenceStore preferenceStore = CorePlugin.getDefault().getPreferenceStore();
        boolean alwaysWelcome = preferenceStore.getBoolean(ITalendCorePrefConstants.ALWAYS_WELCOME);
        if (alwaysWelcome) {
            getWindowConfigurer().getWindow().getWorkbench().getIntroManager()
                    .showIntro(getWindowConfigurer().getWindow(), !alwaysWelcome);
        }

        createActions();
        registerActions();
        adviser.getHelper().postWindowOpen();
        IBrandingService service = (IBrandingService) GlobalServiceRegister.getDefault().getService(IBrandingService.class);
        getWindowConfigurer()
                .setTitle(getWindowConfigurer().getTitle() + service.getBrandingConfiguration().getAdditionalTitle());

        /**
         * PTODO need remove this, if there is not only merging ref-project option in the repository page.(feature 6725)
         * 
         * @see org.talend.designer.core.ui.preferences.RepositoryPreferencePage
         */
        if (!PluginChecker.isRefProjectLoaded()) {
            String[] prefsId = { "org.talend.designer.core.ui.preferences.RepositoryPreferencePage" }; //$NON-NLS-1$
            ApplicationDeletionUtil.removePreferencePages(this.getWindowConfigurer().getWindow(), Arrays.asList(prefsId));
        }

        // feature 18752
        setEditorAreaBG();
        regisitPerspectiveListener();
    }

    private void regisitPerspectiveListener() {
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().addPerspectiveListener(new PerspectiveAdapter() {

            @Override
            public void perspectiveActivated(IWorkbenchPage page, IPerspectiveDescriptor perspective) {
                // MOD xqliu 2010-10-14 bug 15756
                String pId = perspective.getId();
                if (!pId.equals("org.talend.rcp.perspective")) {
                    clearEditorAreaBG(false);
                } else {
                    if (isTos == true) {
                        isTos = false;
                        setEditorAreaBG();
                    }
                    clearEditorAreaBG(true);
                }
            }
        });
    }

    private void clearEditorAreaBG(boolean flag) {
        if (!parentComposite.isDisposed() && parentComposite != null)
            if (flag) {
                stackLayout.topControl = foreGroundComposite;
                parentComposite.layout();
            } else {
                stackLayout.topControl = backGroundComposite;
                parentComposite.layout();
            }
    }

    private void setEditorAreaBG() {
        // PlatformUI.getWorkbench().get
        if (getWindowConfigurer().getWindow() == null) {
            return;
        }
        if (getWindowConfigurer().getWindow().getActivePage() == null) {
            return;
        }
        IBrandingService brandingService = (IBrandingService) GlobalServiceRegister.getDefault().getService(
                IBrandingService.class);
        String title = brandingService.getShortProductName();
        final WorkbenchPage page = (WorkbenchPage) getWindowConfigurer().getWindow().getActivePage();
        if (!page.getPerspective().getId().equals("org.talend.rcp.perspective")) {
            isTos = true;
            return;
        }
        Composite client = page.getClientComposite();
        Control[] children = client.getChildren();
        for (int i = 0; i < children.length; i++) {
            if (children[i] instanceof Composite) {
                Composite child = (Composite) children[i];
                Control[] controls = child.getChildren();
                for (final Control control : controls) {
                    if (control instanceof CTabFolder) {
                        CTabFolder tabFolder = (CTabFolder) control;
                        Control[] parents = tabFolder.getChildren();
                        for (int j = 0; j < parents.length; j++) {
                            if (parents[j] instanceof Composite) {
                                Composite parent = (Composite) parents[j];
                                parent.redraw();
                                Image image = Activator.getImageDescriptor("icons/WelcomeScreenMockup.png").createImage();
                                Image icon = Activator.getImageDescriptor("icons/createjob.png").createImage();
                                GC gc = new GC(image);
                                gc.setFont(new Font(parent.getDisplay(), "Arial", 10, SWT.NONE));
                                gc.drawString(Messages.getString("tips.createJob"), 115, 145, true);
                                gc.drawString(Messages.getString("tips.clickChoose"), 115, 160, true);
                                gc.drawImage(icon, 265, 160);
                                gc.drawText(Messages.getString("tips.optionMenu"), 357, 160, true);
                                gc.drawText(Messages.getString("tips.addDataSource"), 485, 200, true);
                                gc.drawText(Messages.getString("tips.components"), 485, 220, true);
                                gc.setForeground(new Color(null, 0, 0, 0));
                                gc.setFont(new Font(parent.getDisplay(), "Arial", 10, SWT.NONE));
                                gc.drawText(Messages.getString("tips.viewAndEdit"), 125, 255, true);
                                gc.drawText("in the", 125, 275, true);

                                gc.drawText(Messages.getString("tips.finallyText"), 435, 320, true);
                                gc.setForeground(new Color(null, 0, 0, 0));
                                gc.setFont(new Font(parent.getDisplay(), "Arial", 10, SWT.NONE));
                                gc.drawText(Messages.getString("tips.executeJob"), 590, 320, true);
                                gc.drawText(Messages.getString("tips.viewResults"), 435, 340, true);
                                gc.dispose();

                                parentComposite = new Composite(parent, SWT.CENTER);
                                parentComposite.setLayout(stackLayout);
                                parentComposite.setSize(850, 450);
                                backGroundComposite = new Composite(parentComposite, SWT.CENTER);
                                backGroundComposite.setSize(850, 450);
                                foreGroundComposite = new Composite(parentComposite, SWT.CENTER);

                                foreGroundComposite.setLayout(new FormLayout());

                                Label textTitle = new Label(foreGroundComposite, SWT.CENTER);
                                FormData textTitleData = new FormData();
                                textTitleData.left = new FormAttachment(0, 50);
                                textTitleData.right = new FormAttachment(100, -65);
                                textTitleData.top = new FormAttachment(0, 12);
                                textTitleData.bottom = new FormAttachment(0, 50);
                                textTitle.setLayoutData(textTitleData);
                                textTitle.setFont(new Font(foreGroundComposite.getDisplay(), "Arial", 25, SWT.BOLD));
                                textTitle.setBackground(new Color(null, 255, 255, 204));
                                textTitle.setText(Messages.getString("tips.welcome") + " " + title);

                                Label textSecondTitle = new Label(foreGroundComposite, SWT.CENTER);
                                FormData textTitleData2 = new FormData();
                                textTitleData2.left = new FormAttachment(0, 50);
                                textTitleData2.right = new FormAttachment(100, -65);
                                textTitleData2.top = new FormAttachment(0, 50);
                                textTitleData2.bottom = new FormAttachment(0, 70);
                                textSecondTitle.setLayoutData(textTitleData2);
                                textSecondTitle.setFont(new Font(foreGroundComposite.getDisplay(), "Arial", 13, SWT.NONE));
                                textSecondTitle.setBackground(new Color(null, 255, 255, 204));
                                textSecondTitle.setText(title + " " + Messages.getString("tips.title"));

                                Label textThirdTitle = new Label(foreGroundComposite, SWT.CENTER);
                                FormData textTitleData3 = new FormData();
                                textTitleData3.left = new FormAttachment(0, 50);
                                textTitleData3.right = new FormAttachment(100, -65);
                                textTitleData3.top = new FormAttachment(0, 70);
                                textTitleData3.bottom = new FormAttachment(0, 90);
                                textThirdTitle.setLayoutData(textTitleData3);
                                textThirdTitle.setFont(new Font(foreGroundComposite.getDisplay(), "Arial", 13, SWT.NONE));
                                textThirdTitle.setBackground(new Color(null, 255, 255, 204));
                                textThirdTitle.setText(Messages.getString("tips.getStart"));

                                Link jobDesign = new Link(foreGroundComposite, SWT.NONE);
                                FormData layoutData = new FormData();
                                layoutData.left = new FormAttachment(0, 381);
                                layoutData.right = new FormAttachment(0, 485);
                                layoutData.top = new FormAttachment(0, 145);
                                layoutData.bottom = new FormAttachment(0, 165);
                                jobDesign.setLayoutData(layoutData);
                                jobDesign.setBackground(new Color(null, 255, 255, 204));
                                jobDesign.setText("<a>Job Designer</a>.");
                                jobDesign.addListener(SWT.Selection, new Listener() {

                                    public void handleEvent(Event event) {
                                        IViewPart view = page.findView(repositoryViewId);
                                        try {
                                            view = page.showView(repositoryViewId);
                                        } catch (Exception e) {
                                            ExceptionHandler.process(e);
                                        }
                                        page.findView(repositoryViewId).setFocus();
                                    }
                                });
                                Link palette = new Link(foreGroundComposite, SWT.NONE);
                                FormData paletteData = new FormData();
                                paletteData.left = new FormAttachment(0, 680);
                                paletteData.right = new FormAttachment(0, 725);
                                paletteData.top = new FormAttachment(0, 220);
                                paletteData.bottom = new FormAttachment(0, 240);
                                palette.setLayoutData(paletteData);
                                palette.setBackground(new Color(null, 255, 255, 204));
                                palette.setText("<a>Palette</a>.");
                                palette.addListener(SWT.Selection, new Listener() {

                                    public void handleEvent(Event event) {
                                        IViewPart view = page.findView(gefViewId);
                                        try {
                                            view = page.showView(gefViewId);
                                        } catch (Exception e) {
                                            ExceptionHandler.process(e);
                                        }
                                        page.findView(gefViewId).setFocus();
                                    }
                                });
                                Link component = new Link(foreGroundComposite, SWT.NONE);
                                FormData componentData = new FormData();
                                componentData.left = new FormAttachment(0, 162);
                                componentData.right = new FormAttachment(0, 260);
                                componentData.top = new FormAttachment(0, 275);
                                componentData.bottom = new FormAttachment(0, 295);
                                component.setLayoutData(componentData);
                                component.setBackground(new Color(null, 255, 255, 204));
                                component.setText("<a>Component</a> tab.");
                                component.addListener(SWT.Selection, new Listener() {

                                    public void handleEvent(Event event) {
                                        IViewPart view = page.findView(componentViewId);
                                        try {
                                            view = page.showView(componentViewId);
                                        } catch (Exception e) {
                                            ExceptionHandler.process(e);
                                        }
                                        page.findView(componentViewId).setFocus();
                                    }
                                });
                                Link runJob = new Link(foreGroundComposite, SWT.NONE);
                                FormData runJobData = new FormData();
                                runJobData.left = new FormAttachment(0, 533);
                                runJobData.right = new FormAttachment(0, 590);
                                runJobData.top = new FormAttachment(0, 320);
                                runJobData.bottom = new FormAttachment(0, 340);
                                runJob.setLayoutData(runJobData);
                                runJob.setBackground(new Color(null, 255, 255, 204));
                                runJob.setText("<a> Run Job</a>");
                                runJob.addListener(SWT.Selection, new Listener() {

                                    public void handleEvent(Event event) {
                                        IViewPart view = page.findView(processViewId);
                                        try {
                                            view = page.showView(processViewId);
                                        } catch (Exception e) {
                                            ExceptionHandler.process(e);
                                        }
                                        page.findView(processViewId).setFocus();
                                    }
                                });
                                stackLayout.topControl = foreGroundComposite;
                                foreGroundComposite.setBackgroundImage(image);
                                foreGroundComposite.setSize(850, 450);
                                parentComposite.layout();
                                // lala.layout();

                                // tabFolder.pack();
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * DOC smallet Comment method "createActions".
     */
    private void createActions() {

        List<IAction> list = ExtensionImplementationProvider.getInstance(GLOBAL_ACTIONS);
        actions.addAll(list);
    }

    /**
     * DOC smallet Comment method "registerActions".
     */
    private void registerActions() {
        IContextService contextService = (IContextService) Activator.getDefault().getWorkbench()
                .getAdapter(IContextService.class);
        contextService.activateContext("talend.global"); //$NON-NLS-1$

        IWorkbench workbench = PlatformUI.getWorkbench();
        IHandlerService handlerService = (IHandlerService) workbench.getService(IHandlerService.class);

        IHandler handler;
        for (IAction action : actions) {
            handler = new ActionHandler(action);
            handlerService.activateHandler(action.getActionDefinitionId(), handler);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.application.WorkbenchWindowAdvisor#postWindowClose()
     */
    @Override
    public void postWindowClose() {
        if (GlobalServiceRegister.getDefault().isServiceRegistered(ISQLBuilderService.class)) {
            ISQLBuilderService service = (ISQLBuilderService) GlobalServiceRegister.getDefault().getService(
                    ISQLBuilderService.class);
            Shell[] shelles = Display.getDefault().getShells();
            for (Shell shell : shelles) {
                if (!shell.isDisposed() && shell.getData() != null) {
                    service.closeIfSQLBuilderDialog(shell.getData());
                }
            }
        }
        super.postWindowClose();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.application.WorkbenchWindowAdvisor#preWindowShellClose()
     */

    @Override
    public boolean preWindowShellClose() {

        if (PlatformUI.getWorkbench().getWorkbenchWindowCount() > 1) {
            return true;
        }
        IPreferenceStore store = IDEWorkbenchPlugin.getDefault().getPreferenceStore();
        boolean promptOnExit = store.getBoolean(IDEInternalPreferences.EXIT_PROMPT_ON_CLOSE_LAST_WINDOW);

        if (promptOnExit) {
            String message;

            String productName = null;

            if (productName == null) {
                message = IDEWorkbenchMessages.PromptOnExitDialog_message0;
            } else {
                message = NLS.bind(IDEWorkbenchMessages.PromptOnExitDialog_message1, productName);
            }

            MessageDialogWithToggle dlg = MessageDialogWithToggle.openOkCancelConfirm(getWindowConfigurer().getWindow()
                    .getShell(), IDEWorkbenchMessages.PromptOnExitDialog_shellTitle, message,
                    IDEWorkbenchMessages.PromptOnExitDialog_choice, false, null, null);
            if (dlg.getReturnCode() != IDialogConstants.OK_ID) {
                return false;
            }
            if (dlg.getToggleState()) {
                store.setValue(IDEInternalPreferences.EXIT_PROMPT_ON_CLOSE_LAST_WINDOW, false);
                IDEWorkbenchPlugin.getDefault().savePluginPreferences();
            }
        }

        // for bug 7071
        IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (GlobalServiceRegister.getDefault().isServiceRegistered(IDiagramModelService.class)) {
            if (workbenchWindow.getActivePage() != null
                    && CorePlugin.getDefault().getDiagramModelService()
                            .isBusinessDiagramEditor(workbenchWindow.getActivePage().getActiveEditor())) {
                IViewReference findViewReference = workbenchWindow.getActivePage().findViewReference(IRepositoryView.VIEW_ID);
                if (findViewReference != null) {
                    findViewReference.getView(false).setFocus();
                }

            }
        }

        return true;
    }
}
