// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
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
        IContentProposal[] cp = new IContentProposal[] { new PerlGlobalVariableProposal("father_pid", "Father PID"), //$NON-NLS-1$ //$NON-NLS-2$
                new PerlGlobalVariableProposal("pid", "PID"), new PerlGlobalVariableProposal("job_name", "Job Name"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
                new PerlGlobalVariableProposal("job_version", "Job Version"), //$NON-NLS-1$ //$NON-NLS-2$
                new PerlGlobalVariableProposal("system_pid", "System PID"), //$NON-NLS-1$ //$NON-NLS-2$
                new PerlGlobalVariableProposal("project_name", "Project Name") }; //$NON-NLS-1$ //$NON-NLS-2$
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

            this.code = "$_globals{" + name + "}"; //$NON-NLS-1$ //$NON-NLS-2$
            this.display = "global." + name; //$NON-NLS-1$

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
