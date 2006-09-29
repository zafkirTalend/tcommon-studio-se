/**
 * <copyright> </copyright>
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
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.ItemState;
import org.talend.core.model.properties.PropertiesPackage;
import org.talend.core.model.properties.Property;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Item</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.properties.impl.ItemImpl#getProperty <em>Property</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ItemImpl#getState <em>State</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ItemImpl extends EObjectImpl implements Item {

    /**
     * The cached value of the '{@link #getProperty() <em>Property</em>}' reference.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getProperty()
     * @generated
     * @ordered
     */
    protected Property property = null;

    /**
     * The cached value of the '{@link #getState() <em>State</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getState()
     * @generated
     * @ordered
     */
    protected ItemState state = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected ItemImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.ITEM;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Property getProperty() {
        if (property != null && property.eIsProxy()) {
            InternalEObject oldProperty = (InternalEObject)property;
            property = (Property)eResolveProxy(oldProperty);
            if (property != oldProperty) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.ITEM__PROPERTY, oldProperty, property));
            }
        }
        return property;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Property basicGetProperty() {
        return property;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetProperty(Property newProperty, NotificationChain msgs) {
        Property oldProperty = property;
        property = newProperty;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.ITEM__PROPERTY, oldProperty, newProperty);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setProperty(Property newProperty) {
        if (newProperty != property) {
            NotificationChain msgs = null;
            if (property != null)
                msgs = ((InternalEObject)property).eInverseRemove(this, PropertiesPackage.PROPERTY__ITEM, Property.class, msgs);
            if (newProperty != null)
                msgs = ((InternalEObject)newProperty).eInverseAdd(this, PropertiesPackage.PROPERTY__ITEM, Property.class, msgs);
            msgs = basicSetProperty(newProperty, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ITEM__PROPERTY, newProperty, newProperty));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ItemState getState() {
        if (state != null && state.eIsProxy()) {
            InternalEObject oldState = (InternalEObject)state;
            state = (ItemState)eResolveProxy(oldState);
            if (state != oldState) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.ITEM__STATE, oldState, state));
            }
        }
        return state;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ItemState basicGetState() {
        return state;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setState(ItemState newState) {
        ItemState oldState = state;
        state = newState;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ITEM__STATE, oldState, state));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case PropertiesPackage.ITEM__PROPERTY:
                if (property != null)
                    msgs = ((InternalEObject)property).eInverseRemove(this, PropertiesPackage.PROPERTY__ITEM, Property.class, msgs);
                return basicSetProperty((Property)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case PropertiesPackage.ITEM__PROPERTY:
                return basicSetProperty(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case PropertiesPackage.ITEM__PROPERTY:
                if (resolve) return getProperty();
                return basicGetProperty();
            case PropertiesPackage.ITEM__STATE:
                if (resolve) return getState();
                return basicGetState();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case PropertiesPackage.ITEM__PROPERTY:
                setProperty((Property)newValue);
                return;
            case PropertiesPackage.ITEM__STATE:
                setState((ItemState)newValue);
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
            case PropertiesPackage.ITEM__PROPERTY:
                setProperty((Property)null);
                return;
            case PropertiesPackage.ITEM__STATE:
                setState((ItemState)null);
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
            case PropertiesPackage.ITEM__PROPERTY:
                return property != null;
            case PropertiesPackage.ITEM__STATE:
                return state != null;
        }
        return super.eIsSet(featureID);
    }

} // ItemImpl
