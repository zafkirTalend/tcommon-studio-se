/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.indicators.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.talend.dataquality.domain.Domain;

import org.talend.dataquality.indicators.IndicatorParameters;
import org.talend.dataquality.indicators.IndicatorsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Indicator Parameters</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.dataquality.indicators.impl.IndicatorParametersImpl#getIndicatorValidDomain <em>Indicator Valid Domain</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.IndicatorParametersImpl#getDataValidDomain <em>Data Valid Domain</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IndicatorParametersImpl extends EObjectImpl implements IndicatorParameters {
    /**
     * The cached value of the '{@link #getIndicatorValidDomain() <em>Indicator Valid Domain</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIndicatorValidDomain()
     * @generated
     * @ordered
     */
    protected Domain indicatorValidDomain;

    /**
     * The cached value of the '{@link #getDataValidDomain() <em>Data Valid Domain</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDataValidDomain()
     * @generated
     * @ordered
     */
    protected Domain dataValidDomain;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected IndicatorParametersImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return IndicatorsPackage.Literals.INDICATOR_PARAMETERS;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Domain getIndicatorValidDomain() {
        if (indicatorValidDomain != null && indicatorValidDomain.eIsProxy()) {
            InternalEObject oldIndicatorValidDomain = (InternalEObject)indicatorValidDomain;
            indicatorValidDomain = (Domain)eResolveProxy(oldIndicatorValidDomain);
            if (indicatorValidDomain != oldIndicatorValidDomain) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IndicatorsPackage.INDICATOR_PARAMETERS__INDICATOR_VALID_DOMAIN, oldIndicatorValidDomain, indicatorValidDomain));
            }
        }
        return indicatorValidDomain;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Domain basicGetIndicatorValidDomain() {
        return indicatorValidDomain;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIndicatorValidDomain(Domain newIndicatorValidDomain) {
        Domain oldIndicatorValidDomain = indicatorValidDomain;
        indicatorValidDomain = newIndicatorValidDomain;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.INDICATOR_PARAMETERS__INDICATOR_VALID_DOMAIN, oldIndicatorValidDomain, indicatorValidDomain));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Domain getDataValidDomain() {
        if (dataValidDomain != null && dataValidDomain.eIsProxy()) {
            InternalEObject oldDataValidDomain = (InternalEObject)dataValidDomain;
            dataValidDomain = (Domain)eResolveProxy(oldDataValidDomain);
            if (dataValidDomain != oldDataValidDomain) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IndicatorsPackage.INDICATOR_PARAMETERS__DATA_VALID_DOMAIN, oldDataValidDomain, dataValidDomain));
            }
        }
        return dataValidDomain;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Domain basicGetDataValidDomain() {
        return dataValidDomain;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDataValidDomain(Domain newDataValidDomain) {
        Domain oldDataValidDomain = dataValidDomain;
        dataValidDomain = newDataValidDomain;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.INDICATOR_PARAMETERS__DATA_VALID_DOMAIN, oldDataValidDomain, dataValidDomain));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case IndicatorsPackage.INDICATOR_PARAMETERS__INDICATOR_VALID_DOMAIN:
                if (resolve) return getIndicatorValidDomain();
                return basicGetIndicatorValidDomain();
            case IndicatorsPackage.INDICATOR_PARAMETERS__DATA_VALID_DOMAIN:
                if (resolve) return getDataValidDomain();
                return basicGetDataValidDomain();
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
            case IndicatorsPackage.INDICATOR_PARAMETERS__INDICATOR_VALID_DOMAIN:
                setIndicatorValidDomain((Domain)newValue);
                return;
            case IndicatorsPackage.INDICATOR_PARAMETERS__DATA_VALID_DOMAIN:
                setDataValidDomain((Domain)newValue);
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
            case IndicatorsPackage.INDICATOR_PARAMETERS__INDICATOR_VALID_DOMAIN:
                setIndicatorValidDomain((Domain)null);
                return;
            case IndicatorsPackage.INDICATOR_PARAMETERS__DATA_VALID_DOMAIN:
                setDataValidDomain((Domain)null);
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
            case IndicatorsPackage.INDICATOR_PARAMETERS__INDICATOR_VALID_DOMAIN:
                return indicatorValidDomain != null;
            case IndicatorsPackage.INDICATOR_PARAMETERS__DATA_VALID_DOMAIN:
                return dataValidDomain != null;
        }
        return super.eIsSet(featureID);
    }

} //IndicatorParametersImpl
