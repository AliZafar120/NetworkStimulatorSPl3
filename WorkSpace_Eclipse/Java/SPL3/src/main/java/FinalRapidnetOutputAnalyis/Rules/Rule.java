package FinalRapidnetOutputAnalyis.Rules;

import FinalRapidnetOutputAnalyis.Tuples.Tuple;
import RapidnetOutputAnalysis.Event;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Rule {
    public String ruleString;
    public String name;
    public String verifier;
    public String derivedTuple;
    public ArrayList<String> derivedTupleVars;
    public ArrayList<Tuple> inputTuples;
    public ArrayList<String> terms;

    public Rule(String rule){
        this.ruleString=rule;
        extractRulename();
        extractDerivedTuple();
        extractTerms();
    }

    public void extractRulename(){
        this.name=ruleString.split(" ")[0];// rule name is first word of the rule
    }


    public void extractTerms(){
        String []allterms=ruleString.split(":-")[1].split(","); //extracting terms as separated by commas in
        // regex
        terms= new ArrayList<String>(Arrays.asList(allterms));
    }

    public void extractDerivedTuple(){
        String allheads=ruleString.split(":-")[0];
        String []headsAndDerivedTuplename=allheads.split("[(]")[0].split(" ");
        derivedTuple= headsAndDerivedTuplename[headsAndDerivedTuplename.length-1];
        String []derivedVars=allheads.split("[(]")[1].replace(")","").split(",");
        derivedTupleVars=new ArrayList<String>(Arrays.asList(derivedVars));
    }


}
