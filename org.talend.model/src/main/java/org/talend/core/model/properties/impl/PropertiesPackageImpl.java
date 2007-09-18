/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.core.model.properties.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.talend.core.model.metadata.builder.connection.ConnectionPackage;
import org.talend.core.model.properties.BusinessProcessItem;
import org.talend.core.model.properties.ByteArray;
import org.talend.core.model.properties.CSVFileConnectionItem;
import org.talend.core.model.properties.Component;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.ContextItem;
import org.talend.core.model.properties.DatabaseConnectionItem;
import org.talend.core.model.properties.DelimitedFileConnectionItem;
import org.talend.core.model.properties.DocumentationItem;
import org.talend.core.model.properties.FileItem;
import org.talend.core.model.properties.FolderItem;
import org.talend.core.model.properties.FolderType;
import org.talend.core.model.properties.GenericSchemaConnectionItem;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.ItemState;
import org.talend.core.model.properties.LDAPSchemaConnectionItem;
import org.talend.core.model.properties.LDAPSchemaConnection;
import org.talend.core.model.properties.LdifFileConnectionItem;
import org.talend.core.model.properties.License;
import org.talend.core.model.properties.NotationHolder;
import org.talend.core.model.properties.PositionalFileConnectionItem;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.Project;
import org.talend.core.model.properties.ProjectComponentAuthorisation;
import org.talend.core.model.properties.ProjectReference;
import org.talend.core.model.properties.PropertiesFactory;
import org.talend.core.model.properties.PropertiesPackage;
import org.talend.core.model.properties.Property;
import org.talend.core.model.properties.RegExFileConnectionItem;
import org.talend.core.model.properties.RoutineItem;
import org.talend.core.model.properties.SpagoBiServer;
import org.talend.core.model.properties.Status;
import org.talend.core.model.properties.User;
import org.talend.core.model.properties.UserModuleAuthorization;
import org.talend.core.model.properties.UserModuleAuthorizationType;
import org.talend.core.model.properties.UserProjectAuthorization;
import org.talend.core.model.properties.UserProjectAuthorizationType;
import org.talend.core.model.properties.UserRole;
import org.talend.core.model.properties.XmlFileConnectionItem;
import org.talend.designer.business.model.business.BusinessPackage;
import org.talend.designer.core.model.utils.emf.component.ComponentPackage;

import org.talend.designer.core.model.utils.emf.talendfile.TalendFilePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * @generated
 */
