package com.quantum.wizards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.QuantumPlugin;
import com.quantum.adapters.AdapterFactory;
import com.quantum.adapters.DatabaseAdapter;
import com.quantum.model.BookmarkCollection;
import com.quantum.model.JDBCDriver;
import com.quantum.ui.dialog.SimpleSelectionDialog;
import com.quantum.util.JarUtil;
import com.quantum.view.widget.ComboViewer;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

/**
 * Create a new driver Wizard.
 * 
 * @author BC Holmes
 * @author Sirkware
 */
public class AddDriverWizard extends Wizard {
	
	public class LabelProviderImpl extends LabelProvider {
		
		public Image getImage(Object element) {
			return ImageStore.getImage(ImageStore.EXTERNAL_JAR);
		}
	}
	
	public class ContentProviderImpl implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof List) {
				return ((List) inputElement).toArray();
			} else if (inputElement instanceof Object[]){
				return (Object[]) inputElement;
			} else {
				return null;
			}
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}
	
	public class PageImpl extends WizardPage {

		private FileDialog fileDialog;

		private Text driverClassName;
		private ComboViewer type;
		private TableViewer driverFiles;
		private DatabaseAdapter[] adapters = AdapterFactory.getInstance().getDriverList();

		public PageImpl() {
			super("");
		}
		public void createControl(Composite parent) {
			setPageComplete(false);

			setTitle(Messages.getString(getClass(), "title"));
			setDescription(Messages.getString(getClass(), "description"));
			
	    	parent.setLayout(new GridLayout());
	        Composite composite = new Composite(parent, SWT.NONE);
			GridLayout layout = new GridLayout();
			composite.setLayout(layout);
			layout.numColumns = 3;
			composite.setLayoutData(new GridData(GridData.FILL_BOTH));
			
	
			this.fileDialog = new FileDialog(composite.getShell(), SWT.OPEN);
			this.fileDialog.setFilterExtensions(new String[] { "*.jar", "*.zip", "*.*" });
			this.fileDialog.setFilterNames(new String[] {
					Messages.getString("BookmarkWizard.JarFiles"),
					Messages.getString("BookmarkWizard.ZipFiles"),
					Messages.getString("BookmarkWizard.AllFiles") });
	
			Label label = new Label(composite, SWT.NULL);
			label.setText(Messages.getString(AddDriverWizard.class, "jars"));
			GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
			gridData.horizontalSpan = 3;
			label.setLayoutData(gridData);
			
			Table table = new Table(composite, SWT.BORDER);
			gridData = new GridData(GridData.FILL_BOTH);
			gridData.horizontalSpan = 2;
			table.setLayoutData(gridData);
			this.driverFiles = new TableViewer(table);
			this.driverFiles.setLabelProvider(new LabelProviderImpl());
			this.driverFiles.setContentProvider(new ContentProviderImpl());
			this.driverFiles.setInput(AddDriverWizard.this.driverFileNames);
			
			Composite buttonArea = new Composite(composite, SWT.NONE);
			GridLayout gridLayout = new GridLayout();
			gridLayout.marginWidth = 0;
			buttonArea.setLayout(gridLayout);
			buttonArea.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
			
			Button addExternalJar = new Button(buttonArea, SWT.PUSH);
			addExternalJar.setText(Messages.getString(AddDriverWizard.class, "addExternalJar"));
			addExternalJar.setLayoutData(
					new GridData(GridData.FILL_HORIZONTAL 
							| GridData.VERTICAL_ALIGN_BEGINNING));
	
			addExternalJar.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					PageImpl.this.fileDialog.setFilterPath(QuantumPlugin.getDefault()
							.getPreferenceStore().getString(
									"quantum.dialogs.bookmarkwizard.path"));
					String filename = PageImpl.this.fileDialog.open();
					if (filename != null) {
						driverFileNames.add(filename);
						PageImpl.this.driverFiles.refresh();
						QuantumPlugin.getDefault().getPreferenceStore().setValue(
								"quantum.dialogs.bookmarkwizard.path", filename);
						updateButtons();
					}
				}
			});

			final Button removeJar = new Button(buttonArea, SWT.PUSH);
			removeJar.setText(Messages.getString(AddDriverWizard.class, "removeJar"));
			removeJar.setLayoutData(
					new GridData(GridData.HORIZONTAL_ALIGN_FILL 
							| GridData.VERTICAL_ALIGN_BEGINNING));
			removeJar.setEnabled(false);
			
			this.driverFiles.addSelectionChangedListener(new ISelectionChangedListener() {
				public void selectionChanged(SelectionChangedEvent event) {
					removeJar.setEnabled(!event.getSelection().isEmpty());
				}
			});
			
			removeJar.addSelectionListener(new SelectionAdapter(){
				public void widgetSelected(SelectionEvent event) {
					IStructuredSelection selection = 
						(IStructuredSelection) PageImpl.this.driverFiles.getSelection();
					for (Iterator i = selection.iterator(); i.hasNext();) {
						driverFileNames.remove(i.next());
					}
					PageImpl.this.driverFiles.refresh();
				}
			});
			
			label = new Label(composite, SWT.NULL);
			label.setText(Messages.getString(AddDriverWizard.class, "driverClassName"));
			this.driverClassName = new Text(composite, SWT.BORDER | SWT.SINGLE);
			GridData fullHorizontal = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
			this.driverClassName.setLayoutData(fullHorizontal);
	
			this.driverClassName.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent event) {
					AddDriverWizard.this.setDriverClassName(((Text) event.getSource()).getText());
					updateButtons();
				}
			});
			
			
			Button browse = new Button(composite, SWT.PUSH);
			browse.setText(Messages.getString(AddDriverWizard.class, "browse"));
			browse.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
	
			browse.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					SimpleSelectionDialog dialog = new SimpleSelectionDialog(
							getShell(), "Select a Driver", JarUtil.getAllDriverNames(
									getDriverFileNames()), ImageStore.getImage(ImageStore.CLASS));
					if (dialog.open() == SimpleSelectionDialog.OK) {
						IStructuredSelection selection = dialog.getSelection();
						if (!selection.isEmpty()) {
							String className = (String) selection.getFirstElement();
							PageImpl.this.driverClassName.setText(
									className);
							AddDriverWizard.this.setDriverClassName(className);
							updateButtons();
						}
					}
				}
			});
			label = new Label(composite, SWT.NULL);
			label.setText(Messages.getString("BookmarkWizard.TypeAst")); //$NON-NLS-1$
			this.type = new ComboViewer(composite);
			this.type.setContentProvider(new ContentProviderImpl());
			
			this.type.setLabelProvider(new ILabelProvider() {
				public Image getImage(Object element) {
					return null;
				}
				public String getText(Object element) {
					if (element != null && element instanceof DatabaseAdapter) {
						return ((DatabaseAdapter) element).getDisplayName();
					} else {
						return "";
					}
				}
				public void addListener(ILabelProviderListener listener) {
				}
				public void dispose() {
				}
				public boolean isLabelProperty(Object element, String property) {
					return false;
				}
				public void removeListener(ILabelProviderListener listener) {
				}
			});
			
			type.setInput(this.adapters);
	 		fullHorizontal = new GridData();
			fullHorizontal.horizontalAlignment = GridData.FILL;
			type.getControl().setLayoutData(fullHorizontal);
			
			type.setSelection(new StructuredSelection(
					AdapterFactory.getInstance().getAdapter(AdapterFactory.GENERIC)));
			
			setControl(composite);
	    }
	    protected void updateButtons() {
	    	Class driver = JarUtil.loadDriverClass(
	    			getDriverFileNames(), 
					getDriverClassName());
	    	setPageComplete(driver != null);
	    	
	    	String adapterType = AdapterFactory.getInstance().getAdapterType(getDriverClassName());
	    	if (adapterType != null) {
		    	this.type.setSelection(new StructuredSelection(
		    			AdapterFactory.getInstance().getAdapter(adapterType)));
	    	}
	    }
		protected String getDriverType() {
			DatabaseAdapter driverInfo = (DatabaseAdapter) 
					((IStructuredSelection) this.type.getSelection()).getFirstElement();
			return (driverInfo == null) ? null : driverInfo.getType();
		}
	}
	private PageImpl page;
	
	private String driverClassName;
	private String driverType;
	private List driverFileNames = Collections.synchronizedList(new ArrayList());

	
	public void addPages() {
		this.page = new PageImpl();
		addPage(page);
	}
	
	public AddDriverWizard() {
		super();
		setWindowTitle(Messages.getString(getClass(), "windowTitle"));
	}
	
	public boolean performFinish() {
		JDBCDriver driver = new JDBCDriver(
				getDriverClassName(), 
				getDriverFileNames(), 
				this.page.getDriverType());
		BookmarkCollection.getInstance().addDriver(driver);
		return true;
	}
	
	
	/**
	 * @return Returns the driverClassName.
	 */
	public String getDriverClassName() {
		return this.driverClassName;
	}
	/**
	 * @param driverClassName The driverClassName to set.
	 */
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	/**
	 * @return Returns the driverType.
	 */
	public String getDriverType() {
		return this.driverType;
	}
	/**
	 * @param driverType The driverType to set.
	 */
	public void setDriverType(String driverType) {
		this.driverType = driverType;
	}
	
	public String[] getDriverFileNames() {
		return (String[]) this.driverFileNames.toArray(new String[this.driverFileNames.size()]);
	}
}