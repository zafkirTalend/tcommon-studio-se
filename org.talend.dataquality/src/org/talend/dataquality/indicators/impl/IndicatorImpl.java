/**
 * <copyright> </copyright>
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
import org.talend.dataquality.domain.DomainPackage;
import org.talend.dataquality.domain.LiteralValue;
import org.talend.dataquality.indicators.Indicator;
import org.talend.dataquality.indicators.IndicatorParameters;
import org.talend.dataquality.indicators.IndicatorType;
import org.talend.dataquality.indicators.IndicatorsPackage;
import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Indicator</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.dataquality.indicators.impl.IndicatorImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.IndicatorImpl#getIndicatorType <em>Indicator Type</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.IndicatorImpl#getCount <em>Count</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.IndicatorImpl#getNullCount <em>Null Count</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.IndicatorImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.IndicatorImpl#getAnalyzedElement <em>Analyzed Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IndicatorImpl extends EObjectImpl implements Indicator {

    /**
     * The cached value of the '{@link #getValue() <em>Value</em>}' reference.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected LiteralValue value;

    /**
     * The cached value of the '{@link #getIndicatorType() <em>Indicator Type</em>}' reference.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #getIndicatorType()
     * @generated
     * @ordered
     */
    protected IndicatorType indicatorType;

    /**
     * The default value of the '{@link #getCount() <em>Count</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getCount()
     * @generated
     * @ordered
     */
    protected static final long COUNT_EDEFAULT = 0L;

    /**
     * The cached value of the '{@link #getCount() <em>Count</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getCount()
     * @generated
     * @ordered
     */
    protected long count = COUNT_EDEFAULT;

    /**
     * The default value of the '{@link #getNullCount() <em>Null Count</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getNullCount()
     * @generated
     * @ordered
     */
    protected static final long NULL_COUNT_EDEFAULT = 0L;

    /**
     * The cached value of the '{@link #getNullCount() <em>Null Count</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getNullCount()
     * @generated
     * @ordered
     */
    protected long nullCount = NULL_COUNT_EDEFAULT;

    /**
     * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParameters()
     * @generated
     * @ordered
     */
    protected IndicatorParameters parameters;

    /**
     * The cached value of the '{@link #getAnalyzedElement() <em>Analyzed Element</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAnalyzedElement()
     * @generated
     * @ordered
     */
    protected ModelElement analyzedElement;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected IndicatorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return IndicatorsPackage.Literals.INDICATOR;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public IndicatorType getIndicatorType() {
        if (indicatorType != null && indicatorType.eIsProxy()) {
            InternalEObject oldIndicatorType = (InternalEObject)indicatorType;
            indicatorType = (IndicatorType)eResolveProxy(oldIndicatorType);
            if (indicatorType != oldIndicatorType) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IndicatorsPackage.INDICATOR__INDICATOR_TYPE, oldIndicatorType, indicatorType));
            }
        }
        return indicatorType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public IndicatorType basicGetIndicatorType() {
        return indicatorType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setIndicatorType(IndicatorType newIndicatorType) {
        IndicatorType oldIndicatorType = indicatorType;
        indicatorType = newIndicatorType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.INDICATOR__INDICATOR_TYPE, oldIndicatorType, indicatorType));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public long getCount() {
        return count;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setCount(long newCount) {
        long oldCount = count;
        count = newCount;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.INDICATOR__COUNT, oldCount, count));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public long getNullCount() {
        return nullCount;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setNullCount(long newNullCount) {
        long oldNullCount = nullCount;
        nullCount = newNullCount;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.INDICATOR__NULL_COUNT, oldNullCount, nullCount));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IndicatorParameters getParameters() {
        return parameters;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetParameters(IndicatorParameters newParameters, NotificationChain msgs) {
        IndicatorParameters oldParameters = parameters;
        parameters = newParameters;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IndicatorsPackage.INDICATOR__PARAMETERS, oldParameters, newParameters);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParameters(IndicatorParameters newParameters) {
        if (newParameters != parameters) {
            NotificationChain msgs = null;
            if (parameters != null)
                msgs = ((InternalEObject)parameters).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IndicatorsPackage.INDICATOR__PARAMETERS, null, msgs);
            if (newParameters != null)
                msgs = ((InternalEObject)newParameters).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IndicatorsPackage.INDICATOR__PARAMETERS, null, msgs);
            msgs = basicSetParameters(newParameters, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.INDICATOR__PARAMETERS, newParameters, newParameters));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ModelElement getAnalyzedElement() {
        if (analyzedElement != null && analyzedElement.eIsProxy()) {
            InternalEObject oldAnalyzedElement = (InternalEObject)analyzedElement;
            analyzedElement = (ModelElement)eResolveProxy(oldAnalyzedElement);
            if (analyzedElement != oldAnalyzedElement) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IndicatorsPackage.INDICATOR__ANALYZED_ELEMENT, oldAnalyzedElement, analyzedElement));
            }
        }
        return analyzedElement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ModelElement basicGetAnalyzedElement() {
        return analyzedElement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAnalyzedElement(ModelElement newAnalyzedElement) {
        ModelElement oldAnalyzedElement = analyzedElement;
        analyzedElement = newAnalyzedElement;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.INDICATOR__ANALYZED_ELEMENT, oldAnalyzedElement, analyzedElement));
    }

    /**
     * <!-- begin-user-doc --> Increments counts for each given data. <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public boolean handle(Object data) {
        if (data == null) {
            nullCount++;
        }
        count++;
        return true;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public boolean reset() {
        count = COUNT_EDEFAULT;
        nullCount = NULL_COUNT_EDEFAULT;
        return true;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public LiteralValue getValue() {
        if (value != null && value.eIsProxy()) {
            InternalEObject oldValue = (InternalEObject)value;
            value = (LiteralValue)eResolveProxy(oldValue);
            if (value != oldValue) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IndicatorsPackage.INDICATOR__VALUE, oldValue, value));
            }
        }
        return value;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public LiteralValue basicGetValue() {
        return value;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetValue(LiteralValue newValue, NotificationChain msgs) {
        LiteralValue oldValue = value;
        value = newValue;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IndicatorsPackage.INDICATOR__VALUE, oldValue, newValue);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setValue(LiteralValue newValue) {
        if (newValue != value) {
            NotificationChain msgs = null;
            if (value != null)
                msgs = ((InternalEObject)value).eInverseRemove(this, DomainPackage.LITERAL_VALUE__INDICATOR, LiteralValue.class, msgs);
            if (newValue != null)
                msgs = ((InternalEObject)newValue).eInverseAdd(this, DomainPackage.LITERAL_VALUE__INDICATOR, LiteralValue.class, msgs);
            msgs = basicSetValue(newValue, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.INDICATOR__VALUE, newValue, newValue));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case IndicatorsPackage.INDICATOR__VALUE:
                if (value != null)
                    msgs = ((InternalEObject)value).eInverseRemove(this, DomainPackage.LITERAL_VALUE__INDICATOR, LiteralValue.class, msgs);
                return basicSetValue((LiteralValue)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case IndicatorsPackage.INDICATOR__VALUE:
                return basicSetValue(null, msgs);
            case IndicatorsPackage.INDICATOR__PARAMETERS:
                return basicSetParameters(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case IndicatorsPackage.INDICATOR__VALUE:
                if (resolve) return getValue();
                return basicGetValue();
            case IndicatorsPackage.INDICATOR__INDICATOR_TYPE:
                if (resolve) return getIndicatorType();
                return basicGetIndicatorType();
            case IndicatorsPackage.INDICATOR__COUNT:
                return new Long(getCount());
            case IndicatorsPackage.INDICATOR__NULL_COUNT:
                return new Long(getNullCount());
            case IndicatorsPackage.INDICATOR__PARAMETERS:
                return getParameters();
            case IndicatorsPackage.INDICATOR__ANALYZED_ELEMENT:
                if (resolve) return getAnalyzedElement();
                return basicGetAnalyzedElement();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case IndicatorsPackage.INDICATOR__VALUE:
                setValue((LiteralValue)newValue);
                return;
            case IndicatorsPackage.INDICATOR__INDICATOR_TYPE:
                setIndicatorType((IndicatorType)newValue);
                return;
            case IndicatorsPackage.INDICATOR__COUNT:
                setCount(((Long)newValue).longValue());
                return;
            case IndicatorsPackage.INDICATOR__NULL_COUNT:
                setNullCount(((Long)newValue).longValue());
                return;
            case IndicatorsPackage.INDICATOR__PARAMETERS:
                setParameters((IndicatorParameters)newValue);
                return;
            case IndicatorsPackage.INDICATOR__ANALYZED_ELEMENT:
                setAnalyzedElement((ModelElement)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case IndicatorsPackage.INDICATOR__VALUE:
                setValue((LiteralValue)null);
                return;
            case IndicatorsPackage.INDICATOR__INDICATOR_TYPE:
                setIndicatorType((IndicatorType)null);
                return;
            case IndicatorsPackage.INDICATOR__COUNT:
                setCount(COUNT_EDEFAULT);
                return;
            case IndicatorsPackage.INDICATOR__NULL_COUNT:
                setNullCount(NULL_COUNT_EDEFAULT);
                return;
            case IndicatorsPackage.INDICATOR__PARAMETERS:
                setParameters((IndicatorParameters)null);
                return;
            case IndicatorsPackage.INDICATOR__ANALYZED_ELEMENT:
                setAnalyzedElement((ModelElement)null);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case IndicatorsPackage.INDICATOR__VALUE:
                return value != null;
            case IndicatorsPackage.INDICATOR__INDICATOR_TYPE:
                return indicatorType != null;
            case IndicatorsPackage.INDICATOR__COUNT:
                return count != COUNT_EDEFAULT;
            case IndicatorsPackage.INDICATOR__NULL_COUNT:
                return nullCount != NULL_COUNT_EDEFAULT;
            case IndicatorsPackage.INDICATOR__PARAMETERS:
                return parameters != null;
            case IndicatorsPackage.INDICATOR__ANALYZED_ELEMENT:
                return analyzedElement != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (count: ");
        result.append(count);
        result.append(", nullCount: ");
        result.append(nullCount);
        result.append(')');
        return result.toString();
    }

} // IndicatorImpl
