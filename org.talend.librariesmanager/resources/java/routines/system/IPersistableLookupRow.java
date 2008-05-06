package routines.system;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public interface IPersistableLookupRow<R> {

    public void toKeysData(DataOutputStream dos);
    
    public void loadKeysData(DataInputStream dis);
    
    public int toValuesData(DataOutputStream dos);
    
    public void loadValuesData(DataInputStream dis);
    
    public void copyDataTo(R other);
    
    public void copyKeysDataTo(R other);

}
