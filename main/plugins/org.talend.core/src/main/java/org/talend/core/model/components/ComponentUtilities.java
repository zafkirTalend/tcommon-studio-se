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
package org.talend.core.model.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.jface.resource.ImageDescriptor;
import org.talend.commons.CommonsPlugin;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.CorePlugin;
import org.talend.core.model.general.Project;
import org.talend.core.model.process.EParameterFieldType;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.process.UniqueNodeNameGenerator;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.JobletProcessItem;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.designer.core.IPaletteFilter;
import org.talend.designer.core.model.utils.emf.talendfile.ColumnType;
import org.talend.designer.core.model.utils.emf.talendfile.ContextType;
import org.talend.designer.core.model.utils.emf.talendfile.ElementParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.ElementValueType;
import org.talend.designer.core.model.utils.emf.talendfile.MetadataType;
import org.talend.designer.core.model.utils.emf.talendfile.NodeType;
import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;
import org.talend.designer.core.model.utils.emf.talendfile.TalendFileFactory;
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
public class ComponentUtilities {

    private static final String CASE_SENSITIVE = "CASE_SENSITIVE"; //$NON-NLS-1$

    private static final String KEY_ATTRIBUTE = "KEY_ATTRIBUTE"; //$NON-NLS-1$

    private static final String SCHEMA_COLUMN = "SCHEMA_COLUMN"; //$NON-NLS-1$

    public static final String UNIQUE_NAME = "UNIQUE_NAME"; //$NON-NLS-1$

    public static final String NORMAL = "normal"; //$NON-NLS-1$

    public static final String JOBLET_NAME_CHANGED = "joblet name changed"; //$NON-NLS-1$

