/*
 * Created on Jul 15, 2004
 */
package com.quantum.editors.graphical.policy;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.quantum.editors.graphical.model.Column;
import com.quantum.editors.graphical.model.Table;
import com.quantum.editors.graphical.model.commands.DeleteColumnCommand;

/**
 * Column component EditPolicy - handles column deletion and selection
 */
public class ColumnEditPolicy extends ComponentEditPolicy
{

    public Command getCommand(Request request)
    {
        if ("MarkForOutput".equals(request.getType())) {
            MarkForOutputCommand command = new MarkForOutputCommand();
            command.setColumn((Column) getHost().getModel());
            return command;
        }
        return super.getCommand(request);
       
    }
	protected Command createDeleteCommand(GroupRequest request)
	{
		Table parent = (Table) (getHost().getParent().getModel());
		DeleteColumnCommand deleteCmd = new DeleteColumnCommand();
		deleteCmd.setTable(parent);
		deleteCmd.setColumn((Column) (getHost().getModel()));
		return deleteCmd;
	}

    static class MarkForOutputCommand extends Command {
        private Column column;
        
        public MarkForOutputCommand() {
            super();
        }

        public void setColumn(Column c)
        {
            this.column = c;
        }
        
        public void execute() {
            column.toggleSelected();
        }

        public void undo()
        {
            column.setSelected(false);
        }
    }
    
}