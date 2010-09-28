// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.metadata.builder.util;

import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.talend.commons.utils.database.DB2ForZosDataBaseMetadata;
import org.talend.core.model.metadata.MappingTypeRetriever;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.metadata.builder.database.ExtractMetaDataUtils;
import org.talend.cwm.helper.ColumnHelper;
import org.talend.cwm.helper.ConnectionHelper;
import org.talend.cwm.helper.TableHelper;
import org.talend.cwm.helper.TaggedValueHelper;
import org.talend.cwm.relational.RelationalFactory;
import org.talend.cwm.relational.TdColumn;
import org.talend.cwm.relational.TdExpression;
import org.talend.cwm.relational.TdSqlDataType;
import org.talend.cwm.relational.TdTable;
import org.talend.utils.sql.ConnectionUtils;
import org.talend.utils.sql.metadata.constants.GetColumn;
import org.talend.utils.sugars.ReturnCode;
import org.talend.utils.sugars.TypedReturnCode;
import orgomg.cwm.objectmodel.core.TaggedValue;
import orgomg.cwm.resource.relational.enumerations.NullableType;

/**
 * DOC zshen class global comment. Detailled comment
 */
public class TDColumnAttributeHelper {

    private static final String ODBC_DRIVER_NAME = "jdbc-odbc";

    private static final String ODBC_MSSQL_PRODUCT_NAME = "Microsoft SQL Server";

    private static final String IBM_DB2_ZOS_PRODUCT_NAME = "DB2";

    private static DatabaseConnection databaseconnection = null;

    private static Logger log = Logger.getLogger(TDColumnAttributeHelper.class);

    public static TdTable addTagVale(ResultSet resutSet, TdTable table) {
        try {
            if (table == null) {
                table = RelationalFactory.eINSTANCE.createTdTable();
            }
            String colComment = resutSet.getString(GetColumn.REMARKS.name());
            if (colComment == null) {
                colComment = "";
            }
            TableHelper.setComment(colComment, table);
        } catch (Exception e) {
            log.warn(e, e);
        }
        return table;
    }

    public static TdColumn addColumnAttribute(ResultSet resutSet, TdColumn column) throws SQLException {

        // // --- add columns to table
        // ResultSet columns = getConnectionMetadata(connection).getColumns(catalogName, schemaPattern, tablePattern,
        // columnPattern);

        // TODO scorreia other informations for columns can be retrieved here
        // get the default value
        // MOD mzhao 2009-04-09,Bug 6840: fetch LONG or LONG RAW column first , as these kind of columns are read as
        // stream,if not read by select order, there will be "Stream has already been closed" error.
        // Don't move the below block ,if you move it that emerge up bug klliu 2010-09-07
        if (column == null) {
            column = RelationalFactory.eINSTANCE.createTdColumn();
        }
        Object defaultvalue = null;
        try {
            defaultvalue = resutSet.getObject(GetColumn.COLUMN_DEF.name());
        } catch (Exception e1) {
            log.warn(e1, e1);
        }
        String defaultStr = (defaultvalue != null) ? String.valueOf(defaultvalue) : null;
        TdExpression defExpression = createTdExpression(GetColumn.COLUMN_DEF.name(), defaultStr);

        // MOD xqliu 2009-10-29 bug 9838
        // MOD xqliu 2009-12-08 bug 9822, if use odbc connect to sqlserver the resultset is forward only!!!
        String colName = null;
        try {
            colName = resutSet.getString(GetColumn.COLUMN_NAME.name());
        } catch (Exception e1) {
            log.warn(e1, e1);
            if (colName == null) {
                colName = e1.getMessage();
            }
        }
        column.setName(colName);

        int dataType = 0;
        try {
            dataType = resutSet.getInt(GetColumn.DATA_TYPE.name());
            // MOD scorreia 2010-07-24 removed the call to column.getSQLDataType() here because obviously the sql
            // data type it is null and results in a NPE
        } catch (Exception e) {
            log.warn(e, e);
        }

        String typeName = null;
        try {
            typeName = resutSet.getString(GetColumn.TYPE_NAME.name());
            // MOD zshen when the database is mssql,the datatype for "date" and "time" is "-9" and "-2"
            // ,respective.so change them to "91" and "92" for adapt to Java2SqlType.
            Connection conn = ConnectionHelper.getConnection(column);
            if (isMssql(createConnection((DatabaseConnection) conn).getObject())) {
                if (typeName.toLowerCase().equals("date")) {
                    dataType = 91;
                    // MOD scorreia 2010-07-24 removed the call to column.getSQLDataType() here because obviously
                    // the sql
                    // data type it is null and results in a NPE
                } else if (typeName.toLowerCase().equals("time")) {
                    dataType = 92;
                    // MOD scorreia 2010-07-24 removed the call to column.getSQLDataType() here because obviously
                    // the sql
                    // data type it is null and results in a NPE
                }
            }
        } catch (Exception e1) {
            log.warn(e1, e1);
        }

        try {
            column.setLength(resutSet.getInt(GetColumn.COLUMN_SIZE.name()));
        } catch (Exception e1) {
            log.warn(e1, e1);
        }

        int decimalDigits = 0;
        try {
            decimalDigits = resutSet.getInt(GetColumn.DECIMAL_DIGITS.name());
        } catch (Exception e) {
            log.warn(e);
        }

        int numPrecRadix = 0;
        try {
            numPrecRadix = resutSet.getInt(GetColumn.NUM_PREC_RADIX.name());
        } catch (Exception e) {
            log.warn(e);
        }

        // get column description (comment)
        try {
            String colComment = resutSet.getString(GetColumn.REMARKS.name());
            if (colComment == null) {
                colComment = "";
            }
            ColumnHelper.setComment(colComment, column);
        } catch (Exception e) {
            log.warn(e, e);
        }

        // --- create and set type of column
        // TODO scorreia get type of column on demand, not on creation of column
        // TdSqlDataType sqlDataType = DatabaseContentRetriever.createDataType(columns);
        TdSqlDataType sqlDataType = createDataType(dataType, typeName, decimalDigits, numPrecRadix);
        column.setSqlDataType(sqlDataType);
        // column.setType(sqlDataType); // it's only reference to previous sql data type

        try {
            column.getSqlDataType().setNullable(NullableType.get(resutSet.getInt(GetColumn.NULLABLE.name())));
        } catch (Exception e1) {
            log.warn(e1, e1);
        }

        column.setInitialValue(defExpression);

        return column;
    }

