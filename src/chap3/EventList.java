package chap3;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class EventList {

	private int maxEventSize = 5;
	private LinkedList<String> event;

	public EventList() {
		event = new LinkedList<String>();
	}

	public synchronized void set(String name) {
		if(name == null){
			throw new RuntimeException("Exception: name argument is null.");
		}
		if(event == null){
			throw new NoSuchElementException("Exception: Event is null.");
		}

		while(event.size() == maxEventSize){
			try{
				wait();
			} catch(InterruptedException ex){
				ex.printStackTrace();
			}
		}

		event.offer(name);

		System.out.printf("%s：Set: %d\n", Thread.currentThread().getName(),event.size());
		notifyAll();
	}

	public synchronized String get() {
		if(event == null){
			throw new NoSuchElementException("Exception: Event is null.");
		}

		while(event.size() == 0){
			try{
				wait();
			} catch(InterruptedException ex){
				ex.printStackTrace();
			}
		}

		String eventName = event.poll();
		System.out.printf("%s：Get: %s\n", Thread.currentThread().getName(),eventName);

		notifyAll();
		return eventName;
	}

}
