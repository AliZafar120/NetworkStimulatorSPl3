package FinalRapidnetOutputAnalyis.Events;

import FinalRapidnetOutputAnalyis.Tuples.Tuple;

public class NExistEvent extends Event{
    public String stime;
    public String ftime;

    public NExistEvent(String startTime, String endTime, String node,Tuple tuple) {
        this.stime = startTime;
        this.ftime = endTime;
        this.eventName="NExist";
        this.tuple=tuple;
        this.node=node;
    }

}
