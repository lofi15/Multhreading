package threadmanagement;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * CountDownLatch is used to make sure that a task waits 
 * for other threads before it starts
 */


class Worker implements Runnable{
	
	private CountDownLatch latch;
	private int id;
	public Worker(CountDownLatch latch,int id) {
		this.latch=latch;
		this.id=id;
	}

	@Override
	public void run() {
		
		System.out.println("thread has started : "+id);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("thread has stopped : "+id);
		latch.countDown();
		
		
		
	}
	
}
public class LatchJava {
	
	public static void main(String[] args) {
		
		CountDownLatch latch = new CountDownLatch(5);
		
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		
		for(int i = 0 ; i < 5 ; i++) {
			executorService.submit(new Worker(latch,i));
		}
		
		try {
			 // The main task waits for four threads 
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// would run after latch value of counted down to zero
		System.out.println("inside main");
		
	}

}
