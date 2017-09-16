package FinalRapidnetOutputAnalyis.Events;
import FinalRapidnetOutputAnalyis.Tuples.Tuple;

public class Event {
		String occuredAtNode;
		String eventName;
		String time;
		Tuple tuple;
	public Event() {
		occuredAtNode="";
		eventName="";
		time="";
		tuple= new Tuple();
	}
	public Event(String eventName) {
		super();
		this.eventName = eventName;
	}
	public Event(String occuredAtNode, String eventName) {
		super();
		this.occuredAtNode = occuredAtNode;
		this.eventName = eventName;
	}
	public Event(String occuredAtNode, String eventName, String time) {
		super();
		this.occuredAtNode = occuredAtNode;
		this.eventName = eventName;
		this.time = time;
	}
	public Event(String occuredAtNode, String eventName, String time,
			Tuple tuple) {
		super();
		this.occuredAtNode = occuredAtNode;
		this.eventName = eventName;
		this.time = time;
		this.tuple = tuple;
	}
	public String getOccuredAtNode() {
		return occuredAtNode;
	}
	public void setOccuredAtNode(String occuredAtNode) {
		this.occuredAtNode = occuredAtNode;
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
