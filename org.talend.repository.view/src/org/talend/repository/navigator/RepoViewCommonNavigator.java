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
package org.talend.repository.navigator;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.contexts.IContextActivation;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.internal.WorkbenchPage;
import org.eclipse.ui.internal.dialogs.EventLoopProgressMonitor;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.commons.ui.runtime.exception.MessageBoxExceptionHandler;
import org.talend.commons.ui.runtime.image.ECoreImage;
import org.talend.commons.ui.runtime.image.ImageProvider;
import org.talend.commons.ui.swt.dialogs.ProgressDialog;
import org.talend.commons.ui.swt.tooltip.AbstractTreeTooltip;
import org.talend.commons.utils.Timer;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.PluginChecker;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.model.general.ILibrariesService;
import org.talend.core.model.general.Project;
import org.talend.core.model.migration.IMigrationToolService;
import org.talend.core.model.process.IProcess2;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.Property;
import org.talend.core.model.properties.User;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.core.model.repository.LockInfo;
import org.talend.core.model.update.EUpdateItemType;
import org.talend.core.model.update.IUpdateManager;
import org.talend.core.model.update.RepositoryUpdateManager;
import org.talend.core.model.update.UpdateResult;
import org.talend.core.model.utils.RepositoryManagerHelper;
import org.talend.core.repository.CoreRepositoryPlugin;
import org.talend.core.repository.model.ProxyRepositoryFactory;
import org.talend.core.runtime.CoreRuntimePlugin;
import org.talend.core.ui.IJobletProviderService;
import org.talend.designer.runprocess.IRunProcessService;
import org.talend.repository.IRepositoryChangedListener;
import org.talend.repository.RepositoryChangedEvent;
import org.talend.repository.RepositoryViewPlugin;
import org.talend.repository.model.ERepositoryStatus;
import org.talend.repository.model.IRepositoryNode.ENodeType;
import org.talend.repository.model.IRepositoryService;
import org.talend.repository.model.ProjectRepositoryNode;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.model.actions.MoveObjectAction;
import org.talend.repository.plugin.integration.SwitchProjectAction;
import org.talend.repository.ui.actions.CopyAction;
import org.talend.repository.ui.actions.DeleteAction;
import org.talend.repository.ui.actions.PasteAction;
import org.talend.repository.ui.views.IRepositoryView;
import org.talend.repository.ui.views.RepositoryDropAdapter;

/**
 * DOC sgandon class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 55206 2011-02-15 17:32:14Z mhirt $
 * 
 */
