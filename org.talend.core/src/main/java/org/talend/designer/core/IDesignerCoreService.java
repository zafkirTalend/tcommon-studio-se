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
package org.talend.designer.core;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Map;

import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.talend.core.IService;
import org.talend.core.model.components.IComponentsFactory;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.JobletProcessItem;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.update.UpdateResult;

/**
 * Provides Designer core services for other components <br/>.
 * 
 * $Id: IDesignerCore.java 1 2006-12-19 上午10:16:43 bqian
 * 
 */
public interface IDesignerCoreService extends IService {

    // ¨Process will be loaded from XML File with this method, so it can be slow
    // This won't load the graphics of the job, only the datas
    public IProcess getProcessFromProcessItem(ProcessItem processItem);

    public List<IProcess> getOpenedProcess(IEditorReference[] reference);

    public Item getProcessItem(MultiPageEditorPart talendEditor);

    // Used for generating HTML only
    public List<Map> getMaps();

    // Ends
    public String getRepositoryAliasName(ConnectionItem connectionItem);

    public ILabelProvider getGEFEditorNodeLabelProvider();

    public void switchToCurContextsView();

    public void switchToCurComponentSettingsView();

    public void switchToCurJobSettingsView();

    // add for feature 840
    public void saveJobBeforeRun(IProcess activeProcess);

    public IProcess getCurrentProcess();

    public void synchronizeDesignerUI(PropertyChangeEvent evt);

    public String getPreferenceStore(String key);

    public PaletteRoot createPalette(IComponentsFactory factory);

    public PaletteRoot getAllNodeStructure(IComponentsFactory factory);

    public PaletteRoot createPalette(IComponentsFactory compFac, PaletteRoot root);

    public IAction getCreateProcessAction(boolean isToolbar);

    /**
     * tang Comment method "getProcessFromJobletProcessItem".
     * 
     * @param item
     * @return
     */
    public IProcess getProcessFromJobletProcessItem(JobletProcessItem item);

    /**
     * yzhang Comment method "createJobletEtnry".
     */
    public List<PaletteEntry> createJobletEtnry();

    public boolean isTalendEditor(IEditorPart activeEditor);

    public INode getRefrenceNode(String componentName);

    public boolean executeUpdatesManager(List<UpdateResult> results);
}
