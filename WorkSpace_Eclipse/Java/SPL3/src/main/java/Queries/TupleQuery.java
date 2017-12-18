package Queries;

import FinalRapidnetOutputAnalyis.Events.*;
import FinalRapidnetOutputAnalyis.LogFormat;
import FinalRapidnetOutputAnalyis.Tuples.Tuple;
import sun.rmi.runtime.Log;

import java.math.BigInteger;
import java.util.*;

public class TupleQuery {
    public ArrayList<Event> queryOutputEvents;
    ArrayList<LogFormat> logs;
    Event current = new Event();
    public Event origin;
    long startTime ;
    long endTime;
    int vertices_in_response;
    public TupleQuery() {
        queryOutputEvents = new ArrayList<Event>();
        logs = new ArrayList<LogFormat>();
        Event origin = new Event();
        vertices_in_response=0;
    }

    public void setLogs(ArrayList<LogFormat> logs) {
        this.logs = logs;
    }

    public ArrayList<Event> insertQuery(String time, Tuple t, String Node) {
        //it's the base level
        return new ArrayList<Event>();
        //return  null;
    }


    public void searchTuple(Tuple tuple, String querytype, String node, String stime, String ftime) {
        if (querytype.compareTo("exist") == 0) {
            origin = new ExistEvent(stime, ftime, node, tuple);
            current = origin;
            getProvenanceGraph(current);

        } else {
            origin = new NExistEvent(stime, ftime, node, tuple);
            current = origin;
            getProvenanceGraph(current);
        }

    }


    public void getProvenanceGraph(Event event) {
        vertices_in_response++;
        current = event;
        if (event.getEventName().compareTo("Exist") == 0) {
            ArrayList<Event> outputs = existQuery(((ExistEvent) event).startTime, ((ExistEvent) event).endTime, event.node, event.tuple);
            for (Event outevent : outputs) {
                outevent.parent=event;
                event.childs.add(outevent);
                getProvenanceGraph(outevent);
            }
        }
        if (event.getEventName().compareTo("Appear") == 0) {
            ArrayList<Event> outputs = appearQuery(((AppearEvent) event).time, ((AppearEvent) event).tuple, ((AppearEvent) event).node, ((AppearEvent) event).rule, ((AppearEvent) event).derivationCounter);
            for (Event outevent : outputs) {
                outevent.parent=event;
                event.childs.add(outevent);
                getProvenanceGraph(outevent);
            }
        }
        if (event.getEventName().compareTo("Derive") == 0) {
            ArrayList<Event> outputs = deriveQuery(((DeriveEvent) event).time, ((DeriveEvent) event).node, ((DeriveEvent) event).tuple, ((DeriveEvent) event).rule);
            for (Event outevent : outputs) {
                outevent.parent=event;
                event.childs.add(outevent);
                getProvenanceGraph(outevent);
            }
        }
        if (event.getEventName().compareTo("Send") == 0) {
            ArrayList<Event> outputs = sendQuery(((SendEvent) event).node, ((SendEvent) event).destination, ((SendEvent) event).tuple, ((SendEvent) event).time, ((SendEvent) event).isderive);
            for (Event outevent : outputs) {
                outevent.parent=event;
                event.childs.add(outevent);
                getProvenanceGraph(outevent);
            }
        }
        if (event.getEventName().compareTo("Received") == 0) {
            ArrayList<Event> outputs = receiveQuery(((ReceiveEvent) event).time, ((ReceiveEvent) event).node, ((ReceiveEvent) event).tuple, ((ReceiveEvent) event).isderve);
            for (Event outevent : outputs) {
                outevent.parent=event;
                event.childs.add(outevent);
                getProvenanceGraph(outevent);
            }
        }

        if (event.getEventName().compareTo("Dissapear") == 0) {
            ArrayList<Event> outputs = disappearQuery(((DissapearEvent) event).time, ((DissapearEvent) event).tuple, ((DissapearEvent) event).node, ((DissapearEvent) event).rule, ((DissapearEvent) event).derivationCounter);
            for (Event outevent : outputs) {
                outevent.parent=event;
                event.childs.add(outevent);
                getProvenanceGraph(outevent);
            }
        }

        if (event.getEventName().compareTo("Underive") == 0) {
            ArrayList<Event> outputs = underiveQuery(((UnderiveEvent) event).time, ((UnderiveEvent) event).node, ((UnderiveEvent) event).tuple, ((UnderiveEvent) event).rule);
            for (Event outevent : outputs) {
                outevent.parent=event;
                event.childs.add(outevent);
                getProvenanceGraph(outevent);
            }
        }

        if (event.getEventName().compareTo("Delete") == 0) {
            ArrayList<Event> outputs = deleteQuery(((DeleteEvent) event).time, ((DeleteEvent) event).tuple, ((DeleteEvent) event).node);
            for (Event outevent : outputs) {
                outevent.parent=event;
                event.childs.add(outevent);
                getProvenanceGraph(outevent);
            }
        }


        if (event.getEventName().compareTo("NExist") == 0) {
            ArrayList<Event> outputs = nexistQuery(((NExistEvent) event).stime, ((NExistEvent) event).ftime, ((NExistEvent) event).node, ((NExistEvent) event).tuple);
            for (Event outevent : outputs) {
                outevent.parent=event;
                event.childs.add(outevent);
                getProvenanceGraph(outevent);
            }
        }

        if (event.getEventName().compareTo("NAppear") == 0) {
            ArrayList<Event> outputs = nappearQuery(((NAppearEvent) event).stime, ((NAppearEvent) event).ftime, ((NAppearEvent) event).node, ((NAppearEvent) event).tuple);
            for (Event outevent : outputs) {
                outevent.parent=event;
                event.childs.add(outevent);
                getProvenanceGraph(outevent);
            }
        }


        if (event.getEventName().compareTo("NDerive") == 0) {
            ArrayList<Event> outputs = nderiveQuery(((NDeriveEvent) event).stime, ((NDeriveEvent) event).ftime, ((NDeriveEvent) event).node, ((NDeriveEvent) event).tuple);
            for (Event outevent : outputs) {
                outevent.parent=event;
                event.childs.add(outevent);
                getProvenanceGraph(outevent);
            }
        }

        if (event.getEventName().compareTo("NSend") == 0) {
            ArrayList<Event> outputs = nsendQuery(((NSendEvent) event).stime, ((NSendEvent) event).ftime, ((NSendEvent) event).node, ((NSendEvent) event).tuple, ((NSendEvent) event).isexchangederived);
            for (Event outevent : outputs) {
                outevent.parent=event;
                event.childs.add(outevent);
                getProvenanceGraph(outevent);
            }
        }


        if (event.getEventName().compareTo("NReceive") == 0) {
            ArrayList<Event> outputs = nreceiveQuery(((NReceiveEvent) event).stime, ((NReceiveEvent) event).ftime, ((NReceiveEvent) event).node, ((NReceiveEvent) event).tuple, 1);
            for (Event outevent : outputs) {
                outevent.parent=event;
                event.childs.add(outevent);
                getProvenanceGraph(outevent);
            }
        }


        if (event.getEventName().compareTo("NArrive") == 0) {
            ArrayList<Event> outputs = narriveQuery(((NArriveEvent) event).stime, ((NArriveEvent) event).ftime, ((NArriveEvent) event).source, ((NArriveEvent) event).destination, ((NArriveEvent) event).time, ((NArriveEvent) event).tuple, ((NArriveEvent) event).isexchangederived);
            for (Event outevent : outputs) {
                outevent.parent=event;
                event.childs.add(outevent);
                getProvenanceGraph(outevent);
            }
        }


        if (event.getEventName().compareTo("NInserted") == 0) {
            ArrayList<Event> outputs = ninsertQuery(((NInsertEvent) event).stime, ((NInsertEvent) event).ftime, ((NInsertEvent) event).node, ((NInsertEvent) event).tuple);
            for (Event outevent : outputs) {
                outevent.parent=event;
                event.childs.add(outevent);
                getProvenanceGraph(outevent);
            }
        }


    }

