package com.quantum.properties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.quantum.Messages;
import com.quantum.model.Procedure;

/**
 * @author <a href="http://www.intelliware.ca/">Intelliware Development</a>
 * @author BC Holmes
 * edited by JHvdV
 */
class ProcedureHeader extends Composite {

    ProcedureHeader(Composite composite, Procedure proc) {
        super(composite, SWT.NONE);

        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        setLayout(layout);
        setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        Label label = new Label(this, SWT.NONE);
        label.setText(Messages.getString(getClass(), "name"));

        Label name = new Label(this, SWT.NONE);
        name.setText(proc.getName());

        label = new Label(this, SWT.NONE);
        label.setText(Messages.getString(getClass(), "schema"));

        Label schema = new Label(this, SWT.NONE);
        schema.setText(proc.getSchema());
    }
}
