package routines.system;

public interface IPersistableLookupRow<R> {

    public byte[] toKeysData();

    public void loadKeysData(byte[] keysData);

    public byte[] toValuesData();

    public void loadValuesData(byte[] valuesData);

    public void copyDataTo(R other);
    
    public void copyKeysDataTo(R other);

}
