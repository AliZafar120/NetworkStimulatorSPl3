package FinalRapidnetOutputAnalyis.Events;

public class SendEvent extends  Event{

    public String receiver="";

    public SendEvent() {
        this.eventName= "Send";
    }
}
