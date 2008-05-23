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
package org.talend.dataprofiler.core.ui.editor;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;

/**
 * @author rli
 * 
 */
public class DocumentEditorInputFactory implements IElementFactory {

    /**
     * 
     */
    public DocumentEditorInputFactory() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IElementFactory#createElement(org.eclipse.ui.IMemento)
     */
    public IAdaptable createElement(IMemento memento) {
//        String path = memento.getString(PluginConstant.PATH_SAVE);
//
//        IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(path));
//        if (file != null) {
//            AbstractEditorInput analysisEditorInuput = null;
//            String fileSuffix = memento.getString(PluginConstant.FILE_SUFFIX);
//            if (fileSuffix.equals(PluginConstant.ANA_SUFFIX)) {
//                analysisEditorInuput = new AnalysisEditorInuput(file);
//            } else if (fileSuffix.equals(PluginConstant.PRV_SUFFIX)) {
//                analysisEditorInuput = new ConnectionEditorInput(file);
//            }
//            return analysisEditorInuput;
//        } else {
            return null;
//        }
    }

}
