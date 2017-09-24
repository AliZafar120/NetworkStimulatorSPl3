package FinalRapidnetOutputAnalyis.Events;

import FinalRapidnetOutputAnalyis.Tuples.Tuple;

public class InsertEvent extends Event{
	public InsertEvent() {

		this.eventName="Inserted";
	}

	public InsertEvent(String time, String node, Tuple t) {
		super();
		this.eventName = "Inserted";
		this.node = node;
		this.time = time;
		this.tuple = t;

	}

}
