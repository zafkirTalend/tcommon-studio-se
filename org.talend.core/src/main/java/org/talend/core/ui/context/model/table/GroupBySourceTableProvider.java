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
package org.talend.core.ui.context.model.table;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.talend.core.model.process.IContext;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.utils.ContextParameterUtils;
import org.talend.core.ui.context.ContextTableValuesComposite;
import org.talend.core.ui.context.model.ContextProviderProxy;
import org.talend.core.ui.context.model.template.ContextConstant;

/**
 * cli class global comment. Detailled comment
 */
public class GroupBySourceTableProvider extends ContextProviderProxy {

    private ContextTableValuesComposite parentModel;

    public GroupBySourceTableProvider(ContextTableValuesComposite parentModel) {
        super();
        this.parentModel = parentModel;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
     */
    public String getColumnText(Object element, int columnIndex) {
        int count = 0;
        List<IContext> contextList = parentModel.getContexts();
        count = contextList.size();

        IContext defaultContext = parentModel.getContextModelManager().getContextManager().getDefaultContext();
        if (element instanceof GroupBySourceTableParent) {
            if (columnIndex == 0) {
                if (IContextParameter.BUILT_IN.equals(((GroupBySourceTableParent) element).getSourceName())) {
                    if (((GroupBySourceTableParent) element).getContextParameter() != null) {
                        if (defaultContext.getContextParameter(((GroupBySourceTableParent) element).getContextParameter()
                                .getName()) != null) {
                            return defaultContext.getContextParameter(
                                    ((GroupBySourceTableParent) element).getContextParameter().getName()).getName();
                        }
                    }
                } else {
                    if (((GroupBySourceTableParent) element).getSourceName() != null) {
                        return ((GroupBySourceTableParent) element).getSourceName();
                    }
                }
            }

            for (int j = 1; j <= count; j++) {
                if (j == 1) {
                    if (columnIndex == j) {
                        if (IContextParameter.BUILT_IN.equals(((GroupBySourceTableParent) element).getSourceName())) {
                            if (((GroupBySourceTableParent) element).getContextParameter() != null) {
                                if (contextList.get(columnIndex - 1).getContextParameter(
                                        ((GroupBySourceTableParent) element).getContextParameter().getName()) != null) {
                                    return ContextParameterUtils.checkAndHideValue(contextList.get(columnIndex - 1)
                                            .getContextParameter(
                                                    ((GroupBySourceTableParent) element).getContextParameter().getName()));
                                }
                            }
                        } else {
                            StringBuffer sb = new StringBuffer();
                            for (ContextTableSon son : ((GroupBySourceTableParent) element).getSon()) {
                                GroupBySourceTableSon sSon = (GroupBySourceTableSon) son;
                                if (sSon.getParameter() != null) {
                                    for (IContextParameter contextParameter : contextList.get(columnIndex - 1)
                                            .getContextParameterList()) {
                                        if (sSon.getParameter().getName().equals(contextParameter.getName())) {
                                            if (ContextConstant.NULL_STRING.equals(contextParameter.getValue())) {
                                                sb.append("" + "/"); //$NON-NLS-1$ //$NON-NLS-2$
                                            } else {
                                                sb.append(ContextParameterUtils.checkAndHideValue(contextParameter) + "/"); //$NON-NLS-1$
                                            }
                                        }
                                    }
                                }
                            }
                            if (sb.toString().lastIndexOf("/") != -1) { //$NON-NLS-1$
                                return sb.toString().substring(0, sb.toString().lastIndexOf("/")); //$NON-NLS-1$
                            }
                        }
                    }
                } else {
                    if (columnIndex == j) {
                        if (IContextParameter.BUILT_IN.equals(((GroupBySourceTableParent) element).getSourceName())) {
                            if (((GroupBySourceTableParent) element).getContextParameter() != null) {
                                if (contextList.get(columnIndex - 1).getContextParameter(
                                        ((GroupBySourceTableParent) element).getContextParameter().getName()) != null) {
                                    return ContextParameterUtils.checkAndHideValue(contextList.get(columnIndex - 1)
                                            .getContextParameter(
                                                    ((GroupBySourceTableParent) element).getContextParameter().getName()));
                                }
                            }
                        } else {
                            StringBuffer sb = new StringBuffer();
                            for (ContextTableSon son : ((GroupBySourceTableParent) element).getSon()) {
                                GroupBySourceTableSon sSon = (GroupBySourceTableSon) son;
                                if (sSon.getParameter() != null) {
                                    for (IContextParameter contextParameter : contextList.get(columnIndex - 1)
                                            .getContextParameterList()) {
                                        if (sSon.getParameter().getName().equals(contextParameter.getName())) {
                                            if (ContextConstant.NULL_STRING.equals(contextParameter.getValue())) {
                                                sb.append("" + "/"); //$NON-NLS-1$ //$NON-NLS-2$
                                            } else {
                                                sb.append(ContextParameterUtils.checkAndHideValue(contextParameter) + "/"); //$NON-NLS-1$
                                            }
                                        }
                                    }
                                }
                            }
                            if (sb.toString().lastIndexOf("/") != -1) { //$NON-NLS-1$
                                return sb.toString().substring(0, sb.toString().lastIndexOf("/")); //$NON-NLS-1$
                            }
                        }
                    }
                }
            }
        } else {
            GroupBySourceTableSon son = (GroupBySourceTableSon) element;
            if (columnIndex == 0) {
                if (son.getParameter() != null) {
                    if (defaultContext.getContextParameter(son.getParameter().getName()) != null) {
                        return defaultContext.getContextParameter(son.getParameter().getName()).getName();
                    }
                }
            }

            for (int j = 1; j <= count; j++) {
                if (j == 1) {
                    if (columnIndex == j) {
                        if (son.getParameter() != null) {
                            for (IContextParameter contextParameter : contextList.get(columnIndex - 1).getContextParameterList()) {
                                if (son.getParameter().getName().equals(contextParameter.getName())) {
                                    return ContextParameterUtils.checkAndHideValue(contextParameter);
                                }
                            }
                        }
                    }
                } else {
                    if (columnIndex == j) {
                        if (son.getParameter() != null) {
                            for (IContextParameter contextParameter : contextList.get(columnIndex - 1).getContextParameterList()) {
                                if (son.getParameter().getName().equals(contextParameter.getName())) {
                                    return ContextParameterUtils.checkAndHideValue(contextParameter);
                                }
                            }
                        }
                    }
                }
            }
        }
        return ""; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.context.ConextTreeValuesComposite.ProviderProxy#getColumnImage(java.lang.Object, int)
     */
    @Override
    public Image getColumnImage(Object element, int columnIndex) {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    public Object[] getElements(Object inputElement) {
        boolean flag = false;
        boolean b = false;
        boolean builtin = false;
        int index = 0;
        List<String> containers = new ArrayList<String>();
        List<String> nameContainers = new ArrayList<String>();
        List<String> oldBuiltinName = new ArrayList<String>();

        List<IContextParameter> contexts = (List<IContextParameter>) inputElement;

        if (!contexts.isEmpty()) {
            // because all the contexts have the save templ
            for (IContextParameter para : contexts) {
                nameContainers.add(para.getName());
                flag = false;
                if (!containers.isEmpty()) {
                    for (String source : containers) {
                        if (source.equals(para.getSource())) {
                            if (!(IContextParameter.BUILT_IN.equals(para.getSource()))) {
                                flag = true;
                            }
                        }
                    }
                    if (!flag) {
                        containers.add(para.getSource());
                    }
                } else {
                    containers.add(para.getSource());
                }
            }
        }

        List<GroupBySourceTableParent> root = new ArrayList<GroupBySourceTableParent>();

        if (!contexts.isEmpty()) {
            for (String sourceName : containers) {
                builtin = false;
                GroupBySourceTableParent parent = new GroupBySourceTableParent();
                parent.setSourceName(sourceName);
                for (String paraName : nameContainers) {
                    for (IContextParameter para : contexts) {
                        if (para.getName().equals(paraName)) {
                            index = contexts.indexOf(para);
                        }
                    }
                    IContextParameter contextPara = contexts.get(index);
                    if (!(IContextParameter.BUILT_IN.equals(contextPara.getSource()))) {
                        if (parent.getSourceName().equals(contextPara.getSource())) {
                            GroupBySourceTableSon son = new GroupBySourceTableSon();
                            son.setParameter(contextPara);
                            son.setParent(parent);
                            parent.getSon().add(son);
                        }
                    } else {
                        if (IContextParameter.BUILT_IN.equals(parent.getSourceName())) {
                            if (!builtin) {
                                b = false;
                                for (String name : oldBuiltinName) {
                                    if (name.equals(contextPara.getName())) {
                                        b = true;
                                    }
                                }
                                if (!b) {
                                    parent.setContextParameter(contextPara);
                                    oldBuiltinName.add(contextPara.getName());
                                    builtin = true;
                                }
                            }
                        }
                    }
                }
                root.add(parent);
            }
        }
        return root.toArray();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    public Object[] getChildren(Object parentElement) {
        if (parentElement instanceof GroupBySourceTableParent) {
            return ((GroupBySourceTableParent) parentElement).getSon().toArray();
        }
        return new Object[0];
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    public Object getParent(Object element) {
        if (element instanceof GroupBySourceTableSon) {
            return ((GroupBySourceTableSon) element).getParent();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    public boolean hasChildren(Object element) {
        if (element instanceof GroupBySourceTableParent) {
            return !((GroupBySourceTableParent) element).getSon().isEmpty();
        }
        return false;
    }
}
