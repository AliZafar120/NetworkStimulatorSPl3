package Queries;

import FinalRapidnetOutputAnalyis.Events.*;
import FinalRapidnetOutputAnalyis.LogFormat;
import FinalRapidnetOutputAnalyis.Tuples.Tuple;
import sun.rmi.runtime.Log;

import java.math.BigInteger;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Query {
    public ArrayList<Event>queryOutputEvents;
    ArrayList <LogFormat> logs;
    public Query(){
        queryOutputEvents= new ArrayList<Event>();
        logs= new ArrayList<LogFormat>();
    }

    public void setLogs(ArrayList<LogFormat> logs){
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
            receiveQuery(time,Node,tuple,1);
        };
    }

    public void deriveQuery(String time,String Node,Tuple tuple, String rule){
        queryOutputEvents.add(new DeriveEvent(time,Node,tuple,rule));

        //need to modify this to return tuples

        if(rule.compareTo("r1")==0){
            for (LogFormat log:logs){
               if(log.t.type.compareTo("link")==0 &&
                       log.time.compareTo(time)==0 &&
                       log.derived==1 &&
                       log.node.compareTo(Node)==0 &&
                       log.t.attributes.get(0).tupleAttributeValue.compareTo(tuple.attributes.get(0).tupleAttributeValue)==0 &&
                       log.t.attributes.get(1).tupleAttributeValue.compareTo(tuple.attributes.get(1).tupleAttributeValue)==0

                 ){
                   appearQuery(time,log.t,Node,log.rule,log.derivationCounter);
                   break;
               }

            }

        }
        else if(rule.compareTo("r2")==0){
            boolean isanybestpath=false;
            LogFormat bestpath= null;
            for(LogFormat bestPathlogs: logs){
                if(new BigInteger(bestPathlogs.getTime()).compareTo(new BigInteger(time.replaceAll("ns","")))>=0) break;
                if(bestPathlogs.t.type.compareTo("bestPath")==0 &&
                        bestPathlogs.derived==1 &&
                        bestPathlogs.t.attributes.get(1).tupleAttributeValue.compareTo(tuple.attributes.get(0).tupleAttributeValue)==0
                        && bestPathlogs.node.compareTo(Node)==0
                        ){
                        isanybestpath=true;
                        bestpath=bestPathlogs;
                }

            }
            if(isanybestpath){

                for(LogFormat linklogs: logs){
                    if(linklogs.t.type.compareTo("link")==0 &&
                            linklogs.node.compareTo(Node)==0
                            &&
                            linklogs.derived==1 &&
                            linklogs.t.attributes.get(0).tupleAttributeValue.compareTo(Node)==0 &&
                            linklogs.t.attributes.get(1).tupleAttributeValue.compareTo(tuple.attributes.get(1).tupleAttributeValue)==0
                            && Integer.parseInt(linklogs.t.attributes.get(2).tupleAttributeValue)+Integer.parseInt(bestpath.t.attributes.get(2).tupleAttributeValue)==Integer.parseInt(tuple.attributes.get(2).tupleAttributeValue)
                            ){
                        appearQuery(time,linklogs.t,Node,linklogs.rule,linklogs.derivationCounter);
                        appearQuery(time,bestpath.t,Node,bestpath.rule,bestpath.derivationCounter);
                        //existQuery(0,Long.parseLong(time),Node,linklogs.t);
                        break;
                    }
                }
            }else{

               // receiveQuery(time,Node,tuple,1);//ommitted as another log was inserted for r2 rule

                /*for(LogFormat receivedPathLog: logs){
                    if(receivedPathLog.t.type.compareTo("path")==0 &&
                            receivedPathLog.node.compareTo(Node)==0
                            &&
                            receivedPathLog.derived==-1
                            &&receivedPathLog.t.attributes.get(0).tupleAttributeValue.compareTo(tuple.attributes.get(0).tupleAttributeValue)==0
                            &&receivedPathLog.t.attributes.get(2).tupleAttributeValue+bestpath.t.attributes.get(1).tupleAttributeValue==tuple.attributes.get(1).tupleAttributeValue

                            && receivedPathLog.t.attributes.get(2).tupleAttributeValue+bestpath.t.attributes.get(2).tupleAttributeValue==tuple.attributes.get(2).tupleAttributeValue
                            ){


                        //receiveQuery(time,Node,Node,receivedPathLog.rule,bestpath.derivationCounter);

                        //existQuery(0,Long.parseLong(time),Node,linklogs.t);
                    }
                }*/


            }


        }
        else if(rule.compareTo("r3")==0){
            for(LogFormat log: logs){
                if(log.t.type.compareTo("path")==0
                        &&log.derived==1
                        &&log.node.compareTo(Node)==0
                        && log.t.attributes.get(0).tupleAttributeValue.compareTo(tuple.attributes.get(0).tupleAttributeValue)==0
                        && log.t.attributes.get(1).tupleAttributeValue.compareTo(tuple.attributes.get(1).tupleAttributeValue)==0

                        && log.t.attributes.get(2).tupleAttributeValue.compareTo(tuple.attributes.get(2).tupleAttributeValue)==0
                        && log.t.attributes.get(3).tupleAttributelistValue.size()==tuple.attributes.get(3).tupleAttributelistValue.size()
                        ){

                    appearQuery(log.time,log.t,Node,log.rule,log.derivationCounter);
                    break;
                }

            }
        }



    }

    public void sendQuery(String node,String destination,Tuple tuple, String destinationArrivalTime,int isderive){
        //findlog
        //if()

        for(LogFormat log: logs){
            if(log.derived==-1 && log.t.attributesEquals(tuple) && log.node.compareTo(node)==0 && log.t.tupleDestination.compareTo(destination)==0 && log.exchangeIsderived==isderive){
                queryOutputEvents.add(new DelayEvent(log.time,log.node,destination,log.t,""+new BigInteger(destinationArrivalTime).subtract(new BigInteger(log.getTime()))));
                queryOutputEvents.add(new SendEvent(log.time,node,log.t.tupleDestination,tuple));

            }

        }


        for(LogFormat log: logs){

            if (isderive==1&& log.derived == 1 && log.t.attributesEquals(tuple) && log.node.compareTo(node) == 0) {

                    appearQuery(log.time, log.t, node, log.rule, log.derivationCounter);
                    return;
            }

            if(isderive==0 && log.derived==0 && log.t.attributesEquals(tuple) && log.node.compareTo(node)==0){

                disappearQuery(log.time,log.t,node,log.rule,log.derivationCounter);
                return;
            }

        }

    }

    public void receiveQuery( String time, String node, Tuple tuple, int isderive){
        for(LogFormat log: logs){
            if(log.derived==-1 && log.t.attributesEquals(tuple) && log.node.compareTo(node)==0 && log.exchangeIsderived==isderive){
            queryOutputEvents.add(new ReceiveEvent(log.time,node,log.t.tupleSource,tuple));
            sendQuery(log.t.tupleSource,node,tuple,log.getTime(), isderive);
                break;
            }

        }

    }

    public void existQuery(String stime, String ftime, String Node,Tuple t)
    {
        queryOutputEvents=  new ArrayList<Event>();
        if(t==null)//means construct entire provenane graph
        {
            for(LogFormat log: logs){
                String timeOfLog=log.getTime();
                if(log.derived==1 && log.node.compareTo(Node)==0  && timeOfLog.compareTo(stime)>=0 && ftime.compareTo(timeOfLog)>=0){
                    queryOutputEvents.add(new Event("Appear",log.time,Node,log.t, log.rule, log.derivationCounter));
                    appearQuery(log.time,log.t,Node,log.rule,log.derivationCounter);
                    break;
                }
            }
            for(LogFormat log: logs){
                String timeOfLog=log.getTime();
                if(log.derived==0 && log.node.compareTo(Node)==0  && timeOfLog.compareTo(stime)>=0 && ftime.compareTo(timeOfLog)>=0){
                    queryOutputEvents.add(new Event("Dissapear",log.time,Node,log.t, log.rule, log.derivationCounter));
                     disappearQuery(log.time,log.t,Node,log.rule,log.derivationCounter);
                    break;
                }
            }
        }
        else{//querying for particular tuple
            int i=0;
            for(LogFormat log: logs) {
                if(i==8){
                    System.out.println();
                }
                String timeOfLog=log.getTime();
                if(log.derived==1 && log.node.compareTo(Node)==0  && new BigInteger(timeOfLog).compareTo(new BigInteger(stime))>=0 && new BigInteger(timeOfLog).compareTo(new BigInteger(ftime))<=0 && log.t.attributesEquals(t)){
                    appearQuery(log.time,log.t,Node,log.rule,log.derivationCounter);
                    break;
                }
                i++;

            }
            for(LogFormat log: logs) {
                String timeOfLog=log.getTime();
               /* if(i==29){
                    System.out.println();
                }*/
                if(log.derived==0 && log.node.compareTo(Node)==0  && new BigInteger(timeOfLog).compareTo(new BigInteger(stime))>=0 && new BigInteger(timeOfLog).compareTo(new BigInteger(ftime))<=0  && log.t.attributesEquals(t)){
                    disappearQuery(log.time,log.t,Node,log.rule,log.derivationCounter);
                    break;
                }
               /* i++;*/
            }
        }

    }

    public void find(){
        //search log(){}
    }


    public boolean localTuple(String Node, Tuple t){
        for(LogFormat log: logs){
            if(log.t.attributesEquals(t) && log.derived==-1 && log.t.tupleDestination!=null && log.t.tupleDestination.compareTo(Node)==0)
            return false;
        }
        return true;
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
            underiveQuery(time,Node,tuple,rule);
            // return  insertQuery(time,t,Node);

        }

        else{
            ///
            receiveQuery(time,Node,tuple,0);
        };
    }


    public  void deleteQuery(String time, Tuple tuple, String node){
        queryOutputEvents.add(new DeleteEvent(time,node,tuple));
    }

    public void underiveQuery(String time, String Node, Tuple tuple , String rule){

        queryOutputEvents.add(new UnderiveEvent(time,Node,tuple,rule));

        //need to modify this to return tuples
        if(rule.compareTo("r1")==0){
            for (LogFormat log:logs){
                if(log.t.type.compareTo("link")==0 &&
                        log.time.compareTo(time)==0 &&
                        log.derived==0 &&
                        log.node.compareTo(Node)==0 &&
                        log.t.attributes.get(0).tupleAttributeValue.compareTo(tuple.attributes.get(0).tupleAttributeValue)==0 &&
                        log.t.attributes.get(1).tupleAttributeValue.compareTo(tuple.attributes.get(1).tupleAttributeValue)==0

                        ){
                    disappearQuery(time,log.t,Node,log.rule,log.derivationCounter);
                    break;
                }

            }

        }
        else if(rule.compareTo("r2")==0){
            boolean isanybestpath=false;
            LogFormat bestpath= null;
            for(LogFormat linklogs: logs){
                if(linklogs.t.type.compareTo("link")==0 &&
                        linklogs.node.compareTo(Node)==0
                        &&
                        linklogs.derived==0 &&
                        linklogs.t.attributes.get(0).tupleAttributeValue.compareTo(Node)==0 &&
                        linklogs.t.attributes.get(1).tupleAttributeValue.compareTo(tuple.attributes.get(1).tupleAttributeValue)==0
                        ){
                    disappearQuery(linklogs.time,linklogs.t,Node,linklogs.rule,linklogs.derivationCounter);
                    //existQuery(0,Long.parseLong(time),Node,linklogs.t);
                    return;
                }
            }
            for(LogFormat bestPathlogs: logs){
                if(new BigInteger(bestPathlogs.getTime()).compareTo(new BigInteger(time.replaceAll("ns","")))>=0) break;
                if(bestPathlogs.t.type.compareTo("bestPath")==0 &&
                        bestPathlogs.derived==0 &&
                        bestPathlogs.t.attributes.get(1).tupleAttributeValue.compareTo(tuple.attributes.get(0).tupleAttributeValue)==0
                        && bestPathlogs.node.compareTo(Node)==0
                        ){
                    isanybestpath=true;
                    bestpath=bestPathlogs;
                }

            }
            if(isanybestpath){
                disappearQuery(time,bestpath.t,Node,bestpath.rule,bestpath.derivationCounter);


            }else{

                //receiveQuery(time,Node,tuple,0);

                /*for(LogFormat receivedPathLog: logs){
                    if(receivedPathLog.t.type.compareTo("path")==0 &&
                            receivedPathLog.node.compareTo(Node)==0
                            &&
                            receivedPathLog.derived==-1
                            &&receivedPathLog.t.attributes.get(0).tupleAttributeValue.compareTo(tuple.attributes.get(0).tupleAttributeValue)==0
                            &&receivedPathLog.t.attributes.get(2).tupleAttributeValue+bestpath.t.attributes.get(1).tupleAttributeValue==tuple.attributes.get(1).tupleAttributeValue

                            && receivedPathLog.t.attributes.get(2).tupleAttributeValue+bestpath.t.attributes.get(2).tupleAttributeValue==tuple.attributes.get(2).tupleAttributeValue
                            ){


                        //receiveQuery(time,Node,Node,receivedPathLog.rule,bestpath.derivationCounter);

                        //existQuery(0,Long.parseLong(time),Node,linklogs.t);
                    }
                }*/


            }


        }
        else if(rule.compareTo("r3")==0){
            for(LogFormat log: logs){
                if(log.t.type.compareTo("path")==0
                        &&log.derived==0
                        &&log.node.compareTo(Node)==0
                        && log.t.attributes.get(0).tupleAttributeValue.compareTo(tuple.attributes.get(0).tupleAttributeValue)==0
                        && log.t.attributes.get(1).tupleAttributeValue.compareTo(tuple.attributes.get(1).tupleAttributeValue)==0

                        && log.t.attributes.get(2).tupleAttributeValue.compareTo(tuple.attributes.get(2).tupleAttributeValue)==0
                        && log.t.attributes.get(3).tupleAttributelistValue.size()==tuple.attributes.get(3).tupleAttributelistValue.size()
                        ){

                    disappearQuery(log.time,log.t,Node,log.rule,log.derivationCounter);
                    break;
                }

            }
        }



    }



    //starting negative provenance queries

    public void nexistQuery(String stime, String ftime, String node, Tuple tuple ){
        queryOutputEvents.add(new NExistEvent(stime,ftime,node,tuple));
        for(LogFormat log:logs ){
            String timeOfLog=log.getTime();

            if(log.derived==0 && log.node.compareTo(node)==0  && new BigInteger(timeOfLog).compareTo(new BigInteger(stime.replaceAll("\\D+","")))>=0 && new BigInteger(timeOfLog).compareTo(new BigInteger(ftime.replaceAll("\\D+","")))<=0  && log.t.attributesEquals(tuple)){
                queryOutputEvents.add(new NAppearEvent(log.time,ftime,node,tuple));
                disappearQuery(log.time,log.t,node,log.rule,log.derivationCounter);
              return;
            }
        }

        //since not dissappeared so never arrived so query nappear
        nappearQuery(stime,ftime,node,tuple);


    }

    public void nappearQuery(String stime, String ftime,String node, Tuple tuple){
        queryOutputEvents.add(new NAppearEvent(stime,ftime,node,tuple));
        if(tuple.type.compareTo("link")==0){
            ninsertQuery(stime,ftime,node,tuple);
        }
        // how to identify if it will be localtuple?

        if(isNotDerivedLocalTuple(node,tuple)){
            nderiveQuery(stime,ftime,node,tuple);
        }
        else{
            nreceiveQuery(stime,ftime,node,tuple,1 );
        }



    }

    public boolean isNotDerivedLocalTuple(String node,Tuple tuple){

        if(tuple.type.compareTo("link")==0  && tuple.attributes.get(0).tupleAttributeValue.compareTo(node)==0){
            return true;
        }
        if(tuple.type.compareTo("path")==0  && tuple.attributes.get(0).tupleAttributeValue.compareTo(node)==0){
            if(tuple.attributes.get(2).tupleAttributelistValue.size()!=2) return false;
            return true;
        }
        if(tuple.type.compareTo("bestPath")==0  && tuple.attributes.get(0).tupleAttributeValue.compareTo(node)==0){
            return true;
        }
        return  false;

    }

    public boolean nderiveQuery(String stime,String ftime,String node,Tuple tuple){
       // queryOutputEvents.add(new UnderiveEvent(time,Node,tuple,rule));
        if(tuple.type.compareTo("link")==0){
            ninsertQuery(stime,ftime,node,tuple);
        }
        else if(tuple.type.compareTo("path")==0){
            if(tuple.attributes.get(2).tupleAttributelistValue.size()==2){
                Tuple searchTuple = new Tuple();
                searchTuple.type="link";
                searchTuple.attributes.add(tuple.attributes.get(0));
                searchTuple.attributes.add(tuple.attributes.get(1));
                nexistQuery(stime,ftime,node,searchTuple);
            }else{
                Tuple searchTuple = null;
                //log e node e link jotogula ase segula bestpath e ase kina
                HashMap<String,Integer> linknodesAndCost= getLinksList(node,ftime);

                ArrayList<String> tuplerelatedLinks=new ArrayList<String>(linknodesAndCost.keySet());
                tuplerelatedLinks.retainAll(tuple.attributes.get(3).tupleAttributelistValue);


                for (String link: tuplerelatedLinks){
                    int c=Integer.parseInt(tuple.attributes.get(2).tupleAttributeValue) -linknodesAndCost.get(link);
                    searchTuple=getRule2BestPath(link,c,ftime,tuple);
                    if(searchTuple!=null)break;
                }
                if(searchTuple!=null)nexistQuery(stime,ftime,node,searchTuple);
                else{
                    for (String link: tuplerelatedLinks){
                        int c=Integer.parseInt(tuple.attributes.get(2).tupleAttributeValue) -linknodesAndCost.get(link);

                        searchTuple.type="bestPath";
                        searchTuple.attributes=tuple.attributes;
                        searchTuple.attributes.get(0).tupleAttributeValue=link;
                        searchTuple.attributes.get(2).tupleAttributeValue=c+"";
                        searchTuple.attributes.get(3).tupleAttributelistValue.remove(link);
                        nexistQuery(stime,ftime,node,searchTuple);

                    }

                }


            }

        }
        else if(tuple.type.compareTo("bestPath")==0){
        Tuple searchTuple = new Tuple();
        searchTuple.type="path";
        searchTuple.attributes=tuple.attributes;
        nexistQuery(stime,ftime,node,searchTuple);
        }


        return false;
    }


    public void nsendQuery(String stime, String ftime,String node, Tuple tuple,int isexchangedDerive){
        queryOutputEvents.add(new NSendEvent(stime,ftime,node,tuple));
        for(LogFormat log:logs ){
            String timeOfLog=log.getTime();
               /* if(i==29){
                    System.out.println();
                }*/
            if(log.derived==0 && log.node.compareTo(node)==0  && new BigInteger(timeOfLog).compareTo(new BigInteger(stime.replaceAll("\\D+","")))>=0 && new BigInteger(timeOfLog).compareTo(new BigInteger(ftime.replaceAll("\\D+","")))<=0  && log.t.attributesEquals(tuple)){
                queryOutputEvents.add(new ExistEvent(stime,log.time,node,tuple));
                nappearQuery(log.time,ftime,node,tuple);
                return;
            }
        }
        //not found so nappearquery
        nappearQuery(stime,ftime,node,tuple);

    }

    public void nreceiveQuery(String stime, String ftime,String node,Tuple tuple, int isexchangedderived){
        queryOutputEvents.add(new NReceiveEvent(stime,ftime,node,tuple));
        String timeT0=""+(new BigInteger(stime.replaceAll("\\D+",""))).subtract(new BigInteger("100000"));
        ArrayList<String> probablesenders=probeSenders(node,tuple);
        if(probablesenders!=null){
            for(String sender: probablesenders){

                String tuplePresenceTime=stime;
                for(LogFormat log: logs){
                    if(new BigInteger(ftime.replaceAll("\\D+","")).compareTo(new BigInteger(log.time.replaceAll("ns","")))>=0) break;
                    if(log.derived==-1 && log.t.attributesEquals(tuple) && log.node.compareTo(sender)==0 && log.t.tupleDestination.compareTo(node)==0 && log.exchangeIsderived==isexchangedderived){

                        tuplePresenceTime=log.getTime();
                        break;
                    }

                }
                if(tuplePresenceTime.compareTo(stime)!=0){
                    queryOutputEvents.add(new NSendEvent(tuplePresenceTime,ftime,sender,tuple ));
                    //since present so query the tuple in narrive
                    narriveQuery(stime,ftime,sender,node,tuplePresenceTime,tuple,isexchangedderived);

                }else {
                    //else we just consider that it is not present
                    queryOutputEvents.add(new NSendEvent(tuplePresenceTime, ftime, sender, tuple));

                }

            }


        }


    }
    public void narriveQuery(String stime, String ftime,String source, String destination,String sourceSendTime,Tuple tuple,int isexchnaged){
        queryOutputEvents.add( new NArriveEvent(stime,ftime,source,destination,sourceSendTime,tuple));
        for(LogFormat log:logs ){
            String timeOfLog=log.getTime();
               /* if(i==29){
                    System.out.println();
                }*/
            if(log.derived==-1 && log.node.compareTo(source)==0  && log.time.compareTo(sourceSendTime)==0 && log.t.attributesEquals(tuple) && isexchnaged==log.exchangeIsderived && log.t.tupleDestination!=null && log.t.tupleDestination.compareTo(destination)==0){
                queryOutputEvents.add(new SendEvent(sourceSendTime,source,log.t.tupleDestination,tuple));
                queryOutputEvents.add(new DelayEvent(sourceSendTime,source,destination,tuple,""+new BigInteger(stime.replaceAll("\\D+","")).subtract(new BigInteger(sourceSendTime.replaceAll("\\D+","")))));
                return;
            }
        }

    }

    public void ninsertQuery(String stime, String ftime, String node, Tuple tuple){
        queryOutputEvents.add(new NInsertEvent(stime,ftime,node,tuple));

    }


    public ArrayList<String> probeSenders(String node, Tuple tuple){

        //node e kar kar sathe link ase ar
        //tuple er moddhe sei link gula ase kina
        ArrayList<String> probableSenders=getNodeAllLinksInLog(node);
        ArrayList<String>tupleIPV4=getTupleIPV4(tuple);
        if(tupleIPV4!=null)tupleIPV4.remove(node);

        tupleIPV4.retainAll(probableSenders);
        return tupleIPV4;
    }


    public ArrayList<String> getNodeAllLinksInLog(String node){
        Set<String> connected_nodes= new HashSet<String>();

        for (LogFormat log:logs){
            if(log.t.type.compareTo("link")==0 &&
                    log.node.compareTo(node)==0 &&
                    log.t.attributes.get(0).tupleAttributeValue.compareTo(node)==0
                    ){
                connected_nodes.add( log.t.attributes.get(1).tupleAttributeValue);

            }

        }


        return new ArrayList<String>(connected_nodes);
    }

    public ArrayList<String> getTupleIPV4(Tuple tuple){
        Set<String> tuple_nodes= new HashSet<String>();
        for(int i=0;i<tuple.attributes.size();i++){
            if(tuple.attributes.get(i).islist){
                tuple_nodes.addAll(tuple.attributes.get(i).tupleAttributelistValue);
            }else if(tuple.attributes.get(i).tupleAttributeName.compareTo("ipv4")==0){
                tuple_nodes.add(tuple.attributes.get(i).tupleAttributeValue);
            }


        }



        return new ArrayList<String>(tuple_nodes);
    }


    public  HashMap<String,Integer> getLinksList(String node, String ftime){
        HashMap<String,Integer> link_nodes= new  HashMap<String,Integer>();
        for (LogFormat log:logs){
            if( new BigInteger(log.getTime()).compareTo(new BigInteger(ftime))<=0){
                break;
            }

            //if links are present
            if(log.t.type.compareTo("link")==0 &&
                    log.node.compareTo(node)==0 &&
                    log.derived==1)link_nodes.put(log.t.attributes.get(1).tupleAttributeValue,Integer.parseInt(log.t.attributes.get(2).tupleAttributeValue));

            //if links are cut
            if(log.t.type.compareTo("link")==0 &&
                    log.node.compareTo(node)==0 &&
                    log.derived==0)link_nodes.remove(log.t.attributes.get(1).tupleAttributeValue);

            }

            return  link_nodes;
        }


    public Tuple getRule2BestPath(String linknode, int cost,String ftime,Tuple pathTuple){
        for (LogFormat log:logs){
            if( new BigInteger(log.getTime()).compareTo(new BigInteger(ftime))<=0){
                break;
            }

            //if links are present
            if(log.t.type.compareTo("bestPath")==0 &&
                    log.derived==1&&
                    log.t.attributes.get(0).tupleAttributeValue.compareTo(linknode)==0 &&
                    log.t.attributes.get(1).tupleAttributeValue.compareTo(pathTuple.attributes.get(1).tupleAttributeValue)==0 &&
                    Integer.parseInt(log.t.attributes.get(2).tupleAttributeValue)==cost){
                    return log.t;
            }



        }

        return  null;
    }





}
