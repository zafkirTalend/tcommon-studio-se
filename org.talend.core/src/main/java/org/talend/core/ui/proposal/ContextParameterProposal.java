// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
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
package org.talend.core.ui.proposal;

import java.text.MessageFormat;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.i18n.Messages;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.utils.ContextParameterUtils;

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
        RepositoryContext repositoryContext = (RepositoryContext) CorePlugin.getContext().getProperty(
                Context.REPOSITORY_CONTEXT_KEY);
        ECodeLanguage language = repositoryContext.getProject().getLanguage();
        return ContextParameterUtils.getScriptCode(contextParameter, language);
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
        
        String message = "Description: {0}";
        message += "\n\nContext variable.";
        message += "\nType: {1}";
        message += "\n\nVariable Name: {2}";

        MessageFormat format = new MessageFormat(message);
        Object[] args = new Object[] { desc, contextParameter.getType().getDisplayName(), getContent() };
        return format.format(args);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.fieldassist.IContentProposal#getLabel()
     */
    public String getLabel() {
        return "context." + contextParameter.getName();
    }

}
