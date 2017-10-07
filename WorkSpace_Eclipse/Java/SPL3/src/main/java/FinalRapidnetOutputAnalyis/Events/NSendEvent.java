package FinalRapidnetOutputAnalyis.Events;

import FinalRapidnetOutputAnalyis.Tuples.Tuple;

public class NSendEvent extends Event{
    String stime;
    String ftime;

    public NSendEvent(String stime, String ftime,String node,Tuple tuple) {
        super();
        this.eventName = "NSend";
        this.node = node;
        this.stime=stime;
        this.ftime=ftime;
        this.tuple = tuple;

    }


}
