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
package org.talend.rcp.intro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.commands.ActionHandler;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PerspectiveAdapter;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.XMLMemento;
import org.eclipse.ui.activities.IWorkbenchActivitySupport;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.internal.IWorkbenchConstants;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.ide.EditorAreaDropAdapter;
import org.eclipse.ui.internal.ide.IDEInternalPreferences;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.osgi.service.prefs.BackingStoreException;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.utils.VersionUtils;
import org.talend.commons.utils.workbench.extensions.ExtensionImplementationProvider;
import org.talend.commons.utils.workbench.extensions.ExtensionPointLimiterImpl;
import org.talend.commons.utils.workbench.extensions.IExtensionPointLimiter;
import org.talend.core.CorePlugin;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.ITDQRepositoryService;
import org.talend.core.PluginChecker;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.model.general.Project;
import org.talend.core.model.repository.RepositoryManager;
import org.talend.core.model.utils.RepositoryManagerHelper;
import org.talend.core.model.utils.TalendPropertiesUtil;
import org.talend.core.prefs.ITalendCorePrefConstants;
import org.talend.core.prefs.PreferenceManipulator;
import org.talend.core.prefs.hidden.HidePreferencePageProvider;
import org.talend.core.prefs.hidden.HidePreferencePagesManager;
import org.talend.core.prefs.hidden.IHidePreferencePageValidator;
import org.talend.core.repository.model.ProxyRepositoryFactory;
import org.talend.core.runtime.CoreRuntimePlugin;
import org.talend.core.token.TokenCollectorFactory;
import org.talend.core.ui.ISQLBuilderService;
import org.talend.core.ui.branding.IBrandingConfiguration;
import org.talend.core.ui.branding.IBrandingService;
import org.talend.designer.business.diagram.custom.IDiagramModelService;
import org.talend.designer.core.ui.editor.palette.TalendPaletteHelper;
import org.talend.designer.core.ui.views.properties.IComponentSettingsView;
import org.talend.rcp.Activator;
import org.talend.rcp.i18n.Messages;
import org.talend.rcp.intro.starting.StartingEditorInput;
import org.talend.rcp.intro.starting.StartingHelper;
import org.talend.rcp.util.ApplicationDeletionUtil;
import org.talend.repository.ui.login.connections.ConnectionUserPerReader;
import org.talend.repository.ui.views.IRepositoryView;

