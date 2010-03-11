/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.metadata.builder.connection.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.talend.core.model.metadata.builder.connection.ConnectionPackage;
import org.talend.core.model.metadata.builder.connection.HL7Connection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>HL7 Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.HL7ConnectionImpl#getStartChar <em>Start Char</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.HL7ConnectionImpl#getEndChar <em>End Char</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HL7ConnectionImpl extends FileConnectionImpl implements HL7Connection {
    /**
     * The default value of the '{@link #getStartChar() <em>Start Char</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStartChar()
     * @generated
     * @ordered
     */
    protected static final String START_CHAR_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getStartChar() <em>Start Char</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStartChar()
     * @generated
     * @ordered
     */
    protected String startChar = START_CHAR_EDEFAULT;

    /**
     * The default value of the '{@link #getEndChar() <em>End Char</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEndChar()
     * @generated
     * @ordered
     */
    protected static final String END_CHAR_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getEndChar() <em>End Char</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEndChar()
     * @generated
     * @ordered
     */
    protected String endChar = END_CHAR_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected HL7ConnectionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return ConnectionPackage.Literals.HL7_CONNECTION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getStartChar() {
        return startChar;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStartChar(String newStartChar) {
        String oldStartChar = startChar;
        startChar = newStartChar;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.HL7_CONNECTION__START_CHAR, oldStartChar, startChar));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getEndChar() {
        return endChar;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEndChar(String newEndChar) {
        String oldEndChar = endChar;
        endChar = newEndChar;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.HL7_CONNECTION__END_CHAR, oldEndChar, endChar));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ConnectionPackage.HL7_CONNECTION__START_CHAR:
                return getStartChar();
            case ConnectionPackage.HL7_CONNECTION__END_CHAR:
                return getEndChar();
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
            case ConnectionPackage.HL7_CONNECTION__START_CHAR:
                setStartChar((String)newValue);
                return;
            case ConnectionPackage.HL7_CONNECTION__END_CHAR:
                setEndChar((String)newValue);
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
            case ConnectionPackage.HL7_CONNECTION__START_CHAR:
                setStartChar(START_CHAR_EDEFAULT);
                return;
            case ConnectionPackage.HL7_CONNECTION__END_CHAR:
                setEndChar(END_CHAR_EDEFAULT);
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
            case ConnectionPackage.HL7_CONNECTION__START_CHAR:
                return START_CHAR_EDEFAULT == null ? startChar != null : !START_CHAR_EDEFAULT.equals(startChar);
            case ConnectionPackage.HL7_CONNECTION__END_CHAR:
                return END_CHAR_EDEFAULT == null ? endChar != null : !END_CHAR_EDEFAULT.equals(endChar);
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
        result.append(" (StartChar: ");
        result.append(startChar);
        result.append(", EndChar: ");
        result.append(endChar);
        result.append(')');
        return result.toString();
    }

} //HL7ConnectionImpl
