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
package org.talend.core.ui.context;

import java.util.List;

import org.talend.commons.ui.swt.extended.table.ExtendedTableModel;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.context.JobContextParameter;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.types.ContextParameterJavaTypeManager;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.utils.TalendTextUtils;

/**
 * DOC cantoine class global comment. Detailled comment <br/>
 * 
 * $Id: XmlExtractorSchemaModel.java 949 2006-12-11 15:03:40Z cantoine $
 * 
 */
public class ContextTemplateModel extends ExtendedTableModel<IContextParameter> {

    public static final String NEW_PARAM_NAME = "new"; //$NON-NLS-1$

    public ContextTemplateModel() {
        super();
        setUseEquals(true);
    }

    /**
     * DOC bqian Comment method "createNewEntry".
     * 
     * @return
     */
    public Object createNewEntry() {

        List<IContextParameter> listParams = this.getBeansList();
        Integer numParam = new Integer(1);
        boolean paramNameFound;
        String paramName = null;
        do { // look for a new name
            paramNameFound = true;
            paramName = NEW_PARAM_NAME + numParam;
            for (int i = 0; i < listParams.size() && paramNameFound; i++) {
                if (paramName.equals(listParams.get(i).getName())) {
                    paramNameFound = false;
                }
            }
            if (!paramNameFound) {
                numParam++;
            }
        } while (!paramNameFound);

        JobContextParameter contextParam = new JobContextParameter();
        contextParam.setName(paramName);
        ECodeLanguage curLanguage = LanguageManager.getCurrentLanguage();
        if (curLanguage == ECodeLanguage.JAVA) {
            contextParam.setType(ContextParameterJavaTypeManager.getDefaultJavaType().getId());
        } else {
            contextParam.setType(MetadataTalendType.getDefaultTalendType());
        }
        contextParam.setPrompt(paramName + "?"); //$NON-NLS-1$
        String defaultValue;
        if (curLanguage == ECodeLanguage.JAVA) {
            defaultValue = ContextParameterJavaTypeManager.getDefaultValueFromJavaIdType(ContextParameterJavaTypeManager
                    .getDefaultJavaType().getId(), false);
        } else {
            defaultValue = TalendTextUtils.addQuotes(""); //$NON-NLS-1$
        }
        contextParam.setValue(defaultValue);
        contextParam.setComment(""); //$NON-NLS-1$
        return contextParam;
    }
}