public class RepoViewCommonNavigator extends CommonNavigator implements IRepositoryView, ITabbedPropertySheetPageContributor,
        IRepositoryChangedListener, ISelectionListener {

    private static final String PERSPECTIVE_DI_ID = "org.talend.rcp.perspective"; //$NON-NLS-1$

    private static Logger log = Logger.getLogger(RepoViewCommonNavigator.class);

    protected CommonViewer viewer;

    private static List<ISelectionChangedListener> listenersNeedTobeAddedIntoTreeviewer = new ArrayList<ISelectionChangedListener>();

    private static boolean codeGenerationEngineInitialised;

    private Action doubleClickAction;

    private Listener dragDetectListener;

    protected boolean isFromFake = true;

    public static Object initialInput;

    /**
     * yzhang Comment method "addPreparedListeners".
     * 
     * @param listeners
     */
    public static void addPreparedListeners(ISelectionChangedListener listeners) {
        if (listeners != null) {
            listenersNeedTobeAddedIntoTreeviewer.add(listeners);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.part.ViewPart#init(org.eclipse.ui.IViewSite)
     */
    @Override
    public void init(IViewSite site) throws PartInitException {
        super.init(site);
        // FIXME， later, will check this to initialize
        if (GlobalServiceRegister.getDefault().isServiceRegistered(IRepositoryService.class)) {
            final IRepositoryService service = (IRepositoryService) GlobalServiceRegister.getDefault().getService(
                    IRepositoryService.class);
            if (service != null) {
                service.initializePluginMode();

            }
        }
        if (!codeGenerationEngineInitialised && !CoreRepositoryPlugin.getDefault().isRCPMode()) {
            if (GlobalServiceRegister.getDefault().isServiceRegistered(ILibrariesService.class)) {
                ILibrariesService libService = (ILibrariesService) GlobalServiceRegister.getDefault().getService(
                        ILibrariesService.class);
                if (libService != null && libService.isLibSynchronized()) {
                    libService.syncLibraries();
                }
            }
            codeGenerationEngineInitialised = true;
        }
        getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(this);
    }

    public static IRepositoryView show() {
        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        if (page != null) {
            IViewPart part = page.findView(IRepositoryView.VIEW_ID);
            // MOD klliu check TIS is started bug TDQ-3238
            if (part == null && PluginChecker.isTIS()) {
                try {
                    // MOD by zshen for 15750 todo 39 if the Perspective is DataProfilingPerspective refuse the
                    // RepositoryView be display by automatic
                    if (PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getPerspective().getId()
                            .equalsIgnoreCase("org.talend.dataprofiler.DataProfilingPerspective")) {//$NON-NLS-1$
                        part = ((WorkbenchPage) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage())
                                .getViewFactory().createView(IRepositoryView.VIEW_ID).getView(true);
                    } else {
                        // bug 16594
                        String perId = page.getPerspective().getId();
                        if ((!"".equals(perId) || null != perId) && perId.equalsIgnoreCase(PERSPECTIVE_DI_ID)) {
                            part = page.showView(IRepositoryView.VIEW_ID);
                        }
                    }
                } catch (Exception e) {
                    ExceptionHandler.process(e);
                }
            }
            return (IRepositoryView) part;
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.navigator.CommonNavigator#createCommonViewerObject(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected CommonViewer createCommonViewerObject(Composite aParent) {
        return new RepoViewCommonViewer(this, getViewSite().getId(), aParent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
    }

    /*
     * this by default return a ProjectRepositoryNode except if the public static variable named initialInput is set.
     * 
     * @see org.eclipse.ui.navigator.CommonNavigator#getInitialInput()
     */
    @Override
    protected Object getInitialInput() {

        if (initialInput == null) {
            initialInput = new TalendRepositoryRoot();
        }
        return initialInput;
    }

    @Override
    public void createPartControl(Composite parent) {
        super.createPartControl(parent);

        viewer = getCommonViewer();
        if (viewer instanceof ITreeViewerListener) {
            viewer.addTreeListener((ITreeViewerListener) viewer);
        }
        getSite().setSelectionProvider(viewer);
        if (isFromFake) {
            refresh();
        }
        // This only tree listener aim is to change open/close icons on folders :
        // TODO this should be done in the LabelProvider
        viewer.addTreeListener(new ITreeViewerListener() {

            @Override
            public void treeCollapsed(TreeExpansionEvent event) {
                Object element = event.getElement();
                if (element instanceof RepositoryNode) {
                    RepositoryNode node = (RepositoryNode) element;
                    if (node.getType().equals(ENodeType.SIMPLE_FOLDER)) {
                        TreeItem item = getObject(viewer.getTree(), event.getElement());
                        if (item != null) {
                            item.setImage(ImageProvider.getImage(ECoreImage.FOLDER_CLOSE_ICON));
                        }
                    }
                }
            }

            @Override
            public void treeExpanded(TreeExpansionEvent event) {
                Object element = event.getElement();
                if (element instanceof RepositoryNode) {
                    RepositoryNode node = (RepositoryNode) element;
                    if (node.getType().equals(ENodeType.SIMPLE_FOLDER)) {
                        TreeItem item = getObject(viewer.getTree(), element);
                        if (item != null) {
                            item.setImage(ImageProvider.getImage(ECoreImage.FOLDER_OPEN_ICON));
                        }
                    }
                }// else not a repo node so ignor it
            }
        });
        createTreeTooltip(viewer.getTree());

        viewer.getTree().addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                log.trace("Repository gain focus"); //$NON-NLS-1$
                IContextService contextService = (IContextService) RepositoryViewPlugin.getDefault().getWorkbench()
                        .getAdapter(IContextService.class);
                ca = contextService.activateContext("talend.repository"); //$NON-NLS-1$
            }

            @Override
            public void focusLost(FocusEvent e) {
                log.trace("Repository lost focus"); //$NON-NLS-1$
                if (ca != null) {
                    IContextService contextService = (IContextService) RepositoryViewPlugin.getDefault().getWorkbench()
                            .getAdapter(IContextService.class);
                    contextService.deactivateContext(ca);
                }
            }
        });

        if (listenersNeedTobeAddedIntoTreeviewer.size() > 0) {
            for (ISelectionChangedListener listener : listenersNeedTobeAddedIntoTreeviewer) {
                viewer.addSelectionChangedListener(listener);
            }
            listenersNeedTobeAddedIntoTreeviewer.clear();
        }

        CoreRepositoryPlugin.getDefault().registerRepositoryChangedListenerAsFirst(this);

        if (!CoreRepositoryPlugin.getDefault().isRCPMode()) {
            if (GlobalServiceRegister.getDefault().isServiceRegistered(IMigrationToolService.class)) {
                IMigrationToolService migrationService = (IMigrationToolService) GlobalServiceRegister.getDefault().getService(
                        IMigrationToolService.class);
                if (migrationService != null) {
                    migrationService.executeMigration(SwitchProjectAction.PLUGIN_MODEL);
                }
            }
            if (GlobalServiceRegister.getDefault().isServiceRegistered(IRunProcessService.class)) {
                IRunProcessService runProcessService = (IRunProcessService) GlobalServiceRegister.getDefault().getService(
                        IRunProcessService.class);
                if (runProcessService != null) {
                    runProcessService.deleteAllJobs(SwitchProjectAction.PLUGIN_MODEL);
                }
            }
            final RepositoryContext repositoryContext = (RepositoryContext) CoreRuntimePlugin.getInstance().getContext()
                    .getProperty(Context.REPOSITORY_CONTEXT_KEY);
            final Project project = repositoryContext.getProject();

            final IWorkbenchWindow activedWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
            activedWorkbenchWindow.getPartService().addPartListener(new IPartListener() {

                @Override
                public void partActivated(IWorkbenchPart part) {
                    if (part instanceof RepoViewCommonNavigator) {
                        String title = activedWorkbenchWindow.getShell().getText();
                        if (!title.contains("|")) { //$NON-NLS-1$
                            title += " | " + repositoryContext.getUser() + " | " + project.getLabel(); //$NON-NLS-1$ //$NON-NLS-2$
                            activedWorkbenchWindow.getShell().setText(title);
                        }
                    }
                }

                @Override
                public void partBroughtToTop(IWorkbenchPart part) {

                }

                @Override
                public void partClosed(IWorkbenchPart part) {

                }

                @Override
                public void partDeactivated(IWorkbenchPart part) {

                }

                @Override
                public void partOpened(IWorkbenchPart part) {
                    if (part instanceof RepoViewCommonNavigator) {
                        String title = activedWorkbenchWindow.getShell().getText();
                        if (!title.contains("|")) { //$NON-NLS-1$
                            title += " | " + repositoryContext.getUser() + " | " + project.getLabel(); //$NON-NLS-1$ //$NON-NLS-2$
                            activedWorkbenchWindow.getShell().setText(title);
                        }
                    }
                }

            });
        }
        expandTreeRootIfOnlyOneRoot();
        setContentDescription("this is a content description");
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.part.WorkbenchPart#dispose()
     */
    @Override
    public void dispose() {
        getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(this);
        super.dispose();
    }

    /**
     * DOC bqian Comment method "createTreeTooltip".
     * 
     * @param tree
     */
    private void createTreeTooltip(Tree tree) {
        new AbstractTreeTooltip(tree) {

            /*
             * (non-Javadoc)
             * 
             * @see
             * org.talend.commons.ui.swt.tooltip.AbstractTreeTooltip#getTooltipContent(org.eclipse.swt.widgets.TreeItem)
             */
            @Override
            public String getTooltipContent(TreeItem item) {

                RepositoryNode node = (RepositoryNode) item.getData();
                IRepositoryViewObject object = node.getObject();
                if (object == null) {
                    return null;
                }
                // add for feature 10281
                String content = null;
                User currentLoginUser = ((RepositoryContext) CoreRuntimePlugin.getInstance().getContext()
                        .getProperty(Context.REPOSITORY_CONTEXT_KEY)).getUser();
                String currentLogin = null;
                if (currentLoginUser != null) {
                    currentLogin = currentLoginUser.getLogin();
                }
                String login = null;
                String application = "studio";//$NON-NLS-1$
                if (object.getRepositoryStatus() == ERepositoryStatus.LOCK_BY_OTHER) {
                    Property property = object.getProperty();
                    if (property != null) {
                        Item item2 = property.getItem();
                        if (item2 != null) {
                            LockInfo lockInfo = ProxyRepositoryFactory.getInstance().getLockInfo(item2);
                            if (!lockInfo.getUser().equals(currentLogin)) {
                                login = lockInfo.getUser();
                                application = lockInfo.getApplication();
                            }
                        }
                    }
                    if (login != null && !"".equals(login)) {//$NON-NLS-1$
                        content = "  locked by " + login + " on " + application;
                    }
                }
                String description = object.getDescription();
                if (content == null || "".equals(content)) { //$NON-NLS-1$
                    if (description == null || "".equals(description)) {//$NON-NLS-1$
                        return null;
                    }
                    return description;
                } else {
                    if (description == null || "".equals(description)) {//$NON-NLS-1$
                        return content;
                    }
                    return content + "\n" + "  Description: " + description;//$NON-NLS-1$
                }
            }
        };

    }

    IContextActivation ca;

    private TreeItem getObject(Tree tree, Object objectToFind) {
        for (TreeItem item : tree.getItems()) {
            TreeItem toReturn = getObject(item, objectToFind);
            if (toReturn != null) {
                return toReturn;
            }
        }
        return null;
    }

    private TreeItem getObject(TreeItem parent, Object objectToFind) {
        for (TreeItem currentChild : parent.getItems()) {
            if (objectToFind.equals(currentChild.getData())) {
                return currentChild;
            }
            TreeItem toReturn = getObject(currentChild, objectToFind);
            if (toReturn != null) {
                return toReturn;
            }
        }
        return null;
    }

    protected void initDragAndDrop() {
        int ops = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK;
        Transfer[] transfers = new Transfer[] { LocalSelectionTransfer.getTransfer() };

        viewer.addDragSupport(ops, transfers, new DragSourceAdapter() {

            private static final long FFFFFFFFL = 0xFFFFFFFFL;

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.swt.dnd.DragSourceAdapter#dragSetData(org.eclipse.swt.dnd.DragSourceEvent)
             */
            @Override
            public void dragSetData(DragSourceEvent event) {
                event.data = LocalSelectionTransfer.getTransfer().getSelection();
            }

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.swt.dnd.DragSourceAdapter#dragStart(org.eclipse.swt.dnd.DragSourceEvent)
             */
            @Override
            public void dragStart(DragSourceEvent event) {
                ISelection selection = viewer.getSelection();

                for (Object obj : ((StructuredSelection) selection).toArray()) {
                    RepositoryNode sourceNode = (RepositoryNode) obj;

                    // As i don't know how to get event operation i test on MoveOperation
                    event.doit = MoveObjectAction.getInstance().validateAction(sourceNode, null, true);
                }

                LocalSelectionTransfer.getTransfer().setSelection(selection);
                LocalSelectionTransfer.getTransfer().setSelectionSetTime(event.time & FFFFFFFFL);
            }

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.swt.dnd.DragSourceAdapter#dragFinished(org.eclipse.swt.dnd.DragSourceEvent)
             */
            @Override
            public void dragFinished(DragSourceEvent event) {
                RepoViewCommonNavigator.this.dragFinished();
            }
        });
        RepositoryDropAdapter adapter = new RepositoryDropAdapter(viewer);
        adapter.setFeedbackEnabled(false);
        viewer.addDropSupport(ops | DND.DROP_DEFAULT, transfers, adapter);
        dragDetectListener = new Listener() {

            @Override
            public void handleEvent(Event event) {
                // dragDetected = true;
            }
        };
        viewer.getControl().addListener(SWT.DragDetect, dragDetectListener);
    }

    public void dragFinished() {
        refresh();
        LocalSelectionTransfer.getTransfer().setSelection(null);
        LocalSelectionTransfer.getTransfer().setSelectionSetTime(0);
    }

    @Override
    public void setFocus() {
        viewer.getControl().setFocus();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.repository.views.IRepositoryView#getViewer()
     */
    @Override
    public TreeViewer getViewer() {
        return viewer;
    }

    @Override
    public void refresh() {
        this.refresh(false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.repository.views.IRepositoryView#refresh()
     */
    public void refresh(final boolean needInitialize) {
        /*
         * fix bug 4040. Sometimes Display.getCurrent.getActiveShell() get null result we not expect.
         */
        // Shell shell = Display.getCurrent().getActiveShell();
        Shell shell = getSite().getShell();

        if (shell == null) {
            return;
        }

        ProgressDialog progressDialog = new ProgressDialog(shell, 1000) {

            private IProgressMonitor monitorWrap;

            @Override
            public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                Timer timer = Timer.getTimer("repositoryView"); //$NON-NLS-1$
                timer.start();
                if (needInitialize) {
                    monitorWrap = new EventLoopProgressMonitor(monitor);
                    try {
                        final ProxyRepositoryFactory factory = ProxyRepositoryFactory.getInstance();
                        factory.initialize();
                    } catch (Exception e) {
                        throw new InvocationTargetException(e);
                    }
                }
                // TODO Why do we need to recreate a root here ????
                // root = new ProjectRepositoryNode(null, null, ENodeType.STABLE_SYSTEM_FOLDER);
                viewer.refresh();

                // unsetting the selection will prevent the propertyView from displaying dirty data
                viewer.setSelection(new TreeSelection());
                expandTreeRootIfOnlyOneRoot();

                if (PluginChecker.isJobLetPluginLoaded()) {
                    IJobletProviderService jobletService = (IJobletProviderService) GlobalServiceRegister.getDefault()
                            .getService(IJobletProviderService.class);
                    if (jobletService != null) {
                        jobletService.loadComponentsFromProviders();
                    }
                }

                timer.stop();
                // timer.print();
            }
        };

        try {
            progressDialog.executeProcess();
        } catch (InvocationTargetException e) {
            ExceptionHandler.process(e);
            return;
        } catch (Exception e) {
            MessageBoxExceptionHandler.process(e);
            return;
        }
    }

    /**
     * DOC sgandon Comment method "expandRootIfOnlyOneRoot".
     */
    protected void expandTreeRootIfOnlyOneRoot() {
        // if there is only one root element as in the svn repo then expand the first item
        TreeItem[] rootItems = getViewer().getTree().getItems();
        if (rootItems.length == 1) {
            viewer.setExpandedState(rootItems[0].getData(), true);
        }
    }

    @Override
    public void refreshView() {

        refresh(true);

        List<IProcess2> openedProcessList = RepositoryManagerHelper.getOpenedProcess(RepositoryUpdateManager.getEditors());
        List<UpdateResult> updateAllResults = new ArrayList<UpdateResult>();
        IUpdateManager manager = null;
        for (IProcess2 proc : openedProcessList) {
            // UpdateJobletUtils.updateRelationship();
            manager = proc.getUpdateManager();
            List<UpdateResult> updateResults = manager.getUpdatesNeeded(EUpdateItemType.RELOAD);
            updateAllResults.addAll(updateResults);
        }
        if (manager != null) {
            manager.executeUpdates(updateAllResults);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.views.IRepositoryView#refresh(java.lang.Object)
     */
    @Override
    public void refresh(Object object) {

        if (object != null) {
            // maybe, no effect.
            viewer.refresh(object);
            viewer.setExpandedState(object, true);
            if (object instanceof RepositoryNode) {
                RepositoryNode node = (RepositoryNode) object;
                ERepositoryObjectType type = node.getObjectType();
                if (type == null) {
                    type = node.getContentType();
                }
                refresh(type);
            }
        } else {
            // refresh();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.repository.ui.views.IRepositoryView#refresh(org.talend.core.model.repository.ERepositoryObjectType)
     */
    @Override
    public void refresh(ERepositoryObjectType type) {
        // because the getRoot is null, so remove all.
    }

    /**
     * only refresh the child of root node.
     */
    @Override
    public void refreshAllChildNodes(RepositoryNode rootNode) {
        if (rootNode != null) {
            rootNode.setInitialized(false);
            // if (!rootNode.getContentType().equals(ERepositoryObjectType.METADATA)) {
            rootNode.getChildren().clear();
            // }
            // for bug 11786
            if (rootNode.getParent() instanceof ProjectRepositoryNode) {
                ((ProjectRepositoryNode) rootNode.getParent()).clearNodeAndProjectCash();
            }
            // refresh content of recyclebin

            // contentProvider.getChildren(rootNode); // retrieve child
            viewer.refresh(rootNode);
            // user A and B in svn,if user A delete some jobs,B create job,the recyle bin can't refresh,
            // so need refresh recyle bin. if empty recyle bin,must delete job documents,don't need refresh recyle bin.
            // if refresh throw exception.
            if (!rootNode.getContentType().equals(ERepositoryObjectType.DOCUMENTATION)) {
                ProjectRepositoryNode theRoot = (ProjectRepositoryNode) rootNode.getRoot();
                if (theRoot != null) {
                    theRoot.getRecBinNode().setInitialized(false);
                    theRoot.getRecBinNode().getChildren().clear();
                    // contentProvider.getChildren(root.getRecBinNode());
                    viewer.refresh(theRoot.getRecBinNode());
                }
            }
        }
    }

    @Override
    public void expand(Object object) {
        if (object == null) {
            return;
        }
        boolean state = getExpandedState(object);
        expand(object, !state);
    }

    @Override
    public boolean getExpandedState(Object object) {
        if (object == null) {
            return false;
        }
        boolean state = viewer.getExpandedState(object);
        return state;
    }

    @Override
    public void expand(Object object, boolean state) {
        if (object == null) {
            return;
        }
        viewer.setExpandedState(object, state);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.repository.views.IRepositoryView#getSystemFolders()
     * 
     * @deprecate to be removed, returns null;
     */
    @Override
    @Deprecated
    public RepositoryNode getRoot() {
        return null;
    }

    @Override
    public List<IRepositoryViewObject> getAll(ERepositoryObjectType type) {
        // TODO to be re-implmented not relying on root
        // may be look for for the tree input or root nodes for a ProjectRepositoryNode or an adapter of
        // ProjectRepositoryNode
        throw new RuntimeException("To be implemented");
        // // find the system folder
        // RepositoryNode container = findContainer(root, type);
        //
        // if (container == null) {
        //            throw new IllegalArgumentException(type + Messages.getString("RepositoryView.notfound")); //$NON-NLS-1$
        // }
        //
        // List<IRepositoryViewObject> result = new ArrayList<IRepositoryViewObject>();
        // addElement(result, type, container);
        // addElement(result, type, root.getRecBinNode());
        // return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor#getContributorId()
     */
    @Override
    public String getContributorId() {
        return getSite().getId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.part.WorkbenchPart#getAdapter(java.lang.Class)
     */
    @Override
    public Object getAdapter(Class adapter) {
        if (adapter == IPropertySheetPage.class) {
            return new TabbedPropertySheetPage(this);
        }
        return super.getAdapter(adapter);
    }

    @Override
    public void repositoryChanged(RepositoryChangedEvent event) {
        refresh();
    }

    @Override
    public String[] gatherMetadataChildenLabels() {
        return new String[0];// contentProvider.gatherMetdataChildrens();
    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
        if (part == this && selection instanceof TreeSelection) {
            CopyAction.getInstance().init(getViewer(), (IStructuredSelection) selection);
            PasteAction.getInstance().init(getViewer(), (IStructuredSelection) selection);
            DeleteAction.getInstance().init(getViewer(), (IStructuredSelection) selection);
        }
    }

    public Action getDoubleClickAction() {
        return this.doubleClickAction;
    }

    /*
     * (non-Javadoc)
     * 
     * @seeorg.talend.repository.ui.views.IRepositoryView#containsRepositoryType(org.talend.core.model.repository.
     * ERepositoryObjectType)
     */
    @Override
    public boolean containsRepositoryType(ERepositoryObjectType type) {
        // PTODO be implemented, later
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.views.IRepositoryView#isFakeView()
     */
    @Override
    public boolean isFakeView() {
        return false;
    }
}
