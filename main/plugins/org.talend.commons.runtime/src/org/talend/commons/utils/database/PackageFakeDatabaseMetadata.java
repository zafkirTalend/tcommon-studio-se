// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.commons.utils.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.talend.fakejdbc.FakeDatabaseMetaData;

/**
 * created by zshen on Apr 15, 2013 Detailled comment
 * 
 */
public class PackageFakeDatabaseMetadata extends FakeDatabaseMetaData {

    protected Connection connection;

    protected DatabaseMetaData metaData;

    /**
     * DOC xqliu SybaseDatabaseMetaData constructor comment.
     */
    public PackageFakeDatabaseMetadata(Connection connection) throws SQLException {
        this.connection = connection;
        this.metaData = connection.getMetaData();
    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }

    @Override
    public boolean allProceduresAreCallable() throws SQLException {

        return super.allProceduresAreCallable();
    }

    @Override
    public boolean allTablesAreSelectable() throws SQLException {

        return super.allTablesAreSelectable();
    }

    @Override
    public boolean autoCommitFailureClosesAllResultSets() throws SQLException {

        return super.autoCommitFailureClosesAllResultSets();
    }

    @Override
    public boolean dataDefinitionCausesTransactionCommit() throws SQLException {

        return super.dataDefinitionCausesTransactionCommit();
    }

    @Override
    public boolean dataDefinitionIgnoredInTransactions() throws SQLException {

        return super.dataDefinitionIgnoredInTransactions();
    }

    @Override
    public boolean deletesAreDetected(int type) throws SQLException {

        return super.deletesAreDetected(type);
    }

    @Override
    public boolean doesMaxRowSizeIncludeBlobs() throws SQLException {

        return super.doesMaxRowSizeIncludeBlobs();
    }

    @Override
    public ResultSet getAttributes(String catalog, String schemaPattern, String typeNamePattern, String attributeNamePattern)
            throws SQLException {

        return super.getAttributes(catalog, schemaPattern, typeNamePattern, attributeNamePattern);
    }

    @Override
    public ResultSet getBestRowIdentifier(String catalog, String schema, String table, int scope, boolean nullable)
            throws SQLException {

        return super.getBestRowIdentifier(catalog, schema, table, scope, nullable);
    }

    @Override
    public ResultSet getCatalogs() throws SQLException {
        return this.metaData.getCatalogs();
    }

    @Override
    public String getCatalogSeparator() throws SQLException {

        return super.getCatalogSeparator();
    }

    @Override
    public String getCatalogTerm() throws SQLException {

        return super.getCatalogTerm();
    }

    @Override
    public ResultSet getClientInfoProperties() throws SQLException {

        return super.getClientInfoProperties();
    }

    @Override
    public ResultSet getColumnPrivileges(String catalog, String schema, String table, String columnNamePattern)
            throws SQLException {
        return this.metaData.getColumnPrivileges(catalog, schema, table, columnNamePattern);
    }

