package FinalRapidnetOutputAnalyis.Events;

import FinalRapidnetOutputAnalyis.Tuples.Tuple;

public class SendEvent extends  Event{

    public String destination="";

    public SendEvent() {
        this.eventName= "Send";
    }

    public SendEvent(String time, String node,String destination, Tuple t) {
        super();
        this.eventName = "Send";
        this.node = node;
        this.time = time;
        this.tuple = t;
        this.destination=destination;
    }
}
