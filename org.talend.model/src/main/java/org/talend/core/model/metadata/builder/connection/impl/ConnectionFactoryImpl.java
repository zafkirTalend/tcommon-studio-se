/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.core.model.metadata.builder.connection.impl;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.talend.core.model.metadata.builder.connection.*;

import org.talend.core.model.metadata.builder.connection.CSVFileConnection;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.ConnectionFactory;
import org.talend.core.model.metadata.builder.connection.ConnectionPackage;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.metadata.builder.connection.DatabaseProperties;
import org.talend.core.model.metadata.builder.connection.DelimitedFileConnection;
import org.talend.core.model.metadata.builder.connection.Escape;
import org.talend.core.model.metadata.builder.connection.FieldSeparator;
import org.talend.core.model.metadata.builder.connection.FileFormat;
import org.talend.core.model.metadata.builder.connection.Metadata;
import org.talend.core.model.metadata.builder.connection.MetadataColumn;
import org.talend.core.model.metadata.builder.connection.MetadataSchema;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.metadata.builder.connection.PositionalFileConnection;
import org.talend.core.model.metadata.builder.connection.RegexpFileConnection;
import org.talend.core.model.metadata.builder.connection.RowSeparator;
import org.talend.core.model.metadata.builder.connection.SchemaTarget;
import org.talend.core.model.metadata.builder.connection.XmlFileConnection;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * @generated
 */
public class ConnectionFactoryImpl extends EFactoryImpl implements ConnectionFactory 
{

