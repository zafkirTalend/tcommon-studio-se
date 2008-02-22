/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.analysis.impl;

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.talend.dataquality.analysis.AnalysisPackage;
import org.talend.dataquality.analysis.ExecutionInformations;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Execution Informations</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.dataquality.analysis.impl.ExecutionInformationsImpl#getExecutionDate <em>Execution Date</em>}</li>
 *   <li>{@link org.talend.dataquality.analysis.impl.ExecutionInformationsImpl#getExecutionDuration <em>Execution Duration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExecutionInformationsImpl extends EObjectImpl implements ExecutionInformations {
    /**
     * The default value of the '{@link #getExecutionDate() <em>Execution Date</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExecutionDate()
     * @generated
     * @ordered
     */
    protected static final Date EXECUTION_DATE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getExecutionDate() <em>Execution Date</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExecutionDate()
     * @generated
     * @ordered
     */
    protected Date executionDate = EXECUTION_DATE_EDEFAULT;

    /**
     * The default value of the '{@link #getExecutionDuration() <em>Execution Duration</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExecutionDuration()
     * @generated
     * @ordered
     */
    protected static final long EXECUTION_DURATION_EDEFAULT = 0L;

    /**
     * The cached value of the '{@link #getExecutionDuration() <em>Execution Duration</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExecutionDuration()
     * @generated
     * @ordered
     */
    protected long executionDuration = EXECUTION_DURATION_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ExecutionInformationsImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return AnalysisPackage.Literals.EXECUTION_INFORMATIONS;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Date getExecutionDate() {
        return executionDate;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setExecutionDate(Date newExecutionDate) {
        Date oldExecutionDate = executionDate;
        executionDate = newExecutionDate;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, AnalysisPackage.EXECUTION_INFORMATIONS__EXECUTION_DATE, oldExecutionDate, executionDate));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public long getExecutionDuration() {
        return executionDuration;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setExecutionDuration(long newExecutionDuration) {
        long oldExecutionDuration = executionDuration;
        executionDuration = newExecutionDuration;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, AnalysisPackage.EXECUTION_INFORMATIONS__EXECUTION_DURATION, oldExecutionDuration, executionDuration));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case AnalysisPackage.EXECUTION_INFORMATIONS__EXECUTION_DATE:
                return getExecutionDate();
            case AnalysisPackage.EXECUTION_INFORMATIONS__EXECUTION_DURATION:
                return new Long(getExecutionDuration());
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case AnalysisPackage.EXECUTION_INFORMATIONS__EXECUTION_DATE:
                setExecutionDate((Date)newValue);
                return;
            case AnalysisPackage.EXECUTION_INFORMATIONS__EXECUTION_DURATION:
                setExecutionDuration(((Long)newValue).longValue());
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case AnalysisPackage.EXECUTION_INFORMATIONS__EXECUTION_DATE:
                setExecutionDate(EXECUTION_DATE_EDEFAULT);
                return;
            case AnalysisPackage.EXECUTION_INFORMATIONS__EXECUTION_DURATION:
                setExecutionDuration(EXECUTION_DURATION_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case AnalysisPackage.EXECUTION_INFORMATIONS__EXECUTION_DATE:
                return EXECUTION_DATE_EDEFAULT == null ? executionDate != null : !EXECUTION_DATE_EDEFAULT.equals(executionDate);
            case AnalysisPackage.EXECUTION_INFORMATIONS__EXECUTION_DURATION:
                return executionDuration != EXECUTION_DURATION_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (executionDate: ");
        result.append(executionDate);
        result.append(", executionDuration: ");
        result.append(executionDuration);
        result.append(')');
        return result.toString();
    }

} //ExecutionInformationsImpl
