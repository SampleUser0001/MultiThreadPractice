package chap3;

public class EventGet implements Runnable {

	private EventList eventList;

	public EventGet(EventList eventList) {
		this.eventList = eventList;
	}

	@Override
	public void run() {
		for(Integer i=0;i<10;i++){
			eventList.get();
		}
	}

}
