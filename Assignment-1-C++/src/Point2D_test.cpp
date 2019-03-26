/*
 * Point2D_test.cpp
 * @author yu2749luca, Hokang Yu
 */

#include <cstdio>
#include "Point2D.h"
#include <iostream>
#include <cstddef>
#include "CUnit/CUnit.h"
#include "CUnit/Basic.h"


namespace CS_5004 {

static void testPoint2D() {
	// creates vector ((0,0), (2,4))
	Point2D point1(2, 4);
	printf("pint1: %f, %f\n", point1.getX(), point1.getY());

	// creates vector ((0,0),(-2,-4))
	Point2D point2 = -point1;
	printf("-point1: %f, %f\n", point2.getX(), point2.getY());

	// creates vector ((0,0), (0,0))
	Point2D point3 = point1 + point2;
	printf("point1 + (-point1): %f, %f\n", point3.getX(), point3.getY());

	// creates vector ((0,0), (4,8))
	Point2D point4 = point1 - point2;
	printf("point1 - (-point1): %f, %f\n", point4.getX(), point4.getY());

	Point2D point5(0,3);
	Point2D point6(4,0);
	float d = point5.getDistance(point6);
	printf("distance between (0,3)&(4,0) is %f \n",d);




}
static void test_withCUnit(){

	//Test origin
	Point2D pointOrigin(0,0);
	CU_ASSERT_EQUAL(0,pointOrigin.getX());
	CU_ASSERT_EQUAL(0,pointOrigin.getY());

	//Test negative zero
	Point2D pointNegativeZero = -pointOrigin;
	CU_ASSERT_EQUAL(-0,pointNegativeZero.getX());
	CU_ASSERT_EQUAL(-0,pointNegativeZero.getY());
	CU_ASSERT_EQUAL(0,pointNegativeZero.getX());
	CU_ASSERT_EQUAL(0,pointNegativeZero.getY());

	//Test getX,getY
	Point2D point1(4,0);
	CU_ASSERT_EQUAL(4,point1.getX());
	CU_ASSERT_EQUAL(0,point1.getY());


	//test getDistance
	Point2D point2(0,3);
	float distance = point1.getDistance(point2);
	CU_ASSERT_EQUAL(5,distance);

	//test + operator
	Point2D point3 = point1 + point2;
	CU_ASSERT_EQUAL(4,point3.getX());
	CU_ASSERT_EQUAL(3,point3.getY());

	//test negative operator
	Point2D point4 = -point1;
	CU_ASSERT_EQUAL(-4,point4.getX());
	CU_ASSERT_EQUAL(0,point1.getY());

	//test minus operator
	Point2D point5 = point1-point2;
	CU_ASSERT_EQUAL(4,point5.getX());
	CU_ASSERT_EQUAL(-3,point5.getY());

	//test equal
	bool testEqual = (point1 == point1);
	CU_ASSERT_EQUAL(true,testEqual);

	//test not equal
	bool testNotEqual = (point1 != point2);
	CU_ASSERT_EQUAL(true,testNotEqual);

	bool testZero = (pointOrigin == pointNegativeZero);
	CU_ASSERT_EQUAL(true,testZero);


}
static CU_ErrorCode test_all() {
	// initialize the CUnit test registry -- only once per application
	CU_initialize_registry();

	// add a suite to the registry with no init or cleanup
	CU_pSuite pSuite = CU_add_suite("function_tests", NULL, NULL);

	// add the tests to the suite
	CU_add_test(pSuite, "test_withCUnit", test_withCUnit);


	// run all test suites using the basic interface
	CU_basic_set_mode(CU_BRM_VERBOSE);
	CU_basic_run_tests();

	// display information on failures that occurred
	CU_basic_show_failures(CU_get_failure_list());

	// Clean up registry and return status
	CU_cleanup_registry();
	return CU_get_error();
}
}  // namespace CS_5004

/**
 * Main program tests vector methods
 */
int main() {
	 CS_5004::testPoint2D();
	 CU_ErrorCode code = CS_5004::test_all();
	 return (code == CUE_SUCCESS) ? EXIT_SUCCESS : EXIT_FAILURE;
}
