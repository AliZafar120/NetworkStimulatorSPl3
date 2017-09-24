package FinalRapidnetOutputAnalyis.Events;

import FinalRapidnetOutputAnalyis.Tuples.Tuple;

public class DelayEvent extends Event{

    String delayTime="";
    String sourceNode="";
    String destinationNode="";
    String timeSend="";
    public DelayEvent() {
        this.eventName="Delay";
    }

    public DelayEvent(String timeSend, String nodeSource, String nodeDestination,Tuple t, long delayTime) {
        super();
        this.eventName = "Delay";
        this.timeSend=timeSend;
        this.sourceNode=nodeSource;
        this.destinationNode=nodeDestination;
        this.delayTime=""+delayTime;
    }

}
