/**
 *  Circle2D.java
 *  Author: yu2749luca, HoKang Yu
 */
package edu.northeastern.cs_5004;

public class Circle2D implements Shape2D {
	private float x; //topLeft X coordinate
	private float y; //topLeft Y coordinate
	private float width; //width of circle bounding box
	private float height; //height of circle bounding box
	
	/**
	 * Constructs a circle at (0, 0) with dimensions (0, 0)
	 */
	Circle2D(){
		setBounds(0,0,0,0);
	}
	
	/**
	 * Constructs a circle with top-left point (x, y), and dimensions (width, height)
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	Circle2D(float x, float y, float width, float height){
		setBounds(x,y,width,height);
	}
	/**
	 * Constructs a circle with top-left point,
	 * and dimensions (width, height)
	 * @param topLeft
	 * @param width
	 * @param height
	 */
	Circle2D(Point2D topLeft, float width, float height){
		setBounds(topLeft.getX(),topLeft.getY(),width,height);
	}
	
	/**
	 * Constructs a circle with location and dimensions of a rectangle
	 * @param circle
	 */
	Circle2D(Circle2D circle){
		setBounds(circle.getX(),circle.getY(),circle.getWidth(),circle.getHeight());
	}
	
	/**
	 * Returns the X of the top left corner of the circle
	 * @return X
	 */
	float getX() {
		return this.x;
	}
	
	/**
	 * Returns the Y of the top left corner of the circle
	 * @return Y
	 */
	float getY() {
		return this.y;
	}
	
	/**
	 * Returns the width of the circle
	 * @return width
	 */
	float getWidth() {
		return this.width;
	}
	
	/**
	 * Returns the height of the circle
	 * @return height
	 */
	float getHeight() {
		return this.height;
	}
	
	/**
	 * Returns the bounding box for the circle
	 * @return a new bounding box
	 */
	@Override
	public Rectangle2D getBounds() {
		
		return new Rectangle2D(this.x,this.y,this.width,this.height);
	}
	
	/**
	 * Sets the bounding box for the circle with top-left point (x, y), and dimensions (width, height)
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	@Override
	public void setBounds(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = Math.abs(width);
		this.height= Math.abs(height);
		
	}
	
	/**
	 * Sets the bounding box for the circle with top-left point (x, y),
	 * and dimensions (width, height)
	 * @param topLeft Point
	 * @param width
	 * @param height
	 */
	@Override
	
	public void setBounds(Point2D topLeft, float width, float height) {
		setBounds(topLeft.getX(),topLeft.getY(),width,height);
		
	}
	
	/**
	 * Sets the bounding box for the circle
	 * @param bounds
	 */
	@Override
	public void setBounds(Rectangle2D bounds) {
		setBounds(bounds.getX(),bounds.getY(),bounds.getWidth(),bounds.getHeight());
		
	}
	
	/**
	 * formula (x-h)^2/a^2 + (y-k)^2/b^2 <= 1
	 * Determines whether shape contains the point
	 * @param point
	 * @return true/false if a point is within (inclusive) circle/ellipse
	 */
	@Override
	public boolean containsPoint(Point2D point) {
		
		float a = this.width/2;
		float b = this.height/2;
		float centerX = this.x + a;
		float centerY = this.y - b;
		float deltaX = centerX - point.getX();
		float deltaY = centerY - point.getY();
		float epsilon = 0.01f;
		if(width<epsilon) {
			float yLower = y-height;
			float part1 = yLower - point.getY();
			float part2 = point.getY()-y;
			float part3 = Math.abs(point.getX()-x);
			return part1<epsilon && part2 < epsilon && part3 < epsilon;
		}
		else if(height<epsilon) {
			float xUpper = x+width;
			float part1 = point.getX()-xUpper;
			float part2 = x - point.getX();
			float part3 = Math.abs(point.getY()-y);
			return part1 < epsilon && part2 < epsilon && part3 <epsilon;
		}
		else if(width<epsilon && height<epsilon) {
			float part1 = Math.abs(x - point.getX());
			float part2 = Math.abs(y - point.getY());
			return part1<epsilon && part2 <epsilon;
		}
		
		else {
			float formula = (deltaX*deltaX)/(a*a) + (deltaY*deltaY)/(b*b) - 1;
			return formula < epsilon;
		}
		}

}
