/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.designer.core.model.utils.emf.talendfile.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.talend.designer.core.model.utils.emf.talendfile.ColumnType;
import org.talend.designer.core.model.utils.emf.talendfile.ConnectionType;
import org.talend.designer.core.model.utils.emf.talendfile.ContextParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.ContextType;
import org.talend.designer.core.model.utils.emf.talendfile.DocumentRoot;
import org.talend.designer.core.model.utils.emf.talendfile.ElementParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.ElementValueType;
import org.talend.designer.core.model.utils.emf.talendfile.JobType;
import org.talend.designer.core.model.utils.emf.talendfile.LogToDatabaseType;
import org.talend.designer.core.model.utils.emf.talendfile.LogToFileType;
import org.talend.designer.core.model.utils.emf.talendfile.LogToStdOutType;
import org.talend.designer.core.model.utils.emf.talendfile.LogsType;
import org.talend.designer.core.model.utils.emf.talendfile.MetadataType;
import org.talend.designer.core.model.utils.emf.talendfile.NodeType;
import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;
import org.talend.designer.core.model.utils.emf.talendfile.RequiredType;
import org.talend.designer.core.model.utils.emf.talendfile.TalendFileFactory;
import org.talend.designer.core.model.utils.emf.talendfile.TalendFilePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TalendFilePackageImpl extends EPackageImpl implements TalendFilePackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass columnTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass connectionTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass contextParameterTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass contextTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass documentRootEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass elementParameterTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass elementValueTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass jobTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass logsTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass logToDatabaseTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass logToFileTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass logToStdOutTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass metadataTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass nodeTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass processTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass requiredTypeEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.talend.designer.core.model.utils.emf.talendfile.TalendFilePackage#eNS_URI
     * @see #init()
     * @generated
     */
    private TalendFilePackageImpl() {
        super(eNS_URI, TalendFileFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
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
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static TalendFilePackage init() {
        if (isInited) return (TalendFilePackage)EPackage.Registry.INSTANCE.getEPackage(TalendFilePackage.eNS_URI);

        // Obtain or create and register package
        TalendFilePackageImpl theTalendFilePackage = (TalendFilePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof TalendFilePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new TalendFilePackageImpl());

        isInited = true;

        // Initialize simple dependencies
        XMLTypePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theTalendFilePackage.createPackageContents();

        // Initialize created meta-data
        theTalendFilePackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theTalendFilePackage.freeze();

        return theTalendFilePackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getColumnType() {
        return columnTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getColumnType_Comment() {
        return (EAttribute)columnTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getColumnType_Key() {
        return (EAttribute)columnTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getColumnType_Length() {
        return (EAttribute)columnTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getColumnType_Name() {
        return (EAttribute)columnTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getColumnType_Nullable() {
        return (EAttribute)columnTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getColumnType_Precision() {
        return (EAttribute)columnTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getColumnType_Type() {
        return (EAttribute)columnTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getConnectionType() {
        return connectionTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getConnectionType_ElementParameter() {
        return (EReference)connectionTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getConnectionType_Label() {
        return (EAttribute)connectionTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getConnectionType_LineStyle() {
        return (EAttribute)connectionTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getConnectionType_Metaname() {
        return (EAttribute)connectionTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getConnectionType_OffsetLabelX() {
        return (EAttribute)connectionTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getConnectionType_OffsetLabelY() {
        return (EAttribute)connectionTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getConnectionType_Source() {
        return (EAttribute)connectionTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getConnectionType_Target() {
        return (EAttribute)connectionTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getContextParameterType() {
        return contextParameterTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getContextParameterType_Comment() {
        return (EAttribute)contextParameterTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getContextParameterType_Name() {
        return (EAttribute)contextParameterTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getContextParameterType_Prompt() {
        return (EAttribute)contextParameterTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getContextParameterType_PromptNeeded() {
        return (EAttribute)contextParameterTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getContextParameterType_Type() {
        return (EAttribute)contextParameterTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getContextParameterType_Value() {
        return (EAttribute)contextParameterTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getContextType() {
        return contextTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getContextType_ContextParameter() {
        return (EReference)contextTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getContextType_ConfirmationNeeded() {
        return (EAttribute)contextTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getContextType_Name() {
        return (EAttribute)contextTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDocumentRoot() {
        return documentRootEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_Mixed() {
        return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_XMLNSPrefixMap() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_XSISchemaLocation() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Connection() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Context() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ElementParameter() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Node() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Process() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Required() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getElementParameterType() {
        return elementParameterTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getElementParameterType_ElementValue() {
        return (EReference)elementParameterTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getElementParameterType_Field() {
        return (EAttribute)elementParameterTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getElementParameterType_Name() {
        return (EAttribute)elementParameterTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getElementParameterType_Value() {
        return (EAttribute)elementParameterTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getElementValueType() {
        return elementValueTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getElementValueType_ElementRef() {
        return (EAttribute)elementValueTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getElementValueType_Value() {
        return (EAttribute)elementValueTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getJobType() {
        return jobTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getJobType_Context() {
        return (EAttribute)jobTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getJobType_Name() {
        return (EAttribute)jobTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLogsType() {
        return logsTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLogsType_LogToFile() {
        return (EReference)logsTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLogsType_LogToDatabase() {
        return (EReference)logsTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLogsType_LogToStdOut() {
        return (EReference)logsTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLogToDatabaseType() {
        return logToDatabaseTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLogToDatabaseType_Database() {
        return (EAttribute)logToDatabaseTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLogToDatabaseType_Level() {
        return (EAttribute)logToDatabaseTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLogToDatabaseType_Selected() {
        return (EAttribute)logToDatabaseTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLogToFileType() {
        return logToFileTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLogToFileType_Filename() {
        return (EAttribute)logToFileTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLogToFileType_Level() {
        return (EAttribute)logToFileTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLogToFileType_Selected() {
        return (EAttribute)logToFileTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLogToStdOutType() {
        return logToStdOutTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLogToStdOutType_Level() {
        return (EAttribute)logToStdOutTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLogToStdOutType_Selected() {
        return (EAttribute)logToStdOutTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getMetadataType() {
        return metadataTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getMetadataType_Column() {
        return (EReference)metadataTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMetadataType_Comment() {
        return (EAttribute)metadataTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMetadataType_Name() {
        return (EAttribute)metadataTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMetadataType_Source() {
        return (EAttribute)metadataTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getNodeType() {
        return nodeTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNodeType_ElementParameter() {
        return (EReference)nodeTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNodeType_Metadata() {
        return (EReference)nodeTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNodeType_BinaryData() {
        return (EAttribute)nodeTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNodeType_StringData() {
        return (EAttribute)nodeTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNodeType_ComponentName() {
        return (EAttribute)nodeTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNodeType_ComponentVersion() {
        return (EAttribute)nodeTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNodeType_OffsetLabelX() {
        return (EAttribute)nodeTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNodeType_OffsetLabelY() {
        return (EAttribute)nodeTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNodeType_PosX() {
        return (EAttribute)nodeTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNodeType_PosY() {
        return (EAttribute)nodeTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getProcessType() {
        return processTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProcessType_Description() {
        return (EAttribute)processTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getProcessType_Required() {
        return (EReference)processTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getProcessType_Context() {
        return (EReference)processTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getProcessType_Node() {
        return (EReference)processTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getProcessType_Connection() {
        return (EReference)processTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getProcessType_Logs() {
        return (EReference)processTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProcessType_Author() {
        return (EAttribute)processTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProcessType_Comment() {
        return (EAttribute)processTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProcessType_DefaultContext() {
        return (EAttribute)processTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProcessType_Name() {
        return (EAttribute)processTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProcessType_Purpose() {
        return (EAttribute)processTypeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProcessType_Status() {
        return (EAttribute)processTypeEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProcessType_Version() {
        return (EAttribute)processTypeEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getRequiredType() {
        return requiredTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getRequiredType_Job() {
        return (EReference)requiredTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TalendFileFactory getTalendFileFactory() {
        return (TalendFileFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        columnTypeEClass = createEClass(COLUMN_TYPE);
        createEAttribute(columnTypeEClass, COLUMN_TYPE__COMMENT);
        createEAttribute(columnTypeEClass, COLUMN_TYPE__KEY);
        createEAttribute(columnTypeEClass, COLUMN_TYPE__LENGTH);
        createEAttribute(columnTypeEClass, COLUMN_TYPE__NAME);
        createEAttribute(columnTypeEClass, COLUMN_TYPE__NULLABLE);
        createEAttribute(columnTypeEClass, COLUMN_TYPE__PRECISION);
        createEAttribute(columnTypeEClass, COLUMN_TYPE__TYPE);

        connectionTypeEClass = createEClass(CONNECTION_TYPE);
        createEReference(connectionTypeEClass, CONNECTION_TYPE__ELEMENT_PARAMETER);
        createEAttribute(connectionTypeEClass, CONNECTION_TYPE__LABEL);
        createEAttribute(connectionTypeEClass, CONNECTION_TYPE__LINE_STYLE);
        createEAttribute(connectionTypeEClass, CONNECTION_TYPE__METANAME);
        createEAttribute(connectionTypeEClass, CONNECTION_TYPE__OFFSET_LABEL_X);
        createEAttribute(connectionTypeEClass, CONNECTION_TYPE__OFFSET_LABEL_Y);
        createEAttribute(connectionTypeEClass, CONNECTION_TYPE__SOURCE);
        createEAttribute(connectionTypeEClass, CONNECTION_TYPE__TARGET);

        contextParameterTypeEClass = createEClass(CONTEXT_PARAMETER_TYPE);
        createEAttribute(contextParameterTypeEClass, CONTEXT_PARAMETER_TYPE__COMMENT);
        createEAttribute(contextParameterTypeEClass, CONTEXT_PARAMETER_TYPE__NAME);
        createEAttribute(contextParameterTypeEClass, CONTEXT_PARAMETER_TYPE__PROMPT);
        createEAttribute(contextParameterTypeEClass, CONTEXT_PARAMETER_TYPE__PROMPT_NEEDED);
        createEAttribute(contextParameterTypeEClass, CONTEXT_PARAMETER_TYPE__TYPE);
        createEAttribute(contextParameterTypeEClass, CONTEXT_PARAMETER_TYPE__VALUE);

        contextTypeEClass = createEClass(CONTEXT_TYPE);
        createEReference(contextTypeEClass, CONTEXT_TYPE__CONTEXT_PARAMETER);
        createEAttribute(contextTypeEClass, CONTEXT_TYPE__CONFIRMATION_NEEDED);
        createEAttribute(contextTypeEClass, CONTEXT_TYPE__NAME);

        documentRootEClass = createEClass(DOCUMENT_ROOT);
        createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
        createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
        createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
        createEReference(documentRootEClass, DOCUMENT_ROOT__CONNECTION);
        createEReference(documentRootEClass, DOCUMENT_ROOT__CONTEXT);
        createEReference(documentRootEClass, DOCUMENT_ROOT__ELEMENT_PARAMETER);
        createEReference(documentRootEClass, DOCUMENT_ROOT__NODE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__PROCESS);
        createEReference(documentRootEClass, DOCUMENT_ROOT__REQUIRED);

        elementParameterTypeEClass = createEClass(ELEMENT_PARAMETER_TYPE);
        createEReference(elementParameterTypeEClass, ELEMENT_PARAMETER_TYPE__ELEMENT_VALUE);
        createEAttribute(elementParameterTypeEClass, ELEMENT_PARAMETER_TYPE__FIELD);
        createEAttribute(elementParameterTypeEClass, ELEMENT_PARAMETER_TYPE__NAME);
        createEAttribute(elementParameterTypeEClass, ELEMENT_PARAMETER_TYPE__VALUE);

        elementValueTypeEClass = createEClass(ELEMENT_VALUE_TYPE);
        createEAttribute(elementValueTypeEClass, ELEMENT_VALUE_TYPE__ELEMENT_REF);
        createEAttribute(elementValueTypeEClass, ELEMENT_VALUE_TYPE__VALUE);

        jobTypeEClass = createEClass(JOB_TYPE);
        createEAttribute(jobTypeEClass, JOB_TYPE__CONTEXT);
        createEAttribute(jobTypeEClass, JOB_TYPE__NAME);

        logsTypeEClass = createEClass(LOGS_TYPE);
        createEReference(logsTypeEClass, LOGS_TYPE__LOG_TO_FILE);
        createEReference(logsTypeEClass, LOGS_TYPE__LOG_TO_DATABASE);
        createEReference(logsTypeEClass, LOGS_TYPE__LOG_TO_STD_OUT);

        logToDatabaseTypeEClass = createEClass(LOG_TO_DATABASE_TYPE);
        createEAttribute(logToDatabaseTypeEClass, LOG_TO_DATABASE_TYPE__DATABASE);
        createEAttribute(logToDatabaseTypeEClass, LOG_TO_DATABASE_TYPE__LEVEL);
        createEAttribute(logToDatabaseTypeEClass, LOG_TO_DATABASE_TYPE__SELECTED);

        logToFileTypeEClass = createEClass(LOG_TO_FILE_TYPE);
        createEAttribute(logToFileTypeEClass, LOG_TO_FILE_TYPE__FILENAME);
        createEAttribute(logToFileTypeEClass, LOG_TO_FILE_TYPE__LEVEL);
        createEAttribute(logToFileTypeEClass, LOG_TO_FILE_TYPE__SELECTED);

        logToStdOutTypeEClass = createEClass(LOG_TO_STD_OUT_TYPE);
        createEAttribute(logToStdOutTypeEClass, LOG_TO_STD_OUT_TYPE__LEVEL);
        createEAttribute(logToStdOutTypeEClass, LOG_TO_STD_OUT_TYPE__SELECTED);

        metadataTypeEClass = createEClass(METADATA_TYPE);
        createEReference(metadataTypeEClass, METADATA_TYPE__COLUMN);
        createEAttribute(metadataTypeEClass, METADATA_TYPE__COMMENT);
        createEAttribute(metadataTypeEClass, METADATA_TYPE__NAME);
        createEAttribute(metadataTypeEClass, METADATA_TYPE__SOURCE);

        nodeTypeEClass = createEClass(NODE_TYPE);
        createEReference(nodeTypeEClass, NODE_TYPE__ELEMENT_PARAMETER);
        createEReference(nodeTypeEClass, NODE_TYPE__METADATA);
        createEAttribute(nodeTypeEClass, NODE_TYPE__BINARY_DATA);
        createEAttribute(nodeTypeEClass, NODE_TYPE__STRING_DATA);
        createEAttribute(nodeTypeEClass, NODE_TYPE__COMPONENT_NAME);
        createEAttribute(nodeTypeEClass, NODE_TYPE__COMPONENT_VERSION);
        createEAttribute(nodeTypeEClass, NODE_TYPE__OFFSET_LABEL_X);
        createEAttribute(nodeTypeEClass, NODE_TYPE__OFFSET_LABEL_Y);
        createEAttribute(nodeTypeEClass, NODE_TYPE__POS_X);
        createEAttribute(nodeTypeEClass, NODE_TYPE__POS_Y);

        processTypeEClass = createEClass(PROCESS_TYPE);
        createEAttribute(processTypeEClass, PROCESS_TYPE__DESCRIPTION);
        createEReference(processTypeEClass, PROCESS_TYPE__REQUIRED);
        createEReference(processTypeEClass, PROCESS_TYPE__CONTEXT);
        createEReference(processTypeEClass, PROCESS_TYPE__NODE);
        createEReference(processTypeEClass, PROCESS_TYPE__CONNECTION);
        createEReference(processTypeEClass, PROCESS_TYPE__LOGS);
        createEAttribute(processTypeEClass, PROCESS_TYPE__AUTHOR);
        createEAttribute(processTypeEClass, PROCESS_TYPE__COMMENT);
        createEAttribute(processTypeEClass, PROCESS_TYPE__DEFAULT_CONTEXT);
        createEAttribute(processTypeEClass, PROCESS_TYPE__NAME);
        createEAttribute(processTypeEClass, PROCESS_TYPE__PURPOSE);
        createEAttribute(processTypeEClass, PROCESS_TYPE__STATUS);
        createEAttribute(processTypeEClass, PROCESS_TYPE__VERSION);

        requiredTypeEClass = createEClass(REQUIRED_TYPE);
        createEReference(requiredTypeEClass, REQUIRED_TYPE__JOB);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
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
        XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

        // Add supertypes to classes

        // Initialize classes and features; add operations and parameters
        initEClass(columnTypeEClass, ColumnType.class, "ColumnType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getColumnType_Comment(), theXMLTypePackage.getString(), "comment", null, 0, 1, ColumnType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getColumnType_Key(), theXMLTypePackage.getBoolean(), "key", null, 0, 1, ColumnType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getColumnType_Length(), theXMLTypePackage.getInt(), "length", null, 0, 1, ColumnType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getColumnType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, ColumnType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getColumnType_Nullable(), theXMLTypePackage.getBoolean(), "nullable", null, 0, 1, ColumnType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getColumnType_Precision(), theXMLTypePackage.getInt(), "precision", null, 0, 1, ColumnType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getColumnType_Type(), theXMLTypePackage.getString(), "type", null, 0, 1, ColumnType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(connectionTypeEClass, ConnectionType.class, "ConnectionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getConnectionType_ElementParameter(), this.getElementParameterType(), null, "elementParameter", null, 0, -1, ConnectionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getConnectionType_Label(), theXMLTypePackage.getString(), "label", null, 0, 1, ConnectionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getConnectionType_LineStyle(), theXMLTypePackage.getInt(), "lineStyle", null, 0, 1, ConnectionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getConnectionType_Metaname(), theXMLTypePackage.getString(), "metaname", null, 0, 1, ConnectionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getConnectionType_OffsetLabelX(), theXMLTypePackage.getInt(), "offsetLabelX", null, 0, 1, ConnectionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getConnectionType_OffsetLabelY(), theXMLTypePackage.getInt(), "offsetLabelY", null, 0, 1, ConnectionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getConnectionType_Source(), theXMLTypePackage.getString(), "source", null, 0, 1, ConnectionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getConnectionType_Target(), theXMLTypePackage.getString(), "target", null, 0, 1, ConnectionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(contextParameterTypeEClass, ContextParameterType.class, "ContextParameterType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getContextParameterType_Comment(), theXMLTypePackage.getString(), "comment", null, 0, 1, ContextParameterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getContextParameterType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, ContextParameterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getContextParameterType_Prompt(), theXMLTypePackage.getString(), "prompt", null, 0, 1, ContextParameterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getContextParameterType_PromptNeeded(), theXMLTypePackage.getBoolean(), "promptNeeded", null, 0, 1, ContextParameterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getContextParameterType_Type(), theXMLTypePackage.getString(), "type", null, 0, 1, ContextParameterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getContextParameterType_Value(), theXMLTypePackage.getString(), "value", null, 0, 1, ContextParameterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(contextTypeEClass, ContextType.class, "ContextType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getContextType_ContextParameter(), this.getContextParameterType(), null, "contextParameter", null, 0, -1, ContextType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getContextType_ConfirmationNeeded(), theXMLTypePackage.getBoolean(), "confirmationNeeded", null, 0, 1, ContextType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getContextType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, ContextType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_Connection(), this.getConnectionType(), null, "connection", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_Context(), this.getContextType(), null, "context", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_ElementParameter(), this.getElementParameterType(), null, "elementParameter", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_Node(), this.getNodeType(), null, "node", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_Process(), this.getProcessType(), null, "process", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_Required(), this.getRequiredType(), null, "required", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(elementParameterTypeEClass, ElementParameterType.class, "ElementParameterType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getElementParameterType_ElementValue(), this.getElementValueType(), null, "elementValue", null, 0, -1, ElementParameterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getElementParameterType_Field(), theXMLTypePackage.getString(), "field", null, 0, 1, ElementParameterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getElementParameterType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, ElementParameterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getElementParameterType_Value(), theXMLTypePackage.getString(), "value", null, 0, 1, ElementParameterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(elementValueTypeEClass, ElementValueType.class, "ElementValueType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getElementValueType_ElementRef(), theXMLTypePackage.getString(), "elementRef", null, 0, 1, ElementValueType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getElementValueType_Value(), theXMLTypePackage.getString(), "value", null, 0, 1, ElementValueType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(jobTypeEClass, JobType.class, "JobType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getJobType_Context(), theXMLTypePackage.getString(), "context", null, 0, 1, JobType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getJobType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, JobType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(logsTypeEClass, LogsType.class, "LogsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getLogsType_LogToFile(), this.getLogToFileType(), null, "logToFile", null, 1, 1, LogsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getLogsType_LogToDatabase(), this.getLogToDatabaseType(), null, "logToDatabase", null, 1, 1, LogsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getLogsType_LogToStdOut(), this.getLogToStdOutType(), null, "logToStdOut", null, 1, 1, LogsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(logToDatabaseTypeEClass, LogToDatabaseType.class, "LogToDatabaseType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getLogToDatabaseType_Database(), theXMLTypePackage.getString(), "database", null, 0, 1, LogToDatabaseType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getLogToDatabaseType_Level(), theXMLTypePackage.getInt(), "level", null, 0, 1, LogToDatabaseType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getLogToDatabaseType_Selected(), theXMLTypePackage.getBoolean(), "selected", null, 0, 1, LogToDatabaseType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(logToFileTypeEClass, LogToFileType.class, "LogToFileType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getLogToFileType_Filename(), theXMLTypePackage.getString(), "filename", null, 0, 1, LogToFileType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getLogToFileType_Level(), theXMLTypePackage.getInt(), "level", null, 0, 1, LogToFileType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getLogToFileType_Selected(), theXMLTypePackage.getBoolean(), "selected", null, 0, 1, LogToFileType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(logToStdOutTypeEClass, LogToStdOutType.class, "LogToStdOutType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getLogToStdOutType_Level(), theXMLTypePackage.getInt(), "level", null, 0, 1, LogToStdOutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getLogToStdOutType_Selected(), theXMLTypePackage.getBoolean(), "selected", null, 0, 1, LogToStdOutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(metadataTypeEClass, MetadataType.class, "MetadataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getMetadataType_Column(), this.getColumnType(), null, "column", null, 0, -1, MetadataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getMetadataType_Comment(), theXMLTypePackage.getString(), "comment", null, 0, 1, MetadataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getMetadataType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, MetadataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getMetadataType_Source(), theXMLTypePackage.getString(), "source", null, 0, 1, MetadataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(nodeTypeEClass, NodeType.class, "NodeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getNodeType_ElementParameter(), this.getElementParameterType(), null, "elementParameter", null, 1, -1, NodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getNodeType_Metadata(), this.getMetadataType(), null, "metadata", null, 0, -1, NodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getNodeType_BinaryData(), theXMLTypePackage.getBase64Binary(), "binaryData", null, 0, 1, NodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getNodeType_StringData(), theXMLTypePackage.getString(), "stringData", null, 0, 1, NodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getNodeType_ComponentName(), theXMLTypePackage.getString(), "componentName", null, 0, 1, NodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getNodeType_ComponentVersion(), theXMLTypePackage.getString(), "componentVersion", null, 0, 1, NodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getNodeType_OffsetLabelX(), theXMLTypePackage.getInt(), "offsetLabelX", null, 0, 1, NodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getNodeType_OffsetLabelY(), theXMLTypePackage.getInt(), "offsetLabelY", null, 0, 1, NodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getNodeType_PosX(), theXMLTypePackage.getInt(), "posX", null, 0, 1, NodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getNodeType_PosY(), theXMLTypePackage.getInt(), "posY", null, 0, 1, NodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(processTypeEClass, ProcessType.class, "ProcessType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getProcessType_Description(), theXMLTypePackage.getString(), "description", null, 0, 1, ProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getProcessType_Required(), this.getRequiredType(), null, "required", null, 0, 1, ProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getProcessType_Context(), this.getContextType(), null, "context", null, 0, -1, ProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getProcessType_Node(), this.getNodeType(), null, "node", null, 0, -1, ProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getProcessType_Connection(), this.getConnectionType(), null, "connection", null, 0, -1, ProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getProcessType_Logs(), this.getLogsType(), null, "logs", null, 0, 1, ProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getProcessType_Author(), theXMLTypePackage.getString(), "author", null, 0, 1, ProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getProcessType_Comment(), theXMLTypePackage.getString(), "comment", null, 0, 1, ProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getProcessType_DefaultContext(), theXMLTypePackage.getString(), "defaultContext", null, 0, 1, ProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getProcessType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, ProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getProcessType_Purpose(), theXMLTypePackage.getString(), "purpose", null, 0, 1, ProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getProcessType_Status(), theXMLTypePackage.getString(), "status", null, 0, 1, ProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getProcessType_Version(), theXMLTypePackage.getString(), "version", null, 0, 1, ProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(requiredTypeEClass, RequiredType.class, "RequiredType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getRequiredType_Job(), this.getJobType(), null, "job", null, 0, -1, RequiredType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        // Create resource
        createResource(eNS_URI);

        // Create annotations
        // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
        createExtendedMetaDataAnnotations();
    }

    /**
     * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void createExtendedMetaDataAnnotations() {
        String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		 //$NON-NLS-1$
        addAnnotation
          (this, 
           source, 
           new String[] {
             "qualified", "false" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (columnTypeEClass, 
           source, 
           new String[] {
             "name", "Column_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getColumnType_Comment(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "Comment", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getColumnType_Key(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "key", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getColumnType_Length(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "length", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getColumnType_Name(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getColumnType_Nullable(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "nullable", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getColumnType_Precision(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "precision", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getColumnType_Type(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "type", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (connectionTypeEClass, 
           source, 
           new String[] {
             "name", "Connection_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getConnectionType_ElementParameter(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "ElementParameter", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getConnectionType_Label(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "label", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getConnectionType_LineStyle(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "lineStyle", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getConnectionType_Metaname(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "metaname", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getConnectionType_OffsetLabelX(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "offsetLabelX", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getConnectionType_OffsetLabelY(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "offsetLabelY", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getConnectionType_Source(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "source", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getConnectionType_Target(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "target", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (contextParameterTypeEClass, 
           source, 
           new String[] {
             "name", "ContextParameter_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "empty" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getContextParameterType_Comment(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "comment", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getContextParameterType_Name(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getContextParameterType_Prompt(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "prompt", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getContextParameterType_PromptNeeded(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "promptNeeded", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getContextParameterType_Type(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "type", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getContextParameterType_Value(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "value", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (contextTypeEClass, 
           source, 
           new String[] {
             "name", "Context_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getContextType_ContextParameter(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "ContextParameter", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getContextType_ConfirmationNeeded(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "confirmationNeeded", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getContextType_Name(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (documentRootEClass, 
           source, 
           new String[] {
             "name", "", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "mixed" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getDocumentRoot_Mixed(), 
           source, 
           new String[] {
             "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
             "name", ":mixed" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getDocumentRoot_XMLNSPrefixMap(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "xmlns:prefix" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getDocumentRoot_XSISchemaLocation(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "xsi:schemaLocation" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getDocumentRoot_Connection(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "Connection", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getDocumentRoot_Context(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "Context", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getDocumentRoot_ElementParameter(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "ElementParameter", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getDocumentRoot_Node(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "Node", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getDocumentRoot_Process(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "Process", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getDocumentRoot_Required(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "Required", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (elementParameterTypeEClass, 
           source, 
           new String[] {
             "name", "ElementParameter_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getElementParameterType_ElementValue(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "ElementValue", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getElementParameterType_Field(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "field", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getElementParameterType_Name(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getElementParameterType_Value(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "value", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (elementValueTypeEClass, 
           source, 
           new String[] {
             "name", "ElementValue_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "empty" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getElementValueType_ElementRef(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "elementRef", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getElementValueType_Value(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "value", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (jobTypeEClass, 
           source, 
           new String[] {
             "name", "Job_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "empty" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getJobType_Context(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "context", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getJobType_Name(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (logsTypeEClass, 
           source, 
           new String[] {
             "name", "Logs_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getLogsType_LogToFile(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "LogToFile", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getLogsType_LogToDatabase(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "LogToDatabase", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getLogsType_LogToStdOut(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "LogToStdOut", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (logToDatabaseTypeEClass, 
           source, 
           new String[] {
             "name", "LogToDatabase_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "empty" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getLogToDatabaseType_Database(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "database", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getLogToDatabaseType_Level(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "level", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getLogToDatabaseType_Selected(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "selected", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (logToFileTypeEClass, 
           source, 
           new String[] {
             "name", "LogToFile_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "empty" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getLogToFileType_Filename(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "filename", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getLogToFileType_Level(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "level", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getLogToFileType_Selected(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "selected", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (logToStdOutTypeEClass, 
           source, 
           new String[] {
             "name", "LogToStdOut_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "empty" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getLogToStdOutType_Level(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "level", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getLogToStdOutType_Selected(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "selected", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (metadataTypeEClass, 
           source, 
           new String[] {
             "name", "Metadata_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getMetadataType_Column(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "Column", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getMetadataType_Comment(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "Comment", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getMetadataType_Name(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getMetadataType_Source(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "source", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (nodeTypeEClass, 
           source, 
           new String[] {
             "name", "Node_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getNodeType_ElementParameter(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "ElementParameter", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getNodeType_Metadata(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "Metadata", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getNodeType_BinaryData(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "BinaryData", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getNodeType_StringData(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "StringData", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getNodeType_ComponentName(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "componentName", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getNodeType_ComponentVersion(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "componentVersion", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getNodeType_OffsetLabelX(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "offsetLabelX", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getNodeType_OffsetLabelY(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "offsetLabelY", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getNodeType_PosX(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "posX", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getNodeType_PosY(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "posY", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (processTypeEClass, 
           source, 
           new String[] {
             "name", "Process_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getProcessType_Description(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "Description", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getProcessType_Required(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "Required", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getProcessType_Context(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "Context", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getProcessType_Node(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "Node", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getProcessType_Connection(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "Connection", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getProcessType_Logs(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "Logs", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getProcessType_Author(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "author", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getProcessType_Comment(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "comment", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getProcessType_DefaultContext(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "defaultContext", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getProcessType_Name(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getProcessType_Purpose(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "purpose", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getProcessType_Status(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "status", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getProcessType_Version(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "version", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (requiredTypeEClass, 
           source, 
           new String[] {
             "name", "Required_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRequiredType_Job(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "Job", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });
    }

} //TalendFilePackageImpl
