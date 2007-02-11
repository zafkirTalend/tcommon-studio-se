/*
 * Created on 28-ago-2003
 *
 */
package com.quantum.ui.dialog;

import com.quantum.Messages;
import com.quantum.util.connection.PasswordFinder;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author panic
 *
 */
public class PasswordDialog extends InputDialog {

    static class PasswordFinderImpl implements PasswordFinder {
        private boolean passwordMeantToBeSaved = false;
        private String password;
        private boolean prompted = false;
        private Shell shell;
        
        PasswordFinderImpl(Shell shell) {
            this.shell = shell;
        }

        protected void prompt() {
            PasswordDialog dialog =
                new PasswordDialog(
                    this.shell,
                    Messages.getString("MultiSQLServer.PasswordNotProvided"),
                    Messages.getString("MultiSQLServer.Enter_Password"),
                    "",
                    this);
            dialog.open();
            this.prompted = true;
        }

        /**
         * @return
         */
        public String getPassword() {
            if (!this.prompted) {
                prompt();
            }
            return password;
        }

        /**
         * @return
         */
        public boolean isPasswordMeantToBeSaved() {
            if (!this.prompted) {
                prompt();
            }
            return passwordMeantToBeSaved;
        }

        /**
         * @param string
         */
        public void setPassword(String string) {
            password = string;
        }

        /**
         * @param b
         */
        public void setPasswordMeantToBeSaved(boolean b) {
            passwordMeantToBeSaved = b;
        }

    }

    private PasswordFinderImpl passwordFinderImpl;

    /**
     * @param parentShell
     * @param dialogTitle
     * @param dialogMessage
     * @param initialValue
     * @param validator
     */
    protected PasswordDialog(
        Shell parentShell,
        String dialogTitle,
        String dialogMessage,
        String initialValue,
        PasswordFinderImpl passwordFinderImpl) {
        super(parentShell, dialogTitle, dialogMessage, initialValue, null);
        this.passwordFinderImpl = passwordFinderImpl;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    protected Control createDialogArea(Composite parent) {

        Control control = super.createDialogArea(parent);
        Text text = getText();
        text.setEchoChar('*');
        return control;
    }

    public static PasswordFinder createPasswordFinder(Shell parentShell) {
        PasswordFinderImpl passwordFinder = new PasswordFinderImpl(parentShell);
        return passwordFinder;
    }
    /**
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    protected void okPressed() {
        this.passwordFinderImpl.setPassword(getText().getText());
        super.okPressed();
    }

}
