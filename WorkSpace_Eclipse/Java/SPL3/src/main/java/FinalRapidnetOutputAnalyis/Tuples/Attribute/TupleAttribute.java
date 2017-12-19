package FinalRapidnetOutputAnalyis.Tuples.Attribute;

import java.util.ArrayList;

public class TupleAttribute implements Cloneable{

	public String tupleAttributeName;
	public String tupleAttributeValue;
	public boolean islist=false;
	public ArrayList<String>tupleAttributelistValue;

	@Override
	public Object clone() throws CloneNotSupportedException {

		return super.clone();
	}
}
