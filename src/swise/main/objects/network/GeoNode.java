package swise.main.objects.network;

import sim.util.geo.MasonGeometry;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.planargraph.Node;

/**
 * a structure used by the road network to inform Agent movement
 * 
 * @author swise
 *
 */

public class GeoNode extends MasonGeometry {

//	MasonGeometry mg;
	Node n;
	int myHashcode = -1;
	long uniqueId = -1;
	
	public GeoNode(Geometry g, long uniqueId) {
		super();
		this.geometry = g;
		n = new Node(g.getCoordinate());
		myHashcode = (n.toString() + geometry.toString()).hashCode();
		this.uniqueId = uniqueId;
	}
	
	public Node getNode(){ return n; }

	public boolean equals(Object o){
		if(!(o instanceof GeoNode)) return false;
		return ((GeoNode)o).uniqueId == uniqueId;
	}
	
	public int hashCode(){
		return myHashcode;
	}	

}