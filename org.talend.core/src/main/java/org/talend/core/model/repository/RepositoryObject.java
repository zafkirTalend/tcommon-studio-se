// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.talend.core.i18n.Messages;
import org.talend.core.model.metadata.builder.connection.ConnectionFactory;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.metadata.builder.connection.MetadataColumn;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.metadata.builder.connection.QueriesConnection;
import org.talend.core.model.metadata.builder.connection.Query;
import org.talend.core.model.properties.BusinessProcessItem;
import org.talend.core.model.properties.CSVFileConnectionItem;
import org.talend.core.model.properties.ContextItem;
import org.talend.core.model.properties.DatabaseConnectionItem;
import org.talend.core.model.properties.DelimitedFileConnectionItem;
import org.talend.core.model.properties.DocumentationItem;
import org.talend.core.model.properties.ExcelFileConnectionItem;
import org.talend.core.model.properties.GenericSchemaConnectionItem;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.ItemState;
import org.talend.core.model.properties.JobDocumentationItem;
import org.talend.core.model.properties.JobletDocumentationItem;
import org.talend.core.model.properties.JobletProcessItem;
import org.talend.core.model.properties.LDAPSchemaConnectionItem;
import org.talend.core.model.properties.LdifFileConnectionItem;
import org.talend.core.model.properties.LinkDocumentationItem;
import org.talend.core.model.properties.PositionalFileConnectionItem;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.PropertiesFactory;
import org.talend.core.model.properties.Property;
import org.talend.core.model.properties.RegExFileConnectionItem;
import org.talend.core.model.properties.RoutineItem;
import org.talend.core.model.properties.SnippetItem;
import org.talend.core.model.properties.User;
import org.talend.core.model.properties.WSDLSchemaConnectionItem;
import org.talend.core.model.properties.XmlFileConnectionItem;
import org.talend.core.model.properties.util.PropertiesSwitch;

/**
 * DOC nrousseau class global comment. Detailled comment
 */
public class RepositoryObject implements IRepositoryObject, IAdaptable {

    protected Property property = PropertiesFactory.eINSTANCE.createProperty();

    public RepositoryObject() {
    }

    public RepositoryObject(Property property) {
        this.property = property;
    }