    public ArrayList<Event> appearQuery(String time, Tuple tuple, String Node, String rule, int derivationCounter) {
        //it's the base level
        ArrayList<Event> S = new ArrayList<Event>();
        //if baseTuple Search from rule parse
        if (baseTuple(rule)) {//like insert link insert
            S.add(new InsertEvent(time, Node, tuple));
            return S;
        }

        //if localTuple (N,T) from rule parse
        else if (localTuple(Node, tuple)) {//like local tuple? how no receive? or match log node with tuple node 1st parameter
            S.add(new DeriveEvent(time, Node, tuple, rule));
            return S;
            // return  insertQuery(time,t,Node);
        } else {
            S.add(new ReceiveEvent(time, Node, tuple, 1));
        }
        ;
        return S;
    }

    public ArrayList<Event> deriveQuery(String time, String Node, Tuple tuple, String rule) {

        ArrayList<Event> S = new ArrayList<Event>();

        //need to modify this to return tuples

        if (rule.compareTo("r1") == 0) {
            for (LogFormat log : logs) {
                if (log.t.type.compareTo("link") == 0 &&
                        log.time.compareTo(time) == 0 &&
                        log.derived == 1 &&
                        log.node.compareTo(Node) == 0 &&
                        log.t.attributes.get(0).tupleAttributeValue.compareTo(tuple.attributes.get(0).tupleAttributeValue) == 0 &&
                        log.t.attributes.get(1).tupleAttributeValue.compareTo(tuple.attributes.get(1).tupleAttributeValue) == 0

                        ) {
                    S.add(new AppearEvent(time, Node, log.t, log.rule, log.derivationCounter));
                    return S;
                }

            }

        } else if (rule.compareTo("r2") == 0) {
            boolean isanybestpath = false;
            ArrayList<LogFormat>bestPaths= new ArrayList<LogFormat>();

            for (LogFormat bestPathlogs : logs) {

                if (bestPathlogs.t.type.compareTo("bestPath") == 0
                        && bestPathlogs.derived == 1 &&
                        bestPathlogs.t.attributes.get(1).tupleAttributeValue.compareTo(tuple.attributes.get(1).tupleAttributeValue) == 0
                        && bestPathlogs.node.compareTo(Node) == 0
                        ) {
                    isanybestpath = true;
                    bestPaths.add( bestPathlogs);

                }

            }
            if (isanybestpath) {
                for(LogFormat bestpath: bestPaths){
                for (LogFormat linklogs : logs) {
                    if (linklogs.t.type.compareTo("link") == 0 &&
                            linklogs.node.compareTo(Node) == 0
                            &&
                            linklogs.derived == 1 &&
                            linklogs.t.attributes.get(0).tupleAttributeValue.compareTo(Node) == 0 &&
                            linklogs.t.attributes.get(1).tupleAttributeValue.compareTo(tuple.attributes.get(0).tupleAttributeValue) == 0
                            && Integer.parseInt(linklogs.t.attributes.get(2).tupleAttributeValue) + Integer.parseInt(bestpath.t.attributes.get(2).tupleAttributeValue) == Integer.parseInt(tuple.attributes.get(2).tupleAttributeValue)
                            ) {
                        S.add(new AppearEvent(time, Node, linklogs.t, linklogs.rule, linklogs.derivationCounter));
                        S.add(new AppearEvent(time, Node, bestpath.t, bestpath.rule, bestpath.derivationCounter));
                        //existQuery(0,Long.parseLong(time),Node,linklogs.t);
                        return S;
                    }
                }
                }
            }


        } else if (rule.compareTo("r3") == 0) {
            for (LogFormat log : logs) {
                if (log.t.type.compareTo("path") == 0
                        && log.derived == 1
                        && log.node.compareTo(Node) == 0
                        && log.t.attributes.get(0).tupleAttributeValue.compareTo(tuple.attributes.get(0).tupleAttributeValue) == 0
                        && log.t.attributes.get(1).tupleAttributeValue.compareTo(tuple.attributes.get(1).tupleAttributeValue) == 0

                        && log.t.attributes.get(2).tupleAttributeValue.compareTo(tuple.attributes.get(2).tupleAttributeValue) == 0
                        && log.t.attributes.get(3).tupleAttributelistValue.size() == tuple.attributes.get(3).tupleAttributelistValue.size()
                        ) {

                    S.add(new AppearEvent(log.time, Node, log.t, log.rule, log.derivationCounter));
                    return S;
                }

            }
        } else if (rule.compareTo("r4") == 0) {
            for (LogFormat log : logs) {
                if (log.t.type.compareTo("bestPath") == 0
                        && log.derived == 1
                        && log.node.compareTo(Node) == 0
                        && log.t.attributes.get(0).tupleAttributeValue.compareTo(tuple.attributes.get(1).tupleAttributeValue) == 0
                        && log.t.attributes.get(1).tupleAttributeValue.compareTo(tuple.attributes.get(0).tupleAttributeValue) == 0
                        ) {
                    S.add(new AppearEvent(log.time, Node, log.t, log.rule, log.derivationCounter));

                    return S;
                }

            }
        } else if (rule.compareTo("r5") == 0) {
            for (LogFormat log : logs) {
                if (log.t.type.compareTo("advertise") == 0
                        && log.derived == -1
                        && log.node.compareTo(Node) == 0
                        && log.t.attributes.get(0).tupleAttributeValue.compareTo(tuple.attributes.get(0).tupleAttributeValue) == 0
                        && log.t.attributes.get(1).tupleAttributeValue.compareTo(tuple.attributes.get(1).tupleAttributeValue) == 0
                        ) {
                    S.add(new AppearEvent(log.time, Node, log.t, log.rule, log.derivationCounter));

                    return S;
                }

            }
        } else if (rule.compareTo("r6") == 0) {
            for (LogFormat log : logs) {
                if (log.t.type.compareTo("importfilter") == 0
                        && log.derived == 1
                        && log.node.compareTo(Node) == 0
                        && log.t.attributes.get(0).tupleAttributeValue.compareTo(tuple.attributes.get(1).tupleAttributeValue) == 0
                        && log.t.attributes.get(1).tupleAttributeValue.compareTo(tuple.attributes.get(0).tupleAttributeValue) == 0
                        ) {
                    S.add(new AppearEvent(log.time, Node, log.t, log.rule, log.derivationCounter));

                    return S;
                }

            }
        }


        return S;

    }

