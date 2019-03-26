/*
 * Point2D.h
 *
 *  Created on: Jan 14, 2019
 *      Author: yu2749luca, Hokang Yu
 */

#ifndef SRC_POINT2D_H_
#define SRC_POINT2D_H_

namespace CS_5004{


	class Point2D {

		const float x;
		const float y;



	public:
		/**
		 *  Constructor initializes instance this.x, and this.y to x, and y
		 */
		Point2D (float x, float y);

		/**
		 *  Constructor intializes x and y to x and y of point (copy constructor)
		 *
		 */
		Point2D (const Point2D &point);

		/**
		 *  Returns the x coordinate of the point
		 *
		 */
		float getX () const;

		/**
		 *  Returns the Y coordinate of the point
		 *
		 */
		float getY () const;


		/**
		 *  Returns the distance between a point and this point
		 *
		 */
		float getDistance (const Point2D &point) const;




		/**
		 *  Infix binary operator returns Point2D representing sum of this point and another point†.
		 *  The sum of two points is another point whose x and y values are the
		 *  sum of the two point x, and y values.
		 */
		Point2D operator +  (const Point2D &point) const;

		/**
		 *  Infix binary operator returns Point2D representing difference of this point and
		 *  another point The difference of two points is another point whose x and y values
		 *  are the difference of the two point x and y values.
		 */
		Point2D operator -  (const Point2D &point) const;

		/**
		 * Prefix unary operator returns Point2D
		 * representing a point with then negative of this point's coordinates
		 */
		Point2D operator - () const;

		/**
		 *  Infix binary operator returns true if a point is equal to this point
		 *
		 */
		bool operator == (const Point2D &point) const;

		/**
		 *  Infix binary operator returns true if a point is not equal to this point
		 *
		 */
		bool operator != (const Point2D &point) const;



	}; //classPoint@d

} // CS_5004



#endif /* SRC_POINT2D_H_ */
