/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.metadata.builder.connection.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.talend.core.model.metadata.builder.connection.ConnectionPackage;
import org.talend.core.model.metadata.builder.connection.FileExcelConnection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>File Excel Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.FileExcelConnectionImpl#getSheetName <em>Sheet Name</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.FileExcelConnectionImpl#getSheetColumns <em>Sheet Columns</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FileExcelConnectionImpl extends FileConnectionImpl implements FileExcelConnection {
    /**
     * The default value of the '{@link #getSheetName() <em>Sheet Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSheetName()
     * @generated
     * @ordered
     */
    protected static final String SHEET_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSheetName() <em>Sheet Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSheetName()
     * @generated
     * @ordered
     */
    protected String sheetName = SHEET_NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getSheetColumns() <em>Sheet Columns</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSheetColumns()
     * @generated
     * @ordered
     */
    protected EList sheetColumns;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FileExcelConnectionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return ConnectionPackage.Literals.FILE_EXCEL_CONNECTION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getSheetName() {
        return sheetName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSheetName(String newSheetName) {
        String oldSheetName = sheetName;
        sheetName = newSheetName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.FILE_EXCEL_CONNECTION__SHEET_NAME, oldSheetName, sheetName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getSheetColumns() {
        if (sheetColumns == null) {
            sheetColumns = new EDataTypeUniqueEList(String.class, this, ConnectionPackage.FILE_EXCEL_CONNECTION__SHEET_COLUMNS);
        }
        return sheetColumns;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ConnectionPackage.FILE_EXCEL_CONNECTION__SHEET_NAME:
                return getSheetName();
            case ConnectionPackage.FILE_EXCEL_CONNECTION__SHEET_COLUMNS:
                return getSheetColumns();
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
            case ConnectionPackage.FILE_EXCEL_CONNECTION__SHEET_NAME:
                setSheetName((String)newValue);
                return;
            case ConnectionPackage.FILE_EXCEL_CONNECTION__SHEET_COLUMNS:
                getSheetColumns().clear();
                getSheetColumns().addAll((Collection)newValue);
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
            case ConnectionPackage.FILE_EXCEL_CONNECTION__SHEET_NAME:
                setSheetName(SHEET_NAME_EDEFAULT);
                return;
            case ConnectionPackage.FILE_EXCEL_CONNECTION__SHEET_COLUMNS:
                getSheetColumns().clear();
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
            case ConnectionPackage.FILE_EXCEL_CONNECTION__SHEET_NAME:
                return SHEET_NAME_EDEFAULT == null ? sheetName != null : !SHEET_NAME_EDEFAULT.equals(sheetName);
            case ConnectionPackage.FILE_EXCEL_CONNECTION__SHEET_COLUMNS:
                return sheetColumns != null && !sheetColumns.isEmpty();
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
        result.append(" (SheetName: ");
        result.append(sheetName);
        result.append(", sheetColumns: ");
        result.append(sheetColumns);
        result.append(')');
        return result.toString();
    }

} //FileExcelConnectionImpl
