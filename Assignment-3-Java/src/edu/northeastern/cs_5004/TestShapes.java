/**	
 * 	TestShapes.java
 *  Test Functions for Line2D, Rectangle2D, & Circle2D
 *  Author: yu2749luca, HoKang Yu
 */
package edu.northeastern.cs_5004;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * All the test cases for each function
 * @author yu2749luca,HoKang
 * 
 */

import org.junit.Test;

public class TestShapes {
	float diff = 0.00001f;
	/**
	 * Test Rectangle2D
	 */
	@Test
	public void test_Rectangle2D() {
		
		Point2D origin = new Point2D();
		Point2D point1 = new Point2D();
		Rectangle2D rect = new Rectangle2D();
		Rectangle2D rect2 = new Rectangle2D();
		boolean testContains = false;
		float width=10;
		float height=10;
		
		/*
		 * Test Constructor
		 */
		Rectangle2D rectDefault = new Rectangle2D();
		assertEquals(0,rectDefault.getX(),diff);
		assertEquals(0,rectDefault.getY(),diff);
		assertEquals(0,rectDefault.getWidth(),diff);
		assertEquals(0,rectDefault.getHeight(),diff);
		
		/*
		 * Test setBounds
		 */
		point1.setPoint(-10, 10);
		rect.setBounds(point1, width, height);
		assertEquals(-10,rect.getX(),diff);
		assertEquals(10,rect.getY(),diff);
		assertEquals(10,rect.getWidth(),diff);
		assertEquals(10,rect.getHeight(),diff);	
		
		/*
		 * Test getBounds by copying from rect 
		 */
		rect2 = rect.getBounds();
		assertEquals(-10,rect2.getX(),diff);
		assertEquals(10,rect2.getY(),diff);
		assertEquals(10,rect2.getWidth(),diff);
		assertEquals(10,rect2.getHeight(),diff);
		
		/*
		 * Rect: 
		 * 
		 * (-10,10) --------- (0,10)
		 * 		|			|			
		 * 		|			|
		 * 		|			|
		 * (-10,0 ) --------- (0, 0)
		 * 
		 */
		
		/*
		 * Test if four corners of the Rect is included
		 * Test if an out of bound point can be detected
		 */
		testContains = rect.containsPoint(origin); 
		assertTrue(testContains);
		point1.setPoint(-10, 10);
		testContains = rect.containsPoint(point1);
		assertTrue(testContains);
		point1.setPoint(-10, 0);
		testContains = rect.containsPoint(point1);
		assertTrue(testContains);
		point1.setPoint(0, 10);
		testContains = rect.containsPoint(point1);
		assertTrue(testContains);
		point1.setPoint(-10, -10);
		testContains = rect.containsPoint(point1);
		assertFalse(testContains);

	}
	
