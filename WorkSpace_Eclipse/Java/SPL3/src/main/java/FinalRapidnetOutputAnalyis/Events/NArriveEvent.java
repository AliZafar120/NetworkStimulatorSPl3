package FinalRapidnetOutputAnalyis.Events;

import FinalRapidnetOutputAnalyis.Tuples.Tuple;

public class NArriveEvent extends Event{
    String stime;
    String ftime;
    String tuplePresenceTime;
    String source;
    String destination;

    public NArriveEvent(String stime,String ftime, String source,String destination,String tupleprecenceTime,Tuple tuple) {
        super();
        this.eventName = "NArrive";
        this.node = node;
        this.stime=stime;
        this.ftime=ftime;
        this.source=source;
        this.destination=destination;
        this.tuplePresenceTime=tupleprecenceTime;
        this.tuple = tuple;

    }


}
