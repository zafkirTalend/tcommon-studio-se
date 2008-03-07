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
 * A representation of the literals of the enumeration '<em><b>Datamining Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.talend.dataquality.indicators.IndicatorsPackage#getDataminingType()
 * @model
 * @generated
 */
public enum DataminingType implements Enumerator {
    /**
     * The '<em><b>NOMINAL</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NOMINAL_VALUE
     * @generated
     * @ordered
     */
    NOMINAL(0, "NOMINAL", "Nominal"),

    /**
     * The '<em><b>INTERVAL</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #INTERVAL_VALUE
     * @generated
     * @ordered
     */
    INTERVAL(1, "INTERVAL", "Interval");

    /**
     * The '<em><b>NOMINAL</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>NOMINAL</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #NOMINAL
     * @model literal="Nominal"
     * @generated
     * @ordered
     */
    public static final int NOMINAL_VALUE = 0;

    /**
     * The '<em><b>INTERVAL</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>INTERVAL</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #INTERVAL
     * @model literal="Interval"
     * @generated
     * @ordered
     */
    public static final int INTERVAL_VALUE = 1;

    /**
     * An array of all the '<em><b>Datamining Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final DataminingType[] VALUES_ARRAY =
        new DataminingType[] {
            NOMINAL,
            INTERVAL,
        };

    /**
     * A public read-only list of all the '<em><b>Datamining Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<DataminingType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Datamining Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static DataminingType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            DataminingType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Datamining Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static DataminingType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            DataminingType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Datamining Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static DataminingType get(int value) {
        switch (value) {
            case NOMINAL_VALUE: return NOMINAL;
            case INTERVAL_VALUE: return INTERVAL;
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
    private DataminingType(int value, String name, String literal) {
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
    
} //DataminingType
