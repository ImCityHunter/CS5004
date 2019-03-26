/*
 * Point2D.cpp
 *
 *  Created on: Jan 14, 2019
 *      Author: yu2749luca, Hokang Yu
 */

#include "Point2D.h"
#include <math.h>

namespace CS_5004{
	/**
	 *  Constructor initializes instance this.x, and this.y to x, and y
	 */
	Point2D :: Point2D (float x, float y) : x(x),y(y){}

	/**
	 *  Constructor intializes x and y to x and y of point (copy constructor)
	 *
	 */
	Point2D :: Point2D (const Point2D &point) : x(point.x),y(point.y){};


	/**
	 * @returns the x coordinate of the point
	 *
	 */

	float Point2D :: getX () const {
		return x;
	}

	/**
	 * @returns the y coordinate of the point
	 *
	 */

	float Point2D :: getY () const {
		return y;
	}


	/**
	 *  @returns the distance between a point and this point
	 *
	 */
	float Point2D :: getDistance (const Point2D &point) const {
		float x2 = x - point.x;
		float y2 = y - point.y;
		return (float) sqrt((double)x2*x2 + y2*y2);

	}

	/**
	 *  Infix binary operator returns Point2D representing sum of this point and another point†.
	 *  The sum of two points is another point whose x and y values are the
	 *  sum of the two point x, and y values.
	 */
	Point2D Point2D :: operator +  (const Point2D &point) const{
		return {x+point.x,  y+point.y};

	}

	/**
	 *  Infix binary operator returns Point2D representing difference of this point and
	 *  another point The difference of two points is another point whose x and y values
	 *  are the difference of the two point x and y values.
	 */
	Point2D Point2D :: operator - () const {
	    return {-x, -y};
	}

	/**
	 * Prefix unary operator returns Point2D
	 * representing a point with then negative of this point's coordinates
	 */
	Point2D Point2D :: operator - (const Point2D &point) const{
		return {x-point.x,y-point.y};
	}

	/**
	 *  Infix binary operator returns true if a point is equal to this point
	 *
	 */
	bool Point2D :: operator == (const Point2D &point) const{

		return x==point.x && y==point.y;
	}

	/**
	 *  Infix binary operator returns true if a point is not equal to this point
	 *
	 */
	bool Point2D :: operator != (const Point2D &point) const{

		return x!=point.x || y!=point.y;
	}

} //namespace CS_5004
