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
package org.talend.core.model.properties;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;


/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 */
public class ExecutionTask implements EObject {

    
    private int id;
    private String label;
    private String description;
    private String idProject;
    private String idJob;
    private String jobVersion;
    private String context;
    private String executionServer;
    private String quartzJob;
    private boolean active;
    
    

    
    
    
    /**
     * DOC amaumont ExecutionTask constructor comment.
     */
    public ExecutionTask() {
        super();
    }


    /**
     * Getter for active.
     * @return the active
     */
    public boolean isActive() {
        return this.active;
    }

    
    /**
     * Sets the active.
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    
    /**
     * Getter for context.
     * @return the context
     */
    public String getContext() {
        return this.context;
    }

    
    /**
     * Sets the context.
     * @param context the context to set
     */
    public void setContext(String context) {
        this.context = context;
    }

    
    /**
     * Getter for description.
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    
    /**
     * Sets the description.
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    
    /**
     * Getter for executionServer.
     * @return the executionServer
     */
    public String getExecutionServer() {
        return this.executionServer;
    }

    
    /**
     * Sets the executionServer.
     * @param executionServer the executionServer to set
     */
    public void setExecutionServer(String executionServer) {
        this.executionServer = executionServer;
    }

    
    /**
     * Getter for id.
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    
    /**
     * Sets the id.
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    
    /**
     * Getter for idJob.
     * @return the idJob
     */
    public String getIdJob() {
        return this.idJob;
    }

    
    /**
     * Sets the idJob.
     * @param idJob the idJob to set
     */
    public void setIdJob(String idJob) {
        this.idJob = idJob;
    }

    
    /**
     * Getter for idProject.
     * @return the idProject
     */
    public String getIdProject() {
        return this.idProject;
    }

    
    /**
     * Sets the idProject.
     * @param idProject the idProject to set
     */
    public void setIdProject(String idProject) {
        this.idProject = idProject;
    }

    
    /**
     * Getter for jobVersion.
     * @return the jobVersion
     */
    public String getJobVersion() {
        return this.jobVersion;
    }

    
    /**
     * Sets the jobVersion.
     * @param jobVersion the jobVersion to set
     */
    public void setJobVersion(String jobVersion) {
        this.jobVersion = jobVersion;
    }

    
    /**
     * Getter for label.
     * @return the label
     */
    public String getLabel() {
        return this.label;
    }

    
    /**
     * Sets the label.
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    
    /**
     * Getter for quartzJob.
     * @return the quartzJob
     */
    public String getQuartzJob() {
        return this.quartzJob;
    }

    
    /**
     * Sets the quartzJob.
     * @param quartzJob the quartzJob to set
     */
    public void setQuartzJob(String quartzJob) {
        this.quartzJob = quartzJob;
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eAllContents()
     */
    public TreeIterator eAllContents() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eClass()
     */
    public EClass eClass() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eContainer()
     */
    public EObject eContainer() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eContainingFeature()
     */
    public EStructuralFeature eContainingFeature() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eContainmentFeature()
     */
    public EReference eContainmentFeature() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eContents()
     */
    public EList eContents() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eCrossReferences()
     */
    public EList eCrossReferences() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eGet(org.eclipse.emf.ecore.EStructuralFeature)
     */
    public Object eGet(EStructuralFeature feature) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eGet(org.eclipse.emf.ecore.EStructuralFeature, boolean)
     */
    public Object eGet(EStructuralFeature feature, boolean resolve) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eIsProxy()
     */
    public boolean eIsProxy() {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eIsSet(org.eclipse.emf.ecore.EStructuralFeature)
     */
    public boolean eIsSet(EStructuralFeature feature) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eResource()
     */
    public Resource eResource() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eSet(org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object)
     */
    public void eSet(EStructuralFeature feature, Object newValue) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eUnset(org.eclipse.emf.ecore.EStructuralFeature)
     */
    public void eUnset(EStructuralFeature feature) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.common.notify.Notifier#eAdapters()
     */
    public EList eAdapters() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.common.notify.Notifier#eDeliver()
     */
    public boolean eDeliver() {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.common.notify.Notifier#eNotify(org.eclipse.emf.common.notify.Notification)
     */
    public void eNotify(Notification notification) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.common.notify.Notifier#eSetDeliver(boolean)
     */
    public void eSetDeliver(boolean deliver) {
        // TODO Auto-generated method stub
        
    }
    
    
    
}
