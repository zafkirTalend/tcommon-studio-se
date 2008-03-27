/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.indicators;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Datatype</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.talend.dataquality.indicators.IndicatorsPackage#getDatatype()
 * @model
 * @generated
 */
public enum Datatype implements Enumerator {
    /**
     * The '<em><b>INTEGER</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #INTEGER_VALUE
     * @generated
     * @ordered
     */
    INTEGER(0, "INTEGER", "Integer"),

    /**
     * The '<em><b>DOUBLE</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DOUBLE_VALUE
     * @generated
     * @ordered
     */
    DOUBLE(1, "DOUBLE", "Double"),

    /**
     * The '<em><b>TEXT</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #TEXT_VALUE
     * @generated
     * @ordered
     */
    TEXT(2, "TEXT", "Text"), /**
     * The '<em><b>BIGDECIMAL</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #BIGDECIMAL_VALUE
     * @generated
     * @ordered
     */
    BIGDECIMAL(3, "BIGDECIMAL", "Big Decimal");

    /**
     * The '<em><b>INTEGER</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>INTEGER</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #INTEGER
     * @model literal="Integer"
     * @generated
     * @ordered
     */
    public static final int INTEGER_VALUE = 0;

    /**
     * The '<em><b>DOUBLE</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>DOUBLE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #DOUBLE
     * @model literal="Double"
     * @generated
     * @ordered
     */
    public static final int DOUBLE_VALUE = 1;

    /**
     * The '<em><b>TEXT</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>TEXT</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #TEXT
     * @model literal="Text"
     * @generated
     * @ordered
     */
    public static final int TEXT_VALUE = 2;

    /**
     * The '<em><b>BIGDECIMAL</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>BIGDECIMAL</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #BIGDECIMAL
     * @model literal="Big Decimal"
     * @generated
     * @ordered
     */
    public static final int BIGDECIMAL_VALUE = 3;

    /**
     * An array of all the '<em><b>Datatype</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final Datatype[] VALUES_ARRAY =
        new Datatype[] {
            INTEGER,
            DOUBLE,
            TEXT,
            BIGDECIMAL,
        };

    /**
     * A public read-only list of all the '<em><b>Datatype</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<Datatype> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Datatype</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static Datatype get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            Datatype result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Datatype</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static Datatype getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            Datatype result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Datatype</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static Datatype get(int value) {
        switch (value) {
            case INTEGER_VALUE: return INTEGER;
            case DOUBLE_VALUE: return DOUBLE;
            case TEXT_VALUE: return TEXT;
            case BIGDECIMAL_VALUE: return BIGDECIMAL;
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
    private Datatype(int value, String name, String literal) {
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
    
} //Datatype
