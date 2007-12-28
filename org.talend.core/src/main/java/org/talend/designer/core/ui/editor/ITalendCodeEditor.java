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
package org.talend.designer.core.ui.editor;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.talend.designer.core.ui.AbstractMultiPageTalendEditor;

/**
 * DOC qzhang class global comment. Detailled comment
 */
public interface ITalendCodeEditor extends IEditorPart {

    /**
     * DOC qzhang Comment method "setInput".
     * 
     * @param input
     */
    void setInput(IEditorInput input);

    /**
     * DOC qzhang Comment method "removeEditorPart".
     * 
     * @param abstractMultiPageTalendEditor
     */
    void removeEditorPart(AbstractMultiPageTalendEditor abstractMultiPageTalendEditor);

}
