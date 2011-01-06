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
package org.talend.designer.xmlmap.editor;

import java.util.EventObject;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.ui.actions.DeleteAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.gef.ui.rulers.RulerComposite;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.actions.ActionFactory;
import org.talend.designer.xmlmap.dnd.XmlDragSourceListener;
import org.talend.designer.xmlmap.dnd.XmlDropTargetListener;
import org.talend.designer.xmlmap.editor.actions.DeleteTreeNodeAction;
import org.talend.designer.xmlmap.editor.actions.ImportTreeFromXml;
import org.talend.designer.xmlmap.editor.actions.SetGroupAction;
import org.talend.designer.xmlmap.editor.actions.SetLoopAction;
import org.talend.designer.xmlmap.model.emf.xmlmap.InputXmlTree;
import org.talend.designer.xmlmap.model.emf.xmlmap.OutputTreeNode;
import org.talend.designer.xmlmap.model.emf.xmlmap.OutputXmlTree;
import org.talend.designer.xmlmap.model.emf.xmlmap.TreeNode;
import org.talend.designer.xmlmap.model.emf.xmlmap.XmlMapData;
import org.talend.designer.xmlmap.model.emf.xmlmap.XmlmapFactory;
import org.talend.designer.xmlmap.parts.OutputTreeNodeEditPart;
import org.talend.designer.xmlmap.parts.TreeNodeEditPart;
import org.talend.designer.xmlmap.util.XmlMapUtil;

/**
 * wchen class global comment. Detailled comment
 */
public class XmlMapEditor extends GraphicalEditor {

    private RulerComposite rulerComp;

    // public static final String ID = "org.rufus.gef.examples.tree.editors.XMLEditor";

    private KeyHandler sharedKeyHandler;

    public XmlMapEditor() {
        DefaultEditDomain defaultEditDomain = new DefaultEditDomain(this);
        setEditDomain(defaultEditDomain);
    }

    public Control getGraphicalControl() {
        rulerComp.setLayoutData(new GridData(GridData.FILL_BOTH));
        return rulerComp;
    }

    protected void createGraphicalViewer(final Composite parent) {
        rulerComp = new RulerComposite(parent, SWT.BORDER);
        super.createGraphicalViewer(rulerComp);
        rulerComp.setGraphicalViewer((ScrollingGraphicalViewer) getGraphicalViewer());
    }

    /**
     * @see org.eclipse.gef.commands.CommandStackListener#commandStackChanged(java.util.EventObject)
     */
    public void commandStackChanged(EventObject event) {
        firePropertyChange(IEditorPart.PROP_DIRTY);
        super.commandStackChanged(event);
    }

    /**
     * @see org.eclipse.gef.ui.parts.GraphicalEditor#createActions()
     */
    protected void createActions() {
        IAction deleteAction = new DeleteTreeNodeAction(this);
        getActionRegistry().registerAction(deleteAction);
        getSelectionActions().add(deleteAction.getId());

        IAction loopAction = new SetLoopAction(this);
        getActionRegistry().registerAction(loopAction);
        getSelectionActions().add(loopAction.getId());

        IAction groupAction = new SetGroupAction(this);
        getActionRegistry().registerAction(groupAction);
        getSelectionActions().add(groupAction.getId());

    }

    /**
     * @see org.eclipse.gef.ui.parts.GraphicalEditor#configureGraphicalViewer()
     */
    protected void configureGraphicalViewer() {
        super.configureGraphicalViewer();
        getGraphicalViewer().setRootEditPart(new ScalableRootEditPart());
        getGraphicalViewer().setEditPartFactory(new XmlMapPartFactory());
        getGraphicalViewer().setKeyHandler(new GraphicalViewerKeyHandler(getGraphicalViewer()).setParent(getCommonKeyHandler()));

    }

