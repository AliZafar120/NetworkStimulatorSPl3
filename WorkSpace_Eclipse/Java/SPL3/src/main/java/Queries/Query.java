package Queries;

import FinalRapidnetOutputAnalyis.Events.*;
import FinalRapidnetOutputAnalyis.LogFormat;
import FinalRapidnetOutputAnalyis.Tuples.Tuple;
import sun.rmi.runtime.Log;

import java.security.PublicKey;
import java.util.ArrayList;

public class Query {
    ArrayList<Event>queryOutputEvents;
    ArrayList <LogFormat> logs;
    public Query(){
        queryOutputEvents= new ArrayList<Event>();
        logs= new ArrayList<LogFormat>();
    }

    public void getLogs(ArrayList<LogFormat> logs){
        this.logs=logs;
    }

    public void insertQuery(String time,Tuple t, String Node){
        //it's the base level
        queryOutputEvents.add(new InsertEvent(time,Node,t));
        //return  null;
    }

    public void appearQuery(String time,Tuple tuple, String Node, String rule, int derivationCounter){
        //it's the base level
        queryOutputEvents.add(new AppearEvent(time,Node,tuple,rule,derivationCounter));
        //if baseTuple Search from rule parse
        if(baseTuple(rule)){//like insert link insert
            insertQuery(time,tuple,Node);
        }

        //if localTuple (N,T) from rule parse
        else if(localTuple(Node,tuple)){//like local tuple? how no receive? or match log node with tuple node 1st parameter
             deriveQuery(time,Node,tuple,rule);
            // return  insertQuery(time,t,Node);
        }

        else{
            receiveQuery(time,Node,tuple);
        };
    }

    public void deriveQuery(String time,String Node,Tuple tuple, String rule){
        queryOutputEvents.add(new DeriveEvent(time,Node,tuple,rule));

        //need to modify this to return tuples

        if(rule.compareTo("r1")==0){
            for (LogFormat log:logs){
               if(log.t.type.compareTo("link")==0 &&
                       log.time.compareTo(time)==0 &&
                       log.node.compareTo(Node)==0 &&
                       log.t.attributes.get(0).tupleAttributeValue.compareTo(tuple.attributes.get(0).tupleAttributeValue)==0 &&
                       log.t.attributes.get(1).tupleAttributeValue.compareTo(tuple.attributes.get(1).tupleAttributeValue)==0

                 ){
                    insertQuery(time,log.t,Node);
               }

            }

        }
        else if(rule.compareTo("r2")==0){

            for(LogFormat bestPathlogs: logs){
                if(bestPathlogs.t.type.compareTo("bestPath")==0 &&
                        bestPathlogs.t.attributes.get(1).tupleAttributeValue.compareTo(tuple.attributes.get(0).tupleAttributeValue)==0
                        && bestPathlogs.time.compareTo(time)==0
                        && bestPathlogs.node.compareTo(Node)==0
                        ){

                    for(LogFormat linklogs: logs){
                        if(linklogs.t.type.compareTo("link")==0 &&
                        linklogs.node.compareTo(Node)==0
                                &&
                                linklogs.t.attributes.get(0).tupleAttributeValue.compareTo(tuple.attributes.get(0).tupleAttributeValue)==0
                                && linklogs.t.attributes.get(2).tupleAttributeValue+bestPathlogs.t.attributes.get(2).tupleAttributeValue==tuple.attributes.get(2).tupleAttributeValue
                                ){

                            appearQuery(time,bestPathlogs.t,Node,"r3",bestPathlogs.derivationCounter);
                            existQuery(0,Long.parseLong(time),Node,linklogs.t);
                        }
                    }

                }



            }

        }
        else if(rule.compareTo("r3")==0){
            for(LogFormat log: logs){
                if(log.t.type.compareTo("path")==0
                        && log.t.attributes.get(0).tupleAttributeValue.compareTo(tuple.attributes.get(0).tupleAttributeValue)==0
                        && log.t.attributes.get(1).tupleAttributeValue.compareTo(tuple.attributes.get(1).tupleAttributeValue)==0

                        && log.t.attributes.get(2).tupleAttributeValue.compareTo(tuple.attributes.get(2).tupleAttributeValue)==0
                        && log.t.attributes.get(3).tupleAttributelistValue.size()==tuple.attributes.get(3).tupleAttributelistValue.size()
                        ){

                    appearQuery(time,log.t,Node,"r3",log.derivationCounter);

                }

            }
        }



    }

