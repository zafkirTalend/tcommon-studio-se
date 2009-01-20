// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.ui.context;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.talend.commons.ui.swt.tooltip.AbstractTreeTooltip;
import org.talend.core.ui.context.ConextTreeValuesComposite.GroupByVariableProvier;
import org.talend.core.ui.context.ConextTreeValuesComposite.GroupByVariableProvier.Son;

/**
 * DOC YeXiaowei class global comment. Detailled comment <br/>
 * 
 */
public abstract class AbstractContextTabEditComposite extends Composite {

    private static final String PERL_STRING_TYPE = "string"; //$NON-NLS-1$

    /**
     * DOC YeXiaowei AbstractContextTabEditComposite constructor comment.
     * 
     * @param parent
     * @param style
     */
    public AbstractContextTabEditComposite(Composite parent, int style) {
        super(parent, style);
    }

    private boolean needRefresh = true;

    public boolean isNeedRefresh() {
        return this.needRefresh;
    }

    public void setNeedRefresh(boolean refresh) {
        this.needRefresh = refresh;
    }

    public String getPerlStringType() {
        return PERL_STRING_TYPE;
    }

    public abstract void refresh();

}