    /**
     * @see org.eclipse.gef.ui.parts.GraphicalEditor#initializeGraphicalViewer()
     */
    protected void initializeGraphicalViewer() {

        // getGraphicalViewer().setContents(getContents());

        getGraphicalViewer().addDragSourceListener(new XmlDragSourceListener(getGraphicalViewer()));

        getGraphicalViewer().addDropTargetListener(new XmlDropTargetListener(getGraphicalViewer()));

        this.getGraphicalViewer().addSelectionChangedListener(new ISelectionChangedListener() {

            public void selectionChanged(SelectionChangedEvent event) {
                getGraphicalViewer().getSelectedEditParts();
                // XmlMapEditor.this.selectionChanged(XmlMapEditor.this, getGraphicalViewer().getSelection());
            }

        });
        getGraphicalViewer().setContextMenu(new MenueProvider(getGraphicalViewer()));
        initializeActionRegistry();
    }

    //
    // @Override
    // public void selectionChanged(IWorkbenchPart part, ISelection selection) {
    // updateActions(getSelectionActions());
    // }

    public void setContent(Object content) {
        getGraphicalViewer().setContents(content);
    }

    protected KeyHandler getCommonKeyHandler() {
        if (sharedKeyHandler == null) {
            sharedKeyHandler = new KeyHandler();
            sharedKeyHandler.put(KeyStroke.getPressed(SWT.DEL, 127, 0),
                    getActionRegistry().getAction(ActionFactory.DELETE.getId()));
            sharedKeyHandler.put(KeyStroke.getPressed(SWT.F2, 0), getActionRegistry().getAction(GEFActionConstants.DIRECT_EDIT));
        }
        return sharedKeyHandler;
    }

