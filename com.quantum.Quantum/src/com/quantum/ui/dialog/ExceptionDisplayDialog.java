package com.quantum.ui.dialog;

import com.quantum.Messages;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author BC
 */
public class ExceptionDisplayDialog extends Dialog {

    private Control detailsArea;
    private Throwable throwable;
    private String message;

    /**
     * @param parentShell
     */
    public ExceptionDisplayDialog(Shell parentShell, Throwable throwable) {
        super(parentShell);
        this.throwable = throwable;
    }

    /**
     * The Details button.
     */
    private Button detailsButton;

    /**
     * The title of the dialog.
     */
    private String title;

    /**
     * Indicates whether the error details viewer is currently created.
     */
    private boolean detailsShown = false;

    /**
     * Creates an error dialog.
     * Note that the dialog will have no visual representation (no widgets)
     * until it is told to open.
     * <p>
     * Normally one should use <code>openError</code> to create and open one of these.
     * This constructor is useful only if the error object being displayed contains child
     * items <it>and</it> you need to specify a mask which will be used to filter the
     * displaying of these children.
     * </p>
     *
     * @param parentShell the shell under which to create this dialog
     * @param dialogTitle the title to use for this dialog,
     *   or <code>null</code> to indicate that the default title should be used
     * @param message the message to show in this dialog, 
     *   or <code>null</code> to indicate that the error's message should be shown
     *   as the primary message
     * @param status the error to show to the user
     * @param displayMask the mask to use to filter the displaying of child items,
     *   as per <code>IStatus.matches</code>
     * @see org.eclipse.core.runtime.IStatus#matches(int)
     */
    public ExceptionDisplayDialog(
        Shell parentShell,
        String dialogTitle,
        String message,
        Throwable throwable) {
        super(parentShell);
        
        this.title = dialogTitle;
        this.message = message;
        this.throwable = throwable;

        setShellStyle(SWT.DIALOG_TRIM | SWT.RESIZE | SWT.APPLICATION_MODAL);
    }

