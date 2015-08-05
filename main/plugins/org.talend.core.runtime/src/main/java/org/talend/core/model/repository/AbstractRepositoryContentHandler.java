// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.repository;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.ui.IWorkbench;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.commons.ui.runtime.image.IImage;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.Status;
import org.talend.core.runtime.CoreRuntimePlugin;
import org.talend.repository.model.IRepositoryNode;
import org.talend.repository.model.RepositoryNode;

/**
 * DOC ycbai class global comment. Detailled comment
 */
public abstract class AbstractRepositoryContentHandler implements IRepositoryContentHandler {

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.repository.IRepositoryContentHandler#getIcon(org.talend.core.model.repository.
     * ERepositoryObjectType)
     */
    @Override
    public IImage getIcon(ERepositoryObjectType type) {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.repository.IRepositoryContentHandler#isProcess(org.talend.core.model.properties.Item)
     */
    @Override
    public boolean isProcess(Item item) {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.repository.IRepositoryContentHandler#getProcessType()
     */
    @Override
    public ERepositoryObjectType getProcessType() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.repository.IRepositoryContentHandler#getCodeType()
     */
    @Override
    public ERepositoryObjectType getCodeType() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.repository.IRepositoryContentHandler#addNode(org.talend.core.model.repository.
     * ERepositoryObjectType, org.talend.repository.model.RepositoryNode,
     * org.talend.core.model.repository.IRepositoryViewObject, org.talend.repository.model.RepositoryNode)
     */
    @Override
    public void addNode(ERepositoryObjectType type, RepositoryNode recBinNode, IRepositoryViewObject repositoryObject,
            RepositoryNode node) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.repository.IRepositoryContentHandler#addContents(java.util.Collection,
     * org.eclipse.emf.ecore.resource.Resource)
     */
    @Override
    public void addContents(Collection<EObject> collection, Resource resource) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.repository.IRepositoryContentHandler#getIcon(org.talend.core.model.properties.Item)
     */
    @Override
    public IImage getIcon(Item item) {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.repository.IRepositoryContentHandler#getHandleType()
     */
    @Override
    public ERepositoryObjectType getHandleType() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.repository.IRepositoryContentHandler#hasSchemas()
     */
    @Override
    public boolean hasSchemas() {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.core.model.repository.IRepositoryContentHandler#getPropertyStatus(org.talend.core.model.properties
     * .Item)
     */
    @Override
    public List<Status> getPropertyStatus(Item item) {
        if (isProcess(item)) {
            try {
                return CoreRuntimePlugin.getInstance().getProxyRepositoryFactory().getTechnicalStatus();
            } catch (PersistenceException e) {
                ExceptionHandler.process(e);
            }
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.core.model.repository.IRepositoryContentHandler#hideAction(org.talend.repository.model.IRepositoryNode
     * , java.lang.Class)
     */
    @Override
    public boolean hideAction(IRepositoryNode node, Class actionType) {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.core.model.repository.IRepositoryContentHandler#isOwnTable(org.talend.repository.model.IRepositoryNode
     * , java.lang.Class)
     */
    @Override
    public boolean isOwnTable(IRepositoryNode node, Class type) {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.repository.IRepositoryContentHandler#newWizard(org.eclipse.ui.IWorkbench, boolean,
     * org.talend.repository.model.RepositoryNode, java.lang.String[])
     */
    @Override
    public IWizard newWizard(IWorkbench workbench, boolean creation, RepositoryNode node, String[] existingNames) {
        return null;
    }

}
