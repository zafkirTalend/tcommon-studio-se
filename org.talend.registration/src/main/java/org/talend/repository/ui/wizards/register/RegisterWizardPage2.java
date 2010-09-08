// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.ui.wizards.register;

import java.util.Locale;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.talend.commons.exception.BusinessException;
import org.talend.commons.exception.MessageBoxExceptionHandler;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.ui.branding.IBrandingService;
import org.talend.repository.i18n.Messages;
import org.talend.repository.registeruser.RegisterManagement;

/**
 * DOC zli class global comment. Detailled comment
 */
public class RegisterWizardPage2 extends AbstractBasicWizardDialog {

    private int countryToSelect = 0;

    private String str1;

    private String str2;

    private String email;

    private String country;

    private boolean alreadyRegistered;

    private String password;

    private String firstname;

    private String lastname;

    private String pseudonym;

    private String proxyHost;

    private String proxyPort;

    private boolean isProxyEnable;

    private Text passwordText;

    private Text passwordText2;

    private Text pseudonymText;

    private Label password2ValidateLabel;

    private String validateStr = "*";

    private String notValidateStr = Messages.getString("RegisterWizardPage.notValid");

    private Button nextButton;

    /**
     * DOC zli RegisterWizardPage1 constructor comment.
     * 
     * @param parentShell
     * @param wizard
     */
    public RegisterWizardPage2(Shell parentShell, IWizard wizard) {
        super(parentShell, wizard);
    }

    public RegisterWizardPage2(Shell parentShell, IWizard wizard, String email, String country, boolean alreadyRegistered,
            String firstname, String lastname, String pseudonym, String password, boolean isProxyEnable, String proxyHost,
            String proxyPort) {
        super(parentShell, wizard);
        this.email = email;
        this.country = country;
        this.alreadyRegistered = alreadyRegistered;
        this.firstname = firstname;
        this.lastname = lastname;
        this.pseudonym = pseudonym;
        this.password = password;
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
        this.isProxyEnable = isProxyEnable;

    }

