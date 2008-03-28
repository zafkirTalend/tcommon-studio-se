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
     * The meta object id for the '{@link org.talend.dataquality.domain.sql.SqlPredicate <em>Sql Predicate</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.dataquality.domain.sql.SqlPredicate
     * @see org.talend.dataquality.domain.sql.impl.SQLPackageImpl#getSqlPredicate()
     * @generated
     */
    int SQL_PREDICATE = 0;

    /**
     * The meta object id for the '{@link org.talend.dataquality.domain.sql.SqlLogicalOperator <em>Sql Logical Operator</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.dataquality.domain.sql.SqlLogicalOperator
     * @see org.talend.dataquality.domain.sql.impl.SQLPackageImpl#getSqlLogicalOperator()
     * @generated
     */
    int SQL_LOGICAL_OPERATOR = 1;

    /**
     * The meta object id for the '{@link org.talend.dataquality.domain.sql.Bracket <em>Bracket</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.dataquality.domain.sql.Bracket
     * @see org.talend.dataquality.domain.sql.impl.SQLPackageImpl#getBracket()
     * @generated
     */
    int BRACKET = 2;


    /**
     * The meta object id for the '{@link org.talend.dataquality.domain.sql.SqlKeyword <em>Sql Keyword</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.dataquality.domain.sql.SqlKeyword
     * @see org.talend.dataquality.domain.sql.impl.SQLPackageImpl#getSqlKeyword()
     * @generated
     */
    int SQL_KEYWORD = 3;


    /**
     * Returns the meta object for enum '{@link org.talend.dataquality.domain.sql.SqlPredicate <em>Sql Predicate</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Sql Predicate</em>'.
     * @see org.talend.dataquality.domain.sql.SqlPredicate
     * @generated
     */
    EEnum getSqlPredicate();

    /**
     * Returns the meta object for enum '{@link org.talend.dataquality.domain.sql.SqlLogicalOperator <em>Sql Logical Operator</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Sql Logical Operator</em>'.
     * @see org.talend.dataquality.domain.sql.SqlLogicalOperator
     * @generated
     */
    EEnum getSqlLogicalOperator();

    /**
     * Returns the meta object for enum '{@link org.talend.dataquality.domain.sql.Bracket <em>Bracket</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Bracket</em>'.
     * @see org.talend.dataquality.domain.sql.Bracket
     * @generated
     */
    EEnum getBracket();

    /**
     * Returns the meta object for enum '{@link org.talend.dataquality.domain.sql.SqlKeyword <em>Sql Keyword</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Sql Keyword</em>'.
     * @see org.talend.dataquality.domain.sql.SqlKeyword
     * @generated
     */
    EEnum getSqlKeyword();

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
         * The meta object literal for the '{@link org.talend.dataquality.domain.sql.SqlPredicate <em>Sql Predicate</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.dataquality.domain.sql.SqlPredicate
         * @see org.talend.dataquality.domain.sql.impl.SQLPackageImpl#getSqlPredicate()
         * @generated
         */
        EEnum SQL_PREDICATE = eINSTANCE.getSqlPredicate();
        /**
         * The meta object literal for the '{@link org.talend.dataquality.domain.sql.SqlLogicalOperator <em>Sql Logical Operator</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.dataquality.domain.sql.SqlLogicalOperator
         * @see org.talend.dataquality.domain.sql.impl.SQLPackageImpl#getSqlLogicalOperator()
         * @generated
         */
        EEnum SQL_LOGICAL_OPERATOR = eINSTANCE.getSqlLogicalOperator();
        /**
         * The meta object literal for the '{@link org.talend.dataquality.domain.sql.Bracket <em>Bracket</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.dataquality.domain.sql.Bracket
         * @see org.talend.dataquality.domain.sql.impl.SQLPackageImpl#getBracket()
         * @generated
         */
        EEnum BRACKET = eINSTANCE.getBracket();
        /**
         * The meta object literal for the '{@link org.talend.dataquality.domain.sql.SqlKeyword <em>Sql Keyword</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.dataquality.domain.sql.SqlKeyword
         * @see org.talend.dataquality.domain.sql.impl.SQLPackageImpl#getSqlKeyword()
         * @generated
         */
        EEnum SQL_KEYWORD = eINSTANCE.getSqlKeyword();

    }

} //SQLPackage
