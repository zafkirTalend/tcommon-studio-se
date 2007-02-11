package com.quantum.view.widget;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.quantum.Messages;
import com.quantum.ui.dialog.ExceptionDisplayDialog;

/**
 * @author BC Holmes
 */
public class FileSelectionWidget extends Composite {

    private Text fileName;
    private final ErrorMessageDisplayer displayer;
    private File file;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private Button browseButton;

    /**
     * @param parent
     * @param style
     */
    public FileSelectionWidget(Composite parent, int style) {
        this(parent, style, null);
    }
    /**
     * @param parent
     * @param style
     */
    public FileSelectionWidget(Composite parent, int style, ErrorMessageDisplayer displayer) {
        super(parent, style);
        this.displayer = displayer;
        
		setLayout(new GridLayout(3, false));
		setLayoutData(new GridData(GridData.FILL_BOTH));
		
        Label label = new Label(this, SWT.NULL);
        label.setText(Messages.getString(getClass(), "fileName"));
        this.fileName = new Text(this, SWT.BORDER | SWT.SINGLE);
        this.fileName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        this.browseButton = new Button(this, SWT.PUSH);
        this.browseButton.setText(Messages.getString(getClass(), "browse"));
        this.browseButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
        this.browseButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
                String filename = dialog.open();
                if (filename != null) {
                    FileSelectionWidget.this.fileName.setText(filename);
                    validate(filename);
                }
            }
        });
        
        this.fileName.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent event) {
				validate(((Text) event.getSource()).getText());
			}
       	});
    }
	
	private void validate(String fileName) {
		File file = new File(fileName);
		if (file.exists() && file.isFile()) {
			setErrorMessage(null);
			setFile(file);
		} else if (!file.exists() && !fileName.endsWith(File.separator)) {
			setErrorMessage(null);
			setFile(file);
		} else {
			setErrorMessage(Messages.getString(getClass(), "invalidFileName"));
			setFile(null);
		}
	}
    /**
     * @param file
     */
    private void setFile(File file) {
        Object oldValue = this.file;
        this.file = file;
        this.propertyChangeSupport.firePropertyChange("file", oldValue, this.file);
    }
    /**
     * @param object
     */
    private void setErrorMessage(String message) {
        if (this.displayer != null) {
            this.displayer.setErrorMessage(message);
        }
    }
    public File getFile() {
        return this.file;
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(listener);
    }
    
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.fileName.setEnabled(enabled);
        this.browseButton.setEnabled(enabled);
    }
    /**
     * @param initialContents
     * @return
     */
    public File createFile(InputStream initialContents) {
        try {
            if (this.file != null) {
		        FileOutputStream output = new FileOutputStream(this.file);
		        try {
			        for (int c = initialContents.read(); c >= 0; c = initialContents.read()) {
			            output.write(c);
			        }
		        } finally {
		            output.close();
		        }
            }
    	} catch (IOException e) {
    	    ExceptionDisplayDialog.openError(getShell(), null, null, e);
    	}
        return this.file;
    }
}
