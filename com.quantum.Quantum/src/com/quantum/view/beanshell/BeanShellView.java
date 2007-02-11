/**
 * View that interfaces with a BeanShell Interpreter to add some scripting
 * possibilities to Quantum.
 */
package com.quantum.view.beanshell;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.part.ViewPart;

import bsh.Interpreter;

/**
 * @author panic
 *
 */
public class BeanShellView extends ViewPart {

	public static final int ALL = 1;
	public static final int ALL_BUT_SCRIPT = 2;
	public static final int OUTPUT = 3;
	public static final int ERROR = 4;
	
	private Interpreter interpreter = null;
	private StyledText widget;
	private BeanShellActionGroup actionGroup;
	private ByteArrayOutputStream outStr;
	private ByteArrayOutputStream errStr;
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createPartControl(Composite parent) {
		outStr = new ByteArrayOutputStream();
		PrintStream printOut = new PrintStream(outStr);
		errStr = new ByteArrayOutputStream();
		PrintStream printErr = new PrintStream(errStr);
		interpreter = new Interpreter( null, printOut, printErr, false );
	       
		
		widget = new StyledText(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		widget.setEditable(true);
		String initialComment = "";
		widget.setText(initialComment);
		this.actionGroup = new BeanShellActionGroup(this);
		this.actionGroup.fillActionBars(getViewSite().getActionBars());

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	public void setFocus() {

	}

	public String getScriptText() {
		Display display = getSite().getShell().getDisplay();
		
		int numLin = widget.getLineCount();
		String scriptText = "";
		for (int i = 0; i < numLin; i++) {
			int offset = widget.getOffsetAtLine(i);
			int endOffset = (i+1 < numLin) ? widget.getOffsetAtLine(i+1)-1 : widget.getCharCount()-1;
			if (offset > endOffset) offset = endOffset;
			StyleRange style = widget.getStyleRangeAtOffset(offset);
			
			if (style == null) {
				scriptText += widget.getText(offset, endOffset);
			}
			
		}
		return scriptText;
	}

	public String getOutputText() {
		Display display = getSite().getShell().getDisplay();
		
		int numLin = widget.getLineCount();
		String outputText = "";
		for (int i = 0; i < numLin; i++) {
			int offset = widget.getOffsetAtLine(i);
			int endOffset = (i+1 < numLin) ? widget.getOffsetAtLine(i+1)-1 : widget.getCharCount()-1;
			if (offset > endOffset) offset = endOffset;
			StyleRange style = widget.getStyleRangeAtOffset(offset);
			
			if (style != null && style.background.equals(display.getSystemColor(SWT.COLOR_GRAY))) {
				outputText += widget.getText(offset, endOffset);
			}
			
		}
		return outputText;
	}

	public Interpreter getInterpreter() {
		return interpreter;
	}

	public void updateText() {
		String textOut = outStr.toString();
		String textErr = errStr.toString();
		int outChar = widget.getCharCount();
		widget.append(widget.getLineDelimiter() + textOut);
		Display display = getSite().getShell().getDisplay();
		widget.setStyleRange(new StyleRange(outChar, textOut.length(), display.getSystemColor(SWT.COLOR_WIDGET_FOREGROUND), display.getSystemColor(SWT.COLOR_GRAY)));
		outChar = widget.getCharCount();
		widget.append(widget.getLineDelimiter() + textErr);
		widget.setStyleRange(new StyleRange(outChar, textErr.length(),  display.getSystemColor(SWT.COLOR_RED), display.getSystemColor(SWT.COLOR_WHITE)));
	}

	public ByteArrayOutputStream getOutput() {
		return outStr;
	}

	public ByteArrayOutputStream getError() {
		return errStr;
	}

	public void clear(int part) {
		if (part == ALL) {
			widget.replaceTextRange(0, widget.getCharCount(), "");
		} else if (part == ALL_BUT_SCRIPT) {
			// The script should be in no range as have no style
			StyleRange[] ranges = widget.getStyleRanges();
			for (int i = 0; i < ranges.length; i++) {
				StyleRange range = ranges[i];
				widget.replaceTextRange(range.start, range.length, "");
			}
		}
		
	}
	public void setText(String string) {
		widget.setText(string);
	}
}
