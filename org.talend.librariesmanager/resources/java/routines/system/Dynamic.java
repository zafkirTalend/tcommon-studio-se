package routines.system;

import java.util.ArrayList;
import java.util.List;

public class Dynamic {

    public List<DynamicMetadata> metadatas;

    private List<Object> values = new ArrayList<Object>(30);

    // private static String[][] dbMapping = { { "VARCHAR", "id_String" }, { "BIGINT", "id_Long" }, { "INTEGER",
    // "id_Integer" },
    // { "DOUBLE", "id_Double" }, { "DATETIME", "id_Date" } };

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
           // values.add(index, value);
        	modifyValue(index, value);
        }
    }
    /**
     * Need to replace the element if the index already exists or 
     * add the new element if it does not exist yet. 
     * @param index
     * @param value
     */
    private  void modifyValue(int index, Object value){
    	if(values.get(index) != null)
    		values.set(index, value);
    	else
    		values.add(index,value);
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
        String talendType = null;

        talendType = MetadataTalendType.getDefaultSelectedTalendType(dbName, dbType, length, precision);
        if (talendType == null) {
            talendType = "id_String";
        }

        return talendType;
    }

    /**
     * Comment method "getDBTypeFromTalendType"<br>
     * generate and return a string composed of DB type, length and precision, such
     * as:int(4),String(200),DATE,DECIMAL(20,4) .
     * 
     * @author talend
     * @param dbmsId
     * @param talendType
     * @param length
     * @param precision
     * @return
     */
    public static String getDBTypeFromTalendType(String dbmsId, String talendType, int length, int precision) {

        String dbmsType = MetadataTalendType.getDefaultSelectedDbType(dbmsId, talendType, length, precision);

        StringBuilder returnResult = new StringBuilder(dbmsType);

        boolean isIgnoreLen = ("true").equals(MetadataTalendType.getDefaultDBTypes(dbmsId, dbmsType,
                MetadataTalendType.IGNORE_LEN));
        String defaultLength = MetadataTalendType.getDefaultDBTypes(dbmsId, dbmsType, MetadataTalendType.DEFAULT_LENGTH);
        // generate a string like this:(100,3)
        if (!isIgnoreLen && defaultLength != null) {
            returnResult.append("(");
            if (length > 0) {
                returnResult.append(length);
            } else {// use the default length of the DB type
                returnResult.append(defaultLength);
            }
            boolean isIgnorePre = ("true").equals(MetadataTalendType.getDefaultDBTypes(dbmsId, dbmsType,
                    MetadataTalendType.IGNORE_PRE));
            String defaultPre = MetadataTalendType.getDefaultDBTypes(dbmsId, dbmsType, MetadataTalendType.DEFAULT_PRECISION);
            if (!isIgnorePre && defaultPre != null) {
                returnResult.append(",");
                if (precision > 0) {
                    returnResult.append(precision);
                } else {// use the default precision of the db type
                    returnResult.append(defaultPre);
                }
            }
            returnResult.append(")");
        }
        return returnResult.toString();

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
