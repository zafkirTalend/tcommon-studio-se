/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.designer.joblet.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.talend.designer.core.model.utils.emf.talendfile.impl.ProcessTypeImpl;
import org.talend.designer.joblet.model.JobletConnection;
import org.talend.designer.joblet.model.JobletPackage;
import org.talend.designer.joblet.model.JobletProcess;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Joblet Process</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.talend.designer.joblet.model.impl.JobletProcessImpl#getJobletLinks <em>Joblet Links</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class JobletProcessImpl extends ProcessTypeImpl implements JobletProcess {

    /**
     * The cached value of the '{@link #getJobletLinks() <em>Joblet Links</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getJobletLinks()
     * @generated
     * @ordered
     */
    protected EList<JobletConnection> jobletLinks;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected JobletProcessImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return JobletPackage.Literals.JOBLET_PROCESS;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<JobletConnection> getJobletLinks() {
        if (jobletLinks == null) {
            jobletLinks = new EObjectContainmentEList<JobletConnection>(JobletConnection.class, this,
                    JobletPackage.JOBLET_PROCESS__JOBLET_LINKS);
        }
        return jobletLinks;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case JobletPackage.JOBLET_PROCESS__JOBLET_LINKS:
            return ((InternalEList<?>) getJobletLinks()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case JobletPackage.JOBLET_PROCESS__JOBLET_LINKS:
            return getJobletLinks();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case JobletPackage.JOBLET_PROCESS__JOBLET_LINKS:
            getJobletLinks().clear();
            getJobletLinks().addAll((Collection<? extends JobletConnection>) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case JobletPackage.JOBLET_PROCESS__JOBLET_LINKS:
            getJobletLinks().clear();
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case JobletPackage.JOBLET_PROCESS__JOBLET_LINKS:
            return jobletLinks != null && !jobletLinks.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // JobletProcessImpl
