package com.quantum.actions;

import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.gef.ui.actions.ZoomInRetargetAction;
import org.eclipse.gef.ui.actions.ZoomOutRetargetAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.actions.ActionFactory;

import com.quantum.ImageStore;
import com.quantum.QuantumPlugin;

/**
 * Contributes actions to the Editor
 * 
 * @author Phil Zoio
 */
public class EntityRelationActionBarContributor extends ActionBarContributor
{
	FlyoutChangeLayoutAction changeLayoutAction;
    ToggleRelationshipNamesAction toggleRelationshipNamesAction;
    ToggleColumnsAction toggleColumnsAction;
    RefreshDiagramAction refreshDiagramAction;
    
    IEditorPart editor;
	
	protected void buildActions()
	{
		addRetargetAction(new UndoRetargetAction());
		addRetargetAction(new RedoRetargetAction());
		addRetargetAction(new DeleteRetargetAction());
        addRetargetAction(new ZoomInRetargetAction());
        addRetargetAction(new ZoomOutRetargetAction());
		buildChangeLayoutAction();
        buildToggleRelationNamesAction();
        buildToggleColumnsAction();
        buildRefreshDiagramAction();
//		addAction(changeLayoutAction);
        addAction(toggleRelationshipNamesAction);
//        addAction(toggleColumnsAction);
        addAction(refreshDiagramAction);
	}

	public void contributeToToolBar(IToolBarManager toolBarManager)
	{
		toolBarManager.add(getAction(ActionFactory.UNDO.getId()));
		toolBarManager.add(getAction(ActionFactory.REDO.getId()));
//		toolBarManager.add(changeLayoutAction);
        toolBarManager.add(toggleRelationshipNamesAction);
        toolBarManager.add(refreshDiagramAction);
        toolBarManager.add(getAction(GEFActionConstants.ZOOM_IN));
        toolBarManager.add(getAction(GEFActionConstants.ZOOM_OUT));
//        toolBarManager.add(toggleColumnsAction);
	}

	private void buildChangeLayoutAction()
	{
		changeLayoutAction = new FlyoutChangeLayoutAction(editor);
		changeLayoutAction.setToolTipText("Automatic Layout");
		changeLayoutAction.setId("com.quantum.actions.ChangeLayoutAction");
        changeLayoutAction.setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.TABLE));
        changeLayoutAction.setDisabledImageDescriptor(ImageStore.getImageDescriptor(ImageStore.COLUMN));
	}

    private void buildToggleRelationNamesAction()
    {
        toggleRelationshipNamesAction = new ToggleRelationshipNamesAction(editor);
        toggleRelationshipNamesAction.setToolTipText("Toggle relationship names");
        toggleRelationshipNamesAction.setId("com.quantum.actions.ToggleRelationshipNamesAction");
        toggleRelationshipNamesAction.setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.RELATIONSHIPSON));
        toggleRelationshipNamesAction.setDisabledImageDescriptor(ImageStore.getImageDescriptor(ImageStore.RELATIONSHIPSOFF));
    }

    // I think we do not need this, I wanted to use folding but cannot find a relevant example...
    private void buildToggleColumnsAction()
    {
        toggleColumnsAction = new ToggleColumnsAction(editor);
        toggleColumnsAction.setToolTipText("Toggle columns");
        toggleColumnsAction.setId("com.quantum.actions.ToggleColumnsAction");
        toggleColumnsAction.setImageDescriptor(ImageDescriptor.createFromFile(QuantumPlugin.class, "icons/table.gif"));
        toggleColumnsAction.setDisabledImageDescriptor(ImageDescriptor.createFromFile(QuantumPlugin.class, "icons/table.gif"));
    }

    private void buildRefreshDiagramAction()
    {
        refreshDiagramAction = new RefreshDiagramAction(editor);
        refreshDiagramAction.setToolTipText("Update the diagram to match database");
        refreshDiagramAction.setId("com.quantum.actions.RefreshDiagramAction");
        refreshDiagramAction.setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.REFRESH));
//        refreshDiagramAction.setDisabledImageDescriptor(ImageStore.getImageDescriptor(ImageStore.REFRESH));
    }

	public void setActiveEditor(IEditorPart editor)
	{
		this.editor = editor;
//        changeLayoutAction.setActiveEditor(editor);
        toggleRelationshipNamesAction.setActiveEditor(editor);
//        toggleColumnsAction.setActiveEditor(editor);
        refreshDiagramAction.setActiveEditor(editor);
		super.setActiveEditor(editor);
		
	}

	protected void declareGlobalActionKeys()
	{
		//add support for printing
		addGlobalActionKey(ActionFactory.PRINT.getId());
	}

}