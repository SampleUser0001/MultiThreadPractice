package chap01;

import java.util.concurrent.atomic.AtomicInteger;

public class HelloWorld {

	public static void main(String[] args){

		// ThreadHello
		ThreadedHello hello = new ThreadedHello(5);
		threadStart(hello);

		// AtomicThreadHello
		AtomicThreadHello atHello = new AtomicThreadHello(5);
		threadStart(atHello);
	}

	private static void threadStart(Runnable r){
		System.out.println("----- " + r.getClass() + " -----");
		Thread[] thread = new Thread[3];
		for(int i=0;i<3;i++){
			thread[i] = new Thread(r);
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
		}finally{
			System.out.println("----- finish -----");
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

			synchronized (this) {
				counter++;
			}

			System.out.printf("%s:%d\n",Thread.currentThread().getName(),counter);
		}
	}
}

class AtomicThreadHello implements Runnable{
	private AtomicInteger counter;

	public AtomicThreadHello(int counter){
		this.counter = new AtomicInteger(counter);
	}

	@Override
	public void run() {
		for(int i=0;i<3;i++){
			try{
				Thread.sleep(50);
			}catch(InterruptedException ex){
				ex.printStackTrace();
			}

			synchronized (this) {
				counter.getAndIncrement();
			}

			System.out.printf("%s:%d\n",Thread.currentThread().getName(),counter.intValue());
		}
	}
}
