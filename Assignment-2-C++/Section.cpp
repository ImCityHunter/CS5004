/*
 * Section.h
 *
 *  @since January 10, 2019
 *  @author philip gust & HoKang Yu
 */
#include <cstddef>
#include <cassert>
#include <cstring>
#include <string>
//	Person *theTeacher;
//	Person **theStudents;
//	unsigned int theCapacity;
//	Course *theCourse;
//	unsigned int theSectionNum;

#include "Section.h"

namespace CS_5004 {
///** the teacher for the section */
		//Person *theTeacher;
		//Person **theStudents;
		//unsigned int theCapacity;
		//Course *theCourse;
		//unsigned int theSectionNum;
	/**
	 * Create a section of a course with a given capacity.
	 *
	 * @param teacher the teacher for the section
	 * @param course the course
	 * @param sectionNo the section number
	 * @param capacity  the section capacity
	 */
	//Section(Person *teacher, Course *course, unsigned int sectionNum, unsigned int capacity);
	Section :: Section (Person *teacher, Course *course, unsigned int sectionNum, unsigned int capacity){
		this->theTeacher = teacher;
		this->theCourse = course;
		this->theSectionNum = sectionNum;
		this->theCapacity = capacity;
		this->theStudents = new Person*[capacity];

		for (int i=0;i<capacity;i++){
			theStudents[i]=nullptr;
		}
	}
	/**
	 * Section destructor.
	 */
	//~Section();
	Section:: ~Section(){
		theCapacity = 0;
		theSectionNum = 0;
		//delete this;

	}

	/**
	 * Returns the section number.
	 *
	 * @return the section number for this section.
	 */
	//unsigned int sectionNumber() const;
	unsigned int Section::sectionNumber() const{
		return theSectionNum;
	}
	/**
	 * Returns the course for this section.
	 *
	 * @return the course for this section.
	 */
	//Course *course() const ;
	Course *Section::course() const{
		return theCourse;
	}

	/**
	 * The teacher for this section.
	 *
	 * @return the teacher for this section
	 */
	//Person *teacher() const;
	Person *Section::teacher () const{
		return theTeacher;
	}

	/**
	 * Add a student to this section.
	 *
	 * @param student the student
	 * @return true if the student was added, or false
	 * if person is the teacher, the person was already
	 * in the section, or the section is at capacity
	 */
	//bool addStudent(Person *student);
	bool Section::addStudent(Person *student){
		if(student==theTeacher) return false; //check if this is a teacher
		if(this->enrollment() >= theCapacity) return false; //check capacity
		for(unsigned int i=0 ;i<=theCapacity;i++){
			if(theStudents[i]==student) return false; //check if student exists
			if(theStudents[i]==nullptr){
				theStudents[i] = student;
				return true;
			}
		}

		return false;

	}

	/**
	 * Fill in the array of students for this section, up to maxStudents.
	 *
	 * @param students the array of students
	 * @param maxStudents the maximum number of students to return
	 * @return the actual number of students returned
	 */
	//unsigned int students(Person *students[], int maxStudents) const;

	unsigned int Section::students(Person *students[], int maxStudents) const{
		unsigned int i = 0;
		while (i < maxStudents){
			if(this->theStudents[i]!=nullptr){
				students[i] = theStudents[i];
				i++;
			}
		}
		return this->enrollment();
	}
	/**
	 * The number of students enrolled in this section.
	 *
	 * @return the number of students enrolled in this section
	 */
	//unsigned int enrollment() const;
	unsigned int Section::enrollment() const{

		unsigned count = 0 ;
		for (int i=0; i<=this->capacity();i++){
			if(this->theStudents[i]!=nullptr) count+=1;

		}
		return count;
	}
	/**
	 * Returns the section capacity.
	 *
	 * @return the section capacity
	 */
	//unsigned int capacity() const;
	unsigned int Section::capacity() const{
		return theCapacity;
	}

} //namespace CS_5004


