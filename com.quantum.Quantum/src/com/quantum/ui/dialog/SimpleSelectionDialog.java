package com.quantum.ui.dialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.quantum.util.Displayable;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;


/**
 * @author BC
 */
public class SimpleSelectionDialog extends Dialog {
	
	class LabelProvider implements ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return SimpleSelectionDialog.this.image;
		}

		public String getColumnText(Object element, int columnIndex) {
			if (element instanceof Displayable) {
				return ((Displayable) element).getDisplayName();
			} else {
				return element.toString();
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
	}
	
	class ContentProvider implements IStructuredContentProvider {

		public Object[] getElements(Object inputElement) {
			return SimpleSelectionDialog.this.objects;
		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
		
	}

	private final String title;
	private TableViewer viewer;
	private final Image image;
	private final Object[] objects;
	
	private List selection = Collections.synchronizedList(new ArrayList());
	private final boolean multipleSelection;

	/**
	 * @param parentShell
	 */
	public SimpleSelectionDialog(Shell parentShell, String title, 
			Object[] objects, Image image) {
		this(parentShell, title, objects, image, false);
	}
	public SimpleSelectionDialog(Shell parentShell, String title, 
			Object[] objects, Image image, boolean multipleSelection) {
		super(parentShell);
		this.title = title;
		this.objects = objects;
		this.image = image;
		this.multipleSelection = multipleSelection;
	}

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText(title);
        GridLayout layout = new GridLayout();
        shell.setLayout(layout);
    }
        
    protected Control createDialogArea(Composite parent) {

        Composite composite = new Composite(parent, 0);
        GridLayout layout = new GridLayout();
        composite.setLayout(layout);
        layout.numColumns = 1;
        layout.verticalSpacing = 1;
        
        int style = SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL;
        if (this.multipleSelection) {
        	style |= SWT.MULTI;
        }
        this.viewer = new TableViewer(composite, style);
        GridData full = new GridData(GridData.FILL_BOTH);
        full.widthHint = 300;
        full.heightHint = 100;
        this.viewer.getControl().setLayoutData(full);
        
        this.viewer.setLabelProvider(new LabelProvider());
        this.viewer.setContentProvider(new ContentProvider());

        this.viewer.setInput(this);
        
        this.viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				Button okButton = getButton(IDialogConstants.OK_ID);
				okButton.setEnabled(!event.getSelection().isEmpty());
			}
        	
        });

        return composite;
    }
    
	protected void createButtonsForButtonBar(Composite parent) {
		super.createButtonsForButtonBar(parent);
		Button okButton = getButton(IDialogConstants.OK_ID);
		okButton.setEnabled(false);
	}
	
	protected void okPressed() {
        IStructuredSelection selection = (IStructuredSelection) this.viewer.getSelection();
        this.selection.clear();
        for (Iterator i = selection.iterator(); i.hasNext();) {
			this.selection.add(i.next());
		}
        super.okPressed();
	}
	/**
	 * @return Returns the selectedElement.
	 */
	public IStructuredSelection getSelection() {
		return new StructuredSelection(this.selection);
	}
}
