package FinalRapidnetOutputAnalyis.Events;

public class ReceiveEvent extends Event{
    public String sender="";

    public ReceiveEvent() {

        this.eventName="Received";
    }
}
