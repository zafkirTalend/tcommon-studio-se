/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.designer.business.model.business.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.talend.designer.business.model.business.ActionBusinessItem;
import org.talend.designer.business.model.business.ActorBusinessItem;
import org.talend.designer.business.model.business.BusinessAssignment;
import org.talend.designer.business.model.business.BusinessFactory;
import org.talend.designer.business.model.business.BusinessItemRelationship;
import org.talend.designer.business.model.business.BusinessPackage;
import org.talend.designer.business.model.business.BusinessProcess;
import org.talend.designer.business.model.business.DataBusinessItem;
import org.talend.designer.business.model.business.DatabaseBusinessItem;
import org.talend.designer.business.model.business.DatabaseMetadata;
import org.talend.designer.business.model.business.DecisionBusinessItem;
import org.talend.designer.business.model.business.DocumentBusinessItem;
import org.talend.designer.business.model.business.Documentation;
import org.talend.designer.business.model.business.EllipseBusinessItem;
import org.talend.designer.business.model.business.FileDelimitedMetadata;
import org.talend.designer.business.model.business.FileLdifMetadata;
import org.talend.designer.business.model.business.FilePositionalMetadata;
import org.talend.designer.business.model.business.FileRegexpMetadata;
import org.talend.designer.business.model.business.FileXmlMetadata;
import org.talend.designer.business.model.business.GearBusinessItem;
import org.talend.designer.business.model.business.InputBusinessItem;
import org.talend.designer.business.model.business.ListBusinessItem;
import org.talend.designer.business.model.business.Repository;
import org.talend.designer.business.model.business.Routine;
import org.talend.designer.business.model.business.TableMetadata;
import org.talend.designer.business.model.business.TerminalBusinessItem;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * @generated
 */
