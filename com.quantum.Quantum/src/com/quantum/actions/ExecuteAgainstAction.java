package com.quantum.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.quantum.sql.parser.SQLParser;
import com.quantum.util.io.InputStreamHelper;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;

/**
 * This action can be executed against any .sql file, regardless of 
 * whether or not the Quantum perspective is open.
 * 
 * @author BC Holmes
 */
public class ExecuteAgainstAction extends BaseExecuteAction
    implements IObjectActionDelegate {

    private IFile[] files = null;

    private IWorkbenchPart workbenchPart;
    
    /**
     * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
     */
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        this.workbenchPart = targetPart;
    }

    protected Shell getShell() {    
        return this.workbenchPart.getSite().getShell();
    }

    /**
     * This method is called when a new selection has been made on one 
     * of the views.
     * 
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(IAction action, ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection =
                (IStructuredSelection) selection;
            List list = new ArrayList();

            for (Iterator i = structuredSelection.iterator(); i.hasNext();) {
                Object temp = i.next();
                if (temp != null && temp instanceof IFile) {
                    list.add(temp);
                }
            }
            this.files = (IFile[]) list.toArray(new IFile[list.size()]);
        }
    }

	/* (non-Javadoc)
	 * @see com.quantum.actions.BaseSQLAction#getQueries()
	 */
	protected List getQueries(boolean sendQueryAsIs) throws IOException, CoreException {
		List list = new ArrayList();
		for (int i = 0, length = this.files == null ? 0 : this.files.length; i < length; i++) {
			String fileContents = InputStreamHelper.readIntoString(this.files[i]
					.getContents(), this.files[i].getCharset());
			List queryList;
			if (sendQueryAsIs) {
				queryList = new Vector();
				queryList.add(fileContents);
			} else {
				queryList = SQLParser.parse(fileContents);
			}
			list.addAll(queryList);
		}
		return list;
	}

	protected IStatusLineManager getStatusLineManager() {
		if (this.workbenchPart instanceof IViewPart) {
			return ((IViewPart) this.workbenchPart).getViewSite().getActionBars().getStatusLineManager();
		} else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		run();
	}
}
