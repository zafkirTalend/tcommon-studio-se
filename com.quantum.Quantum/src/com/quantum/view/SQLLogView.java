package com.quantum.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.StyledTextContent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.ViewPart;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.log.LogEntry;
import com.quantum.log.QuantumLog;
import com.quantum.log.Severity;

public class SQLLogView extends ViewPart implements PropertyChangeListener {
    
	private Color debugColor;
	private Color warningColor;
	private Color defaultColor;
	private Color errorColor;
	private Color infoColor;
	private StyledText widget;
	private static final String newLine = "\n"; //$NON-NLS-1$
	
	private int numberSinceLastPurge = 0;

	public void createPartControl(Composite parent) {
	   	initializeColors();
		this.widget =  new StyledText(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		IActionBars bars = this.getViewSite().getActionBars();
		bars.setGlobalActionHandler(ActionFactory.COPY.getId(), copyAction);
		bars.setGlobalActionHandler(ActionFactory.SELECT_ALL.getId(), selectAllAction);

		IToolBarManager toolBar = getViewSite().getActionBars().getToolBarManager();
		clearAction.setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.CLEAR));
		clearAction.setToolTipText(Messages.getString("SQLLogView.ClearLog")); //$NON-NLS-1$
		toolBar.add(clearAction);

 		widget.setEditable(false);
 		setText(QuantumLog.getInstance().getEntries());
 		QuantumLog.getInstance().addPropertyChangeListener(this);
	}
	
    /**
     * 
     */
    private void initializeColors() {
        this.defaultColor = this.debugColor = this.infoColor = 
	   	    	Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
	   	this.errorColor = Display.getCurrent().getSystemColor(SWT.COLOR_DARK_RED);
	   	this.warningColor = Display.getCurrent().getSystemColor(SWT.COLOR_DARK_YELLOW);
    }

    private void addEntry(LogEntry entry) {
    	String text = entry.toString() + newLine;
    	int start = widget.getText().length();
		StyleRange styleRange = new StyleRange();
		styleRange.start = start;
		styleRange.length = text.length();
		styleRange.foreground = getColor(entry);
		widget.append(text);
		widget.setStyleRange(styleRange);
		scrollToBottom();
    }

    /**
     * @param entry
     * @param styleRange
     */
    private Color getColor(LogEntry entry) {
        if (entry.getSeverity() == Severity.DEBUG) {
			return debugColor;
		} else if (entry.getSeverity() == Severity.INFO) {
			return infoColor;
		} else if (entry.getSeverity() == Severity.WARN) {
			return warningColor;
		} else if (entry.getSeverity() == Severity.ERROR) {
			return errorColor;
		} else {
			return defaultColor;
		}
    }

    public void dispose() {
        QuantumLog.getInstance().removePropertyChangeListener(this);
        super.dispose();
    }
	protected void scrollToBottom() {
		StyledTextContent document = widget.getContent();
		int length= document.getCharCount();
		if (length > 0) {
			widget.setCaretOffset(length);
			widget.showSelection();
		}
	}

	public void setFocus() {
		widget.setFocus();
	}

	private Action copyAction = new Action() {
		public void run() {
			widget.copy();
		}
	};
	private Action selectAllAction = new Action() {
		public void run() {
			widget.selectAll();
		}
	};
	private Action clearAction = new Action() {
		public void run() {
			QuantumLog.getInstance().clear(); //$NON-NLS-1$
		}
	};

	/**
	 * Dispatch a change to the SWT Thread and update the log view as soon as possible.
	 */
    public void propertyChange(final PropertyChangeEvent event) {
        if ("entries".equals(event.getPropertyName())) {
        	getViewSite().getShell().getDisplay().asyncExec(new Runnable() {
        		public void run() {
					if (event.getNewValue() == null) {
    					setText(QuantumLog.getInstance().getEntries());
    				} else if (shouldRefresh()) {
    					setText(QuantumLog.getInstance().getEntries());
    				} else {
    					addEntry((LogEntry) event.getNewValue());
    				}
        		}
    		});
        }
    }

    /**
     * The performance would be terrible if we cleared the text area every time a new line
     * was added.  So only do it a few times.
     * 
     * @return
     */
    private boolean shouldRefresh() {
        this.numberSinceLastPurge = (this.numberSinceLastPurge + 1) % 
        		(QuantumLog.getInstance().getNumberOfEntries() / 2);
        return this.numberSinceLastPurge == 0;
    }

    /**
     * @param entries
     */
    private void setText(LogEntry[] entries) {
        this.widget.setText("");
        for (int i = 0, length = (entries == null ? 0 : entries.length); i < length; i++) {
            addEntry(entries[i]);
        }
    }
}
