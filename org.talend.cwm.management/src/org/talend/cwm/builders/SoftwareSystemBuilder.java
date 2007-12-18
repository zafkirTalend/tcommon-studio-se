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
package org.talend.cwm.builders;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.talend.cwm.constants.SoftwareSystemConstants;
import org.talend.cwm.constants.TypeInfoColumns;
import org.talend.cwm.relational.RelationalFactory;
import org.talend.cwm.relational.TdSqlDataType;
import org.talend.cwm.softwaredeployment.SoftwaredeploymentFactory;
import org.talend.cwm.softwaredeployment.TdSoftwareSystem;
import org.talend.utils.sql.ResultSetUtils;
import orgomg.cwm.foundation.typemapping.TypeSystem;
import orgomg.cwm.foundation.typemapping.TypemappingFactory;

/**
 * @author scorreia
 * 
 * This class create softwaredeployment classes from a connection.
 */
public class SoftwareSystemBuilder extends CwmBuilder {

    private final TdSoftwareSystem softwareSystem;

    private TypeSystem typeSystem;

    public SoftwareSystemBuilder(Connection conn) throws SQLException {
        super(conn);
        softwareSystem = initializeSoftwareSystem();
    }

    private TdSoftwareSystem initializeSoftwareSystem() throws SQLException {
        // --- get informations
        int databaseMinorVersion = databaseMetadata.getDatabaseMinorVersion();
        int databaseMajorVersion = databaseMetadata.getDatabaseMajorVersion();
        String databaseProductName = databaseMetadata.getDatabaseProductName();
        String databaseProductVersion = databaseMetadata.getDatabaseProductVersion();
        String version = Integer.toString(databaseMajorVersion) + "." + databaseMinorVersion;
        print("Database=", databaseProductName + " | " + databaseProductVersion + " " + version);

        // --- create and fill the software system
        TdSoftwareSystem system = SoftwaredeploymentFactory.eINSTANCE.createTdSoftwareSystem();
        system.setName(databaseProductName); // TODO scorreia find the name!
        system.setType(SoftwareSystemConstants.DBMS.toString());
        system.setSubtype(databaseProductName);
        system.setVersion(databaseProductVersion);

        // --- get the types supported by the system
        ResultSet typeInfo = databaseMetadata.getTypeInfo();
        int columnCount = typeInfo.getMetaData().getColumnCount();

        typeSystem = TypemappingFactory.eINSTANCE.createTypeSystem();
        typeSystem.setName(databaseProductName); // TODO scorreia put another name?
        while (typeInfo.next()) {
            // --- get the informations form the DB
            String name = typeInfo.getString(TypeInfoColumns.TYPE_NAME.name());
            int datatype = typeInfo.getInt(TypeInfoColumns.DATA_TYPE.name());
            int precision = typeInfo.getInt(TypeInfoColumns.PRECISION.name());
            String literalPrefix = typeInfo.getString(TypeInfoColumns.LITERAL_PREFIX.name());
            String literalsuffix = typeInfo.getString(TypeInfoColumns.LITERAL_SUFFIX.name());
            String createparams = typeInfo.getString(TypeInfoColumns.CREATE_PARAMS.name());
            short nullable = typeInfo.getShort(TypeInfoColumns.NULLABLE.name());
            boolean casesensitive = typeInfo.getBoolean(TypeInfoColumns.CASE_SENSITIVE.name());
            short searchable = typeInfo.getShort(TypeInfoColumns.SEARCHABLE.name());
            boolean unsignedattribute = typeInfo.getBoolean(TypeInfoColumns.UNSIGNED_ATTRIBUTE.name());
            boolean fixedprecscale = typeInfo.getBoolean(TypeInfoColumns.FIXED_PREC_SCALE.name());
            boolean autoincrement = typeInfo.getBoolean(TypeInfoColumns.AUTO_INCREMENT.name());
            String localtypename = typeInfo.getString(TypeInfoColumns.LOCAL_TYPE_NAME.name());
            short minimumscale = typeInfo.getShort(TypeInfoColumns.MINIMUM_SCALE.name());
            short maximumscale = typeInfo.getShort(TypeInfoColumns.MAXIMUM_SCALE.name());
            int sqldatatype = typeInfo.getInt(TypeInfoColumns.SQL_DATA_TYPE.name());
            int sqldatetimesub = typeInfo.getInt(TypeInfoColumns.SQL_DATETIME_SUB.name());
            int numprecradix = typeInfo.getInt(TypeInfoColumns.NUM_PREC_RADIX.name());

            System.out.println(ResultSetUtils.formatRow(typeInfo, columnCount, 10));

            // --- store the information in CWM structure
            // TODO scorreia change to SQLSimpleType ?
            TdSqlDataType dataType = RelationalFactory.eINSTANCE.createTdSqlDataType();
            dataType.setName(name);
            dataType.setJavaDataType(datatype);
            dataType.setNumericPrecision(precision);
            dataType.setNullable(nullable);
            dataType.setCaseSensitive(casesensitive);
            dataType.setSearchable(searchable);
            dataType.setUnsignedAttribute(unsignedattribute);
            dataType.setAutoIncrement(autoincrement);
            dataType.setLocalTypeName(localtypename);
            dataType.setNumericPrecisionRadix(numprecradix);

            // --- add the element to the list
            typeSystem.getOwnedElement().add(dataType);

            // --- create the mapping with the java language
            // TODO scorreia TypeMapping typeMapping = TypemappingFactory.eINSTANCE.createTypeMapping();
            // typeMapping.

        } // end of loop on typeinfo rows

        // --- add type systems: softwareSystem.getTypespace()
        system.getTypespace().add(typeSystem);

        return system;
    }

    public TdSoftwareSystem getSoftwareSystem() {
        return this.softwareSystem;
    }

    public TypeSystem getTypeSystem() {
        return this.typeSystem;
    }

}
