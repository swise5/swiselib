package swise.main.objects.network;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import sim.field.network.Edge;
import sim.util.geo.MasonGeometry;
import swise.main.agents.MobileAgent;

public class ListEdge extends Edge {

	private static final long serialVersionUID = 1L;
	ArrayList <MobileAgent> elements = new ArrayList <MobileAgent> ();
	double length = 1.;
	double width = 1.;
	
	public ListEdge(Edge e) {
		super(e);
	}
	
	public ListEdge(Edge e, double length) {
		this(e);
		this.length = length;
	}

	public ListEdge(Edge e, double length, double width){
		this(e, length);
		this.width = width;
	}

	public boolean equals(Object o){
		if(! (o instanceof ListEdge))
			return false;
		ListEdge l = (ListEdge)o;

		if(o == this)
			return true;
		
		MasonGeometry mg = (MasonGeometry) l.getInfo();
		MasonGeometry myMG = (MasonGeometry) this.info;
		if(mg == myMG || mg.geometry.equals(myMG.geometry))
			return true;

		return false;
	}
	
	public int hashCode(){
		//return (from().toString() + to().toString()).hashCode();
		return from().hashCode() + to().hashCode() + info.hashCode();
	}
	
	public void addElement(MobileAgent o){
		if(o.getDirection() == -1)
			elements.add(o);
		else
			elements.add(0, o);
	}
	
	public Object removeElement(MobileAgent o){
		Object result = elements.remove(o);
		resort();
		return result;
	}
	
	public void resort(){
		Collections.sort(elements, new Comparator<MobileAgent>() {

			@Override
			public int compare(MobileAgent o1, MobileAgent o2) {
				if(o1 == o2) return 0;
				if(o1.getDirection() != o2.getDirection()){
					if(o1.getDirection() == 1) return 1;
					return -1;
				}
				else{
					if(o1.getCurrentIndex() == o2.getCurrentIndex())
						return 0;
					else if(Math.abs(o1.getCurrentIndex()) > Math.abs(o2.getCurrentIndex())) 
						return 1;
					return -1;
				}
			}
			
		});
	}
	
	public int numElementsOnListEdge(){ return elements.size();}
	
	public double lengthPerElement(){
		return length * width / (double) Math.max(1, elements.size());
	}
	
	public int returnMyIndex(MobileAgent m){
		return elements.indexOf(m);
	}

	public double length(){
		return length;
	}
	
	public void setWidth(double w){
		width = w;
	}
	
	public double width(){ return width; }
}