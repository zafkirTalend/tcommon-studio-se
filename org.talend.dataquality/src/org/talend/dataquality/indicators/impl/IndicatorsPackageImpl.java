/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.dataquality.indicators.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.talend.dataquality.analysis.AnalysisPackage;
import org.talend.dataquality.analysis.impl.AnalysisPackageImpl;
import org.talend.dataquality.domain.DomainPackage;
import org.talend.dataquality.domain.impl.DomainPackageImpl;
import org.talend.dataquality.domain.pattern.PatternPackage;
import org.talend.dataquality.domain.pattern.impl.PatternPackageImpl;
import org.talend.dataquality.expressions.impl.ExpressionsPackageImpl;
import org.talend.dataquality.indicators.BigDecimalIndicator;
import org.talend.dataquality.indicators.BigDecimalMeanIndicator;
import org.talend.dataquality.indicators.BlankCountIndicator;
import org.talend.dataquality.indicators.BoxIndicator;
import org.talend.dataquality.indicators.CompositeIndicator;
import org.talend.dataquality.indicators.DoubleMeanIndicator;
import org.talend.dataquality.indicators.DoubleSumIndicator;
import org.talend.dataquality.indicators.FrequencyIndicator;
import org.talend.dataquality.indicators.Indicator;
import org.talend.dataquality.indicators.IndicatorParameters;
import org.talend.dataquality.indicators.IndicatorType;
import org.talend.dataquality.indicators.IndicatorsFactory;
import org.talend.dataquality.indicators.IndicatorsPackage;
import org.talend.dataquality.indicators.IntegerMeanIndicator;
import org.talend.dataquality.indicators.IntegerSumIndicator;
import org.talend.dataquality.indicators.MeanIndicator;
import org.talend.dataquality.indicators.MedianIndicator;
import org.talend.dataquality.indicators.RangeIndicator;
import org.talend.dataquality.indicators.RowCountIndicator;
import org.talend.dataquality.indicators.SumIndicator;
import org.talend.dataquality.reports.ReportsPackage;
import org.talend.dataquality.reports.impl.ReportsPackageImpl;
import orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage;
import orgomg.cwm.analysis.datamining.DataminingPackage;
import orgomg.cwm.analysis.informationvisualization.InformationvisualizationPackage;
import orgomg.cwm.analysis.olap.OlapPackage;
import orgomg.cwm.analysis.transformation.TransformationPackage;
import orgomg.cwm.foundation.businessinformation.BusinessinformationPackage;
import orgomg.cwm.foundation.datatypes.DatatypesPackage;
import orgomg.cwm.foundation.expressions.ExpressionsPackage;
import orgomg.cwm.foundation.keysindexes.KeysindexesPackage;
import orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage;
import orgomg.cwm.foundation.typemapping.TypemappingPackage;
import orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage;
import orgomg.cwm.management.warehouseprocess.WarehouseprocessPackage;
import orgomg.cwm.objectmodel.behavioral.BehavioralPackage;
import orgomg.cwm.objectmodel.core.CorePackage;
import orgomg.cwm.objectmodel.instance.InstancePackage;
import orgomg.cwm.objectmodel.relationships.RelationshipsPackage;
import orgomg.cwm.resource.multidimensional.MultidimensionalPackage;
import orgomg.cwm.resource.record.RecordPackage;
import orgomg.cwm.resource.relational.RelationalPackage;
import orgomg.cwm.resource.xml.XmlPackage;
import orgomg.cwmmip.CwmmipPackage;
import orgomg.cwmx.analysis.informationreporting.InformationreportingPackage;
import orgomg.cwmx.analysis.informationset.InformationsetPackage;
import orgomg.cwmx.foundation.er.ErPackage;
import orgomg.cwmx.resource.coboldata.CoboldataPackage;
import orgomg.cwmx.resource.dmsii.DmsiiPackage;
import orgomg.cwmx.resource.essbase.EssbasePackage;
import orgomg.cwmx.resource.express.ExpressPackage;
import orgomg.cwmx.resource.imsdatabase.ImsdatabasePackage;
import orgomg.mof.model.ModelPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * @generated
 */