    /**
     * Get the viewer in the editor.
     * 
     * @return
     */
    public GraphicalViewer getViewer() {
        return getGraphicalViewer();
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void hookGraphicalViewer() {
        getSelectionSynchronizer().addViewer(getGraphicalViewer());
    }

    /**
     * wchen class global comment. Detailled comment
     */
    class MenueProvider extends ContextMenuProvider {

        public MenueProvider(EditPartViewer viewer) {
            super(viewer);
        }

        @Override
        public void buildContextMenu(IMenuManager menu) {
            // context menu should only display in the document tree
            List selectedEditParts = getGraphicalViewer().getSelectedEditParts();
            if (selectedEditParts != null && !selectedEditParts.isEmpty()) {
                if (selectedEditParts.get(0) instanceof OutputTreeNodeEditPart) {
                    OutputTreeNode model = (OutputTreeNode) ((OutputTreeNodeEditPart) selectedEditParts.get(0)).getModel();
                    if (XmlMapUtil.getXPathLength(model.getXpath()) > 2) {
                        DeleteTreeNodeAction action = (DeleteTreeNodeAction) getActionRegistry().getAction(DeleteAction.ID);
                        action.update();
                        menu.add(action);

                        SetLoopAction loopAction = (SetLoopAction) getActionRegistry().getAction(SetLoopAction.ID);
                        loopAction.update();
                        menu.add(loopAction);

                        SetGroupAction grouptAction = (SetGroupAction) getActionRegistry().getAction(SetGroupAction.ID);
                        grouptAction.update();
                        menu.add(grouptAction);
                    }

                } else if (selectedEditParts.get(0) instanceof TreeNodeEditPart) {
                    TreeNodeEditPart editPart = (TreeNodeEditPart) selectedEditParts.get(0);
                    TreeNode treeNode = (TreeNode) editPart.getModel();
                    if (treeNode.eContainer() instanceof InputXmlTree && XmlMapUtil.DOCUMENT.equals(treeNode.getType())) {
                        menu.add(new ImportTreeFromXml(getGraphicalViewer().getControl().getShell(), treeNode));
                    }
                    SetLoopAction loopAction = (SetLoopAction) getActionRegistry().getAction(SetLoopAction.ID);
                    loopAction.update();
                    menu.add(loopAction);
                }
            }

        }
    }

    private XmlMapData getContents() {
        XmlMapData mapData = XmlmapFactory.eINSTANCE.createXmlMapData();
        InputXmlTree inputXmlTree = XmlmapFactory.eINSTANCE.createInputXmlTree();
        inputXmlTree.setName("input1");
        mapData.getInputTrees().add(inputXmlTree);

        TreeNode normalNode = XmlmapFactory.eINSTANCE.createTreeNode();
        normalNode.setName("treeNode1");
        normalNode.setType("INT");
        normalNode.setXpath("input1/treeNode1");
        inputXmlTree.getNodes().add(normalNode);
        //
        TreeNode treeNode = XmlmapFactory.eINSTANCE.createTreeNode();
        treeNode.setName("root");
        treeNode.setType(XmlMapUtil.DOCUMENT);
        treeNode.setXpath("input1/root");
        inputXmlTree.getNodes().add(treeNode);

        TreeNode treeNode1 = XmlmapFactory.eINSTANCE.createTreeNode();
        treeNode1.setName("treeNode1");
        treeNode1.setType(XmlMapUtil.DOCUMENT);
        treeNode1.setXpath("input1/root/treeNode1");
        treeNode.getChildren().add(treeNode1);
        // treeNode1.setParent(treeNode);

        TreeNode treeNode11 = XmlmapFactory.eINSTANCE.createTreeNode();
        treeNode11.setName("treeNode11");
        treeNode11.setType(XmlMapUtil.DOCUMENT);
        treeNode11.setXpath("input1/root/treeNode1/treeNode11");
        treeNode1.getChildren().add(treeNode11);

        TreeNode treeNode12 = XmlmapFactory.eINSTANCE.createTreeNode();
        treeNode12.setName("treeNode12");
        treeNode12.setType(XmlMapUtil.DOCUMENT);
        treeNode12.setXpath("input1/root/treeNode1/treeNode12");
        treeNode1.getChildren().add(treeNode12);

        OutputXmlTree outputXmlTree = XmlmapFactory.eINSTANCE.createOutputXmlTree();
        outputXmlTree.setName("output1");
        mapData.getOutputTrees().add(outputXmlTree);

        OutputTreeNode outputTreeNode = XmlmapFactory.eINSTANCE.createOutputTreeNode();
        outputTreeNode.setName("root");
        outputTreeNode.setType(XmlMapUtil.DOCUMENT);
        outputTreeNode.setXpath("output1/root");
        outputTreeNode.setDefaultValue(XmlMapUtil.DOCUMENT);
        outputXmlTree.getNodes().add(outputTreeNode);

        OutputTreeNode output1 = XmlmapFactory.eINSTANCE.createOutputTreeNode();
        output1.setName("out1");
        output1.setXpath("output1/root/out1");
        output1.setDefaultValue(XmlMapUtil.DOCUMENT);
        outputTreeNode.getChildren().add(output1);

        OutputTreeNode output2 = XmlmapFactory.eINSTANCE.createOutputTreeNode();
        output2.setName("out2");
        output2.setXpath("output1/root/out2");
        output2.setDefaultValue(XmlMapUtil.DOCUMENT);
        outputTreeNode.getChildren().add(output2);

        OutputTreeNode output11 = XmlmapFactory.eINSTANCE.createOutputTreeNode();
        output11.setName("out12");
        output11.setXpath("output1/root/out1/out2");
        output11.setDefaultValue(XmlMapUtil.DOCUMENT);
        output1.getChildren().add(output11);

        OutputTreeNode outputsigle = XmlmapFactory.eINSTANCE.createOutputTreeNode();
        outputsigle.setName("outputsigle");
        outputsigle.setExpression("outputsigle");
        outputsigle.setDefaultValue("INT");
        outputXmlTree.getNodes().add(outputsigle);
        //

        return mapData;
    }

}
