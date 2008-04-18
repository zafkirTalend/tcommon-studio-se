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
package org.talend.dataprofiler.core.ui.views;


import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;
import org.talend.commons.emf.EMFUtil;
import org.talend.cwm.softwaredeployment.TdDataProvider;
import org.talend.dataprofiler.core.PluginConstant;
import org.talend.dataprofiler.core.helper.PrvFileMapHelper;
import org.talend.utils.sugars.TypedReturnCode;

/**
 * @author rli
 * 
 */
public class ResourceViewLabelProvider extends WorkbenchLabelProvider implements ICommonLabelProvider {

    private static Logger log = Logger.getLogger(ResourceViewLabelProvider.class);

    public void init(ICommonContentExtensionSite aConfig) {
    }

    public String getDescription(Object anElement) {

        if (anElement instanceof IResource) {
            return ((IResource) anElement).getFullPath().makeRelative().toString();
        }
        return null;
    }

    public void restoreState(IMemento aMemento) {

    }

    public void saveState(IMemento aMemento) {
    }

    protected String decorateText(String input, Object element) {
        if (input.endsWith(PluginConstant.PRV_SUFFIX)) {
            IFile file = (IFile) element;
            TypedReturnCode<TdDataProvider> rc = PrvFileMapHelper.getInstance().readFromFile(file);
            String decorateText = PluginConstant.EMPTY_STRING;
            if (rc.isOk()) {
                decorateText = rc.getObject().getName();
            } else {
                log.warn(rc.getMessage());
            }
            return decorateText;
        } else if (input.endsWith(PluginConstant.ANA_SUFFIX)) {
            EMFUtil util = new EMFUtil();
            log.info("Loading file " + ((IFile) element).getLocation());
            // ResourceSet rs = util.getResourceSet();
            // Resource r = rs.getResource(URI.createFileURI(fFile.getAbsolutePath()), true);
            //
            // EList<EObject> contents = r.getContents();
            // if (contents.isEmpty()) {
            // log.error("No content in " + r);
            // }
            // log.info("Nb elements in contents " + contents.size());
            // AnalysisSwitch<Analysis> mySwitch = new AnalysisSwitch<Analysis>() {
            //
            // public Analysis caseAnalysis(Analysis object) {
            // return object;
            // }
            // };
            // if (contents != null) {
            // analysis = mySwitch.doSwitch(contents.get(0));
            // }
            // return analysis;
        }
        return input;
    }

}
