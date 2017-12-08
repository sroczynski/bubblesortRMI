package Server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Server {

	static List<int[]> jobs = Collections.synchronizedList(new ArrayList<int[]>());
	static List<int[]> results = Collections.synchronizedList(new ArrayList<int[]>());
	static int nJobs;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server t = new Server();
		String url;

		t.createJobs(10);
		
		try {
			startRegistry(8877);
			url = "rmi://localhost:" + 8877 + "/tracker";
			
			IJob job = new Job();
			
			Naming.rebind(url, job);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Server Ready!");
	}

	private void createJobs(int nJobs) {
		Server.nJobs = nJobs;
		for (int i = 0; i < nJobs; i++) {
			int[] array = randomArray();
			jobs.add(array);
		}
	}
	
	public static void listResults(){
        if(Server.nJobs != results.size()){
            return;
        }
        
        int i = 1;
        for(int[] collection : results){
        	
        	System.out.println("\nOrder ("+ i +") : \n");
        	
        	int j = 1;
        	for(int num : collection)
        	{        		
        		System.out.print(num + " - ");
        		if((j % 10) == 0)
        		{
        			System.out.println("");
        		}
        		j++;
        	}
        	
        	i++;
        }
    }
	
	private int[] randomArray() {
        int[] arr = new int[10000];
        Random rdn = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rdn.nextInt(10001);
        }
        return arr;
    }

	private static void startRegistry(int port) throws RemoteException {
		try {
			Registry registry = LocateRegistry.getRegistry(port);
			registry.list();
		} catch (RemoteException e) {
			LocateRegistry.createRegistry(port);
		}
	}

}
