package com.quantum.view;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.quantum.IQuantumConstants;
import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.model.Bookmark;
import com.quantum.model.Schema;
import com.quantum.ui.dialog.ConnectionUtil;
import com.quantum.ui.dialog.ExceptionDisplayDialog;
import com.quantum.ui.dialog.SQLExceptionDialog;
import com.quantum.ui.dialog.SimpleSelectionDialog;
import com.quantum.util.connection.ConnectionException;
import com.quantum.util.connection.NotConnectedException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;


/**
 * @author BC
 */
public class SchemaSelectionControl extends Composite {
	
    class ContentProviderImpl implements IStructuredContentProvider {
        public Object[] getElements(Object inputElement) {
            List list = new ArrayList((Collection) inputElement);
            Collections.sort(list);
            return list.toArray();
        }

        public void dispose() {
        }

        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        }
    }
    
    class LabelProviderImpl implements ITableLabelProvider {

        public Image getColumnImage(Object element, int columnIndex) {
            if (columnIndex == 0) {
                return ImageStore.getImage(ImageStore.SCHEMA);
            } else {
                return null;
            }
        }

        public String getColumnText(Object element, int columnIndex) {
            if (columnIndex == 0) {
                return ((Schema) element).getDisplayName();
            } else {
                return null;
            }
        }

        public void addListener(ILabelProviderListener listener) {
        }

        public void dispose() {
        }

        public boolean isLabelProperty(Object element, String property) {
            return "displayName".equals(property);
        }

        public void removeListener(ILabelProviderListener listener) {
        }
    }
    
	
    private final Bookmark bookmarkForConnection;
    private ConnectionUtil connectionUtil = new ConnectionUtil();
	private Set schemas = Collections.synchronizedSet(new HashSet());
	private TableViewer schemaTable;
	private Button useAllSchemasButton;
	
	private int schemaRule;
	
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	private Button useUsernameAsSchemaButton;
	private Button useSelectedSchemasButton;
	private Button removeButton;
	private Button addButton;

	/**
	 * @param parent
	 * @param style
	 */
	public SchemaSelectionControl(Composite parent, Bookmark bookmarkForConnection) {
		super(parent, SWT.NONE);
		this.bookmarkForConnection = bookmarkForConnection;
		
		Schema[] schemas = bookmarkForConnection.getSchemaSelections();
		this.schemas.addAll(Arrays.asList(schemas));
		this.schemaRule = this.bookmarkForConnection.getSchemaRule();
		createContents();
	}

	protected void createContents() {

        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        setLayout(layout);
        GridData data = new GridData(GridData.FILL_BOTH);
        setLayoutData(data);

        this.useAllSchemasButton = new Button(this, SWT.RADIO);
        this.useAllSchemasButton.setText(Messages.getString(getClass(), "useAllSchemas")); //$NON-NLS-1$
		this.useAllSchemasButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		this.useAllSchemasButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				setSchemaRule(Bookmark.SCHEMA_RULE_USE_ALL);
				updateControls();
			}
		});
		
        this.useUsernameAsSchemaButton = new Button(this, SWT.RADIO);
        this.useUsernameAsSchemaButton.setText(Messages.getString(getClass(), "useUsernameAsSchema")); //$NON-NLS-1$
        this.useUsernameAsSchemaButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		this.useUsernameAsSchemaButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				setSchemaRule(Bookmark.SCHEMA_RULE_USE_DEFAULT);
				updateControls();
			}
		});
		
        this.useSelectedSchemasButton = new Button(this, SWT.RADIO);
        this.useSelectedSchemasButton.setText(Messages.getString(getClass(), "useSelectedSchemas")); //$NON-NLS-1$
        this.useSelectedSchemasButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		this.useSelectedSchemasButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				setSchemaRule(Bookmark.SCHEMA_RULE_USE_SELECTED);
				updateControls();
			}
		});
 		
 		Composite composite = new Composite(this, SWT.NONE);
        layout = new GridLayout();
        layout.numColumns = 2;
        composite.setLayout(layout);
        data = new GridData(GridData.FILL_BOTH);
        composite.setLayoutData(data);
		
        this.schemaTable = new TableViewer(composite, 
				            SWT.FULL_SELECTION | SWT.MULTI | SWT.BORDER);
		layout = new GridLayout();
        layout.marginWidth = 5;
        layout.marginHeight = 5;
        
        this.schemaTable.getTable().setLayout(layout);
        data = new GridData(GridData.FILL_BOTH);
        this.schemaTable.getTable().setLayoutData(data);
        this.schemaTable.setLabelProvider(new LabelProviderImpl());
        this.schemaTable.setContentProvider(new ContentProviderImpl());
        this.schemaTable.setInput(this.schemas);
        
        createButtonArea(composite);
        
        updateControls();
    }
	
	private void updateControls() {
		this.useAllSchemasButton.setSelection(this.schemaRule == Bookmark.SCHEMA_RULE_USE_ALL);
		this.useUsernameAsSchemaButton.setSelection(this.schemaRule == Bookmark.SCHEMA_RULE_USE_DEFAULT);
		
		boolean enabled = (this.schemaRule != Bookmark.SCHEMA_RULE_USE_ALL
				&& this.schemaRule != Bookmark.SCHEMA_RULE_USE_DEFAULT);

		this.useSelectedSchemasButton.setSelection(enabled);
        this.schemaTable.getControl().setEnabled(enabled);
        
        this.addButton.setEnabled(enabled);
        this.removeButton.setEnabled(
        		enabled && !this.schemaTable.getSelection().isEmpty());
	}

    private void createButtonArea(Composite composite) {
        Composite buttonArea = new Composite(composite, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        buttonArea.setLayout(layout);
        GridData data = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.VERTICAL_ALIGN_BEGINNING);
        buttonArea.setLayoutData(data);
        
        this.addButton = new Button(buttonArea, SWT.NULL);
        this.addButton.setText("Add");
        data = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
        this.addButton.setLayoutData(data);
        this.addButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                addSchema();
            }
        });
        
        this.removeButton = new Button(buttonArea, SWT.NULL);
        this.removeButton.setText("Remove");
        this.removeButton.setEnabled(false);
        data = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
        this.removeButton.setLayoutData(data);
        this.removeButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                removeSchema(SchemaSelectionControl.this.schemaTable.getSelection());
            }
        });
        
        schemaTable.addSelectionChangedListener(new ISelectionChangedListener() {
            public void selectionChanged(SelectionChangedEvent event) {
            	updateControls();
            }
        });
    }
    private void addSchema() {
        Bookmark bookmark = getBookmark();
        boolean isAlreadyConnected = bookmark.isConnected();
        
        if (!isAlreadyConnected) {
            boolean confirmed = MessageDialog.openConfirm(getShell(), 
            		Messages.getString(getClass(), "connectTitle"),
            		Messages.getString(getClass(), "connectMessage"));
            if (confirmed) {
                this.connectionUtil.connect(bookmark, getShell());
            }
        }
        
        try {
            if (bookmark.isConnected()) {
                List schemaList = getAllUnselectedSchemas(bookmark);
                SimpleSelectionDialog dialog = new SimpleSelectionDialog(
                		getShell(), Messages.getString(getClass(), "addSchemaDialog"), 
						schemaList.toArray(), 
						ImageStore.getImage(ImageStore.SCHEMA), true);
                int result = dialog.open();
                if (result == SimpleSelectionDialog.OK 
                		&& !dialog.getSelection().isEmpty()) {
                    for (Iterator i = dialog.getSelection().iterator(); i.hasNext();) {
                        this.schemas.add(i.next());
                    }
                    
	                refreshTable();
                	this.propertyChangeSupport.firePropertyChange(IQuantumConstants.SCHEMAS_PROPERTY, null, getSchemas());
                }
    
                if (!isAlreadyConnected) {
                    bookmark.disconnect();
                }
            }
        } catch (ConnectionException e) {
            ExceptionDisplayDialog.openError(getShell(), null, null, e);
        } catch (SQLException e) {
            SQLExceptionDialog.openException(getShell(), bookmark, e);
		}
    }
    
    /**
	 * @param bookmark
	 * @return
	 * @throws NotConnectedException
	 * @throws SQLException
	 */
	private List getAllUnselectedSchemas(Bookmark bookmark) 
			throws NotConnectedException, SQLException {
		Schema[] schemas = bookmark.getDatabase().getSchemas();
		List schemaList = new ArrayList(Arrays.asList(schemas));
		schemaList.removeAll(this.schemas);
		Collections.sort(schemaList);
		return schemaList;
	}

	private void removeSchema(ISelection selection) {
        IStructuredSelection structuredSelection = (IStructuredSelection) selection;
        for (Iterator i = structuredSelection.iterator(); i.hasNext();) {
            Schema element = (Schema) i.next();
            this.schemas.remove(element);
        }
        refreshTable();
    	this.propertyChangeSupport.firePropertyChange("schemas", null, getSchemas());
    }

    private Bookmark getBookmark() {
        return this.bookmarkForConnection;
    }

    private void refreshTable() {
        this.schemaTable.refresh();
    }
    
    public Schema[] getSchemas() {
    	return (Schema[]) this.schemas.toArray(new Schema[this.schemas.size()]);
    }

    public void setSchemas(Schema[] schemas) {
    	this.schemas.clear();
    	this.schemas.addAll(Arrays.asList(schemas));
		updateControls();
    	refreshTable();
    	this.propertyChangeSupport.firePropertyChange("schemas", null, getSchemas());
    }
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		this.propertyChangeSupport.addPropertyChangeListener(arg0);
	}
	public void removePropertyChangeListener(PropertyChangeListener arg0) {
		this.propertyChangeSupport.removePropertyChangeListener(arg0);
	}
	public int getSchemaRule() {
		return this.schemaRule;
	}
	public void setSchemaRule(int schemaRule) {
		if (schemaRule != this.schemaRule) {
			int original = this.schemaRule;
			this.schemaRule = schemaRule;
			updateControls();
			refreshTable();
			this.propertyChangeSupport.firePropertyChange(
					IQuantumConstants.SCHEMA_RULE_PROPERTY, original, schemaRule);
		}
	}
}