    /* (non-Javadoc)
     * Method declared on Dialog.
     * Handles the pressing of the Ok or Details button in this dialog.
     * If the Ok button was pressed then close this dialog.  If the Details
     * button was pressed then toggle the displaying of the error details area.
     * Note that the Details button will only be visible if the error being
     * displayed specifies child details.
     */
    protected void buttonPressed(int id) {
        if (id == IDialogConstants.DETAILS_ID) {
            // was the details button pressed?
            toggleDetailsArea();
        } else {
            super.buttonPressed(id);
        }
    }
    /* (non-Javadoc)
     * Method declared in Window.
     */
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText(title);
    }
    /* (non-Javadoc)
     * Method declared on Dialog.
     */
    protected void createButtonsForButtonBar(Composite parent) {
        // create OK and Details buttons
        createButton(
            parent,
            IDialogConstants.OK_ID,
            IDialogConstants.OK_LABEL,
            true);
        this.detailsButton =
            createButton(
                parent,
                IDialogConstants.DETAILS_ID,
                IDialogConstants.SHOW_DETAILS_LABEL,
                false);
    }

    protected Control createDialogArea(Composite parent) {

        // create a composite with standard margins and spacing
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginHeight =
            convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
        layout.marginWidth =
            convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
        layout.verticalSpacing =
            convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
        layout.horizontalSpacing =
            convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
        composite.setLayout(layout);
        GridData childData = new GridData(GridData.FILL_BOTH);
        childData.horizontalSpan = 2;
        composite.setLayoutData(childData);
        composite.setFont(parent.getFont());

        Label label = new Label(composite, 0);
        label.setText(this.message);
        label.setFont(parent.getFont());
        
        
        GridData full = new GridData();
        full.horizontalAlignment = GridData.FILL;
        full.verticalAlignment = GridData.FILL;
        full.heightHint = convertHeightInCharsToPixels(3);
        full.widthHint = convertWidthInCharsToPixels(60);
        label.setLayoutData(full);

        return composite;

    }

    /**
     * Create the expandable details arae.
     *
     * @param parent the parent composite
     * @return the details text control
     */
    protected Control createDetailsArea(Composite parent) {
        
        // create a composite with standard margins and spacing
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginHeight =
            convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
        layout.marginWidth =
            convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
        layout.verticalSpacing =
            convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
        layout.horizontalSpacing =
            convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
        composite.setLayout(layout);
        GridData childData = new GridData(GridData.FILL_BOTH);
        childData.horizontalSpan = 2;
        composite.setLayoutData(childData);
        composite.setFont(parent.getFont());

        Label label = new Label(composite, 0);
        label.setText(Messages.getString("ExceptionDisplayDialog.stackTrace"));
        label.setFont(parent.getFont());
        
        GridData full = new GridData();
        full.horizontalAlignment = GridData.FILL;
        full.verticalAlignment = GridData.FILL;
        full.heightHint = convertHeightInCharsToPixels(3);
        full.widthHint = convertWidthInCharsToPixels(60);
        label.setLayoutData(full);

        Text text =
            new Text(
                composite,
                SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI);

        text.setText(this.throwable.getLocalizedMessage());

        GridData data =
            new GridData(
                GridData.HORIZONTAL_ALIGN_FILL
                    | GridData.GRAB_HORIZONTAL
                    | GridData.VERTICAL_ALIGN_FILL
                    | GridData.GRAB_VERTICAL);
        data.heightHint = convertHeightInCharsToPixels(8);
        text.setLayoutData(data);
        text.setFont(parent.getFont());

        this.detailsShown = true;
        return composite;
    }
    /**
     * Opens an error dialog to display the given error.  Use this method if the
     * error object being displayed contains child items <it>and</it> you wish to
     * specify a mask which will be used to filter the displaying of these
     * children.  The error dialog will only be displayed if there is at
     * least one child status matching the mask.
     *
     * @param parentShell - 
     *   the parent shell of the dialog, or <code>null</code> if none
     * @param title the title to use for this dialog,
     *   or <code>null</code> to indicate that the default title should be used
     * @param message the message to show in this dialog, 
     *   or <code>null</code> to indicate that the error's message should be shown
     *   as the primary message
     *   as per <code>IStatus.matches</code>
     * @return the code of the button that was pressed that resulted in this dialog
     *     closing.  This will be <code>Dialog.OK</code> if the OK button was 
     *     pressed, or <code>Dialog.CANCEL</code> if this dialog's close window 
     *     decoration or the ESC key was used.
     * @see org.eclipse.core.runtime.IStatus#matches(int)
     */
    public static int openError(
        Shell parentShell,
        String title,
        String message,
        Throwable throwable) {
            
        if (title == null) {
            title = Messages.getString(ExceptionDisplayDialog.class.getName() +
                "." + throwable.getClass().getName() + ".title");
        }
        if (message == null) {
            message = Messages.getString(ExceptionDisplayDialog.class.getName() +
                "." + throwable.getClass().getName() + ".message");
        }
        ExceptionDisplayDialog dialog =
            new ExceptionDisplayDialog(parentShell, title, message, throwable);
        return dialog.open();
    }

    /**
     * Toggles the unfolding of the details area.  This is triggered by
     * the user pressing the details button.
     */
    private void toggleDetailsArea() {
        Point windowSize = getShell().getSize();
        Point oldSize = getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT);

        if (this.detailsShown) {
            this.detailsArea.dispose();
            this.detailsShown = false;
            detailsButton.setText(IDialogConstants.SHOW_DETAILS_LABEL);
        } else {
            this.detailsArea = createDetailsArea((Composite) getContents());
            detailsButton.setText(IDialogConstants.HIDE_DETAILS_LABEL);
        }

        Point newSize = getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT);

        getShell().setSize(
            new Point(windowSize.x, windowSize.y + (newSize.y - oldSize.y)));

    }
}
