/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.core.model.properties.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.talend.core.model.properties.BusinessProcessItem;
import org.talend.core.model.properties.ByteArray;
import org.talend.core.model.properties.CSVFileConnectionItem;
import org.talend.core.model.properties.Component;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.DatabaseConnectionItem;
import org.talend.core.model.properties.DelimitedFileConnectionItem;
import org.talend.core.model.properties.DocumentationItem;
import org.talend.core.model.properties.FolderItem;
import org.talend.core.model.properties.FolderType;
import org.talend.core.model.properties.ItemState;
import org.talend.core.model.properties.NotationHolder;
import org.talend.core.model.properties.PositionalFileConnectionItem;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.Project;
import org.talend.core.model.properties.PropertiesFactory;
import org.talend.core.model.properties.PropertiesPackage;
import org.talend.core.model.properties.Property;
import org.talend.core.model.properties.RegExFileConnectionItem;
import org.talend.core.model.properties.RoutineItem;
import org.talend.core.model.properties.Status;
import org.talend.core.model.properties.User;
import org.talend.core.model.properties.UserRole;
import org.talend.core.model.properties.XmlFileConnectionItem;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class PropertiesFactoryImpl extends EFactoryImpl implements PropertiesFactory {

    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static PropertiesFactory init() {
        try {
            PropertiesFactory thePropertiesFactory = (PropertiesFactory) EPackage.Registry.INSTANCE
                    .getEFactory("http://www.talend.org/properties");
            if (thePropertiesFactory != null) {
                return thePropertiesFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new PropertiesFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PropertiesFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case PropertiesPackage.PROJECT:
            return createProject();
        case PropertiesPackage.STATUS:
            return createStatus();
        case PropertiesPackage.ITEM_STATE:
            return createItemState();
        case PropertiesPackage.PROPERTY:
            return createProperty();
        case PropertiesPackage.BUSINESS_PROCESS_ITEM:
            return createBusinessProcessItem();
        case PropertiesPackage.BYTE_ARRAY:
            return createByteArray();
        case PropertiesPackage.DOCUMENTATION_ITEM:
            return createDocumentationItem();
        case PropertiesPackage.ROUTINE_ITEM:
            return createRoutineItem();
        case PropertiesPackage.CONNECTION_ITEM:
            return createConnectionItem();
        case PropertiesPackage.DELIMITED_FILE_CONNECTION_ITEM:
            return createDelimitedFileConnectionItem();
        case PropertiesPackage.POSITIONAL_FILE_CONNECTION_ITEM:
            return createPositionalFileConnectionItem();
        case PropertiesPackage.REG_EX_FILE_CONNECTION_ITEM:
            return createRegExFileConnectionItem();
        case PropertiesPackage.CSV_FILE_CONNECTION_ITEM:
            return createCSVFileConnectionItem();
        case PropertiesPackage.DATABASE_CONNECTION_ITEM:
            return createDatabaseConnectionItem();
        case PropertiesPackage.PROCESS_ITEM:
            return createProcessItem();
        case PropertiesPackage.USER_ROLE:
            return createUserRole();
        case PropertiesPackage.USER:
            return createUser();
        case PropertiesPackage.FOLDER_ITEM:
            return createFolderItem();
        case PropertiesPackage.COMPONENT:
            return createComponent();
        case PropertiesPackage.XML_FILE_CONNECTION_ITEM:
            return createXmlFileConnectionItem();
        case PropertiesPackage.NOTATION_HOLDER:
            return createNotationHolder();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
        case PropertiesPackage.FOLDER_TYPE:
            return createFolderTypeFromString(eDataType, initialValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
        case PropertiesPackage.FOLDER_TYPE:
            return convertFolderTypeToString(eDataType, instanceValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Status createStatus() {
        StatusImpl status = new StatusImpl();
        return status;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Project createProject() {
        ProjectImpl project = new ProjectImpl();
        return project;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Property createProperty() {
        PropertyImpl property = new PropertyImpl();
        return property;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public BusinessProcessItem createBusinessProcessItem() {
        BusinessProcessItemImpl businessProcessItem = new BusinessProcessItemImpl();
        businessProcessItem.setNotationHolder(createNotationHolder());
        return businessProcessItem;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ItemState createItemState() {
        ItemStateImpl itemState = new ItemStateImpl();
        return itemState;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DocumentationItem createDocumentationItem() {
        DocumentationItemImpl documentationItem = new DocumentationItemImpl();
        return documentationItem;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RoutineItem createRoutineItem() {
        RoutineItemImpl routineItem = new RoutineItemImpl();
        return routineItem;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ByteArray createByteArray() {
        ByteArrayImpl byteArray = new ByteArrayImpl();
        return byteArray;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ConnectionItem createConnectionItem() {
        ConnectionItemImpl connectionItem = new ConnectionItemImpl();
        return connectionItem;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DelimitedFileConnectionItem createDelimitedFileConnectionItem() {
        DelimitedFileConnectionItemImpl delimitedFileConnectionItem = new DelimitedFileConnectionItemImpl();
        return delimitedFileConnectionItem;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PositionalFileConnectionItem createPositionalFileConnectionItem() {
        PositionalFileConnectionItemImpl positionalFileConnectionItem = new PositionalFileConnectionItemImpl();
        return positionalFileConnectionItem;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RegExFileConnectionItem createRegExFileConnectionItem() {
        RegExFileConnectionItemImpl regExFileConnectionItem = new RegExFileConnectionItemImpl();
        return regExFileConnectionItem;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public CSVFileConnectionItem createCSVFileConnectionItem() {
        CSVFileConnectionItemImpl csvFileConnectionItem = new CSVFileConnectionItemImpl();
        return csvFileConnectionItem;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DatabaseConnectionItem createDatabaseConnectionItem() {
        DatabaseConnectionItemImpl databaseConnectionItem = new DatabaseConnectionItemImpl();
        return databaseConnectionItem;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ProcessItem createProcessItem() {
        ProcessItemImpl processItem = new ProcessItemImpl();
        return processItem;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public User createUser() {
        UserImpl user = new UserImpl();
        return user;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public FolderItem createFolderItem() {
        FolderItemImpl folderItem = new FolderItemImpl();
        return folderItem;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Component createComponent() {
        ComponentImpl component = new ComponentImpl();
        return component;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public XmlFileConnectionItem createXmlFileConnectionItem() {
        XmlFileConnectionItemImpl xmlFileConnectionItem = new XmlFileConnectionItemImpl();
        return xmlFileConnectionItem;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotationHolder createNotationHolder() {
        NotationHolderImpl notationHolder = new NotationHolderImpl();
        return notationHolder;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public FolderType createFolderTypeFromString(EDataType eDataType, String initialValue) {
        FolderType result = FolderType.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
                    + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertFolderTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public UserRole createUserRole() {
        UserRoleImpl userRole = new UserRoleImpl();
        return userRole;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PropertiesPackage getPropertiesPackage() {
        return (PropertiesPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    public static PropertiesPackage getPackage() {
        return PropertiesPackage.eINSTANCE;
    }

} // PropertiesFactoryImpl