	/**
	 * Test Circle2D
	 */
	@Test
	public void test_Circle2D() {
		
		Circle2D circleDefault = new Circle2D();
		Circle2D circle1 = new Circle2D();
		Circle2D circle2 = new Circle2D();
		Point2D point1 = new Point2D();
		Point2D point2 = new Point2D();
		Point2D origin = new Point2D();
		Rectangle2D rect = new Rectangle2D();
		boolean testContains=false;
		float width =0;
		float height = 0;
		
		/*
		 * Test Constructor
		 */
		assertEquals(0,circleDefault.getX(),diff);
		assertEquals(0,circleDefault.getY(),diff);
		assertEquals(0,circleDefault.getWidth(),diff);
		assertEquals(0,circleDefault.getHeight(),diff);
		
		/*
		 * Test setbounds
		 */
		point1.setPoint(-10, 10);
		width=10;
		height=10;
		circle1.setBounds(point1, width, height);
		assertEquals(-10,circle1.getX(),diff);
		assertEquals(10,circle1.getY(),diff);
		assertEquals(10,circle1.getWidth(),diff);
		assertEquals(10,circle1.getHeight(),diff);
		
		/*
		 * Test getBounds by copying from rect 
		 */
		rect = circle1.getBounds();
		assertEquals(-10,rect.getX(),diff);
		assertEquals(10,rect.getY(),diff);
		assertEquals(10,rect.getWidth(),diff);
		assertEquals(10,rect.getHeight(),diff);
		
		/*
		 * Circle1 bounds: 
		 * width=10,height=10
		 * 
		 * (-10,10) --------- (0,10)
		 * 		|			|			
		 * 		|			|
		 * 		|			|
		 * (-10,0 ) --------- (0, 0)
		 * 
		 */
		
		/*
		 * Test if four corners of the Rect is included
		 * Test if an inbound point can be detected
		 */
		testContains = circle1.containsPoint(origin); 
		assertFalse(testContains);
		point1.setPoint(-10, 10);
		testContains = circle1.containsPoint(point1);
		assertFalse(testContains);
		point1.setPoint(-10, 0);
		testContains = circle1.containsPoint(point1);
		assertFalse(testContains);
		point1.setPoint(0, 10);
		testContains = circle1.containsPoint(point1);
		assertFalse(testContains);
		point1.setPoint(-5, 5);
		testContains = circle1.containsPoint(point1);
		assertTrue(testContains);
		
		/*
		 * Circle2 bounds: 
		 * width = 20
		 * height = 10
		 * 
		 * (-10,10) ------------- (10,10)
		 * 		|					|			
		 * 		|					|
		 * 		|					|
		 * (-10,0 ) ------------- (10, 0)
		 * 
		 */
		width=20;
		height=10;
		circle2.setBounds(-10,10,width,height);
		/*
		 * Test if four corners of the Rect is included
		 * Test if an inbound point can be detected
		 */
		point1.setPoint(10, 0);
		testContains = circle2.containsPoint(point1); 
		assertFalse(testContains);
		point1.setPoint(-10, 10);
		testContains = circle2.containsPoint(point1);
		assertFalse(testContains);
		point1.setPoint(-10, 0);
		testContains = circle2.containsPoint(point1);
		assertFalse(testContains);
		point1.setPoint(10, 10);
		testContains = circle2.containsPoint(point1);
		assertFalse(testContains);
		point1.setPoint(0, 5);
		testContains = circle2.containsPoint(point1);
		assertTrue(testContains);
		
		/*
		 * Test width=0 || height=0
		 */
		rect = new Rectangle2D(0,8,0,8);
		circle1.setBounds(rect);
		point1.setPoint(0, 8); //should be in bound
		testContains = circle1.containsPoint(point1);
		assertTrue(testContains);
		point1.setPoint(0,0);//should be in bound
		testContains = circle1.containsPoint(point1);
		assertTrue(testContains);	
		point2.setPoint(0, 8.11f); //should be out of bound
		testContains = circle1.containsPoint(point2);
		assertFalse(testContains);
		
		rect = new Rectangle2D(5,0,5,0);
		circle1.setBounds(rect);
		point1.setPoint(10, 0);
		testContains = circle1.containsPoint(point1);
		assertTrue(testContains);
		point2.setPoint(4.99f, 0);
		testContains = circle2.containsPoint(point2);
		assertFalse(testContains); //should be out of bound
		
		point2.setPoint(10, 10);
		testContains = circle2.containsPoint(point2);
		assertFalse(testContains); //should be out of bound
		
		
		
		
	}

