/*
 * Created on Jul 18, 2004
 */
package com.quantum.editors.graphical.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.CellEditor;

import com.quantum.editors.graphical.model.Table;
import com.quantum.editors.graphical.model.commands.ChangeTableAliasCommand;
import com.quantum.editors.graphical.parts.TablePart;
/**
 * EditPolicy for the direct editing of table names
 * 
 * @author Phil Zoio
 */
public class TableAliasDirectEditPolicy extends DirectEditPolicy
{

	private String oldValue;

	/**
	 * @see DirectEditPolicy#getDirectEditCommand(org.eclipse.gef.requests.DirectEditRequest)
	 */
	protected Command getDirectEditCommand(DirectEditRequest request)
	{
		ChangeTableAliasCommand cmd = new ChangeTableAliasCommand();
		Table table = (Table) getHost().getModel();
		cmd.setTable(table);
		cmd.setOldAlias(table.getAlias());
		CellEditor cellEditor = request.getCellEditor();
		cmd.setAlias((String) cellEditor.getValue());
		return cmd;
	}

	/**
	 * @see DirectEditPolicy#showCurrentEditValue(org.eclipse.gef.requests.DirectEditRequest)
	 */
    protected void showCurrentEditValue(DirectEditRequest request)
    {
        String value = (String) request.getCellEditor().getValue();
        TablePart tablePart = (TablePart) getHost();
        tablePart.handleAliasChange(value);
    }

	/**
	 * @param to
	 *            Saves the initial text value so that if the user's changes are not committed then 
	 */
	protected void storeOldEditValue(DirectEditRequest request)
	{
		
		CellEditor cellEditor = request.getCellEditor();
		oldValue = (String) cellEditor.getValue();
	}

	/**
	 * @param request
	 */
    protected void revertOldEditValue(DirectEditRequest request)
    {
        CellEditor cellEditor = request.getCellEditor();
        cellEditor.setValue(oldValue);
        TablePart tablePart = (TablePart) getHost();
        tablePart.revertAliasChange();
    }
}