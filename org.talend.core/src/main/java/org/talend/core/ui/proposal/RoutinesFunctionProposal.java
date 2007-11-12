// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the  agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//   
// ============================================================================
package org.talend.core.ui.proposal;

import java.text.MessageFormat;

import org.eclipse.jface.fieldassist.IContentProposal;
import org.talend.core.i18n.Messages;
import org.talend.designer.rowgenerator.data.Function;
import org.talend.designer.rowgenerator.data.FunctionManager;

/**
 * DOC ggu class global comment. Detailled comment <br/>
 * 
 */
public class RoutinesFunctionProposal implements IContentProposal {

    private Function function;

    private String method = "";

    /**
     * DOC ggu RoutinesProposal constructor comment.
     * 
     * @param function
     */
    public RoutinesFunctionProposal(Function function) {
        super();
        this.function = function;
        if (function != null) {
            this.method = FunctionManager.getFunctionMethod(function);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.fieldassist.IContentProposal#getContent()
     */
    public String getContent() {
        return method;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.fieldassist.IContentProposal#getCursorPosition()
     */
    public int getCursorPosition() {

        return getContent().length();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.fieldassist.IContentProposal#getDescription()
     */
    public String getDescription() {
        String message = Messages.getString("RoutinesFunctionProposal.ReturnType"); //$NON-NLS-1$
        message += Messages.getString("RoutinesFunctionProposal.isUserDefined"); //$NON-NLS-1$
        message += Messages.getString("RoutinesFunctionProposal.VariableName"); //$NON-NLS-1$
        message += Messages.getString("RoutinesFunctionProposal.Description"); //$NON-NLS-1$

        MessageFormat format = new MessageFormat(message);
        Object[] args = new Object[] { function.getTalendType().getName(),
                function.isUserDefined() ? "User Defined" : "System Defined", method, function.getDescription() };
        return format.format(args);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.fieldassist.IContentProposal#getLabel()
     */
    public String getLabel() {

        return function.getCategory() + "." + function.getName();
    }

}
