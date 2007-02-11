package com.quantum.actions;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.log.QuantumLog;
import com.quantum.util.StringUtil;
import com.quantum.view.SQLQueryView;
import com.quantum.view.ViewHelper;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

public class ExportQueryAction extends Action implements IViewActionDelegate  {
	SQLQueryView view;
	
	public ExportQueryAction() {
		setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.EXPORT));
		setText(Messages.getString("sqlqueryview.exportQuery"));
		setToolTipText(Messages.getString("sqlqueryview.exportQuery"));
	}
	
	/**
	 * @see org.eclipse.ui.IViewActionDelegate#init(IViewPart)
	 */
	public void init(IViewPart view) {
		this.view = (SQLQueryView) view;		
	}

	/**
	 * @see org.eclipse.ui.IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		run();
	}

	public void run() {
		FileOutputStream out = ViewHelper.askSaveFile("exportquery", view.getSite().getShell(), 
														new String[]{"*.sql", "*.ddl", "*.*"},
														new String[]{
																	Messages.getString("filedialog.sqlFiles"),
																	Messages.getString("filedialog.ddlFiles"),
																	Messages.getString("filedialog.allfiles")
														});
		if (out == null)
			return;
			
		try {
			FileWriter fileWriter = new FileWriter(out.getFD());
			PrintWriter writer = new PrintWriter(fileWriter);
			String output = view.getQuery();
			output = StringUtil.substituteString(output, "\r", "");
			StringTokenizer tokenizer = new StringTokenizer(output, "\n", true); //$NON-NLS-1$
			String prevToken = "";
			while (tokenizer.hasMoreElements()) {
				String token = (String) tokenizer.nextElement();
				// If it's a normal line end, we won't write it, because the println() will
				// adapting it to the OS (have to test that). But if it's a line end after
				// another, then it's a blank line.
				if (token.equals("\n"))
					if (prevToken.equals("\n"))
						writer.println(); // Two consecutives "\n", means a separate line
				else
					; // Do nothing, the end of line is already written 
				else
					writer.println(token); //Normal line
				prevToken = token;
			}
			writer.close();
		} catch (IOException e) {
			QuantumLog.getInstance().error("Problem exporting query.", e);
			e.printStackTrace();
		}
	}

	/**
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}
}
