package syncronization;

import java.util.ArrayList;
import java.util.List;

public class MultipleLocks {

	
	private List<Integer> list1;
	private List<Integer> list2;
	public MultipleLocks() {
		list1 = new ArrayList<Integer>();
		list2 = new ArrayList<Integer>();
	}
	
	
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	
	public static void main(String [] args) {
		
		MultipleLocks m = new MultipleLocks();
		m.execute();
		
	}

	private void process() {
		for(int i =0 ; i < 1000  ;i++) {
			subprocessOne();
			subprocessTwo();
		}
	}
	
	private void subprocessOne() {
		
		synchronized (lock1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			list1.add(1);
		}
		
		
	}
	
	private void subprocessTwo() {
		
		synchronized (lock2) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e){
				e.printStackTrace();
			}
			list2.add(2);
		}
		
	}

	private void execute() {
		
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run(){
				process();
			}	
		});
		
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run(){
				process();
			}	
		});
		
		
		long start= System.currentTimeMillis();
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("time taken :"+ (end-start) +" size1 : "+list1.size()+" size2 : "+list2.size());
		
		
//		Thread t2 = new Thread(new Runnable(){		
//			@Override
//			public void run(){
//				process();
//			}			
//		});
		
	}
	
	
	
	
}
