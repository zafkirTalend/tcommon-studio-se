package com.quantum.ui.dialog;

import java.util.ArrayList;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.quantum.ImageStore;

public class RelationshipDialog extends Dialog {
	// The image
	private Image image;
	
    private Label firstTable;
    private Label secondTable;
    private Tree keys;
    private Table firstColumns;
    private Table secondColumns;
    private Text name;
	private Button add;
    private Button remove;
    private TreeItem relations; // root on which to hang all relations
    
    private String[] relationshipNames;
    private String primaryColumn;
    private String foreignColumn;
	
	public RelationshipDialog (Shell parent, String WhatToSearch) {
		super(parent);
		setShellStyle(getShellStyle());
		image = ImageStore.getImage(WhatToSearch);
        name = null;
	}
	
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
 		GridLayout layout = new GridLayout(1, true);
        layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
        layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
        layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
        layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
        composite.setLayout(layout);
        Group g = new Group(composite, SWT.NONE);
        layout = new GridLayout(1, true);
        layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
        layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
        layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
        layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
        g.setLayout(layout);
        keys = new Tree(g, SWT.BORDER | SWT.SINGLE);
        keys.addSelectionListener(new SelectionAdapter(){
            public void widgetSelected(SelectionEvent e) {
                TreeItem[] ti = keys.getSelection();
                if (ti[0].getParentItem() != null)
                {
                    if (ti[0].getParentItem().equals(relations))
                    {
                        name.setText(ti[0].getText());
                        remove.setEnabled(true);
                    }else{
                        name.setText("");
                        remove.setEnabled(false);
                    }
                }else{
                    remove.setEnabled(false);
                    name.setText("");
                }
            }
        });
// keys.setLocation(5, 5);
        relations = new TreeItem(keys, SWT.NONE);
        relations.setText("Relationships");
        relations.setImage(ImageStore.getImage(ImageStore.FOREIGNKEY));
        GridData lblStringData = new GridData(SWT.FILL, SWT.FILL, true, true);
        lblStringData.widthHint = 350;
        lblStringData.heightHint = 150;
        keys.setLayoutData(lblStringData);
//        Label dummy = new Label(composite, SWT.NONE);
        g = new Group(composite, SWT.NONE);
        layout = new GridLayout(2, true);
        layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
        layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
        layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
        layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
        g.setLayout(layout);
        firstTable = new Label(g, SWT.CENTER | SWT.BOLD);
        firstTable.setText("&Table1");
        firstTable.setFont(parent.getFont());
        lblStringData = new GridData(SWT.FILL, SWT.FILL, true, true);
        lblStringData.widthHint = 150;
        firstTable.setLayoutData(lblStringData);
        secondTable = new Label(g, SWT.CENTER | SWT.BOLD);
        secondTable.setText("&Table2");
        secondTable.setLayoutData(lblStringData);
        secondTable.setFont(parent.getFont());
//        lblStringData = new GridData(SWT.FILL, SWT.FILL, true, true);
//        lblStringData.widthHint = 150;
//        lblStringData.heightHint = 50;
//        Label l = new Label(g, SWT.NONE);
//        l.setText("Keys");
//        l = new Label(g, SWT.NONE);
//        l.setText("Keys");
//        firstKeys = new Table(g, SWT.BORDER | SWT.READ_ONLY | SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE | SWT.FULL_SELECTION);
//        firstKeys.setLayoutData(lblStringData);
//        secondKeys = new Table(g, SWT.BORDER | SWT.READ_ONLY | SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE | SWT.FULL_SELECTION);
//        secondKeys.setLayoutData(lblStringData);
        lblStringData = new GridData(SWT.FILL, SWT.FILL, true, true);
        lblStringData.widthHint = 150;
        lblStringData.heightHint = 100;
//        Label l = new Label(g, SWT.NONE);
//        l.setText("Columns");
//        l = new Label(g, SWT.NONE);
//        l.setText("Columns");
        firstColumns = new Table(g, SWT.BORDER | SWT.READ_ONLY | SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION);
        firstColumns.setLayoutData(lblStringData);
//        firstColumns.addSelectionListener(new SelectionAdapter(){
//            public void widgetSelected(SelectionEvent e) {
//                TableItem[] ti = firstColumns.getSelection();
//                name.setText(ti[0].getText());
//            }
//        });
        secondColumns = new Table(g, SWT.BORDER | SWT.READ_ONLY | SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION);
        secondColumns.setLayoutData(lblStringData);
//        secondColumns.addSelectionListener(new SelectionAdapter(){
//            public void widgetSelected(SelectionEvent e) {
//                TableItem[] ti = firstColumns.getSelection();
//                name.setText(name.getText() + "_" + ti[0].getText());
//            }
//        });
        lblStringData = new GridData(SWT.FILL, SWT.FILL, true, true);
        lblStringData.widthHint = 150;
        Label l = new Label(g, SWT.NONE);
        l.setText("Relationship name:");
        name = new Text(g, SWT.BORDER);
        name.setLayoutData(lblStringData);
        add = new Button(g, SWT.PUSH);
        add.setText("&Add");
        add.setLayoutData(lblStringData);
        add.addSelectionListener(new SelectionAdapter(){public void widgetSelected(SelectionEvent e) {
            TableItem[] pKeys = firstColumns.getSelection();
            String[] p = new String[pKeys.length];
            for(int i=0; i < pKeys.length; i++)
            {
                p[i] = pKeys[i].getText();
            }
            TableItem[] fKeys = secondColumns.getSelection();
            String[] f = new String[fKeys.length];
            for(int i=0; i < fKeys.length; i++)
            {
                f[i] = fKeys[i].getText();
            }
            addRelation(name.getText(), firstTable.getText(), p, secondTable.getText(), f);
        }});
        remove = new Button(g, SWT.PUSH);
        remove.setText("&Remove");
        remove.setLayoutData(lblStringData);
        remove.setEnabled(false);
        remove.addSelectionListener(new SelectionAdapter(){public void widgetSelected(SelectionEvent e) {
            removeRelation(name.getText());
        }});
		return composite;
	}
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Create/edit relationship");
		newShell.setImage(image);
	}

    public void setTable1(String tableName)
    {
        firstTable.setText(tableName);
//        name.setText("FK_" + tableName + "_" + secondTable.getText());
    }
    
    public void setTable2(String tableName)
    {
        secondTable.setText(tableName);
//        name.setText("FK_" + firstTable.getText() + "_" + tableName);
    }
    
    public void addAColumnToTable1(String columnName, boolean isPrimary, boolean isForeign)
    {
        addToATable(firstColumns, columnName, isPrimary, isForeign);
    }
    
    public void addAColumnToTable2(String columnName, boolean isPrimary, boolean isForeign)
    {
        addToATable(secondColumns, columnName, isPrimary, isForeign);
    }
    
    private void addToATable(Table t, String columnName, boolean isPrimary, boolean isForeign)
    {
        TableItem item1 = new TableItem(t, SWT.NONE);
        item1.setText(columnName);
        if(isPrimary)
        {
            item1.setImage(ImageStore.getImage(ImageStore.KEYCOLUMN));
        }else if(isForeign){
            item1.setImage(ImageStore.getImage(ImageStore.FOREIGNKEY));
        }else{
            item1.setImage(ImageStore.getImage(ImageStore.COLUMN));
        }
    }

    public void addRelation(String name, String primaryTable, String[] primaryColumn, String foreignTable, String[] foreignColumn)
    {
        TreeItem keyName = new TreeItem(relations, SWT.NONE);
        keyName.setText(name);
        keyName.setImage(ImageStore.getImage(ImageStore.RELATIONSHIPSON));
        TreeItem primary = new TreeItem(keyName, SWT.NONE);
        primary.setText(primaryTable);
        primary.setImage(ImageStore.getImage(ImageStore.TABLE));
        TreeItem column = null;
        for(int i = 0; i < primaryColumn.length; i++)
        {
            column = new TreeItem(primary, SWT.NONE);
            column.setText(primaryColumn[i]);
            column.setImage(ImageStore.getImage(ImageStore.KEYCOLUMN));
            setColumnImage(primaryTable, primaryColumn[i], ImageStore.KEYCOLUMN);
            selectColumn(firstColumns, primaryColumn[i]);
        }
        keys.showItem(column);
        TreeItem foreign = new TreeItem(keyName, SWT.NONE);
        foreign.setText(foreignTable);
        foreign.setImage(ImageStore.getImage(ImageStore.TABLE));
        for(int i = 0; i < foreignColumn.length; i++)
        {
            column = new TreeItem(foreign, SWT.NONE);
            column.setText(foreignColumn[i]);
            column.setImage(ImageStore.getImage(ImageStore.FOREIGNKEY));
            setColumnImage(foreignTable, foreignColumn[i], ImageStore.FOREIGNKEY);
            selectColumn(secondColumns, foreignColumn[i]);
        }
        keys.showItem(column);
    }
    
    private void selectColumn(Table table, String columnName) {
        for(int i=0; i<table.getItems().length; i++)
        {
            TableItem ti = table.getItem(i);
            if(ti.getText().equals(columnName))
            {
                table.select(table.indexOf(ti));
            }
        }
    }

    private void setColumnImage(String table, String column, String imageName) {
        Table t = null;
        if(table.equals(firstTable.getText()))
        {
            t = firstColumns;
        }else{
            t = secondColumns;
        }
        for(int i=0; i<t.getItems().length; i++)
        {
            TableItem ti = t.getItem(i);
            if(ti.getText().equals(column))
            {
                ti.setImage(ImageStore.getImage(imageName));
                break;
            }
        }
    }

    public void removeRelation(String name)
    {
        TreeItem[] toDelete = relations.getItems();
        int i=0;
        while(i<toDelete.length)
        {
            if(toDelete[i].getText().equals(name))
            {
//              toDelete[i].removeAll();
                toDelete[i].dispose();
                break;
            }
            i++;
        }
    }
    
    protected void okPressed()
	{
        relationshipNames = getRelationshipNames();
        foreignColumn = getForeignKeyColumn(relationshipNames[0]);
        primaryColumn = getPrimaryKeyColumn(relationshipNames[0]);
		super.okPressed();
	}
    
    public String getRelationshipName()
    {
        return relationshipNames[0];
    }
    
    public String getForeignKey()
    {
        return foreignColumn;
    }
    
    public String getPrimaryKey()
    {
        return primaryColumn;
    }
    
    private String[] getRelationshipNames(){
        TreeItem[] items = relations.getItems();
        ArrayList l = new ArrayList(items.length);
        for(int i=0; i<items.length; i++)
        {
            l.add(items[i].getText());
        }
        return (String[]) l.toArray(new String[items.length]);
    }
    
    private String getPrimaryKeyColumn(String name)
    {
        TreeItem[] ti = relations.getItems();
        int i=0;
        while(i<ti.length)
        {
            if(ti[i].getText().equals(name))
            {
                TreeItem[] table = ti[i].getItems();
                TreeItem[] columns = table[0].getItems();
                return columns[0].getText();
            }
            i++;
        }
        return null;
    }
    private String getForeignKeyColumn(String name)
    {
        TreeItem[] ti = relations.getItems();
        int i=0;
        while(i<ti.length)
        {
            if(ti[i].getText().equals(name))
            {
                TreeItem[] table = ti[i].getItems();
                TreeItem[] columns = table[1].getItems();
                return columns[0].getText();
            }
            i++;
        }
        return null;
    }
}
