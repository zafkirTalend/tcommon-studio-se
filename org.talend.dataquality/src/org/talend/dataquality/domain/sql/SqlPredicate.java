/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.domain.sql;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Sql Predicate</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * See http://www.experlog.com/gibello/zql/sqltut.html#Relational%20Operators
 * <!-- end-model-doc -->
 * @see org.talend.dataquality.domain.sql.SQLPackage#getSqlPredicate()
 * @model
 * @generated
 */
public enum SqlPredicate implements Enumerator {
    /**
     * The '<em><b>EQUAL</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #EQUAL_VALUE
     * @generated
     * @ordered
     */
    EQUAL(0, "EQUAL", "="),

    /**
     * The '<em><b>NOT EQUAL</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NOT_EQUAL_VALUE
     * @generated
     * @ordered
     */
    NOT_EQUAL(1, "NOT_EQUAL", "<>"),

    /**
     * The '<em><b>GREATER</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #GREATER_VALUE
     * @generated
     * @ordered
     */
    GREATER(2, "GREATER", ">"),

    /**
     * The '<em><b>LESS</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LESS_VALUE
     * @generated
     * @ordered
     */
    LESS(3, "LESS", "<"),

    /**
     * The '<em><b>GREATER EQUAL</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #GREATER_EQUAL_VALUE
     * @generated
     * @ordered
     */
    GREATER_EQUAL(4, "GREATER_EQUAL", ">="),

    /**
     * The '<em><b>LESS EQUAL</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LESS_EQUAL_VALUE
     * @generated
     * @ordered
     */
    LESS_EQUAL(5, "LESS_EQUAL", "<="),

    /**
     * The '<em><b>IS</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #IS_VALUE
     * @generated
     * @ordered
     */
    IS(6, "IS", "IS"),

    /**
     * The '<em><b>BETWEEN</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #BETWEEN_VALUE
     * @generated
     * @ordered
     */
    BETWEEN(7, "BETWEEN", "BETWEEN"), /**
     * The '<em><b>LIKE</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LIKE_VALUE
     * @generated
     * @ordered
     */
    LIKE(8, "LIKE", "LIKE"), /**
     * The '<em><b>IN</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #IN_VALUE
     * @generated
     * @ordered
     */
    IN(9, "IN", "IN"),

    /**
     * The '<em><b>NOT EQUAL2</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NOT_EQUAL2_VALUE
     * @generated
     * @ordered
     */
    NOT_EQUAL2(10, "NOT_EQUAL2", "!=");

    /**
     * The '<em><b>EQUAL</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>EQUAL</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #EQUAL
     * @model literal="="
     * @generated
     * @ordered
     */
    public static final int EQUAL_VALUE = 0;

    /**
     * The '<em><b>NOT EQUAL</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Some DBMS do not support the != not equal syntax. In this case, use this syntax.
     * <!-- end-model-doc -->
     * @see #NOT_EQUAL
     * @model literal="<>"
     * @generated
     * @ordered
     */
    public static final int NOT_EQUAL_VALUE = 1;

    /**
     * The '<em><b>GREATER</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>GREATER</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #GREATER
     * @model literal=">"
     * @generated
     * @ordered
     */
    public static final int GREATER_VALUE = 2;

    /**
     * The '<em><b>LESS</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>LESS</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #LESS
     * @model literal="<"
     * @generated
     * @ordered
     */
    public static final int LESS_VALUE = 3;

    /**
     * The '<em><b>GREATER EQUAL</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>GREATER EQUAL</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #GREATER_EQUAL
     * @model literal=">="
     * @generated
     * @ordered
     */
    public static final int GREATER_EQUAL_VALUE = 4;

    /**
     * The '<em><b>LESS EQUAL</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>LESS EQUAL</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #LESS_EQUAL
     * @model literal="<="
     * @generated
     * @ordered
     */
    public static final int LESS_EQUAL_VALUE = 5;

    /**
     * The '<em><b>IS</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>IS</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #IS
     * @model
     * @generated
     * @ordered
     */
    public static final int IS_VALUE = 6;

    /**
     * The '<em><b>BETWEEN</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>BETWEEN</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #BETWEEN
     * @model
     * @generated
     * @ordered
     */
    public static final int BETWEEN_VALUE = 7;

    /**
     * The '<em><b>LIKE</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>LIKE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #LIKE
     * @model
     * @generated
     * @ordered
     */
    public static final int LIKE_VALUE = 8;

    /**
     * The '<em><b>IN</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>IN</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #IN
     * @model
     * @generated
     * @ordered
     */
    public static final int IN_VALUE = 9;

    /**
     * The '<em><b>NOT EQUAL2</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Some DBMS do not support the <> not equal syntax. In this case, use this syntax.
     * <!-- end-model-doc -->
     * @see #NOT_EQUAL2
     * @model literal="!="
     * @generated
     * @ordered
     */
    public static final int NOT_EQUAL2_VALUE = 10;

    /**
     * An array of all the '<em><b>Sql Predicate</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final SqlPredicate[] VALUES_ARRAY =
        new SqlPredicate[] {
            EQUAL,
            NOT_EQUAL,
            GREATER,
            LESS,
            GREATER_EQUAL,
            LESS_EQUAL,
            IS,
            BETWEEN,
            LIKE,
            IN,
            NOT_EQUAL2,
        };

    /**
     * A public read-only list of all the '<em><b>Sql Predicate</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<SqlPredicate> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Sql Predicate</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static SqlPredicate get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            SqlPredicate result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Sql Predicate</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static SqlPredicate getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            SqlPredicate result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Sql Predicate</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static SqlPredicate get(int value) {
        switch (value) {
            case EQUAL_VALUE: return EQUAL;
            case NOT_EQUAL_VALUE: return NOT_EQUAL;
            case GREATER_VALUE: return GREATER;
            case LESS_VALUE: return LESS;
            case GREATER_EQUAL_VALUE: return GREATER_EQUAL;
            case LESS_EQUAL_VALUE: return LESS_EQUAL;
            case IS_VALUE: return IS;
            case BETWEEN_VALUE: return BETWEEN;
            case LIKE_VALUE: return LIKE;
            case IN_VALUE: return IN;
            case NOT_EQUAL2_VALUE: return NOT_EQUAL2;
        }
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final int value;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final String name;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final String literal;

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private SqlPredicate(int value, String name, String literal) {
        this.value = value;
        this.name = name;
        this.literal = literal;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getValue() {
      return value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
      return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLiteral() {
      return literal;
    }

    /**
     * Returns the literal value of the enumerator, which is its string representation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        return literal;
    }
    
} //SqlPredicate
