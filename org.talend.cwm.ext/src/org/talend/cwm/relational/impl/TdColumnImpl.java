/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.cwm.relational.impl;

import org.eclipse.emf.ecore.EClass;
import org.talend.cwm.helper.TaggedValueHelper;
import org.talend.cwm.relational.RelationalPackage;
import org.talend.cwm.relational.TdColumn;
import orgomg.cwm.resource.relational.impl.ColumnImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Td Column</b></em>'. <!-- end-user-doc -->
 * <p>
 * </p>
 * 
 * @generated
 */
public class TdColumnImpl extends ColumnImpl implements TdColumn {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TdColumnImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return RelationalPackage.Literals.TD_COLUMN;
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

} // TdColumnImpl
