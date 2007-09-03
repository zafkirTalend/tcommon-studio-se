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

import org.eclipse.emf.common.notify.Adapter;
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
public class TalendTrigger implements EObject {

    private int id;
    private boolean active;
    private String label;
    private String description;
    private String triggerType;
    private ExecutionTask executionTask;
    
    
    
    
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
     * Getter for triggerType.
     * @return the triggerType
     */
    public String getTriggerType() {
        return this.triggerType;
    }

    

    
    /**
     * Sets the triggerType.
     * @param triggerType the triggerType to set
     */
    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType;
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
     * Getter for executionTask.
     * @return the executionTask
     */
    public ExecutionTask getExecutionTask() {
        return this.executionTask;
    }

    
    /**
     * Sets the executionTask.
     * @param executionTask the executionTask to set
     */
    public void setExecutionTask(ExecutionTask executionTask) {
        this.executionTask = executionTask;
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eAllContents()
     */
    public TreeIterator<EObject> eAllContents() {
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
    public EList<EObject> eContents() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eCrossReferences()
     */
    public EList<EObject> eCrossReferences() {
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
    public EList<Adapter> eAdapters() {
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
