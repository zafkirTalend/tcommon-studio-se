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

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.talend.cwm.management.connection.ConnectionParameters;
import org.talend.dataprofiler.core.model.nodes.analysis.AnalysisDataFactory;
import org.talend.dataprofiler.core.model.nodes.analysis.AnalysisTypeNode;
import org.talend.dataprofiler.core.ui.wizard.analysis.filter.NamedViewerFilter;
import org.talend.dataprofiler.core.ui.wizard.analysis.provider.AnalysisTypeContentProvider;
import org.talend.dataprofiler.core.ui.wizard.analysis.provider.AnalysisTypeLabelProvider;

/**
 * @author zqin
 * 
 */
public class AnalysisWizardPageStep0 extends WizardPage {
    
    private final String defaultValue = "type filter text";

    private Text typeName;

    private TreeViewer analysisTypes;
    
    private ConnectionParameters connectionParams;

    public AnalysisWizardPageStep0(ConnectionParameters connectionParams) {
        super("WizardPage");      
        setTitle("Select a wizard");
        setMessage("Create a new Analysis");
        
        this.connectionParams = connectionParams;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {

        Composite container = new Composite(parent, SWT.NONE);
        GridLayout gdLayout = new GridLayout(1, true);
        container.setLayout(gdLayout);
        container.setSize(200, 300);

        Label nameLabel = new Label(container, SWT.NONE);
        nameLabel.setText("Wizards:");

        typeName = new Text(container, SWT.BORDER);
        typeName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        typeName.setText(defaultValue);

        typeName.addKeyListener(new KeyAdapter() {

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
             */
            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub
                String filterString = ((Text) e.getSource()).getText();
                if (filterString != "") {
                    analysisTypes.addFilter(new NamedViewerFilter(filterString));
                }
            }
        });

        typeName.addMouseListener(new MouseAdapter() {

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.swt.events.MouseAdapter#mouseDown(org.eclipse.swt.events.MouseEvent)
             */
            @Override
            public void mouseDown(MouseEvent e) {

                typeName.setText("");
            }

        });

        createTypeTree(container);

        setControl(container);

    }

    public void createTypeTree(Composite parent) {

        Composite treeContainer = new Composite(parent, SWT.NONE);
        treeContainer.setLayoutData(new GridData(GridData.FILL_BOTH));
        treeContainer.setLayout(new FillLayout());

        ILabelProvider labelProvider = new AnalysisTypeLabelProvider();
        ITreeContentProvider contentProvider = new AnalysisTypeContentProvider();

        analysisTypes = new TreeViewer(treeContainer, SWT.BORDER);
        analysisTypes.setContentProvider(contentProvider);
        analysisTypes.setLabelProvider(labelProvider);

        analysisTypes.setInput(AnalysisDataFactory.createTreeData());

        analysisTypes.addSelectionChangedListener(new ISelectionChangedListener() {

            public void selectionChanged(SelectionChangedEvent event) {
                // TODO Auto-generated method stub
                AnalysisTypeNode node = (AnalysisTypeNode) ((IStructuredSelection) getSelection()).getFirstElement();
                if (node.getParent() != null) {
                    String parentType = ((AnalysisTypeNode) node.getParent()).getName();
                    String type = node.getName();
                    if (node.getName().equals("Default")) {
                        typeName.setText(parentType);
                    } else {
                        typeName.setText(parentType + "." + type);
                    }
                    
                    //set parameter
                    connectionParams.setConnectionTypeForANA(typeName.getText());
                }
            }

        });

    }

    /**
     * @return the selection
     */
    public ISelection getSelection() {
        return this.analysisTypes.getSelection();
    }

    /**
     * @param selection the selection to set
     */
    public void setSelection(ISelection selection) {
        this.analysisTypes.setSelection(selection);
    }
    
    public String getTypeName(){
        return typeName.getText();
    }
}
