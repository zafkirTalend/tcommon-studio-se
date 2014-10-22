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
import java.util.List;
import java.util.Map;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.CoolBarManager;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PerspectiveAdapter;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.XMLMemento;
import org.eclipse.ui.internal.IPreferenceConstants;
import org.eclipse.ui.internal.IWorkbenchConstants;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.eclipse.ui.internal.registry.PerspectiveDescriptor;
import org.eclipse.ui.internal.util.PrefUtil;
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
     * DOC Comment method "setPerspectiveTabs".
     */

    public static void setPerspectiveTabs() {
        // // feature 19053 add
        // IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        //
        // // fix TDI-23057
        // ((WorkbenchWindow) activeWorkbenchWindow).addPerspectiveReorderListener(null);
        //
        // PerspectiveBarManager barManager = ((WorkbenchWindow) activeWorkbenchWindow).getPerspectiveBar();
        //
        // if (barManager != null && (barManager instanceof PerspectiveBarManager)) {
        // cleanPerspectiveBar();
        // // DI
        // IContributionItem lastPerspective = null;
        // IContributionItem diCItem = barManager.find(IBrandingConfiguration.PERSPECTIVE_DI_ID);
        // if (null == diCItem) {
        // IPerspectiveDescriptor diMailPerspective = WorkbenchPlugin.getDefault().getPerspectiveRegistry()
        // .findPerspectiveWithId(IBrandingConfiguration.PERSPECTIVE_DI_ID);
        // if (null != diMailPerspective && (diMailPerspective instanceof IPerspectiveDescriptor)) {
        // PerspectiveBarContributionItem diItem = new PerspectiveBarContributionItem(diMailPerspective,
        // activeWorkbenchWindow.getActivePage());
        // if (null != diItem && (diItem instanceof PerspectiveBarContributionItem)) {
        // barManager.addItem(diItem);
        // diCItem = diItem;
        // }
        // }
        // }
        // lastPerspective = diCItem;
        //
        // // DQ
        // IContributionItem dqCItem = barManager.find(IBrandingConfiguration.PERSPECTIVE_DQ_ID);
        // if (null == dqCItem) {
        // IPerspectiveDescriptor dqMailPerspective = WorkbenchPlugin.getDefault().getPerspectiveRegistry()
        // .findPerspectiveWithId(IBrandingConfiguration.PERSPECTIVE_DQ_ID);
        // if (null != dqMailPerspective && (dqMailPerspective instanceof IPerspectiveDescriptor)) {
        // PerspectiveBarContributionItem dqItem = new PerspectiveBarContributionItem(dqMailPerspective,
        // activeWorkbenchWindow.getActivePage());
        // if (null != dqItem && (dqItem instanceof PerspectiveBarContributionItem)) {
        // if (diCItem != null) {
        // barManager.insertAfter(diCItem.getId(), dqItem);
        // } else {
        // barManager.addItem(dqItem);
        // }
        // dqCItem = dqItem;
        // }
        // }
        // }
        // if (dqCItem != null) {
        // lastPerspective = dqCItem;
        // }
        // // MDM
        // IContributionItem mdmCItem = barManager.find(IBrandingConfiguration.PERSPECTIVE_MDM_ID);
        // if (null == mdmCItem) {
        // IPerspectiveDescriptor mdmMailPerspective = WorkbenchPlugin.getDefault().getPerspectiveRegistry()
        // .findPerspectiveWithId(IBrandingConfiguration.PERSPECTIVE_MDM_ID);
        // if (null != mdmMailPerspective && (mdmMailPerspective instanceof IPerspectiveDescriptor)) {
        // PerspectiveBarContributionItem mdmItem = new PerspectiveBarContributionItem(mdmMailPerspective,
        // activeWorkbenchWindow.getActivePage());
        // if (null != mdmItem && (mdmItem instanceof PerspectiveBarContributionItem)) {
        // if (lastPerspective != null) {
        // barManager.insertAfter(lastPerspective.getId(), mdmItem);
        // } else {
        // barManager.addItem(mdmItem);
        // }
        // mdmCItem = mdmItem;
        // }
        // }
        // }
        // if (mdmCItem != null) {
        // lastPerspective = mdmCItem;
        // }
        //
        // // CAMEL
        // IContributionItem esbCItem = barManager.find(IBrandingConfiguration.PERSPECTIVE_CAMEL_ID);
        // if (null == esbCItem) {
        // IPerspectiveDescriptor esbPerspective = WorkbenchPlugin.getDefault().getPerspectiveRegistry()
        // .findPerspectiveWithId(IBrandingConfiguration.PERSPECTIVE_CAMEL_ID);
        // if (null != esbPerspective && (esbPerspective instanceof IPerspectiveDescriptor)) {
        // PerspectiveBarContributionItem esbItem = new PerspectiveBarContributionItem(esbPerspective,
        // activeWorkbenchWindow.getActivePage());
        // if (null != esbItem && (esbItem instanceof PerspectiveBarContributionItem)) {
        // if (lastPerspective != null) {
        // barManager.insertAfter(lastPerspective.getId(), esbItem);
        // } else {
        // barManager.addItem(esbItem);
        // }
        // mdmCItem = esbItem;
        // }
        // }
        // }
        //
        // barManager.update(false);
        // }
    }

    private static void cleanPerspectiveBar() {
        // IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        // if (activeWorkbenchWindow == null) {
        // return;
        // }
        // PerspectiveBarManager barManager = ((WorkbenchWindow) activeWorkbenchWindow).getPerspectiveBar();
        // if (barManager == null) {
        // return;
        // }
        //
        // for (IContributionItem item : barManager.getItems()) {
        // if (item instanceof PerspectiveBarContributionItem) {
        // PerspectiveBarContributionItem perspectiveItem = (PerspectiveBarContributionItem) item;
        // String pId = perspectiveItem.getId();
        // IPerspectiveDescriptor pPerspective = WorkbenchPlugin.getDefault().getPerspectiveRegistry()
        // .findPerspectiveWithId(pId);
        // if (pPerspective == null) {
        // barManager.removeItem(perspectiveItem);
        // }
        // }
        // }
    }

    /**
     * 
     * DOC Comment method "setPerspectiveTabsBarSize".
     */
    public static void setPerspectiveTabsBarSize() {
        int nbPerpectives = 1;

        if (WorkbenchPlugin.getDefault().getPerspectiveRegistry().findPerspectiveWithId(IBrandingConfiguration.PERSPECTIVE_DQ_ID) != null) {
            nbPerpectives++;
        }
        if (WorkbenchPlugin.getDefault().getPerspectiveRegistry()
                .findPerspectiveWithId(IBrandingConfiguration.PERSPECTIVE_MDM_ID) != null) {
            nbPerpectives++;
        }
        if (WorkbenchPlugin.getDefault().getPerspectiveRegistry()
                .findPerspectiveWithId(IBrandingConfiguration.PERSPECTIVE_CAMEL_ID) != null) {
            nbPerpectives++;
        }

        IPreferenceStore apiStore = PrefUtil.getAPIPreferenceStore();
        apiStore.setValue(IWorkbenchPreferenceConstants.PERSPECTIVE_BAR_SIZE, nbPerpectives * 100);
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

    public static void resetPerspective() {
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
        final IPerspectiveRegistry perspectiveRegistry = WorkbenchPlugin.getDefault().getPerspectiveRegistry();
        if (stateFile != null && stateExist) {
            try {
                input = new FileInputStream(stateFile);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input, "utf-8")); //$NON-NLS-1$
                IMemento memento = XMLMemento.createReadRoot(reader);

                IMemento[] windowArray = memento.getChildren(IWorkbenchConstants.TAG_WINDOW);
                for (int cw = 0; windowArray != null && cw < windowArray.length; cw++) {
                    final IMemento windowMem = windowArray[cw];
                    IMemento[] pageArray = windowMem.getChildren(IWorkbenchConstants.TAG_PAGE);
                    for (int i = 0; pageArray != null && i < pageArray.length; i++) {
                        final IMemento pageMem = pageArray[i];
                        IMemento pespectiveMem = pageMem.getChild(IWorkbenchConstants.TAG_PERSPECTIVES);
                        if (pespectiveMem != null) {
                            String activePerspectiveID = pespectiveMem.getString(IWorkbenchConstants.TAG_ACTIVE_PERSPECTIVE);
                            IPerspectiveDescriptor perspectiveDesc = perspectiveRegistry
                                    .findPerspectiveWithId(activePerspectiveID);
                            // find from original id
                            if (perspectiveDesc != null && perspectiveDesc instanceof PerspectiveDescriptor) {
                                String originalId = ((PerspectiveDescriptor) perspectiveDesc).getOriginalId();
                                perspectiveDesc = perspectiveRegistry.findPerspectiveWithId(originalId);
                            }
                            if (perspectiveDesc == null) { // not found, reset the workbench
                                stateFile.delete(); // if delete, will recreate a default new one
                                reset = true;
                                break;
                            }
                        }
                    }
                }
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
        // delete the custom
        for (IPerspectiveDescriptor pd : perspectiveRegistry.getPerspectives()) {
            if (pd instanceof PerspectiveDescriptor) {
                PerspectiveDescriptor descriptor = (PerspectiveDescriptor) pd;
                // if custom, the OriginalId will have value
                if (descriptor.getOriginalId() != null) {
                    IPerspectiveDescriptor findPerspective = perspectiveRegistry
                            .findPerspectiveWithId(descriptor.getOriginalId());
                    if (findPerspective == null) { // not found
                        perspectiveRegistry.deletePerspective(pd);
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
