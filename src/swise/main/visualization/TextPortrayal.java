package swise.main.visualization;

import com.vividsolutions.jts.geom.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import sim.display.GUIState;
import sim.portrayal.*;
import sim.portrayal.geo.GeomInfo2D;
import sim.portrayal.geo.GeomPortrayal;
import sim.portrayal.inspector.TabbedInspector;
import sim.util.Properties;
import sim.util.geo.MasonGeometry;
import sim.util.gui.ColorMap;

/**
 * Portrays the given text in the centroid of the polygon
 * 
 * @author swise
 *
 */
public class TextPortrayal extends GeomPortrayal
{

	private static final long serialVersionUID = 1L;
	String attribute = null;
	int fontSize = 12;
	Font myFont;
	Color myColor;
	
	/** How to paint each object */
	public Paint outlinePaint;

	/** Default constructor creates gray text with font 12 */
	public TextPortrayal() {
		this("name", Color.GRAY, 12);
	}

	public TextPortrayal(Color color) {
		this("name", color, 12);
	}

	public TextPortrayal(String attributeName, Color color, int scale) {
		this.attribute = attributeName;
		myColor = color;
		this.fontSize = scale;
		myFont = new Font("SansSerif", 0, fontSize);  // keep it around for efficiency

	}    

	/**
	 * Draw a JTS geometry object. The JTS geometries are converted to Java
	 * general path objects, which are then drawn using the native Graphics2D
	 * methods.
	 */
    @Override
	public void draw(Object object, Graphics2D graphics, DrawInfo2D info)
	{
    	
		GeomInfo2D gInfo;
		if (info instanceof GeomInfo2D)
        {
            gInfo = (GeomInfo2D) info;
        }
		else
        {
            gInfo = new GeomInfo2D(info, new AffineTransform());
        }
		
		MasonGeometry gm = (MasonGeometry) object;
		Point myCentroid = gm.geometry.getCentroid();
		
		// don't have cached shape or the transform changed, so need to build
		// the shape
		if ((gm.isMovable) || (gm.shape == null) || (!gm.transform.equals(gInfo.transform)))
		{
			gm.transform.setTransform(gInfo.transform);
				double offset = 3 * scale / 2.0; // used to center point
				Ellipse2D.Double ellipse = new Ellipse2D.Double(myCentroid.getX() - offset, myCentroid.getY() - offset,
						3 * scale, 3 * scale);

				GeneralPath path = (GeneralPath) (new GeneralPath(ellipse).createTransformedShape(gInfo.transform));
				gm.shape = path;
		}		
		
		try {
			String myString = gm.getStringAttribute(attribute);
			graphics.setFont(myFont);
			graphics.setColor(myColor);
			
	        graphics.drawString(myString, (int) gm.shape.getCurrentPoint().getX(), (int) gm.shape.getCurrentPoint().getY());

//			System.out.println(p.toString() + "\t" + myString);
		} catch (Exception e){
			e.printStackTrace();
		}
	}



	/**
	 * Helper function for drawing a JTS polygon.
	 * 
	 * <p>
	 * Polygons have two sets of coordinates; one for the outer ring, and
	 * optionally another for internal ring coordinates. Draw the outer ring
	 * first, and then draw each internal ring, if they exist.
	 * */
	GeneralPath drawPolygon(Polygon polygon, GeomInfo2D info, boolean fill)
	{
		GeneralPath p = drawGeometry(polygon.getExteriorRing(), info, fill);

		for (int i = 0; i < polygon.getNumInteriorRing(); i++)
		{ // fill for internal rings will always be false as they are literally
			// "holes" in the polygon
			p.append(drawGeometry(polygon.getInteriorRingN(i), info, false), false);
		}
		return p;
	}

	/**
	 * Helper function to draw a JTS geometry object. The coordinates of the JTS
	 * geometry are converted to a native Java GeneralPath which is used to draw
	 * the object.
	 */
	GeneralPath drawGeometry(Geometry geom, GeomInfo2D info, boolean fill)
	{
		Coordinate coords[] = geom.getCoordinates();
		GeneralPath path = new GeneralPath(GeneralPath.WIND_NON_ZERO, coords.length);
		path.moveTo((float) coords[0].x, (float) coords[0].y);

		for (int i = 1; i < coords.length; i++)
        {
            path.lineTo((float) coords[i].x, (float) coords[i].y);
        }

		path.transform(info.transform);

		return path;
	}


}
