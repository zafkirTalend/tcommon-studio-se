package com.quantum.view.driver;


import com.quantum.view.JDBCDriverTableViewer;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.ViewPart;


/**
 * @author BC
 */
public class JDBCDriverView extends ViewPart {
	
	private JDBCDriverTableViewer viewer;
	private JDBCDriverViewActionGroup actionGroup;

	public void createPartControl(Composite parent) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		parent.setLayout(layout);
		this.viewer = new JDBCDriverTableViewer(parent);
		this.viewer.getControl().setLayoutData(new GridData(GridData.FILL_BOTH));
		
		initActions();
	}

	public void initActions() {
		
        this.actionGroup = new JDBCDriverViewActionGroup(this, this.viewer);

        IActionBars actionBars = getViewSite().getActionBars();
        this.actionGroup.fillActionBars(actionBars);
	}
	
	public void setFocus() {
	}
	public void dispose() {
		this.viewer.dispose();
		super.dispose();
	}
}