public class IndicatorsPackageImpl extends EPackageImpl implements IndicatorsPackage {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass indicatorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass rowCountIndicatorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass meanIndicatorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass sumIndicatorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass compositeIndicatorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass rangeIndicatorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass boxIndicatorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass indicatorTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass integerSumIndicatorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass doubleSumIndicatorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass bigDecimalIndicatorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass frequencyIndicatorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass integerMeanIndicatorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass doubleMeanIndicatorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass bigDecimalMeanIndicatorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass blankCountIndicatorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass indicatorParametersEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass medianIndicatorEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType javaSetEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType javaHashMapEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType javaTreeMapEDataType = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
     * performs initialization of the package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.talend.dataquality.indicators.IndicatorsPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private IndicatorsPackageImpl() {
        super(eNS_URI, IndicatorsFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this
     * model, and for any others upon which it depends.  Simple
     * dependencies are satisfied by calling this method on all
     * dependent packages before doing anything else.  This method drives
     * initialization for interdependent packages directly, in parallel
     * with this package, itself.
     * <p>Of this package and its interdependencies, all packages which
     * have not yet been registered by their URI values are first created
     * and registered.  The packages are then initialized in two steps:
     * meta-model objects for all of the packages are created before any
     * are initialized, since one package's meta-model objects may refer to
     * those of another.
     * <p>Invocation of this method will not affect any packages that have
     * already been initialized.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static IndicatorsPackage init() {
        if (isInited) return (IndicatorsPackage)EPackage.Registry.INSTANCE.getEPackage(IndicatorsPackage.eNS_URI);

        // Obtain or create and register package
        IndicatorsPackageImpl theIndicatorsPackage = (IndicatorsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof IndicatorsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new IndicatorsPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        CorePackage.eINSTANCE.eClass();
        BehavioralPackage.eINSTANCE.eClass();
        RelationshipsPackage.eINSTANCE.eClass();
        InstancePackage.eINSTANCE.eClass();
        BusinessinformationPackage.eINSTANCE.eClass();
        DatatypesPackage.eINSTANCE.eClass();
        ExpressionsPackage.eINSTANCE.eClass();
        KeysindexesPackage.eINSTANCE.eClass();
        SoftwaredeploymentPackage.eINSTANCE.eClass();
        TypemappingPackage.eINSTANCE.eClass();
        RelationalPackage.eINSTANCE.eClass();
        RecordPackage.eINSTANCE.eClass();
        MultidimensionalPackage.eINSTANCE.eClass();
        XmlPackage.eINSTANCE.eClass();
        TransformationPackage.eINSTANCE.eClass();
        OlapPackage.eINSTANCE.eClass();
        DataminingPackage.eINSTANCE.eClass();
        InformationvisualizationPackage.eINSTANCE.eClass();
        BusinessnomenclaturePackage.eINSTANCE.eClass();
        WarehouseprocessPackage.eINSTANCE.eClass();
        WarehouseoperationPackage.eINSTANCE.eClass();
        ErPackage.eINSTANCE.eClass();
        CoboldataPackage.eINSTANCE.eClass();
        DmsiiPackage.eINSTANCE.eClass();
        ImsdatabasePackage.eINSTANCE.eClass();
        EssbasePackage.eINSTANCE.eClass();
        ExpressPackage.eINSTANCE.eClass();
        InformationsetPackage.eINSTANCE.eClass();
        InformationreportingPackage.eINSTANCE.eClass();
        CwmmipPackage.eINSTANCE.eClass();
        ModelPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        AnalysisPackageImpl theAnalysisPackage = (AnalysisPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AnalysisPackage.eNS_URI) instanceof AnalysisPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AnalysisPackage.eNS_URI) : AnalysisPackage.eINSTANCE);
        ReportsPackageImpl theReportsPackage = (ReportsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ReportsPackage.eNS_URI) instanceof ReportsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ReportsPackage.eNS_URI) : ReportsPackage.eINSTANCE);
        ExpressionsPackageImpl theExpressionsPackage_1 = (ExpressionsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(org.talend.dataquality.expressions.ExpressionsPackage.eNS_URI) instanceof ExpressionsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(org.talend.dataquality.expressions.ExpressionsPackage.eNS_URI) : org.talend.dataquality.expressions.ExpressionsPackage.eINSTANCE);
        DomainPackageImpl theDomainPackage = (DomainPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DomainPackage.eNS_URI) instanceof DomainPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DomainPackage.eNS_URI) : DomainPackage.eINSTANCE);
        PatternPackageImpl thePatternPackage = (PatternPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PatternPackage.eNS_URI) instanceof PatternPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PatternPackage.eNS_URI) : PatternPackage.eINSTANCE);

        // Create package meta-data objects
        theIndicatorsPackage.createPackageContents();
        theAnalysisPackage.createPackageContents();
        theReportsPackage.createPackageContents();
        theExpressionsPackage_1.createPackageContents();
        theDomainPackage.createPackageContents();
        thePatternPackage.createPackageContents();

        // Initialize created meta-data
        theIndicatorsPackage.initializePackageContents();
        theAnalysisPackage.initializePackageContents();
        theReportsPackage.initializePackageContents();
        theExpressionsPackage_1.initializePackageContents();
        theDomainPackage.initializePackageContents();
        thePatternPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theIndicatorsPackage.freeze();

        return theIndicatorsPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getIndicator() {
        return indicatorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getIndicator_IndicatorType() {
        return (EReference)indicatorEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIndicator_Count() {
        return (EAttribute)indicatorEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIndicator_NullCount() {
        return (EAttribute)indicatorEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getIndicator_Parameters() {
        return (EReference)indicatorEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getIndicator_AnalyzedElement() {
        return (EReference)indicatorEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getIndicator_Value() {
        return (EReference)indicatorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getRowCountIndicator() {
        return rowCountIndicatorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getMeanIndicator() {
        return meanIndicatorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getSumIndicator() {
        return sumIndicatorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getCompositeIndicator() {
        return compositeIndicatorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getCompositeIndicator_Indicators() {
        return (EReference)compositeIndicatorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getRangeIndicator() {
        return rangeIndicatorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getRangeIndicator_LowerValue() {
        return (EReference)rangeIndicatorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getRangeIndicator_UpperValue() {
        return (EReference)rangeIndicatorEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getRangeIndicator_Range() {
        return (EReference)rangeIndicatorEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getBoxIndicator() {
        return boxIndicatorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getBoxIndicator_Min() {
        return (EReference)boxIndicatorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getBoxIndicator_Max() {
        return (EReference)boxIndicatorEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getBoxIndicator_FirstQuartile() {
        return (EReference)boxIndicatorEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getBoxIndicator_ThirdQuartile() {
        return (EReference)boxIndicatorEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getBoxIndicator_IQR() {
        return (EReference)boxIndicatorEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getIndicatorType() {
        return indicatorTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getIntegerSumIndicator() {
        return integerSumIndicatorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIntegerSumIndicator_Sum() {
        return (EAttribute)integerSumIndicatorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getDoubleSumIndicator() {
        return doubleSumIndicatorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDoubleSumIndicator_Sum() {
        return (EAttribute)doubleSumIndicatorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getBigDecimalIndicator() {
        return bigDecimalIndicatorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBigDecimalIndicator_Sum() {
        return (EAttribute)bigDecimalIndicatorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getFrequencyIndicator() {
        return frequencyIndicatorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFrequencyIndicator_Mode() {
        return (EAttribute)frequencyIndicatorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFrequencyIndicator_UniqueValues() {
        return (EAttribute)frequencyIndicatorEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFrequencyIndicator_DistinctValueCount() {
        return (EAttribute)frequencyIndicatorEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFrequencyIndicator_UniqueValueCount() {
        return (EAttribute)frequencyIndicatorEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFrequencyIndicator_DupplicateValueCount() {
        return (EAttribute)frequencyIndicatorEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFrequencyIndicator_ValueToFreq() {
        return (EAttribute)frequencyIndicatorEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getIntegerMeanIndicator() {
        return integerMeanIndicatorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getDoubleMeanIndicator() {
        return doubleMeanIndicatorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getBigDecimalMeanIndicator() {
        return bigDecimalMeanIndicatorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getBlankCountIndicator() {
        return blankCountIndicatorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBlankCountIndicator_BlankCount() {
        return (EAttribute)blankCountIndicatorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getIndicatorParameters() {
        return indicatorParametersEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getIndicatorParameters_IndicatorValidDomain() {
        return (EReference)indicatorParametersEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getIndicatorParameters_DataValidDomain() {
        return (EReference)indicatorParametersEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getMedianIndicator() {
        return medianIndicatorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMedianIndicator_Median() {
        return (EAttribute)medianIndicatorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMedianIndicator_FrequenceTable() {
        return (EAttribute)medianIndicatorEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getJavaSet() {
        return javaSetEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getJavaHashMap() {
        return javaHashMapEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getJavaTreeMap() {
        return javaTreeMapEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public IndicatorsFactory getIndicatorsFactory() {
        return (IndicatorsFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        indicatorEClass = createEClass(INDICATOR);
        createEReference(indicatorEClass, INDICATOR__VALUE);
        createEReference(indicatorEClass, INDICATOR__INDICATOR_TYPE);
        createEAttribute(indicatorEClass, INDICATOR__COUNT);
        createEAttribute(indicatorEClass, INDICATOR__NULL_COUNT);
        createEReference(indicatorEClass, INDICATOR__PARAMETERS);
        createEReference(indicatorEClass, INDICATOR__ANALYZED_ELEMENT);

        rowCountIndicatorEClass = createEClass(ROW_COUNT_INDICATOR);

        meanIndicatorEClass = createEClass(MEAN_INDICATOR);

        sumIndicatorEClass = createEClass(SUM_INDICATOR);

        compositeIndicatorEClass = createEClass(COMPOSITE_INDICATOR);
        createEReference(compositeIndicatorEClass, COMPOSITE_INDICATOR__INDICATORS);

        rangeIndicatorEClass = createEClass(RANGE_INDICATOR);
        createEReference(rangeIndicatorEClass, RANGE_INDICATOR__LOWER_VALUE);
        createEReference(rangeIndicatorEClass, RANGE_INDICATOR__UPPER_VALUE);
        createEReference(rangeIndicatorEClass, RANGE_INDICATOR__RANGE);

        boxIndicatorEClass = createEClass(BOX_INDICATOR);
        createEReference(boxIndicatorEClass, BOX_INDICATOR__MIN);
        createEReference(boxIndicatorEClass, BOX_INDICATOR__MAX);
        createEReference(boxIndicatorEClass, BOX_INDICATOR__FIRST_QUARTILE);
        createEReference(boxIndicatorEClass, BOX_INDICATOR__THIRD_QUARTILE);
        createEReference(boxIndicatorEClass, BOX_INDICATOR__IQR);

        indicatorTypeEClass = createEClass(INDICATOR_TYPE);

        integerSumIndicatorEClass = createEClass(INTEGER_SUM_INDICATOR);
        createEAttribute(integerSumIndicatorEClass, INTEGER_SUM_INDICATOR__SUM);

        doubleSumIndicatorEClass = createEClass(DOUBLE_SUM_INDICATOR);
        createEAttribute(doubleSumIndicatorEClass, DOUBLE_SUM_INDICATOR__SUM);

        bigDecimalIndicatorEClass = createEClass(BIG_DECIMAL_INDICATOR);
        createEAttribute(bigDecimalIndicatorEClass, BIG_DECIMAL_INDICATOR__SUM);

        frequencyIndicatorEClass = createEClass(FREQUENCY_INDICATOR);
        createEAttribute(frequencyIndicatorEClass, FREQUENCY_INDICATOR__MODE);
        createEAttribute(frequencyIndicatorEClass, FREQUENCY_INDICATOR__UNIQUE_VALUES);
        createEAttribute(frequencyIndicatorEClass, FREQUENCY_INDICATOR__DISTINCT_VALUE_COUNT);
        createEAttribute(frequencyIndicatorEClass, FREQUENCY_INDICATOR__UNIQUE_VALUE_COUNT);
        createEAttribute(frequencyIndicatorEClass, FREQUENCY_INDICATOR__DUPPLICATE_VALUE_COUNT);
        createEAttribute(frequencyIndicatorEClass, FREQUENCY_INDICATOR__VALUE_TO_FREQ);

        integerMeanIndicatorEClass = createEClass(INTEGER_MEAN_INDICATOR);

        doubleMeanIndicatorEClass = createEClass(DOUBLE_MEAN_INDICATOR);

        bigDecimalMeanIndicatorEClass = createEClass(BIG_DECIMAL_MEAN_INDICATOR);

        blankCountIndicatorEClass = createEClass(BLANK_COUNT_INDICATOR);
        createEAttribute(blankCountIndicatorEClass, BLANK_COUNT_INDICATOR__BLANK_COUNT);

        indicatorParametersEClass = createEClass(INDICATOR_PARAMETERS);
        createEReference(indicatorParametersEClass, INDICATOR_PARAMETERS__INDICATOR_VALID_DOMAIN);
        createEReference(indicatorParametersEClass, INDICATOR_PARAMETERS__DATA_VALID_DOMAIN);

        medianIndicatorEClass = createEClass(MEDIAN_INDICATOR);
        createEAttribute(medianIndicatorEClass, MEDIAN_INDICATOR__MEDIAN);
        createEAttribute(medianIndicatorEClass, MEDIAN_INDICATOR__FREQUENCE_TABLE);

        // Create data types
        javaSetEDataType = createEDataType(JAVA_SET);
        javaHashMapEDataType = createEDataType(JAVA_HASH_MAP);
        javaTreeMapEDataType = createEDataType(JAVA_TREE_MAP);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        DomainPackage theDomainPackage = (DomainPackage)EPackage.Registry.INSTANCE.getEPackage(DomainPackage.eNS_URI);
        CorePackage theCorePackage = (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        rowCountIndicatorEClass.getESuperTypes().add(this.getIndicator());
        sumIndicatorEClass.getESuperTypes().add(this.getIndicator());
        compositeIndicatorEClass.getESuperTypes().add(this.getIndicator());
        rangeIndicatorEClass.getESuperTypes().add(this.getCompositeIndicator());
        boxIndicatorEClass.getESuperTypes().add(this.getCompositeIndicator());
        indicatorTypeEClass.getESuperTypes().add(theCorePackage.getClassifier());
        integerSumIndicatorEClass.getESuperTypes().add(this.getSumIndicator());
        doubleSumIndicatorEClass.getESuperTypes().add(this.getSumIndicator());
        bigDecimalIndicatorEClass.getESuperTypes().add(this.getSumIndicator());
        frequencyIndicatorEClass.getESuperTypes().add(this.getIndicator());
        integerMeanIndicatorEClass.getESuperTypes().add(this.getIntegerSumIndicator());
        integerMeanIndicatorEClass.getESuperTypes().add(this.getMeanIndicator());
        doubleMeanIndicatorEClass.getESuperTypes().add(this.getDoubleSumIndicator());
        doubleMeanIndicatorEClass.getESuperTypes().add(this.getMeanIndicator());
        bigDecimalMeanIndicatorEClass.getESuperTypes().add(this.getBigDecimalIndicator());
        bigDecimalMeanIndicatorEClass.getESuperTypes().add(this.getMeanIndicator());
        blankCountIndicatorEClass.getESuperTypes().add(this.getIndicator());
        medianIndicatorEClass.getESuperTypes().add(this.getIndicator());

        // Initialize classes and features; add operations and parameters
        initEClass(indicatorEClass, Indicator.class, "Indicator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getIndicator_Value(), theDomainPackage.getLiteralValue(), theDomainPackage.getLiteralValue_Indicator(), "value", null, 0, 1, Indicator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getIndicator_IndicatorType(), this.getIndicatorType(), null, "indicatorType", null, 0, 1, Indicator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIndicator_Count(), ecorePackage.getELong(), "count", null, 0, 1, Indicator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIndicator_NullCount(), ecorePackage.getELong(), "nullCount", null, 0, 1, Indicator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getIndicator_Parameters(), this.getIndicatorParameters(), null, "parameters", null, 0, 1, Indicator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getIndicator_AnalyzedElement(), theCorePackage.getModelElement(), null, "analyzedElement", null, 0, 1, Indicator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        EOperation op = addEOperation(indicatorEClass, ecorePackage.getEBoolean(), "handle", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEJavaObject(), "data", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(indicatorEClass, ecorePackage.getEBoolean(), "reset", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(rowCountIndicatorEClass, RowCountIndicator.class, "RowCountIndicator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(meanIndicatorEClass, MeanIndicator.class, "MeanIndicator", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        addEOperation(meanIndicatorEClass, ecorePackage.getEDouble(), "getMean", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(meanIndicatorEClass, ecorePackage.getEDouble(), "getMeanWithNulls", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "valueForNull", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(sumIndicatorEClass, SumIndicator.class, "SumIndicator", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(compositeIndicatorEClass, CompositeIndicator.class, "CompositeIndicator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCompositeIndicator_Indicators(), this.getIndicator(), null, "indicators", null, 0, -1, CompositeIndicator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(rangeIndicatorEClass, RangeIndicator.class, "RangeIndicator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getRangeIndicator_LowerValue(), this.getIndicator(), null, "lowerValue", null, 0, 1, RangeIndicator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getRangeIndicator_UpperValue(), this.getIndicator(), null, "upperValue", null, 0, 1, RangeIndicator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getRangeIndicator_Range(), this.getIndicator(), null, "range", null, 0, 1, RangeIndicator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(boxIndicatorEClass, BoxIndicator.class, "BoxIndicator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getBoxIndicator_Min(), this.getIndicator(), null, "min", null, 0, 1, BoxIndicator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBoxIndicator_Max(), this.getIndicator(), null, "max", null, 0, 1, BoxIndicator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBoxIndicator_FirstQuartile(), this.getIndicator(), null, "firstQuartile", null, 0, 1, BoxIndicator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBoxIndicator_ThirdQuartile(), this.getIndicator(), null, "thirdQuartile", null, 0, 1, BoxIndicator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBoxIndicator_IQR(), this.getIndicator(), null, "IQR", null, 0, 1, BoxIndicator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(indicatorTypeEClass, IndicatorType.class, "IndicatorType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(integerSumIndicatorEClass, IntegerSumIndicator.class, "IntegerSumIndicator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getIntegerSumIndicator_Sum(), ecorePackage.getELong(), "sum", null, 0, 1, IntegerSumIndicator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(doubleSumIndicatorEClass, DoubleSumIndicator.class, "DoubleSumIndicator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDoubleSumIndicator_Sum(), ecorePackage.getEDouble(), "sum", null, 0, 1, DoubleSumIndicator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(bigDecimalIndicatorEClass, BigDecimalIndicator.class, "BigDecimalIndicator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBigDecimalIndicator_Sum(), ecorePackage.getEBigDecimal(), "sum", null, 0, 1, BigDecimalIndicator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(frequencyIndicatorEClass, FrequencyIndicator.class, "FrequencyIndicator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFrequencyIndicator_Mode(), ecorePackage.getEJavaObject(), "mode", null, 0, 1, FrequencyIndicator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFrequencyIndicator_UniqueValues(), ecorePackage.getEJavaObject(), "uniqueValues", null, 0, -1, FrequencyIndicator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFrequencyIndicator_DistinctValueCount(), ecorePackage.getEInt(), "distinctValueCount", null, 0, 1, FrequencyIndicator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFrequencyIndicator_UniqueValueCount(), ecorePackage.getEInt(), "uniqueValueCount", null, 0, 1, FrequencyIndicator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFrequencyIndicator_DupplicateValueCount(), ecorePackage.getEInt(), "dupplicateValueCount", null, 0, 1, FrequencyIndicator.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFrequencyIndicator_ValueToFreq(), this.getJavaHashMap(), "valueToFreq", null, 0, 1, FrequencyIndicator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        op = addEOperation(frequencyIndicatorEClass, ecorePackage.getELong(), "getCount", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEJavaObject(), "dataValue", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(frequencyIndicatorEClass, ecorePackage.getEDouble(), "getFrequency", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEJavaObject(), "dataValue", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(frequencyIndicatorEClass, this.getJavaSet(), "getDistinctValues", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(integerMeanIndicatorEClass, IntegerMeanIndicator.class, "IntegerMeanIndicator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(doubleMeanIndicatorEClass, DoubleMeanIndicator.class, "DoubleMeanIndicator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(bigDecimalMeanIndicatorEClass, BigDecimalMeanIndicator.class, "BigDecimalMeanIndicator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(blankCountIndicatorEClass, BlankCountIndicator.class, "BlankCountIndicator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBlankCountIndicator_BlankCount(), ecorePackage.getEInt(), "blankCount", null, 0, 1, BlankCountIndicator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(indicatorParametersEClass, IndicatorParameters.class, "IndicatorParameters", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getIndicatorParameters_IndicatorValidDomain(), theDomainPackage.getDomain(), null, "indicatorValidDomain", null, 0, 1, IndicatorParameters.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getIndicatorParameters_DataValidDomain(), theDomainPackage.getDomain(), null, "dataValidDomain", null, 0, 1, IndicatorParameters.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(medianIndicatorEClass, MedianIndicator.class, "MedianIndicator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getMedianIndicator_Median(), ecorePackage.getEDouble(), "median", null, 0, 1, MedianIndicator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMedianIndicator_FrequenceTable(), this.getJavaTreeMap(), "frequenceTable", null, 0, 1, MedianIndicator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        addEOperation(medianIndicatorEClass, ecorePackage.getEBoolean(), "computeMedian", 0, 1, IS_UNIQUE, IS_ORDERED);

        // Initialize data types
        initEDataType(javaSetEDataType, Set.class, "JavaSet", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS, "java.util.Set<Object>");
        initEDataType(javaHashMapEDataType, HashMap.class, "JavaHashMap", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS, "java.util.HashMap<Object, java.lang.Long>");
        initEDataType(javaTreeMapEDataType, TreeMap.class, "JavaTreeMap", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS, "java.util.TreeMap<Object, java.lang.Long>");

        // Create resource
        createResource(eNS_URI);
    }

} // IndicatorsPackageImpl