    public ArrayList<Event> sendQuery(String node, String destination, Tuple tuple, String destinationArrivalTime, int isderive) {
        //findlog
        //if()
        ArrayList<Event> S = new ArrayList<Event>();
        for (LogFormat log : logs) {
            if (log.derived == -1 && log.t.attributesEquals(tuple) && log.node.compareTo(node) == 0 && log.t.tupleDestination.compareTo(destination) == 0 && log.exchangeIsderived == isderive) {
                S.add(new DelayEvent(log.time, log.node, destination, log.t, "" + new BigInteger(destinationArrivalTime).subtract(new BigInteger(log.getTime()))));
                S.add(new SendEvent(log.time, node, log.t.tupleDestination, tuple));

            }

        }


        for (LogFormat log : logs) {

            if (isderive == 1 && log.derived == 1 && log.t.attributesEquals(tuple) && log.node.compareTo(node) == 0) {

                S.add(new AppearEvent(log.time, node, log.t, log.rule, log.derivationCounter));
                return S;
            }

            if (isderive == 0 && log.derived == 0 && log.t.attributesEquals(tuple) && log.node.compareTo(node) == 0) {

                S.add(new DissapearEvent(log.time, node, log.t, log.rule, log.derivationCounter));
                return S;
            }

        }
        return S;
    }

    public ArrayList<Event> receiveQuery(String time, String node, Tuple tuple, int isderive) {
        ArrayList<Event> S = new ArrayList<Event>();
        for (LogFormat log : logs) {
            if (log.derived == -1 && log.t.attributesEquals(tuple) && log.node.compareTo(node) == 0 && log.exchangeIsderived == isderive) {
                current.time = log.time;
                ((ReceiveEvent) current).sender = log.t.tupleSource;
                S.add(new SendEvent(log.getTime(), log.t.tupleSource, node, tuple, isderive));
                return S;
            }

        }
        return S;
    }

    public ArrayList<Event> existQuery(String stime, String ftime, String Node, Tuple t) {
        ArrayList<Event> S = new ArrayList<Event>();
        queryOutputEvents = new ArrayList<Event>();
        int i=0;
        //querying for particular tuple
        for (LogFormat log : logs) {

            String timeOfLog = log.getTime();

            if (log.derived == 1 && log.node.compareTo(Node) == 0 && new BigInteger(timeOfLog).compareTo(new BigInteger(stime.replaceAll("\\D+", ""))) >= 0 && new BigInteger(timeOfLog).compareTo(new BigInteger(ftime.replaceAll("\\D+", ""))) <= 0 && log.t.attributesEquals(t)) {
                ((ExistEvent) current).startTime = log.time;
                S.add(new AppearEvent(log.time, Node, log.t, log.rule, log.derivationCounter));
                break;
            }


        }
        for (LogFormat log : logs) {
            String timeOfLog = log.getTime();
               /* if(i==29){
                    System.out.println();
                }*/
            //if(i==393){
               // System.out.println();
           // }
            if (log.derived == 0 && log.node.compareTo(Node) == 0 && new BigInteger(timeOfLog).compareTo(new BigInteger(stime.replaceAll("\\D+",""))) >= 0 && new BigInteger(timeOfLog).compareTo(new BigInteger(ftime.replaceAll("\\D+",""))) <= 0 && log.t.attributesEquals(t)) {
                ((ExistEvent) current).endTime = log.time;
                S.add(new DissapearEvent(log.time, Node, log.t, log.rule, log.derivationCounter));
                break;
            }
               /* i++;*/
        }

        return S;
    }

    /*public void find(){
        //search log(){}
    }*/


