/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.domain.sql;

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.talend.dataquality.domain.sql.SQLFactory
 * @model kind="package"
 * @generated
 */
public interface SQLPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "sql";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://dataquality.domain.sql";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "dataquality.domain.sql";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    SQLPackage eINSTANCE = org.talend.dataquality.domain.sql.impl.SQLPackageImpl.init();

    /**
     * The meta object id for the '{@link org.talend.dataquality.domain.sql.SqlRelationalOperator <em>Sql Relational Operator</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.dataquality.domain.sql.SqlRelationalOperator
     * @see org.talend.dataquality.domain.sql.impl.SQLPackageImpl#getSqlRelationalOperator()
     * @generated
     */
    int SQL_RELATIONAL_OPERATOR = 0;


    /**
     * Returns the meta object for enum '{@link org.talend.dataquality.domain.sql.SqlRelationalOperator <em>Sql Relational Operator</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Sql Relational Operator</em>'.
     * @see org.talend.dataquality.domain.sql.SqlRelationalOperator
     * @generated
     */
    EEnum getSqlRelationalOperator();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    SQLFactory getSQLFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link org.talend.dataquality.domain.sql.SqlRelationalOperator <em>Sql Relational Operator</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.dataquality.domain.sql.SqlRelationalOperator
         * @see org.talend.dataquality.domain.sql.impl.SQLPackageImpl#getSqlRelationalOperator()
         * @generated
         */
        EEnum SQL_RELATIONAL_OPERATOR = eINSTANCE.getSqlRelationalOperator();

    }

} //SQLPackage
