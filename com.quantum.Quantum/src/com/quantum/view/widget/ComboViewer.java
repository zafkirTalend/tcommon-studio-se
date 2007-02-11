package com.quantum.view.widget;

import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * <p>A Combo viewer.
 * 
 * <p>In Eclipse version 3.0, a standard JFace ComboViewer class exists.
 * 
 * @author BC Holmes
 */
public class ComboViewer extends ContentViewer {

	private Combo combo;

	private Object[] objects = new Object[0];

	public ComboViewer(Composite parent) {
		this.combo = new Combo(parent, SWT.SIMPLE | SWT.DROP_DOWN | SWT.READ_ONLY);
		this.combo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				ComboViewer.this.fireSelectionChanged(new SelectionChangedEvent(
						ComboViewer.this, getSelection()) );
			}
		});
	}

	public Control getControl() {
		return this.combo;
	}

	public ISelection getSelection() {
		int index = this.combo.getSelectionIndex();

		return (this.objects == null || index >= this.objects.length || index < 0) 
				? new StructuredSelection()
				: new StructuredSelection(this.objects[index]);
	}

	protected void inputChanged(Object input, Object oldInput) {
		super.inputChanged(input, oldInput);

		if (input == null) {
			this.objects = new Object[0];
		} else {
			this.objects = ((IStructuredContentProvider) getContentProvider()).getElements(input);
			for (int i = 0, length = this.objects == null ? 0 : this.objects.length; i < length; i++) {
				String label = ((ILabelProvider) getLabelProvider()).getText(this.objects[i]);
				this.combo.add(label);
			}
		}
	}
	public void refresh() {
	}

	public void setSelection(ISelection selection, boolean reveal) {
		if (selection != null && selection instanceof IStructuredSelection) {
			Object object = ((IStructuredSelection) selection).getFirstElement();
			for (int i = 0, length = this.objects == null ? 0 : this.objects.length; 
					object != null && i < length; i++) {
				if (this.objects[i].equals(object)) {
					this.combo.select(i);
				}
			}
		}
	}
}