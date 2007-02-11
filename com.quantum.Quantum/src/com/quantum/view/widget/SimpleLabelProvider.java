package com.quantum.view.widget;

import com.quantum.util.Displayable;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;


/**
 * @author BC
 */
public class SimpleLabelProvider extends LabelProvider {
	
	private final Image image;
	
	public SimpleLabelProvider() {
		this(null);
	}
	public SimpleLabelProvider(Image image) {
		this.image = image;
	}
	public Image getImage(Object element) {
		return this.image;
	}
	public String getText(Object element) {
		if (element instanceof Displayable) {
			return ((Displayable) element).getDisplayName();
		} else {
			return super.getText(element);
		}
	}
}
