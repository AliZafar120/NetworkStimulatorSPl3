package RapidnetOutputAnalysis;

public class Event {
	String node;
	String eventname;
	String time;
	String source;
	String destination;
	Tuple tuple= new Tuple();
	public Event(String eventname, String time, String nodeIP) {
		super();
		this.eventname = eventname;
		this.time = time;
		this.node = nodeIP;
		this.source="";
		
	}
	
}
