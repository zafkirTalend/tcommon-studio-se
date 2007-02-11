/**
 * ActionGroup for the BeanShell View
 */
package com.quantum.view.beanshell;

import java.io.IOException;
import java.io.PrintStream;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionGroup;

import bsh.EvalError;
import bsh.Interpreter;

import com.quantum.ImageStore;
import com.quantum.Messages;

/**
 * @author panic
 *
 */
public class BeanShellActionGroup extends ActionGroup {
	
	private class EvaluateAction extends Action {
		private final Interpreter interpreter;
		
		public EvaluateAction(Interpreter interpreter) {
			this.interpreter = interpreter;
			setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.PLAY));
			setToolTipText(Messages.getString("beanshellview.evaluate"));
		}
		public void run() {
			//Save the output and error streams because we'll redirect it
			PrintStream sOut = System.out;
			PrintStream sErr = System.err;
			try {
				view.getOutput().reset();
				view.getError().reset();
				// Redirect the output and error streams to our byte array stream
				System.setOut(interpreter.getOut());
				System.setErr(interpreter.getErr());
				Object result = interpreter.eval(view.getScriptText());
			} catch (EvalError e) {
				e.printStackTrace();
			} finally {
				System.setOut(sOut);
				System.setErr(sErr);
			}
			try {
				view.getOutput().flush();
				view.getError().flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			view.updateText();
		}
	}
	private class ClearAction extends Action {
		
		public ClearAction() {
			setText(Messages.getString("sqlqueryview.clear"));
			setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.CLEAR));
			setToolTipText(Messages.getString("sqlqueryview.clear"));
		}
		public void run() {
			view.clear(BeanShellView.ALL);
		}
	}
	private class ClearAllButScriptAction extends Action {
		
		public ClearAllButScriptAction() {
			setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.CLEAR));
			setToolTipText(Messages.getString("sqlqueryview.clearAllButScript"));
		}
		public void run() {
			view.clear(BeanShellView.ALL_BUT_SCRIPT);
		}
	}
	
	private final BeanShellView view;
	private EvaluateAction evaluateAction;
	private ClearAction clearAction;
	private ClearAllButScriptAction clearAllButScriptAction;
	private ExportScriptAction exportScriptAction;
	private ImportScriptAction importScriptAction;
	private ExportOutputAction exportOutputAction;
	
	public BeanShellActionGroup(BeanShellView view) {
		this.view = view;
		this.evaluateAction = new EvaluateAction(view.getInterpreter());
		this.clearAllButScriptAction = new ClearAllButScriptAction();
		this.clearAction = new ClearAction();
		this.exportScriptAction = new ExportScriptAction();
		this.exportScriptAction.init(this.view);
		this.importScriptAction = new ImportScriptAction();
		this.importScriptAction.init(this.view);
		this.exportOutputAction = new ExportOutputAction();
		this.exportOutputAction.init(this.view);
        
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.actions.ActionGroup#fillActionBars(org.eclipse.ui.IActionBars)
	 */
	public void fillActionBars(IActionBars actionBars) {
        IToolBarManager toolBar = actionBars.getToolBarManager();
		toolBar.add(this.evaluateAction);
		toolBar.add(this.clearAllButScriptAction);
		
		actionBars.getMenuManager().add(this.importScriptAction);
		actionBars.getMenuManager().add(this.exportScriptAction);
		actionBars.getMenuManager().add(new Separator());
        actionBars.getMenuManager().add(this.exportOutputAction);
		actionBars.getMenuManager().add(this.clearAction);
	       
	}
	
}
