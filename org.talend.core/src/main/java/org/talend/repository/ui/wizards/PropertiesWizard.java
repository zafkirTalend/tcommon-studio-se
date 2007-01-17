// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.repository.ui.wizards;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.talend.commons.exception.BusinessException;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.MessageBoxExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.model.properties.Property;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class PropertiesWizard extends Wizard {

    private PropertiesWizardPage mainPage;

    private IRepositoryObject object;

    private IPath path;

    public PropertiesWizard(IRepositoryObject object, IPath path) {
        super();
        this.object = object;
        this.path = path;

        lockObject();
    }

    private void lockObject() {
        IProxyRepositoryFactory repositoryFactory = CorePlugin.getDefault().getRepositoryService().getProxyRepositoryFactory();
        try {
            repositoryFactory.lock(object);
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        } catch (BusinessException e) {
            // Nothing to do
        }
    }

    private void unlockObject() {
        IProxyRepositoryFactory repositoryFactory = CorePlugin.getDefault().getRepositoryService().getProxyRepositoryFactory();
        try {
            repositoryFactory.unlock(object);
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }
    }

    private boolean isReadOnly() {
        IProxyRepositoryFactory repositoryFactory = CorePlugin.getDefault().getRepositoryService().getProxyRepositoryFactory();
        return !repositoryFactory.getStatus(object).isEditable();
    }

    @Override
    public void addPages() {
        mainPage = new PropertiesWizardPage("WizardPage", object.getProperty(), path, isReadOnly(), false) {

            public void createControl(Composite parent) {
                Composite container = new Composite(parent, SWT.NONE);
                GridLayout layout = new GridLayout(2, false);
                container.setLayout(layout);

                super.createControl(container);

                setControl(container);
                updateContent();
                addListeners();
                setPageComplete(false);
            }

            @Override
            public ERepositoryObjectType getRepositoryObjectType() {
                return object.getType();
            }
        };
        addPage(mainPage);
        setWindowTitle("Edit properties");
    }

    @Override
    public boolean performFinish() {
        IProxyRepositoryFactory repositoryFactory = CorePlugin.getDefault().getRepositoryService().getProxyRepositoryFactory();
        try {
            repositoryFactory.save(object.getProperty());
            return true;
        } catch (PersistenceException e) {
            MessageBoxExceptionHandler.process(e);
            return false;
        }
    }

    @Override
    public boolean performCancel() {
        reloadProperty();
        return true;
    }

    private void reloadProperty() {
        IProxyRepositoryFactory repositoryFactory = CorePlugin.getDefault().getRepositoryService().getProxyRepositoryFactory();
        Property property = repositoryFactory.reload(object.getProperty());
        object.setProperty(property);
    }

    @Override
    public void dispose() {
        unlockObject();
        super.dispose();
    }

}
