package com.quantum.wizards.sql;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author BC
 */
public class ShowSQLStatementWizardPage extends WizardPage {

	private Text text;
	private String sqlStatement;
	
	/**
	 * @param pageName
	 * @param title
	 * @param titleImage
	 */
	public ShowSQLStatementWizardPage(String pageName, String title,
			ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
	}
	
	/**
	 * @param pageName
	 */
	protected ShowSQLStatementWizardPage(String pageName) {
		super(pageName);
		setTitle("Final SQL Statement");
		setDescription("Review the final SQL Statement before executing it");
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label label = new Label(composite, SWT.NONE);
		label.setText("SQL Statement");
		
		this.text = new Text(composite, SWT.MULTI | SWT.READ_ONLY | SWT.BORDER);
		this.text.setText(this.sqlStatement == null ? "" : this.sqlStatement);
		this.text.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		setControl(composite);
	}
	public String getSQLStatement() {
		return this.sqlStatement;
	}
	public void setSQLStatement(String sqlStatement) {
		this.sqlStatement = sqlStatement;
		if (this.text != null) {
			this.text.setText(this.sqlStatement == null ? "" : this.sqlStatement);
		}
	}
}