    private static final String MAP_DELIMTER = "\n\n";

  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
    public static ConnectionFactory init()
  {
    try
    {
      ConnectionFactory theConnectionFactory = (ConnectionFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.talend.org"); 
      if (theConnectionFactory != null)
      {
        return theConnectionFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new ConnectionFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
    public ConnectionFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
    public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case ConnectionPackage.METADATA: return createMetadata();
      case ConnectionPackage.CONNECTION: return createConnection();
      case ConnectionPackage.METADATA_COLUMN: return createMetadataColumn();
      case ConnectionPackage.METADATA_TABLE: return createMetadataTable();
      case ConnectionPackage.DELIMITED_FILE_CONNECTION: return createDelimitedFileConnection();
      case ConnectionPackage.POSITIONAL_FILE_CONNECTION: return createPositionalFileConnection();
      case ConnectionPackage.DATABASE_CONNECTION: return createDatabaseConnection();
      case ConnectionPackage.CSV_FILE_CONNECTION: return createCSVFileConnection();
      case ConnectionPackage.REGEXP_FILE_CONNECTION: return createRegexpFileConnection();
      case ConnectionPackage.XML_FILE_CONNECTION: return createXmlFileConnection();
      case ConnectionPackage.METADATA_SCHEMA: return createMetadataSchema();
      case ConnectionPackage.SCHEMA_TARGET: return createSchemaTarget();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
    public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case ConnectionPackage.DATABASE_PROPERTIES:
        return createDatabasePropertiesFromString(eDataType, initialValue);
      case ConnectionPackage.FILE_FORMAT:
        return createFileFormatFromString(eDataType, initialValue);
      case ConnectionPackage.FIELD_SEPARATOR:
        return createFieldSeparatorFromString(eDataType, initialValue);
      case ConnectionPackage.ESCAPE:
        return createEscapeFromString(eDataType, initialValue);
      case ConnectionPackage.ROW_SEPARATOR:
        return createRowSeparatorFromString(eDataType, initialValue);
      case ConnectionPackage.MAP:
        return createMapFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
    public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case ConnectionPackage.DATABASE_PROPERTIES:
        return convertDatabasePropertiesToString(eDataType, instanceValue);
      case ConnectionPackage.FILE_FORMAT:
        return convertFileFormatToString(eDataType, instanceValue);
      case ConnectionPackage.FIELD_SEPARATOR:
        return convertFieldSeparatorToString(eDataType, instanceValue);
      case ConnectionPackage.ESCAPE:
        return convertEscapeToString(eDataType, instanceValue);
      case ConnectionPackage.ROW_SEPARATOR:
        return convertRowSeparatorToString(eDataType, instanceValue);
      case ConnectionPackage.MAP:
        return convertMapToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
    public Metadata createMetadata()
  {
    MetadataImpl metadata = new MetadataImpl();
    return metadata;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
    public Connection createConnection()
  {
    ConnectionImpl connection = new ConnectionImpl();
    return connection;
  }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     */
    public MetadataTable createMetadataTable() {
        MetadataTableImpl metadataTable = new MetadataTableImpl();
        metadataTable.setLabel("metadata");
        return metadataTable;
    }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
    public MetadataColumn createMetadataColumn()
  {
    MetadataColumnImpl metadataColumn = new MetadataColumnImpl();
    return metadataColumn;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
    public DelimitedFileConnection createDelimitedFileConnection()
  {
    DelimitedFileConnectionImpl delimitedFileConnection = new DelimitedFileConnectionImpl();
    return delimitedFileConnection;
  }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     */
    public PositionalFileConnection createPositionalFileConnection() {
        PositionalFileConnectionImpl positionalFileConnection = new PositionalFileConnectionImpl();
        positionalFileConnection.setFieldSeparatorValue("*");
        return positionalFileConnection;
    }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
    public DatabaseConnection createDatabaseConnection()
  {
    DatabaseConnectionImpl databaseConnection = new DatabaseConnectionImpl();
    return databaseConnection;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
    public CSVFileConnection createCSVFileConnection()
  {
    CSVFileConnectionImpl csvFileConnection = new CSVFileConnectionImpl();
    return csvFileConnection;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
    public RegexpFileConnection createRegexpFileConnection()
  {
    RegexpFileConnectionImpl regexpFileConnection = new RegexpFileConnectionImpl();
    return regexpFileConnection;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
    public XmlFileConnection createXmlFileConnection()
  {
    XmlFileConnectionImpl xmlFileConnection = new XmlFileConnectionImpl();
    return xmlFileConnection;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
    public MetadataSchema createMetadataSchema()
  {
    MetadataSchemaImpl metadataSchema = new MetadataSchemaImpl();
    return metadataSchema;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
    public SchemaTarget createSchemaTarget()
  {
    SchemaTargetImpl schemaTarget = new SchemaTargetImpl();
    return schemaTarget;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
    public DatabaseProperties createDatabasePropertiesFromString(EDataType eDataType, String initialValue)
  {
    DatabaseProperties result = DatabaseProperties.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
    public String convertDatabasePropertiesToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
    public FileFormat createFileFormatFromString(EDataType eDataType, String initialValue)
  {
    FileFormat result = FileFormat.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
    public String convertFileFormatToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
    public FieldSeparator createFieldSeparatorFromString(EDataType eDataType, String initialValue)
  {
    FieldSeparator result = FieldSeparator.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
    public String convertFieldSeparatorToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
    public Escape createEscapeFromString(EDataType eDataType, String initialValue)
  {
    Escape result = Escape.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
    public String convertEscapeToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
    public RowSeparator createRowSeparatorFromString(EDataType eDataType, String initialValue)
  {
    RowSeparator result = RowSeparator.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
    public String convertRowSeparatorToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     */
    public HashMap createMapFromString(EDataType eDataType, String initialValue) {
        HashMap<String, String> map = new HashMap<String, String>();
        if (initialValue == null || initialValue.length() == 0)
            return map;
        String[] tokens = initialValue.split(MAP_DELIMTER);
        if (tokens.length % 2 != 0)
            throw new IllegalStateException("Expecting an even number of token in \'" + initialValue + "\'");
        int i = 0;
        while (i < tokens.length) {
            map.put(tokens[i++], tokens[i++]);
        }
        return map;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     */
    public String convertMapToString(EDataType eDataType, Object instanceValue) {
        Map<String, String> map = (Map<String, String>) instanceValue;
        StringBuffer buffer = new StringBuffer(map.size() * 40);

        for (String key : map.keySet()) {
            String value = map.get(key);
            buffer.append(key).append(MAP_DELIMTER).append(value).append(MAP_DELIMTER);
        }
        return buffer.toString();
    }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
    public ConnectionPackage getConnectionPackage()
  {
    return (ConnectionPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
    public static ConnectionPackage getPackage()
  {
    return ConnectionPackage.eINSTANCE;
  }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.builder.connection.MetadataColumn#copy()
     */
    public MetadataColumn copy(MetadataColumn column, String newId) {
        MetadataColumn newColumn = new MetadataColumnImpl();
        newColumn.setId(newId);
        newColumn.setLabel(column.getLabel());
        newColumn.setKey(column.isKey());
        newColumn.setLength(column.getLength());
        newColumn.setDefaultValue(column.getDefaultValue());
        newColumn.setPrecision(column.getPrecision());
        newColumn.setSourceType(column.getSourceType());
        newColumn.setTalendType(column.getTalendType());
        newColumn.setNullable(column.isNullable());
        newColumn.setComment(column.getComment());
        return newColumn;
    }

} // ConnectionFactoryImpl
