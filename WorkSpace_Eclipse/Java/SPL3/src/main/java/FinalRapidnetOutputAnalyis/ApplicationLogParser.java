package FinalRapidnetOutputAnalyis;

import FinalRapidnetOutputAnalyis.Rules.CustomizedRules.BestRouteRules;
import FinalRapidnetOutputAnalyis.Tuples.Attribute.TupleAttribute;
import FinalRapidnetOutputAnalyis.Tuples.Tuple;
import RapidnetOutputAnalysis.TupleAttributes;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ApplicationLogParser {
    String logFilePath="";
    BestRouteRules rules= new BestRouteRules();
    BufferedReader bufferedLogFile;
    HashMap<String,Integer> derivationManager= new HashMap<String, Integer>();
    // keeps <nodeOrigin,<TupleType,DerivationCounter>>

    HashMap<String,ArrayList<Tuple>> nodesWithTupleLogs;


    HashMap<String,String> applyRule;//for applying rule as appeared in log file

    public ApplicationLogParser() {
        applyRule= new HashMap<String, String>();
    }

    public ApplicationLogParser(String logFilePath,String ruleFilePath) {
        this.logFilePath = logFilePath;

        applyRule= new HashMap<String, String>();
        derivationManager= new HashMap<String, Integer>();
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public void setLogFilebuffer(){
        try {
            bufferedLogFile = new BufferedReader(new FileReader(new File(logFilePath)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public  ArrayList<String> parseRapidnetLog(){
        ArrayList<String> rapidnetLog= new ArrayList<String>();

        String line;

        try {
            while ((line = bufferedLogFile.readLine()) != null) {
                // process the line.
                //System.out.println(line);
                if(line.contains("Pathvector")||line.contains("Application") ||line.contains("cxx") ||line.contains("Waf") || line.contains("'build'") || line.contains("Port set") || line.contains("triggered")){
                    continue;
                }
                else{
                    rapidnetLog.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rapidnetLog;
    }

    public void parseTuplefromLog(){


    }


    public HashMap<String,ArrayList<String>> sourcewiseLog(ArrayList<String>logsFromRapidnet){
        HashMap<String,ArrayList<String>> logs= new HashMap<String,ArrayList<String>>();
        for(String log : logsFromRapidnet){
            String []splittedlogline=log.split(" ");
            splittedlogline[1]=splittedlogline[1].replace(":","");
            if(logs.keySet().contains(splittedlogline[1])){
                ArrayList<String> updated=logs.get(splittedlogline[1]);
                updated.add(log);
                logs.put(splittedlogline[1],updated);
            }
            else{
                ArrayList<String> newArray= new ArrayList<String>();
                newArray.add(log);
                logs.put(splittedlogline[1],newArray);
            }
        }

        return logs;
    }





    public LogFormat getLogFormat(String logline){
        int derived=isderive(logline);
        String time=getTupleTime(logline);
        String node=getLogSource(logline);
        int derivationCounter=0;

        Tuple t= new Tuple();
        t.type=getTupleType(logline);
        t.attributes=getTupleAttributes(logline);
        t.tupleOrigin=t.attributes.get(0).tupleAttributeValue;
        if(isSend(logline))t.tupleDestination=getReceiver(logline);
        if(isReceive(logline))t.tupleSource=getSender(logline);

        String rule=getRule(t);


        if(derived==1) {
            if (!derivationManager.keySet().contains(t.tupleOrigin + t.type +t.attributes.get(0).tupleAttributeValue+t.attributes.get(1).tupleAttributeValue)) {
                derivationManager.put(t.tupleOrigin + t.type+t.attributes.get(0).tupleAttributeValue+t.attributes.get(1).tupleAttributeValue, 1);
                derivationCounter = 1;
            } else {
                derivationCounter = derivationManager.get(t.tupleOrigin + t.type+t.attributes.get(0).tupleAttributeValue+t.attributes.get(1).tupleAttributeValue) + 1;
                derivationManager.put(t.tupleOrigin + t.type, derivationCounter);
            }
        }

        LogFormat logFormat= new LogFormat(t,derived,node,time,rule,derivationCounter);
        return  logFormat;
        /*
        System.out.println(getTupleTime(logline));
        System.out.println(isderive(logline));
        System.out.println(getTupleType(logline));
        ArrayList<TupleAttribute> attributes= getTupleAttributes(logline);
        for (TupleAttribute attr: attributes){
            System.out.println(attr.tupleAttributeName);
            if(!attr.islist) System.out.println(attr.tupleAttributeValue);
            else{
                for(String value:attr.tupleAttributelistValue){
                    System.out.println(value);
                }
            }
        }*/

    }


    public ArrayList<LogFormat> getAllFormattedLog(ArrayList<String> logs){
        ArrayList<LogFormat> formattedLogs= new ArrayList<LogFormat>();
        for(String logline:logs){
            LogFormat format=getLogFormat(logline);

            if(format.rule!=null && format.rule.compareTo("r2")==0 && format.t.tupleDestination!=null){
                LogFormat tempformat=new LogFormat(format.t,1,format.node,format.time,format.rule,format.derivationCounter);
                formattedLogs.add(tempformat);//addding appear for rule2

            }

            formattedLogs.add(format);

        }

        return formattedLogs;
    }

    //end of parsing

    //added
    //needed for easy parsing of log files
    public String getTupleTime(String log){
        String []splitlog= log.split(" ");
        return  splitlog[0].replace("[","").replace("]","").replaceAll("\\s+","");
    }


    public String getLogSource(String log){
        String []splitlog= log.split(" ");
        return  splitlog[1].replace(":","").replaceAll("\\s+","");


    }

    public String getRule(Tuple t){
        if(t.type=="path"){
            if(t.attributes.get(3).tupleAttributelistValue.size()==2) return "r1";
            else return "r2";
        }
        else if(t.type=="bestPath"){
            return "r3";
        }
        else {
            return  null;
        }
    }


    public int isderive(String log){
        //+-t
       String []splitlog= log.split(" ");
       if(splitlog[2].contains("Insert")) return  1;
       else if(splitlog[2].contains("Delet")) return  0;
       else return -1;
    }



    public String getTupleType(String log){

        for (String type:rules.getAllTuple()) {
            if(log.contains(type))return type;

        }
        return  null;
    }

    public ArrayList<TupleAttribute> getTupleAttributes(String log){
        ArrayList<TupleAttribute> attributes= new ArrayList<TupleAttribute>();
        String []logsplit=log.split(" ");
        for(String splitted: logsplit){
            if(splitted.contains("attr")){
                String []nameAndVal=splitted.split("[(]")[1].replace(")","").split(":");
                TupleAttribute attribute= new TupleAttribute();
                attribute.tupleAttributeName=nameAndVal[0];
                if(nameAndVal[0].compareTo("list")==0)attribute.islist=true;
                if(attribute.islist){
                   String attrsString= splitted.split("[(]")[2].replace(")","");
                   String []attrArray=attrsString.split(",");
                    attribute.tupleAttributeName=attrArray[0].split(":")[0];
                    attribute.tupleAttributelistValue= new ArrayList<String>();
                    for (String attr:attrArray) {
                        attribute.tupleAttributelistValue.add(attr.split(":")[1]);
                    }

                }else{
                    attribute.tupleAttributeValue=nameAndVal[1];
                }
                attributes.add(attribute);
            };
        }
        return  attributes;
    }

    public boolean isSend(String logline){
        if(logline.contains("Sending"))return  true;
        else return false;

    }

    public boolean isReceive(String logline){
        if(logline.contains("Received"))return  true;
        else return false;

    }

    public String getReceiver(String logLine){
        return logLine.split("to")[1].replaceAll("\\s+","");
    }

    public String getSender(String logLine){
        return logLine.split("from")[1].replaceAll("\\s+","");
    }

    public boolean willApplyRules(String log){
        //will store rule when encounter and match next line with rule



        return  false;
    }

    //end of needed for easy parsing of log files



}
