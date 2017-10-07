package FinalRapidnetOutputAnalyis.Events;

import FinalRapidnetOutputAnalyis.Tuples.Tuple;

public class NReceiveEvent extends Event{
    String stime;
    String ftime;

    public NReceiveEvent(String stime,String ftime, String node, Tuple t) {
        super();
        this.eventName = "NReceive";
        this.node = node;
        this.stime = stime;
        this.ftime = ftime;
        this.tuple = t;
    }
}
