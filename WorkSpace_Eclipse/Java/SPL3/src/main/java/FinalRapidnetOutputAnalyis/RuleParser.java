package FinalRapidnetOutputAnalyis;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class RuleParser {
    public ArrayList<String> baseTuples;//tuples derived according to available rules
    public ArrayList<String> derivedTuples;//tuples not derived according available rules
    public ArrayList<String> allTupleTypes;

    ArrayList<String> parsedRules;//rules parsed  rulehead(like r1) , tupletype (like path) these are ofc derived

    String ruleFilePath="";//location of source file
    BufferedReader bufferedruleFile;



    public RuleParser() {
        baseTuples= new ArrayList<String>();
        derivedTuples= new ArrayList<String>();
        allTupleTypes= new ArrayList<String>();
        parsedRules= new ArrayList<String>();

    }

    public RuleParser(String ruleFilePath) {
        this.ruleFilePath = ruleFilePath;
        baseTuples= new ArrayList<String>();
        derivedTuples= new ArrayList<String>();
        allTupleTypes= new ArrayList<String>();
        parsedRules= new ArrayList<String>();
    }

    public void setRuleFilePath(String ruleFilePath) {this.ruleFilePath = ruleFilePath; }



    public void setRuleBuffer(){
        try {
            bufferedruleFile = new BufferedReader(new FileReader(new File(ruleFilePath)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void parseRules(){

        String line="";
        try {
            while ((line = bufferedruleFile.readLine()) != null) {
                parseTuples(line);
                parseARule(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(allTupleTypes);
    }


    public void parseTuples(String logLine){
        String templine="";
        if(logLine.contains("materialize")){
            templine=logLine.replaceAll("[\\(\\)\\.]"," ").replace("materialize","").replace(" ","");;
            allTupleTypes.add(templine.split(",")[0]);//first parameter which indicates tuple type
        }
    }

    public void parseARule(String ruleLine){
        String templine="";
        if(ruleLine.contains(":-")){// start of rule
            templine+=ruleLine;
            while (true && ruleLine!=null){
                if(ruleLine.contains("."))break;
                try {
                    ruleLine = bufferedruleFile.readLine();
                    if(ruleLine!=null)templine+=ruleLine;
                } catch (IOException e) {
                    e.printStackTrace();
                };
            }//end of while loop;
            //now templine contains the entire rule in a line
            parsedRules.add(templine.replace(".",""));
            System.out.println(templine);

        }

    }
}
