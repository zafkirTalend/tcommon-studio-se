/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.core.model.properties.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.gmf.runtime.notation.Diagram;

import org.talend.core.model.properties.BusinessProcessItem;
import org.talend.core.model.properties.PropertiesPackage;

import org.talend.designer.business.model.business.BusinessProcess;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Business Process Item</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.properties.impl.BusinessProcessItemImpl#getNotation <em>Notation</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.BusinessProcessItemImpl#getSemantic <em>Semantic</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BusinessProcessItemImpl extends ItemImpl implements BusinessProcessItem {

    /**
     * The cached value of the '{@link #getNotation() <em>Notation</em>}' reference.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getNotation()
     * @generated
     * @ordered
     */
    protected Diagram notation = null;

    /**
     * The cached value of the '{@link #getSemantic() <em>Semantic</em>}' reference.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getSemantic()
     * @generated
     * @ordered
     */
    protected BusinessProcess semantic = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected BusinessProcessItemImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.BUSINESS_PROCESS_ITEM;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Diagram getNotation() {
        if (notation != null && notation.eIsProxy()) {
            InternalEObject oldNotation = (InternalEObject)notation;
            notation = (Diagram)eResolveProxy(oldNotation);
            if (notation != oldNotation) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.BUSINESS_PROCESS_ITEM__NOTATION, oldNotation, notation));
            }
        }
        return notation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Diagram basicGetNotation() {
        return notation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNotation(Diagram newNotation) {
        Diagram oldNotation = notation;
        notation = newNotation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.BUSINESS_PROCESS_ITEM__NOTATION, oldNotation, notation));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public BusinessProcess getSemantic() {
        if (semantic != null && semantic.eIsProxy()) {
            InternalEObject oldSemantic = (InternalEObject)semantic;
            semantic = (BusinessProcess)eResolveProxy(oldSemantic);
            if (semantic != oldSemantic) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.BUSINESS_PROCESS_ITEM__SEMANTIC, oldSemantic, semantic));
            }
        }
        return semantic;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public BusinessProcess basicGetSemantic() {
        return semantic;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSemantic(BusinessProcess newSemantic) {
        BusinessProcess oldSemantic = semantic;
        semantic = newSemantic;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.BUSINESS_PROCESS_ITEM__SEMANTIC, oldSemantic, semantic));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case PropertiesPackage.BUSINESS_PROCESS_ITEM__NOTATION:
                if (resolve) return getNotation();
                return basicGetNotation();
            case PropertiesPackage.BUSINESS_PROCESS_ITEM__SEMANTIC:
                if (resolve) return getSemantic();
                return basicGetSemantic();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case PropertiesPackage.BUSINESS_PROCESS_ITEM__NOTATION:
                setNotation((Diagram)newValue);
                return;
            case PropertiesPackage.BUSINESS_PROCESS_ITEM__SEMANTIC:
                setSemantic((BusinessProcess)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void eUnset(int featureID) {
        switch (featureID) {
            case PropertiesPackage.BUSINESS_PROCESS_ITEM__NOTATION:
                setNotation((Diagram)null);
                return;
            case PropertiesPackage.BUSINESS_PROCESS_ITEM__SEMANTIC:
                setSemantic((BusinessProcess)null);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case PropertiesPackage.BUSINESS_PROCESS_ITEM__NOTATION:
                return notation != null;
            case PropertiesPackage.BUSINESS_PROCESS_ITEM__SEMANTIC:
                return semantic != null;
        }
        return super.eIsSet(featureID);
    }

} // BusinessProcessItemImpl
