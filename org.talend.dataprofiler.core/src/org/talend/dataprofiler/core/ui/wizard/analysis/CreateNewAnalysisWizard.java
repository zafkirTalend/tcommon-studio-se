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
package org.talend.dataprofiler.core.ui.wizard.analysis;

import java.io.ByteArrayInputStream;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.talend.commons.emf.FactoriesUtil;
import org.talend.cwm.management.connection.ConnectionParameters;
import org.talend.dataprofiler.core.ImageLib;
import org.talend.dataprofiler.core.manager.DQStructureManager;
import org.talend.dataprofiler.core.ui.wizard.PropertiesWizardPage;
import org.talend.dataquality.analysis.AnalysisType;
import org.talend.dq.analysis.AnalysisBuilder;

/**
 * @author huangssssx
 *
 */
public class CreateNewAnalysisWizard extends Wizard implements INewWizard {
    
    private IWorkbench workbench;

    private Object selection;
    
    private boolean creation;
    
    private PropertiesWizardPage propertiesWizardPage;

    private ConnectionParameters connectionProperty;
    
    private AnalysisWizardPageStep0 page0;
    
    private AnalysisWizardPageStep1 page1;
    /**
     * 
     */
    public CreateNewAnalysisWizard(IWorkbench workbench, boolean creation, IStructuredSelection selection,
            String[] existingNames) {
        super();
        this.init(workbench, selection);
        this.creation = creation;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        // TODO Auto-generated method stub
        String analysisName = page1.getName();
        AnalysisType analysisType = AnalysisType.getByName(page0.getTypeName());
        if (analysisName != null && analysisType != null) {
            
            if (creation) {
              IFolder folder = ResourcesPlugin.getWorkspace().getRoot().getProject(DQStructureManager.DATA_PROFILING).getFolder(
              DQStructureManager.ANALYSIS);
              IFile newFile = folder.getFile(analysisName+ "." + FactoriesUtil.ANA);
              if (newFile.exists()) {
                  return false;
              }
              try {
                  newFile.create(new ByteArrayInputStream(new byte[0]), false, null);
                  return true;
              } catch (CoreException e) {
                  e.printStackTrace();
                  return false;
              }
            } else {
                AnalysisBuilder builder = new AnalysisBuilder();
                builder.initializeAnalysis(analysisName, analysisType);
                
                //create realted property to build the analysis file
                return true;
            }
            
        } else {
            return false;
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
     */
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        // TODO Auto-generated method stub
        this.workbench = workbench;
        this.selection = selection;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {

        setWindowTitle("Create New Analysis");
        setDefaultPageImageDescriptor(ImageLib.getImageDescriptor(ImageLib.REFRESH_IMAGE));
        connectionProperty = new ConnectionParameters();
        connectionProperty.setParameters(new Properties());
        
        page0 = new AnalysisWizardPageStep0();
        page1 = new AnalysisWizardPageStep1(connectionProperty, null, false, true); 
        
        addPage(page0);
        addPage(page1);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#canFinish()
     */
    @Override
    public boolean canFinish() {
        if(this.getContainer().getCurrentPage() != page1){
            return false;
        }
        return super.canFinish();
    }
    
    public boolean createANA(String anaName, String anaType, String anaParameter){
        return false;
    }

}
