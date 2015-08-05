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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.jface.fieldassist.IContentProposal;
import org.talend.commons.utils.generation.JavaUtils;
import org.talend.core.i18n.Messages;
import org.talend.core.model.context.ContextUtils;
import org.talend.core.model.context.JobContextManager;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.properties.ContextItem;
import org.talend.designer.rowgenerator.data.Function;
import org.talend.designer.rowgenerator.data.FunctionManager;
import org.talend.designer.rowgenerator.data.TalendType;

/**
 * DOC hcyi class global comment. Detailled comment
 */
public class PigProposalProvider extends TalendProposalProvider {

    public PigProposalProvider(IProcess process, INode node) {
        super();
        this.currentNode = node;
        this.process = process;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.fieldassist.IContentProposalProvider#getProposals(java.lang.String, int)
     */
    @Override
    public IContentProposal[] getProposals(String contents, int position) {
        List<IContentProposal> proposals = new ArrayList<IContentProposal>();
        if (process != null) {
            // Proposals based on process context
            List<IContextParameter> ctxParams = process.getContextManager().getDefaultContext().getContextParameterList();
            for (IContextParameter ctxParam : ctxParams) {
                proposals.add(new ContextParameterProposal(ctxParam));
            }
        } else {
            List<ContextItem> allContextItem = ContextUtils.getAllContextItem();
            List<IContextParameter> ctxParams = new ArrayList<IContextParameter>();
            if (allContextItem != null) {
                for (ContextItem item : allContextItem) {
                    List<IContextParameter> tmpParams = new JobContextManager(item.getContext(), item.getDefaultContext())
                            .getDefaultContext().getContextParameterList();
                    ctxParams.addAll(tmpParams);
                }
            }
            for (IContextParameter ctxParam : ctxParams) {
                proposals.add(new ContextParameterProposal(ctxParam));
            }
        }

        // Proposals based pig functions
        FunctionManager functionManager = new FunctionManager(JavaUtils.JAVA_PIG_DIRECTORY);
        List<TalendType> talendTypes = functionManager.getTalendTypes();
        for (TalendType type : talendTypes) {
            for (Object objectFunction : type.getFunctions()) {
                Function function = (Function) objectFunction;
                // pig components
                if (currentNode != null) {
                    if (currentNode.getComponent().getName().equals("tPigLoad") && "LoadFunc".equals(function.getPreview())) {
                        if (function.isUserDefined()) {
                            proposals.add(new PigFunctionProposal(function, JavaUtils.JAVA_PIG_DIRECTORY));
                        }
                    } else if (currentNode.getComponent().getName().equals("tPigStoreResult")
                            && "StoreFunc".equals(function.getPreview())) {
                        if (function.isUserDefined()) {
                            proposals.add(new PigFunctionProposal(function, JavaUtils.JAVA_PIG_DIRECTORY));
                        }
                    } else if (currentNode.getComponent().getName().equals("tPigMap")
                            && !"StoreFunc".equals(function.getPreview()) && !"LoadFunc".equals(function.getPreview())) {
                        proposals.add(new PigFunctionProposal(function, JavaUtils.JAVA_PIG_DIRECTORY));
                    }
                } else if (!"StoreFunc".equals(function.getPreview()) && !"LoadFunc".equals(function.getPreview())) {
                    proposals.add(new PigFunctionProposal(function, JavaUtils.JAVA_PIG_DIRECTORY));
                }
            }
        }

        for (IExternalProposals externalProposals : ProposalFactory.getInstances()) {
            proposals.addAll(externalProposals.getStandardProposals());
        }

        // sort the list
        Collections.sort(proposals, new Comparator<IContentProposal>() {

            public int compare(IContentProposal arg0, IContentProposal arg1) {
                return compareRowAndContextProposal(arg0.getLabel(), arg1.getLabel());
            }
        });

        // set a default function avoid NPE
        if (proposals.size() == 0) {
            Function function = new Function();
            function.setClassName("NONE");
            function.setName("NONE");
            function.setDescription(Messages.getString("PigProposalProvider.defaultUDFDescription"));
            function.setCategory("Pig UDF");
            function.setUserDefined(true);
            TalendType talendType = new TalendType();
            talendType.setName("id_String");
            function.setTalendType(talendType);
            proposals.add(new PigFunctionProposal(function, JavaUtils.JAVA_PIG_DIRECTORY));
        }
        IContentProposal[] res = new IContentProposal[proposals.size()];
        res = proposals.toArray(res);
        return res;
    }

    /**
     * 
     * DOC hcyi PigProposalProvider class global comment. Detailled comment
     */
    class PigFunctionProposal extends RoutinesFunctionProposal {

        public PigFunctionProposal(Function function, String type) {
            super();
            this.function = function;
            if (function != null && JavaUtils.JAVA_PIG_DIRECTORY.equals(type)) {
                if (function.isUserDefined()) {
                    method = JavaUtils.JAVA_PIGUDF_DIRECTORY + "." + function.getName() + "()";
                } else {
                    method = function.getName() + "()";
                }
            }
        }
    }
}
