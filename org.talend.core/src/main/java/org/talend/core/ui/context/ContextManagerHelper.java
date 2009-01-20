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
package org.talend.core.ui.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.context.JobContextParameter;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.types.ContextParameterJavaTypeManager;
import org.talend.core.model.process.IContext;
import org.talend.core.model.process.IContextManager;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.properties.ContextItem;
import org.talend.core.ui.context.cmd.OrderContextParameterCommand;
import org.talend.core.ui.context.model.template.ContextConstant;
import org.talend.core.ui.context.model.template.ContextParameterParent;
import org.talend.core.ui.context.model.template.ContextParameterSortedSon;
import org.talend.designer.core.model.utils.emf.talendfile.ContextParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.ContextType;
import org.talend.repository.model.ERepositoryStatus;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * DOC ggu class global comment. Detailled comment
 */
public final class ContextManagerHelper {

    private Map<ContextItem, List<ContextParameterType>> contextMap;

    private IContextManager manager;

    private Map<String, Set<String>> itemNameToParametersMap = new HashMap<String, Set<String>>();

    public ContextManagerHelper(IContextManager manager) {
        super();
        initHelper(manager);
    }

    public void initHelper(IContextManager manager) {
        this.manager = manager;
        initExistedParametersMap();
        contextMap = new HashMap<ContextItem, List<ContextParameterType>>();
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        List<ContextItem> itemList = null;

        try {
            itemList = factory.getContextItem();
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }
        if (itemList != null) {

            for (ContextItem contextItem : itemList) {
                if (factory.getStatus(contextItem) != ERepositoryStatus.DELETED) {
                    addParameterMap(contextItem);
                }
            }
        }

    }

    private void addParameterMap(ContextItem item) {
        List<ContextParameterType> paramTypeSet = contextMap.get(item);
        if (paramTypeSet == null) {
            paramTypeSet = new ArrayList<ContextParameterType>();
            contextMap.put(item, paramTypeSet);
        }
        ContextType type = getDefaultContextType(item);

        if (type != null) {
            for (Object objParam : type.getContextParameter()) {
                ContextParameterType param = (ContextParameterType) objParam;
                paramTypeSet.add(param);
            }
        }
    }

    private void initExistedParametersMap() {
        if (!isValid(manager)) {
            return;
        }
        itemNameToParametersMap.clear();
        for (IContextParameter param : manager.getDefaultContext().getContextParameterList()) {
            if (!param.isBuiltIn()) {
                final String source = param.getSource();
                Set<String> paramSet = itemNameToParametersMap.get(source);
                if (paramSet == null) {
                    paramSet = new HashSet<String>();
                    itemNameToParametersMap.put(source, paramSet);
                }
                paramSet.add(param.getName());
            }
        }
    }

