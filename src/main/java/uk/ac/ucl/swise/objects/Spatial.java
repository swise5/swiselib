package uk.ac.ucl.swise.objects;

import com.vividsolutions.jts.geom.Geometry;

public interface Spatial {
	public Geometry getGeometry();
}