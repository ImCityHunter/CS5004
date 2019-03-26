/**
 *  Rectangle2D.java
 *  Author: yu2749luca, HoKang Yu
 */
package edu.northeastern.cs_5004;

public class Rectangle2D implements Shape2D  {
	private float x; //topLeft X coordinate
	private float y; //topLeft Y coordinate
	private float width; //width of box
	private float height;//height of box
	
	/**
	 * Constructs a rectangle at (0, 0) with dimensions (0, 0)
	 */
	Rectangle2D() {
		setBounds(0,0,0,0);
	}
	
	/**
	 * Constructs a rectangle with top-left point (x, y), and dimensions (width, height)
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	Rectangle2D(float x, float y, float width, float height) {
		setBounds(x,y,width,height);
	}
	
	/**
	 * Constructs a rectangle with top-left point, and dimensions (width, height)
	 * @param topLeft
	 * @param width
	 * @param height
	 */
	Rectangle2D(Point2D topLeft, float width, float height) {
		setBounds(topLeft.getX(),topLeft.getY(),width,height);
	}
	
	/**
	 * Constructs a rectangle with location and dimensions of a rectangle
	 * @param rect
	 */
	Rectangle2D(Rectangle2D rect	) {
		setBounds(rect.getX(),rect.getY(),rect.getWidth(),rect.getHeight());
	}
	
	/**
	 * Returns the X of the top left corner of the rectangle
	 * @return X
	 */
	float getX() {
		return this.x;
	}
	
	/**
	 * Returns the Y of the top left corner of the rectangle
	 * @return Y
	 */
	float getY() {
		return this.y;
	}
	
	/**
	 * Returns the width of the rectangle
	 * @return width
	 */
	float getWidth() {
		return this.width;
	}
	
	/**
	 * Returns the height of the rectangle
	 * @return height
	 */
	float getHeight() {
		return this.height;
	}
	
	/**
	 * Returns the bounding box for the rectangle
	 * @return boundingbox
	 */
	@Override
	public Rectangle2D getBounds() {
		return new Rectangle2D(this.x,this.y,this.width,this.height);
	}

	/**
	 * Sets the bounding box for the rectangle with top-left point (x, y), 
	 * and dimensions (width, height)
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	@Override
	public void setBounds(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height= height;
		
	}
	
	/**
	 * Sets the bounding box for the rectangle with top-left point (x, y),
	 * and dimensions (width, height)
	 * @param topLeft point
	 * @param width
	 * @param height
	 */
	@Override
	public void setBounds(Point2D topLeft, float width, float height) {
		setBounds(topLeft.getX(),topLeft.getY(),width,height);
		// TODO Auto-generated method stub
		
	}

	/**
	 * Sets the bounding box for the rectangle
	 * @param bounds
	 */
	@Override
	public void setBounds(Rectangle2D bounds) {
		setBounds(bounds.getX(),bounds.getY(),bounds.getWidth(),bounds.getHeight());
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Determines whether shape contains the point
	 * @param point
	 * @return true/false if a point is within the Rectangle
	 */
	@Override
	public boolean containsPoint(Point2D point) {
		float farRight = this.x+this.width;
		float farBottom = this.y - this.height;
		float epsilon = 0.00001f;
		boolean xLower = x - point.getX() < epsilon;
		boolean xUpper = point.getX()-farRight < epsilon;
		boolean yLower = farBottom - point.getY()  < epsilon;
		boolean yUpper = point.getY()-y < epsilon;
		return xLower&&xUpper&&yLower&&yUpper;
	}

}
