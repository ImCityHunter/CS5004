/*
 * Line2D.cpp
 *
 *  Created on: Jan 24, 2019
 *      Author: hokang yu, yu2749luca
 */
#include "Point2D.h"
#include "Line2D.h"
#include "Rectangle2D.h"
#include <math.h>
#include <cassert>
#include <iostream>
using namespace CS_5004;


/**
 * constructor. set all to 0
 */
Line2D::Line2D() {
	this->x1=0;
	this->x2=0;
	this->y1=0;
	this->y2=0;
	this->dX = this->calculateDelta(this->x1,this->x2);
	this->dY = this->calculateDelta(this->y1,this->y2);

}

/**
 * destructor.
 */
Line2D::~Line2D() {
	this->x1=0;
	this->x2=0;
	this->y1=0;
	this->y2=0;
	this->dX = undefine; //use this as default
	this->dY = undefine;
}

/**
 * Constructs a line with points point1, point2
 * @param point1
 * @param point2
 */
Line2D :: Line2D (const Point2D &point1, const Point2D &point2){
	this->x1 = point1.getX();
	this->y1 = point1.getY();
	this->x2 = point2.getX();
	this->y2 = point2.getY();
	this->dX = this->calculateDelta(this->x1,this->x2);
	this->dY = this->calculateDelta(this->y1,this->y2);

}

/**
 * Constructs a line with points (x1, y1), (x2, y2)
 * @param x1
 * @param y1
 * @param x2
 * @param y2
 */
Line2D :: Line2D (float x1, float y1, float x2, float y2) {
	this->x1=x1;
	this->y1=y1;
	this->x2=x2;
	this->y2=y2;
	this->dX = this->calculateDelta(this->x1,this->x2);
	this->dY = this->calculateDelta(this->y1,this->y2);
}

/**
 * Constructs a line with points of a line
 *
 */
Line2D :: Line2D (const Line2D &line){
	this->x1=line.getPoint1().getX();
	this->y1=line.getPoint1().getY();
	this->x2=line.getPoint2().getX();
	this->y2=line.getPoint2().getY();
	this->dX = this->calculateDelta(this->x1,this->x2);
	this->dY = this->calculateDelta(this->y1,this->y2);

}

/**
 * Calculate the relationship between p1 & p2
 * @return deltaX
 */
Line2D::delta Line2D::calculateDelta(float p1,float p2) const{
	float epsilon = 0.0000001f;
	if(fabs(p1-p2) < epsilon ||p1==p2) return equal;
	else if(p1>p2) return p1greater;
	else if(p1<p2) return p1less;
	else return undefine; //should not reach this point
}





/**
 * Returns the first point of the line
 * @return point1
 */
Point2D &Line2D::getPoint1() const{
	return *new Point2D(this->x1,this->y1);
}
/**
 * Returns the second point of the line
 * @return point2
 */
Point2D &Line2D::getPoint2() const{
	return *new Point2D(this->x2,this->y2);
}

/**
 * Returns the first point of the line
 * @param point1
 * @return
 */
Point2D &Line2D::getPoint1(Point2D &point1) const{
	point1.setPoint(this->x1,this->y1);

	return point1;
}
/**
 * Returns the second point of the line
 * @param point2
 * @return point2
 */
Point2D &Line2D::getPoint2(Point2D &point2) const{
	point2.setPoint(this->x2,this->y2);
	return point2;
}

/**
 * Returns the bounding box for the line
 * through the result parameter and the return value.
 * @return the new bounding box
 */
Rectangle2D& Line2D::getBounds(Rectangle2D &bounds) const{

	//if undefine/vertical/horizontal, then the result value will be 0
	float tempWidth = abs(this->x1 - this->x2);
	float tempHeight= abs(this->y1 - this->y2);

	//no matter what the slope is, topLeft x is always the smaller of x1 & x2
	float tempX = (this->x1 < this->x2) ? this->x1 : this->x2;

	//no matter what the sloep is, topLeft y is always the greater or y1 & y2
	float tempY = (this->y1 > this->y2) ? this->y1 : this->y2;

	bounds.setBounds(tempX,tempY,tempWidth,tempHeight);

	return bounds;
}

/**
 * Sets the bounding box for the line,
 * keeping the relative positions of the two points.
 * @param bounds
 */
//void setBounds (const Rectangle2D &bounds) override;
void Line2D :: setBounds(const Rectangle2D &bounds){
	setBounds(bounds.getX(),bounds.getY(),bounds.getWidth(),bounds.getHeight());

}
/**
 * Sets the bounding box for the line with top-left point (x, y),
 * and dimensions (width, height),
 * keeping the relative positions of the two points.
 * @param x
 * @param y
 * @param width
 * @param height
 */
void Line2D::setBounds (float x, float y, float width, float height){



	//width & height must be greater than 0 in my function
	assert(width>= 0 && "Width Must be Greater Than Zero in my function. Sorry");
	assert(height>=0 &&"Height Must Be Greater Than Zero in my function. Sorry");
	assert(dX!=undefine && dY!=undefine);

	//defaultPosition
	x1=x2=x;
	y1=y2=y;

	if(dX == p1greater) x1+=width;
	else if(dX == p1less) x2+=width;

	if(dY == p1greater) y2-=height;
	else if(dY == p1less) y1-=height;

}
/**
 * @param upperLeft
 * @param width
 * @param height
 */

void Line2D::setBounds(const Point2D &upperLeft, float width, float height){
	setBounds(upperLeft.getX(),upperLeft.getY(),width,height);
}
/**
 * Determines whether line contains the point
 * @param point
 * @return if a point is on the line segment
 *
 */

bool Line2D::containsPoint(const Point2D &point) const {
	Point2D point1 (this->getPoint1().getX(),this->getPoint1().getY());
	Point2D point2 (this->getPoint2().getX(),this->getPoint2().getY());
	float epsilon = 0.0001f;
	float longestdistance = point1.getDistance(point2);
	float distance1=point1.getDistance(point);
	float distance2=point2.getDistance(point);
	return fabs(longestdistance-distance1-distance2) < epsilon;


}


