// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.ui.wizards;

import org.eclipse.core.runtime.IPath;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.designer.core.convert.ProcessConvertManager;
import org.talend.designer.core.convert.ProcessConverterType;
import org.talend.metadata.managment.ui.i18n.Messages;

/**
 * Created by Marvin Wang on Feb 18, 2013.
 */
public class EditProcessPropertiesWizard extends PropertiesWizard {

    private IRepositoryViewObject repositoryViewObject;

    /**
     * DOC marvin EditProcessPropertiesWizard constructor comment.
     * 
     * @param repositoryViewObject
     * @param path
     * @param useLastVersion
     */
    public EditProcessPropertiesWizard(IRepositoryViewObject repositoryViewObject, IPath path, boolean useLastVersion) {
        super(repositoryViewObject, path, useLastVersion);
        this.repositoryViewObject = repositoryViewObject;
        setWindowTitle(Messages.getString("EditProcessPropertiesWizard.wizardTitle")); //$NON-NLS-1$
    }

    @Override
    public void addPages() {
        mainPage = new EditProcessPropertiesWizardPage(Messages.getString("EditProcessPropertiesWizard.pageName"), //$NON-NLS-1$
                object.getProperty(), path, isReadOnly(), false, lastVersionFound);
        mainPage.setItem(object.getProperty().getItem());
        mainPage.setConverter(ProcessConvertManager.getInstance().extractConvertService(
                ProcessConverterType.CONVERTER_FOR_MAPREDUCE));
        addPage(mainPage);
    }

    /**
     * Getter for repositoryViewObject.
     * 
     * @return the repositoryViewObject
     */
    public IRepositoryViewObject getRepositoryViewObject() {
        return this.repositoryViewObject;
    }

    /**
     * Sets the repositoryViewObject.
     * 
     * @param repositoryViewObject the repositoryViewObject to set
     */
    public void setRepositoryViewObject(IRepositoryViewObject repositoryViewObject) {
        this.repositoryViewObject = repositoryViewObject;
    }

}