    public boolean localTuple(String Node, Tuple t) {
        for (LogFormat log : logs) {
            if (log.t.attributesEquals(t) && log.derived == -1 && log.t.tupleDestination != null && log.t.tupleDestination.compareTo(Node) == 0)
                return false;
        }
        return true;
    }

    public boolean baseTuple(String rule) {
        //return from rules is base?
        if (rule == null) return true;
        else return false;
    }

    public boolean isPresentInLog(Tuple t, boolean derived, String time, String rule, int derivationCounter) {

        return false;
    }


    public ArrayList<Event> disappearQuery(String time, Tuple tuple, String Node, String rule, int derivationCounter) {
        //it's the base level
        ArrayList<Event> S = new ArrayList<Event>();
        //if baseTuple Search from rule parse
        if (baseTuple(rule)) {//like insert link insert
            S.add(new DeleteEvent(time, Node, tuple));
            return S;

        }

        //if localTuple (N,T) from rule parse
        else if (localTuple(Node, tuple)) {//like local tuple? how no receive? or match log node with tuple node 1st parameter
            S.add(new UnderiveEvent(time, Node, tuple, rule));
            // return  insertQuery(time,t,Node);
            return S;
        } else {
            ///
            S.add(new ReceiveEvent(time, Node, tuple, 0));
            return S;
        }
    }


    public ArrayList<Event> deleteQuery(String time, Tuple tuple, String node) {
        return new ArrayList<Event>();
    }

    public ArrayList<Event> underiveQuery(String time, String Node, Tuple tuple, String rule) {
        ArrayList<Event> S = new ArrayList<Event>();
        //need to modify this to return tuples
        if (rule.compareTo("r1") == 0) {
            for (LogFormat log : logs) {
                if (log.t.type.compareTo("link") == 0 &&
                        log.time.compareTo(time) == 0 &&
                        log.derived == 0 &&
                        log.node.compareTo(Node) == 0 &&
                        log.t.attributes.get(0).tupleAttributeValue.compareTo(tuple.attributes.get(0).tupleAttributeValue) == 0 &&
                        log.t.attributes.get(1).tupleAttributeValue.compareTo(tuple.attributes.get(1).tupleAttributeValue) == 0

                        ) {
                    S.add(new DissapearEvent(time, Node, log.t, log.rule, log.derivationCounter));
                    return S;
                }

            }

        } else if (rule.compareTo("r2") == 0) {
            boolean isanybestpath = false;
            LogFormat bestpath = null;
            for (LogFormat linklogs : logs) {
                if (linklogs.t.type.compareTo("link") == 0 &&
                        linklogs.node.compareTo(Node) == 0
                        &&
                        linklogs.derived == 0 &&
                        linklogs.t.attributes.get(0).tupleAttributeValue.compareTo(Node) == 0 &&
                        linklogs.t.attributes.get(1).tupleAttributeValue.compareTo(tuple.attributes.get(1).tupleAttributeValue) == 0
                        ) {
                    S.add(new DissapearEvent(linklogs.time, Node, linklogs.t, linklogs.rule, linklogs.derivationCounter));
                    //existQuery(0,Long.parseLong(time),Node,linklogs.t);
                    return S;
                }
            }

            for (LogFormat bestPathlogs : logs) {

                if (bestPathlogs.t.type.compareTo("bestPath") == 0 &&
                        bestPathlogs.derived == 0 &&
                        bestPathlogs.t.attributes.get(1).tupleAttributeValue.compareTo(tuple.attributes.get(1).tupleAttributeValue) == 0
                        && bestPathlogs.node.compareTo(Node) == 0
                        ) {
                    isanybestpath = true;
                    bestpath = bestPathlogs;
                    break;
                }

            }
            if (isanybestpath) {

                for (LogFormat pathlogs : logs) {
                    if (pathlogs.t.type.compareTo("path") == 0 &&
                            pathlogs.node.compareTo(Node) == 0
                            &&
                            pathlogs.derived == 1 &&
                            pathlogs.t.attributes.get(0).tupleAttributeValue.compareTo(Node) == 0 &&
                            pathlogs.t.attributes.get(1).tupleAttributeValue.compareTo(bestpath.t.attributes.get(1).tupleAttributeValue) == 0
                            && Integer.parseInt(pathlogs.t.attributes.get(2).tupleAttributeValue) < Integer.parseInt(bestpath.t.attributes.get(2).tupleAttributeValue)
                            ) {
                        S.add(new AppearEvent(time, Node, pathlogs.t, pathlogs.rule, pathlogs.derivationCounter));
                        //existQuery(0,Long.parseLong(time),Node,linklogs.t);
                        return S;
                    }
                }
            }
/*
            for (LogFormat bestPathlogs : logs) {
                if (bestPathlogs.t.type.compareTo("bestPath") == 0 &&
                        bestPathlogs.derived == 0 &&
                        bestPathlogs.t.attributes.get(1).tupleAttributeValue.compareTo(tuple.attributes.get(0).tupleAttributeValue) == 0
                        && bestPathlogs.node.compareTo(Node) == 0
                        ) {
                    isanybestpath = true;
                    bestpath = bestPathlogs;
                }

            }
            if (isanybestpath) {
                S.add(new DissapearEvent(time, Node, bestpath.t, bestpath.rule, bestpath.derivationCounter));
                return S;

            }*/


        } else if (rule.compareTo("r3") == 0) {
            for (LogFormat log : logs) {
                if (log.t.type.compareTo("path") == 0
                        && log.derived == 0
                        && log.node.compareTo(Node) == 0
                        && log.t.attributes.get(0).tupleAttributeValue.compareTo(tuple.attributes.get(0).tupleAttributeValue) == 0
                        && log.t.attributes.get(1).tupleAttributeValue.compareTo(tuple.attributes.get(1).tupleAttributeValue) == 0

                        && log.t.attributes.get(2).tupleAttributeValue.compareTo(tuple.attributes.get(2).tupleAttributeValue) == 0
                        && log.t.attributes.get(3).tupleAttributelistValue.size() == tuple.attributes.get(3).tupleAttributelistValue.size()
                        ) {

                    S.add(new DissapearEvent(log.time, Node, log.t, log.rule, log.derivationCounter));
                    return S;
                }

            }
        }

        return S;

    }


