/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.properties;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Folder Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.core.model.properties.FolderItem#getChildren <em>Children</em>}</li>
 *   <li>{@link org.talend.core.model.properties.FolderItem#getType <em>Type</em>}</li>
 *   <li>{@link org.talend.core.model.properties.FolderItem#getParent <em>Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.core.model.properties.PropertiesPackage#getFolderItem()
 * @model
 * @generated
 */
public interface FolderItem extends Item, Container {
    /**
     * Returns the value of the '<em><b>Children</b></em>' containment reference list.
     * The list contents are of type {@link org.talend.core.model.properties.Item}.
     * It is bidirectional and its opposite is '{@link org.talend.core.model.properties.FolderItem#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Children</em>' containment reference list.
     * @see org.talend.core.model.properties.PropertiesPackage#getFolderItem_Children()
     * @see org.talend.core.model.properties.FolderItem#getParent
     * @model type="org.talend.core.model.properties.Item" opposite="parent" containment="true"
     * @generated
     */
    EList getChildren();

    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * The literals are from the enumeration {@link org.talend.core.model.properties.FolderType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see org.talend.core.model.properties.FolderType
     * @see #setType(FolderType)
     * @see org.talend.core.model.properties.PropertiesPackage#getFolderItem_Type()
     * @model unique="false" required="true"
     * @generated
     */
    FolderType getType();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.FolderItem#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see org.talend.core.model.properties.FolderType
     * @see #getType()
     * @generated
     */
    void setType(FolderType value);

    /**
     * Returns the value of the '<em><b>Parent</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.talend.core.model.properties.FolderItem#getChildren <em>Children</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parent</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parent</em>' container reference.
     * @see #setParent(Container)
     * @see org.talend.core.model.properties.PropertiesPackage#getFolderItem_Parent()
     * @see org.talend.core.model.properties.FolderItem#getChildren
     * @model opposite="children"
     * @generated
     */
    Container getParent();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.FolderItem#getParent <em>Parent</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parent</em>' container reference.
     * @see #getParent()
     * @generated
     */
    void setParent(Container value);

} // FolderItem