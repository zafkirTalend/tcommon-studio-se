/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.properties.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.talend.core.model.properties.ExecutionServer;
import org.talend.core.model.properties.ExecutionServerPhysicalVirtualRelation;
import org.talend.core.model.properties.ExecutionVirtualServer;
import org.talend.core.model.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Execution Server Physical Virtual Relation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.properties.impl.ExecutionServerPhysicalVirtualRelationImpl#getPhysical <em>Physical</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ExecutionServerPhysicalVirtualRelationImpl#getVirtual <em>Virtual</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExecutionServerPhysicalVirtualRelationImpl extends EObjectImpl implements ExecutionServerPhysicalVirtualRelation {
    /**
     * The cached value of the '{@link #getPhysical() <em>Physical</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPhysical()
     * @generated
     * @ordered
     */
    protected ExecutionServer physical;

    /**
     * The cached value of the '{@link #getVirtual() <em>Virtual</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVirtual()
     * @generated
     * @ordered
     */
    protected ExecutionVirtualServer virtual;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ExecutionServerPhysicalVirtualRelationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.EXECUTION_SERVER_PHYSICAL_VIRTUAL_RELATION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExecutionServer getPhysical() {
        if (physical != null && physical.eIsProxy()) {
            InternalEObject oldPhysical = (InternalEObject)physical;
            physical = (ExecutionServer)eResolveProxy(oldPhysical);
            if (physical != oldPhysical) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.EXECUTION_SERVER_PHYSICAL_VIRTUAL_RELATION__PHYSICAL, oldPhysical, physical));
            }
        }
        return physical;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExecutionServer basicGetPhysical() {
        return physical;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetPhysical(ExecutionServer newPhysical, NotificationChain msgs) {
        ExecutionServer oldPhysical = physical;
        physical = newPhysical;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.EXECUTION_SERVER_PHYSICAL_VIRTUAL_RELATION__PHYSICAL, oldPhysical, newPhysical);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPhysical(ExecutionServer newPhysical) {
        if (newPhysical != physical) {
            NotificationChain msgs = null;
            if (physical != null)
                msgs = ((InternalEObject)physical).eInverseRemove(this, PropertiesPackage.EXECUTION_SERVER__VIRTUAL_SERVERS_RELATIONS, ExecutionServer.class, msgs);
            if (newPhysical != null)
                msgs = ((InternalEObject)newPhysical).eInverseAdd(this, PropertiesPackage.EXECUTION_SERVER__VIRTUAL_SERVERS_RELATIONS, ExecutionServer.class, msgs);
            msgs = basicSetPhysical(newPhysical, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.EXECUTION_SERVER_PHYSICAL_VIRTUAL_RELATION__PHYSICAL, newPhysical, newPhysical));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExecutionVirtualServer getVirtual() {
        if (virtual != null && virtual.eIsProxy()) {
            InternalEObject oldVirtual = (InternalEObject)virtual;
            virtual = (ExecutionVirtualServer)eResolveProxy(oldVirtual);
            if (virtual != oldVirtual) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.EXECUTION_SERVER_PHYSICAL_VIRTUAL_RELATION__VIRTUAL, oldVirtual, virtual));
            }
        }
        return virtual;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExecutionVirtualServer basicGetVirtual() {
        return virtual;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetVirtual(ExecutionVirtualServer newVirtual, NotificationChain msgs) {
        ExecutionVirtualServer oldVirtual = virtual;
        virtual = newVirtual;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.EXECUTION_SERVER_PHYSICAL_VIRTUAL_RELATION__VIRTUAL, oldVirtual, newVirtual);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVirtual(ExecutionVirtualServer newVirtual) {
        if (newVirtual != virtual) {
            NotificationChain msgs = null;
            if (virtual != null)
                msgs = ((InternalEObject)virtual).eInverseRemove(this, PropertiesPackage.EXECUTION_VIRTUAL_SERVER__PHYSICAL_SERVERS_RELATIONS, ExecutionVirtualServer.class, msgs);
            if (newVirtual != null)
                msgs = ((InternalEObject)newVirtual).eInverseAdd(this, PropertiesPackage.EXECUTION_VIRTUAL_SERVER__PHYSICAL_SERVERS_RELATIONS, ExecutionVirtualServer.class, msgs);
            msgs = basicSetVirtual(newVirtual, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.EXECUTION_SERVER_PHYSICAL_VIRTUAL_RELATION__VIRTUAL, newVirtual, newVirtual));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case PropertiesPackage.EXECUTION_SERVER_PHYSICAL_VIRTUAL_RELATION__PHYSICAL:
                if (physical != null)
                    msgs = ((InternalEObject)physical).eInverseRemove(this, PropertiesPackage.EXECUTION_SERVER__VIRTUAL_SERVERS_RELATIONS, ExecutionServer.class, msgs);
                return basicSetPhysical((ExecutionServer)otherEnd, msgs);
            case PropertiesPackage.EXECUTION_SERVER_PHYSICAL_VIRTUAL_RELATION__VIRTUAL:
                if (virtual != null)
                    msgs = ((InternalEObject)virtual).eInverseRemove(this, PropertiesPackage.EXECUTION_VIRTUAL_SERVER__PHYSICAL_SERVERS_RELATIONS, ExecutionVirtualServer.class, msgs);
                return basicSetVirtual((ExecutionVirtualServer)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case PropertiesPackage.EXECUTION_SERVER_PHYSICAL_VIRTUAL_RELATION__PHYSICAL:
                return basicSetPhysical(null, msgs);
            case PropertiesPackage.EXECUTION_SERVER_PHYSICAL_VIRTUAL_RELATION__VIRTUAL:
                return basicSetVirtual(null, msgs);
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
            case PropertiesPackage.EXECUTION_SERVER_PHYSICAL_VIRTUAL_RELATION__PHYSICAL:
                if (resolve) return getPhysical();
                return basicGetPhysical();
            case PropertiesPackage.EXECUTION_SERVER_PHYSICAL_VIRTUAL_RELATION__VIRTUAL:
                if (resolve) return getVirtual();
                return basicGetVirtual();
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
            case PropertiesPackage.EXECUTION_SERVER_PHYSICAL_VIRTUAL_RELATION__PHYSICAL:
                setPhysical((ExecutionServer)newValue);
                return;
            case PropertiesPackage.EXECUTION_SERVER_PHYSICAL_VIRTUAL_RELATION__VIRTUAL:
                setVirtual((ExecutionVirtualServer)newValue);
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
            case PropertiesPackage.EXECUTION_SERVER_PHYSICAL_VIRTUAL_RELATION__PHYSICAL:
                setPhysical((ExecutionServer)null);
                return;
            case PropertiesPackage.EXECUTION_SERVER_PHYSICAL_VIRTUAL_RELATION__VIRTUAL:
                setVirtual((ExecutionVirtualServer)null);
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
            case PropertiesPackage.EXECUTION_SERVER_PHYSICAL_VIRTUAL_RELATION__PHYSICAL:
                return physical != null;
            case PropertiesPackage.EXECUTION_SERVER_PHYSICAL_VIRTUAL_RELATION__VIRTUAL:
                return virtual != null;
        }
        return super.eIsSet(featureID);
    }

} //ExecutionServerPhysicalVirtualRelationImpl
