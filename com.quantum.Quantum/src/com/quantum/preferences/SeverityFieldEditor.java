package com.quantum.preferences;

import com.quantum.Messages;
import com.quantum.log.Severity;
import com.quantum.view.widget.ComboViewer;

import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;

/**
 * A basic field editor for selecting a limited set of options for a preference.
 * 
 * @author BC Holmes
 */
class SeverityFieldEditor extends FieldEditor {
    
    class ContentProviderImpl implements IStructuredContentProvider {
        public Object[] getElements(Object inputElement) {
            if (inputElement instanceof Severity[]) {
                return (Severity[]) inputElement;
            } else {
	            return null;
            }
        }
        public void dispose() {
        }
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        }
    }
    
    class LabelProviderImpl extends LabelProvider {
        public String getText(Object element) {
            if (element instanceof Severity) {
                return Messages.getString(element.getClass(), ((Severity) element).getName());
            } else {
                return super.getText(element);
            }
        }
    }
    
    private ComboViewer viewer;

    public SeverityFieldEditor(String name, String labelText, Composite parent) {
        super(name, labelText, parent);
    }
    
    protected void adjustForNumColumns(int numColumns) {
        
    }

    protected void doFillIntoGrid(Composite parent, int numColumns) {
        getLabelControl(parent);
        
        this.viewer = new ComboViewer(parent);
        this.viewer.setContentProvider(new ContentProviderImpl());
        this.viewer.setLabelProvider(new LabelProviderImpl());
        this.viewer.setInput(Severity.getSeverities());
    }

    protected void doLoad() {
		String value = getPreferenceStore().getString(getPreferenceName());
		select(value);
    }

    protected void doLoadDefault() {
		String value = getPreferenceStore().getDefaultString(getPreferenceName());
		select(value);
    }

    /**
     * @param value
     */
    private void select(String value) {
        this.viewer.setSelection(new StructuredSelection(Severity.getSeverity(value)));
    }

    protected void doStore() {
        IStructuredSelection selection = (IStructuredSelection) this.viewer.getSelection();
        Severity severity = (Severity) selection.getFirstElement();
        getPreferenceStore().setValue(getPreferenceName(), severity.getName());
    }

    public int getNumberOfControls() {
        return 2;
    }

}