    public static TdColumn addColumnAttribute(ResultSet resutSet, TdColumn column, DatabaseConnection conn) throws SQLException {
        databaseconnection = conn;
        return addColumnAttribute(resutSet, column, createConnection(conn).getObject());

    }

    public static TdColumn addColumnAttribute(ResultSet resutSet, TdColumn column, java.sql.Connection conn) throws SQLException {

        // // --- add columns to table
        // ResultSet columns = getConnectionMetadata(connection).getColumns(catalogName, schemaPattern, tablePattern,
        // columnPattern);

        // TODO scorreia other informations for columns can be retrieved here
        // get the default value
        // MOD mzhao 2009-04-09,Bug 6840: fetch LONG or LONG RAW column first , as these kind of columns are read as
        // stream,if not read by select order, there will be "Stream has already been closed" error.
        // Don't move the below block ,if you move it that emerge up bug klliu 2010-09-07
        if (column == null) {
            column = RelationalFactory.eINSTANCE.createTdColumn();
        }
        Object defaultvalue = null;
        try {
            defaultvalue = resutSet.getObject(GetColumn.COLUMN_DEF.name());
        } catch (Exception e1) {
            log.warn(e1, e1);
        }
        String defaultStr = (defaultvalue != null) ? String.valueOf(defaultvalue) : null;
        TdExpression defExpression = createTdExpression(GetColumn.COLUMN_DEF.name(), defaultStr);

        // MOD xqliu 2009-10-29 bug 9838
        // MOD xqliu 2009-12-08 bug 9822, if use odbc connect to sqlserver the resultset is forward only!!!
        String colName = null;
        try {
            colName = resutSet.getString(GetColumn.COLUMN_NAME.name());
        } catch (Exception e1) {
            log.warn(e1, e1);
            if (colName == null) {
                colName = e1.getMessage();
            }
        }
        column.setName(colName);
        column.setLabel(colName);

        int dataType = 0;
        try {
            dataType = resutSet.getInt(GetColumn.DATA_TYPE.name());
            // MOD scorreia 2010-07-24 removed the call to column.getSQLDataType() here because obviously the sql
            // data type it is null and results in a NPE
        } catch (Exception e) {
            log.warn(e, e);
        }

        String typeName = null;
        try {
            typeName = resutSet.getString(GetColumn.TYPE_NAME.name());
            // MOD zshen when the database is mssql,the datatype for "date" and "time" is "-9" and "-2"
            // ,respective.so change them to "91" and "92" for adapt to Java2SqlType.

            if (isMssql(conn)) {
                if (typeName.toLowerCase().equals("date")) {
                    dataType = 91;
                    // MOD scorreia 2010-07-24 removed the call to column.getSQLDataType() here because obviously
                    // the sql
                    // data type it is null and results in a NPE
                } else if (typeName.toLowerCase().equals("time")) {
                    dataType = 92;
                    // MOD scorreia 2010-07-24 removed the call to column.getSQLDataType() here because obviously
                    // the sql
                    // data type it is null and results in a NPE
                }
            }
        } catch (Exception e1) {
            log.warn(e1, e1);
        }

        try {
            column.setLength(resutSet.getInt(GetColumn.COLUMN_SIZE.name()));
        } catch (Exception e1) {
            log.warn(e1, e1);
        }

        int decimalDigits = 0;
        try {
            decimalDigits = resutSet.getInt(GetColumn.DECIMAL_DIGITS.name());
        } catch (Exception e) {
            log.warn(e);
        }

        int numPrecRadix = 0;
        try {
            numPrecRadix = resutSet.getInt(GetColumn.NUM_PREC_RADIX.name());
        } catch (Exception e) {
            log.warn(e);
        }

        // get column description (comment)
        try {
            String colComment = resutSet.getString(GetColumn.REMARKS.name());
            if (colComment == null) {
                colComment = "";
            }
            ColumnHelper.setComment(colComment, column);
        } catch (Exception e) {
            log.warn(e, e);
        }

        // --- create and set type of column
        // TODO scorreia get type of column on demand, not on creation of column
        // TdSqlDataType sqlDataType = DatabaseContentRetriever.createDataType(columns);
        TdSqlDataType sqlDataType = createDataType(dataType, typeName, decimalDigits, numPrecRadix);
        column.setSqlDataType(sqlDataType);
        // column.setType(sqlDataType); // it's only reference to previous sql data type

        try {
            column.getSqlDataType().setNullable(NullableType.get(resutSet.getInt(GetColumn.NULLABLE.name())));
        } catch (Exception e1) {
            log.warn(e1, e1);
        }

        column.setInitialValue(defExpression);

        String mapping = databaseconnection.getDbmsId();
        MappingTypeRetriever mappingTypeRetriever = MetadataTalendType.getMappingTypeRetriever(mapping);
        String talendType = mappingTypeRetriever.getDefaultSelectedTalendType(typeName, ExtractMetaDataUtils.getIntMetaDataInfo(
                resutSet, "COLUMN_SIZE"), ExtractMetaDataUtils.getIntMetaDataInfo(resutSet, //$NON-NLS-1$
                "DECIMAL_DIGITS")); //$NON-NLS-1$
        column.setTalendType(talendType);
        // mappingTypeRetriever = MetadataTalendType.getMappingTypeRetriever(metadataConnection.getMapping());
        return column;
    }

