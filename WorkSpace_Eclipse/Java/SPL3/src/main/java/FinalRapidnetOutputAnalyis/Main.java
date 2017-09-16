package FinalRapidnetOutputAnalyis;


import FinalRapidnetOutputAnalyis.Rules.Rule;

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
       ApplicationLogParser parse= new ApplicationLogParser();

        parse.getLogFormat("[2000101435ns] 10.1.1.2: Sending pathDelete[pathDelete_attr1(ipv4:10.1.1.3) pathDelete_attr2(ipv4:10.1.1.1) pathDelete_attr3(int32:11) pathDelete_attr4(list:(ipv4:10.1.1.3,ipv4:10.1.1.2,ipv4:10.1.1.1)) ] to 10.1.1.3");

     //startStopwatch();
       /*parse.setLogFilePath("/home/ali/Workspace/Java/SPL3/src/RapidnetOutputAnalysis/outputPath.txt");
        parse.setLogFilebuffer();
        parse.parseRapidnetLog();
        HashMap<String,ArrayList<String>> a= parse.sourcewiseLog(parse.parseRapidnetLog());

        for(String as: a.keySet()){
            System.out.println("Source " +as);
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            for(String k: a.get(as))
            System.out.println(k);
        }*/

       // parse.setRuleBuffer();
       // System.out.println(elapsedTime());

    }

    public static void startStopwatch() {

        start = System.currentTimeMillis();
    }


    public static double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }
}
