/*
 * TestShapes.cpp
 *
 *  Created on: Jan 24, 2019
 *      Author: hokang yu, yu2749luca
 */
#include <cstdlib>
#include <cstdio>
#include "CUnit/CUnit.h"
#include "CUnit/Basic.h"

#include "Shape2D.h"
#include "Line2D.h"
#include "Rectangle2D.h"
#include "Circle2D.h"

using namespace CS_5004;

/**
 * Test Rectangle2D
 */
void test_Rectangle2D(){

	Rectangle2D *rectOrigin = new Rectangle2D();
	CU_ASSERT_EQUAL(0,rectOrigin->getX());
	CU_ASSERT_EQUAL(0,rectOrigin->getY());
	CU_ASSERT_EQUAL(0,rectOrigin->getWidth());
	CU_ASSERT_EQUAL(0,rectOrigin->getHeight());
	delete rectOrigin;

	float width = 7;
	float height = 5;

	/*
	 * Rectangle has points (-7,5) (0,5) (-7,0) (0,0)
	 */

	//test giving 4 values
	Rectangle2D rect1 (-7,5,width,height);
	CU_ASSERT_EQUAL(-7,rect1.getX());
	CU_ASSERT_EQUAL(5,rect1.getY());
	CU_ASSERT_EQUAL(width,rect1.getWidth());
	CU_ASSERT_EQUAL(height,rect1.getHeight());

	//test giving a point
	Point2D point1 (-7,5);
	Rectangle2D rect2 (point1,width,height);
	CU_ASSERT_EQUAL(-7,rect2.getX());
	CU_ASSERT_EQUAL(5,rect2.getY());
	CU_ASSERT_EQUAL(width,rect2.getWidth());
	CU_ASSERT_EQUAL(height,rect2.getHeight());

	//test constructing a rect via a rect
	Rectangle2D rectCopy (rect2);
	CU_ASSERT_EQUAL(-7,rectCopy.getX());
	CU_ASSERT_EQUAL(5,rectCopy.getY());
	CU_ASSERT_EQUAL(width,rectCopy.getWidth());
	CU_ASSERT_EQUAL(height,rectCopy.getHeight());

	//Test copying a rect via getBound
	Rectangle2D rectGetBound = rectCopy.getBounds(rect1);
	CU_ASSERT_EQUAL(rectGetBound.getX(),rectCopy.getX());
	CU_ASSERT_EQUAL(rectGetBound.getY(),rectCopy.getY());
	CU_ASSERT_EQUAL(rectGetBound.getWidth(),rectCopy.getWidth());
	CU_ASSERT_EQUAL(rectGetBound.getHeight(),rectCopy.getHeight());

	//test setBounds given 4 values
	Rectangle2D rect3(0,0,0,0);
	rect3.setBounds(-7,5,width,height);
	CU_ASSERT_EQUAL(-7,rect3.getX());
	CU_ASSERT_EQUAL(5,rect3.getY());
	CU_ASSERT_EQUAL(width,rect3.getWidth());
	CU_ASSERT_EQUAL(height,rect3.getHeight());

	//reset Rect3 and test
	rect3 = *new Rectangle2D();
	CU_ASSERT_EQUAL(0,rect3.getX());
	CU_ASSERT_EQUAL(0,rect3.getY());
	CU_ASSERT_EQUAL(0,rect3.getWidth());
	CU_ASSERT_EQUAL(0,rect3.getHeight());

	//test setBounds given 1 rect
	rect3.setBounds(rect1);
	CU_ASSERT_EQUAL(-7,rect3.getX());
	CU_ASSERT_EQUAL(5,rect3.getY());
	CU_ASSERT_EQUAL(width,rect3.getWidth());
	CU_ASSERT_EQUAL(height,rect3.getHeight());

	//test setBounds given 1 point,width,height
	rect3 = *new Rectangle2D(); //reset Rect1
	rect3.setBounds(point1,width,height);
	CU_ASSERT_EQUAL(-7,rect3.getX());
	CU_ASSERT_EQUAL(5,rect3.getY());
	CU_ASSERT_EQUAL(width,rect3.getWidth());
	CU_ASSERT_EQUAL(height,rect3.getHeight());

	bool testContains = false;
	testContains = rect3.containsPoint(point1);
	CU_ASSERT_TRUE(testContains); // (-7,5) is within Rectangle
	Point2D Origin (0,0);
	testContains = rect3.containsPoint(Origin);
	CU_ASSERT_TRUE(testContains);//(0,0) is within Rectangle
	Point2D rightTop (0,5);
	testContains = rect3.containsPoint(rightTop);
	CU_ASSERT_TRUE(testContains); // (0,5) is within Rectangle
	Point2D bottomLeft (-7,0);
	testContains = rect3.containsPoint(bottomLeft);
	CU_ASSERT_TRUE(testContains); // (-7,0) is within Rectangle
	Point2D inside (-2,2);
	testContains = rect3.containsPoint(inside);
	CU_ASSERT_TRUE(testContains);  //(-2,2) is within Rectangle

	Point2D pointFarTop (-7,6);
	testContains = rect3.containsPoint(pointFarTop);
	CU_ASSERT_FALSE(testContains); //(-7,6) is outside the box



} /* test Rectangle2D */

