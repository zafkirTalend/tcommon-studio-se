package com.quantum.properties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.quantum.Messages;
import com.quantum.model.Entity;

/**
 * @author <a href="http://www.intelliware.ca/">Intelliware Development</a>
 * @author BC Holmes
 */
class EntityHeader extends Composite {

    EntityHeader(Composite composite, Entity entity) {
        super(composite, SWT.NONE);

        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        setLayout(layout);
        setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        Label label = new Label(this, SWT.NONE);
        label.setText(Messages.getString(getClass(), "name"));

        Label name = new Label(this, SWT.NONE);
        name.setText(entity.getName());

        label = new Label(this, SWT.NONE);
        label.setText(Messages.getString(getClass(), "schema"));

        Label schema = new Label(this, SWT.NONE);
        schema.setText(entity.getSchema());
    }
}
