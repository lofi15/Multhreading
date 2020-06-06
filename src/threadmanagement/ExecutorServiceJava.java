package threadmanagement;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class MyThread implements Runnable{

	private int id;
	
	public MyThread(int id) {
		this.id=id;
	}
	
	@Override
	public void run(){
		
		System.out.println("starting thread : "+id);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		
		System.out.println("ending thread :"+id);
		
	}
	
}

public class ExecutorServiceJava {
	
	public static void main(String [] args) {
		ExecutorServiceJava esj = new ExecutorServiceJava();
		esj.dosomething();
		System.out.println("here");
	}
	
	public void dosomething() {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		for(int i = 0 ; i < 5 ; i++) {
			executorService.submit(new MyThread(i));
		}
		executorService.shutdown();
		try {
			
			executorService.awaitTermination(1, TimeUnit.MINUTES);
			
		} catch (InterruptedException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		System.out.println("all tasks submitted");
	}
	
	
}


