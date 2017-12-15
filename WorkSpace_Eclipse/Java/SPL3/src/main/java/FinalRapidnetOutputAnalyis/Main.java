package FinalRapidnetOutputAnalyis;


import FinalRapidnetOutputAnalyis.Parser.ApplicationLogParser;
import FinalRapidnetOutputAnalyis.Tuples.Attribute.TupleAttribute;
import FinalRapidnetOutputAnalyis.Tuples.Tuple;
import Queries.Query;
import Queries.TupleQuery;

import java.util.ArrayList;

public class Main {
    private static long start;
    public static void main(String[] args) throws CloneNotSupportedException {
        ArrayList <LogFormat> formattedlogs = new ArrayList<LogFormat>();

       ApplicationLogParser parse= new ApplicationLogParser();
        parse.setLogFilePath("/home/ali/output/Queries/case6/Case6log.txt");
        //parse.setLogFilePath("/home/ali/output/Queries/case7/10verticeBGPblackholelog.txt");

        //file for off path change

        //Q1,Q4,Q5//parse.setLogFilePath("/home/ali/output/Queries/case6/Case6log.txt");

       //Q2,Q6// parse.setLogFilePath("/home/ali/output/Queries/case7/10verticeBGPblackholelog.txt");
        //Q3//parse.setLogFilePath("/home/ali/output/Queries/case8/Case8Log.txt");


        parse.setLogFilebuffer();
        ArrayList<String> stringlogs=parse.parseRapidnetLog();
        formattedlogs= parse.getAllFormattedLog(stringlogs);

        TupleQuery query1= new TupleQuery();
        query1.setLogs(formattedlogs);

        query1.startTimer();
     /*   Tuple tuple= (Tuple) formattedlogs.get(0).t.clone();
        tuple.attributes.get(1).tupleAttributeValue="10.1.1.11";
*/
        query1.startTimer();
        query1.searchTuple(formattedlogs.get(418).t,"nexist","10.1.1.2","6000000000","10000000000");
        query1.getSuperVertices();

        System.out.println(query1.getPassedTime());

        //Q2//query1.searchTuple(formattedlogs.get(708).t,"nexist","10.1.2.1","6000000000","10000000000");
        //Q1//query1.searchTuple(formattedlogs.get(305).t,"nexist","10.1.1.1","6000000000","10000000000");
        // Q3//query1.searchTuple(formattedlogs.get(26).t,"nexist","10.1.1.5","6000000000","10000000000");
        //Q4//
        /*  Tuple tuple= (Tuple) formattedlogs.get(0).t.clone();
        tuple.attributes.get(1).tupleAttributeValue="10.1.1.11";

        query1.startTimer();
        query1.searchTuple(tuple,"nexist","10.1.1.1","0000000000","10000000000");
        */
        //Q5//  query1.searchTuple(formattedlogs.get(305).t,"exist","10.1.1.1","0000000000","10000000000");
        //q6//  query1.searchTuple(formattedlogs.get(1033).t,"exist","10.1.1.10","0000000000","10000000000");


        System.out.println(query1.getVerticesInResponse());


    }

    public static void startStopwatch() {

        start = System.currentTimeMillis();
    }


    public static double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }
}
