/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.metadata.builder.connection;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Escape</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getEscape()
 * @model
 * @generated
 */
public final class Escape extends AbstractEnumerator {
    /**
     * The '<em><b>Delimited</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Delimited</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #DELIMITED_LITERAL
     * @model name="Delimited"
     * @generated
     * @ordered
     */
    public static final int DELIMITED = 1;

    /**
     * The '<em><b>CSV</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>CSV</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #CSV_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int CSV = 0;

    /**
     * The '<em><b>Delimited</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DELIMITED
     * @generated
     * @ordered
     */
    public static final Escape DELIMITED_LITERAL = new Escape(DELIMITED, "Delimited", "Delimited"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>CSV</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CSV
     * @generated
     * @ordered
     */
    public static final Escape CSV_LITERAL = new Escape(CSV, "CSV", "CSV"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * An array of all the '<em><b>Escape</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final Escape[] VALUES_ARRAY =
        new Escape[] {
            DELIMITED_LITERAL,
            CSV_LITERAL,
        };

    /**
     * A public read-only list of all the '<em><b>Escape</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Escape</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static Escape get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            Escape result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Escape</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static Escape getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            Escape result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Escape</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static Escape get(int value) {
        switch (value) {
            case DELIMITED: return DELIMITED_LITERAL;
            case CSV: return CSV_LITERAL;
        }
        return null;	
    }

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private Escape(int value, String name, String literal) {
        super(value, name, literal);
    }

} //Escape
