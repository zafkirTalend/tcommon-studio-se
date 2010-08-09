package routines.system;

public class DynamicMetadata {

    private String name = null;

    private String dbName = null;

    private String type = "id_String";

    private String dbType = "VARCHAR";

    private int length = -1;

    private int precision = -1;

    private String format = null;

    private String description = null;

    private boolean isKey = false;

    private boolean isNullable = true;

    private sourceTypes sourceType = sourceTypes.unknown;

    private int columnPosition = -1;

    public static enum sourceTypes {
        unknown,
        demilitedFile,
        positionalFile,
        database
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getDbType() {
        return dbType;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public int getPrecision() {
        return precision;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setKey(boolean isKey) {
        this.isKey = isKey;
    }

    public boolean isKey() {
        return isKey;
    }

    public void setNullable(boolean isNullable) {
        this.isNullable = isNullable;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public void setSourceType(sourceTypes sourceType) {
        this.sourceType = sourceType;
    }

    public sourceTypes getSourceType() {
        return sourceType;
    }

    public void setColumnPosition(int columnPosition) {
        this.columnPosition = columnPosition;
    }

    public int getColumnPosition() {
        return columnPosition;
    }
}
