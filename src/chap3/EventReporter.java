package chap3;

public class EventReporter {

	public static void main(String[] args) {
		final EventList eventList = new EventList();
		Thread writeThread = new Thread(new Runnable(){
			@Override
			public void run() {
				for(Integer i=0;i<20;i++){
					eventList.set(i.toString());
				}
			}
		});

		Thread readThread = new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i=0;i<20;i++){
					eventList.get();
				}
			}
		});

		writeThread.start();
		readThread.start();

		try{
			writeThread.join();
			readThread.join();
		} catch(InterruptedException ex){
			ex.printStackTrace();
		}
	}

}
