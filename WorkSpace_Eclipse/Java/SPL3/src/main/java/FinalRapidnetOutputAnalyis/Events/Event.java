package FinalRapidnetOutputAnalyis.Events;
import FinalRapidnetOutputAnalyis.Tuples.Tuple;

public class Event {
		String node;
		String eventName;
		String time;
		Tuple tuple;
		int derived;
		int derivationCounter;
		String rule;
	public Event() {
		node="";
		eventName="";
		time="";
		tuple= new Tuple();
	}
	public Event(String eventName) {
		super();
		this.eventName = eventName;
	}
	public Event(String node, String eventName) {
		super();
		this.node = node;
		this.eventName = eventName;
	}
	public Event(String node, String eventName, String time) {
		super();
		this.node = node;
		this.eventName = eventName;
		this.time = time;
	}
	public Event(String node, String eventName, String time,
			Tuple tuple) {
		super();
		this.node = node;
		this.eventName = eventName;
		this.time = time;
		this.tuple = tuple;
	}
	public String getOccuredAtNode() {
		return node;
	}
	public void setOccuredAtNode(String occuredAtNode) {
		this.node = occuredAtNode;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Tuple getTuple() {
		return tuple;
	}
	public void setTuple(Tuple tuple) {
		this.tuple = tuple;
	}
	
	

}
