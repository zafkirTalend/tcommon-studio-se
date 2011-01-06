package org.talend.designer.xmlmap.ui;

import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.talend.commons.ui.runtime.image.ImageUtils.ICON_SIZE;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.model.components.IODataComponent;
import org.talend.core.model.components.IODataComponentContainer;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.IExternalNode;
import org.talend.core.model.process.INode;
import org.talend.core.ui.branding.IBrandingService;
import org.talend.core.ui.images.CoreImageProvider;
import org.talend.designer.core.ui.AbstractMultiPageTalendEditor;
import org.talend.designer.core.ui.editor.cmd.ExternalNodeChangeCommand;
import org.talend.designer.core.ui.editor.nodes.Node;
import org.talend.designer.xmlmap.XmlMapComponent;
import org.talend.designer.xmlmap.editor.XmlMapEditor;
import org.talend.designer.xmlmap.model.emf.xmlmap.InputXmlTree;
import org.talend.designer.xmlmap.model.emf.xmlmap.OutputTreeNode;
import org.talend.designer.xmlmap.model.emf.xmlmap.OutputXmlTree;
import org.talend.designer.xmlmap.model.emf.xmlmap.TreeNode;
import org.talend.designer.xmlmap.model.emf.xmlmap.XmlMapData;
import org.talend.designer.xmlmap.model.emf.xmlmap.XmlmapFactory;
import org.talend.designer.xmlmap.util.XmlMapUtil;

public class MapperUI {

    private XmlMapComponent mapperComponent;

    private XmlMapData copyOfMapData;

    private int mapperResponse;

    private Shell mapperShell;

    public MapperUI(XmlMapComponent mapperComponent) {
        this.mapperComponent = mapperComponent;
    }

    public Shell createWindow(final Display display) {

        Shell activeShell = display.getActiveShell();
        int style = SWT.DIALOG_TRIM | SWT.MIN | SWT.MAX | SWT.APPLICATION_MODAL | SWT.RESIZE;
        if (activeShell == null) {
            mapperShell = new Shell(mapperShell, style);
        } else {
            mapperShell = new Shell(activeShell, style);
        }

        // mapperShell.addShellListener(new ShellListener() {
        //
        // public void shellActivated(ShellEvent e) {
        // }
        //
        // public void shellClosed(ShellEvent e) {
        // // if (mapperManager.isDataChanged() && !mapperManager.getUiManager().isCloseWithoutPrompt()) {
        // // boolean closeWindow = MessageDialog.openConfirm(mapperShellFinal,
        //                //                            "MapperUI.CancelWithoutSaveModifications.Title", //$NON-NLS-1$
        //                //                            "MapperUI.CancelWithoutSaveModifications.Message"); //$NON-NLS-1$
        // // if (!closeWindow) {
        // // e.doit = false;
        // // } else {
        // // mapperManager.getUiManager().prepareClosing(SWT.CANCEL);
        // // }
        // //
        // // }
        // }
        //
        // public void shellDeactivated(ShellEvent e) {
        // }
        //
        // public void shellDeiconified(ShellEvent e) {
        // }
        //
        // public void shellIconified(ShellEvent e) {
        // }
        //
        // });

        // Rectangle boundsMapper = new Rectangle(50, 50, 800, 600);
        // mapperShell.setBounds(boundsMapper);
        mapperShell.setMaximized(true);

        mapperShell.setImage(CoreImageProvider.getComponentIcon(mapperComponent.getComponent(), ICON_SIZE.ICON_32));

        IBrandingService brandingService = (IBrandingService) GlobalServiceRegister.getDefault().getService(
                IBrandingService.class);
        String productName = brandingService.getFullProductName();
        mapperShell.setText(productName
                + " - " + mapperComponent.getComponent().getName() + " - " + mapperComponent.getUniqueName()); //$NON-NLS-1$

        GridLayout parentLayout = new GridLayout(1, true);
        mapperShell.setLayout(parentLayout);
        // Composite composite = new Composite(mapperShell, SWT.NONE);
        // composite.setBackground(display.getSystemColor(SWT.COLOR_YELLOW));

        Composite editorContainer = new Composite(mapperShell, SWT.NONE);
        editorContainer.setLayoutData(new GridData(GridData.FILL_BOTH));
        editorContainer.setLayout(new FillLayout());

        XmlMapEditor editor = new XmlMapEditor();
        editor.createPartControl(editorContainer);
        editor.setContent(copyOfMapData);
        // editor.setContent(getContents());

        FooterComposite footerComposite = new FooterComposite(mapperShell, this);
        footerComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        mapperShell.open();
        return mapperShell;

    }

