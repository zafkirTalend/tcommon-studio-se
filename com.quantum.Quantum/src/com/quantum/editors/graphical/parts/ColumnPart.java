/*
 * Created on Jul 14, 2004
 */
package com.quantum.editors.graphical.parts;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.draw2d.Button;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.ui.PlatformUI;

import com.quantum.ImageStore;
import com.quantum.editors.graphical.directedit.ExtendedDirectEditManager;
import com.quantum.editors.graphical.directedit.LabelCellEditorLocator;
import com.quantum.editors.graphical.figures.ColumnFigure;
import com.quantum.editors.graphical.figures.ColumnsFigure;
import com.quantum.editors.graphical.figures.EditableLabel;
import com.quantum.editors.graphical.figures.TableFigure;
import com.quantum.editors.graphical.model.Column;
import com.quantum.editors.graphical.policy.ColumnDirectEditPolicy;
import com.quantum.editors.graphical.policy.ColumnEditPolicy;
import com.quantum.ui.dialog.ColumnDialog;


/**
 * Represents an editable Column object in the model
 * @author Phil Zoio/Jan H. van der Ven
 */
public class ColumnPart extends PropertyAwarePart
{

	protected DirectEditManager manager;
    private ColumnFigure figure;
    private EditableLabel columnLabel;
    private EditableLabel columnAliasLabel;
    private Button type;
    private boolean editingName;
	/**
	 * @return the ColumnLabel representing the Column
	 */
	protected IFigure createFigure()
	{
		Column column = (Column) getModel();
		String label = column.getLabelText();
		columnLabel = new EditableLabel(column.getName());
        if(column.isPrimaryKey())
        {
            if(column.isForeignKey())
            {
                if(column.isSelected()){
                    columnLabel.setIcon(ImageStore.getImage(ImageStore.KEYCOLUMNSELECTED));
                }else{
                    columnLabel.setIcon(ImageStore.getImage(ImageStore.KEYCOLUMN));
                }
            }else{
                if(column.isSelected()){
                    columnLabel.setIcon(ImageStore.getImage(ImageStore.KEYCOLUMNSELECTED));
                }else{
                    columnLabel.setIcon(ImageStore.getImage(ImageStore.KEYCOLUMN));
                }
            }
        }else if(column.isForeignKey()){
            if(column.isSelected()){
                columnLabel.setIcon(ImageStore.getImage(ImageStore.FOREIGNKEYSELECTED));
            }else{
                columnLabel.setIcon(ImageStore.getImage(ImageStore.FOREIGNKEY));
            }
        }else{
            if(column.isSelected()){
                columnLabel.setIcon(ImageStore.getImage(ImageStore.COLUMNSELECTED));
            }else{
                columnLabel.setIcon(ImageStore.getImage(ImageStore.COLUMN));
            }
        }
        columnAliasLabel= new EditableLabel(column.getAliasName());
        type = new Button(column.getLabelText());
        ColumnFigureActionListener cfal = new ColumnFigureActionListener(this);
        type.addActionListener(cfal);
        figure = new ColumnFigure(columnLabel, columnAliasLabel, type);
        return figure;
	}

