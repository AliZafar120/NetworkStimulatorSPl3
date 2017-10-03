package FinalRapidnetOutputAnalyis.Events;

import FinalRapidnetOutputAnalyis.Tuples.Tuple;

public class NInsertEvent extends Event{
    String stime;
    String ftime;
    public NInsertEvent() {

        this.eventName="NInserted";
    }

    public NInsertEvent(String stime,String ftime, String node, Tuple t) {
        super();
        this.eventName = "NInserted";
        this.node = node;
        this.stime=stime;
        this.ftime=ftime;

    }

}
