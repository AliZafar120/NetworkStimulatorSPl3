package FinalRapidnetOutputAnalyis.Rules.RulesWithApplications;

import FinalRapidnetOutputAnalyis.Rules.Rule;
import  FinalRapidnetOutputAnalyis.Tuples.Tuple;
import  FinalRapidnetOutputAnalyis.Tuples.Attribute.TupleAttribute;

import java.util.ArrayList;

public class BestPathRule1 extends Rule{

    Tuple tupleUnderQuery;
    int derivationCounter=0;
    String source;


    public BestPathRule1(String rule) {
        super(rule);
        this.inputTuples= new ArrayList<Tuple>();
        this.name=rule;

        this.derivationCounter=derivationCounter;
        this.source=source;
        extractDerivedTuple();



    }

    public BestPathRule1(String rule,Tuple tuple,int derivationCounter, String source) {
        super(rule);
        this.inputTuples= new ArrayList<Tuple>();
        this.name=rule;
        tupleUnderQuery=tuple;
        this.derivationCounter=derivationCounter;
        this.source=source;
        extractDerivedTuple();



    }

    public void extractDerivedTuple(){
        if(this.name=="r1" || this.name=="r2"){
            this.derivedTuple="path";
        }
        if(this.name=="r3"){
            this.derivedTuple="bestPath";
        }
    }


    public void extractInputTuples(){
        ArrayList<Tuple> inputTuples= new ArrayList<Tuple>();
        if(this.name=="r1"){
            Tuple tuple = new Tuple();
            tuple.type="link";
           // tuple.rule=null;
            tuple.attributes= new ArrayList<TupleAttribute>(){{
                add(tupleUnderQuery.attributes.get(0));
                add(tupleUnderQuery.attributes.get(1));
                add(tupleUnderQuery.attributes.get(2));
            }};

            inputTuples.add(tuple);

        }


        if(this.name=="r3"){
            Tuple tuple = new Tuple();
            tuple.type="link";
           // tuple.rule=null;
            tuple.attributes= new ArrayList<TupleAttribute>(){{
                add(tupleUnderQuery.attributes.get(0));
                add(tupleUnderQuery.attributes.get(1));
                add(tupleUnderQuery.attributes.get(2));
            }};

            inputTuples.add(tuple);
        }
    }

}
