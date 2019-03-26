/*
 * Person.h
 *
 *  @since January 10, 2019
 *  @author philip gust & HoKang Yu
 */

#include <cstddef>
#include <cassert>
#include <cstring>
#include "Person.h"
#include "Course.h"
#include "Section.h"

namespace CS_5004{

	//string theGivenName;
	//string theFamilyName;
	//Department *theDept;
	/**
	 * Creates a new student or teacher.
	 *
	 * @param familyName the family name
	 * @param givenName the given name
	 * @param dept the department
	 */
	Person::Person(const string &familyName, const string &givenName, Department *dept){
		this->theDept = dept;
		this->theFamilyName = familyName;
		this->theGivenName = givenName;
		//printf("\nPerson Make and Made\n");
	}
	Person::~Person(){
		//should not delete deptName
		theFamilyName.clear();
		theGivenName.clear();
		theDept = nullptr;
	}
	/**
	 * Return the student or teacher given name.
	 *
	 * @return the student or teacher given name.
	 */
	//const string &givenName() const;
	const string &Person::givenName() const{
		return theGivenName;
	}
	/**
	 * Return the student or teacher family name.
	 *
	 * @return the student or teacher family name
	 */
	//const string &familyName() const;
	const string &Person::familyName()const{
		return theFamilyName;
	}
	/**
	 * Return the department of this student or teacher.
	 *
	 * @return the student or teacher department
	 */
	//Department *department() const;
	Department *Person::department() const {
		return theDept;
	}
	/**
	 * Add this student to a section of a course.
	 *
	 * @param course the course
	 * @param sectionNum the section number
	 * @return true if the course was added
	 */
	//bool addCourse(Course* course, unsigned int sectionNum);
	bool Person :: addCourse(Course* course, unsigned int sectionNum){
		bool added = false;

		Section *newSection = course->findSection(sectionNum); //find the section via course
		added = newSection->addStudent(this); //use the addstudent function within section


		return added;
	}
}
