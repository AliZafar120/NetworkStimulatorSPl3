package FinalRapidnetOutputAnalyis.Events;

import FinalRapidnetOutputAnalyis.Tuples.Tuple;

public class SendEvent extends  Event{

    public String destination="";
    public int isderive=-100;
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

    public SendEvent(String time, String node,String destination, Tuple t,int isderive) {
        super();
        this.eventName = "Send";
        this.node = node;
        this.time = time;
        this.tuple = t;
        this.destination=destination;
        this.isderive=isderive;
    }
    public String toString() {
        return this.eventName+" : ("+this.node+"->"+this.destination+", ["+this.time+"], "
                +this.tuple.toString()+")";
    }
}
