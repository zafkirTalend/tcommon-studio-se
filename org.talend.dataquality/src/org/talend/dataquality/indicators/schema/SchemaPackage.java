/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.indicators.schema;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.talend.dataquality.indicators.IndicatorsPackage;

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
 * @see org.talend.dataquality.indicators.schema.SchemaFactory
 * @model kind="package"
 * @generated
 */
public interface SchemaPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "schema";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://dataquality.indicators.schema";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "dataquality.indicators.schema";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    SchemaPackage eINSTANCE = org.talend.dataquality.indicators.schema.impl.SchemaPackageImpl.init();

    /**
     * The meta object id for the '{@link org.talend.dataquality.indicators.schema.impl.SchemaIndicatorImpl <em>Indicator</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.dataquality.indicators.schema.impl.SchemaIndicatorImpl
     * @see org.talend.dataquality.indicators.schema.impl.SchemaPackageImpl#getSchemaIndicator()
     * @generated
     */
    int SCHEMA_INDICATOR = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCHEMA_INDICATOR__VALUE = IndicatorsPackage.INDICATOR__VALUE;

    /**
     * The feature id for the '<em><b>Indicator Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCHEMA_INDICATOR__INDICATOR_TYPE = IndicatorsPackage.INDICATOR__INDICATOR_TYPE;

    /**
     * The feature id for the '<em><b>Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCHEMA_INDICATOR__COUNT = IndicatorsPackage.INDICATOR__COUNT;

    /**
     * The feature id for the '<em><b>Null Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCHEMA_INDICATOR__NULL_COUNT = IndicatorsPackage.INDICATOR__NULL_COUNT;

    /**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCHEMA_INDICATOR__PARAMETERS = IndicatorsPackage.INDICATOR__PARAMETERS;

    /**
     * The feature id for the '<em><b>Analyzed Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCHEMA_INDICATOR__ANALYZED_ELEMENT = IndicatorsPackage.INDICATOR__ANALYZED_ELEMENT;

    /**
     * The feature id for the '<em><b>Total Row Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCHEMA_INDICATOR__TOTAL_ROW_COUNT = IndicatorsPackage.INDICATOR_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Table Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCHEMA_INDICATOR__TABLE_COUNT = IndicatorsPackage.INDICATOR_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Key Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCHEMA_INDICATOR__KEY_COUNT = IndicatorsPackage.INDICATOR_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Index Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCHEMA_INDICATOR__INDEX_COUNT = IndicatorsPackage.INDICATOR_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>View Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCHEMA_INDICATOR__VIEW_COUNT = IndicatorsPackage.INDICATOR_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Trigger Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCHEMA_INDICATOR__TRIGGER_COUNT = IndicatorsPackage.INDICATOR_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Indicator</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCHEMA_INDICATOR_FEATURE_COUNT = IndicatorsPackage.INDICATOR_FEATURE_COUNT + 6;


    /**
     * Returns the meta object for class '{@link org.talend.dataquality.indicators.schema.SchemaIndicator <em>Indicator</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Indicator</em>'.
     * @see org.talend.dataquality.indicators.schema.SchemaIndicator
     * @generated
     */
    EClass getSchemaIndicator();

    /**
     * Returns the meta object for the attribute '{@link org.talend.dataquality.indicators.schema.SchemaIndicator#getTotalRowCount <em>Total Row Count</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Total Row Count</em>'.
     * @see org.talend.dataquality.indicators.schema.SchemaIndicator#getTotalRowCount()
     * @see #getSchemaIndicator()
     * @generated
     */
    EAttribute getSchemaIndicator_TotalRowCount();

    /**
     * Returns the meta object for the attribute '{@link org.talend.dataquality.indicators.schema.SchemaIndicator#getTableCount <em>Table Count</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Table Count</em>'.
     * @see org.talend.dataquality.indicators.schema.SchemaIndicator#getTableCount()
     * @see #getSchemaIndicator()
     * @generated
     */
    EAttribute getSchemaIndicator_TableCount();

    /**
     * Returns the meta object for the attribute '{@link org.talend.dataquality.indicators.schema.SchemaIndicator#getKeyCount <em>Key Count</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Key Count</em>'.
     * @see org.talend.dataquality.indicators.schema.SchemaIndicator#getKeyCount()
     * @see #getSchemaIndicator()
     * @generated
     */
    EAttribute getSchemaIndicator_KeyCount();

    /**
     * Returns the meta object for the attribute '{@link org.talend.dataquality.indicators.schema.SchemaIndicator#getIndexCount <em>Index Count</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Index Count</em>'.
     * @see org.talend.dataquality.indicators.schema.SchemaIndicator#getIndexCount()
     * @see #getSchemaIndicator()
     * @generated
     */
    EAttribute getSchemaIndicator_IndexCount();

    /**
     * Returns the meta object for the attribute '{@link org.talend.dataquality.indicators.schema.SchemaIndicator#getViewCount <em>View Count</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>View Count</em>'.
     * @see org.talend.dataquality.indicators.schema.SchemaIndicator#getViewCount()
     * @see #getSchemaIndicator()
     * @generated
     */
    EAttribute getSchemaIndicator_ViewCount();

    /**
     * Returns the meta object for the attribute '{@link org.talend.dataquality.indicators.schema.SchemaIndicator#getTriggerCount <em>Trigger Count</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Trigger Count</em>'.
     * @see org.talend.dataquality.indicators.schema.SchemaIndicator#getTriggerCount()
     * @see #getSchemaIndicator()
     * @generated
     */
    EAttribute getSchemaIndicator_TriggerCount();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    SchemaFactory getSchemaFactory();

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
         * The meta object literal for the '{@link org.talend.dataquality.indicators.schema.impl.SchemaIndicatorImpl <em>Indicator</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.dataquality.indicators.schema.impl.SchemaIndicatorImpl
         * @see org.talend.dataquality.indicators.schema.impl.SchemaPackageImpl#getSchemaIndicator()
         * @generated
         */
        EClass SCHEMA_INDICATOR = eINSTANCE.getSchemaIndicator();

        /**
         * The meta object literal for the '<em><b>Total Row Count</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SCHEMA_INDICATOR__TOTAL_ROW_COUNT = eINSTANCE.getSchemaIndicator_TotalRowCount();

        /**
         * The meta object literal for the '<em><b>Table Count</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SCHEMA_INDICATOR__TABLE_COUNT = eINSTANCE.getSchemaIndicator_TableCount();

        /**
         * The meta object literal for the '<em><b>Key Count</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SCHEMA_INDICATOR__KEY_COUNT = eINSTANCE.getSchemaIndicator_KeyCount();

        /**
         * The meta object literal for the '<em><b>Index Count</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SCHEMA_INDICATOR__INDEX_COUNT = eINSTANCE.getSchemaIndicator_IndexCount();

        /**
         * The meta object literal for the '<em><b>View Count</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SCHEMA_INDICATOR__VIEW_COUNT = eINSTANCE.getSchemaIndicator_ViewCount();

        /**
         * The meta object literal for the '<em><b>Trigger Count</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SCHEMA_INDICATOR__TRIGGER_COUNT = eINSTANCE.getSchemaIndicator_TriggerCount();

    }

} //SchemaPackage
