package FinalRapidnetOutputAnalyis;

import FinalRapidnetOutputAnalyis.Tuples.Tuple;

public class LogFormat {
    Tuple t;
    int derived;
    String time;
    String rule;
    String node;
    int derivationCounter;

    public LogFormat(Tuple t, int derived,  String node,String time,String rule, int derivationCounter) {
        this.t = t;
        this.derived = derived;//+-t
        this.rule = rule;//r
        this.node = node;//n
        this.derivationCounter = derivationCounter;//c
        this.time=time;
    }
}
