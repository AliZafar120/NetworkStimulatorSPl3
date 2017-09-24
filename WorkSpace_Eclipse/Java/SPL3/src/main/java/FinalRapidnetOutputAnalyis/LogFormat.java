package FinalRapidnetOutputAnalyis;

import FinalRapidnetOutputAnalyis.Tuples.Tuple;

public class LogFormat {
    public Tuple t;
    public int derived;
    public String time;
    public String rule;
    public String node;
    public int derivationCounter;

    public LogFormat(Tuple t, int derived,  String node,String time,String rule, int derivationCounter) {
        this.t = t;
        this.derived = derived;//+-t
        this.rule = rule;//r
        this.node = node;//n
        this.derivationCounter = derivationCounter;//c
        this.time=time;
    }
}
