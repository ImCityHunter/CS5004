/*
 * Rectangle2D.cpp
 *
 *  Created on: Jan 24, 2019
 *      Author: hokang yu, yu2749luca
 */

#include "Rectangle2D.h"
using namespace CS_5004;
/**
 * Constructs a rectangle at (0, 0) with dimensions (0, 0)
 */
Rectangle2D::Rectangle2D(){
	setBounds(0,0,0,0);
}

/**
 *  Destructor
 */
Rectangle2D::~Rectangle2D(){
	setBounds(0,0,0,0);
}
/**
 * Constructs a rectangle with top-left point (x, y), and dimensions (width, height)
 * @param x
 * @param y
 * @param width
 * @param height
 */
//Rectangle2D (float x, float y, float width, float height);
Rectangle2D::Rectangle2D(float x, float y, float width, float height){
	setBounds(x,y,width,height);
}

/**
 * Constructs a rectangle with top-left point, and dimensions (width, height)
 * @param topLeft
 * @param width
 * @param height
 */
Rectangle2D :: Rectangle2D (const Point2D &topLeft, float width, float height) {
	setBounds(topLeft.getX(),topLeft.getY(),width,height);
}

/**
 * Constructs a rectangle with location and dimensions of a rectangle
 */
Rectangle2D::Rectangle2D(const Rectangle2D &rect){
	setBounds(rect.getX(),rect.getY(),rect.getWidth(),rect.getHeight());
}
/**
 * Returns the X of the top left corner of the rectangle
 */
float Rectangle2D::getX ()const {
	return this->x;
}

/**
 * Returns the Y of the top left corner of the rectangle
 */
float Rectangle2D::getY ()const {
	return this->y;
}

/**
 * Returns the width of the top left corner of the rectangle
 */
float Rectangle2D::getWidth ()const {
	return this->width;
}

/**
 * Returns the height of the top left corner of the rectangle
 */
float Rectangle2D::getHeight ()const {
	return this->height;
}

/**
 * Returns the bounding box for the rectangle through
 * the result parameter and the return value.
 * @param bounds
 * @return
 */
Rectangle2D &Rectangle2D::getBounds (Rectangle2D &bounds) const {
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
void Rectangle2D:: setBounds (float x, float y, float width, float height) {
	this->x = x;
	this->y = y;
	this->width = width;
	this->height=height;
}

/**
 * Sets the bounding box for the rectangle
 * @param bounds
 */
void Rectangle2D:: setBounds (const Rectangle2D &bounds) {
	setBounds(bounds.getX(),bounds.getY(),bounds.getWidth(),bounds.getHeight());
}
/**
 * Sets the bounding box for the shape with top-left point (x, y), and dimensions (width, height)
 * @param upperLeft
 * @param width
 * @param height
 */
void Rectangle2D:: setBounds (const Point2D &upperLeft, float width, float height) {
	setBounds(upperLeft.getX(),upperLeft.getY(),width,height);
}
/**
 * Determines whether shape contains the point
 * @param point
 * @return
 */
bool Rectangle2D::containsPoint (const Point2D &point) const {
	float xBound = this->x+this->width;
	float yBound = this->y-this->height;
	float epsilon = 0.00001f;
	bool xLower = x - point.getX() <  epsilon;
	bool xUpper = point.getX() - xBound < epsilon;
	bool yLower = yBound - point.getY() < epsilon;
	bool yUpper = point.getY() - y < epsilon;
	return xLower && xUpper && yLower && yUpper;

}