    @Override
    protected void rightComposite(Composite composite, int style) {

        GridData data;
        Composite c = new Composite(composite, SWT.NONE);
        c.setLayout(new GridLayout(3, false));
        c.setLayoutData(new GridData());

        IBrandingService brandingService = (IBrandingService) GlobalServiceRegister.getDefault().getService(
                IBrandingService.class);
        if (alreadyRegistered) {
            str1 = Messages.getString("RegisterWizardPage.logininfosPage2Yes1", brandingService.getCorporationName());
        } else {
            str1 = Messages.getString("RegisterWizardPage.logininfosPage2Not1", brandingService.getCorporationName());
        }
        createLegalInfos(c, 3, str1);
        createSpacer(c, 3);

        // email
        Label emailLable = new Label(c, SWT.NONE);
        emailLable.setText(Messages.getString("RegisterWizardPage.email"));
        data = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
        data.widthHint = 100;
        data.horizontalIndent = 50;
        emailLable.setLayoutData(data);

        Text emailText = new Text(c, SWT.BORDER);
        data = new GridData();
        data.widthHint = 200;
        emailText.setLayoutData(data);
        emailText.setText(email);
        emailText.setEditable(false);
        emailText.setEnabled(false);
        new Label(c, SWT.NONE);

        // country
        Label countryLable = new Label(c, SWT.NONE);
        data = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
        data.widthHint = 100;
        data.horizontalIndent = 50;
        countryLable.setLayoutData(data);
        countryLable.setText(Messages.getString("RegisterWizardPage.country"));
        Combo countryCombo = new Combo(c, SWT.BORDER | SWT.READ_ONLY);
        data = new GridData();
        data.widthHint = 200 - 22;
        countryCombo.setItems(initiateCountryList());
        countryCombo.select(countryToSelect);
        countryCombo.setLayoutData(data);
        new Label(c, SWT.NONE);

        createSpacer(c, 3);
        if (alreadyRegistered) {
            str2 = Messages.getString("RegisterWizardPage.logininfosPage2Yes2", brandingService.getCorporationName());
        } else {
            str2 = Messages.getString("RegisterWizardPage.logininfosPage2Not2", brandingService.getCorporationName());
        }
        createLegalInfos(c, 3, str2);
        createSpacer(c, 3);

        // password
        Label password = new Label(c, SWT.NONE);
        data = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
        data.widthHint = 100;
        data.horizontalIndent = 50;
        password.setLayoutData(data);
        password.setText(Messages.getString("RegisterWizardPage.password"));
        passwordText = new Text(c, SWT.BORDER);
        data = new GridData();
        data.widthHint = 200;
        passwordText.setLayoutData(data);
        passwordText.setEchoChar('*');//$NON-NLS-N$
        if (alreadyRegistered) {
            passwordText.setText(this.password);
        }
        Label a = new Label(c, SWT.NONE);
        a.setText("*");
        a.setForeground(new Color(null, new RGB(255, 0, 0)));

        // password again
        Label password2 = new Label(c, SWT.NONE);
        data = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
        data.widthHint = 100;
        data.horizontalIndent = 50;
        password2.setLayoutData(data);
        password2.setText(Messages.getString("RegisterWizardPage.password"));
        passwordText2 = new Text(c, SWT.BORDER);
        data = new GridData();
        data.widthHint = 200;
        passwordText2.setLayoutData(data);
        passwordText2.setEchoChar('*');//$NON-NLS-N$
        if (alreadyRegistered) {
            passwordText2.setText(this.password);
        }
        password2ValidateLabel = new Label(c, SWT.NONE);
        password2ValidateLabel.setText(validateStr);
        data = new GridData();
        data.widthHint = 80;
        password2ValidateLabel.setLayoutData(data);
        password2ValidateLabel.setForeground(new Color(null, new RGB(255, 0, 0)));

        // firstName
        Label firstName = new Label(c, SWT.NONE);
        data = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
        data.widthHint = 100;
        data.horizontalIndent = 50;
        firstName.setLayoutData(data);
        firstName.setText(Messages.getString("RegisterWizardPage.firstName"));
        Text firstNameText = new Text(c, SWT.BORDER);
        data = new GridData();
        data.widthHint = 200;
        firstNameText.setLayoutData(data);
        if (alreadyRegistered) {
            firstNameText.setText(firstname);
        }
        new Label(c, SWT.NONE);

        // sureName
        Label surName = new Label(c, SWT.NONE);
        data = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
        data.widthHint = 100;
        data.horizontalIndent = 50;
        surName.setLayoutData(data);
        surName.setText(Messages.getString("RegisterWizardPage.surName"));
        Text surNameText = new Text(c, SWT.BORDER);
        data = new GridData();
        data.widthHint = 200;
        surNameText.setLayoutData(data);
        if (alreadyRegistered) {
            surNameText.setText(lastname);
        }
        new Label(c, SWT.NONE);

        // pseudonym
        Label pseudonym = new Label(c, SWT.NONE);
        data = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
        data.widthHint = 100;
        data.horizontalIndent = 50;
        pseudonym.setLayoutData(data);
        pseudonym.setText(Messages.getString("RegisterWizardPage.pseudonym"));
        pseudonymText = new Text(c, SWT.BORDER);
        data = new GridData();
        data.widthHint = 200;
        pseudonymText.setLayoutData(data);
        if (alreadyRegistered) {
            pseudonymText.setText(this.pseudonym);
        }
        Label d = new Label(c, SWT.NONE);
        d.setText("*");
        d.setForeground(new Color(null, new RGB(255, 0, 0)));
        createSpacer(c, 2);

        addListener();
    }

