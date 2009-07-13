// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.webservice.ui.tree;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.talend.designer.webservice.ws.wsdlinfo.ParameterInfo;

/**
 * DOC Administrator class global comment. Detailled comment
 */
public class WebServiceTreeLabelProvider extends LabelProvider implements ILabelProvider {

    @Override
    public String getText(Object element) {
        if (element instanceof ParameterInfo) {
            ParameterInfo para = (ParameterInfo) element;
            if (para.getArraySize() != 0) {
                int size = para.getArraySize();
                if (size == -1) {
                    return para.getName() + "(Array:size" + " infinite)";
                } else {
                    return para.getName() + "(Array:size=" + size + ")";
                }

            } else if (para.getParameterInfos().size() > 0) {
                return para.getName() + "(List)";
            } else {
                return para.getName();
            }

        }
        return super.getText(element);
    }

}
