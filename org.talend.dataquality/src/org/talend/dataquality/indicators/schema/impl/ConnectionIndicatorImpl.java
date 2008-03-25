/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.dataquality.indicators.schema.impl;

import org.eclipse.emf.ecore.EClass;
import org.talend.dataquality.indicators.schema.ConnectionIndicator;
import org.talend.dataquality.indicators.schema.SchemaIndicator;
import org.talend.dataquality.indicators.schema.SchemaPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Connection Indicator</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * </p>
 * 
 * @generated
 */
public class ConnectionIndicatorImpl extends SchemaIndicatorImpl implements ConnectionIndicator {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ConnectionIndicatorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SchemaPackage.Literals.CONNECTION_INDICATOR;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public boolean addSchemaIndicator(SchemaIndicator schemaIndicator) {
        return this.getIndicators().add(schemaIndicator);
    }

} // ConnectionIndicatorImpl
