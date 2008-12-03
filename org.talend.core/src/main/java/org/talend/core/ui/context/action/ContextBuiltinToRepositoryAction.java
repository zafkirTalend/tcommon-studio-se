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
package org.talend.core.ui.context.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.talend.core.CorePlugin;
import org.talend.core.i18n.Messages;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.ui.context.model.template.ContextParameterParent;
import org.talend.core.ui.context.model.template.ContextParameterSortedParent;
import org.talend.core.ui.context.model.template.ContextParameterSortedSon;
import org.talend.repository.ui.actions.AContextualAction;

/**
 * DOC xye class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ææäº, 29 ä¹æ 2006) nrousseau $
 * 
 */
public class ContextBuiltinToRepositoryAction extends AContextualAction {

    public final static String ID = "org.talend.core.ui.context.actions.ContextBuiltinToRepositoryAction";

    private TreeViewer viewer = null;

    private List<IContextParameter> params = new ArrayList<IContextParameter>();

    private static final String LABEL = Messages.getString("ConextTemplateComposite.addToRepositoryContextAction.label"); //$NON-NLS-1$

    public ContextBuiltinToRepositoryAction() {
        super();
        this.setText(LABEL);
        this.setToolTipText(LABEL);
    }

    @Override
    public void run() {
        CorePlugin.getDefault().getRepositoryService().openRepositoryReviewDialog(ERepositoryObjectType.CONTEXT, null, params);
        viewer.refresh();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.actions.ITreeContextualAction#init(org.eclipse.jface.viewers.TreeViewer,
     * org.eclipse.jface.viewers.IStructuredSelection)
     */
    public void init(TreeViewer viewer, IStructuredSelection selection) {
        this.viewer = viewer;

        boolean canWork = viewer != null && selection != null && selection.size() > 0;
        if (canWork) {
            for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
                Object object = iter.next();
                if (object instanceof ContextParameterSortedParent) {
                    ContextParameterSortedParent param = (ContextParameterSortedParent) object;
                    if (!IContextParameter.BUILT_IN.equals(param.getSourceName())) {
                        setEnabled(false);
                        return;
                    } else {
                        params.add(param.getParameter());
                    }
                } else if (object instanceof ContextParameterSortedSon) {
                    ContextParameterSortedSon param = (ContextParameterSortedSon) object;
                    if (!IContextParameter.BUILT_IN.equals(param.getParameter().getSource())) {
                        setEnabled(false);
                        return;
                    } else {
                        params.add(param.getParameter());
                    }
                } else if (object instanceof ContextParameterParent) {
                    ContextParameterParent param = (ContextParameterParent) object;
                    if (!IContextParameter.BUILT_IN.equals(param.getParameter().getSource())) {
                        setEnabled(false);
                        return;
                    } else {
                        params.add(param.getParameter());
                    }
                }
            }
        }
        setEnabled(canWork);
    }

}
