package FinalRapidnetOutputAnalyis.Tuples;

import FinalRapidnetOutputAnalyis.Tuples.Attribute.TupleAttribute;

import java.util.ArrayList;

public class Tuple {
	public String type;
	public String timeOccurred;
	public int derivationCounter;
	public String tupleOrigin;
	public String tupleDestination;
	public String tupleSource;
	//public boolean isderived;
	public ArrayList<TupleAttribute> attributes= new ArrayList<TupleAttribute>();

	public Tuple(){

	}
	public Tuple(String type, String timeOccurred) {
		this.type = type;
		this.timeOccurred = timeOccurred;
	}
}
