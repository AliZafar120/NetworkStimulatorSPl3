package FinalRapidnetOutputAnalyis.Events;

import FinalRapidnetOutputAnalyis.Tuples.Tuple;

public class AbsenceEvent extends Event{
    public String stime;
    public String ftime;

    public AbsenceEvent(String startTime, String endTime, String node,Tuple tuple) {
        this.stime = startTime;
        this.ftime = endTime;
        this.eventName="Absence";
        this.tuple=tuple;
        this.node=node;
    }
    public AbsenceEvent(NExistEvent nexistevent) {
        this.stime = nexistevent.stime;
        this.ftime = nexistevent.ftime;
        this.eventName="Absence";
        this.tuple=nexistevent.tuple;
        this.node=nexistevent.node;
    }
    public String toString() {
        return this.eventName+" : ("+this.node+", ["+this.stime+","+this.ftime+"], "
                +this.tuple.toString()+")";
    }
}
