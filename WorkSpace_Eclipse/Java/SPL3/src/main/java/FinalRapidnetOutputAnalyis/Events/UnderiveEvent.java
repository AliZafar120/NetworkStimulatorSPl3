package FinalRapidnetOutputAnalyis.Events;

import FinalRapidnetOutputAnalyis.Tuples.Tuple;

public class UnderiveEvent extends Event{



    public UnderiveEvent(String time, String node, Tuple t, String rule) {
        super();
        this.eventName = "Underive";
        this.node = node;
        this.time = time;
        this.tuple = t;
        this.rule=rule;

    }
}
