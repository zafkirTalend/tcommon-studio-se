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
import org.talend.designer.core.model.utils.emf.talendfile.ContextParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.ContextType;
import org.talend.repository.model.ERepositoryStatus;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * DOC ggu class global comment. Detailled comment
 */
public final class ContextManagerHelper {

    private Map<ContextItem, List<ContextParameterType>> contextMap;

    IContextManager manager;

    public ContextManagerHelper(IContextManager manager) {
        super();
        initHelper(manager);
    }

    public void initHelper(IContextManager manager) {
        this.manager = manager;
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

    private boolean isValid(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if ("".equals(str)) {
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
        Set<ContextItem> itemSet = contextMap.keySet();
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

}
