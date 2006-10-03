/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.properties.impl;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.talend.core.model.properties.Container;
import org.talend.core.model.properties.FolderItem;
import org.talend.core.model.properties.FolderType;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Folder Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.properties.impl.FolderItemImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.FolderItemImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.FolderItemImpl#getParent <em>Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FolderItemImpl extends ItemImpl implements FolderItem {
    /**
     * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChildren()
     * @generated
     * @ordered
     */
    protected EList children = null;

    /**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final FolderType TYPE_EDEFAULT = FolderType.SYSTEM_FOLDER_LITERAL;

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected FolderType type = TYPE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FolderItemImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.FOLDER_ITEM;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getChildren() {
        if (children == null) {
            children = new EObjectContainmentWithInverseEList(Item.class, this, PropertiesPackage.FOLDER_ITEM__CHILDREN, PropertiesPackage.FOLDER_ITEM__PARENT);
        }
        return children;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FolderType getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setType(FolderType newType) {
        FolderType oldType = type;
        type = newType == null ? TYPE_EDEFAULT : newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.FOLDER_ITEM__TYPE, oldType, type));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Container getParent() {
        if (eContainerFeatureID != PropertiesPackage.FOLDER_ITEM__PARENT) return null;
        return (Container)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetParent(Container newParent, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newParent, PropertiesPackage.FOLDER_ITEM__PARENT, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParent(Container newParent) {
        if (newParent != eInternalContainer() || (eContainerFeatureID != PropertiesPackage.FOLDER_ITEM__PARENT && newParent != null)) {
            if (EcoreUtil.isAncestor(this, newParent))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newParent != null)
                msgs = ((InternalEObject)newParent).eInverseAdd(this, PropertiesPackage.FOLDER_ITEM__CHILDREN, FolderItem.class, msgs);
            msgs = basicSetParent(newParent, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.FOLDER_ITEM__PARENT, newParent, newParent));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public FolderItem findChildFolder(String name) {
        for (Iterator it = getChildren().iterator(); it.hasNext();)
        {
            Item item = (Item)it.next();
            if (item instanceof FolderItem &&  item.getProperty().getLabel().equals(name))
                return (FolderItem)item;
        }
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public EList getContent() {
        return getChildren();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public Container getContainer() {
        return getParent();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case PropertiesPackage.FOLDER_ITEM__CHILDREN:
                return ((InternalEList)getChildren()).basicAdd(otherEnd, msgs);
            case PropertiesPackage.FOLDER_ITEM__PARENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetParent((Container)otherEnd, msgs);
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
            case PropertiesPackage.FOLDER_ITEM__CHILDREN:
                return ((InternalEList)getChildren()).basicRemove(otherEnd, msgs);
            case PropertiesPackage.FOLDER_ITEM__PARENT:
                return basicSetParent(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID) {
            case PropertiesPackage.FOLDER_ITEM__PARENT:
                return eInternalContainer().eInverseRemove(this, PropertiesPackage.FOLDER_ITEM__CHILDREN, FolderItem.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case PropertiesPackage.FOLDER_ITEM__CHILDREN:
                return getChildren();
            case PropertiesPackage.FOLDER_ITEM__TYPE:
                return getType();
            case PropertiesPackage.FOLDER_ITEM__PARENT:
                return getParent();
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
            case PropertiesPackage.FOLDER_ITEM__CHILDREN:
                getChildren().clear();
                getChildren().addAll((Collection)newValue);
                return;
            case PropertiesPackage.FOLDER_ITEM__TYPE:
                setType((FolderType)newValue);
                return;
            case PropertiesPackage.FOLDER_ITEM__PARENT:
                setParent((Container)newValue);
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
            case PropertiesPackage.FOLDER_ITEM__CHILDREN:
                getChildren().clear();
                return;
            case PropertiesPackage.FOLDER_ITEM__TYPE:
                setType(TYPE_EDEFAULT);
                return;
            case PropertiesPackage.FOLDER_ITEM__PARENT:
                setParent((Container)null);
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
            case PropertiesPackage.FOLDER_ITEM__CHILDREN:
                return children != null && !children.isEmpty();
            case PropertiesPackage.FOLDER_ITEM__TYPE:
                return type != TYPE_EDEFAULT;
            case PropertiesPackage.FOLDER_ITEM__PARENT:
                return getParent() != null;
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
        result.append(" (type: ");
        result.append(type);
        result.append(')');
        return result.toString();
    }

} //FolderItemImpl