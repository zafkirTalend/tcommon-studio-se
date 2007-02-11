package com.quantum.properties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.quantum.model.Procedure;
import com.quantum.view.bookmark.ProcedureNode;

public class SPPropertyPage extends MetaDataPropertyPage {
	
	protected void createInformationArea(Composite composite) {
		// this should only apply to Stored Procedures
		try {
			GridData gridData = new GridData(GridData.FILL_BOTH);
            gridData.horizontalSpan = 2;
            Text text = new Text (composite,
            	    SWT.WRAP | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER | SWT.READ_ONLY);
            text.setLayoutData(gridData);
            Procedure proc = getProcedure();
            text.setText(proc.getBody());
        	
//		} catch (NotConnectedException e) {
//			createErrorMessage(composite, e);
//		} catch (SQLException e) {
//			createErrorMessage(composite, e);
		} catch (RuntimeException e) {
			createErrorMessage(composite, e);
		}
	}

	private Procedure getProcedure() {
		return (Procedure)((ProcedureNode)getElement()).getProcedure();
    }

	/**
     * @param composite
     */
    protected void createHeader(Composite composite) {
        new ProcedureHeader(composite, getProcedure());
    }

}