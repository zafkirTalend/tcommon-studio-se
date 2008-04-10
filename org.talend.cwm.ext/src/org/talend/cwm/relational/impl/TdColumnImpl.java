/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.cwm.relational.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.talend.cwm.helper.TaggedValueHelper;
import org.talend.cwm.relational.RelationalPackage;
import org.talend.cwm.relational.TdColumn;
import orgomg.cwm.resource.relational.impl.ColumnImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Td Column</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.cwm.relational.impl.TdColumnImpl#getJavaType <em>Java Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TdColumnImpl extends ColumnImpl implements TdColumn {

    /**
     * The default value of the '{@link #getJavaType() <em>Java Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getJavaType()
     * @generated
     * @ordered
     */
    protected static final int JAVA_TYPE_EDEFAULT = 0;
    /**
     * The cached value of the '{@link #getJavaType() <em>Java Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getJavaType()
     * @generated
     * @ordered
     */
    protected int javaType = JAVA_TYPE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected TdColumnImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return RelationalPackage.Literals.TD_COLUMN;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getJavaType() {
        return javaType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setJavaType(int newJavaType) {
        int oldJavaType = javaType;
        javaType = newJavaType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.TD_COLUMN__JAVA_TYPE, oldJavaType, javaType));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public void setContentType(String contentType) {
        TaggedValueHelper.setTaggedValue(this, TaggedValueHelper.DATA_CONTENT_TYPE_TAGGED_VAL, contentType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public String getContentType() {
        return TaggedValueHelper.getValue(TaggedValueHelper.DATA_CONTENT_TYPE_TAGGED_VAL, this.getTaggedValue());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case RelationalPackage.TD_COLUMN__JAVA_TYPE:
                return new Integer(getJavaType());
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
            case RelationalPackage.TD_COLUMN__JAVA_TYPE:
                setJavaType(((Integer)newValue).intValue());
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
            case RelationalPackage.TD_COLUMN__JAVA_TYPE:
                setJavaType(JAVA_TYPE_EDEFAULT);
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
            case RelationalPackage.TD_COLUMN__JAVA_TYPE:
                return javaType != JAVA_TYPE_EDEFAULT;
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
        result.append(" (javaType: ");
        result.append(javaType);
        result.append(')');
        return result.toString();
    }

} // TdColumnImpl
