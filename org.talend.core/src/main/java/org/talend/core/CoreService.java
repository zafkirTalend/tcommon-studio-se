// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.talend.commons.exception.BusinessException;
import org.talend.commons.exception.SystemException;
import org.talend.core.model.general.Project;
import org.talend.core.model.metadata.ColumnNameChanged;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.MetadataTool;
import org.talend.core.model.metadata.QueryUtil;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.properties.Item;
import org.talend.core.model.relationship.RelationshipItemBuilder;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.core.model.repository.RepositoryManager;
import org.talend.core.model.routines.RoutineLibraryMananger;
import org.talend.core.model.utils.ContextParameterUtils;
import org.talend.core.model.utils.JavaResourcesHelper;
import org.talend.core.model.utils.PerlResourcesHelper;
import org.talend.core.model.utils.TalendTextUtils;
import org.talend.core.prefs.PreferenceManipulator;
import org.talend.core.ui.IRulesProviderService;
import org.talend.core.ui.images.OverlayImageProvider;
import org.talend.core.utils.KeywordsValidator;
import org.talend.designer.codegen.ICodeGeneratorService;
import org.talend.designer.codegen.ITalendSynchronizer;
import org.talend.designer.core.IDesignerCoreService;
import org.talend.designer.core.model.utils.emf.talendfile.NodeType;
import org.talend.designer.joblet.ui.IJobCheckService;
import org.talend.designer.runprocess.IRunProcessService;
import org.talend.repository.model.ComponentsFactoryProvider;
import org.talend.repository.model.IRepositoryNode;

/**
 * DOC Administrator class global comment. Detailled comment
 */
public class CoreService implements ICoreService {

    public List<ColumnNameChanged> getColumnNameChanged(IMetadataTable oldTable, IMetadataTable newTable) {
        return MetadataTool.getColumnNameChanged(oldTable, newTable);
    }

    public List<ColumnNameChanged> getNewMetadataColumns(IMetadataTable oldTable, IMetadataTable newTable) {
        return MetadataTool.getNewMetadataColumns(oldTable, newTable);
    }

    public List<ColumnNameChanged> getRemoveMetadataColumns(IMetadataTable oldTable, IMetadataTable newTable) {
        return MetadataTool.getRemoveMetadataColumns(oldTable, newTable);
    }

    public void initializeForTalendStartupJob() {
        CorePlugin.getDefault().getRepositoryService().initializeForTalendStartupJob();
    }

    public String getLanTypeString() {
        return CorePlugin.getDefault().getPluginPreferences().getString(CorePlugin.PROJECT_LANGUAGE_TYPE);
    }

    public Image getImageWithDocExt(String extension) {
        return OverlayImageProvider.getImageWithDocExt(extension);
    }

    public ImageDescriptor getImageWithSpecial(Image source) {
        return OverlayImageProvider.getImageWithSpecial(source);
    }

