package FinalRapidnetOutputAnalyis.Events;

import FinalRapidnetOutputAnalyis.Tuples.Tuple;

public class NAppearEvent extends Event{
    public String stime;
    public String ftime;
    public NAppearEvent() {
        this.eventName="NAppear";

    }
    public NAppearEvent(String stime, String ftime,String node, Tuple t, String rule, int derivationCounter) {
        super();
        this.eventName = "NAppear";
        this.node = node;
        this.stime = stime;
        this.ftime=ftime;
        this.tuple = t;
        this.rule=rule;
        this.derivationCounter=derivationCounter;
    }

    public NAppearEvent(String stime, String ftime, String node, Tuple t) {
        super();
        this.eventName = "NAppear";
        this.node = node;
        this.stime = stime;
        this.ftime=ftime;
        this.tuple = t;
        //this.derivationCounter=derivationCounter;
    }
}
