package com.quantum.actions;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

public class MarkForOutputAction extends SelectionAction {

    private Request request;
    
    public MarkForOutputAction(IWorkbenchPart part) {
        super(part);
        setText("Toggle query output");
        request = new Request("MarkForOutput");
        setId("com.quantum.actions.MarkForOutput");
        setToolTipText("Toggle this to add the column to the target list of the query");
    }

    protected boolean calculateEnabled() {
        if (getSelectedObjects().isEmpty())
            return false;
        List parts = getSelectedObjects();
        for (int i=0; i<parts.size(); i++){
            Object o = parts.get(i);
            if (!(o instanceof EditPart))
                return false;
        }
        return true;
    }
    
    public Command getCommand(){
        List editparts = getSelectedObjects();

        CompoundCommand cc = new CompoundCommand();
        for (int i=0; i < editparts.size(); i++) {
            EditPart part = (EditPart)editparts.get(i);
            cc.add(part.getCommand(request));
        }
 
        return cc;
    
}
    /**
     * Performs the delete action on the selected objects.
     */
    public void run() {
        execute(getCommand());
    }

}