    private boolean isValid(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if ("".equals(str)) { //$NON-NLS-1$
                return false;
            }
        }
        return true;
    }

    /*
     * get valid the ContextItem.
     */
    public Set<ContextItem> getContextItems() {
        return contextMap.keySet();
    }

    /*
     * get the ContextParameterTypes of default ContextType from ContextItem
     */
    public List<ContextParameterType> getContextParameterType(ContextItem item) {
        if (!isValid(item)) {
            return null;
        }
        List<ContextParameterType> paramSet = contextMap.get(item);
        if (paramSet != null) {
            return paramSet;
        }
        return null;
    }

    /*
     * get default ContextType from ContextItem
     */
    public ContextType getDefaultContextType(ContextItem item) {
        if (!isValid(item)) {
            return null;
        }
        for (Object obj : item.getContext()) {
            ContextType type = (ContextType) obj;
            if (type.getName().equals(item.getDefaultContext())) {
                return type;
            }
        }
        return null;
    }

    /*
     * get the name of ContextParameterType from ContextItem by name.
     */

    private ContextParameterType getContextParameterType(ContextItem item, ContextParameterType defaultContextParameterType,
            String typeName, boolean defaultType) {
        if (!isValid(item) || !isValid(defaultContextParameterType) || !isValid(typeName)) {
            return null;
        }
        if (defaultType) { // default ContextType
            typeName = item.getDefaultContext();
        }
        for (Object obj : item.getContext()) {
            ContextType type = (ContextType) obj;
            if (type.getName().equals(typeName)) {
                for (ContextParameterType param : (List<ContextParameterType>) type.getContextParameter()) {
                    if (param.getName().equals(defaultContextParameterType.getName())) {
                        return param;
                    }
                }
                break;
            }
        }

        return null;
    }

    /**
     * get the ContextItem from the name.
     */
    public ContextItem getContextItemByName(String name) {
        if (!isValid(name)) {
            return null;
        }
        Set<ContextItem> itemSet = getContextItems();
        for (ContextItem item : itemSet) {
            if (item.getProperty().getLabel().equals(name)) {
                return item;
            }
        }
        return null;

    }

    public boolean hasExistedVariableFromContextItem(ContextItem item, String paramName) {
        if (!isValid(item) || !isValid(paramName)) {
            return false;
        }
        ContextType type = getDefaultContextType(item);
        if (type != null) {
            for (ContextParameterType param : (List<ContextParameterType>) type.getContextParameter()) {
                if (param.getName().equals(paramName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * get parent objecte of the object
     */
    public Object getParentContextItem(Object obj) {
        if (!isValid(obj)) {
            return null;
        }
        if (obj instanceof ContextItem) {
            return null;
        }
        Set<ContextItem> itemSet = getContextItems();
        if (itemSet != null) {
            // for SelectRepositoryContextDialog
            if (obj instanceof ContextParameterType) {
                for (ContextItem contextItem : itemSet) {
                    for (Object objType : contextItem.getContext()) {
                        ContextType type = (ContextType) objType;
                        if (type.getName().equals(contextItem.getDefaultContext())) {
                            List paramList = type.getContextParameter();
                            if (paramList != null && paramList.contains(obj)) {
                                return contextItem;
                            }
                        }

                    }
                }
            }
            // for SelectRepositoryContextGroupDialog
            if (obj instanceof ContextType) {
                for (ContextItem contextItem : itemSet) {
                    if (contextItem.getContext().contains(obj)) {
                        return contextItem;
                    }
                }
            }
        }
        return null;
    }

    /*
     * get sibling objecte of the object(include self).
     */
    public Set getSiblingContextObject(Object obj) {
        if (!isValid(obj)) {
            return null;
        }
        Set<ContextItem> itemSet = getContextItems();
        if (itemSet != null) {
            if (obj instanceof ContextItem) {
                return itemSet;
            }
            // for SelectRepositoryContextDialog
            if (obj instanceof ContextParameterType) {
                for (ContextItem contextItem : getContextItems()) {
                    for (Object objType : contextItem.getContext()) {
                        ContextType type = (ContextType) objType;
                        if (type.getName().equals(contextItem.getDefaultContext())) {
                            List paramList = type.getContextParameter();
                            if (paramList != null && paramList.contains(obj)) {
                                return new HashSet(paramList);
                            }
                        }
                    }
                }
            }
            // for SelectRepositoryContextGroupDialog
            if (obj instanceof ContextType) {
                for (ContextItem contextItem : getContextItems()) {
                    if (contextItem.getContext().contains(obj)) {
                        return new HashSet(contextItem.getContext());
                    }

                }
            }
        }
        return null;
    }

    /*
     * get the have existed variables.
     */
    public IContextParameter getExistedContextParameter(String paramName) {
        if (!isValid(paramName) || !isValid(manager)) {
            return null;
        }
        return manager.getDefaultContext().getContextParameter(paramName);
    }

    /*
     * create the JobContextParameter form the contextParamType of contextItem.
     */
    private JobContextParameter createJobContextParameter(ContextItem contextItem, ContextParameterType contextParamType) {
        if (!isValid(contextItem) || !isValid(contextParamType)) {
            return null;
        }
        JobContextParameter contextParam = new JobContextParameter();

        contextParam.setName(contextParamType.getName());
        contextParam.setPrompt(contextParamType.getPrompt());
        boolean exists = false;
        ECodeLanguage curLanguage = LanguageManager.getCurrentLanguage();
        if (curLanguage == ECodeLanguage.JAVA) {
            exists = true;
            try {
                ContextParameterJavaTypeManager.getJavaTypeFromId(contextParamType.getType());
            } catch (IllegalArgumentException e) {
                exists = false;
            }
        } else {
            String[] existingTypes;
            existingTypes = ContextParameterJavaTypeManager.getPerlTypesLabels();
            for (int k = 0; k < existingTypes.length; k++) {
                if (existingTypes[k].equals(contextParamType.getType())) {
                    exists = true;
                }
            }
        }
        if (exists) {
            contextParam.setType(contextParamType.getType());
        } else {
            contextParam.setType(MetadataTalendType.getDefaultTalendType());
        }
        contextParam.setValue(contextParamType.getValue());
        contextParam.setPromptNeeded(contextParamType.isPromptNeeded());
        contextParam.setComment(contextParamType.getComment());
        contextParam.setSource(contextItem.getProperty().getLabel());
        return contextParam;
    }

    /**
     * 
     * DOC ggu Comment method "addContextParameterType".
     * 
     * @param defaultContextParameterType
     */
    public void addContextParameterType(ContextParameterType defaultContextParameterType) {
        if (!isValid(defaultContextParameterType) || !isValid(manager)) {
            return;
        }

        ContextItem item = (ContextItem) getParentContextItem(defaultContextParameterType);
        if (item == null) {
            return;
        }
        for (IContext context : manager.getListContext()) {
            ContextParameterType foundParam = getContextParameterType(item, defaultContextParameterType, context.getName(), false);
            if (foundParam == null) {
                // not found, set the default
                foundParam = getContextParameterType(item, defaultContextParameterType, context.getName(), true);
            }
            if (foundParam != null) {
                JobContextParameter jobParam = createJobContextParameter(item, foundParam);
                context.getContextParameterList().add(jobParam);
            }
        }
    }

    /**
     * 
     * ggu Comment method "existParameterForJob".
     * 
     * check that the obj is existed in job context.
     */
    public boolean existParameterForJob(Object obj) {
        if (!isValid(obj)) {
            return false;
        }
        if (obj instanceof ContextItem) {
            Set<String> paramSet = this.itemNameToParametersMap.get(((ContextItem) obj).getProperty().getLabel());
            return paramSet != null && !paramSet.isEmpty();
        } else if (obj instanceof ContextParameterType) {
            ContextItem contextItem = (ContextItem) getParentContextItem(obj);
            if (contextItem != null) {
                Set<String> paramSet = this.itemNameToParametersMap.get(contextItem.getProperty().getLabel());
                return paramSet != null && paramSet.contains(((ContextParameterType) obj).getName());
            }
        }
        return false;
    }

    /**
     * 
     * ggu Comment method "convertFormat".
     * 
     * @param contextParameterType
     * @return
     */
    public static String convertFormat(String contextParameterType) {
        String newType = null;

        final ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
        if (codeLanguage == ECodeLanguage.JAVA) {
            String[] values = ContextConstant.ITEMS;
            newType = contextParameterType.substring(3, contextParameterType.length());
            for (String format : values) {
                if (format.indexOf(ContextConstant.DOWNWARDS_STRING) != -1) {
                    String[] formats = format.split(ContextConstant.SPLIT_CHAR);
                    for (String aformat : formats) {
                        if (newType.trim().equals(aformat.trim())) {
                            return format;
                        }
                    }
                } else {
                    if (newType.trim().equals(format.trim())) {
                        return format;
                    }
                }
            }
        } else {
            String[] values = ContextParameterJavaTypeManager.getPerlTypesLabels();
            if ("".equals(contextParameterType)) { //$NON-NLS-1$
                newType = ""; //$NON-NLS-1$
            } else {
                newType = contextParameterType;
            }
            for (String format : values) {
                if (format.indexOf(ContextConstant.DOWNWARDS_STRING) != -1) {
                    String[] formats = format.split(ContextConstant.SPLIT_CHAR);
                    for (String aformat : formats) {
                        if (newType.trim().equals(aformat.trim())) {
                            return format;
                        }
                    }
                } else {
                    if (newType.trim().equals(format.trim())) {
                        return format;
                    }
                }
            }
        }
        return ""; //$NON-NLS-1$
    }

    /**
     * 
     * ggu Comment method "changeContextOrder".
     * 
     * order the context parameter
     */
    public static boolean changeContextOrder(TreeViewer viewer, IContextModelManager modelManager, boolean up) {
        if (viewer == null || modelManager == null) {
            return false;
        }
        final ISelection selection = viewer.getSelection();
        if (selection == null || selection.isEmpty()) {
            return false;
        }
        if (!(selection instanceof IStructuredSelection)) {
            return false;
        }
        IStructuredSelection sSection = (IStructuredSelection) selection;
        if (sSection.size() != 1) { // not support multi-selection
            return false;
        }

        Object element = sSection.getFirstElement();
        IContextParameter movedParam = null;
        if (element instanceof ContextParameterParent) {
            movedParam = ((ContextParameterParent) element).getParameter();
        }
        if (movedParam == null) {
            return false;
        }

        OrderContextParameterCommand orderCommand = new OrderContextParameterCommand(modelManager.getContextManager(),
                movedParam, up);
        final CommandStack commandStack = modelManager.getCommandStack();
        if (commandStack != null) {
            commandStack.execute(orderCommand);
        } else {
            orderCommand.execute();
        }
        //
        modelManager.refresh();

        revertTreeSelection(viewer, movedParam);

        return orderCommand.isExecution();
    }

    /**
     * 
     * revert selection according to parameter.
     */
    public static void revertTreeSelection(TreeViewer viewer, IContextParameter param) {
        if (viewer == null) {
            return;
        }
        final Tree tree = viewer.getTree();
        final TreeItem[] items = tree.getItems();

        TreeItem item = retrieveTreeItem(items, param);
        if (item == null) {
            if (items.length > 0) { // select first
                tree.setSelection(items[0]);
            }
        } else {
            tree.setSelection(item);
        }
    }

    private static TreeItem retrieveTreeItem(final TreeItem[] items, IContextParameter param) {
        if (items == null || param == null) {
            return null;
        }
        for (TreeItem item : items) {
            final Object data = item.getData();
            if (data != null) {
                if (data instanceof ContextParameterParent) {
                    ContextParameterParent parent = (ContextParameterParent) data;
                    if (parent.getParameter() != null && param.getName().equals(parent.getParameter().getName())) {
                        return item;
                    }
                } else if (data instanceof ContextParameterSortedSon) {
                    ContextParameterSortedSon son = (ContextParameterSortedSon) data;
                    if (son.getParameter() != null && param.getName().equals(son.getParameter().getName())) {
                        return item;
                    }
                }
            }
            // search child item
            TreeItem childItem = retrieveTreeItem(item.getItems(), param);
            if (childItem != null) {
                return childItem;
            }
        }
        return null;
    }

    /**
     * 
     * search before context parameter item.
     */
    public static IContextParameter getNearParameterBySelectionItem(final TreeViewer viewer) {
        if (viewer == null) {
            return null;
        }
        final Tree tree = viewer.getTree();
        TreeItem[] selection = tree.getSelection();

        final TreeItem[] items = tree.getItems();
        if (selection != null && selection.length == 1) {
            TreeItem movedItem = selection[0];
            Object data = movedItem.getData();
            if (data != null) {
                return searchNearItemParam(items, movedItem);

            }
        }
        return null;

    }

    /**
     * 
     * return the item of real context parameter.
     */
    private static IContextParameter searchNearItemParam(final TreeItem[] items, final TreeItem searchItem) {
        if (items == null || searchItem == null) {
            return null;
        }
        for (int i = items.length - 1; i >= 0; i--) {
            TreeItem item = items[i];
            IContextParameter param = searchNearItemParam(item.getItems(), searchItem);
            if (param != null) {
                return param;
            }
            //
            if (item == searchItem) {
                if (i > 0) { // before item
                    return searchEndItemParam(items[i - 1]);
                } else { // first in current
                    if (i + 1 < items.length) { // next item
                        return searchEndItemParam(items[i + 1]);
                    } else { // only one left
                        TreeItem parentItem = item.getParentItem();
                        if (parentItem != null) {
                            final Tree parentTree = item.getParent();
                            final int index = parentTree.indexOf(parentItem);
                            if (index > -1) { // in tree root
                                if (index > 0) {
                                    return searchEndItemParam(parentTree.getItem(index - 1));
                                } else { // is first
                                    return null;
                                }
                            } else { // not found
                                return null;

                            }
                        }
                    }
                }

            }
        }
        return null;
    }

    private static IContextParameter searchEndItemParam(final TreeItem item) {
        if (item == null) {
            return null;
        }
        TreeItem foundItem = item; // self
        final TreeItem[] items = item.getItems();
        if (items != null && items.length > 0) {
            foundItem = items[items.length - 1];

        }
        final Object data = foundItem.getData();

        if (data == null) {
            return null;
        } else if (data instanceof ContextParameterSortedSon) {
            return ((ContextParameterSortedSon) data).getParameter();
        } else if (data instanceof ContextParameterParent) {
            return ((ContextParameterParent) data).getParameter();
        }
        return null;
    }

    /**
     * 
     * ggu Comment method "checkAndSetDefaultValue".
     * 
     * if value is null or empty. will return the undef value (bug 4420).
     */
    public static void checkAndSetDefaultValue(final IContextParameter param) {
        if (param == null) {
            return;
        }
        final String value = param.getValue();
        final String type = param.getType();

        if (param.isBuiltIn() && (value == null || "".equals(value.trim()))) { //$NON-NLS-1$
            final ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
            switch (codeLanguage) {
            case JAVA:
                //
                break;
            case PERL:
            default:
                param.setValue("undef"); //$NON-NLS-1$
            }
        }
        return;
    }
}
