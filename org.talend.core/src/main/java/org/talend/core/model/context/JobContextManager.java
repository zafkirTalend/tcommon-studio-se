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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.EList;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.types.ContextParameterJavaTypeManager;
import org.talend.core.model.process.IContext;
import org.talend.core.model.process.IContextListener;
import org.talend.core.model.process.IContextManager;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.properties.ContextItem;
import org.talend.designer.core.model.utils.emf.talendfile.ContextParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.ContextType;
import org.talend.designer.core.model.utils.emf.talendfile.TalendFileFactory;
import org.talend.repository.model.ERepositoryStatus;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.RepositoryConstants;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class JobContextManager implements IContextManager {

    private IContext defaultContext = new JobContext(IContext.DEFAULT);

    private List<IContext> listContext = new ArrayList<IContext>();

    private List<IContextListener> contextListenerList = new ArrayList<IContextListener>();

    private Map<String, String> nameMap = new HashMap<String, String>();

    public JobContextManager() {
        listContext.add(defaultContext);
    }

    public JobContextManager(EList contextTypeList, String defaultContextName) {
        loadFromEmf(contextTypeList, defaultContextName);
    }

    public void addContextListener(IContextListener listener) {
        contextListenerList.add(listener);
    }

    public void removeContextListener(IContextListener listener) {
        contextListenerList.remove(listener);
    }

    public void fireContextsChangedEvent() {
        for (IContextListener contextListener : contextListenerList) {
            contextListener.contextsChanged();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.process.IContextManager#getDefaultContext()
     */
    public IContext getDefaultContext() {
        return defaultContext;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.process.IContextManager#setDefaultContext(org.talend.core.model.process.IContext)
     */
    public void setDefaultContext(IContext context) {
        defaultContext = context;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.process.IContextManager#getListContext()
     */
    public List<IContext> getListContext() {
        return listContext;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.process.IContextManager#getListContext(java.util.List)
     */
    public void setListContext(List<IContext> listContext) {
        this.listContext = listContext;
    }

    /**
     * Check if the given name will be unique in the process. If another link already exists with that name, false will
     * be returned.
     * 
     * @param uniqueName
     * @return true if the name is unique
     */
    public boolean checkValidParameterName(String contextName) {
        for (IContextParameter contextParameter : listContext.get(0).getContextParameterList()) {
            if (contextParameter.getName().equals(contextName)) {
                return false;
            }
        }

        return Pattern.matches(RepositoryConstants.CONTEXT_AND_VARIABLE_PATTERN, contextName);
    }

    public IContext getContext(String name) {
        for (int i = 0; i < listContext.size(); i++) {
            if (listContext.get(i).getName().equals(name)) {
                return listContext.get(i);
            }
        }
        return defaultContext;
    }

    public void saveToEmf(EList contextTypeList) {
        ContextType contextType;
        IContext context;

        EList contextTypeParamList;
        ContextParameterType contextParamType;
        IContextParameter contextParam;

        for (int i = 0; i < listContext.size(); i++) {
            contextType = TalendFileFactory.eINSTANCE.createContextType();
            context = listContext.get(i);
            contextType.setName(context.getName());
            contextType.setConfirmationNeeded(context.isConfirmationNeeded());
            contextTypeParamList = contextType.getContextParameter();

            if (context.getContextParameterList() != null) {
                for (int j = 0; j < context.getContextParameterList().size(); j++) {
                    contextParamType = TalendFileFactory.eINSTANCE.createContextParameterType();
                    contextParam = context.getContextParameterList().get(j);
                    contextParamType.setName(contextParam.getName());
                    contextParamType.setPrompt(contextParam.getPrompt());
                    contextParamType.setType(contextParam.getType());
                    contextParamType.setValue(contextParam.getValue());
                    contextParamType.setPromptNeeded(contextParam.isPromptNeeded());
                    contextParamType.setComment(contextParam.getComment());
                    if (!contextParam.isBuiltIn()) {
                        String id = getContexIdFromName(contextParam.getSource());
                        if (id != null) {
                            contextParamType.setRepositoryContextId(id);
                        }
                    }
                    contextTypeParamList.add(contextParamType);
                }
            }
            contextTypeList.add(contextType);
        }
    }

    public void loadFromEmf(EList contextTypeList, String defaultContextName) {
        IContext context;
        ContextType contextType;
        List<IContextParameter> contextParamList;
        EList contextTypeParamList;
        ContextParameterType contextParamType;
        JobContextParameter contextParam;

        listContext.clear();

        for (int i = 0; i < contextTypeList.size(); i++) {
            contextType = (ContextType) contextTypeList.get(i);
            context = new JobContext(contextType.getName());
            context.setConfirmationNeeded(contextType.isConfirmationNeeded());
            contextParamList = new ArrayList<IContextParameter>();
            contextTypeParamList = contextType.getContextParameter();

            for (int j = 0; j < contextTypeParamList.size(); j++) {
                contextParamType = (ContextParameterType) contextTypeParamList.get(j);
                contextParam = new JobContextParameter();
                contextParam.setContext(context);
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
                String name = getContextNameFromId(contextParamType.getRepositoryContextId());
                if (name == null) {
                    name = IContextParameter.BUILT_IN;
                } else {
                    if (!isFound(name, contextParamType.getName())) {
                        name = IContextParameter.BUILT_IN;
                    }
                }
                contextParam.setSource(name);
                contextParamList.add(contextParam);
            }
            context.setContextParameterList(contextParamList);

            if (context.getName().equals(defaultContextName)) {
                setDefaultContext(context);
            }
            listContext.add(context);
        }
    }

    public boolean sameAs(IContextManager contextManager) {
        if (!contextManager.getDefaultContext().getName().equals(defaultContext.getName())) {
            return false;
        }
        if (listContext.size() != contextManager.getListContext().size()) {
            return false;
        }
        for (int i = 0; i < listContext.size(); i++) {
            IContext curContext = listContext.get(i);
            IContext testContext = contextManager.getListContext().get(i);
            if (!curContext.sameAs(testContext)) {
                return false;
            }
        }
        return true;
    }

    public String getContexIdFromName(String contextName) {
        if (contextName == null || "".equals(contextName)) {
            return null;
        }
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        List<ContextItem> contextItemList = null;
        try {
            contextItemList = factory.getContextItem();
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }
        if (contextItemList != null) {
            for (ContextItem item : contextItemList) {
                if (factory.getStatus(item) != ERepositoryStatus.DELETED) {
                    String name = item.getProperty().getLabel();
                    if (name.equals(contextName)) {
                        return item.getProperty().getId();
                    }
                }
            }
        }
        return null;
    }

    public String getContextNameFromId(String contextId) {
        if (contextId == null || "".equals(contextId)) {
            return null;
        }
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        List<ContextItem> contextItemList = null;
        try {
            contextItemList = factory.getContextItem();
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }
        if (contextItemList != null) {
            for (ContextItem item : contextItemList) {
                if (factory.getStatus(item) != ERepositoryStatus.DELETED) {
                    String id = item.getProperty().getId();
                    if (id.equals(contextId)) {
                        return item.getProperty().getLabel();
                    }
                }
            }
        }
        return null;
    }

    public ContextItem getContextItemByName(String name) {
        if (name == null) {
            return null;
        }
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        List<ContextItem> contextItemList = null;
        try {
            contextItemList = factory.getContextItem();
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }
        if (contextItemList != null) {
            for (ContextItem item : contextItemList) {
                if (item.getProperty().getLabel().equals(name)) {
                    return item;
                }
            }
        }
        return null;

    }

    public boolean isFound(String itemName, String paramName) {
        if (paramName == null || itemName == null) {
            return false;
        }
        ContextItem item = getContextItemByName(itemName);
        if (item != null) {
            for (Object obj : ((ContextType) item.getContext().get(0)).getContextParameter()) {
                ContextParameterType paramType = (ContextParameterType) obj;
                if (paramType.getName().equals(paramName)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void addNewName(String newName, String oldName) {
        String name = nameMap.get(oldName);
        if (name != null) {
            nameMap.remove(oldName);
            nameMap.put(newName, name);
        } else {
            nameMap.put(newName, oldName);
        }
    }

    public Map<String, String> getNameMap() {
        return nameMap;
    }
}
