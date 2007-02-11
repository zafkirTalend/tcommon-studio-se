package com.quantum.ui.dialog;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import com.quantum.ImageStore;
import com.quantum.actions.BookmarkSelectionUtil;
import com.quantum.editors.graphical.model.Column;
import com.quantum.model.Bookmark;
import com.quantum.model.DataType;
import com.quantum.util.connection.NotConnectedException;
import com.quantum.util.sql.TypesHelper;

public class ColumnDialog extends Dialog {
	
    Column column;
    Image image;
    private String tableName;
    private String aliasName;
    
    private String columnAlias;
    private String typeName;
    private int size;
    private int precision;

    private Text aliasNameText;
    private Spinner sizeSpinner;
    private Spinner precisionSpinner;
    private Combo typeCombo;
    
	public ColumnDialog (Shell parent, Column c, String tableName, String aliasName) {
		super(parent);
        column = c;
		setShellStyle(getShellStyle());
		image = ImageStore.getImage(ImageStore.COLUMN);
        this.tableName = tableName;
        this.aliasName = aliasName;
        columnAlias = c.getAliasName();
        typeName = c.getType();
        size = (int) c.getSize();
        precision = c.getPrecision();
	}
	
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
 		GridLayout layout = new GridLayout(2, false);
        layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
        layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
        layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
        layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
        composite.setLayout(layout);
        Label l = new Label(composite, SWT.LEFT);
        l.setImage(ImageStore.getImage(ImageStore.TABLE));
        l = new Label(composite, SWT.RIGHT);
        if (aliasName == null) {
            l.setText(tableName);
        } else {
            if (aliasName == "") {
                l.setText(tableName);
            } else {
                l.setText(tableName + ":" + aliasName);
            }
        }
        l = new Label(composite, SWT.LEFT);
        l.setImage(ImageStore.getImage(ImageStore.COLUMN));
        l = new Label(composite, SWT.RIGHT);
        l.setText(column.getName());
        l = new Label(composite, SWT.RIGHT);
        l.setText("Column alias");
        aliasNameText = new Text(composite, SWT.BORDER);
        aliasNameText.setText(columnAlias);
        l = new Label(composite, SWT.LEFT);
        l.setText("Type");
        typeCombo = new Combo(composite, SWT.DROP_DOWN|SWT.FLAT);
        Bookmark bookmark = BookmarkSelectionUtil.getInstance().getLastUsedBookmark();
        DataType[] types = null;
        int compatibleType = -1;
        try {
            types = bookmark.getDataTypes();
            ArrayList s = new ArrayList(types.length);
            int[] columnTypes = new int[types.length];
            for (int i = 0; i < types.length; i++) {
                s.add(i, types[i].getDatabaseTypeName());
                int jt = types[i].getJavaType();
                if(TypesHelper.isNumeric(jt))
                {
                    columnTypes[i] = 1;
                }else if(TypesHelper.isText(jt)){
                    columnTypes[i] = 2;
                }else{
                    columnTypes[i] = 3;
                }
                if(types[i].getDatabaseTypeName().equalsIgnoreCase(column.getType())){
                    compatibleType = columnTypes[i];
                }
            }
            String[] strings = new String[types.length];
            int n = 0;
            int m = 1;
            for (int i = 0; i < types.length; i++) {
                if(columnTypes[i]==compatibleType ||compatibleType == -1){
                    strings[n] = (String)s.get(i);
                    n++;
                }else{
                    strings[types.length - m] = (String) s.get(i);
                    m++;
                }
            }
            // put the compatible ones first
            Arrays.sort(strings, 0, n-1);
            // then add the rest for optimum flexibility
            Arrays.sort(strings, n, types.length - 1);
            for (int i = 0; i < types.length; i++) {
                typeCombo.add(strings[i]);
            }
        } catch (NotConnectedException e) {
        } catch (SQLException e) {
        }
        try
        {
            typeCombo.setText(column.getType());
        }catch(Exception e){
            ExceptionDisplayDialog.openError(null, "Error with combo", e.getMessage(), e);
        }

        l = new Label(composite, SWT.LEFT);
        l.setText("Size");
        sizeSpinner = new Spinner(composite, SWT.BORDER);
        sizeSpinner.setMinimum(-1); // -1 means irrelevant
        sizeSpinner.setMaximum(1024);// must come from adapter, depending on type
        sizeSpinner.setSelection((int)column.getSize());
        l = new Label(composite, SWT.LEFT);
        l.setText("Precision");
        precisionSpinner= new Spinner(composite, SWT.BORDER);
        precisionSpinner.setMinimum(-1); // -1 means irrelevant
        precisionSpinner.setMaximum(1024);// must come from adapter, depending on type
        precisionSpinner.setSelection((int)column.getPrecision());
		return composite;
	}
    
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Edit " + tableName + "." + column.getName());
		newShell.setImage(image);
	}

    protected void okPressed()
	{
        columnAlias = aliasNameText.getText();
        typeName = typeCombo.getText();
        size = sizeSpinner.getSelection();
        precision = precisionSpinner.getSelection();
		super.okPressed();
	}

    public int getSize(){
        return size;
    }

    public int getPrecision(){
        return precision;
    }
    
    public String getColumnAlias(){
        return columnAlias;
    }

    public String getTypeName(){
        return typeName;
    }
}