    public boolean isContainContextParam(String code) {
        return ContextParameterUtils.isContainContextParam(code);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ICoreService#setFlagForQueryUtils(boolean)
     */
    public void setFlagForQueryUtils(boolean flag) {
        QueryUtil.isContextQuery = flag;

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ICoreService#getFlagFromQueryUtils()
     */
    public boolean getContextFlagFromQueryUtils() {
        return QueryUtil.isContextQuery;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ICoreService#getRoot()
     */
    public IRepositoryNode getRoot() {
        return RepositoryManager.getRepositoryView().getRoot();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ICoreService#getRoutineAndJars()
     */
    public Map<String, List<URI>> getRoutineAndJars() {
        return RoutineLibraryMananger.getInstance().getRoutineAndJars();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ICoreService#getTemplateString()
     */
    public String getTemplateString() {
        return ITalendSynchronizer.TEMPLATE;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ICoreService#getParameterUNIQUENAME()
     */
    public String getParameterUNIQUENAME(NodeType node) {
        return ElementParameterParser.getUNIQUENAME(node);
    }

    public boolean isAlreadyBuilt(Project project) {
        return !project.getEmfProject().getItemsRelations().isEmpty();
    }

    public void removeItemRelations(Item item) {
        RelationshipItemBuilder.getInstance().removeItemRelations(item);
    }

    public String getJavaJobFolderName(String jobName, String version) {
        return JavaResourcesHelper.getJobFolderName(jobName, version);
    }

    public String getJavaProjectFolderName(Item item) {
        return JavaResourcesHelper.getProjectFolderName(item);
    }

    public IResource getSpecificResourceInJavaProject(IPath path) throws CoreException {
        return JavaResourcesHelper.getSpecificResourceInJavaProject(path);
    }

    public String getContextFileNameForPerl(String projectName, String jobName, String version, String context) {
        return PerlResourcesHelper.getContextFileName(projectName, jobName, version, context);
    }

    public String getRootProjectNameForPerl(Item item) {
        return PerlResourcesHelper.getRootProjectName(item);
    }

    public IResource getSpecificResourceInPerlProject(IPath path) throws CoreException {
        return PerlResourcesHelper.getSpecificResourceInPerlProject(path);
    }

    public void syncLibraries(IProgressMonitor... monitorWrap) {
        CorePlugin.getDefault().getLibrariesService().syncLibraries(monitorWrap);
    }

    public void componentsReset() {
        ComponentsFactoryProvider.getInstance().reset();
    }

    public void initializeComponents(IProgressMonitor monitor) {
        ComponentsFactoryProvider.getInstance().initializeComponents(monitor);
    }

    public void removeJobLaunch(IRepositoryViewObject objToDelete) {
        if (GlobalServiceRegister.getDefault().isServiceRegistered(IDesignerCoreService.class)) {
            IDesignerCoreService designerCoreService = (IDesignerCoreService) GlobalServiceRegister.getDefault().getService(
                    IDesignerCoreService.class);
            designerCoreService.removeJobLaunch(objToDelete);
        }
    }

    public void deleteRoutinefile(IRepositoryViewObject objToDelete) {
        if (GlobalServiceRegister.getDefault().isServiceRegistered(ICodeGeneratorService.class)) {
            ICodeGeneratorService codeGenService = (ICodeGeneratorService) GlobalServiceRegister.getDefault().getService(
                    ICodeGeneratorService.class);
            codeGenService.createRoutineSynchronizer().deleteRoutinefile(objToDelete);
        }
    }

    public boolean checkJob(String name) throws BusinessException {
        IJobCheckService jobCheckService = (IJobCheckService) GlobalServiceRegister.getDefault().getService(
                IJobCheckService.class);
        if (jobCheckService != null) {
            return jobCheckService.checkJob(name);
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ICoreService#syncAllRoutines()
     */
    public void syncAllRoutines() throws SystemException {
        ICodeGeneratorService codeGenService = (ICodeGeneratorService) GlobalServiceRegister.getDefault().getService(
                ICodeGeneratorService.class);
        codeGenService.createRoutineSynchronizer().syncAllRoutines();

    }

    public void syncAllRules() {
        if (PluginChecker.isRulesPluginLoaded()) {
            IRulesProviderService rulesService = (IRulesProviderService) GlobalServiceRegister.getDefault().getService(
                    IRulesProviderService.class);
            if (rulesService != null) {
                rulesService.syncAllRules();
            }
        }

    }

    public Job initializeTemplates() {
        return CorePlugin.getDefault().getCodeGeneratorService().initializeTemplates();
    }

    public void createStatsLogAndImplicitParamter(Project project) {
        IDesignerCoreService designerCoreService = CorePlugin.getDefault().getDesignerCoreService();
        if (designerCoreService != null) {
            designerCoreService.createStatsLogAndImplicitParamter(project);
        }
    }

    public void deleteAllJobs(boolean fromPluginModel) {
        IRunProcessService runProcessService = (IRunProcessService) GlobalServiceRegister.getDefault().getService(
                IRunProcessService.class);
        runProcessService.deleteAllJobs(false);
    }

    public void addWorkspaceTaskDone(String task) {
        PreferenceManipulator prefManipulator = new PreferenceManipulator(CorePlugin.getDefault().getPreferenceStore());
        prefManipulator.addWorkspaceTaskDone(task);
    }

    public String filterSpecialChar(String input) {
        return TalendTextUtils.filterSpecialChar(input);
    }

    public String getLastUser() {
        PreferenceManipulator prefManipulator = new PreferenceManipulator(CorePlugin.getDefault().getPreferenceStore());
        return prefManipulator.getLastUser();
    }

    public boolean isKeyword(String word) {
        return KeywordsValidator.isKeyword(word);
    }

    public List<String> readWorkspaceTasksDone() {
        PreferenceManipulator prefManipulator = new PreferenceManipulator(CorePlugin.getDefault().getPreferenceStore());
        return prefManipulator.readWorkspaceTasksDone();
    }

    public String validateValueForDBType(String columnName) {
        return MetadataTool.validateValueForDBType(columnName);
    }

}
