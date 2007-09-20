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
package org.talend.core.ui.proposal;

import java.text.MessageFormat;

import org.eclipse.jface.fieldassist.IContentProposal;
import org.talend.core.i18n.Messages;

/**
 * DOC ggu class global comment. Detailled comment <br/>
 * 
 */
public final class PerlGlobalUtils {

    // private static String[] vars = { "father_pid", "pid", "job_name", "job_version", "system_pid", "project_name",
    // "job_execution_datetime", };

    public static IContentProposal[] getProposals() {
        IContentProposal[] cp = new IContentProposal[] { new PerlGlobalVariableProposal("father_pid", "Father PID"),
                new PerlGlobalVariableProposal("pid", "PID"), new PerlGlobalVariableProposal("job_name", "Job Name"),
                new PerlGlobalVariableProposal("job_version", "Job Version"),
                new PerlGlobalVariableProposal("system_pid", "System PID"),
                new PerlGlobalVariableProposal("project_name", "Project Name") };
        return cp;
    }

    /**
     * 
     * DOC ggu PerlGlobalUtils class global comment. Detailled comment <br/>
     * 
     */
    static class PerlGlobalVariableProposal implements IContentProposal {

        private String name;

        private String desc;

        private String code;

        private String display;

        public PerlGlobalVariableProposal(String name, String desc) {

            this.name = name;
            this.desc = desc;

            this.code = "$_globals{" + name + "}";
            this.display = "global." + name;

        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.fieldassist.IContentProposal#getContent()
         */
        public String getContent() {

            return code;
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

            String message = Messages.getString("PerlGlobalVariableProposal.Description"); //$NON-NLS-1$
            message += Messages.getString("PerlGlobalVariableProposal.VariableName"); //$NON-NLS-1$

            MessageFormat format = new MessageFormat(message);
            Object[] args = new Object[] { desc, code };
            return format.format(args);
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.fieldassist.IContentProposal#getLabel()
         */
        public String getLabel() {

            return display;
        }

    }

}
