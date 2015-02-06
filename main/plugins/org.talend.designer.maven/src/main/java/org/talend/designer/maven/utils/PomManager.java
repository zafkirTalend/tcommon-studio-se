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
package org.talend.designer.maven.utils;

import org.apache.maven.model.Model;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2e.core.MavenPlugin;

/**
 * created by ggu on 6 Feb 2015 Detailled comment
 *
 */
public class PomManager {

    public static void savePom(IProgressMonitor monitor, Model model, IFile pomFile) throws Exception {
        /*
         * need find one way to do overwrite.
         */
        // IModelManager modelManager = StructuredModelManager.getModelManager();
        // IStructuredModel sModel = modelManager.getModelForRead(pomFile);
        // IDOMModel domModel = (IDOMModel)
        // modelManager.getModelForEdit(sModel.getStructuredDocument());
        // ElementValueProvider privider = new ElementValueProvider(PomEdits.ARTIFACT_ID);
        // Element el = privider.get(domModel.getDocument());
        // PomEdits.setText(el, model.getArtifactId());
        // sModel.save();

        pomFile.delete(true, monitor);
        MavenPlugin.getMavenModelManager().createMavenModel(pomFile, model);
    }
}
