package FinalRapidnetOutputAnalyis;


import FinalRapidnetOutputAnalyis.Rules.Rule;
import FinalRapidnetOutputAnalyis.Tuples.Attribute.TupleAttribute;
import FinalRapidnetOutputAnalyis.Tuples.Tuple;
import Queries.Query;
import com.sun.org.apache.xpath.internal.SourceTree;


import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    private static long start;
    public static void main(String[] args) {
       /* RuleParser ruleParser= new RuleParser("/home/ali/Education/rapidnet_v1.0/src/applications/pathvector/pathvector.olg");
        ruleParser.setRuleBuffer();
        ruleParser.parseRules();*/
/*
        Rule r1= new Rule(ruleParser.parsedRules.get(0));
        System.out.println(r1.derivedTupleVars);
        System.out.println(r1.derivedTuple);
        System.out.println(r1.terms);*/
       // if("a * b".matches("[+\\-*/]")) System.out.println("okj");


        //*********************currently working on this

        ArrayList <LogFormat> formattedlogs = new ArrayList<LogFormat>();

       ApplicationLogParser parse= new ApplicationLogParser();


       parse.setLogFilePath("/home/ali/Education/rapidnet_v1.0/WorkSpace_Eclipse/Java/SPL3/src/main/java/TextLogFiles/Log2.txt");
        parse.setLogFilebuffer();
        ArrayList<String> stringlogs=parse.parseRapidnetLog();
        formattedlogs= parse.getAllFormattedLog(stringlogs);
        System.out.println(formattedlogs.size());
        System.out.println(formattedlogs.get(0).t.equals(formattedlogs.get(0).t));
        System.out.println(formattedlogs.get(0).t.attributesEquals(formattedlogs.get(0).t));

        Tuple qtuple=formattedlogs.get(24).t;//31

        Query query= new Query();
        query.setLogs(formattedlogs);


        query.existQuery("0","10000000000","10.1.1.1",qtuple);
        System.out.println(query.queryOutputEvents);
        //System.out.println("102".compareTo("101")>0);
        //*************************************Currently working on this


       // parse.setRuleBuffer();
       // System.out.println(elapsedTime());

        //**********using main for test


        //**********using main for test
    }

    public static void startStopwatch() {

        start = System.currentTimeMillis();
    }


    public static double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }
}
