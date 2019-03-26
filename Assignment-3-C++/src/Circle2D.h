/*
 * Circle2D.h
 *
 * Created on: Jan 24, 2019
 *      Author: hokang yu, yu2749luca
 */

#ifndef CIRCLE2D_H_
#define CIRCLE2D_H_
#include "Shape2D.h"

namespace CS_5004 {
class Rectangle2D;
class Circle2D : public Shape2D {
private:
	float x; //X coordinate of top left of the circle
	float y; //Y coordinate of top left of the circle
	float width;
	float height;
public:
	/**
	 * Constructs a circle at (0, 0) with dimensions (0, 0)
	 */
	Circle2D();

	/**
	 * Destructor
	 */
	virtual ~Circle2D();

	/**
	 * Constructs a circle with top-left point (x, y), and dimensions (width, height)
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	Circle2D (float x, float y, float width, float height);

	/**
	 * Constructs a circle with top-left point, and dimensions (width, height)
	 * @param topLeft
	 * @param width
	 * @param height
	 */
	Circle2D (const Point2D &topLeft, float width, float height);

	/**
	 * Constructs a circle with location and dimensions of a circle
	 * @param rect
	 */
	Circle2D (const Circle2D &rect);

	/**
	 * Returns the X of the top left corner of the Circle
	 */
	float getX ()const;

	/**
	 * Returns the Y of the top left corner of the Circle
	 */
	float getY ()const;

	/**
	 * Returns the width of the Circle
	 */
	float getWidth ()const;

	/**
	 * Returns the height of the Circle
	 */
	float getHeight ()const;

	/**
	 * Returns the bounding box for the circle through the result parameter and the return value.
	 * @param bounds
	 * @return new bounding box
	 */
	Rectangle2D& getBounds (Rectangle2D &bounds) const override;

	/**
	 * Sets the bounding box for the rectangle
	 * with top-left point (x, y), and dimensions (width, height)
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	void setBounds (float x, float y, float width, float height) override;

	/**
	 * Sets the bounding box for the rectangle
	 * @param bounds
	 */
	void setBounds (const Rectangle2D &bounds) override;

	/**
	 * Sets the bounding box for the shape with top-left point (x, y), and dimensions (width, height)
	 * @param upperLeft
	 * @param width
	 * @param height
	 */
	void setBounds (const Point2D &upperLeft, float width, float height) override;

	/**
	 * Determines whether shape contains the point
	 * @param point
	 * @return true/false is a point is within circle/ellipse
	 */
	bool containsPoint (const Point2D &point) const override;

};

} /* namespace CS_5004 */

#endif /* CIRCLE2D_H_ */
