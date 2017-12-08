package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Job extends UnicastRemoteObject implements IJob {

	public Job() throws RemoteException{
        super();
    }
	
	@Override
	public int[] getJob() throws RemoteException {
		// TODO Auto-generated method stub
		return (Server.jobs.size() > 0)? Server.jobs.remove(0) : null;
	}

	@Override
	public void sendJobResult(int[] result) throws RemoteException {
		// TODO Auto-generated method stub
		Server.results.add(result);
        Server.listResults();
	}
}
