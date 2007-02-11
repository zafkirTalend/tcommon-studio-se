package com.quantum.wizards;

import com.quantum.IQuantumConstants;
import com.quantum.Messages;
import com.quantum.model.Bookmark;
import com.quantum.model.BookmarkCollection;
import com.quantum.model.JDBCDriver;
import com.quantum.view.JDBCDriverTableViewer;
import com.quantum.view.driver.DeleteDriverAction;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * @author BC
 */
public class JDBCDriverSelectionWizardPage extends PropertyChangeWizardPage implements ISelectionChangedListener {

	private JDBCDriverTableViewer tableViewer;
	
	private JDBCDriver driver = null;
	
	/**
	 * @param pageName
	 */
	public JDBCDriverSelectionWizardPage(String pageName) {
		super(pageName);

		setTitle(Messages.getString(getClass(), "title"));
		setDescription(Messages.getString(getClass(), "description"));
	}

	public void createControl(Composite parent) {
		setPageComplete(false);
		Composite container = createBasicContainer(parent, 1);
		GridLayout layout = new GridLayout(1, false);
		layout.numColumns = 2;

		container.setLayout(layout);
		
		this.tableViewer = new JDBCDriverTableViewer(container);
		GridData data = new GridData(GridData.FILL_BOTH);
		data.horizontalAlignment = GridData.FILL; 
		data.horizontalSpan = 2;

		this.tableViewer.getControl().setLayoutData(data);
		this.tableViewer.addSelectionChangedListener(this);
		
		Button newDriver = new Button(container, SWT.PUSH);
		newDriver.setText(Messages.getString(getClass(), "addDriver"));
		newDriver.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
		        AddDriverWizard wizard = new AddDriverWizard();
		        WizardDialog dialog =
		            new WizardDialog(getShell(), wizard);
		        dialog.open();
			}
		});
		Button deleteDriver = new Button(container, SWT.PUSH);
		deleteDriver.setText(Messages.getString(getClass(), "deleteDriver"));
		deleteDriver.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (driver == null) 
					MessageDialog.openInformation(
						      getShell(),Messages.getString(getClass(), "nothingToDeleteTitle"),Messages.getString(getClass(), "nothingToDeleteMsg")); //$NON-NLS-1$ //$NON-NLS-2$
				else {
					Bookmark[] bookmarks = BookmarkCollection.getInstance().getBookmarksUsingDriver(driver);
					if (bookmarks.length > 0) {
						String bookmarksMessage = "";
						for (int i = 0; i < bookmarks.length; i++) {
							bookmarksMessage += "\n" + bookmarks[i].getName();
						} 
						MessageDialog.openInformation(
								getShell(),Messages.getString(getClass(), "driverUsedTitle"),Messages.getString(getClass(), "driverUsedMsg") + //$NON-NLS-1$ //$NON-NLS-2$
								bookmarksMessage	);			
					} else {
		
						MessageDialog confirmDialog = new MessageDialog(
								getShell(),Messages.getString(getClass(), "confirmDeleteTitle"),null, Messages.getString(getClass(), "confirmDeleteMsg") + //$NON-NLS-1$ //$NON-NLS-2$
								driver.getDisplayName(), MessageDialog.QUESTION, new String[]{Messages.getString(getClass(), "confirmDeleteButton"), Messages.getString(getClass(), "cancelDeleteButton")}, 1 ); //$NON-NLS-1$ //$NON-NLS-2$
						// 0 is the index of the first button of the MessageDialog (Delete)
						if (confirmDialog.open() == 0) {  
							if (!BookmarkCollection.getInstance().removeDriver(driver)) {
								MessageDialog.openWarning(getShell(), 
										Messages.getString(DeleteDriverAction.class, "failed"),
										Messages.getString(DeleteDriverAction.class, "cant"));
							} 
						}
					}	
				}
			}
		});
		data = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.VERTICAL_ALIGN_BEGINNING);
		newDriver.setLayoutData(data);
		
		setControl(container);
	}
	/**
	 * @param parent
	 */
	private Composite createBasicContainer(Composite parent, int numberOfColumns) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = numberOfColumns;
		layout.verticalSpacing = 9;
		GridData fullHorizontal = new GridData(GridData.FILL_BOTH);
		container.setLayoutData(fullHorizontal);
		return container;
	}
	public void selectionChanged(SelectionChangedEvent event) {
		IStructuredSelection selection = (IStructuredSelection) event.getSelection();
		setPageComplete(!selection.isEmpty());
		if (!selection.isEmpty()) {
			setDriver((JDBCDriver) selection.getFirstElement());
		}
		
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
		if (this.driver != driver) {
			JDBCDriver original = this.driver;
			this.driver = driver;
			firePropertyChange(IQuantumConstants.DRIVER_PROPERTY, original, driver); //$NON-NLS-1$
		}
	}
	public void dispose() {
		this.tableViewer.dispose();
		super.dispose();
	}
}