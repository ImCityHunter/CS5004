/**
 *  Line2D.java
 *  Author: yu2749luca, HoKang Yu
 */
package edu.northeastern.cs_5004;

public class Line2D implements Shape2D {
	private float x1; //x1 value
	private float y1; //y1 value
	private float x2; //x2 value
	private float y2; //y2 value
	private delta dX; //x1 & x2 relation
	private delta dY; //y1 & y2 relation
	

	/**
	 * 
	 * return the relationship between original x1 & x2
	 *
	 */
	public enum delta{
		p1Greater,
		p1Less,
		equal
	}
	
	/**
	 * Constructs a line with points (0, 0), (0, 0)
	 */
	Line2D(){
		this.x1=0;
		this.y1=0;
		this.x2=0;
		this.y2=0;
		this.dX = this.calculateDelta(x1, x2);
		this.dY = this.calculateDelta(y1, y2);
		}
	
	/**
	 * Constructs a line with points point1, point2
	 * @param point1
	 * @param point2
	 */
	Line2D(Point2D point1, Point2D point2){
		this.x1=point1.getX();
		this.y1=point1.getY();
		this.x2=point2.getX();
		this.y2=point2.getY();
		this.dX = this.calculateDelta(x1, x2);
		this.dY = this.calculateDelta(y1, y2);
	}
	
	/**
	 * Constructs a line with points (x1, y1), (x2, y2)
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	Line2D(float x1, float y1, float x2, float y2){
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
		this.dX = this.calculateDelta(x1, x2);
		this.dY = this.calculateDelta(y1, y2);
	}
	
	/**
	 * Constructs a line with points of a line
	 * @param line
	 */
	Line2D(Line2D line){
		this.x1 = line.getPoint1().getX();
		this.y1 = line.getPoint1().getY();
		this.x2 = line.getPoint2().getX();
		this.y2 = line.getPoint2().getY();
		this.dX = this.calculateDelta(x1, x2);
		this.dY = this.calculateDelta(y1, y2);
	}
	
	/**
	 * calculate the relationship between p1 & p2
	 * @return deltaX
	 */
	private delta calculateDelta (float p1,float p2){
		if(Math.abs(p1-p2) < (0.00000001) ||p1==p2) return delta.equal;
		else if(p1>p2) return delta.p1Greater;
		else if(p1<p2) return delta.p1Less;
		else throw new IllegalArgumentException(); //should not be here
	}
	

	
	/**
	 * Returns the first point of the line
	 * @return Point1
	 */
	public Point2D getPoint1() {
		return new Point2D(this.x1,this.y1);
	}
	
	/**
	 * Returns the second point of the line
	 * @return point2
	 */
	public Point2D getPoint2() {
		return new Point2D(this.x2,this.y2);
	}
	
	/**
	 * Returns the bounding box for the shape
	 */
	@Override
	public Rectangle2D getBounds() {
		float tempX = (this.x1<this.x2) ? this.x1:this.x2;
		float tempY = (this.y1>this.y2) ? this.y1:this.y2;
		float tempWidth = Math.abs(this.x1-this.x2);
		float tempHeight= Math.abs(this.y1-this.y2);
		return new Rectangle2D(tempX,tempY,tempWidth,tempHeight);
	}

	/**
	 * Sets the bounding box for the line
	 * keeping the relative positions of the two points.
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	@Override
	public void setBounds(float x, float y, float width, float height) {
		
		//default position
		x1=x2=x;
		y1=y2=y;
		
		if(dX==delta.p1Greater) x1+=width;
		else if(dX==delta.p1Less) x2+=width;
		
		if(dY==delta.p1Greater) y2 -= height;
		else if(dY==delta.p1Less) y1 -= height;

	}
	
	/**
	 * Sets the bounding box for the line with top-left point (x, y), 
	 * and dimensions (width, height), 
	 * keeping the relative positions of the two points.
	 * @param topLeft point
	 * @param width
	 * @param height
	 */
	@Override
	public void setBounds(Point2D topLeft, float width, float height) {
		setBounds(topLeft.getX(),topLeft.getY(),width,height);
		
	}

	/**
	 * Sets the bounding box for the line with top-left point (x, y), 
	 * and dimensions (width, height)
	 * @param bounds
	 */
	@Override
	public void setBounds(Rectangle2D bounds) {
		setBounds(bounds.getX(),bounds.getY(),bounds.getWidth(),bounds.getHeight());
		
	}
	
	/**
	 * Determines whether line contains the point
	 * @param point
	 * @return true/false if a point is on the line
	 */
	@Override
	public boolean containsPoint(Point2D point) {
		float epsilon = 0.000001f;
		Point2D point1 = new Point2D(this.getPoint1().getX(),this.getPoint1().getY());
		Point2D point2 = new Point2D(this.getPoint2().getX(),this.getPoint2().getY());
		float longestDistance = point1.getDistance(point2);
		float distance1 = point.getDistance(point1);
		float distance2 = point.getDistance(point2);
		return Math.abs(longestDistance-distance1-distance2) < epsilon; // set my own epsilon
	}

}
