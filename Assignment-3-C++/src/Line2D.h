/*
 * Line2D.h
 *
 *  Created on: Jan 24, 2019
 *      Author: hky
 */

#ifndef LINE2D_H_
#define LINE2D_H_

#include "Shape2D.h"
#include <float.h>
namespace CS_5004 {
class Rectangle2D;
class Line2D : public Shape2D {


public:
	/**
	 * relationship between x1 & x2
	 */
	enum delta{
		p1greater,
		p1less,
		equal,
		undefine
	};


private:
	float x1; //x coordinate of first point of line
	float y1; //y coordinate of first point of line
	float x2; //x coordinate of last point of line
	float y2; //y coordinate of last point of line
	delta dX; // x1 & x2 relationship
	delta dY; // y1 & y2 relationship
private:
	/**
	 * Calculate the relationshion between p1 and p2
	 * @return delta
	 */
	delta calculateDelta(float p1,float p2) const;

public:
	/**
	 * Constructs a line with points (0, 0), (0, 0)
	 *
	 */
	Line2D();

	/**
	 * Destructor
	 */
	virtual ~Line2D();

	/**
	 * Constructs a line with points point1, point2
	 * @param point1
	 * @param point2
	 */
	Line2D(const Point2D &point1, const Point2D &point2);

	/**
	 * Constructs a line with points (x1, y1), (x2, y2)
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	Line2D(float x1, float y1, float x2, float y2);

	/**
	 * Constructs a line with points of a line
	 * @param line
	 * @return line
	 */
	Line2D(const Line2D &line);


	/**
	 * Returns the first point of the line
	 * @return point1
	 */
	Point2D &getPoint1() const;

	/**
	 * Returns the second point of the line
	 * @return point2
	 */
	Point2D &getPoint2() const;

	/**
	 * return point1
	 * @param point1
	 * @return point1
	 */
	Point2D &getPoint1(Point2D &point1) const;

	/**
	 * return point2
	 * @param point2
	 * @return point2
	 */
	Point2D &getPoint2(Point2D &point2) const;
	/**
	 * Returns the bounding box for the line
	 * through the result parameter and the return value.
	 * @return new bounding box
	 */
	Rectangle2D& getBounds (Rectangle2D &bounds) const override;

	/**
	 * Sets the bounding box for the line,
	 * keeping the relative positions of the two points.
	 */
	void setBounds (const Rectangle2D &bounds) override;

	/**
	 * Sets the bounding box for the line with top-left point (x, y),
	 * and dimensions (width, height),
	 * keeping the relative positions of the two points.
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	void setBounds (float x, float y, float width, float height) override;

	/**
	 * @param upperLeft
	 * @param width
	 * @param height
	 */
	void setBounds (const Point2D &upperLeft, float width, float height) override;

	/**
	 * Determines whether line contains the point
	 * @param point
	 * @return true/false if a point is on the line segment
	 */
	bool containsPoint (const Point2D &point) const override;
};

} /* namespace CS_5004 */

#endif /* LINE2D_H_ */
