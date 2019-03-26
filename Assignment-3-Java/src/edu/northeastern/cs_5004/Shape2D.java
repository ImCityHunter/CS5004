/**
 *  Shape2D.java
 *  Author: yu2749luca, HoKang Yu
 */
package edu.northeastern.cs_5004;

public interface Shape2D {
	/**
	 * Returns the bounding box for the shape

	 */
	Rectangle2D getBounds();
	
	/**
	 * Sets the bounding box for the shape with 
	 * top-left point (x, y), 
	 * and dimensions (width, height)
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	void setBounds(float x, float y, float width, float height);
	
	/**
	 * Sets the bounding box for the shape with top-left point (x, y), 
	 * and dimensions (width, height)
	 * @param topLeft
	 * @param width
	 * @param height
	 */
	void setBounds(Point2D topLeft, float width, float height);
	
	/**
	 * Sets the bounding box for the shape
	 * @param bounds
	 */
	void setBounds(Rectangle2D bounds);
	
	/**
	 * Determines whether shape contains the point
	 * @param point
	 * @return true/false
	 */
	boolean containsPoint(Point2D point);
}
