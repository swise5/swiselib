package swise.objects.network;

import java.util.ArrayList;

import sim.field.network.Edge;
import sim.util.geo.MasonGeometry;

public class ListEdge extends Edge {

	private static final long serialVersionUID = 1L;
	ArrayList <Object> elements = new ArrayList <Object> ();
	double length = 1.;
	
	public ListEdge(Edge e) {
		super(e);
	}

	public boolean equals(Object o){
		if(! (o instanceof ListEdge))
			return false;
		ListEdge l = (ListEdge)o;

		if(l.getInfo().equals(this.info))
			return true;

		return false;
	}
	
	public int hashCode(){
		//return (from().toString() + to().toString()).hashCode();
		return from().hashCode() + to().hashCode();
	}
	
	public ListEdge(Edge e, double length) {
		super(e);
		this.length = length;
	}

	public void addElement(Object o){
		elements.add(o);
	}
	
	public Object removeElement(Object o){
		return elements.remove(o);
	}
	
	public double lengthPerElement(){
		return length / (double) Math.max(1, elements.size());
	}
	
	public double length(){
		return length;
	}
}