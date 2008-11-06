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
import org.talend.core.model.metadata.builder.connection.EbcdicConnection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ebcdic Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.EbcdicConnectionImpl#getMidFile <em>Mid File</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EbcdicConnectionImpl extends FileConnectionImpl implements EbcdicConnection {
    /**
     * The default value of the '{@link #getMidFile() <em>Mid File</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMidFile()
     * @generated
     * @ordered
     */
    protected static final String MID_FILE_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getMidFile() <em>Mid File</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMidFile()
     * @generated
     * @ordered
     */
    protected String midFile = MID_FILE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EbcdicConnectionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return ConnectionPackage.Literals.EBCDIC_CONNECTION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getMidFile() {
        return midFile;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMidFile(String newMidFile) {
        String oldMidFile = midFile;
        midFile = newMidFile;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.EBCDIC_CONNECTION__MID_FILE, oldMidFile, midFile));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ConnectionPackage.EBCDIC_CONNECTION__MID_FILE:
                return getMidFile();
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
            case ConnectionPackage.EBCDIC_CONNECTION__MID_FILE:
                setMidFile((String)newValue);
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
            case ConnectionPackage.EBCDIC_CONNECTION__MID_FILE:
                setMidFile(MID_FILE_EDEFAULT);
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
            case ConnectionPackage.EBCDIC_CONNECTION__MID_FILE:
                return MID_FILE_EDEFAULT == null ? midFile != null : !MID_FILE_EDEFAULT.equals(midFile);
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
        result.append(" (MidFile: ");
        result.append(midFile);
        result.append(')');
        return result.toString();
    }

} //EbcdicConnectionImpl