public class PropertiesPackageImpl extends EPackageImpl implements PropertiesPackage {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass statusEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass projectEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass propertyEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass itemEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass businessProcessItemEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass itemStateEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fileItemEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass documentationItemEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass routineItemEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass byteArrayEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass connectionItemEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass delimitedFileConnectionItemEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass positionalFileConnectionItemEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass regExFileConnectionItemEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass csvFileConnectionItemEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass databaseConnectionItemEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass processItemEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass userEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass folderItemEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass componentEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlFileConnectionItemEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass notationHolderEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass projectComponentAuthorisationEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass projectReferenceEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass ldifFileConnectionItemEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass userProjectAuthorizationEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass contextItemEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass spagoBiServerEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass licenseEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass genericSchemaConnectionItemEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass userModuleAuthorizationEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass ldapSchemaConnectionItemEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EEnum folderTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum userProjectAuthorizationTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum userModuleAuthorizationTypeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass userRoleEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
     * performs initialization of the package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.talend.core.model.properties.PropertiesPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private PropertiesPackageImpl() {
        super(eNS_URI, PropertiesFactory.eINSTANCE);
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
    public static PropertiesPackage init() {
        if (isInited) return (PropertiesPackage)EPackage.Registry.INSTANCE.getEPackage(PropertiesPackage.eNS_URI);

        // Obtain or create and register package
        PropertiesPackageImpl thePropertiesPackage = (PropertiesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof PropertiesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new PropertiesPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        BusinessPackage.eINSTANCE.eClass();
        ComponentPackage.eINSTANCE.eClass();
        EcorePackage.eINSTANCE.eClass();
        ConnectionPackage.eINSTANCE.eClass();
        NotationPackage.eINSTANCE.eClass();
        TalendFilePackage.eINSTANCE.eClass();
        XMLTypePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        thePropertiesPackage.createPackageContents();

        // Initialize created meta-data
        thePropertiesPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        thePropertiesPackage.freeze();

        return thePropertiesPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getStatus() {
        return statusEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getStatus_Label() {
        return (EAttribute)statusEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getStatus_Code() {
        return (EAttribute)statusEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getProject() {
        return projectEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getProject_TechnicalStatus() {
        return (EReference)projectEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getProject_DocumentationStatus() {
        return (EReference)projectEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProject_Id() {
        return (EAttribute)projectEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProject_Label() {
        return (EAttribute)projectEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProject_Description() {
        return (EAttribute)projectEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProject_Language() {
        return (EAttribute)projectEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getProject_Author() {
        return (EReference)projectEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getProject_UserAuthorization() {
        return (EReference)projectEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getProject_AllowedComponents() {
        return (EReference)projectEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getProject_ReferencedProjects() {
        return (EReference)projectEClass.getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getProject_AvailableRefProject() {
        return (EReference)projectEClass.getEStructuralFeatures().get(16);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProject_MigrationTasks() {
        return (EAttribute)projectEClass.getEStructuralFeatures().get(17);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProject_MasterJobId() {
        return (EAttribute)projectEClass.getEStructuralFeatures().get(18);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getProject_SpagoBiServer() {
        return (EReference)projectEClass.getEStructuralFeatures().get(19);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProject_TechnicalLabel() {
        return (EAttribute)projectEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProject_Local() {
        return (EAttribute)projectEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getProject_Folders() {
        return (EReference)projectEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProject_Deleted() {
        return (EAttribute)projectEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProject_DeleteDate() {
        return (EAttribute)projectEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProject_CreationDate() {
        return (EAttribute)projectEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getProperty() {
        return propertyEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProperty_Id() {
        return (EAttribute)propertyEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProperty_Label() {
        return (EAttribute)propertyEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProperty_Purpose() {
        return (EAttribute)propertyEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProperty_Description() {
        return (EAttribute)propertyEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProperty_CreationDate() {
        return (EAttribute)propertyEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProperty_ModificationDate() {
        return (EAttribute)propertyEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getProperty_Author() {
        return (EReference)propertyEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProperty_Version() {
        return (EAttribute)propertyEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProperty_StatusCode() {
        return (EAttribute)propertyEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getProperty_Item() {
        return (EReference)propertyEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getItem() {
        return itemEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getItem_Property() {
        return (EReference)itemEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getItem_State() {
        return (EReference)itemEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getBusinessProcessItem() {
        return businessProcessItemEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getBusinessProcessItem_Notation() {
        return (EReference)businessProcessItemEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getBusinessProcessItem_Semantic() {
        return (EReference)businessProcessItemEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getBusinessProcessItem_NotationHolder() {
        return (EReference)businessProcessItemEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getItemState() {
        return itemStateEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getItemState_Path() {
        return (EAttribute)itemStateEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getItemState_Deleted() {
        return (EAttribute)itemStateEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getItemState_Locked() {
        return (EAttribute)itemStateEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getItemState_Locker() {
        return (EReference)itemStateEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getItemState_LockDate() {
        return (EAttribute)itemStateEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getItemState_CommitDate() {
        return (EAttribute)itemStateEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getFileItem() {
        return fileItemEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileItem_Name() {
        return (EAttribute)fileItemEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileItem_Extension() {
        return (EAttribute)fileItemEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileItem_Content() {
        return (EReference)fileItemEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getDocumentationItem() {
        return documentationItemEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getRoutineItem() {
        return routineItemEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getRoutineItem_BuiltIn() {
        return (EAttribute)routineItemEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getRoutineItem_Imports() {
        return (EReference)routineItemEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getByteArray() {
        return byteArrayEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getByteArray_InnerContent() {
        return (EAttribute)byteArrayEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getConnectionItem() {
        return connectionItemEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getConnectionItem_Connection() {
        return (EReference)connectionItemEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getDelimitedFileConnectionItem() {
        return delimitedFileConnectionItemEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getPositionalFileConnectionItem() {
        return positionalFileConnectionItemEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getRegExFileConnectionItem() {
        return regExFileConnectionItemEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getCSVFileConnectionItem() {
        return csvFileConnectionItemEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getDatabaseConnectionItem() {
        return databaseConnectionItemEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getProcessItem() {
        return processItemEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getProcessItem_Process() {
        return (EReference)processItemEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getUser() {
        return userEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getUser_Id() {
        return (EAttribute)userEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getUser_Login() {
        return (EAttribute)userEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getUser_Password() {
        return (EAttribute)userEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getUser_FirstName() {
        return (EAttribute)userEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getUser_LastName() {
        return (EAttribute)userEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getUser_CreationDate() {
        return (EAttribute)userEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getUser_DeleteDate() {
        return (EAttribute)userEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getUser_Deleted() {
        return (EAttribute)userEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getUser_AllowedToModifyComponents() {
        return (EAttribute)userEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getUser_Comment() {
        return (EAttribute)userEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getUser_Role() {
        return (EReference)userEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getUser_ProjectAuthorization() {
        return (EReference)userEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getUser_ModuleAuthorization() {
        return (EReference)userEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getFolderItem() {
        return folderItemEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getFolderItem_Children() {
        return (EReference)folderItemEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFolderItem_Type() {
        return (EAttribute)folderItemEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getComponent() {
        return componentEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getComponent_Id() {
        return (EAttribute)componentEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getComponent_Label() {
        return (EAttribute)componentEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getComponent_Version() {
        return (EAttribute)componentEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getComponent_LastUpdateDate() {
        return (EAttribute)componentEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getComponent_Projects() {
        return (EReference)componentEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getComponent_Author() {
        return (EReference)componentEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getComponent_CreationDate() {
        return (EAttribute)componentEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getComponent_DeleteDate() {
        return (EAttribute)componentEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getComponent_Deleted() {
        return (EAttribute)componentEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getComponent_FileDescriptor() {
        return (EAttribute)componentEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getXmlFileConnectionItem() {
        return xmlFileConnectionItemEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getNotationHolder() {
        return notationHolderEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNotationHolder_NotationString() {
        return (EAttribute)notationHolderEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getProjectComponentAuthorisation() {
        return projectComponentAuthorisationEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getProjectComponentAuthorisation_Project() {
        return (EReference)projectComponentAuthorisationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getProjectComponentAuthorisation_Component() {
        return (EReference)projectComponentAuthorisationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getProjectReference() {
        return projectReferenceEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getProjectReference_Project() {
        return (EReference)projectReferenceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getProjectReference_ReferencedProject() {
        return (EReference)projectReferenceEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLdifFileConnectionItem() {
        return ldifFileConnectionItemEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getUserProjectAuthorization() {
        return userProjectAuthorizationEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getUserProjectAuthorization_User() {
        return (EReference)userProjectAuthorizationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getUserProjectAuthorization_Project() {
        return (EReference)userProjectAuthorizationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getUserProjectAuthorization_Type() {
        return (EAttribute)userProjectAuthorizationEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getContextItem() {
        return contextItemEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getContextItem_Context() {
        return (EReference)contextItemEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getContextItem_DefaultContext() {
        return (EAttribute)contextItemEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSpagoBiServer() {
        return spagoBiServerEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSpagoBiServer_EngineName() {
        return (EAttribute)spagoBiServerEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSpagoBiServer_ShortDescription() {
        return (EAttribute)spagoBiServerEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSpagoBiServer_Host() {
        return (EAttribute)spagoBiServerEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSpagoBiServer_Port() {
        return (EAttribute)spagoBiServerEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSpagoBiServer_Login() {
        return (EAttribute)spagoBiServerEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSpagoBiServer_Password() {
        return (EAttribute)spagoBiServerEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSpagoBiServer_ApplicationContext() {
        return (EAttribute)spagoBiServerEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLicense() {
        return licenseEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLicense_License() {
        return (EAttribute)licenseEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getGenericSchemaConnectionItem() {
        return genericSchemaConnectionItemEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getUserModuleAuthorization() {
        return userModuleAuthorizationEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getUserModuleAuthorization_User() {
        return (EReference)userModuleAuthorizationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getUserModuleAuthorization_Type() {
        return (EAttribute)userModuleAuthorizationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLDAPSchemaConnectionItem() {
        return ldapSchemaConnectionItemEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EEnum getFolderType() {
        return folderTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getUserProjectAuthorizationType() {
        return userProjectAuthorizationTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getUserModuleAuthorizationType() {
        return userModuleAuthorizationTypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getUserRole() {
        return userRoleEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getUserRole_Id() {
        return (EAttribute)userRoleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getUserRole_Name() {
        return (EAttribute)userRoleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getUserRole_LocalizedLabel() {
        return (EAttribute)userRoleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public PropertiesFactory getPropertiesFactory() {
        return (PropertiesFactory)getEFactoryInstance();
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
        projectEClass = createEClass(PROJECT);
        createEReference(projectEClass, PROJECT__TECHNICAL_STATUS);
        createEReference(projectEClass, PROJECT__DOCUMENTATION_STATUS);
        createEAttribute(projectEClass, PROJECT__ID);
        createEAttribute(projectEClass, PROJECT__LABEL);
        createEAttribute(projectEClass, PROJECT__DESCRIPTION);
        createEAttribute(projectEClass, PROJECT__LANGUAGE);
        createEAttribute(projectEClass, PROJECT__TECHNICAL_LABEL);
        createEAttribute(projectEClass, PROJECT__LOCAL);
        createEReference(projectEClass, PROJECT__FOLDERS);
        createEAttribute(projectEClass, PROJECT__DELETED);
        createEAttribute(projectEClass, PROJECT__DELETE_DATE);
        createEAttribute(projectEClass, PROJECT__CREATION_DATE);
        createEReference(projectEClass, PROJECT__AUTHOR);
        createEReference(projectEClass, PROJECT__USER_AUTHORIZATION);
        createEReference(projectEClass, PROJECT__ALLOWED_COMPONENTS);
        createEReference(projectEClass, PROJECT__REFERENCED_PROJECTS);
        createEReference(projectEClass, PROJECT__AVAILABLE_REF_PROJECT);
        createEAttribute(projectEClass, PROJECT__MIGRATION_TASKS);
        createEAttribute(projectEClass, PROJECT__MASTER_JOB_ID);
        createEReference(projectEClass, PROJECT__SPAGO_BI_SERVER);

        projectComponentAuthorisationEClass = createEClass(PROJECT_COMPONENT_AUTHORISATION);
        createEReference(projectComponentAuthorisationEClass, PROJECT_COMPONENT_AUTHORISATION__PROJECT);
        createEReference(projectComponentAuthorisationEClass, PROJECT_COMPONENT_AUTHORISATION__COMPONENT);

        projectReferenceEClass = createEClass(PROJECT_REFERENCE);
        createEReference(projectReferenceEClass, PROJECT_REFERENCE__PROJECT);
        createEReference(projectReferenceEClass, PROJECT_REFERENCE__REFERENCED_PROJECT);

        statusEClass = createEClass(STATUS);
        createEAttribute(statusEClass, STATUS__LABEL);
        createEAttribute(statusEClass, STATUS__CODE);

        itemStateEClass = createEClass(ITEM_STATE);
        createEAttribute(itemStateEClass, ITEM_STATE__PATH);
        createEAttribute(itemStateEClass, ITEM_STATE__DELETED);
        createEAttribute(itemStateEClass, ITEM_STATE__LOCKED);
        createEReference(itemStateEClass, ITEM_STATE__LOCKER);
        createEAttribute(itemStateEClass, ITEM_STATE__LOCK_DATE);
        createEAttribute(itemStateEClass, ITEM_STATE__COMMIT_DATE);

        propertyEClass = createEClass(PROPERTY);
        createEAttribute(propertyEClass, PROPERTY__ID);
        createEAttribute(propertyEClass, PROPERTY__LABEL);
        createEAttribute(propertyEClass, PROPERTY__PURPOSE);
        createEAttribute(propertyEClass, PROPERTY__DESCRIPTION);
        createEAttribute(propertyEClass, PROPERTY__CREATION_DATE);
        createEAttribute(propertyEClass, PROPERTY__MODIFICATION_DATE);
        createEAttribute(propertyEClass, PROPERTY__VERSION);
        createEAttribute(propertyEClass, PROPERTY__STATUS_CODE);
        createEReference(propertyEClass, PROPERTY__ITEM);
        createEReference(propertyEClass, PROPERTY__AUTHOR);

        itemEClass = createEClass(ITEM);
        createEReference(itemEClass, ITEM__PROPERTY);
        createEReference(itemEClass, ITEM__STATE);

        businessProcessItemEClass = createEClass(BUSINESS_PROCESS_ITEM);
        createEReference(businessProcessItemEClass, BUSINESS_PROCESS_ITEM__NOTATION);
        createEReference(businessProcessItemEClass, BUSINESS_PROCESS_ITEM__SEMANTIC);
        createEReference(businessProcessItemEClass, BUSINESS_PROCESS_ITEM__NOTATION_HOLDER);

        fileItemEClass = createEClass(FILE_ITEM);
        createEAttribute(fileItemEClass, FILE_ITEM__NAME);
        createEAttribute(fileItemEClass, FILE_ITEM__EXTENSION);
        createEReference(fileItemEClass, FILE_ITEM__CONTENT);

        byteArrayEClass = createEClass(BYTE_ARRAY);
        createEAttribute(byteArrayEClass, BYTE_ARRAY__INNER_CONTENT);

        documentationItemEClass = createEClass(DOCUMENTATION_ITEM);

        routineItemEClass = createEClass(ROUTINE_ITEM);
        createEAttribute(routineItemEClass, ROUTINE_ITEM__BUILT_IN);
        createEReference(routineItemEClass, ROUTINE_ITEM__IMPORTS);

        connectionItemEClass = createEClass(CONNECTION_ITEM);
        createEReference(connectionItemEClass, CONNECTION_ITEM__CONNECTION);

        delimitedFileConnectionItemEClass = createEClass(DELIMITED_FILE_CONNECTION_ITEM);

        positionalFileConnectionItemEClass = createEClass(POSITIONAL_FILE_CONNECTION_ITEM);

        regExFileConnectionItemEClass = createEClass(REG_EX_FILE_CONNECTION_ITEM);

        csvFileConnectionItemEClass = createEClass(CSV_FILE_CONNECTION_ITEM);

        databaseConnectionItemEClass = createEClass(DATABASE_CONNECTION_ITEM);

        xmlFileConnectionItemEClass = createEClass(XML_FILE_CONNECTION_ITEM);

        ldifFileConnectionItemEClass = createEClass(LDIF_FILE_CONNECTION_ITEM);

        processItemEClass = createEClass(PROCESS_ITEM);
        createEReference(processItemEClass, PROCESS_ITEM__PROCESS);

        userRoleEClass = createEClass(USER_ROLE);
        createEAttribute(userRoleEClass, USER_ROLE__ID);
        createEAttribute(userRoleEClass, USER_ROLE__NAME);
        createEAttribute(userRoleEClass, USER_ROLE__LOCALIZED_LABEL);

        userEClass = createEClass(USER);
        createEAttribute(userEClass, USER__ID);
        createEAttribute(userEClass, USER__LOGIN);
        createEAttribute(userEClass, USER__PASSWORD);
        createEAttribute(userEClass, USER__FIRST_NAME);
        createEAttribute(userEClass, USER__LAST_NAME);
        createEAttribute(userEClass, USER__CREATION_DATE);
        createEAttribute(userEClass, USER__DELETE_DATE);
        createEAttribute(userEClass, USER__DELETED);
        createEAttribute(userEClass, USER__ALLOWED_TO_MODIFY_COMPONENTS);
        createEAttribute(userEClass, USER__COMMENT);
        createEReference(userEClass, USER__ROLE);
        createEReference(userEClass, USER__PROJECT_AUTHORIZATION);
        createEReference(userEClass, USER__MODULE_AUTHORIZATION);

        folderItemEClass = createEClass(FOLDER_ITEM);
        createEReference(folderItemEClass, FOLDER_ITEM__CHILDREN);
        createEAttribute(folderItemEClass, FOLDER_ITEM__TYPE);

        componentEClass = createEClass(COMPONENT);
        createEAttribute(componentEClass, COMPONENT__ID);
        createEAttribute(componentEClass, COMPONENT__LABEL);
        createEAttribute(componentEClass, COMPONENT__VERSION);
        createEAttribute(componentEClass, COMPONENT__LAST_UPDATE_DATE);
        createEReference(componentEClass, COMPONENT__AUTHOR);
        createEAttribute(componentEClass, COMPONENT__CREATION_DATE);
        createEAttribute(componentEClass, COMPONENT__DELETE_DATE);
        createEAttribute(componentEClass, COMPONENT__DELETED);
        createEAttribute(componentEClass, COMPONENT__FILE_DESCRIPTOR);
        createEReference(componentEClass, COMPONENT__PROJECTS);

        notationHolderEClass = createEClass(NOTATION_HOLDER);
        createEAttribute(notationHolderEClass, NOTATION_HOLDER__NOTATION_STRING);

        userProjectAuthorizationEClass = createEClass(USER_PROJECT_AUTHORIZATION);
        createEReference(userProjectAuthorizationEClass, USER_PROJECT_AUTHORIZATION__USER);
        createEReference(userProjectAuthorizationEClass, USER_PROJECT_AUTHORIZATION__PROJECT);
        createEAttribute(userProjectAuthorizationEClass, USER_PROJECT_AUTHORIZATION__TYPE);

        contextItemEClass = createEClass(CONTEXT_ITEM);
        createEReference(contextItemEClass, CONTEXT_ITEM__CONTEXT);
        createEAttribute(contextItemEClass, CONTEXT_ITEM__DEFAULT_CONTEXT);

        spagoBiServerEClass = createEClass(SPAGO_BI_SERVER);
        createEAttribute(spagoBiServerEClass, SPAGO_BI_SERVER__ENGINE_NAME);
        createEAttribute(spagoBiServerEClass, SPAGO_BI_SERVER__SHORT_DESCRIPTION);
        createEAttribute(spagoBiServerEClass, SPAGO_BI_SERVER__HOST);
        createEAttribute(spagoBiServerEClass, SPAGO_BI_SERVER__PORT);
        createEAttribute(spagoBiServerEClass, SPAGO_BI_SERVER__LOGIN);
        createEAttribute(spagoBiServerEClass, SPAGO_BI_SERVER__PASSWORD);
        createEAttribute(spagoBiServerEClass, SPAGO_BI_SERVER__APPLICATION_CONTEXT);

        licenseEClass = createEClass(LICENSE);
        createEAttribute(licenseEClass, LICENSE__LICENSE);

        genericSchemaConnectionItemEClass = createEClass(GENERIC_SCHEMA_CONNECTION_ITEM);

        userModuleAuthorizationEClass = createEClass(USER_MODULE_AUTHORIZATION);
        createEReference(userModuleAuthorizationEClass, USER_MODULE_AUTHORIZATION__USER);
        createEAttribute(userModuleAuthorizationEClass, USER_MODULE_AUTHORIZATION__TYPE);

        ldapSchemaConnectionItemEClass = createEClass(LDAP_SCHEMA_CONNECTION_ITEM);

        // Create enums
        folderTypeEEnum = createEEnum(FOLDER_TYPE);
        userProjectAuthorizationTypeEEnum = createEEnum(USER_PROJECT_AUTHORIZATION_TYPE);
        userModuleAuthorizationTypeEEnum = createEEnum(USER_MODULE_AUTHORIZATION_TYPE);
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
        EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
        XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);
        NotationPackage theNotationPackage = (NotationPackage)EPackage.Registry.INSTANCE.getEPackage(NotationPackage.eNS_URI);
        BusinessPackage theBusinessPackage = (BusinessPackage)EPackage.Registry.INSTANCE.getEPackage(BusinessPackage.eNS_URI);
        ComponentPackage theComponentPackage = (ComponentPackage)EPackage.Registry.INSTANCE.getEPackage(ComponentPackage.eNS_URI);
        ConnectionPackage theConnectionPackage = (ConnectionPackage)EPackage.Registry.INSTANCE.getEPackage(ConnectionPackage.eNS_URI);
        TalendFilePackage theTalendFilePackage = (TalendFilePackage)EPackage.Registry.INSTANCE.getEPackage(TalendFilePackage.eNS_URI);

        // Add supertypes to classes
        businessProcessItemEClass.getESuperTypes().add(this.getItem());
        fileItemEClass.getESuperTypes().add(this.getItem());
        documentationItemEClass.getESuperTypes().add(this.getFileItem());
        routineItemEClass.getESuperTypes().add(this.getFileItem());
        connectionItemEClass.getESuperTypes().add(this.getItem());
        delimitedFileConnectionItemEClass.getESuperTypes().add(this.getConnectionItem());
        positionalFileConnectionItemEClass.getESuperTypes().add(this.getConnectionItem());
        regExFileConnectionItemEClass.getESuperTypes().add(this.getConnectionItem());
        csvFileConnectionItemEClass.getESuperTypes().add(this.getDelimitedFileConnectionItem());
        databaseConnectionItemEClass.getESuperTypes().add(this.getConnectionItem());
        xmlFileConnectionItemEClass.getESuperTypes().add(this.getConnectionItem());
        ldifFileConnectionItemEClass.getESuperTypes().add(this.getConnectionItem());
        processItemEClass.getESuperTypes().add(this.getItem());
        folderItemEClass.getESuperTypes().add(this.getItem());
        contextItemEClass.getESuperTypes().add(this.getItem());
        genericSchemaConnectionItemEClass.getESuperTypes().add(this.getConnectionItem());
        ldapSchemaConnectionItemEClass.getESuperTypes().add(this.getConnectionItem());

        // Initialize classes and features; add operations and parameters
        initEClass(projectEClass, Project.class, "Project", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getProject_TechnicalStatus(), this.getStatus(), null, "technicalStatus", null, 0, -1, Project.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getProject_DocumentationStatus(), this.getStatus(), null, "documentationStatus", null, 0, -1, Project.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getProject_Id(), ecorePackage.getEInt(), "id", null, 1, 1, Project.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getProject_Label(), ecorePackage.getEString(), "label", null, 1, 1, Project.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getProject_Description(), ecorePackage.getEString(), "description", null, 1, 1, Project.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getProject_Language(), ecorePackage.getEString(), "language", null, 1, 1, Project.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getProject_TechnicalLabel(), ecorePackage.getEString(), "technicalLabel", null, 1, 1, Project.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getProject_Local(), ecorePackage.getEBoolean(), "local", null, 1, 1, Project.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getProject_Folders(), this.getFolderItem(), null, "folders", null, 0, -1, Project.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getProject_Deleted(), ecorePackage.getEBoolean(), "deleted", null, 1, 1, Project.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getProject_DeleteDate(), ecorePackage.getEDate(), "deleteDate", null, 0, 1, Project.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getProject_CreationDate(), ecorePackage.getEDate(), "creationDate", null, 1, 1, Project.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getProject_Author(), this.getUser(), null, "author", null, 0, 1, Project.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getProject_UserAuthorization(), this.getUserProjectAuthorization(), this.getUserProjectAuthorization_Project(), "userAuthorization", null, 0, -1, Project.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getProject_AllowedComponents(), this.getProjectComponentAuthorisation(), this.getProjectComponentAuthorisation_Project(), "allowedComponents", null, 0, -1, Project.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getProject_ReferencedProjects(), this.getProjectReference(), this.getProjectReference_Project(), "referencedProjects", null, 0, -1, Project.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getProject_AvailableRefProject(), this.getProjectReference(), this.getProjectReference_ReferencedProject(), "availableRefProject", null, 0, -1, Project.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getProject_MigrationTasks(), theEcorePackage.getEString(), "migrationTasks", null, 0, -1, Project.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getProject_MasterJobId(), theXMLTypePackage.getString(), "masterJobId", null, 0, 1, Project.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getProject_SpagoBiServer(), this.getSpagoBiServer(), null, "spagoBiServer", null, 0, -1, Project.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(projectComponentAuthorisationEClass, ProjectComponentAuthorisation.class, "ProjectComponentAuthorisation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getProjectComponentAuthorisation_Project(), this.getProject(), this.getProject_AllowedComponents(), "project", null, 1, 1, ProjectComponentAuthorisation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getProjectComponentAuthorisation_Component(), this.getComponent(), this.getComponent_Projects(), "component", null, 1, 1, ProjectComponentAuthorisation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(projectReferenceEClass, ProjectReference.class, "ProjectReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getProjectReference_Project(), this.getProject(), this.getProject_ReferencedProjects(), "project", null, 1, 1, ProjectReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getProjectReference_ReferencedProject(), this.getProject(), this.getProject_AvailableRefProject(), "referencedProject", null, 1, 1, ProjectReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(statusEClass, Status.class, "Status", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getStatus_Label(), ecorePackage.getEString(), "label", null, 0, 1, Status.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getStatus_Code(), ecorePackage.getEString(), "code", null, 1, 1, Status.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(itemStateEClass, ItemState.class, "ItemState", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getItemState_Path(), ecorePackage.getEString(), "path", null, 0, 1, ItemState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getItemState_Deleted(), ecorePackage.getEBoolean(), "deleted", null, 0, 1, ItemState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getItemState_Locked(), ecorePackage.getEBoolean(), "locked", null, 0, 1, ItemState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getItemState_Locker(), this.getUser(), null, "locker", null, 0, 1, ItemState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getItemState_LockDate(), ecorePackage.getEDate(), "lockDate", null, 0, 1, ItemState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getItemState_CommitDate(), ecorePackage.getEDate(), "commitDate", null, 0, 1, ItemState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(propertyEClass, Property.class, "Property", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getProperty_Id(), ecorePackage.getEString(), "id", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getProperty_Label(), ecorePackage.getEString(), "label", null, 1, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getProperty_Purpose(), ecorePackage.getEString(), "purpose", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getProperty_Description(), ecorePackage.getEString(), "description", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getProperty_CreationDate(), ecorePackage.getEDate(), "creationDate", null, 1, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getProperty_ModificationDate(), ecorePackage.getEDate(), "modificationDate", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getProperty_Version(), ecorePackage.getEString(), "version", null, 1, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getProperty_StatusCode(), ecorePackage.getEString(), "statusCode", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getProperty_Item(), this.getItem(), this.getItem_Property(), "item", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getProperty_Author(), this.getUser(), null, "author", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(itemEClass, Item.class, "Item", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getItem_Property(), this.getProperty(), this.getProperty_Item(), "property", null, 0, 1, Item.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getItem_State(), this.getItemState(), null, "state", null, 0, 1, Item.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(businessProcessItemEClass, BusinessProcessItem.class, "BusinessProcessItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getBusinessProcessItem_Notation(), theNotationPackage.getDiagram(), null, "notation", null, 0, 1, BusinessProcessItem.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBusinessProcessItem_Semantic(), theBusinessPackage.getBusinessProcess(), null, "semantic", null, 0, 1, BusinessProcessItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBusinessProcessItem_NotationHolder(), this.getNotationHolder(), null, "notationHolder", null, 0, 1, BusinessProcessItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fileItemEClass, FileItem.class, "FileItem", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFileItem_Name(), ecorePackage.getEString(), "name", null, 0, 1, FileItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFileItem_Extension(), ecorePackage.getEString(), "extension", null, 0, 1, FileItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFileItem_Content(), this.getByteArray(), null, "content", null, 0, 1, FileItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(byteArrayEClass, ByteArray.class, "ByteArray", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getByteArray_InnerContent(), ecorePackage.getEByteArray(), "innerContent", null, 0, 1, ByteArray.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(documentationItemEClass, DocumentationItem.class, "DocumentationItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(routineItemEClass, RoutineItem.class, "RoutineItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRoutineItem_BuiltIn(), ecorePackage.getEBoolean(), "builtIn", "false", 0, 1, RoutineItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getRoutineItem_Imports(), theComponentPackage.getIMPORTType(), null, "imports", null, 0, -1, RoutineItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(connectionItemEClass, ConnectionItem.class, "ConnectionItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getConnectionItem_Connection(), theConnectionPackage.getConnection(), null, "connection", null, 0, 1, ConnectionItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(delimitedFileConnectionItemEClass, DelimitedFileConnectionItem.class, "DelimitedFileConnectionItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(positionalFileConnectionItemEClass, PositionalFileConnectionItem.class, "PositionalFileConnectionItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(regExFileConnectionItemEClass, RegExFileConnectionItem.class, "RegExFileConnectionItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(csvFileConnectionItemEClass, CSVFileConnectionItem.class, "CSVFileConnectionItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(databaseConnectionItemEClass, DatabaseConnectionItem.class, "DatabaseConnectionItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(xmlFileConnectionItemEClass, XmlFileConnectionItem.class, "XmlFileConnectionItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(ldifFileConnectionItemEClass, LdifFileConnectionItem.class, "LdifFileConnectionItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(processItemEClass, ProcessItem.class, "ProcessItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getProcessItem_Process(), theTalendFilePackage.getProcessType(), null, "process", null, 0, 1, ProcessItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(userRoleEClass, UserRole.class, "UserRole", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getUserRole_Id(), ecorePackage.getEInt(), "id", null, 1, 1, UserRole.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getUserRole_Name(), ecorePackage.getEString(), "name", null, 1, 1, UserRole.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getUserRole_LocalizedLabel(), ecorePackage.getEString(), "localizedLabel", null, 1, 1, UserRole.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(userEClass, User.class, "User", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getUser_Id(), ecorePackage.getEInt(), "id", null, 1, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getUser_Login(), ecorePackage.getEString(), "login", null, 1, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getUser_Password(), ecorePackage.getEByteArray(), "password", null, 1, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getUser_FirstName(), ecorePackage.getEString(), "firstName", null, 1, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getUser_LastName(), ecorePackage.getEString(), "lastName", null, 1, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getUser_CreationDate(), ecorePackage.getEDate(), "creationDate", null, 1, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getUser_DeleteDate(), ecorePackage.getEDate(), "deleteDate", null, 0, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getUser_Deleted(), ecorePackage.getEBoolean(), "deleted", null, 0, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getUser_AllowedToModifyComponents(), ecorePackage.getEBoolean(), "allowedToModifyComponents", null, 0, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getUser_Comment(), ecorePackage.getEString(), "Comment", null, 0, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getUser_Role(), this.getUserRole(), null, "role", null, 1, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getUser_ProjectAuthorization(), this.getUserProjectAuthorization(), this.getUserProjectAuthorization_User(), "projectAuthorization", null, 0, -1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getUser_ModuleAuthorization(), this.getUserModuleAuthorization(), this.getUserModuleAuthorization_User(), "moduleAuthorization", null, 0, -1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(folderItemEClass, FolderItem.class, "FolderItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFolderItem_Children(), this.getItem(), null, "children", null, 0, -1, FolderItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFolderItem_Type(), this.getFolderType(), "type", null, 1, 1, FolderItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(componentEClass, Component.class, "Component", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getComponent_Id(), ecorePackage.getEInt(), "id", null, 1, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getComponent_Label(), ecorePackage.getEString(), "label", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getComponent_Version(), ecorePackage.getEFloat(), "version", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getComponent_LastUpdateDate(), ecorePackage.getEDate(), "lastUpdateDate", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getComponent_Author(), this.getUser(), null, "author", null, 1, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getComponent_CreationDate(), ecorePackage.getEDate(), "creationDate", null, 1, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getComponent_DeleteDate(), ecorePackage.getEDate(), "deleteDate", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getComponent_Deleted(), ecorePackage.getEBoolean(), "deleted", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getComponent_FileDescriptor(), ecorePackage.getEByteArray(), "fileDescriptor", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getComponent_Projects(), this.getProjectComponentAuthorisation(), this.getProjectComponentAuthorisation_Component(), "projects", null, 0, -1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(notationHolderEClass, NotationHolder.class, "NotationHolder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getNotationHolder_NotationString(), theEcorePackage.getEString(), "notationString", null, 0, 1, NotationHolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(userProjectAuthorizationEClass, UserProjectAuthorization.class, "UserProjectAuthorization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getUserProjectAuthorization_User(), this.getUser(), this.getUser_ProjectAuthorization(), "user", null, 0, 1, UserProjectAuthorization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getUserProjectAuthorization_Project(), this.getProject(), this.getProject_UserAuthorization(), "project", null, 0, 1, UserProjectAuthorization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getUserProjectAuthorization_Type(), this.getUserProjectAuthorizationType(), "type", "ReadWrite", 0, 1, UserProjectAuthorization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(contextItemEClass, ContextItem.class, "ContextItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getContextItem_Context(), theTalendFilePackage.getContextType(), null, "context", null, 0, -1, ContextItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getContextItem_DefaultContext(), theXMLTypePackage.getString(), "defaultContext", null, 0, 1, ContextItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(spagoBiServerEClass, SpagoBiServer.class, "SpagoBiServer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSpagoBiServer_EngineName(), ecorePackage.getEString(), "engineName", null, 1, 1, SpagoBiServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSpagoBiServer_ShortDescription(), ecorePackage.getEString(), "shortDescription", null, 0, 1, SpagoBiServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSpagoBiServer_Host(), ecorePackage.getEString(), "host", null, 0, 1, SpagoBiServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSpagoBiServer_Port(), ecorePackage.getEString(), "port", null, 0, 1, SpagoBiServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSpagoBiServer_Login(), theEcorePackage.getEString(), "login", null, 0, 1, SpagoBiServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSpagoBiServer_Password(), ecorePackage.getEString(), "password", null, 0, 1, SpagoBiServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSpagoBiServer_ApplicationContext(), theEcorePackage.getEString(), "applicationContext", null, 0, 1, SpagoBiServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(licenseEClass, License.class, "License", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLicense_License(), ecorePackage.getEByteArray(), "license", null, 0, 1, License.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(genericSchemaConnectionItemEClass, GenericSchemaConnectionItem.class, "GenericSchemaConnectionItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(userModuleAuthorizationEClass, UserModuleAuthorization.class, "UserModuleAuthorization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getUserModuleAuthorization_User(), this.getUser(), this.getUser_ModuleAuthorization(), "user", null, 0, 1, UserModuleAuthorization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getUserModuleAuthorization_Type(), this.getUserModuleAuthorizationType(), "type", "Scheduler", 0, 1, UserModuleAuthorization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(ldapSchemaConnectionItemEClass, LDAPSchemaConnectionItem.class, "LDAPSchemaConnectionItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        // Initialize enums and add enum literals
        initEEnum(folderTypeEEnum, FolderType.class, "FolderType");
        addEEnumLiteral(folderTypeEEnum, FolderType.FOLDER_LITERAL);
        addEEnumLiteral(folderTypeEEnum, FolderType.SYSTEM_FOLDER_LITERAL);
        addEEnumLiteral(folderTypeEEnum, FolderType.STABLE_SYSTEM_FOLDER_LITERAL);

        initEEnum(userProjectAuthorizationTypeEEnum, UserProjectAuthorizationType.class, "UserProjectAuthorizationType");
        addEEnumLiteral(userProjectAuthorizationTypeEEnum, UserProjectAuthorizationType.READ_WRITE_LITERAL);
        addEEnumLiteral(userProjectAuthorizationTypeEEnum, UserProjectAuthorizationType.READ_ONLY_LITERAL);

        initEEnum(userModuleAuthorizationTypeEEnum, UserModuleAuthorizationType.class, "UserModuleAuthorizationType");
        addEEnumLiteral(userModuleAuthorizationTypeEEnum, UserModuleAuthorizationType.SCHEDULER_LITERAL);
        addEEnumLiteral(userModuleAuthorizationTypeEEnum, UserModuleAuthorizationType.DASHBOARD_LITERAL);

        // Create resource
        createResource(eNS_URI);
    }

} // PropertiesPackageImpl
