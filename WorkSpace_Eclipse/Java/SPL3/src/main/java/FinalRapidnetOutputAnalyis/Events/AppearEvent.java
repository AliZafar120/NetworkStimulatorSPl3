package FinalRapidnetOutputAnalyis.Events;

import FinalRapidnetOutputAnalyis.Tuples.Tuple;

public class AppearEvent extends Event{
    public AppearEvent() {
        this.eventName="Appear";

    }
    public AppearEvent(String time, String node, Tuple t, String rule, int derivationCounter) {
        super();
        this.eventName = "Appear";
        this.node = node;
        this.time = time;
        this.tuple = t;
        this.rule=rule;
        this.derivationCounter=derivationCounter;
    }

    public AppearEvent(String time, String node, Tuple t,int derivationCounter) {
        super();
        this.eventName = "Appear";
        this.node = node;
        this.time = time;
        this.tuple = t;
        this.derivationCounter=derivationCounter;
    }

    public String toString() {
        return this.eventName+" : ("+this.node+", ["+this.time+"], "
                +this.tuple.toString()+")";
    }


}
