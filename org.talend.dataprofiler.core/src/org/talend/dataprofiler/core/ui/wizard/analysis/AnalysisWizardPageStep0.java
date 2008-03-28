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

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
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
    
    private NamedViewerFilter filter = new NamedViewerFilter();

    public AnalysisWizardPageStep0(ConnectionParameters connectionParams) {
        super("WizardPage");      
        setPageComplete(false);
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

        Label nameLabel = new Label(container, SWT.NONE);
        nameLabel.setText("Wizards:");

        typeName = new Text(container, SWT.BORDER);
        typeName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        typeName.setText(defaultValue);
        typeName.setSelection(new Point(0, typeName.getText().length()));

        createTypeTree(container);
        addListeners();

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

    }
    
    protected void addListeners() {
        
        typeName.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                String filterString = ((Text) e.getSource()).getText();
                if (filterString != "") {
                    filter.setTypeName(filterString);
                    analysisTypes.addFilter(filter);
                } else {
                    ViewerFilter[] filters = analysisTypes.getFilters();
                    for (ViewerFilter one : filters) {
                        analysisTypes.removeFilter(one);
                    }
                    analysisTypes.refresh(true);
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
        
        analysisTypes.addSelectionChangedListener(new ISelectionChangedListener() {

            public void selectionChanged(SelectionChangedEvent event) {
                AnalysisTypeNode node = (AnalysisTypeNode) ((IStructuredSelection) event.getSelection()).getFirstElement();
                
                if (node.getParent() != null) {
                    setMessage(node.getLiteral());
                    setPageComplete(true);
                    //set parameter
                    connectionParams.setConnectionTypeForANA(((AnalysisTypeNode) node.getParent()).getName());
                } else {
                    setPageComplete(false);
                }
            }

        });
        
        analysisTypes.addDoubleClickListener(new IDoubleClickListener() {

            public void doubleClick(DoubleClickEvent event) {
                
                AnalysisTypeNode node = (AnalysisTypeNode) ((IStructuredSelection) event.getSelection()).getFirstElement();
                if (node.getParent() == null) {
                    analysisTypes.expandToLevel(node, 1);
                }
            }
            
        });
    }

    
    public String getTypeName() {
        return typeName.getText();
    }
}