/**
 * Test Circle2D
 */
void test_Circle2D(){

	Point2D topLeft(0,0);
	Point2D mid(0,0);
	Point2D center(0,0);
	Point2D inbound(0,0);
	Point2D outbound(0,0);
	Rectangle2D rect(0,0,0,0);
	bool testContains = false;

	/*
	 * Test Constructor
	 */
	Circle2D circleOrigin;
	CU_ASSERT_EQUAL(0,circleOrigin.getX());
	CU_ASSERT_EQUAL(0,circleOrigin.getY());
	CU_ASSERT_EQUAL(0,circleOrigin.getWidth());
	CU_ASSERT_EQUAL(0,circleOrigin.getHeight());

	/*
	 * Test Ellipse
	 * Width = 20, Height = 10
	 * (-10,10) ---------------------- (10,10)
	 * 	|									|
	 * 	|									|
	 * (-10, 0) ---------------------- (10, 0)
	 *
	 */

	Circle2D circle1(-10,10,20,10); //test constructor
	CU_ASSERT_EQUAL(-10,circle1.getX());
	CU_ASSERT_EQUAL(10,circle1.getY());
	CU_ASSERT_EQUAL(20,circle1.getWidth());
	CU_ASSERT_EQUAL(10,circle1.getHeight());

	topLeft.setPoint(-10,-10);
	//topLeft should NOT be inside the eclipse
	testContains = circle1.containsPoint(topLeft);
	CU_ASSERT_FALSE(testContains);

	//mid should be inside the eclipse
	mid.setPoint(0,10);
	testContains = circle1.containsPoint(mid);
	CU_ASSERT_TRUE(testContains);

	//the rect for circle 1 should have point (-10,10), width = 20, height = 10
	circle1.getBounds(rect);
	CU_ASSERT_EQUAL(-10,rect.getX());
	CU_ASSERT_EQUAL(10,rect.getY());
	CU_ASSERT_EQUAL(20,rect.getWidth());
	CU_ASSERT_EQUAL(10,rect.getHeight());

	/*
	 * Test Circle
	 * Width = 10, Height = 10
	 * (-10,10) ----------- (0,10)
	 * 		|				|
	 * 		|				|
	 * 		|				|
	 * 		|				|
	 * (-10, 0) ----------- (0, 0)
	 *
	 */
	topLeft.setPoint(-10,10);
	Circle2D circle2(topLeft,10,10);
	testContains = circle2.containsPoint(topLeft);
	CU_ASSERT_FALSE(testContains); //topLeft should not be inside Circle

	center.setPoint(-5,5);
	testContains = circle2.containsPoint(center);
	CU_ASSERT_TRUE(testContains); //center should be inside the circle
	mid.setPoint(-5,10);
	testContains = circle2.containsPoint(mid);
	CU_ASSERT_TRUE(testContains); //mid should be inside the circle

	//the rect for circle 2 should have point (-10,10), width = 10, height = 10
	circle2.getBounds(rect);
	CU_ASSERT_EQUAL(-10,rect.getX());
	CU_ASSERT_EQUAL(10,rect.getY());
	CU_ASSERT_EQUAL(10,rect.getWidth());
	CU_ASSERT_EQUAL(10,rect.getHeight());

	//circle3 have the same bound as circle2 given by the rect
	Circle2D circle3(0,0,0,0);
	circle3.setBounds(rect); // (-10,10,10,10)
	CU_ASSERT_EQUAL(-10,circle3.getX());
	CU_ASSERT_EQUAL(10,circle3.getY());
	CU_ASSERT_EQUAL(10,circle3.getWidth());
	CU_ASSERT_EQUAL(10,circle3.getHeight());

	/*
	 * Test width=0 or height=0
	 */
	rect={5,5,5,0};
	circle1.setBounds(rect);
	inbound.setPoint(10,5);
	testContains = circle1.containsPoint(inbound);
	CU_ASSERT_TRUE(testContains);
	outbound.setPoint(4.99f,5);
	testContains = circle1.containsPoint(outbound);
	CU_ASSERT_FALSE(testContains);

	rect={5,5,0,5};
	circle1.setBounds(rect);
	inbound.setPoint(5,0);
	testContains = circle1.containsPoint(inbound);
	CU_ASSERT_TRUE(testContains);
	outbound.setPoint(5,5.001f);
	testContains = circle1.containsPoint(outbound);
	CU_ASSERT_FALSE(testContains);
	outbound.setPoint(4.99f,5);
	testContains = circle1.containsPoint(outbound);
	CU_ASSERT_FALSE(testContains);

} /* test Circle2D */

