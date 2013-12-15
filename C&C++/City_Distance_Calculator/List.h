//Header File --> Function declarations


#include "Link.h"

using namespace std;

#ifndef List_H
#define List_H

/**
 * List class
 * This performs functions to do with the linked list
 * e.g. adding, searching and removing
 * @author Ollie, George, JJ
 **/
class List{

public:
	/**
	 * Default Constuctor
	 **/
	List();

	/**
	 * Destructor
	 **/
	~List();

	/**
	 * returns the head pointer of the list
	 * @return	Link object
	 **/
	Link* getHeadpointer();
	
	/**
	 * adds a new object to the list
	 * @param City*		A City to be added
	 **/
	void addObject(City* objectIn);
	
	/**
	 * searches the list for a city based upon
	 * their name and the country they're in
	 * @param string	The name of the city
	 * @param string	The country the city is in
	 * @return 		If found will return the city abject accosiated with the name and country
	 * @return		If not found will return a blank city object
	 **/
	City* searchList(string name, string country);
	
	/**
	 * Removes an object from the list
	 * @param string	The name of the city
	 * @param string	The country the city is in
	 * @return 		True or false depending on if it was successful or not
	 **/
	bool removeObject(string name, string country);

private:
	Link* headPointer;
};



#endif
