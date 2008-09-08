/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.core.model.properties;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Item</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.core.model.properties.Item#getProperty <em>Property</em>}</li>
 *   <li>{@link org.talend.core.model.properties.Item#getState <em>State</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.core.model.properties.PropertiesPackage#getItem()
 * @model abstract="true"
 * @generated
 */
public interface Item extends EObject {

    /**
	 * Returns the value of the '<em><b>Property</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.talend.core.model.properties.Property#getItem <em>Item</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Property</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Property</em>' reference.
	 * @see #setProperty(Property)
	 * @see org.talend.core.model.properties.PropertiesPackage#getItem_Property()
	 * @see org.talend.core.model.properties.Property#getItem
	 * @model opposite="item"
	 * @generated
	 */
    Property getProperty();

    /**
	 * Sets the value of the '{@link org.talend.core.model.properties.Item#getProperty <em>Property</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property</em>' reference.
	 * @see #getProperty()
	 * @generated
	 */
    void setProperty(Property value);

    /**
	 * Returns the value of the '<em><b>State</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>State</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>State</em>' reference.
	 * @see #setState(ItemState)
	 * @see org.talend.core.model.properties.PropertiesPackage#getItem_State()
	 * @model
	 * @generated
	 */
    ItemState getState();

    /**
	 * Sets the value of the '{@link org.talend.core.model.properties.Item#getState <em>State</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>State</em>' reference.
	 * @see #getState()
	 * @generated
	 */
    void setState(ItemState value);

} // Item
