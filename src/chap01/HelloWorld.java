package chap01;

public class HelloWorld {

	public static void main(String[] args){
		ThreadedHello hello = new ThreadedHello(5);

		Thread[] thread = new Thread[3];
		for(int i=0;i<3;i++){
			thread[i] = new Thread(hello);
		}
		for(int i=0;i<3;i++){
			thread[i].start();
		}
		try{
			for(int i=0;i<3;i++){
				thread[i].join();
			}
		}catch (InterruptedException ex){
			ex.printStackTrace();
		}
	}

}

class ThreadedHello implements Runnable{
	private int counter;

	public ThreadedHello(int counter){
		this.counter = counter;
	}

	@Override
	public void run() {
		for(int i=0;i<3;i++){
			try{
				Thread.sleep(50);
			}catch(InterruptedException ex){
				ex.printStackTrace();
			}
			counter++;

			System.out.printf("%s:%d\n",Thread.currentThread().getName(),counter);
		}
	}
}