    //starting negative provenance queries

    public ArrayList<Event> nexistQuery(String stime, String ftime, String node, Tuple tuple) {
        ArrayList<Event> S = new ArrayList<Event>();
        for (LogFormat log : logs) {
            String timeOfLog = log.getTime();

            if (log.derived == 0 && log.node.compareTo(node) == 0 && new BigInteger(timeOfLog).compareTo(new BigInteger(stime.replaceAll("\\D+", ""))) <= 0 && log.t.attributesEquals(tuple)) {
                queryOutputEvents.add(new NAppearEvent(log.time, ftime, node, tuple));
                S.add(new NAppearEvent(stime, ftime, node, tuple));
                S.add(new DissapearEvent(log.time, node, log.t, log.rule, log.derivationCounter));
                return S;
            }
        }

        //since not dissappeared so never arrived so query nappear
        S.add(new NAppearEvent(stime, ftime, node, tuple));
        return S;


    }

    public ArrayList<Event> nappearQuery(String stime, String ftime, String node, Tuple tuple) {
        ArrayList<Event> S = new ArrayList<Event>();
        queryOutputEvents.add(new NAppearEvent(stime, ftime, node, tuple));
        if (tuple.type.compareTo("link") == 0) {
            S.add(new NInsertEvent(stime, ftime, node, tuple));
            return S;
        }

        if (tuple.type.compareTo("packet") == 0 && tuple.attributes.get(1).tupleAttributeValue.compareTo(node) == 0) {
            return S;
        }
        // how to identify if it will be localtuple?

        if (isNotDerivedLocalTuple(node, tuple)) {
            S.add(new NDeriveEvent(stime, ftime, node, tuple));
            return S;
        } else {
            S.add(new NReceiveEvent(stime, ftime, node, tuple, 1));
            return S;
        }


    }

    public boolean isNotDerivedLocalTuple(String node, Tuple tuple) {

        if (tuple.type.compareTo("link") == 0 && tuple.attributes.get(0).tupleAttributeValue.compareTo(node) == 0) {
            return true;
        }
        if (tuple.type.compareTo("path") == 0 && tuple.attributes.get(0).tupleAttributeValue.compareTo(node) == 0) {
            if (tuple.attributes.get(3).tupleAttributelistValue.size() != 2) return false;
            return true;
        }
        if (tuple.type.compareTo("bestPath") == 0 && tuple.attributes.get(0).tupleAttributeValue.compareTo(node) == 0) {
            return true;
        }
        return false;

    }

    public ArrayList<Event> nderiveQuery(String stime, String ftime, String node, Tuple tuple) {
        // queryOutputEvents.add(new UnderiveEvent(time,Node,tuple,rule));
        ArrayList<Event> S = new ArrayList<Event>();
        if (tuple.type.compareTo("link") == 0) {
            S.add(new NInsertEvent(stime, ftime, node, tuple));
        } else if (tuple.type.compareTo("path") == 0) {
            if (tuple.attributes.get(2).tupleAttributelistValue.size() == 2) {
                Tuple searchTuple = new Tuple();
                searchTuple.type = "link";
                searchTuple.attributes.add(tuple.attributes.get(0));
                searchTuple.attributes.add(tuple.attributes.get(1));
                S.add(new NExistEvent(stime, ftime, node, searchTuple));
            } else {
                Tuple searchTuple = null;
                //log e node e link jotogula ase segula bestpath e ase kina
                HashMap<String, Integer> linknodesAndCost = getLinksList(node, ftime);

                ArrayList<String> tuplerelatedLinks = new ArrayList<String>(linknodesAndCost.keySet());
                tuplerelatedLinks.retainAll(tuple.attributes.get(3).tupleAttributelistValue);


                for (String link : tuplerelatedLinks) {
                    int c = Integer.parseInt(tuple.attributes.get(2).tupleAttributeValue) - linknodesAndCost.get(link);
                    searchTuple = getRule2BestPath(link, c, ftime, tuple);
                    if (searchTuple != null) break;
                }
                if (searchTuple != null) S.addAll(nexistQuery(stime, ftime, node, searchTuple));
                else {
                    for (String link : tuplerelatedLinks) {
                        int c = Integer.parseInt(tuple.attributes.get(2).tupleAttributeValue) - linknodesAndCost.get(link);

                        searchTuple.type = "bestPath";
                        searchTuple.attributes = tuple.attributes;
                        searchTuple.attributes.get(0).tupleAttributeValue = link;
                        searchTuple.attributes.get(2).tupleAttributeValue = c + "";
                        searchTuple.attributes.get(3).tupleAttributelistValue.remove(link);
                        S.add(new NExistEvent(stime, ftime, node, searchTuple));

                    }

                }


            }

        } else if (tuple.type.compareTo("bestPath") == 0) {
            Tuple searchTuple = new Tuple();
            searchTuple.type = "path";
            searchTuple.attributes = tuple.attributes;
            S.add(new NExistEvent(stime, ftime, node, searchTuple));
        } else if (tuple.type.compareTo("packet") == 0) {
            Tuple searchTuple = new Tuple();
            searchTuple.type = "bestPath";
            searchTuple.attributes = tuple.attributes;
            S.add(new NExistEvent(stime, ftime, node, searchTuple));

        }


        return S;
    }


    public ArrayList<Event> nsendQuery(String stime, String ftime, String node, Tuple tuple, int isexchangedDerive) {

        ArrayList<Event> S = new ArrayList<Event>();

        for (LogFormat log : logs) {
            String timeOfLog = log.getTime();
               /* if(i==29){
                    System.out.println();
                }*/
            if (log.derived == 1 && log.node.compareTo(node) == 0 && new BigInteger(timeOfLog).compareTo(new BigInteger(stime.replaceAll("\\D+", ""))) <= 0 && log.t.attributesEquals(tuple)) {
                S.add(new ExistEvent("0ns", log.time, node, tuple));
                S.add(new NAppearEvent(log.time, ftime, node, tuple));
                return S;
            }
        }
        //not found so nappearquery
        S.add(new NAppearEvent(stime, ftime, node, tuple));
        return S;

    }

