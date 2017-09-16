package RapidnetOutputAnalysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;


public class PathvectorApplicationAnalysis {

public static void main(String[] args) throws IOException {
	ArrayList<String> rapidnetEvents= new ArrayList<String>();
	ArrayList<String> pathVectorEvents= new ArrayList<String>();
	ArrayList<Event> events= new ArrayList<Event>();
	Set<String> set = new LinkedHashSet<String>();
		BufferedReader br = new BufferedReader(new FileReader(new File("/home/ali/Workspace/Java/SPL3/src/RapidnetOutputAnalysis/outputPath.txt")));
	    String line;
	    while ((line = br.readLine()) != null) {
	       // process the line.
	  
	    	if(line.contains("Pathvector")||line.contains("Application") ||line.contains("cxx") ||line.contains("Waf") || line.contains("'build'") || line.contains("Port set")){
	    		continue;
	    	}
	    	if(line.contains("triggered")){
	    		pathVectorEvents.add(line);
	    	}
	    	else{
	    		rapidnetEvents.add(line);
	    	}
	    }
	    /*for(String s:rapidnetEvents){
	    	System.out.println(s);
	    }*/
	  
	    for(String s: rapidnetEvents){
	    	s=s.replaceAll("[\\[\\]]", " ");
	    	String []splitS=s.split(" ");
	    	splitS[3]=splitS[3].replace(":", "");
	    	set.add(splitS[3]);
	    	Event e = new Event(splitS[4],splitS[1],splitS[3]);
	    	Tuple t= new Tuple();
	    	t.type=splitS[5];
	    	for(int i=6;i<splitS.length;i++){
	    		
	    			splitS[i]=splitS[i].replace("(", " ").replace(")", "").replace(":", " ");
	    			if(splitS[i].length()==0)continue;
	    			
	    			if(splitS[i].replace(" ","").equals("to") ||splitS[i].replace(" ","").equals("from")){
	    				i=i+1;
	    				e.destination=splitS[i];
	    				continue;
	    			}
	    			String []attributes_split=splitS[i].split(" ");
	    		
	    			TupleAttributes tupleAttribute= new TupleAttributes(attributes_split[1], attributes_split[2]);
	    			if(splitS[i]!=null && splitS[i]!="")t.attributes.add(tupleAttribute);
	    	}
	    	e.tuple=t;
	    	events.add(e);
	    	//String []splitS=s.split(" ");
	    }
	   for(String source :set){
	    	System.out.println("Source "+source);
	    	System.out.println();
	    	System.out.println();
	    	System.out.println();
	    	System.out.println();
		    for(Event e:events){
			    	if(e.source.compareTo(source)==0){
			    		System.out.println("Time " +e.time);
			    		System.out.println("Event " +e.eventname);
			    		System.out.println("Tuple " +e.tuple.type);
			    		System.out.printf("Tuple Attributes : " );
			    		for(TupleAttributes attr: e.tuple.attributes){
			    			System.out.printf(attr.tupleAttributeName+" ");
			    			System.out.printf(attr.tupleAttributeValueInString +" ");
			    		}
			    		System.out.println();
			    		
			    		
			    	}
		    }
	    }
	
	}


}
