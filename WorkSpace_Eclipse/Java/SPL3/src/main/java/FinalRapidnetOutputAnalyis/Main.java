package FinalRapidnetOutputAnalyis;


import FinalRapidnetOutputAnalyis.Parser.ApplicationLogParser;
import Queries.Query;
import Queries.TupleQuery;


import java.util.ArrayList;

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

        //file for link failure
       parse.setLogFilePath("/home/ali/Education/rapidnet_v1.0/WorkSpace_Eclipse/Java/SPL3/src/main/java/TextLogFiles/Log2.txt");
        //parse.setLogFilePath("/home/ali/Education/rapidnet_v1.0/WorkSpace_Eclipse/Java/SPL3/src/main/java/TextLogFiles/offpathchange.txt");



        parse.setLogFilebuffer();
        ArrayList<String> stringlogs=parse.parseRapidnetLog();
        formattedlogs= parse.getAllFormattedLog(stringlogs);
        System.out.println(formattedlogs.size());
        System.out.println(formattedlogs.get(0).t.equals(formattedlogs.get(0).t));
        System.out.println(formattedlogs.get(0).t.attributesEquals(formattedlogs.get(0).t));

        //tuple for link failure
        //Tuple qtuple=formattedlogs.get(24).t;//31

        //Query query= new Query();
        //query.setLogs(formattedlogs);
        TupleQuery query1= new TupleQuery();
        query1.setLogs(formattedlogs);
        startStopwatch();
        query1.searchTuple(formattedlogs.get(24).t,"nexist","10.1.1.1","6000000000","10000000000");
        System.out.println(elapsedTime());
        System.out.println("ok");
        //for querying non existant best
        //query.nexistQuery("6000000000","10000000000","10.1.1.1",qtuple);


        //System.out.println(query.queryOutputEvents);
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