    public ArrayList<Event> nreceiveQuery(String stime, String ftime, String node, Tuple tuple, int isexchangedderived) {

        ArrayList<Event> S = new ArrayList<Event>();

        String timeT0 = "" + (new BigInteger(stime.replaceAll("\\D+", ""))).subtract(new BigInteger("100000"));
        ArrayList<String> probablesenders = probeSenders(node, tuple);
        if (probablesenders != null) {
            for (String sender : probablesenders) {

                String tuplePresenceTime = stime;
                for (LogFormat log : logs) {
                    if (new BigInteger(ftime.replaceAll("\\D+", "")).compareTo(new BigInteger(log.time.replaceAll("ns", ""))) >= 0)
                        break;
                    if (log.derived == -1 && log.t.attributesEquals(tuple) && log.node.compareTo(sender) == 0 && log.t.tupleDestination.compareTo(node) == 0 && log.exchangeIsderived == isexchangedderived) {

                        tuplePresenceTime = log.getTime();
                        break;
                    }

                }
                if (tuplePresenceTime.compareTo(stime) != 0) {
                    S.add(new NSendEvent(tuplePresenceTime, ftime, sender, tuple, isexchangedderived));
                    //since present so query the tuple in narrive
                    return S;
                } else {
                    //else we just consider that it is not present
                    S.add(new NSendEvent(tuplePresenceTime, ftime, sender, tuple, isexchangedderived));
                    return S;
                }

            }


        }
        return S;

    }

    public ArrayList<Event> narriveQuery(String stime, String ftime, String source, String destination, String sourceSendTime, Tuple tuple, int isexchnaged) {

        ArrayList<Event> S = new ArrayList<Event>();
        for (LogFormat log : logs) {
            String timeOfLog = log.getTime();
               /* if(i==29){
                    System.out.println();
                }*/
            if (log.derived == -1 && log.node.compareTo(source) == 0 && log.time.compareTo(sourceSendTime) == 0 && log.t.attributesEquals(tuple) && isexchnaged == log.exchangeIsderived && log.t.tupleDestination != null && log.t.tupleDestination.compareTo(destination) == 0) {
                S.add(new SendEvent(sourceSendTime, source, log.t.tupleDestination, tuple));
                S.add(new DelayEvent(sourceSendTime, source, destination, tuple, "" + new BigInteger(stime.replaceAll("\\D+", "")).subtract(new BigInteger(sourceSendTime.replaceAll("\\D+", "")))));
                return S;
            }
        }
        return S;
    }

    public ArrayList<Event> ninsertQuery(String stime, String ftime, String node, Tuple tuple) {
        return new ArrayList<Event>();

    }


    public ArrayList<String> probeSenders(String node, Tuple tuple) {

        //node e kar kar sathe link ase ar
        //tuple er moddhe sei link gula ase kina
        ArrayList<String> probableSenders = getNodeAllLinksInLog(node);
        ArrayList<String> tupleIPV4 = getTupleIPV4(tuple);
        if (tupleIPV4 != null) tupleIPV4.remove(node);

        tupleIPV4.addAll(probableSenders);
        return tupleIPV4;
    }


    public ArrayList<String> getNodeAllLinksInLog(String node) {
        Set<String> connected_nodes = new HashSet<String>();

        for (LogFormat log : logs) {
            if (log.t.type.compareTo("link") == 0 &&
                    log.node.compareTo(node) == 0 &&
                    log.t.attributes.get(0).tupleAttributeValue.compareTo(node) == 0
                    ) {
                connected_nodes.add(log.t.attributes.get(1).tupleAttributeValue);

            }

        }


        return new ArrayList<String>(connected_nodes);
    }

    public ArrayList<String> getTupleIPV4(Tuple tuple) {
        Set<String> tuple_nodes = new HashSet<String>();
        for (int i = 0; i < tuple.attributes.size(); i++) {
            if (tuple.attributes.get(i).islist) {
                tuple_nodes.addAll(tuple.attributes.get(i).tupleAttributelistValue);
            } else if (tuple.attributes.get(i).tupleAttributeName.compareTo("ipv4") == 0) {
                tuple_nodes.add(tuple.attributes.get(i).tupleAttributeValue);
            }


        }


        return new ArrayList<String>(tuple_nodes);
    }


    public HashMap<String, Integer> getLinksList(String node, String ftime) {
        HashMap<String, Integer> link_nodes = new HashMap<String, Integer>();
        for (LogFormat log : logs) {
            if (new BigInteger(log.getTime()).compareTo(new BigInteger(ftime)) <= 0) {
                break;
            }

            //if links are present
            if (log.t.type.compareTo("link") == 0 &&
                    log.node.compareTo(node) == 0 &&
                    log.derived == 1)
                link_nodes.put(log.t.attributes.get(1).tupleAttributeValue, Integer.parseInt(log.t.attributes.get(2).tupleAttributeValue));

            //if links are cut
            if (log.t.type.compareTo("link") == 0 &&
                    log.node.compareTo(node) == 0 &&
                    log.derived == 0) link_nodes.remove(log.t.attributes.get(1).tupleAttributeValue);

        }

        return link_nodes;
    }


    public Tuple getRule2BestPath(String linknode, int cost, String ftime, Tuple pathTuple) {
        for (LogFormat log : logs) {
            if (new BigInteger(log.getTime()).compareTo(new BigInteger(ftime)) <= 0) {
                break;
            }

            //if links are present
            if (log.t.type.compareTo("bestPath") == 0 &&
                    log.derived == 1 &&
                    log.t.attributes.get(0).tupleAttributeValue.compareTo(linknode) == 0 &&
                    log.t.attributes.get(1).tupleAttributeValue.compareTo(pathTuple.attributes.get(1).tupleAttributeValue) == 0 &&
                    Integer.parseInt(log.t.attributes.get(2).tupleAttributeValue) == cost) {
                return log.t;
            }


        }

        return null;
    }


