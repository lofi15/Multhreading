package syncronization;

// in this example we see why we need mutexes, in case multiple threads
// are accessing same values then if operation performed are not atomic then
// it might lead to inconsistent values results.

/*
 * in given example the value of count should be 20,000 after running but
 * if count++ is used inside loops the values remains less,
 * count++ =>  count=count+1;
 * 
 */

/*
 * Locks In Synchronized Methods
When a thread invokes a synchronized method, 
it automatically acquires the intrinsic lock for that method's object and
releases it when the method returns.
The lock release occurs even if the return was caused by an uncaught exception.

You might wonder what happens when a static synchronized method is invoked, since a static method
is associated with a class,not an object. In this case, the thread acquires the intrinsic lock for the Class object
associated with the class. Thus access to class's static fields is controlled by
a lock that's distinct from the lock for any instance of the class.
 */

/*
 * ISSUES
 *
 * 1. synchronize keyword acquires lock of object (all fields) hence slowing down the 
 * process if multiple threads need to acquire lock on different fields.(see next example for solution).
 * 
 */

public class BasicMutex {

	private int count = 0;
	
	public synchronized void increment() {
		count++;
	}
	
	public static void main(String [] args) {
		BasicMutex mut = new BasicMutex();
		mut.doWork();
		System.out.println("inside main");
	}
	
	public void doWork() {//	//		try {
//		t1.join();
//		t2.join();
//	} catch (InterruptedException e) {
//		e.printStackTrace();
//	}	try {
//		t1.join();
//		t2.join();
//	} catch (InterruptedException e) {
//		e.printStackTrace();
//	}

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i =0  ; i < 10000 ; i++) {
					//count++;
					increment();
				}

				System.out.println("inside thread : "+ count);
				
			}
			
		
		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i =0  ; i < 10000 ; i++) {
					increment();
					//count++;
				}
				System.out.println("inside thread : "+count);
				
			}
			
		
		});
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(count);
		
	}
	
}
