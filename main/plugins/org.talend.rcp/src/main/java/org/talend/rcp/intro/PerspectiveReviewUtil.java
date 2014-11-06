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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.runtime.IPath;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspectiveStack;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.action.CoolBarManager;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PerspectiveAdapter;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.XMLMemento;
import org.eclipse.ui.internal.IPreferenceConstants;
import org.eclipse.ui.internal.IWorkbenchConstants;
import org.eclipse.ui.internal.WorkbenchPage;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.eclipse.ui.internal.menus.MenuHelper;
import org.eclipse.ui.internal.registry.PerspectiveDescriptor;
import org.talend.core.ui.branding.IBrandingConfiguration;
import org.talend.repository.ui.views.IRepositoryView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * DOC yhch class global comment. Detailled comment
 * 
 * 
 */
@SuppressWarnings("restriction")
public final class PerspectiveReviewUtil {

    private static String isfirst = "";

    static List<String> diViewList = Collections.synchronizedList(new ArrayList<String>());

    static List<String> dqViewList = Collections.synchronizedList(new ArrayList<String>());

    static List<String> mdmViewList = Collections.synchronizedList(new ArrayList<String>());

    // DI View
    static String componentSettingViewerId = "org.talend.designer.core.ui.views.properties.ComponentSettingsView";//$NON-NLS-1$

    static String navigatorId = "org.eclipse.ui.views.ResourceNavigator"; //$NON-NLS-1$

    static String outlineId = "org.eclipse.ui.views.ContentOutline"; //$NON-NLS-1$

    static String codeId = "org.talend.designer.core.codeView"; //$NON-NLS-1$

    static String repositoryId = IRepositoryView.VIEW_ID;

    static String runProcessViewId = "org.talend.designer.runprocess.ui.views.processview"; //$NON-NLS-1$

    static String problemsViewId = "org.talend.designer.core.ui.views.ProblemsView"; //$NON-NLS-1$

    static String modulesViewId = "org.talend.designer.codegen.perlmodule.ModulesView"; //$NON-NLS-1$

    static String exchangeViewId = "org.talend.designer.components.exchange.ui.views.ExchangeView"; //$NON-NLS-1$

    static String schedulerViewId = "org.talend.scheduler.views.Scheduler"; //$NON-NLS-1$

    static String contextsViewId = "org.talend.designer.core.ui.views.ContextsView"; //$NON-NLS-1$

    static String gefPaletteViewId = "org.eclipse.gef.ui.palette_view"; //$NON-NLS-1$

    static String jobSettingsViewId = "org.talend.designer.core.ui.views.jobsettings.JobSettingsView"; //$NON-NLS-1$

    static String jobHierarchyViewId = "org.talend.designer.core.ui.hierarchy.JobHierarchyViewPart"; //$NON-NLS-1$

    // DQ View
    static String dqRepositoryViewId = "org.talend.dataprofiler.core.ui.views.DQRespositoryView";//$NON-NLS-1$

    static String dqRepositoryDetailViewId = "org.talend.dataprofiler.core.ui.views.RespositoryDetailView";

    // MDM View
    static String mdmServerViewId = "org.talend.mdm.workbench.views.ServerView";

    // toolbar
    private static final String JOB_SCRIPT_ACTION = "org.talend.metalanguage.jobscript.JobScript";

    private static final String ASSIST_ACTION_SET = "org.talend.designer.core.assist.actionSet";

    private static Map<String, IContributionItem> lastPerspectives = new HashMap<String, IContributionItem>();

    /*
     * record the perspective to reset. maybe should try to find another way. if have done reset, don't do again.
     */
    private static Map<String, Boolean> resetPerspectiveFlags = new HashMap<String, Boolean>();

