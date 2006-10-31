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
 * A representation of the literals of the enumeration '<em><b>Field Separator</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getFieldSeparator()
 * @model
 * @generated
 */
public final class FieldSeparator extends AbstractEnumerator {
    /**
     * The '<em><b>Tabulation</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Tabulation</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #TABULATION_LITERAL
     * @model name="Tabulation"
     * @generated
     * @ordered
     */
    public static final int TABULATION = 0;

    /**
     * The '<em><b>Semicolon</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Semicolon</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #SEMICOLON_LITERAL
     * @model name="Semicolon"
     * @generated
     * @ordered
     */
    public static final int SEMICOLON = 1;

    /**
     * The '<em><b>Comma</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Comma</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #COMMA_LITERAL
     * @model name="Comma"
     * @generated
     * @ordered
     */
    public static final int COMMA = 2;

    /**
     * The '<em><b>Space</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Space</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #SPACE_LITERAL
     * @model name="Space"
     * @generated
     * @ordered
     */
    public static final int SPACE = 3;

    /**
     * The '<em><b>Alt 65</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Alt 65</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ALT_65_LITERAL
     * @model name="Alt_65" literal="''(Alt 65, #A4)"
     * @generated
     * @ordered
     */
    public static final int ALT_65 = 4;

    /**
     * The '<em><b>Custom ANSI</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Custom ANSI</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #CUSTOM_ANSI_LITERAL
     * @model name="Custom_ANSI"
     * @generated
     * @ordered
     */
    public static final int CUSTOM_ANSI = 5;

    /**
     * The '<em><b>Custom UTF8</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Custom UTF8</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #CUSTOM_UTF8_LITERAL
     * @model name="Custom_UTF8"
     * @generated
     * @ordered
     */
    public static final int CUSTOM_UTF8 = 6;

    /**
     * The '<em><b>Custom Reg Exp</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Custom Reg Exp</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #CUSTOM_REG_EXP_LITERAL
     * @model name="Custom_RegExp"
     * @generated
     * @ordered
     */
    public static final int CUSTOM_REG_EXP = 7;

    /**
     * The '<em><b>Tabulation</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #TABULATION
     * @generated
     * @ordered
     */
    public static final FieldSeparator TABULATION_LITERAL = new FieldSeparator(TABULATION, "Tabulation", "Tabulation");

    /**
     * The '<em><b>Semicolon</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #SEMICOLON
     * @generated
     * @ordered
     */
    public static final FieldSeparator SEMICOLON_LITERAL = new FieldSeparator(SEMICOLON, "Semicolon", "Semicolon");

    /**
     * The '<em><b>Comma</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #COMMA
     * @generated
     * @ordered
     */
    public static final FieldSeparator COMMA_LITERAL = new FieldSeparator(COMMA, "Comma", "Comma");

    /**
     * The '<em><b>Space</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #SPACE
     * @generated
     * @ordered
     */
    public static final FieldSeparator SPACE_LITERAL = new FieldSeparator(SPACE, "Space", "Space");

    /**
     * The '<em><b>Alt 65</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ALT_65
     * @ordered
     */
    public static final FieldSeparator ALT_65_LITERAL = new FieldSeparator(ALT_65, "''(Alt 65, #A4)", "''(Alt 65, #A4)");

    /**
     * The '<em><b>Custom ANSI</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CUSTOM_ANSI
     * @ordered
     */
    public static final FieldSeparator CUSTOM_ANSI_LITERAL = new FieldSeparator(CUSTOM_ANSI, "Custom ANSI", "Custom ANSI");

    /**
     * The '<em><b>Custom UTF8</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CUSTOM_UTF8
     * @ordered
     */
    public static final FieldSeparator CUSTOM_UTF8_LITERAL = new FieldSeparator(CUSTOM_UTF8, "Custom UTF8", "Custom UTF8");

    /**
     * The '<em><b>Custom Reg Exp</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CUSTOM_REG_EXP
     * @ordered
     */
    public static final FieldSeparator CUSTOM_REG_EXP_LITERAL = new FieldSeparator(CUSTOM_REG_EXP, "Custom RegExp", "Custom RegExp");

    /**
     * An array of all the '<em><b>Field Separator</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final FieldSeparator[] VALUES_ARRAY =
        new FieldSeparator[] {
            TABULATION_LITERAL,
            SEMICOLON_LITERAL,
            COMMA_LITERAL,
            SPACE_LITERAL,
            ALT_65_LITERAL,
            CUSTOM_ANSI_LITERAL,
            CUSTOM_UTF8_LITERAL,
            CUSTOM_REG_EXP_LITERAL,
        };

    /**
     * A public read-only list of all the '<em><b>Field Separator</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Field Separator</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static FieldSeparator get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            FieldSeparator result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Field Separator</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static FieldSeparator getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            FieldSeparator result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Field Separator</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static FieldSeparator get(int value) {
        switch (value) {
            case TABULATION: return TABULATION_LITERAL;
            case SEMICOLON: return SEMICOLON_LITERAL;
            case COMMA: return COMMA_LITERAL;
            case SPACE: return SPACE_LITERAL;
            case ALT_65: return ALT_65_LITERAL;
            case CUSTOM_ANSI: return CUSTOM_ANSI_LITERAL;
            case CUSTOM_UTF8: return CUSTOM_UTF8_LITERAL;
            case CUSTOM_REG_EXP: return CUSTOM_REG_EXP_LITERAL;
        }
        return null;	
    }

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private FieldSeparator(int value, String name, String literal) {
        super(value, name, literal);
    }

} //FieldSeparator
