/*
 * Circle2D.cpp
 *
 *  Created on: Jan 24, 2019
 *      Author: hokang yu, yu2749luca
 */

#include "Circle2D.h"
#include "Rectangle2D.h"
#include <math.h>
using namespace CS_5004;

/**
 * Constructs a circle at (0, 0) with dimensions (0, 0)
 */
Circle2D::Circle2D() {
	setBounds(0,0,0,0);

}

/**
 * Destructor
 */
Circle2D::~Circle2D() {
	setBounds(0,0,0,0);
}
/**
 * Constructs a circle with top-left point (x, y), and dimensions (width, height)
 * @param x
 * @param y
 * @param width
 * @param height
 */
//Circle2D (float x, float y, float width, float height);
Circle2D::Circle2D(float x, float y, float width, float height){
	setBounds(x,y,width,height);
}

/**
 * Constructs a circle with top-left point, and dimensions (width, height)
 * @param topLeft
 * @param width
 * @param height
 */
//Circle2D (const Point2D &topLeft, float width, float height);
Circle2D::Circle2D(const Point2D &topLeft, float width, float height){
	setBounds(topLeft.getX(),topLeft.getY(),width,height);
}

/**
 * Constructs a circle with location and dimensions of a circle
 * @param rect
 */
//Circle2D (const Circle2D &rect);
Circle2D::Circle2D(const Circle2D &rect){
	setBounds(rect.getX(),rect.getY(),rect.getWidth(),rect.getHeight());
}
/**
 * Returns the X of the top left corner of the Circle
 */
//float getX ()const;
float Circle2D::getX ()const {
	return this->x;
}

/**
 * Returns the Y of the top left corner of the Circle
 */
//float getY ()const;
float Circle2D::getY ()const {
	return this->y;
}
/**
 * Returns the width of the Circle
 */
//float getWidth ()const;
float Circle2D::getWidth ()const {
	return this->width;
}
/**
 * Returns the height of the Circle
 */
//float getHeight ()const;
float Circle2D::getHeight ()const {
	return this->height;
}
/**
 * Returns the bounding box for the circle through the result parameter and the return value.
 * @param bounds
 * @return new bounding box
 */
//Rectangle2D& getBounds (Rectangle2D &bounds) const override;
Rectangle2D &Circle2D::getBounds(Rectangle2D &bounds) const{
	bounds.setBounds(this->x,this->y,this->width,this->height);
	return bounds;
}

/**
 * Sets the bounding box for the rectangle
 * with top-left point (x, y), and dimensions (width, height)
 * @param x
 * @param y
 * @param width
 * @param height
 */
//void setBounds (float x, float y, float width, float height) override;
void Circle2D::setBounds(float x, float y, float width, float height){
	this->x=x;
	this->y=y;
	this->width=width;
	this->height=height;
}
/**
 * Sets the bounding box for the rectangle
 * @param bounds
 */
//void setBounds (const Rectangle2D &bounds) override;
void Circle2D::setBounds(const Rectangle2D &bounds){
	setBounds(bounds.getX(),bounds.getY(),bounds.getWidth(),bounds.getHeight());
}

/**
 * Sets the bounding box for the shape with top-left point (x, y), and dimensions (width, height)
 * @param upperLeft
 * @param width
 * @param height
 */
//void setBounds (const Point2D &upperLeft, float width, float height) override;
void Circle2D::setBounds(const Point2D &upperLeft, float width, float height){
	setBounds(upperLeft.getX(),upperLeft.getY(),width,height);
}

/**
 * Determines whether shape contains the point
 * Formula = (x-h)^2/a^2 + (y-h)^2/b^2 <=1
 * @param point
 * @return true/false is a point is within circle/ellipse
 */
//bool containsPoint (const Point2D &point) const override;
bool Circle2D ::containsPoint(const Point2D &point) const {
	float centerX = this->x + this->width/2;
	float centerY = this->y - this->height/2;
	float epsilon = 0.000001f;
	Point2D center(centerX,centerY);
	float deltaX = point.getX() - centerX;
	float deltaY = point.getY() - centerY;
	float a = this->width/2;
	float b = this->height/2;
	if(width<epsilon){
		float yLower = y - height;
		float part1 = point.getY() - y;
		float part2 = yLower - point.getY();
		float part3 = fabs(point.getX()-x);
		return part1<epsilon && part2 < epsilon && part3<epsilon;
	}
	else if(height<epsilon){
		float xUpper = x+width;
		float part1 = point.getX()-xUpper;
		float part2 = x - point.getX();
		return part1<epsilon&&part2<epsilon;
	}
	else if(width<epsilon && height<epsilon){
		float part1 = fabs(x-point.getX());
		float part2 = fabs(y-point.getY());
		float part3 = fabs(point.getY()-y);
		return part1<epsilon&&part2<epsilon&&part3<epsilon;

	}
	else return (deltaX*deltaX)/(a*a)+(deltaY*deltaY)/(b*b)-1< epsilon;
}
