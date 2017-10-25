package FinalRapidnetOutputAnalyis.Events;

import FinalRapidnetOutputAnalyis.Tuples.Tuple;

public class NReceiveEvent extends Event{
    public String stime;
    public String ftime;
    int isderive=-100;

    public NReceiveEvent(String stime,String ftime, String node, Tuple t) {
        super();
        this.eventName = "NReceive";
        this.node = node;
        this.stime = stime;
        this.ftime = ftime;
        this.tuple = t;
    }

    public NReceiveEvent(String stime,String ftime, String node, Tuple t,int isederive) {
        super();
        this.eventName = "NReceive";
        this.node = node;
        this.stime = stime;
        this.ftime = ftime;
        this.tuple = t;
        this.isderive=isederive;
    }
}
