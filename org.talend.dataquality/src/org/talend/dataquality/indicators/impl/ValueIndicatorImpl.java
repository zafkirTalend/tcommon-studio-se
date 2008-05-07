/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.dataquality.indicators.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.talend.dataquality.indicators.Datatype;
import org.talend.dataquality.indicators.IndicatorsPackage;
import org.talend.dataquality.indicators.ValueIndicator;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Value Indicator</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.talend.dataquality.indicators.impl.ValueIndicatorImpl#getValue <em>Value</em>}</li>
 * <li>{@link org.talend.dataquality.indicators.impl.ValueIndicatorImpl#getDatatype <em>Datatype</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ValueIndicatorImpl extends IndicatorImpl implements ValueIndicator {

    private static Logger log = Logger.getLogger(ValueIndicatorImpl.class);

    /**
     * The default value of the '{@link #getValue() <em>Value</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected static final String VALUE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getValue() <em>Value</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected String value = VALUE_EDEFAULT;

    /**
     * The default value of the '{@link #getDatatype() <em>Datatype</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getDatatype()
     * @generated
     * @ordered
     */
    protected static final Datatype DATATYPE_EDEFAULT = Datatype.INTEGER;

    /**
     * The cached value of the '{@link #getDatatype() <em>Datatype</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getDatatype()
     * @generated
     * @ordered
     */
    protected Datatype datatype = DATATYPE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ValueIndicatorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return IndicatorsPackage.Literals.VALUE_INDICATOR;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getValue() {
        return value;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setValue(String newValue) {
        String oldValue = value;
        value = newValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.VALUE_INDICATOR__VALUE, oldValue, value));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Datatype getDatatype() {
        return datatype;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setDatatype(Datatype newDatatype) {
        Datatype oldDatatype = datatype;
        datatype = newDatatype == null ? DATATYPE_EDEFAULT : newDatatype;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.VALUE_INDICATOR__DATATYPE, oldDatatype,
                    datatype));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case IndicatorsPackage.VALUE_INDICATOR__VALUE:
            return getValue();
        case IndicatorsPackage.VALUE_INDICATOR__DATATYPE:
            return getDatatype();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case IndicatorsPackage.VALUE_INDICATOR__VALUE:
            setValue((String) newValue);
            return;
        case IndicatorsPackage.VALUE_INDICATOR__DATATYPE:
            setDatatype((Datatype) newValue);
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
        case IndicatorsPackage.VALUE_INDICATOR__VALUE:
            setValue(VALUE_EDEFAULT);
            return;
        case IndicatorsPackage.VALUE_INDICATOR__DATATYPE:
            setDatatype(DATATYPE_EDEFAULT);
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
        case IndicatorsPackage.VALUE_INDICATOR__VALUE:
            return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
        case IndicatorsPackage.VALUE_INDICATOR__DATATYPE:
            return datatype != DATATYPE_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (value: ");
        result.append(value);
        result.append(", datatype: ");
        result.append(datatype);
        result.append(')');
        return result.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.dataquality.indicators.impl.IndicatorImpl#storeSqlResults(java.util.List)
     * 
     * ADDED scorreia 2008-05-06 storeSqlResults(List<Object[]> objects)
     */
    @Override
    public boolean storeSqlResults(List<Object[]> objects) {
        if (!checkResults(objects, 1)) {
            return false;
        }

        // TODO scorreia should get the correct type of result from the analyzed element

        if (objects.size() == 1) {
            String med = String.valueOf(objects.get(0)[0]);
            if (med == null) {
                log.error("Value is null of " + this.getName() + " !!");
                return false;
            }
            this.setValue(med);
            // FIXME scorreia (set datatype here ?)
            return true;
        }
        return false;
    }

} // ValueIndicatorImpl
