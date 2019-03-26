package edu.northeastern.cs_5004;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * All the test cases for each function
 * @author yu2749luca,HoKang
 * 
 */

import org.junit.Test;

public class Point2D_main {

	@Test
	public void test() {
		
		//test default
		Point2D pointOrigin = new Point2D();
		assertEquals(0,pointOrigin.getX());
		assertEquals(0,pointOrigin.getY());
		assertEquals(-0,pointOrigin.getX());
		assertEquals(-0,pointOrigin.getY());
		
		assertEquals(-0.0,-pointOrigin.getX());
		assertEquals(-0.0,-pointOrigin.getY());
//		assertEquals(-0,-pointOrigin.getX());
//		assertEquals(-0,-pointOrigin.getY());
		
		assertEquals(0.0,0);
		assertEquals(-0.0,0);
		
		//test getX & getY
		Point2D point1 = new Point2D(0,3);
		assertEquals(0,point1.getX());
		assertEquals(3,point1.getY());
		
		Point2D point2 = new Point2D(4,0);
		//test distance
		float distance = point1.getDistance(point2);
		assertEquals(5,distance);
		
		//test plus
		Point2D point3 = new Point2D(0,0);
		point3 = point1.plus(point2);
		assertEquals(3,point3.getY());
		assertEquals(4,point3.getX());
		
		//test negative
		Point2D point4 = point1.minus();
		assertEquals(-0,point4.getX());
		assertEquals( 0,point4.getX());
		assertEquals(-3,point4.getY());
		
		//test minus
		Point2D point5 = point1.minus(point2);
		assertEquals(-4,point5.getX());
		assertEquals(3,point5.getY());
		
		//test equal true
		boolean testEqual = point1.equal(point1);
		assertTrue(testEqual);
		
		Point2D pointNegativeOrigin = new Point2D(-0,-0);
		boolean testZero = pointOrigin.equal(pointNegativeOrigin);
		assertTrue(testZero);
		
		//test equal false
		boolean testNotEqual = point1.equal(point2);
		assertFalse(testNotEqual);
		
		//test hashCode
		int hashCode = point1.hashCode();
		assertNotNull(hashCode);
	}

}
