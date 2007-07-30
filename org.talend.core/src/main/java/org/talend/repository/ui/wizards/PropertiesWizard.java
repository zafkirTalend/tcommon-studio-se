// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
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

import java.util.Arrays;

import org.apache.log4j.Level;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.talend.commons.exception.BusinessException;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.MessageBoxExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.image.EImage;
import org.talend.commons.ui.image.ImageProvider;
import org.talend.core.CorePlugin;
import org.talend.core.i18n.Messages;
import org.talend.core.model.components.ModifyComponentsAction;
import org.talend.core.model.components.conversions.IComponentConversion;
import org.talend.core.model.components.conversions.UpdatePropertyComponentConversion;
import org.talend.core.model.components.filters.IComponentFilter;
import org.talend.core.model.components.filters.PropertyComponentFilter;
import org.talend.core.model.properties.Property;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.designer.core.model.utils.emf.talendfile.JobType;
import org.talend.designer.core.model.utils.emf.talendfile.NodeType;
import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;
import org.talend.designer.core.model.utils.emf.talendfile.RequiredType;
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

    private String originaleObjectLabel;

    public PropertiesWizard(IRepositoryObject object, IPath path) {
        super();
        this.object = object;
        this.originaleObjectLabel = object.getLabel();
        this.path = path;
        setDefaultPageImageDescriptor(ImageProvider.getImageDesc(EImage.PROPERTIES_WIZ));

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
        mainPage = new PropertiesWizardPage("WizardPage", object.getProperty(), path, isReadOnly(), false) { //$NON-NLS-1$

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
        setWindowTitle(Messages.getString("PropertiesWizard.EditPropertiesPageTitle")); //$NON-NLS-1$
    }

    @Override
    public boolean performFinish() {
        IProxyRepositoryFactory repositoryFactory = CorePlugin.getDefault().getRepositoryService().getProxyRepositoryFactory();
        try {
            repositoryFactory.save(object.getProperty());
            if (!object.getLabel().equals(originaleObjectLabel)) {
                manageRunJobRenaming(object.getLabel(), originaleObjectLabel);
            }
            return true;
        } catch (PersistenceException e) {
            MessageBoxExceptionHandler.process(e);
            return false;
        }
    }

    /**
     * Use to replace in all tRunJob, the old job name by the new one.
     */
    private void manageRunJobRenaming(String newName, String oldName) {
        System.out.println("Rename " + oldName + "->" + newName);

        IComponentFilter filter1 = new PropertyComponentFilter("tRunJob", "PROCESS_TYPE_PROCESS", oldName); //$NON-NLS-1$ //$NON-NLS-2$

        IComponentConversion updateCompProperty = new UpdatePropertyComponentConversion("PROCESS_TYPE_PROCESS", newName); //$NON-NLS-1$
        IComponentConversion updateRequiredProperty = new UpdateRequiredProperty(oldName, newName);

        try {
            ModifyComponentsAction.searchAndModify(filter1, Arrays.<IComponentConversion> asList(updateCompProperty,
                    updateRequiredProperty));
        } catch (Exception e) {
            ExceptionHandler.process(e, Level.ERROR);
        }
    }

    /**
     * Update the "required" property.
     * 
     * <required> <job context="Default" name="newJobName"/> </required>
     */
    private class UpdateRequiredProperty implements IComponentConversion {

        private String oldJobName;

        private String newJobName;

        public UpdateRequiredProperty(String oldJobName, String newJobName) {
            super();
            this.oldJobName = oldJobName;
            this.newJobName = newJobName;
        }

        public void transform(NodeType node) {
            ProcessType item = (ProcessType) node.eContainer();
            renameJobInRequiredProperty(item, newJobName);
        }

        private void renameJobInRequiredProperty(ProcessType item, String newJobName) {
            RequiredType required = item.getRequired();
            for (Object o : required.getJob()) {
                JobType job = (JobType) o;
                if (job.getName().equals(oldJobName)) {
                    job.setName(newJobName);
                }
            }
        }
    }

    @Override
    public boolean performCancel() {
        try {
            reloadProperty();
        } catch (PersistenceException e) {
            MessageBoxExceptionHandler.process(e);
        }
        return true;
    }

    private void reloadProperty() throws PersistenceException {
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
