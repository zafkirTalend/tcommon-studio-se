// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.dataprofiler.core.ui.wizard;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.RefreshAction;
import org.talend.cwm.management.api.ConnectionService;
import org.talend.cwm.management.connection.ConnectionParameters;
import org.talend.cwm.softwaredeployment.TdDataProvider;
import org.talend.dataprofiler.core.ImageLib;

/**
 * DatabaseWizard present the DatabaseForm. Use to manage the metadata connection.
 */
public class DatabaseConnectionWizard extends Wizard implements INewWizard {

    private static Logger log = Logger.getLogger(DatabaseConnectionWizard.class);

    private boolean creation;

    private PropertiesWizardPage propertiesWizardPage;

    private ConnectionParameters connectionProperty;

    private IWorkbench workbench;

    private Object selection;

    private DatabaseWizardPage databaseWizardPage;

    /**
     * Constructor for DatabaseWizard. Analyse Iselection to extract DatabaseConnection and the pathToSave. Start the
     * Lock Strategy.
     * 
     * @param selection
     * @param existingNames
     */
    public DatabaseConnectionWizard(IWorkbench workbench, boolean creation, IStructuredSelection selection, String[] existingNames) {
        super();
        this.init(workbench, selection);
        this.creation = creation;
    }

    public void init(IWorkbench workbench, IStructuredSelection selection) {
        this.workbench = workbench;
        this.selection = selection;
    }

    /**
     * Adding the page to the wizard and set Title, Description and PageComplete.
     */
    public void addPages() {
        setWindowTitle("Database Connection");
        setDefaultPageImageDescriptor(ImageLib.getImageDescriptor(ImageLib.REFRESH_IMAGE));
        connectionProperty = new ConnectionParameters();
        connectionProperty.setParameters(new Properties());
        propertiesWizardPage = new Step0WizardPage(connectionProperty, null, false, creation);
        databaseWizardPage = new DatabaseWizardPage("DatabaseParam Page", connectionProperty);

        if (creation) {
            propertiesWizardPage.setTitle("New Database Connection on repository - Step 1/2");
            propertiesWizardPage.setDescription("Define the properties");
            propertiesWizardPage.setPageComplete(false);

            databaseWizardPage.setTitle("Database connection");
            databaseWizardPage.setDescription("New database connection on repository - step 2/2"); //$NON-NLS-1$
            // //$NON-NLS-1$
            // databaseWizardPage.setPageComplete(false);
        } else {
            propertiesWizardPage.setTitle("Update Database Connection - Step 1/2");
            propertiesWizardPage.setDescription("Update properties");
            // propertiesWizardPage.setPageComplete(isRepositoryObjectEditable());

            // databaseWizardPage.setTitle(Messages.getString("DatabaseWizardPage.titleUpdate.Step2")); //$NON-NLS-1$
            // databaseWizardPage.setDescription(Messages.getString("DatabaseWizardPage.descriptionUpdate.Step2"));
            // //$NON-NLS-1$
            // databaseWizardPage.setPageComplete(isRepositoryObjectEditable());
        }
        addPage(propertiesWizardPage);
        addPage(databaseWizardPage);
    }

    /**
     * This method is called when 'Finish' button is pressed in the wizard. Save metadata close Lock Strategy and close
     * wizard.
     */
    public boolean performFinish() {
        TdDataProvider provider = ConnectionService.createConnection(this.connectionProperty);
        if (provider == null) {
            return false;
        }

        RefreshAction refreshAction = new RefreshAction(this.getShell());
        refreshAction.run();

        // TdDataProvider provider = ConnectionService.createConnection(this.connectionProperty);
        // if (provider == null) {
        // return false;
        // }
        // DqRepositoryViewService.listTdDataProviders(folder)
        return true;
    }  
    

}
