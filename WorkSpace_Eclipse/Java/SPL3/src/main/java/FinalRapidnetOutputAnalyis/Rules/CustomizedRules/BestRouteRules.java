package FinalRapidnetOutputAnalyis.Rules.CustomizedRules;

import FinalRapidnetOutputAnalyis.RuleParser;
import FinalRapidnetOutputAnalyis.Rules.Rule;
import FinalRapidnetOutputAnalyis.Rules.RulesWithApplications.BestPathRule1;
import FinalRapidnetOutputAnalyis.Tuples.Tuple;

import java.util.ArrayList;
import java.util.HashMap;

public class BestRouteRules {
    RuleParser ruleParser=  new RuleParser();
    public ArrayList<BestRouteRules> rules = new ArrayList<BestRouteRules>();

    public ArrayList<String> getBaseTuple(){
        ruleParser.baseTuples.add("link");
        return  ruleParser.baseTuples;
    }


    public ArrayList<String> getDerivedTuple(){
        ruleParser.derivedTuples.add("path");
        ruleParser.derivedTuples.add("bestPath");
        return  ruleParser.derivedTuples;
    }

    public ArrayList<String> getAllTuple(){
        ruleParser.allTupleTypes.add("link");
        ruleParser.allTupleTypes.add("path");
        ruleParser.allTupleTypes.add("bestPath");
        ruleParser.allTupleTypes.add("packet");
        return  ruleParser.allTupleTypes;
    }

    public HashMap<String,BestPathRule1> getrules(){
        HashMap<String,BestPathRule1> rules= new HashMap<String, BestPathRule1>();
        BestPathRule1 r1= new BestPathRule1("r1");
        BestPathRule1 r2= new BestPathRule1("r2");
        BestPathRule1 r3= new BestPathRule1("r3");
        rules.put("r1",r1);
        rules.put("r2",r2);
        rules.put("r3",r3);
        return  rules;
    }



}
