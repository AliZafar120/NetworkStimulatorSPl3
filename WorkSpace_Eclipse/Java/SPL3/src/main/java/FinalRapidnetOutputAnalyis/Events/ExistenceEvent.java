package FinalRapidnetOutputAnalyis.Events;

import FinalRapidnetOutputAnalyis.Tuples.Tuple;

public class ExistenceEvent extends Event{

    public String startTime;
    public String endTime;

    public ExistenceEvent(String startTime, String endTime, String node,Tuple tuple) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventName="Existence";
        this.tuple=tuple;
        this.node=node;
    }
    public ExistenceEvent(ExistEvent existevent) {
        this.startTime = existevent.startTime;
        this.endTime = existevent.endTime;
        this.eventName="Absence";
        this.tuple=existevent.tuple;
        this.node=existevent.node;
    }

    public String toString() {
        return this.eventName+" : ("+this.node+", ["+this.startTime+","+this.endTime+"], "
                +this.tuple.toString()+this.tuple.toString()+")";
    }
}