/**
 * DOC ccarbone class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
@SuppressWarnings("restriction")
public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    private final List<IAction> actions = new ArrayList<IAction>();

    ApplicationActionBarAdvisor adviser = null;

    private static String componentViewId = "org.talend.designer.core.ui.views.properties.ComponentSettingsView";

    private static String gefViewId = "org.eclipse.gef.ui.palette_view";

    private static String repositoryViewId = IRepositoryView.VIEW_ID;

    private static String processViewId = "org.talend.designer.runprocess.ui.views.processview";

    private Composite foreGroundComposite;

    private Composite parentComposite;

    private Composite backGroundComposite;

    private IEditorPart startingBrowser;

    private final boolean isTos = false;

    private final StackLayout stackLayout = new StackLayout();

    public static final IExtensionPointLimiter GLOBAL_ACTIONS = new ExtensionPointLimiterImpl("org.talend.core.global_actions", //$NON-NLS-1$
            "GlobalAction"); //$NON-NLS-1$

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    @Override
    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        adviser = new ApplicationActionBarAdvisor(configurer);
        return adviser;
    }

    @Override
    public void preWindowOpen() {
        // feature 19053
        PerspectiveReviewUtil.setPerspectiveTabsBarSize();
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

        IBrandingService service = (IBrandingService) GlobalServiceRegister.getDefault().getService(IBrandingService.class);
        IBrandingConfiguration brandingConfiguration = service.getBrandingConfiguration();
        String appName = service.getFullProductName();
        PreferenceManipulator prefManipulator = new PreferenceManipulator(CorePlugin.getDefault().getPreferenceStore());
        // TDI-18644
        ProxyRepositoryFactory factory = ProxyRepositoryFactory.getInstance();
        boolean localProvider = false;
        try {
            localProvider = factory.isLocalConnectionProvider();
        } catch (PersistenceException e) {
            localProvider = true;
        }
        String buildIdField = " (" + VersionUtils.getVersion() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
        if (TalendPropertiesUtil.isHideBuildNumber()) {
            buildIdField = ""; //$NON-NLS-1$
        }
        if (localProvider) {
            configurer
                    .setTitle(appName
                            + buildIdField
                            + " | " + project.getLabel() + " (" //$NON-NLS-1$ //$NON-NLS-2$ 
                            + Messages.getString("ApplicationWorkbenchWindowAdvisor.repositoryConnection") + ": " + ConnectionUserPerReader.getInstance().readLastConncetion() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            configurer
                    .setTitle(appName
                            + buildIdField
                            + " | " + repositoryContext.getUser() + " | " + project.getLabel() + " (" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 
                            + Messages.getString("ApplicationWorkbenchWindowAdvisor.repositoryConnection") + ": " + ConnectionUserPerReader.getInstance().readLastConncetion() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
        }
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

        //
        if (GlobalServiceRegister.getDefault().isServiceRegistered(ITDQRepositoryService.class)) {
            ITDQRepositoryService tdqRepositoryService = (ITDQRepositoryService) GlobalServiceRegister.getDefault().getService(
                    ITDQRepositoryService.class);
            if (tdqRepositoryService != null) {
                tdqRepositoryService.initProxyRepository();
            }
        }
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
            ApplicationDeletionUtil.removeAndResetPreferencePages(this.getWindowConfigurer().getWindow(), Arrays.asList(prefsId),
                    true);
        } else {
            String[] prefsId = { "org.talend.mdm.repository.ui.preferences.RepositoryPreferencePage" }; //$NON-NLS-1$
            ApplicationDeletionUtil.removeAndResetPreferencePages(this.getWindowConfigurer().getWindow(), Arrays.asList(prefsId),
                    false);
        }
        List<HidePreferencePageProvider> providers = HidePreferencePagesManager.getInstance().getProviders();
        List<String> needRemovedPrefs = new ArrayList<String>();
        for (HidePreferencePageProvider provider : providers) {
            String prefPageId = provider.getPrefPageId();
            IHidePreferencePageValidator validator = provider.getValidator();
            if (prefPageId != null && (validator == null || validator.validate())) {
                needRemovedPrefs.add(prefPageId);
            }
        }
        ApplicationDeletionUtil.removeAndResetPreferencePages(this.getWindowConfigurer().getWindow(), needRemovedPrefs, false);
        // MOD mzhao feature 9207. 2009-09-21 ,Add part listener.
        if (GlobalServiceRegister.getDefault().isServiceRegistered(ITDQRepositoryService.class)) {
            ITDQRepositoryService tdqRepositoryService = (ITDQRepositoryService) GlobalServiceRegister.getDefault().getService(
                    ITDQRepositoryService.class);
            if (tdqRepositoryService != null) {
                tdqRepositoryService.addPartListener();
            }
        }

        showStarting();
        // feature 18752
        // setEditorAreaBG();
        regisitPerspectiveListener();
        // feature 19053
        PerspectiveReviewUtil.regisitPerspectiveBarSelectListener();

        if (PluginChecker.isBPMloaded()) {
            IPath path = WorkbenchPlugin.getDefault().getDataLocation();
            if (path == null) {
                return;
            }
            final File stateFile = path.append("workbench.xml").toFile(); //$NON-NLS-1$ 
            if (stateFile.exists()) {
                IWorkbenchWindow workBenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                IWorkbenchPage activePage = workBenchWindow.getActivePage();
                FileInputStream input;
                try {
                    input = new FileInputStream(stateFile);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input, "utf-8")); //$NON-NLS-1$ 
                    IMemento memento = XMLMemento.createReadRoot(reader);
                    IMemento[] hideMenuArray = memento.getChild(IWorkbenchConstants.TAG_WINDOW)
                            .getChild(IWorkbenchConstants.TAG_PAGE).getChild(IWorkbenchConstants.TAG_PERSPECTIVES)
                            .getChild(IWorkbenchConstants.TAG_PERSPECTIVE).getChildren(IWorkbenchConstants.TAG_HIDE_MENU);
                    if (hideMenuArray.length == 0) {
                        activePage.resetPerspective();
                    } else {
                        // if no bonita menue is filtered ,need to recaculate
                        String bonitaMenues = "org.bonitasoft.studio";
                        boolean isBPMFilterWork = false;
                        for (int i = 0; hideMenuArray != null && i < hideMenuArray.length; i++) {
                            IMemento hideMenu = hideMenuArray[i];
                            String string = hideMenu.getString(IWorkbenchConstants.TAG_ID);
                            if (string != null && string.startsWith(bonitaMenues)) {
                                isBPMFilterWork = true;
                                break;
                            }
                        }
                        if (!isBPMFilterWork) {
                            activePage.resetPerspective();
                        }

                    }
                } catch (Exception e) {
                    // do nothing
                }
            }
        }
        // tmp for token
        final IPreferenceStore store = CoreRuntimePlugin.getInstance().getPreferenceStore();
        if (!store.getBoolean(ITalendCorePrefConstants.DATA_COLLECTOR)) {
            TokenCollectorFactory.getFactory().send();
            store.setValue(ITalendCorePrefConstants.DATA_COLLECTOR, true);
        }

        IWorkbenchActivitySupport activitySupport = getWindowConfigurer().getWindow().getWorkbench().getActivitySupport();
        String hideUpdateSiteId = "org.talend.rcp.hideUpdatesite";
        Set<String> enabledActivities = new HashSet<String>();
        enabledActivities.addAll(activitySupport.getActivityManager().getEnabledActivityIds());
        if (!PluginChecker.isSVNProviderPluginLoaded()) {
            if (activitySupport.getActivityManager().getActivity(hideUpdateSiteId).isDefined()) {
                enabledActivities.remove(hideUpdateSiteId);
            }
            activitySupport.setEnabledActivityIds(enabledActivities);
        } else {
            if (activitySupport.getActivityManager().getActivity(hideUpdateSiteId).isDefined()) {
                enabledActivities.add(hideUpdateSiteId);
            }
            activitySupport.setEnabledActivityIds(enabledActivities);
        }
    }

    private void showStarting() {
        try {
            IBrandingService service = (IBrandingService) GlobalServiceRegister.getDefault().getService(IBrandingService.class);
            // the first time to call getHtmlContent, if throws any exception ,don't show StartingBrower
            StartingHelper.getHelper().getHtmlContent();
            IWorkbenchPage activePage = getWindowConfigurer().getWindow().getWorkbench().getActiveWorkbenchWindow()
                    .getActivePage();
            if (activePage != null) {
                if (activePage.getPerspective().getId().equals("org.talend.rcp.perspective")) {
                    startingBrowser = activePage.openEditor(new StartingEditorInput(service), service.getStartingBrowserId());
                }
            }
        } catch (Exception e) {
            // do nothing
        }
    }

    private void regisitPerspectiveListener() {
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().addPerspectiveListener(new PerspectiveAdapter() {

            @Override
            public void perspectiveActivated(IWorkbenchPage page, IPerspectiveDescriptor perspective) {
                // MOD xqliu 2010-10-14 bug 15756
                String pId = perspective.getId();
                IRepositoryView view = RepositoryManager.getRepositoryView();
                // if (IBrandingConfiguration.PERSPECTIVE_DI_ID.equals(pId)) {
                // if (isTos == true) {
                // isTos = false;
                // setEditorAreaBG();
                // }
                // clearEditorAreaBG(true);
                // } else {
                // clearEditorAreaBG(false);
                // }
                if (view != null) {
                    if (IBrandingConfiguration.PERSPECTIVE_DI_ID.equals(pId)
                            || IBrandingConfiguration.PERSPECTIVE_CAMEL_ID.equals(pId)) {
                        /* 0016610 need to refresh not only databaseconnection but only trash bin */
                        view.refresh();
                        TalendPaletteHelper.checkAndInitToolBar();
                    } else if (IBrandingConfiguration.PERSPECTIVE_DQ_ID.equals(pId)) {
                        if (GlobalServiceRegister.getDefault().isServiceRegistered(ITDQRepositoryService.class)) {
                            ITDQRepositoryService tdqRepositoryService = (ITDQRepositoryService) GlobalServiceRegister
                                    .getDefault().getService(ITDQRepositoryService.class);
                            if (tdqRepositoryService != null) {
                                tdqRepositoryService.refresh();
                            }
                        }
                        IViewReference findViewReference = RepositoryManagerHelper.findRepositoryViewRef();
                        if (findViewReference != null) {
                            page.hideView(findViewReference);
                        }
                    }
                }
                // ~ 15756
            }
            
            @Override
            public void perspectiveDeactivated(IWorkbenchPage page, IPerspectiveDescriptor perspective) {
                String pId = perspective.getId();
                if (IBrandingConfiguration.PERSPECTIVE_DI_ID.equals(pId)) {
                    IComponentSettingsView viewer = (IComponentSettingsView) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                            .getActivePage().findView(IComponentSettingsView.ID);

                    if (viewer != null) {
                        viewer.cleanDisplay();
                    }
                }
            }
        });

        // MOD yyi 2011-05-17 19088: add perspective change listener for cheatsheet view of tdq
        PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                .addPerspectiveListener(new CheatSheetPerspectiveAdapter(CheatSheetPerspectiveAdapter.DQ_CHEATSHEET_START_ID));
    }

    // private void clearEditorAreaBG(boolean flag) {
    // if (parentComposite != null && !parentComposite.isDisposed())
    // if (flag) {
    // stackLayout.topControl = foreGroundComposite;
    // parentComposite.layout();
    // } else {
    // stackLayout.topControl = backGroundComposite;
    // parentComposite.layout();
    // }
    // }
    //
    // private void setEditorAreaBG() {
    // // PlatformUI.getWorkbench().get
    // if (getWindowConfigurer().getWindow() == null) {
    // return;
    // }
    // if (getWindowConfigurer().getWindow().getActivePage() == null) {
    // return;
    // }
    // IBrandingService brandingService = (IBrandingService) GlobalServiceRegister.getDefault().getService(
    // IBrandingService.class);
    // String title = brandingService.getFullProductName();
    // String secondTitle = brandingService.getShortProductName();
    // final WorkbenchPage page = (WorkbenchPage) getWindowConfigurer().getWindow().getActivePage();
    // if (!page.getPerspective().getId().equals("org.talend.rcp.perspective")) {
    // isTos = true;
    // return;
    // }
    // Composite client = page.getClientComposite();
    // Control[] children = client.getChildren();
    // for (int i = 0; i < children.length; i++) {
    // if (children[i] instanceof Composite) {
    // Composite child = (Composite) children[i];
    // Control[] controls = child.getChildren();
    // for (final Control control : controls) {
    // if (control instanceof CTabFolder) {
    // CTabFolder tabFolder = (CTabFolder) control;
    // Control[] parents = tabFolder.getChildren();
    // for (int j = 0; j < parents.length; j++) {
    // if (parents[j] instanceof Composite) {
    // Composite parent = (Composite) parents[j];
    // parent.redraw();
    // int width = parent.getBounds().width;
    // int hight = parent.getBounds().height;
    // // if (width < 700) {
    // // width = 700;
    // // } else if (width > 860) {
    // // width = 860;
    // // }
    // // if (hight < 370) {
    // // hight = 370;
    // // } else if (hight > 460) {
    // // hight = 460;
    // // }
    // Image image = null;
    // int tipsFontSize = 10;
    // int titleSize = width / 65;
    // if ((width >= 844) && (hight >= 445)) {
    // image = Activator.getImageDescriptor("icons/WelcomeScreenMockup.png").createImage();
    // width = 860;
    // hight = 460;
    // tipsFontSize = 10;
    // titleSize = width / 65;
    // } else { // if ((width>=650)&&(hight>=343))
    // image = Activator.getImageDescriptor("icons/WelcomeScreenMockupSmall.png").createImage();
    // width = 710;
    // hight = 379;
    // tipsFontSize = 8;
    // titleSize = 9;
    // }
    // // } else {
    // // image =
    // // Activator.getImageDescriptor("icons/WelcomeScreenMockupVerySmall.png").createImage();
    // // }
    // // ImageData id = image.getImageData();
    // // id = id.scaledTo(width - 10, hight - 10);
    // // image = new Image(null, id);
    // Image icon = Activator.getImageDescriptor("icons/createJob.png").createImage();
    // int imageWith = image.getBounds().width;
    // GC gc = new GC(image);
    // gc.setForeground(new Color(null, 0, 0, 0));
    // gc.setFont(new Font(parent.getDisplay(), "Arial", tipsFontSize, SWT.NONE));
    // String tipsClickText = Messages.getString("tips.clickChoose");
    // int imageHeight = image.getBounds().height;
    //
    // // gc.drawText(Messages.getString("tips.addDataSource"), ((imageWith - 10) * 10) / 17,
    // // (imageHeight * 2) / 5 + 20, true);
    //
    // gc.setFont(new Font(parent.getDisplay(), "Arial", tipsFontSize, SWT.NONE));
    //
    // // gc.drawText(Messages.getString("tips.viewAndEdit"), (imageWith - 10) / 7 + 5,
    // // (imageHeight * 10) / 17 - 5, true);
    // gc.drawText(Messages.getString("tips.viewResults"), (imageWith * 10) / 19 - 3,
    // (imageHeight * 10) / 14 + 18, true);
    //
    // parentComposite = new Composite(parent, SWT.NONE);
    // parentComposite.setLayout(stackLayout);
    // parentComposite.setSize(width, imageHeight);
    // backGroundComposite = new Composite(parentComposite, SWT.NONE);
    // backGroundComposite.setSize(width, imageHeight);
    // foreGroundComposite = new Composite(parentComposite, SWT.NONE);
    //
    // foreGroundComposite.setLayout(new FormLayout());
    // Label clickChoose = new Label(foreGroundComposite, SWT.NONE);
    // FormData clickChooseData = new FormData();
    // clickChooseData.left = new FormAttachment(0, (imageWith - 10) / 7);
    // clickChooseData.top = new FormAttachment(0, (imageHeight - 10) / 3 + 19);
    // clickChooseData.height = 15;
    // clickChoose.setLayoutData(clickChooseData);
    // clickChoose.setBackground(new Color(null, 255, 255, 204));
    // clickChoose.setForeground(new Color(null, 0, 0, 0));
    // clickChoose.setFont(new Font(parent.getDisplay(), "Arial", tipsFontSize, SWT.NONE));
    // clickChoose.setText(tipsClickText);
    //
    // Label imageLabel = new Label(foreGroundComposite, SWT.NONE);
    // FormData imageLabelData = new FormData();
    // imageLabelData.left = new FormAttachment(clickChoose, 2);
    // imageLabelData.top = new FormAttachment(0, (imageHeight - 10) / 3 + 19);
    // imageLabelData.height = 20;
    // imageLabel.setLayoutData(imageLabelData);
    // imageLabel.setImage(icon);
    //
    // Label optionMenu = new Label(foreGroundComposite, SWT.NONE);
    // FormData optionMenuData = new FormData();
    // optionMenuData.left = new FormAttachment(imageLabel, 2);
    // optionMenuData.top = new FormAttachment(0, (imageHeight - 10) / 3 + 19);
    // optionMenuData.height = 15;
    // optionMenu.setFont(new Font(parent.getDisplay(), "Arial", tipsFontSize, SWT.NONE));
    // optionMenu.setLayoutData(optionMenuData);
    // optionMenu.setBackground(new Color(null, 255, 255, 204));
    // optionMenu.setForeground(new Color(null, 0, 0, 0));
    // optionMenu.setText(Messages.getString("tips.optionMenu"));
    //
    // Label textTitle = new Label(foreGroundComposite, SWT.CENTER);
    // FormData textTitleData = new FormData();
    // textTitleData.left = new FormAttachment(0, 60);
    // textTitleData.right = new FormAttachment(100, -75);
    // textTitleData.top = new FormAttachment(0, 12);
    // textTitleData.bottom = new FormAttachment(0, 50);
    // textTitle.setLayoutData(textTitleData);
    // int titleFontSize = width / 34;
    // int titleLegth = 0;
    // gc.setFont(new Font(parent.getDisplay(), "Arial", titleFontSize, SWT.BOLD));
    // String titleText = Messages.getString("tips.welcome") + " " + title;
    // for (int l = 0; l < titleText.length(); l++) {
    // titleLegth += gc.getCharWidth(titleText.charAt(l)) + 1;
    // }
    // int defaultWidth = imageWith - 150;
    // if (imageWith == 850) {
    // defaultWidth = 670;
    // }
    // while (titleLegth > defaultWidth) {
    // titleLegth = 0;
    // titleFontSize = titleFontSize - 1;
    // gc.setFont(new Font(parent.getDisplay(), "Arial", titleFontSize, SWT.BOLD));
    // for (int l = 0; l < titleText.length(); l++) {
    // titleLegth += gc.getCharWidth(titleText.charAt(l)) + 1;
    // }
    // }
    // gc.setFont(new Font(parent.getDisplay(), "Arial", titleFontSize, SWT.NONE));
    // textTitle.setFont(new Font(foreGroundComposite.getDisplay(), "Arial", titleFontSize, SWT.BOLD));
    // textTitle.setBackground(new Color(null, 255, 255, 204));
    // textTitle.setForeground(new Color(null, 0, 0, 0));
    // textTitle.setText(titleText);
    //
    // Label textSecondTitle = new Label(foreGroundComposite, SWT.CENTER);
    // FormData textTitleData2 = new FormData();
    // textTitleData2.left = new FormAttachment(0, 50);
    // textTitleData2.right = new FormAttachment(100, -65);
    // textTitleData2.top = new FormAttachment(0, 50);
    // textTitleData2.bottom = new FormAttachment(0, 70);
    // textSecondTitle.setLayoutData(textTitleData2);
    //
    // textSecondTitle.setFont(new Font(foreGroundComposite.getDisplay(), "Arial", titleSize, SWT.NONE));
    // textSecondTitle.setBackground(new Color(null, 255, 255, 204));
    // textSecondTitle.setForeground(new Color(null, 0, 0, 0));
    // textSecondTitle.setText(Messages.getString("tips.title"));
    //
    // Label textThirdTitle = new Label(foreGroundComposite, SWT.CENTER);
    // FormData textTitleData3 = new FormData();
    // textTitleData3.left = new FormAttachment(0, 150);
    // textTitleData3.right = new FormAttachment(100, -150);
    // textTitleData3.top = new FormAttachment(0, 70);
    // textTitleData3.bottom = new FormAttachment(0, 90);
    // textThirdTitle.setLayoutData(textTitleData3);
    // textThirdTitle.setForeground(new Color(null, 0, 0, 0));
    // textThirdTitle.setFont(new Font(foreGroundComposite.getDisplay(), "Arial", titleSize, SWT.NONE));
    // textThirdTitle.setBackground(new Color(null, 255, 255, 204));
    // textThirdTitle.setText(Messages.getString("tips.getStart"));
    //
    // String jobDesignText = Messages.getString("tips.createJob");
    // int jobDesignWidth = 0;
    // for (int l = 0; l < jobDesignText.length(); l++) {
    // jobDesignWidth += gc.getCharWidth(jobDesignText.charAt(l)) + 1;
    // }
    // Link jobDesign = new Link(foreGroundComposite, SWT.NONE);
    // FormData layoutData = new FormData();
    // layoutData.left = new FormAttachment(0, (imageWith - 10) / 7);
    // // layoutData.right = new FormAttachment(0, jobDesignWidth + (imageWith - 10) / 7 +
    // // 100);
    // layoutData.top = new FormAttachment(0, (imageHeight - 10) / 3 - 2);
    // layoutData.height = 16;
    // // layoutData.bottom = new FormAttachment(0, (image.getBounds().height + 30) / 3);
    // jobDesign.setLayoutData(layoutData);
    // jobDesign.setBackground(new Color(null, 255, 255, 204));
    // jobDesign.setForeground(new Color(null, 0, 0, 0));
    // jobDesign.setFont(new Font(parent.getDisplay(), "Arial", tipsFontSize, SWT.NONE));
    // jobDesign.setText(jobDesignText);
    //
    // jobDesign.addListener(SWT.Selection, new Listener() {
    //
    // public void handleEvent(Event event) {
    // IViewPart view = page.findView(repositoryViewId);
    // try {
    // view = page.showView(repositoryViewId);
    // } catch (Exception e) {
    // ExceptionHandler.process(e);
    // }
    // page.findView(repositoryViewId).setFocus();
    // }
    // });
    // Link palette = new Link(foreGroundComposite, SWT.NONE);
    // FormData paletteData = new FormData();
    // paletteData.left = new FormAttachment(0, ((imageWith - 10) * 10) / 17);
    // paletteData.right = new FormAttachment(0, imageWith - 50);
    // paletteData.right = new FormAttachment(0, imageWith - 80); //
    // paletteData.top = new FormAttachment(0, (imageHeight * 2) / 5 + 15);
    // paletteData.height = 48;
    // paletteData.height = 36; //
    // palette.setLayoutData(paletteData);
    // palette.setBackground(new Color(null, 255, 255, 204));
    // palette.setFont(new Font(parent.getDisplay(), "Arial", tipsFontSize, SWT.NONE));
    // palette.setForeground(new Color(null, 0, 0, 0));
    // palette.setText(Messages.getString("tips.addDataSource"));
    // palette.addListener(SWT.Selection, new Listener() {
    //
    // public void handleEvent(Event event) {
    // IViewPart view = page.findView(gefViewId);
    // try {
    // view = page.showView(gefViewId);
    // } catch (Exception e) {
    // ExceptionHandler.process(e);
    // }
    // page.findView(gefViewId).setFocus();
    // }
    // });
    // String componentText = Messages.getString("tips.Component");
    // int componentWidth = 0;
    // for (int l = 0; l < componentText.length(); l++) {
    // componentWidth += gc.getCharWidth(componentText.charAt(l)) + 1;
    // }
    // Link component = new Link(foreGroundComposite, SWT.NONE);
    // FormData componentData = new FormData();
    // componentData.left = new FormAttachment(0, (imageWith - 10) / 7 + 5);
    // componentData.right = new FormAttachment(50, 0);
    // componentData.top = new FormAttachment(0, (imageHeight * 10) / 17 - 5);
    // componentData.height = 45;
    // component.setLayoutData(componentData);
    // component.setBackground(new Color(null, 255, 255, 204));
    // component.setForeground(new Color(null, 0, 0, 0));
    // component.setFont(new Font(parent.getDisplay(), "Arial", tipsFontSize, SWT.NONE));
    // component.setText(Messages.getString("tips.viewAndEdit") + " " + componentText);
    // component.addListener(SWT.Selection, new Listener() {
    //
    // public void handleEvent(Event event) {
    // IViewPart view = page.findView(componentViewId);
    // try {
    // view = page.showView(componentViewId);
    // } catch (Exception e) {
    // ExceptionHandler.process(e);
    // }
    // page.findView(componentViewId).setFocus();
    // }
    // });
    // Link runJob = new Link(foreGroundComposite, SWT.NONE);
    // FormData runJobData = new FormData();
    // runJobData.left = new FormAttachment(0, (imageWith * 10) / 19 - 3);
    // runJobData.right = new FormAttachment(0, imageWith - 40);
    // runJobData.top = new FormAttachment(0, (imageHeight * 10) / 14);
    // runJobData.height = 16;
    // runJob.setLayoutData(runJobData);
    // runJob.setBackground(new Color(null, 255, 255, 204));
    // runJob.setFont(new Font(parent.getDisplay(), "Arial", tipsFontSize, SWT.NONE));
    // runJob.setForeground(new Color(null, 0, 0, 0));
    // runJob.setText(Messages.getString("tips.finallyText"));
    // runJob.addListener(SWT.Selection, new Listener() {
    //
    // public void handleEvent(Event event) {
    // IViewPart view = page.findView(processViewId);
    // try {
    // view = page.showView(processViewId);
    // } catch (Exception e) {
    // ExceptionHandler.process(e);
    // }
    // page.findView(processViewId).setFocus();
    // }
    // });
    // stackLayout.topControl = foreGroundComposite;
    // foreGroundComposite.setBackgroundImage(image);
    // foreGroundComposite.setSize(width, imageHeight);
    // parentComposite.layout();
    // // lala.layout();
    // gc.dispose();
    // // tabFolder.pack();
    // }
    // }
    // }
    // }
    // }
    // }
    // }

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
                // MOD qiongli 2010-11-1,bug 16723: Code Cleansing
                // IDEWorkbenchPlugin.getDefault().savePluginPreferences();
                try {// FIXME, need check it for tdq
                    new InstanceScope().getNode(Activator.PLUGIN_ID).flush();
                } catch (BackingStoreException e) {
                    e.printStackTrace();
                }
            }
        }

        // for bug 7071
        IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (GlobalServiceRegister.getDefault().isServiceRegistered(IDiagramModelService.class)) {
            if (workbenchWindow.getActivePage() != null
                    && CorePlugin.getDefault().getDiagramModelService()
                            .isBusinessDiagramEditor(workbenchWindow.getActivePage().getActiveEditor())) {
                IViewReference findViewReference = RepositoryManagerHelper.findRepositoryViewRef();
                if (findViewReference != null) {
                    findViewReference.getView(false).setFocus();
                }

            }
        }
        // tmp for data collector
        TokenCollectorFactory.getFactory().process();

        return true;
    }
}
