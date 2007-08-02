// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend – www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
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
package org.talend.core.model.components.conversions;

import org.eclipse.emf.common.util.EList;
import org.talend.core.model.components.ComponentUtilities;
import org.talend.designer.core.model.utils.emf.talendfile.ContextParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.ContextType;
import org.talend.designer.core.model.utils.emf.talendfile.ElementParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.ElementValueType;
import org.talend.designer.core.model.utils.emf.talendfile.NodeType;
import org.talend.designer.core.model.utils.emf.talendfile.TalendFileFactory;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class MoveSpecialParameterToContextFortRunJobConversion implements IComponentConversion {

    private static final String NEW_PARAM_NAME = "new"; //$NON-NLS-1$

    private static final String NEW_PARAM_TYPE = "id_String"; //$NON-NLS-1$

    private static final String SPCIALSTRING = "context.getProperty"; //$NON-NLS-1$

    private String propertyToMove;

    public MoveSpecialParameterToContextFortRunJobConversion(String propertyToMove) {
        super();
        this.propertyToMove = propertyToMove;
    }

    public void transform(NodeType node) {
        ElementParameterType elementParameterType = ComponentUtilities.getNodeProperty(node, propertyToMove);
        ContextType currentContextType = ComponentUtilities.getNodeCurrentContextType(node);
        EList contextParameter = currentContextType.getContextParameter();

        if (elementParameterType != null) {
            EList elementValueTypes = elementParameterType.getElementValue();

            if (elementValueTypes != null && elementValueTypes.size() > 0) {

                for (Object elementValue : elementValueTypes) {
                    ElementValueType elementValueType = (ElementValueType) elementValue;
                    String oldValue = elementValueType.getValue();
                    if (oldValue != null && oldValue.contains(SPCIALSTRING)) {
                        // add a context parameter, and modify the old value in table column
                        ContextParameterType contextParameterNew = TalendFileFactory.eINSTANCE.createContextParameterType();
                        String newName = generateNewContextParameterName(currentContextType);
                        contextParameterNew.setName(newName);
                        contextParameterNew.setValue(oldValue);
                        contextParameterNew.setType(NEW_PARAM_TYPE);
                        contextParameterNew.setPromptNeeded(false);
                        contextParameterNew.setPrompt(newName);
                        contextParameter.add(contextParameterNew);

                        elementValueType.setValue("(String)((String)context.getProperty(\"" + newName + "\"))"); //$NON-NLS-1$ //$NON-NLS-2$
                    }
                }
            }
        }
    }

    private String generateNewContextParameterName(ContextType currentContextType) {
        EList contextParameter = currentContextType.getContextParameter();
        Integer numParam = new Integer(1);
        boolean paramNameFound;
        String paramName = null;
        do { // look for a new name
            paramNameFound = true;
            paramName = NEW_PARAM_NAME + numParam;
            for (int i = 0; i < contextParameter.size() && paramNameFound; i++) {
                if (paramName.equals(((ContextParameterType) (contextParameter.get(i))).getName())) {
                    paramNameFound = false;
                }
            }
            if (!paramNameFound) {
                numParam++;
            }
        } while (!paramNameFound);

        return paramName;

    }
}
