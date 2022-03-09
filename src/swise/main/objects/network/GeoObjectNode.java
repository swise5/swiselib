package swise.main.objects.network;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.planargraph.Node;

import sim.util.geo.MasonGeometry;

public class GeoObjectNode extends GeoNode {

	Object object = null;
	
	public GeoObjectNode(Geometry g, long uniqueId) {
		super(g, uniqueId);
	}

	public GeoObjectNode(Geometry g, Object o, long uniqueId) {
		super(g, uniqueId);
		this.object = o;
	}

	public Object getObject(){ return object; }
}