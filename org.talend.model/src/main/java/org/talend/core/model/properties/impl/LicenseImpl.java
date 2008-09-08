/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.properties.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.talend.core.model.properties.License;
import org.talend.core.model.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>License</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.properties.impl.LicenseImpl#getLicense <em>License</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.LicenseImpl#getCustomerName <em>Customer Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LicenseImpl extends EObjectImpl implements License {
    /**
	 * The default value of the '{@link #getLicense() <em>License</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getLicense()
	 * @generated
	 * @ordered
	 */
    protected static final byte[] LICENSE_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getLicense() <em>License</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getLicense()
	 * @generated
	 * @ordered
	 */
    protected byte[] license = LICENSE_EDEFAULT;

    /**
	 * The default value of the '{@link #getCustomerName() <em>Customer Name</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCustomerName()
	 * @generated
	 * @ordered
	 */
    protected static final String CUSTOMER_NAME_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getCustomerName() <em>Customer Name</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCustomerName()
	 * @generated
	 * @ordered
	 */
    protected String customerName = CUSTOMER_NAME_EDEFAULT;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected LicenseImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return PropertiesPackage.Literals.LICENSE;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public byte[] getLicense() {
		return license;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setLicense(byte[] newLicense) {
		byte[] oldLicense = license;
		license = newLicense;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.LICENSE__LICENSE, oldLicense, license));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getCustomerName() {
		return customerName;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setCustomerName(String newCustomerName) {
		String oldCustomerName = customerName;
		customerName = newCustomerName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.LICENSE__CUSTOMER_NAME, oldCustomerName, customerName));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PropertiesPackage.LICENSE__LICENSE:
				return getLicense();
			case PropertiesPackage.LICENSE__CUSTOMER_NAME:
				return getCustomerName();
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
			case PropertiesPackage.LICENSE__LICENSE:
				setLicense((byte[])newValue);
				return;
			case PropertiesPackage.LICENSE__CUSTOMER_NAME:
				setCustomerName((String)newValue);
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
			case PropertiesPackage.LICENSE__LICENSE:
				setLicense(LICENSE_EDEFAULT);
				return;
			case PropertiesPackage.LICENSE__CUSTOMER_NAME:
				setCustomerName(CUSTOMER_NAME_EDEFAULT);
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
			case PropertiesPackage.LICENSE__LICENSE:
				return LICENSE_EDEFAULT == null ? license != null : !LICENSE_EDEFAULT.equals(license);
			case PropertiesPackage.LICENSE__CUSTOMER_NAME:
				return CUSTOMER_NAME_EDEFAULT == null ? customerName != null : !CUSTOMER_NAME_EDEFAULT.equals(customerName);
		}
		return super.eIsSet(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (license: ");
		result.append(license);
		result.append(", customerName: ");
		result.append(customerName);
		result.append(')');
		return result.toString();
	}

} //LicenseImpl