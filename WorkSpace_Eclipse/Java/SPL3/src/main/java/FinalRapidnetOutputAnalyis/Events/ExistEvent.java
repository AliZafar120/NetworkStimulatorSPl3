package FinalRapidnetOutputAnalyis.Events;

import FinalRapidnetOutputAnalyis.Tuples.Tuple;

public class ExistEvent extends Event{
    public String startTime;
    public String endTime;

    public ExistEvent(String startTime, String endTime, String node,Tuple tuple) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventName="Exist";
        this.tuple=tuple;
        this.node=node;
    }

    public String toString() {
        return this.eventName+" : ("+this.node+", ["+this.startTime+","+this.endTime+"], "
                +")";
    }
}
