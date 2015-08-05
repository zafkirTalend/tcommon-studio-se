// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.ui.proposal;

import java.text.MessageFormat;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.talend.core.i18n.Messages;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.utils.ContextParameterUtils;
import org.talend.repository.ProjectManager;

/**
 * Content proposal based on a IContextParameter. <br/>
 * 
 * $Id$
 * 
 */
public class ContextParameterProposal implements IContentProposal {

    private IContextParameter contextParameter;

    /**
     * Constructs a new ContextParameterProposal.
     */
    public ContextParameterProposal(IContextParameter contextParameter) {
        super();

        this.contextParameter = contextParameter;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.fieldassist.IContentProposal#getContent()
     */
    public String getContent() {

        ECodeLanguage language = ProjectManager.getInstance().getCurrentProject().getLanguage();
        return ContextParameterUtils.getNewScriptCode(contextParameter.getName(), language);
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
        String desc = new String();
        if (!StringUtils.isEmpty(contextParameter.getComment())) {
            desc = contextParameter.getComment();
        } else {
            desc = Messages.getString("ContextParameterProposal.NoCommentAvaiable"); //$NON-NLS-1$
        }

        String message = Messages.getString("ContextParameterProposal.Description"); //$NON-NLS-1$
        message += Messages.getString("ContextParameterProposal.ContextVariable"); //$NON-NLS-1$
        message += Messages.getString("ContextParameterProposal.Type"); //$NON-NLS-1$
        message += Messages.getString("ContextParameterProposal.VariableName"); //$NON-NLS-1$

        MessageFormat format = new MessageFormat(message);
        Object[] args = new Object[] { desc, contextParameter.getType(), getContent() };
        return format.format(args);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.fieldassist.IContentProposal#getLabel()
     */
    public String getLabel() {
        return ContextParameterUtils.JAVA_NEW_CONTEXT_PREFIX + contextParameter.getName();
    }

}
