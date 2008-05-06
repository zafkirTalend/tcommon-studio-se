package routines.system;

import java.io.DataInputStream;
import java.io.DataOutputStream;



public interface IPersistableRow<R> {

    public void writeData(DataOutputStream dos);
    
    public void readData(DataInputStream dis);

}