    public void closeMapperDialog(int response) {
        mapperResponse = response;
        if (response == SWT.OK || response == SWT.APPLICATION_MODAL) {
            mapperComponent.setEmfMapData(copyOfMapData);
            if (response == SWT.APPLICATION_MODAL) {
                IExternalNode externalNode = mapperComponent;
                IWorkbenchPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();

                if (externalNode != null && (part instanceof AbstractMultiPageTalendEditor)) {
                    INode node = externalNode.getOriginalNode();
                    if (node != null && node instanceof Node) {
                        Command cmd = new ExternalNodeChangeCommand((Node) node, externalNode);
                        CommandStack cmdStack = (CommandStack) part.getAdapter(CommandStack.class);
                        cmdStack.execute(cmd);
                    }
                }
            }
        }
        if (response == SWT.OK || response == SWT.CANCEL) {
            mapperShell.close();
        }

    }

    public int getMapperDialogResponse() {
        return mapperResponse;
    }

    public XmlMapComponent getMapperComponent() {
        return mapperComponent;
    }

    public void prepareModel(IODataComponentContainer ioDataContainer, List<IMetadataTable> outputMetadataTables) {

        if (mapperComponent.getEmfMapData() != null) {
            copyOfMapData = EcoreUtil.copy(mapperComponent.getEmfMapData());
        } else {
            copyOfMapData = XmlmapFactory.eINSTANCE.createXmlMapData();
        }
        prepareModelInputs(ioDataContainer.getInputs());
        prepareModelOutputs(ioDataContainer.getOuputs(), outputMetadataTables);
    }

    private void prepareModelInputs(List<IODataComponent> inputConn) {
        for (IODataComponent inData : inputConn) {
            String name = inData.getName();
            InputXmlTree inputTree = null;
            for (InputXmlTree in : copyOfMapData.getInputTrees()) {
                if (in.getName() != null && in.getName().equals(name)) {
                    inputTree = in;
                    break;
                }
            }
            if (inputTree == null) {
                inputTree = XmlmapFactory.eINSTANCE.createInputXmlTree();
                inputTree.setName(name);
                inputTree.setLookup(EConnectionType.FLOW_MAIN != inData.getConnection().getLineStyle());
            }
            copyOfMapData.getInputTrees().add(inputTree);

            if (inData.getTable() != null && inData.getTable().getListColumns() != null) {
                for (IMetadataColumn column : inData.getTable().getListColumns()) {
                    TreeNode found = null;
                    for (TreeNode node : inputTree.getNodes()) {
                        if (node.getName() != null && node.getName().equals(column.getLabel())) {
                            found = node;
                            break;
                        }
                    }
                    if (found == null) {
                        found = XmlmapFactory.eINSTANCE.createTreeNode();
                        found.setName(column.getLabel());
                        found.setType(column.getTalendType());
                        found.setNullable(column.isNullable());
                        found.setXpath(inputTree.getName() + XmlMapUtil.XPATH_SEPARATOR + found.getName());
                        inputTree.getNodes().add(found);
                    }
                }

            }

        }

    }

    private void prepareModelOutputs(List<IODataComponent> outputConn, List<IMetadataTable> outputMetadataTables) {
        for (IODataComponent outData : outputConn) {
            String name = outData.getName();
            OutputXmlTree outputTree = null;
            for (OutputXmlTree out : copyOfMapData.getOutputTrees()) {
                if (out.getName() != null && out.getName().equals(name)) {
                    outputTree = out;
                    break;
                }
            }
            if (outputTree == null) {
                outputTree = XmlmapFactory.eINSTANCE.createOutputXmlTree();
                outputTree.setName(name);
            }

            if (outData.getTable() != null && outData.getTable().getListColumns() != null) {
                for (IMetadataColumn column : outData.getTable().getListColumns()) {
                    OutputTreeNode found = null;
                    for (OutputTreeNode node : outputTree.getNodes()) {
                        if (node.getName() != null && node.getName().equals(column.getLabel())) {
                            found = node;
                            break;
                        }
                    }
                    if (found == null) {
                        found = XmlmapFactory.eINSTANCE.createOutputTreeNode();
                        found.setName(column.getLabel());
                        found.setType(column.getTalendType());
                        found.setNullable(column.isNullable());
                        found.setXpath(outputTree.getName() + XmlMapUtil.XPATH_SEPARATOR + found.getName());
                        outputTree.getNodes().add(found);
                    }
                }

            }
            copyOfMapData.getOutputTrees().add(outputTree);
        }
    }

    private XmlMapData getContents() {
        copyOfMapData = XmlmapFactory.eINSTANCE.createXmlMapData();
        InputXmlTree inputXmlTree = XmlmapFactory.eINSTANCE.createInputXmlTree();
        inputXmlTree.setName("input1");
        copyOfMapData.getInputTrees().add(inputXmlTree);

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
        copyOfMapData.getOutputTrees().add(outputXmlTree);

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
        //

        return copyOfMapData;
    }

}
