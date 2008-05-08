package routines.system;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public interface IPersistableLookupRow<R> {

    public void writeKeysData(DataOutputStream dos);
    
    public void readKeysData(DataInputStream dis);
    
    public int writeValuesData(DataOutputStream dos);
    
    public void readValuesData(DataInputStream dis);
    
    public void copyDataTo(R other);
    
    public void copyKeysDataTo(R other);

}