    public static void setPerspectiveReviewUtil() {
        // DI
        diViewList.add(componentSettingViewerId);
        diViewList.add(navigatorId);
        diViewList.add(outlineId);
        diViewList.add(codeId);
        diViewList.add(repositoryId);
        diViewList.add(runProcessViewId);
        diViewList.add(problemsViewId);
        diViewList.add(modulesViewId);
        diViewList.add(exchangeViewId);
        diViewList.add(schedulerViewId);
        diViewList.add(contextsViewId);
        diViewList.add(gefPaletteViewId);
        diViewList.add(jobSettingsViewId);
        diViewList.add(jobHierarchyViewId);

        // DQ
        dqViewList.add(dqRepositoryViewId);
        dqViewList.add(dqRepositoryDetailViewId);

        // MDM
        mdmViewList.add(mdmServerViewId);
    }

    /**
     * 
     * DOC Comment method "regisitPerspectiveBarSelectListener".
     */
    public static void regisitPerspectiveBarSelectListener() {
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().addPerspectiveListener(new PerspectiveAdapter() {

            @Override
            public void perspectiveActivated(IWorkbenchPage page, IPerspectiveDescriptor perspective) {
                String pId = perspective.getId();
                // bug TDI-8867
                hideToolBarItem(pId);

                if (null == isfirst || "".equals(isfirst)) {
                    isfirst = perspective.getId();
                    refreshAll();
                } else if (pId.equals(isfirst) && !"".equals(isfirst)) {
                    return;
                } else if (!pId.equals(isfirst) && !"".equals(isfirst)) {
                    isfirst = perspective.getId();
                    refreshAll();
                }

                // FIXME TUP-2293, reset the new created perspective.
                if (resetPerspectiveFlags.containsKey(pId)) {
                    Boolean flag = resetPerspectiveFlags.get(pId);
                    if (flag == null || !flag.booleanValue()) { // don't do
                        page.resetPerspective();
                        resetPerspectiveFlags.put(pId, true);
                    }
                }

            }

        });
    }

    /**
     * DOC PLV Comment method "hideToolBarItem".
     * 
     * @param pId
     */
    private static void hideToolBarItem(String pId) {
        hideToolBarItem(pId, JOB_SCRIPT_ACTION, new String[] { IBrandingConfiguration.PERSPECTIVE_DI_ID });
        hideToolBarItem(pId, ASSIST_ACTION_SET, new String[] { IBrandingConfiguration.PERSPECTIVE_DI_ID,
                IBrandingConfiguration.PERSPECTIVE_CAMEL_ID });
    }