    /**
     * DOC xqliu Comment method "createDataType".
     * 
     * @param dataType
     * @param typeName
     * @param decimalDigits
     * @param numPrecRadix
     * @return
     */
    private static TdSqlDataType createDataType(int dataType, String typeName, int decimalDigits, int numPrecRadix) {
        TdSqlDataType sqlDataType = RelationalFactory.eINSTANCE.createTdSqlDataType();
        sqlDataType.setName(typeName);
        sqlDataType.setJavaDataType(dataType);
        sqlDataType.setNumericPrecision(decimalDigits);
        sqlDataType.setNumericPrecisionRadix(numPrecRadix);
        return sqlDataType;
    }

    /**
     * DOC zshen Comment method "isMssql".
     * 
     * @param connection
     * @return decide to whether is mssql connection
     * @throws SQLException
     */
    private static boolean isMssql(java.sql.Connection connection) throws SQLException {
        DatabaseMetaData connectionMetadata = getConnectionMetadata(connection);
        if (connectionMetadata.getDriverName() != null
                && !connectionMetadata.getDriverName().toLowerCase().startsWith(ODBC_DRIVER_NAME)
                && connectionMetadata.getDatabaseProductName() != null
                && connectionMetadata.getDatabaseProductName().equals(ODBC_MSSQL_PRODUCT_NAME)) {
            return true;
        }
        return false;
    }

