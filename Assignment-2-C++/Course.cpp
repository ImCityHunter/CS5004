/*
 * Course.cpp
 *
 *  @since January 10, 2019
 *  @author philip gust & HoKang Yu
 */

#include <cstddef>
#include <cstring>
#include <cassert>

#include "Course.h"
#include "Section.h"

#include <string>

namespace CS_5004{
	//private string courseName;
	//Section **theSections;
	//unsigned int maxSections;
	//Department *theDept;
	//Course(const string &courseName, unsigned int maxSections, Department *dept);
	/**
	 * Create a new course.
	 * @param courseName the course name
	 * @param maxSections max number of sections
	 * @param dept the department
	 */
	Course :: Course (const string &courseName, unsigned int maxSections, Department *dept){
			//assert(courseName != nullptr);
			this->courseName  = courseName;
			this->maxSections = maxSections;
			this->theDept = dept;

			this->theSections = new Section*[maxSections];
			for(int i=0; i< maxSections;i++){
				theSections[i] = nullptr;
			}


	}
	Course :: ~Course(){
		//the Department should not be deleted
		for(int i=0;i<maxSections;i++){
			theSections[i]->~Section();
			delete theSections[i];
		}
		delete[] theSections;
		courseName.clear();
		maxSections = 0;
		theSections = nullptr;
	}
	/**
	 * Returns course name
	 *
	 * @return name of course
	 */
	//const string &name() const;
	const string &Course :: name () const{

		return courseName;
	}

	/**
	 * Gets department for this course.
	 *
	 * @return the department
	 */
	//Department *department() const;
	Department *Course :: department () const {
		return theDept;
	}

	/**
	 * Add a section for this course.
	 *
	 * @param teacher the teacher
	 * @param capacity the course capacity
	 * @return the new section
	 */
	//Section *addSection(Person *teacher, unsigned int capacity);
	Section *Course :: addSection(Person *teacher, unsigned int capacity){
		unsigned int numSection = this->numSections();
		Section *newSection = new Section (teacher,this, numSection+1 ,capacity);
		if(this->numSections()<this->maxSections){
			theSections[this->numSections()] = newSection;
		}
		return newSection;

	}
	/**
	 * Find section by number.
	 *
	 * @param sectionNum the section number
	 * @return the section or
	 */
	//Section *findSection(unsigned int sectionNum) const;
	Section *Course:: findSection(unsigned int sectionNum) const{

		//should maxSections be inclusive or exclusive?
		if(sectionNum >= maxSections ||theSections[sectionNum] == nullptr) {
			printf("\nfindSection Error !!!\n");
			return nullptr; //default
		}
		return theSections[sectionNum];
	}

	/**
	 * Fill in the array of sections for this course, up to nSections.
	 *
	 * @param sections the array of sections
	 * @param nSections the maximum number of sections to return
	 * @return the actual number of sections returned
	 */
	//unsigned int sections(Section *sections[], int nSections) const;
	unsigned int Course:: sections(Section *sections[], int nSections) const{
		unsigned int i = 0;
		while (i<nSections && i<=this->maxSections){
			if(this->theSections[i]==nullptr) return i+1;
			sections[i] = this->theSections[i];
			i++;
		}
		return i+1; // i start from 0
	}

	/**
	 * Returns number of sections currently offered.
	 *
	 * @return number of sections currently offered
	 */
	//unsigned int numSections() const;
	unsigned int Course :: numSections() const{
		unsigned int count = 0;
		for (int i = 0;i < this->maxSections;i++){
			if(theSections[i]!=nullptr) count++;

		}

		return count;
	}

	/**
	 * Returns course enrollment
	 *
	 * @return enrollment for all sections
	 */
	//unsigned int enrollment() const;
	unsigned int Course::enrollment() const{
		unsigned int sum=0;
		int i = 0;
		for(;i < this->numSections();i++){
			Section *s = theSections[i];
			if(s!=nullptr){
				sum = sum +  s->enrollment();
			}

		}
		return sum;
	}







} //close CS_5004