	/**
	 * Creats EditPolicies for the column label
	 */
	protected void createEditPolicies()
	{
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ColumnEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new ColumnDirectEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, null);
	}

	public void performRequest(Request request)
	{
		if (request.getType() == RequestConstants.REQ_DIRECT_EDIT)
		{
			if (request instanceof DirectEditRequest)
            {
			    if(directEditHitTestName(((DirectEditRequest) request).getLocation().getCopy())){
                    setEditingName(true);
                    performDirectEdit();
                }
                if(directEditHitTestAlias(((DirectEditRequest) request).getLocation().getCopy())){
                    setEditingName(false);
                    performDirectEdit();
                }
            }
		}
	}

	private void setEditingName(boolean b) {
        editingName = b;        
    }

    public boolean isNameBeingEdited(){
        return editingName;
    }
    
    private boolean directEditHitTestName(Point requestLoc)
	{
        columnLabel.translateToRelative(requestLoc);
        if(columnLabel.containsPoint(requestLoc))
			return true;
		return false;
	}
    private boolean directEditHitTestAlias(Point requestLoc)
    {
        columnAliasLabel.translateToRelative(requestLoc);
        if(columnAliasLabel.containsPoint(requestLoc))
            return true;
        return false;
    }

	protected void performDirectEdit()
	{
//			ValidationEnabledGraphicalViewer viewer = (ValidationEnabledGraphicalViewer) getViewer();
//			ValidationMessageHandler handler = viewer.getValidationHandler();

//			Label l = (Label)((ColumnFigure) getFigure()).getColumnLabel();
//			ColumnNameTypeCellEditorValidator columnNameTypeCellEditorValidator = new ColumnNameTypeCellEditorValidator(
//					handler);
	    if(editingName){
			manager = new ExtendedDirectEditManager(this, TextCellEditor.class, new LabelCellEditorLocator(columnLabel), columnLabel, null);
//					columnNameTypeCellEditorValidator);
		}else{
            manager = new ExtendedDirectEditManager(this, TextCellEditor.class, new LabelCellEditorLocator(columnAliasLabel), columnAliasLabel, null);
//          columnNameTypeCellEditorValidator);
        }
		manager.show();
	}

	/**
	 * Sets the width of the line when selected
	 */
	public void setSelected(int value)
	{
//		EditableLabel columnLabel = ((ColumnFigure) getFigure()).getColumnLabel();
		if (value != EditPart.SELECTED_NONE)
			columnLabel.setSelected(true);
		else
            columnLabel.setSelected(false);
		columnLabel.repaint();
        super.setSelected(value);
	}

	/**
	 * @param Handles
	 *            name change during direct edit
	 */
	public void handleNameChange(String textValue)
	{
//		EditableLabel label = ((ColumnFigure) getFigure()).getColumnLabel();
        columnLabel.setVisible(false);
		setSelected(EditPart.SELECTED_NONE);
        columnLabel.revalidate();
	}

	/**
	 * Handles when successfully applying direct edit
	 */
	protected void commitNameChange(PropertyChangeEvent evt)
	{
		columnLabel.setText(getColumn().getName());
		setSelected(EditPart.SELECTED_PRIMARY);
		columnLabel.revalidate();
	}
    protected void commitAliasChange(PropertyChangeEvent evt)
    {
        columnAliasLabel.setText(getColumn().getAliasName());
        columnAliasLabel.revalidate();
    }

    protected void commitSizeChange(PropertyChangeEvent evt)
    {
        Column column = (Column) getModel();
        Label l = (Label) type.getChildren().get(0);
        l.setText(column.getLabelText());
    }

	/**
	 * Reverts state back to prior edit state
	 */
	public void revertNameChange(String oldValue)
	{
		columnLabel.setVisible(true);
		setSelected(EditPart.SELECTED_PRIMARY);
		columnLabel.revalidate();
	}

    protected void handleColumnImageChange(PropertyChangeEvent evt) {
        Column column = (Column) getModel();
        if(column.isPrimaryKey())
        {
            if(column.isForeignKey())
            {
                if(column.isSelected()){
                    columnLabel.setIcon(ImageStore.getImage(ImageStore.KEYCOLUMNSELECTED));
                }else{
                    columnLabel.setIcon(ImageStore.getImage(ImageStore.KEYCOLUMN));
                }
            }else{
                if(column.isSelected()){
                    columnLabel.setIcon(ImageStore.getImage(ImageStore.KEYCOLUMNSELECTED));
                }else{
                    columnLabel.setIcon(ImageStore.getImage(ImageStore.KEYCOLUMN));
                }
            }
        }else if(column.isForeignKey()){
            if(column.isSelected()){
                columnLabel.setIcon(ImageStore.getImage(ImageStore.FOREIGNKEYSELECTED));
            }else{
                columnLabel.setIcon(ImageStore.getImage(ImageStore.FOREIGNKEY));
            }
        }else{
            if(column.isSelected()){
                columnLabel.setIcon(ImageStore.getImage(ImageStore.COLUMNSELECTED));
            }else{
                columnLabel.setIcon(ImageStore.getImage(ImageStore.COLUMN));
            }
        }
    }

	/**
	 * We don't need to explicitly handle refresh visuals because the times when
	 * this needs to be done it is handled by the table e.g. handleNameChange()
	 */
	protected void refreshVisuals()
	{
		Column column = (Column) getModel();
		columnLabel.setText(column.getName());
	}
	
	

	private Column getColumn()
	{
		return (Column) getModel();
	}
	
    protected void handleMarkedForOutputChange(PropertyChangeEvent evt)
    {
        // change the image of the label
        Column column = (Column) getModel();
        if(column.isPrimaryKey())
        {
            if(column.isForeignKey())
            {
                if(column.isSelected()){
                    columnLabel.setIcon(ImageStore.getImage(ImageStore.KEYCOLUMNSELECTED));
                }else{
                    columnLabel.setIcon(ImageStore.getImage(ImageStore.KEYCOLUMN));
                }
            }else{
                if(column.isSelected()){
                    columnLabel.setIcon(ImageStore.getImage(ImageStore.KEYCOLUMNSELECTED));
                }else{
                    columnLabel.setIcon(ImageStore.getImage(ImageStore.KEYCOLUMN));
                }
            }
        }else if(column.isForeignKey()){
            if(column.isSelected()){
                columnLabel.setIcon(ImageStore.getImage(ImageStore.FOREIGNKEYSELECTED));
            }else{
                columnLabel.setIcon(ImageStore.getImage(ImageStore.FOREIGNKEY));
            }
        }else{
            if(column.isSelected()){
                columnLabel.setIcon(ImageStore.getImage(ImageStore.COLUMNSELECTED));
            }else{
                columnLabel.setIcon(ImageStore.getImage(ImageStore.COLUMN));
            }
        }
    }
    
    private class ColumnFigureActionListener implements ActionListener{
        private ColumnPart cp;

        ColumnFigureActionListener(ColumnPart cp){
            this.cp = cp;
        }
        
        public void actionPerformed(ActionEvent event) {
            ColumnFigure cf = (ColumnFigure) cp.getFigure();
            ColumnsFigure csf = (ColumnsFigure) cf.getParent();
            TableFigure tf = (TableFigure) csf.getParent();
            String tableName = tf.getNameLabel().getText();
            String aliasName = tf.getAliasLabel().getText();
            ColumnDialog sd = new ColumnDialog(PlatformUI
                    .getWorkbench().getActiveWorkbenchWindow()
                    .getShell(), cp.getColumn(), tableName, aliasName);
            sd.create();
            sd.open();
            cp.getColumn().setAliasName(sd.getColumnAlias());
            cp.getColumn().setType(sd.getTypeName());
            cp.getColumn().setSize(sd.getSize()); 
            cp.getColumn().setPrecision(sd.getPrecision()); 
        }
    }
}