    @Override
    public ResultSet getColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern)
            throws SQLException {
        return this.metaData.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern);
    }

    @Override
    public ResultSet getCrossReference(String parentCatalog, String parentSchema, String parentTable, String foreignCatalog,
            String foreignSchema, String foreignTable) throws SQLException {

        return super.getCrossReference(parentCatalog, parentSchema, parentTable, foreignCatalog, foreignSchema, foreignTable);
    }

    @Override
    public int getDatabaseMajorVersion() throws SQLException {
        return this.metaData.getDatabaseMajorVersion();
    }

    @Override
    public int getDatabaseMinorVersion() throws SQLException {
        return this.metaData.getDatabaseMinorVersion();
    }

    @Override
    public String getDatabaseProductName() throws SQLException {
        return this.metaData.getDatabaseProductName();
    }

    @Override
    public String getDatabaseProductVersion() throws SQLException {
        return this.metaData.getDatabaseProductVersion();
    }

    @Override
    public int getDefaultTransactionIsolation() throws SQLException {
        return this.metaData.getDefaultTransactionIsolation();
    }

    @Override
    public int getDriverMajorVersion() {
        return this.metaData.getDriverMajorVersion();
    }

    @Override
    public int getDriverMinorVersion() {
        return this.metaData.getDriverMinorVersion();
    }

    @Override
    public String getDriverName() throws SQLException {
        return this.metaData.getDriverName();
    }

    @Override
    public String getDriverVersion() throws SQLException {
        return this.metaData.getDriverVersion();
    }

    @Override
    public ResultSet getExportedKeys(String catalog, String schema, String table) throws SQLException {

        return super.getExportedKeys(catalog, schema, table);
    }

    @Override
    public String getExtraNameCharacters() throws SQLException {

        return super.getExtraNameCharacters();
    }

    @Override
    public ResultSet getFunctionColumns(String catalog, String schemaPattern, String functionNamePattern, String columnNamePattern)
            throws SQLException {

        return super.getFunctionColumns(catalog, schemaPattern, functionNamePattern, columnNamePattern);
    }

    @Override
    public ResultSet getFunctions(String catalog, String schemaPattern, String functionNamePattern) throws SQLException {

        return super.getFunctions(catalog, schemaPattern, functionNamePattern);
    }

    @Override
    public String getIdentifierQuoteString() throws SQLException {

        return super.getIdentifierQuoteString();
    }

    @Override
    public ResultSet getImportedKeys(String catalog, String schema, String table) throws SQLException {

        return super.getImportedKeys(catalog, schema, table);
    }

    @Override
    public ResultSet getIndexInfo(String catalog, String schema, String table, boolean unique, boolean approximate)
            throws SQLException {

        return super.getIndexInfo(catalog, schema, table, unique, approximate);
    }

    @Override
    public int getJDBCMajorVersion() throws SQLException {

        return super.getJDBCMajorVersion();
    }

    @Override
    public int getJDBCMinorVersion() throws SQLException {

        return super.getJDBCMinorVersion();
    }

    @Override
    public int getMaxBinaryLiteralLength() throws SQLException {

        return super.getMaxBinaryLiteralLength();
    }

    @Override
    public int getMaxCatalogNameLength() throws SQLException {

        return super.getMaxCatalogNameLength();
    }

    @Override
    public int getMaxCharLiteralLength() throws SQLException {

        return super.getMaxCharLiteralLength();
    }

    @Override
    public int getMaxColumnNameLength() throws SQLException {

        return super.getMaxColumnNameLength();
    }

    @Override
    public int getMaxColumnsInGroupBy() throws SQLException {

        return super.getMaxColumnsInGroupBy();
    }

    @Override
    public int getMaxColumnsInIndex() throws SQLException {

        return super.getMaxColumnsInIndex();
    }

    @Override
    public int getMaxColumnsInOrderBy() throws SQLException {

        return super.getMaxColumnsInOrderBy();
    }

    @Override
    public int getMaxColumnsInSelect() throws SQLException {

        return super.getMaxColumnsInSelect();
    }

    @Override
    public int getMaxColumnsInTable() throws SQLException {

        return super.getMaxColumnsInTable();
    }

    @Override
    public int getMaxConnections() throws SQLException {

        return super.getMaxConnections();
    }

    @Override
    public int getMaxCursorNameLength() throws SQLException {

        return super.getMaxCursorNameLength();
    }

    @Override
    public int getMaxIndexLength() throws SQLException {

        return super.getMaxIndexLength();
    }

    @Override
    public int getMaxProcedureNameLength() throws SQLException {

        return super.getMaxProcedureNameLength();
    }

    @Override
    public int getMaxRowSize() throws SQLException {

        return super.getMaxRowSize();
    }

    @Override
    public int getMaxSchemaNameLength() throws SQLException {

        return super.getMaxSchemaNameLength();
    }

    @Override
    public int getMaxStatementLength() throws SQLException {

        return super.getMaxStatementLength();
    }

    @Override
    public int getMaxStatements() throws SQLException {

        return super.getMaxStatements();
    }

    @Override
    public int getMaxTableNameLength() throws SQLException {

        return super.getMaxTableNameLength();
    }

    @Override
    public int getMaxTablesInSelect() throws SQLException {

        return super.getMaxTablesInSelect();
    }

    @Override
    public int getMaxUserNameLength() throws SQLException {

        return super.getMaxUserNameLength();
    }

    @Override
    public String getNumericFunctions() throws SQLException {

        return super.getNumericFunctions();
    }

    @Override
    public ResultSet getPrimaryKeys(String catalog, String schema, String table) throws SQLException {
        return this.metaData.getPrimaryKeys(catalog, schema, table);
    }

    @Override
    public ResultSet getProcedureColumns(String catalog, String schemaPattern, String procedureNamePattern,
            String columnNamePattern) throws SQLException {

        return super.getProcedureColumns(catalog, schemaPattern, procedureNamePattern, columnNamePattern);
    }

    @Override
    public ResultSet getProcedures(String catalog, String schemaPattern, String procedureNamePattern) throws SQLException {

        return super.getProcedures(catalog, schemaPattern, procedureNamePattern);
    }

    @Override
    public String getProcedureTerm() throws SQLException {

        return super.getProcedureTerm();
    }

    @Override
    public int getResultSetHoldability() throws SQLException {

        return super.getResultSetHoldability();
    }

    @Override
    public ResultSet getSchemas() throws SQLException {
        return this.metaData.getSchemas();
    }

    @Override
    public String getSchemaTerm() throws SQLException {

        return super.getSchemaTerm();
    }

    @Override
    public String getSearchStringEscape() throws SQLException {

        return super.getSearchStringEscape();
    }

    @Override
    public String getSQLKeywords() throws SQLException {

        return super.getSQLKeywords();
    }

    @Override
    public int getSQLStateType() throws SQLException {

        return super.getSQLStateType();
    }

    @Override
    public String getStringFunctions() throws SQLException {

        return super.getStringFunctions();
    }

    @Override
    public ResultSet getSuperTables(String catalog, String schemaPattern, String tableNamePattern) throws SQLException {

        return super.getSuperTables(catalog, schemaPattern, tableNamePattern);
    }

    @Override
    public ResultSet getSuperTypes(String catalog, String schemaPattern, String typeNamePattern) throws SQLException {

        return super.getSuperTypes(catalog, schemaPattern, typeNamePattern);
    }

    @Override
    public String getSystemFunctions() throws SQLException {

        return super.getSystemFunctions();
    }

    @Override
    public ResultSet getTablePrivileges(String catalog, String schemaPattern, String tableNamePattern) throws SQLException {
        return this.metaData.getTablePrivileges(catalog, schemaPattern, tableNamePattern);
    }

    @Override
    public ResultSet getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types) throws SQLException {
        return this.metaData.getTables(catalog, schemaPattern, tableNamePattern, types);
    }

    @Override
    public ResultSet getTableTypes() throws SQLException {
        return this.metaData.getTableTypes();
    }

    @Override
    public String getTimeDateFunctions() throws SQLException {

        return super.getTimeDateFunctions();
    }

    @Override
    public ResultSet getTypeInfo() throws SQLException {
        return this.metaData.getTypeInfo();
    }

    @Override
    public ResultSet getUDTs(String catalog, String schemaPattern, String typeNamePattern, int[] types) throws SQLException {

        return super.getUDTs(catalog, schemaPattern, typeNamePattern, types);
    }

    @Override
    public String getURL() throws SQLException {

        return super.getURL();
    }

    @Override
    public String getUserName() throws SQLException {

        return super.getUserName();
    }

    @Override
    public ResultSet getVersionColumns(String catalog, String schema, String table) throws SQLException {

        return super.getVersionColumns(catalog, schema, table);
    }

    @Override
    public boolean insertsAreDetected(int type) throws SQLException {

        return super.insertsAreDetected(type);
    }

    @Override
    public boolean isCatalogAtStart() throws SQLException {

        return super.isCatalogAtStart();
    }

    @Override
    public boolean isReadOnly() throws SQLException {

        return super.isReadOnly();
    }

    @Override
    public boolean locatorsUpdateCopy() throws SQLException {

        return super.locatorsUpdateCopy();
    }

    @Override
    public boolean nullPlusNonNullIsNull() throws SQLException {

        return super.nullPlusNonNullIsNull();
    }

    @Override
    public boolean nullsAreSortedAtEnd() throws SQLException {

        return super.nullsAreSortedAtEnd();
    }

    @Override
    public boolean nullsAreSortedAtStart() throws SQLException {

        return super.nullsAreSortedAtStart();
    }

    @Override
    public boolean nullsAreSortedHigh() throws SQLException {

        return super.nullsAreSortedHigh();
    }

    @Override
    public boolean nullsAreSortedLow() throws SQLException {

        return super.nullsAreSortedLow();
    }

    @Override
    public boolean othersDeletesAreVisible(int type) throws SQLException {

        return super.othersDeletesAreVisible(type);
    }

    @Override
    public boolean othersInsertsAreVisible(int type) throws SQLException {

        return super.othersInsertsAreVisible(type);
    }

    @Override
    public boolean othersUpdatesAreVisible(int type) throws SQLException {

        return super.othersUpdatesAreVisible(type);
    }

    @Override
    public boolean ownDeletesAreVisible(int type) throws SQLException {

        return super.ownDeletesAreVisible(type);
    }

    @Override
    public boolean ownInsertsAreVisible(int type) throws SQLException {

        return super.ownInsertsAreVisible(type);
    }

    @Override
    public boolean ownUpdatesAreVisible(int type) throws SQLException {

        return super.ownUpdatesAreVisible(type);
    }

    @Override
    public boolean storesLowerCaseIdentifiers() throws SQLException {

        return super.storesLowerCaseIdentifiers();
    }

    @Override
    public boolean storesLowerCaseQuotedIdentifiers() throws SQLException {

        return super.storesLowerCaseQuotedIdentifiers();
    }

    @Override
    public boolean storesMixedCaseIdentifiers() throws SQLException {

        return super.storesMixedCaseIdentifiers();
    }

    @Override
    public boolean storesMixedCaseQuotedIdentifiers() throws SQLException {

        return super.storesMixedCaseQuotedIdentifiers();
    }

    @Override
    public boolean storesUpperCaseIdentifiers() throws SQLException {

        return super.storesUpperCaseIdentifiers();
    }

    @Override
    public boolean storesUpperCaseQuotedIdentifiers() throws SQLException {

        return super.storesUpperCaseQuotedIdentifiers();
    }

    @Override
    public boolean supportsAlterTableWithAddColumn() throws SQLException {

        return super.supportsAlterTableWithAddColumn();
    }

    @Override
    public boolean supportsAlterTableWithDropColumn() throws SQLException {

        return super.supportsAlterTableWithDropColumn();
    }

    @Override
    public boolean supportsANSI92EntryLevelSQL() throws SQLException {

        return super.supportsANSI92EntryLevelSQL();
    }

    @Override
    public boolean supportsANSI92FullSQL() throws SQLException {

        return super.supportsANSI92FullSQL();
    }

    @Override
    public boolean supportsANSI92IntermediateSQL() throws SQLException {

        return super.supportsANSI92IntermediateSQL();
    }

    @Override
    public boolean supportsBatchUpdates() throws SQLException {

        return super.supportsBatchUpdates();
    }

    @Override
    public boolean supportsCatalogsInDataManipulation() throws SQLException {

        return super.supportsCatalogsInDataManipulation();
    }

    @Override
    public boolean supportsCatalogsInIndexDefinitions() throws SQLException {

        return super.supportsCatalogsInIndexDefinitions();
    }

    @Override
    public boolean supportsCatalogsInPrivilegeDefinitions() throws SQLException {

        return super.supportsCatalogsInPrivilegeDefinitions();
    }

    @Override
    public boolean supportsCatalogsInProcedureCalls() throws SQLException {

        return super.supportsCatalogsInProcedureCalls();
    }

    @Override
    public boolean supportsCatalogsInTableDefinitions() throws SQLException {

        return super.supportsCatalogsInTableDefinitions();
    }

    @Override
    public boolean supportsColumnAliasing() throws SQLException {

        return super.supportsColumnAliasing();
    }

    @Override
    public boolean supportsConvert() throws SQLException {

        return super.supportsConvert();
    }

    @Override
    public boolean supportsConvert(int fromType, int toType) throws SQLException {

        return super.supportsConvert(fromType, toType);
    }

    @Override
    public boolean supportsCoreSQLGrammar() throws SQLException {

        return super.supportsCoreSQLGrammar();
    }

    @Override
    public boolean supportsCorrelatedSubqueries() throws SQLException {

        return super.supportsCorrelatedSubqueries();
    }

    @Override
    public boolean supportsDataDefinitionAndDataManipulationTransactions() throws SQLException {

        return super.supportsDataDefinitionAndDataManipulationTransactions();
    }

    @Override
    public boolean supportsDataManipulationTransactionsOnly() throws SQLException {

        return super.supportsDataManipulationTransactionsOnly();
    }

    @Override
    public boolean supportsDifferentTableCorrelationNames() throws SQLException {

        return super.supportsDifferentTableCorrelationNames();
    }

    @Override
    public boolean supportsExpressionsInOrderBy() throws SQLException {

        return super.supportsExpressionsInOrderBy();
    }

    @Override
    public boolean supportsExtendedSQLGrammar() throws SQLException {

        return super.supportsExtendedSQLGrammar();
    }

    @Override
    public boolean supportsFullOuterJoins() throws SQLException {

        return super.supportsFullOuterJoins();
    }

    @Override
    public boolean supportsGetGeneratedKeys() throws SQLException {

        return super.supportsGetGeneratedKeys();
    }

    @Override
    public boolean supportsGroupBy() throws SQLException {

        return super.supportsGroupBy();
    }

    @Override
    public boolean supportsGroupByBeyondSelect() throws SQLException {

        return super.supportsGroupByBeyondSelect();
    }

    @Override
    public boolean supportsGroupByUnrelated() throws SQLException {

        return super.supportsGroupByUnrelated();
    }

    @Override
    public boolean supportsIntegrityEnhancementFacility() throws SQLException {

        return super.supportsIntegrityEnhancementFacility();
    }

    @Override
    public boolean supportsLikeEscapeClause() throws SQLException {

        return super.supportsLikeEscapeClause();
    }

    @Override
    public boolean supportsLimitedOuterJoins() throws SQLException {

        return super.supportsLimitedOuterJoins();
    }

    @Override
    public boolean supportsMinimumSQLGrammar() throws SQLException {

        return super.supportsMinimumSQLGrammar();
    }

    @Override
    public boolean supportsMixedCaseIdentifiers() throws SQLException {

        return super.supportsMixedCaseIdentifiers();
    }

    @Override
    public boolean supportsMixedCaseQuotedIdentifiers() throws SQLException {

        return super.supportsMixedCaseQuotedIdentifiers();
    }

    @Override
    public boolean supportsMultipleOpenResults() throws SQLException {

        return super.supportsMultipleOpenResults();
    }

    @Override
    public boolean supportsMultipleResultSets() throws SQLException {

        return super.supportsMultipleResultSets();
    }

    @Override
    public boolean supportsMultipleTransactions() throws SQLException {

        return super.supportsMultipleTransactions();
    }

    @Override
    public boolean supportsNamedParameters() throws SQLException {

        return super.supportsNamedParameters();
    }

    @Override
    public boolean supportsNonNullableColumns() throws SQLException {

        return super.supportsNonNullableColumns();
    }

    @Override
    public boolean supportsOpenCursorsAcrossCommit() throws SQLException {

        return super.supportsOpenCursorsAcrossCommit();
    }

    @Override
    public boolean supportsOpenCursorsAcrossRollback() throws SQLException {

        return super.supportsOpenCursorsAcrossRollback();
    }

    @Override
    public boolean supportsOpenStatementsAcrossCommit() throws SQLException {

        return super.supportsOpenStatementsAcrossCommit();
    }

    @Override
    public boolean supportsOpenStatementsAcrossRollback() throws SQLException {

        return super.supportsOpenStatementsAcrossRollback();
    }

    @Override
    public boolean supportsOrderByUnrelated() throws SQLException {

        return super.supportsOrderByUnrelated();
    }

    @Override
    public boolean supportsOuterJoins() throws SQLException {

        return super.supportsOuterJoins();
    }

    @Override
    public boolean supportsPositionedDelete() throws SQLException {

        return super.supportsPositionedDelete();
    }

    @Override
    public boolean supportsPositionedUpdate() throws SQLException {

        return super.supportsPositionedUpdate();
    }

    @Override
    public boolean supportsResultSetConcurrency(int type, int concurrency) throws SQLException {

        return super.supportsResultSetConcurrency(type, concurrency);
    }

    @Override
    public boolean supportsResultSetHoldability(int holdability) throws SQLException {

        return super.supportsResultSetHoldability(holdability);
    }

    @Override
    public boolean supportsResultSetType(int type) throws SQLException {

        return super.supportsResultSetType(type);
    }

    @Override
    public boolean supportsSavepoints() throws SQLException {

        return super.supportsSavepoints();
    }

    @Override
    public boolean supportsSchemasInDataManipulation() throws SQLException {

        return super.supportsSchemasInDataManipulation();
    }

    @Override
    public boolean supportsSchemasInIndexDefinitions() throws SQLException {

        return super.supportsSchemasInIndexDefinitions();
    }

    @Override
    public boolean supportsSchemasInPrivilegeDefinitions() throws SQLException {

        return super.supportsSchemasInPrivilegeDefinitions();
    }

    @Override
    public boolean supportsSchemasInProcedureCalls() throws SQLException {

        return super.supportsSchemasInProcedureCalls();
    }

    @Override
    public boolean supportsSchemasInTableDefinitions() throws SQLException {

        return super.supportsSchemasInTableDefinitions();
    }

    @Override
    public boolean supportsSelectForUpdate() throws SQLException {

        return super.supportsSelectForUpdate();
    }

    @Override
    public boolean supportsStatementPooling() throws SQLException {

        return super.supportsStatementPooling();
    }

    @Override
    public boolean supportsStoredFunctionsUsingCallSyntax() throws SQLException {

        return super.supportsStoredFunctionsUsingCallSyntax();
    }

    @Override
    public boolean supportsStoredProcedures() throws SQLException {

        return super.supportsStoredProcedures();
    }

    @Override
    public boolean supportsSubqueriesInComparisons() throws SQLException {

        return super.supportsSubqueriesInComparisons();
    }

    @Override
    public boolean supportsSubqueriesInExists() throws SQLException {

        return super.supportsSubqueriesInExists();
    }

    @Override
    public boolean supportsSubqueriesInIns() throws SQLException {

        return super.supportsSubqueriesInIns();
    }

    @Override
    public boolean supportsSubqueriesInQuantifieds() throws SQLException {

        return super.supportsSubqueriesInQuantifieds();
    }

    @Override
    public boolean supportsTableCorrelationNames() throws SQLException {

        return super.supportsTableCorrelationNames();
    }

    @Override
    public boolean supportsTransactionIsolationLevel(int level) throws SQLException {

        return super.supportsTransactionIsolationLevel(level);
    }

    @Override
    public boolean supportsTransactions() throws SQLException {

        return super.supportsTransactions();
    }

    @Override
    public boolean supportsUnion() throws SQLException {

        return super.supportsUnion();
    }

    @Override
    public boolean supportsUnionAll() throws SQLException {

        return super.supportsUnionAll();
    }

    @Override
    public boolean updatesAreDetected(int type) throws SQLException {

        return super.updatesAreDetected(type);
    }

    @Override
    public boolean usesLocalFilePerTable() throws SQLException {

        return super.usesLocalFilePerTable();
    }

    @Override
    public boolean usesLocalFiles() throws SQLException {

        return super.usesLocalFiles();
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {

        return super.isWrapperFor(iface);
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return this.metaData.unwrap(iface);
    }
}