    /**
     * DOC xqliu Comment method "getConnectionMetadata". 2009-07-13 bug 7888.
     * 
     * @param conn
     * @return
     * @throws SQLException
     */

    private static DatabaseMetaData getConnectionMetadata(java.sql.Connection conn) throws SQLException {
        DatabaseMetaData dbMetaData = conn.getMetaData();
        // MOD xqliu 2009-11-17 bug 7888
        if (dbMetaData != null && dbMetaData.getDatabaseProductName() != null
                && dbMetaData.getDatabaseProductName().equals(IBM_DB2_ZOS_PRODUCT_NAME)) {
            dbMetaData = createFakeDatabaseMetaData(conn);
            log.info("IBM DB2 for z/OS");
        }
        // ~
        return dbMetaData;
    }

    /**
     * only for db2 on z/os right now. 2009-07-13 bug 7888.
     * 
     * @param conn2
     * @return
     */
    private static DatabaseMetaData createFakeDatabaseMetaData(java.sql.Connection conn) {
        DB2ForZosDataBaseMetadata dmd = new DB2ForZosDataBaseMetadata(conn);
        return dmd;
    }

    /**
     * Method "createConnection" returns the connection with {@link ReturnCode#getObject()} if {@link ReturnCode#isOk()}
     * is true. This is the behaviour when everything goes ok.
     * <p>
     * When something goes wrong, {@link ReturnCode#isOk()} is false and {@link ReturnCode#getMessage()} gives the error
     * message.
     * <p>
     * The created connection must be closed by the caller. (use {@link ConnectionUtils#closeConnection(Connection)})
     * 
     * @param providerConnection the provider connection
     * @return a ReturnCode (never null)
     * 
     * getby JavaSqlFactory
     */
    private static TypedReturnCode<java.sql.Connection> createConnection(DatabaseConnection providerConnection) {
        TypedReturnCode<java.sql.Connection> rc = new TypedReturnCode<java.sql.Connection>(false);
        String url = providerConnection.getURL();
        if (url == null) {
            rc
                    .setMessage("Unable to decrypt given password correctly. It's probably due to a change in the passphrase used in encryption"); //$NON-NLS-1$
            rc.setOk(false);
        }
        String driverClassName = providerConnection.getDriverClass();
        Collection<TaggedValue> taggedValues = providerConnection.getTaggedValue();
        Properties props = new Properties();
        props.put(TaggedValueHelper.USER, providerConnection.getUsername());
        props.put(TaggedValueHelper.PASSWORD, providerConnection.getPassword());
        String pass = props.getProperty(TaggedValueHelper.PASSWORD);
        if (pass != null) {
            String clearTextPassword = providerConnection.getPassword();
            if (clearTextPassword == null) {
                rc
                        .setMessage("Unable to decrypt given password correctly. It's probably due to a change in the passphrase used in encryption."); //$NON-NLS-1$
                rc.setOk(false);
            } else {
                props.setProperty(TaggedValueHelper.PASSWORD, clearTextPassword);
            }
        }
        try {
            java.sql.Connection connection = createConnection(url, driverClassName, props);
            rc.setObject(connection);
            rc.setOk(true);
        } catch (SQLException e) {
            rc.setReturnCode(e.getMessage(), false);
        } catch (InstantiationException e) {
            rc.setReturnCode(e.getMessage(), false);
        } catch (IllegalAccessException e) {
            rc.setReturnCode(e.getMessage(), false);
        } catch (ClassNotFoundException e) {
            rc.setReturnCode(e.getMessage(), false);
        }
        return rc;
    }

    /**
     * Method "createConnection".
     * 
     * @param url the database url
     * @param driverClassName the Driver classname
     * @param props properties passed to the driver manager for getting the connection (normally at least a "user" and
     * "password" property should be included)
     * @return the connection
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    private static java.sql.Connection createConnection(String url, String driverClassName, Properties props)
            throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        Driver driver = (Driver) Class.forName(driverClassName).newInstance();
        DriverManager.registerDriver(driver);
        java.sql.Connection connection = DriverManager.getConnection(url, props);
        return connection;
    }

    private static TdExpression createTdExpression(String language, String body) {
        TdExpression expression = RelationalFactory.eINSTANCE.createTdExpression();
        expression.setBody(body);
        expression.setLanguage(language);
        return expression;
    }
}
