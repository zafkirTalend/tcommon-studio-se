package com.quantum.wizards;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.quantum.IQuantumConstants;
import com.quantum.Messages;
import com.quantum.model.JDBCDriver;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


class BookmarkConnectionWizardPage extends PropertyChangeWizardPage {
	
	/* use this to paint a more helpful UI for the JDBC URL */
	private JDBCDriver driver;
	private String userid;
	private String password;
	private String connectionURL;
    private boolean prompt;
    
    private Label jdbcLabel;
    private Text jdbcUrl;
    private URLSetupControl urlSetupControl;
    private Composite container;
    private boolean requiresRebuild = false;
    
    private PropertyChangeListener listener = new PropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent event) {
			if (IQuantumConstants.CONNECTION_URL_PROPERTY.equals(event.getPropertyName())) {
				BookmarkConnectionWizardPage.this.setConnectionURL((String) event.getNewValue());
				BookmarkConnectionWizardPage.this.updateButtonState();
			}
		}
    }; 
    
    /**
	 * Constructor for BookmarkPage.
	 * @param pageName
	 */
	public BookmarkConnectionWizardPage(String pageName) {
		super(pageName);
		setTitle(Messages.getString(getClass(), "title"));
		setDescription(Messages.getString(getClass(), "description"));
	}
	public void createControl(Composite parent) {
		setPageComplete(false);
		
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
		layout.verticalSpacing = 9;

		Label label = new Label(container, SWT.NULL);
		label.setText(Messages.getString(getClass(), "userid")); //$NON-NLS-1$
		Text username = new Text(container, SWT.BORDER | SWT.SINGLE);
		
		
 		GridData fullHorizontal = new GridData(GridData.FILL_HORIZONTAL);
		username.setLayoutData(fullHorizontal);
		username.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent event) {
				String userid = ((Text) event.getSource()).getText();
				setUserid(userid);
				updateButtonState();
			}
		});

		label = new Label(container, SWT.NULL);
		label.setText(Messages.getString(getClass(), "password")); //$NON-NLS-1$
		final Text password = new Text(container, SWT.BORDER | SWT.SINGLE);
		password.setEchoChar('*');
 		fullHorizontal = new GridData(GridData.FILL_HORIZONTAL);
		password.setLayoutData(fullHorizontal);
		password.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent event) {
				String password = ((Text) event.getSource()).getText();
				setPassword(password);
				updateButtonState();
			}
		});

        Button prompt = new Button(container, SWT.CHECK);
		prompt.setText(Messages.getString(getClass(), "prompt")); //$NON-NLS-1$
		fullHorizontal = new GridData(GridData.FILL_HORIZONTAL);
 		fullHorizontal.horizontalSpan = 2;
        prompt.setLayoutData(fullHorizontal);
 
		createStandardJDBCWidgets(container);
        prompt.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
            	Button prompt = ((Button) event.getSource());
                password.setEditable(!prompt.getSelection());
                setPrompt(prompt.getSelection());
                updateButtonState();
            }
        });

        this.container = container;
		setControl(container);
	}
	
	public void setVisible(boolean visible) {
		if (visible && this.requiresRebuild) {
			rebuildJDBCControls(this.driver);
		}
		super.setVisible(visible);
	}
	
	/**
	 * @param container
	 */
	private void createStandardJDBCWidgets(Composite container) {
		setConnectionURL("");
		
		this.jdbcLabel = new Label(container, SWT.NULL);
		this.jdbcLabel.setText(Messages.getString(getClass(), "url")); //$NON-NLS-1$
		
		this.jdbcUrl = new Text(container, SWT.BORDER | SWT.SINGLE);
 		this.jdbcUrl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
 		this.jdbcUrl.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent event) {
				setConnectionURL(((Text) event.getSource()).getText());
				updateButtonState();
			}
		});
 		
 		updateButtonState();
	}
	/**
	 * @return Returns the driver.
	 */
	public JDBCDriver getDriver() {
		return this.driver;
	}
	/**
	 * @param driver The driver to set.
	 */
	public void setDriver(JDBCDriver driver) {
		String oldDriverClassName = this.driver == null ? null : this.driver.getClassName();
		this.driver = driver;
		
		if (oldDriverClassName == null 
				|| !oldDriverClassName.equals(this.driver.getClassName())) {
			this.requiresRebuild = true;
		}
	}
	/**
	 * 
	 */
	private void rebuildJDBCControls(JDBCDriver driver) {
        Point windowSize = getShell().getSize();
        Point oldSize = getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT);
		
		if (URLSetupControlFactory.hasControl(driver)) {
			disposeOfCurrentJDBCControls();
			
			this.urlSetupControl = URLSetupControlFactory.create(driver, this.container);
			GridData data = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
			data.horizontalSpan = 2;
			this.urlSetupControl.setLayoutData(data);
			
			this.urlSetupControl.addPropertyChangeListener(this.listener);
			
			setConnectionURL(this.urlSetupControl.getConnectionURL());
			updateButtonState();

	        resizeWindow(windowSize, oldSize);
			this.container.layout();
			
		} else if (this.jdbcLabel == null || this.jdbcUrl == null) {
			
			disposeOfCurrentJDBCControls();
			createStandardJDBCWidgets(this.container);

	        resizeWindow(windowSize, oldSize);
	        
			this.container.layout();
		}
		this.container.setVisible(true);
		this.container.redraw();
	}
	
	/**
	 * @param windowSize
	 * @param oldSize
	 */
	private void resizeWindow(Point windowSize, Point oldSize) {
		Point newSize = getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT);
		if (newSize.y > windowSize.y) {
			getShell().setSize(
			    new Point(windowSize.x, windowSize.y + (newSize.y - oldSize.y)));
		}
	}
	private void disposeOfCurrentJDBCControls() {
		if (this.jdbcUrl != null) {
			this.jdbcUrl.dispose();
			this.jdbcUrl = null;
		}
		if (this.jdbcLabel != null) {
			this.jdbcLabel.dispose();
			this.jdbcLabel = null;
		}
		if (this.urlSetupControl != null) {
			this.urlSetupControl.removePropertyChangeListener(this.listener);
			this.urlSetupControl.dispose();
			this.urlSetupControl = null;
		}
	}
	/**
	 * 
	 */
	private void updateButtonState() {
		boolean complete = true;
		complete &= (this.connectionURL != null 
				&& this.connectionURL.trim().length() > 0);
		// Some databases don't use user id
		//complete &= (this.userid != null 
		//		&& this.userid.trim().length() > 0);
		setPageComplete(complete);
	}
	/**
	 * @return Returns the userid.
	 */
	public String getUserid() {
		return this.userid;
	}
	/**
	 * @param userid The userid to set.
	 */
	public void setUserid(String userid) {
		if (userid != null && !userid.equals(this.userid)) {
			String original = this.userid;
			this.userid = userid;
			firePropertyChange(IQuantumConstants.USERID_PROPERTY, original, userid);
		}
	}
	/**
	 * @return Returns the prompt.
	 */
	public boolean isPrompt() {
		return this.prompt;
	}
	/**
	 * @param prompt The prompt to set.
	 */
	public void setPrompt(boolean prompt) {
		if (this.prompt != prompt) {
			boolean original = this.prompt;
			this.prompt = prompt;
			firePropertyChange(IQuantumConstants.PROMPT_PROPERTY, original, prompt);
		}
	}
	/**
	 * @return Returns the connectionURL.
	 */
	public String getConnectionURL() {
		return this.connectionURL;
	}
	/**
	 * @param connectionURL The connectionURL to set.
	 */
	public void setConnectionURL(String connectionURL) {
		if (connectionURL != null && !connectionURL.equals(this.connectionURL)) {
			String original = this.connectionURL;
			this.connectionURL = connectionURL;
			firePropertyChange(IQuantumConstants.CONNECTION_URL_PROPERTY, original, connectionURL);
		}
	}
	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return this.password;
	}
	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		if (password != null && !password.equals(this.password)) {
			String original = this.password;
			this.password = password;
			firePropertyChange(IQuantumConstants.PASSWORD_PROPERTY, original, password);
		}
	}
}