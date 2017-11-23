package FinalRapidnetOutputAnalyis.Rules.CustomizedRules;

import FinalRapidnetOutputAnalyis.RuleParser;
import FinalRapidnetOutputAnalyis.Rules.Rule;
import FinalRapidnetOutputAnalyis.Rules.RulesWithApplications.BestPathRule1;
import FinalRapidnetOutputAnalyis.Tuples.Tuple;

import java.util.ArrayList;
import java.util.HashMap;

public class BestRouteRules {
    RuleParser ruleParser=  new RuleParser();

    public ArrayList<String> getAllTuple(){
        ruleParser.allTupleTypes.add("link");
        ruleParser.allTupleTypes.add("path");
        ruleParser.allTupleTypes.add("bestPath");
        ruleParser.allTupleTypes.add("packet");
        ruleParser.allTupleTypes.add("advertise");
        ruleParser.allTupleTypes.add("importfilter");
        return  ruleParser.allTupleTypes;
    }


}
