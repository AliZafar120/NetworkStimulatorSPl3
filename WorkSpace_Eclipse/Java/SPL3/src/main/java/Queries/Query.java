package Queries;

import FinalRapidnetOutputAnalyis.Tuples.Tuple;

import java.security.PublicKey;
import java.util.ArrayList;

public class Query {


    public Tuple insertQuery(String time,Tuple t, String Node){
        //it's the base level
        return  null;
    }

    public Tuple appearQuery(String time,Tuple tuple, String Node, String rule, int derivationCounter){
        //it's the base level

        //if baseTuple Search from rule parse
        if(baseTuple(rule)){//like insert link insert
            return  insertQuery(time,tuple,Node);
        }

        //if localTuple (N,T) from rule parse
        if(localTuple(Node,tuple)){//like local tuple? how no receive? or match log node with tuple node 1st parameter
             deriveQuery(time,Node,tuple,rule);
            // return  insertQuery(time,t,Node);
        }

        return  null;
    }

    public void deriveQuery(String time,String Node,Tuple tuple, String rule){




    }

    public void sendQuery(String time, String sender, String receiver,Tuple t, boolean derived){
        //findlog
        //if()

    }

    public void receiveQuery(Tuple t, String receiverNode,String senderNode, String time,boolean derived){

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


}
