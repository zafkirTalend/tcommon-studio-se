// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.core.model.context;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.EList;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.types.ContextParameterJavaTypeManager;
import org.talend.core.model.process.IContext;
import org.talend.core.model.process.IContextListener;
import org.talend.core.model.process.IContextManager;
import org.talend.core.model.process.IContextParameter;
import org.talend.designer.core.model.utils.emf.talendfile.ContextParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.ContextType;
import org.talend.designer.core.model.utils.emf.talendfile.TalendFileFactory;
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

        // TODO SML/NRO See with nrousseau if this new way to check name may cause some troubles

        // Perl5Matcher matcher = new Perl5Matcher();
        // Perl5Compiler compiler = new Perl5Compiler();
        // Pattern pattern;
        //
        // switch (language) {
        // case PERL:
        // try {
        // pattern = compiler.compile("^[A-Za-z_][A-Za-z0-9_]*$"); //$NON-NLS-1$
        // if (!matcher.matches(contextName, pattern)) {
        // return false;
        // }
        // } catch (MalformedPatternException e) {
        // throw new RuntimeException(e);
        // }
        // default:
        // }
        //
        // return true;
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
}
