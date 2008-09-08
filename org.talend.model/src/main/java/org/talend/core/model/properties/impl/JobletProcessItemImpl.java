/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.properties.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.talend.core.model.properties.JobletProcessItem;
import org.talend.core.model.properties.PropertiesPackage;

import org.talend.designer.joblet.model.JobletProcess;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Joblet Process Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.properties.impl.JobletProcessItemImpl#getJobletProcess <em>Joblet Process</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JobletProcessItemImpl extends ItemImpl implements JobletProcessItem {
    /**
	 * The cached value of the '{@link #getJobletProcess() <em>Joblet Process</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getJobletProcess()
	 * @generated
	 * @ordered
	 */
    protected JobletProcess jobletProcess;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected JobletProcessItemImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return PropertiesPackage.Literals.JOBLET_PROCESS_ITEM;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public JobletProcess getJobletProcess() {
		if (jobletProcess != null && jobletProcess.eIsProxy()) {
			InternalEObject oldJobletProcess = (InternalEObject)jobletProcess;
			jobletProcess = (JobletProcess)eResolveProxy(oldJobletProcess);
			if (jobletProcess != oldJobletProcess) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.JOBLET_PROCESS_ITEM__JOBLET_PROCESS, oldJobletProcess, jobletProcess));
			}
		}
		return jobletProcess;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public JobletProcess basicGetJobletProcess() {
		return jobletProcess;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setJobletProcess(JobletProcess newJobletProcess) {
		JobletProcess oldJobletProcess = jobletProcess;
		jobletProcess = newJobletProcess;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.JOBLET_PROCESS_ITEM__JOBLET_PROCESS, oldJobletProcess, jobletProcess));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PropertiesPackage.JOBLET_PROCESS_ITEM__JOBLET_PROCESS:
				if (resolve) return getJobletProcess();
				return basicGetJobletProcess();
		}
		return super.eGet(featureID, resolve, coreType);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PropertiesPackage.JOBLET_PROCESS_ITEM__JOBLET_PROCESS:
				setJobletProcess((JobletProcess)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void eUnset(int featureID) {
		switch (featureID) {
			case PropertiesPackage.JOBLET_PROCESS_ITEM__JOBLET_PROCESS:
				setJobletProcess((JobletProcess)null);
				return;
		}
		super.eUnset(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean eIsSet(int featureID) {
		switch (featureID) {
			case PropertiesPackage.JOBLET_PROCESS_ITEM__JOBLET_PROCESS:
				return jobletProcess != null;
		}
		return super.eIsSet(featureID);
	}

} //JobletProcessItemImpl