    public void sendQuery(String node,String destination,Tuple tuple, String destinationArrivalTime){
        //findlog
        //if()

        for(LogFormat log: logs){
            if(log.derived==-1 && log.t.attributesEquals(tuple) && log.node.compareTo(node)==0 && log.t.tupleDestination.compareTo(destination)==0){
                queryOutputEvents.add(new SendEvent(log.time,node,log.t.tupleDestination,tuple));
                queryOutputEvents.add(new DelayEvent(log.time,log.node,destination,log.t,Long.parseLong(destinationArrivalTime)-Long.parseLong(log.time)));
            }

        }


        for(LogFormat log: logs){
            if(log.derived==1 && log.t.attributesEquals(tuple) && log.node.compareTo(node)==0){

            appearQuery(log.time,log.t,node,log.rule,log.derivationCounter);
            }

        }

    }

    public void receiveQuery( String time, String node, Tuple tuple){
        for(LogFormat log: logs){
            if(log.derived==-1 && log.t.attributesEquals(tuple) && log.node.compareTo(node)==0){
            queryOutputEvents.add(new ReceiveEvent(log.time,node,log.t.tupleSource,tuple));
            sendQuery(log.t.tupleSource,node,tuple,log.time);

            }

        }

    }

    public void existQuery(long stime, long ftime, String Node,Tuple t)
    {
        queryOutputEvents=  new ArrayList<Event>();
        if(t==null)//means construct entire provenane graph
        {
            for(LogFormat log: logs){
                long timeOfLog=Long.parseLong(log.time);
                if(log.derived==1 && log.node.compareTo(Node)==0  && stime<timeOfLog && timeOfLog<ftime){
                    queryOutputEvents.add(new Event("Appear",log.time,Node,log.t, log.rule, log.derivationCounter));
                    appearQuery(log.time,log.t,Node,log.rule,log.derivationCounter);
                }
            }
            for(LogFormat log: logs){
                long timeOfLog=Long.parseLong(log.time);
                if(log.derived==0 && log.node.compareTo(Node)==0  && stime<timeOfLog && timeOfLog<ftime){
                    queryOutputEvents.add(new Event("Dissapear",log.time,Node,log.t, log.rule, log.derivationCounter));
                    //dissappear query

                    /**/
                     /**/
                     /**/
                     /**/


                     appearQuery(log.time,log.t,Node,log.rule,log.derivationCounter);
                }
            }
        }
        else{//querying for particular tuple
            for(LogFormat log: logs) {
                long timeOfLog=Long.parseLong(log.time);
                if(log.derived==1 && log.node.compareTo(Node)==0  && stime<timeOfLog && timeOfLog<ftime && log.t.equals(t)){
                    queryOutputEvents.add(new Event("Appear",log.time,Node,log.t, log.rule, log.derivationCounter));
                    appearQuery(log.time,log.t,Node,log.rule,log.derivationCounter);
                }

            }
            for(LogFormat log: logs) {
                long timeOfLog=Long.parseLong(log.time);
                if(log.derived==1 && log.node.compareTo(Node)==0  && stime<timeOfLog && timeOfLog<ftime && log.t.equals(t)){
                    queryOutputEvents.add(new Event("Dissapear",log.time,Node,log.t, log.rule, log.derivationCounter));
                    appearQuery(log.time,log.t,Node,log.rule,log.derivationCounter);
                }

            }
        }

    }

    public void find(){
        //search log(){}
    }


    public boolean localTuple(String Node, Tuple t){
        if(t.tupleOrigin==Node)return  true;
        else return false;
    }

    public boolean baseTuple(String rule){
        //return from rules is base?
        if(rule==null)return true;
        else return false;
    }

    public boolean isPresentInLog(Tuple t,boolean derived, String time,String rule, int derivationCounter){

        return false;
    }


    public void disappearQuery(String time,Tuple tuple, String Node, String rule, int derivationCounter){
        //it's the base level
        queryOutputEvents.add(new DissapearEvent(time,Node,tuple,rule,derivationCounter));
        //if baseTuple Search from rule parse
        if(baseTuple(rule)){//like insert link insert
            deleteQuery(time,tuple,Node);
        }

        //if localTuple (N,T) from rule parse
        else if(localTuple(Node,tuple)){//like local tuple? how no receive? or match log node with tuple node 1st parameter
          //  underiveQuery(time,Node,tuple,rule);
            // return  insertQuery(time,t,Node);
        }

        else{
            ///
            receiveQuery(time,Node,tuple);
        };
    }


    public  void deleteQuery(String time, Tuple tuple, String node){
        queryOutputEvents.add(new DeleteEvent(time,node,tuple));
    }


}