public class BusinessFactoryImpl extends EFactoryImpl implements BusinessFactory {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = ""; //$NON-NLS-1$

    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated NOT
     */
    public static BusinessFactory init() {
        try {
            BusinessFactory theBusinessFactory = (BusinessFactory)EPackage.Registry.INSTANCE.getEFactory("business");  //$NON-NLS-1$
            if (theBusinessFactory != null) {
                return theBusinessFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new BusinessFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public BusinessFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case BusinessPackage.REPOSITORY: return createRepository();
            case BusinessPackage.BUSINESS_PROCESS: return createBusinessProcess();
            case BusinessPackage.PROCESS: return createProcess();
            case BusinessPackage.ROUTINE: return createRoutine();
            case BusinessPackage.DOCUMENTATION: return createDocumentation();
            case BusinessPackage.DATABASE_METADATA: return createDatabaseMetadata();
            case BusinessPackage.TABLE_METADATA: return createTableMetadata();
            case BusinessPackage.FILE_DELIMITED_METADATA: return createFileDelimitedMetadata();
            case BusinessPackage.FILE_POSITIONAL_METADATA: return createFilePositionalMetadata();
            case BusinessPackage.BUSINESS_ASSIGNMENT: return createBusinessAssignment();
            case BusinessPackage.BUSINESS_ITEM_RELATIONSHIP: return createBusinessItemRelationship();
            case BusinessPackage.DECISION_BUSINESS_ITEM: return createDecisionBusinessItem();
            case BusinessPackage.ACTION_BUSINESS_ITEM: return createActionBusinessItem();
            case BusinessPackage.TERMINAL_BUSINESS_ITEM: return createTerminalBusinessItem();
            case BusinessPackage.DATA_BUSINESS_ITEM: return createDataBusinessItem();
            case BusinessPackage.DOCUMENT_BUSINESS_ITEM: return createDocumentBusinessItem();
            case BusinessPackage.INPUT_BUSINESS_ITEM: return createInputBusinessItem();
            case BusinessPackage.LIST_BUSINESS_ITEM: return createListBusinessItem();
            case BusinessPackage.DATABASE_BUSINESS_ITEM: return createDatabaseBusinessItem();
            case BusinessPackage.ACTOR_BUSINESS_ITEM: return createActorBusinessItem();
            case BusinessPackage.ELLIPSE_BUSINESS_ITEM: return createEllipseBusinessItem();
            case BusinessPackage.GEAR_BUSINESS_ITEM: return createGearBusinessItem();
            case BusinessPackage.FILE_REGEXP_METADATA: return createFileRegexpMetadata();
            case BusinessPackage.FILE_XML_METADATA: return createFileXmlMetadata();
            case BusinessPackage.FILE_LDIF_METADATA: return createFileLdifMetadata();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Repository createRepository() {
        RepositoryImpl repository = new RepositoryImpl();
        return repository;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public BusinessProcess createBusinessProcess() {
        BusinessProcessImpl businessProcess = new BusinessProcessImpl();
        return businessProcess;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public org.talend.designer.business.model.business.Process createProcess() {
        ProcessImpl process = new ProcessImpl();
        return process;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Routine createRoutine() {
        RoutineImpl routine = new RoutineImpl();
        return routine;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Documentation createDocumentation() {
        DocumentationImpl documentation = new DocumentationImpl();
        return documentation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public DatabaseMetadata createDatabaseMetadata() {
        DatabaseMetadataImpl databaseMetadata = new DatabaseMetadataImpl();
        return databaseMetadata;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public TableMetadata createTableMetadata() {
        TableMetadataImpl tableMetadata = new TableMetadataImpl();
        return tableMetadata;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FileDelimitedMetadata createFileDelimitedMetadata() {
        FileDelimitedMetadataImpl fileDelimitedMetadata = new FileDelimitedMetadataImpl();
        return fileDelimitedMetadata;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FilePositionalMetadata createFilePositionalMetadata() {
        FilePositionalMetadataImpl filePositionalMetadata = new FilePositionalMetadataImpl();
        return filePositionalMetadata;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public BusinessAssignment createBusinessAssignment() {
        BusinessAssignmentImpl businessAssignment = new BusinessAssignmentImpl();
        return businessAssignment;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public BusinessItemRelationship createBusinessItemRelationship() {
        BusinessItemRelationshipImpl businessItemRelationship = new BusinessItemRelationshipImpl();
        return businessItemRelationship;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public DecisionBusinessItem createDecisionBusinessItem() {
        DecisionBusinessItemImpl decisionBusinessItem = new DecisionBusinessItemImpl();
        return decisionBusinessItem;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public ActionBusinessItem createActionBusinessItem() {
        ActionBusinessItemImpl actionBusinessItem = new ActionBusinessItemImpl();
        return actionBusinessItem;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public TerminalBusinessItem createTerminalBusinessItem() {
        TerminalBusinessItemImpl terminalBusinessItem = new TerminalBusinessItemImpl();
        return terminalBusinessItem;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public DataBusinessItem createDataBusinessItem() {
        DataBusinessItemImpl dataBusinessItem = new DataBusinessItemImpl();
        return dataBusinessItem;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public DocumentBusinessItem createDocumentBusinessItem() {
        DocumentBusinessItemImpl documentBusinessItem = new DocumentBusinessItemImpl();
        return documentBusinessItem;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public InputBusinessItem createInputBusinessItem() {
        InputBusinessItemImpl inputBusinessItem = new InputBusinessItemImpl();
        return inputBusinessItem;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public ListBusinessItem createListBusinessItem() {
        ListBusinessItemImpl listBusinessItem = new ListBusinessItemImpl();
        return listBusinessItem;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public DatabaseBusinessItem createDatabaseBusinessItem() {
        DatabaseBusinessItemImpl databaseBusinessItem = new DatabaseBusinessItemImpl();
        return databaseBusinessItem;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FileRegexpMetadata createFileRegexpMetadata() {
        FileRegexpMetadataImpl fileRegexpMetadata = new FileRegexpMetadataImpl();
        return fileRegexpMetadata;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ActorBusinessItem createActorBusinessItem() {
        ActorBusinessItemImpl actorBusinessItem = new ActorBusinessItemImpl();
        return actorBusinessItem;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EllipseBusinessItem createEllipseBusinessItem() {
        EllipseBusinessItemImpl ellipseBusinessItem = new EllipseBusinessItemImpl();
        return ellipseBusinessItem;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GearBusinessItem createGearBusinessItem() {
        GearBusinessItemImpl gearBusinessItem = new GearBusinessItemImpl();
        return gearBusinessItem;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FileXmlMetadata createFileXmlMetadata() {
        FileXmlMetadataImpl fileXmlMetadata = new FileXmlMetadataImpl();
        return fileXmlMetadata;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FileLdifMetadata createFileLdifMetadata() {
        FileLdifMetadataImpl fileLdifMetadata = new FileLdifMetadataImpl();
        return fileLdifMetadata;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public BusinessPackage getBusinessPackage() {
        return (BusinessPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    public static BusinessPackage getPackage() {
        return BusinessPackage.eINSTANCE;
    }

} // BusinessFactoryImpl
