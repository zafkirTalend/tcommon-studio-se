/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.designer.core.model.utils.emf.component.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.talend.designer.core.model.utils.emf.component.ComponentPackage;
import org.talend.designer.core.model.utils.emf.component.TEMPLATESType;
import org.talend.designer.core.model.utils.emf.component.TEMPLATEType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TEMPLATES Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.TEMPLATESTypeImpl#getTEMPLATE <em>TEMPLATE</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TEMPLATESTypeImpl extends EObjectImpl implements TEMPLATESType {
    /**
     * The cached value of the '{@link #getTEMPLATE() <em>TEMPLATE</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTEMPLATE()
     * @generated
     * @ordered
     */
    protected EList tEMPLATE = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TEMPLATESTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return ComponentPackage.Literals.TEMPLATES_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getTEMPLATE() {
        if (tEMPLATE == null) {
            tEMPLATE = new EObjectContainmentEList(TEMPLATEType.class, this, ComponentPackage.TEMPLATES_TYPE__TEMPLATE);
        }
        return tEMPLATE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ComponentPackage.TEMPLATES_TYPE__TEMPLATE:
                return ((InternalEList)getTEMPLATE()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ComponentPackage.TEMPLATES_TYPE__TEMPLATE:
                return getTEMPLATE();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ComponentPackage.TEMPLATES_TYPE__TEMPLATE:
                getTEMPLATE().clear();
                getTEMPLATE().addAll((Collection)newValue);
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
            case ComponentPackage.TEMPLATES_TYPE__TEMPLATE:
                getTEMPLATE().clear();
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
            case ComponentPackage.TEMPLATES_TYPE__TEMPLATE:
                return tEMPLATE != null && !tEMPLATE.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //TEMPLATESTypeImpl