	/**
	 * Test Line2D
	 */
	@Test
	public void test_Line2D() {
		Point2D point1 =  new Point2D();
		Point2D point2 =  new Point2D();
		Point2D origin =  new Point2D();
		Point2D containPoint = new Point2D();
		Point2D leftTop =  new Point2D();
		Rectangle2D rect = new Rectangle2D();
		boolean testContains = false;
		float width = 0;
		float height = 0;
		Line2D line1 = new Line2D();
		/*
		 * Test Constructor
		 */
		point1=line1.getPoint1();
		point2=line1.getPoint2();
		assertEquals(0,point1.getX(),diff);
		assertEquals(0,point1.getY(),diff);
		assertEquals(0,point2.getX(),diff);
		assertEquals(0,point2.getX(),diff);
		
		/*
		 * Test setBounds when two points overlap
		 * New two points should have the same X & Y coordinates
		 */
		line1 = new Line2D(point1,point2);
		leftTop.setPoint(-10, 10);
		width=10;
		height=10;
		line1.setBounds(leftTop, width, height);
		point1 = line1.getPoint1();
		point2 = line1.getPoint2();
		assertEquals(-10,point1.getX(),diff);
		assertEquals(10,point1.getY(),diff);
		assertEquals(-10,point2.getX(),diff);
		assertEquals(10,point2.getY(),diff);
		containPoint.setPoint(-9.99f, 10.001f); //should not be in bound
		testContains = line1.containsPoint(containPoint);
		assertFalse(testContains);
		containPoint.setPoint(-10f, 10f); //should be in bound
		testContains = line1.containsPoint(containPoint);
		assertTrue(testContains);
		/*
		 * Test GetBounds when two points overlap
		 */
		rect = line1.getBounds();
		assertEquals(-10,rect.getX());
		assertEquals(10,rect.getY());
		assertEquals(0,rect.getWidth());
		assertEquals(0,rect.getHeight());
		
		
		
		/*
		 * Test setbounds for a horizontal line
		 * 
		 * (-10,10) ------------------ (0,10)
		 *  point1 						point2
		 * new result line should be ^
		 * 
		 */
		point1.setPoint(0, 2);
		point2.setPoint(7, 2);
		line1 = new Line2D(point1,point2);
		width=10;
		height=10;
		line1.setBounds(leftTop, width, height);
		point1 = line1.getPoint1();
		point2 = line1.getPoint2();
		assertEquals(-10,point1.getX(),diff);
		assertEquals(10,point1.getY(),diff);
		assertEquals(0,point2.getX(),diff);
		assertEquals(10,point2.getY(),diff);
		
		containPoint.setPoint(-5, 10); //test containsPoint
		testContains=line1.containsPoint(containPoint);
		assertTrue(testContains);
		testContains=line1.containsPoint(origin);
		assertFalse(testContains);
		
		/*
		 * Test setbounds for a horizontal line
		 * 
		 * point2 & point1 switches position
		 * 
		 * (-10,10) ------------------ (0,10)
		 *  point2 						point1
		 * new result line should be ^
		 * 
		 */
		point1.setPoint(5, 2);
		point2.setPoint(7, 2);
		line1 = new Line2D(point2,point1); //swap p1, p2
		line1.setBounds(leftTop, width, height);
		point1 = line1.getPoint1();
		point2 = line1.getPoint2();
		assertEquals(0,point1.getX(),diff);
		assertEquals(10,point1.getY(),diff);
		assertEquals(-10,point2.getX(),diff);
		assertEquals(10,point2.getY(),diff);
		
		/*
		 * Test GetBounds For a Horizontal Line
		 */
		rect = line1.getBounds();
		assertEquals(-10,rect.getX(),diff);
		assertEquals(10,rect.getY(),diff);
		assertEquals(10,rect.getWidth(),diff);
		assertEquals(0,rect.getHeight(),diff);
		
		/*
		 * Test setBounds for a vertical Line
		 * 
		 * (-10,10) point1
		 * 	|
		 * 	|
		 * 	|
		 * 	|
		 * 	|
		 * (-10, 0) point2
		 *  
		 *  ^ new line should look like this
		 *  
		 */
		point1.setPoint(0,5);
		point2.setPoint(0,2);
		line1 = new Line2D(point1,point2);
		//call setBounds with a different parameter
		line1.setBounds(leftTop.getX(),leftTop.getY(),width,height); 
		point1 = line1.getPoint1();
		point2 = line1.getPoint2();
		assertEquals(-10,point1.getX(),diff);
		assertEquals(10,point1.getY(),diff);
		assertEquals(-10,point2.getX(),diff);
		assertEquals(0,point2.getY(),diff);
		
		containPoint.setPoint(-10,5);
		testContains=line1.containsPoint(containPoint);
		assertTrue(testContains);
		testContains=line1.containsPoint(origin);
		assertFalse(testContains);
		
		/*
		 * Test GetBounds For a Vertical Line
		 */
		rect = line1.getBounds();
		assertEquals(-10,rect.getX(),diff);
		assertEquals(10,rect.getY(),diff);
		assertEquals(0,rect.getWidth(),diff);
		assertEquals(10,rect.getHeight(),diff);
		
		/*
		 * Test SetBounds for a Positive Slope
		 * 
		 * 
		 * (-10,10) ------- (0,10) point2
		 * 		|			|
		 * 		|			|
		 * 		|			|
		 * (-10, 0) ------- (0, 0)
		 * point1
		 * 
		 * ^ new point position
		 */
		point1.setPoint(-2, -2);
		point2.setPoint(2, 2);
		line1 = new Line2D(point1,point2);
		line1.setBounds(leftTop,width,height);
		point1 = line1.getPoint1();
		point2 = line1.getPoint2();
		assertEquals(-10,point1.getX(),diff);
		assertEquals(0,point1.getY(),diff);
		assertEquals(0,point2.getX(),diff);
		assertEquals(10,point2.getY(),diff);
		
		containPoint.setPoint(-5, 5); //test containsPoint
		testContains=line1.containsPoint(containPoint);
		assertTrue(testContains);
		testContains=line1.containsPoint(origin);
		assertFalse(testContains);
		
		/*
		 * Test SetBounds for a Positive Slope
		 * Switch Point1 & Point2 Position
		 * 
		 * (-10,10) ------- (0,10) point1
		 * 		|			|
		 * 		|			|
		 * 		|			|
		 * (-10, 0) ------- (0, 0)
		 * point2
		 * 
		 * ^ new point position
		 */
		point1.setPoint(-2, -2);
		point2.setPoint(2, 2);
		line1 = new Line2D(point2,point1); //swap p1, p2
		line1.setBounds(leftTop,width,height);
		point1 = line1.getPoint1();
		point2 = line1.getPoint2();
		assertEquals(0,point1.getX(),diff);
		assertEquals(10,point1.getY(),diff);
		assertEquals(-10,point2.getX(),diff);
		assertEquals(0,point2.getY(),diff);
		

		
		/*
		 * Test SetBounds for a Negative Slope
		 * 
		 * 
		 * Point1
		 * (-10,10) ------- (0,10) 
		 * 		|			|
		 * 		|			|
		 * 		|			|
		 * (-10, 0) ------- (0, 0)
		 * 					point2
		 * 
		 * ^ new point position
		 */
		point1.setPoint(-20, 0);
		point2.setPoint(0, -20);
		line1 = new Line2D(point1,point2);
		line1.setBounds(leftTop,width,height);
		point1 = line1.getPoint1();
		point2 = line1.getPoint2();
		assertEquals(-10,point1.getX(),diff);
		assertEquals(10,point1.getY(),diff);
		assertEquals(0,point2.getX(),diff);
		assertEquals(0,point2.getY(),diff);
		
		containPoint.setPoint(-5, 5); //test containsPoint
		testContains=line1.containsPoint(containPoint);
		assertTrue(testContains);
		testContains=line1.containsPoint(origin);
		assertTrue(testContains);

		
		/*
		 * Test GetBounds For Negative Slope
		 */
		rect = line1.getBounds();
		assertEquals(-10,rect.getX(),diff);
		assertEquals(10,rect.getY(),diff);
		assertEquals(10,rect.getWidth(),diff);
		assertEquals(10,rect.getHeight(),diff);
		
		/*
		 * Test SetBounds for a Negative Slope
		 * With two points swap position
		 * 
		 * Point2
		 * (-10,10) ------- (0,10) 
		 * 		|			|
		 * 		|			|
		 * 		|			|
		 * (-10, 0) ------- (0, 0)
		 * 					point1
		 * 
		 * ^ new point position
		 */
		point1.setPoint(-20, 0);
		point2.setPoint(0, -20);
		line1 = new Line2D(point2,point1); //swap p1, p2
		//call setBounds with a different parameter
		line1.setBounds(rect);
		point1 = line1.getPoint1();
		point2 = line1.getPoint2();
		assertEquals(0,point1.getX(),diff);
		assertEquals(0,point1.getY(),diff);
		assertEquals(-10,point2.getX(),diff);
		assertEquals(10,point2.getY(),diff);
		
		/*
		 * Test if a sloped line can be stretched
		 * a line with originally a slope: -1
		 * give the line a box with 0 height, and become horizontal
		 * 
		 * point1 = (-10,10) ----> (0,0)
		 * point2 = (0,0) -------> (10,0)
		 */
		point1.setPoint(-10, 10);
		point2.setPoint(0, 0);
		line1 = new Line2D(point1,point2); //make new line
		line1.setBounds(0,0,10,0);
		point1 = line1.getPoint1();
		point2 = line1.getPoint2();
		assertEquals(0,point1.getX(),diff);
		assertEquals(0,point1.getY(),diff);
		assertEquals(10,point2.getX(),diff);
		assertEquals(0,point2.getY(),diff);
		
		//stretch the line again with a rect
		rect = new Rectangle2D(-10,10,10,10);
		line1.setBounds(rect);
		point1 = line1.getPoint1(); //(-10,10)
		point2 = line1.getPoint2(); // (0,0)
		assertEquals(-10,point1.getX(),diff);
		assertEquals(10,point1.getY(),diff);
		assertEquals(0,point2.getX(),diff);
		assertEquals(0,point2.getY(),diff);
		
		/*
		 * Test if a vertical line remains vertical 
		 * when given a rectangle 
		 */
		point1.setPoint(0,4);
		point2.setPoint(0, 0);
		line1 = new Line2D (point1,point2);
		rect = new Rectangle2D(-10,10,10,10);
		line1.setBounds(rect);
		point1 = line1.getPoint1(); //(-10,10)
		point2 = line1.getPoint2(); //(-10, 0)
		assertEquals(-10,point1.getX(),diff);
		assertEquals(10,point1.getY(),diff);
		assertEquals(-10,point2.getX(),diff);
		assertEquals(0,point2.getY(),diff);	
		
		

		
		
	}
}
