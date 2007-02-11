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
public class BasicThreePartURLSetupControl extends URLSetupControl {
	
	public BasicThreePartURLSetupControl(Composite parent, JDBCDriver driver) {
		super(parent, driver);
	}

	protected void createPart(Composite parent) {
		
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		parent.setLayout(layout);
		
		Label label = new Label(parent, SWT.NONE);
		label.setText(Messages.getString(URLSetupControl.class, "hostname"));
		
		Text hostNameText = new Text(parent, SWT.BORDER | SWT.SINGLE);
		hostNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		hostNameText.setText(getProperty("hostname"));

		label = new Label(parent, SWT.NONE);
		label.setText(Messages.getString(URLSetupControl.class, "port"));
		
		Text portText = new Text(parent, SWT.BORDER | SWT.SINGLE);
		portText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		portText.setText(getProperty("port"));

		label = new Label(parent, SWT.NONE);
		label.setText(Messages.getString(URLSetupControl.class, "dbname"));
		
		Text databaseNameText = new Text(parent, SWT.BORDER | SWT.SINGLE);
		databaseNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		databaseNameText.setText(getProperty("dbname"));
		label = new Label(parent, SWT.NONE);
		label.setText(Messages.getString(URLSetupControl.class, "url"));
		
		final Text urlText = new Text(parent, SWT.BORDER | SWT.SINGLE);
		urlText.setEditable(false);
		urlText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		urlText.setText(getConnectionURL());

 		hostNameText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent event) {
				putProperty("hostname", ((Text) event.getSource()).getText());
				urlText.setText(getConnectionURL());
			}
		});

 		portText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent event) {
				putProperty("port", ((Text) event.getSource()).getText());
				urlText.setText(getConnectionURL());
			}
		});

 		databaseNameText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent event) {
				putProperty("dbname", ((Text) event.getSource()).getText());
				urlText.setText(getConnectionURL());
			}
		});
	}
}
