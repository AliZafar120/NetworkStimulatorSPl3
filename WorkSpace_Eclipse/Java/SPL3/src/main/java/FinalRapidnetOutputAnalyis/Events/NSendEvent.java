package FinalRapidnetOutputAnalyis.Events;

import FinalRapidnetOutputAnalyis.Tuples.Tuple;

public class NSendEvent extends Event{
    public String stime;
    public String ftime;
    public int isexchangederived;
    public NSendEvent(String stime, String ftime,String node,Tuple tuple) {
        super();
        this.eventName = "NSend";
        this.node = node;
        this.stime=stime;
        this.ftime=ftime;
        this.tuple = tuple;

    }
    public NSendEvent(String stime, String ftime,String node,Tuple tuple,int isexchangederived) {
        super();
        this.eventName = "NSend";
        this.node = node;
        this.stime=stime;
        this.ftime=ftime;
        this.tuple = tuple;
        this.isexchangederived=isexchangederived;

    }

    public String toString() {
        return this.eventName+" : ("+this.node+", ["+this.stime+","+this.ftime+"], "
                +")";
    }

}
