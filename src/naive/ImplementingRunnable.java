package naive;

class myThread implements Runnable{

	@Override
	public void run() {
		
		for(int i = 0 ; i < 10 ; i++) {
			
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}


public class ImplementingRunnable {
	
	public static void main(String [] args) {
		
		Thread thread = new Thread(new myThread());
		thread.start();
		Thread thread2 = new Thread(new myThread());
		thread2.start();
		
	
		
		Thread thread3 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println(1);
				
			}
			
		});
		thread3.start();
		
		
		
	}
}
