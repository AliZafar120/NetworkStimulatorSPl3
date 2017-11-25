package FinalRapidnetOutputAnalyis.Events;

import FinalRapidnetOutputAnalyis.Tuples.Tuple;

public class DeleteEvent extends Event{

    public DeleteEvent() {
        this.eventName="Delete";
    }
    public DeleteEvent(String time, String node, Tuple t) {
        super();
        this.eventName = "Delete";
        this.node = node;
        this.time = time;
        this.tuple = t;

    }
    public String toString() {
        return this.eventName+" : ("+this.node+", ["+this.time+"], "
                +")";
    }

}