    public static final String JOBLET_SCHEMA_CHANGED = "joblet schema changed"; //$NON-NLS-1$

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
            paletteRoot = CorePlugin.getDefault().getDesignerCoreService().createEmptyPalette();
        }
        return paletteRoot;
    }

    public static void setExtraEntryVisible(boolean visible) {
        jobletFlag = visible;
        if (extraPaletteEntry != null) {
            for (PaletteEntry entry : extraPaletteEntry) {
                entry.setVisible(visible);
            }
        }
    }

    public static void setSkipUpdatePalette(boolean skipUpdatePalette) {
        ComponentUtilities.skipUpdatePalette = skipUpdatePalette;
    }

    public static void updatePalette() {
        if (jobletFlag == true) {
            setExtraEntryVisible(true);
        }
        if (skipUpdatePalette) {
            return;
        }
        IComponentsFactory components = ComponentsFactoryProvider.getInstance();

        if (paletteRoot != null) {
            List oldRoots = new ArrayList(paletteRoot.getChildren());

            for (Object obj : oldRoots) {
                if (obj instanceof TalendPaletteGroup) {
                    continue;
                }
                paletteRoot.remove((PaletteEntry) obj);
            }

            paletteRoot = CorePlugin.getDefault().getDesignerCoreService().createPalette(components, paletteRoot);

        } else {
            paletteRoot = CorePlugin.getDefault().getDesignerCoreService().createPalette(components);
        }

        if (extraPaletteEntry == null || extraPaletteEntry.size() == 0) {
            extraPaletteEntry = CorePlugin.getDefault().getDesignerCoreService().createJobletEtnry();
        }

    }

    public static void updatePalette(boolean isFavorite) {
        // if (jobletFlag == true) {
        // setExtraEntryVisible(true);
        // }
        faState = isFavorite;
        if (skipUpdatePalette) {
            return;
        }
        IComponentsFactory components = ComponentsFactoryProvider.getInstance();

        if (paletteRoot != null) {
            List oldRoots = new ArrayList(paletteRoot.getChildren());

            for (Object obj : oldRoots) {
                if (obj instanceof TalendPaletteGroup) {
                    continue;
                }
                paletteRoot.remove((PaletteEntry) obj);
            }
            // if (histate == 1) {
            // paletteRoot.getChildren().clear();
            // }
            paletteRoot = CorePlugin.getDefault().getDesignerCoreService().createPalette(components, paletteRoot, isFavorite);
        } else {
            paletteRoot = CorePlugin.getDefault().getDesignerCoreService().createPalette(components, isFavorite);
        }
        if (extraPaletteEntry == null || extraPaletteEntry.size() == 0) {
            extraPaletteEntry = CorePlugin.getDefault().getDesignerCoreService().createJobletEtnry();
        }

    }

    /**
     * yzhang Comment method "filterPalette".
     * 
     * @param filer
     */
    public static void filterPalette(String filer) {
        CorePlugin.getDefault().getDesignerCoreService().setPaletteFilter(filer);
        if (faState) {
            ComponentUtilities.updatePalette(true);
        } else
            ComponentUtilities.updatePalette(false);

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

    public static String getNodePropertyValue(NodeType node, String property) {
        ElementParameterType prop = getNodeProperty(node, property);
        if (prop == null) {
            return null;
        } else {
            return prop.getValue();
        }
    }

    public static ElementParameterType getNodeProperty(NodeType node, String property) {
        for (Object o : node.getElementParameter()) {
            ElementParameterType t = (ElementParameterType) o;
            if (t.getName().equals(property)) {
                return t;
            }
        }
        return null;
    }

    public static void addNodeProperty(NodeType node, String name, String field) {
        ElementParameterType property = TalendFileFactory.eINSTANCE.createElementParameterType();
        property.setName(name);
        property.setField(field);
        node.getElementParameter().add(property);
    }

    public static void setNodeProperty(NodeType node, String name, List<ElementValueType> values) {
        ElementParameterType property = getNodeProperty(node, name);

        for (ElementValueType value : values) {
            property.getElementValue().add(value);
        }
    }

    public static void setNodeValue(NodeType node, String name, String value) {
        ElementParameterType property = getNodeProperty(node, name);
        property.setValue(value);
    }

    public static void removeNodeProperty(NodeType node, String property) {
        // TODO SML Use getNodeProperty
        EList elementParameter = node.getElementParameter();
        Iterator iterator = elementParameter.iterator();
        for (Object o = iterator.next(); iterator.hasNext(); o = iterator.next()) {
            ElementParameterType t = (ElementParameterType) o;
            if (t.getName().equals(property)) {
                iterator.remove();
            }
        }
    }

    public static String getNodeUniqueName(NodeType node) {
        return ComponentUtilities.getNodePropertyValue(node, UNIQUE_NAME);
    }

    public static void setNodeUniqueName(NodeType node, String newName) {
        ElementParameterType t = getNodeProperty(node, UNIQUE_NAME);
        t.setValue(newName);
    }

    public static void replaceInNodeParameterValue(NodeType node, String oldName, String newName) {
        String oldName2 = "\\b" + oldName + "\\b"; //$NON-NLS-1$ //$NON-NLS-2$
        for (Object o : node.getElementParameter()) {
            ElementParameterType t = (ElementParameterType) o;
            String value = t.getValue();
            if (value != null) {
                String replaceAll = value.replaceAll(oldName2, newName);
                t.setValue(replaceAll);
            }
        }
    }

    public static String generateUniqueNodeName(String baseName, ProcessType process) {
        List<String> uniqueNodeNameList = new ArrayList<String>();
        for (Object o : process.getNode()) {
            NodeType nt = (NodeType) o;
            uniqueNodeNameList.add(getNodeUniqueName(nt));
        }
        return UniqueNodeNameGenerator.generateUniqueNodeName(baseName, uniqueNodeNameList);
    }

    public static List<ElementValueType> createtUniqRowV2UniqueKey(NodeType node) {
        // TODO SML Move in a more specific class
        List<ElementValueType> values = new ArrayList<ElementValueType>();
        TalendFileFactory fileFact = TalendFileFactory.eINSTANCE;

        MetadataType object = (MetadataType) node.getMetadata().get(0);

        for (Object o : object.getColumn()) {
            ColumnType tagada = (ColumnType) o;
            ElementValueType elementValue = fileFact.createElementValueType();
            elementValue.setElementRef(SCHEMA_COLUMN);
            elementValue.setValue(tagada.getName());
            values.add(elementValue);

            ElementValueType elementValue2 = fileFact.createElementValueType();
            elementValue2.setElementRef(KEY_ATTRIBUTE);
            elementValue2.setValue(new Boolean(tagada.isKey()).toString());
            values.add(elementValue2);

            ElementValueType elementValue3 = fileFact.createElementValueType();
            elementValue3.setElementRef(CASE_SENSITIVE);
            elementValue3.setValue("false"); //$NON-NLS-1$
            values.add(elementValue3);
        }
        return values;
    }

    public static NodeType getNodeTypeFromUniqueName(ProcessType processType, String uniqueName) {
        for (Object oNodeType : processType.getNode()) {
            NodeType nodeType = (NodeType) oNodeType;
            if (getNodeUniqueName(nodeType).equals(uniqueName)) {
                return nodeType;
            }
        }
        return null;
    }

    private static ProcessType getNodeProcessType(NodeType node) {
        return (ProcessType) node.eContainer();
    }

    public static ContextType getNodeCurrentContextType(NodeType node) {
        ProcessType processType = getNodeProcessType(node);
        String defaultContext = processType.getDefaultContext();
        EList context = processType.getContext();

        for (Object object : context) {
            ContextType contextType = (ContextType) object;
            if (defaultContext.endsWith(contextType.getName())) {
                return contextType;
            }
        }
        return null;
    }

    public static String getExtFolder(String folder) {
        String path = folder;
        // bug fix : several headless instance should not use the same folder
        if (CommonsPlugin.isStoreLibsInWorkspace()) {
            String workspaceName = ResourcesPlugin.getWorkspace().getRoot().getLocation().lastSegment();
            path = folder + "-" + workspaceName; //$NON-NLS-1$
        }
        return path;
    }

    public static PaletteRoot createPaletteRootWithAllComponents() {
        Map<String, String> allComponentsCanBeProvided = ComponentsFactoryProvider.getInstance().getAllComponentsCanBeProvided();
        Set<String> components = allComponentsCanBeProvided.keySet();

        List<String> families = new ArrayList<String>();
        Hashtable<String, PaletteDrawer> ht = new Hashtable<String, PaletteDrawer>();
        PaletteRoot palette = new PaletteRoot();

        // for family folders
        for (String nameAndFamily : components) {
            String[] split = nameAndFamily.split(FAMILY_SPEARATOR);
            if (split.length != 2) {
                continue;
            }
            families.add(split[0]);
        }
        Collections.sort(families);
        for (Iterator iter = families.iterator(); iter.hasNext();) {
            String oraFam = (String) iter.next();
            String family = getTranslatedFamilyName(oraFam);
            PaletteDrawer componentsDrawer = ht.get(family);
            if (componentsDrawer == null) {
                componentsDrawer = createComponentDrawer(palette, ht, family, oraFam);
            }

        }

        // for components
        for (String nameAndFamily : components) {
            String[] split = nameAndFamily.split(FAMILY_SPEARATOR);
            if (split.length != 2) {
                continue;
            }
            String family = getTranslatedFamilyName(split[0]);
            String[] strings = family.split(ComponentsFactoryProvider.FAMILY_SEPARATOR_REGEX);
            for (int j = 0; j < strings.length; j++) {
                ImageDescriptor imageDescriptor = ComponentsFactoryProvider.getInstance().getComponentsImageRegistry()
                        .get(allComponentsCanBeProvided.get(nameAndFamily));
                CombinedTemplateCreationEntry component = new CombinedTemplateCreationEntry(split[1], split[1], null, null,
                        imageDescriptor, imageDescriptor);
                PaletteDrawer componentsDrawer = ht.get(strings[j]);
                component.setParent(componentsDrawer);
                componentsDrawer.add(component);
            }
        }

        return palette;
    }

    private static String getTranslatedFamilyName(String originalName) {
        IComponentsFactory factory = ComponentsFactoryProvider.getInstance();
        String families[] = originalName.split(ComponentsFactoryProvider.FAMILY_SEPARATOR_REGEX);
        String translatedFamilyName = ""; //$NON-NLS-1$
        int nbTotal = families.length;
        int nb = 0;
        for (Object objFam : families) {
            String curFamily = (String) objFam;
            String[] namesToTranslate = curFamily.split("/"); //$NON-NLS-1$
            int nbSubTotal = namesToTranslate.length;
            int nbSub = 0;
            for (String toTranslate : namesToTranslate) {
                String translated = factory.getFamilyTranslation(originalName, "FAMILY." + toTranslate.replace(" ", "_")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                if (translated.startsWith("!!")) { //$NON-NLS-1$
                    // no key to translate, so use original
                    translatedFamilyName += toTranslate;
                } else {
                    translatedFamilyName += translated;
                }
                nbSub++;
                if (nbSubTotal != nbSub) {
                    translatedFamilyName += "/"; //$NON-NLS-1$
                }
            }
            nb++;
            if (nbTotal != nb) {
                translatedFamilyName += "|"; //$NON-NLS-1$
            }
        }

        return translatedFamilyName;
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

        PaletteDrawer paletteDrawer = CorePlugin.getDefault().getDesignerCoreService().createTalendPaletteDrawer(family);
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
        IProxyRepositoryFactory repositoryFactory = CorePlugin.getDefault().getProxyRepositoryFactory();

        try {
            List<IRepositoryViewObject> allProcess = repositoryFactory.getAll(project, ERepositoryObjectType.PROCESS, true);
            List<IRepositoryViewObject> allJoblet = repositoryFactory.getAll(project, ERepositoryObjectType.JOBLET, true);
            addUsedComponents(components, allProcess);
            addUsedComponents(components, allJoblet);
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
                        for (int i = 0; i < original.length; i++) {
                            components.add(original[i] + FAMILY_SPEARATOR + component.getName());
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

        setExtraEntryVisible(ERepositoryObjectType.JOBLET.equals(itemType));
    }

}
