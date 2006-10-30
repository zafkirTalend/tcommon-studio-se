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
 * A representation of the literals of the enumeration '<em><b>Row Separator</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getRowSeparator()
 * @model
 * @generated
 */
public final class RowSeparator extends AbstractEnumerator {
	/**
	 * The '<em><b>Custom String</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Custom String</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #CUSTOM_STRING_LITERAL
	 * @model name="Custom_String" literal="Custom String"
	 * @generated
	 * @ordered
	 */
    public static final int CUSTOM_STRING = 0;

	/**
	 * The '<em><b>Standart EOL</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Standart EOL</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #STANDART_EOL_LITERAL
	 * @model name="Standart_EOL" literal="Standart EOL"
	 * @generated
	 * @ordered
	 */
    public static final int STANDART_EOL = 1;

    /**
     * The '<em><b>Custom String</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CUSTOM_STRING
     * @ordered
     */
    public static final RowSeparator CUSTOM_STRING_LITERAL = new RowSeparator(CUSTOM_STRING, "Custom String", "Custom String");

    /**
     * The '<em><b>Standart EOL</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #STANDART_EOL
     * @ordered
     */
    public static final RowSeparator STANDART_EOL_LITERAL = new RowSeparator(STANDART_EOL, "Standart EOL", "Standard EOL");

	/**
	 * An array of all the '<em><b>Row Separator</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private static final RowSeparator[] VALUES_ARRAY =
		new RowSeparator[] {
			CUSTOM_STRING_LITERAL,
			STANDART_EOL_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Row Separator</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Row Separator</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static RowSeparator get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			RowSeparator result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Row Separator</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static RowSeparator getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			RowSeparator result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Row Separator</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static RowSeparator get(int value) {
		switch (value) {
			case CUSTOM_STRING: return CUSTOM_STRING_LITERAL;
			case STANDART_EOL: return STANDART_EOL_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private RowSeparator(int value, String name, String literal) {
		super(value, name, literal);
	}

} //RowSeparator
