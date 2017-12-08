package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IJob extends Remote {
    public int[] getJob() throws RemoteException;
    public void sendJobResult(int[] result) throws RemoteException;
}
