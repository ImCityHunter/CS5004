/**
 *  Point2D.java
 *  Author: yu2749luca, HoKang Yu
 */
package edu.northeastern.cs_5004;

/* Point2D functions
 * @author yu2749luca, HoKang Yu
 */

/**
 * This class implements a Point2D 
 */
public class Point2D {
	
	private float x; //x coordinate
	private float y; //y coordinate
    

	/**
	 * Constructor initializes this.x and this.y to 0.0
	 */
	Point2D (){
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * Constructor initializes instance this.x and this.y to x, and y
	 */
	Point2D (float x, float y){
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructor intializes x and y to x and y of point (copy constructor)
	 */
	Point2D (Point2D point){
		this.x = point.x;
		this.y = point.y;
		
	}
	
	public void setPoint(float x,float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns the x coordinate of the point
	 */
	float getX () {return x;}

	/**
	 * Returns the y coordinate of the point
	 */
	float getY () {return y;}
	
	/**
	 * Returns the distance between a point and this point
	 * @param point
	 */

    float getDistance(Point2D point) {
    	
    	float result=0;
    	
    	float x2 = x-point.x;
    	float y2 = y-point.y;
    	
    	result = (float) Math.sqrt((double)x2*x2+y2*y2);
    	return result;
    }
    
	/**
	 * Returns Point2D representing sum of this point and another point†. 
	 * The sum of two points is another point whose x and y values 
	 * are the sum of the two point x and y values.
	 * @param point
	 * @return point
	 */   
    Point2D plus (Point2D point) {
    	return new Point2D(x+point.x,y+point.y);
    }
 
	/**
	 * Returns Point2D representing difference of this point and another point 
	 * The difference of two points is another point 
	 * whose x and y values are the difference of the two point x and y values.
	 * @param point
	 * @return point
	 */
    Point2D minus (Point2D point) {
    	return new Point2D(x-point.x,y-point.y);
    	
    }
 
	/**
	 * Returns Point2D representing a point with then negative of this point's coordinates
	 * @return resulted point
	 */
    Point2D minus() {
    	
    	float x2 = -this.x;
    	float y2 = -this.y;
    	if(this.x == 0) x2 = 0;
    	if(this.y == 0) y2 = 0;
    	Point2D result = new Point2D(x2,y2);
    	return result;

    }
  
	/**
	 * Returns true if 'obj' is a Point2D that is equal to this point. 
	 * Note that direct comparison of floats does not work
	 * correctly for float values of infinity and NaN (not-a-number). 
	 * Try boxing x and y values as Float and calling equals() for each with other x and y values.
	 * @param true/false two points are the same
	 **/
    boolean equal (Object obj) {
    	if(obj instanceof Point2D) {
    		Point2D point = (Point2D) obj;
    		
    		return (Math.abs(x-point.x)<0.000001) && (Math.abs(y-point.y)<0.000001);
    	}
    	else return false;
    }
    /**
     * Return the hashcode for this point
     *
     * @return the hash code for this point
     */
    @Override
    public int hashCode() {
    	return java.util.Objects.hash(x, y);
    }


}
