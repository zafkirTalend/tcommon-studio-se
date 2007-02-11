package com.quantum.properties;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.quantum.model.Entity;
import com.quantum.model.EntityHolder;
import com.quantum.util.connection.NotConnectedException;

public class SQLPropertyPage extends MetaDataPropertyPage {
	
	protected void createInformationArea(Composite composite) {
        if (!Entity.SEQUENCE_TYPE.equals(getEntity().getType())) {
			try {
				GridData gridData = new GridData(GridData.FILL_BOTH);
	            gridData.horizontalSpan = 2;
	            Text text = new Text (composite,
	            	    SWT.WRAP | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER | SWT.READ_ONLY);
	            text.setLayoutData(gridData);
	            Entity entity = getEntity();
	            text.setText(entity.getCreateStatement());
	        	
			} catch (NotConnectedException e) {
				createErrorMessage(composite, e);
			} catch (SQLException e) {
				createErrorMessage(composite, e);
			} catch (RuntimeException e) {
				createErrorMessage(composite, e);
			}
        }
	}

	private Entity getEntity() {
        return ((EntityHolder) getElement()).getEntity();
    }

	/**
     * @param composite
     */
    protected void createHeader(Composite composite) {
        new EntityHeader(composite, getEntity());
    }

}