/**
 * Test Line2D
 */
void test_Line2D(){

	Point2D point1(0,0);
	Point2D point2(0,0);
	Point2D origin(0,0);
	Point2D leftTop(0,0);
	Rectangle2D rect(0,0,0,0); //make a default Rectangle
	Line2D line1(0,0,0,0);
	line1 = {3.4, 5.6, 7.0, 9.0};
	Line2D line2(0,0,0,0);
	bool testContains = false;

	/*
	 * Test Default Constructor
	 */
	Line2D *LineDefault = new Line2D();
	CU_ASSERT_EQUAL(0,LineDefault->getPoint1().getX());
	CU_ASSERT_EQUAL(0,LineDefault->getPoint1().getY());
	CU_ASSERT_EQUAL(0,LineDefault->getPoint2().getX());
	CU_ASSERT_EQUAL(0,LineDefault->getPoint2().getY());

	/*
	 * When there was no line (two points over lap)
	 * The TopLeft of Rect is Origin (0,0)
	 * Width & Height = 0
	 *
	 */
	LineDefault->getBounds(rect);
	CU_ASSERT_EQUAL(0,rect.getX());
	CU_ASSERT_EQUAL(0,rect.getY())
	CU_ASSERT_EQUAL(0,rect.getWidth());
	CU_ASSERT_EQUAL(0,rect.getHeight());


	/*
	 * Use Origin to setBounds
	 * newLine should still be a point, not a line
	 * Point1(0,0) --> (-10,10)
	 * Point2(0,0) --> (-10,10)
	 */

	line1 = {origin,origin};
	line1.setBounds(-10,10,10,10);
	line1.getPoint1(point1);
	line1.getPoint2(point2);
	CU_ASSERT_EQUAL(-10,point1.getX());
	CU_ASSERT_EQUAL(10,point1.getY());
	CU_ASSERT_EQUAL(-10,point2.getX());
	CU_ASSERT_EQUAL(10,point2.getY());

	/*
	 * Point1 (0,4)
	 * Point2 (4,0)
	 * Slope = -1
	 * With Point1 on Left
	 *
	 * point1
	 * (0,4) ----- (4,4)
	 *		|		|
	 *		|		|
	 *		---------
	 * (0,0)	   (4,0)
	 * 				point2
	 *
	 */
	point1.setPoint(0,4);
	point2.setPoint(4,0);
	line1={point1,point2};
	CU_ASSERT_EQUAL(0,line1.getPoint1().getX());
	CU_ASSERT_EQUAL(4,line1.getPoint1().getY());
	CU_ASSERT_EQUAL(4,line1.getPoint2().getX());
	CU_ASSERT_EQUAL(0,line1.getPoint2().getY());

	/*
	 *  Use line1 to get a bounding box
	 * 	(0,4) --> (4,0)
	 *  TopLeft Should be (0,4)
	 *  Width = 4, Height = 4
	 */
	line1.getBounds(rect);
	CU_ASSERT_EQUAL(0,rect.getX());
	CU_ASSERT_EQUAL(4,rect.getY())
	CU_ASSERT_EQUAL(4,rect.getWidth());
	CU_ASSERT_EQUAL(4,rect.getHeight());

	/*
	 * copy line1 to line2
	 */

	line2 = {line1};
	CU_ASSERT_EQUAL(0,line2.getPoint1().getX());
	CU_ASSERT_EQUAL(4,line2.getPoint1().getY());
	CU_ASSERT_EQUAL(4,line2.getPoint2().getX());
	CU_ASSERT_EQUAL(0,line2.getPoint2().getY());
	line2.getPoint1(point1);
	line2.getPoint2(point2);



	/*
	 * make a new line2
	 * same slope as line1 but swap p1 & p2
	 * (4,0), (0,4)
	 *
	 * point2
	 * (0,4) ----- (4,4)
	 *		|		|
	 *		|		|
	 *		---------
	 * (0,0)	   (4,0)
	 * 				point1
	 */
	line2 = {point2,point1}; //swap p1 & p2
	line2.getPoint1(point1);
	line2.getPoint2(point2);
	CU_ASSERT_EQUAL(4,point1.getX());
	CU_ASSERT_EQUAL(0,point1.getY());
	CU_ASSERT_EQUAL(0,point2.getX());
	CU_ASSERT_EQUAL(4,point2.getY());
	line2.getBounds(rect); //get the bounding box for line2
	leftTop.setPoint(rect.getX(),rect.getY());
	CU_ASSERT_EQUAL(0,leftTop.getX());
	CU_ASSERT_EQUAL(4,leftTop.getY())
	CU_ASSERT_EQUAL(4,rect.getWidth());
	CU_ASSERT_EQUAL(4,rect.getHeight());

	/*
	 * Vertical Line of (1,4) & (1,-4)
	 * TopLeft point should be (1,4)
	 * Width = 0, Height = 8
	 *
	 *
	 * (1, 4) point1
	 * 	|
	 * 	|
	 * 	|
	 * (1,-4) point2
	 *
	 */
	point1.setPoint(1,4);
	point2.setPoint(1,-4);
	line1 = {point1,point2};
	line1.getBounds(rect);
	CU_ASSERT_EQUAL(1,rect.getX());
	CU_ASSERT_EQUAL(4,rect.getY())
	CU_ASSERT_EQUAL(0,rect.getWidth());
	CU_ASSERT_EQUAL(8,rect.getHeight());

	/*
	 * Vertical Line of (1,4) & (1,-4)
	 * TopLeft point should be (1,4)
	 * Width = 0, Height = 8
	 *
	 *
	 * Test GetBounds
	 *
	 */

	line1.getBounds(rect);
	CU_ASSERT_EQUAL(1,rect.getX());
	CU_ASSERT_EQUAL(4,rect.getY())
	CU_ASSERT_EQUAL(0,rect.getWidth());
	CU_ASSERT_EQUAL(8,rect.getHeight());

	/*
	 * Test Vertical Line with Point2 on Top
	 * Vertical Line of (1,-4) & (1,4)
	 * TopLeft point should be (1,4)
	 * Width = 0, Height = 8
	 *
	 *
	 *  (1,4) point2
	 *  |
	 *  |
	 *  |
	 *  (1,-4) point1
	 *
	 */
	line2 = {point2,point1}; //swap p1 and p2
	line2.getBounds(rect);
	CU_ASSERT_EQUAL(1,rect.getX());
	CU_ASSERT_EQUAL(4,rect.getY())
	CU_ASSERT_EQUAL(0,rect.getWidth());
	CU_ASSERT_EQUAL(8,rect.getHeight());



	/*
	 * Test Horizontal Line with Point1 on Left (-4,2),(10,2)
	 * Horizontal Line of (-4,2) & (10,2)
	 * TopLeft point should be (-4,2)
	 * Width = 14, Height = 0
	 *
	 * point1			point2
	 * (-4,2) --------- (10,2)
	 *
	 */
	point1.setPoint(-4,2);
	point2.setPoint(10,2);
	line1 = {point1,point2};
	line1.getBounds(rect);
	CU_ASSERT_EQUAL(-4,rect.getX());
	CU_ASSERT_EQUAL(2,rect.getY())
	CU_ASSERT_EQUAL(14,rect.getWidth());
	CU_ASSERT_EQUAL(0,rect.getHeight());

	/* Test Horizontal Line with Point2 on Left (10,2),(-4,2)
	 * Horizontal Line of (-4,2) & (10,2)
	 * TopLeft point should be (-4,2)
	 * Width = 14, Height = 0
	 *
	 * point2			point1
	 * (-4,2) --------- (10,2)
	 *
	 */
	line2 = {point2,point1}; //swap p1 & p2
	line2.getBounds(rect);
	CU_ASSERT_EQUAL(-4,rect.getX());
	CU_ASSERT_EQUAL(2,rect.getY())
	CU_ASSERT_EQUAL(14,rect.getWidth());
	CU_ASSERT_EQUAL(0,rect.getHeight());

	/*
	 * Test Positive Slope Line with Point1 on left
	 *   Point1 (-4,0)
	 * 	 Point2 (0, 4)
	 * 	 Slope is 1
	 *
	 * P1 & P2 has a positive slope
	 * Point1 (-4,0) ---> (-10,0)
	 * Point2 ( 0,4) ---> (0, 10)
	 *
	 * The Relative Position of P1 and P2
	 * should remain the same in new bounding box
	 *
	 * Four Corners of the Rect
	 *
	 *
	 * (-10,10)--------- (0,10) point2
	 * 		|			|
	 * 		|			|
	 *		|			|
	 * (-10, 0)--------- (0, 0)
	 * point1
	 *
	 * width & height = 10 (square)
	 */
	point1.setPoint(-4,0);
	point2.setPoint(0,4);
	line1 = {point1,point2};

	leftTop.setPoint(-10,10);
	line1.setBounds(leftTop,10,10);
	line1.getPoint1(point1);
	line1.getPoint2(point2);
	CU_ASSERT_EQUAL(-10,point1.getX());
	CU_ASSERT_EQUAL(0,point1.getY());
	printf("\n %f \n",point1.getY());
	CU_ASSERT_EQUAL(0,point2.getX());
	CU_ASSERT_EQUAL(10,point2.getY());

	/*
	 * Test Positive Slope Line with Point2 on left
	 * Point 1 (0,4)
	 * Point 2 (-4,0)
	 */
	line2 = {point2,point1}; //swap p1 & p2

	/*
	 * Point1 ( 0,4) ---> (0 ,10)
	 * Point2 (-4,0) ---> (-10,0)
	 *
	 * The Relative Position of P1 and P2
	 * should remain the same in new bounding box
	 *
	 * Four Corners of the Rect
	 *
	 * (-10,10)--------- (0,10) Point1
	 * 		|			|
	 * 		|			|
	 *		|			|
	 * (-10, 0)--------- (0, 0)
	 * Point2
	 *
	 *  width & height = 10 (square)
	 *
	 */

	line2.setBounds(-10,10,10,10); //Call setBounds with a different parameter
	line2.getPoint1(point1);
	line2.getPoint2(point2);
	CU_ASSERT_EQUAL(0,point1.getX());
	CU_ASSERT_EQUAL(10,point1.getY());
	CU_ASSERT_EQUAL(-10,point2.getX());
	CU_ASSERT_EQUAL(0.0,point2.getY());


	testContains = line2.containsPoint(origin);
	//Origin should not be on the new Line
	CU_ASSERT_FALSE(testContains);
	testContains = line2.containsPoint(point1);
	CU_ASSERT_TRUE(testContains);
	point1.setPoint(-5,5);
	//(-5,5) should be on the Line
	testContains = line2.containsPoint(point1);
	CU_ASSERT_TRUE(testContains);

	/*
	 * Point1 ( 1, 4) ---> (-10 ,10)
	 * Point2 ( 1, -4) ---> (-10, 0)
	 *
	 * The Relative Position of P1 and P2
	 * should remain the same in new bounding box
	 *
	 * Four Corners of the Rect
	 *
	 * (-10,10)--------- (0,10)
	 * 		|			|
	 * 		|			|
	 *		|			|
	 * (-10, 0)--------- (0, 0)
	 *
	 *  width & height = 10 (square)
	 *
	 */
	point1.setPoint(1,4);
	point2.setPoint(1,-4);
	line1 = {point1,point2};
	rect.setBounds(-10,10,10,10);
	line1.setBounds(rect); //Call setBounds with a different parameter
	line1.getPoint1(point1);
	line1.getPoint2(point2);
	CU_ASSERT_EQUAL(-10,point1.getX());
	CU_ASSERT_EQUAL(10,point1.getY());
	CU_ASSERT_EQUAL(-10,point2.getX());
	CU_ASSERT_EQUAL(0,point2.getY());

	/*
	 * Point1 ( 1, -4) ---> (-10 ,0)
	 * Point2 ( 1,  4) ---> (-10, 10)
	 *
	 * The Relative Position of P1 and P2
	 * should remain the same in new bounding box
	 *
	 * Four Corners of the Rect
	 *
	 * (-10,10)--------- (0,10)
	 * 		|			|
	 * 		|			|
	 *		|			|
	 * (-10, 0)--------- (0, 0)
	 *
	 *  width & height = 10 (square)
	 *
	 */
	line2 = {point2,point1};//swap p1 & p2
	rect.setBounds(-10,10,10,10);
	line2.setBounds(rect); //Call setBounds with a different parameter
	line2.getPoint1(point1);
	line2.getPoint2(point2);
	CU_ASSERT_EQUAL(-10,point1.getX());
	CU_ASSERT_EQUAL(0,point1.getY());
	CU_ASSERT_EQUAL(-10,point2.getX());
	CU_ASSERT_EQUAL(10,point2.getY());

	/*
	 * Point1 ( -4, 2) ---> (-10 ,10)
	 * Point2 ( 10,  2) ---> (0, 10)
	 *
	 * The Relative Position of P1 and P2
	 * should remain the same in new bounding box
	 *
	 * Four Corners of the Rect
	 *
	 * (-10,10)--------- (0,10)
	 * 		|			|
	 * 		|			|
	 *		|			|
	 * (-10, 0)--------- (0, 0)
	 *
	 *  width & height = 10 (square)
	 *
	 */
	point1.setPoint(-4,2);
	point2.setPoint(10,2);
	line1 = {point1,point2};
	rect.setBounds(-10,10,10,10);
	line1.setBounds(rect); //Call setBounds with a different parameter
	line1.getPoint1(point1);
	line1.getPoint2(point2);
	CU_ASSERT_EQUAL(-10,point1.getX());
	CU_ASSERT_EQUAL(10,point1.getY());
	CU_ASSERT_EQUAL(0,point2.getX());
	CU_ASSERT_EQUAL(10,point2.getY());

	/*
	 * P1 & P2 is Horizontal, Remain Horizontal
	 * Point1 ( 10, 2) ---> (0 ,10)
	 * Point2 ( -4,  2) ---> (-10, 10)
	 *
	 * The Relative Position of P1 and P2
	 * should remain the same in new bounding box
	 *
	 * Four Corners of the Rect
	 *
	 * (-10,10)--------- (0,10)
	 * 		|			|
	 * 		|			|
	 *		|			|
	 * (-10, 0)--------- (0, 0)
	 *
	 *  width & height = 10 (square)
	 *
	 */
	line2 = {point2,point1}; //swap p2 & p1
	rect.setBounds(-10,10,10,10);
	line2.setBounds(rect); //Call setBounds with a different parameter
	line2.getPoint1(point1);
	line2.getPoint2(point2);
	CU_ASSERT_EQUAL(0,point1.getX());
	CU_ASSERT_EQUAL(10,point1.getY());
	CU_ASSERT_EQUAL(-10,point2.getX());
	CU_ASSERT_EQUAL(10,point2.getY());
	testContains = line2.containsPoint(origin);
	//(0,0) should not be on Line (-10,10) & (0,10)
	CU_ASSERT_FALSE(testContains);
	testContains = line2.containsPoint(point1);
	//Point1 (0,10) should be on Line;
	CU_ASSERT_TRUE(testContains);
	point1.setPoint(-3,10);
	//(-3,10) should be on the Line
	testContains = line2.containsPoint(point1);


	/*
	 * Test A line with angle can be stretched
	 * even after been set to vertical by a box
	 *
	 */
	point1.setPoint(0,20);
	point2.setPoint(20,0);
	Line2D line3(point1,point2); //line3 has a negative slope
	line3.getBounds(rect); //form the original rect/sqaure (0,20,20,20)
	CU_ASSERT_EQUAL(0,rect.getX());
	CU_ASSERT_EQUAL(20,rect.getY());
	CU_ASSERT_EQUAL(20,rect.getWidth());
	CU_ASSERT_EQUAL(20,rect.getHeight());

	line3.setBounds(-5,5,0,10); //change line3 to a vertical line
	line3.getBounds(rect);
	CU_ASSERT_EQUAL(-5,rect.getX());
	CU_ASSERT_EQUAL(5,rect.getY());
	CU_ASSERT_EQUAL(0,rect.getWidth());
	CU_ASSERT_EQUAL(10,rect.getHeight());

	line3.setBounds(5,10,10,20);
	line3.getBounds(rect); //line should be able to be stretched out again
	CU_ASSERT_EQUAL(5,rect.getX());
	CU_ASSERT_EQUAL(10,rect.getY());
	CU_ASSERT_EQUAL(10,rect.getWidth());
	CU_ASSERT_EQUAL(20,rect.getHeight());

	/*
	 * Test a horizontal line cannot be stretched
	 */
	point1.setPoint(0,0);
	point2.setPoint(2,0);
	line3 = {point1,point2}; //line3 is a horizontal line
	line3.getBounds(rect);
	CU_ASSERT_EQUAL(0,rect.getX());
	CU_ASSERT_EQUAL(0,rect.getY());
	CU_ASSERT_EQUAL(2,rect.getWidth());
	CU_ASSERT_EQUAL(0,rect.getHeight());

	line3.setBounds(5,5,10,20); //give line3 a box
	line3.getBounds(rect);
	CU_ASSERT_EQUAL(5,rect.getX());
	CU_ASSERT_EQUAL(5,rect.getY());
	CU_ASSERT_EQUAL(10,rect.getWidth());
	//the height of the bounding should remain 0 still
	CU_ASSERT_EQUAL(0,rect.getHeight());


} /* test Line2D */

/**
 * Test all the functions for this application.
 *
 * @return test error code
 */
static CU_ErrorCode test_all() {
	// initialize the CUnit test registry -- only once per application
	CU_initialize_registry();

	// add a suite to the registry with no init or cleanup
	CU_pSuite pSuite = CU_add_suite("function_tests", NULL, NULL);

	// add the tests to the suite
	CU_add_test(pSuite, "test_Rectangle2D", test_Rectangle2D);
	CU_add_test(pSuite, "test_Circle2D", test_Circle2D);
	CU_add_test(pSuite, "test_Line2D", test_Line2D);

	// run all test suites using the basic interface
	CU_basic_set_mode(CU_BRM_VERBOSE);
	CU_basic_run_tests();

	// display information on failures that occurred


 	//CU_basic_show_failures(CU_get_failure_list());

	// Clean up registry and return status
	CU_cleanup_registry();
	return CU_get_error();
}

/**
 * Main program to invoke test functions
 *
 * @return the exit status of the program
 */
int main(void) {
	// test all the functions
	CU_ErrorCode code = test_all();

	return (code == CUE_SUCCESS) ? EXIT_SUCCESS : EXIT_FAILURE;
}


