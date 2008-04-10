package routines.system;



public interface IPersistableRow<R> {

    public byte[] toData();

    public void loadData(byte[] data);

}
