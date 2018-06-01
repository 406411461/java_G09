
public class BBB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread run1 = new Thread(new Runner2());
		run1.start();
		Thread run2 = new Thread(new Runner2());
		run2.start();
	}

}

class Runner2 implements Runnable{
	public void run() {
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