    public Tuple findTupleWithPrimaryKeys(Tuple tuple, ArrayList<Integer> primaryAttributePositions) {
       int j=0;
        for (LogFormat log : logs) {
            j++;
            if(j==123){
                System.out.println("ok");
            }
            if (log.t.type.compareTo(tuple.type) == 0) {
                boolean isfound = true;
                for (int i : primaryAttributePositions) {
                    i=i-1;
                    if (!(log.t.attributes.get(i).islist && tuple.attributes.get(i).islist)) {
                        if (!(log.t.attributes.get(i).tupleAttributeValue.compareTo(tuple.attributes.get(i).tupleAttributeValue) == 0))
                            isfound = false;
                    } else {
                        if (!(log.t.attributes.get(i).tupleAttributelistValue.containsAll(tuple.attributes.get(i).tupleAttributelistValue) && tuple.attributes.get(i).tupleAttributelistValue.containsAll(log.t.attributes.get(i).tupleAttributelistValue)))
                            isfound = false;
                    }
                }
                if (isfound) return log.t;
            }


        }
        return null;
    }


    public Tuple getTupleStructure(String tupleType){
        for (LogFormat log : logs) {
            if(log.t.type.compareTo(tupleType)==0)
                try {
                    return  (Tuple)log.t.clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
        }
        return  null;
    }


    public void startTimer(){
        startTime = System.currentTimeMillis();
    }

    public String getPassedTime(){
         endTime = System.currentTimeMillis();
        return (endTime - startTime)+"";
    }
    public int getVerticesInResponse(){
        int i=0;
        Queue<Event> events=  new LinkedList<Event>();
        events.add(this.origin);
        while (events.size()!=0){
            Event currentevent=events.poll();
            i++;
            for(Event childevents:currentevent.childs){
                events.add(childevents);
            }
        }
        return i;
    }



   public void getSuperVertices() throws CloneNotSupportedException {
        Stack<Event> current_Vertices= new Stack<Event>();
        current_Vertices.push(this.origin);
        Event newOrigin= current_Vertices.peek();
        int i=0;
        String parentEventEndtime=null;
        while (current_Vertices.size()!=0) {
            Event currentEvent = current_Vertices.pop();
            if (currentEvent.eventName.compareTo("NExist") == 0) {
                if
                        (currentEvent.childs.size() > 0 && currentEvent.childs.get(0).eventName.contains("NAppear")
                        &&
                        currentEvent.childs.get(0).childs.size() > 0 && currentEvent.childs.get(0).childs.get(0).eventName.contains("NDerive")

                        ) {
                    AbsenceEvent absenceEvent = new AbsenceEvent(((NExistEvent) currentEvent));
                    Tuple advertise = (Tuple) ((NExistEvent) currentEvent).tuple.clone();
                    advertise.type = "advertise";
                    AbsenceEvent absenceAdvertise = new AbsenceEvent(((NExistEvent) currentEvent).stime, ((NExistEvent) currentEvent).ftime, ((NExistEvent) currentEvent).node, advertise);

                    if (currentEvent.childs.size() >= 2) {//Child of parent NExistEvent
                        for (int k = 1; k < currentEvent.childs.size(); k++) {
                            currentEvent.childs.get(k).parent = absenceEvent;
                            absenceEvent.childs.add(currentEvent.childs.get(k));
                        }
                    }
                    //child of NDeriveEvent
                    if (currentEvent.childs.get(0).childs.get(0).childs.size() > 1) {
                        Event child = currentEvent.childs.get(0).childs.get(0).childs.get(0);
                        child.parent = absenceEvent;
                        absenceEvent.childs.add(0, child);

                    } else {
                        Event child = null;
                    }

                    if (currentEvent.parent != null) {
                        if (currentEvent.tuple.type.contains("path") || currentEvent.tuple.type.contains("link") || currentEvent.tuple.type.contains("bestpath")) {
                            absenceAdvertise.parent = currentEvent.parent;
                            currentEvent.parent.childs.remove(currentEvent);
                            currentEvent.parent.childs.add(absenceAdvertise);
                            absenceAdvertise.childs.add(absenceEvent);
                            absenceEvent.parent = absenceAdvertise;
                        } else {
                            absenceEvent.parent = currentEvent.parent;
                            currentEvent.parent.childs.remove(currentEvent);
                            currentEvent.parent.childs.add(absenceEvent);

                        }
                    }
                        currentEvent = absenceEvent;

                    }
                    parentEventEndtime = ((AbsenceEvent) currentEvent).ftime;

                }
            //complete of NExist

                else if (currentEvent.eventName.compareTo("Exist") == 0) {
                    if
                            (currentEvent.childs.size() > 0 && currentEvent.childs.get(0).eventName.contains("Appear")
                            &&
                            currentEvent.childs.get(0).childs.size() > 0 && currentEvent.childs.get(0).childs.get(0).eventName.contains("Derive")

                            ) {
                        ExistenceEvent existenceEvent = new ExistenceEvent(((ExistEvent) currentEvent));

                        Tuple advertise = (Tuple) ((ExistEvent) currentEvent).tuple.clone();
                        advertise.type = "advertise";
                        ExistenceEvent existAdvertise = new ExistenceEvent(((ExistEvent) currentEvent).startTime, ((ExistEvent) currentEvent).endTime, ((ExistEvent) currentEvent).node, advertise);

                        if (currentEvent.childs.size() >= 2) {//Child of parent NExistEvent
                            for (int k = 1; k < currentEvent.childs.size(); k++) {
                                currentEvent.childs.get(k).parent = existenceEvent;
                                existenceEvent.childs.add(currentEvent.childs.get(k));
                            }
                        }
                        //child of NDeriveEvent
                        if (currentEvent.childs.get(0).childs.get(0).childs.size() > 1) {
                            Event child = currentEvent.childs.get(0).childs.get(0).childs.get(0);
                            child.parent = existenceEvent;
                            existenceEvent.childs.add(0, child);

                        } else {
                            Event child = null;
                        }


                        if (currentEvent.parent != null) {
                      /*  existenceEvent.parent=currentEvent.parent;
                        currentEvent.parent.childs.remove(currentEvent);
                        currentEvent.parent.childs.add(existenceEvent);
                    */
                            if (currentEvent.tuple.type.contains("path") || currentEvent.tuple.type.contains("link") || currentEvent.tuple.type.contains("bestpath")) {
                                existAdvertise.parent = currentEvent.parent;
                                currentEvent.parent.childs.remove(currentEvent);
                                currentEvent.parent.childs.add(existAdvertise);
                                existAdvertise.childs.add(existenceEvent);
                                existenceEvent.parent = existAdvertise;

                            } else {
                                existenceEvent.parent = currentEvent.parent;
                                currentEvent.parent.childs.remove(currentEvent);
                                currentEvent.parent.childs.add(existenceEvent);
                            }


                        }
                        currentEvent = existenceEvent;


                    }


                    parentEventEndtime = ((ExistenceEvent) currentEvent).endTime;
                }//complete of Exist

                else if (currentEvent.eventName.compareTo("Appear") == 0 && parentEventEndtime != null) {
                    if
                            (currentEvent.childs.size() > 0 && (currentEvent.childs.get(0).eventName.contains("Derive") || currentEvent.childs.get(0).eventName.contains("Insert") || currentEvent.childs.get(0).eventName.contains("Received"))) {
                        ExistenceEvent existenceEvent = new ExistenceEvent(((AppearEvent) currentEvent).getTime(), parentEventEndtime, ((AppearEvent) currentEvent).node, ((AppearEvent) currentEvent).tuple);

                        Tuple advertise = (Tuple) ((AppearEvent) currentEvent).tuple.clone();
                        advertise.type = "advertise";
                        ExistenceEvent existAdvertise = new ExistenceEvent(((AppearEvent) currentEvent).time,parentEventEndtime, ((AppearEvent) currentEvent).node, advertise);


                        if (currentEvent.childs.size() > 0) {//Child of parent NExistEvent
                            for (int k = 0; k < currentEvent.childs.size(); k++) {
                                currentEvent.childs.get(k).parent = existenceEvent;
                                existenceEvent.childs.add(currentEvent.childs.get(k));

                            }
                        } else {
                            Event child = null;
                        }


                        if (currentEvent.parent != null) {
  /*                      existenceEvent.parent=currentEvent.parent;
                        currentEvent.parent.childs.remove(currentEvent);
                        currentEvent.parent.childs.add(existenceEvent);*/
                            if (currentEvent.tuple.type.contains("path") || currentEvent.tuple.type.contains("link") || currentEvent.tuple.type.contains("bestpath")) {
                                existAdvertise.parent = currentEvent.parent;
                                currentEvent.parent.childs.remove(currentEvent);
                                currentEvent.parent.childs.add(existAdvertise);
                                existAdvertise.childs.add(existenceEvent);
                                existenceEvent.parent = existAdvertise;

                            } else {
                                existenceEvent.parent = currentEvent.parent;
                                currentEvent.parent.childs.remove(currentEvent);
                                currentEvent.parent.childs.add(existenceEvent);
                            }
                        }
                        currentEvent = existenceEvent;

                    }

                }//complete of Appear

                if (currentEvent.eventName.contains("Derive") || currentEvent.eventName.contains("Send") || currentEvent.eventName.contains("Receive") ||  currentEvent.eventName.contains("Delay")||currentEvent.eventName.contains("Underive")) {

                    for (Event childevents : currentEvent.childs) {
                        childevents.parent = currentEvent.parent;
                        currentEvent.parent.childs.add(childevents);
                    }
                    currentEvent.parent.childs.remove(currentEvent);
                }

                if(currentEvent.eventName.contains("Dissapear")){
                    if
                            (currentEvent.childs.size() > 0 && (currentEvent.childs.get(0).eventName.contains("Underive") || currentEvent.childs.get(0).eventName.contains("Delete") || currentEvent.childs.get(0).eventName.contains("Received"))) {
                        AbsenceEvent absenenceEvent = new AbsenceEvent(((DissapearEvent) currentEvent).getTime(), parentEventEndtime, ((DissapearEvent) currentEvent).node, ((DissapearEvent) currentEvent).tuple);

                        Tuple advertise = (Tuple) ((DissapearEvent) currentEvent).tuple.clone();
                        advertise.type = "advertise";
                        AbsenceEvent absentAdvertise = new AbsenceEvent(((DissapearEvent) currentEvent).time,parentEventEndtime, ((DissapearEvent) currentEvent).node, advertise);


                        if (currentEvent.childs.size() > 0) {//Child of parent NExistEvent
                            for (int k = 0; k < currentEvent.childs.size(); k++) {
                                currentEvent.childs.get(k).parent = absenenceEvent;
                                absenenceEvent.childs.add(currentEvent.childs.get(k));

                            }
                        } else {
                            Event child = null;
                        }


                        if (currentEvent.parent != null) {
  /*                      existenceEvent.parent=currentEvent.parent;
                        currentEvent.parent.childs.remove(currentEvent);
                        currentEvent.parent.childs.add(existenceEvent);*/
                            if (currentEvent.tuple.type.contains("path") || currentEvent.tuple.type.contains("link") || currentEvent.tuple.type.contains("bestpath")) {
                                absentAdvertise.parent = currentEvent.parent;
                                currentEvent.parent.childs.remove(currentEvent);
                                currentEvent.parent.childs.add(absentAdvertise);
                                absentAdvertise.childs.add(absenenceEvent);
                                absenenceEvent.parent = absentAdvertise;

                            } else {
                                absenenceEvent.parent = currentEvent.parent;
                                currentEvent.parent.childs.remove(currentEvent);
                                currentEvent.parent.childs.add(absenenceEvent);
                            }
                        }
                        currentEvent = absenenceEvent;

                    }
                }

                //adding all childevents to the queue
                for (Event childevents : currentEvent.childs) {
                    current_Vertices.push(childevents);

                }

                //just pushing other events

                if (i == 0) newOrigin = currentEvent;
                i++;
            }

        this.origin=newOrigin;
        vertices_in_response=i;

    }


}