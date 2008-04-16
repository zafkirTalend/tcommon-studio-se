/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.indicators.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.talend.dataquality.domain.Domain;

import org.talend.dataquality.indicators.DateGrain;
import org.talend.dataquality.indicators.IndicatorParameters;
import org.talend.dataquality.indicators.IndicatorsPackage;
import org.talend.dataquality.indicators.TextParameters;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Indicator Parameters</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.dataquality.indicators.impl.IndicatorParametersImpl#getIndicatorValidDomain <em>Indicator Valid Domain</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.IndicatorParametersImpl#getDataValidDomain <em>Data Valid Domain</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.IndicatorParametersImpl#getBins <em>Bins</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.IndicatorParametersImpl#getDateAggregationType <em>Date Aggregation Type</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.IndicatorParametersImpl#getTextParameter <em>Text Parameter</em>}</li>
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
     * The cached value of the '{@link #getBins() <em>Bins</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBins()
     * @generated
     * @ordered
     */
    protected Domain bins;

    /**
     * The default value of the '{@link #getDateAggregationType() <em>Date Aggregation Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDateAggregationType()
     * @generated
     * @ordered
     */
    protected static final DateGrain DATE_AGGREGATION_TYPE_EDEFAULT = DateGrain.DAY;

    /**
     * The cached value of the '{@link #getDateAggregationType() <em>Date Aggregation Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDateAggregationType()
     * @generated
     * @ordered
     */
    protected DateGrain dateAggregationType = DATE_AGGREGATION_TYPE_EDEFAULT;

    /**
     * The cached value of the '{@link #getTextParameter() <em>Text Parameter</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTextParameter()
     * @generated
     * @ordered
     */
    protected TextParameters textParameter;

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
    public Domain getBins() {
        if (bins != null && bins.eIsProxy()) {
            InternalEObject oldBins = (InternalEObject)bins;
            bins = (Domain)eResolveProxy(oldBins);
            if (bins != oldBins) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IndicatorsPackage.INDICATOR_PARAMETERS__BINS, oldBins, bins));
            }
        }
        return bins;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Domain basicGetBins() {
        return bins;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBins(Domain newBins) {
        Domain oldBins = bins;
        bins = newBins;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.INDICATOR_PARAMETERS__BINS, oldBins, bins));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DateGrain getDateAggregationType() {
        return dateAggregationType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDateAggregationType(DateGrain newDateAggregationType) {
        DateGrain oldDateAggregationType = dateAggregationType;
        dateAggregationType = newDateAggregationType == null ? DATE_AGGREGATION_TYPE_EDEFAULT : newDateAggregationType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.INDICATOR_PARAMETERS__DATE_AGGREGATION_TYPE, oldDateAggregationType, dateAggregationType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TextParameters getTextParameter() {
        return textParameter;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTextParameter(TextParameters newTextParameter, NotificationChain msgs) {
        TextParameters oldTextParameter = textParameter;
        textParameter = newTextParameter;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IndicatorsPackage.INDICATOR_PARAMETERS__TEXT_PARAMETER, oldTextParameter, newTextParameter);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTextParameter(TextParameters newTextParameter) {
        if (newTextParameter != textParameter) {
            NotificationChain msgs = null;
            if (textParameter != null)
                msgs = ((InternalEObject)textParameter).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IndicatorsPackage.INDICATOR_PARAMETERS__TEXT_PARAMETER, null, msgs);
            if (newTextParameter != null)
                msgs = ((InternalEObject)newTextParameter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IndicatorsPackage.INDICATOR_PARAMETERS__TEXT_PARAMETER, null, msgs);
            msgs = basicSetTextParameter(newTextParameter, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.INDICATOR_PARAMETERS__TEXT_PARAMETER, newTextParameter, newTextParameter));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case IndicatorsPackage.INDICATOR_PARAMETERS__TEXT_PARAMETER:
                return basicSetTextParameter(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
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
            case IndicatorsPackage.INDICATOR_PARAMETERS__BINS:
                if (resolve) return getBins();
                return basicGetBins();
            case IndicatorsPackage.INDICATOR_PARAMETERS__DATE_AGGREGATION_TYPE:
                return getDateAggregationType();
            case IndicatorsPackage.INDICATOR_PARAMETERS__TEXT_PARAMETER:
                return getTextParameter();
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
            case IndicatorsPackage.INDICATOR_PARAMETERS__BINS:
                setBins((Domain)newValue);
                return;
            case IndicatorsPackage.INDICATOR_PARAMETERS__DATE_AGGREGATION_TYPE:
                setDateAggregationType((DateGrain)newValue);
                return;
            case IndicatorsPackage.INDICATOR_PARAMETERS__TEXT_PARAMETER:
                setTextParameter((TextParameters)newValue);
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
            case IndicatorsPackage.INDICATOR_PARAMETERS__BINS:
                setBins((Domain)null);
                return;
            case IndicatorsPackage.INDICATOR_PARAMETERS__DATE_AGGREGATION_TYPE:
                setDateAggregationType(DATE_AGGREGATION_TYPE_EDEFAULT);
                return;
            case IndicatorsPackage.INDICATOR_PARAMETERS__TEXT_PARAMETER:
                setTextParameter((TextParameters)null);
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
            case IndicatorsPackage.INDICATOR_PARAMETERS__BINS:
                return bins != null;
            case IndicatorsPackage.INDICATOR_PARAMETERS__DATE_AGGREGATION_TYPE:
                return dateAggregationType != DATE_AGGREGATION_TYPE_EDEFAULT;
            case IndicatorsPackage.INDICATOR_PARAMETERS__TEXT_PARAMETER:
                return textParameter != null;
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
        result.append(" (dateAggregationType: ");
        result.append(dateAggregationType);
        result.append(')');
        return result.toString();
    }

} //IndicatorParametersImpl
