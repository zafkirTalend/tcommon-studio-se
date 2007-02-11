package com.quantum.wizards;

import com.quantum.Messages;
import com.quantum.model.JDBCDriver;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


/**
 * @author BC
 */
public class BasicOnePartURLSetupControl extends URLSetupControl {
	
	private final String propertyName;

	public BasicOnePartURLSetupControl(Composite parent, JDBCDriver driver, String propertyName) {
		super(parent, driver);
		this.propertyName = propertyName;
	}

	protected void createPart(Composite parent) {
		
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		parent.setLayout(layout);
		
		Label label = new Label(parent, SWT.NONE);
		label.setText(Messages.getString(URLSetupControl.class, this.propertyName));
		
		Text databaseNameText = new Text(parent, SWT.BORDER | SWT.SINGLE);
		databaseNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		databaseNameText.setText(getProperty(this.propertyName));
		label = new Label(parent, SWT.NONE);
		label.setText(Messages.getString(URLSetupControl.class, "url"));
		
		final Text urlText = new Text(parent, SWT.BORDER | SWT.SINGLE);
		urlText.setEditable(false);
		urlText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		urlText.setText(getConnectionURL());

 		databaseNameText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent event) {
				putProperty(
						BasicOnePartURLSetupControl.this.propertyName, 
						((Text) event.getSource()).getText());
				urlText.setText(getConnectionURL());
			}
		});
	}
}
