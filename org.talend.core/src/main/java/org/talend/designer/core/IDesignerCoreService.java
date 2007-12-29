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

import java.util.List;
import java.util.Map;

import org.eclipse.gef.EditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.talend.core.IService;
import org.talend.core.model.components.IComponentsFactory;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.process.IProcess2;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.Property;
import org.talend.designer.core.ui.editor.AbstractTalendEditor;
import org.talend.designer.core.ui.editor.ITalendCodeEditor;

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

    // add for feature 840
    public void saveJobBeforeRun(IProcess activeProcess);

    public IProcess getCurrentProcess();

    public void refreshDesignerPalette();

    public IPartListener getActiveProcessTracker();

    public String getPreferenceStore(String key);

    public PaletteRoot createPalette(IComponentsFactory factory);

    public IAction getCreateProcessAction(boolean isToolbar);

    public ITalendCodeEditor getCodeEditor();

    public IProcess2 newProcess(Property property);

    public FlyoutPreferences createPalettePreferences();

    public PaletteViewerProvider createPaletteViewerProvider(EditDomain domain);

    public void configureGEFEditor(AbstractTalendEditor talendEditor);

    public PaletteRoot createPalette(IComponentsFactory factory, PaletteRoot paletteRoot);

    public TransferDropTargetListener createTemplateTransferDropTargetListener(GraphicalViewer viewer);

    public TransferDropTargetListener createEditorDropTargetListener(AbstractTalendEditor viewer);
}
