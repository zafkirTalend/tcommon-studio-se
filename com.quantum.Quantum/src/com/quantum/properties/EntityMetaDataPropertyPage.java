package com.quantum.properties;

import org.eclipse.swt.widgets.Composite;

import com.quantum.model.Entity;
import com.quantum.model.EntityHolder;

/**
 * @author <a href="http://www.intelliware.ca/">Intelliware Development</a>
 * @author BC Holmes
 */
public abstract class EntityMetaDataPropertyPage extends TabularMetaDataPropertyPage {
	/**
     * @param composite
     */
    protected void createHeader(Composite composite) {
        new EntityHeader(composite, getEntity());
    }

	protected void createInformationArea(Composite composite) {
        if (!Entity.SEQUENCE_TYPE.equals(getEntity().getType())) {
            super.createInformationArea(composite);
        }
	}


	protected Entity getEntity() {
        return ((EntityHolder) getElement()).getEntity();
    }
    
    
}
