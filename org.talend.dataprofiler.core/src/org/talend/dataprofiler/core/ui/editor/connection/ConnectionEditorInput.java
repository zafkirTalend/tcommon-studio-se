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

import org.eclipse.ui.IMemento;
import org.talend.cwm.softwaredeployment.TdDataProvider;
import org.talend.dataprofiler.core.PluginConstant;
import org.talend.dataprofiler.core.helper.PrvResourceFileHelper;
import org.talend.dataprofiler.core.ui.editor.AbstractEditorInput;


/**
 * DOC rli  class global comment. Detailled comment
 */
public class ConnectionEditorInput extends AbstractEditorInput  {

    private TdDataProvider tdDataProvider;

    public ConnectionEditorInput(String name) {
        super(name);
    }

    public ConnectionEditorInput(File file) {
        super(file);
    }

    public TdDataProvider getProvider() {
        if (tdDataProvider != null) {
            return tdDataProvider;
        }
        tdDataProvider = PrvResourceFileHelper.getInstance().getTdProvider(fFile).getObject();
        return tdDataProvider;
    }
    
    public void saveState(IMemento memento) {
        super.saveState(memento);
        memento.putString(PluginConstant.FILE_SUFFIX, PluginConstant.PRV_SUFFIX);
    }
    
}