    /**
     * DOC PLV Comment method "hideToolBarItem".
     * 
     * @param pId
     * @param actionID the actionID you want to hide
     * @param perspectivesID the perspectivesID you want to show
     */
    private static void hideToolBarItem(String pId, String actionID, String[] perspectivesID) {
        IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (activeWorkbenchWindow != null && pId != null && !"".equals(pId)) {
            ICoolBarManager barManager = ((WorkbenchWindow) activeWorkbenchWindow).getCoolBarManager2();
            if (barManager != null && (barManager instanceof CoolBarManager)) {
                IContributionItem diCItem = barManager.find(actionID);
                if (diCItem != null) {
                    boolean flag = false;
                    for (String perspective : perspectivesID) {
                        if (perspective.equals(pId)) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        lastPerspectives.put(actionID, diCItem);
                        barManager.remove(diCItem);
                    }

                } else {
                    if (lastPerspectives.get(actionID) != null) {
                        for (String perspective : perspectivesID) {
                            if (perspective.equals(pId)) {
                                barManager.add(lastPerspectives.get(actionID));
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 
     * DOC Comment method "refreshAll".
     */
    private static void refreshAll() {
        diViewList.clear();
        dqViewList.clear();
        mdmViewList.clear();
        setPerspectiveReviewUtil();
        IWorkbenchWindow workBenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (workBenchWindow != null) {
            IWorkbenchPage page = workBenchWindow.getActivePage();
            if (page != null) {
                String perId = page.getPerspective().getId();
                if ((!"".equals(perId) && null != perId)) {
                    // eg : use DI, then switch to DQ : All view from DI must be hidden when switch
                    if (perId.equalsIgnoreCase(IBrandingConfiguration.PERSPECTIVE_DI_ID)) {
                        for (String strId : dqViewList.toArray(new String[0])) {
                            IViewPart viewPart = page.findView(strId);
                            if (viewPart != null) {
                                page.hideView(viewPart);
                            }
                        }
                        for (String strId : mdmViewList.toArray(new String[0])) {
                            IViewPart viewPart = page.findView(strId);
                            if (viewPart != null) {
                                page.hideView(viewPart);
                            }
                        }
                    } else if (perId.equalsIgnoreCase(IBrandingConfiguration.PERSPECTIVE_DQ_ID)) {
                        for (String strId : diViewList.toArray(new String[0])) {
                            IViewPart viewPart = page.findView(strId);
                            if (viewPart != null) {
                                page.hideView(viewPart);
                            }
                        }
                        for (String strId : mdmViewList.toArray(new String[0])) {
                            IViewPart viewPart = page.findView(strId);
                            if (viewPart != null) {
                                page.hideView(viewPart);
                            }
                        }

                    } else if (perId.equalsIgnoreCase(IBrandingConfiguration.PERSPECTIVE_MDM_ID)) {
                        for (String strId : diViewList.toArray(new String[0])) {
                            IViewPart viewPart = page.findView(strId);
                            if (viewPart != null) {
                                page.hideView(viewPart);
                            }
                        }
                        for (String strId : dqViewList.toArray(new String[0])) {
                            IViewPart viewPart = page.findView(strId);
                            if (viewPart != null) {
                                page.hideView(viewPart);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 
     * DOC ggu Comment method "createPerspectiveBars".
     * 
     * try to display several Talend official perspectives.
     */

    public static void createPerspectiveBars() {
        // TUP-2293
        IWorkbench workbench = PlatformUI.getWorkbench();
        if (workbench instanceof org.eclipse.e4.ui.workbench.IWorkbench) {
            org.eclipse.e4.ui.workbench.IWorkbench e4Workbench = (org.eclipse.e4.ui.workbench.IWorkbench) workbench;
            MApplication mApp = e4Workbench.getApplication();
            IEclipseContext context = mApp.getContext();
            EModelService modelService = context.get(EModelService.class);
            if (modelService == null) {
                return;
            }
            MWindow mWindow = mApp.getSelectedElement();
            if (mWindow == null) {
                IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
                if (workbenchWindow != null) {
                    IWorkbenchPage activePage = workbenchWindow.getActivePage();
                    if (activePage != null && activePage instanceof WorkbenchPage) {
                        mWindow = ((WorkbenchPage) activePage).getWindowModel();
                    }
                }
            }
            if (mWindow == null) {
                return;
            }

            List<MPerspectiveStack> perspStackList = modelService.findElements(mWindow, null, MPerspectiveStack.class, null);
            if (perspStackList.size() > 0) {
                MPerspectiveStack perspectiveStack = perspStackList.get(0);

                List<MPerspective> validChildren = new ArrayList<MPerspective>();

                // DI
                findAndCreatePerspective(mApp, perspectiveStack, IBrandingConfiguration.PERSPECTIVE_DI_ID, validChildren);
                // DQ
                findAndCreatePerspective(mApp, perspectiveStack, IBrandingConfiguration.PERSPECTIVE_DQ_ID, validChildren);
                // MDM
                findAndCreatePerspective(mApp, perspectiveStack, IBrandingConfiguration.PERSPECTIVE_MDM_ID, validChildren);
                // Camel
                findAndCreatePerspective(mApp, perspectiveStack, IBrandingConfiguration.PERSPECTIVE_CAMEL_ID, validChildren);

                //
                reoderPerspectives(perspectiveStack, validChildren);

            }

        }
    }

    /**
     * 
     * DOC ggu Comment method "findAndCreatePerspective".
     * 
     * try to find and create the perspective. if the existed custom perspective, will use the custom one. won't create
     * original one.
     */
    public static void findAndCreatePerspective(MApplication mApp, MPerspectiveStack perspectiveStack, String id,
            List<MPerspective> validPerspList) {
        if (id != null && mApp != null && perspectiveStack != null) {
            MPerspective mPersp = null;

            IPerspectiveRegistry perspectiveRegistry = PlatformUI.getWorkbench().getPerspectiveRegistry();
            IPerspectiveDescriptor perspDesc = perspectiveRegistry.findPerspectiveWithId(id);
            if (perspDesc != null) {
                // find the existed.
                if (perspectiveStack != null) {
                    for (MPerspective mp : perspectiveStack.getChildren()) {
                        if (mp.getElementId().equals(id)) { // existed.
                            mPersp = mp;
                            break;
                        } else { // try to check custom perspective. (the element id should be different with id.)
                            IPerspectiveDescriptor persp = perspectiveRegistry.findPerspectiveWithId(mp.getElementId());
                            if (persp != null && persp instanceof PerspectiveDescriptor) {
                                PerspectiveDescriptor pd = (PerspectiveDescriptor) persp;

                                // if custom perspective, the original id and id are different.
                                if (!pd.getOriginalId().equals(pd.getId()) //
                                        && pd.getOriginalId().equals(id)) { // when custom, just use it.
                                    mPersp = mp;
                                    break;
                                }
                            }
                        }

                    }
                }
                /*
                 * if created, will be some problem for the perspective, like related view, action, etc... must do reset
                 * for those perspective.(need reset perspective is in resetPerspectiveFlags)
                 */
                // create new
                if (mPersp == null) { // FIXME copied some form method setPerspective of class WorkbenchPage
                    String perspId = perspDesc.getId();

                    resetPerspectiveFlags.put(perspId, false);

                    IEclipseContext context = mApp.getContext();
                    EModelService modelService = context.get(EModelService.class);
                    MWindow mWindow = mApp.getSelectedElement();

                    mPersp = (MPerspective) modelService.cloneSnippet(mApp, perspId, mWindow);
                    if (mPersp == null) {
                        // couldn't find the perspective, create a new one
                        mPersp = modelService.createModelElement(MPerspective.class);
                        // tag it with the same id
                        mPersp.setElementId(perspId);
                    }
                    mPersp.setLabel(perspDesc.getLabel());
                    ImageDescriptor imageDescriptor = perspDesc.getImageDescriptor();
                    if (imageDescriptor != null) {
                        String imageURL = MenuHelper.getImageUrl(imageDescriptor);
                        mPersp.setIconURI(imageURL);
                    }

                    // mPersp.setToBeRendered(true); // no need, it's true by default already.
                }
            }// else { // can't find or not be loaded.

            if (mPersp != null) {
                validPerspList.add(mPersp);
            }
        }
    }

    private static void reoderPerspectives(MPerspectiveStack perspectiveStack, List<MPerspective> validChildren) {
        List<MPerspective> children = perspectiveStack.getChildren();

        // record the order of perspective.
        Map<Integer, MPerspective> otherCustomPerspMap = new HashMap<Integer, MPerspective>();
        // try add back other custom perspectives
        for (int i = 0; i < children.size(); i++) {
            MPerspective p = children.get(i);
            if (!validChildren.contains(p)) { // not added, another custom
                otherCustomPerspMap.put(i, p);
            }// else { have added in front.
        }

        children.clear(); // clean other perspectives.
        children.addAll(validChildren); // add back the valid perspectives.

        // add back other costom perspectives
        Iterator<Integer> iterator = otherCustomPerspMap.keySet().iterator();
        while (iterator.hasNext()) {
            Integer index = iterator.next();
            if (index != null) {
                MPerspective persp = otherCustomPerspMap.get(index);
                // maybe this is not good, because after add, the original index have be changed.
                if (index < children.size()) {
                    children.add(index, persp);
                } else {
                    children.add(persp);
                }
            }
        }
    }

    /**
     * 
     * DOC ggu Comment method "resetDefaultPerspective".
     * 
     * If the perspective is not found, should reset one default perspective.
     */
    public static void resetDefaultPerspective() {
        // TUP-2293
        IWorkbench workbench = PlatformUI.getWorkbench();
        if (workbench instanceof org.eclipse.e4.ui.workbench.IWorkbench) {
            org.eclipse.e4.ui.workbench.IWorkbench e4Workbench = (org.eclipse.e4.ui.workbench.IWorkbench) workbench;
            MApplication mApp = e4Workbench.getApplication();
            IEclipseContext context = mApp.getContext();
            EPartService partService = context.get(EPartService.class);
            EModelService modelService = context.get(EModelService.class);
            MWindow mWindow = mApp.getSelectedElement();

            if (partService != null && modelService != null && mWindow != null) {
                List<MPerspectiveStack> perspStackList = modelService.findElements(mWindow, null, MPerspectiveStack.class, null);
                if (perspStackList.size() > 0) {
                    MPerspectiveStack perspectiveStack = perspStackList.get(0);
                    List<MPerspective> children = perspectiveStack.getChildren();

                    // just find out the last perspective.
                    String lastPerspId = perspectiveStack.getSelectedElement() != null ? perspectiveStack.getSelectedElement()
                            .getElementId() : null;

                    IPerspectiveDescriptor perspDesc = PlatformUI.getWorkbench().getPerspectiveRegistry()
                            .findPerspectiveWithId(lastPerspId);

                    if (perspDesc != null) { // existed, no need rest
                        return;
                    }
                    // for default
                    IPerspectiveRegistry perspectiveRegistry = workbench.getPerspectiveRegistry();
                    perspDesc = perspectiveRegistry.findPerspectiveWithId(perspectiveRegistry.getDefaultPerspective());

                    MPerspective mPersp = null;
                    if (perspDesc != null) {
                        for (MPerspective mp : children) {
                            if (mp.getElementId().equals(perspDesc.getId())) { // existed.
                                mPersp = mp;
                                break;
                            }
                        }
                    }

                    if (mPersp == null && !children.isEmpty()) { // if not found, try to use the first one.
                        mPersp = children.get(0);
                    }
                    if (mPersp != null) {
                        perspDesc = PlatformUI.getWorkbench().getPerspectiveRegistry()
                                .findPerspectiveWithId(mPersp.getElementId());

                        IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                        if (activeWorkbenchWindow != null && perspDesc != null) {
                            perspectiveStack.setSelectedElement(mPersp); // set one valid perspective.
                            IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
                            if (activePage != null) {
                                activePage.resetPerspective();
                            }
                        }

                    }
                }
            }
        }
    }

    /**
     * 
     * reset for perspective.
     */
    public static void resetPerspective() {

        // delete the invalid custom perspective, if not existed
        final IPerspectiveRegistry perspectiveRegistry = WorkbenchPlugin.getDefault().getPerspectiveRegistry();
        for (IPerspectiveDescriptor pd : perspectiveRegistry.getPerspectives()) {
            if (pd instanceof PerspectiveDescriptor) {
                PerspectiveDescriptor descriptor = (PerspectiveDescriptor) pd;
                // if custom, the OriginalId will not equal with id.
                if (descriptor.getOriginalId() != null && !descriptor.getOriginalId().equals(descriptor.getId())) {
                    IPerspectiveDescriptor findPerspective = perspectiveRegistry
                            .findPerspectiveWithId(descriptor.getOriginalId());
                    if (findPerspective == null) { // not found
                        perspectiveRegistry.deletePerspective(pd);
                    }
                }
            }
        }

        /*
         * the following codes should be @Deprecated, Seems it't not useful after E4. and should try to find other way
         * to do.
         */
        boolean reset = false;

        IPath path = WorkbenchPlugin.getDefault().getDataLocation();
        if (path == null) {
            return;
        }
        final File stateFile = path.append("workbench.xml").toFile(); //$NON-NLS-1$
        final boolean stateExist = stateFile.exists();
        if (stateFile == null || !stateExist) {
            reset = true;
        }
        FileInputStream input = null;

        if (stateFile != null && stateExist) { // PTODO seems can't work in E4, need check.
            try {
                input = new FileInputStream(stateFile);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input, "utf-8")); //$NON-NLS-1$
                IMemento memento = XMLMemento.createReadRoot(reader);
                /*
                 * it's not useful after E4, and will do reset in reSwitchPerspective.
                 */

                // IMemento[] windowArray = memento.getChildren(IWorkbenchConstants.TAG_WINDOW);
                // for (int cw = 0; windowArray != null && cw < windowArray.length; cw++) {
                // final IMemento windowMem = windowArray[cw];
                // IMemento[] pageArray = windowMem.getChildren(IWorkbenchConstants.TAG_PAGE);
                // for (int i = 0; pageArray != null && i < pageArray.length; i++) {
                // final IMemento pageMem = pageArray[i];
                // IMemento pespectiveMem = pageMem.getChild(IWorkbenchConstants.TAG_PERSPECTIVES);
                // if (pespectiveMem != null) {
                // String activePerspectiveID = pespectiveMem.getString(IWorkbenchConstants.TAG_ACTIVE_PERSPECTIVE);
                // IPerspectiveDescriptor perspectiveDesc = perspectiveRegistry
                // .findPerspectiveWithId(activePerspectiveID);
                // // find from original id
                // if (perspectiveDesc != null && perspectiveDesc instanceof PerspectiveDescriptor) {
                // String originalId = ((PerspectiveDescriptor) perspectiveDesc).getOriginalId();
                // perspectiveDesc = perspectiveRegistry.findPerspectiveWithId(originalId);
                // }
                // if (perspectiveDesc == null) { // not found, reset the workbench
                // stateFile.delete(); // if delete, will recreate a default new one
                // reset = true;
                // break;
                // }
                // }
                // }
                // }

                // check if bpm is installed to fix for TUP-647
                if (!reset) {
                    IMemento window = memento.getChild(IWorkbenchConstants.TAG_WINDOW);
                    if (window != null) {
                        IMemento child = window.getChild(IWorkbenchConstants.TAG_INTRO);
                        if (child != null) {
                            // child.putBoolean(IWorkbenchConstants.TAG_INTRO, true);
                            Class<? extends IMemento> mementoClass = window.getClass();
                            Field factoryField = mementoClass.getDeclaredField("factory");
                            Field elementFied = mementoClass.getDeclaredField("element");
                            if (factoryField != null && elementFied != null) {
                                elementFied.setAccessible(true);
                                factoryField.setAccessible(true);
                                Element element = (Element) elementFied.get(child);
                                Element winElement = (Element) elementFied.get(window);
                                Document document = (Document) factoryField.get(window);
                                winElement.removeChild(element.getNextSibling());
                                winElement.removeChild(element);

                                document.normalize();
                                FileOutputStream stream = new FileOutputStream(stateFile);
                                OutputStreamWriter writer = new OutputStreamWriter(stream, "utf-8"); //$NON-NLS-1$
                                Transformer tf = TransformerFactory.newInstance().newTransformer();

                                tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                                tf.setOutputProperty(OutputKeys.INDENT, "yes");
                                tf.transform(new DOMSource(document), new StreamResult(writer));

                                writer.close();

                            }
                        }
                    }

                }
            } catch (FileNotFoundException e) {
                //
            } catch (UnsupportedEncodingException e) {
                //
            } catch (WorkbenchException e) {
                //
            } catch (Exception e) {
                //
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        //
                    }
                }
            }
        }

        if (reset) {

            // clear the preference
            IPreferenceStore store = WorkbenchPlugin.getDefault().getPreferenceStore();
            String customPerspectives = store.getString(IPreferenceConstants.PERSPECTIVES);
            String[] perspectivesList = StringConverter.asArray(customPerspectives);

            for (String element : perspectivesList) {
                store.setValue(element + "_persp", ""); //$NON-NLS-1$
            }
            store.setValue(IPreferenceConstants.PERSPECTIVES, ""); //$NON-NLS-1$
            if (store.needsSaving() && store instanceof IPersistentPreferenceStore) {
                try {
                    ((IPersistentPreferenceStore) store).save();
                } catch (IOException e) {
                    //
                }
            }

            // delete the custom
            File folder = path.toFile();
            if (folder.isDirectory()) {
                File[] fileList = folder.listFiles();
                for (File file : fileList) {
                    if (file.getName().endsWith("_persp.xml")) { //$NON-NLS-1$
                        file.delete();
                    }
                }
            }
        }
    }
}
