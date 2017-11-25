package FinalRapidnetOutputAnalyis.Events;

import FinalRapidnetOutputAnalyis.Tuples.Tuple;

public class NInsertEvent extends Event{
    public String stime;
    public String ftime;
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

    public String toString() {
        return this.eventName+" : ("+this.node+", ["+this.stime+","+this.ftime+"], "
                +")";
    }

}
