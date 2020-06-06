package threadsafeclassexample;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadSafeQueue {

	static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
	
	public static void main(String [] args) {
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
		t2.start();
		System.out.println("inside main.......................................");
	}
	
	public static void producer() throws InterruptedException {
		while(true) {
			queue.put(0);
		}
	}
	public static void consumer() throws InterruptedException {
		Random random = new Random();
		while(true) {
			Thread.sleep(100);
			if(random.nextInt(10) == 0) {
				System.out.println("queue.size() :"+ queue.size());
				int val = queue.take();
				System.out.println(" value : "+val);
			}
			
		}
	}
	
}
