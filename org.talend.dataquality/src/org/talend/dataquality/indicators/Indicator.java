/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.indicators;

import org.eclipse.emf.ecore.EObject;
import org.talend.dataquality.domain.LiteralValue;

import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwmx.analysis.informationreporting.ReportField;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Indicator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.dataquality.indicators.Indicator#getIndicatorType <em>Indicator Type</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.Indicator#getCount <em>Count</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.Indicator#getNullCount <em>Null Count</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.Indicator#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.Indicator#getAnalyzedElement <em>Analyzed Element</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.Indicator#getDataminingType <em>Datamining Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.dataquality.indicators.IndicatorsPackage#getIndicator()
 * @model
 * @generated
 */
public interface Indicator extends ModelElement {
    /**
     * Returns the value of the '<em><b>Indicator Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Indicator Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Indicator Type</em>' reference.
     * @see #setIndicatorType(IndicatorType)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getIndicator_IndicatorType()
     * @model
     * @generated
     */
    IndicatorType getIndicatorType();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.Indicator#getIndicatorType <em>Indicator Type</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Indicator Type</em>' reference.
     * @see #getIndicatorType()
     * @generated
     */
    void setIndicatorType(IndicatorType value);

    /**
     * Returns the value of the '<em><b>Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Count</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Count</em>' attribute.
     * @see #setCount(long)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getIndicator_Count()
     * @model
     * @generated
     */
    long getCount();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.Indicator#getCount <em>Count</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Count</em>' attribute.
     * @see #getCount()
     * @generated
     */
    void setCount(long value);

    /**
     * Returns the value of the '<em><b>Null Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Null Count</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Null Count</em>' attribute.
     * @see #setNullCount(long)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getIndicator_NullCount()
     * @model
     * @generated
     */
    long getNullCount();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.Indicator#getNullCount <em>Null Count</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Null Count</em>' attribute.
     * @see #getNullCount()
     * @generated
     */
    void setNullCount(long value);

    /**
     * Returns the value of the '<em><b>Parameters</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parameters</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parameters</em>' containment reference.
     * @see #setParameters(IndicatorParameters)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getIndicator_Parameters()
     * @model containment="true"
     * @generated
     */
    IndicatorParameters getParameters();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.Indicator#getParameters <em>Parameters</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parameters</em>' containment reference.
     * @see #getParameters()
     * @generated
     */
    void setParameters(IndicatorParameters value);

    /**
     * Returns the value of the '<em><b>Analyzed Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Analyzed Element</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Analyzed Element</em>' reference.
     * @see #setAnalyzedElement(ModelElement)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getIndicator_AnalyzedElement()
     * @model
     * @generated
     */
    ModelElement getAnalyzedElement();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.Indicator#getAnalyzedElement <em>Analyzed Element</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Analyzed Element</em>' reference.
     * @see #getAnalyzedElement()
     * @generated
     */
    void setAnalyzedElement(ModelElement value);

    /**
     * Returns the value of the '<em><b>Datamining Type</b></em>' attribute.
     * The literals are from the enumeration {@link org.talend.dataquality.indicators.DataminingType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Datamining Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Datamining Type</em>' attribute.
     * @see org.talend.dataquality.indicators.DataminingType
     * @see #setDataminingType(DataminingType)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getIndicator_DataminingType()
     * @model
     * @generated
     */
    DataminingType getDataminingType();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.Indicator#getDataminingType <em>Datamining Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Datamining Type</em>' attribute.
     * @see org.talend.dataquality.indicators.DataminingType
     * @see #getDataminingType()
     * @generated
     */
    void setDataminingType(DataminingType value);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model
     * @generated
     */
    boolean handle(Object data);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model
     * @generated
     */
    boolean reset();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model kind="operation"
     * @generated
     */
    String getPurpose();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model kind="operation"
     * @generated
     */
    String getLongDescription();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Prepares this indicator for a computation. This method can be used for initialization some internal state data from parameters. Or initializing some internal values before handling data. 
     * <!-- end-model-doc -->
     * @model
     * @generated
     */
    boolean prepare();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * can be called after having given all objects to handle. Subclasses can use this method for doing some computation that must be done at the end only.
     * <!-- end-model-doc -->
     * @model
     * @generated
     */
    boolean finalizeComputation();

} // Indicator
