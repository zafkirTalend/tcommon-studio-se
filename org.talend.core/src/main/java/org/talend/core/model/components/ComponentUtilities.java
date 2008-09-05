// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.talend.core.CorePlugin;
import org.talend.core.model.process.UniqueNodeNameGenerator;
import org.talend.designer.core.IFilter;
import org.talend.designer.core.model.utils.emf.talendfile.ColumnType;
import org.talend.designer.core.model.utils.emf.talendfile.ContextType;
import org.talend.designer.core.model.utils.emf.talendfile.ElementParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.ElementValueType;
import org.talend.designer.core.model.utils.emf.talendfile.MetadataType;
import org.talend.designer.core.model.utils.emf.talendfile.NodeType;
import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;
import org.talend.designer.core.model.utils.emf.talendfile.TalendFileFactory;
import org.talend.repository.model.ComponentsFactoryProvider;

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

    private static final String UNIQUE_NAME = "UNIQUE_NAME"; //$NON-NLS-1$

    public static final String NORMAL = "normal";

    public static final String JOBLET_NAME_CHANGED = "joblet name changed";

    public static final String JOBLET_SCHEMA_CHANGED = "joblet schema changed";

    private static PaletteRoot paletteRoot;

    private static List<PaletteEntry> extraPaletteEntry;

    public static PaletteRoot getPaletteRoot() {
        if (paletteRoot == null) {
            updatePalette();
        }
        return paletteRoot;
    }

    public static void setExtraEntryVisible(boolean visible) {
        if (extraPaletteEntry != null) {
            for (PaletteEntry entry : extraPaletteEntry) {
                entry.setVisible(visible);
            }
        }
    }

    public static void updatePalette() {

        IComponentsFactory components = ComponentsFactoryProvider.getInstance();
        if (paletteRoot != null) {
            List oldRoots = new ArrayList(paletteRoot.getChildren());

            for (Object obj : oldRoots) {
                if (obj instanceof PaletteGroup) {
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

    /**
     * yzhang Comment method "filterPalette".
     * 
     * @param filer
     */
    public static void filterPalette(String filer) {
        CorePlugin.getDefault().getDesignerCoreService().setPaletteFilter(filer);
        ComponentUtilities.updatePalette();
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
            if (drawer instanceof IFilter && ((IFilter) entry).isFiltered()) {
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
        if (parentEntry instanceof IFilter) {
            ((IFilter) parentEntry).setFiltered(false);
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
        return ComponentUtilities.getNodePropertyValue(node, UNIQUE_NAME); //$NON-NLS-1$
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
}
