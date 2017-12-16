package FinalRapidnetOutputAnalyis.Events;
import FinalRapidnetOutputAnalyis.Tuples.Tuple;

import java.util.ArrayList;

public class Event implements Cloneable{
	public String node;
	public String eventName;
		public String time;
	public Tuple tuple;
		int derived;
	public	int derivationCounter;
	public	String rule;
	public	ArrayList<Event> childs;
	public	Event parent;
	public Event() {
		node="";
		eventName="";
		time="";
		tuple= new Tuple();
		childs= new ArrayList<Event>();
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
	public Event(String eventName,String time, String node, Tuple t,String rule, int derivationCounter) {
		super();
		this.eventName = eventName;
		this.node = node;
		this.time = time;
		this.tuple = t;
		this.rule=rule;
		this.derivationCounter=derivationCounter;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
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
	public Tuple getTupkle() {
		return tuple;
	}
	public void setTuple(Tuple tuple) {
		this.tuple = tuple;
	}
	
	

}
