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

import java.sql.Blob;
import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;

import sun.security.util.Cache.EqualByteArray;


/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 */
public class ExecutionTask implements EObject {

    private int id;
    private String label;
    private String description;
    private ExecutionServer executionServer;
    private Project project;
    private ProcessItem processItem;
    private String context;
    private String jobVersion;
    private boolean active;
    private int idQuartzJob;
    private Date lastScriptGenerationDate;
    private String idRemoteJob;
    private String idRemoteJobExecution;
    private String checksumArchive;
    private byte[] jobScriptArchive;
    private String status;
    private String errorStatus;

    /**
     * DOC amaumont ExecutionTask constructor comment.
     */
    public ExecutionTask() {
        super();
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
    public ExecutionServer getExecutionServer() {
        return this.executionServer;
    }

    
    /**
     * Sets the executionServer.
     * @param executionServer the executionServer to set
     */
    public void setExecutionServer(ExecutionServer executionServer) {
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
     * Getter for idQuartzJob.
     * @return the idQuartzJob
     */
    public int getIdQuartzJob() {
        return this.idQuartzJob;
    }

    
    /**
     * Sets the idQuartzJob.
     * @param idQuartzJob the idQuartzJob to set
     */
    public void setIdQuartzJob(int idQuartzJob) {
        this.idQuartzJob = idQuartzJob;
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
     * Getter for processItem.
     * @return the processItem
     */
    public ProcessItem getProcessItem() {
        return this.processItem;
    }

    
    /**
     * Sets the processItem.
     * @param processItem the processItem to set
     */
    public void setProcessItem(ProcessItem processItem) {
        this.processItem = processItem;
    }

    
    /**
     * Getter for project.
     * @return the project
     */
    public Project getProject() {
        return this.project;
    }

    
    /**
     * Sets the project.
     * @param project the project to set
     */
    public void setProject(Project project) {
        this.project = project;
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
     * Getter for lastScriptGenerationDate.
     * @return the lastScriptGenerationDate
     */
    public Date getLastScriptGenerationDate() {
        return this.lastScriptGenerationDate;
    }

    
    /**
     * Sets the lastScriptGenerationDate.
     * @param lastScriptGenerationDate the lastScriptGenerationDate to set
     */
    public void setLastScriptGenerationDate(Date lastScriptGenerationDate) {
        this.lastScriptGenerationDate = lastScriptGenerationDate;
    }

    
    /**
     * Getter for jobScriptArchive.
     * @return the jobScriptArchive
     */
    public byte[] getJobScriptArchive() {
        return this.jobScriptArchive;
    }

    
    /**
     * Sets the jobScriptArchive.
     * @param jobScriptArchive the jobScriptArchive to set
     */
    public void setJobScriptArchive(byte[] jobScriptArchive) {
        this.jobScriptArchive = jobScriptArchive;
    }

    
    /**
     * Getter for state.
     * @return the state
     */
    public String getStatus() {
        return this.status;
    }

    
    /**
     * Sets the state.
     * @param state the state to set
     */
    public void setStatus(String state) {
        this.status = state;
    }

    
    /**
     * Getter for idJobScriptArchive.
     * @return the idJobScriptArchive
     */
    public String getChecksumArchive() {
        return this.checksumArchive;
    }

    
    /**
     * Sets the idJobScriptArchive.
     * @param idJobScriptArchive the idJobScriptArchive to set
     */
    public void setChecksumArchive(String idJobScriptArchive) {
        this.checksumArchive = idJobScriptArchive;
    }

    
    /**
     * Getter for idRemoteJob.
     * @return the idRemoteJob
     */
    public String getIdRemoteJob() {
        return this.idRemoteJob;
    }

    
    /**
     * Sets the idRemoteJob.
     * @param idRemoteJob the idRemoteJob to set
     */
    public void setIdRemoteJob(String idRemoteJob) {
        this.idRemoteJob = idRemoteJob;
    }

    
    /**
     * Getter for idRemoteJobExecution.
     * @return the idRemoteJobExecution
     */
    public String getIdRemoteJobExecution() {
        return this.idRemoteJobExecution;
    }

    
    /**
     * Sets the idRemoteJobExecution.
     * @param idRemoteJobExecution the idRemoteJobExecution to set
     */
    public void setIdRemoteJobExecution(String idRemoteJobExecution) {
        this.idRemoteJobExecution = idRemoteJobExecution;
    }


    
    /**
     * Getter for errorStatus.
     * @return the errorStatus
     */
    public String getErrorStatus() {
        return this.errorStatus;
    }


    
    /**
     * Sets the errorStatus.
     * @param errorStatus the errorStatus to set
     */
    public void setErrorStatus(String errorStatus) {
        this.errorStatus = errorStatus;
    }

    
    
    

}