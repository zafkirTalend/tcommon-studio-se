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
package org.talend.dataprofiler.core.ui.editor.connection;

import java.io.File;

import org.talend.cwm.softwaredeployment.TdDataProvider;
import org.talend.dataprofiler.core.helper.PrvResourceFileHelper;
import org.talend.dataprofiler.core.ui.editor.AbstractEditorInput;


/**
 * DOC rli  class global comment. Detailled comment
 */
public class ConnnectionEditorInput extends AbstractEditorInput  {

    private TdDataProvider analysis;

    public ConnnectionEditorInput(String name) {
        super(name);
    }

    public ConnnectionEditorInput(File file) {
        super(file);
    }

    public TdDataProvider getAnalysis() {
        if (analysis != null) {
            return analysis;
        }
        analysis = PrvResourceFileHelper.getInstance().getTdProvider(fFile).getObject();
        return analysis;
    }
    
}
