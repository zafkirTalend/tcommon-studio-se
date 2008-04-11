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
package org.talend.dataprofiler.core.ui.utils;

import org.eclipse.swt.widgets.Composite;


/**
 * DOC zqin  class global comment. Detailled comment
 */
public abstract class AbstractIndicatorForm extends AbstractForm {

    private String formName;
    /**
     * DOC zqin AbstractIndicatorForm constructor comment.
     * @param parent
     * @param style
     */
    public AbstractIndicatorForm(Composite parent, int style) {
        super(parent, style);
        // TODO Auto-generated constructor stub
    }
    
    public abstract String getFormName();
    
}
