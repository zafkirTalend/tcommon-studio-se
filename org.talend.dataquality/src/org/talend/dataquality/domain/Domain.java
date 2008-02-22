/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.domain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.talend.dataquality.domain.pattern.Pattern;

import orgomg.cwm.objectmodel.core.DataType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Domain</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.dataquality.domain.Domain#getName <em>Name</em>}</li>
 *   <li>{@link org.talend.dataquality.domain.Domain#getDescription <em>Description</em>}</li>
 *   <li>{@link org.talend.dataquality.domain.Domain#getDataType <em>Data Type</em>}</li>
 *   <li>{@link org.talend.dataquality.domain.Domain#getLengthRestriction <em>Length Restriction</em>}</li>
 *   <li>{@link org.talend.dataquality.domain.Domain#getRanges <em>Ranges</em>}</li>
 *   <li>{@link org.talend.dataquality.domain.Domain#getPatterns <em>Patterns</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.dataquality.domain.DomainPackage#getDomain()
 * @model
 * @generated
 */
public interface Domain extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.talend.dataquality.domain.DomainPackage#getDomain_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.talend.dataquality.domain.Domain#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Description</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Description</em>' attribute.
     * @see #setDescription(String)
     * @see org.talend.dataquality.domain.DomainPackage#getDomain_Description()
     * @model
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '{@link org.talend.dataquality.domain.Domain#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
    void setDescription(String value);

    /**
     * Returns the value of the '<em><b>Data Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data Type</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Type</em>' reference.
     * @see #setDataType(DataType)
     * @see org.talend.dataquality.domain.DomainPackage#getDomain_DataType()
     * @model
     * @generated
     */
    DataType getDataType();

    /**
     * Sets the value of the '{@link org.talend.dataquality.domain.Domain#getDataType <em>Data Type</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data Type</em>' reference.
     * @see #getDataType()
     * @generated
     */
    void setDataType(DataType value);

    /**
     * Returns the value of the '<em><b>Length Restriction</b></em>' reference list.
     * The list contents are of type {@link org.talend.dataquality.domain.LengthRestriction}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Length Restriction</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Length Restriction</em>' reference list.
     * @see org.talend.dataquality.domain.DomainPackage#getDomain_LengthRestriction()
     * @model
     * @generated
     */
    EList<LengthRestriction> getLengthRestriction();

    /**
     * Returns the value of the '<em><b>Ranges</b></em>' reference list.
     * The list contents are of type {@link org.talend.dataquality.domain.RangeRestriction}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ranges</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Ranges</em>' reference list.
     * @see org.talend.dataquality.domain.DomainPackage#getDomain_Ranges()
     * @model
     * @generated
     */
    EList<RangeRestriction> getRanges();

    /**
     * Returns the value of the '<em><b>Patterns</b></em>' reference list.
     * The list contents are of type {@link org.talend.dataquality.domain.pattern.Pattern}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Patterns</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Patterns</em>' reference list.
     * @see org.talend.dataquality.domain.DomainPackage#getDomain_Patterns()
     * @model
     * @generated
     */
    EList<Pattern> getPatterns();

} // Domain