    public Property getProperty() {
        return this.property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public RepositoryObject(String id, String label) {
        this.setId(id);
        this.setLabel(label);
    }

    public User getAuthor() {
        return this.property.getAuthor();
    }

    public Date getCreationDate() {
        return this.property.getCreationDate();
    }

    public String getDescription() {
        return this.property.getDescription();
    }

    public String getId() {
        return this.property.getId();
    }

    public Date getModificationDate() {
        return this.property.getModificationDate();
    }

    public String getLabel() {
        return this.property.getLabel();
    }

    public String getPurpose() {
        return this.property.getPurpose();
    }

    public String getStatusCode() {
        return this.property.getStatusCode();
    }

    public String getVersion() {
        return this.property.getVersion();
    }

    public void setAuthor(User value) {
        this.property.setAuthor(value);
    }

    public void setCreationDate(Date value) {
        this.property.setCreationDate(value);
    }

    public void setDescription(String value) {
        this.property.setDescription(value);
    }

    public void setId(String value) {
        this.property.setId(value);
    }

    public void setModificationDate(Date value) {
        this.property.setModificationDate(value);
    }

    public void setLabel(String value) {
        this.property.setLabel(value);
    }

    public void setPurpose(String value) {
        this.property.setPurpose(value);
    }

    public void setStatusCode(String value) {
        this.property.setStatusCode(value);
    }

    public void setVersion(String value) {
        this.property.setVersion(value);
    }

    public ERepositoryObjectType getType() {
        return (ERepositoryObjectType) new PropertiesSwitch() {

            public Object caseDocumentationItem(DocumentationItem object) {
                return ERepositoryObjectType.DOCUMENTATION;
            }

            public Object caseRoutineItem(RoutineItem object) {
                return ERepositoryObjectType.ROUTINES;
            }

            public Object caseContextItem(ContextItem object) {
                return ERepositoryObjectType.CONTEXT;
            }

            public Object caseSnippetItem(SnippetItem object) {
                return ERepositoryObjectType.SNIPPETS;
            }

            public Object caseProcessItem(ProcessItem object) {
                return ERepositoryObjectType.PROCESS;
            }

            /*
             * (non-Javadoc)
             * 
             * @see org.talend.core.model.properties.util.PropertiesSwitch#caseJobletProcessItem(org.talend.core.model.properties.JobletProcessItem)
             */
            @Override
            public Object caseJobletProcessItem(JobletProcessItem object) {
                return ERepositoryObjectType.JOBLET;
            }

            public Object caseBusinessProcessItem(BusinessProcessItem object) {
                return ERepositoryObjectType.BUSINESS_PROCESS;
            }

            public Object caseCSVFileConnectionItem(CSVFileConnectionItem object) {
                throw new IllegalStateException(Messages.getString("RepositoryObject.NotImplemented")); //$NON-NLS-1$
            }

            public Object caseDatabaseConnectionItem(DatabaseConnectionItem object) {
                return ERepositoryObjectType.METADATA_CONNECTIONS;
            }

            public Object caseDelimitedFileConnectionItem(DelimitedFileConnectionItem object) {
                return ERepositoryObjectType.METADATA_FILE_DELIMITED;
            }

            public Object casePositionalFileConnectionItem(PositionalFileConnectionItem object) {
                return ERepositoryObjectType.METADATA_FILE_POSITIONAL;
            }

            public Object caseRegExFileConnectionItem(RegExFileConnectionItem object) {
                return ERepositoryObjectType.METADATA_FILE_REGEXP;
            }

            public Object caseXmlFileConnectionItem(XmlFileConnectionItem object) {
                return ERepositoryObjectType.METADATA_FILE_XML;
            }

            public Object caseExcelFileConnectionItem(ExcelFileConnectionItem object) {
                return ERepositoryObjectType.METADATA_FILE_EXCEL;
            }

            public Object caseLdifFileConnectionItem(LdifFileConnectionItem object) {
                return ERepositoryObjectType.METADATA_FILE_LDIF;
            }

            public Object caseGenericSchemaConnectionItem(GenericSchemaConnectionItem object) {
                return ERepositoryObjectType.METADATA_GENERIC_SCHEMA;
            }

            public Object caseLDAPSchemaConnectionItem(LDAPSchemaConnectionItem object) {
                return ERepositoryObjectType.METADATA_LDAP_SCHEMA;
            }

            public Object caseJobDocumentationItem(JobDocumentationItem object) {
                return ERepositoryObjectType.JOB_DOC;
            }

            public Object caseJobletDocumentationItem(JobletDocumentationItem object) {
                return ERepositoryObjectType.JOBLET_DOC;
            }

            public Object caseWSDLSchemaConnectionItem(WSDLSchemaConnectionItem object) {
                return ERepositoryObjectType.METADATA_WSDL_SCHEMA;
            }

            @Override
            public Object caseLinkDocumentationItem(LinkDocumentationItem object) {

                return ERepositoryObjectType.DOCUMENTATION;
            }

            public Object defaultCase(EObject object) {
                throw new IllegalStateException();
            }
        }.doSwitch(property.getItem());
    }

    public List<IRepositoryObject> getChildren() {
        List<IRepositoryObject> toReturn = new ArrayList<IRepositoryObject>();
        return toReturn;
    }

    @SuppressWarnings("unchecked")
    public RepositoryObject cloneNewObject() {
        RepositoryObject object = new RepositoryObject();
        try {
            Property connectionProperty = PropertiesFactory.eINSTANCE.createProperty();
            connectionProperty.setAuthor(getAuthor());
            connectionProperty.setCreationDate(getCreationDate());
            connectionProperty.setDescription(getDescription());
            connectionProperty.setId(getId());
            connectionProperty.setLabel(getLabel());
            connectionProperty.setModificationDate(getModificationDate());
            connectionProperty.setPurpose(getPurpose());
            connectionProperty.setStatusCode(getStatusCode());
            connectionProperty.setVersion(getVersion());
            final Item oldItem = getProperty().getItem();
            DatabaseConnectionItem newItem = null;
            if (oldItem instanceof DatabaseConnectionItem) {
                DatabaseConnectionItem item = (DatabaseConnectionItem) oldItem;
                newItem = PropertiesFactory.eINSTANCE.createDatabaseConnectionItem();
                newItem.setProperty(connectionProperty);
                ItemState state = PropertiesFactory.eINSTANCE.createItemState();
                state.setCommitDate(oldItem.getState().getCommitDate());
                state.setDeleted(oldItem.getState().isDeleted());
                state.setLockDate(oldItem.getState().getLockDate());
                state.setLocked(oldItem.getState().isLocked());
                state.setLocker(oldItem.getState().getLocker());
                state.setPath(oldItem.getState().getPath());
                newItem.setState(state);

                final DatabaseConnection connection = (DatabaseConnection) item.getConnection();
                DatabaseConnection conn = ConnectionFactory.eINSTANCE.createDatabaseConnection();
                conn.setProperties(connection.getProperties());
                conn.setDatabaseType(connection.getDatabaseType());
                conn.setUsername(connection.getUsername());
                conn.setPort(connection.getPort());
                conn.setPassword(connection.getPassword());
                conn.setSID(connection.getSID());
                conn.setLabel(connection.getLabel());
                conn.setDatasourceName(connection.getDatasourceName());
                conn.setSchema(connection.getSchema());
                conn.setURL(connection.getURL());
                conn.setDriverClass(connection.getDriverClass());
                conn.setComment(connection.getComment());
                conn.setDivergency(connection.isDivergency());
                conn.setFileFieldName(connection.getFileFieldName());
                conn.setId(connection.getId());
                conn.setNullChar(connection.getNullChar());
                // conn.setReadOnly(connection.isReadOnly());
                conn.setServerName(connection.getServerName());
                conn.setSqlSynthax(connection.getSqlSynthax());
                conn.setStringQuote(connection.getStringQuote());
                conn.setSynchronised(connection.isSynchronised());
                conn.setVersion(connection.getVersion());
                conn.setDbmsId(connection.getDbmsId());
                conn.setProductId(connection.getProductId());
                final QueriesConnection queries = connection.getQueries();
                QueriesConnection newQ = null;
                if (queries != null) {
                    newQ = ConnectionFactory.eINSTANCE.createQueriesConnection();
                    newQ.setConnection(conn);
                    final List<Query> query = queries.getQuery();
                    List<Query> queries2 = new ArrayList<Query>();
                    for (Query query2 : query) {
                        Query newQuery = ConnectionFactory.eINSTANCE.createQuery();
                        newQuery.setProperties(query2.getProperties());
                        newQuery.setComment(query2.getComment());
                        newQuery.setDivergency(query2.isDivergency());
                        newQuery.setId(query2.getId());
                        newQuery.setLabel(query2.getLabel());
                        newQuery.setQueries(newQ);
                        // newQuery.setReadOnly(query2.isReadOnly());
                        newQuery.setSynchronised(query2.isSynchronised());
                        newQuery.setValue(query2.getValue());
                        queries2.add(newQuery);
                    }
                    newQ.getQuery().addAll(queries2);
                }

                final List<MetadataTable> tables = connection.getTables();
                List<MetadataTable> newTs = null;
                if (tables != null) {
                    newTs = new ArrayList<MetadataTable>();
                    for (MetadataTable table : tables) {
                        MetadataTable table2 = ConnectionFactory.eINSTANCE.createMetadataTable();
                        table2.setProperties(table.getProperties());
                        table2.setComment(table.getComment());
                        table2.setConnection(conn);
                        table2.setDivergency(table.isDivergency());
                        table2.setId(table.getId());
                        table2.setLabel(table.getLabel());
                        // table2.setReadOnly(table.isReadOnly());
                        table2.setSourceName(table.getSourceName());
                        table2.setSynchronised(table.isSynchronised());
                        table2.setTableType(table.getTableType());
                        List<MetadataColumn> list = new ArrayList<MetadataColumn>();
                        for (MetadataColumn column : (List<MetadataColumn>) table.getColumns()) {
                            MetadataColumn column2 = ConnectionFactory.eINSTANCE.createMetadataColumn();
                            column2.setProperties(column.getProperties());
                            column2.setComment(column.getComment());
                            column2.setDefaultValue(column.getDefaultValue());
                            column2.setDivergency(column.isDivergency());
                            column2.setId(column.getId());
                            column2.setKey(column.isKey());
                            column2.setLabel(column.getLabel());
                            column2.setLength(column.getLength());
                            column2.setNullable(column.isNullable());
                            column2.setOriginalField(column.getOriginalField());
                            column2.setPattern(column.getPattern());
                            column2.setPrecision(column.getPrecision());
                            // column2.setReadOnly(column.isReadOnly());
                            column2.setSourceType(column.getSourceType());
                            column2.setSynchronised(column.isSynchronised());
                            column2.setTable(table2);
                            column2.setTalendType(column.getTalendType());
                            list.add(column2);
                        }
                        table2.getColumns().addAll(list);
                        newTs.add(table2);
                    }
                }

                conn.setQueries(newQ);
                conn.getTables().addAll(newTs);
                newItem.setConnection(conn);
            }
            connectionProperty.setItem(newItem);
            object.setProperty(connectionProperty);
        } catch (Exception e) {
            e.printStackTrace();
            // do notbing.
        }
        return object;
    }

    public Object getAdapter(Class adapter) {
        return null;
    }
}
