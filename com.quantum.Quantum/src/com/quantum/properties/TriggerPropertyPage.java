package com.quantum.properties;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.quantum.model.Entity;
import com.quantum.model.EntityHolder;
import com.quantum.model.Trigger;
import com.quantum.util.connection.NotConnectedException;

public class TriggerPropertyPage extends MetaDataPropertyPage {
	
	private Text text;
	private List list;
	private Trigger[] trigs = null;
	
	protected void createInformationArea(Composite composite) {
		// this should only apply to tables having triggers
		try {
			GridData gridListData = new GridData(GridData.FILL_HORIZONTAL);
            gridListData.horizontalSpan=2;
            gridListData.heightHint = 50;
            list = new List(composite, SWT.SINGLE|SWT.V_SCROLL|SWT.BORDER);
            list.setLayoutData(gridListData);
            text = new Text (composite,
            	    SWT.WRAP | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER | SWT.READ_ONLY);
			GridData gridData = new GridData(GridData.FILL_BOTH);
            gridData.horizontalSpan = 2;
            text.setLayoutData(gridData);
            Entity entity = getEntity();
            trigs = entity.getTriggers();
			for(int i=0; i<trigs.length; i++ )
			{
				list.add(trigs[i].getName());
			}
		} catch (NotConnectedException e) {
			createErrorMessage(composite, e);
		} catch (SQLException e) {
			createErrorMessage(composite, e);
		} catch (RuntimeException e) {
			createErrorMessage(composite, e);
		}
        list.addSelectionListener(new SelectionAdapter()
		{
        	public void widgetSelected(SelectionEvent e)
        	{
		       List list = (List) e.getSource();
		       int i = list.getSelectionIndex();
	            try
	            {
	            	text.setText(trigs[i].getBody());
	            }catch(Exception ex){
	            }
		       
		     }
        });
	}

	private Entity getEntity() {
        return ((EntityHolder) getElement()).getEntity();
    }

	/**
     * @param composite
     */
    protected void createHeader(Composite composite) {
        new TriggerHeader(composite, getEntity());
    }
//    else if(this.getType() == Entity.TABLE_TYPE) {
//		Trigger[] t = null;
//		t = getTriggers();
//		String result = "";
//		try
//		{
//			for(int i=0; i<t.length; i++ )
//			{
//				result+=t[i].getBody();
//			}
//		}catch(NullPointerException e){
//		}
//		return result;
//	}
	
}