package com.quantum.properties;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PropertyPage;

import com.quantum.IQuantumConstants;
import com.quantum.adapters.AdapterFactory;
import com.quantum.model.Bookmark;
import com.quantum.model.BookmarkHolder;
import com.quantum.model.JDBCDriver;
import com.quantum.wizards.JDBCDriverSelectionWizardPage;

public class BookmarkPropertyPage extends PropertyPage {
	
	class ChooseDriverWizard extends Wizard {

		private JDBCDriver selection;
		private JDBCDriverSelectionWizardPage page;
		private PropertyChangeListener listener = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				if (IQuantumConstants.DRIVER_PROPERTY.equals(event.getPropertyName())) {
					ChooseDriverWizard.this.selection = (JDBCDriver) event.getNewValue();
				}
			}
		};
		
		public void addPages() {
			this.page = new JDBCDriverSelectionWizardPage("page1");
			this.page.addPropertyChangeListener(this.listener);
			addPage(this.page);
		}
		
		public void dispose() {
			this.page.removePropertyChangeListener(this.listener);
			super.dispose();
		}
		public boolean performFinish() {
			BookmarkPropertyPage.this.driver = this.selection;
			BookmarkPropertyPage.this.setDriverDetails();
			return true;
		}
		
	}
	
    
    private Text password;
    private Text userid;
    private Button prompt;
    
    private Text jdbcURL;
    private Text driverName;
    private Text driverPath;
    private Text driverClassName;
    private Text driverVersion;
    private Text type;
    
	private Combo autoCommit;
	private Button quoteAll;
	private Button ignoreQueryViewSetting;
	private JDBCDriver driver;
	private Button stripNewline;
	private Button sendQueryAsIs;
	private Combo defaultEncoding;

    protected Control createContents(Composite parent) {

        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        composite.setLayout(layout);
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label nameLabel = new Label(composite, SWT.NONE);
        nameLabel.setText("Name:");

        Label name = new Label(composite, SWT.NONE);

        Bookmark bookmark = getBookmark();
        String description = bookmark.getName();
        name.setText(description);
        name.setLayoutData(
        		new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING 
        				| GridData.VERTICAL_ALIGN_BEGINNING));
        
        TabFolder tabFolder = new TabFolder(composite, SWT.NONE);
        layout = new GridLayout();
        tabFolder.setLayout(layout);
        GridData data = new GridData(GridData.FILL_BOTH);
        data.horizontalSpan = 2;
        tabFolder.setLayoutData(data);
        
        createConnectionTab(tabFolder);
        createDriverTab(tabFolder);
        createOptionsTab(tabFolder);
        
        performDefaults();
        return composite;
	}

 	private Bookmark getBookmark() {
        Bookmark bookmark =
            ((BookmarkHolder) getElement()).getBookmark();
        return bookmark;
    }

    private void createDriverTab(TabFolder tabFolder) {
        TabItem driverTab = new TabItem(tabFolder, SWT.NONE);
        driverTab.setText("JDBC Driver");
        
        Composite composite = new Composite(tabFolder, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        composite.setLayout(layout);
        composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        Label label = new Label(composite, SWT.NONE);
        label.setText("Driver Name:");

        this.driverName = new Text(composite, SWT.BORDER);
        this.driverName.setLayoutData(createFillHorizontalGridData());
        this.driverName.setEditable(false);

        label = new Label(composite, SWT.NONE);
        label.setText("Driver Class Name:");

        this.driverClassName = new Text(composite, SWT.BORDER);
        this.driverClassName.setLayoutData(createFillHorizontalGridData());
        this.driverClassName.setEditable(false);

        label = new Label(composite, SWT.NONE);
        label.setText("Driver Version:");

        this.driverVersion = new Text(composite, SWT.BORDER);
        this.driverVersion.setLayoutData(createFillHorizontalGridData());
        this.driverVersion.setEditable(false);

        label = new Label(composite, SWT.NONE);
        label.setText("Driver Path:");

        this.driverPath = new Text(composite, SWT.BORDER);
        this.driverPath.setLayoutData(createFillHorizontalGridData());
        this.driverPath.setEditable(false);

        label = new Label(composite, SWT.NULL);
        label.setText("Type:");
        this.type = new Text(composite, SWT.BORDER);
        this.type.setLayoutData(createFillHorizontalGridData());
        this.type.setEditable(false);

        driverTab.setControl(composite);
        
        Button button = new Button(composite, SWT.PUSH);
        button.setText("Change");
        GridData data = new GridData(GridData.HORIZONTAL_ALIGN_END);
        data.horizontalSpan = 2;
        button.setLayoutData(data);
        button.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent event) {
		        WizardDialog dialog = 
		        	new WizardDialog(getShell(), new ChooseDriverWizard());
		        dialog.open();
			}
			public void widgetDefaultSelected(SelectionEvent event) {
			}
        });
    }
    
    private GridData createFillHorizontalGridData() {
    	GridData data = new GridData(GridData.FILL_HORIZONTAL);
    	data.widthHint = 200;
    	return data;
    }

    private void createConnectionTab(TabFolder tabFolder) {
        TabItem userTab = new TabItem(tabFolder, SWT.NONE);
        userTab.setText("Connection");
        
        Composite composite = new Composite(tabFolder, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        composite.setLayout(layout);
        GridData data = new GridData(GridData.FILL);
        data.verticalAlignment = GridData.VERTICAL_ALIGN_BEGINNING;
        data.grabExcessHorizontalSpace = true;
        composite.setLayoutData(data);
        
        Label useridLabel = new Label(composite, SWT.NONE);
        useridLabel.setText("Userid:");

        this.userid = new Text(composite, SWT.BORDER);
        data = new GridData(GridData.FILL);
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        data.grabExcessHorizontalSpace = true;
        this.userid.setLayoutData(data);
        
        Label passworLabel = new Label(composite, SWT.NONE);
        passworLabel.setText("Password:");

        this.password = new Text(composite, SWT.BORDER);
        this.password.setEchoChar('*');
        data = new GridData(GridData.FILL);
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        this.password.setLayoutData(data);

        this.prompt = new Button(composite, SWT.CHECK);
        this.prompt.setText("Prompt for password");
        data = new GridData(GridData.FILL);
        data.horizontalSpan = 2;
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        this.prompt.setLayoutData(data);
        
        this.prompt.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                password.setEditable(!((Button) event.getSource()).getSelection()); 
            }
        });
        
        Label label = new Label(composite, SWT.NONE);
        label.setText("Connection URL:");

        this.jdbcURL = new Text(composite, SWT.BORDER);
        data = new GridData(GridData.FILL);
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        data.grabExcessHorizontalSpace = true;
        this.jdbcURL.setLayoutData(data);
        
        userTab.setControl(composite);
    }

	/**
	  * @param tabFolder
	  */
	 private void createOptionsTab(TabFolder tabFolder) {
		TabItem optionsTab = new TabItem(tabFolder, SWT.NONE);
		optionsTab.setText("Options");
        
		Composite composite = new Composite(tabFolder, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		GridData data = new GridData(GridData.FILL);
		data.verticalAlignment = GridData.VERTICAL_ALIGN_BEGINNING;
		data.grabExcessHorizontalSpace = true;
		composite.setLayoutData(data);
        
		Label autocommitLabel = new Label(composite, SWT.NULL);
		autocommitLabel.setText("On connection, Auto-Commit should be:");
		this.autoCommit = new Combo(composite, SWT.SIMPLE | SWT.DROP_DOWN | SWT.READ_ONLY);
		String autoCommitTypes[] = new String[] {
			IQuantumConstants.autoCommitTrue, 
			IQuantumConstants.autoCommitFalse,
			IQuantumConstants.autoCommitSaved
		};
		this.autoCommit.setItems(autoCommitTypes);

		Label ignoreQueryViewSettingLabel = new Label (composite, SWT.NULL);
		ignoreQueryViewSettingLabel.setText("Ignore the Autocommit setting of the Query View:");
		this.ignoreQueryViewSetting = new Button(composite, SWT.CHECK);
        
		Label quoteAllLabel = new Label (composite, SWT.NULL);
		quoteAllLabel.setText("Quote all schemas, tables and columns:");
		this.quoteAll = new Button(composite, SWT.CHECK);
        
        Label stripNewlines = new Label (composite, SWT.NULL);
		stripNewlines.setText("Strip new lines when sending to JDBC:");
		this.stripNewline = new Button(composite, SWT.CHECK);
        
        Label sendQueryAsIs = new Label (composite, SWT.NULL);
		sendQueryAsIs.setText("Send query text as-is to the JDBC:");
		this.sendQueryAsIs = new Button(composite, SWT.CHECK);
          
        Label defaultEncoding = new Label (composite, SWT.NULL);
		defaultEncoding.setText("Default encoding:");
		this.defaultEncoding = new Combo(composite, SWT.SIMPLE | SWT.DROP_DOWN | SWT.READ_ONLY);
		SortedMap mapCharsets = Charset.availableCharsets();
	    Set setCharsets = mapCharsets.keySet();
	    this.defaultEncoding.add("");
		for (Iterator iter = setCharsets.iterator(); iter.hasNext();) {
	         String key = (String) iter.next();
	         this.defaultEncoding.add(key);
		}

        data = new GridData();
		data.horizontalAlignment = GridData.FILL;
		this.autoCommit.setLayoutData(data);

		optionsTab.setControl(composite);
	 }



    /**
     * @see org.eclipse.jface.preference.PreferencePage#performApply()
     */
    public boolean performOk() {
        Bookmark bookmark = getBookmark();
        bookmark.setUsername(this.userid.getText());
        bookmark.setPromptForPassword(this.prompt.getSelection());
        if (this.prompt.getSelection()) {
            bookmark.setPassword("");
        } else {
            bookmark.setPassword(this.password.getText());
        }
        bookmark.setConnect(this.jdbcURL.getText());
        bookmark.setSendQueryAsIs(sendQueryAsIs.getSelection());
        bookmark.setStripNewline(stripNewline.getSelection());
        bookmark.setQuoteAll(quoteAll.getSelection());
        bookmark.setIgnoreQueryViewSetting(ignoreQueryViewSetting.getSelection());
        
		bookmark.setJDBCDriver(this.driver);
        if (this.autoCommit.getSelectionIndex() >= 0)
        	bookmark.setAutoCommitPreference(this.autoCommit.getItem(this.autoCommit.getSelectionIndex()));
        if (this.defaultEncoding.getSelectionIndex() >= 0)
        	bookmark.setDefaultEncoding(this.defaultEncoding.getItem(this.defaultEncoding.getSelectionIndex()));
        return super.performOk();
    }
    /* (non-Javadoc)
     * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
     */
    protected void performDefaults() {
        super.performDefaults();
        Bookmark bookmark = getBookmark();

        this.prompt.setSelection(bookmark.getPromptForPassword());
        this.password.setEditable(!bookmark.getPromptForPassword());
        String password = bookmark.getPassword();
        this.password.setText(password == null ? "" : password);
        this.userid.setText(bookmark.getUsername());
        this.jdbcURL.setText(bookmark.getConnect());

        this.driver = bookmark.getJDBCDriver();
        
        setDriverDetails();
        
        if (bookmark.getAutoCommitPreference().equals(IQuantumConstants.autoCommitTrue))
        	this.autoCommit.select(0);
        else if (bookmark.getAutoCommitPreference().equals(IQuantumConstants.autoCommitFalse))
			this.autoCommit.select(1);
		else if (bookmark.getAutoCommitPreference().equals(IQuantumConstants.autoCommitSaved))
			this.autoCommit.select(2);
        
        this.defaultEncoding.setText(bookmark.getDefaultEncoding() == null ? "" : bookmark.getDefaultEncoding());
		
		this.quoteAll.setSelection(bookmark.isQuoteAll());
		this.ignoreQueryViewSetting.setSelection(bookmark.isIgnoreQueryViewSetting());
		this.stripNewline.setSelection(bookmark.isStripNewline());
		this.sendQueryAsIs.setSelection(bookmark.isSendQueryAsIs());
    }

	/**
	 * 
	 */
	private void setDriverDetails() {
		this.driverName.setText(this.driver.getName());
        this.driverClassName.setText(this.driver.getClassName());
        String path = this.driver.getJarFilePath();
        this.driverPath.setText(path == null ? "" : path);
        String version = this.driver.getVersion();
        this.driverVersion.setText(version == null ? "" : version);
        this.type.setText(AdapterFactory.getInstance().getAdapter(this.driver.getType()).getDisplayName());
	}
}
