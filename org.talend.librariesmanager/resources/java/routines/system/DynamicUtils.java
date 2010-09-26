/**
 * 
 */
package routines.system;

/**
 * @author Administrator
 * 
 */
public class DynamicUtils {

    /**
     * @author parham parvizi
     * @description: write all the values in the dynamic schema to a delimited file writer. fields are separated by the
     * fieldSeparator.
     * @param column the dynamic column to write
     * @param out a java.io.Writer
     * @param fieldSeparator field delimiter
     * @throws java.io.IOException
     */
    public static void writeValuesToDelimitedFile(Dynamic column, java.io.Writer out, String fieldSeparator)
            throws java.io.IOException {
        for (int i = 0; i < column.getColumnCount(); i++) {
            out.write(String.valueOf(column.getColumnValue(i)));
            if (i != (column.getColumnCount() - 1))
                out.write(fieldSeparator);
        }
        out.flush();
    }

    public static void writeHeaderToDelimitedFile(Dynamic column, java.io.Writer out, String fieldSeparator)
            throws java.io.IOException {
        for (int i = 0; i < column.getColumnCount(); i++) {
            out.write(column.getColumnMetadata(i).getName());
            if (i != (column.getColumnCount() - 1))
                out.write(fieldSeparator);
        }
        out.flush();
    }

    public static void readColumnsFromDelimitedFile(Dynamic column, org.talend.fileprocess.FileInputDelimited fid,
            int fixedColumnCount) throws Exception {
        int fieldCount = fid.getColumnsCountOfCurrentRow();
        for (int i = 0; i < column.getColumnCount(); i++) {
            if ((fixedColumnCount + i) < fieldCount)
                column.addColumnValue(fid.get((fixedColumnCount + i)));
            else
                column.addColumnValue("");
        }
    }

    public static void readColumnsFromDatabase(Dynamic column, java.sql.ResultSet rs) throws Exception {
        column.clearColumnValues();
        for (int i = 0; i < column.getColumnCount(); i++) {
            DynamicMetadata dcm = column.getColumnMetadata(i);
            if ("id_String".equals(dcm.getType()))
                column.addColumnValue(rs.getString(dcm.getDbName()));
            else if ("id_Integer".equals(dcm.getType()))
                column.addColumnValue(rs.getInt(dcm.getDbName()));
            else if ("id_Long".equals(dcm.getType()))
                column.addColumnValue(rs.getLong(dcm.getDbName()));
            else if ("id_Date".equals(dcm.getType()))
                column.addColumnValue(rs.getTimestamp(dcm.getDbName()));
            else if ("id_Double".equals(dcm.getType()))
                column.addColumnValue(rs.getDouble(dcm.getDbName()));
            else
                column.addColumnValue(rs.getObject(dcm.getDbName()));
        }
    }

    public static int writeColumnsToDatabse(Dynamic column, java.sql.PreparedStatement pstmt, int fixedColumnCount)
            throws Exception {

        for (int i = 0; i < column.getColumnCount(); i++) {
            // DynamicColumnMetadata dcm = column.getColumnMetadata(i);
            Object value = column.getColumnValue(i);

            // if ("id_String".equals(dcm.type)) {
            if (value == null)
                pstmt.setNull((fixedColumnCount + i + 1), java.sql.Types.VARCHAR);
            else
                pstmt.setString((fixedColumnCount + i + 1), String.valueOf(value));
            // }
        }
        return column.getColumnCount();
    }

    public static String getCreateTableSQL(Dynamic column, String database) {
        StringBuilder sql = new StringBuilder();
        for (int i = 0; i < column.getColumnCount(); i++) {
            DynamicMetadata dcm = column.getColumnMetadata(i);
            sql.append(" `" + dcm.getDbName() + "` ");
            sql.append(Dynamic.getDBTypeFromTalendType(database, dcm.getType(), dcm.getLength(), dcm.getPrecision()));

            // if ("id_String".equals(dcm.getType()))
            // sql.append("VARCHAR(" + String.valueOf(dcm.getLength()) + ") ");
            // else if ("id_Integer".equals(dcm.getType()))
            // sql.append("INT(" + String.valueOf(dcm.getLength()) + ") ");
            // else if ("id_Long".equals(dcm.getType()))
            // sql.append("BIGINT ");
            // else if ("id_Double".equals(dcm.getType()))
            // sql.append("DOUBLE(" + String.valueOf(dcm.getLength()) + "," + String.valueOf(dcm.getPrecision()) +
            // ") ");
            // else if ("id_Date".equals(dcm.getType())) {
            // sql.append(" DEFAULT NULL ");
            // }

            if (dcm.isNullable() == false) {
                sql.append(" NOT NULL ");
            }

            if (i < (column.getColumnCount() - 1)) {
                sql.append(", ");
            }
        }
        return sql.toString();
    }

    public static String getInsertIntoStmtColumnsList(Dynamic column) {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < column.getColumnCount(); i++) {
            list.append(column.getColumnMetadata(i).getName());
            if (i < (column.getColumnCount() - 1))
                list.append(",");
        }
        return list.toString();
    }

    public static String getInsertIntoStmtValuesList(Dynamic column) {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < column.getColumnCount(); i++) {
            if (i < (column.getColumnCount() - 1))
                list.append("?,");
            else
                list.append("?");
        }
        return list.toString();
    }

    public static String getUpdateSet(Dynamic column) {
        StringBuilder set = new StringBuilder();
        for (int i = 0; i < column.getColumnCount(); i++) {
            if (i != 0)
                set.append(", ");
            set.append(column.getColumnMetadata(i).getName() + " = ?");
        }
        return set.toString();
    }
}
