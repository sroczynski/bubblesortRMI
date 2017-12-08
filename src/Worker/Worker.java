package Worker;

import java.rmi.Naming;

import Server.IJob;
import Server.Job;

public class Worker {

	IJob job;
	
	/**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Worker p = new Worker();
        
        p.getJobTracker();
        
        p.listJobs();
        
        System.out.println("No more Jobs!");
    }
	
    private void getJobTracker() {
        String url = "rmi://localhost:" + 8877 + "/tracker";
        try {
            job = (IJob)Naming.lookup(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
    private void listJobs() {
        int[] array;
        try {
            while ((array = job.getJob()) != null) {
            	System.out.println("Get another job");
                
            	Thread.sleep(1000);

                int[] sortedArray = bubbleSort(array);
                
                job.sendJobResult(sortedArray);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
    private int[] bubbleSort(int[] arr) {
        int val;

        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j+1]) {
                	val = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = val;
                }
            }
        }

        return arr;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
