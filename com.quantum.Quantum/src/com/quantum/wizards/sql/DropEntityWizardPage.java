package com.quantum.wizards.sql;

import com.quantum.sql.parser.DropEntityStatement;
import com.quantum.wizards.PropertyChangeWizardPage;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


/**
 * @author BC Holmes
 */
public class DropEntityWizardPage extends PropertyChangeWizardPage {

	private final DropEntityStatement dropEntityStatement;

	/**
	 * @param pageName
	 */
	public DropEntityWizardPage(String pageName, DropEntityStatement dropEntityStatement) {
		super(pageName);
		this.dropEntityStatement = dropEntityStatement;
		setTitle("Drop Parameters");
		setDescription("Choose your drop parameters");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label label = new Label(composite, SWT.NONE);
		label.setText("Entity:");
		
		Text text = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		text.setText(this.dropEntityStatement.getTableName());
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		label = new Label(composite, SWT.NONE);
		label.setText("How to handle dependent entities:");
		
		final Combo combo = new Combo(composite, SWT.READ_ONLY);
		combo.setItems(new String[] { "", "CASCADE", "RESTRICT" });
		combo.select(0);
		combo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		combo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				String rule = combo.getItem(combo.getSelectionIndex());
				dropEntityStatement.setDependentRule(rule);
				
				firePropertyChange("sqlStatement", null, dropEntityStatement.toString());
			}
		});
		
		setControl(composite);
	}
}