    private void addListener() {
        passwordText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {

                String password = passwordText.getText();
                String password2 = passwordText2.getText();
                String pseudonym = pseudonymText.getText();
                if (password != null && !"".equals(password) && password2 != null && !"".equals(password2)
                        && password.equals(password2)) {
                    password2ValidateLabel.setText(validateStr);
                    if (pseudonym != null && !"".equals(pseudonym)) {//$NON-NLS-N$//$NON-NLS-N$//$NON-NLS-N$
                        nextButton.setEnabled(true);
                    } else {
                        nextButton.setEnabled(false);
                    }
                } else {
                    nextButton.setEnabled(false);
                    password2ValidateLabel.setText(notValidateStr);
                }
            }
        });

        passwordText2.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                String password = passwordText.getText();
                String password2 = passwordText2.getText();
                String pseudonym = pseudonymText.getText();

                if (password != null && !"".equals(password) && password2 != null && !"".equals(password2)
                        && password.equals(password2)) {
                    password2ValidateLabel.setText(validateStr);
                    if (pseudonym != null && !"".equals(pseudonym)) {//$NON-NLS-N$//$NON-NLS-N$//$NON-NLS-N$
                        nextButton.setEnabled(true);
                    } else {
                        nextButton.setEnabled(false);
                    }
                } else {
                    nextButton.setEnabled(false);
                    password2ValidateLabel.setText(notValidateStr);
                }
            }
        });
        pseudonymText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                String password = passwordText.getText();
                String password2 = passwordText2.getText();
                String pseudonym = pseudonymText.getText();
                if (password != null
                        && !"".equals(password) && password2 != null && !"".equals(password2) && password.equals(password2) && pseudonym != null && !"".equals(pseudonym)) {//$NON-NLS-N$//$NON-NLS-N$//$NON-NLS-N$
                    nextButton.setEnabled(true);
                } else {
                    nextButton.setEnabled(false);
                }
            }
        });
    }

    protected void createBottomComposite(Composite composite, int style) {
        Composite bottom = new Composite(composite, SWT.NONE);
        GridLayout gd = new GridLayout();
        bottom.setLayout(gd);

        String string = Messages.getString("RegisterWizardPage.registration_not_effective");
        Label label = new Label(bottom, SWT.NONE);

        GridData lgd = new GridData(GridData.FILL_BOTH);
        label.setLayoutData(lgd);
        label.setForeground(new Color(null, new RGB(240, 0, 0)));
        label.setText(string);
        new Label(bottom, SWT.NONE);
        new Label(bottom, SWT.NONE);
    }

    private String[] initiateCountryList() {
        SortedSet<String> countryList = new TreeSet<String>();
        for (Locale locale : Locale.getAvailableLocales()) {
            if (locale.getDisplayCountry().compareTo("") != 0) { //$NON-NLS-1$
                countryList.add(locale.getDisplayCountry());
            }
        }

        // String defaultCountry = Locale.getDefault().getDisplayCountry();
        int i = 0;
        for (String countrys : countryList) {
            if (countrys.equals(country)) {
                countryToSelect = i;
                break;
            }
            i++;
        }
        return countryList.toArray(new String[] {});
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.BACK_ID, IDialogConstants.BACK_LABEL, false);
        nextButton = createButton(parent, IDialogConstants.NEXT_ID, IDialogConstants.NEXT_LABEL, true);
        nextButton.setEnabled(false);
    }

    @Override
    protected void buttonPressed(int buttonId) {
        if (IDialogConstants.NEXT_ID == buttonId) {
            boolean updateOrCreateSuccess = false;
            boolean success = false;
            try {
                if (alreadyRegistered) {
                    success = RegisterManagement.updateUser(email, pseudonym, password, firstname, lastname, country,
                            isProxyEnable, proxyHost, proxyPort);
                } else {
                    success = RegisterManagement.createUser(email, pseudonym, password, firstname, lastname, country,
                            isProxyEnable, proxyHost, proxyPort);
                }
                if (success) {
                    updateOrCreateSuccess = true;
                }
            } catch (BusinessException e) {
                MessageBoxExceptionHandler.process(e);
                updateOrCreateSuccess = false;
            } finally {

                close();
                RegisterWizard registerWizard = new RegisterWizard();
                if (!updateOrCreateSuccess) {
                    email = null;
                }
                RegisterWizardPage3 dialog = new RegisterWizardPage3(this.getShell(), registerWizard, email);
                dialog.open();
            }

        } else if (IDialogConstants.BACK_ID == buttonId) {
            close();
            RegisterWizard registerWizard = new RegisterWizard();
            RegisterWizardPage1 dialog = new RegisterWizardPage1(this.getShell(), registerWizard, email, country);
            dialog.open();
        }
    }

}
