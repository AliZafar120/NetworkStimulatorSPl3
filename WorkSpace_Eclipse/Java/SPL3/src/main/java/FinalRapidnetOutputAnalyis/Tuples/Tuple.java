package FinalRapidnetOutputAnalyis.Tuples;

import FinalRapidnetOutputAnalyis.Tuples.Attribute.TupleAttribute;

import java.util.ArrayList;

public class Tuple {
	public String type;
	public String tupleOrigin;
	public String tupleDestination;
	public String tupleSource;
	//public boolean isderived;
	public ArrayList<TupleAttribute> attributes= new ArrayList<TupleAttribute>();

	public Tuple(){

	}
	public Tuple(String type) {
		this.type = type;

	}


	public boolean equals(Tuple anotherTuple) {

		//tuple name
		if (this.type.compareTo(anotherTuple.type)!=0)
			return  false;//if tuple type does not match
		//tuple origin
		if((tupleOrigin==null && anotherTuple.tupleOrigin!=null) || (tupleOrigin!=null && anotherTuple.tupleOrigin==null))
			return false;//if either of the rule is null
		if(tupleOrigin!=null && anotherTuple.tupleOrigin!=null && tupleOrigin.compareTo(anotherTuple.tupleOrigin)!=0)
			return false;//if both are not null and their value don't match

		//tuple destination
		if((tupleDestination==null && anotherTuple.tupleDestination!=null) || (tupleDestination!=null && anotherTuple.tupleDestination==null))
			return false;//if either of the rule is null
		if(tupleDestination!=null && anotherTuple.tupleDestination!=null && tupleDestination.compareTo(anotherTuple.tupleDestination)!=0)
			return false;//if both are not null and their value don't match

		//tuple source

		if((tupleSource==null && anotherTuple.tupleSource!=null) || (tupleSource!=null && anotherTuple.tupleSource==null))
			return false;//if either of the rule is null
		if(tupleSource!=null && anotherTuple.tupleSource!=null && tupleSource.compareTo(anotherTuple.tupleSource)!=0)
			return false;//if both are not null and their value don't match


		//attributes
		if(attributes.size()!=anotherTuple.attributes.size())
			return false;//if attributes size does not match
		for (int i=0;i<attributes.size();i++) {
			if(attributes.get(i).tupleAttributeName.compareTo(anotherTuple.attributes.get(i).tupleAttributeName)!=0)
				return false;
			if(attributes.get(i).islist!=anotherTuple.attributes.get(i).islist)
				return false;
			if(attributes.get(i).islist==true && attributes.size()!=anotherTuple.attributes.size())
				return false;
			if(attributes.get(i).islist==true) {
				for (int j = 0; j < attributes.get(i).tupleAttributelistValue.size(); j++) {
					if (attributes.get(i).tupleAttributelistValue.get(j).compareTo(anotherTuple.attributes.get(i).tupleAttributelistValue.get(j)) != 0)
						return false;
				}
			}
		}


		return  true;
	}
}
