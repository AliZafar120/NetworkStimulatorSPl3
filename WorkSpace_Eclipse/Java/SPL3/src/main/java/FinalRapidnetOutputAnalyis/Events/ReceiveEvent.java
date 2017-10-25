package FinalRapidnetOutputAnalyis.Events;

import FinalRapidnetOutputAnalyis.Tuples.Tuple;

public class ReceiveEvent extends Event{
    public String sender="";
    public int isderve;

    public ReceiveEvent() {

        this.eventName="Received";
    }

    public ReceiveEvent(String time, String node,String sender, Tuple t) {
        super();
        this.eventName = "Received";
        this.node = node;
        this.time = time;
        this.tuple = t;
        this.sender=sender;
    }

    public ReceiveEvent(String time, String node, Tuple t,int isderve) {
        super();
        this.eventName = "Received";
        this.node = node;
        this.time = time;
        this.tuple = t;
        this.isderve=isderve;
    }

}
