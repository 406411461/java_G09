
public class AAA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runner1 run1 = new Runner1();
		run1.start();
		Runner1 run2 = new Runner1();
		run2.start();
	}

}

class Runner1 extends Thread{
	
	public void run(){
		for(int i=1;i<=10;i++) {
			System.out.println("Hello"+i);
			try {
				Thread.sleep(10);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}