package naive;

class Mythread extends Thread{
	
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

public class ExtendingThread {
	
	public static void main(String [] args) {
		
		Mythread t1 = new Mythread();
		Mythread t2 = new Mythread();
		t1.start();
		
		t2.start();
	}

}
