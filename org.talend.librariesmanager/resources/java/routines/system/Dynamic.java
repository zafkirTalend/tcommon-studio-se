package routines.system;

import java.util.ArrayList;
import java.util.List;

public class Dynamic {

    public List<DynamicMetadata> metadatas;

    private List<Object> values = new ArrayList<Object>(30);

    private static String[][] dbMapping = { { "VARCHAR", "id_String" }, { "BIGINT", "id_Long" }, { "INTEGER", "id_Integer" },
            { "DOUBLE", "id_Double" }, { "DATETIME", "id_Date" } };

    // private constructor for internal/static use only
    public Dynamic() {
        this.metadatas = new ArrayList<DynamicMetadata>();
    }

    public int getColumnCount() {
        return this.metadatas.size();
    }

    public DynamicMetadata getColumnMetadata(int index) {
        return this.metadatas.get(index);
    }

    public Object getColumnValue(int index) {
        if (index < this.metadatas.size()) {
            return values.get(index);
        }
        return null;
    }

    public void addColumnValue(Object value) {
        if (values.size() < metadatas.size())
            values.add(value);
    }

    public void setColumnValue(int index, Object value) {
        if (index < this.metadatas.size()) {
            values.add(index, value);
        }
    }

    public void clearColumnValues() {
        values.clear();
    }

    public void writeValuesToStream(java.io.OutputStream out, String delimiter) throws java.io.IOException {
        for (int i = 0; i < metadatas.size(); i++) {
            out.write((String.valueOf(values.get(i))).getBytes());
            if (i != (metadatas.size() - 1))
                out.write(delimiter.getBytes());
        }
        out.flush();
    }

    public void writeHeaderToStream(java.io.OutputStream out, String delimiter) throws java.io.IOException {
        for (int i = 0; i < metadatas.size(); i++) {
            out.write((String.valueOf(metadatas.get(i).getName())).getBytes());
            if (i != (metadatas.size() - 1))
                out.write(delimiter.getBytes());
        }
        out.flush();
    }

    public static String getTalendTypeFromDBType(String dbName, String dbType, int length, int precision) {
        // TODO:: replace this fucntion with full implementation with XML mappings
        String talendType = "id_String";
        for (int i = 0; i < Dynamic.dbMapping.length; i++) {
            if (dbType.contains(Dynamic.dbMapping[i][0]))
                return Dynamic.dbMapping[i][1];
        }
        return talendType;
    }

    public static String getDBTypeFromTalendType(String dbVender, String talendType, int length, int precision) {
        // TODO :: implement correct type creation based on the DB system
        if (length > 0)
            return "VARCHAR(" + String.valueOf(length) + ")";
        else
            return "VARCHAR(100)";
    }

    public String toString() {
        StringBuffer out = new StringBuffer();
        for (int i = 0; i < metadatas.size(); i++) {
            out.append((String.valueOf(values.get(i))));
            if (i != (metadatas.size() - 1))
                out.append(" - ");
        }
        return out.toString();
    }

    public Boolean equals(Dynamic D) {
        Boolean b = true;
        if (this.metadatas.size() != D.metadatas.size()) {
            b = false;
        } else {
            for (int i = 0; i < this.metadatas.size(); i++) {
                if (!(this.metadatas.get(i).equals(D.metadatas.get(i)))) {
                    b = false;
                }
            }
        }
        return b;
    }
}
