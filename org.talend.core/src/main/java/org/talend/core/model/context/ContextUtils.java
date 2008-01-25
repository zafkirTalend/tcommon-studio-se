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
package org.talend.core.model.context;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.types.ContextParameterJavaTypeManager;
import org.talend.core.model.process.IContext;
import org.talend.core.model.process.IContextManager;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.properties.ContextItem;
import org.talend.designer.core.model.utils.emf.talendfile.ContextParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.ContextType;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class ContextUtils {

    /**
     * 
     * update the JobContextParameter form repository ContextItem by context name.
     * 
     */
    public static boolean updateParameterFromRepository(ContextItem sourceItem, IContextParameter contextParam, String contextName) {
        if (sourceItem == null || contextParam == null) {
            return false;
        }
        // not found, use default.
        ContextType contextType = getContextTypeByName(sourceItem, contextName, true);

        if (contextType != null) {
            ContextParameterType parameterType = getContextParameterTypeByName(contextType, contextParam.getName());
            // found parameter, update it.
            if (parameterType != null) {
                contextParam.setComment(parameterType.getComment());
                contextParam.setPrompt(parameterType.getPrompt());
                contextParam.setPromptNeeded(parameterType.isPromptNeeded());
                contextParam.setType(parameterType.getType());
                contextParam.setValue(parameterType.getValue());
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * get ContextType from the repository ContextItem by context name.
     * 
     * if not found, check the byDefault to return default context or not.
     * 
     * 
     */
    @SuppressWarnings("unchecked")
    public static ContextType getContextTypeByName(ContextItem sourceItem, final String contextName, boolean byDefault) {
        if (sourceItem == null) {
            return null;
        }
        List<ContextType> contextTypeList = (List<ContextType>) sourceItem.getContext();

        if (byDefault) {
            return getContextTypeByName(contextTypeList, contextName, sourceItem.getDefaultContext());
        }

        return getContextTypeByName(contextTypeList, contextName, null);
    }

    public static ContextType getContextTypeByName(List<ContextType> contextTypeList, final String contextName) {

        return getContextTypeByName(contextTypeList, contextName, null);

    }

    public static ContextType getContextTypeByName(List<ContextType> contextTypeList, final String contextName,
            final String defaultContextName) {
        if (checkObject(contextTypeList)) {
            return null;
        }
        if (checkObject(contextName) && checkObject(defaultContextName)) {
            return null;
        }
        ContextType contextType = null;
        ContextType defaultContextType = null;
        for (ContextType type : (List<ContextType>) contextTypeList) {
            if (contextName != null && type.getName().equals(contextName)) {
                contextType = type;
            } else if (defaultContextName != null && type.getName().equals(defaultContextName)) {
                defaultContextType = type;
            }
        }
        // not found the name of context, get the default context.
        if (contextType == null && defaultContextType != null) {
            contextType = defaultContextType;
        }
        return contextType;
    }

    /**
     * 
     * get ContextParameterType form a ContextType by parameter name.
     */
    @SuppressWarnings("unchecked")
    public static ContextParameterType getContextParameterTypeByName(ContextType contextType, final String paramName) {
        if (contextType == null || paramName == null) {
            return null;
        }

        ContextParameterType parameterType = null;
        for (ContextParameterType param : (List<ContextParameterType>) contextType.getContextParameter()) {
            if (param.getName().equals(paramName)) {
                parameterType = param;
                break;
            }
        }
        return parameterType;
    }

    @SuppressWarnings("unchecked")
    private static boolean checkObject(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof Collection) {
            Collection collection = (Collection) obj;
            if (collection.isEmpty()) {
                return true;
            }
        }
        if (obj instanceof String) {
            String string = (String) obj;
            if ("".equals(string.trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * get ContextItem by the id.
     */
    public static ContextItem getContextItemById(String contextId) {
        if (checkObject(contextId)) {
            return null;
        }

        List<ContextItem> contextItemList = getAllContextItem();

        return getContextItemById(contextItemList, contextId);
    }

    public static ContextItem getContextItemById(List<ContextItem> contextItemList, String contextId) {
        if (checkObject(contextItemList) || checkObject(contextId)) {
            return null;
        }
        for (ContextItem item : contextItemList) {
            String id = item.getProperty().getId();
            if (id.equals(contextId)) {
                return item;
            }
        }

        return null;
    }

    /**
     * 
     * get ContextItem by name.
     * 
     */
    public static ContextItem getContextItemByName(String name) {
        if (checkObject(name)) {
            return null;
        }
        List<ContextItem> contextItemList = getAllContextItem();

        return getContextItemByName(contextItemList, name);

    }

    public static ContextItem getContextItemByName(List<ContextItem> contextItemList, String name) {
        if (checkObject(contextItemList) || checkObject(name)) {
            return null;
        }
        for (ContextItem item : contextItemList) {
            if (item.getProperty().getLabel().equals(name)) {
                return item;
            }
        }

        return null;
    }

    /**
     * 
     * get all of repository ContextItem(not include deleted item).
     * 
     */
    public static List<ContextItem> getAllContextItem() {
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        List<ContextItem> contextItemList = null;
        try {
            contextItemList = factory.getContextItem();
        } catch (PersistenceException e) {
            return null;
        }
        return contextItemList;
    }

    /**
     * 
     * get JobContext from ContextManager by name.
     * 
     * if not found, check the byDefault to return default context or not.
     */
    public static IContext getContextByName(IContextManager contextManager, final String contextName, boolean byDefault) {
        if (checkObject(contextManager)) {
            return null;
        }
        if (contextName != null) {
            if (byDefault) {
                return contextManager.getContext(contextName);
            } else {
                for (IContext context : contextManager.getListContext()) {
                    if (context.getName().equals(contextName)) {
                        return context;
                    }
                }
            }
        }

        return null;
    }

    /**
     * 
     * update the JobContextParameter form the ContextParameterType.
     */
    public static void updateParameter(ContextParameterType sourceParam, IContextParameter targetParam) {
        if (checkObject(sourceParam) || checkObject(targetParam)) {
            return;
        }

        targetParam.setName(sourceParam.getName());
        targetParam.setPrompt(sourceParam.getPrompt());
        boolean exists = false;
        ECodeLanguage curLanguage = LanguageManager.getCurrentLanguage();
        if (curLanguage == ECodeLanguage.JAVA) {
            exists = true;
            try {
                ContextParameterJavaTypeManager.getJavaTypeFromId(sourceParam.getType());
            } catch (IllegalArgumentException e) {
                exists = false;
            }
        } else {
            String[] existingTypes;
            existingTypes = ContextParameterJavaTypeManager.getPerlTypesLabels();
            for (int k = 0; k < existingTypes.length; k++) {
                if (existingTypes[k].equals(sourceParam.getType())) {
                    exists = true;
                }
            }
        }
        if (exists) {
            targetParam.setType(sourceParam.getType());
        } else {
            targetParam.setType(MetadataTalendType.getDefaultTalendType());
        }
        targetParam.setValue(sourceParam.getValue());
        targetParam.setPromptNeeded(sourceParam.isPromptNeeded());
        targetParam.setComment(sourceParam.getComment());

    }

    public static Map<String, ContextItem> getRepositoryContextItemIdMapping() {
        List<ContextItem> contextItemList = getAllContextItem();

        if (checkObject(contextItemList)) {
            return Collections.emptyMap();
        }

        Map<String, ContextItem> itemMap = new HashMap<String, ContextItem>();
        for (ContextItem item : contextItemList) {
            itemMap.put(item.getProperty().getId(), item);
        }
        return itemMap;
    }
}
