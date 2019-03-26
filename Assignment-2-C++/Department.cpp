/*
 * Department.h
 *
 *  @since January 10, 2019
 *  @author philip gust & HoKang Yu
 */
#include <cstdlib>
#include <cstdio>
#include <cstddef>
#include <cassert>
#include <cstring>
#include <string>
#include "Department.h"
#include "Course.h"
#include "Person.h"


using namespace std;

namespace CS_5004 {
///** the department name */
//string deptName;
//Course **theCourses;
//Person **theMembers;
//int maxCourses;
//int maxMembers;
/**
 * Create a new department.
 *
 * @param name department name
 * @param maxCourses the maximum number of courses
 * @param maxMembers the maximum number of members
 */
//Department(const string &name, unsigned int maxCourses,unsigned int maxMembers);
Department::Department(const string &name, unsigned int maxCourses,unsigned int maxMembers){

	this->deptName = name;
	this->maxCourses = maxCourses;
	this->maxMembers = maxMembers;

	this->theCourses = new Course* [maxCourses];
	this->theMembers = new Person* [maxMembers];
	for (int i=0;i<maxMembers;i++){
		theMembers[i] = nullptr;
	}
	for (int i=0;i<maxCourses;i++){
		theCourses[i] = nullptr;
	}

}

/**
 * Department destructor.
 */
//~Department();
Department::~Department(){
	//delete all according instruction
	for(int i=0;i<maxCourses;i++){
		delete theCourses[i];
		theCourses[i]->~Course();
		theCourses[i] = nullptr;
	}
	for(int i=0;i<maxMembers;i++){
		delete theCourses[i];
		theMembers[i]->~Person();
		theMembers[i] = nullptr;
	}
	delete [] theCourses;
	delete [] theMembers;

	deptName = "";
	maxMembers= 0;
	maxCourses= 0;

}
/**
 * Returns the name of the department.
 *
 * @return the name of the department
 */
//const string& name() const;
const string &Department::name() const {
	return deptName;
}

/**
 * Add new student or teacher to the department
 * @param givenName the person's first
 * @param familyName the person's family name
 * @return the new student or teacher, or nullptr if could not add
 */
//Person *addMember(const string &familyName, const string &givenName);
Person* Department::addMember(const string &familyName, const string &givenName){
	//Person(const string &familyName, const string &givenName, Department *dept)

	Person* p;
	p = this->findMember(familyName,givenName);

	unsigned int num = this->numMembers(); //current num of members;
	if(p == nullptr) {
		p = new Person(familyName, givenName, this);
		theMembers[num] = p;
		return p;
	}
	else return nullptr;

}

/**
 * Finds the person by their family and given names
 *
 * @param familyName the family name
 * @param givenName the given name
 * @return the person or nullptr of person not found
 */
//Person *findMember(const string &familyName, const string &givenName) const;
Person *Department::findMember (const string &familyName, const string &givenName) const {
	for (int i=0;i<this->maxMembers;i++){
		Person* p;
		p = this->theMembers[i];
		if (p==nullptr) return nullptr;
		if (familyName.compare(p->familyName())==0 && givenName.compare(p->givenName())==0)return p;
		//if (p->familyName() == familyName && p->givenName() == givenName) return p;
	}


	return nullptr;
}

/**
 * Fill in the array of members of this department, up to maxMembers.
 *
 * @param members the array of members or nullptr for count only
 * @param nMembers the maximum number of members to return
 * @return the actual number of members returned
 */
//unsigned int members(Person *members[], int nMembers) const;
unsigned int Department ::members (Person *members[], int nMembers) const {
	unsigned int i=0;
	for (i = 0;i < nMembers && i<maxMembers;i++){
		if(theMembers[i]==nullptr) return i+1;
		members[i] = theMembers[i];
	}
	return i+1; //start from 0
}
/**
 * Returns number of members currently in the department.
 *
 * @return number of members currently in the department
 */
//unsigned int numMembers() const;
unsigned int Department::numMembers() const{
	unsigned int count = 0;
	for (int i = 0;i<maxMembers;i++){
		if(theMembers[i]!=nullptr) count++;
	}
	return count;
}

/**
 * Add a course for the department.
 *
 * @param courseName the name of the course
 * @param maxSections the maximum number of sections available
 * @return the new course or nullptr if the course could not be added
 */
//Course *addCourse(const string &courseName, unsigned int nSections);
Course *Department:: addCourse(const string &courseName, unsigned int nSections){
	//Course (const string &courseName, unsigned int maxSections, Department *dept)
	Course *c;
	c = this->findCourse(courseName);
	if(c == nullptr){
		c = new Course(courseName,nSections,this);
		theCourses[this->numCourses()] = c;
		return c;
	}
	else return nullptr;
}

/**
 * Finds the course by its course name.
 *
 * @param courseName the course name
 * @return the course or nullptr if course not found
 */
//Course *findCourse(const string &courseName) const;

Course *Department::findCourse(const string &courseName) const{

	for (int i =0 ;i<maxCourses;i++){
		Course *c;
		c = theCourses[i];
		if(c==nullptr) return nullptr;
		if(c->name().compare(courseName)==0) return c;
		//if(c->name() == courseName) return c;
	}
	return nullptr;
}

/**
 * Fill in the array of courses of this department, up to maxCourses.
 *
 * @param courses the array of courses or nullptr for count only
 * @param nCourses the maximum number of courses to return
 * @return the actual number of courses returned
 */
//unsigned int courses(Course *courses[], int nCourses) const;
unsigned int Department :: courses (Course *courses[], int nCourses) const {
	unsigned int i=0;
	for(;i<nCourses;i++){
		if(courses[i]==nullptr) return i+1; //start from 0
	}
	return i+1;
}

/**
 * Returns number of courses currently offered by the department.
 *
 * @return number of courses currently offered by the department
 */
//unsigned int numCourses() const;
unsigned int Department::numCourses() const{

	unsigned int count= 0;
	for (int i = 0; i < this->maxCourses;i++){
		if(theCourses[i]!=nullptr) count++;
	}
	return count;
}

} // namespace CS_5004


