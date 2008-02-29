/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.indicators;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import orgomg.cwm.objectmodel.core.CorePackage;
import orgomg.cwmx.analysis.informationreporting.InformationreportingPackage;

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
 * @see org.talend.dataquality.indicators.IndicatorsFactory
 * @model kind="package"
 * @generated
 */
public interface IndicatorsPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "indicators";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://.dataquality.indicators";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "dataquality.indicators";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    IndicatorsPackage eINSTANCE = org.talend.dataquality.indicators.impl.IndicatorsPackageImpl.init();

    /**
     * The meta object id for the '{@link org.talend.dataquality.indicators.impl.IndicatorImpl <em>Indicator</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.dataquality.indicators.impl.IndicatorImpl
     * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getIndicator()
     * @generated
     */
    int INDICATOR = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR__VALUE = 0;

    /**
     * The feature id for the '<em><b>Indicator Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR__INDICATOR_TYPE = 1;

    /**
     * The feature id for the '<em><b>Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR__COUNT = 2;

    /**
     * The feature id for the '<em><b>Null Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR__NULL_COUNT = 3;

    /**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR__PARAMETERS = 4;

    /**
     * The feature id for the '<em><b>Analyzed Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR__ANALYZED_ELEMENT = 5;

    /**
     * The number of structural features of the '<em>Indicator</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_FEATURE_COUNT = 6;

    /**
     * The meta object id for the '{@link org.talend.dataquality.indicators.impl.RowCountIndicatorImpl <em>Row Count Indicator</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.dataquality.indicators.impl.RowCountIndicatorImpl
     * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getRowCountIndicator()
     * @generated
     */
    int ROW_COUNT_INDICATOR = 1;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ROW_COUNT_INDICATOR__VALUE = INDICATOR__VALUE;

    /**
     * The feature id for the '<em><b>Indicator Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ROW_COUNT_INDICATOR__INDICATOR_TYPE = INDICATOR__INDICATOR_TYPE;

    /**
     * The feature id for the '<em><b>Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ROW_COUNT_INDICATOR__COUNT = INDICATOR__COUNT;

    /**
     * The feature id for the '<em><b>Null Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ROW_COUNT_INDICATOR__NULL_COUNT = INDICATOR__NULL_COUNT;

    /**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ROW_COUNT_INDICATOR__PARAMETERS = INDICATOR__PARAMETERS;

    /**
     * The feature id for the '<em><b>Analyzed Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ROW_COUNT_INDICATOR__ANALYZED_ELEMENT = INDICATOR__ANALYZED_ELEMENT;

    /**
     * The number of structural features of the '<em>Row Count Indicator</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ROW_COUNT_INDICATOR_FEATURE_COUNT = INDICATOR_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.talend.dataquality.indicators.MeanIndicator <em>Mean Indicator</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.dataquality.indicators.MeanIndicator
     * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getMeanIndicator()
     * @generated
     */
    int MEAN_INDICATOR = 2;

    /**
     * The number of structural features of the '<em>Mean Indicator</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MEAN_INDICATOR_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '{@link org.talend.dataquality.indicators.impl.SumIndicatorImpl <em>Sum Indicator</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.dataquality.indicators.impl.SumIndicatorImpl
     * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getSumIndicator()
     * @generated
     */
    int SUM_INDICATOR = 3;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SUM_INDICATOR__VALUE = INDICATOR__VALUE;

    /**
     * The feature id for the '<em><b>Indicator Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SUM_INDICATOR__INDICATOR_TYPE = INDICATOR__INDICATOR_TYPE;

    /**
     * The feature id for the '<em><b>Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SUM_INDICATOR__COUNT = INDICATOR__COUNT;

    /**
     * The feature id for the '<em><b>Null Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SUM_INDICATOR__NULL_COUNT = INDICATOR__NULL_COUNT;

    /**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SUM_INDICATOR__PARAMETERS = INDICATOR__PARAMETERS;

    /**
     * The feature id for the '<em><b>Analyzed Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SUM_INDICATOR__ANALYZED_ELEMENT = INDICATOR__ANALYZED_ELEMENT;

    /**
     * The number of structural features of the '<em>Sum Indicator</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SUM_INDICATOR_FEATURE_COUNT = INDICATOR_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.talend.dataquality.indicators.impl.CompositeIndicatorImpl <em>Composite Indicator</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.dataquality.indicators.impl.CompositeIndicatorImpl
     * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getCompositeIndicator()
     * @generated
     */
    int COMPOSITE_INDICATOR = 4;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPOSITE_INDICATOR__VALUE = INDICATOR__VALUE;

    /**
     * The feature id for the '<em><b>Indicator Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPOSITE_INDICATOR__INDICATOR_TYPE = INDICATOR__INDICATOR_TYPE;

    /**
     * The feature id for the '<em><b>Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPOSITE_INDICATOR__COUNT = INDICATOR__COUNT;

    /**
     * The feature id for the '<em><b>Null Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPOSITE_INDICATOR__NULL_COUNT = INDICATOR__NULL_COUNT;

    /**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPOSITE_INDICATOR__PARAMETERS = INDICATOR__PARAMETERS;

    /**
     * The feature id for the '<em><b>Analyzed Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPOSITE_INDICATOR__ANALYZED_ELEMENT = INDICATOR__ANALYZED_ELEMENT;

    /**
     * The feature id for the '<em><b>Indicators</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPOSITE_INDICATOR__INDICATORS = INDICATOR_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Composite Indicator</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPOSITE_INDICATOR_FEATURE_COUNT = INDICATOR_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.talend.dataquality.indicators.impl.RangeIndicatorImpl <em>Range Indicator</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.dataquality.indicators.impl.RangeIndicatorImpl
     * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getRangeIndicator()
     * @generated
     */
    int RANGE_INDICATOR = 5;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RANGE_INDICATOR__VALUE = COMPOSITE_INDICATOR__VALUE;

    /**
     * The feature id for the '<em><b>Indicator Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RANGE_INDICATOR__INDICATOR_TYPE = COMPOSITE_INDICATOR__INDICATOR_TYPE;

    /**
     * The feature id for the '<em><b>Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RANGE_INDICATOR__COUNT = COMPOSITE_INDICATOR__COUNT;

    /**
     * The feature id for the '<em><b>Null Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RANGE_INDICATOR__NULL_COUNT = COMPOSITE_INDICATOR__NULL_COUNT;

    /**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RANGE_INDICATOR__PARAMETERS = COMPOSITE_INDICATOR__PARAMETERS;

    /**
     * The feature id for the '<em><b>Analyzed Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RANGE_INDICATOR__ANALYZED_ELEMENT = COMPOSITE_INDICATOR__ANALYZED_ELEMENT;

    /**
     * The feature id for the '<em><b>Indicators</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RANGE_INDICATOR__INDICATORS = COMPOSITE_INDICATOR__INDICATORS;

    /**
     * The feature id for the '<em><b>Lower Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RANGE_INDICATOR__LOWER_VALUE = COMPOSITE_INDICATOR_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Upper Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RANGE_INDICATOR__UPPER_VALUE = COMPOSITE_INDICATOR_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Range</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RANGE_INDICATOR__RANGE = COMPOSITE_INDICATOR_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Range Indicator</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RANGE_INDICATOR_FEATURE_COUNT = COMPOSITE_INDICATOR_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.talend.dataquality.indicators.impl.BoxIndicatorImpl <em>Box Indicator</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.dataquality.indicators.impl.BoxIndicatorImpl
     * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getBoxIndicator()
     * @generated
     */
    int BOX_INDICATOR = 6;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOX_INDICATOR__VALUE = COMPOSITE_INDICATOR__VALUE;

    /**
     * The feature id for the '<em><b>Indicator Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOX_INDICATOR__INDICATOR_TYPE = COMPOSITE_INDICATOR__INDICATOR_TYPE;

    /**
     * The feature id for the '<em><b>Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOX_INDICATOR__COUNT = COMPOSITE_INDICATOR__COUNT;

    /**
     * The feature id for the '<em><b>Null Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOX_INDICATOR__NULL_COUNT = COMPOSITE_INDICATOR__NULL_COUNT;

    /**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOX_INDICATOR__PARAMETERS = COMPOSITE_INDICATOR__PARAMETERS;

    /**
     * The feature id for the '<em><b>Analyzed Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOX_INDICATOR__ANALYZED_ELEMENT = COMPOSITE_INDICATOR__ANALYZED_ELEMENT;

    /**
     * The feature id for the '<em><b>Indicators</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOX_INDICATOR__INDICATORS = COMPOSITE_INDICATOR__INDICATORS;

    /**
     * The feature id for the '<em><b>Min</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOX_INDICATOR__MIN = COMPOSITE_INDICATOR_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Max</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOX_INDICATOR__MAX = COMPOSITE_INDICATOR_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>First Quartile</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOX_INDICATOR__FIRST_QUARTILE = COMPOSITE_INDICATOR_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Third Quartile</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOX_INDICATOR__THIRD_QUARTILE = COMPOSITE_INDICATOR_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>IQR</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOX_INDICATOR__IQR = COMPOSITE_INDICATOR_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Box Indicator</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOX_INDICATOR_FEATURE_COUNT = COMPOSITE_INDICATOR_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '{@link org.talend.dataquality.indicators.impl.IndicatorTypeImpl <em>Indicator Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.dataquality.indicators.impl.IndicatorTypeImpl
     * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getIndicatorType()
     * @generated
     */
    int INDICATOR_TYPE = 7;


    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__NAME = CorePackage.CLASSIFIER__NAME;

    /**
     * The feature id for the '<em><b>Visibility</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__VISIBILITY = CorePackage.CLASSIFIER__VISIBILITY;

    /**
     * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__CLIENT_DEPENDENCY = CorePackage.CLASSIFIER__CLIENT_DEPENDENCY;

    /**
     * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__SUPPLIER_DEPENDENCY = CorePackage.CLASSIFIER__SUPPLIER_DEPENDENCY;

    /**
     * The feature id for the '<em><b>Constraint</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__CONSTRAINT = CorePackage.CLASSIFIER__CONSTRAINT;

    /**
     * The feature id for the '<em><b>Namespace</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__NAMESPACE = CorePackage.CLASSIFIER__NAMESPACE;

    /**
     * The feature id for the '<em><b>Importer</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__IMPORTER = CorePackage.CLASSIFIER__IMPORTER;

    /**
     * The feature id for the '<em><b>Stereotype</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__STEREOTYPE = CorePackage.CLASSIFIER__STEREOTYPE;

    /**
     * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__TAGGED_VALUE = CorePackage.CLASSIFIER__TAGGED_VALUE;

    /**
     * The feature id for the '<em><b>Document</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__DOCUMENT = CorePackage.CLASSIFIER__DOCUMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__DESCRIPTION = CorePackage.CLASSIFIER__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__RESPONSIBLE_PARTY = CorePackage.CLASSIFIER__RESPONSIBLE_PARTY;

    /**
     * The feature id for the '<em><b>Element Node</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__ELEMENT_NODE = CorePackage.CLASSIFIER__ELEMENT_NODE;

    /**
     * The feature id for the '<em><b>Set</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__SET = CorePackage.CLASSIFIER__SET;

    /**
     * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__RENDERED_OBJECT = CorePackage.CLASSIFIER__RENDERED_OBJECT;

    /**
     * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__VOCABULARY_ELEMENT = CorePackage.CLASSIFIER__VOCABULARY_ELEMENT;

    /**
     * The feature id for the '<em><b>Measurement</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__MEASUREMENT = CorePackage.CLASSIFIER__MEASUREMENT;

    /**
     * The feature id for the '<em><b>Change Request</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__CHANGE_REQUEST = CorePackage.CLASSIFIER__CHANGE_REQUEST;

    /**
     * The feature id for the '<em><b>Dasdl Property</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__DASDL_PROPERTY = CorePackage.CLASSIFIER__DASDL_PROPERTY;

    /**
     * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__OWNED_ELEMENT = CorePackage.CLASSIFIER__OWNED_ELEMENT;

    /**
     * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__IS_ABSTRACT = CorePackage.CLASSIFIER__IS_ABSTRACT;

    /**
     * The feature id for the '<em><b>Feature</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__FEATURE = CorePackage.CLASSIFIER__FEATURE;

    /**
     * The feature id for the '<em><b>Structural Feature</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__STRUCTURAL_FEATURE = CorePackage.CLASSIFIER__STRUCTURAL_FEATURE;

    /**
     * The feature id for the '<em><b>Parameter</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__PARAMETER = CorePackage.CLASSIFIER__PARAMETER;

    /**
     * The feature id for the '<em><b>Generalization</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__GENERALIZATION = CorePackage.CLASSIFIER__GENERALIZATION;

    /**
     * The feature id for the '<em><b>Specialization</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__SPECIALIZATION = CorePackage.CLASSIFIER__SPECIALIZATION;

    /**
     * The feature id for the '<em><b>Instance</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__INSTANCE = CorePackage.CLASSIFIER__INSTANCE;

    /**
     * The feature id for the '<em><b>Alias</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__ALIAS = CorePackage.CLASSIFIER__ALIAS;

    /**
     * The feature id for the '<em><b>Expression Node</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__EXPRESSION_NODE = CorePackage.CLASSIFIER__EXPRESSION_NODE;

    /**
     * The feature id for the '<em><b>Mapping From</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__MAPPING_FROM = CorePackage.CLASSIFIER__MAPPING_FROM;

    /**
     * The feature id for the '<em><b>Mapping To</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__MAPPING_TO = CorePackage.CLASSIFIER__MAPPING_TO;

    /**
     * The feature id for the '<em><b>Classifier Map</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__CLASSIFIER_MAP = CorePackage.CLASSIFIER__CLASSIFIER_MAP;

    /**
     * The feature id for the '<em><b>Cf Map</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__CF_MAP = CorePackage.CLASSIFIER__CF_MAP;

    /**
     * The feature id for the '<em><b>Domain</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__DOMAIN = CorePackage.CLASSIFIER__DOMAIN;

    /**
     * The feature id for the '<em><b>Simple Dimension</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE__SIMPLE_DIMENSION = CorePackage.CLASSIFIER__SIMPLE_DIMENSION;

    /**
     * The number of structural features of the '<em>Indicator Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_TYPE_FEATURE_COUNT = CorePackage.CLASSIFIER_FEATURE_COUNT + 0;


    /**
     * The meta object id for the '{@link org.talend.dataquality.indicators.impl.IntegerSumIndicatorImpl <em>Integer Sum Indicator</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.dataquality.indicators.impl.IntegerSumIndicatorImpl
     * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getIntegerSumIndicator()
     * @generated
     */
    int INTEGER_SUM_INDICATOR = 8;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTEGER_SUM_INDICATOR__VALUE = SUM_INDICATOR__VALUE;

    /**
     * The feature id for the '<em><b>Indicator Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTEGER_SUM_INDICATOR__INDICATOR_TYPE = SUM_INDICATOR__INDICATOR_TYPE;

    /**
     * The feature id for the '<em><b>Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTEGER_SUM_INDICATOR__COUNT = SUM_INDICATOR__COUNT;

    /**
     * The feature id for the '<em><b>Null Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTEGER_SUM_INDICATOR__NULL_COUNT = SUM_INDICATOR__NULL_COUNT;

    /**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTEGER_SUM_INDICATOR__PARAMETERS = SUM_INDICATOR__PARAMETERS;

    /**
     * The feature id for the '<em><b>Analyzed Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTEGER_SUM_INDICATOR__ANALYZED_ELEMENT = SUM_INDICATOR__ANALYZED_ELEMENT;

    /**
     * The feature id for the '<em><b>Sum</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTEGER_SUM_INDICATOR__SUM = SUM_INDICATOR_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Integer Sum Indicator</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTEGER_SUM_INDICATOR_FEATURE_COUNT = SUM_INDICATOR_FEATURE_COUNT + 1;


    /**
     * The meta object id for the '{@link org.talend.dataquality.indicators.impl.DoubleSumIndicatorImpl <em>Double Sum Indicator</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.dataquality.indicators.impl.DoubleSumIndicatorImpl
     * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getDoubleSumIndicator()
     * @generated
     */
    int DOUBLE_SUM_INDICATOR = 9;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOUBLE_SUM_INDICATOR__VALUE = SUM_INDICATOR__VALUE;

    /**
     * The feature id for the '<em><b>Indicator Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOUBLE_SUM_INDICATOR__INDICATOR_TYPE = SUM_INDICATOR__INDICATOR_TYPE;

    /**
     * The feature id for the '<em><b>Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOUBLE_SUM_INDICATOR__COUNT = SUM_INDICATOR__COUNT;

    /**
     * The feature id for the '<em><b>Null Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOUBLE_SUM_INDICATOR__NULL_COUNT = SUM_INDICATOR__NULL_COUNT;

    /**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOUBLE_SUM_INDICATOR__PARAMETERS = SUM_INDICATOR__PARAMETERS;

    /**
     * The feature id for the '<em><b>Analyzed Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOUBLE_SUM_INDICATOR__ANALYZED_ELEMENT = SUM_INDICATOR__ANALYZED_ELEMENT;

    /**
     * The feature id for the '<em><b>Sum</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOUBLE_SUM_INDICATOR__SUM = SUM_INDICATOR_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Double Sum Indicator</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOUBLE_SUM_INDICATOR_FEATURE_COUNT = SUM_INDICATOR_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.talend.dataquality.indicators.impl.BigDecimalIndicatorImpl <em>Big Decimal Indicator</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.dataquality.indicators.impl.BigDecimalIndicatorImpl
     * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getBigDecimalIndicator()
     * @generated
     */
    int BIG_DECIMAL_INDICATOR = 10;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BIG_DECIMAL_INDICATOR__VALUE = SUM_INDICATOR__VALUE;

    /**
     * The feature id for the '<em><b>Indicator Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BIG_DECIMAL_INDICATOR__INDICATOR_TYPE = SUM_INDICATOR__INDICATOR_TYPE;

    /**
     * The feature id for the '<em><b>Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BIG_DECIMAL_INDICATOR__COUNT = SUM_INDICATOR__COUNT;

    /**
     * The feature id for the '<em><b>Null Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BIG_DECIMAL_INDICATOR__NULL_COUNT = SUM_INDICATOR__NULL_COUNT;

    /**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BIG_DECIMAL_INDICATOR__PARAMETERS = SUM_INDICATOR__PARAMETERS;

    /**
     * The feature id for the '<em><b>Analyzed Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BIG_DECIMAL_INDICATOR__ANALYZED_ELEMENT = SUM_INDICATOR__ANALYZED_ELEMENT;

    /**
     * The feature id for the '<em><b>Sum</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BIG_DECIMAL_INDICATOR__SUM = SUM_INDICATOR_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Big Decimal Indicator</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BIG_DECIMAL_INDICATOR_FEATURE_COUNT = SUM_INDICATOR_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.talend.dataquality.indicators.impl.FrequencyIndicatorImpl <em>Frequency Indicator</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.dataquality.indicators.impl.FrequencyIndicatorImpl
     * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getFrequencyIndicator()
     * @generated
     */
    int FREQUENCY_INDICATOR = 11;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FREQUENCY_INDICATOR__VALUE = INDICATOR__VALUE;

    /**
     * The feature id for the '<em><b>Indicator Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FREQUENCY_INDICATOR__INDICATOR_TYPE = INDICATOR__INDICATOR_TYPE;

    /**
     * The feature id for the '<em><b>Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FREQUENCY_INDICATOR__COUNT = INDICATOR__COUNT;

    /**
     * The feature id for the '<em><b>Null Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FREQUENCY_INDICATOR__NULL_COUNT = INDICATOR__NULL_COUNT;

    /**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FREQUENCY_INDICATOR__PARAMETERS = INDICATOR__PARAMETERS;

    /**
     * The feature id for the '<em><b>Analyzed Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FREQUENCY_INDICATOR__ANALYZED_ELEMENT = INDICATOR__ANALYZED_ELEMENT;

    /**
     * The feature id for the '<em><b>Mode</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FREQUENCY_INDICATOR__MODE = INDICATOR_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Unique Values</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FREQUENCY_INDICATOR__UNIQUE_VALUES = INDICATOR_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Distinct Value Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FREQUENCY_INDICATOR__DISTINCT_VALUE_COUNT = INDICATOR_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Unique Value Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FREQUENCY_INDICATOR__UNIQUE_VALUE_COUNT = INDICATOR_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Dupplicate Value Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FREQUENCY_INDICATOR__DUPPLICATE_VALUE_COUNT = INDICATOR_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Value To Freq</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FREQUENCY_INDICATOR__VALUE_TO_FREQ = INDICATOR_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Frequency Indicator</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FREQUENCY_INDICATOR_FEATURE_COUNT = INDICATOR_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '{@link org.talend.dataquality.indicators.impl.IntegerMeanIndicatorImpl <em>Integer Mean Indicator</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.dataquality.indicators.impl.IntegerMeanIndicatorImpl
     * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getIntegerMeanIndicator()
     * @generated
     */
    int INTEGER_MEAN_INDICATOR = 12;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTEGER_MEAN_INDICATOR__VALUE = INTEGER_SUM_INDICATOR__VALUE;

    /**
     * The feature id for the '<em><b>Indicator Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTEGER_MEAN_INDICATOR__INDICATOR_TYPE = INTEGER_SUM_INDICATOR__INDICATOR_TYPE;

    /**
     * The feature id for the '<em><b>Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTEGER_MEAN_INDICATOR__COUNT = INTEGER_SUM_INDICATOR__COUNT;

    /**
     * The feature id for the '<em><b>Null Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTEGER_MEAN_INDICATOR__NULL_COUNT = INTEGER_SUM_INDICATOR__NULL_COUNT;

    /**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTEGER_MEAN_INDICATOR__PARAMETERS = INTEGER_SUM_INDICATOR__PARAMETERS;

    /**
     * The feature id for the '<em><b>Analyzed Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTEGER_MEAN_INDICATOR__ANALYZED_ELEMENT = INTEGER_SUM_INDICATOR__ANALYZED_ELEMENT;

    /**
     * The feature id for the '<em><b>Sum</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTEGER_MEAN_INDICATOR__SUM = INTEGER_SUM_INDICATOR__SUM;

    /**
     * The number of structural features of the '<em>Integer Mean Indicator</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTEGER_MEAN_INDICATOR_FEATURE_COUNT = INTEGER_SUM_INDICATOR_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.talend.dataquality.indicators.impl.DoubleMeanIndicatorImpl <em>Double Mean Indicator</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.dataquality.indicators.impl.DoubleMeanIndicatorImpl
     * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getDoubleMeanIndicator()
     * @generated
     */
    int DOUBLE_MEAN_INDICATOR = 13;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOUBLE_MEAN_INDICATOR__VALUE = DOUBLE_SUM_INDICATOR__VALUE;

    /**
     * The feature id for the '<em><b>Indicator Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOUBLE_MEAN_INDICATOR__INDICATOR_TYPE = DOUBLE_SUM_INDICATOR__INDICATOR_TYPE;

    /**
     * The feature id for the '<em><b>Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOUBLE_MEAN_INDICATOR__COUNT = DOUBLE_SUM_INDICATOR__COUNT;

    /**
     * The feature id for the '<em><b>Null Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOUBLE_MEAN_INDICATOR__NULL_COUNT = DOUBLE_SUM_INDICATOR__NULL_COUNT;

    /**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOUBLE_MEAN_INDICATOR__PARAMETERS = DOUBLE_SUM_INDICATOR__PARAMETERS;

    /**
     * The feature id for the '<em><b>Analyzed Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOUBLE_MEAN_INDICATOR__ANALYZED_ELEMENT = DOUBLE_SUM_INDICATOR__ANALYZED_ELEMENT;

    /**
     * The feature id for the '<em><b>Sum</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOUBLE_MEAN_INDICATOR__SUM = DOUBLE_SUM_INDICATOR__SUM;

    /**
     * The number of structural features of the '<em>Double Mean Indicator</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOUBLE_MEAN_INDICATOR_FEATURE_COUNT = DOUBLE_SUM_INDICATOR_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.talend.dataquality.indicators.impl.BigDecimalMeanIndicatorImpl <em>Big Decimal Mean Indicator</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.dataquality.indicators.impl.BigDecimalMeanIndicatorImpl
     * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getBigDecimalMeanIndicator()
     * @generated
     */
    int BIG_DECIMAL_MEAN_INDICATOR = 14;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BIG_DECIMAL_MEAN_INDICATOR__VALUE = BIG_DECIMAL_INDICATOR__VALUE;

    /**
     * The feature id for the '<em><b>Indicator Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BIG_DECIMAL_MEAN_INDICATOR__INDICATOR_TYPE = BIG_DECIMAL_INDICATOR__INDICATOR_TYPE;

    /**
     * The feature id for the '<em><b>Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BIG_DECIMAL_MEAN_INDICATOR__COUNT = BIG_DECIMAL_INDICATOR__COUNT;

    /**
     * The feature id for the '<em><b>Null Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BIG_DECIMAL_MEAN_INDICATOR__NULL_COUNT = BIG_DECIMAL_INDICATOR__NULL_COUNT;

    /**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BIG_DECIMAL_MEAN_INDICATOR__PARAMETERS = BIG_DECIMAL_INDICATOR__PARAMETERS;

    /**
     * The feature id for the '<em><b>Analyzed Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BIG_DECIMAL_MEAN_INDICATOR__ANALYZED_ELEMENT = BIG_DECIMAL_INDICATOR__ANALYZED_ELEMENT;

    /**
     * The feature id for the '<em><b>Sum</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BIG_DECIMAL_MEAN_INDICATOR__SUM = BIG_DECIMAL_INDICATOR__SUM;

    /**
     * The number of structural features of the '<em>Big Decimal Mean Indicator</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BIG_DECIMAL_MEAN_INDICATOR_FEATURE_COUNT = BIG_DECIMAL_INDICATOR_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.talend.dataquality.indicators.impl.BlankCountIndicatorImpl <em>Blank Count Indicator</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.dataquality.indicators.impl.BlankCountIndicatorImpl
     * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getBlankCountIndicator()
     * @generated
     */
    int BLANK_COUNT_INDICATOR = 15;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BLANK_COUNT_INDICATOR__VALUE = INDICATOR__VALUE;

    /**
     * The feature id for the '<em><b>Indicator Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BLANK_COUNT_INDICATOR__INDICATOR_TYPE = INDICATOR__INDICATOR_TYPE;

    /**
     * The feature id for the '<em><b>Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BLANK_COUNT_INDICATOR__COUNT = INDICATOR__COUNT;

    /**
     * The feature id for the '<em><b>Null Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BLANK_COUNT_INDICATOR__NULL_COUNT = INDICATOR__NULL_COUNT;

    /**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BLANK_COUNT_INDICATOR__PARAMETERS = INDICATOR__PARAMETERS;

    /**
     * The feature id for the '<em><b>Analyzed Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BLANK_COUNT_INDICATOR__ANALYZED_ELEMENT = INDICATOR__ANALYZED_ELEMENT;

    /**
     * The feature id for the '<em><b>Blank Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BLANK_COUNT_INDICATOR__BLANK_COUNT = INDICATOR_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Blank Count Indicator</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BLANK_COUNT_INDICATOR_FEATURE_COUNT = INDICATOR_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.talend.dataquality.indicators.impl.IndicatorParametersImpl <em>Indicator Parameters</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.dataquality.indicators.impl.IndicatorParametersImpl
     * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getIndicatorParameters()
     * @generated
     */
    int INDICATOR_PARAMETERS = 16;

    /**
     * The feature id for the '<em><b>Indicator Valid Domain</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_PARAMETERS__INDICATOR_VALID_DOMAIN = 0;

    /**
     * The feature id for the '<em><b>Data Valid Domain</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_PARAMETERS__DATA_VALID_DOMAIN = 1;

    /**
     * The number of structural features of the '<em>Indicator Parameters</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDICATOR_PARAMETERS_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link org.talend.dataquality.indicators.impl.MedianIndicatorImpl <em>Median Indicator</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.dataquality.indicators.impl.MedianIndicatorImpl
     * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getMedianIndicator()
     * @generated
     */
    int MEDIAN_INDICATOR = 17;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MEDIAN_INDICATOR__VALUE = INDICATOR__VALUE;

    /**
     * The feature id for the '<em><b>Indicator Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MEDIAN_INDICATOR__INDICATOR_TYPE = INDICATOR__INDICATOR_TYPE;

    /**
     * The feature id for the '<em><b>Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MEDIAN_INDICATOR__COUNT = INDICATOR__COUNT;

    /**
     * The feature id for the '<em><b>Null Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MEDIAN_INDICATOR__NULL_COUNT = INDICATOR__NULL_COUNT;

    /**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MEDIAN_INDICATOR__PARAMETERS = INDICATOR__PARAMETERS;

    /**
     * The feature id for the '<em><b>Analyzed Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MEDIAN_INDICATOR__ANALYZED_ELEMENT = INDICATOR__ANALYZED_ELEMENT;

    /**
     * The feature id for the '<em><b>Median</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MEDIAN_INDICATOR__MEDIAN = INDICATOR_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Frequence Table</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MEDIAN_INDICATOR__FREQUENCE_TABLE = INDICATOR_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Median Indicator</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MEDIAN_INDICATOR_FEATURE_COUNT = INDICATOR_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '<em>Java Set</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.util.Set
     * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getJavaSet()
     * @generated
     */
    int JAVA_SET = 18;

    /**
     * The meta object id for the '<em>Java Hash Map</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.util.HashMap
     * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getJavaHashMap()
     * @generated
     */
    int JAVA_HASH_MAP = 19;

    /**
     * The meta object id for the '<em>Java Tree Map</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.util.TreeMap
     * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getJavaTreeMap()
     * @generated
     */
    int JAVA_TREE_MAP = 20;

    /**
     * Returns the meta object for class '{@link org.talend.dataquality.indicators.Indicator <em>Indicator</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Indicator</em>'.
     * @see org.talend.dataquality.indicators.Indicator
     * @generated
     */
    EClass getIndicator();

    /**
     * Returns the meta object for the reference '{@link org.talend.dataquality.indicators.Indicator#getIndicatorType <em>Indicator Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Indicator Type</em>'.
     * @see org.talend.dataquality.indicators.Indicator#getIndicatorType()
     * @see #getIndicator()
     * @generated
     */
    EReference getIndicator_IndicatorType();

    /**
     * Returns the meta object for the attribute '{@link org.talend.dataquality.indicators.Indicator#getCount <em>Count</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Count</em>'.
     * @see org.talend.dataquality.indicators.Indicator#getCount()
     * @see #getIndicator()
     * @generated
     */
    EAttribute getIndicator_Count();

    /**
     * Returns the meta object for the attribute '{@link org.talend.dataquality.indicators.Indicator#getNullCount <em>Null Count</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Null Count</em>'.
     * @see org.talend.dataquality.indicators.Indicator#getNullCount()
     * @see #getIndicator()
     * @generated
     */
    EAttribute getIndicator_NullCount();

    /**
     * Returns the meta object for the containment reference '{@link org.talend.dataquality.indicators.Indicator#getParameters <em>Parameters</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Parameters</em>'.
     * @see org.talend.dataquality.indicators.Indicator#getParameters()
     * @see #getIndicator()
     * @generated
     */
    EReference getIndicator_Parameters();

    /**
     * Returns the meta object for the reference '{@link org.talend.dataquality.indicators.Indicator#getAnalyzedElement <em>Analyzed Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Analyzed Element</em>'.
     * @see org.talend.dataquality.indicators.Indicator#getAnalyzedElement()
     * @see #getIndicator()
     * @generated
     */
    EReference getIndicator_AnalyzedElement();

    /**
     * Returns the meta object for the reference '{@link org.talend.dataquality.indicators.Indicator#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Value</em>'.
     * @see org.talend.dataquality.indicators.Indicator#getValue()
     * @see #getIndicator()
     * @generated
     */
    EReference getIndicator_Value();

    /**
     * Returns the meta object for class '{@link org.talend.dataquality.indicators.RowCountIndicator <em>Row Count Indicator</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Row Count Indicator</em>'.
     * @see org.talend.dataquality.indicators.RowCountIndicator
     * @generated
     */
    EClass getRowCountIndicator();

    /**
     * Returns the meta object for class '{@link org.talend.dataquality.indicators.MeanIndicator <em>Mean Indicator</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Mean Indicator</em>'.
     * @see org.talend.dataquality.indicators.MeanIndicator
     * @generated
     */
    EClass getMeanIndicator();

    /**
     * Returns the meta object for class '{@link org.talend.dataquality.indicators.SumIndicator <em>Sum Indicator</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Sum Indicator</em>'.
     * @see org.talend.dataquality.indicators.SumIndicator
     * @generated
     */
    EClass getSumIndicator();

    /**
     * Returns the meta object for class '{@link org.talend.dataquality.indicators.CompositeIndicator <em>Composite Indicator</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Composite Indicator</em>'.
     * @see org.talend.dataquality.indicators.CompositeIndicator
     * @generated
     */
    EClass getCompositeIndicator();

    /**
     * Returns the meta object for the reference list '{@link org.talend.dataquality.indicators.CompositeIndicator#getIndicators <em>Indicators</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Indicators</em>'.
     * @see org.talend.dataquality.indicators.CompositeIndicator#getIndicators()
     * @see #getCompositeIndicator()
     * @generated
     */
    EReference getCompositeIndicator_Indicators();

    /**
     * Returns the meta object for class '{@link org.talend.dataquality.indicators.RangeIndicator <em>Range Indicator</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Range Indicator</em>'.
     * @see org.talend.dataquality.indicators.RangeIndicator
     * @generated
     */
    EClass getRangeIndicator();

    /**
     * Returns the meta object for the reference '{@link org.talend.dataquality.indicators.RangeIndicator#getLowerValue <em>Lower Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Lower Value</em>'.
     * @see org.talend.dataquality.indicators.RangeIndicator#getLowerValue()
     * @see #getRangeIndicator()
     * @generated
     */
    EReference getRangeIndicator_LowerValue();

    /**
     * Returns the meta object for the reference '{@link org.talend.dataquality.indicators.RangeIndicator#getUpperValue <em>Upper Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Upper Value</em>'.
     * @see org.talend.dataquality.indicators.RangeIndicator#getUpperValue()
     * @see #getRangeIndicator()
     * @generated
     */
    EReference getRangeIndicator_UpperValue();

    /**
     * Returns the meta object for the reference '{@link org.talend.dataquality.indicators.RangeIndicator#getRange <em>Range</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Range</em>'.
     * @see org.talend.dataquality.indicators.RangeIndicator#getRange()
     * @see #getRangeIndicator()
     * @generated
     */
    EReference getRangeIndicator_Range();

    /**
     * Returns the meta object for class '{@link org.talend.dataquality.indicators.BoxIndicator <em>Box Indicator</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Box Indicator</em>'.
     * @see org.talend.dataquality.indicators.BoxIndicator
     * @generated
     */
    EClass getBoxIndicator();

    /**
     * Returns the meta object for the reference '{@link org.talend.dataquality.indicators.BoxIndicator#getMin <em>Min</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Min</em>'.
     * @see org.talend.dataquality.indicators.BoxIndicator#getMin()
     * @see #getBoxIndicator()
     * @generated
     */
    EReference getBoxIndicator_Min();

    /**
     * Returns the meta object for the reference '{@link org.talend.dataquality.indicators.BoxIndicator#getMax <em>Max</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Max</em>'.
     * @see org.talend.dataquality.indicators.BoxIndicator#getMax()
     * @see #getBoxIndicator()
     * @generated
     */
    EReference getBoxIndicator_Max();

    /**
     * Returns the meta object for the reference '{@link org.talend.dataquality.indicators.BoxIndicator#getFirstQuartile <em>First Quartile</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>First Quartile</em>'.
     * @see org.talend.dataquality.indicators.BoxIndicator#getFirstQuartile()
     * @see #getBoxIndicator()
     * @generated
     */
    EReference getBoxIndicator_FirstQuartile();

    /**
     * Returns the meta object for the reference '{@link org.talend.dataquality.indicators.BoxIndicator#getThirdQuartile <em>Third Quartile</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Third Quartile</em>'.
     * @see org.talend.dataquality.indicators.BoxIndicator#getThirdQuartile()
     * @see #getBoxIndicator()
     * @generated
     */
    EReference getBoxIndicator_ThirdQuartile();

    /**
     * Returns the meta object for the reference '{@link org.talend.dataquality.indicators.BoxIndicator#getIQR <em>IQR</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>IQR</em>'.
     * @see org.talend.dataquality.indicators.BoxIndicator#getIQR()
     * @see #getBoxIndicator()
     * @generated
     */
    EReference getBoxIndicator_IQR();

    /**
     * Returns the meta object for class '{@link org.talend.dataquality.indicators.IndicatorType <em>Indicator Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Indicator Type</em>'.
     * @see org.talend.dataquality.indicators.IndicatorType
     * @generated
     */
    EClass getIndicatorType();

    /**
     * Returns the meta object for class '{@link org.talend.dataquality.indicators.IntegerSumIndicator <em>Integer Sum Indicator</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Integer Sum Indicator</em>'.
     * @see org.talend.dataquality.indicators.IntegerSumIndicator
     * @generated
     */
    EClass getIntegerSumIndicator();

    /**
     * Returns the meta object for the attribute '{@link org.talend.dataquality.indicators.IntegerSumIndicator#getSum <em>Sum</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Sum</em>'.
     * @see org.talend.dataquality.indicators.IntegerSumIndicator#getSum()
     * @see #getIntegerSumIndicator()
     * @generated
     */
    EAttribute getIntegerSumIndicator_Sum();

    /**
     * Returns the meta object for class '{@link org.talend.dataquality.indicators.DoubleSumIndicator <em>Double Sum Indicator</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Double Sum Indicator</em>'.
     * @see org.talend.dataquality.indicators.DoubleSumIndicator
     * @generated
     */
    EClass getDoubleSumIndicator();

    /**
     * Returns the meta object for the attribute '{@link org.talend.dataquality.indicators.DoubleSumIndicator#getSum <em>Sum</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Sum</em>'.
     * @see org.talend.dataquality.indicators.DoubleSumIndicator#getSum()
     * @see #getDoubleSumIndicator()
     * @generated
     */
    EAttribute getDoubleSumIndicator_Sum();

    /**
     * Returns the meta object for class '{@link org.talend.dataquality.indicators.BigDecimalIndicator <em>Big Decimal Indicator</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Big Decimal Indicator</em>'.
     * @see org.talend.dataquality.indicators.BigDecimalIndicator
     * @generated
     */
    EClass getBigDecimalIndicator();

    /**
     * Returns the meta object for the attribute '{@link org.talend.dataquality.indicators.BigDecimalIndicator#getSum <em>Sum</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Sum</em>'.
     * @see org.talend.dataquality.indicators.BigDecimalIndicator#getSum()
     * @see #getBigDecimalIndicator()
     * @generated
     */
    EAttribute getBigDecimalIndicator_Sum();

    /**
     * Returns the meta object for class '{@link org.talend.dataquality.indicators.FrequencyIndicator <em>Frequency Indicator</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Frequency Indicator</em>'.
     * @see org.talend.dataquality.indicators.FrequencyIndicator
     * @generated
     */
    EClass getFrequencyIndicator();

    /**
     * Returns the meta object for the attribute '{@link org.talend.dataquality.indicators.FrequencyIndicator#getMode <em>Mode</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Mode</em>'.
     * @see org.talend.dataquality.indicators.FrequencyIndicator#getMode()
     * @see #getFrequencyIndicator()
     * @generated
     */
    EAttribute getFrequencyIndicator_Mode();

    /**
     * Returns the meta object for the attribute list '{@link org.talend.dataquality.indicators.FrequencyIndicator#getUniqueValues <em>Unique Values</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Unique Values</em>'.
     * @see org.talend.dataquality.indicators.FrequencyIndicator#getUniqueValues()
     * @see #getFrequencyIndicator()
     * @generated
     */
    EAttribute getFrequencyIndicator_UniqueValues();

    /**
     * Returns the meta object for the attribute '{@link org.talend.dataquality.indicators.FrequencyIndicator#getDistinctValueCount <em>Distinct Value Count</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Distinct Value Count</em>'.
     * @see org.talend.dataquality.indicators.FrequencyIndicator#getDistinctValueCount()
     * @see #getFrequencyIndicator()
     * @generated
     */
    EAttribute getFrequencyIndicator_DistinctValueCount();

    /**
     * Returns the meta object for the attribute '{@link org.talend.dataquality.indicators.FrequencyIndicator#getUniqueValueCount <em>Unique Value Count</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Unique Value Count</em>'.
     * @see org.talend.dataquality.indicators.FrequencyIndicator#getUniqueValueCount()
     * @see #getFrequencyIndicator()
     * @generated
     */
    EAttribute getFrequencyIndicator_UniqueValueCount();

    /**
     * Returns the meta object for the attribute '{@link org.talend.dataquality.indicators.FrequencyIndicator#getDupplicateValueCount <em>Dupplicate Value Count</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Dupplicate Value Count</em>'.
     * @see org.talend.dataquality.indicators.FrequencyIndicator#getDupplicateValueCount()
     * @see #getFrequencyIndicator()
     * @generated
     */
    EAttribute getFrequencyIndicator_DupplicateValueCount();

    /**
     * Returns the meta object for the attribute '{@link org.talend.dataquality.indicators.FrequencyIndicator#getValueToFreq <em>Value To Freq</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value To Freq</em>'.
     * @see org.talend.dataquality.indicators.FrequencyIndicator#getValueToFreq()
     * @see #getFrequencyIndicator()
     * @generated
     */
    EAttribute getFrequencyIndicator_ValueToFreq();

    /**
     * Returns the meta object for class '{@link org.talend.dataquality.indicators.IntegerMeanIndicator <em>Integer Mean Indicator</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Integer Mean Indicator</em>'.
     * @see org.talend.dataquality.indicators.IntegerMeanIndicator
     * @generated
     */
    EClass getIntegerMeanIndicator();

    /**
     * Returns the meta object for class '{@link org.talend.dataquality.indicators.DoubleMeanIndicator <em>Double Mean Indicator</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Double Mean Indicator</em>'.
     * @see org.talend.dataquality.indicators.DoubleMeanIndicator
     * @generated
     */
    EClass getDoubleMeanIndicator();

    /**
     * Returns the meta object for class '{@link org.talend.dataquality.indicators.BigDecimalMeanIndicator <em>Big Decimal Mean Indicator</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Big Decimal Mean Indicator</em>'.
     * @see org.talend.dataquality.indicators.BigDecimalMeanIndicator
     * @generated
     */
    EClass getBigDecimalMeanIndicator();

    /**
     * Returns the meta object for class '{@link org.talend.dataquality.indicators.BlankCountIndicator <em>Blank Count Indicator</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Blank Count Indicator</em>'.
     * @see org.talend.dataquality.indicators.BlankCountIndicator
     * @generated
     */
    EClass getBlankCountIndicator();

    /**
     * Returns the meta object for the attribute '{@link org.talend.dataquality.indicators.BlankCountIndicator#getBlankCount <em>Blank Count</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Blank Count</em>'.
     * @see org.talend.dataquality.indicators.BlankCountIndicator#getBlankCount()
     * @see #getBlankCountIndicator()
     * @generated
     */
    EAttribute getBlankCountIndicator_BlankCount();

    /**
     * Returns the meta object for class '{@link org.talend.dataquality.indicators.IndicatorParameters <em>Indicator Parameters</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Indicator Parameters</em>'.
     * @see org.talend.dataquality.indicators.IndicatorParameters
     * @generated
     */
    EClass getIndicatorParameters();

    /**
     * Returns the meta object for the reference '{@link org.talend.dataquality.indicators.IndicatorParameters#getIndicatorValidDomain <em>Indicator Valid Domain</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Indicator Valid Domain</em>'.
     * @see org.talend.dataquality.indicators.IndicatorParameters#getIndicatorValidDomain()
     * @see #getIndicatorParameters()
     * @generated
     */
    EReference getIndicatorParameters_IndicatorValidDomain();

    /**
     * Returns the meta object for the reference '{@link org.talend.dataquality.indicators.IndicatorParameters#getDataValidDomain <em>Data Valid Domain</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Data Valid Domain</em>'.
     * @see org.talend.dataquality.indicators.IndicatorParameters#getDataValidDomain()
     * @see #getIndicatorParameters()
     * @generated
     */
    EReference getIndicatorParameters_DataValidDomain();

    /**
     * Returns the meta object for class '{@link org.talend.dataquality.indicators.MedianIndicator <em>Median Indicator</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Median Indicator</em>'.
     * @see org.talend.dataquality.indicators.MedianIndicator
     * @generated
     */
    EClass getMedianIndicator();

    /**
     * Returns the meta object for the attribute '{@link org.talend.dataquality.indicators.MedianIndicator#getMedian <em>Median</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Median</em>'.
     * @see org.talend.dataquality.indicators.MedianIndicator#getMedian()
     * @see #getMedianIndicator()
     * @generated
     */
    EAttribute getMedianIndicator_Median();

    /**
     * Returns the meta object for the attribute '{@link org.talend.dataquality.indicators.MedianIndicator#getFrequenceTable <em>Frequence Table</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Frequence Table</em>'.
     * @see org.talend.dataquality.indicators.MedianIndicator#getFrequenceTable()
     * @see #getMedianIndicator()
     * @generated
     */
    EAttribute getMedianIndicator_FrequenceTable();

    /**
     * Returns the meta object for data type '{@link java.util.Set <em>Java Set</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Java Set</em>'.
     * @see java.util.Set
     * @model instanceClass="java.util.Set<java.lang.Object>"
     * @generated
     */
    EDataType getJavaSet();

    /**
     * Returns the meta object for data type '{@link java.util.HashMap <em>Java Hash Map</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Java Hash Map</em>'.
     * @see java.util.HashMap
     * @model instanceClass="java.util.HashMap<java.lang.Object, java.lang.Long>"
     * @generated
     */
    EDataType getJavaHashMap();

    /**
     * Returns the meta object for data type '{@link java.util.TreeMap <em>Java Tree Map</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Java Tree Map</em>'.
     * @see java.util.TreeMap
     * @model instanceClass="java.util.TreeMap<java.lang.Object, java.lang.Long>"
     * @generated
     */
    EDataType getJavaTreeMap();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    IndicatorsFactory getIndicatorsFactory();

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
         * The meta object literal for the '{@link org.talend.dataquality.indicators.impl.IndicatorImpl <em>Indicator</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.dataquality.indicators.impl.IndicatorImpl
         * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getIndicator()
         * @generated
         */
        EClass INDICATOR = eINSTANCE.getIndicator();

        /**
         * The meta object literal for the '<em><b>Indicator Type</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference INDICATOR__INDICATOR_TYPE = eINSTANCE.getIndicator_IndicatorType();

        /**
         * The meta object literal for the '<em><b>Count</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute INDICATOR__COUNT = eINSTANCE.getIndicator_Count();

        /**
         * The meta object literal for the '<em><b>Null Count</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute INDICATOR__NULL_COUNT = eINSTANCE.getIndicator_NullCount();

        /**
         * The meta object literal for the '<em><b>Parameters</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference INDICATOR__PARAMETERS = eINSTANCE.getIndicator_Parameters();

        /**
         * The meta object literal for the '<em><b>Analyzed Element</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference INDICATOR__ANALYZED_ELEMENT = eINSTANCE.getIndicator_AnalyzedElement();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference INDICATOR__VALUE = eINSTANCE.getIndicator_Value();

        /**
         * The meta object literal for the '{@link org.talend.dataquality.indicators.impl.RowCountIndicatorImpl <em>Row Count Indicator</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.dataquality.indicators.impl.RowCountIndicatorImpl
         * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getRowCountIndicator()
         * @generated
         */
        EClass ROW_COUNT_INDICATOR = eINSTANCE.getRowCountIndicator();

        /**
         * The meta object literal for the '{@link org.talend.dataquality.indicators.MeanIndicator <em>Mean Indicator</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.dataquality.indicators.MeanIndicator
         * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getMeanIndicator()
         * @generated
         */
        EClass MEAN_INDICATOR = eINSTANCE.getMeanIndicator();

        /**
         * The meta object literal for the '{@link org.talend.dataquality.indicators.impl.SumIndicatorImpl <em>Sum Indicator</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.dataquality.indicators.impl.SumIndicatorImpl
         * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getSumIndicator()
         * @generated
         */
        EClass SUM_INDICATOR = eINSTANCE.getSumIndicator();

        /**
         * The meta object literal for the '{@link org.talend.dataquality.indicators.impl.CompositeIndicatorImpl <em>Composite Indicator</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.dataquality.indicators.impl.CompositeIndicatorImpl
         * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getCompositeIndicator()
         * @generated
         */
        EClass COMPOSITE_INDICATOR = eINSTANCE.getCompositeIndicator();

        /**
         * The meta object literal for the '<em><b>Indicators</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference COMPOSITE_INDICATOR__INDICATORS = eINSTANCE.getCompositeIndicator_Indicators();

        /**
         * The meta object literal for the '{@link org.talend.dataquality.indicators.impl.RangeIndicatorImpl <em>Range Indicator</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.dataquality.indicators.impl.RangeIndicatorImpl
         * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getRangeIndicator()
         * @generated
         */
        EClass RANGE_INDICATOR = eINSTANCE.getRangeIndicator();

        /**
         * The meta object literal for the '<em><b>Lower Value</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference RANGE_INDICATOR__LOWER_VALUE = eINSTANCE.getRangeIndicator_LowerValue();

        /**
         * The meta object literal for the '<em><b>Upper Value</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference RANGE_INDICATOR__UPPER_VALUE = eINSTANCE.getRangeIndicator_UpperValue();

        /**
         * The meta object literal for the '<em><b>Range</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference RANGE_INDICATOR__RANGE = eINSTANCE.getRangeIndicator_Range();

        /**
         * The meta object literal for the '{@link org.talend.dataquality.indicators.impl.BoxIndicatorImpl <em>Box Indicator</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.dataquality.indicators.impl.BoxIndicatorImpl
         * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getBoxIndicator()
         * @generated
         */
        EClass BOX_INDICATOR = eINSTANCE.getBoxIndicator();

        /**
         * The meta object literal for the '<em><b>Min</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference BOX_INDICATOR__MIN = eINSTANCE.getBoxIndicator_Min();

        /**
         * The meta object literal for the '<em><b>Max</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference BOX_INDICATOR__MAX = eINSTANCE.getBoxIndicator_Max();

        /**
         * The meta object literal for the '<em><b>First Quartile</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference BOX_INDICATOR__FIRST_QUARTILE = eINSTANCE.getBoxIndicator_FirstQuartile();

        /**
         * The meta object literal for the '<em><b>Third Quartile</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference BOX_INDICATOR__THIRD_QUARTILE = eINSTANCE.getBoxIndicator_ThirdQuartile();

        /**
         * The meta object literal for the '<em><b>IQR</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference BOX_INDICATOR__IQR = eINSTANCE.getBoxIndicator_IQR();

        /**
         * The meta object literal for the '{@link org.talend.dataquality.indicators.impl.IndicatorTypeImpl <em>Indicator Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.dataquality.indicators.impl.IndicatorTypeImpl
         * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getIndicatorType()
         * @generated
         */
        EClass INDICATOR_TYPE = eINSTANCE.getIndicatorType();

        /**
         * The meta object literal for the '{@link org.talend.dataquality.indicators.impl.IntegerSumIndicatorImpl <em>Integer Sum Indicator</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.dataquality.indicators.impl.IntegerSumIndicatorImpl
         * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getIntegerSumIndicator()
         * @generated
         */
        EClass INTEGER_SUM_INDICATOR = eINSTANCE.getIntegerSumIndicator();

        /**
         * The meta object literal for the '<em><b>Sum</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute INTEGER_SUM_INDICATOR__SUM = eINSTANCE.getIntegerSumIndicator_Sum();

        /**
         * The meta object literal for the '{@link org.talend.dataquality.indicators.impl.DoubleSumIndicatorImpl <em>Double Sum Indicator</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.dataquality.indicators.impl.DoubleSumIndicatorImpl
         * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getDoubleSumIndicator()
         * @generated
         */
        EClass DOUBLE_SUM_INDICATOR = eINSTANCE.getDoubleSumIndicator();

        /**
         * The meta object literal for the '<em><b>Sum</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOUBLE_SUM_INDICATOR__SUM = eINSTANCE.getDoubleSumIndicator_Sum();

        /**
         * The meta object literal for the '{@link org.talend.dataquality.indicators.impl.BigDecimalIndicatorImpl <em>Big Decimal Indicator</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.dataquality.indicators.impl.BigDecimalIndicatorImpl
         * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getBigDecimalIndicator()
         * @generated
         */
        EClass BIG_DECIMAL_INDICATOR = eINSTANCE.getBigDecimalIndicator();

        /**
         * The meta object literal for the '<em><b>Sum</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute BIG_DECIMAL_INDICATOR__SUM = eINSTANCE.getBigDecimalIndicator_Sum();

        /**
         * The meta object literal for the '{@link org.talend.dataquality.indicators.impl.FrequencyIndicatorImpl <em>Frequency Indicator</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.dataquality.indicators.impl.FrequencyIndicatorImpl
         * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getFrequencyIndicator()
         * @generated
         */
        EClass FREQUENCY_INDICATOR = eINSTANCE.getFrequencyIndicator();

        /**
         * The meta object literal for the '<em><b>Mode</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FREQUENCY_INDICATOR__MODE = eINSTANCE.getFrequencyIndicator_Mode();

        /**
         * The meta object literal for the '<em><b>Unique Values</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FREQUENCY_INDICATOR__UNIQUE_VALUES = eINSTANCE.getFrequencyIndicator_UniqueValues();

        /**
         * The meta object literal for the '<em><b>Distinct Value Count</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FREQUENCY_INDICATOR__DISTINCT_VALUE_COUNT = eINSTANCE.getFrequencyIndicator_DistinctValueCount();

        /**
         * The meta object literal for the '<em><b>Unique Value Count</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FREQUENCY_INDICATOR__UNIQUE_VALUE_COUNT = eINSTANCE.getFrequencyIndicator_UniqueValueCount();

        /**
         * The meta object literal for the '<em><b>Dupplicate Value Count</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FREQUENCY_INDICATOR__DUPPLICATE_VALUE_COUNT = eINSTANCE.getFrequencyIndicator_DupplicateValueCount();

        /**
         * The meta object literal for the '<em><b>Value To Freq</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FREQUENCY_INDICATOR__VALUE_TO_FREQ = eINSTANCE.getFrequencyIndicator_ValueToFreq();

        /**
         * The meta object literal for the '{@link org.talend.dataquality.indicators.impl.IntegerMeanIndicatorImpl <em>Integer Mean Indicator</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.dataquality.indicators.impl.IntegerMeanIndicatorImpl
         * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getIntegerMeanIndicator()
         * @generated
         */
        EClass INTEGER_MEAN_INDICATOR = eINSTANCE.getIntegerMeanIndicator();

        /**
         * The meta object literal for the '{@link org.talend.dataquality.indicators.impl.DoubleMeanIndicatorImpl <em>Double Mean Indicator</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.dataquality.indicators.impl.DoubleMeanIndicatorImpl
         * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getDoubleMeanIndicator()
         * @generated
         */
        EClass DOUBLE_MEAN_INDICATOR = eINSTANCE.getDoubleMeanIndicator();

        /**
         * The meta object literal for the '{@link org.talend.dataquality.indicators.impl.BigDecimalMeanIndicatorImpl <em>Big Decimal Mean Indicator</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.dataquality.indicators.impl.BigDecimalMeanIndicatorImpl
         * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getBigDecimalMeanIndicator()
         * @generated
         */
        EClass BIG_DECIMAL_MEAN_INDICATOR = eINSTANCE.getBigDecimalMeanIndicator();

        /**
         * The meta object literal for the '{@link org.talend.dataquality.indicators.impl.BlankCountIndicatorImpl <em>Blank Count Indicator</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.dataquality.indicators.impl.BlankCountIndicatorImpl
         * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getBlankCountIndicator()
         * @generated
         */
        EClass BLANK_COUNT_INDICATOR = eINSTANCE.getBlankCountIndicator();

        /**
         * The meta object literal for the '<em><b>Blank Count</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute BLANK_COUNT_INDICATOR__BLANK_COUNT = eINSTANCE.getBlankCountIndicator_BlankCount();

        /**
         * The meta object literal for the '{@link org.talend.dataquality.indicators.impl.IndicatorParametersImpl <em>Indicator Parameters</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.dataquality.indicators.impl.IndicatorParametersImpl
         * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getIndicatorParameters()
         * @generated
         */
        EClass INDICATOR_PARAMETERS = eINSTANCE.getIndicatorParameters();

        /**
         * The meta object literal for the '<em><b>Indicator Valid Domain</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference INDICATOR_PARAMETERS__INDICATOR_VALID_DOMAIN = eINSTANCE.getIndicatorParameters_IndicatorValidDomain();

        /**
         * The meta object literal for the '<em><b>Data Valid Domain</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference INDICATOR_PARAMETERS__DATA_VALID_DOMAIN = eINSTANCE.getIndicatorParameters_DataValidDomain();

        /**
         * The meta object literal for the '{@link org.talend.dataquality.indicators.impl.MedianIndicatorImpl <em>Median Indicator</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.dataquality.indicators.impl.MedianIndicatorImpl
         * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getMedianIndicator()
         * @generated
         */
        EClass MEDIAN_INDICATOR = eINSTANCE.getMedianIndicator();

        /**
         * The meta object literal for the '<em><b>Median</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute MEDIAN_INDICATOR__MEDIAN = eINSTANCE.getMedianIndicator_Median();

        /**
         * The meta object literal for the '<em><b>Frequence Table</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute MEDIAN_INDICATOR__FREQUENCE_TABLE = eINSTANCE.getMedianIndicator_FrequenceTable();

        /**
         * The meta object literal for the '<em>Java Set</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see java.util.Set
         * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getJavaSet()
         * @generated
         */
        EDataType JAVA_SET = eINSTANCE.getJavaSet();

        /**
         * The meta object literal for the '<em>Java Hash Map</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see java.util.HashMap
         * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getJavaHashMap()
         * @generated
         */
        EDataType JAVA_HASH_MAP = eINSTANCE.getJavaHashMap();

        /**
         * The meta object literal for the '<em>Java Tree Map</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see java.util.TreeMap
         * @see org.talend.dataquality.indicators.impl.IndicatorsPackageImpl#getJavaTreeMap()
         * @generated
         */
        EDataType JAVA_TREE_MAP = eINSTANCE.getJavaTreeMap();

    }

} //IndicatorsPackage
