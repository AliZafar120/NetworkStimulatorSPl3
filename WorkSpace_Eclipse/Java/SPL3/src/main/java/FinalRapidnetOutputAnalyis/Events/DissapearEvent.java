package FinalRapidnetOutputAnalyis.Events;

import FinalRapidnetOutputAnalyis.Tuples.Tuple;

public class DissapearEvent extends Event{

    public DissapearEvent() {
        this.eventName="Dissapear";
    }

    public DissapearEvent(String time, String node, Tuple t, String rule, int derivationCounter) {
        super();
        this.eventName = "Dissapear";
        this.node = node;
        this.time = time;
        this.tuple = t;
        this.rule=rule;
        this.derivationCounter=derivationCounter;
    }

    public String toString() {
        return this.eventName+" : ("+this.node+", ["+this.time+"], "
                +")";
    }
}
