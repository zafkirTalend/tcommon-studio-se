/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.designer.core.model.utils.emf.component.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.talend.designer.core.model.utils.emf.component.ComponentPackage;
import org.talend.designer.core.model.utils.emf.component.SQLPATTERNType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQLPATTERN Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.SQLPATTERNTypeImpl#getCONTENT <em>CONTENT</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.SQLPATTERNTypeImpl#getNAME <em>NAME</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SQLPATTERNTypeImpl extends EObjectImpl implements SQLPATTERNType {
    /**
	 * The default value of the '{@link #getCONTENT() <em>CONTENT</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCONTENT()
	 * @generated
	 * @ordered
	 */
    protected static final String CONTENT_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getCONTENT() <em>CONTENT</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCONTENT()
	 * @generated
	 * @ordered
	 */
    protected String cONTENT = CONTENT_EDEFAULT;

    /**
	 * The default value of the '{@link #getNAME() <em>NAME</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNAME()
	 * @generated
	 * @ordered
	 */
    protected static final String NAME_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getNAME() <em>NAME</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNAME()
	 * @generated
	 * @ordered
	 */
    protected String nAME = NAME_EDEFAULT;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected SQLPATTERNTypeImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return ComponentPackage.Literals.SQLPATTERN_TYPE;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getCONTENT() {
		return cONTENT;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setCONTENT(String newCONTENT) {
		String oldCONTENT = cONTENT;
		cONTENT = newCONTENT;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.SQLPATTERN_TYPE__CONTENT, oldCONTENT, cONTENT));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getNAME() {
		return nAME;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setNAME(String newNAME) {
		String oldNAME = nAME;
		nAME = newNAME;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.SQLPATTERN_TYPE__NAME, oldNAME, nAME));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ComponentPackage.SQLPATTERN_TYPE__CONTENT:
				return getCONTENT();
			case ComponentPackage.SQLPATTERN_TYPE__NAME:
				return getNAME();
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
			case ComponentPackage.SQLPATTERN_TYPE__CONTENT:
				setCONTENT((String)newValue);
				return;
			case ComponentPackage.SQLPATTERN_TYPE__NAME:
				setNAME((String)newValue);
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
			case ComponentPackage.SQLPATTERN_TYPE__CONTENT:
				setCONTENT(CONTENT_EDEFAULT);
				return;
			case ComponentPackage.SQLPATTERN_TYPE__NAME:
				setNAME(NAME_EDEFAULT);
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
			case ComponentPackage.SQLPATTERN_TYPE__CONTENT:
				return CONTENT_EDEFAULT == null ? cONTENT != null : !CONTENT_EDEFAULT.equals(cONTENT);
			case ComponentPackage.SQLPATTERN_TYPE__NAME:
				return NAME_EDEFAULT == null ? nAME != null : !NAME_EDEFAULT.equals(nAME);
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
		result.append(" (cONTENT: ");
		result.append(cONTENT);
		result.append(", nAME: ");
		result.append(nAME);
		result.append(')');
		return result.toString();
	}

} //SQLPATTERNTypeImpl
