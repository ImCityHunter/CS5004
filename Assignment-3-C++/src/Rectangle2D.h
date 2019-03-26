/*
 * Rectangle2D.h
 *
 *  Created on: Jan 24, 2019
 *      Author: hokang yu, yu2749luca
 */
#ifndef Rectangle2D_h_
#define Rectangle2D_h_

#include <string>
#include "Shape2D.h"
using std::string;

namespace CS_5004 {
	class Rectangle2D : public Shape2D {
	private:
		float x; //x coordinate of top left
		float y; //y coordinate of top left
		float width; //width of rectangle
		float height; //height of rectangle
	public:
		/**
		 * Constructs a rectangle at (0, 0) with dimensions (0, 0)
		 */
		Rectangle2D	();

		/**
		 * Destructor
		 */
		virtual ~Rectangle2D();

		/**
		 * Constructs a rectangle with top-left point (x, y), and dimensions (width, height)
		 * @param x
		 * @param y
		 * @param width
		 * @param height
		 */
		Rectangle2D (float x, float y, float width, float height);

		/**
		 * Constructs a rectangle with top-left point, and dimensions (width, height)
		 * @param topLeft
		 * @param width
		 * @param height
		 */
		Rectangle2D (const Point2D &topLeft, float width, float height);

		/**
		 * Constructs a rectangle with location and dimensions of a rectangle
		 */
		Rectangle2D(const Rectangle2D &ect);

		/**
		 * Returns the X of the top left corner of the rectangle
		 * @return x
		 */
		float getX ()const;

		/**
		 * Returns the Y of the top left corner of the rectangle
		 * @return y
		 */
		float getY ()const;

		/**
		 * Returns the width of the rectangle
		 * @return width
		 */
		float getWidth ()const;

		/**
		 * Returns the height of the rectangle
		 * @return height
		 */
		float getHeight ()const;

		/**
		 * Returns the bounding box for the rectangle through
		 * the result parameter and the return value.
		 * @param bounds
		 * @return new boundgin box
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
		 * @return true/false point is inside the box
		 */
		bool containsPoint (const Point2D &point) const override;



	}; /* Class Rectangle2D */


} /* CS_5004 */
#endif

