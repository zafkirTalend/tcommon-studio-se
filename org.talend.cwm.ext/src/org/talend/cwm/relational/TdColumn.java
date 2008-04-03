/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.cwm.relational;

import orgomg.cwm.resource.relational.Column;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Td Column</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.talend.cwm.relational.RelationalPackage#getTdColumn()
 * @model
 * @generated
 */
public interface TdColumn extends Column {

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The type of the content of the column. This type is a meta-information either set by the user who knows what type of data is contained in the column, or infered from the data.
     * <!-- end-model-doc -->
     * @model contentTypeDataType="orgomg.cwm.objectmodel.core.String"
     * @generated
     */
    void setContentType(String contentType);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The type of the content of the column. This type is a meta-information either set by the user who knows what type of data is contained in the column, or infered from the data.
     * <!-- end-model-doc -->
     * @model kind="operation" dataType="orgomg.cwm.objectmodel.core.String"
     * @generated
     */
    String getContentType();
} // TdColumn
