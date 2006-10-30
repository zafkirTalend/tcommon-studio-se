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
 * A representation of the literals of the enumeration '<em><b>File Format</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getFileFormat()
 * @model
 * @generated
 */
public final class FileFormat extends AbstractEnumerator {
	/**
	 * The '<em><b>UNIX</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>UNIX</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #UNIX_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
    public static final int UNIX = 0;

	/**
	 * The '<em><b>MAC</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>MAC</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #MAC_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
    public static final int MAC = 1;

	/**
	 * The '<em><b>WINDOWS</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>WINDOWS</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #WINDOWS_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
    public static final int WINDOWS = 2;

	/**
	 * The '<em><b>UNIX</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #UNIX
	 * @generated
	 * @ordered
	 */
    public static final FileFormat UNIX_LITERAL = new FileFormat(UNIX, "UNIX", "UNIX");

	/**
	 * The '<em><b>MAC</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #MAC
	 * @generated
	 * @ordered
	 */
    public static final FileFormat MAC_LITERAL = new FileFormat(MAC, "MAC", "MAC");

	/**
	 * The '<em><b>WINDOWS</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #WINDOWS
	 * @generated
	 * @ordered
	 */
    public static final FileFormat WINDOWS_LITERAL = new FileFormat(WINDOWS, "WINDOWS", "WINDOWS");

	/**
	 * An array of all the '<em><b>File Format</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private static final FileFormat[] VALUES_ARRAY =
		new FileFormat[] {
			UNIX_LITERAL,
			MAC_LITERAL,
			WINDOWS_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>File Format</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>File Format</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static FileFormat get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			FileFormat result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>File Format</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static FileFormat getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			FileFormat result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>File Format</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static FileFormat get(int value) {
		switch (value) {
			case UNIX: return UNIX_LITERAL;
			case MAC: return MAC_LITERAL;
			case WINDOWS: return WINDOWS_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private FileFormat(int value, String name, String literal) {
		super(value, name, literal);
	}

} //FileFormat
