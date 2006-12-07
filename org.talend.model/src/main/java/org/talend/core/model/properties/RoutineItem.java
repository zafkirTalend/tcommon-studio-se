/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.core.model.properties;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Routine Item</b></em>'. <!-- end-user-doc
 * -->
 * 
 * 
 * @see org.talend.core.model.properties.PropertiesPackage#getRoutineItem()
 * @model
 * @generated
 */
public interface RoutineItem extends FileItem {
    /**
     * Returns the value of the '<em><b>Built In</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Built In</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Built In</em>' attribute.
     * @see #setBuiltIn(boolean)
     * @see org.talend.core.model.properties.PropertiesPackage#getRoutineItem_BuiltIn()
     * @model default="false"
     * @generated
     */
    boolean isBuiltIn();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.RoutineItem#isBuiltIn <em>Built In</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Built In</em>' attribute.
     * @see #isBuiltIn()
     * @generated
     */
    void setBuiltIn(boolean value);

} // RoutineItem
