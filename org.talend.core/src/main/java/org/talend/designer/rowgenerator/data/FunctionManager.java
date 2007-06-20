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
package org.talend.designer.rowgenerator.data;

import java.util.ArrayList;
import java.util.List;

import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.i18n.Messages;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;

/**
 * class global comment. Detailled comment <br/>
 * 
 * $Id: FunctionManager.java,v 1.13 2007/01/31 05:20:51 pub Exp $
 * 
 */
public class FunctionManager {

    public static final String PURE_PERL_NAME = "...";

    public static final String PURE_PERL_DESC = Messages.getString("FunctionManager.PurePerl.Desc"); //$NON-NLS-1$

    public static final String PURE_PERL_PARAM = Messages.getString("FunctionManager.PurePerl.ParaName"); //$NON-NLS-1$

    private List<TalendType> talendTypes = null;

    public static final String PERL_FUN_PREFIX = "sub{";

    public static final String PERL_FUN_SUFFIX = ")}";

    public static final String FUN_PARAM_SEPARATED = ",";

    /**
     * qzhang Comment method "getFunctionByName".
     * 
     * @param name is TalendType name.
     * @return
     */
    @SuppressWarnings("unchecked")//$NON-NLS-1$
    public List<Function> getFunctionByName(String name) {
        List<Function> funtions = new ArrayList<Function>();

        for (TalendType talendType : talendTypes) {
            if (talendType.getName().equals(name)) {
                funtions.addAll(talendType.getFunctions());
            }
        }
        funtions.add(createCustomizeFunction());
        return funtions;
    }

    private Function createCustomizeFunction() {
        Function function = new Function();
        function.setName(PURE_PERL_NAME);
        function.setDescription(PURE_PERL_DESC);
        StringParameter param = new StringParameter();
        List<Parameter> params = new ArrayList<Parameter>();
        param.setName(PURE_PERL_PARAM);
        params.add(param);
        function.setParameters(params);
        return function;
    }

    public RepositoryContext getRepositoryContext() {
        Context ctx = CorePlugin.getContext();
        return (RepositoryContext) ctx.getProperty(Context.REPOSITORY_CONTEXT_KEY);
    }

    @SuppressWarnings("unchecked")
    public FunctionManager() {
        // this code move to FunctionParser .
        //        
        // for (int i = 0; i < list.size(); i++) {
        // URL url = list.get(i);
        // try {
        // url = FileLocator.toFileURL(url);
        // File file = new File(url.getFile());
        // files.add(file);
        // } catch (Exception e) {
        // ExceptionHandler.process(e);
        // }
        // }
        AbstractFunctionParser parser = null;
        if (isJavaProject()) {
            parser = new JavaFunctionParser();
        } else {
            parser = new PerlFunctionParser();
        }
        parser.parse();
        talendTypes = parser.getList();
    }


    public static boolean isJavaProject() {
        ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
        return (codeLanguage == ECodeLanguage.JAVA);
    }

    
    public List<TalendType> getTalendTypes() {
        return this.talendTypes;
    }
    

}
