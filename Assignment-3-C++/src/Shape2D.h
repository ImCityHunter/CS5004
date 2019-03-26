
/*
 * Shape2D.h
 *
 *  Created on: Jan 24, 2019
 *      Author: hky
 */

#ifndef Shape2D_h_
#define Shape2D_h_

#include "Point2D.h"
#include <string>
using std::string;

namespace CS_5004 {

/**
 *  This class defines Shape2D
 */
class Rectangle2D;
class Shape2D {
public:
	/**
	 * Constructor
	 */
	Shape2D();

	/**
	 * Destructor
	 */
	virtual ~Shape2D();

	/**
	 * Returns the bounding box for the shape through the result parameter and the return value.
	 * @param bounds
	 * @return
	 */
	virtual Rectangle2D& getBounds (Rectangle2D &bounds) const = 0;
	/**
	 * Sets the bounding box for the shape with top-left point (x, y), and dimensions (width, height)
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	virtual void setBounds (float x, float y, float width, float height)=0;

	/**
	 * Sets the bounding box for the shape with top-left point (x, y), and dimensions (width, height)
	 * @param upperLeft
	 * @param width
	 * @param height
	 */
	virtual void setBounds (const Point2D &upperLeft, float width, float height)=0;

	/**
	 * Sets the bounding box for the shape
	 * @param bounds
	 */
	virtual void setBounds (const Rectangle2D& bounds) =0;

	/**
	 * Determines whether shape contains the point
	 * @param point
	 * @return
	 */
	virtual bool containsPoint (const Point2D &point) const = 0;



};  /*   Shape2D  */
} //end CS_5004
#endif

