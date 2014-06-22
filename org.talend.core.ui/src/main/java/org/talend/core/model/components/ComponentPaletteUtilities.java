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
package org.talend.core.model.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.gmf.util.DisplayUtils;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.model.general.Project;
import org.talend.core.model.process.EParameterFieldType;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.JobletProcessItem;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.core.service.IDesignerCoreUIService;
import org.talend.core.ui.CoreUIPlugin;
import org.talend.designer.core.IPaletteFilter;
import org.talend.designer.core.model.utils.emf.talendfile.ElementParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.NodeType;
import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;
import org.talend.repository.model.ComponentsFactoryProvider;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * TODO SML/NRO Move into org.talend.core ?
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class ComponentPaletteUtilities {

    private static final String FAMILY_HIER_SEPARATOR = "/"; //$NON-NLS-1$

    private static final String FAMILY_SPEARATOR = "--FAMILY--"; //$NON-NLS-1$

    private static PaletteRoot paletteRoot;

    private static List<PaletteEntry> extraPaletteEntry;

    private static boolean skipUpdatePalette;

    private static boolean faState = false;

    // public static int histate = 0;

    private static boolean jobletFlag = false;

    public static int histate = 0;

    public static PaletteRoot getPaletteRoot() {
        if (paletteRoot == null) {
            paletteRoot = CoreUIPlugin.getDefault().getDesignerCoreUIService().createEmptyPalette();
        }
        return paletteRoot;
    }

    private static void setExtraEntryVisible(boolean visible) {
        jobletFlag = visible;
        if (extraPaletteEntry != null) {
            for (PaletteEntry entry : extraPaletteEntry) {
                entry.setVisible(visible);
            }
        }
    }

    public static void setSkipUpdatePalette(boolean skipUpdatePalette) {
        ComponentPaletteUtilities.skipUpdatePalette = skipUpdatePalette;
    }

    public static void updatePalette() {
        if (jobletFlag == true) {
            setExtraEntryVisible(true);
        }
        if (skipUpdatePalette) {
            return;
        }
        DisplayUtils.getDisplay().syncExec(new Runnable() {

            @Override
            public void run() {
                IComponentsFactory components = ComponentsFactoryProvider.getInstance();

                final IDesignerCoreUIService designerCoreUIService = CoreUIPlugin.getDefault().getDesignerCoreUIService();
                if (paletteRoot != null) {
                    List oldRoots = new ArrayList(paletteRoot.getChildren());

                    for (Iterator it = oldRoots.iterator(); it.hasNext();) {
                        Object obj = it.next();
                        if (obj instanceof TalendPaletteGroup) {
                            continue;
                        }
                        it.remove();
                    }
                    paletteRoot.setChildren(oldRoots);
                    paletteRoot = designerCoreUIService.createPalette(components, paletteRoot);

                } else {
                    paletteRoot = designerCoreUIService.createPalette(components);
                }

                if (extraPaletteEntry == null || extraPaletteEntry.size() == 0) {
                    extraPaletteEntry = designerCoreUIService.createJobletEtnry();
                }
            }
        });
    }

    public static void updatePalette(final boolean isFavorite) {
        // if (jobletFlag == true) {
        // setExtraEntryVisible(true);
        // }
        faState = isFavorite;
        if (skipUpdatePalette) {
            return;
        }
        DisplayUtils.getDisplay().syncExec(new Runnable() {

            @Override
            public void run() {
                IComponentsFactory components = ComponentsFactoryProvider.getInstance();

                final IDesignerCoreUIService designerCoreUIService = CoreUIPlugin.getDefault().getDesignerCoreUIService();
                if (paletteRoot != null) {
                    List oldRoots = new ArrayList(paletteRoot.getChildren());

                    for (Iterator it = oldRoots.iterator(); it.hasNext();) {
                        Object obj = it.next();
                        if (obj instanceof TalendPaletteGroup) {
                            continue;
                        }
                        it.remove();
                    }
                    paletteRoot.setChildren(oldRoots);
                    paletteRoot = designerCoreUIService.createPalette(components, paletteRoot, isFavorite);
                } else {
                    paletteRoot = designerCoreUIService.createPalette(components, isFavorite);
                }
                if (extraPaletteEntry == null || extraPaletteEntry.size() == 0) {
                    extraPaletteEntry = designerCoreUIService.createJobletEtnry();
                }
            }
        });
    }

    /**
     * yzhang Comment method "filterPalette".
     * 
     * @param filer
     */
    public static void filterPalette(String filer) {
        CoreUIPlugin.getDefault().getDesignerCoreUIService().setPaletteFilter(filer);
        if (faState) {
            updatePalette(true);
        } else {
            updatePalette(false);
        }

        markEmptyDrawer(paletteRoot);
        emptyEntry.clear();
        recordEmptyDrawer(paletteRoot);
        removeEmptyDrawer();
    }

    private static List<PaletteEntry> emptyEntry = new ArrayList<PaletteEntry>();

    /**
     * yzhang Comment method "removeEmptyDrawer".
     */
    private static void removeEmptyDrawer() {
        for (PaletteEntry entry : emptyEntry) {
            PaletteContainer container = entry.getParent();
            if (container != null) {
                container.remove(entry);
            }
        }
    }

    /**
     * yzhang Comment method "recordEmptyDrawer".
     * 
     * @param entry
     */
    private static void recordEmptyDrawer(PaletteEntry entry) {
        if (entry instanceof PaletteRoot) {
            List<PaletteEntry> entries = ((PaletteRoot) entry).getChildren();
            for (PaletteEntry paletteEntry : entries) {
                if (paletteEntry instanceof PaletteDrawer) {
                    recordEmptyDrawer(paletteEntry);
                }
            }
        } else if (entry instanceof PaletteDrawer) {
            PaletteDrawer drawer = (PaletteDrawer) entry;
            if (drawer instanceof IPaletteFilter && ((IPaletteFilter) entry).isFiltered()) {
                emptyEntry.add(entry);
            } else {
                List children = drawer.getChildren();
                for (Object obj : children) {
                    recordEmptyDrawer((PaletteEntry) obj);
                }
            }
        }
    }

    /**
     * yzhang Comment method "filterEmptyDrawer".
     * 
     * @param entry
     */
    private static void markEmptyDrawer(PaletteEntry entry) {
        if (entry instanceof PaletteRoot) {
            List<PaletteEntry> entries = ((PaletteRoot) entry).getChildren();
            for (PaletteEntry paletteEntry : entries) {
                if (paletteEntry instanceof PaletteDrawer) {
                    markEmptyDrawer(paletteEntry);
                }
            }
        } else if (entry instanceof PaletteDrawer) {
            PaletteDrawer drawer = (PaletteDrawer) entry;
            for (Object obj : drawer.getChildren()) {
                markEmptyDrawer((PaletteEntry) obj);
            }
            return;
        }
        PaletteEntry parentEntry = entry.getParent();
        if (parentEntry instanceof IPaletteFilter) {
            ((IPaletteFilter) parentEntry).setFiltered(false);
        }
    }

    public static PaletteRoot createPaletteRootWithAllComponents() {
        Set<IComponent> components = ComponentsFactoryProvider.getInstance().getComponents();

        List<String> families = new ArrayList<String>();
        Hashtable<String, PaletteDrawer> ht = new Hashtable<String, PaletteDrawer>();
        PaletteRoot palette = new PaletteRoot();

        // for family folders
        for (IComponent component : components) {
            if (component.isTechnical() || component.getComponentType() == EComponentType.JOBLET) {
                continue;
            }
            String family = component.getOriginalFamilyName();
            families.add(family);
        }
        Collections.sort(families);
        for (String family : families) {
            String[] strings = family.split(ComponentsFactoryProvider.FAMILY_SEPARATOR_REGEX);
            for (String string : strings) {
                PaletteDrawer componentsDrawer = ht.get(string);
                if (componentsDrawer == null) {
                    componentsDrawer = createComponentDrawer(palette, ht, string, family);
                }
            }
        }

        // for components
        for (IComponent component : components) {
            if (component.isTechnical() || component.getComponentType() == EComponentType.JOBLET) {
                continue;
            }
            String family = component.getOriginalFamilyName();
            String[] strings = family.split(ComponentsFactoryProvider.FAMILY_SEPARATOR_REGEX);
            for (String string : strings) {
                CombinedTemplateCreationEntry componentEntry = new CombinedTemplateCreationEntry(component.getName(),
                        component.getName(), null, null, component.getIcon24(), component.getIcon32());
                PaletteDrawer componentsDrawer = ht.get(string);
                componentEntry.setParent(componentsDrawer);
                componentsDrawer.add(componentEntry);
            }
        }

        return palette;
    }

    private static PaletteDrawer createComponentDrawer(PaletteRoot palette, Hashtable<String, PaletteDrawer> ht,
            String familyToCreate, String originalFamily) {

        int index = familyToCreate.lastIndexOf(FAMILY_HIER_SEPARATOR);
        int orgIndex = originalFamily.lastIndexOf(FAMILY_HIER_SEPARATOR);
        String family;
        String orgFamily;
        PaletteDrawer parentPaletteDrawer = null;

        if (index > -1) {
            family = familyToCreate.substring(index + 1);
            orgFamily = originalFamily.substring(0, orgIndex);
            String parentFamily = familyToCreate.substring(0, index);
            parentPaletteDrawer = ht.get(parentFamily);
            if (parentPaletteDrawer == null) {
                parentPaletteDrawer = createComponentDrawer(palette, ht, parentFamily, orgFamily);
            }
        } else {
            family = familyToCreate;
            orgFamily = originalFamily;
        }

        PaletteDrawer paletteDrawer = CoreUIPlugin.getDefault().getDesignerCoreUIService().createTalendPaletteDrawer(family);
        if (parentPaletteDrawer == null) {
            palette.add(paletteDrawer);
        } else {
            parentPaletteDrawer.add(paletteDrawer);
        }
        if (paletteDrawer instanceof IPaletteFilter) {
            ((IPaletteFilter) paletteDrawer).setOriginalName(originalFamily);
        }
        ht.put(familyToCreate, paletteDrawer);

        return paletteDrawer;
    }

    public static Set<String> getComponentsUsedInProject(Project project) {
        Set<String> components = new HashSet<String>();
        IProxyRepositoryFactory repositoryFactory = CoreUIPlugin.getDefault().getProxyRepositoryFactory();

        try {
            ERepositoryObjectType jobType = ERepositoryObjectType.PROCESS;
            if (jobType != null) {
                List<IRepositoryViewObject> allProcess = repositoryFactory.getAll(project, jobType, true);
                addUsedComponents(components, allProcess);
            }
            ERepositoryObjectType jobletType = ERepositoryObjectType.JOBLET;
            if (jobletType != null) {
                List<IRepositoryViewObject> allJoblet = repositoryFactory.getAll(project, jobletType, true);
                addUsedComponents(components, allJoblet);
            }
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }
        return components;
    }

    private static void addUsedComponents(Set<String> components, List<IRepositoryViewObject> allProcess) {
        for (IRepositoryViewObject object : allProcess) {
            Item item = object.getProperty().getItem();

            List parameters = null;
            ProcessType processType = null;
            if (item instanceof ProcessItem) {
                processType = ((ProcessItem) item).getProcess();
            } else if (item instanceof JobletProcessItem) {
                processType = ((JobletProcessItem) item).getJobletProcess();
            }
            if (processType != null) {
                for (Object oNode : processType.getNode()) {
                    NodeType node = (NodeType) oNode;
                    IComponent component = ComponentsFactoryProvider.getInstance().get(node.getComponentName());
                    if (component != null) {
                        String originalFamilyName = component.getOriginalFamilyName();
                        String original[] = originalFamilyName.split(ComponentsFactoryProvider.FAMILY_SEPARATOR_REGEX);
                        for (String element : original) {
                            components.add(element + FAMILY_SPEARATOR + component.getName());
                        }
                    }
                }
                if (processType.getParameters() != null) { // occurs actually only in joblets
                    parameters = processType.getParameters().getElementParameter();
                }
            }

            if (parameters != null) {
                // used in stats&log and implicite
                Set<String> inStatsLogsAndImplicit = getComponentsInStatsLogsAndImplicit(parameters);
                if (inStatsLogsAndImplicit != null) {
                    components.addAll(inStatsLogsAndImplicit);
                }
            }
        }
    }

    private static Set<String> getComponentsInStatsLogsAndImplicit(List parameters) {
        Set<String> components = new HashSet<String>();
        for (Object obj : parameters) {
            String paramName = null;
            Object value = null;
            if (obj instanceof ElementParameterType) {
                ElementParameterType param = (ElementParameterType) obj;
                paramName = param.getName();
                value = param.getValue();
            }
            if (value == null) {
                continue;
            }
            if ("ON_STATCATCHER_FLAG".equals(paramName)) { //$NON-NLS-1$
                addComponent(components, "tStatCatcher", value); //$NON-NLS-1$
            } else if ("ON_METERCATCHER_FLAG".equals(paramName)) { //$NON-NLS-1$
                addComponent(components, "tFlowMeterCatcher", value); //$NON-NLS-1$
            } else if ("ON_LOGCATCHER_FLAG".equals(paramName)) { //$NON-NLS-1$
                addComponent(components, "tLogCatcher", value); //$NON-NLS-1$
            } else if ("ON_CONSOLE_FLAG".equals(paramName)) { //$NON-NLS-1$
                addComponent(components, "tLogRow", value); //$NON-NLS-1$
            } else if ("ON_FILES_FLAG".equals(paramName)) { //$NON-NLS-1$
                addComponent(components, "tFileOutputDelimited", value); //$NON-NLS-1$
            } else if ("ON_DATABASE_FLAG".equals(paramName)) { //$NON-NLS-1$
                String usedDatabase = getUsedDatabase(parameters, "DB_TYPE"); //$NON-NLS-1$
                if (usedDatabase != null) {
                    addComponent(components, usedDatabase, value);
                }
            } else if ("IMPLICIT_TCONTEXTLOAD".equals(paramName)) { //$NON-NLS-1$
                addComponent(components, "tContextLoad", value); //$NON-NLS-1$
            } else if ("FROM_FILE_FLAG_IMPLICIT_CONTEXT".equals(paramName)) { //$NON-NLS-1$
                addComponent(components, "tFileInputDelimited", value); //$NON-NLS-1$
            } else if ("FROM_DATABASE_FLAG_IMPLICIT_CONTEXT".equals(paramName)) { //$NON-NLS-1$
                String usedDatabase = getUsedDatabase(parameters, "DB_TYPE_IMPLICIT_CONTEXT"); //$NON-NLS-1$
                if (usedDatabase != null) {
                    addComponent(components, usedDatabase, value);
                }

            }
        }
        return components;
    }

    private static void addComponent(Set<String> components, String name, Object value) {
        if (Boolean.TRUE.equals(Boolean.valueOf(value.toString()))) {
            components.add(getComponentFamily(name) + FAMILY_SPEARATOR + name);
        }
    }

    private static String getComponentFamily(String componentName) {
        Set<IComponent> components = ComponentsFactoryProvider.getInstance().getComponents();
        for (IComponent component : components) {
            if (component.getName().equals(componentName)) {
                return component.getOriginalFamilyName();
            }
        }
        return null;

    }

    private static String getUsedDatabase(List parameters, String typeName) {
        for (Object obj : parameters) {
            String paramName = null;
            Object value = null;
            String field = null;
            if (obj instanceof IElementParameter) {
                IElementParameter param = (IElementParameter) obj;
                paramName = param.getName();
                value = param.getValue();
                field = param.getFieldType().getName();
            } else if (obj instanceof ElementParameterType) {
                ElementParameterType param = (ElementParameterType) obj;
                paramName = param.getName();
                value = param.getValue();
                field = param.getField();
            }
            if (EParameterFieldType.CLOSED_LIST.getName().equals(field) && typeName.equals(paramName)) {
                if (value != null) {
                    return value.toString();
                }
            }
        }
        return null;
    }

    /**
     * DOC guanglong.du Comment method "updateFromRepositoryType".
     * 
     * @param itemType
     */
    public static void updateFromRepositoryType(ERepositoryObjectType itemType) {
        updatePalette(faState);

        // if (ERepositoryObjectType.PROCESS.equals(itemType) || ERepositoryObjectType.JOBLET.equals(itemType)) {
        // isCamel = false;
        // // show all components
        // for (Object objectEntry : getPaletteEntriesGroupMap().get("tos")) {
        // if (objectEntry instanceof PaletteEntry) {
        // ((PaletteEntry) objectEntry).setVisible(true);
        // }
        // }
        // // hide camel
        // for (Object entry : getPaletteEntriesGroupMap().get("camel")) {
        // if (entry instanceof PaletteEntry) {
        // ((PaletteEntry) entry).setVisible(true);
        // }
        // }
        // } else {
        // updatePalette();
        //
        // isCamel = true;
        // // hide all components
        // for (Object objectEntry : getPaletteEntriesGroupMap().get("tos")) {
        // if (objectEntry instanceof PaletteEntry) {
        // // ((PaletteEntry) objectEntry).setVisible(false);
        // paletteRoot.getChildren().remove(objectEntry);
        // }
        // }
        // // show only the camel components
        // for (Object entry : getPaletteEntriesGroupMap().get("camel")) {
        // if (entry instanceof PaletteEntry) {
        // ((PaletteEntry) entry).setVisible(true);
        // }
        // }
        //
        // }

        setExtraEntryVisible(itemType.equals(ERepositoryObjectType.JOBLET));
    }

}
