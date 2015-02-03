// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.metadata;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.SchemaBuilder.BaseFieldTypeBuilder;
import org.apache.avro.SchemaBuilder.FieldAssembler;
import org.apache.avro.SchemaBuilder.FieldTypeBuilder;
import org.apache.avro.compiler.specific.SpecificCompiler;
import org.talend.commons.exception.ExceptionHandler;

/**
 * Meta Data Table. Contains all the columns. <br/>
 * $Id: MetadataTable.java 46622 2010-08-11 10:04:57Z wliu $
 */
public class AvroMetadataTable extends MetadataTable {

    private Schema schema = null;

    @Override
    public IMetadataTable clone(boolean withCustoms) {
        IMetadataTable clonedMetadata = null;
        try {
            clonedMetadata = super.clone(false);

            List<IMetadataColumn> clonedMetaColumns = new ArrayList<IMetadataColumn>();
            clonedMetadata.setListColumns(clonedMetaColumns);
            for (int i = 0; i < super.getListColumns().size(); i++) {
                clonedMetaColumns.add(super.getListColumns().get(i).clone(withCustoms));
            }
            List<IMetadataColumn> clonedMetaUnusedColumns = new ArrayList<IMetadataColumn>();
            clonedMetadata.setUnusedColumns(clonedMetaUnusedColumns);
            for (int i = 0; i < super.getListUnusedColumns().size(); i++) {
                clonedMetaColumns.add(super.getListUnusedColumns().get(i).clone(withCustoms));
            }
            clonedMetadata.setTableName(this.getTableName());
            clonedMetadata.setLabel(this.getLabel());
            clonedMetadata.setAdditionalProperties(new HashMap<String, String>(super.getAdditionalProperties()));
        } catch (Exception e) {
            // e.printStackTrace();
            ExceptionHandler.process(e);
        }
        return clonedMetadata;
    }

    /**
     * cloned without custom columns by default.
     */
    @Override
    public IMetadataTable clone() {
        return clone(false);
    }

    public void setAvroSchema(String connectionName) {
        if (schema == null) {
            FieldAssembler<Schema> fieldAssembler = SchemaBuilder.record("AvroStruct" + connectionName)
                    .prop(connectionName, connectionName)
                    // TODO set this path generically. (get data from Process class)
                    .namespace("emr24.s_dfs_0_1") //$NON-NLS-1$
                    .fields();

            for (IMetadataColumn column : super.getListColumns()) {
                // Set field name
                BaseFieldTypeBuilder<Schema> fieldTypeSchema = fieldAssembler.name(column.getLabel()).type();

                // Set Nullable
                if (column.isNullable()) {
                    fieldTypeSchema = ((FieldTypeBuilder<Schema>) fieldTypeSchema).nullable();
                }

                // Set field type
                if ("id_Boolean".equals(column.getTalendType())) { //$NON-NLS-1$
                    fieldTypeSchema.booleanType().noDefault();
                } else if ("id_Byte".equals(column.getTalendType())) { //$NON-NLS-1$
                    // TODO Does not work
                    fieldTypeSchema.intBuilder().prop("java-class", "java.lang.Byte").endInt().noDefault(); //$NON-NLS-1$  //$NON-NLS-2$
                } else if ("id_byte[]".equals(column.getTalendType())) { //$NON-NLS-1$
                    fieldTypeSchema.bytesType().noDefault();
                } else if ("id_Character".equals(column.getTalendType())) { //$NON-NLS-1$
                    // TODO Does not work
                    fieldTypeSchema.intBuilder().prop("java-class", "java.lang.Character").endInt().noDefault(); //$NON-NLS-1$  //$NON-NLS-2$
                } else if ("id_Date".equals(column.getTalendType())) { //$NON-NLS-1$
                    // TODO Does not work
                    fieldTypeSchema.longBuilder().prop("java-class", "java.util.Date").endLong().noDefault(); //$NON-NLS-1$  //$NON-NLS-2$
                } else if ("id_Double".equals(column.getTalendType())) { //$NON-NLS-1$
                    fieldTypeSchema.doubleType().noDefault();
                } else if ("id_Float".equals(column.getTalendType())) { //$NON-NLS-1$
                    fieldTypeSchema.floatType().noDefault();
                } else if ("id_BigDecimal".equals(column.getTalendType())) { //$NON-NLS-1$
                    fieldTypeSchema.stringBuilder().prop("java-class", "java.math.BigDecimal").endString().noDefault(); //$NON-NLS-1$  //$NON-NLS-2$
                } else if ("id_Integer".equals(column.getTalendType())) { //$NON-NLS-1$
                    fieldTypeSchema.intType().noDefault();
                } else if ("id_Long".equals(column.getTalendType())) { //$NON-NLS-1$
                    fieldTypeSchema.longType().noDefault();
                } else if ("id_Short".equals(column.getTalendType())) { //$NON-NLS-1$
                    // TODO Copy/past of
                    // https://avro.apache.org/docs/1.7.6/api/java/org/apache/avro/reflect/package-summary.html
                    // but does not work
                    fieldTypeSchema.intBuilder().prop("java-class", "java.lang.Short").endInt().noDefault(); //$NON-NLS-1$  //$NON-NLS-2$
                } else if ("id_String".equals(column.getTalendType())) { //$NON-NLS-1$
                    // TODO double check string type.
                    fieldTypeSchema.stringType().noDefault();
                    // } else if ("id_List".equals(column.getTalendType())) {
                    // // TODO
                    // } else if ("id_Object".equals(column.getTalendType())) {
                    // //TODO
                }
            }
            schema = fieldAssembler.endRecord();
            System.out.println(schema);

            PrintWriter out;
            try {
                // Generate the schema output and dump it on a file
                // TODO create /tmp/avsc on runtime
                out = new PrintWriter("/tmp/avsc/AvroStruct" + connectionName + ".avsc");
                out.println(schema);
                out.close();

                // Generate the java class from the schema
                SpecificCompiler.compileSchema(new File("/tmp/avsc/AvroStruct" + connectionName + ".avsc"), new File(
                        "/home/pbailly/yoxosluna/.metadata/.plugins/org.eclipse.pde.core/TP_BD_DQ/workspace2/.Java/src/"));

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setListColumns(List<IMetadataColumn> listColumns) {
        super.setListColumns(listColumns);
    }

    @Override
    public void setLabel(String label) {
        super.setLabel(label);
        System.out.println(label);
    }

    /**
     * Getter for schema.
     * 
     * @return the schema
     */
    public Schema getSchema() {
        return this.schema;
    }

    /**
     * Note: for a table with custom columns, the order for the test is really important. It should be
     * outputMetadata.sameMetadataAs (inputMetadata).
     */
    @Override
    public boolean sameMetadataAs(IMetadataTable input, int options) {
        return super.sameMetadataAs(input, options);
    }
}
