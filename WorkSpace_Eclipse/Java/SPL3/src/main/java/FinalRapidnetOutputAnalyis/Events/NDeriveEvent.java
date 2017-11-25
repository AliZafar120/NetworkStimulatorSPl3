package FinalRapidnetOutputAnalyis.Events;

import FinalRapidnetOutputAnalyis.Tuples.Tuple;

public class NDeriveEvent extends Event{
    public String stime,ftime ;
    public NDeriveEvent() {
        this.eventName="NDerive";
    }

    public NDeriveEvent(String stime, String ftime, String node, Tuple t) {
        super();
        this.eventName = "NDerive   ";
        this.node = node;
        this.stime = stime;
        this.ftime=ftime;
        this.tuple = t;

    }
    public String toString() {
        return this.eventName+" : ("+this.node+", ["+this.stime+","+this.ftime+"], "
                +")";
    }

}
